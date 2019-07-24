package demo.api.repositories;

import demo.data.user.User;

import java.util.Collection;

public interface UsersRepository {
    Collection<User> getAllUsers();

    User getUser(int id);

    User getUser(String username, String password);

    boolean updateUser(User user);

    boolean registerUser(User user);

    void deleteUser(int id);
}
