package model;

// Generated Dec 16, 2013 7:25:34 PM by Hibernate Tools 4.0.0

import dao.Tag;

/**
 * Utility object for domain model class Tag.
 * @see dao.Tag
 * @author Justin Yang
 */
public class TagUtil {
	
	public void add(Tag transientInstance) {
		HibernateUtil.persist(transientInstance);
	}

	public void remove(Tag persistentInstance) {
		HibernateUtil.remove(persistentInstance);
	}

	public Tag update(Tag detachedInstance) {
		if (detachedInstance == null) {
			return null;
		}
		return (Tag) HibernateUtil.merge(detachedInstance);
	}

	public Tag findById(int id) {
		return (Tag) HibernateUtil.findById(Tag.class, id);
	}
}
