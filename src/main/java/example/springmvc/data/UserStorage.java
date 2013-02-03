package example.springmvc.data;

public interface UserStorage {

	public User create(UserRegistrationData userRegistrationData);
	
	public User byId(String id);
}
