package service.impl;

import dao.interfaces.BasketDao;
import factory.BasketDaoFactory;
import model.Basket;
import model.Product;
import model.User;
import service.interfaces.BasketService;

import java.util.List;
import java.util.Optional;

public class BasketServiceImpl implements BasketService {

    private static final BasketDao basketDao = BasketDaoFactory.getInstance();

    @Override
    public void addProduct(Product product, User user) {
        basketDao.addProduct(product, user);
    }

    @Override
    public List<Product> getAllProducts(User user) {
        return basketDao.getAllProducts(user);
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return basketDao.getProductById(id);
    }

    @Override
    public void removeProduct(Product product, User user) {
        basketDao.removeProduct(product, user);
    }

    @Override
    public void clear(User user) {
        basketDao.clearBasket(user);
    }

    @Override
    public Optional<Basket> getBasketById(Long id) {
        return basketDao.getBasketById(id);
    }
}
