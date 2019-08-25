package dao.impl;

import dao.interfaces.BasketDao;
import model.Basket;
import model.Product;
import model.User;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import utils.HibernateUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BasketDaoHibImpl implements BasketDao {

    private static final Logger logger = Logger.getLogger(BasketDaoHibImpl.class);
    private static final String ADD_PRODUCT = "INSERT INTO baskets (product_id, product_name, " +
            "product_description, product_price, user_id) VALUES(?, ?, ?, ?, ?)";
    private static final String GET_ALL_PRODUCTS = "FROM Basket WHERE user_id = :user_id";
    private static final String REMOVE_PRODUCT = "DELETE Basket " +
            "WHERE product_id = :product_id AND user_id = :user_id";
    private static final String CLEAR_BASKET = "DELETE FROM Basket WHERE user_id = :user_id";


    @Override
    public void addProduct(Product product, User user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            NativeQuery nativeQuery = session.createSQLQuery(ADD_PRODUCT);
            nativeQuery.setParameter(1, product.getId());
            nativeQuery.setParameter(2, product.getName());
            nativeQuery.setParameter(3, product.getDescription());
            nativeQuery.setParameter(4, product.getPrice());
            nativeQuery.setParameter(5, user.getId());
            nativeQuery.executeUpdate();
            logger.info(product + " added to basket");
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Exception: " + e);
        }
    }

    @Override
    public List<Product> getAllProducts(User user) {
        List<Product> products = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery(GET_ALL_PRODUCTS);
            query.setParameter("user_id", user.getId());
            List<Basket> baskets = query.list();
            for (Basket b : baskets) {
                products.add(new Product(
                        b.getProduct_id(),
                        b.getProduct_name(),
                        b.getProduct_description(),
                        b.getPrice()));
            }
            return products;
        } catch (Exception e) {
            logger.error("Exception: " + e);
        }
        return products;
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return Optional.ofNullable(session.get(Product.class, id));
        } catch (Exception e) {
            logger.error("Exception: " + e);
        }
        return Optional.empty();
    }

    @Override
    public void removeProduct(Product product, User user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery(REMOVE_PRODUCT);
            query.setParameter("product_id", product.getId());
            query.setParameter("user_id", user.getId());
            query.executeUpdate();
            logger.info(product + " deleted from basket");
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Exception: " + e);
        }
    }

    @Override
    public void clearBasket(User user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery(CLEAR_BASKET);
            query.setParameter("user_id", user.getId());
            query.executeUpdate();
            logger.info("Basket of user " + user.getId() + " is cleared");
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Exception: " + e);
        }
    }

    public Optional<Basket> getBasketById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return Optional.ofNullable(session.get(Basket.class, id));
        } catch (Exception e) {
            logger.error("Exception: " + e);
        }
        return Optional.empty();
    }
}
