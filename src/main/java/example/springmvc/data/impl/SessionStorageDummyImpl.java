package example.springmvc.data.impl;

import java.util.HashMap;
import java.util.UUID;

import example.springmvc.data.Session;
import example.springmvc.data.SessionStorage;

public class SessionStorageDummyImpl implements SessionStorage {

	private HashMap<String, Session> data = new HashMap<>();
	
	@Override
	public Session getById(String sessionId) {
		return this.data.get(sessionId);
	}

	@Override
	public Session create(String userId) {
		Session session = new Session();
		session.setUserId(userId);
		session.setId(UUID.randomUUID().toString());
		this.data.put(session.getId(), session);
		return session;
	}

	@Override
	public Session deleteById(String sessionId) {
		return this.data.remove(sessionId);
	}

}
