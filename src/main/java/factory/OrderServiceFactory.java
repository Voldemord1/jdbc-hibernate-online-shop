package factory;

import service.impl.OrderServiceImpl;
import service.interfaces.OrderService;

import java.util.Objects;

public class OrderServiceFactory {

    private static OrderService instance;

    private OrderServiceFactory() {
    }

    public static OrderService getInstance() {
        return (Objects.isNull(instance)) ? instance = new OrderServiceImpl() : instance;
    }
}
