package example.springmvc.data.impl;

import java.util.Hashtable;

import example.springmvc.data.RegistrationError;
import example.springmvc.data.User;
import example.springmvc.data.UserRegistrationData;
import example.springmvc.data.UserStorage;
import example.springmvc.utils.Result;

public class UserStorageDummyImpl implements UserStorage {
	
	private Hashtable<String, User> data = new Hashtable<>();

	public Result<User, RegistrationError> createNewUser(UserRegistrationData userRegistrationData) {
		String userId = userRegistrationData.getId();
		if (this.byId(userId) == null) {
			User user = new User(userRegistrationData.getId(), userRegistrationData.getPassword());
			this.data.put(userRegistrationData.getId(), user);
			return Result.createSuccess(user);
		} else {
			return Result.createError(new RegistrationError(RegistrationError.ErrorType.USER_ALREADY_EXISTS, userId));
		}
	}
	
	public User byId(String id) {
		return this.data.get(id);
	}

	@Override
	public boolean verifyLogin(String userName, String password) {
		User user = this.data.get("userName");
		return (user != null) &&  user.getPassword().equals(password);
	}
}
