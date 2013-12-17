package model;

// Generated Dec 16, 2013 7:25:34 PM by Hibernate Tools 4.0.0

import dao.RelationType;;

/**
 * Utility object for domain model class RelationType.
 * @see dao.RelationType
 * @author Justin Yang
 */
public class RelationTypeUtil {
	
	public void add(RelationType transientInstance) throws Exception {
		HibernateUtil.persist(transientInstance);
	}

	public void remove(RelationType persistentInstance) throws Exception {
		HibernateUtil.remove(persistentInstance);
	}

	public void update(RelationType detachedInstance) throws Exception {
		if (detachedInstance != null) {
			HibernateUtil.update(detachedInstance);
		}
	}

	public RelationType findById(int id) throws Exception {
		return (RelationType) HibernateUtil.findById(RelationType.class, id);
	}
}
