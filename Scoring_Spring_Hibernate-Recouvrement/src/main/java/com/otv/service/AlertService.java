package com.otv.service;

import java.util.List;

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

public interface AlertService {
	
	List<Patient> findAll();
	List<Patient> findAllAEnvoyer();
	List<Patient> findByCriteria(String parameter);
	void saveUser(User user);
	void savePatient(Patient patient);
	void savePromesse(Promesse promesse);
	void savePromesseTenue(Promessetenue promesse);
	
	void saveIntervenant(Intervenant intervenant);
	
	void saveReglement(Reglement reglement);
	
	void saveIntervenantDossier(Intervenantdossier intervenantdossier);
	
	void saveProfilpayeur(Profilpayeur profilpayeur);
	
	List<Profilpayeur> findAllProfilpayeur();
	
	List<Intervenant> findAllIntervenant();
	
	List<Intervenantdossier> findAllIntervenantDossierByIdDossier(Integer idDossier);
	
	List<Intervenant> findIntervenantById(Integer identifiant);
	
	void saveActionClient(Actionclient actionclient);
	
	void saveActionDossierContentieux(Actiondossiercontentieux actiondossiercontentieux);
	
	void saveDossierContentieux(Dossiercontentieux dossiercontentieux);
	
	void saveHistoriqueAppelEntrant(Historiqueappelentrant historiqueappelentrant);
	void saveHistoriqueAppelSortant(Historiqueappelsortant historiqueappelsortant);
	void saveHistoriqueDossierConfie(Historiquedossierconfie historiquedossierconfie);
	void saveHistoriquePayment(Historiquepayement Historiquepayement);
	void saveClient(Client client);
	void deletePatient(Patient patient);
	boolean checkHabilitation(String login, String motDePasse);
	List<User> findUserByLogin(String login);
	List<Sms> findSmsByCriteria(String parameter);
	void saveSms(Sms sms);
	void saveDossierRecouvrement(Dossierrecouvrement dossierrecouvrement);
	void updateDossierRecouvrement(Dossierrecouvrement dossierrecouvrement);
	List<Dossierrecouvrement> findAllDossierrecouvrement();
	
	List<Dossiercontentieux> findAllDossiercontentieux();
	List<Dossiercontentieux> findDossiercontentieuxByCriteria(String parameter);
	
	List<Dossierrecouvrement> findDossierrecouvrementByCriteria(String parameter);
	List<Dossierrecouvrement> findDossierrecouvrementById(String id);
	List<Dossierrecouvrement> findDossierrecouvrementByAssigne(String parameter);
	List<Dossierrecouvrement> findDossierrecouvrementByAssigneArelancer(String parameter);
	List<Dossierrecouvrement> findDossierrecouvrementCloture();
	List<Dossierrecouvrement> findDossierrecouvrementArelancer(String parameter);
	List<Dossierrecouvrement> findDossierrecouvrementByAssigneAndByStatus(String identifiant, String parameter);
	List<Dossierrecouvrement> findDossierrecouvrementByGlobalCriteria(String parameter);
	
	List<Actionclient> findActionClientByCriteria(Integer identifiant);
	
	List<Actiondossiercontentieux> findActiondossiercontentieuxByCriteria(Integer identifiant);
	
	List<User> findAllUser();
	
	List<Historiqueappelentrant> findAppelEntrantDepuisDebutMois();
	List<Historiqueappelsortant> findAppelSortantDepuisDebutMois();
	List<Historiquedossierconfie> findDossierConfieDepuisDebutMois();
	List<Historiquepayement> findPaymentDepuisDebutMois();
	
	List<Historiqueappelentrant> findAppelEntrantDeJournee();
	List<Historiqueappelsortant> findAppelSortantDeJournee();
	List<Historiquedossierconfie> findDossierConfieDeJournee();
	List<Historiquepayement> findPaymentDeJournee();
	List<Historiquepayement> findAllPayement();
	
	List<Promesse> findPromesseByDate();
	
	List<Promessetenue> findPromesseTenueByDate();
	
	List<Promesse> findPromesseDepuisDebutMois();
	
	List<Promesse> findPromesseEnregistreMoisB();
	
	List<Promesse> findPromesseEnregistreMoisC();
	
	List<Promesse> findPromesseEnregistreMoisD();
	
	List<Promesse> findPromesseEnregistreMoisE();
	
	List<Promessetenue> findPromesseTenueDepuisDebutMois();
	
	List<Promesse> findPromesseEchu();
	
	List<Promesse> findPromesseDateDujour();
	
	List<Promessetenue> findPromesseTenueDateDujour();
	
	List<Promesse> findPromesseTenue();
	
	List<Client> findClientByCriteria(Integer identifiant);
} 
