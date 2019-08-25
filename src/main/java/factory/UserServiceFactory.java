package factory;

import service.interfaces.UserService;
import service.impl.UserServiceImpl;

import java.util.Objects;

public class UserServiceFactory {

    private static UserService instance;

    private UserServiceFactory() {
    }

    public static UserService getInstance() {
        return (Objects.isNull(instance)) ? instance = new UserServiceImpl() : instance;
    }
}
