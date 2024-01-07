package com.otv.service;

import hirondelle.date4j.DateTime;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.recouvrement.model.Actionclient;
import com.recouvrement.model.Actiondossiercontentieux;
import com.recouvrement.model.Client;
import com.recouvrement.model.Dossiercontentieux;
import com.recouvrement.model.Dossierrecouvrement;
import com.recouvrement.model.Historiqueappelentrant;
import com.recouvrement.model.Historiqueappelsortant;
import com.recouvrement.model.Historiquedossierconfie;
import com.recouvrement.model.Historiquepayement;
import com.recouvrement.model.Intervenant;
import com.recouvrement.model.Intervenantdossier;
import com.recouvrement.model.Patient;
import com.recouvrement.model.Profilpayeur;
import com.recouvrement.model.Promesse;
import com.recouvrement.model.Promessetenue;
import com.recouvrement.model.Reglement;
import com.recouvrement.model.Sms;
import com.recouvrement.model.User;

@Service("alertService")
@Transactional
public final class AlertServiceImpl implements AlertService {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Patient> findAll() {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Patient.class);
		crit.addOrder(Order.asc("identifant"));
		return crit.list();
	}

	@Override
	public List<Patient> findByCriteria(String parameter) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Patient.class);
		if(parameter!=null && !parameter.equals(""))
		{
		crit.add(Restrictions.like("nom", parameter));
		crit.add(Restrictions.like("statut", "a envoyer"));
		return crit.list();
		}
		else
		{
		crit.add(Restrictions.like("statut", "a envoyer"));
		return crit.list();
		}
	}

	@Override
	public void saveUser(User user) {
		sessionFactory.getCurrentSession().saveOrUpdate(user);
	}

	@Override
	public void savePatient(Patient patient) {
		sessionFactory.getCurrentSession().saveOrUpdate(patient);
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
	public void deletePatient(Patient patient) {
		sessionFactory.getCurrentSession().delete(patient);
	}

	@Override
	public List<Sms> findSmsByCriteria(String parameter) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Sms.class);
		crit.add(Restrictions.like("type", parameter));
		List<Sms> listSms = crit.list();
		return listSms;
	}

	@Override
	public void saveSms(Sms sms) {
		sessionFactory.getCurrentSession().saveOrUpdate(sms);
	}

	@Override
	public List<Patient> findAllAEnvoyer() {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Patient.class);
		crit.add(Restrictions.like("statut", "a envoyer"));
		return crit.list();
	}

	@Override
	public void saveDossierRecouvrement(Dossierrecouvrement dossierrecouvrement) {
		sessionFactory.getCurrentSession().saveOrUpdate(dossierrecouvrement);	
	}

	@Override
	public List<Dossierrecouvrement> findAllDossierrecouvrement() {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Dossierrecouvrement.class);
		return crit.list();
	}

	@Override
	public List<Dossierrecouvrement> findDossierrecouvrementByCriteria(
			String parameter) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Dossierrecouvrement.class);
		//Faire la recherche par statut uniquement si la liste déroulante n'est pas vide
		if(!parameter.equals("Vide"))
		{
		crit.add(Restrictions.like("statusTraitement", parameter));
		}
     return crit.list();
	}

	@Override
	public List<User> findAllUser() {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				User.class);
		List<User> listUser = crit.list();
		return listUser;
	}

	@Override
	public List<Dossierrecouvrement> findDossierrecouvrementByAssigne(
			String parameter) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Dossierrecouvrement.class);
		crit.add(Restrictions.like("assigne", parameter));
		return crit.list();
	}



	@Override
	public void savePromesse(Promesse promesse) {
		sessionFactory.getCurrentSession().saveOrUpdate(promesse);	
	}

	@Override
	public void saveClient(Client client) {
		sessionFactory.getCurrentSession().saveOrUpdate(client);	
	}

	@Override
	public List<Promesse> findPromesseByDate() {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Promesse.class);
		crit.add(Restrictions.like("statutPromesse", "new"));
		crit.add(Restrictions.le("nouvelleDatePromesse", new Date()));
		return crit.list();
	}



	@Override
	public List<Dossierrecouvrement> findDossierrecouvrementByAssigneAndByStatus(String parameter, String status) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Dossierrecouvrement.class);
		crit.add(Restrictions.like("assigne", parameter));
		if(status!=null && !status.equals("Vide"))
		{
		crit.add(Restrictions.like("statusTraitement", status));
		}
		return crit.list();
	}

	@Override
	public List<Client> findClientByCriteria(Integer identifiant) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Client.class);
		if(identifiant!=null)
		{
		crit.add(Restrictions.like("identifiant", identifiant));
		}
		return crit.list();
	}

	@Override
	public List<Dossierrecouvrement> findDossierrecouvrementByGlobalCriteria(
			String parameter) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Dossierrecouvrement.class);
