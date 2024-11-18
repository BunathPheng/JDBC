package controller;

import model.dto.ProductDto;
import model.entity.Product;
import model.service.ProductService;
import model.service.ProductServiceImpl;

import java.util.Iterator;
import java.util.List;

public class ProductController {
    ProductService productService = new ProductServiceImpl();
    public List<ProductDto> getAllProducts() {
        return productService.getAllProducts();
    }
    public void addProduct(Product product) {
        productService.addProduct(product);
    }
    public ProductDto searchProduct(Integer id) {
        return productService.searchProductById(id);
    }
    public void updateProduct(Integer id) {
        productService.updateProductById(id);
    }
    public void deleteProduct(Integer id) {
        productService.deleteProductById(id);
    }
}
