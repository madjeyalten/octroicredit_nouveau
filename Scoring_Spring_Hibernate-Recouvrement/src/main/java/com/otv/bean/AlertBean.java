package com.otv.bean;

import java.awt.Color;
import java.awt.Desktop;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.security.cert.X509Certificate;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.application.ViewHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
//import javax.servlet.http.HttpServletRequest;

import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.Friend;
import facebook4j.Post;
import facebook4j.ResponseList;
import facebook4j.conf.Configuration;
import facebook4j.conf.ConfigurationBuilder;


import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.otv.model.mod.User;
import com.otv.service.AlertService;
import com.otv.user.service.ScoringService;
import com.recouvrement.model.*;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.HorizontalBarChartModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import speech.CallUtils;
import speech.SmsUtils;

@ManagedBean(name = "alertBean")
@Component("alertBean")
@SessionScoped
public class AlertBean implements Serializable {

	private static final String TRANSFÉRÉ = "transféré";
	private static final String NON_CONTACTABLE = "non contactable";
	private static final String À_TRANSFÉRER = "à transférer";
	private static final String CLOTURE = "cloture";
	private static final String À_TRAITER = "à traiter";
	private String login;
	private String mdpasse;
	private String loginAndPassword;
	private String nom;
	private String prenom;
	private String telephone;
	private String email;
	private Patient currentPatient;
	private Patient selectedPatient;
	private List<Patient> selectedPatients;
	private List<Dossierrecouvrement> selectedDossiers;
	private Dossierrecouvrement selectedTransferedDossiers;
	private List<Dossierrecouvrement> selectedTransferedDossiersList;
	private List<Dossierrecouvrement> listDossier;
	private List<Dossierrecouvrement> listMesTaches;
	private List<Dossierrecouvrement> listTachesAtransferer;
	
	private List<Dossierrecouvrement> listDossierNonContactable;
	
	private List<Dossierrecouvrement> listDossierCloture;
	
	//tâches dont la date d'échéance est proche
	private List<Dossierrecouvrement> listMesTachesArelancer;
	private List<Dossierrecouvrement> listAllTachesArelancer;
	private int compteur = 0;
	private User autocompleteUser;
	private Dossierrecouvrement selectedDossier;
	private List<Client> selectedClient;
	private Client currentClient;
	private Client currentTransferedClient;

	// promesse
	private String statutPromesse;
	private String statutTraitement;
	private Date datePromesse;
	private Date dateRelance;
	private String commentairePromesse;
	private List<Promessetenue> listPromessetenueJournee;
	private List<Promessetenue> listPromessetenueDepuisDebutDuMois;
	
	private List<Promesse> listPromesseEnregistreJournee;
	private List<Promesse> listPromesseEnregistreDepuisDebutDuMois;
	
	//Appels
	private List<Historiqueappelsortant> listHistoriqueappelsortant;
	
	//Liste historique action
	private List<Actionclient> listActionclient;
	

	List<Patient> listPatient = new ArrayList<Patient>();

	// search
	private String searchNom;

	// sms
	private String smsType;
	private String statutType;
	private String statutMestachesType;
	private String smsCorps;

	// montant
	public static Long staticAmount;
	
	//client à alerter
	private String clientToAlerte;
	private Integer numeroDossierClientToAlerte;
	
	Integer montantPromesse;
	
	private String nbreDossierArelancer;
	
	private String nbreAllDossierArelancer;
	
	private String nbreDossiernonContactable;
	
	//Statistique
	private String nbrePromesseEnregistreJournee="0";
	private String nbrePromesseEnregistreDepuisDebutMois="0";
	private String nbrePromesseTenueJournee="0";
	private String nbrePromesseTenueDepuisDebutMois="0";
	private String nbrePromesseActive;
	private String nbrePromesseEchu;
	private String nbrePromesseTenu;
	private String nbreJourTravaille;
	private String nbreAgent;
	private String nbreAppelEntrant;
	private String nbreAppelSortant;
	private String montantTotalRecu;
	
	//les statistiques
	private String nbrePromesseTenueMoisC;
	private String nbrePromesseTenueMoisB;
	private String nbrePromesseTenueMoisD;
	private String nbrePromesseTenueMoisE;
	
	private String nbrePromesseEnregistreMoisC;
	private String nbrePromesseEnregistreMoisB;
	private String nbrePromesseEnregistreMoisD;
	private String nbrePromesseEnregistreMoisE;
	
	//données clients :
	private String telephoneDomicile;
	private String telephonePortable;
	
	private boolean boutonTransferedVisible;
	
	//relance
	private String typeRelance;
	private String typeDossier;
	private String periodicite;
	
	//profil payeur
	private int profilemauvaispayeur;
	private int profiletressmauvaispayeur;
	private int profilebonpayeur;
	private int profiletresbonpayeur;
	
	//intervenant
	private String nomIntervenant;
	private String typeIntervenant;
	private String telephoneIntervenant;
	private String emailIntervenant;
	private String adresseIntervenant;
	
	//reglèment
	private String modeReglement;
	private String typeReglement;

	private String deviseReglement;
	private int montantReglement;
	private String commentaireReglement;
	
	
	private String nbreMesTaches;
	private String nbreTransfertDossier;
	
	//bar model (pour historique des promesses)
	 private BarChartModel barModel;
	 private HorizontalBarChartModel horizontalBarModel;
	 
	 //Montants totaux
	 int montantTotalEncours;
	 
	 int montantTotalRetard;
	
	//global Filter
	private String globalFilter;
	
	List<RelanceBean> listRelance=new ArrayList<RelanceBean>();
	
	List<RelanceBean> listRelancePromesse=new ArrayList<RelanceBean>();
	
	List<RelanceBean> listRelanceBonPayeur=new ArrayList<RelanceBean>();
	
	List<RelanceBean> listRelanceMauvaisPayeur=new ArrayList<RelanceBean>();
	
	//Dossiers contentieux
	private List<Dossiercontentieux> listDossiercontentieux=new ArrayList<Dossiercontentieux>();
	
	private List<Intervenant> listIntervenant=new ArrayList<Intervenant>();
	
	private String intervenantSelected;
	
	private String modeReglementSelected;
	
	private String typeIntervenantSelected;
	
	private String typeReglementSelected;
	
	private String selectedDossierAsString;
	
	private String selectedDossierContentieuxAsString;
	
	Dossiercontentieux dossiercontentieuxSelected;
	
	//intervenant
	private List<String> listIntervenantAsString=new ArrayList<String>();
	
	private List<Intervenantdossier> listIntervenantByDossier=new ArrayList<Intervenantdossier>();
	
	private List<IntervenantDossierBean> listIntervenantDossierBean=new ArrayList<IntervenantDossierBean>();
	
	
	Intervenant selectedIntervenant;
	
	@Autowired
	private transient ScoringService scoringService;
	

	@Autowired
	private transient AlertService alertService;
	
	 @ManagedProperty(value = "#{param.id}")
	  private String id;

