package demoShop.repository;

import java.sql.ResultSet;
import demoShop.parts.Tire;
import java.sql.SQLException;
import java.util.Collection;


import org.springframework.stereotype.Component;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class JdbcTireRepository implements TireRepository {
    private JdbcTemplate jdbc;

    @Autowired
    public JdbcTireRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Collection<Tire> findAll() {
        return jdbc.query("select * from Tires", this::mapRowToTire);
    }

    @Override
    public void deleteTire(Long id) {
        jdbc.update("delete from Tires where id=?", id);
    }

    @Override
    public Tire findTire(Long id) {
        return jdbc.queryForObject("select * from Tires where id=?", this::mapRowToTire, id);
    }

    @Override
    public int getTireCount(Long id) {
        Integer count = jdbc.queryForObject("select count from TiresCount where id=?", this::mapRowToTireCount, id);
        return count == null ? 0 : count;
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

    private int mapRowToTireCount(ResultSet rs, int rowNum)
            throws SQLException {
        return rs.getInt("count");
    }
}
