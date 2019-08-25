package service.impl;

import dao.interfaces.OrderDao;
import factory.OrderDaoFactory;
import model.Order;
import service.interfaces.OrderService;

import java.util.Optional;

public class OrderServiceImpl implements OrderService {

    private static final OrderDao orderDao = OrderDaoFactory.getInstance();

    @Override
    public Optional<Long> addOrder(Order order) {
        return orderDao.addOrder(order);
    }

    @Override
    public Optional<Order> getOrderById(Long id) {
        return orderDao.getOrderById(id);
    }
}
