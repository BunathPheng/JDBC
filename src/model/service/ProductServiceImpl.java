package model.service;

import exception.NotFoundException;
import mapper.Mapper;
import model.dao.ProductDao;
import model.dao.ProductDaoImpl;
import model.dto.ProductDto;
import model.entity.Product;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    ProductDao productDao = new ProductDaoImpl();
    @Override
    public List<ProductDto> getAllProducts() {
        return productDao.getAllProduct().stream().map(e-> Mapper.convertProductToProductDto(e)).toList();
    }

    @Override
    public void addProduct(Product product) {
        try {
          int row = productDao.addProduct(
                  Product.builder()
                          .id(product.getId())
                          .productName(product.getProductName())
                          .productCode(product.getProductCode())
                          .isDeleted(product.getIsDeleted())
                          .importedAt(product.getImportedAt())
                          .exportedAt(product.getExportedAt())
                          .productDescription(product.getProductDescription())
                          .build()
          );
          if (row > 0) {
              throw new NotFoundException("Insert product successfully");
          }
          else
          {
              throw new NotFoundException("Insert product failed");
          }
        }catch (NotFoundException  notFoundException)
        {
            System.out.println(notFoundException.getMessage());
        }
    }

    @Override
    public void updateProductById(Integer id) {
       try {
           int row  = productDao.updateProduct(id);
           if(row > 0 )
           {
               throw new NotFoundException("Update product successfully");
           }
           else
           {
               throw new NotFoundException("Update product failed");
           }
       }catch (NotFoundException notFoundException)
       {
           System.out.println(notFoundException.getMessage());
       }
    }

    @Override
    public void deleteProductById(Integer id) {
        try {
            productDao.deleteProduct(id);
        }catch (NotFoundException notFoundException)
        {
            System.out.println(notFoundException.getMessage());
        }
    }

    @Override
    public ProductDto searchProductById(Integer id) {
        try {
            if(productDao.searchProduct(id) == null)
            {
                throw new NotFoundException("Product not found");
            }
            else {
                return Mapper.convertProductToProductDto(productDao.searchProduct(id));
            }
        }catch (NotFoundException notFoundException)
        {
            System.out.println(notFoundException.getMessage());
        }
        return null;
    }
}
