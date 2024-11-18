package model.dao;

import exception.NotFoundException;
import model.entity.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustomerDaoImpl implements CustomerDao{
    @Override
    public List<Customer> getCustomers() {
        String sql = """
select * from "customer"
""";
        try(
                Connection conn = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/postgres",
                        "postgres",
                        ""
                )
                ) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            List<Customer> customers = new ArrayList<>();
            while (rs.next()) {
                customers.add(new Customer(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getBoolean("is_deleted"),
                        rs.getDate("created_date")
                ));
            }
            return customers;
        }catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return new ArrayList<>();
    }

    @Override
    public int addCustomer(Customer customer) throws NotFoundException {
        String SQL = """
                insert into customer(name , email ,password , is_deleted , created_date) values(?,?,?,?,?)
                """;
        try(
                Connection connection = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/postgres",
                        "postgres",
                        "")
                )
        {
         PreparedStatement preparedStatement = connection.prepareStatement(SQL);
         preparedStatement.setString(1, customer.getName());
         preparedStatement.setString(2, customer.getEmail());
         preparedStatement.setString(3, customer.getPassword());
         preparedStatement.setBoolean(4, customer.getIsDeleted());
         preparedStatement.setDate(5,customer.getCreatedAt());
         int row  = preparedStatement.executeUpdate();
         return row;
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    @Override
    public int updateCustomerById(Integer id) throws NotFoundException {
        String sql = """
                UPDATE customer SET name = ? , email = ?
                WHERE id = ?
                """;

        try(
                Connection conn = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/postgres",
                        "postgres",
                        ""
                )
                ) {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            Customer customer =  searchCustomerById(id);
                System.out.print("[+] insert Customer name:");
                preparedStatement.setString(1, new Scanner(System.in).next());
                System.out.print("[+] insert Customer email:");
                preparedStatement.setString(2, new Scanner(System.in).next());
                 preparedStatement.setInt(3, customer.getId());
                int rowAffcted= preparedStatement.executeUpdate();
               return rowAffcted;

        }catch (SQLException  sqlException)
        {
            System.out.println(sqlException.getMessage());
        }
        return 0;
    }

    @Override
    public int  deleteCustomerById(Integer id) {
        String sql  = """
                DELETE FROM customer WHERE id = ?
                """;
        try(
                Connection conn = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/postgres",
                        "postgres",
                        ""
                )
                ) {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            int row = preparedStatement.executeUpdate();
            return row;
        }catch (SQLException  sqlException)
        {
            System.out.println(sqlException.getMessage());
        }
        return 0;
    }

    @Override
    public Customer searchCustomerById(Integer id) throws NotFoundException {
        String  sql  = """
                select * from customer where id = ?
                """;
        try(
                Connection conn = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/postgres",
                        "postgres",
                        ""
                )
                ){
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Customer customer = null;
            while (resultSet.next()) {
                customer = Customer.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .email(resultSet.getString("email"))
                        .password(resultSet.getString("password"))
                        .isDeleted(resultSet.getBoolean("is_deleted"))
                        .createdAt(resultSet.getDate("created_date"))
                        .build();
            }
            return customer;
        }catch (SQLException sqlException)
        {
            System.out.println(sqlException.getMessage());
        }
        return null;
    }
}
