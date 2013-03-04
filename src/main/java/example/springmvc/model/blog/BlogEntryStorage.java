package example.springmvc.model.blog;

import java.util.List;


/**
 * Storage for blog entries.
 * @author Daniel
 *
 */
public interface BlogEntryStorage {

	public BlogEntry byId(String id);
	
	public void saveOrUpdate(BlogEntry entry);
	
	public List<BlogEntry> findAll();
}
