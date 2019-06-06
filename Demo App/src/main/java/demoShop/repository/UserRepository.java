package demoShop.repository;

import demoShop.parts.User;

import java.util.Collection;

public interface UserRepository {
    Collection<User> findAll();

    void deleteUser(int id);

    User findUser(int id);

    User addUser(User user);
}
