package dao.impl;

import dao.interfaces.ProductDao;
import model.Product;
import storage.Storage;

import java.util.List;
import java.util.Optional;

public class ProductDaoImpl implements ProductDao {

    @Override
    public void addProduct(Product product) {
        Storage.productList.add(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return Storage.productList;
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return Storage.productList
                .stream()
                .filter(product -> product.getId().equals(id))
                .findFirst();
    }

    @Override
    public void removeProduct(Product product) {
        Storage.productList.remove(product);
    }

    @Override
    public void updateProduct(Product product) {
        Optional<Product> oldProduct = getProductById(product.getId());
        if (oldProduct.isPresent()) {
            int index = Storage.productList.indexOf(oldProduct.get());
            Storage.productList.set(index, product);
        }
    }
}
