package example.springmvc.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import example.springmvc.model.users.User;
import example.springmvc.model.users.UserRegistrationData;
import example.springmvc.model.users.UserService;
import example.springmvc.model.users.impl.UserStorageDummyImpl;

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
	
	@Test
	public void testVerifyLogin_success() {
		userService.getUserStorage().saveOrUpdate(new User("admin", "system"));
		assertEquals(true, userService.verifyLogin("admin", "system"));
	}
	
	@Test
	public void testVerifyLogin_wrongPassword() {
		userService.getUserStorage().saveOrUpdate(new User("admin", "system"));
		assertEquals(false, userService.verifyLogin("admin", "123"));
	}
	
	@Test
	public void testVerifyLogin_userDoesNotExist() {
		assertEquals(false, userService.verifyLogin("admin", "system"));
	}

}
