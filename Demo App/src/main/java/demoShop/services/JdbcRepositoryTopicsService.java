package demoShop.services;

import demoShop.data.title.Topic;
import demoShop.api.services.TopicsService;
import org.springframework.stereotype.Component;
import demoShop.repositories.JdbcTopicsRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.util.Collection;

@Component
public class JdbcRepositoryTopicsService implements TopicsService {
    private JdbcTopicsRepository topicsRepo;

    @Autowired
    public JdbcRepositoryTopicsService(JdbcTopicsRepository topicsRepo) {
        this.topicsRepo = topicsRepo;
    }


    @Override
    public Collection<Topic> getAllTopics() {
        return topicsRepo.getAllTopics();
    }

    @Override
    public Topic getTopic(int id) {
        return topicsRepo.getTopic(id);
    }

    @Override
    public void addTopic(Topic topic) {
        topic.setPostedAt(new Date(System.currentTimeMillis()));
        topicsRepo.addTopic(topic);
    }

    @Override
    public void updateTopic(Topic topic) {
        topic.setPostedAt(new Date(System.currentTimeMillis()));
        topicsRepo.updateTopic(topic);
    }
}
