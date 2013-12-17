package model;

import dao.DocumentType;;

public class DocumentTypeUtil {
	public void add(DocumentType transientInstance) throws Exception {
		HibernateUtil.persist(transientInstance);
	}

	public void remove(DocumentType persistentInstance) throws Exception {
		HibernateUtil.remove(persistentInstance);
	}

	public void update(DocumentType detachedInstance) throws Exception {
		if (detachedInstance != null) {
			HibernateUtil.update(detachedInstance);
		}
	}

	public DocumentType findById(int id) throws Exception {
		return (DocumentType) HibernateUtil.findById(DocumentType.class, id);
	}
}
