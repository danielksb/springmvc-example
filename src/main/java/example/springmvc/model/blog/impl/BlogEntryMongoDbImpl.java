package example.springmvc.model.blog.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;

import example.springmvc.model.blog.BlogEntry;
import example.springmvc.model.blog.BlogEntryStorage;

public class BlogEntryMongoDbImpl implements BlogEntryStorage {

	@Autowired
	private MongoOperations mongoOperations;
	
	@Override
	public BlogEntry byId(String id) {
		return this.mongoOperations.findById(id, BlogEntry.class);
	}

	@Override
	public void saveOrUpdate(BlogEntry entry) {
		this.mongoOperations.save(entry);
	}

	@Override
	public List<BlogEntry> findAll() {
		return this.mongoOperations.findAll(BlogEntry.class);
	}

}
