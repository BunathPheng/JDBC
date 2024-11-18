package model.dao;
import exception.NotFoundException;
import model.entity.Product;
import java.util.List;

public interface ProductDao {
    int addProduct(Product product);
    int deleteProduct(Integer id) throws NotFoundException;
    int updateProduct(Integer id);
    Product searchProduct(Integer  id);
    List<Product> getAllProduct();
}
