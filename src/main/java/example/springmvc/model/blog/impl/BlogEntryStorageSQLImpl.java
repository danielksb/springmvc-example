package example.springmvc.model.blog.impl;

import java.util.List;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import example.springmvc.model.blog.BlogEntry;
import example.springmvc.model.blog.BlogEntryStorage;

public class BlogEntryStorageSQLImpl implements BlogEntryStorage {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public BlogEntry byId(String id) {
		Session session = this.sessionFactory.getCurrentSession();
		session.beginTransaction();
		BlogEntry entry = (BlogEntry) session.get(BlogEntry.class, id);
		session.getTransaction().commit();
		return entry;
	}

	@Override
	public void saveOrUpdate(BlogEntry entry) {
		String id = entry.getId();
		if (id == null || id.length() == 0) {
			entry.setId(UUID.randomUUID().toString());
		}
		Session session = this.sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.saveOrUpdate(entry);
		session.getTransaction().commit();
	}

	@Override
	public List<BlogEntry> findAll() {
		Session session = this.sessionFactory.getCurrentSession();
		session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<BlogEntry> list = session.createQuery("from BlogEntry").list();
		session.getTransaction().commit();
		return list;
	}

}
