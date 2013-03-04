package example.springmvc.controller;

import java.security.Principal;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.web.servlet.ModelAndView;

import example.springmvc.model.users.UserRegistrationData;
import example.springmvc.model.users.UserService;
import example.springmvc.model.users.impl.UserStorageDummyImpl;

public class MainControllerTest {

	private MainController controller;
	private UserService userService;
	
	@Before
	public void setUp() {
		this.controller = new MainController();
		this.userService = new UserService();
		userService.setUserStorage(new UserStorageDummyImpl());
	}
	
	@Test
	public void testGetIndexPage_userLoggedIn() throws Exception {
		this.userService.createNewUser(new UserRegistrationData("admin", "system", "system"));
		final ModelAndView mav = controller.getIndexPage(new Principal() {
			
			@Override
			public String getName() {
				return "admin";
			}
		});
		ModelAndViewAssert.assertViewName(mav, "index");
		ModelAndViewAssert.assertModelAttributeValue(mav, "name", "admin");
		ModelAndViewAssert.assertModelAttributeValue(mav, "isUserLoggedIn", true);
	}
	
	@Test
	public void testGetIndexPage_userNotLoggedIn() throws Exception {
		final ModelAndView mav = controller.getIndexPage(null);
		ModelAndViewAssert.assertViewName(mav, "index");
		ModelAndViewAssert.assertModelAttributeValue(mav, "name", "World");
		ModelAndViewAssert.assertModelAttributeValue(mav, "isUserLoggedIn", false);
	}
}
