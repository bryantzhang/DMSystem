package model;

// Generated Dec 16, 2013 7:25:34 PM by Hibernate Tools 4.0.0

import dao.DocumentExtraProperty;

/**
 * Utility object for domain model class DocumentExtraProperty.
 * @see dao.DocumentExtraProperty
 * @author Justin Yang
 */
public class DocumentExtraPropertyUtil {
	
	public void add(DocumentExtraProperty transientInstance) {
		HibernateUtil.persist(transientInstance);
	}

	public void remove(DocumentExtraProperty persistentInstance) {
		HibernateUtil.remove(persistentInstance);
	}

	public DocumentExtraProperty update(DocumentExtraProperty detachedInstance) {
		if (detachedInstance == null) {
			return null;
		}
		return (DocumentExtraProperty) HibernateUtil.merge(detachedInstance);
	}

	public DocumentExtraProperty findById(int id) {
		return (DocumentExtraProperty) HibernateUtil.findById(DocumentExtraProperty.class, id);
	}
}
