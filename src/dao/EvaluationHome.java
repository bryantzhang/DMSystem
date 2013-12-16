package dao;

// Generated Dec 16, 2013 7:25:34 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Evaluation.
 * @see dao.Evaluation
 * @author Justin Yang
 */
@Stateless
public class EvaluationHome {

	private static final Log log = LogFactory.getLog(EvaluationHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Evaluation transientInstance) {
		log.debug("persisting Evaluation instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Evaluation persistentInstance) {
		log.debug("removing Evaluation instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Evaluation merge(Evaluation detachedInstance) {
		log.debug("merging Evaluation instance");
		try {
			Evaluation result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Evaluation findById(int id) {
		log.debug("getting Evaluation instance with id: " + id);
		try {
			Evaluation instance = entityManager.find(Evaluation.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
