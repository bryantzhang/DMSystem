package model;

import dao.Document;
import dao.DocumentExtraProperty;
import dao.DocumentWithExtraProperty;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 * Created by justinyang on 13-12-18.
 */
public class DocumentWithExtraPropertyUtil {

        public static void add(DocumentWithExtraProperty transientInstance) throws Exception {
            HibernateUtil.persist(transientInstance);
        }

        public static void remove(DocumentWithExtraProperty persistentInstance) throws Exception {
            HibernateUtil.remove(persistentInstance);
        }

        public static void update(DocumentWithExtraProperty detachedInstance) throws Exception {
            if (detachedInstance != null) {
                HibernateUtil.update(detachedInstance);
            }
        }

        public static DocumentWithExtraProperty find(Document document, DocumentExtraProperty documentExtraProperty) throws Exception {
            Session session = HibernateUtil.getSessionFactory().openSession();

            session.beginTransaction();
            Query query = session
                    .createQuery("from DocumentWithExtraProperty d where d.pk.document=? and d.pk.documentExtraProperty=?");
            Object dbResult = query.setEntity(0, document).setEntity(1, documentExtraProperty).uniqueResult();

            if (dbResult != null) {
                return (DocumentWithExtraProperty) dbResult;
            } else {
                return null;
            }
        }
}
