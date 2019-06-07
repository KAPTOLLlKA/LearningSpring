package demoShop.repositories;

import demoShop.api.UsersTokensRepository;
import org.springframework.stereotype.Component;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@Component
public class JdbcUsersTokensRepository implements UsersTokensRepository {
    private JdbcTemplate jdbc;

    @Autowired
    public JdbcUsersTokensRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public void createTokenFor(int id) {
        jdbc.update("insert into UsersTokens (user_id, user_token) values (?, ?)",
                id,
                UUID.randomUUID().toString()
        );
    }

    @Override
    public void deleteUserToken(int id) {
        jdbc.update("delete from UsersTokens where user_id=?", id);
    }
}
