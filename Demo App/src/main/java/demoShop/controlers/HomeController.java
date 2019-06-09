package demoShop.controlers;

import demoShop.parts.User;
import demoShop.parts.UserInfo;
import demoShop.api.UserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

@RestController
@RequestMapping("/users")
public class HomeController {
    private final UserService userService;

    @Autowired
    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @ResponseBody
    public Collection<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/login")
    public User login(@RequestBody UserInfo userInfo) {
        return userService.login(userInfo);
    }

    @PostMapping("/logout")
    public void logout(@RequestBody UserInfo userInfo) {
        userService.logout(userInfo);
    }

    @PutMapping
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Integer id) {
        return userService.getUser(id);
    }

    @PostMapping
    public void updateUser(@RequestBody User user) {
        userService.updateUser(user);
    }
}
