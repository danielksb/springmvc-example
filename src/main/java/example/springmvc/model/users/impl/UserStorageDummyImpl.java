package example.springmvc.model.users.impl;

import java.util.Hashtable;

import example.springmvc.model.blog.User;
import example.springmvc.model.blog.UserStorage;

public class UserStorageDummyImpl implements UserStorage {
	
	private Hashtable<String, User> data = new Hashtable<>();

	@Override
	public User byId(String id) {
		return this.data.get(id);
	}


	@Override
	public void saveOrUpdate(User user) {
		String key = user.getId();
		this.data.put(key, user);
	}

}
