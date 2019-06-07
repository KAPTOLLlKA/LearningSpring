package demoShop.services;

import demoShop.parts.User;
import demoShop.parts.UserInfo;
import demoShop.api.UserService;
import demoShop.api.UserRepository;
import demoShop.api.UsersTokensRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

public class JdbcRepositoryUserService implements UserService {
    private final UserRepository userRepo;
    private final UsersTokensRepository usersTokensRepo;

    @Autowired
    public JdbcRepositoryUserService(UserRepository userRepo, UsersTokensRepository usersTokensRepo) {
        this.userRepo = userRepo;
        this.usersTokensRepo = usersTokensRepo;
    }

    @Override
    public Collection<User> getAllUsers() {
        return userRepo.getAllUsers();
    }

    @Override
    public User login(UserInfo userInfo) {
        User user = userRepo.getUser(userInfo.getUsername(), userInfo.getPassword());
        if (user != null) {
            usersTokensRepo.createTokenFor(user.getId());
        }
        return user;
    }

    @Override
    public User logout(UserInfo userInfo) {
        User user = userRepo.getUser(userInfo.getUsername(), userInfo.getPassword());
        if (user != null) {
            usersTokensRepo.deleteUserToken(user.getId());
        }
        return user;
    }

    @Override
    public User addUser(User user) {
        return userRepo.addUser(user);
    }

    @Override
    public User getUser(int id) {
        return userRepo.getUser(id);
    }

    @Override
    public User updateUser(User user) {
        return userRepo.updateUser(user);
    }
}
