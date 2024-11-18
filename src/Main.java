import controller.CustomerController;
import exception.NotFoundException;
import model.dao.*;
import model.entity.Customer;
import model.entity.Order;
import model.entity.Product;
import model.service.CustomerService;
import model.service.CustomerServiceImpl;
import view.View;


import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import static java.lang.System.exit;


public class Main {
    public static void main(String[] args) throws NotFoundException {
        System.out.println(
                        "\t\t\t      ██╗    ██████╗     ██████╗      ██████╗\n" +
                        "\t\t\t      ██║    ██╔══██╗    ██╔══██╗    ██╔════╝\n" +
                        "\t\t\t      ██║    ██║  ██║    ██████╔╝    ██║     \n" +
                        "\t\t\t ██   ██║    ██║  ██║    ██╔══██╗    ██║     \n" +
                        "\t\t\t ╚█████╔╝    ██████╔╝    ██████╔╝    ╚██████╗\n" +
                        "\t\t\t  ╚════╝     ╚═════╝     ╚═════╝      ╚═════╝\n"
        );
        boolean isRunning = true;
        while (isRunning){
        View.menuAll();
        System.out.print("[+] Insert One Choice:");
        int choice = new Scanner(System.in).nextInt();
            switch (choice)
            {
                case 1:
                    boolean submenuRunning = true;

                    do {
                        View.menuAllProducts();
                        System.out.print("[+] Input One Choice:");
                        String c = new Scanner(System.in).next().toLowerCase().trim();
                        switch (c){
                            case "a":
                                View.addProduct();
                                break;
                            case "b":
                                View.deleteProduct();
                                break;
                            case "c":
                                View.updateProduct();
                                break;
                            case "d":
                                View.viewAllProducts();
                                break;
                            case "e":
                                View.searchProduct();
                                break;
                            case "f":
                                submenuRunning = false;
                                break;
                            default:
                                System.out.println("invalid for choice");

                        }
                    }while (submenuRunning);
                    break;
                case 2:
                    boolean subProduct = true;
                    do {
                        View.menuAllCustomer();
                        System.out.print("[+] Input One Choice:");
                        String chose = new Scanner(System.in).next().toLowerCase().toLowerCase();
                        switch (chose) {
                            case "a":
                                View.viewAllCustomer();
                                break;
                            case "b":
                                View.addCustomer();
                                break;
                            case "c":
                                View.updateCustomer();
                                break;
                            case "d":
                                View.deleteCustomer();
                                break;
                            case "e":
                                View.searchCustomer();
                                break;
                            case "f":
                                subProduct = false;
                                break;
                            default:
                                System.out.println("invalid for choice");
                        }
                    }while (subProduct);
                    break;
                case 3:
                    boolean subOrder = true;
                    do {
                        View.menuOrder();
                        System.out.print("[+] Input One Choice:");
                        String chose1 = new Scanner(System.in).next().toLowerCase().toLowerCase();
                        switch (chose1) {
                            case "a":
                                View.addOrder();
                                break;
                            case "b":
                                View.deleteOrder();
                                break;
                            case "c":
                                View.updateProduct();
                                break;
                            case "d":
                                View.viewAllOrders();
                                break;
                            case "e":
                                View.searchOrder();
                                break;
                            case "f":
                                subOrder = false;
                                break;
                            default:
                                System.out.println("invalid for choice");
                        }
                    }while (subOrder);
                    break;
                case 4:
                    exit(0);
                default:
                    System.out.println("Don't know what to do");
            }


//        CustomerService  customerService = new CustomerServiceImpl();
//         customerService.getCustomers().forEach(System.out::println);
//        CustomerController cust
//        omerController = new CustomerController();
//        CustomerDao  customerDao  =  new CustomerDaoImpl();
//        customerDao.addCustomer(Customer.builder()
//                        .id(3)
//                        .name("lyly")
//                        .email("lyly@gmail.com")
//                        .password("lyly@1212")
//                        .isDeleted(false)
//                        .createdAt(Date.valueOf(LocalDate.now()))
//                .build());
//        System.out.println(customerDao.searchCustomerById(10));
//        customerDao.updateCustomerById(6);
//        OrderDao orderDao1 = new OrderDaoImpl();
//        orderDao1.deleteOrderById(1);
//        }

//        customerDao.deleteCustomerById(10);
//        ProductDao  productDao = new ProductDaoImpl();

//        productDao.addProduct(Product.builder()
//                        .productName("Sting")
//                        .productCode("885")
//                        .isDeleted(false)
//                        .importedAt(Date.valueOf(LocalDate.now()))
//                        .exportedAt(Date.valueOf(LocalDate.of(2025 , 12, 4)))
//                        .productDescription("សូរក្សាទុកកន្លែងត្រជាក់")
//                .build());
//        orderDao1.getAllOrder().stream().forEach(System.out::println);
//        orderDao.getAllOrder().stream().forEach(System.out::println);
//        System.out.println(orderDao.searchOrderById(2));
//

//        productDao.getAllProduct().stream().forEach(e-> System.out.println(e));
//        System.out.println(productDao.searchProduct(1));
//         productDao.updateProduct(1);
//          productDao.deleteProduct(1);

    }
        List<Integer> productIds = new ArrayList<>(List.of(2, 3));
        for (Integer productId : productIds) {
            OrderDao orderDao = new OrderDaoImpl();
            CustomerDao customerDao = new CustomerDaoImpl();
            orderDao.addOrder(Order.builder()
                    .id(new Random().nextInt(1000))
                    .orderName("មី ជប៉ុនហិល")
                    .orderDescription("សុំម្ទេសឆា៥0គ្រាប់")
                    .customerId(customerDao.searchCustomerById(3))
                    .products(new ArrayList<>(
                            List.of(Product.builder()
                                    .id(productId)
                                    .build())
                    ))
                    .orderAt(Date.valueOf(LocalDate.now()))
                    .build());
        }
    }

}