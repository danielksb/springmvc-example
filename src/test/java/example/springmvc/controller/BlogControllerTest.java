package example.springmvc.controller;

import java.security.Principal;
import java.util.LinkedList;
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
	public void testGetIndex() {
		User user = new User("admin", "system");
		BlogEntry entryA = new BlogEntry("This is message A.", user);
		BlogEntry entryB = new BlogEntry("This is message B.", user);
		this.controller.getUserStorage().saveOrUpdate(user);
		this.controller.getBlogStorage().saveOrUpdate(entryA);
		this.controller.getBlogStorage().saveOrUpdate(entryB);
		List<BlogEntry> expectedEntries = new LinkedList<>();
		expectedEntries.add(entryA);
		expectedEntries.add(entryB);
		
		ModelAndView mav = this.controller.getIndexPage();
		ModelAndViewAssert.assertViewName(mav, "blog/index");
		ModelAndViewAssert.assertModelAttributeAvailable(mav, "entries");
		@SuppressWarnings("unchecked")
		List<BlogEntry> entries = (List<BlogEntry>) mav.getModel().get("entries");
		assertEquals(expectedEntries.size(), entries.size());
		assertEquals(true, expectedEntries.containsAll(entries));
	}
	
	@Test
	public void testGetCreateBlogPage() {
		ModelAndView mav = this.controller.getCreatePage();
		ModelAndViewAssert.assertViewName(mav, "blog/create");
		ModelAndViewAssert.assertModelAttributeAvailable(mav, "blogEntryFormData");
	}
	
	@Test
	public void testCreateBlogEntry_asUser() {
		this.controller.getUserStorage().saveOrUpdate(new User("admin", "system"));
		Principal principal = this.createNewPrincipal();
		
		BlogEntryFormData formData = new BlogEntryFormData("text");
		formData.setTags("a b");
		ModelAndView mav = this.controller.createBlogEntry(formData, principal);
		List<BlogEntry> entries = this.controller.getBlogStorage().findAll();
		
		// check blog storage
		List<String> expectedTags = new LinkedList<>();
		expectedTags.add("a");
		expectedTags.add("b");
		assertEquals(1, entries.size());
		assertEquals(true, entries.get(0).getId().length() > 0);
		assertEquals(formData.getText(), entries.get(0).getText());
		assertEquals("admin", entries.get(0).getAuthorId());
		assertEquals(expectedTags, entries.get(0).getTags());
		
		ModelAndViewAssert.assertViewName(mav, "redirect:/blog");
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
	
	@Test
	public void testGetBlogEntryPage() {
		User user = new User("admin", "system");
		BlogEntry entryA = new BlogEntry("This is message A.", user);
		BlogEntry entryB = new BlogEntry("This is message B.", user);
		this.controller.getUserStorage().saveOrUpdate(user);
		this.controller.getBlogStorage().saveOrUpdate(entryA);
		this.controller.getBlogStorage().saveOrUpdate(entryB);
		
		ModelAndView mav = this.controller.getBlogEntry(entryA.getId());
		
		ModelAndViewAssert.assertModelAttributeAvailable(mav, "entry");
		ModelAndViewAssert.assertViewName(mav, "blog/update");
		
		BlogEntry entry = (BlogEntry) mav.getModelMap().get("entry");
		
		assertEquals(entryA.getId(), entry.getId());
		assertEquals(entryA.getAuthorId(), entry.getAuthorId());
		assertEquals(entryA.getTags(), entry.getTags());
		assertEquals(entryA.getText(), entry.getText());
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



