package model;

// Generated Dec 16, 2013 7:25:34 PM by Hibernate Tools 4.0.0

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import dao.User;
import org.restlet.Restlet;
import org.restlet.resource.Resource;

/**
 * Utility object for domain model class User.
 * 
 * @see dao.User
 * @author Justin Yang
 */
public class UserUtil {

	public static void add(User transientInstance) throws Exception {
		HibernateUtil.persist(transientInstance);
	}

	public static void remove(User persistentInstance) throws Exception {
		HibernateUtil.remove(persistentInstance);
	}

	public static void update(User detachedInstance) throws Exception {
		if (detachedInstance != null) {
			HibernateUtil.update(detachedInstance);
		}
	}

	public static User findById(int id) throws Exception {
		return (User) HibernateUtil.findById(User.class, id);
	}

	public static User findByUsername(String username) throws Exception {
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

    public static User getCurrentUser(Resource resource) throws Exception {
        String identifier = resource.getClientInfo().getUser().getIdentifier();
        return UserUtil.findByUsername(identifier);
    }
}
