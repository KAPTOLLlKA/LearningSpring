package demoShop.api.repositories;

import demoShop.data.user.User;

import java.util.Collection;

public interface UsersRepository {
    Collection<User> getAllUsers();

    User getUser(int id);

    User getUser(String username, String password);

    void updateUser(User user);

    boolean registerUser(User user);

    void deleteUser(int id);
}
