package example.springmvc.data;

/**
 * Storage for user data.
 * @author Daniel
 *
 */
public interface UserStorage {
	
	public User byId(String id);

	public void saveOrUpdate(User user);
}
