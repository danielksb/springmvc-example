package example.springmvc.controller;

import static org.junit.Assert.*;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.web.servlet.ModelAndView;

import example.springmvc.data.RegistrationError;
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
	public void testDoSignupSuccessful() throws Exception {
		UserRegistrationData userAccountData = new UserRegistrationData(
				"admin", "system", "system");
		final ModelAndView mav = this.controller.doSignup(userAccountData);
		
		User user = this.controller.getUserStorage().byId("admin");
		
		ModelAndViewAssert.assertViewName(mav, "redirect:/");
		assertNotNull(user);
		assertEquals("admin", user.getId());
		assertEquals("system", user.getPassword());
	}
	
	@Test
	public void testDoSignup_userAlreadyExists() throws Exception {
		this.createStandardTestUser();
		UserRegistrationData userRegistrationData = new UserRegistrationData("admin", "system", "system");
		final ModelAndView mav = controller.doSignup(userRegistrationData);
		
		ModelAndViewAssert.assertViewName(mav, "signup");
		ModelAndViewAssert.assertModelAttributeValue(mav, "userId_error", "admin");
		ModelAndViewAssert.assertModelAttributeValue(mav, "userRegistrationData", userRegistrationData);
	}
	
	@Test
	public void testDoSignup_passwordsNotEqual() throws Exception {
		UserRegistrationData userRegistrationData = new UserRegistrationData("admin", "system", "metsys");
		final ModelAndView mav = controller.doSignup(userRegistrationData);
		
		ModelAndViewAssert.assertViewName(mav, "signup");
		ModelAndViewAssert.assertModelAttributeValue(mav, "error", RegistrationError.ErrorType.PASSWORDS_DONT_MATCH.toString());
		ModelAndViewAssert.assertModelAttributeValue(mav, "userRegistrationData", userRegistrationData);
	}
	
	@Test
	public void testDoSignup_invalidInput_userId() throws Exception {
		UserRegistrationData userRegistrationData = new UserRegistrationData(null, "system", "system");
		final ModelAndView mav = controller.doSignup(userRegistrationData);
		
		ModelAndViewAssert.assertViewName(mav, "signup");
		ModelAndViewAssert.assertModelAttributeValue(mav, "error", RegistrationError.ErrorType.INVALID_INPUT.toString());
		ModelAndViewAssert.assertModelAttributeValue(mav, "userRegistrationData", userRegistrationData);
	}
	
	@Test
	public void testDoSignup_invalidInput_password() throws Exception {
		UserRegistrationData userRegistrationData = new UserRegistrationData("admin", null, "system");
		final ModelAndView mav = controller.doSignup(userRegistrationData);
		
		ModelAndViewAssert.assertViewName(mav, "signup");
		ModelAndViewAssert.assertModelAttributeValue(mav, "error", RegistrationError.ErrorType.INVALID_INPUT.toString());
		ModelAndViewAssert.assertModelAttributeValue(mav, "userRegistrationData", userRegistrationData);
	}
	
	@Test
	public void testDoSignup_invalidInput_confirmedPassword() throws Exception {
		UserRegistrationData userRegistrationData = new UserRegistrationData("admin", "system", null);
		final ModelAndView mav = controller.doSignup(userRegistrationData);
		
		ModelAndViewAssert.assertViewName(mav, "signup");
		ModelAndViewAssert.assertModelAttributeValue(mav, "error", RegistrationError.ErrorType.INVALID_INPUT.toString());
		ModelAndViewAssert.assertModelAttributeValue(mav, "userRegistrationData", userRegistrationData);
	}

	@Test
	public void testGetLogin() throws Exception {
		final ModelAndView mav = controller.getLoginPage();
		ModelAndViewAssert.assertViewName(mav, "login");
	}

	private void createStandardTestUser() {
		this.controller.getUserStorage().createNewUser(new UserRegistrationData("admin", "system", "system"));
	}
}
