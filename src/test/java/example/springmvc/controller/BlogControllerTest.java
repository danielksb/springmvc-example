package example.springmvc.controller;

import java.security.Principal;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.web.servlet.ModelAndView;

import example.springmvc.model.BlogEntry;
import example.springmvc.model.BlogEntryFormData;
import example.springmvc.model.User;
import example.springmvc.model.impl.BlogEntryStorageDummyImpl;
import example.springmvc.model.impl.UserStorageDummyImpl;

import static org.junit.Assert.*;

public class BlogControllerTest {

	private BlogController controller;
	
	@Before
	public void setUp() {
		this.controller = new BlogController();
		this.controller.setBlogStorage(new BlogEntryStorageDummyImpl());
		this.controller.setUserStorage(new UserStorageDummyImpl());
	}
	
	@Test
	public void testGetCreateBlogPage() {
		ModelAndView mav = this.controller.getCreateBlogPage();
		ModelAndViewAssert.assertViewName(mav, "create");
		ModelAndViewAssert.assertModelAttributeAvailable(mav, "blogEntryFormData");
	}
	
	@Test
	public void testCreateBlogEntry_asUser() {
		this.controller.getUserStorage().saveOrUpdate(new User("admin", "system"));
		Principal principal = this.createNewPrincipal();
		
		BlogEntryFormData formData = new BlogEntryFormData("text");
		ModelAndView mav = this.controller.createBlogEntry(formData, principal);
		List<BlogEntry> entries = this.controller.getBlogStorage().findAll();
		
		// check blog storage
		assertEquals(1, entries.size());
		assertEquals(true, entries.get(0).getId().length() > 0);
		assertEquals(formData.getText(), entries.get(0).getText());
		assertEquals("admin", entries.get(0).getAuthorId());
		assertEquals(formData.getTags(), entries.get(0).getTags());
		
		ModelAndViewAssert.assertViewName(mav, "redirect:/");
	}
	
	@Test
	public void testCreateBlogEntry_notLoggedIn() {
		this.controller.getUserStorage().saveOrUpdate(new User("admin", "system"));
		Principal principal = null;
		
		BlogEntryFormData formData = new BlogEntryFormData("text");
		ModelAndView mav = this.controller.createBlogEntry(formData, principal);
		List<BlogEntry> entries = this.controller.getBlogStorage().findAll();
		
		assertEquals(0, entries.size());
		
		ModelAndViewAssert.assertViewName(mav, "redirect:/login");
	}
	
	private Principal createNewPrincipal() {
		return new Principal() {
			
			@Override
			public String getName() {
				return "admin";
			}
		};
	}
}
