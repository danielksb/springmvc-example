package example.springmvc.model.users.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;

import example.springmvc.model.users.User;
import example.springmvc.model.users.UserStorage;

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
