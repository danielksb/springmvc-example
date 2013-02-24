package example.springmvc.model;

import java.util.LinkedList;
import java.util.List;

public class BlogEntry {
	
	private String text = "";
	
	private String authorId = "";
	
	private List<String> tags = new LinkedList<String>();
	
	public BlogEntry(User author) {
		this.setAuthorId(author.getId());
	}
	
	public BlogEntry(User author, String text) {
		this.setAuthorId(author.getId());
		this.setText(text);
	}
	
	/**
	 * Adds a new tag. Tags must be unique, so adding
	 * a tag twice will only add the tag once.
	 * @param tag
	 * 		name of the new tag
	 */
	public void addTag(String tag) {
		if (this.tags.contains(tag) == false) {
			this.tags.add(tag);
		}
	}
	
	/**
	 * Checks if a specific tag is defined for this entry.
	 * @param tag
	 * 		name of the tag
	 * @return
	 * 		true if the entry is marked by the given tag
	 */
	public boolean isTagged(String tag) {
		return this.tags.contains(tag);
	}

	public List<String> getTags() {
		return tags;
	}
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getAuthorId() {
		return authorId;
	}

	public void setAuthorId(String authorId) {
		this.authorId = authorId;
	}

}
