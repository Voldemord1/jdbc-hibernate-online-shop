package service.impl;

import dao.interfaces.ProductDao;
import factory.ProductDaoFactory;
import model.Product;
import service.interfaces.ProductService;

import java.util.List;
import java.util.Optional;

public class ProductServiceImp implements ProductService {

    private static final ProductDao productDao = ProductDaoFactory.getInstance();

    @Override
    public void addProduct(Product product) {
        productDao.addProduct(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productDao.getAllProducts();
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return productDao.getProductById(id);
    }

    @Override
    public void removeProduct(Product product) {
        productDao.removeProduct(product);
    }

    @Override
    public void updateProduct(Product product) {
        productDao.updateProduct(product);
    }
}
