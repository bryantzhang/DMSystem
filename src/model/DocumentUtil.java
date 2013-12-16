package model;

// Generated Dec 16, 2013 7:25:34 PM by Hibernate Tools 4.0.0

import dao.Document;

/**
 * Utility object for domain model class Document.
 * @see dao.Document
 * @author Justin Yang
 */
public class DocumentUtil {
	public void add(Document transientInstance) {
		HibernateUtil.persist(transientInstance);
	}

	public void remove(Document persistentInstance) {
		HibernateUtil.remove(persistentInstance);
	}

	public Document update(Document detachedInstance) {
		if (detachedInstance == null) {
			return null;
		}
		return (Document) HibernateUtil.merge(detachedInstance);
	}

	public Document findById(int id) {
		return (Document) HibernateUtil.findById(Document.class, id);
	}
}
