package example.springmvc.model;

import java.util.LinkedList;

/**
 * This class contains the form data for creating a new blog entry
 * @author Daniel
 *
 */
public class BlogEntryFormData {

	private String text = "";
	
	private LinkedList<String> tags = new LinkedList<>();
	
	
	public BlogEntryFormData() {
		
	}
	
	public BlogEntryFormData(String text) {
		this.setText(text);
	}
	

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public LinkedList<String> getTags() {
		return tags;
	}

	public void setTags(LinkedList<String> tags) {
		this.tags = tags;
	}
}
