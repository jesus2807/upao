package com.upao;

import java.util.Arrays;
import java.util.Comparator;

public class Inventory {
    private ProductInventory[] products = new ProductInventory[0];
    private ArrayHelper arrayHelper = new ArrayHelper();

    public ProductInventory[] reportAllProducts(){
        return products;
    }

    public ProductInventory[] addProduct(ProductInventory product) {
        products = arrayHelper.addElement(products, product);
        return products;
    }

    public ProductInventory[] modifyProduct(ProductInventory product, ProductInventory newProduct){
        product.setName(newProduct.getName());
        product.setDrinkType(newProduct.getDrinkType());
        product.setStock(newProduct.getStock());
        product.setId(newProduct.getId());
        product.setUnitPrice(newProduct.getUnitPrice());

        return products;
    }

    public ProductInventory[] removeProduct(ProductInventory drink) {
        ProductInventory[] result = new ProductInventory[0];
        for (ProductInventory element : products) {
            if (!(drink.getId().compareTo(element.getId()) == 0)) {
                result = arrayHelper.addElement(result, element);
            }
        }
        this.products = result;
        return result;
    }

    public ProductInventory[] getProducts(String name) {
        ProductInventory[] result = new ProductInventory[0];
        for(ProductInventory element : products) {
            if (name != null && name.equalsIgnoreCase(element.getName())){
                result = arrayHelper.addElement(result, element);
            }
        }
        return result;
    }

    public ProductInventory[] getProducts(double unitPrice) {
        ProductInventory[] result = new ProductInventory[0];
        for(ProductInventory element : products) {
            if (element.getUnitPrice() == unitPrice){
                result = arrayHelper.addElement(result, element);
            }
        }
        return result;
    }

    public ProductInventory[] orderAscByName() {
        Arrays.sort(this.products, new CompatorDrinkByNameAsc());
        return this.products;
    }

    public ProductInventory[] orderDescById() {
        Arrays.sort(this.products, new ComparatorDrinkByIdDesc());
        return this.products;
    }

    public ProductInventory getProductById(String id) {
        ProductInventory result = null;
        for(ProductInventory element : products) {
            if (id != null && id.equals(element.getId())){
                result = element;
                break;
            }
        }
        return result;
    }

    public ProductInventory getProductAvailable(String name, char type, int quantity) {
        ProductInventory result = null;
        for (ProductInventory element : products) {
            if (name.equalsIgnoreCase(element.getName())
                    && type == element.getDrinkType()
                    && quantity <= element.getStock()) {
                result = element;
                break;
            }
        }

        return result;
    }

    private class CompatorDrinkByNameAsc implements Comparator<ProductInventory> {

        @Override
        public int compare(ProductInventory o1, ProductInventory o2) {
            return o1.getName().compareTo(o2.getName());
        }
    }

    private class ComparatorDrinkByIdDesc implements Comparator<ProductInventory> {

        @Override
        public int compare(ProductInventory o1, ProductInventory o2) {
            return (o2.getId().compareTo(o1.getId()));
        }
    }
}
