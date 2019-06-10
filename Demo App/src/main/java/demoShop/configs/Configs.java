package demoShop.configs;

import demoShop.api.repositories.UsersRepository;
import demoShop.api.repositories.UsersTokensRepository;
import demoShop.interceptors.HomeInterceptor;
import org.springframework.jdbc.core.JdbcTemplate;
import demoShop.services.JdbcRepositoryUsersService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class Configs implements WebMvcConfigurer {
    private HomeInterceptor interceptor;

    @Autowired
    public void setInterceptor(HomeInterceptor interceptor) {
        this.interceptor = interceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor).addPathPatterns("/users/**", "/topics/**").excludePathPatterns("/users/login", "/users/logout");
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/postgres");
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgre");
        Properties properties = new Properties();
        properties.setProperty("initialization-mode", "always");
        dataSource.setConnectionProperties(properties);

        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public JdbcRepositoryUsersService userService(UsersRepository userRepo, UsersTokensRepository usersTokensRepo) {
        return new JdbcRepositoryUsersService(userRepo, usersTokensRepo);
    }
}
