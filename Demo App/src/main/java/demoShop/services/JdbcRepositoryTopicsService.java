package demoShop.services;

import demoShop.data.topic.Topic;
import demoShop.api.services.TopicsService;
import org.springframework.stereotype.Component;
import demoShop.api.repositories.UsersRepository;
import demoShop.api.repositories.TopicsRepository;
import demoShop.api.repositories.UsersTokensRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.util.Collection;

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
