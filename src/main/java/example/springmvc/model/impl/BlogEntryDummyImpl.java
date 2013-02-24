package example.springmvc.model.impl;

import java.util.Hashtable;

import example.springmvc.model.BlogEntry;
import example.springmvc.model.BlogEntryStorage;

public class BlogEntryDummyImpl implements BlogEntryStorage {

	private Hashtable<String, BlogEntry> data = new Hashtable<>();
	
	@Override
	public BlogEntry byId(String id) {
		return this.data.get(id);
	}

	@Override
	public void saveOrUpdate(BlogEntry entry) {
		this.data.put(entry.getId(), entry);
	}

}
