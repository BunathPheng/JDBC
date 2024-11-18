package model.service;

import model.dto.ProductDto;
import model.entity.Product;

import java.util.List;

public interface ProductService {
    List<ProductDto> getAllProducts();
    void addProduct(Product product);
    void updateProductById(Integer id);
    void deleteProductById(Integer id);
    ProductDto searchProductById(Integer id);
}
