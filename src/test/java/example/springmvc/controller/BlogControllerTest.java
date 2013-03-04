package example.springmvc.controller;

import java.security.Principal;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DirectFieldBindingResult;
import org.springframework.web.servlet.ModelAndView;

import example.springmvc.model.blog.BlogEntry;
import example.springmvc.model.blog.BlogEntryFormData;
import example.springmvc.model.blog.impl.BlogEntryStorageDummyImpl;
import example.springmvc.model.users.User;
import example.springmvc.model.users.impl.UserStorageDummyImpl;

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
		BlogEntry entryA = new BlogEntry("", "This is message A.", user);
		BlogEntry entryB = new BlogEntry("", "This is message B.", user);
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
		
		BindingResult result = new DirectFieldBindingResult(formData, "formData");
		ModelAndView mav = this.controller.createBlogEntry(formData, principal, result);
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
		assertNotEquals(null, entries.get(0).getCreationDate());
		
		ModelAndViewAssert.assertViewName(mav, "redirect:/blog");
	}
	
	@Test
	public void testCreateBlogEntry_emptyTextField() {
		this.controller.getUserStorage().saveOrUpdate(new User("admin", "system"));
		Principal principal = this.createNewPrincipal();
		
		BlogEntryFormData formData = new BlogEntryFormData("");
		
		BindingResult result = new DirectFieldBindingResult(formData, "formData");
		ModelAndView mav = this.controller.createBlogEntry(formData, principal, result);
		List<BlogEntry> entries = this.controller.getBlogStorage().findAll();
		
		// check blog storage
		assertEquals(0, entries.size());
		
		ModelAndViewAssert.assertViewName(mav, "blog/create");
	}
	
	@Test
	public void testCreateBlogEntry_notLoggedIn() {
		this.controller.getUserStorage().saveOrUpdate(new User("admin", "system"));
		Principal principal = null;
		
		BlogEntryFormData formData = new BlogEntryFormData("text");
		formData.setTags("a b");
		
		BindingResult result = new DirectFieldBindingResult(formData, "formData");
		ModelAndView mav = this.controller.createBlogEntry(formData, principal, result);
		List<BlogEntry> entries = this.controller.getBlogStorage().findAll();
		
		// check blog storage
		List<String> expectedTags = new LinkedList<>();
		expectedTags.add("a");
		expectedTags.add("b");
		assertEquals(1, entries.size());
		assertEquals(true, entries.get(0).getId().length() > 0);
		assertEquals(formData.getText(), entries.get(0).getText());
		assertEquals("", entries.get(0).getAuthorId());
		assertEquals(expectedTags, entries.get(0).getTags());
		assertNotEquals(null, entries.get(0).getCreationDate());
		
		ModelAndViewAssert.assertViewName(mav, "redirect:/blog");
	}
	
	@Test
	public void testGetBlogEntryPage() {
		User user = new User("admin", "system");
		BlogEntry entryA = new BlogEntry("", "This is message A.", user);
		BlogEntry entryB = new BlogEntry("", "This is message B.", user);
		this.controller.getUserStorage().saveOrUpdate(user);
		this.controller.getBlogStorage().saveOrUpdate(entryA);
		this.controller.getBlogStorage().saveOrUpdate(entryB);
		
		BlogEntryFormData expectedFormData = new BlogEntryFormData(entryA);
		
		ModelAndView mav = this.controller.getBlogEntry(entryA.getId());
		
		ModelAndViewAssert.assertModelAttributeAvailable(mav, "entry");
		ModelAndViewAssert.assertViewName(mav, "blog/update");
		
		BlogEntryFormData entry = (BlogEntryFormData) mav.getModelMap().get("entry");
		
		assertEquals(expectedFormData.getAuthorId(), entry.getAuthorId());
		assertEquals(expectedFormData.getTags(), entry.getTags());
		assertEquals(expectedFormData.getText(), entry.getText());
		assertEquals(expectedFormData.getCreationDate(), entry.getCreationDate().toString());
	}
	
	@Test
	public void testUpdateBlogEntry() {
		User user = new User("admin", "system");
		BlogEntry entryA = new BlogEntry("", "This is message A.", user);
		BlogEntry entryB = new BlogEntry("", "This is message B.", user);
		this.controller.getUserStorage().saveOrUpdate(user);
		this.controller.getBlogStorage().saveOrUpdate(entryA);
		this.controller.getBlogStorage().saveOrUpdate(entryB);
		
		BlogEntryFormData expectedFormData = new BlogEntryFormData(entryA);
		expectedFormData.setTags("a b");
		List<String> expectedTags = new LinkedList<String>();
		expectedTags.add("a");
		expectedTags.add("b");
		
		ModelAndView mav = this.controller.changeBlogEntry(entryA.getId(), expectedFormData);
		
		ModelAndViewAssert.assertModelAttributeAvailable(mav, "entry");
		ModelAndViewAssert.assertViewName(mav, "blog/update");
		
		BlogEntryFormData entry = (BlogEntryFormData) mav.getModelMap().get("entry");
		
		List<String> updatedTags = this.controller.getBlogStorage().byId(entryA.getId()).getTags();
		
		assertEquals(expectedTags, updatedTags);
		assertEquals(expectedFormData.getAuthorId(), entry.getAuthorId());
		assertEquals(expectedFormData.getTags(), entry.getTags());
		assertEquals(expectedFormData.getText(), entry.getText());
		assertEquals(expectedFormData.getCreationDate(), entry.getCreationDate().toString());
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



