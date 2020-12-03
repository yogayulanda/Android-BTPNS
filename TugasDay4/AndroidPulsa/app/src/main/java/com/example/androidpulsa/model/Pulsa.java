package com.example.androidpulsa.model;

public class Pulsa {
    private int id;
    private String code;
    private double nominal;
    private double price;

    public Pulsa() {
    }

    public Pulsa(String code, double nominal) {
        this.code = code;
        this.nominal = nominal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getNominal() {
        return nominal;
    }

    public void setNominal(double nominal) {
        this.nominal = nominal;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

