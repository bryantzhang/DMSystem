package dao;

// Generated Dec 16, 2013 7:25:34 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Operation.
 * @see dao.Operation
 * @author Justin Yang
 */
@Stateless
public class OperationHome {

	private static final Log log = LogFactory.getLog(OperationHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Operation transientInstance) {
		log.debug("persisting Operation instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Operation persistentInstance) {
		log.debug("removing Operation instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Operation merge(Operation detachedInstance) {
		log.debug("merging Operation instance");
		try {
			Operation result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Operation findById(int id) {
		log.debug("getting Operation instance with id: " + id);
		try {
			Operation instance = entityManager.find(Operation.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
