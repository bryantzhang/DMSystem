package model;

// Generated Dec 16, 2013 7:25:34 PM by Hibernate Tools 4.0.0

import dao.Operation;

/**
 * Utility object for domain model class Operation.
 * @see dao.Operation
 * @author Justin Yang
 */
public class OperationUtil {
	
	public void add(Operation transientInstance) {
		HibernateUtil.persist(transientInstance);
	}

	public void remove(Operation persistentInstance) {
		HibernateUtil.remove(persistentInstance);
	}

	public Operation update(Operation detachedInstance) {
		if (detachedInstance == null) {
			return null;
		}
		return (Operation) HibernateUtil.merge(detachedInstance);
	}

	public Operation findById(int id) {
		return (Operation) HibernateUtil.findById(Operation.class, id);
	}
}
