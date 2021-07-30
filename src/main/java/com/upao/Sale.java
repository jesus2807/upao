package com.upao;


import java.util.Locale;

public class Sale {
    private ArrayHelper arrayHelper = new ArrayHelper();
    private Customer[] customers = new Customer[0];

    public Customer sale(String nameCustomer, String dniCustomer, int quantityProduct, ProductInventory productInventory) {
       Customer customer = null;
       if (productInventory != null) {
           customer = this.getCustomer(dniCustomer);
           if (customer == null) {
               customer = new Customer();
               customer.setName(nameCustomer.toUpperCase(Locale.ROOT));
               customer.setDni(dniCustomer);
               customers = arrayHelper.addElement(customers, customer);
           }
           ProductSale productSale = this.getProductSale(customer, productInventory.getName(), productInventory.getDrinkType(), productInventory.getUnitPrice());
           if (productSale == null) {
               productSale = new ProductSale();
               productSale.setName(productInventory.getName());
               productSale.setDrinkType(productInventory.getDrinkType());
               productSale.setUnitPrice(productInventory.getUnitPrice());
               productSale.setQuantity(quantityProduct);
               customer.setSales(arrayHelper.addElement(customer.getSales(), productSale));
           } else {
               productSale.setQuantity(productSale.getQuantity() + quantityProduct);
           }

           productInventory.setStock(productInventory.getStock() - quantityProduct);
           productInventory.setNT(productInventory.getNT() + quantityProduct);
       }
       return customer;
    }

    public Customer getCustomer(String dni) {
        Customer result = null;
        for (Customer customer : customers) {
            if (customer.getDni().equals(dni)) {
                result = customer;
                break;
            }
        }

        return result;
    }

    public Customer[] getCustomerByName(String name) {
        Customer[] result = new Customer[0];
        for (Customer customer : customers) {
            if (customer.getName().equalsIgnoreCase(name)) {
                result = arrayHelper.addElement(result, customer);
            }
        }

        return result;
    }

    private ProductSale getProductSale(Customer customer, String name, char type, double unitPrice) {
        ProductSale result = null;
        for (ProductSale element : customer.getSales()) {
            if (element.getName().equals(name)
                    && element.getDrinkType() == type
                    && element.getUnitPrice() == unitPrice) {
                result = element;
                break;
            }
        }

        return result;
    }
}
