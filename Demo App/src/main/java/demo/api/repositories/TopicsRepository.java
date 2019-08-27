package demo.api.repositories;

import demo.data.topic.Topic;

import java.util.Collection;

public interface TopicsRepository {
    Collection<Topic> getAllTopics();

    Collection<Topic> getFromWithOffset(int offset, int size);

    Topic getTopic(int id);

    Collection<Topic> searchTopic(String searchFor, int size);

    int getTopicIdForUsername(String title);

    void updateTopic(Topic topic);

    void addTopic(Topic topic);

    void deleteTopic(int id);
}
