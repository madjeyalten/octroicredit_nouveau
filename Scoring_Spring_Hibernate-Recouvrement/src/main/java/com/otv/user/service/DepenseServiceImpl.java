package com.otv.user.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.otv.model.mod.Depensecourante;
import com.otv.model.mod.Depenseexception;
import com.otv.model.mod.Revenucourant;
import com.otv.model.mod.Revenuexceptionnel;
import com.otv.model.mod.Revenuordepense;
import com.otv.model.mod.User;

@Service("depenseService")
@Transactional
public class DepenseServiceImpl implements DepenseService {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Depensecourante> findAllDepenseCourante() {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Depensecourante.class);
		return crit.list();
	}

	@Override
	public List<Depensecourante> findDepenseCouranteByCriteria(String parameter) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Depensecourante.class);
		crit.add(Restrictions.like("id.libelle", "c"));
		return crit.list();
	}

	@Override
	public List<Depenseexception> findAllDepenseException() {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Depenseexception.class);
		return crit.list();
	}

	@Override
	public List<Depenseexception> findDepenseExceptionByCriteria(String parameter) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Depenseexception.class);
		crit.add(Restrictions.like("id.libelle", "c"));
		return crit.list();
	}

	@Override
	public void saveDepenseCourante(Depensecourante depensecourante) {
		sessionFactory.getCurrentSession().saveOrUpdate(depensecourante);
		
	}

	@Override
	public void saveDepenseException(Depenseexception depenseException) {
		sessionFactory.getCurrentSession().saveOrUpdate(depenseException);	
	}

	@Override
	public void saveUser(User user) {
		sessionFactory.getCurrentSession().saveOrUpdate(user);
	}

	@Override
	public boolean checkHabilitation(String login, String motDePasse) {
		boolean result = false;
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				User.class);
		crit.add(Restrictions.like("login", login));
		crit.add(Restrictions.like("mdpasse", motDePasse));
		List<User> listUser = crit.list();
		if (listUser != null && !listUser.isEmpty()) {
			result = true;
		}
		return result;
	}

	@Override
	public List<User> findUserByLogin(String login) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				User.class);
		crit.add(Restrictions.like("login", login));
		List<User> listUser = crit.list();
		return listUser;
	}

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
	public List<Revenuordepense> findAllRevenuordepense() {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Revenuordepense.class);
		crit.addOrder(Order.asc("date"));
		return crit.list();
	}

	@Override
	public List<Revenuordepense> findRevenuordepenseByCriteria(String parameter) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Revenuordepense.class);
		crit.add(Restrictions.like("id.libelle", "c"));
		return crit.list();
	}
	
	@Override
	public List<Revenuordepense> findRevenuordepenseByDate(Date inputDate) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Revenuordepense.class);
	    crit.add(Restrictions.le("date",new java.sql.Timestamp(inputDate.getTime()))); 
		return crit.list();
	}
	
	@Override
	public List<Revenuordepense> findRevenuByDate(Date inputDate) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Revenuordepense.class);
		crit.add(Restrictions.le("date",new java.sql.Timestamp(inputDate.getTime()))); 
		crit.add(Restrictions.like("type","REVEXC"));
//		Criterion criterion = Restrictions.eq("type","REVREC");
//		Disjunction disjunction = Restrictions.disjunction();
//		disjunction.add(criterion);
//		crit.add(disjunction);
		return crit.list();
	}

	@Override
	public List<Revenuordepense> findDepenseDate(Date inputDate) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Revenuordepense.class);
		crit.add(Restrictions.le("date",new java.sql.Timestamp(inputDate.getTime()))); 
		crit.add(Restrictions.like("type","DEPEXC"));
//		Criterion criterion = Restrictions.eq("type","DEPREC");
//		Disjunction disjunction = Restrictions.disjunction();
//		disjunction.add(criterion);
//		crit.add(disjunction);
		return crit.list();
	}
	
	@Override
	public List<Revenuordepense> findListRevReccurent() {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Revenuordepense.class);
		crit.add(Restrictions.like("type","REVREC"));
		return crit.list();
	}

	@Override
	public List<Revenuordepense> findDepRevReccurent() {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Revenuordepense.class);
		crit.add(Restrictions.like("type","DEPREC"));
		return crit.list();
	}
	
	@Override
	public void saveDepenseOrRevenu(Revenuordepense revenuordepense) {
		sessionFactory.getCurrentSession().saveOrUpdate(revenuordepense);
	}

	@Override
	public void saveRevenucourant(Revenucourant revenucourant) {
		sessionFactory.getCurrentSession().saveOrUpdate(revenucourant);
		
	}

	@Override
	public void saveRevenuexceptionnel(Revenuexceptionnel Revenuexceptionnel) {
		sessionFactory.getCurrentSession().saveOrUpdate(Revenuexceptionnel);
	}

	@Override
	public List<Revenuordepense> findRevorDepenseMoisSuivant(int currentValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteRevenuOrDepense(Revenuordepense revenuordepense) {
		sessionFactory.getCurrentSession().delete(revenuordepense);
	}


}
