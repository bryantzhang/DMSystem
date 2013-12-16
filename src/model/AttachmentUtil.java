package model;

// Generated Dec 16, 2013 7:25:34 PM by Hibernate Tools 4.0.0

import dao.Attachment;

/**
 * Utility object for domain model class Attachment.
 * @see dao.Attachment
 * @author Justin Yang
 */
public class AttachmentUtil {
	
	public void add(Attachment transientInstance) {
		HibernateUtil.persist(transientInstance);
	}

	public void remove(Attachment persistentInstance) {
		HibernateUtil.remove(persistentInstance);
	}

	public Attachment update(Attachment detachedInstance) {
		if (detachedInstance == null) {
			return null;
		}
		return (Attachment) HibernateUtil.merge(detachedInstance);
	}

	public Attachment findById(int id) {
		return (Attachment) HibernateUtil.findById(Attachment.class, id);
	}
}
