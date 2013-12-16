package dao;

// Generated Dec 16, 2013 7:25:34 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Document.
 * @see dao.Document
 * @author Justin Yang
 */
@Stateless
public class DocumentHome {

	private static final Log log = LogFactory.getLog(DocumentHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Document transientInstance) {
		log.debug("persisting Document instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Document persistentInstance) {
		log.debug("removing Document instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Document merge(Document detachedInstance) {
		log.debug("merging Document instance");
		try {
			Document result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Document findById(int id) {
		log.debug("getting Document instance with id: " + id);
		try {
			Document instance = entityManager.find(Document.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
