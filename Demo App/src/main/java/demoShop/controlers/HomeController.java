package demoShop.controlers;

import demoShop.parts.User;
import demoShop.repository.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

@RestController
@RequestMapping("/users")
public class HomeController {
    private final UserRepository userRepo;

    @Autowired
    public HomeController(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping
    public Collection<User> users() {
        return userRepo.findAll();
    }
}
