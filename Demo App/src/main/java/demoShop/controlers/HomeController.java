package demoShop.controlers;

import demoShop.parts.Tire;
import demoShop.repository.TireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tire")
public class HomeController {
    private final TireRepository tireRepo;

    @Autowired
    public HomeController(TireRepository tireRepo) {
        this.tireRepo = tireRepo;
    }

    @GetMapping
    public Collection<Tire> home() {
        return tireRepo.findAll();
    }

    private List<Tire> filterByType(
            List<Tire> ingredients, Tire.Type type) {
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }
}
