package com.example.icts.infinitea;

/**
 * Created by icts on 12/6/2016.
 */
public class TeaClass {
    /*private String jeansName[];
    private String jeansDesc[];
    private float jeansPrice[];
    private int jeansImage[];
    private double teaprice[][];*/
    private int imageArray[];
    private String sugarlevel[];
    private String teasize[];
    private String teaflavor[];
    private String teaaddons[];
    private Double addonsprice[];
    private Double teaprice[][];
    public TeaClass(){
        sugarlevel = new String[]{"No Sugar","25% Sugar","50% Sugar","75% Sugar","Full Sugar"};
        teasize = new String[]{"Small","Medium","Large"};
         imageArray  = new int[] {R.drawable.milktea, R.drawable.milktea1, R.drawable.images};
         teaflavor=new String[]{"Milo Dinosaur","Oreo Milktea","Blueberry Cheesecake Frappe"};
       teaaddons=new String[]{"No Add Ons","Almond","Boba","Pudding"};
         addonsprice=new Double[]{0.00,10.00,11.00,13.00};
         teaprice=new Double[][]{{70.00,80.00,90.00},{72.00,82.00,92.00},{80.00,90.00,100.00}};
    }

    public int getImageArray(int index) {
        return imageArray[index];
    }



    public Double getAddonsprice(int index) {
        return addonsprice[index];
    }

    public String getTeaflavor(int index) {
        return teaflavor[index];
    }

    public String getSugarlevel(int index) {
        return sugarlevel[index];
    }

    public String getTeaaddons(int index) {
        return teaaddons[index];
    }

    public String getTeasize(int index) {
        return teasize[index];
    }
    public double getTeaprice(int row, int col) {
        return teaprice[row][col];
    }

}
