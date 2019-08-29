package demo.services;

import demo.api.repositories.TopicsRepository;
import demo.api.repositories.UsersRepository;
import demo.api.repositories.UsersTokensRepository;
import demo.api.services.TopicsService;
import demo.data.topic.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JdbcRepositoryTopicsService implements TopicsService {
    private UsersRepository usersRepo;
    private TopicsRepository topicsRepo;
    private UsersTokensRepository usersTokensRepo;

    @Autowired
    public JdbcRepositoryTopicsService(UsersRepository usersRepo,
                                       TopicsRepository topicsRepo,
                                       UsersTokensRepository usersTokensRepo) {
        this.usersRepo = usersRepo;
        this.topicsRepo = topicsRepo;
        this.usersTokensRepo = usersTokensRepo;
    }


    @Override
    public Collection<Topic> getAllTopics() {
        return topicsRepo.getAllTopics();
    }

    @Override
    public Collection<Topic> getFromWithOffset(int offset, int size) {
        return topicsRepo.getFromWithOffset(offset, size);
    }

    @Override
    public Collection<Topic> searchTopics(String searchFor, int size) {
        return searchTopics(searchFor, size, SearchType.BOTH);
    }

    @Override
    public Collection<Topic> searchTopicsByTitle(String searchFor, int size) {
        return searchTopics(searchFor, size, SearchType.TITLE);
    }

    @Override
    public Collection<Topic> searchTopicsByContent(String searchFor, int size) {
        return searchTopics(searchFor, size, SearchType.CONTENT);
    }

    @Override
    public Topic getTopic(int id) {
        return topicsRepo.getTopic(id);
    }

    @Override
    public void addTopic(Topic topic) {
        if (topic.getTitle() == null || topic.getTitle().isEmpty()) {
            throw new RuntimeException("Title shouldn't be empty");
        }
        topic.setPostedAt(new Timestamp(System.currentTimeMillis()));
        String usernameByToken = usersRepo.getUser(usersTokensRepo.getUserIdForToken(topic.getPostedBy())).getUsername();
        topic.setPostedBy(usernameByToken);
        topicsRepo.addTopic(topic);
    }

    @Override
    public void updateTopic(Topic topic) {
        if (topic.getTitle() == null || topic.getTitle().isEmpty()) {
            throw new RuntimeException("Title shouldn't be empty");
        }
        topic.setPostedAt(new Timestamp(System.currentTimeMillis()));
        String usernameByToken = usersRepo.getUser(usersTokensRepo.getUserIdForToken(topic.getPostedBy())).getUsername();
        topic.setPostedBy(usernameByToken);
        topicsRepo.updateTopic(topic);
    }

    private Collection<Topic> searchTopics(String searchFor, int size, SearchType searchType) {
        if (searchFor == null || searchFor.isEmpty() || size == 0) return topicsRepo.getAllTopics();
        List<String> titles = new ArrayList<>();
        titles.add(searchFor);
        titles.addAll(Arrays.asList(searchFor.split(" ")));
        return titles
                .stream()
                .flatMap(title -> {
                    if (searchType == SearchType.TITLE) {
                        return topicsRepo.searchTopicsByTitle(title, size).stream();
                    } else if (searchType == SearchType.CONTENT) {
                        return topicsRepo.searchTopicsByContent(title, size).stream();
                    }
                    return topicsRepo.searchTopics(title, size).stream();
                })
                .distinct()
                .sorted()
                .limit(size)
                .collect(Collectors.toList());
    }

    private enum SearchType {
        BOTH, TITLE, CONTENT
    }
}
