package mapper;

import model.dto.CustomerDto;
import model.dto.OrderDto;
import model.dto.ProductDto;
import model.entity.Customer;
import model.entity.Order;
import model.entity.Product;
import model.service.OrderService;

public class Mapper {
    public static CustomerDto convertCustomerToCustomerDto(Customer customer)
    {
        if (customer == null)  return  null;
        return  new CustomerDto(customer.getName() , customer.getEmail() , customer. getIsDeleted() , customer.getCreatedAt());
    }
    public static ProductDto convertProductToProductDto(Product product)
    {
        if (product == null)  return  null;
        return new ProductDto(product.getProductName() , product.getProductCode() , product.getIsDeleted() , product.getImportedAt() , product.getExportedAt() , product.getProductDescription());
    }

}
