package demo.api.services;

import demo.data.topic.Topic;

import java.util.Collection;

public interface TopicsService {
    Collection<Topic> getAllTopics();

    Collection<Topic> getFromWithOffset(int offset, int size);

    Collection<Topic> searchTopics(String searchFor, int size);

    Topic getTopic(int id);

    void addTopic(Topic topic);

    void updateTopic(Topic topic);
}
