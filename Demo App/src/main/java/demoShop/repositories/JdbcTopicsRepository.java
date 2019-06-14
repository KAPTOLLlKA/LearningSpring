package demoShop.repositories;

import demoShop.data.topic.Topic;
import org.springframework.stereotype.Component;
import org.springframework.jdbc.core.JdbcTemplate;
import demoShop.api.repositories.TopicsRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

@Component
public class JdbcTopicsRepository implements TopicsRepository {
    private JdbcTemplate jdbc;

    @Autowired
    public JdbcTopicsRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Collection<Topic> getAllTopics() {
        return jdbc.query("select * from Topics", this::mapRowToTopic);
    }

    @Override
    public Topic getTopic(int id) {
        return jdbc.queryForObject("select * from Topics where id=?", this::mapRowToTopic, id);
    }

    @Override
    public Collection<Topic> searchTopicByTitle(String title) {
        return jdbc.query("select * from Topics where title ilike ?", this::mapRowToTopic,"%" +  title + "%");
    }

    @Override
    public void updateTopic(Topic topic) {
        jdbc.update("update Topics set title=?, content=?, posted_by=?, posted_at=? where id=?",
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
                rs.getDate("posted_at")
        );
    }
}
