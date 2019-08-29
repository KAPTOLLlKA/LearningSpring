package demo.repositories;

import demo.data.topic.Topic;
import org.springframework.stereotype.Component;
import org.springframework.jdbc.core.JdbcTemplate;
import demo.api.repositories.TopicsRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

@Component
public class JdbcTopicsRepository implements TopicsRepository {
    private JdbcTemplate jdbc;

    @Autowired
    public JdbcTopicsRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Collection<Topic> getAllTopics() {
        return jdbc.query("SELECT * FROM Topics ORDER BY posted_at DESC", this::mapRowToTopic);
    }

    @Override
    public Collection<Topic> getFromWithOffset(int offset, int size) {
        return jdbc.query("SELECT * FROM Topics ORDER BY posted_at DESC LIMIT ? OFFSET ?",
                this::mapRowToTopic, size, offset);
    }

    @Override
    public Topic getTopic(int id) {
        return jdbc.queryForObject("SELECT * FROM Topics WHERE id=?", this::mapRowToTopic, id);
    }

    @Override
    public Collection<Topic> searchTopics(String searchFor, int size) {
        return jdbc.query("SELECT * FROM Topics WHERE ((title ILIKE ?) OR (content ILIKE ?)) ORDER BY posted_at DESC LIMIT ?", this::mapRowToTopic,
                searchFor + "%", searchFor + "%", size);
    }

    @Override
    public Collection<Topic> searchTopicsByTitle(String searchFor, int size) {
        return jdbc.query("SELECT * FROM Topics WHERE title ILIKE ? ORDER BY posted_at DESC LIMIT ?", this::mapRowToTopic,
                searchFor + "%", size);
    }

    @Override
    public Collection<Topic> searchTopicsByContent(String searchFor, int size) {
        return jdbc.query("SELECT * FROM Topics WHERE content ILIKE ? ORDER BY posted_at DESC LIMIT ?", this::mapRowToTopic,
                searchFor + "%", size);
    }

    @Override
    public int getTopicIdForUsername(String username) {
        List<Topic> topics = jdbc.query("SELECT * FROM Topics WHERE posted_by=?", this::mapRowToTopic, username);
        return topics.get(topics.size() - 1).getId();
    }

    @Override
    public void updateTopic(Topic topic) {
        jdbc.update("UPDATE Topics SET title=?, content=?, posted_by=?, posted_at=? WHERE id=?",
                topic.getTitle(),
                topic.getContent(),
                topic.getPostedBy(),
                topic.getPostedAt(),
                topic.getId()
        );
    }

    @Override
    public void addTopic(Topic topic) {
        jdbc.update("insert into Topics (title, content, posted_by, posted_at) values (?, ?, ?, ?)",
                topic.getTitle(),
                topic.getContent(),
                topic.getPostedBy(),
                topic.getPostedAt()
        );
    }

    @Override
    public void deleteTopic(int id) {
        jdbc.update("delete from Topics where id=?", id);
    }

    private Topic mapRowToTopic(ResultSet rs, int rowNum) throws SQLException {
        return new Topic(
                rs.getInt("id"),
                rs.getString("title"),
                rs.getString("content"),
                rs.getString("posted_by"),
                rs.getTimestamp("posted_at")
        );
    }
}
