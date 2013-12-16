package dao;

// Generated Dec 16, 2013 7:25:34 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Attachment.
 * @see dao.Attachment
 * @author Justin Yang
 */
@Stateless
public class AttachmentHome {

	private static final Log log = LogFactory.getLog(AttachmentHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Attachment transientInstance) {
		log.debug("persisting Attachment instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Attachment persistentInstance) {
		log.debug("removing Attachment instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Attachment merge(Attachment detachedInstance) {
		log.debug("merging Attachment instance");
		try {
			Attachment result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Attachment findById(int id) {
		log.debug("getting Attachment instance with id: " + id);
		try {
			Attachment instance = entityManager.find(Attachment.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
