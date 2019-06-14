package demoShop.controlers;

import demoShop.data.user.User;
import demoShop.data.user.UserInfo;
import demoShop.api.services.UsersService;
import demoShop.data.user.UserToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

@RestController
@RequestMapping("/users")
public class UsersController {
    private final UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping
    public Collection<User> getAllUsers() {
        return usersService.getAllUsers();
    }

    @PostMapping("is_valid")
    public void isUserValid(@RequestBody UserToken userToken) {
        usersService.isUserTokenValid(userToken);
    }

    @GetMapping("/id")
    public User getUser(HttpServletRequest request) {
        return usersService.getUser(request);
    }

    @PostMapping("/register")
    public void registerUser(@RequestBody User user) {
        usersService.registerUser(user);
    }

    @PostMapping("/update")
    public void updateUser(@RequestBody User user, HttpServletRequest request) {
        usersService.updateUser(user, request);
    }

    @PostMapping("/login")
    public String login(@RequestBody UserInfo userInfo) {
        return usersService.login(userInfo);
    }

    @PostMapping("/logout")
    public void logout(@RequestBody UserToken userToken) {
        usersService.logout(userToken);
    }
}
