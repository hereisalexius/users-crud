package com.hereisalexius.userscrud.dao;

import java.util.List;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.hereisalexius.userscrud.dto.UserRowMapper;
import com.hereisalexius.userscrud.model.User;

public class UserDAO{

    @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
	
    public UserDAO() {
    	jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    public UserDAO(DataSource dataSource) {
    	this.dataSource = dataSource;
    	jdbcTemplate = new JdbcTemplate(dataSource);
    }

	public boolean create(User user) {
	    String query="INSERT INTO USERS "
	    		+ "(USERNAME,PASSWORD,FULLNAME)"
	    		+ " VALUES('"
	    		//+user.getId()+"','"
	    		+user.getUsername()+"','"
	    		+user.getPassword()+"','"
	    		+user.getFullName()+"');"; 
	    try {
	    jdbcTemplate.update(query);
	    }catch(Exception ex) {
	    	ex.printStackTrace();
	    	return false;
	    }
	    
	    //rewrite user id
	    user.setId(findByUsername(user.getUsername()).getId());
	    
	    return true; 
	}

	public boolean update(User user) {
		String query="UPDATE USERS SET "
				+ "USERNAME='" + user.getUsername()+"',"
				+ "PASSWORD='" + user.getPassword()+"',"
				+ "FULLNAME='" + user.getFullName()+"' "
				+ "WHERE ID='" + user.getId()+"' ";  
		
		 try {
			    jdbcTemplate.update(query);
			    }catch(Exception ex) {
			    	ex.printStackTrace();
			    	return false;
			    }
			    
			    return true; 
	}

	public void delete(User user) {
		String query= "DELETE FROM USERS WHERE ID ='" + user.getId() + "' ";  
	    jdbcTemplate.update(query);  
	    user.setId(-1L);
	}

	public List<User> list() {
		String query = "SELECT * FROM USERS";
		return (List<User>) jdbcTemplate.query(
		    query, new UserRowMapper());
	}

	@SuppressWarnings("finally")
	public User findById(Long id) {
		String query = "SELECT * FROM USERS WHERE ID = ?";
		User user = null;
		try {
		user = (User) jdbcTemplate.queryForObject(
		    query, new Object[] { id }, new UserRowMapper());
		}catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			return user;
		}
	}
	
	@SuppressWarnings("finally")
	public User findByUsername(String username) {
		String query = "SELECT * FROM USERS WHERE USERNAME = ?";
		User user = null;
		try {
		user = (User) jdbcTemplate.queryForObject(
		    query, new Object[] { username }, new UserRowMapper());
		}catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			return user;
		}
	}

	public List<User> findAllInFullname(String data) {
		
		String query = "SELECT * FROM USERS "
				+ "WHERE LOWER(FULLNAME) "
				+ "LIKE LOWER('%?%')";
			
		return (List<User>) jdbcTemplate.query(
			    query,new Object[] {data}, new UserRowMapper());
	}
	
	@Deprecated
	public List<User> streamFindAllInFullname(String data) {
		return list().stream()
				.filter(user -> 
					user.getFullName().toLowerCase()
					.contains(data.toLowerCase()))
				.collect(Collectors.toList());
	}

}