	@PostConstruct
	public void init() {
		
		//contrôle de licence
		Date expiredDate=null;
		try {
			expiredDate = new SimpleDateFormat("dd/MM/yyyy").parse("14/09/2028");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(new Date().after(expiredDate)) {
			return;
		}
		//liste des dossiers cloturés
		listDossierCloture=alertService.findDossierrecouvrementCloture();
			
		initListRelance();
		initListRelancePromesse();
		initListRelanceBonpayeur();
		initListRelanceMauvaispayeur();
		createBarModels();
		listDossiercontentieux=alertService.findAllDossiercontentieux();
		listIntervenant=alertService.findAllIntervenant();
		listIntervenantAsString.clear();
		for (Intervenant intervenant : listIntervenant) {
			listIntervenantAsString.add(intervenant.getTypeintervenant()!=null? intervenant.getLibelle()+" "+intervenant.getTypeintervenant() : intervenant.getLibelle());	
		}
		listPatient = alertService.findAllAEnvoyer();
		listDossier = alertService.findAllDossierrecouvrement();
		for (Dossierrecouvrement dossierrecouvrement : listDossier) {
			montantTotalEncours=montantTotalEncours+dossierrecouvrement.getMontantEncours().intValue();
			montantTotalRetard=montantTotalRetard+dossierrecouvrement.getMontantRetard().intValue();
		}
		listMesTaches = alertService
				.findDossierrecouvrementByAssigne(getUserName());
		if(listMesTaches!=null && !listMesTaches.isEmpty()){
			nbreMesTaches=listMesTaches.size()+"";
		}
			
		
		listTachesAtransferer=alertService.findDossierrecouvrementByCriteria(À_TRANSFÉRER);
		
		if(listTachesAtransferer!=null && !listTachesAtransferer.isEmpty()){
			nbreTransfertDossier=listTachesAtransferer.size()+"";
		}
		
		listDossierNonContactable=alertService.findDossierrecouvrementByCriteria(NON_CONTACTABLE);
		listMesTachesArelancer = alertService
		.findDossierrecouvrementByAssigneArelancer(getUserName());
		if(listMesTachesArelancer!=null && !listMesTachesArelancer.isEmpty())
		{
		nbreDossierArelancer=listMesTachesArelancer.size()+"";
		}
		else
		{
		nbreDossierArelancer="";
		}
		
		//tous les dossiers à relancer
        listAllTachesArelancer = alertService.findDossierrecouvrementArelancer(getUserName());
        if(listAllTachesArelancer!=null && !listAllTachesArelancer.isEmpty())
        {
         nbreAllDossierArelancer=listAllTachesArelancer.size()+"";
        }
        else
       {
        	nbreAllDossierArelancer="0";
        }
		
		
		//Les promesses
		listPromessetenueDepuisDebutDuMois=alertService.findPromesseTenueDepuisDebutMois();
		listPromessetenueJournee=alertService.findPromesseTenueDateDujour();
		listPromesseEnregistreDepuisDebutDuMois=alertService.findPromesseDepuisDebutMois();
		listPromesseEnregistreJournee=alertService.findPromesseDateDujour();
		if(listPromessetenueDepuisDebutDuMois!=null && !listPromessetenueDepuisDebutDuMois.isEmpty())
		{
		nbrePromesseTenueDepuisDebutMois=listPromessetenueDepuisDebutDuMois.size()+"";	
		}
		if(listPromessetenueJournee!=null && !listPromessetenueJournee.isEmpty())
		{
		nbrePromesseTenueJournee=listPromessetenueJournee.size()+"";	
		}
		
		if(listPromesseEnregistreDepuisDebutDuMois!=null && !listPromesseEnregistreDepuisDebutDuMois.isEmpty())
		{
		nbrePromesseEnregistreDepuisDebutMois=listPromesseEnregistreDepuisDebutDuMois.size()+"";	
		}
		if(listPromesseEnregistreJournee!=null && !listPromesseEnregistreJournee.isEmpty())
		{
		nbrePromesseEnregistreJournee=listPromesseEnregistreJournee.size()+"";
		}
		
		//Appel sortants
		listHistoriqueappelsortant=alertService.findAppelSortantDepuisDebutMois();
		if(listHistoriqueappelsortant!=null && !listHistoriqueappelsortant.isEmpty())
		{
		nbreAppelSortant=listHistoriqueappelsortant.size()+"";
		}
		else
		{
		nbreAppelSortant="0";	
		}
	}
	
	private void initListRelance() {
		listRelance.clear();
		RelanceBean relanceBean1=new RelanceBean();
		relanceBean1.setCodeRelance("Relance téléphonique");
		relanceBean1.setLibelleRelance("+2 jours");
		relanceBean1.setSymboleRelance("Tel");
		listRelance.add(relanceBean1);
		
		RelanceBean relanceBean2=new RelanceBean();
		relanceBean2.setCodeRelance("Relance par email");
		relanceBean2.setLibelleRelance("+4 jours");
		relanceBean2.setSymboleRelance("@");
		listRelance.add(relanceBean2);
		
		RelanceBean relanceBean3=new RelanceBean();
		relanceBean3.setCodeRelance("Courrier de mise en demeure");
		relanceBean3.setLibelleRelance("+15 jours");
		relanceBean3.setSymboleRelance("@");
		listRelance.add(relanceBean3);
		
		RelanceBean relanceBean4=new RelanceBean();
		relanceBean4.setCodeRelance("Contentieux");
		relanceBean4.setLibelleRelance("+30 jours");
		relanceBean4.setSymboleRelance("@");
		listRelance.add(relanceBean4);
	}
	
	private void initListRelancePromesse() {
		listRelancePromesse.clear();
		RelanceBean relanceBean1=new RelanceBean();
		relanceBean1.setCodeRelance("Relance téléphonique");
		relanceBean1.setLibelleRelance("-2 jours");
		relanceBean1.setSymboleRelance("Tel");
		listRelancePromesse.add(relanceBean1);
		
		RelanceBean relanceBean2=new RelanceBean();
		relanceBean2.setCodeRelance("Relance par email");
		relanceBean2.setLibelleRelance("+4 jours");
		relanceBean2.setSymboleRelance("@");
		listRelancePromesse.add(relanceBean2);
		
		RelanceBean relanceBean3=new RelanceBean();
		relanceBean3.setCodeRelance("Courrier de mise en demeure");
		relanceBean3.setLibelleRelance("+10 jours");
		relanceBean3.setSymboleRelance("@");
		listRelancePromesse.add(relanceBean3);
		
		RelanceBean relanceBean4=new RelanceBean();
		relanceBean4.setCodeRelance("Contentieux");
		relanceBean4.setLibelleRelance("+90 jours");
		relanceBean4.setSymboleRelance("@");
		listRelancePromesse.add(relanceBean4);
	}
	
	private void initListRelanceBonpayeur() {
		listRelanceBonPayeur.clear();
		RelanceBean relanceBean1=new RelanceBean();
		relanceBean1.setCodeRelance("Relance téléphonique");
		relanceBean1.setLibelleRelance("+2 jours");
		relanceBean1.setSymboleRelance("Tel");
		listRelanceBonPayeur.add(relanceBean1);
		
		RelanceBean relanceBean2=new RelanceBean();
		relanceBean2.setCodeRelance("Relance par email");
		relanceBean2.setLibelleRelance("+10 jours");
		relanceBean2.setSymboleRelance("@");
		listRelanceBonPayeur.add(relanceBean2);
		
		RelanceBean relanceBean3=new RelanceBean();
		relanceBean3.setCodeRelance("Courrier de mise en demeure");
		relanceBean3.setLibelleRelance("+30 jours");
		relanceBean3.setSymboleRelance("@");
		listRelanceBonPayeur.add(relanceBean3);
		
		RelanceBean relanceBean4=new RelanceBean();
		relanceBean4.setCodeRelance("Contentieux");
		relanceBean4.setLibelleRelance("+90 jours");
		relanceBean4.setSymboleRelance("@");
		listRelanceBonPayeur.add(relanceBean4);
	}
	
	private void initListRelanceMauvaispayeur() {
		listRelanceMauvaisPayeur.clear();
		RelanceBean relanceBean1=new RelanceBean();
		relanceBean1.setCodeRelance("Relance téléphonique");
		relanceBean1.setLibelleRelance("+1 jours");
		relanceBean1.setSymboleRelance("Tel");
		listRelanceMauvaisPayeur.add(relanceBean1);
		
		RelanceBean relanceBean2=new RelanceBean();
		relanceBean2.setCodeRelance("Relance par email");
		relanceBean2.setLibelleRelance("+2 jours");
		relanceBean2.setSymboleRelance("@");
		listRelanceMauvaisPayeur.add(relanceBean2);
		
		RelanceBean relanceBean3=new RelanceBean();
		relanceBean3.setCodeRelance("Courrier de mise en demeure");
		relanceBean3.setLibelleRelance("+10 jours");
		relanceBean3.setSymboleRelance("@");
		listRelanceMauvaisPayeur.add(relanceBean3);
		
		RelanceBean relanceBean4=new RelanceBean();
		relanceBean4.setCodeRelance("Contentieux");
		relanceBean4.setLibelleRelance("+20 jours");
		relanceBean4.setSymboleRelance("@");
		listRelanceMauvaisPayeur.add(relanceBean4);
	}


	public void onload()
	{
		Date expiredDate=null;
		try {
			expiredDate = new SimpleDateFormat("dd/MM/yyyy").parse("14/09/2028");
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if(new Date().after(expiredDate)) {
			try {
				FacesContext.getCurrentInstance().getExternalContext()
				.redirect("index.xhtml");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	System.out.println(listDossiercontentieux);	
	selectedDossierContentieuxAsString="";
	}
	
	public String returnNbreAppelSortant()
	{
		listHistoriqueappelsortant=alertService.findAppelSortantDepuisDebutMois();
		if(listHistoriqueappelsortant!=null && !listHistoriqueappelsortant.isEmpty())
		{
		nbreAppelSortant=listHistoriqueappelsortant.size()+"";
		}
		else
		{
		nbreAppelSortant="0";	
		}
	return nbreAppelSortant;	
	}
	
	public String returnNbrePromesseEnregistreJournee()
	{
	listPromesseEnregistreJournee=alertService.findPromesseDateDujour();
	if(listPromesseEnregistreJournee!=null && !listPromesseEnregistreJournee.isEmpty())
	{
	nbrePromesseEnregistreJournee=listPromesseEnregistreJournee.size()+"";
	}
	return nbrePromesseEnregistreJournee;	
	}
	
	public String returnNbrePromesseEnregistreDepuisDebutMois()
	{
	    listPromesseEnregistreDepuisDebutDuMois=alertService.findPromesseDepuisDebutMois();
		if(listPromesseEnregistreDepuisDebutDuMois!=null && !listPromesseEnregistreDepuisDebutDuMois.isEmpty())
	{
	nbrePromesseEnregistreDepuisDebutMois=listPromesseEnregistreDepuisDebutDuMois.size()+"";	
	}
	return nbrePromesseEnregistreDepuisDebutMois;	
	}
	
	public String returnNbrePromesseTenueJournee()
	{
	listPromessetenueJournee=alertService.findPromesseTenueDateDujour();
    if(listPromessetenueJournee!=null && !listPromessetenueJournee.isEmpty())
	{
	nbrePromesseTenueJournee=listPromessetenueJournee.size()+"";	
	}
	return nbrePromesseTenueJournee;	
	}
	
	public String returnNbrePromesseTenueDepuisDebutMois()
	{
		listPromessetenueJournee=alertService.findPromesseTenueDateDujour();
		if(listPromessetenueDepuisDebutDuMois!=null && !listPromessetenueDepuisDebutDuMois.isEmpty())
		{
		nbrePromesseTenueDepuisDebutMois=listPromessetenueDepuisDebutDuMois.size()+"";	
		}
	return nbrePromesseTenueDepuisDebutMois;	
	}
	
	public String returnNbreDossierArelancer()
	{
        listAllTachesArelancer = alertService.findDossierrecouvrementArelancer(getUserName());
        if(listAllTachesArelancer!=null && !listAllTachesArelancer.isEmpty())
        {
         nbreAllDossierArelancer=listAllTachesArelancer.size()+"";
        }
        else
       {
        	nbreAllDossierArelancer="0";
        }
	return nbreAllDossierArelancer;	
	}
	
	public String returnNbreDossierNonContactable()
	{
		listDossierNonContactable=alertService.findDossierrecouvrementByCriteria(NON_CONTACTABLE);
        if(listDossierNonContactable!=null && !listDossierNonContactable.isEmpty())
        {
         nbreDossiernonContactable=listDossierNonContactable.size()+"";
        }
        else
       {
        	nbreDossiernonContactable="0";
        }
	return nbreDossiernonContactable;	
	}
	
	
	public int returnMontantEncaisseJournee()
	{
	int montantEncaisse=0;
	for (Promessetenue promessetenue : listPromessetenueJournee) {
		if(promessetenue.getMontantPromesse()!=null)
		{
		montantEncaisse=montantEncaisse+promessetenue.getMontantPromesse();
		}
	}
	return montantEncaisse;	
	}
	
	public int returnMontantEncaisseMois()
	{
		int montantEncaisse=0;
		for (Promessetenue promessetenue : listPromessetenueDepuisDebutDuMois) {
			if(promessetenue.getMontantPromesse()!=null)
			{
			montantEncaisse=montantEncaisse+promessetenue.getMontantPromesse();
			}
		}
		return montantEncaisse;	
		}
	

	public String deconnect() throws IOException {
		FacesContext.getCurrentInstance().getExternalContext()
				.invalidateSession();
		listPatient = null;
		login = null;
		mdpasse = null;
		loginAndPassword = null;
		nom = null;
		prenom = null;
		telephone = null;
		email = null;
		currentPatient = null;
		selectedPatient = null;
		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("index.xhtml");
		return "deconnexion";
	}

	public void createAbonne() throws IOException {
		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("newContact.xhtml");
	}

	public void savePromesse() throws IOException {

		//Mise à jour historique promesse tenue
		Promessetenue promessetenue=new Promessetenue();
		promessetenue.setDateEnregistrement(new Date());
		promessetenue.setMontantPromesse(selectedDossier.getMontantPromesse());
		promessetenue.setStatutPromesse(selectedDossier.getStatusPromesse());
		promessetenue.setIdDossier(selectedDossier.getIdentifiant());
		promessetenue.setIdClient(currentClient.getIdentifiant()+"");
		alertService.savePromesseTenue(promessetenue);
			
		//Mise à jour du statut de la promesse dans le dossier recouvrement
		
		//Gestion des statuts
		alertService.saveDossierRecouvrement(selectedDossier);
		
		//Mise à jour action client
		if(selectedDossier.getStatusPromesse()!=null && selectedDossier.getStatusPromesse().startsWith("Tenue"))
		{
		saveActionPromesseTenue(selectedDossier.getIdentifiant());	
		}
		
		
		//Appel de la fonction init
		init();
		
		FacesContext.getCurrentInstance().addMessage("ficheClientForm", new FacesMessage(FacesMessage.SEVERITY_INFO,"Dossier :", "Le dossier client a été mis à jour"));
		FacesContext context = FacesContext.getCurrentInstance();
//		context.getExternalContext().getFlash().setKeepMessages(true);
//		FacesContext.getCurrentInstance().getExternalContext()
//				.redirect("ficheClient.xhtml");
	}
	
	public void saveRelance() throws IOException {
		FacesContext.getCurrentInstance().addMessage("ficheClientForm", new FacesMessage(FacesMessage.SEVERITY_INFO,"Relance :", "Votre relance a été prise en compte"));
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getFlash().setKeepMessages(true);
		resetRelance();
		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("mesDossier.xhtml");	
	}
	
	
	public void saveIntervenant() throws IOException {
		Intervenant intervenant=new Intervenant();
		intervenant.setLibelle(nomIntervenant);
		intervenant.setEmailintervenant(emailIntervenant);
		intervenant.setAdresseintervenant(adresseIntervenant);
		intervenant.setTelephoneintervenant(telephoneIntervenant);
		intervenant.setTypeintervenant(intervenantSelected);
		alertService.saveIntervenant(intervenant);
		//Recharger les intervenants
		listIntervenant=alertService.findAllIntervenant();
		listIntervenantAsString.clear();
		for (Intervenant currentIntervenant : listIntervenant) {
			listIntervenantAsString.add(currentIntervenant.getTypeintervenant()!=null? currentIntervenant.getLibelle()+" "+currentIntervenant.getTypeintervenant() : currentIntervenant.getLibelle());	
		}
		//reset intervenant
		resetIntervenant();
		FacesContext.getCurrentInstance().addMessage("ficheClientForm", new FacesMessage(FacesMessage.SEVERITY_INFO,"Intervenant :", "L'intervenant a bien été ajoutée"));
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getFlash().setKeepMessages(true);
		FacesContext.getCurrentInstance().getExternalContext()
		.redirect("ajouterIntervenant.xhtml");	
	}
	
	public void saveReglement() throws IOException {
		Reglement reglement=new Reglement();
		reglement.setMontantreglement(montantReglement);
		reglement.setModereglement(modeReglement);
		reglement.setDevisereglement(deviseReglement);
		reglement.setTypereglement(typeReglement);
		reglement.setCommentaire(commentaireReglement);
		alertService.saveReglement(reglement);
	}
	

	private void resetIntervenant() {
		nomIntervenant=null;
		emailIntervenant=null;
		adresseIntervenant=null;
		telephoneIntervenant=null;
		intervenantSelected=null;
	}

	public void abandonnerRelance() throws IOException {
		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("mesDossier.xhtml");	
	}
	
	public void resetRelance()
	{
	typeDossier="";
	typeRelance="";
	periodicite="";
	}
	public void saveDossierRecouvrement()
	{
		alertService.saveDossierRecouvrement(selectedDossier);
	}
	
	public void abandonnerPromesse() throws IOException {
		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("ficheClient.xhtml");
	}
	
	public void nouvellePromesse() throws IOException {
		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("nouvellepromesse.xhtml");
	}
	
	public void saveNouvellePromesse() throws IOException {
		Promesse promesse = new Promesse();
		promesse.setDateEnregistrement(new Date());
		promesse.setDatePromesse(datePromesse);
		promesse.setNouvelleDatePromesse(dateRelance);
		promesse.setStatutPromesse("new");
		promesse.setCommentaire(commentairePromesse);
		alertService.savePromesse(promesse);
		
		//Mise à jour dossier recouvrement avec les informations de la promesse
		selectedDossier.setStatusPromesse("en cours");
		selectedDossier.setDatePromesse(datePromesse);
		selectedDossier.setDateReview(dateRelance);
		selectedDossier.setMontantPromesse(montantPromesse);
		selectedDossier.setCommentairePromesse(commentairePromesse);
		alertService.saveDossierRecouvrement(selectedDossier);
		
		//Tracer les actions 
		saveActionNouvellePromesse(selectedDossier.getIdentifiant());
		
		//Mise à jour du pavé promesse
		//Appel de la fonction init pour recharger les données
		init();
		resetPromesse();
		
		FacesContext.getCurrentInstance().addMessage("formprincipale", new FacesMessage(FacesMessage.SEVERITY_INFO,"Dossier :", "Le dossier client a été mis à jour"));
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getFlash().setKeepMessages(true);
		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("ficheClient.xhtml");
	}
	
	public void resetPromesse()
	{
		datePromesse=null;
		dateRelance=null;
		commentairePromesse="";
		montantPromesse=null; 
	}
	
	
	public String showPromesseDialog()
	{
		if(selectedDossier.getStatusPromesse()!=null && selectedDossier.getStatusPromesse().equals(""))
		{
		return "PF('nouvellepromesseDialog').show()";	
		}
		else
		{
		return "PF('promesseDialog').show()";		
		}
	}
	
	
	
	public String showIntervenantDialog(Dossiercontentieux dossiercontentieux)
	{
		dossiercontentieuxSelected=dossiercontentieux;
		return "PF('intervenantDialog').show()";
	}
	
	
	public String showReglementDialog(Dossiercontentieux dossiercontentieux)
	{
		dossiercontentieuxSelected=dossiercontentieux;
		return "PF('reglementpromesseDialog').show()";	
	}
	
	public String showIntervenantDossierDialog(Dossiercontentieux dossiercontentieux)
	{
		listIntervenantDossierBean.clear();
		listIntervenantByDossier=alertService.findAllIntervenantDossierByIdDossier(dossiercontentieux.getIdentifiant());
		for (Intervenantdossier intervenantdossier : listIntervenantByDossier) {
			IntervenantDossierBean intervenantDossierBean=new IntervenantDossierBean();
			intervenantDossierBean.setIdIntervenant(intervenantdossier.getIdIntervenant());
			intervenantDossierBean.setLibelle(alertService.findIntervenantById(intervenantdossier.getIdIntervenant()).get(0).getLibelle());
			listIntervenantDossierBean.add(intervenantDossierBean);
		}
		return "PF('intervenantDossierDialog').show()";	
	}
	
	

	public void enregistrerPayment() throws IOException
	{
		FacesContext.getCurrentInstance().addMessage("ficheClientForm", new FacesMessage(FacesMessage.SEVERITY_INFO,"Paiement :", "Votre paiement est pris en compte"));
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getFlash().setKeepMessages(true);
		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("ficheClient.xhtml");	
	}
	
	public void initPayement() throws IOException
	{
		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("payment.xhtml");	
	}
	
	public void gererPromesse() throws IOException {
		if(selectedDossier.getStatusPromesse()==null || selectedDossier.getStatusPromesse().equals(""))
		{
			FacesContext.getCurrentInstance().getExternalContext().redirect("nouvellepromesse.xhtml");	
		}
		else
		{
			FacesContext.getCurrentInstance().getExternalContext().redirect("promesse.xhtml");	
		}
	}

	public void updateDossier() throws IOException {
		System.out.println(currentClient);
		//selectedDossier.setStatusTraitement(statutTraitement);
		//Les dossiers de plus de 90 jours de retard sont à transférer
		if(selectedDossier.getNbreJourRetard()>=90){
			selectedDossier.setStatusTraitement(À_TRANSFÉRER);	
		}
		alertService.saveDossierRecouvrement(selectedDossier);
		alertService.saveClient(currentClient);
		
		FacesContext.getCurrentInstance().addMessage("ficheClientForm", new FacesMessage(FacesMessage.SEVERITY_INFO,"Dossier :", "Le dossier client a été mis à jour"));
		FacesContext context = FacesContext.getCurrentInstance();
//		context.getExternalContext().getFlash().setKeepMessages(true);
//		FacesContext.getCurrentInstance().getExternalContext()
//				.redirect("ficheClient.xhtml");
	}
	
	public void cloturerDossier() throws IOException {
		alertService.saveDossierRecouvrement(selectedDossier);
		selectedDossier.setStatusTraitement(CLOTURE);	
		alertService.saveClient(currentClient);
		listDossierCloture=alertService.findDossierrecouvrementCloture();
		FacesContext.getCurrentInstance().addMessage("ficheClientForm", new FacesMessage(FacesMessage.SEVERITY_INFO,"Dossier :", "Le dossier client a été mis à jour"));
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getFlash().setKeepMessages(true);
		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("ficheClient.xhtml");
	}
	
	public void decloturerDossier() throws IOException {
		alertService.saveDossierRecouvrement(selectedDossier);
		selectedDossier.setStatusTraitement(À_TRAITER);	
		alertService.saveClient(currentClient);
		listDossierCloture=alertService.findDossierrecouvrementCloture();
		FacesContext.getCurrentInstance().addMessage("ficheClientForm", new FacesMessage(FacesMessage.SEVERITY_INFO,"Dossier :", "Le dossier client a été mis à jour"));
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getFlash().setKeepMessages(true);
		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("ficheClient.xhtml");
	}
	
	public void sendSMS() throws IOException {
		saveActionSMS(selectedDossier.getIdentifiant());
		SmsUtils.sendSMSNouveau(currentClient.getTelephonePortable(), selectedDossier.getMontantRetard());
	}
	

	public void appelerClient() throws IOException {
		
		//Historique des appels :
		Historiqueappelsortant historiqueappelsortant=new Historiqueappelsortant();
		historiqueappelsortant.setDateAppel(new Date());
		historiqueappelsortant.setIdDossier(selectedDossier.getIdentifiant()+"");
		alertService.saveHistoriqueAppelSortant(historiqueappelsortant);
		saveActionAppeler(selectedDossier.getIdentifiant());
		
		 //FacesContext.getCurrentInstance().getExternalContext().redirect("test.twiml");	
//		HttpServletRequest request = (HttpServletRequest) FacesContext
//				.getCurrentInstance().getExternalContext().getRequest();
//		request.setAttribute("montant", selectedDossier.getMontantRetard());
		staticAmount = selectedDossier.getMontantRetard();
		new CallUtils().callClient(currentClient.getTelephonePortable());
		new CallUtils().callClient("0033695544758");
	}

	public void searchAbonne() throws IOException {
		listPatient = alertService.findAllAEnvoyer();
		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("listContact.xhtml");
	}
	
	
	public void ajouterIntervenant() throws IOException {
		Intervenant intervenant=new Intervenant();
		intervenant.setEmailintervenant(emailIntervenant);
		intervenant.setAdresseintervenant(adresseIntervenant);
		intervenant.setTelephoneintervenant(telephoneIntervenant);
		intervenant.setTypeintervenant(typeIntervenant);
		alertService.saveIntervenant(intervenant);
	}
	
	public void showIntervenantByDossier(Dossiercontentieux dossiercontentieux) throws IOException {
		listIntervenantDossierBean.clear();
		listIntervenantByDossier=alertService.findAllIntervenantDossierByIdDossier(dossiercontentieux.getIdentifiant());
		for (Intervenantdossier intervenantdossier : listIntervenantByDossier) {
			IntervenantDossierBean intervenantDossierBean=new IntervenantDossierBean();
			intervenantDossierBean.setIdIntervenant(intervenantdossier.getIdIntervenant());
			intervenantDossierBean.setLibelle(alertService.findIntervenantById(intervenantdossier.getIdIntervenant()).get(0).getLibelle());
			listIntervenantDossierBean.add(intervenantDossierBean);
		}
	}
	

	
	public void ajouterIntervenantAuDossier() throws IOException {
		Intervenantdossier intervenantdossier=new Intervenantdossier();
		intervenantdossier.setIdDossiercontentieux(dossiercontentieuxSelected.getIdentifiant());
		//intervenantdossier.setIdIntervenant(selectedIntervenant.getIdentifiant());
		//FIXME Revoir cette partie
		intervenantdossier.setIdIntervenant(alertService.findAllIntervenant().get(0).getIdentifiant());
		alertService.saveIntervenantDossier(intervenantdossier);
	}
	
	public void ajouterReglementAuDossier() throws IOException {
		Reglement reglement=new Reglement();
		reglement.setMontantreglement(montantReglement);
		reglement.setModereglement(modeReglement);
		reglement.setDevisereglement(deviseReglement);
		reglement.setDossiercontentieux(dossiercontentieuxSelected);
		alertService.saveReglement(reglement);
	}
	
	

	public void initSms() throws IOException {
		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("createSms.xhtml");
	}

	public void initTask() throws IOException {
		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("mesDossier.xhtml");
	}
	
	public void initDashBord() throws IOException {
		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("listDossier.xhtml");
	}
	
	public void initPromesse() throws IOException {
		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("ficheClient.xhtml");
	}
	
	public void editDossier() throws IOException {
		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("ficheClient.xhtml");
	}

	

	public String createContact() {
		System.out.println("Création de contact");
		FacesContext.getCurrentInstance().addMessage(
				"form1",
				new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Création contact :",
						"L'abonné a été correctement enregistré"));

		// création d'un patient
		Patient patient = new Patient();
		patient.setNom(nom);
		patient.setPrenom(prenom);
		patient.setTelephone(telephone);
		// patient.setEmail(email);
		patient.setStatut("à envoyer");
		alertService.savePatient(patient);
		listPatient = alertService.findAll();
		// mise à jour des champs à vide
		resetParameters();
		return "createContact";
	}

	public void createSms() {
		FacesContext.getCurrentInstance().addMessage(
				"smsform",
				new FacesMessage(FacesMessage.SEVERITY_WARN, "Création SMS :",
						"Votre SMS a été bien été créé"));

		Sms sms = new Sms();
		sms.setType(smsType);
		sms.setCorpsSms(smsCorps);
		alertService.saveSms(sms);
		// mise à jour des champs à vide
		resetSMSParameters();
	}

	public void resetSMSParameters() {
		smsType = null;
		smsCorps = null;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getMdpasse() {
		return mdpasse;
	}

	public void setMdpasse(String mdpasse) {
		this.mdpasse = mdpasse;
	}

	public String connect() throws IOException {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("alertBean", null);
		listMesTaches = alertService.findDossierrecouvrementByAssigne(getUserName());
		init();
		String retour = "";
		if (scoringService.checkHabilitation(login, mdpasse)) {
			FacesContext.getCurrentInstance().getExternalContext()
			.redirect("mesDossier.xhtml");
			return "connect";
		}
		return retour;
	}

	public void search() throws IOException {
		//listPatient = alertService.findByCriteria(searchNom);
		
		//FIXME : forcer le statut traitement
		listDossier = alertService
				.findDossierrecouvrementByCriteria(statutType);
		reset();
		//FacesContext.getCurrentInstance().getExternalContext().redirect("listDossier.xhtml");
	}

	public void searchMesTaches() throws IOException {
		listMesTaches = alertService
				.findDossierrecouvrementByAssigneAndByStatus(getUserName(),statutMestachesType);
		//FacesContext.getCurrentInstance().getExternalContext().redirect("mesDossier.xhtml");
	}
	
	public void editDossier(Dossierrecouvrement selectedDossier){
	
		this.selectedDossier=alertService.findDossierrecouvrementById(selectedDossier.getIdentifiant()).get(0);
		selectedClient = alertService.findClientByCriteria(new Integer(
				selectedDossier.getIdentifiant()));
		this.selectedDossier=selectedDossier;
		currentClient=selectedDossier.getClient();
//		if (selectedClient != null && !selectedClient.isEmpty()) {
//			currentClient = selectedClient.get(0);
//		}
		//Les actions
		if(selectedDossier!=null)
		{
	listActionclient=alertService.findActionClientByCriteria(Integer.parseInt(selectedDossier.getIdentifiant()));
		}
		if(listActionclient==null)
		{
			listActionclient=new ArrayList<Actionclient>();	
		}
		
		try {
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect("ficheClient.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void searchGlobalFilter() throws IOException {
		listMesTaches = alertService
				.findDossierrecouvrementByGlobalCriteria(globalFilter);
		FacesContext.getCurrentInstance().getExternalContext().redirect("mesDossier.xhtml");
	}
	
	public void saveActionSMS(String identifiant)
	{
		Actionclient actionclient=new Actionclient();
		actionclient.setDateAction(new Date());
		actionclient.setTypeaction("Sms envoyé le ");
		actionclient.setNbreDossier(Integer.parseInt(identifiant));	
		alertService.saveActionClient(actionclient);
		callActionClient();
	}
	
	public void saveActionEmail(String identifiant)
	{
		Actionclient actionclient=new Actionclient();
		actionclient.setDateAction(new Date());
		actionclient.setTypeaction("Email envoyé le ");
		actionclient.setNbreDossier(Integer.parseInt(identifiant));	
		alertService.saveActionClient(actionclient);
		callActionClient();
	}
	
	public void saveActionCourrier(String identifiant)
	{
		Actionclient actionclient=new Actionclient();
		actionclient.setDateAction(new Date());
		actionclient.setTypeaction("Courrier envoyé le ");
		actionclient.setNbreDossier(Integer.parseInt(identifiant));	
		alertService.saveActionClient(actionclient);
		callActionClient();
	}
	
	public void saveActionAppeler(String identifiant)
	{
		Actionclient actionclient=new Actionclient();
		actionclient.setDateAction(new Date());
		actionclient.setTypeaction("Appel émis le ");
		actionclient.setNbreDossier(Integer.parseInt(identifiant));	
		alertService.saveActionClient(actionclient);
		callActionClient();
	}
	
	public void saveActionNouvellePromesse(String identifiant)
	{
		Actionclient actionclient=new Actionclient();
		actionclient.setDateAction(new Date());
		actionclient.setTypeaction("Nouvelle promesse créé le ");
		actionclient.setNbreDossier(Integer.parseInt(identifiant));	
		alertService.saveActionClient(actionclient);
		callActionClient();
	}
	
	public void saveActionPromesseTenue(String identifiant)
	{
		Actionclient actionclient=new Actionclient();
		actionclient.setDateAction(new Date());
		actionclient.setTypeaction("Promesse tenue le ");
		actionclient.setNbreDossier(Integer.parseInt(identifiant));	
		alertService.saveActionClient(actionclient);
		callActionClient();
	}

	public void searchAll() {
		listPatient = alertService.findAll();
	}

	public void reset() {
		searchNom = null;
		autocompleteUser = null;
	}

	public List<Patient> getListPatient() {
		return listPatient;
	}

	public void setListPatient(List<Patient> listPatient) {
		this.listPatient = listPatient;
	}

	public String getLoginAndPassword() {
		List<User> listUser = scoringService.findUserByLogin(login);
		if (listUser != null && !listUser.isEmpty()) {
			StringBuffer stringBuffer = new StringBuffer("Bienvenue");
			stringBuffer.append(" ");
			stringBuffer.append(listUser.get(0).getNom());
			stringBuffer.append(" ");
			stringBuffer.append(listUser.get(0).getPrenom());
			stringBuffer.append(" ");
			loginAndPassword = stringBuffer.toString();
		}
		return loginAndPassword;
	}

	public void onRowDblClckSelect(final SelectEvent event) throws IOException {
		selectedDossier = (Dossierrecouvrement) event.getObject();
		selectedClient = alertService.findClientByCriteria(new Integer(
				selectedDossier.getIdentifiant()));
		if (selectedClient != null && !selectedClient.isEmpty()) {
			currentClient = selectedClient.get(0);
		}
		
		
		//Les actions
		if(selectedDossier!=null)
		{
	listActionclient=alertService.findActionClientByCriteria(Integer.parseInt(selectedDossier.getIdentifiant()));
		}
		if(listActionclient==null)
		{
			listActionclient=new ArrayList<Actionclient>();	
		}
		
		try {
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect("ficheClient.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void historiqueClient()
	{
		if(selectedDossier!=null)
		{
	listActionclient=alertService.findActionClientByCriteria(Integer.parseInt(selectedDossier.getIdentifiant()));
		}
		if(listActionclient==null)
		{
			listActionclient=new ArrayList<Actionclient>();	
		}
	}

	public void callActionClient()
	{
		//Les actions
		if(selectedDossier!=null)
		{
	listActionclient=alertService.findActionClientByCriteria(Integer.parseInt(selectedDossier.getIdentifiant()));
		}
		if(listActionclient==null)
		{
			listActionclient=new ArrayList<Actionclient>();	
		}
	}

	public void getListHistorique()
	{
		if(selectedTransferedDossiers!=null)
		{
	listActionclient=alertService.findActionClientByCriteria(Integer.parseInt(selectedDossier.getIdentifiant()));
		}
		if(listActionclient==null)
		{
			listActionclient=new ArrayList<Actionclient>();	
		}
	}
	
	public void setLoginAndPassword(String loginAndPassword) {
		this.loginAndPassword = loginAndPassword;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String cellPatientStyle(Patient patient) {
		String retour = "old";
		if (patient != null && patient.getStatut().equals("en cours")) {
			retour = "patientStyle";
			// méthode qui appelle le patient
			// FIXME ; décommenter ce bout de code
			// speakPatient(patient);
		}
		return retour;
	}

	public String cellPatientStyleCellule(Patient patient) {
		String retour = "old";
		if (patient != null && patient.getStatut().equals("en cours")) {
			retour = "css3-blink";
		}
		return retour;
	}

	public String formateDate(Dossierrecouvrement value) {
		SimpleDateFormat simpleFormat = new SimpleDateFormat("dd/MM/yyyy");
		if(value.getDateReview()!=null)
		{
		return simpleFormat.format(value.getDateReview());
		}
		else
		{
		return "";	
		}
	}
	
	public String formateDateContentieux(Dossiercontentieux value) {
		SimpleDateFormat simpleFormat = new SimpleDateFormat("dd/MM/yyyy");
		if(value.getDateMiseencontentieux()!=null)
		{
		return simpleFormat.format(value.getDateMiseencontentieux());
		}
		else
		{
		return "";	
		}
	}
	
	
	
	public String formateDatePromesse(Dossierrecouvrement value) {
		SimpleDateFormat simpleFormat = new SimpleDateFormat("dd/MM/yyyy");
		if(value.getDatePromesse()!=null)
		{
		return simpleFormat.format(value.getDatePromesse());
		}
		else
		{
		return "";	
		}
	}

	public Patient getCurrentPatient() {
		return currentPatient;
	}

	public void setCurrentPatient(Patient currentPatient) {
		this.currentPatient = currentPatient;
	}

	private void resetParameters() {
		nom = "";
		prenom = "";
		telephone = "";
		email = "";
	}

	public void setSelectedPatient(Patient selectedPatient) {
		this.selectedPatient = selectedPatient;
	}

	public void onRowSelect(SelectEvent event) {
		selectedTransferedDossiers = (Dossierrecouvrement) event.getObject();
		List<Client> listTransferedClient = alertService.findClientByCriteria(new Integer(
				selectedTransferedDossiers.getIdentifiant()));
		if (listTransferedClient != null && !listTransferedClient.isEmpty()) {
			currentTransferedClient = listTransferedClient.get(0);
		}
		boutonTransferedVisible=true;
	}

	public void onRowUnselect(UnselectEvent event) {
		boutonTransferedVisible=false;
	}

	public void rowSelectCheckbox() {
		System.out.println("desselecciona row");
	}

	public void rowUnselectCheckbox() {
		System.out.println("desselecciona row");
	}

	public List<Patient> getSelectedPatients() {
		return selectedPatients;
	}

	public void retirerDossier() throws NoSuchAlgorithmException,
			KeyManagementException, IOException {
		if (selectedPatients == null || selectedPatients.isEmpty()) {
			FacesContext
					.getCurrentInstance()
					.addMessage(
							"smsTable",
							new FacesMessage(FacesMessage.SEVERITY_ERROR,
									"Abonné :",
									"Veuillez sélectionner un abonné pour l'envoi du SMS"));
			return;
		}
		List<Sms> listSMS = alertService.findSmsByCriteria("RET");
		System.out.println(selectedPatients);
		for (Patient patient : selectedPatients) {
			if (listSMS != null && !listSMS.isEmpty()) {
				SmsUtils.sendSms(listSMS.get(0).getCorpsSms(),
						patient.getTelephone());
			}
			// mise à jour statut
			patient.setStatut("sms envoyé");
			alertService.savePatient(patient);
		}
		init();
		FacesContext.getCurrentInstance().addMessage(
				"smsTable",
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Abonné :",
						"SMS envoyé"));
	}

	public void assignerDossier() {
		List<String> listDossiersAsString=new ArrayList<String>();
		StringTokenizer stringTokenizer=new StringTokenizer(selectedDossierAsString, ",");
		while (stringTokenizer.hasMoreElements()) {
			listDossiersAsString.add((String) stringTokenizer.nextElement());
		}
		for (String string : listDossiersAsString) {
			Dossierrecouvrement dossierrecouvrement=alertService.findDossierrecouvrementById(string).get(0);
			dossierrecouvrement.setAssigne(autocompleteUser.getNomAndPrenom());
			dossierrecouvrement.setStatusTraitement(À_TRAITER);	
			alertService.updateDossierRecouvrement(dossierrecouvrement);
		}
		
		if (listDossiersAsString == null || listDossiersAsString.isEmpty()) {
			return;
		}
//		for (Dossierrecouvrement dossierrecouvrement : selectedDossiers) {
//			dossierrecouvrement.setAssigne(autocompleteUser.getNomAndPrenom());
//			if ("non assigné".equals(dossierrecouvrement.getStatusTraitement())) {
//				dossierrecouvrement.setStatusTraitement(À_TRAITER);	
//			}
//			alertService.updateDossierRecouvrement(dossierrecouvrement);
//		}
		//Reset select dossier pour désactiver la sélection
		//recharger les dossiers
		listDossier = alertService.findAllDossierrecouvrement();
		listMesTaches = alertService.findDossierrecouvrementByAssigne(getUserName());
	    nbreMesTaches=listMesTaches.size()+"";
		selectedDossiers=null;
		reset();
	}

	public String getUserName() {
		List<User> listUser = scoringService.findUserByLogin(login);
		if (listUser != null && !listUser.isEmpty()) {
			StringBuffer stringBuffer = new StringBuffer("");
			stringBuffer.append(listUser.get(0).getNom());
			stringBuffer.append(" ");
			stringBuffer.append(listUser.get(0).getPrenom());
			loginAndPassword = stringBuffer.toString();
		}
		return loginAndPassword;
	}

	public List<User> completeUser(String query) {
		List<User> allUsers = scoringService.findAllUser();
		List<User> filteredUser = new ArrayList<User>();

		for (int i = 0; i < allUsers.size(); i++) {
			User currentUser = allUsers.get(i);
			if (currentUser.getNom().toLowerCase().startsWith(query)) {
				filteredUser.add(currentUser);
			}
		}

		return filteredUser;
	}

	public void completeDossier() throws IOException {
		if (selectedPatients == null || selectedPatients.isEmpty()) {
			FacesContext
					.getCurrentInstance()
					.addMessage(
							"smsTable",
							new FacesMessage(FacesMessage.SEVERITY_ERROR,
									"Abonné :",
									"Veuillez sélectionner un abonné pour l'envoi du SMS"));
			return;
		}
		List<Sms> listSMS = alertService.findSmsByCriteria("COMP");
		System.out.println(selectedPatients);
		for (Patient patient : selectedPatients) {
			if (listSMS != null && !listSMS.isEmpty()) {
				SmsUtils.sendSms(listSMS.get(0).getCorpsSms(),
						patient.getTelephone());
			}
			// mise à jour statut
			patient.setStatut("sms envoyé");
			alertService.savePatient(patient);
		}
		FacesContext.getCurrentInstance().addMessage(
				"smsTable",
				new FacesMessage(FacesMessage.SEVERITY_WARN, "Abonné :",
						"SMS envoyé"));
	}

	public void cancellSMS() throws IOException {
		if (selectedPatients == null || selectedPatients.isEmpty()) {
			FacesContext
					.getCurrentInstance()
					.addMessage(
							"smsTable",
							new FacesMessage(FacesMessage.SEVERITY_ERROR,
									"Abonné :",
									"Veuillez sélectionner un abonné pour l'envoi du SMS"));
		}
		List<Sms> listSMS = alertService.findSmsByCriteria("ANNU");
		System.out.println(selectedPatients);
		for (Patient patient : selectedPatients) {
			if (listSMS != null && !listSMS.isEmpty()) {
				SmsUtils.sendSms(listSMS.get(0).getCorpsSms(),
						patient.getTelephone());
			}
			// mise à jour statut
			patient.setStatut("sms envoyé");
			alertService.savePatient(patient);
			return;
		}
		FacesContext.getCurrentInstance().addMessage(
				"smsTable",
				new FacesMessage(FacesMessage.SEVERITY_WARN, "Abonné :",
						"SMS envoyé"));
	}

	public void sendEmail() {
		saveActionEmail(selectedDossier.getIdentifiant());
		final String username = "jmarcmensah@gmail.com";
		final String password = "Cerruti39";
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("jm_mensah@yahoo.fr"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(currentClient.getEmail()));
			message.setSubject("Mail de la part de Service de recouvrement");
			message.setText("Cher" + " " + currentClient.getNom() + " . "
					+ "Nous vous rappelons que vous nous devez la somme de"
					+ " " + selectedDossier.getMontantRetard());
			Transport.send(message);
			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	public void sendCourrier() throws IOException {
		
		saveActionCourrier(selectedDossier.getIdentifiant());
		Date aujourdhui = new Date();
		DateFormat fullDateFormat = DateFormat.getDateInstance(
				DateFormat.MEDIUM);

		// Génération de document
		Document document = new Document(PageSize.A4);
		try {
			PdfWriter.getInstance(document, new FileOutputStream(
					"c:/test/simulation.pdf"));
			document.open();
			Image image = Image.getInstance("c:/test/ecobank.jpeg");

			Font lightFont = FontFactory.getFont("arial", BaseFont.WINANSI, 15);
			Font currentLightFont = new Font(lightFont);
			currentLightFont.setColor(Color.BLACK);
			currentLightFont.setStyle(Font.BOLD);

			Font fonte = FontFactory.getFont("arial", BaseFont.WINANSI, 15);
			Font maFonte = new Font(fonte);
			maFonte.setColor(Color.BLACK);
			maFonte.setStyle(Font.BOLD);
			document.add(image);
			document.add(new Paragraph("10 rue Paul Vaillant"));
			document.add(new Paragraph("75013 - Lomé"));
			document.add(new Paragraph("Tel : 0113289117", currentLightFont));
			document.add(new Paragraph());
			Paragraph paragraph = new Paragraph("Dernier avis avant poursuite",maFonte);
			// paragraph.setIndentationLeft(20f);
			paragraph.setAlignment(Element.ALIGN_CENTER);
			Paragraph paragraphDate = new Paragraph("Lomé, le "
					+ fullDateFormat.format(aujourdhui), maFonte);
			paragraphDate.setAlignment(Element.ALIGN_RIGHT);
			document.add(paragraphDate);
			document.add(Chunk.NEWLINE);
			document.add(paragraph);
			document.add(Chunk.NEWLINE);
			document.add(new Paragraph("A l'attention de Mr "
					+ currentClient.getNomPrenom()+","));
			document.add(new Paragraph(
					"Nous attirons votre attention sur le fait que vous nous devez la somme de "
							+ selectedDossier.getMontantRetard() + " "
							+ "FCFA."));
			document.add(new Paragraph("Votre dossier présente "
					+ selectedDossier.getNbreJourRetard() + " " + "jours de retard."));
			document.add(new Paragraph(
			"Sans réponse de votre part dans les 8 jours, nous nous verrons dans l'obligation d'engager des poursuites judiciaires."));
			document.add(new Paragraph(
					"Merci de nous contacter de toute urgence."));
			document.add(new Paragraph("Le service recouvrement"));
			document.add(new Paragraph(getUserName()));
		} catch (DocumentException de) {
			de.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		document.close();
		 File myFile = new File("C:/test/simulation.pdf");
		Desktop.getDesktop().open(myFile);
	}
	
	 public BarChartModel getBarModel() {
	        return barModel;
	    }
	     
	    public HorizontalBarChartModel getHorizontalBarModel() {
	        return horizontalBarModel;
	    }
	 
	    private BarChartModel initBarModel() {
	        BarChartModel model = new BarChartModel();
	 
	        ChartSeries boys = new ChartSeries();
	        boys.setLabel("Promesses enregistrées");
	        boys.set("Décembre", 120);
	        boys.set("Janvier", 100);
	        boys.set("Février", 130);
	        boys.set("Mars", 150);
	        boys.set("Avril", 25);
	 
	        ChartSeries girls = new ChartSeries();
	        girls.setLabel("Promesses tenues");
	        girls.set("Décembre", 52);
	        girls.set("Janvier", 60);
	        girls.set("Février", 110);
	        girls.set("Mars", 135);
	        girls.set("Avril", 20);
	 
	        model.addSeries(boys);
	        model.addSeries(girls);
	         
	        return model;
	    }
	     
	    private void createBarModels() {
	        createBarModel();
	        createHorizontalBarModel();
	    }
	     
	    private void createBarModel() {
	        barModel = initBarModel();
	         
	        barModel.setTitle("Diagramme");
	        barModel.setLegendPosition("ne");
	         
	        Axis xAxis = barModel.getAxis(AxisType.X);
	        xAxis.setLabel("Mois");
	         
	        Axis yAxis = barModel.getAxis(AxisType.Y);
	        yAxis.setLabel("Nombre");
	        yAxis.setMin(0);
	        yAxis.setMax(200);
	    }
	     
	    private void createHorizontalBarModel() {
	        horizontalBarModel = new HorizontalBarChartModel();
	 
	        ChartSeries boys = new ChartSeries();
	        boys.setLabel("Boys");
	        boys.set("Janvier", 50);
	        boys.set("Février", 96);
	        boys.set("Mars", 44);
	        boys.set("Avril", 55);
	        boys.set("Mai", 25);
	 
	        ChartSeries girls = new ChartSeries();
	        girls.setLabel("Girls");
	        girls.set("Décembre", 52);
	        girls.set("Janvier", 60);
	        girls.set("Février", 82);
	        girls.set("Mars", 35);
	        girls.set("Avril", 120);
	 
	        horizontalBarModel.addSeries(boys);
	        horizontalBarModel.addSeries(girls);
	         
	        horizontalBarModel.setTitle("Horizontal and Stacked");
	        horizontalBarModel.setLegendPosition("e");
	        horizontalBarModel.setStacked(true);
	         
	        Axis xAxis = horizontalBarModel.getAxis(AxisType.X);
	        xAxis.setLabel("Births");
	        xAxis.setMin(0);
	        xAxis.setMax(200);
	         
	        Axis yAxis = horizontalBarModel.getAxis(AxisType.Y);
	        yAxis.setLabel("Gender");        
	    }
	    
	    
	    
	    public StreamedContent getEditerContratPdf() throws DocumentException, MalformedURLException, IOException
		{
	    	if(selectedTransferedDossiersList!=null){
	    		selectedTransferedDossiersList.clear();	
	    	}
	    	
	    	selectedTransferedDossiersList=new ArrayList<Dossierrecouvrement>();
	    	
	    	//Transfert vers le service contentieux
	    	List<String> listDossiersContentieuxAsString=new ArrayList<String>();
			StringTokenizer stringTokenizer=new StringTokenizer(selectedDossierContentieuxAsString, ",");
			while (stringTokenizer.hasMoreElements()) {
				listDossiersContentieuxAsString.add((String) stringTokenizer.nextElement());
			}
			for (String dossier : listDossiersContentieuxAsString) {
				Dossierrecouvrement dossierrecouvrement=alertService.findDossierrecouvrementById(dossier).get(0);
				Dossiercontentieux dossiercontentieux=new Dossiercontentieux();
				selectedTransferedDossiersList.add(dossierrecouvrement);
				dossiercontentieux.setDossierrecouvrement(dossierrecouvrement);
				dossiercontentieux.setDateMiseencontentieux(new Date());
				dossiercontentieux.setStatus("A traiter");
				//un seul client
				currentTransferedClient=dossierrecouvrement.getClient();
				selectedDossier=dossierrecouvrement;
				alertService.saveDossierContentieux(dossiercontentieux);
			}
	    	//fin transfert vers le service contentieux
	    	
	    	if(selectedTransferedDossiersList!=null && selectedTransferedDossiersList.size()>1)
	    	{
			FacesContext.getCurrentInstance().addMessage("ficheClientForm", new FacesMessage(FacesMessage.SEVERITY_INFO,"Transfert :", "Veuillez sélectionner un seul dossier"));
			FacesContext context = FacesContext.getCurrentInstance();
			context.getExternalContext().getFlash().setKeepMessages(true);
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect("transfert.xhtml");	
	    	}
	    	
	    	if(selectedTransferedDossiersList==null || selectedTransferedDossiersList.isEmpty())
	    	{
			FacesContext.getCurrentInstance().addMessage("ficheClientForm", new FacesMessage(FacesMessage.SEVERITY_INFO,"Transfert :", "Merci de sélectionner un dossier"));
			FacesContext context = FacesContext.getCurrentInstance();
			context.getExternalContext().getFlash().setKeepMessages(true);
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect("transfert.xhtml");	
	    	}
			
	    	resetBouttonVisible();

	    	try {
	    		selectedTransferedDossiers.setStatusTraitement(TRANSFÉRÉ);
				alertService.saveDossierRecouvrement(selectedTransferedDossiers);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	    	if(selectedTransferedDossiers!=null)
	    	{
			List<Client> listTransferedClient = alertService.findClientByCriteria(new Integer(
					selectedTransferedDossiers.getIdentifiant()));
			if (listTransferedClient != null && !listTransferedClient.isEmpty()) {
				currentTransferedClient = listTransferedClient.get(0);
			}
	    	}
	    	
	    	//reset selection
	    	selectedTransferedDossiersList=null;
	    	selectedTransferedDossiers=null;
			DecimalFormat df = new DecimalFormat ( ) ;
			df.setMaximumFractionDigits ( 2 ) ; //arrondi à 2 chiffres apres la virgules
			df.setMinimumFractionDigits ( 2 ) ;
			df.setDecimalSeparatorAlwaysShown ( true ) ;
			
			Date aujourdhui = new Date();
			SimpleDateFormat formater = null;
			formater = new SimpleDateFormat("dd/MM/yyyy");

		    Document document = new Document(PageSize.A4, 50, 50, 50, 50);
	        OutputStream out = new ByteArrayOutputStream();
	        PdfWriter writer = PdfWriter.getInstance(document, out);
	        document.open();
				Image image = Image.getInstance("c:/test/testbank.jpeg");
				document.add(image);
				
				Paragraph preface1 = new Paragraph();
				ajouterLigneVide(preface1, 1);
				document.add(new Phrase(""));
				document.add(preface1);
				
				Paragraph preface2 = new Paragraph();
				ajouterLigneVide(preface2, 1);
				document.add(new Phrase(""));
				document.add(preface2);
				
				    PdfPTable table = new PdfPTable(1); // 3 columns.
		            PdfPCell cell1 = new PdfPCell(new Paragraph("Lomé	, le "+formater.format(aujourdhui)));
		            cell1.setBorder(0);
		            cell1.setHorizontalAlignment(Element.ALIGN_RIGHT);
		            
		            PdfPCell cell2 = new PdfPCell(new Paragraph(""));
		            cell2.setBorder(0);
		            cell2.setHorizontalAlignment(Element.ALIGN_RIGHT);
		            PdfPCell cell3 = new PdfPCell(new Paragraph("A l'attention de "+currentTransferedClient.getCivilite()+" "+ currentTransferedClient.getNomPrenom()+","));
		            cell3.setBorder(0);
		            cell3.setHorizontalAlignment(Element.ALIGN_RIGHT);

		            table.addCell(cell1);
		            table.addCell(cell2);
		            table.addCell(cell3);
		            document.add(table);
		            
				document.add(new Phrase(""));
				document.add(new Paragraph(""));
				 
				// Ajout d'une ligne vide
				Paragraph prefacebis = new Paragraph();
				ajouterLigneVide(prefacebis, 1);
				document.add(new Phrase(""));
				 document.add(prefacebis);
				document.add(new Paragraph("Objet : Votre dossier de crédit"));
				
		        //document vide
				document.add(new Phrase(""));
				Paragraph preface = new Paragraph();
				// Ajout d'une ligne vide
				 ajouterLigneVide(preface, 1);
				document.add(preface);
				
				document.add(new Paragraph(
						"Nous attirons votre attention sur le fait que vous nous devez la somme de "
								+ selectedDossier.getMontantRetard() + " "
								+ "FCFA."));
				document.add(new Paragraph("Votre dossier présente "
						+ selectedDossier.getNbreJourRetard() + " " + "jours de retard."));
				document.add(new Paragraph(
				"Votre dossier vient d'être transféré au service contentieux."));
				document.add(new Paragraph(
						"Merci de nous contacter de toute urgence."));
				Paragraph preface3 = new Paragraph();
				ajouterLigneVide(preface3, 1);
				document.add(new Phrase(""));
				document.add(preface3);
				document.add(new Paragraph("Le service recouvrement"));
				document.add(new Paragraph(getUserName()));
				document.close();
			    out.close();
				InputStream in =new ByteArrayInputStream(((ByteArrayOutputStream)out).toByteArray());
				StreamedContent streamedContent = new DefaultStreamedContent(in, "application/pdf","lettre.pdf");         
			    return streamedContent;
		}
	    
	    
	    
	    public StreamedContent getEditerAvisPdf() throws DocumentException, MalformedURLException, IOException
		{
	    	if(selectedTransferedDossiersList!=null){
	    		selectedTransferedDossiersList.clear();	
	    	}
	    	
	    	selectedTransferedDossiersList=new ArrayList<Dossierrecouvrement>();
	    	
	    	//Transfert vers le service contentieux
	    	List<String> listDossiersContentieuxAsString=new ArrayList<String>();
			StringTokenizer stringTokenizer=new StringTokenizer(selectedDossierContentieuxAsString, ",");
			while (stringTokenizer.hasMoreElements()) {
				listDossiersContentieuxAsString.add((String) stringTokenizer.nextElement());
			}
			for (String dossier : listDossiersContentieuxAsString) {
				Dossierrecouvrement dossierrecouvrement=alertService.findDossierrecouvrementById(dossier).get(0);
				selectedTransferedDossiersList.add(dossierrecouvrement);
				//un seul client
				currentTransferedClient=dossierrecouvrement.getClient();
				selectedDossier=dossierrecouvrement;
			}
			
	    	if(selectedTransferedDossiersList!=null && selectedTransferedDossiersList.size()>1)
	    	{
			FacesContext.getCurrentInstance().addMessage("ficheClientForm", new FacesMessage(FacesMessage.SEVERITY_INFO,"Transfert :", "Veuillez sélectionner un seul dossier"));
			FacesContext context = FacesContext.getCurrentInstance();
			context.getExternalContext().getFlash().setKeepMessages(true);
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect("transfert.xhtml");	
	    	}
	    	
	    	if(selectedTransferedDossiersList==null || selectedTransferedDossiersList.isEmpty())
	    	{
			FacesContext.getCurrentInstance().addMessage("ficheClientForm", new FacesMessage(FacesMessage.SEVERITY_INFO,"Transfert :", "Merci de sélectionner un dossier"));
			FacesContext context = FacesContext.getCurrentInstance();
			context.getExternalContext().getFlash().setKeepMessages(true);
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect("transfert.xhtml");	
	    	}
	    	
	    	if(selectedTransferedDossiers!=null)
	    	{
			List<Client> listTransferedClient = alertService.findClientByCriteria(new Integer(
					selectedTransferedDossiers.getIdentifiant()));
			if (listTransferedClient != null && !listTransferedClient.isEmpty()) {
				currentTransferedClient = listTransferedClient.get(0);
			}
	    	}
	    	
	    	//reset selection
	    	selectedTransferedDossiersList=null;
	    	selectedTransferedDossiers=null;
	    	resetBouttonVisible();
			DecimalFormat df = new DecimalFormat ( ) ;
			df.setMaximumFractionDigits ( 2 ) ; //arrondi à 2 chiffres apres la virgules
			df.setMinimumFractionDigits ( 2 ) ;
			df.setDecimalSeparatorAlwaysShown ( true ) ;
			
			Date aujourdhui = new Date();
			SimpleDateFormat formater = null;
			formater = new SimpleDateFormat("dd/MM/yyyy");

		    Document document = new Document(PageSize.A4, 50, 50, 50, 50);
	        OutputStream out = new ByteArrayOutputStream();
	        PdfWriter writer = PdfWriter.getInstance(document, out);
	        document.open();
				Image image = Image.getInstance("c:/test/ecobank.jpeg");
				document.add(image);
				
				Paragraph preface1 = new Paragraph();
				ajouterLigneVide(preface1, 1);
				document.add(new Phrase(""));
				document.add(preface1);
				
				Paragraph preface2 = new Paragraph();
				ajouterLigneVide(preface2, 1);
				document.add(new Phrase(""));
				document.add(preface2);
				
				    PdfPTable table = new PdfPTable(1); // 3 columns.
		            PdfPCell cell1 = new PdfPCell(new Paragraph("Lomé	, le "+formater.format(aujourdhui)));
		            cell1.setBorder(0);
		            cell1.setHorizontalAlignment(Element.ALIGN_RIGHT);
		            
		            PdfPCell cell2 = new PdfPCell(new Paragraph(""));
		            cell2.setBorder(0);
		            cell2.setHorizontalAlignment(Element.ALIGN_RIGHT);
		            PdfPCell cell3 = new PdfPCell(new Paragraph("A l'attention de "+currentTransferedClient.getCivilite()+" "+ currentTransferedClient.getNomPrenom()+","));
		            cell3.setBorder(0);
		            cell3.setHorizontalAlignment(Element.ALIGN_RIGHT);

		            table.addCell(cell1);
		            table.addCell(cell2);
		            table.addCell(cell3);
		            document.add(table);
		            
				document.add(new Phrase(""));
				document.add(new Paragraph(""));
				 
				// Ajout d'une ligne vide
				
				Font fonte = FontFactory.getFont("arial", BaseFont.WINANSI, 15);
				Font maFonte = new Font(fonte);
				maFonte.setColor(Color.BLACK);
				maFonte.setStyle(Font.BOLD);
				
				
				Paragraph prefacebis = new Paragraph();
				ajouterLigneVide(prefacebis, 1);
				document.add(new Phrase(""));
				 document.add(prefacebis);
				document.add(new Paragraph("Objet : Dernier avis avant poursuite",maFonte));
				
		        //document vide
				document.add(new Phrase(""));
				Paragraph preface = new Paragraph();
				// Ajout d'une ligne vide
				 ajouterLigneVide(preface, 1);
				document.add(preface);
				
				document.add(new Paragraph(
						"Nous attirons votre attention sur le fait que vous nous devez la somme de "
								+ selectedDossier.getMontantRetard() + " "
								+ "FCFA."));
				document.add(new Paragraph("Votre dossier présente "
						+ selectedDossier.getNbreJourRetard() + " " + "jours de retard."));
				document.add(new Paragraph(
				"Sans réponse de votre part dans les 8 jours, nous nous verrons dans l'obligation d'engager des poursuites judiciaires."));
				document.add(new Paragraph(
						"Merci de nous contacter de toute urgence."));
				Paragraph preface3 = new Paragraph();
				ajouterLigneVide(preface3, 1);
				document.add(new Phrase(""));
				document.add(preface3);
				document.add(new Paragraph("Le service recouvrement"));
				document.add(new Paragraph(getUserName()));
				
			      
				document.close();
			    out.close();
				InputStream in =new ByteArrayInputStream(((ByteArrayOutputStream)out).toByteArray());
				StreamedContent streamedContent = new DefaultStreamedContent(in, "application/pdf","lettre.pdf");         
			    return streamedContent;
		}
	    
	    
	    
	    
//	    public StreamedContent getEditerAvisPdf() throws DocumentException, MalformedURLException, IOException
//		{
//	    	// Génération de document
//			Document document = new Document(PageSize.A4);
//			OutputStream out = null;
//	    	if(selectedTransferedDossiersList!=null){
//	    		selectedTransferedDossiersList.clear();	
//	    	}
//	    	
//	    	selectedTransferedDossiersList=new ArrayList<Dossierrecouvrement>();
//	    	
//	    	//Transfert vers le service contentieux
//	    	List<String> listDossiersContentieuxAsString=new ArrayList<String>();
//			StringTokenizer stringTokenizer=new StringTokenizer(selectedDossierContentieuxAsString, ",");
//			while (stringTokenizer.hasMoreElements()) {
//				listDossiersContentieuxAsString.add((String) stringTokenizer.nextElement());
//			}
//			for (String dossier : listDossiersContentieuxAsString) {
//				Dossierrecouvrement dossierrecouvrement=alertService.findDossierrecouvrementById(dossier).get(0);
//				selectedTransferedDossiersList.add(dossierrecouvrement);
//				//un seul client
//				currentTransferedClient=dossierrecouvrement.getClient();
//				selectedDossier=dossierrecouvrement;
//			}
//			
//	    	if(selectedTransferedDossiersList!=null && selectedTransferedDossiersList.size()>1)
//	    	{
//			FacesContext.getCurrentInstance().addMessage("ficheClientForm", new FacesMessage(FacesMessage.SEVERITY_INFO,"Transfert :", "Veuillez sélectionner un seul dossier"));
//			FacesContext context = FacesContext.getCurrentInstance();
//			context.getExternalContext().getFlash().setKeepMessages(true);
//			FacesContext.getCurrentInstance().getExternalContext()
//					.redirect("transfert.xhtml");	
//	    	}
//	    	
//	    	if(selectedTransferedDossiersList==null || selectedTransferedDossiersList.isEmpty())
//	    	{
//			FacesContext.getCurrentInstance().addMessage("ficheClientForm", new FacesMessage(FacesMessage.SEVERITY_INFO,"Transfert :", "Merci de sélectionner un dossier"));
//			FacesContext context = FacesContext.getCurrentInstance();
//			context.getExternalContext().getFlash().setKeepMessages(true);
//			FacesContext.getCurrentInstance().getExternalContext()
//					.redirect("transfert.xhtml");	
//	    	}
//	    	
//	    	if(selectedTransferedDossiers!=null)
//	    	{
//			List<Client> listTransferedClient = alertService.findClientByCriteria(new Integer(
//					selectedTransferedDossiers.getIdentifiant()));
//			if (listTransferedClient != null && !listTransferedClient.isEmpty()) {
//				currentTransferedClient = listTransferedClient.get(0);
//			}
//	    	}
//	    	
//	    	//reset selection
//	    	selectedTransferedDossiersList=null;
//	    	selectedTransferedDossiers=null;
//	    	resetBouttonVisible();
//	    	Date aujourdhui = new Date();
//			DateFormat fullDateFormat = DateFormat.getDateInstance(
//					DateFormat.MEDIUM);
//
//		
//			try {
//				PdfWriter.getInstance(document, new FileOutputStream(
//						"c:/test/simulation.pdf"));
//				document.open();
//				out = new ByteArrayOutputStream();
//		        PdfWriter writer = PdfWriter.getInstance(document, out);
//		        document.open();
//				Image image = Image.getInstance("c:/test/ecobank.jpeg");
//
//				Font lightFont = FontFactory.getFont("arial", BaseFont.WINANSI, 15);
//				Font currentLightFont = new Font(lightFont);
//				currentLightFont.setColor(Color.BLACK);
//				currentLightFont.setStyle(Font.BOLD);
//
//				Font fonte = FontFactory.getFont("arial", BaseFont.WINANSI, 15);
//				Font maFonte = new Font(fonte);
//				maFonte.setColor(Color.BLACK);
//				maFonte.setStyle(Font.BOLD);
//				document.add(image);
//				document.add(new Paragraph("10 rue Paul Vaillant"));
//				document.add(new Paragraph("75013 - Lomé"));
//				document.add(new Paragraph("Tel : 0113289117", currentLightFont));
//				document.add(new Paragraph());
//				Paragraph paragraph = new Paragraph("Dernier avis avant poursuite",maFonte);
//				// paragraph.setIndentationLeft(20f);
//				paragraph.setAlignment(Element.ALIGN_CENTER);
//				Paragraph paragraphDate = new Paragraph("Lomé, le "
//						+ fullDateFormat.format(aujourdhui), maFonte);
//				paragraphDate.setAlignment(Element.ALIGN_RIGHT);
//				document.add(paragraphDate);
//				document.add(Chunk.NEWLINE);
//				document.add(paragraph);
//				document.add(Chunk.NEWLINE);
//				document.add(new Paragraph("A l'attention de Mr "
//						+ currentTransferedClient.getNomPrenom()+","));
//				document.add(new Paragraph(
//						"Nous attirons votre attention sur le fait que vous nous devez la somme de "
//								+ selectedDossier.getMontantRetard() + " "
//								+ "FCFA."));
//				document.add(new Paragraph("Votre dossier présente "
//						+ selectedDossier.getNbreJourRetard() + " " + "jours de retard."));
//				document.add(new Paragraph(
//				"Sans réponse de votre part dans les 8 jours, nous nous verrons dans l'obligation d'engager des poursuites judiciaires."));
//				document.add(new Paragraph(
//						"Merci de nous contacter de toute urgence."));
//				document.add(new Paragraph("Le service recouvrement"));
//				document.add(new Paragraph(getUserName()));
//			} catch (DocumentException de) {
//				de.printStackTrace();
//			} catch (IOException ioe) {
//				ioe.printStackTrace();
//			}
//			document.close();
//			    out.close();
//				InputStream in =new ByteArrayInputStream(((ByteArrayOutputStream)out).toByteArray());
//				StreamedContent streamedContent = new DefaultStreamedContent(in, "application/pdf","lettre.pdf");         
//			    return streamedContent;
//		}
	    
	    
		private void ajouterLigneVide(Paragraph paragraph, int number) {
		    for (int i = 0; i < number; i++) {
		        paragraph.add(new Paragraph(" "));
		    }
		}
	    
		public void resetBouttonVisible()
		{
			boutonTransferedVisible=false;
		}
	    
	public boolean alertIsVisible()
	{
		boolean retour=false;
		List<Promesse> listPromesse=alertService.findPromesseByDate();
		if(listPromesse!=null && !listPromesse.isEmpty())
		{
		List<Client> currentClient=alertService.findClientByCriteria(new Integer(listPromesse.get(0).getIdClient()));
		clientToAlerte=currentClient.get(0).getNomPrenom();
		numeroDossierClientToAlerte=currentClient.get(0).getIdentifiant();
		retour=true;	
		}
		return retour;
	}
	
	
	public String getStatutStyle(Dossierrecouvrement dossierrecouvrement){
		String retourstyle = "label label-warning";
	if(dossierrecouvrement.getStatusTraitement()!=null){
		if(dossierrecouvrement.getStatusTraitement().equals("à traiter"))
		{
			retourstyle=	"primary-cell";
		}
		else if(dossierrecouvrement.getStatusTraitement().equals("Non contactable"))
		{
			retourstyle=	"danger-cell";	
		}
		else 		if(dossierrecouvrement.getStatusTraitement().equals("A transférer"))
		{
			retourstyle=	"warning-cell";	
		}
		else 		if(dossierrecouvrement.getStatusTraitement().equals("Traité"))
		{
			retourstyle=	"success-cell";	
		}
	}
	return retourstyle;
	}
	
	
	public String getStatutPromesseStyle(Dossierrecouvrement dossierrecouvrement){
		String retourstyle = "label label-primary";
	if(dossierrecouvrement.getStatusPromesse()!=null){
		if(dossierrecouvrement.getStatusPromesse().equals("A définir"))
		{
			retourstyle=	"primary-cell";
		}
		else if(dossierrecouvrement.getStatusPromesse().equals("Non Tenue"))
		{
			retourstyle=	"danger-cell";	
		}
		else 		if(dossierrecouvrement.getStatusPromesse().equals("En cours"))
		{
			retourstyle=	"warning-cell";	
		}
		else 		if(dossierrecouvrement.getStatusPromesse().equals("Tenue"))
		{
			retourstyle=	"success-cell";	
		}
	}
	return retourstyle;
	}
	
	
	public String getProfilPayeurStyle(Dossierrecouvrement dossierrecouvrement){
		String retourstyle = "label label-primary";
		if(dossierrecouvrement.getNbreJourRetard()>30)
		{
			retourstyle=	"btn z-depth-0 btn-sm m-0 red-text red lighten-3";
		}
		else if(dossierrecouvrement.getNbreJourRetard()>10)
		{
			retourstyle=	"btn z-depth-0 btn-sm m-0 orange lighten-3";	
		}
		else 		if(dossierrecouvrement.getNbreJourRetard()<10)
		{
			retourstyle=	"btn z-depth-0 btn-sm m-0 green lighten-3";	
		}
		else 		if(dossierrecouvrement.getStatusPromesse().equals("Tenue"))
		{
			retourstyle=	"btn z-depth-0 btn-sm m-0 orange lighten-3";	
		}
	return retourstyle;
	}
	
	
	public String getProfilPayeur(Dossierrecouvrement dossierrecouvrement){
		String retourstyle = "label label-primary";
		if(dossierrecouvrement.getNbreJourRetard()>30)
		{
			retourstyle=	"Mauvais payeur";
		}
		else if(dossierrecouvrement.getNbreJourRetard()>10)
		{
			retourstyle=	"Payeur moyen";	
		}
		else 		if(dossierrecouvrement.getNbreJourRetard()<10)
		{
			retourstyle=	"Bon payeur";	
		}
		else 		if(dossierrecouvrement.getStatusPromesse().equals("Tenue"))
		{
			retourstyle=	"Payeur moyen";	
		}
	return retourstyle;
	}
	
	
	
	public void onIdle() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                                        "Rappel.", "Vous devez rappeler Mr " + clientToAlerte+". "+"Numéro de dossier : "+numeroDossierClientToAlerte));
    }
 
    public void onActive() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                                        "Rappel", "Vous devez rappeler Mr " + clientToAlerte+". "+"Numéro de dossier : "+numeroDossierClientToAlerte));
    }
	
	public void confirmAlert() throws IOException
	{
		FacesContext.getCurrentInstance().getExternalContext().redirect("ficheClient.xhtml");	
	}
	
	public void reportAlert() throws IOException
	{
		FacesContext.getCurrentInstance().getExternalContext().redirect("ficheClient.xhtml");		
	}

	public void setSelectedPatients(List<Patient> selectedPatients) {
		this.selectedPatients = selectedPatients;
	}

	public String getSearchNom() {
		return searchNom;
	}

	public void setSearchNom(String searchNom) {
		this.searchNom = searchNom;
	}

	public String getSmsType() {
		return smsType;
	}

	public void setSmsType(String smsType) {
		this.smsType = smsType;
	}

	public String getSmsCorps() {
		return smsCorps;
	}

	public void setSmsCorps(String smsCorps) {
		this.smsCorps = smsCorps;
	}

	public List<Dossierrecouvrement> getListDossier() {
		return listDossier;
	}

	public void setListDossier(List<Dossierrecouvrement> listDossier) {
		this.listDossier = listDossier;
	}

	public List<Dossierrecouvrement> getSelectedDossiers() {
		return selectedDossiers;
	}

	public void setSelectedDossiers(List<Dossierrecouvrement> selectedDossiers) {
		this.selectedDossiers = selectedDossiers;
	}

	public String getStatutType() {
		return statutType;
	}

	public void setStatutType(String statutType) {
		this.statutType = statutType;
	}

	public User getAutocompleteUser() {
		return autocompleteUser;
	}

	public void setAutocompleteUser(User autocompleteUser) {
		this.autocompleteUser = autocompleteUser;
	}

	public List<Dossierrecouvrement> getListMesTaches() {
		return listMesTaches;
	}

	public void setListMesTaches(List<Dossierrecouvrement> listMesTaches) {
		this.listMesTaches = listMesTaches;
	}

	public String getStatutMestachesType() {
		return statutMestachesType;
	}

	public void setStatutMestachesType(String statutMestachesType) {
		this.statutMestachesType = statutMestachesType;
	}

	public Dossierrecouvrement getSelectedDossier() {
		return selectedDossier;
	}

	public void setSelectedDossier(Dossierrecouvrement selectedDossier) {
		this.selectedDossier = selectedDossier;
	}

	public List<Client> getSelectedClient() {
		return selectedClient;
	}

	public void setSelectedClient(List<Client> selectedClient) {
		this.selectedClient = selectedClient;
	}

	public Client getCurrentClient() {
		return currentClient;
	}

	public void setCurrentClient(Client currentClient) {
		this.currentClient = currentClient;
	}

	public String getStatutPromesse() {
		return statutPromesse;
	}

	public void setStatutPromesse(String statutPromesse) {
		this.statutPromesse = statutPromesse;
	}

	public Date getDatePromesse() {
		return datePromesse;
	}

	public void setDatePromesse(Date datePromesse) {
		this.datePromesse = datePromesse;
	}

	public Date getDateRelance() {
		return dateRelance;
	}

	public void setDateRelance(Date dateRelance) {
		this.dateRelance = dateRelance;
	}

	public String getCommentairePromesse() {
		return commentairePromesse;
	}

	public void setCommentairePromesse(String commentairePromesse) {
		this.commentairePromesse = commentairePromesse;
	}

	public String getTelephoneDomicile() {
		return telephoneDomicile;
	}

	public void setTelephoneDomicile(String telephoneDomicile) {
		this.telephoneDomicile = telephoneDomicile;
	}

	public String getTelephonePortable() {
		return telephonePortable;
	}

	public void setTelephonePortable(String telephonePortable) {
		this.telephonePortable = telephonePortable;
	}

	public String getGlobalFilter() {
		return globalFilter;
	}

	public void setGlobalFilter(String globalFilter) {
		this.globalFilter = globalFilter;
	}

	public Integer getMontantPromesse() {
		return montantPromesse;
	}

	public void setMontantPromesse(Integer montantPromesse) {
		this.montantPromesse = montantPromesse;
	}

	public String getNbreDossierArelancer() {
		return nbreDossierArelancer;
	}

	public void setNbreDossierArelancer(String nbreDossierArelancer) {
		this.nbreDossierArelancer = nbreDossierArelancer;
	}

	public String getNbrePromesseEnregistreJournee() {
		return nbrePromesseEnregistreJournee;
	}

	public void setNbrePromesseEnregistreJournee(
			String nbrePromesseEnregistreJournee) {
		this.nbrePromesseEnregistreJournee = nbrePromesseEnregistreJournee;
	}

	public String getNbrePromesseEnregistreDepuisDebutMois() {
		return nbrePromesseEnregistreDepuisDebutMois;
	}

	public void setNbrePromesseEnregistreDepuisDebutMois(
			String nbrePromesseEnregistreDepuisDebutMois) {
		this.nbrePromesseEnregistreDepuisDebutMois = nbrePromesseEnregistreDepuisDebutMois;
	}

	public String getNbrePromesseActive() {
		return nbrePromesseActive;
	}

	public void setNbrePromesseActive(String nbrePromesseActive) {
		this.nbrePromesseActive = nbrePromesseActive;
	}

	public String getNbrePromesseEchu() {
		return nbrePromesseEchu;
	}

	public void setNbrePromesseEchu(String nbrePromesseEchu) {
		this.nbrePromesseEchu = nbrePromesseEchu;
	}

	public String getNbrePromesseTenu() {
		return nbrePromesseTenu;
	}

	public void setNbrePromesseTenu(String nbrePromesseTenu) {
		this.nbrePromesseTenu = nbrePromesseTenu;
	}

	public String getNbreJourTravaille() {
		return nbreJourTravaille;
	}

	public void setNbreJourTravaille(String nbreJourTravaille) {
		this.nbreJourTravaille = nbreJourTravaille;
	}

	public String getNbreAgent() {
		return nbreAgent;
	}

	public void setNbreAgent(String nbreAgent) {
		this.nbreAgent = nbreAgent;
	}

	public String getNbreAppelEntrant() {
		return nbreAppelEntrant;
	}

	public void setNbreAppelEntrant(String nbreAppelEntrant) {
		this.nbreAppelEntrant = nbreAppelEntrant;
	}

	public String getNbreAppelSortant() {
		return nbreAppelSortant;
	}

	public void setNbreAppelSortant(String nbreAppelSortant) {
		this.nbreAppelSortant = nbreAppelSortant;
	}

	public String getMontantTotalRecu() {
		return montantTotalRecu;
	}

	public void setMontantTotalRecu(String montantTotalRecu) {
		this.montantTotalRecu = montantTotalRecu;
	}

	public List<Dossierrecouvrement> getListMesTachesArelancer() {
		return listMesTachesArelancer;
	}

	public void setListMesTachesArelancer(
			List<Dossierrecouvrement> listMesTachesArelancer) {
		this.listMesTachesArelancer = listMesTachesArelancer;
	}

	public List<Promessetenue> getListPromessetenueJournee() {
		return listPromessetenueJournee;
	}

	public void setListPromessetenueJournee(
			List<Promessetenue> listPromessetenueJournee) {
		this.listPromessetenueJournee = listPromessetenueJournee;
	}

	public List<Promessetenue> getListPromessetenueDepuisDebutDuMois() {
		return listPromessetenueDepuisDebutDuMois;
	}

	public void setListPromessetenueDepuisDebutDuMois(
			List<Promessetenue> listPromessetenueDepuisDebutDuMois) {
		this.listPromessetenueDepuisDebutDuMois = listPromessetenueDepuisDebutDuMois;
	}
	
	
	public List<Promesse> getListPromesseEnregistreJournee() {
		return listPromesseEnregistreJournee;
	}

	public void setListPromesseEnregistreJournee(
			List<Promesse> listPromesseEnregistreJournee) {
		this.listPromesseEnregistreJournee = listPromesseEnregistreJournee;
	}
	
	public List<Promesse> getListPromesseEnregistreDepuisDebutDuMois() {
		return listPromesseEnregistreDepuisDebutDuMois;
	}

	public void setListPromesseEnregistreDepuisDebutDuMois(
			List<Promesse> listPromesseEnregistreDepuisDebutDuMois) {
		this.listPromesseEnregistreDepuisDebutDuMois = listPromesseEnregistreDepuisDebutDuMois;
	}

	public Integer getNumeroDossierClientToAlerte() {
		return numeroDossierClientToAlerte;
	}

	public void setNumeroDossierClientToAlerte(Integer numeroDossierClientToAlerte) {
		this.numeroDossierClientToAlerte = numeroDossierClientToAlerte;
	}

	public String getNbrePromesseTenueJournee() {
		return nbrePromesseTenueJournee;
	}

	public void setNbrePromesseTenueJournee(String nbrePromesseTenueJournee) {
		this.nbrePromesseTenueJournee = nbrePromesseTenueJournee;
	}

	public String getNbrePromesseTenueDepuisDebutMois() {
		return nbrePromesseTenueDepuisDebutMois;
	}

	public void setNbrePromesseTenueDepuisDebutMois(
			String nbrePromesseTenueDepuisDebutMois) {
		this.nbrePromesseTenueDepuisDebutMois = nbrePromesseTenueDepuisDebutMois;
	}

	public String getTypeRelance() {
		return typeRelance;
	}

	public void setTypeRelance(String typeRelance) {
		this.typeRelance = typeRelance;
	}

	public String getTypeDossier() {
		return typeDossier;
	}

	public void setTypeDossier(String typeDossier) {
		this.typeDossier = typeDossier;
	}

	public String getPeriodicite() {
		return periodicite;
	}

	public void setPeriodicite(String periodicite) {
		this.periodicite = periodicite;
	}

	public Dossierrecouvrement getSelectedTransferedDossiers() {
		return selectedTransferedDossiers;
	}

	public void setSelectedTransferedDossiers(
			Dossierrecouvrement selectedTransferedDossiers) {
		this.selectedTransferedDossiers = selectedTransferedDossiers;
	}

	public Client getCurrentTransferedClient() {
		return currentTransferedClient;
	}

	public void setCurrentTransferedClient(Client currentTransferedClient) {
		this.currentTransferedClient = currentTransferedClient;
	}

	public boolean isBoutonTransferedVisible() {
		return boutonTransferedVisible;
	}

	public void setBoutonTransferedVisible(boolean boutonTransferedVisible) {
		this.boutonTransferedVisible = boutonTransferedVisible;
	}

	public List<Historiqueappelsortant> getListHistoriqueappelsortant() {
		return listHistoriqueappelsortant;
	}

	public void setListHistoriqueappelsortant(
			List<Historiqueappelsortant> listHistoriqueappelsortant) {
		this.listHistoriqueappelsortant = listHistoriqueappelsortant;
	}

	public String getNbreAllDossierArelancer() {
		return nbreAllDossierArelancer;
	}

	public void setNbreAllDossierArelancer(String nbreAllDossierArelancer) {
		this.nbreAllDossierArelancer = nbreAllDossierArelancer;
	}

	public List<Actionclient> getListActionclient() {
		return listActionclient;
	}

	public void setListActionclient(List<Actionclient> listActionclient) {
		this.listActionclient = listActionclient;
	}

	public List<Dossierrecouvrement> getListTachesAtransferer() {
		return listTachesAtransferer;
	}

	public void setListTachesAtransferer(
			List<Dossierrecouvrement> listTachesAtransferer) {
		this.listTachesAtransferer = listTachesAtransferer;
	}

	public List<Dossierrecouvrement> getListDossierNonContactable() {
		return listDossierNonContactable;
	}

	public void setListDossierNonContactable(
			List<Dossierrecouvrement> listDossierNonContactable) {
		this.listDossierNonContactable = listDossierNonContactable;
	}

	public String getNbreDossiernonContactable() {
		return nbreDossiernonContactable;
	}

	public void setNbreDossiernonContactable(String nbreDossiernonContactable) {
		this.nbreDossiernonContactable = nbreDossiernonContactable;
	}

	public List<Dossierrecouvrement> getSelectedTransferedDossiersList() {
		return selectedTransferedDossiersList;
	}

	public void setSelectedTransferedDossiersList(
			List<Dossierrecouvrement> selectedTransferedDossiersList) {
		this.selectedTransferedDossiersList = selectedTransferedDossiersList;
	}

	public String getStatutTraitement() {
		return statutTraitement;
	}

	public void setStatutTraitement(String statutTraitement) {
		this.statutTraitement = statutTraitement;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNbreMesTaches() {
		return nbreMesTaches;
	}

	public void setNbreMesTaches(String nbreMesTaches) {
		this.nbreMesTaches = nbreMesTaches;
	}

	public String getNbreTransfertDossier() {
		return nbreTransfertDossier;
	}

	public void setNbreTransfertDossier(String nbreTransfertDossier) {
		this.nbreTransfertDossier = nbreTransfertDossier;
	}

	public List<RelanceBean> getListRelance() {
		return listRelance;
	}

	public void setListRelance(List<RelanceBean> listRelance) {
		this.listRelance = listRelance;
	}

	public int getProfilemauvaispayeur() {
		return profilemauvaispayeur;
	}

	public void setProfilemauvaispayeur(int profilemauvaispayeur) {
		this.profilemauvaispayeur = profilemauvaispayeur;
	}

	public int getProfiletressmauvaispayeur() {
		return profiletressmauvaispayeur;
	}

	public void setProfiletressmauvaispayeur(int profiletressmauvaispayeur) {
		this.profiletressmauvaispayeur = profiletressmauvaispayeur;
	}

	public int getProfilebonpayeur() {
		return profilebonpayeur;
	}

	public void setProfilebonpayeur(int profilebonpayeur) {
		this.profilebonpayeur = profilebonpayeur;
	}

	public int getProfiletresbonpayeur() {
		return profiletresbonpayeur;
	}

	public void setProfiletresbonpayeur(int profiletresbonpayeur) {
		this.profiletresbonpayeur = profiletresbonpayeur;
	}

	public String getNomIntervenant() {
		return nomIntervenant;
	}

	public void setNomIntervenant(String nomIntervenant) {
		this.nomIntervenant = nomIntervenant;
	}

	public String getTypeIntervenant() {
		return typeIntervenant;
	}

	public void setTypeIntervenant(String typeIntervenant) {
		this.typeIntervenant = typeIntervenant;
	}

	public String getTelephoneIntervenant() {
		return telephoneIntervenant;
	}

	public void setTelephoneIntervenant(String telephoneIntervenant) {
		this.telephoneIntervenant = telephoneIntervenant;
	}

	public String getEmailIntervenant() {
		return emailIntervenant;
	}

	public void setEmailIntervenant(String emailIntervenant) {
		this.emailIntervenant = emailIntervenant;
	}

	public String getModeReglement() {
		return modeReglement;
	}

	public void setModeReglement(String modeReglement) {
		this.modeReglement = modeReglement;
	}

	public String getTypeReglement() {
		return typeReglement;
	}

	public void setTypeReglement(String typeReglement) {
		this.typeReglement = typeReglement;
	}

	public String getDeviseReglement() {
		return deviseReglement;
	}

	public void setDeviseReglement(String deviseReglement) {
		this.deviseReglement = deviseReglement;
	}

	public int getMontantReglement() {
		return montantReglement;
	}

	public void setMontantReglement(int montantReglement) {
		this.montantReglement = montantReglement;
	}

	public String getCommentaireReglement() {
		return commentaireReglement;
	}

	public void setCommentaireReglement(String commentaireReglement) {
		this.commentaireReglement = commentaireReglement;
	}

	public List<Dossiercontentieux> getListDossiercontentieux() {
		return listDossiercontentieux;
	}

	public void setListDossiercontentieux(
			List<Dossiercontentieux> listDossiercontentieux) {
		this.listDossiercontentieux = listDossiercontentieux;
	}

	public List<Intervenant> getListIntervenant() {
		return listIntervenant;
	}

	public void setListIntervenant(List<Intervenant> listIntervenant) {
		this.listIntervenant = listIntervenant;
	}

	public String getIntervenantSelected() {
		return intervenantSelected;
	}

	public void setIntervenantSelected(String intervenantSelected) {
		this.intervenantSelected = intervenantSelected;
	}

	public String getTypeIntervenantSelected() {
		return typeIntervenantSelected;
	}

	public void setTypeIntervenantSelected(String typeIntervenantSelected) {
		this.typeIntervenantSelected = typeIntervenantSelected;
	}
	
	public String getAdresseIntervenant() {
		return adresseIntervenant;
	}

	public void setAdresseIntervenant(String adresseIntervenant) {
		this.adresseIntervenant = adresseIntervenant;
	}

	public int getMontantTotalEncours() {
		return montantTotalEncours;
	}

	public void setMontantTotalEncours(int montantTotalEncours) {
		this.montantTotalEncours = montantTotalEncours;
	}

	public int getMontantTotalRetard() {
		return montantTotalRetard;
	}

	public void setMontantTotalRetard(int montantTotalRetard) {
		this.montantTotalRetard = montantTotalRetard;
	}

	public String getSelectedDossierAsString() {
		return selectedDossierAsString;
	}

	public void setSelectedDossierAsString(String selectedDossierAsString) {
		this.selectedDossierAsString = selectedDossierAsString;
	}

	public String getSelectedDossierContentieuxAsString() {
		return selectedDossierContentieuxAsString;
	}

	public void setSelectedDossierContentieuxAsString(
			String selectedDossierContentieuxAsString) {
		this.selectedDossierContentieuxAsString = selectedDossierContentieuxAsString;
	}

	public List<String> getListIntervenantAsString() {
		return listIntervenantAsString;
	}

	public void setListIntervenantAsString(List<String> listIntervenantAsString) {
		this.listIntervenantAsString = listIntervenantAsString;
	}

	public Intervenant getSelectedIntervenant() {
		return selectedIntervenant;
	}

	public void setSelectedIntervenant(Intervenant selectedIntervenant) {
		this.selectedIntervenant = selectedIntervenant;
	}

	public Dossiercontentieux getDossiercontentieuxSelected() {
		return dossiercontentieuxSelected;
	}

	public void setDossiercontentieuxSelected(
			Dossiercontentieux dossiercontentieuxSelected) {
		this.dossiercontentieuxSelected = dossiercontentieuxSelected;
	}

	public String getTypeReglementSelected() {
		return typeReglementSelected;
	}

	public void setTypeReglementSelected(String typeReglementSelected) {
		this.typeReglementSelected = typeReglementSelected;
	}

	public List<RelanceBean> getListRelancePromesse() {
		return listRelancePromesse;
	}

	public void setListRelancePromesse(List<RelanceBean> listRelancePromesse) {
		this.listRelancePromesse = listRelancePromesse;
	}

	public List<RelanceBean> getListRelanceBonPayeur() {
		return listRelanceBonPayeur;
	}

	public void setListRelanceBonPayeur(List<RelanceBean> listRelanceBonPayeur) {
		this.listRelanceBonPayeur = listRelanceBonPayeur;
	}

	public List<RelanceBean> getListRelanceMauvaisPayeur() {
		return listRelanceMauvaisPayeur;
	}

	public void setListRelanceMauvaisPayeur(
			List<RelanceBean> listRelanceMauvaisPayeur) {
		this.listRelanceMauvaisPayeur = listRelanceMauvaisPayeur;
	}

	public String getModeReglementSelected() {
		return modeReglementSelected;
	}

	public void setModeReglementSelected(String modeReglementSelected) {
		this.modeReglementSelected = modeReglementSelected;
	}

	public List<Intervenantdossier> getListIntervenantByDossier() {
		return listIntervenantByDossier;
	}

	public void setListIntervenantByDossier(
			List<Intervenantdossier> listIntervenantByDossier) {
		this.listIntervenantByDossier = listIntervenantByDossier;
	}

	public List<IntervenantDossierBean> getListIntervenantDossierBean() {
		return listIntervenantDossierBean;
	}

	public void setListIntervenantDossierBean(
			List<IntervenantDossierBean> listIntervenantDossierBean) {
		this.listIntervenantDossierBean = listIntervenantDossierBean;
	}

	public List<Dossierrecouvrement> getListDossierCloture() {
		return listDossierCloture;
	}

	public void setListDossierCloture(List<Dossierrecouvrement> listDossierCloture) {
		this.listDossierCloture = listDossierCloture;
	}

	

}
