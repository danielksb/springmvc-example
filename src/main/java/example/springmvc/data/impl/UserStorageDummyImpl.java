package example.springmvc.data.impl;

import java.util.Hashtable;

import example.springmvc.data.User;
import example.springmvc.data.UserRegistrationData;
import example.springmvc.data.UserStorage;

public class UserStorageDummyImpl implements UserStorage {
	
	private Hashtable<String, User> data = new Hashtable<>();

	public User create(UserRegistrationData userRegistrationData) {
		User user = new User(userRegistrationData.getId(), userRegistrationData.getPassword());
		this.data.put(userRegistrationData.getId(), user);
		return user;
	}
	
	public User byId(String id) {
		return this.data.get(id);
	}
}
