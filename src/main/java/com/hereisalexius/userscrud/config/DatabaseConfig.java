package com.hereisalexius.userscrud.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.hereisalexius.userscrud.dao.UserDAO;

@Configuration
public class DatabaseConfig {

	@Value("${db.url}")
	private String url;
	@Value("${db.username}")
	private String username;
	@Value("${db.password}")
	private String password;
	
	@Bean
	public DataSource dataSource() {
		System.out.println(url);
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.postgresql.Driver");
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);

		/*dataSource.setUrl("jdbc:postgresql://localhost:5432/users_db");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres");*/
		
		return dataSource;
	}

	@Bean
	public UserDAO userDao() {
		return new UserDAO(dataSource());
	}
	

}
