package model.service;

import exception.NotFoundException;
import model.entity.Order;

import java.util.List;

public interface OrderService {
    List<Order>  getAllOrders();
    void addOrder(Order order);
    int updateOrderBy(Integer id) throws NotFoundException;
    void deleteOrderBy(Integer id);
    Order searchOrderById (Integer id) throws NotFoundException;
}
