package service.interfaces;

import model.Basket;
import model.Product;
import model.User;

import java.util.List;
import java.util.Optional;

public interface BasketService {

    void addProduct(Product product, User user);

    List<Product> getAllProducts(User user);

    Optional<Product> getProductById(Long id);

    void removeProduct(Product product, User user);

    void clear(User user);

    Optional<Basket> getBasketById(Long id);
}
