package com.hereisalexius.userscrud.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hereisalexius.userscrud.dao.UserDAO;
import com.hereisalexius.userscrud.model.User;

@Service
public class LoginService {

	@Autowired
	private UserDAO userDao;
	private User user;

	public boolean login(String username, String password) {
		user = userDao.findByUsername(username);
		return user != null && user.getPassword().contentEquals(password);
	}
	
	public void logout() {
		user = null;
	}
	
	public boolean isLoggedIn() {
		return user != null;
	}

}
