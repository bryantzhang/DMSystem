package dao;

// Generated Dec 16, 2013 7:25:34 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class RelationType.
 * @see dao.RelationType
 * @author Justin Yang
 */
@Stateless
public class RelationTypeHome {

	private static final Log log = LogFactory.getLog(RelationTypeHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(RelationType transientInstance) {
		log.debug("persisting RelationType instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(RelationType persistentInstance) {
		log.debug("removing RelationType instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public RelationType merge(RelationType detachedInstance) {
		log.debug("merging RelationType instance");
		try {
			RelationType result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public RelationType findById(int id) {
		log.debug("getting RelationType instance with id: " + id);
		try {
			RelationType instance = entityManager.find(RelationType.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
