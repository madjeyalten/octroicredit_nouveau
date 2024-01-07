package com.otv.user.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.otv.model.mod.Balance;
import com.otv.model.mod.Simulation1;
import com.otv.model.mod.Simulation1Id;
import com.otv.model.mod.Simulation2;
import com.otv.model.mod.Simulation2Id;
import com.otv.model.mod.Simulation3;
import com.otv.model.mod.Simulation3Id;
import com.otv.model.mod.Total;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("balanceService")
@Transactional
public class BalanceServiceImpl implements BalanceService {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Balance> findAll() {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Balance.class);
		return crit.list();
	}

	@Override
	public List<Balance> findByCriteria(String parameter) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Balance.class);
		crit.add(Restrictions.like("id.compte", parameter));
		return crit.list();
	}

	@Override
	public List<Total> findAllTotal() {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Total.class);
		return crit.list();
	}

	@Override
	public List<Total> findTotalByCriteria(String parameter) {
		if (parameter == null) {
			return null;
		}
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Total.class);
		crit.add(Restrictions.eq("id.compte", parameter));
		return crit.list();
	}

	@Override
	public List<Total> findTotalByCriteriaLike(String parameter) {
		if (parameter == null) {
			return null;
		}
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Total.class);
		crit.add(Restrictions.like("id.compte", parameter + "%"));
		return crit.list();
	}

	@Override
	public void saveTotal(List<Total> listTotal) {
		sessionFactory.getCurrentSession()
				.createSQLQuery("TRUNCATE TABLE simulation1").executeUpdate();
		for (Total total : listTotal) {
			Simulation1Id simulation1Id = new Simulation1Id();
			simulation1Id.setCompte(total.getId().getCompte());
			simulation1Id.setCreditMovement(total.getId().getCreditMovement());
			simulation1Id.setDebitMouvement(total.getId().getDebitMouvement());
			simulation1Id.setDebitReport(total.getId().getDebitReport());
			simulation1Id.setCreditReport(total.getId().getCreditReport());
			simulation1Id.setCreditSolde(total.getId().getCreditSolde());
			simulation1Id.setDebitSolde(total.getId().getDebitSolde());
			simulation1Id.setIntitule(total.getId().getIntitule());
			Simulation1 simulation1 = new Simulation1();
			simulation1.setId(simulation1Id);
			sessionFactory.getCurrentSession().saveOrUpdate(simulation1);
		}
	}

	@Override
	public void saveSimulation1(List<Total> listTotal) {
		sessionFactory.getCurrentSession()
				.createSQLQuery("TRUNCATE TABLE simulation1").executeUpdate();
		for (Total total : listTotal) {
			Simulation1Id simulation1Id = new Simulation1Id();
			simulation1Id.setCompte(total.getId().getCompte());
			simulation1Id.setCreditMovement(total.getId().getCreditMovement());
			simulation1Id.setDebitMouvement(total.getId().getDebitMouvement());
			simulation1Id.setDebitReport(total.getId().getDebitReport());
			simulation1Id.setCreditReport(total.getId().getCreditReport());
			simulation1Id.setCreditSolde(total.getId().getCreditSolde());
			simulation1Id.setDebitSolde(total.getId().getDebitSolde());
			simulation1Id.setIntitule(total.getId().getIntitule());
			simulation1Id.setRatio("Capacité d'auto financement");
			Date date = new Date();
			SimpleDateFormat dateFormatComp;
			dateFormatComp = new SimpleDateFormat("dd MMM yyyy hh:mm:ss a");
			simulation1Id.setDateAndHeure(dateFormatComp.format(date));
			Simulation1 simulation1 = new Simulation1();
			simulation1.setId(simulation1Id);
			sessionFactory.getCurrentSession().saveOrUpdate(simulation1);
		}

	}

	@Override
	public void saveSimulation2(List<Total> listTotal) {
		sessionFactory.getCurrentSession()
				.createSQLQuery("TRUNCATE TABLE simulation2").executeUpdate();
		for (Total total : listTotal) {
			Simulation2Id simulation2Id = new Simulation2Id();
			simulation2Id.setCompte(total.getId().getCompte());
			simulation2Id.setCreditMovement(total.getId().getCreditMovement());
			simulation2Id.setDebitMouvement(total.getId().getDebitMouvement());
			simulation2Id.setDebitReport(total.getId().getDebitReport());
			simulation2Id.setCreditReport(total.getId().getCreditReport());
			simulation2Id.setCreditSolde(total.getId().getCreditSolde());
			simulation2Id.setDebitSolde(total.getId().getDebitSolde());
			simulation2Id.setIntitule(total.getId().getIntitule());
			simulation2Id.setRatio("Capacité d'auto financement");
			Date date = new Date();
			SimpleDateFormat dateFormatComp;
			dateFormatComp = new SimpleDateFormat("dd MMM yyyy hh:mm:ss a");
			simulation2Id.setDateAndHeure(dateFormatComp.format(date));
			Simulation2 simulation2 = new Simulation2();
			simulation2.setId(simulation2Id);
			sessionFactory.getCurrentSession().saveOrUpdate(simulation2);
		}

	}

	@Override
	public void saveSimulation3(List<Total> listTotal) {
		sessionFactory.getCurrentSession()
				.createSQLQuery("TRUNCATE TABLE simulation3").executeUpdate();
		for (Total total : listTotal) {
			Simulation3Id simulation3Id = new Simulation3Id();
			simulation3Id.setCompte(total.getId().getCompte());
			simulation3Id.setCreditMovement(total.getId().getCreditMovement());
			simulation3Id.setDebitMouvement(total.getId().getDebitMouvement());
			simulation3Id.setDebitReport(total.getId().getDebitReport());
			simulation3Id.setCreditReport(total.getId().getCreditReport());
			simulation3Id.setCreditSolde(total.getId().getCreditSolde());
			simulation3Id.setDebitSolde(total.getId().getDebitSolde());
			simulation3Id.setIntitule(total.getId().getIntitule());
			simulation3Id.setRatio("Capacité d'auto financement");
			Date date = new Date();
			SimpleDateFormat dateFormatComp;
			dateFormatComp = new SimpleDateFormat("dd MMM yyyy hh:mm:ss a");
			simulation3Id.setDateAndHeure(dateFormatComp.format(date));
			Simulation3 simulation3 = new Simulation3();
			simulation3.setId(simulation3Id);
			sessionFactory.getCurrentSession().saveOrUpdate(simulation3);
		}
	}

	@Override
	public List<Simulation1> findSimulation1() {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Simulation1.class);
		crit.setMaxResults(1);
		return crit.list();
	}

	@Override
	public List<Simulation2> findSimulation2() {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Simulation2.class);
		crit.setMaxResults(1);
		return crit.list();
	}

	@Override
	public List<Simulation3> findSimulation3() {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Simulation3.class);
		crit.setMaxResults(1);
		return crit.list();
	}

}
