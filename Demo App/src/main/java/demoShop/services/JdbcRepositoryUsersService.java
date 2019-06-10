package demoShop.services;

import demoShop.data.user.User;
import demoShop.api.services.UsersService;
import demoShop.data.user.UserInfo;
import demoShop.api.repositories.UsersRepository;
import demoShop.api.repositories.UsersTokensRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

public class JdbcRepositoryUsersService implements UsersService {
    private final UsersRepository userRepo;
    private final UsersTokensRepository usersTokensRepo;

    @Autowired
    public JdbcRepositoryUsersService(UsersRepository userRepo, UsersTokensRepository usersTokensRepo) {
        this.userRepo = userRepo;
        this.usersTokensRepo = usersTokensRepo;
    }

    @Override
    public Collection<User> getAllUsers() {
        return userRepo.getAllUsers();
    }

    @Override
    public String login(UserInfo userInfo) {
        User user = userRepo.getUser(userInfo.getUsername(), userInfo.getPassword());
        if (user != null) {
            return usersTokensRepo.createTokenFor(user.getId());
        }
        return null;
    }

    @Override
    public void logout(int id) {
        usersTokensRepo.deleteUserToken(id);
    }

    @Override
    public User getUser(int id) {
        return userRepo.getUser(id);
    }

    @Override
    public void addUser(User user) {
        userRepo.addUser(user);
    }

    @Override
    public void updateUser(User user) {
        userRepo.updateUser(user);
    }
}
