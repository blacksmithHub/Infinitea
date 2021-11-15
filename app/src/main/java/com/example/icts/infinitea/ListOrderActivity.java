package com.example.icts.infinitea;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ArrayAdapter;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ListOrderActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<OrderDetailClass> orderList;
    TextView lblTotal;

    TeaListDataAdapter teaAdapter;
    TeaClass teaDetail;

    float totalPrice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_order);
        teaDetail = new TeaClass();
        orderList = (ArrayList<OrderDetailClass>) getIntent().getSerializableExtra("orderList");

        lblTotal = (TextView) findViewById(R.id.txtTotalPrice);

        listView = (ListView) findViewById(R.id.listView);
        teaAdapter = new TeaListDataAdapter(getBaseContext(), R.layout.custom_listview_layout, orderList);
        listView.setAdapter(teaAdapter);

        //compute total
        for (OrderDetailClass orbobj: orderList) {
            int milktea = orbobj.getMilktea();
            int quant = orbobj.getQuantity();
            int size = orbobj.getTeasize();
            int addons =orbobj.getAddons();
            double teaprice =teaDetail.getTeaprice(milktea,size);
            double addonprice =teaDetail.getAddonsprice(addons);

            totalPrice += ((teaprice+addonprice) * quant);
            lblTotal.setText("Total Amount: Php " + String.format("%,.2f", totalPrice));
        }
    }

    private class TeaListDataAdapter extends ArrayAdapter<OrderDetailClass> {

        private ArrayList<OrderDetailClass> teaList;
        int layoutResID;

        public TeaListDataAdapter(Context context, int resourceLayoutID,
                                    ArrayList<OrderDetailClass> listObj) {
            super(context, resourceLayoutID, listObj);
            // TODO Auto-generated constructor stub
            this.layoutResID = resourceLayoutID;
            this.teaList = new ArrayList<OrderDetailClass>();
            this.teaList.addAll(listObj);
        }


        //adding new set of arraylist (custom)
        public void addAll(ArrayList<OrderDetailClass> obj) {
            // TODO Auto-generated method stub
            teaList.clear();
            teaList.addAll(obj);
        }

        @Override
        public void remove(OrderDetailClass object) {
            // TODO Auto-generated method stub
            super.remove(object);
            teaList.remove(object);

            totalPrice = 0;
            //compute total
            for (OrderDetailClass orbobj: teaList) {
                int milktea = orbobj.getMilktea();
                int quant = orbobj.getQuantity();

                int size = orbobj.getTeasize();
                totalPrice += ( teaDetail.getTeaprice(milktea,size) * quant);
                lblTotal.setText("Total Amount: Php " + String.format("%,.2f", totalPrice));
            }
        }

        private class ViewHolder {
            TextView lblName,lblSugarSize,lblAdd,lblPrice, lblDine, lblQuantity,lblTotalP,lblPricePerUnit;
            ImageView imgtea;
            Button btndelete;



        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub

            ViewHolder holder = null;
            View view = convertView;

            if (convertView == null) {

                LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = vi.inflate(layoutResID, null);

                holder = new ViewHolder();
                holder.lblName = (TextView) view.findViewById(R.id.txtName);
                holder.lblPricePerUnit = (TextView) view.findViewById(R.id.txtPricePerUnit);
                holder.lblDine = (TextView) view.findViewById(R.id.txtDine);
                holder.lblSugarSize = (TextView) view.findViewById(R.id.txtTeaSugarSize);
                holder.lblPrice = (TextView) view.findViewById(R.id.txtPrice);
                holder.lblQuantity = (TextView) view.findViewById(R.id.txtQuantity);
                holder.imgtea = (ImageView) view.findViewById(R.id.teaOrderImg);
                holder.btndelete = (Button) view.findViewById(R.id.btnDelete);
                view.setTag(holder);

                holder.btndelete.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        Button btnTag = (Button) v;
                        OrderDetailClass iconObj = (OrderDetailClass) btnTag.getTag();
                        teaAdapter.remove(iconObj);
                        teaAdapter.notifyDataSetChanged();

                    }
                });

            }else{
                holder = (ViewHolder) view.getTag();
            }

            try {
                OrderDetailClass teaObj = teaList.get(position);
                int mealOrderNo = teaObj.getMilktea();
                int size = teaObj.getTeasize();
                String repairSTR = "";
                int sugar = teaObj.getSugarlevel();
                int addons=teaObj.getAddons();
                int qty=teaObj.getQuantity();
                if (teaObj.isDine()) {
                    repairSTR = "Take Out";
                }else {
                    repairSTR = "Dine-In";

                }

                //String.format("%,.2f",(qty*priceunit))
                double teaprice =teaDetail.getTeaprice(mealOrderNo,size);
                double addonprice =teaDetail.getAddonsprice(addons);
                holder.lblName.setText( teaDetail.getTeaflavor(mealOrderNo));
                holder.lblDine.setText(repairSTR);
                holder.lblPricePerUnit.setText("Price Per Unit: " + String.format("%,.2f",teaprice)+" || "+teaDetail.getTeaaddons(teaObj.getAddons())+": "+String.format("%,.2f",addonprice));

                holder.lblSugarSize.setText("Sugar: "+teaDetail.getSugarlevel(sugar)+" || Size: "+ teaDetail.getTeasize(size));

                double total=teaprice+addonprice;
                holder.lblPrice.setText("Price/Unit: " +  String.format("%,.2f",total));
                holder.lblQuantity.setText("Quantity: " + qty +" || Total: "+String.format("%,.2f",qty*total));
                holder.imgtea.setBackgroundResource( teaDetail.getImageArray(mealOrderNo));
                holder.btndelete.setTag(teaObj);

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            return view;
        }

    }
}
