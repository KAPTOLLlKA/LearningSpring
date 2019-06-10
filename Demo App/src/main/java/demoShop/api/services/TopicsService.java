package demoShop.api.services;

import demoShop.data.title.Topic;

import java.util.Collection;

public interface TopicsService {
    Collection<Topic> getAllTopics();

    Topic getTopic(int id);

    void addTopic(Topic topic);

    void updateTopic(Topic topic);
}
