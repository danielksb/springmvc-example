package example.springmvc.model;

/**
 * Storage for blog entries.
 * @author Daniel
 *
 */
public interface BlogEntryStorage {

	public BlogEntry byId(String id);
	
	public void saveOrUpdate(BlogEntry entry);
}
