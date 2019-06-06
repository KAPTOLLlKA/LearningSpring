package demoShop.repository;

import java.sql.ResultSet;

import demoShop.parts.User;

import java.sql.SQLException;
import java.util.Collection;


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
    public Collection<User> findAll() {
        return jdbc.query("select * from Users", this::mapRowToUser);
    }

    @Override
    public void deleteUser(int id) {
        jdbc.update("delete from Users where id=?", id);
    }

    @Override
    public User findUser(int id) {
        return jdbc.queryForObject("select * from Users where id=?", this::mapRowToUser, id);
    }

    @Override
    public User addUser(User user) {
        jdbc.update("insert into Users (id, password, username, mail, about) values (?, ?, ?, ?, ?)",
                user.getId(),
                user.getPassword(),
                user.getUsername(),
                user.getMail(),
                user.getAbout());
        return user;
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
