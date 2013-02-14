package example.springmvc.data;

/**
 * Storage for user sessions.
 * @author Daniel
 *
 */
public interface SessionStorage {

	public Session create(String userId);
	public Session getById(String sessionId);
	public Session deleteById(String sessionId);
}
