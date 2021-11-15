package com.example.icts.infinitea;

import android.content.DialogInterface;
import android.content.Intent;
import android.icu.text.DecimalFormat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    SeekBar seekSize = null,seekPercentage = null,seekAdd=null;
    EditText txtQty;
    int currentImage=0;
    ImageButton milktea;
    ToggleButton dine;

    TextView txtTea,txtSize,txtPercentage,txtAdd,txtAddOn,txtPrice,txtAddPrice,txtPriceUnit,txtTotal;
    Button btnAdd,btnView;

    ArrayList<OrderDetailClass> orderTea;
    TeaClass teaDetails;

    int random;

    int[] imageArray  = new int[] {R.drawable.milktea, R.drawable.milktea1, R.drawable.images};
    public String sugarlevel[]={"No Sugar","25% Sugar","50% Sugar","75% Sugar","Full Sugar"};
    public String teasize[]={"Small","Medium","Large"};
    public String teaflavor[]={"Milo Dinosaur","Oreo Milktea","Blueberry Cheesecake Frappe"};
    public String teaaddons[]={"No Add Ons","Almond","Boba","Pudding"};
    public Double addonsprice[]={0.00,10.00,11.00,13.00};
    public Double teaprice  [][]={{70.00,80.00,90.00},{72.00,82.00,92.00},{80.00,90.00,100.00}};


    private void setDefaultTea() {

        dine.setChecked(false);
        seekAdd.setProgress(0);
        seekSize.setProgress(0);
        seekPercentage.setProgress(0);
        txtQty.setText("");
        random=(int) (Math.random()*2);
        milktea.setImageResource(imageArray[random]);
        info();

    }

    public void info(){
        txtPercentage.setText(sugarlevel[seekPercentage.getProgress()]);
        txtSize.setText(teasize[seekSize.getProgress()]);
        txtAdd.setText(teaaddons[seekAdd.getProgress()]);
        txtAddOn.setText(teaaddons[seekAdd.getProgress()]);
        txtTea.setText(teaflavor[random]+" ||| "+teasize[seekSize.getProgress()]+" ||| "+sugarlevel[seekPercentage.getProgress()]+" ||| "+teaaddons[seekAdd.getProgress()]);
        double teaP = teaprice[random][seekSize.getProgress()];
        double addP =addonsprice[seekAdd.getProgress()];
        txtPrice.setText(String.format("%,.2f",(teaP)));
        txtAddPrice.setText(String.format("%,.2f",(addP)));
        try
        {
            double priceunit = teaP+addP;
            txtPriceUnit.setText(String.format("%,.2f",(priceunit)));
            txtPriceUnit.setText(String.format("%,.2f",(teaP+addP)));
            double qty;
            if (txtQty.getText().toString().equals(""))
            {qty=0;}
                else
            {qty = Double.parseDouble(txtQty.getText().toString());}

            txtTotal.setText(String.format("%,.2f",(qty*priceunit)));}

        catch (Exception e){

        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        dine=(ToggleButton)findViewById(R.id.toggleButton);

        teaDetails = new TeaClass();
        orderTea = new ArrayList<OrderDetailClass>();

        seekSize=(SeekBar) findViewById(R.id.seekSize);
        seekPercentage=(SeekBar) findViewById(R.id.seekPercentage);
        seekAdd=(SeekBar) findViewById(R.id.seekAdd);

        milktea = (ImageButton) findViewById(R.id.btnImage);

        txtPrice=(TextView) findViewById(R.id.txtPrice);
        txtPriceUnit=(TextView) findViewById(R.id.txtPriceUnit);
        txtTotal=(TextView) findViewById(R.id.txtTotal);
        txtAddPrice=(TextView) findViewById(R.id.txtAddPrice);
        txtAddOn=(TextView) findViewById(R.id.txtAddOn);
        txtTea=(TextView) findViewById(R.id.txtTea);
        txtAdd=(TextView) findViewById(R.id.txtAdd);
        txtSize=(TextView) findViewById(R.id.txtSize);
        txtPercentage=(TextView) findViewById(R.id.txtPercentage);
        txtQty = (EditText) findViewById(R.id.txtQty);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnView = (Button) findViewById(R.id.btnView);

        currentImage=0;

        random=(int) (Math.random()*2);
        milktea.setImageResource(imageArray[random]);
        info();

       milktea.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {

                random++;
                random = random % imageArray.length;

                milktea.setImageResource(imageArray[random]);
                info();

            }



        });

        seekSize.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChanged = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
                progressChanged = progress;
                txtSize.setText(teasize[progressChanged]);
                info();
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        seekAdd.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChanged = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
                progressChanged = progress;
                txtAdd.setText(teaaddons[progressChanged]);
                txtAddOn.setText(teaaddons[progressChanged]);
                info();

            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        seekPercentage.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChanged = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
                progressChanged = progress;

                txtPercentage.setText(sugarlevel[progressChanged]);
                info();
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {


            }
        });

        txtQty.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO Auto-generated method stub
            info();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            info();



            }

        });

       btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (txtQty.getText().toString().equals("0")||txtQty.getText().toString().equals("")) {

                    AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                    alert.setTitle("Order Error");
                    alert.setMessage("Please add at least one quantity per order...");
                    alert.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Write your code here to execute after dialog closed

                        }
                    }).create();
                    alert.show();


                } else {

                    OrderDetailClass newOrder = new OrderDetailClass();
                    newOrder.setMilktea(random);
                    newOrder.setDine(dine.isChecked());
                    newOrder.setSugarlevel(seekPercentage.getProgress());
                    newOrder.setQuantity(Integer.parseInt(txtQty.getText().toString()));
                    newOrder.setTeasize(seekSize.getProgress());
                    newOrder.setAddons(seekAdd.getProgress());

                    orderTea.add(newOrder);
                    setDefaultTea();

                    AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                    alert.setTitle("Add Order");
                    alert.setMessage("Please successfully added new order...");
                    alert.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Write your code here to execute after dialog closed

                        }
                    }).create();
                    alert.show();

                }
            }
        });
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(orderTea.size() == 0) {
                    android.app.AlertDialog.Builder alert = new android.app.AlertDialog.Builder(MainActivity.this);
                    alert.setTitle("Order Error");
                    alert.setMessage("Please add at least one order in cart...");
                    alert.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Write your code here to execute after dialog closed

                        }
                    }).create();
                    alert.show();
                } else {

                    Intent openAct = new Intent(MainActivity.this, ListOrderActivity.class);
                    openAct.putExtra("orderList", orderTea);
                    startActivity(openAct);

                    //finish();
                }

            }
        });
    }
    }


