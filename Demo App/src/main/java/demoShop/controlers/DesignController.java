package demoShop.controlers;

import demoShop.parts.Tire;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/design")
public class DesignController {
    @GetMapping
    public String showDesignForm(Model model) {
        List<Tire> tires = Arrays.asList(
                new Tire(0, "Goodyear", Tire.Type.SUMMER),
                new Tire(1, "Goodyear", Tire.Type.WINTER),
                new Tire(2, "Bridgestone", Tire.Type.ALL_SEASON),
                new Tire(3, "Continental", Tire.Type.WINTER)
                );

        Tire.Type[] types = Tire.Type.values();
        for (Tire.Type type : types) {
//            model.addAttribute(type.toString().toLowerCase(), )
        }
        return "design.html";
    }
}
