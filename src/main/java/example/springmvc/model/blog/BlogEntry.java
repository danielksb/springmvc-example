package example.springmvc.model.blog;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import example.springmvc.model.users.User;


@Entity
@Table(name="blogentry_table")
public class BlogEntry {
	
	@Id
	@Column(name="id")
	private String id = "";
	
	@Column(name="text")
	private String text = "";
	
	@Column(name="user_id")
	private String authorId = "";
	
	@Column(name="creation_date")
	private Date creationDate = new Date();
	
	@ElementCollection(fetch=FetchType.EAGER, targetClass=String.class)
	@CollectionTable(name="tags_table", joinColumns=@JoinColumn(name = "id"))
	@Column(name="tags")
	private List<String> tags = new LinkedList<String>();
	
	public BlogEntry() {
	}
	
	public BlogEntry(String id, String text) {
		this.setId(id);
		this.setText(text);
	}
	
	public BlogEntry(String id, String text, User author) {
		this.setId(id);
		this.setText(text);
		if (author != null) {
			this.setAuthorId(author.getId());
		}
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Add a list of tags to the entry.
	 * @param tagString
	 * 		space separated list of tags
	 */
	public void addTags(String tagString) {
		this.addTags(tagString, " ");
	}
	/**
	 * Add a list of tags to the entry.
	 * @param tagString
	 * 		string containing tags separated by a delimeter
	 * @param delimeter
	 * 		delimieter by which the tags are separated 
	 */
	public void addTags(String tagString, String delimeter) {
		String[] tags = tagString.split(delimeter);
		for (String tag : tags) {
			if (tag.length() > 0) {
				this.addTag(tag);
			}
		}
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

}
