package controller;

import exception.NotFoundException;
import model.entity.Order;
import model.service.OrderService;
import model.service.OrderServiceImpl;

import java.util.List;

public class OrderController {
    public static final OrderService orderService = new OrderServiceImpl();
    public List<Order> getAllOrder()
    {
        return orderService.getAllOrders().stream().toList();
    }
    public Order searchOrderById(Integer id) throws NotFoundException {
        return orderService.searchOrderById(id);
    }
    public void deleteOrderById(Integer id) throws NotFoundException {
        orderService.deleteOrderBy(id);
    }
    public void updateOrder(Integer id) throws NotFoundException {
        orderService.searchOrderById(id);
    }
    public void addOrder(Order order) throws NotFoundException {
        orderService.addOrder(order);
    }
}
