package demo.controlers;

import demo.data.user.*;
import demo.api.services.UsersService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
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
    public boolean isUserValid(@RequestBody UserToken userToken) {
        return usersService.isUserTokenValid(userToken);
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
    public void updateUser(@RequestBody UserForUpdate userForUpdate, HttpServletRequest request) {
        usersService.updateUser(userForUpdate, request);
    }

    @PostMapping("/login")
    public String login(@RequestBody UserCredentials userCredentials) {
        return usersService.login(userCredentials);
    }

    @PostMapping("/logout")
    public void logout(@RequestBody UserToken userToken) {
        usersService.logout(userToken);
    }

    @PostMapping("/subscribe")
    public void subscribeUserToUser(@RequestParam Subscription subscription) {
        usersService.subscribeUserToUser(subscription.getUserId(), subscription.getSubId());
    }

    @PostMapping("/unsubscribe")
    public void unsubscribeUserFromUser(@RequestParam Subscription subscription) {
        usersService.unsubscribeUserFromUser(subscription.getUserId(), subscription.getSubId());
    }

    @GetMapping("/subscriptions/{userId}")
    public Collection<User> getUserSubscriptions(@PathVariable Integer userId) {
        return usersService.getUserSubscriptions(userId);
    }
}
