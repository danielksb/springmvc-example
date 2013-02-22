package example.springmvc.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import example.springmvc.model.User;
import example.springmvc.model.UserRegistrationData;
import example.springmvc.model.UserService;
import example.springmvc.model.impl.UserStorageDummyImpl;

public class UserServiceTest {
	
	UserService userService;
	
	@Before
	public void setUp() {
		userService = new UserService();
		userService.setUserStorage(new UserStorageDummyImpl());
	}
	
	@Test
	public void testCreateNewUser_success() {
		UserRegistrationData data = new UserRegistrationData("admin", "system", "system");
		User user = userService.createNewUser(data);
		assertEquals("admin", user.getId());
		assertEquals("system", user.getPassword());
	}
	
	@Test
	public void testCreateNewUser_userAlreadyExists() {
		userService.getUserStorage().saveOrUpdate(new User("admin", "123"));
		UserRegistrationData data = new UserRegistrationData("admin", "system", "system");
		User user = userService.createNewUser(data);
		assertNull(user);
	}

}
