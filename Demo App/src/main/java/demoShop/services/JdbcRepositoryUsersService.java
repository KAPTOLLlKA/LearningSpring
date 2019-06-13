package demoShop.services;

import demoShop.data.user.User;
import demoShop.data.user.UserInfo;
import demoShop.api.services.UsersService;
import demoShop.data.user.UserToken;
import demoShop.exceptions.NotAllFieldsFilled;
import demoShop.api.repositories.UsersRepository;
import demoShop.exceptions.UsernameIsTakenException;
import demoShop.exceptions.WrongCredentialsException;
import demoShop.api.repositories.UsersTokensRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
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
        throw new WrongCredentialsException("Wrong username or password");
    }

    @Override
    public void logout(UserToken userToken) {
        usersTokensRepo.deleteUserToken(userToken.getToken());
    }

    @Override
    public User getUser(int id) {
        return userRepo.getUser(id);
    }

    @Override
    public User getUser(HttpServletRequest request) {
        int id = usersTokensRepo.getUserIdForToken(request.getHeader("authorization"));
        return userRepo.getUser(id);
    }

    @Override
    public void registerUser(User user) {
        if (user.getMail().isEmpty() ||
                user.getPassword().isEmpty() ||
                user.getUsername().isEmpty()) {
            throw new NotAllFieldsFilled("Not all mandatory fields are filled");
        }
        if (!userRepo.registerUser(user)) {
            throw new UsernameIsTakenException("Username is already taken");
        }
    }

    @Override
    public void updateUser(User user, HttpServletRequest request) {
        if (user.getMail().isEmpty() ||
                user.getPassword().isEmpty() ||
                user.getUsername().isEmpty()) {
            throw new NotAllFieldsFilled("Not all mandatory fields are filled");
        }
        user.setId(usersTokensRepo.getUserIdForToken(request.getHeader("authorization")));
        if (!userRepo.updateUser(user)) {
            throw new UsernameIsTakenException("Username is already taken");
        }
    }
}
