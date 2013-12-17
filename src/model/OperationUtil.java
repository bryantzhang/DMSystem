package model;

// Generated Dec 16, 2013 7:25:34 PM by Hibernate Tools 4.0.0

import dao.Operation;

/**
 * Utility object for domain model class Operation.
 * @see dao.Operation
 * @author Justin Yang
 */
public class OperationUtil {
	
	public void add(Operation transientInstance) throws Exception {
		HibernateUtil.persist(transientInstance);
	}

	public void remove(Operation persistentInstance) throws Exception {
		HibernateUtil.remove(persistentInstance);
	}

	public void update(Operation detachedInstance) throws Exception {
		if (detachedInstance != null) {
			HibernateUtil.update(detachedInstance);
		}
	}

	public Operation findById(int id) throws Exception {
		return (Operation) HibernateUtil.findById(Operation.class, id);
	}
}
