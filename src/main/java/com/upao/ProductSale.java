package com.upao;

public class ProductSale extends Drink{
    private int quantity;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Cantidad: ").append(quantity).append('\n');
        sb.append("Nombre: ").append(name).append('\n');
        sb.append("Tipo: ").append(drinkType).append('\n');
        sb.append("UnitPrice: ").append(unitPrice).append('\n');
        return sb.toString();
    }
}
