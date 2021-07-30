package com.upao;

import java.io.Console;

public class Principal {
    private static final String USER_ADMIN = "admin";
    private static final String PASSWORD_ADMIN = "admin";

    private static final String MENU_PRINCIPAL_OPTION_INTO_AS_ADMIN = "1. Ingresar como administrador";
    private static final String MENU_PRINCIPAL_OPTION_INTO_AS_CUSTOMER = "2. Ingresar como vendedor";
    private static final String MENU_PRINCIPAL_OPTION_EXIT = "[X] Salir";
    private static final String GO_MENU_PRINCIPAL = "[M] Ir al menu principal";

    private static final String ADMIN_OPTION_REPORT_PRODUCTS = "1. Reportar productos";
    private static final String ADMIN_OPTION_ADD_PRODUCT = "2. Agregar producto";
    private static final String ADMIN_OPTION_MODIFY_PRODUCT = "3. Modificar productos";
    private static final String ADMIN_OPTION_DELETE_PRODUCT = "4. Eliminar producto";
    private static final String ADMIN_OPTION_GET_PRODUCT_BY_NAME = "5. Consultar productos por nombre";
    private static final String ADMIN_OPTION_GET_PRODUCT_BY_PRECIO = "6. Consultar productos por precio";
    private static final String ADMIN_OPTION_ORDER_ALPHA_PRODUCTS_ASC_BY_NAME = "7. Ordenar los productos alfabeticamente por nombre";
    private static final String ADMIN_OPTION_ORDER_ALPHA_PRODUCTS_ASC_BY_ID = "8. Ordenar los productos alfabeticamente por identificador";
    private static final String ADMIN_OPTION_GET_SALE_BY_CUSTOMER = "9. Obtener ventas por cliente";

    private static final String PRODUCT_ALLOW_GASEOSA = "G";
    private static final String PRODUCT_ALLOW_CERVEZA = "C";
    private static final String PRODUCT_ALLOW_VINO = "V";
    private static final String PRODUCT_ALLOW_OTROS = "O";

    private static Console console = System.console();

    private static Inventory inventory = new Inventory();
    private static Sale sale = new Sale();

    public static void main(String[] arguments) {
        while (true) {
            String intoAs = principalMenu();
            if (intoAs == null) {
                continue;
            }
            switch (intoAs) {
                case "1":
                    processAdmin();
                    break;
                case "2":
                    processVendor();
                    break;
                default:
                    System.exit(0);
            }
        }
    }

    private static String principalMenu() {
        String result = null;
        System.out.println("\n\n\nBienvenido al menu principal");
        System.out.println("============================");
        System.out.println(MENU_PRINCIPAL_OPTION_INTO_AS_ADMIN);
        System.out.println(MENU_PRINCIPAL_OPTION_INTO_AS_CUSTOMER);
        System.out.println(MENU_PRINCIPAL_OPTION_EXIT);
        String capture = console.readLine();
        if ("1".equals(capture)
                || "2".equals(capture)
                || "X".equals(capture)) {
            result = capture;
        } else {
            System.out.println("Opcion no reconocida");
        }
        return result;
    }

    private static void processVendor() {
        System.out.println("Nombre del cliente: ");
        String customerNameCapture = console.readLine();
        System.out.println("DNI del cliente: ");
        String customerDniCapture = console.readLine();
        ProductInventory productInventoryFound;
        int productQuantityCapture;
        while (true) {
            System.out.println("Tipo de producto: ");
            char productTypeCapture = console.readLine().toCharArray()[0];
            System.out.println("Nombre del producto: ");
            String productNameCapture = console.readLine();
            while (true) {
                System.out.println("Cantidad del producto: ");
                try {
                    productQuantityCapture = Integer.parseInt(console.readLine());
                    if (productQuantityCapture == 0) {
                        System.out.println("Cantidad invalida. Por favor intente nuevamente.");
                        continue;
                    }
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Numero invalido. Por favor intente nuevamente.");
                }
            }
            productInventoryFound = inventory.getProductAvailable(productNameCapture, productTypeCapture, productQuantityCapture);
            if (productInventoryFound == null) {
                System.out.println("Stock no disponible.");
                System.out.println("++++++++++++++++++++++");
            } else {
                break;
            }
        }

        System.out.println("Se encuentra satisfecho con la venta? [S/N]");
        String confirmSale = console.readLine();

        if ("S".equalsIgnoreCase(confirmSale)) {
            sale.sale(customerNameCapture, customerDniCapture, productQuantityCapture, productInventoryFound);
            System.out.println("Se realizo la venta con exito.");
        }

    }

