package demoShop.api;

import demoShop.parts.User;

import java.util.Collection;

public interface UserRepository {
    Collection<User> getAllUsers();

    User getUser(int id);

    User getUser(String username, String password);

    User updateUser(User user);

    User addUser(User user);

    void deleteUser(int id);
}
