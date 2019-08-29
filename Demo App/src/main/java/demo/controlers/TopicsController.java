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

    @GetMapping("/{id}")
    public Topic getTopic(@PathVariable int id) {
        return topicsService.getTopic(id);
    }

    @PostMapping
    public Collection<Topic> getTopics(@RequestParam(required = false) Integer offset,
                                       @RequestParam(required = false) Integer size) {
        return topicsService.getFromWithOffset(offset, size);
    }

    @PostMapping("/search/everything")
    public Collection<Topic> searchForTopics(@RequestParam(required = false) String searchFor,
                                            @RequestParam(required = false) Integer size) {
        return topicsService.searchTopics(searchFor, size == null ? 0 : size);
    }

    @PostMapping("/search/title")
    public Collection<Topic> searchForTopicsByTitle(@RequestParam(required = false) String searchFor,
                                            @RequestParam(required = false) Integer size) {
        return topicsService.searchTopicsByTitle(searchFor, size == null ? 0 : size);
    }

    @PostMapping("/search/content")
    public Collection<Topic> searchForTopicsByContent(@RequestParam(required = false) String searchFor,
                                            @RequestParam(required = false) Integer size) {
        return topicsService.searchTopicsByContent(searchFor, size == null ? 0 : size);
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
