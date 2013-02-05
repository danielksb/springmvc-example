package example.springmvc.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.web.servlet.ModelAndView;

import example.springmvc.data.Session;
import example.springmvc.data.impl.SessionStorageDummyImpl;

public class MainControllerTest {

	private MainController controller;
	
	@Before
	public void setUp() {
		controller = new MainController();
		controller.setSessionStorage(new SessionStorageDummyImpl());
	}

	@After
	public void tearDown() {
	}

	@Test
	public void testGetIndex_notLoggedIn() throws Exception {
		final ModelAndView mav = this.controller.getIndexPage(null);
		
		ModelAndViewAssert.assertViewName(mav, "index");
		ModelAndViewAssert.assertModelAttributeValue(mav, "name", "World");
		ModelAndViewAssert.assertModelAttributeValue(mav, "isUserLoggedIn", false);
	}
	
	@Test
	public void testGetIndex_loggedIn() throws Exception {
		Session session = this.controller.getSessionStorage().create("admin");
		
		final ModelAndView mav = this.controller.getIndexPage(session.getId());
		
		ModelAndViewAssert.assertViewName(mav, "index");
		ModelAndViewAssert.assertModelAttributeValue(mav, "name", "admin");
		ModelAndViewAssert.assertModelAttributeValue(mav, "isUserLoggedIn", true);
	}
}
