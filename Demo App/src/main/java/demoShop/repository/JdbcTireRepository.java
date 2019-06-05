package demoShop.repository;

import demoShop.parts.Tire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class JdbcTireRepository implements TireRepository {
    private JdbcTemplate jdbc;

    @Autowired
    public JdbcTireRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Iterable<Tire> findAll() {
        return jdbc.query("select id, manufacturer, type from Tires where id=?", this::mapRowToTire);
    }

    @Override
    public void deleteTire(Long id) {
        jdbc.update("delete from Tires where id=?", id);
    }

    @Override
    public Tire findTire(Long id) {
        return jdbc.queryForObject("select id, manufacturer, type from Tires where id=?", this::mapRowToTire, id);
    }

    @Override
    public Tire addTire(Tire tire) {
        jdbc.update("insert into Tires (id, manufacturer, type) values (?, ?, ?)",
                tire.getId(),
                tire.getManufacturer(),
                tire.getType());
        
        return tire;
    }

    private Tire mapRowToTire(ResultSet rs, int rowNum)
            throws SQLException {
        return new Tire(
                rs.getLong("id"),
                rs.getString("manufacturer"),
                Tire.Type.valueOf(rs.getString("type")
                ));
    }
}
