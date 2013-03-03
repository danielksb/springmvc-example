package example.springmvc.model;

/**
 * This class contains the form data for creating a new blog entry
 * @author Daniel
 *
 */
public class BlogEntryFormData {

	private String text = "";
	
	private String tags = "";
	
	
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

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}
}
