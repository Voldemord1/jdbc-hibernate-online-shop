package dao.interfaces;

import model.Order;

import java.util.Optional;

public interface OrderDao {

    Optional<Long> addOrder(Order order);

    Optional<Order> getOrderById(Long id);
}
