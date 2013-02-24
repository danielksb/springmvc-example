package example.springmvc.test.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.security.Principal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import example.springmvc.model.User;
import example.springmvc.model.UserStorage;

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
	public void testGetCreateEntryPage() throws Exception {
		this.mockMvc.perform(get("/create"))
				.andExpect(status().isOk())
				.andExpect(view().name("create"));
	}
	
	@Test
	public void testCreateNewBlogEntry_loggedIn() throws Exception {
		UserStorage userStorage = wac.getBean("userStorage", UserStorage.class);
		userStorage.saveOrUpdate(new User("admin", "system"));
		Principal principal = new Principal() {
			
			@Override
			public String getName() {
				return "admin";
			}
		};
		this.mockMvc.perform(post("/create")
				.param("text", "this is a blog entry")
				.param("tags[0]", "a")
				.param("tags[1]", "b")
				.principal(principal))
					.andExpect(view().name("redirect:/"));
	}
	
	@Test
	public void testCreateNewBlogEntry_NotloggedIn() throws Exception {
		this.mockMvc.perform(post("/create")
				.param("text", "this is a blog entry")
				.param("tags[0]", "a")
				.param("tags[1]", "b"))
					.andExpect(view().name("redirect:/login"));
	}
}
