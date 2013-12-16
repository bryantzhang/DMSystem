package model;

// Generated Dec 16, 2013 7:25:34 PM by Hibernate Tools 4.0.0

import dao.RelationType;;

/**
 * Utility object for domain model class RelationType.
 * @see dao.RelationType
 * @author Justin Yang
 */
public class RelationTypeUtil {
	
	public void add(RelationType transientInstance) {
		HibernateUtil.persist(transientInstance);
	}

	public void remove(RelationType persistentInstance) {
		HibernateUtil.remove(persistentInstance);
	}

	public RelationType update(RelationType detachedInstance) {
		if (detachedInstance == null) {
			return null;
		}
		return (RelationType) HibernateUtil.merge(detachedInstance);
	}

	public RelationType findById(int id) {
		return (RelationType) HibernateUtil.findById(RelationType.class, id);
	}
}
