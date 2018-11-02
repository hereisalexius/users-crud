package com.hereisalexius.userscrud.dao;

import java.util.List;

import com.hereisalexius.userscrud.model.User;

public interface UserDAO {
	
	public int create(User user);
	public int update(User user);
	public int delete(User user);
	public List<User> list();
	public User findById(Long id);
	public List<User> findAllByFullname(String fullname);

}
