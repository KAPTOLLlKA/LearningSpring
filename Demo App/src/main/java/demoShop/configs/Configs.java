package demoShop.configs;

import demoShop.api.UserRepository;
import demoShop.api.UsersTokensRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import demoShop.services.JdbcRepositoryUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class Configs {
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/tireshop");
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgre");
        Properties properties = new Properties();
        properties.setProperty("initialization-mode", "always");
        dataSource.setConnectionProperties(properties);

        return dataSource;
    }

    @Bean
    @Autowired
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public JdbcRepositoryUserService userService(UserRepository userRepo, UsersTokensRepository usersTokensRepo) {
        return new JdbcRepositoryUserService(userRepo, usersTokensRepo);
    }
}
