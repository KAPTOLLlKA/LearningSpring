package demoShop.services;

import demoShop.data.topic.Topic;
import demoShop.api.services.TopicsService;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import demoShop.api.repositories.UsersRepository;
import demoShop.api.repositories.TopicsRepository;
import demoShop.api.repositories.UsersTokensRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.util.*;

@Component
public class JdbcRepositoryTopicsService implements TopicsService {
    private UsersRepository usersRepo;
    private TopicsRepository topicsRepo;
    private UsersTokensRepository usersTokensRepo;

    @Autowired
    public JdbcRepositoryTopicsService(UsersRepository usersRepo, TopicsRepository topicsRepo, UsersTokensRepository usersTokensRepo) {
        this.usersRepo = usersRepo;
        this.topicsRepo = topicsRepo;
        this.usersTokensRepo = usersTokensRepo;
    }


    @Override
    public Collection<Topic> getAllTopics() {
        return topicsRepo.getAllTopics();
    }

    @Override
    public Collection<Topic> searchTopics(String searchFor) {
        if (searchFor == null || searchFor.isEmpty()) return topicsRepo.getAllTopics();
        String[] titles = searchFor.split("\\+");
        Set<Topic> topics = new HashSet<>();
        for (String title : titles) {
            try {
                topics.addAll(topicsRepo.searchTopicByTitle(title));
            } catch (DataAccessException ignored) {
            }
        }
        return topics;
    }

    @Override
    public Topic getTopic(int id) {
        return topicsRepo.getTopic(id);
    }

    @Override
    public void addTopic(Topic topic) {
        topic.setPostedAt(new Date(System.currentTimeMillis()));
        String usernameByToken = usersRepo.getUser(usersTokensRepo.getUserIdForToken(topic.getPostedBy())).getUsername();
        topic.setPostedBy(usernameByToken);
        topicsRepo.addTopic(topic);
    }

    @Override
    public void updateTopic(Topic topic) {
        topic.setPostedAt(new Date(System.currentTimeMillis()));
        topicsRepo.updateTopic(topic);
    }
}
