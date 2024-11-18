package model.dao;

import exception.NotFoundException;
import model.entity.Customer;
import model.entity.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductDaoImpl implements ProductDao{
    @Override
    public int addProduct(Product product) {
        String sql = """
                INSERT INTO "product" (product_name, product_code, is_deleted, imported_at, expired_at, product_description)
                VALUES (?, ?, ?, ?, ?, ?)
    """;

        int  rowAffected = 0;
        try(
                Connection  connection = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/postgres",
                        "postgres",
                        ""
                )
                ) {
            PreparedStatement  preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, product.getProductName());
            preparedStatement.setString(2, product.getProductCode());
            preparedStatement.setBoolean(3, product.getIsDeleted());
            preparedStatement.setDate(4, product.getImportedAt());
            preparedStatement.setDate(5, product.getExportedAt());
            preparedStatement.setString(6, product.getProductDescription());
            rowAffected =  preparedStatement.executeUpdate();
            return rowAffected;

        }catch (SQLException sqlException)
        {
            System.out.println(sqlException.getMessage());
        }

        return 0;
    }

    @Override
    public int deleteProduct(Integer id) throws NotFoundException {
        String sql = """
                DELETE FROM product WHERE id = ?
                """;
        try (
                Connection  connection = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/postgres",
                        "postgres",
                        ""
                );
                PreparedStatement  preparedStatement = connection.prepareStatement(sql);
                ){
            Product product = searchProduct(id);
           if(product != null)  {
                    preparedStatement.setInt(1 , product.getId());
                    preparedStatement.executeUpdate();
                    throw  new NotFoundException("Product is deleted successfully");

            } else {
               throw new NotFoundException("Product is  not found in table  product");
           }
        }catch (SQLException sqlException)
        {
            System.out.println(sqlException.getMessage());
        }
        return 0;
    }

    @Override
    public int updateProduct(Integer id) {
        String  sql  = """
                UPDATE Product SET is_deleted=? , product_description = ?   WHERE id=?
                """;
        try(
                Connection connection = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/postgres",
                        "postgres",
                        ""
                );
                PreparedStatement preparedStatement  = connection.prepareStatement(sql);
                ) {
               Product product  =  searchProduct(id);
                   System.out.print("[+] Insert is deleted :");
                   preparedStatement.setBoolean(1, new Scanner(System.in).nextBoolean());
                   System.out.print("[+] Insert is product_description:");
                   preparedStatement.setString(2, new Scanner(System.in).nextLine().toLowerCase().trim());
                   preparedStatement.setInt(3, product.getId());
                   int  rowAffected   =    preparedStatement.executeUpdate();
                   return rowAffected;
        }catch (SQLException sqlException)
        {
             System.out.println(sqlException.getMessage());
        }
        return 0;
    }

    @Override
    public Product searchProduct(Integer id) {
        String sql = """
                select * from product where id = ?
                """;
        try(
                Connection  connection = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/postgres",
                        "postgres",
                        ""
                );
                PreparedStatement preparedStatement  = connection.prepareStatement(sql);
        )
        {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Product product = null;
            while (resultSet.next()) {
                product = Product.builder()
                        .id(resultSet.getInt("id"))
                        .productName(resultSet.getString("product_name"))
                        .productCode(resultSet.getString("product_code"))
                        .isDeleted(resultSet.getBoolean("is_deleted"))
                        .importedAt(resultSet.getDate("imported_at"))
                        .exportedAt(resultSet.getDate("expired_at"))
                        .productDescription(resultSet.getString("product_description"))
                        .build();
            }
            return product;
        }catch (SQLException  sqlException)
        {
            System.out.println(sqlException.getMessage());
        }
        return null;
    }

    @Override
    public List<Product> getAllProduct() {
        String  sql   = """
                select * from product;
                """ ;
        try(
                Connection  connection = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/postgres",
                        "postgres",
                        ""
                );
                PreparedStatement  preparedStatement = connection.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery();
                ) {
               List<Product> productList = new ArrayList<>();
                while(resultSet.next()) {
                    productList.add(Product.builder()
                                    .productName(resultSet.getString("product_name"))
                                    .productCode(resultSet.getString("product_code"))
                                    .isDeleted(resultSet.getBoolean("is_deleted"))
                                    .importedAt(resultSet.getDate("imported_at"))
                                    .exportedAt(resultSet.getDate("expired_at"))
                                    .productDescription(resultSet.getString("product_description"))
                            .build());
                }
                return productList;

        }catch (SQLException sqlException)
        {
            System.out.println(sqlException.getMessage());
        }

        return new ArrayList<>();
    }
}
