package example.springmvc.model.blog;

import org.springframework.util.StringUtils;


/**
 * This class contains the form data for creating a new blog entry
 * @author Daniel
 *
 */
public class BlogEntryFormData {

	private String text = "";
	
	private String tags = "";
	
	private String authorId = "";
	
	private String creationDate = "";
	
	public BlogEntryFormData() {
		
	}
	
	public BlogEntryFormData(String text) {
		this.setText(text);
	}
	

	public BlogEntryFormData(BlogEntry entry) {
		this.setTags(StringUtils.collectionToDelimitedString(entry.getTags(), " "));
		this.setText(entry.getText());
		this.setAuthorId(entry.getAuthorId());
		this.setCreationDate(entry.getCreationDate().toString());
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

	public String getAuthorId() {
		return authorId;
	}

	public void setAuthorId(String authorId) {
		this.authorId = authorId;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}
}
