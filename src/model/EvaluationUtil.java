package model;

// Generated Dec 16, 2013 7:25:34 PM by Hibernate Tools 4.0.0

import dao.Evaluation;

/**
 * Utility object for domain model class Evaluation.
 * @see dao.Evaluation
 * @author Justin Yang
 */
public class EvaluationUtil {
	
	public void add(Evaluation transientInstance) {
		HibernateUtil.persist(transientInstance);
	}

	public void remove(Evaluation persistentInstance) {
		HibernateUtil.remove(persistentInstance);
	}

	public Evaluation update(Evaluation detachedInstance) {
		if (detachedInstance == null) {
			return null;
		}
		return (Evaluation) HibernateUtil.merge(detachedInstance);
	}

	public Evaluation findById(int id) {
		return (Evaluation) HibernateUtil.findById(Evaluation.class, id);
	}
}
