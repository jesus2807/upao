package com.upao;

public class ProductInventory extends Product{
    private int stock;

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Id: ").append(id).append('\n');
        sb.append("Nombre: ").append(name).append('\n');
        sb.append("Tipo: ").append(drinkType).append('\n');
        sb.append("UnitPrice: ").append(unitPrice).append('\n');
        sb.append("Stock: ").append(stock).append('\n');
        sb.append("Cantidad vendida: ").append(NT).append('\n');
        return sb.toString();
    }
}
