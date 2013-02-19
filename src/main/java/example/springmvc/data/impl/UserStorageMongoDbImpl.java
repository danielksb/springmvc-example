package example.springmvc.data.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;

import example.springmvc.data.User;
import example.springmvc.data.UserStorage;

public class UserStorageMongoDbImpl implements UserStorage {
	
	@Autowired
	private MongoOperations mongoOperations;


	@Override
	public User byId(String id) {
		return this.mongoOperations.findById(id, User.class);
	}


	@Override
	public void saveOrUpdate(User user) {
		this.mongoOperations.save(user);
	}

}
