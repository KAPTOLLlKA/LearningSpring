package demo.services;

import demo.api.repositories.SubscriptionsRepository;
import demo.data.user.User;
import demo.data.user.UserCredentials;
import demo.data.user.UserForUpdate;
import demo.data.user.UserToken;
import demo.api.services.UsersService;
import demo.exceptions.NotAllFieldsFilled;
import demo.api.repositories.UsersRepository;
import demo.exceptions.UnauthorizedException;
import org.springframework.dao.DataAccessException;
import demo.exceptions.UsernameIsTakenException;
import demo.exceptions.WrongCredentialsException;
import demo.api.repositories.UsersTokensRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.UUID;
import java.util.stream.Collectors;

public class JdbcRepositoryUsersService implements UsersService {
    private final UsersRepository userRepo;
    private final UsersTokensRepository usersTokensRepo;
    private final SubscriptionsRepository subscriptionsRepo;

    @Autowired
    public JdbcRepositoryUsersService(UsersRepository userRepo,
                                      UsersTokensRepository usersTokensRepo,
                                      SubscriptionsRepository subscriptionsRepo) {
        this.userRepo = userRepo;
        this.usersTokensRepo = usersTokensRepo;
        this.subscriptionsRepo = subscriptionsRepo;
    }

    @Override
    public Collection<User> getAllUsers() {
        return userRepo.getAllUsers();
    }

    @Override
    public String login(UserCredentials userCredentials) {
        User user = userRepo.getUser(userCredentials.getUsername(), userCredentials.getPassword());
        if (user != null) {
            String token = UUID.randomUUID().toString();
            usersTokensRepo.addTokenFor(user.getId(), token);
            return token;
        }
        throw new WrongCredentialsException("Wrong username or password");
    }

    @Override
    public void logout(UserToken userToken) {
        usersTokensRepo.deleteUserToken(userToken.getToken());
    }

    @Override
    public boolean isUserTokenValid(UserToken token) {
        try {
            usersTokensRepo.getUserIdForToken(token.getToken());
            return true;
        } catch (DataAccessException e) {
            return false;
        }
    }

    @Override
    public User getUser(int id) {
        return userRepo.getUser(id);
    }

    @Override
    public User getUser(HttpServletRequest request) {
        try {
            int id = usersTokensRepo.getUserIdForToken(request.getHeader("authorization"));
            return userRepo.getUser(id);
        } catch (DataAccessException e) {
            throw new RuntimeException("Invalid authorization token");
        }
    }

    @Override
    public void registerUser(User user) {
        if (user.getEmail() == null ||
                user.getPassword() == null ||
                user.getUsername() == null ||
                user.getEmail().isEmpty() ||
                user.getPassword().isEmpty() ||
                user.getUsername().isEmpty()) {
            throw new NotAllFieldsFilled("Not all mandatory fields are filled");
        }
        if (!userRepo.registerUser(user)) {
            throw new UsernameIsTakenException("Username is already taken");
        }
    }

    @Override
    public void updateUser(UserForUpdate userForUpdate, HttpServletRequest request) {
        userForUpdate.setId(usersTokensRepo.getUserIdForToken(request.getHeader("authorization")));
        User user = userRepo.getUser(userForUpdate.getId());
        if (!userForUpdate.getCurrentPassword().equals(user.getPassword())) {
            throw new RuntimeException("Entered password doesn't match current one");
        }

        if (userForUpdate.getEmail() != null && !userForUpdate.getEmail().isEmpty()) {
            user.setEmail(userForUpdate.getEmail());
        }
        if (userForUpdate.getAbout() != null && !userForUpdate.getAbout().isEmpty()) {
            user.setAbout(userForUpdate.getAbout());
        }
        if (userForUpdate.getPassword() != null && !userForUpdate.getPassword().isEmpty()) {
            user.setPassword(userForUpdate.getPassword());
        }
        userRepo.updateUser(user);
    }

    @Override
    public void subscribeUserToUser(int userId, int subToId) {
        subscriptionsRepo.subscribeUserToUser(userId, subToId);
    }

    @Override
    public void unsubscribeUserFromUser(int userId, int subFromId) {
        subscriptionsRepo.unsubscribeUserFromUser(userId, subFromId);
    }

    @Override
    public Collection<User> getUserSubscriptions(int userId, int offset, int size) {
        return subscriptionsRepo.getUserSubscriptions(userId, offset, size)
                .stream()
                .map(userRepo::getUser)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<User> searchUsers(String searchFor, int size) {
        if (searchFor == null || searchFor.isEmpty() || size == 0) return userRepo.getAllUsers();
        String[] names = searchFor.split(" ");
        return Arrays.stream(names)
                .flatMap(name -> userRepo.searchUsers(name, size).stream())
                .distinct()
                .sorted()
                .limit(size)
                .collect(Collectors.toList());
    }
}
