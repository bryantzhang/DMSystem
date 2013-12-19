package model;

import dao.DocumentType;
import org.hibernate.Query;
import org.hibernate.Session;;

public class DocumentTypeUtil {
	public static void add(DocumentType transientInstance) throws Exception {
		HibernateUtil.persist(transientInstance);
	}

	public static void remove(DocumentType persistentInstance) throws Exception {
		HibernateUtil.remove(persistentInstance);
	}

	public static void update(DocumentType detachedInstance) throws Exception {
		if (detachedInstance != null) {
			HibernateUtil.update(detachedInstance);
		}
	}

	public static DocumentType findById(int id) throws Exception {
		return (DocumentType) HibernateUtil.findById(DocumentType.class, id);
	}

    public static DocumentType findByName(String name) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();
        Query query = session
                .createQuery("from DocumentType dt where dt.name=?");
        Object dbResult = query.setString(0, name).uniqueResult();

        if (dbResult != null) {
            return (DocumentType) dbResult;
        } else {
            return null;
        }
    }
}
