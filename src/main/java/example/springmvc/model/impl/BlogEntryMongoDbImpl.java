package example.springmvc.model.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;

import example.springmvc.model.BlogEntry;
import example.springmvc.model.BlogEntryStorage;

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

}
