package example.springmvc.controller;

import static org.junit.Assert.*;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DirectFieldBindingResult;
import org.springframework.web.servlet.ModelAndView;

import example.springmvc.data.User;
import example.springmvc.data.UserRegistrationData;
import example.springmvc.data.UserService;
import example.springmvc.data.impl.UserStorageDummyImpl;

public class AccountControllerTest {

	AccountController controller;
	UserService userService;

	@Before
	public void setUp() {
		controller = new AccountController();
		userService = new UserService();
		controller.setUserService(userService);
		userService.setUserStorage(new UserStorageDummyImpl());
	}

	@After
	public void tearDown() {
	}

	@Test
	public void testDoSignupSuccessful() throws Exception {
		UserRegistrationData userAccountData = new UserRegistrationData(
				"admin", "system", "system");
		BindingResult result = new DirectFieldBindingResult(userAccountData, "userAccountData");
		final ModelAndView mav = this.controller.doSignup(userAccountData, result);
		
		User user = this.controller.getUserService().getUserStorage().byId("admin");
		
		ModelAndViewAssert.assertViewName(mav, "redirect:/");
		assertNotNull(user);
		assertEquals("admin", user.getId());
		assertEquals("system", user.getPassword());
	}
	
	@Test
	public void testDoSignup_userAlreadyExists() throws Exception {
		this.createStandardTestUser();
		UserRegistrationData userRegistrationData = new UserRegistrationData("admin", "system", "system");
		BindingResult result = new DirectFieldBindingResult(userRegistrationData, "userAccountData");
		final ModelAndView mav = controller.doSignup(userRegistrationData, result);
		
		ModelAndViewAssert.assertViewName(mav, "signup");
		assertEquals(1, result.getAllErrors().size());
		assertEquals("error.user_already_exists", result.getAllErrors().get(0).getDefaultMessage());
	}
	
	@Test
	public void testDoSignup_passwordsNotEqual() throws Exception {
		UserRegistrationData userRegistrationData = new UserRegistrationData("admin", "system", "metsys");
		BindingResult result = new DirectFieldBindingResult(userRegistrationData, "userAccountData");
		final ModelAndView mav = controller.doSignup(userRegistrationData, result);
		
		ModelAndViewAssert.assertViewName(mav, "signup");
		assertEquals(1, result.getAllErrors().size());
		assertEquals("error.passwords_dont_match", result.getAllErrors().get(0).getCode());
	}
	
	@Test
	public void testDoSignup_invalidInput_userId() throws Exception {
		UserRegistrationData userRegistrationData = new UserRegistrationData("", "system", "system");
		BindingResult result = new DirectFieldBindingResult(userRegistrationData, "userAccountData");
		final ModelAndView mav = controller.doSignup(userRegistrationData, result);
		
		ModelAndViewAssert.assertViewName(mav, "signup");
		assertEquals(1, result.getAllErrors().size());
		assertEquals("error.fieldRequired", result.getAllErrors().get(0).getCode());
	}
	
	@Test
	public void testDoSignup_invalidInput_password() throws Exception {
		UserRegistrationData userRegistrationData = new UserRegistrationData("admin", "", "system");
		BindingResult result = new DirectFieldBindingResult(userRegistrationData, "userAccountData");
		final ModelAndView mav = controller.doSignup(userRegistrationData, result);
		
		ModelAndViewAssert.assertViewName(mav, "signup");
		assertEquals(2, result.getAllErrors().size());
		assertEquals("error.fieldRequired", result.getAllErrors().get(0).getCode());
		assertEquals("error.passwords_dont_match", result.getAllErrors().get(1).getCode());
	}
	
	@Test
	public void testDoSignup_invalidInput_confirmedPassword() throws Exception {
		UserRegistrationData userRegistrationData = new UserRegistrationData("admin", "system", "");
		BindingResult result = new DirectFieldBindingResult(userRegistrationData, "userAccountData");
		final ModelAndView mav = controller.doSignup(userRegistrationData, result);
		
		ModelAndViewAssert.assertViewName(mav, "signup");
		assertEquals(1, result.getAllErrors().size());
		assertEquals("error.passwords_dont_match", result.getAllErrors().get(0).getCode());
	}

	@Test
	public void testGetLogin() throws Exception {
		final ModelAndView mav = controller.getLoginPage();
		ModelAndViewAssert.assertViewName(mav, "login");
	}

	private void createStandardTestUser() {
		this.controller.getUserService().createNewUser(new UserRegistrationData("admin", "system", "system"));
	}
}
