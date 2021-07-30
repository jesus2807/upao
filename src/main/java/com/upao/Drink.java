package com.upao;

public class Drink {
    protected String name;
    protected char drinkType;
    protected double unitPrice;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getDrinkType() {
        return drinkType;
    }

    public void setDrinkType(char drinkType) {
        this.drinkType = drinkType;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }
}
