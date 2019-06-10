package demoShop.controlers;

import demoShop.api.services.TopicsService;
import demoShop.data.title.Topic;
import demoShop.data.user.User;
import demoShop.api.services.UsersService;
import demoShop.data.user.UserInfo;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

@RestController
@RequestMapping
public class HomeController {
    private final UsersService usersService;
    private final TopicsService topicsService;

    @Autowired
    public HomeController(UsersService usersService, TopicsService topicsService) {
        this.usersService = usersService;
        this.topicsService = topicsService;
    }

    @GetMapping("/users")
    public Collection<User> getAllUsers() {
        return usersService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable Integer id) {
        return usersService.getUser(id);
    }

    @PutMapping("/users/add")
    public void addUser(@RequestBody User user) {
        usersService.addUser(user);
    }

    @PostMapping("/users/update")
    public void updateUser(@RequestBody User user) {
        usersService.updateUser(user);
    }

    @PostMapping("/users/login")
    public String login(@RequestBody UserInfo userInfo) {
        return usersService.login(userInfo);
    }

    @PostMapping("/users/logout")
    public void logout(@RequestBody Integer id) {
        usersService.logout(id);
    }

    @GetMapping("/topics")
    public Collection<Topic> getAllTopics() {
        return topicsService.getAllTopics();
    }

    @GetMapping("/topics/{id}")
    public Topic getTopic(@PathVariable int id) {
        return topicsService.getTopic(id);
    }

    @PostMapping("/topics/add")
    public void addTopic(@RequestBody Topic topic) {
        topicsService.addTopic(topic);
    }

    @PostMapping("/topics/update")
    public void updateTopic(@RequestBody Topic topic) {
        topicsService.updateTopic(topic);
    }
}
