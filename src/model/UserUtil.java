package model;

// Generated Dec 16, 2013 7:25:34 PM by Hibernate Tools 4.0.0

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import dao.User;

/**
 * Utility object for domain model class User.
 * 
 * @see dao.User
 * @author Justin Yang
 */
public class UserUtil {

	public void add(User transientInstance) throws Exception {
		HibernateUtil.persist(transientInstance);
	}

	public void remove(User persistentInstance) throws Exception {
		HibernateUtil.remove(persistentInstance);
	}

	public void update(User detachedInstance) throws Exception {
		if (detachedInstance != null) {
			HibernateUtil.update(detachedInstance);
		}
	}

	public User findById(int id) throws Exception {
		return (User) HibernateUtil.findById(User.class, id);
	}

	public User findByUsername(String username) throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();
		Query query = session
				.createQuery("from User user where username=?");
		Object dbResult = query.setString(0, username).uniqueResult();

		if (dbResult != null) {
			return (User) dbResult;
		} else {
			return null;
		}
	}
}
