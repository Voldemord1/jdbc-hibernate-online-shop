package dao.impl;

import dao.interfaces.BasketDao;
import model.Basket;
import model.Product;
import model.User;
import org.apache.log4j.Logger;
import utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BasketDaoJdbcImpl implements BasketDao {

    private static final Logger logger = Logger.getLogger(BasketDaoJdbcImpl.class);
    private static final String ADD_PRODUCT = "INSERT INTO basket(product_id, product_name, " +
            "product_description, product_price, user_id) VALUES(?, ?, ?, ?, ?)";
    private static final String GET_ALL_PRODUCTS = "SELECT * FROM basket WHERE user_id = ?";
    private static final String GET_PRODUCT_BY_ID = "SELECT FROM basket WHERE id = ?";
    private static final String REMOVE_PRODUCT = "DELETE FROM basket " +
            "WHERE product_id = ? AND user_id = ?";
    private static final String CLEAR_BASKET = "DELETE FROM basket WHERE user_id = ?";

    @Override
    public void addProduct(Product product, User user) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_PRODUCT)) {
            preparedStatement.setString(1, String.valueOf(product.getId()));
            preparedStatement.setString(2, product.getName());
            preparedStatement.setString(3, product.getDescription());
            preparedStatement.setString(4, String.valueOf(product.getPrice()));
            preparedStatement.setString(5, String.valueOf(user.getId()));
            preparedStatement.execute();
            logger.info(product + "added to basket");
        } catch (SQLException e) {
            logger.error("SQl exception " + e.getMessage());
        }
    }

    @Override
    public List<Product> getAllProducts(User user) {
        List<Product> productList = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_PRODUCTS)) {
            preparedStatement.setString(1, String.valueOf(user.getId()));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                productList.add(new Product(
                        resultSet.getLong("product_id"),
                        resultSet.getString("product_name"),
                        resultSet.getString("product_description"),
                        resultSet.getDouble("product_price")));
            }
        } catch (SQLException e) {
            logger.error("SQl exception " + e.getMessage());
            return null;
        }
        return productList;
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_PRODUCT_BY_ID)) {
            preparedStatement.setString(1, String.valueOf(id));
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(new Product(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getDouble("price")));
            }
        } catch (SQLException e) {
            logger.error("SQl exception " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<Basket> getBasketById(Long id) {
        return Optional.empty();
    }

    @Override
    public void removeProduct(Product product, User user) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_PRODUCT)) {
            preparedStatement.setString(1, String.valueOf(product.getId()));
            preparedStatement.setString(2, String.valueOf(user.getId()));
            preparedStatement.execute();
            logger.info(product + " removed from basket successful");
        } catch (SQLException e) {
            logger.error("SQl exception " + e.getMessage());
        }
    }

    @Override
    public void clearBasket(User user) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CLEAR_BASKET)) {
            preparedStatement.setString(1, String.valueOf(user.getId()));
            preparedStatement.execute();
            logger.info("Basket of user " + user.getId() + " is cleared");
        } catch (SQLException e) {
            logger.error("SQl exception " + e.getMessage());
        }
    }
}
