package model.dao;

import com.sun.source.tree.NewArrayTree;
import model.entity.Customer;
import model.entity.Order;
import model.entity.Product;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderDaoImpl implements OrderDao{
    @Override
    public int addOrder(Order order) {
        String sql = """
                INSERT INTO "order" (id , order_name , order_description , cus_id , ordered_at)
                VALUES( ?, ? , ?, ? ,?)
                """;
        String  sql1  = """
                INSERT INTO "product_order" values(? , ?)
                """;
        try(
                Connection  connection = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/postgres",
                        "postgres",
                        ""
                )
                )
        {
            PreparedStatement
                    preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setInt(1, order.getId());
                    preparedStatement.setString(2 , order.getOrderName());
                    preparedStatement.setString(3, order.getOrderDescription());
                    preparedStatement.setInt(4,order.getCustomerId().getId());
                    preparedStatement.setDate(5,order.getOrderAt());
                    int  rowAffected  = preparedStatement.executeUpdate();
                    String message = rowAffected > 0 ?  "Add  to  Order table  is Successfully" : "No Success" ;
                    System.out.println(message);
            PreparedStatement  preparedStatement1 = connection.prepareStatement(sql1);
            for (Product product : order.getProducts()) {
                preparedStatement1.setInt(1, product.getId());
                preparedStatement1.setInt(2, order.getId());
            }
            int  rowAffected1 = preparedStatement1.executeUpdate();
            String message1 = rowAffected1 > 0 ?  "Add  to  product_order  table  is Successfully" : "No Success" ;
            System.out.println(message1);

        }catch (SQLException sqlException)
        {
            System.out.println(sqlException.getMessage());
        }
        return 0;
    }

    @Override
    public int updateOrderById(Integer id) {
        String spl = """
                UPDATE "order" SET order_name = ?, order_description = ? WHERE id = ?
                """;
        try(
                Connection  connection = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/postgres",
                        "postgres",
                        ""
                );
                PreparedStatement preparedStatement = connection.prepareStatement(spl);
                ){
              Order order = searchOrderById(id);
              if(order != null) {
                  System.out.print("[+]insert name:");
                  preparedStatement.setString(1, new Scanner(System.in).next().trim());
                  System.out.print("[+]insert description:");
                  preparedStatement.setString(2,new Scanner(System.in).next().trim());
                  preparedStatement.setInt(3, order.getId());
                  preparedStatement.executeUpdate();
                  int rowAffected = preparedStatement.executeUpdate();
                  return rowAffected;
              }else {
                  System.out.println("Is not found");
              }
        }catch (SQLException sqlException)
        {
            System.out.println(sqlException.getMessage());
        }
        return 0;
    }

    @Override
    public int deleteOrderById(Integer id) {
        String sql = """
                DELETE FROM "order" WHERE id = ?
                """;
        try(
                Connection  connection = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/postgres",
                        "postgres",
                        ""
                );
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ) {
                preparedStatement.setInt(1, id);
                int  rowAffected  = preparedStatement.executeUpdate();
                return rowAffected;

        } catch (SQLException sqlException)
        {
            System.out.println(sqlException.getMessage());
        }
        return 0;
    }

    @Override
    public Order searchOrderById(Integer id) {
        String sql = """
                select * from "order" where id = ?
                """;
        try(
                Connection  connection = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/postgres",
                        "postgres",
                        ""
                )
                ){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1 , id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Order order = new Order();
            while (resultSet.next()) {
                order = Order.builder()
                        .id(resultSet.getInt("id"))
                        .orderName(resultSet.getString("order_name"))
                        .orderDescription(resultSet.getString("order_description"))
                        .customerId(Customer.builder()
                                .id(resultSet.getInt("cus_id"))
                                .build())
                        .build();
            }
            return order;

        }catch (SQLException  sqlException)
        {
            System.out.println(sqlException.getMessage());
        }
        return null;
    }

    @Override
    public List<Order> getAllOrder() {
        String sql = """
                SELECT customer.id as customer_id, customer.name, customer.email, customer.password, customer.is_deleted, customer.created_date,
                               o.id as order_id, o.order_name, o.order_description, o.ordered_at
                        FROM customer
                        INNER JOIN public."order" o ON customer.id = o.cus_id
                """;
        try(
                Connection  connection = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/postgres",
                        "postgres",
                        ""
                )
                ) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println(resultSet.getMetaData().getColumnName(7));
            System.out.println(resultSet.getMetaData().getColumnName(1));
            List<Order> orders = new ArrayList<>();
            while (resultSet.next()) {
               orders.add(Order.builder()
                               .id(resultSet.getInt("order_id"))
                               .orderName(resultSet.getString("order_name"))
                               .orderDescription(resultSet.getString("order_description"))
                               .orderAt(resultSet.getDate("ordered_at"))
                               .customerId(Customer.builder()
                                       .id(resultSet.getInt("customer_id"))
                                       .name(resultSet.getString("name"))
                                       .email(resultSet.getString("email"))
                                       .password(resultSet.getString("password"))
                                       .isDeleted(resultSet.getBoolean("is_deleted"))
                                       .createdAt(resultSet.getDate("created_date"))
                                       .build())
                       .build());
            }
            return orders;
        }catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
        return new ArrayList<>();
    }
}
