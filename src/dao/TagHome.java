package dao;

// Generated Dec 16, 2013 7:25:34 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Tag.
 * @see dao.Tag
 * @author Justin Yang
 */
@Stateless
public class TagHome {

	private static final Log log = LogFactory.getLog(TagHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Tag transientInstance) {
		log.debug("persisting Tag instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Tag persistentInstance) {
		log.debug("removing Tag instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Tag merge(Tag detachedInstance) {
		log.debug("merging Tag instance");
		try {
			Tag result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Tag findById(int id) {
		log.debug("getting Tag instance with id: " + id);
		try {
			Tag instance = entityManager.find(Tag.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
