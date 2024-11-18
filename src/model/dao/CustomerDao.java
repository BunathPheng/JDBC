package model.dao;

import exception.NotFoundException;
import model.entity.Customer;

import java.util.List;

public interface CustomerDao {
    List<Customer> getCustomers();
    int addCustomer(Customer customer) throws NotFoundException;
    int updateCustomerById(Integer id) throws NotFoundException;
    int deleteCustomerById(Integer id);
    Customer searchCustomerById(Integer id) throws NotFoundException;
}
