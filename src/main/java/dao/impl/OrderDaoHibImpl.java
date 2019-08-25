package dao.impl;

import dao.interfaces.OrderDao;
import model.Order;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtil;

import java.util.Optional;

public class OrderDaoHibImpl implements OrderDao {

    private static final Logger logger = Logger.getLogger(OrderDaoJdbcImpl.class);

    @Override
    public Optional<Long> addOrder(Order order) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(order);
            logger.info(order + " added to db");
            transaction.commit();
            return Optional.of(order.getId());
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Exception: " + e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Order> getOrderById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return Optional.ofNullable(session.get(Order.class, id));
        } catch (Exception e) {
            logger.error("Exception: " + e);
        }
        return Optional.empty();
    }
}
