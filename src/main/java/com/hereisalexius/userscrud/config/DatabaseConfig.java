package com.hereisalexius.userscrud.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.hereisalexius.userscrud.dao.UserDAO;

@Configuration
public class DatabaseConfig {
	
	
	@Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/users_db");
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres");
 
        return dataSource;
    }
	
	@Bean
	public UserDAO userDao() {
		return new UserDAO(dataSource());
	}

}