    private static void processAdmin() {
        System.out.print("Usuario: ");
        String user = console.readLine();
        System.out.print("Password: ");
        String password = new String(console.readPassword());
        if (USER_ADMIN.equals(user)
                && PASSWORD_ADMIN.equals(password)) {

            boolean showMenuAdministrator = true;
            while (showMenuAdministrator) {
                System.out.printf("\n\n\nBienvenido %s al menu administrativo\n", user);
                System.out.println("========================================");
                String intoAs = showAdministratorMenu();
                if (intoAs == null) {
                    continue;
                }
                switch (intoAs) {
                    case "1":
                        executeReportInventory();
                        break;
                    case "2":
                        executeCreateProductInventory();
                        break;
                    case "3":
                        executeModifyProductInventory();
                        break;
                    case "4":
                        executeDeleteProductInventory();
                        break;
                    case "5":
                        executeConsultProductInventoryByName();
                        break;
                    case "6":
                        executeConsultProductInventoryByPrice();
                        break;
                    case "7":
                        executeSortProductInventoryByNameAsc();
                        break;
                    case "8":
                        executeSortProductInventoryByIdDesc();
                        break;
                    case "9":
                        executeGetSalesByCustomer();
                        break;
                    case "M":
                        return;
                    case "X":
                        System.exit(0);
                }
            }
        } else {
            System.out.println("Usuario/Password no valido!");
        }
    }

    private static String showAdministratorMenu() {
        String result = null;
        System.out.println(ADMIN_OPTION_REPORT_PRODUCTS);
        System.out.println(ADMIN_OPTION_ADD_PRODUCT);
        System.out.println(ADMIN_OPTION_MODIFY_PRODUCT);
        System.out.println(ADMIN_OPTION_DELETE_PRODUCT);
        System.out.println(ADMIN_OPTION_GET_PRODUCT_BY_NAME);
        System.out.println(ADMIN_OPTION_GET_PRODUCT_BY_PRECIO);
        System.out.println(ADMIN_OPTION_ORDER_ALPHA_PRODUCTS_ASC_BY_NAME);
        System.out.println(ADMIN_OPTION_ORDER_ALPHA_PRODUCTS_ASC_BY_ID);
        System.out.println(ADMIN_OPTION_GET_SALE_BY_CUSTOMER);
        System.out.println(GO_MENU_PRINCIPAL);
        System.out.println(MENU_PRINCIPAL_OPTION_EXIT);
        String capture = console.readLine();
        if ("1".equals(capture)
                || "2".equals(capture)
                || "3".equals(capture)
                || "4".equals(capture)
                || "5".equals(capture)
                || "6".equals(capture)
                || "7".equals(capture)
                || "8".equals(capture)
                || "9".equals(capture)
                || "M".equals(capture)
                || "X".equals(capture)) {
            result = capture;
        } else {
            System.out.println("Opcion no reconocida");
        }
        return result;
    }

    private static void executeReportInventory() {
        System.out.println("Reportar productos");
        System.out.println("==================");
        ProductInventory[] result = inventory.reportAllProducts();
        System.out.println("**********************************");
        for (ProductInventory element : result) {
            System.out.println(element);
            System.out.println("**********************************");
        }
    }

    private static void executeCreateProductInventory() {
        System.out.println("Producto nuevo");
        System.out.println("==============");
        ProductInventory productInventory = buildProductInventory(true);
        if (productInventory != null) {
            inventory.addProduct(productInventory);
            System.out.println("Producto agregado satisfactoriamente.");
        }
    }

    private static void executeModifyProductInventory() {
        System.out.println("Modificando producto");
        System.out.println("====================");
        System.out.print("Ingrese Id del producto a modificar: ");
        String idCapture = console.readLine();
        ProductInventory productInventory = inventory.getProductById(idCapture);
        if (productInventory == null) {
            System.out.println("Producto no encontrado en el inventario.");
        } else {
            System.out.println("Producto encontrado");
            System.out.println("===================");
            System.out.println(productInventory);
            System.out.println("Ingresar nuevos datos");
            System.out.println("=====================");
            ProductInventory newProductInventory = buildProductInventory(false);
            if (newProductInventory != null) {
                inventory.modifyProduct(productInventory, newProductInventory);
                System.out.println("Producto modificado satisfactoriamente.");
            }
        }
    }

    private static void executeDeleteProductInventory() {
        System.out.println("Eliminando producto");
        System.out.println("===================");
        System.out.print("Ingrese Id del producto a eliminar: ");
        String idCapture = console.readLine();
        ProductInventory productInventory = inventory.getProductById(idCapture);
        if (productInventory == null) {
            System.out.println("Producto no encontrado en el inventario.");
        } else {
            System.out.println("Producto encontrado");
            System.out.println("===================");
            System.out.println(productInventory);
            System.out.println("Desea continuar con la eliminacion del producto encontrado? [S/N]");
            String confirmCature = console.readLine();
            if ("S".equalsIgnoreCase(confirmCature)) {
                inventory.removeProduct(productInventory);
            }
        }
    }

