package example.springmvc.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BlogEntryTest {

	private User user;
	private BlogEntry entry;
	
	
	@Before
	public void setUp() {
		user = new User("admin", "system");
		entry = new BlogEntry("123", user);
	}
	
	@Test
	public void testUniquenessOfTags() {
		entry.addTag("a");
		entry.addTag("b");
		entry.addTag("a");
		assertEquals(2, entry.getTags().size());
		assertEquals(true, entry.getTags().contains("a"));
		assertEquals(true, entry.getTags().contains("b"));
	}
	
	@Test
	public void testIsTagged() {
		entry.addTag("a");
		entry.addTag("b");
		assertEquals(true, entry.isTagged("a"));
		assertEquals(true, entry.isTagged("b"));
		assertEquals(false, entry.isTagged("c"));
	}
}
