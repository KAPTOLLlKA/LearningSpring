package demo.api.repositories;

import demo.data.user.User;

import java.util.Collection;

public interface UsersRepository {
    Collection<User> getAllUsers();

    User getUser(int id);

    User getUser(String username, String password);

    boolean updateUser(User user);

    boolean registerUser(User user);

    Collection<User> searchUsers(String searchFor, int size);

    void deleteUser(int id);
}
