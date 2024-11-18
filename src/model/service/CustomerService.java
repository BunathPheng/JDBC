package model.service;

import exception.NotFoundException;
import model.dto.CustomerDto;
import model.entity.Customer;

import java.util.List;

public interface CustomerService
{
    List<CustomerDto> getCustomers() throws NotFoundException;
    void addCustomer(Customer customer) throws NotFoundException;
    void updateCustomer(Integer id) throws NotFoundException;
    void deleteCustomer(Integer id) throws NotFoundException;
    CustomerDto searchCustomer(Integer id) throws NotFoundException;
}
