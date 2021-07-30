package com.upao;

public class Product extends Drink {
    protected String id;
    protected int NT = 0;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getNT() {
        return NT;
    }

    public void setNT(int NT) {
        this.NT = NT;
    }
}
