package example.springmvc.test.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import static org.junit.Assert.*;

import java.security.Principal;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import example.springmvc.model.blog.BlogEntry;
import example.springmvc.model.blog.BlogEntryStorage;
import example.springmvc.model.users.User;
import example.springmvc.model.users.UserStorage;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:WEB-INF/application-context.xml",
		"classpath:WEB-INF/spring-security.xml",
		"classpath:WEB-INF/spring-web.xml" })
@WebAppConfiguration("src/main/webapp")
public class BlogIntegrationTest {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	public void testGetBlogIndexPage() throws Exception {
		this.mockMvc.perform(get("/blog"))
			.andExpect(status().isOk())
			.andExpect(view().name("blog/index"));
	}
	
	@Test
	@DirtiesContext
	public void testGetCreateEntryPage() throws Exception {
		this.mockMvc.perform(get("/blog/create"))
				.andExpect(status().isOk())
				.andExpect(view().name("blog/create"));
	}
	
	@Test
	@DirtiesContext
	public void testCreateNewBlogEntry_loggedIn() throws Exception {
		UserStorage userStorage = wac.getBean("userStorage", UserStorage.class);
		userStorage.saveOrUpdate(new User("admin", "system"));
		Principal principal = new Principal() {
			
			@Override
			public String getName() {
				return "admin";
			}
		};
		this.mockMvc.perform(post("/blog/create")
				.param("text", "this is a blog entry")
				.param("tags", "a b")
				.principal(principal))
					.andExpect(view().name("redirect:/blog"));
	}
	
	@Test
	@DirtiesContext
	public void testCreateNewBlogEntry_NotloggedIn() throws Exception {
		this.mockMvc.perform(post("/blog/create")
				.param("text", "this is a blog entry")
				.param("tags", "a b"))
					.andExpect(view().name("redirect:/blog"));
	}
	
	@Test
	@DirtiesContext
	public void testGetBlogPage() throws Exception {
		UserStorage userStorage = wac.getBean("userStorage", UserStorage.class);
		BlogEntryStorage blogStorage = wac.getBean("blogEntryStorage", BlogEntryStorage.class);
		User user = new User("admin", "system");
		userStorage.saveOrUpdate(user);
		
		BlogEntry entryA = new BlogEntry("", "This is message A.", user);
		BlogEntry entryB = new BlogEntry("", "This is message B.", user);
		blogStorage.saveOrUpdate(entryA);
		blogStorage.saveOrUpdate(entryB);
		
		this.mockMvc.perform(get("/blog/update/" + entryA.getId()))
					.andExpect(view().name("blog/update"));
	}
	
	@Test
	@DirtiesContext
	public void changeGetBlogPage() throws Exception {
		UserStorage userStorage = wac.getBean("userStorage", UserStorage.class);
		BlogEntryStorage blogStorage = wac.getBean("blogEntryStorage", BlogEntryStorage.class);
		User user = new User("admin", "system");
		userStorage.saveOrUpdate(user);
		
		BlogEntry entryA = new BlogEntry("", "This is message A.", user);
		BlogEntry entryB = new BlogEntry("", "This is message B.", user);
		blogStorage.saveOrUpdate(entryA);
		blogStorage.saveOrUpdate(entryB);
		
		// let's check if we can change the entry by forging our own post request
		this.mockMvc.perform(post("/blog/update/" + entryA.getId())
					.param("text", "this is a blog entry")
					.param("tags", "a b")
					.param("authorId", "user"))
					.andExpect(view().name("blog/update"));
		
		List<String> expectedTags = new LinkedList<String>();
		expectedTags.add("a");
		expectedTags.add("b");
		BlogEntry newEntry = blogStorage.byId(entryA.getId());
		assertEquals(entryA.getAuthorId(), newEntry.getAuthorId());
		assertEquals(expectedTags, newEntry.getTags());
		assertEquals(entryA.getText(), newEntry.getText());
	}
}