    private static void executeConsultProductInventoryByName() {
        System.out.println("Consultar producto");
        System.out.println("===================");
        System.out.print("Ingrese nombre: ");
        String nameCapture = console.readLine();
        System.out.println("Resultado de la busqueda");
        System.out.println("========================");
        ProductInventory[] productInventoryList = inventory.getProducts(nameCapture);
        System.out.println("**********************************");
        for (ProductInventory element : productInventoryList) {
            System.out.println(element);
            System.out.println("**********************************");
        }
    }

    private static void executeConsultProductInventoryByPrice() {
        System.out.println("Consultar producto");
        System.out.println("===================");
        double unitPrice;
        while (true) {
            System.out.print("Precio unitario: ");
            try {
                unitPrice = Double.parseDouble(console.readLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Numero invalido. Por favor intente nuevamente.");
            }
        }
        System.out.println("Resultado de la busqueda");
        System.out.println("========================");
        ProductInventory[] productInventoryList = inventory.getProducts(unitPrice);
        System.out.println("**********************************");
        for (ProductInventory element : productInventoryList) {
            System.out.println(element);
            System.out.println("**********************************");
        }
    }

    private static void executeSortProductInventoryByNameAsc() {
        System.out.println("Ordenar producto por nombre de forma ascendente");
        System.out.println("===============================================");
        ProductInventory[] productInventoryList = inventory.orderAscByName();
        System.out.println("**********************************");
        for (ProductInventory element : productInventoryList) {
            System.out.println(element);
            System.out.println("**********************************");
        }
    }

    private static void executeSortProductInventoryByIdDesc() {
        System.out.println("Ordenar producto por nombre de forma descendente");
        System.out.println("================================================");
        ProductInventory[] productInventoryList = inventory.orderDescById();
        for (ProductInventory element : productInventoryList) {
            System.out.println(element);
            System.out.println("**********************************");
        }
    }

    private static void executeGetSalesByCustomer() {
        System.out.println("Obtener ventas por cliente");
        System.out.println("==========================");
        System.out.print("Nombre de cliente: ");
        String nameCustomerCapture = console.readLine();
        Customer[] customerList = sale.getCustomerByName(nameCustomerCapture);
        System.out.println("**********************************");
        for (Customer element : customerList) {
            System.out.println(element);
            System.out.println("**********************************");
        }
    }

    private static ProductInventory buildProductInventory(boolean isCreated) {
        ProductInventory result;
        int stock = 0;
        String id = null;
        String name = null;
        char drinkType = 0;
        double unitPrice;
        if (isCreated) {
            while (true) {
                System.out.print("Id: ");
                id = console.readLine();
                ProductInventory productExist = inventory.getProductById(id);
                if (productExist == null) {
                    break;
                } else {
                    System.out.println("Producto con identificador existente.");
                }
            }
        }

        System.out.print("Nombre: ");
        name = console.readLine().toUpperCase();
        while (true) {
            System.out.print("Tipo: ");
            String typeCaptured = console.readLine();
            if (PRODUCT_ALLOW_GASEOSA.equals(typeCaptured)
                    || PRODUCT_ALLOW_CERVEZA.equals(typeCaptured)
                    || PRODUCT_ALLOW_VINO.equals(typeCaptured)
                    || PRODUCT_ALLOW_OTROS.equals(typeCaptured)) {
                drinkType = typeCaptured.charAt(0);
                break;
            } else {
                System.out.println("Los productos permitidos son: Gaseosa (G), Cerveza (C), Vino (V) y Otros (O).");
            }
        }

        while (true) {
            System.out.print("Precio unitario: ");
            try {
                unitPrice = Double.parseDouble(console.readLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Numero invalido. Por favor intente nuevamente.");
            }
        }

        while (true) {
            System.out.print("Stock: ");
            try {
                stock = Integer.parseInt(console.readLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Numero invalido. Por favor intente nuevamente.");
            }
        }

        System.out.println("Está satisfecho con la asignacion de datos? [S/N]");
        if ("S".equalsIgnoreCase(console.readLine()) ? true : false) {
            result = new ProductInventory();
            result.setId(id);
            result.setName(name);
            result.setDrinkType(drinkType);
            result.setUnitPrice(unitPrice);
            result.setStock(stock);
        } else {
            result = null;
        }

        return result;
    }
}
