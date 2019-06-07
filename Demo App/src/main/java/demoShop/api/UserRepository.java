package demoShop.repository;

import demoShop.parts.User;

import java.util.Collection;

public interface UserRepository {
    Collection<User> getAllUsers();

    void deleteUser(int id);

    User getUser(int id);

    User updateUser(User user);

    User addUser(User user);
}
