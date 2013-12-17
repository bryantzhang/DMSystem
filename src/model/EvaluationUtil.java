package model;

// Generated Dec 16, 2013 7:25:34 PM by Hibernate Tools 4.0.0

import dao.Evaluation;

/**
 * Utility object for domain model class Evaluation.
 * @see dao.Evaluation
 * @author Justin Yang
 */
public class EvaluationUtil {
	
	public void add(Evaluation transientInstance) throws Exception {
		HibernateUtil.persist(transientInstance);
	}

	public void remove(Evaluation persistentInstance) throws Exception {
		HibernateUtil.remove(persistentInstance);
	}

	public void update(Evaluation detachedInstance) throws Exception {
		if (detachedInstance != null) {
			HibernateUtil.update(detachedInstance);
		}
	}

	public Evaluation findById(int id) throws Exception {
		return (Evaluation) HibernateUtil.findById(Evaluation.class, id);
	}
}
