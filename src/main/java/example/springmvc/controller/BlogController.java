package example.springmvc.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import example.springmvc.model.BlogEntry;
import example.springmvc.model.BlogEntryFormData;
import example.springmvc.model.BlogEntryStorage;
import example.springmvc.model.User;
import example.springmvc.model.UserStorage;

@Controller
public class BlogController {

	@Autowired
	private BlogEntryStorage blogStorage;
	
	@Autowired
	private UserStorage userStorage;
	
	public String createBlogEntry(BlogEntryFormData formData, Principal principal) {
		if (principal != null) {
			User user = this.userStorage.byId(principal.getName());
			// the id is empty and will be set when we save the blog entry for
			// the first time into the database
			BlogEntry entry = new BlogEntry("", user, formData.getText());
			this.blogStorage.saveOrUpdate(entry);
			return "redirect:/";
		}
		return "redirect:/login";
	}
	
	
	public BlogEntryStorage getBlogStorage() {
		return blogStorage;
	}

	public void setBlogStorage(BlogEntryStorage blogStorage) {
		this.blogStorage = blogStorage;
	}

	public UserStorage getUserStorage() {
		return userStorage;
	}

	public void setUserStorage(UserStorage userStorage) {
		this.userStorage = userStorage;
	}

}
