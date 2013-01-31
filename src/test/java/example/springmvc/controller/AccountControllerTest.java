package example.springmvc.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.web.servlet.ModelAndView;

public class AccountControllerTest {

	AccountController controller;

	@Before
	public void setUp() {
		controller = new AccountController();
	}

	@After
	public void tearDown() {
	}

	@Test
	public void testGetSignup() throws Exception {
		final ModelAndView mav = controller.getSignupPage();
		ModelAndViewAssert.assertViewName(mav, "signup");
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
