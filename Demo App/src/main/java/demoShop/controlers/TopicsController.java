package demoShop.controlers;

import demoShop.data.topic.Topic;
import demoShop.api.services.TopicsService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

@RestController
@RequestMapping("/topics")//Finish with topic editing
public class TopicsController {
    private final TopicsService topicsService;

    @Autowired
    public TopicsController(TopicsService topicsService) {
        this.topicsService = topicsService;
    }

    @GetMapping
    public Collection<Topic> getAllTopics() {
        return topicsService.getAllTopics();
    }

    @GetMapping("search/{search_for}")
    public Collection<Topic> searchForTopic(@PathVariable String search_for) {
        return topicsService.searchTopics(search_for);
    }

    @GetMapping("/{id}")
    public Topic getTopic(@PathVariable int id) {
        return topicsService.getTopic(id);
    }

    @PostMapping("/add")
    public void addTopic(@RequestBody Topic topic) {
        topicsService.addTopic(topic);
    }

    @PostMapping("/update")
    public void updateTopic(@RequestBody Topic topic) {
        topicsService.updateTopic(topic);
    }
}
