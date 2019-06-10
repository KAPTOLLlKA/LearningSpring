package demoShop.api.services;

import demoShop.data.user.User;
import demoShop.data.user.UserInfo;

import java.util.Collection;

public interface UsersService {
    Collection<User> getAllUsers();

    String login(UserInfo userInfo);

    void logout(int id);

    User getUser(int id);

    void addUser(User user);

    void updateUser(User user);
}
