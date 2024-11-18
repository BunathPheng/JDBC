package view;

import controller.CustomerController;
import controller.OrderController;
import controller.ProductController;
import exception.NotFoundException;
import model.dao.CustomerDao;
import model.dao.CustomerDaoImpl;
import model.dao.OrderDao;
import model.dao.OrderDaoImpl;
import model.entity.Customer;
import model.entity.Order;
import model.entity.Product;
import model.service.CustomerService;

import java.security.PublicKey;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class View {
   public static final CustomerController customerController = new CustomerController();
   public static final ProductController productController = new ProductController();
   public static final OrderController orderController = new OrderController();
   public static void menuAll(){
        System.out.println("=".repeat(100));
        System.out.println("[1] This is Product");
        System.out.println("[2] This is Customer");
        System.out.println("[3] This is Order");
        System.out.println("[4] Exit program");
       System.out.println("=".repeat(100));
    }
    public static void menuAllCustomer(){
        System.out.println("=".repeat(100));
        System.out.println("\t[a] List all customers");
        System.out.println("\t[b] Add new customer");
        System.out.println("\t[c] Edit customer by id");
        System.out.println("\t[d] Delete customer by id");
        System.out.println("\t[e] Search customer by id");
        System.out.println("\t[f] Exit to Main Menu");
        System.out.println("=".repeat(100));
    }
    public static void menuAllProducts()  {
        System.out.println("=".repeat(100));
        System.out.println("\t[a] Add new product");
        System.out.println("\t[b] Delete product by id");
        System.out.println("\t[c] Edit product by id");
        System.out.println("\t[d] List all products");
        System.out.println("\t[e] Search product");
        System.out.println("\t[f] Exit to Main Menu");
        System.out.println("=".repeat(100));
    }
    public static void menuOrder()  {
        System.out.println("=".repeat(100));
        System.out.println("\t[a] Add new order");
        System.out.println("\t[b] Delete order by id");
        System.out.println("\t[c] Edit order by id");
        System.out.println("\t[d] List all orders");
        System.out.println("\t[e] Search order by id");
        System.out.println("\t[f] Exit to Main Menu");
        System.out.println("=".repeat(100));
    }
    //Customer
   public static void viewAllCustomer() throws NotFoundException {
       customerController.getAllCustomers().forEach(System.out::println);
   }
   public static void addCustomer() throws NotFoundException {
       System.out.print("[+] insert id Customer:");
       Integer id = new Scanner(System.in).nextInt();
       System.out.print("[+]insert name Customer:");
       String name = new Scanner(System.in).next();
       System.out.print("[+]insert email Customer:");
       String email = new Scanner(System.in).next();
       System.out.print("[+]insert password Customer:");
       String password = new Scanner(System.in).next();
       customerController.addCustomer(
               Customer.builder()
                       .id(id)
                       .name(name)
                       .email(email)
                       .password(password)
                       .isDeleted(false)
                       .createdAt(Date.valueOf(LocalDate.now()))
                       .build()
       );
   }
   public static void updateCustomer() throws NotFoundException {
       System.out.print("[+]insert id Customer:");
       Integer id = new Scanner(System.in).nextInt();
       customerController.updateCustomer(id);
   }
   public static void deleteCustomer() throws NotFoundException {
       System.out.print("[+] insert id Customer:");
       Integer id = new Scanner(System.in).nextInt();
       customerController.deleteCustomer(id);
   }
   public static void searchCustomer() throws NotFoundException {
       System.out.print("[+] Insert id Customer:");
       Integer id = new Scanner(System.in).nextInt();
       System.out.println(customerController.searchCustomer(id));
   }
   //Product
    public static void viewAllProducts() throws NotFoundException {
       productController.getAllProducts().forEach(System.out::println);
    }
    public static void addProduct() throws NotFoundException {
       System.out.print("[+]insert id Product:");
       Integer id = new Scanner(System.in).nextInt();
       System.out.print("[+]insert name Product:");
       String name = new Scanner(System.in).next().toLowerCase().trim();
       System.out.print("[+]insert Product Code:");
       String code = new Scanner(System.in).next().toLowerCase().trim();
       System.out.print("[+]insert price Product Description:");
       String description = new Scanner(System.in).next().trim().toLowerCase();
       productController.addProduct(Product.builder()
                       .id(id)
                       .productName(name)
                       .productCode(code)
                       .isDeleted(false)
                       .importedAt(Date.valueOf(LocalDate.now()))
                       .exportedAt(Date.valueOf(LocalDate.of(2025 , 12 , 3)))
                       .productDescription(description)
               .build());

    }
    public static void searchProduct() throws NotFoundException {
       System.out.print("[+]Insert id Product:");
       Integer id = new Scanner(System.in).nextInt();
        System.out.println(productController.searchProduct(id));
    }
    public static void updateProduct() throws NotFoundException {
        System.out.print("[+] insert id Product:");
        Integer id = new Scanner(System.in).nextInt();
        productController.updateProduct(id);
    }
    public static void deleteProduct() throws NotFoundException {
       System.out.print("[+]insert id Product:");
       Integer id = new Scanner(System.in).nextInt();
       productController.deleteProduct(id);
    }
    //Order
    public static void viewAllOrders() throws NotFoundException {
       orderController.getAllOrder().forEach(System.out::println);
    }
    public static void searchOrder() throws NotFoundException {
       System.out.print("[+]Insert id Order:");
       Integer id = new Scanner(System.in).nextInt();
        System.out.println(orderController.searchOrderById(id));
    }
    public static void deleteOrder() throws NotFoundException {
       System.out.print("[+] insert id Order:");
       Integer id = new Scanner(System.in).nextInt();
       orderController.deleteOrderById(id);
    }
    public static void addOrder() throws NotFoundException {
       System.out.print("[+]insert id Order:");
        List<Integer> productIds = new ArrayList<>(List.of(2, 3));
        for (Integer productId : productIds) {
            CustomerDao customerDao = new CustomerDaoImpl();
            orderController.addOrder(Order.builder()
                            .id(new Random().nextInt(1000))
                            .orderName("មី ជប៉ុនហិល")
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
