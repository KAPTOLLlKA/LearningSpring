package demo.repositories;

import org.springframework.stereotype.Component;
import org.springframework.jdbc.core.JdbcTemplate;
import demo.api.repositories.UsersTokensRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class JdbcUsersTokensRepository implements UsersTokensRepository {
    private JdbcTemplate jdbc;

    @Autowired
    public JdbcUsersTokensRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public void addTokenFor(int id, String token) {
        jdbc.update("INSERT INTO UsersTokens (user_id, user_token) VALUES (?, ?)",
                id,
                token
        );
    }

    @Override
    public void deleteUserToken(int id) {
        jdbc.update("DELETE FROM UsersTokens WHERE user_id=?", id);
    }

    @Override
    public void deleteUserToken(String token) {
        jdbc.update("DELETE FROM UsersTokens WHERE user_token=?", token);
    }

    @Override
    public Integer getUserIdForToken(String token) {
        return jdbc.queryForObject("SELECT * FROM UsersTokens WHERE user_token=?", this::mapRowToUserId, token);
    }

    private int mapRowToUserId(ResultSet rs, int rowNum) throws SQLException {
        return rs.getInt("user_id");
    }
}
