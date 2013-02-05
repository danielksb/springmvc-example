package example.springmvc.data;

public interface SessionStorage {

	public Session create(String userId);
	public Session getById(String sessionId);
	public Session deleteById(String sessionId);
}
