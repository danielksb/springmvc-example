package example.springmvc.model.blog.impl;

import java.util.List;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import example.springmvc.model.blog.BlogEntry;
import example.springmvc.model.blog.BlogEntryStorage;

public class BlogEntryStorageSQLImpl implements BlogEntryStorage {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional(readOnly=true)
	public BlogEntry byId(String id) {
		Session session = this.sessionFactory.getCurrentSession();
		BlogEntry entry = (BlogEntry) session.get(BlogEntry.class, id);
		return entry;
	}

	@Override
	@Transactional
	public void saveOrUpdate(BlogEntry entry) {
		String id = entry.getId();
		if (id == null || id.length() == 0) {
			entry.setId(UUID.randomUUID().toString());
		}
		Session session = this.sessionFactory.getCurrentSession();
		session.saveOrUpdate(entry);
	}

	@Override
	@Transactional(readOnly=true)
	@SuppressWarnings("unchecked")
	public List<BlogEntry> findAll() {
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery("from BlogEntry").list();
	}

}
