package demo.repositories;

import java.sql.ResultSet;

import demo.data.user.User;
import demo.api.repositories.UsersRepository;

import java.util.Collection;
import java.sql.SQLException;

import org.springframework.stereotype.Component;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.dao.DataAccessException;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class JdbcUsersRepository implements UsersRepository {
    private JdbcTemplate jdbc;

    @Autowired
    public JdbcUsersRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Collection<User> getAllUsers() {
        return jdbc.query("select * from Users", this::mapRowToUser);
    }

    @Override
    public User getUser(int id) {
        return jdbc.queryForObject("select * from Users where id=?", this::mapRowToUser, id);
    }

    @Override
    public User getUser(String username, String password) {
        try {
            return jdbc.queryForObject("select * from Users where password=? and username=?", this::mapRowToUser, password, username);
        } catch (DataAccessException e) {
            return null;
        }
    }

    @Override
    public boolean updateUser(User user) {
        try {
            jdbc.update("update Users set password=?, username=?, email=?, about=? where id=?",
                    user.getPassword(),
                    user.getUsername(),
                    user.getEmail(),
                    user.getAbout(),
                    user.getId());
            return true;
        } catch (DataAccessException e) {
            return false;
        }
    }

    @Override
    public boolean registerUser(User user) {
        try {
            jdbc.update("insert into Users (password, username, email, about) values (?, ?, ?, ?)",
                    user.getPassword(),
                    user.getUsername(),
                    user.getEmail(),
                    user.getAbout()
            );
            return true;
        } catch (DataAccessException e) {
            return false;
        }
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
                rs.getString("email"),
                rs.getString("about")
        );
    }
}
