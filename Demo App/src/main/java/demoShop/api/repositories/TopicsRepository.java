package demoShop.api.repositories;

import demoShop.data.topic.Topic;

import java.util.Collection;

public interface TopicsRepository {
    Collection<Topic> getAllTopics();

    Topic getTopic(int id);

    Collection<Topic> searchTopicByTitle(String title);

    int getTopicIdForUsername(String title);

    void updateTopic(Topic topic);

    void addTopic(Topic topic);

    void deleteTopic(int id);
}
