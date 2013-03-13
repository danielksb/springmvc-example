package example.springmvc.model.users;

/**
 * This class stores all form data which is needed to register a new user.
 * 
 * In many scenarios form data does not match the underlying data structure
 * which is created from the user input, to reflect this common problem the
 * UserRegistrationData class differs from User.
 * 
 * @author Daniel
 * 
 */
public class UserRegistrationData {

	private String id = "";

	private String password = "";
	
	private String confirmedPassword = "";

	// it's a bean, so we unfortunately need the default constructor :-(
	public UserRegistrationData() {

	}

	public UserRegistrationData(String id, String password, String confirmedPassword) {
		this.id = id;
		this.password = password;
		this.confirmedPassword = confirmedPassword;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmedPassword() {
		return confirmedPassword;
	}

	public void setConfirmedPassword(String confirmedPassword) {
		this.confirmedPassword = confirmedPassword;
	}
}
