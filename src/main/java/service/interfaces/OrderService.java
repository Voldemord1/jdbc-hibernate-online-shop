package service.interfaces;

import model.Order;

import java.util.Optional;

public interface OrderService {

    Optional<Long> addOrder(Order order);

    Optional<Order> getOrderById(Long id);
}
