package model;

// Generated Dec 16, 2013 7:25:34 PM by Hibernate Tools 4.0.0

import dao.User;

/**
 * Utility object for domain model class User.
 * @see dao.User
 * @author Justin Yang
 */
public class UserUtil {
	
	public void add(User transientInstance) {
		HibernateUtil.persist(transientInstance);
	}

	public void remove(User persistentInstance) {
		HibernateUtil.remove(persistentInstance);
	}

	public User update(User detachedInstance) {
		if (detachedInstance == null) {
			return null;
		}
		return (User) HibernateUtil.merge(detachedInstance);
	}

	public User findById(int id) {
		return (User) HibernateUtil.findById(User.class, id);
	}
}
