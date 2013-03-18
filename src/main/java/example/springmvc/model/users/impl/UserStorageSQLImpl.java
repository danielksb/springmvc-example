package example.springmvc.model.users.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import example.springmvc.model.users.User;
import example.springmvc.model.users.UserStorage;

public class UserStorageSQLImpl implements UserStorage {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public User byId(String id) {
		Session session = this.sessionFactory.getCurrentSession();
		session.beginTransaction();
		User user = (User) session.get(User.class, id);
		session.getTransaction().commit();
		return user;
	}

	@Override
	public void saveOrUpdate(User user) {
		Session session = this.sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.saveOrUpdate(user);
		session.getTransaction().commit();
	}

}
