package example.springmvc.controller;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.web.servlet.ModelAndView;

import example.springmvc.data.User;
import example.springmvc.data.UserRegistrationData;
import example.springmvc.data.impl.UserStorageDummyImpl;

public class AccountControllerTest {

	AccountController controller;

	@Before
	public void setUp() {
		controller = new AccountController();
		controller.setUserStorage(new UserStorageDummyImpl());
	}

	@After
	public void tearDown() {
	}

	@Test
	public void testGetSignup() throws Exception {
		UserRegistrationData userAccountData = new UserRegistrationData(
				"admin", "system");
		final ModelAndView mav = controller.getSignupPage(userAccountData);
		ModelAndViewAssert.assertViewName(mav, "signup");
		User user = controller.getUserStorage().byId("admin");
		assertNotNull(user);
		assertEquals("admin", user.getId());
		assertEquals("system", user.getPassword());
	}

	@Test
	public void testGetLogin() throws Exception {
		final ModelAndView mav = controller.getLoginPage();
		ModelAndViewAssert.assertViewName(mav, "login");
	}

	@Test
	public void testDoLogin() throws Exception {
		final ModelAndView mav = controller.doLogin();
		ModelAndViewAssert.assertViewName(mav, "index");
	}
}
