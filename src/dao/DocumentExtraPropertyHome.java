package dao;

// Generated Dec 16, 2013 7:25:34 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class DocumentExtraProperty.
 * @see dao.DocumentExtraProperty
 * @author Justin Yang
 */
@Stateless
public class DocumentExtraPropertyHome {

	private static final Log log = LogFactory
			.getLog(DocumentExtraPropertyHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(DocumentExtraProperty transientInstance) {
		log.debug("persisting DocumentExtraProperty instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(DocumentExtraProperty persistentInstance) {
		log.debug("removing DocumentExtraProperty instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public DocumentExtraProperty merge(DocumentExtraProperty detachedInstance) {
		log.debug("merging DocumentExtraProperty instance");
		try {
			DocumentExtraProperty result = entityManager
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public DocumentExtraProperty findById(int id) {
		log.debug("getting DocumentExtraProperty instance with id: " + id);
		try {
			DocumentExtraProperty instance = entityManager.find(
					DocumentExtraProperty.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
