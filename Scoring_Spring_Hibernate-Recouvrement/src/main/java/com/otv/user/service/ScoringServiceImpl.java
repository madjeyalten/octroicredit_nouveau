package com.otv.user.service;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.otv.model.mod.Client;
import com.otv.model.mod.Credit;
import com.otv.model.mod.Depensecourante;
import com.otv.model.mod.Historique;
import com.otv.model.mod.Revenucourant;
import com.otv.model.mod.Revenuexceptionnel;
import com.otv.model.mod.Scoreparametrage;
import com.otv.model.mod.Scoring;
import com.otv.model.mod.Seuilscore;
import com.otv.model.mod.User;

@Service("scoringService")
@Transactional
public class ScoringServiceImpl implements ScoringService{
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Scoring> findScoringByCriteria(String parameter) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Scoring.class);
		crit.add(Restrictions.like("identifiant", parameter));
		return crit.list();
	}

	@Override
	public List<Client> findClientByCriteria(String nom,Integer identifiant) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Client.class);
		if(identifiant!=null)
		{
		crit.add(Restrictions.eq("identifiant", identifiant));
		}
		if(nom!=null && !nom.isEmpty())
		{
		crit.add(Restrictions.like("nom", nom));
		}
		return crit.list();
	}

	@Override
	public void saveScoring(Scoring scoring) {
		sessionFactory.getCurrentSession().saveOrUpdate(scoring);	
	}

	@Override
	public void saveClient(Client client) {
		sessionFactory.getCurrentSession().saveOrUpdate(client);	
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
	public void saveScoringParametrage(Scoreparametrage Scoreparametrage) {
		sessionFactory.getCurrentSession().saveOrUpdate(Scoreparametrage);	
	}

	@Override
	public void saveCredit(Credit credit) {
		sessionFactory.getCurrentSession().saveOrUpdate(credit);	
	}

	@Override
	public List<Credit> findCreditByStatus(String status) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Credit.class);
		crit.add(Restrictions.like("statut", status));
		List<Credit> listCredit = crit.list();
		return listCredit;
	}

	@Override
	public List<User> findAllUser() {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				User.class);
		List<User> listUser = crit.list();
		return listUser;
	}

	
	@Override
	public List<User> findUserByName(String nom) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				User.class);
		crit.add(Restrictions.like("nom", nom));
		List<User> listUser = crit.list();
		return listUser;
	}

	@Override
	public List<Credit> findCreditByEnvoyerVers(String envoyer) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Credit.class);
		crit.add(Restrictions.like("envoyerVers", envoyer));
		List<Credit> listCredit = crit.list();
		return listCredit;
	}

	@Override
	public void saveHistorique(Historique historique) {
		sessionFactory.getCurrentSession().saveOrUpdate(historique);
	}

	@Override
	public List<Historique> findHistoriqueById(String identifier) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Historique.class);
		crit.add(Restrictions.eq("idCredit", identifier));
		List<Historique> listHistorique = crit.list();
		return listHistorique;
	}

	public void saveSeuilScore(Seuilscore seuilscore) {
		sessionFactory.getCurrentSession().saveOrUpdate(seuilscore);
		
	}

	public List<Seuilscore> findSeuilscore(Seuilscore Seuilscore) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Seuilscore.class);
		return crit.list();
	}

	public List<Scoreparametrage> findsaveScoringParametrage() {
		// TODO Auto-generated method stub
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Scoreparametrage.class);
		return crit.list();
	}


}
