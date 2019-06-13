package demoShop.api.services;

import demoShop.data.user.User;
import demoShop.data.user.UserInfo;
import demoShop.data.user.UserToken;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

public interface UsersService {
    Collection<User> getAllUsers();

    String login(UserInfo userInfo);

    void logout(UserToken userToken);

    User getUser(int id);

    User getUser(HttpServletRequest request);

    void registerUser(User user);

    void updateUser(User user, HttpServletRequest request);
}
