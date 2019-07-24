package demo.controlers;

import demo.data.topic.Topic;
import demo.api.services.TopicsService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

@RestController
@RequestMapping("/topics")
@CrossOrigin("*")
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

    @GetMapping("search/{searchFor}")
    public Collection<Topic> searchForTopic(@PathVariable String searchFor) {
        return topicsService.searchTopics(searchFor);
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
