package model.service;
import exception.NotFoundException;
import mapper.Mapper;
import model.dao.CustomerDao;
import model.dao.CustomerDaoImpl;
import model.dto.CustomerDto;
import model.entity.Customer;
import java.util.List;

public class CustomerServiceImpl implements CustomerService  {
    CustomerDao customerDao = new CustomerDaoImpl();
    @Override
    public List<CustomerDto> getCustomers() throws NotFoundException {
        if (customerDao.getCustomers() == null) {
            throw new NotFoundException("Customer is not have");
        } else {
            return customerDao.getCustomers().stream().map(e-> Mapper.convertCustomerToCustomerDto(e)).toList();
        }
    }

    @Override
    public void addCustomer(Customer customer) throws NotFoundException {
        try{
            int row = customerDao.addCustomer(Customer.builder()
                    .id(customer.getId())
                    .name(customer.getName())
                    .email(customer.getEmail())
                    .password(customer.getPassword())
                    .isDeleted(customer.getIsDeleted())
                    .createdAt(customer.getCreatedAt())
                    .build());
            if( row > 0)
            {
                throw new NotFoundException("Insert Customer Successfully");
            }
            else
            {
                throw new NotFoundException("Insert Customer Failed");
            }
        }catch (
                NotFoundException notFoundException
        ){
            System.out.println(notFoundException.getMessage());
        };
    }
    @Override
    public void updateCustomer(Integer id) throws NotFoundException {
        try {
            int  row  = customerDao.updateCustomerById(id);
            if( row > 0)
            {
                throw new NotFoundException("Update  Customer Successfully");
            }
            else
            {
                throw new NotFoundException("Update Customer Failed");
            }
        } catch (NotFoundException notFoundException) {
            System.out.println(notFoundException.getMessage());
        }
    }

    @Override
    public void deleteCustomer(Integer id) throws NotFoundException {
        try {
            int  row  = customerDao.deleteCustomerById(id);
            if (row > 0)
            {
                throw new NotFoundException("Delete Customer Successfully");
            }
            else {
                throw new NotFoundException("Delete Customer Failed");
            }
        }catch (NotFoundException notFoundException)
        {
            System.out.println(notFoundException.getMessage());
        }
    }

    @Override
    public CustomerDto searchCustomer(Integer id) throws NotFoundException {
        try {
            if (customerDao.searchCustomerById(id) != null) {
                return  Mapper.convertCustomerToCustomerDto(customerDao.searchCustomerById(id));
            }
            else
            {
                throw new NotFoundException("Search Customer Failed");
            }
        }catch (NotFoundException notFoundException)
        {
            System.out.println(notFoundException.getMessage());
        }
        return null;
    }
}
