package model;

// Generated Dec 16, 2013 7:25:34 PM by Hibernate Tools 4.0.0

import dao.DocumentExtraProperty;

/**
 * Utility object for domain model class DocumentExtraProperty.
 * @see dao.DocumentExtraProperty
 * @author Justin Yang
 */
public class DocumentExtraPropertyUtil {
	
	public static void add(DocumentExtraProperty transientInstance) throws Exception {
		HibernateUtil.persist(transientInstance);
	}

	public static void remove(DocumentExtraProperty persistentInstance) throws Exception {
		HibernateUtil.remove(persistentInstance);
	}

	public static void update(DocumentExtraProperty detachedInstance) throws Exception {
		if (detachedInstance != null) {
			HibernateUtil.update(detachedInstance);
		}
	}

	public static DocumentExtraProperty findById(int id) throws Exception {
		return (DocumentExtraProperty) HibernateUtil.findById(DocumentExtraProperty.class, id);
	}
}
