package service.interfaces;

import model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    void addProduct(Product product);

    List<Product> getAllProducts();

    Optional<Product> getProductById(Long id);

    void removeProduct(Product product);

    void updateProduct(Product product);
}
