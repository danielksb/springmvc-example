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
	public void testGetSignupSuccessful() throws Exception {
		UserRegistrationData userAccountData = new UserRegistrationData(
				"admin", "system");
		final ModelAndView mav = this.controller.getSignupPage(userAccountData);
		User user = this.controller.getUserStorage().byId("admin");
		
		ModelAndViewAssert.assertViewName(mav, "signup");
		ModelAndViewAssert.assertModelAttributeValue(mav, "success", true);
		assertNotNull(user);
		assertEquals("admin", user.getId());
		assertEquals("system", user.getPassword());
	}
	
	@Test
	public void testGetSignup_userAlreadyExists() throws Exception {
		this.controller.getUserStorage().create(new UserRegistrationData("admin", "system"));
		
		final ModelAndView mav = controller.getSignupPage(new UserRegistrationData("admin", "system"));
		
		ModelAndViewAssert.assertViewName(mav, "signup");
		ModelAndViewAssert.assertModelAttributeValue(mav, "success", false);
		ModelAndViewAssert.assertModelAttributeValue(mav, "errorMsg", "USER_EXISTS_ALREADY");
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
