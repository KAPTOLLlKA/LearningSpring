package demoShop.api;

import demoShop.parts.User;
import demoShop.parts.UserInfo;

import java.util.Collection;

public interface UserService {
    Collection<User> getAllUsers();

    User login(UserInfo userInfo);

    User logout(UserInfo userInfo);

    User addUser(User user);

    User getUser(int id);

    User updateUser(User user);
}
