package demoShop.repositories;

import demoShop.api.repositories.UsersTokensRepository;
import org.springframework.stereotype.Component;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Component
public class JdbcUsersTokensRepository implements UsersTokensRepository {
    private JdbcTemplate jdbc;

    @Autowired
    public JdbcUsersTokensRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public String createTokenFor(int id) {
        String token = UUID.randomUUID().toString();
        jdbc.update("insert into UsersTokens (user_id, user_token) values (?, ?)",
                id,
                token
        );
        return token;
    }

    @Override
    public void deleteUserToken(int id) {
        jdbc.update("delete from UsersTokens where user_id=?", id);
    }

    @Override
    public Integer getUserIdForToken(String token) {
        return jdbc.queryForObject("select * from UsersTokens where user_token=?", this::mapRowToUserId, token);
    }

    private int mapRowToUserId(ResultSet rs, int rowNum) throws SQLException {
        return rs.getInt("user_id");
    }
}
