package model;

// Generated Dec 16, 2013 7:25:34 PM by Hibernate Tools 4.0.0

import dao.Tag;

/**
 * Utility object for domain model class Tag.
 * @see dao.Tag
 * @author Justin Yang
 */
public class TagUtil {
	
	public void add(Tag transientInstance) throws Exception {
		HibernateUtil.persist(transientInstance);
	}

	public void remove(Tag persistentInstance) throws Exception {
		HibernateUtil.remove(persistentInstance);
	}

	public void update(Tag detachedInstance) throws Exception {
		if (detachedInstance != null) {
			HibernateUtil.update(detachedInstance);
		}
	}

	public Tag findById(int id) throws Exception {
		return (Tag) HibernateUtil.findById(Tag.class, id);
	}
}
