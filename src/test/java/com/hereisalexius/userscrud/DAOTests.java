package com.hereisalexius.userscrud;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.hereisalexius.userscrud.dao.UserDAO;
import com.hereisalexius.userscrud.model.User;


@RunWith(SpringRunner.class)
@SpringBootTest
public class DAOTests {

	@Autowired
	private UserDAO userDAO;
	
	private User user1, user2, user3;
	
	@Before
	public void init() {
		user1 = new User("username","password","Tommy Wiseau");
		user2 = new User("username2","password","John Cena");
		user3 = new User("username3","password","John Smith");
	}
	
	@After
	public void clear() {
		userDAO.delete(user1);
		userDAO.delete(user2);
		userDAO.delete(user3);
	}
	
	@Test
	public void whenCreateNewUserAndFindByUsername() {
		userDAO.delete(user1);
		assertEquals(true,userDAO.create(user1));
		User user = userDAO.findByUsername(user1.getUsername());
		assertEquals(user1.getUsername(), user.getUsername());
		userDAO.delete(user1);
	}
	
	@Test
	public void whenCreateUserWithUsedUsername() {
		userDAO.create(user1);
		assertEquals(false,userDAO.create(user1));
		userDAO.delete(user1);
	}
	
	@Test
	public void whenUpdateUserInfo() {
		userDAO.create(user1);
		String newPassword = "123";
		user1.setPassword(newPassword);
		userDAO.update(user1);
		assertEquals(newPassword,userDAO.findByUsername(user1.getUsername()).getPassword());
		userDAO.delete(user1);
	}
	
	@Test
	public void whenDeleteUser() {
		userDAO.create(user1);
		assertNotEquals(-1L, user1.getId().longValue());
		userDAO.delete(user1);
		assertEquals(-1L, user1.getId().longValue());
		assertEquals(null, userDAO.findByUsername(user1.getUsername()));
	}
	
	@Test
	public void whenReadUsersList() {
		userDAO.create(user1);
		userDAO.create(user2);
		userDAO.create(user3);
		List<User> users = userDAO.list();
		assertEquals(3, users.size());
		assertEquals(user2.getUsername(), users.get(1).getUsername());
	}
	
	@Test
	public void whenYouLookingForTommy() {
		userDAO.create(user1);
		userDAO.create(user2);
		userDAO.create(user3);
		List<User> users = userDAO.findAllInFullname("tommy");
		assertEquals(user1.getUsername(), users.get(0).getUsername());
		//Oh hi mark
	}
	
	
}
