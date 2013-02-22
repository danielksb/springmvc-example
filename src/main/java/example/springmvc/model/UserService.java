package example.springmvc.model;

import org.springframework.beans.factory.annotation.Autowired;

public class UserService {
	
	@Autowired
	private UserStorage userStorage;
	
	public User createNewUser(UserRegistrationData userRegistrationData) {
		String userId = userRegistrationData.getId();
		
		// check if user already exists
		if (this.userStorage.byId(userId) != null) {
			return null;
		} else {
			User user = new User(userRegistrationData.getId(), userRegistrationData.getPassword());
			this.userStorage.saveOrUpdate(user);
			return user;	
		}
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
