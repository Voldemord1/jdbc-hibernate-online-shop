package factory;

import dao.impl.UserDaoHibImpl;
import dao.interfaces.UserDao;

import java.util.Objects;

public class UserDaoFactory {

    private static UserDao instance;

    private UserDaoFactory() {
    }

    public static UserDao getInstance() {
        return (Objects.isNull(instance)) ? instance = new UserDaoHibImpl() : instance;
    }
}
