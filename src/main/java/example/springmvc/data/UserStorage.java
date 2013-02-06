package example.springmvc.data;

import example.springmvc.utils.Result;

public interface UserStorage {

	public Result<User, RegistrationError> createNewUser(UserRegistrationData userRegistrationData);
	
	public User byId(String id);
	
	public boolean verifyLogin(String userName, String password);
}
