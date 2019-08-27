package demo.repositories;

import demo.api.repositories.SubscriptionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

@Component
public class JdbcSubscriptionsRepository implements SubscriptionsRepository {
    private final JdbcTemplate jdbc;

    @Autowired
    public JdbcSubscriptionsRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public void subscribeUserToUser(int userId, int subscribeToId) {
        jdbc.update("INSERT INTO users_subscriptions (user_id, sub_id) VALUES (?, ?)", userId, subscribeToId);
    }

    @Override
    public void unsubscribeUserFromUser(int userId, int unsubscribeFromId) {
        jdbc.update("DELETE FROM users_subscriptions WHERE user_id=? AND sub_id=?", userId, unsubscribeFromId);
    }

    @Override
    public Collection<Integer> getUserSubscriptions(int userId, int offset, int size) {
        return jdbc.query("SELECT * FROM users_subscriptions WHERE user_id=? LIMIT ? OFFSET ?",
                this::mapRowToSubscription, userId, size, offset);
    }

    private Integer mapRowToSubscription(ResultSet rs, int rowNum) throws SQLException {
        return rs.getInt("sub_id");
    }
}