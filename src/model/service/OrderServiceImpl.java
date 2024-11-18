package model.service;

import exception.NotFoundException;
import model.dao.OrderDao;
import model.dao.OrderDaoImpl;
import model.entity.Customer;
import model.entity.Order;
import model.entity.Product;

import java.util.ArrayList;
import java.util.List;

public class OrderServiceImpl implements OrderService{
    OrderDao orderDao = new OrderDaoImpl();
    @Override
    public List<Order> getAllOrders() {
        return orderDao.getAllOrder().stream().toList();
    }

    @Override
    public void addOrder(Order order) {
        orderDao.addOrder(Order.builder()
                        .id(order.getId())
                        .orderName(order.getOrderName())
                        .orderDescription(order.getOrderDescription())
                        .customerId(Customer.builder()
                                .id(3)
                                .build())
                .products(order.getProducts().stream()
                        .map(product -> Product.builder()
                                .id(product.getId())
                                .build())
                        .toList())
                .build());
    }
    @Override
    public int updateOrderBy(Integer id) throws NotFoundException {
        try {
           int  row  =  orderDao.updateOrderById(id);
           if (row > 0 ) {
              throw  new NotFoundException("update successfully");
           }else {
               throw new NotFoundException("update failed");
           }
        }catch (NotFoundException notFoundException){
            System.out.println(notFoundException.getMessage());
        }
        return 0;
    }

    @Override
    public void deleteOrderBy(Integer id) {
        try {
            int row  = orderDao.deleteOrderById(id);
            if(row > 0) {
                throw new NotFoundException("delete order by id successfully");
            }
            else {
                throw new NotFoundException("delete order by id failed");
            }
        }catch (NotFoundException  notFoundException)
        {
            System.out.println(notFoundException.getMessage());
        }

    }

    @Override
    public Order searchOrderById(Integer id)
    {
        try {
            if (orderDao.searchOrderById(id) != null )
            {
                return orderDao.searchOrderById(id);
            }
            else {
                throw new NotFoundException("Search  is not successfully");
            }
        }catch (NotFoundException notFoundException)
        {
            System.out.println(notFoundException.getMessage());
        }
     return null;
    }

}
