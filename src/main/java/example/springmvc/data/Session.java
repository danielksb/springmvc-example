package example.springmvc.data;

/**
 * This is a user session.
 * @author Daniel
 *
 */
public class Session {

	private String id = "";
	
	private String userId = "";

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