//		if(org.apache.commons.lang.StringUtils.isEmpty(parameter)){
//			return crit.list();
//		}
		crit.add(Restrictions.like("identifiant", parameter));
		return crit.list();
	}

	@Override
	public void saveHistoriqueAppelEntrant(
			Historiqueappelentrant historiqueappelentrant) {
		sessionFactory.getCurrentSession().saveOrUpdate(historiqueappelentrant);
	}

	@Override
	public void saveHistoriqueAppelSortant(
			Historiqueappelsortant historiqueappelsortant) {
		sessionFactory.getCurrentSession().saveOrUpdate(historiqueappelsortant);
	}

	@Override
	public void saveHistoriqueDossierConfie(
			Historiquedossierconfie historiquedossierconfie) {
		sessionFactory.getCurrentSession().saveOrUpdate(historiquedossierconfie);
	}

	@Override
	public void saveHistoriquePayment(Historiquepayement historiquepayement) {
		sessionFactory.getCurrentSession().saveOrUpdate(historiquepayement);	
	}

	@Override
	public List<Historiqueappelentrant> findAppelEntrantDepuisDebutMois() {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Historiqueappelentrant.class);
		 Calendar c = Calendar.getInstance();   // this takes current date
		    c.set(Calendar.DAY_OF_MONTH, 1);   
		   Date firstOfMonth=c.getTime();
		crit.add(Restrictions.ge("dateAppel", firstOfMonth)); 
		crit.add(Restrictions.le("dateAppel", new Date()));
		return crit.list();
	}

	@Override
	public List<Historiqueappelsortant> findAppelSortantDepuisDebutMois() {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Historiqueappelsortant.class);
		 Calendar c = Calendar.getInstance();   // this takes current date
		    c.set(Calendar.DAY_OF_MONTH, 1);   
		   Date firstOfMonth=c.getTime();
		crit.add(Restrictions.ge("dateAppel", firstOfMonth)); 
		crit.add(Restrictions.le("dateAppel", new Date()));
		return crit.list();
	}

	@Override
	public List<Historiquedossierconfie> findDossierConfieDepuisDebutMois() {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Historiquedossierconfie.class);
		 Calendar c = Calendar.getInstance();   // this takes current date
		    c.set(Calendar.DAY_OF_MONTH, 1);   
		   Date firstOfMonth=c.getTime();
		crit.add(Restrictions.ge("dateDossierConfie", firstOfMonth)); 
		crit.add(Restrictions.le("dateDossierConfie", new Date()));
		return crit.list();
	}

	@Override
	public List<Historiquepayement> findPaymentDepuisDebutMois() {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Historiquepayement.class);
		 Calendar c = Calendar.getInstance();   // this takes current date
		    c.set(Calendar.DAY_OF_MONTH, 1);   
		   Date firstOfMonth=c.getTime();
		crit.add(Restrictions.ge("datePayement", firstOfMonth)); 
		crit.add(Restrictions.le("datePayement", new Date()));
		return crit.list();
	}

	@Override
	public List<Historiqueappelentrant> findAppelEntrantDeJournee() {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Historiqueappelentrant.class);
		crit.add(Restrictions.eq("dateAppel", new Date())); 
		return crit.list();
	}

	@Override
	public List<Historiqueappelsortant> findAppelSortantDeJournee() {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Historiqueappelsortant.class);
		crit.add(Restrictions.eq("dateAppel", new Date())); 
		return crit.list();
	}

	@Override
	public List<Historiquedossierconfie> findDossierConfieDeJournee() {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Historiquedossierconfie.class);
		crit.add(Restrictions.eq("dateDossierConfie", new Date())); 
		return crit.list();
	}

	@Override
	public List<Historiquepayement> findPaymentDeJournee() {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Historiquedossierconfie.class);
		crit.add(Restrictions.eq("datePayement", new Date())); 
		return crit.list();
	}

	@Override
	public List<Historiquepayement> findAllPayement() {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Historiquedossierconfie.class);
		return crit.list();
	}

	@Override
	public List<Promesse> findPromesseDepuisDebutMois() {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Promesse.class);
		 Calendar c = Calendar.getInstance();   // this takes current date
		    c.set(Calendar.DAY_OF_MONTH, 1);   
		   Date firstOfMonth=c.getTime();
		crit.add(Restrictions.ge("dateEnregistrement", firstOfMonth)); 
		crit.add(Restrictions.le("dateEnregistrement", new Date()));
		return crit.list();
	}

	@Override
	public List<Promesse> findPromesseEchu() {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Promesse.class);		
		 Calendar c = Calendar.getInstance();   // this takes current date
		    c.set(Calendar.DAY_OF_MONTH, 1);   
		   Date firstOfMonth=c.getTime();
		crit.add(Restrictions.ge("datePromesse", firstOfMonth)); 
		crit.add(Restrictions.le("datePromesse", new Date()));
		return crit.list();
	}

	@Override
	public List<Promesse> findPromesseDateDujour() {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Promesse.class);
		crit.add(Restrictions.eq("dateEnregistrement", new Date())); 
		return crit.list();
	}

	@Override
	public List<Promesse> findPromesseTenue() {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Promesse.class);
		 Calendar c = Calendar.getInstance();   // this takes current date
		    c.set(Calendar.DAY_OF_MONTH, 1);   
		   Date firstOfMonth=c.getTime();
		crit.add(Restrictions.ge("datePromesse", firstOfMonth)); 
		crit.add(Restrictions.le("datePromesse", new Date()));
		crit.add(Restrictions.eq("statutPromesse", "TENUE"));
		return crit.list();
	}

	@Override
	public void savePromesseTenue(Promessetenue promessetenue) {
		sessionFactory.getCurrentSession().saveOrUpdate(promessetenue);	
		
	}

	@Override
	public List<Promessetenue> findPromesseTenueByDate() {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Promessetenue.class);
		crit.add(Restrictions.like("statutPromesse", "new"));
		crit.add(Restrictions.le("nouvelleDatePromesse", new Date()));
		return crit.list();
	}

	@Override
	public List<Promessetenue> findPromesseTenueDepuisDebutMois() {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Promessetenue.class);
		 Calendar c = Calendar.getInstance();   // this takes current date
		    c.set(Calendar.DAY_OF_MONTH, 1);   
		   Date firstOfMonth=c.getTime();
		crit.add(Restrictions.ge("dateEnregistrement", firstOfMonth)); 
		crit.add(Restrictions.le("dateEnregistrement", new Date()));
		return crit.list();
	}

	@Override
	public List<Promessetenue> findPromesseTenueDateDujour() {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Promessetenue.class);
		crit.add(Restrictions.eq("dateEnregistrement", new Date())); 
		return crit.list();
	}

	@Override
	public List<Dossierrecouvrement> findDossierrecouvrementByAssigneArelancer(
			String parameter) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Dossierrecouvrement.class);
		crit.add(Restrictions.like("assigne", parameter));
		DateTime today = DateTime.today(TimeZone.getDefault());
		crit.add(Restrictions.le("datePromesse", new Date())); 
		crit.add(Restrictions.eq("statusPromesse", "en cours"));
		return crit.list();
	}

	@Override
	public List<Dossierrecouvrement> findDossierrecouvrementArelancer(
			String parameter) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Dossierrecouvrement.class);
		DateTime today = DateTime.today(TimeZone.getDefault());
		crit.add(Restrictions.like("assigne", parameter));
		crit.add(Restrictions.le("datePromesse", new Date())); 
		crit.add(Restrictions.eq("statusPromesse", "en cours"));
		return crit.list();
	}

	@Override
	public void saveActionClient(Actionclient actionclient) {
		sessionFactory.getCurrentSession().saveOrUpdate(actionclient);
	}

	@Override
	public List<Actionclient> findActionClientByCriteria(Integer identifiant) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Actionclient.class);
		if(identifiant!=null)
		{
		crit.add(Restrictions.like("nbreDossier", identifiant));
		}
		return crit.list();
	}

	@Override
	public void updateDossierRecouvrement(
			Dossierrecouvrement dossierrecouvrement) {
		sessionFactory.getCurrentSession().update(dossierrecouvrement);	
	}

	@Override
	public void saveDossierContentieux(
			Dossiercontentieux dossiercontentieux) {
		sessionFactory.getCurrentSession().saveOrUpdate(dossiercontentieux);	
	}

	@Override
	public List<Dossiercontentieux> findAllDossiercontentieux() {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Dossiercontentieux.class);
		return crit.list();
	}

	@Override
	public List<Dossiercontentieux> findDossiercontentieuxByCriteria(
			String parameter) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Dossiercontentieux.class);
		//Faire la recherche par statut uniquement si la liste déroulante n'est pas vide
		if(!parameter.equals("Vide"))
		{
		crit.add(Restrictions.like("status", parameter));
		}
     return crit.list();
	}

	@Override
	public List<Actiondossiercontentieux> findActiondossiercontentieuxByCriteria(
			Integer identifiant) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Actiondossiercontentieux.class);
		return crit.createAlias("dossiercontentieux", "dossiercontentieux")
		.add(Restrictions.eq("dossiercontentieux.identifiant", identifiant))
		.list();
	}

	@Override
	public void saveIntervenant(Intervenant intervenant) {
		sessionFactory.getCurrentSession().saveOrUpdate(intervenant);		
	}

	@Override
	public void saveIntervenantDossier(Intervenantdossier intervenantdossier) {
		sessionFactory.getCurrentSession().saveOrUpdate(intervenantdossier);	
	}

	@Override
	public void saveProfilpayeur(Profilpayeur profilpayeur) {
		sessionFactory.getCurrentSession().saveOrUpdate(profilpayeur);	
	}

	@Override
	public List<Profilpayeur> findAllProfilpayeur() {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Profilpayeur.class);
		return crit.list();
	}

	@Override
	public List<Intervenant> findAllIntervenant() {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Intervenant.class);
		return crit.list();
	}

	@Override
	public List<Intervenantdossier> findAllIntervenantDossierByIdDossier(
			Integer idDossier) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Intervenantdossier.class);
		crit.add(Restrictions.like("idIntervenant", idDossier));
		return crit.list();
	}

	@Override
	public void saveActionDossierContentieux(
			Actiondossiercontentieux actiondossiercontentieux) {
		sessionFactory.getCurrentSession().saveOrUpdate(actiondossiercontentieux);	
	}

	@Override
	public void saveReglement(Reglement reglement) {
		sessionFactory.getCurrentSession().saveOrUpdate(reglement);			
	}

	@Override
	public List<Dossierrecouvrement> findDossierrecouvrementById(String id) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Dossierrecouvrement.class);
		crit.add(Restrictions.eq("identifiant", id));
     return crit.list();
	}

	@Override
	public List<Intervenant> findIntervenantById(Integer identifiant) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Intervenant.class);
		crit.add(Restrictions.eq("identifiant", identifiant));
     return crit.list();
	}

	@Override
	public List<Dossierrecouvrement> findDossierrecouvrementCloture() {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Dossierrecouvrement.class);
		crit.add(Restrictions.eq("statusTraitement", "cloture"));
		return crit.list();
	}

	@Override
	public List<Promesse> findPromesseEnregistreMoisB() {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Promesse.class);
		 Calendar c = Calendar.getInstance();   // this takes current date
		    c.set(Calendar.DAY_OF_MONTH, -1);   
		   Date firstOfMonth=c.getTime();
		   
		     Calendar calendarLast = Calendar.getInstance();  
		     calendarLast.setTime(firstOfMonth);  
		     calendarLast.add(Calendar.MONTH, 1);  
		     calendarLast.set(Calendar.DAY_OF_MONTH, 1);  
		     calendarLast.add(Calendar.DATE, -1); 
	        
	        
		crit.add(Restrictions.ge("dateEnregistrement", firstOfMonth)); 
		crit.add(Restrictions.le("dateEnregistrement", calendarLast.getTime()));
		return crit.list();
	}

	@Override
	public List<Promesse> findPromesseEnregistreMoisC() {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Promesse.class);
		 Calendar c = Calendar.getInstance();   // this takes current date
		    c.set(Calendar.DAY_OF_MONTH, -2);   
		   Date firstOfMonth=c.getTime();
		   
		   Calendar calendarLast = Calendar.getInstance();  
		     calendarLast.setTime(firstOfMonth);  
		     calendarLast.add(Calendar.MONTH, 1);  
		     calendarLast.set(Calendar.DAY_OF_MONTH, 1);  
		     calendarLast.add(Calendar.DATE, -1); 
		     
		crit.add(Restrictions.ge("dateEnregistrement", firstOfMonth)); 
		crit.add(Restrictions.le("dateEnregistrement", calendarLast.getTime()));
		return crit.list();
	}

	@Override
	public List<Promesse> findPromesseEnregistreMoisD() {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Promesse.class);
		 Calendar c = Calendar.getInstance();   // this takes current date
		    c.set(Calendar.DAY_OF_MONTH, -3);   
		   Date firstOfMonth=c.getTime();
		   
		   Calendar calendarLast = Calendar.getInstance();  
		     calendarLast.setTime(firstOfMonth);  
		     calendarLast.add(Calendar.MONTH, 1);  
		     calendarLast.set(Calendar.DAY_OF_MONTH, 1);  
		     calendarLast.add(Calendar.DATE, -1); 
		     
		     
		crit.add(Restrictions.ge("dateEnregistrement", firstOfMonth)); 
		crit.add(Restrictions.le("dateEnregistrement", calendarLast.getTime()));
		return crit.list();
	}

	@Override
	public List<Promesse> findPromesseEnregistreMoisE() {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Promesse.class);
		 Calendar c = Calendar.getInstance();   // this takes current date
		    c.set(Calendar.DAY_OF_MONTH, -4);   
		   Date firstOfMonth=c.getTime();
		   
		   Calendar calendarLast = Calendar.getInstance();  
		     calendarLast.setTime(firstOfMonth);  
		     calendarLast.add(Calendar.MONTH, 1);  
		     calendarLast.set(Calendar.DAY_OF_MONTH, 1);  
		     calendarLast.add(Calendar.DATE, -1); 
		     
		crit.add(Restrictions.ge("dateEnregistrement", firstOfMonth)); 
		crit.add(Restrictions.le("dateEnregistrement", calendarLast.getTime()));
		return crit.list();
	}

}
