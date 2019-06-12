package demoShop.controlers;

import demoShop.data.user.User;
import demoShop.data.user.UserInfo;
import demoShop.api.services.UsersService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

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

    @GetMapping("/{id}")
    public User getUser(@PathVariable Integer id) {
        return usersService.getUser(id);
    }

    @PostMapping("/register")
    public void registerUser(@RequestBody User user) {
        usersService.registerUser(user);
    }

    @PostMapping("/update")
    public void updateUser(@RequestBody User user) {
        usersService.updateUser(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody UserInfo userInfo) {
        return usersService.login(userInfo);
    }

    @PostMapping("/logout/{id}")
    public void logout(@PathVariable Integer id) {
        usersService.logout(id);
    }
}
