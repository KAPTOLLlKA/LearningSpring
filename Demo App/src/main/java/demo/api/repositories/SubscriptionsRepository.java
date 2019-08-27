package demo.api.repositories;

import java.util.Collection;

public interface SubscriptionsRepository {
    void subscribeUserToUser(int userId, int subscribeToId);

    void unsubscribeUserFromUser(int userId, int unsubscribeFromId);

    Collection<Integer> getUserSubscriptions(int userId, int offset, int size);
}
