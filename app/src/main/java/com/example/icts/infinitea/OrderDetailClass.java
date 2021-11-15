package com.example.icts.infinitea;

import java.io.Serializable;

/**
 * Created by icts on 12/6/2016.
 */
public class OrderDetailClass implements Serializable {
    private int milktea;
    private int sugarlevel;
    private int teasize;
    private int addons;
    private int quantity;
    private boolean dine;


    public OrderDetailClass() {
        this.milktea = 0;
        this.sugarlevel = 0;
        this.teasize = 0;
        this.addons = 0;
        this.quantity = 0;
        this.dine = false;
    }


    public OrderDetailClass(int milktea, int sugarlevel, int teasize, int addons, int quantity, boolean dine) {
        this.milktea = milktea;
        this.sugarlevel = sugarlevel;
        this.teasize = teasize;
        this.addons = addons;
        this.quantity = quantity;
        this.dine = dine;

    }

    public boolean isDine() {
        return dine;
    }

    public void setDine(boolean dine) {
        this.dine = dine;
    }

    public int getQuantity() {

        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getAddons() {

        return addons;
    }

    public void setAddons(int addons) {
        this.addons = addons;
    }

    public int getTeasize() {

        return teasize;
    }

    public void setTeasize(int teasize) {
        this.teasize = teasize;
    }

    public int getSugarlevel() {

        return sugarlevel;
    }

    public void setSugarlevel(int sugarlevel) {
        this.sugarlevel = sugarlevel;
    }

    public int getMilktea() {

        return milktea;
    }

    public void setMilktea(int milktea) {
        this.milktea = milktea;
    }
    /*public String toString() {
        return "OrderDetailClass{" +
                "jeansNo=" + jeansNo +
                ", lengthPercent=" + (lengthPercent)+
                ", waistPercent=" + (waistPercent) +
                ", orderQuantity=" + orderQuantity +
                ", repair=" + typerepair +
                '}';
    }*/
}


