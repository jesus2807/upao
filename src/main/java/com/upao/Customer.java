package com.upao;


public class Customer {
    private String name;
    private String dni;
    private ProductSale[] sales = new ProductSale[0];

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Nombre: ").append(name).append('\n');
        sb.append("DNI: '").append(dni).append('\n');
        sb.append("Ventas:\n");
        sb.append("++++++++++++++++++++++\n");
        for (ProductSale element : sales) {
            sb.append(element);
            sb.append("++++++++++++++++++++++\n");
        }
        return sb.toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public ProductSale[] getSales() {
        return sales;
    }

    public void setSales(ProductSale[] sales) {
        this.sales = sales;
    }
}
