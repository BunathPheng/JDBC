package controller;
import exception.NotFoundException;
import model.dto.CustomerDto;
import model.entity.Customer;
import model.service.CustomerService;
import model.service.CustomerServiceImpl;
import java.util.List;

public class CustomerController {
    CustomerService customerService = new CustomerServiceImpl();
    public List<CustomerDto> getAllCustomers() throws NotFoundException {
        return  customerService.getCustomers().stream().toList();
    }
    public void addCustomer(Customer customer) throws NotFoundException {
          customerService.addCustomer(customer);
    }
    public void updateCustomer(Integer id) throws NotFoundException {
          customerService.updateCustomer(id);
    }
    public void deleteCustomer(Integer id) throws NotFoundException {
          customerService.deleteCustomer(id);
    }
    public CustomerDto searchCustomer(Integer id) throws NotFoundException {
         return customerService.searchCustomer(id);
    }

}
