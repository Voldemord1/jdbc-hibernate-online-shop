package service.interfaces;

import model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    void addUser(User user);

    List<User> getAllUsers();

    Optional<User> getUserById(Long id);

    Optional<User> getUserByEmail(String email);

    void updateUser(User user);

    void deleteUser(User user);

    boolean isUserExist(String email);
}
