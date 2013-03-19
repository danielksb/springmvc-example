package example.springmvc.model.users.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import example.springmvc.model.users.User;
import example.springmvc.model.users.UserStorage;

public class UserStorageSQLImpl implements UserStorage {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	@Transactional(readOnly=true)
	public User byId(String id) {
		Session session = this.sessionFactory.getCurrentSession();
		User user = (User) session.get(User.class, id);
		return user;
	}

	@Override
	@Transactional
	public void saveOrUpdate(User user) {
		Session session = this.sessionFactory.getCurrentSession();
		session.saveOrUpdate(user);
	}
}
