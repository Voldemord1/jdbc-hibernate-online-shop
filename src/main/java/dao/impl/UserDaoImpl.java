package dao.impl;

import dao.interfaces.UserDao;
import model.User;
import org.apache.log4j.Logger;
import storage.Storage;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

public class UserDaoImpl implements UserDao {

    private static final Logger logger = Logger.getLogger(UserDaoImpl.class);

    @Override
    public void addUser(User user) {
        Storage.userList.add(user);
        logger.info(user + " added to db");
    }

    @Override
    public List<User> getAllUsers() {
        return Storage.userList;
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return Storage.userList
                .stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return Storage.userList
                .stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst();
    }

    @Override
    public void removeUser(User user) {
        Storage.userList.remove(user);
    }

    @Override
    public void updateUser(User user) {
        Optional<User> oldUser = getUserById(user.getId());
        if (oldUser.isPresent()) {
            int index = Storage.userList.indexOf(oldUser.get());
            Storage.userList.set(index, user);
        }
    }
}
