package demo.api.services;

import demo.data.user.User;
import demo.data.user.UserCredentials;
import demo.data.user.UserForUpdate;
import demo.data.user.UserToken;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

public interface UsersService {
    Collection<User> getAllUsers();

    String login(UserCredentials userCredentials);

    void logout(UserToken userToken);

    boolean isUserTokenValid(UserToken token);

    User getUser(int id);

    User getUser(HttpServletRequest request);

    void registerUser(User user);

    void updateUser(UserForUpdate userForUpdate, HttpServletRequest request);

    void subscribeUserToUser(int userId, int subToId);

    void unsubscribeUserFromUser(int userId, int subFromId);

    Collection<User> getUserSubscriptions(int userId, int offset, int size);

    Collection<User> searchUsers(String searchFor, int size);
}
