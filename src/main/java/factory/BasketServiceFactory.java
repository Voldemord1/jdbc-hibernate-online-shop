package factory;

import service.impl.BasketServiceImpl;
import service.interfaces.BasketService;

import java.util.Objects;

public class BasketServiceFactory {

    private static BasketService instance;

    private BasketServiceFactory() {
    }

    public static BasketService getInstance() {
        return (Objects.isNull(instance)) ? instance = new BasketServiceImpl() : instance;
    }
}
