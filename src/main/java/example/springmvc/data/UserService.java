package example.springmvc.data;

import org.springframework.beans.factory.annotation.Autowired;

import example.springmvc.utils.Result;

public class UserService {
	
	@Autowired
	private UserStorage userStorage;
	
	public Result<User, RegistrationError> createNewUser(UserRegistrationData userRegistrationData) {
		String userId = userRegistrationData.getId();
		// check if an input field is null
		if (userRegistrationData.getId() == null || userRegistrationData.getPassword() == null || userRegistrationData.getConfirmedPassword() == null) {
			return Result.createError(new RegistrationError(RegistrationError.ErrorType.INVALID_INPUT));
		}
		// check if user already exists
		if (this.userStorage.byId(userId) != null) {
			return Result.createError(new RegistrationError(RegistrationError.ErrorType.USER_ALREADY_EXISTS, userId));
		}
		// check if passwords match
		if (!userRegistrationData.getPassword().equals(userRegistrationData.getConfirmedPassword())) {
			return Result.createError(new RegistrationError(RegistrationError.ErrorType.PASSWORDS_DONT_MATCH));
		}
		User user = new User(userRegistrationData.getId(), userRegistrationData.getPassword());
		this.userStorage.saveOrUpdate(user);
		return Result.createSuccess(user);
	}
	
	public boolean verifyLogin(String userName, String password) {
		User user = this.userStorage.byId(userName);
		return (user != null) &&  user.getPassword().equals(password);
	}

	public UserStorage getUserStorage() {
		return userStorage;
	}

	public void setUserStorage(UserStorage userStorage) {
		this.userStorage = userStorage;
	}

}
