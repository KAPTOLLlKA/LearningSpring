package demoShop.repository;

import java.sql.ResultSet;

import demoShop.api.UserRepository;
import demoShop.parts.User;

import java.sql.SQLException;
import java.util.Collection;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class JdbcUserRepository implements UserRepository {
    private JdbcTemplate jdbc;

    @Autowired
    public JdbcUserRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Collection<User> getAllUsers() {
        return jdbc.query("select * from Users", this::mapRowToUser);
    }

    @Override
    public User createTokenFor(User user) {
        jdbc.update("insert into UsersTokens (user_id, user_token) values (?, ?)",
                user.getId(),
                UUID.randomUUID().toString()
        );
        return user;
    }

    @Override
    public User getUser(int id) {
        return jdbc.queryForObject("select * from Users where id=?", this::mapRowToUser, id);
    }

    @Override
    public User getUser(String username, String password) {
        return jdbc.queryForObject("select * from Users where password=? and username=?", this::mapRowToUser, password, username);
    }

    @Override
    public User updateUser(User user) {
        jdbc.update("update Users set password=?, username=?, mail=?, about=? where id=?",
                user.getPassword(),
                user.getUsername(),
                user.getMail(),
                user.getAbout(),
                user.getId());
        return user;
    }

    @Override
    public User addUser(User user) {
        jdbc.update("insert into Users (password, username, mail, about) values (?, ?, ?, ?)",
                user.getPassword(),
                user.getUsername(),
                user.getMail(),
                user.getAbout()
        );
        return user;
    }

    @Override
    public void deleteUser(int id) {
        jdbc.update("delete from Users where id=?", id);
    }

    private User mapRowToUser(ResultSet rs, int rowNum) throws SQLException {
        return new User(
                rs.getInt("id"),
                rs.getString("password"),
                rs.getString("username"),
                rs.getString("mail"),
                rs.getString("about")
        );
    }
}
