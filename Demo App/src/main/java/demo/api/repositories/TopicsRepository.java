package demo.api.repositories;

import demo.data.topic.Topic;

import java.util.Collection;

public interface TopicsRepository {
    Collection<Topic> getAllTopics();

    Collection<Topic> getFromWithOffset(int offset, int size);

    Topic getTopic(int id);

    Collection<Topic> searchTopics(String searchFor, int size);

    Collection<Topic> searchTopicsByTitle(String searchFor, int size);

    Collection<Topic> searchTopicsByContent(String searchFor, int size);

    int getTopicIdForUsername(String title);

    void updateTopic(Topic topic);

    void addTopic(Topic topic);

    void deleteTopic(int id);
}
