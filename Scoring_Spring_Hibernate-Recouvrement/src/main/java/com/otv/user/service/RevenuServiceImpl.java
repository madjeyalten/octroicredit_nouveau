package com.otv.user.service;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.otv.model.mod.Depensecourante;
import com.otv.model.mod.Revenucourant;
import com.otv.model.mod.Revenuexceptionnel;

@Service("revenuService")
@Transactional
public class RevenuServiceImpl implements RevenuService{
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Revenucourant> findAllRevenucourant() {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Revenucourant.class);
		return crit.list();
	}

	@Override
	public List<Revenucourant> findRevenucourantByCriteria(String parameter) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Revenucourant.class);
		crit.add(Restrictions.like("id.libelle", "c"));
		return crit.list();
	}

	@Override
	public List<Revenuexceptionnel> findAllRevenuexceptionnel() {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Revenuexceptionnel.class);
		return crit.list();
	}

	@Override
	public List<Revenuexceptionnel> findRevenuexceptionnelByCriteria(
			String parameter) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Revenuexceptionnel.class);
		crit.add(Restrictions.like("id.libelle", "c"));
		return crit.list();
	}

	@Override
	public void saveRevenucourant(Revenucourant revenucourant) {
		sessionFactory.getCurrentSession().saveOrUpdate(revenucourant);
	}

	@Override
	public void saveRevenuexceptionnel(Revenuexceptionnel revenuexceptionnel) {
		sessionFactory.getCurrentSession().saveOrUpdate(revenuexceptionnel);
	}


}
