package demoShop.services;

import demoShop.parts.User;
import demoShop.api.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

public class UserService {
    private final UserRepository userRepo;

    @Autowired
    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public Collection<User> getAllUsers() {
        return userRepo.getAllUsers();
    }

    public User addUser(User user) {
        return userRepo.addUser(user);
    }

    public User getUser(int id) {
        return userRepo.getUser(id);
    }

    public User updateUser(User user) {
        return userRepo.updateUser(user);
    }
}
