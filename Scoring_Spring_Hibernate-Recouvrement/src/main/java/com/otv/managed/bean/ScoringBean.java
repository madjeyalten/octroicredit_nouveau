package com.otv.managed.bean;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.FlowEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.ToggleEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.otv.model.mod.Client;
import com.otv.model.mod.Credit;
import com.otv.model.mod.Depensecourante;
import com.otv.model.mod.Historique;
import com.otv.model.mod.Revenuordepense;
import com.otv.model.mod.Scoreparametrage;
import com.otv.model.mod.Seuilscore;
import com.otv.model.mod.User;
import com.otv.user.service.DepenseService;
import com.otv.user.service.ScoringService;

import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.Image;

/**
 * 
 * User Managed Bean
 * 
 * @author onlinetechvision.com
 * @since 25 Mar 2012
 * @version 1.0.0
 *
 */

/**
 * @author dell
 * 
 */
@ManagedBean(name = "scoringBean")
@Component("scoringBean")
@SessionScoped
public class ScoringBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final String SUCCESS = "success";
	private static final String ERROR = "error";
	private String login;
	private String mdpasse;
	private String loginAndPassword;
	private String nom;
	private String prenom;
	private String listeDeroulante="";
	private String valeurListeDeroulante="";
	private String nomClient;
	private String numeroDossier;
	private boolean skip;
	/**
	 * Client
	 */
	private Integer identifiant;
	private String civilite;
	private String prenomClient;
	private Date dateNaissance;
	private String communeNaissance;
	private String adresseRue;
	private String adressecodePostal;
	private String adressePays;
	private String telephoneDomicile;
	private String telephonePortable;
	private String email;
	private String situationFamilial;
	private Integer nombreEnfant;
	private Date dateNaissancePremierEnfant;
	private Date dateNaissanceDeuxiemeEnfant;
	private String modeHabitation;
	private Integer nombrePersonneEnCharge;
	private String profession;
	private String anciennete;
	private String nomEmployeur;
	private String typeContrat;
	private String revenuPension;
	private String chargeLoyer;
	private String chargePremierPret;
	private String chargeDeuxiemePret;
	private Date date;
	private String categorieSocioProfessionel;
	private String secteurActivite;
	private String paysNaissance;
	private String adresseVille;
	private String telephoneBureau;
	
	/**
	 * Scoring
	 */
	
	private Integer scoreHomme;
	private Integer scoreFemme;
	private Boolean actifBooleanHomme;
	private Boolean actifBooleanFemme;
	private Integer scoreOuvrier;
	private Boolean actifBooleanOuvrier;
	private Integer scoreCadre;
	private Boolean actifBooleanCadre;
	private Integer scoreFonctionPublique;
	private Boolean actifBooleanFonctionPublique;
	private Integer scoreSecteurPrive;
	private Boolean actifBooleanSecteurPrive;
	private Integer scoreCelibataire;
	private Boolean actifBooleanCelibataire;
	private Integer scoreMarie;
	private Boolean actifBooleanMarie;
	private Integer scoreLocataire;
	private Boolean actifBooleanLocataire;
	private Integer scorePropriétaire;
	private Boolean actifBooleanPropriétaire;
	private Integer revenuSup;
	private Boolean actifBooleanRevenuSup;
	private Integer revenuInf;
	private Boolean actifBooleanRevenuInf;
	private Integer nombreEnfantInf;
	private Boolean actifBooleanEnfantInf;
	private Integer nombreEnfantSup;
	private Boolean actifBooleanEnfantSup;
	private Integer ageInf;
	private Boolean actifBooleanAgeInf;
	private Integer ageSup;
	private Boolean actifBooleanAgeSup;
	
	//autocomplete
	private String exemple;
	
	/**
	 * Seuil score
	 */
	private Integer seuilscoreAccepte;
	private Integer seuilscoreRefuse;
	
	//eligibilite
	private String credit;
	private String activitePro;
	private String caution;
	private String demandeCredit;
	private String carteBancaire;
	private String impaye;
	
	
	//Simulation
	double capital;
	Float taux;
	int nbreMois;
	double mensualite;
	double coutTotal;
	String coutTotalOutput;
	String capitalOutput;
	String interetOutput;
	String mensualiteOutput;
	
	String mensualiteMaximum;
	
	//Taux endettement
	String tauxEndettementAvantCredit;
	String tauxEndettementApresCredit;
	
	//Recherche
	private Integer searchIdentifiant;
	private String searchNom;
	
	//commentaire
	private String commentaire;
	
	//User
	private User user;
	private String currentUer;
	private User autocompleteUser;
	
	//creationUtilisateur
	private String nomUtilisateur;
	private String prenomUtilisateur;
	private String emailUtilisateur;
	private String loginUtilisateur;
	private String mdpUtilisateur;
	
	
	public List<EligibiliteBean> listEligibilite=new ArrayList<EligibiliteBean>();
	public List<Client> listSearchClient=new ArrayList<Client>();
	public List<ArmotissementBean> listArmotissementBean=new ArrayList<ArmotissementBean>();
	public List<Credit> listCredit=new ArrayList<Credit>();
	public List<Piece> listPiece=new ArrayList<Piece>();
	
	//liste des utilisateurs
	public List<String> listUser=new ArrayList<String>();
	
	
	
	//historique
	public List<Historique> listHistorique=new ArrayList<Historique>();
	
	private Client selectedClient;
	private Credit selectedCredit;
	
	private boolean showTable;
	private boolean showAmortissement;
	private boolean showSynthese;
	
	public List<String> listCivilites=new ArrayList<String>();
	
	StreamedContent editerContratPdf;


	@Autowired
	private transient ScoringService scoringService;

	private int id;
	private String name;

	@PostConstruct
	public void init() {
		listPiece.clear();
		
		//Utilisateurs
		
		 List<User> allUsers = scoringService.findAllUser();
		 for (User user : allUsers)
		 {
		 listUser.add(user.getNom()+ " "+user.getPrenom());	
		 }
		
		//licence
		listCivilites.add("Monsieur");
		listCivilites.add("Madame");
		Piece piece=new Piece();
		piece.setLabel("Pièce d'identité emprunteur");
		listPiece.add(piece);
		piece=new Piece();
		piece.setLabel("Bulletin de salaire emprunteur");
		listPiece.add(piece);
		piece=new Piece();
		piece.setLabel("Relevé de compte emprunteur");
		listPiece.add(piece);
		piece=new Piece();
		piece.setLabel("Justificatif de domicile");
		listPiece.add(piece);
		Date dateExpiration=new Date(2027, 12, 31);
		if(new Date().after(dateExpiration))
		{
			return;
		}
		initEligibilite();
	}

	public void initEligibilite()
	{
		//1
		EligibiliteBean eligibiliteBean=new EligibiliteBean();
		eligibiliteBean.setQuestion("Avez-vous des crédits en cours");
		eligibiliteBean.setReponse1("Oui");
		eligibiliteBean.setReponse2("Non");
		listEligibilite.add(eligibiliteBean);
		//2
		EligibiliteBean eligibiliteBean2=new EligibiliteBean();
		eligibiliteBean2.setQuestion("Exercez-vous une activité professionnelle?");
		eligibiliteBean2.setReponse1("Oui");
		eligibiliteBean2.setReponse2("Non");
		listEligibilite.add(eligibiliteBean2);
		//3
		EligibiliteBean eligibiliteBean3=new EligibiliteBean();
		eligibiliteBean3.setQuestion("Avez-vous des crédits impayés?");
		eligibiliteBean3.setReponse1("Oui");
		eligibiliteBean3.setReponse2("Non");
		listEligibilite.add(eligibiliteBean3);
		//4
		EligibiliteBean eligibiliteBean4=new EligibiliteBean();
		eligibiliteBean4.setQuestion("Avez-vous un co-emprunteur ou une caution solidaire");
		eligibiliteBean4.setReponse1("Oui");
		eligibiliteBean4.setReponse2("Non");
		listEligibilite.add(eligibiliteBean4);
		
	
	}


	 public void onRowSelect(SelectEvent event) {  
		 System.out.println("Suppression");
//	        FacesMessage msg = new FacesMessage("Simulation selected Selected", ((Simulation1) event.getObject()).getId().toString());  
//	        try {
//				FacesContext.getCurrentInstance().getExternalContext().dispatch("simulation1.xhtml");
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
	    } 
	 
	 public void onRowDblClckSelect(final SelectEvent event) {
		    selectedClient = (Client) event.getObject();
		 try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("dossierModification.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		    RequestContext context = RequestContext.getCurrentInstance();
	        context.execute("confirmDialog.show();");
		    System.out.println("");
		}
	 
	 public void onRowDblClckCreditSelect(final SelectEvent event) {
		    selectedCredit = (Credit) event.getObject();
		 try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("creditUpdate.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}

	public String connect() throws IOException {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		
		resetClient();
		listCredit=scoringService.findCreditByEnvoyerVers(getUserName());
		FacesContext.getCurrentInstance().getExternalContext().redirect("eligibilite.xhtml");
		if (scoringService.checkHabilitation(login, mdpasse)) {
			return "connect";
		} else {
			return "";
		}
	}
	
	
	public String createDossier() throws IOException {
		//licence
		Date dateExpiration=new Date(2027, 12, 31);
		if(new Date().after(dateExpiration))
		{
			return "";
		}
		FacesContext.getCurrentInstance().getExternalContext().redirect("dossier.xhtml");
		if (scoringService.checkHabilitation(login, mdpasse)) {
			return "connect";
		} else {
			return "";
		}
	}
	
	
	public String validerEligibilite() throws IOException{
		if("Non".equals(activitePro))
		{
		FacesContext.getCurrentInstance().addMessage("eligibilite", new FacesMessage(FacesMessage.SEVERITY_INFO,"Demande refusée :", "L'exercice d'une activité professionelle est nécessaire à l'obtention d'un crédit"));
		}
		else
		{
			FacesContext.getCurrentInstance().getExternalContext().redirect("recherche.xhtml");
		}
		return "eligibilite";
	}
	
	public String approuverCredit() throws IOException{
		if(selectedCredit!=null)
		{
		selectedCredit.setStatut("Approuvé");
		scoringService.saveCredit(selectedCredit);
		//initialisatiion
		init();
		FacesContext.getCurrentInstance().getExternalContext().redirect("tache.xhtml");
		}
		return "approuverCredit";
	}
	
	public String validerCredit() throws IOException{
		if(selectedCredit!=null)
		{
		selectedCredit.setStatut("Validé");
		scoringService.saveCredit(selectedCredit);
		//appel méthode d'historisation
		saveHistorique(selectedCredit);
		//initialisatiion
		init();
		FacesContext.getCurrentInstance().getExternalContext().redirect("tache.xhtml");
		}
		return "validerCredit";
	}
	
	
	public String showHistorique() throws IOException{
		if(selectedCredit!=null)
		{
        listHistorique=scoringService.findHistoriqueById(selectedCredit.getIdentifiant());
		//initialisatiion
		init();
		FacesContext.getCurrentInstance().getExternalContext().redirect("historique.xhtml");
		}
		return "showHistorique";
	}
	
	public String refuserCredit() throws IOException{
		if(selectedCredit!=null)
		{
		selectedCredit.setStatut("Refusé");
		scoringService.saveCredit(selectedCredit);
		//appel méthode d'historisation
		saveHistorique(selectedCredit);
		//initialisatiion
		init();
		FacesContext.getCurrentInstance().getExternalContext().redirect("tache.xhtml");
		}
		return "refuserCredit";
	}
	
	public String deconnect() throws IOException
	{
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
        
        //
        return "deconnexion";
	}
	
	public String rechercher() throws IOException
	{
		
		 capital=0;
		 taux=0f;
		 nbreMois=0;
		 mensualiteOutput="";
		 coutTotalOutput="";
		//licence
		Date dateExpiration=new Date(2027, 12, 31);
		if(new Date().after(dateExpiration))
		{
			return"";
		}
		showTable=true;
		
		//réiitialisation des champs de la simulation
		showAmortissement=false;
		showSynthese=false;
		listSearchClient=scoringService.findClientByCriteria(searchNom,searchIdentifiant);
		FacesContext.getCurrentInstance().getExternalContext().redirect("recherche.xhtml");
		return "rechercher";	
	}
	
	public String simulateCredit() throws IOException
	{
		FacesContext.getCurrentInstance().getExternalContext().redirect("simulationCredit.xhtml");
		return "simulation";	
	}
	
	
	public void onCellEdit(CellEditEvent event) {
		 DataTable dataTable =  (DataTable) event.getSource();
	}
	
	public String createFolder() throws IOException{
		Client client=new Client();
		client.setAdressecodePostal(adressecodePostal);
		client.setAdressePays(adressePays);
		client.setAdresseRue(adresseRue);
		client.setAnciennete(anciennete);
		client.setChargeDeuxiemePret(chargeDeuxiemePret);
		client.setChargeLoyer(chargeLoyer);
		client.setChargePremierPret(chargePremierPret);
		client.setCivilite(civilite);
		client.setCommuneNaissance(communeNaissance);
		client.setDate(date);
		client.setDateNaissance(dateNaissance);
		client.setDateNaissanceDeuxiemeEnfant(dateNaissanceDeuxiemeEnfant);
		client.setDateNaissancePremierEnfant(dateNaissancePremierEnfant);
		client.setEmail(email);
		client.setIdentifiant(identifiant);
		client.setModeHabitation(modeHabitation);
		client.setNom(nomClient);
		client.setNombreEnfant(nombreEnfant);
		client.setNombrePersonneEnCharge(nombrePersonneEnCharge);
		client.setNomEmployeur(nomEmployeur);
		client.setPrenom(prenomClient);
		client.setProfession(profession);
		client.setRevenuPension(revenuPension);
		client.setSituationFamilial(situationFamilial);
		client.setTelephoneDomicile(telephoneDomicile);
		client.setTelephonePortable(telephonePortable);
		client.setTypeContrat(typeContrat);
		client.setCategorieSocioProfessionel(categorieSocioProfessionel);
		client.setSecteurActivite(secteurActivite);
		client.setTypeContrat(typeContrat);	
		client.setTelephoneBureau(telephoneBureau);
		Integer scoreClient= computeScore(client);
		client.setScoreClient(scoreClient);
		scoringService.saveClient(client);
		resetClient(client);
		FacesContext.getCurrentInstance().getExternalContext().redirect("recherche.xhtml");
		return "eligibilite";
	   }
	
	public void resetClient(Client client)
	{
		adressecodePostal="";
		adressePays="";
		adresseRue="";
		anciennete=null;
		chargeDeuxiemePret="";
		chargeLoyer="";
		chargePremierPret="";
		civilite="";
		communeNaissance="";
		date=null;
		dateNaissance=null;
		dateNaissanceDeuxiemeEnfant=null;
		dateNaissancePremierEnfant=null;
		email="";
		modeHabitation="";
		nom="";
		nombreEnfant=null;
		nombrePersonneEnCharge=null;
		nomEmployeur="";
		prenom="";
		profession="";
		revenuPension="";
		situationFamilial="";
		telephoneDomicile="";
		telephonePortable="";
		typeContrat="";
		categorieSocioProfessionel="";
		secteurActivite="";
		typeContrat="";	
		searchNom="";	
		searchIdentifiant=null;
		telephoneBureau="";
	}
	
	
	public void resetClient()
	{
		adressecodePostal="";
		adressePays="";
		adresseRue="";
		anciennete=null;
		chargeDeuxiemePret="";
		chargeLoyer="";
		chargePremierPret="";
		civilite="";
		communeNaissance="";
		date=null;
		dateNaissance=null;
		dateNaissanceDeuxiemeEnfant=null;
		dateNaissancePremierEnfant=null;
		email="";
		modeHabitation="";
		nom="";
		nombreEnfant=null;
		nombrePersonneEnCharge=null;
		nomEmployeur="";
		prenom="";
		profession="";
		revenuPension="";
		situationFamilial="";
		telephoneDomicile="";
		telephonePortable="";
		typeContrat="";
		categorieSocioProfessionel="";
		secteurActivite="";
		typeContrat="";	
		searchNom="";	
		searchIdentifiant=null;
		telephoneBureau="";
		commentaire="";
		
		//Mise à jour éligibilité
		 credit="";
		 activitePro="";
		 caution="";
		 demandeCredit="";
		 carteBancaire="";
		 impaye="";
		 showTable=false;
		 showAmortissement=false;
		 showSynthese=false;
	}
	
	private Integer computeScore(Client client) {
		Integer score=new Integer(0);
		Scoreparametrage scoreparametrage=null;
		List<Scoreparametrage> listScoreParametrage=new ArrayList<Scoreparametrage>();
		listScoreParametrage=scoringService.findsaveScoringParametrage();
		if(listScoreParametrage!=null && !listScoreParametrage.isEmpty())
		{
		scoreparametrage=listScoreParametrage.get(0);	
		}
		if(client.getCivilite().equals("Monsieur"))
		{
			score=score+scoreparametrage.getScoreHomme();
		}
		else
		{
		score=score+scoreparametrage.getScoreFemme();
		}		
		if(client.getCategorieSocioProfessionel().equals("Cadre"))
		{
		score=score+scoreparametrage.getScoreCadre();
		}
		if(client.getCategorieSocioProfessionel().equals("Ouvrier"))
		{
		score=score+scoreparametrage.getScoreOuvrier();
		}
		if(client.getSecteurActivite().equals("PRIVE"))
		{
		score=score+scoreparametrage.getScoreSecteurPrive();
		}
		if(client.getSecteurActivite().equals("PUBLIC"))
		{
		score=score+scoreparametrage.getScoreFonctionPublique();
		}
		if(client.getModeHabitation().equals("PROP"))
		{
		score=score+scoreparametrage.getScorePropriétaire();	
		}
		if(client.getModeHabitation().equals("LOC"))
		{
		score=score+scoreparametrage.getScoreLocataire();	
		}
		if(client.getSituationFamilial().equals("Marié"))
		{
		score=score+scoreparametrage.getScoreMarie();		
		}		
		//FIXME pour le score Revenu supérieur, Nombre d'enfant, Moins de 30 ans		
		return score;
	}

	public String createUtilisateur() throws IOException{
		User user=new User();
		user.setNom(nomUtilisateur);
		user.setPrenom(prenomUtilisateur);
		user.setEmail(emailUtilisateur);
		user.setLogin(loginUtilisateur);
		user.setMdpasse(mdpUtilisateur);
		scoringService.saveUser(user);
		reset();
		FacesContext.getCurrentInstance().addMessage("userForm", new FacesMessage(FacesMessage.SEVERITY_INFO,"Création utilisateur :", "L'utilisateur a bien été crée"));
		return "createUser";
	   }
	
	public void reset() {
		nomUtilisateur=null;
		prenomUtilisateur=null;
		emailUtilisateur=null;
		loginUtilisateur=null;
		mdpUtilisateur=null;
	}

	
	
	public String createScore() throws IOException{
		Scoreparametrage scoreparametrage= new Scoreparametrage();
		scoreparametrage.setActifBooleanAgeInf(actifBooleanAgeInf);
		scoreparametrage.setActifBooleanAgeSup(actifBooleanAgeSup);
		scoreparametrage.setActifBooleanCadre(actifBooleanCadre);
		scoreparametrage.setActifBooleanCelibataire(actifBooleanCelibataire);
		scoreparametrage.setActifBooleanEnfantInf(actifBooleanEnfantInf);
		scoreparametrage.setActifBooleanEnfantSup(actifBooleanEnfantSup);
		scoreparametrage.setActifBooleanFemme(actifBooleanFemme);
		scoreparametrage.setActifBooleanHomme(actifBooleanHomme);
		scoreparametrage.setActifBooleanLocataire(actifBooleanLocataire);
		scoreparametrage.setActifBooleanMarie(actifBooleanMarie);
		scoreparametrage.setActifBooleanOuvrier(actifBooleanOuvrier);
		scoreparametrage.setActifBooleanPropriétaire(actifBooleanPropriétaire);
		scoreparametrage.setActifBooleanRevenuInf(actifBooleanRevenuInf);
		scoreparametrage.setActifBooleanRevenuSup(actifBooleanRevenuInf);
		scoreparametrage.setActifBooleanSecteurPrive(actifBooleanSecteurPrive);
		
		scoreparametrage.setAgeInf(ageInf);
		scoreparametrage.setAgeSup(ageSup);
		scoreparametrage.setNombreEnfantInf(nombreEnfantInf);
		scoreparametrage.setNombreEnfantSup(nombreEnfantSup);
		scoreparametrage.setRevenuInf(revenuInf);
		scoreparametrage.setRevenuSup(revenuSup);
		scoreparametrage.setScoreCadre(scoreCadre);
		scoreparametrage.setScoreCelibataire(scoreCelibataire);
		scoreparametrage.setScoreFemme(scoreFemme);
		scoreparametrage.setScoreFonctionPublique(scoreFonctionPublique);
		scoreparametrage.setScoreHomme(scoreHomme);
		scoreparametrage.setScoreLocataire(scoreLocataire);
		scoreparametrage.setScoreMarie(scoreMarie);
		scoreparametrage.setScoreOuvrier(scoreOuvrier);
		scoreparametrage.setScorePropriétaire(scorePropriétaire);
		scoreparametrage.setScoreSecteurPrive(scoreSecteurPrive);
		scoreparametrage.setIdentifiant("1");
		scoringService.saveScoringParametrage(scoreparametrage);
		FacesContext.getCurrentInstance().getExternalContext().redirect("recherche.xhtml");
		return "eligibilite";
	}
	
	
	public String createSeuilScore() throws IOException{
		Seuilscore seuilscore=new Seuilscore();
		seuilscore.setSeuilcoreAccepte(seuilscoreAccepte);
		seuilscore.setSeuilscoreRefuse(seuilscoreRefuse);
		scoringService.saveSeuilScore(seuilscore);
		FacesContext.getCurrentInstance().getExternalContext().redirect("recherche.xhtml");
		return "eligibilite";
		
	}
		
	
	public void afficheAmortissement() throws IOException
	{
		showAmortissement=true;
		showSynthese=false;
		FacesContext.getCurrentInstance().getExternalContext().redirect("simulationCredit.xhtml");
	}
	
	public void afficherSynthese() throws IOException
	{
		//Calcul du taux d'endettement
		Integer revenu=null;
		Integer loyer=null;
		
		
		if(selectedClient.getRevenuPension()!=null && !selectedClient.getRevenuPension().equals(""))
		{
		revenu=Integer.parseInt(selectedClient.getRevenuPension());
		}
		if(selectedClient.getChargeLoyer()!=null && !selectedClient.getChargeLoyer().equals(""))
		{
		loyer=Integer.parseInt(selectedClient.getChargeLoyer());	
		}
		if(revenu!=null && loyer!=null)
		{
		DecimalFormat df = new DecimalFormat ( ) ;
		df.setMaximumFractionDigits ( 2 ) ; //arrondi à 2 chiffres apres la virgules
		df.setMinimumFractionDigits ( 2 ) ;
		df.setDecimalSeparatorAlwaysShown ( true ) ;
		//df.format(((float)loyer/(float)revenu)*100)
		tauxEndettementAvantCredit= df.format(((float)loyer/(float)revenu)*100)+" "+"%";
		}
		
		if(revenu!=null && loyer!=null)
		{
		DecimalFormat df = new DecimalFormat ( ) ;
		df.setMaximumFractionDigits ( 2 ) ; //arrondi à 2 chiffres apres la virgules
		df.setMinimumFractionDigits ( 2 ) ;
		df.setDecimalSeparatorAlwaysShown ( true ) ;
		tauxEndettementApresCredit=df.format(((float)(loyer+mensualite)/(float)revenu)*100)+" "+"%"; 
		}
		
		//mensualité maximum
		if(revenu!=null)
		{
		DecimalFormat df = new DecimalFormat ( ) ;
		df.setMaximumFractionDigits ( 2 ) ; //arrondi à 2 chiffres apres la virgules
		df.setMinimumFractionDigits ( 2 ) ;
		df.setDecimalSeparatorAlwaysShown ( true ) ;
		mensualiteMaximum=df.format((float)(revenu*33/100))+"";
		}

		showAmortissement=false;
		showSynthese=true;
		FacesContext.getCurrentInstance().getExternalContext().redirect("simulationCredit.xhtml");
	}
	
	
	public void displaySyntheseAfterCalculate() throws IOException
	{
		//Calcul du taux d'endettement
		Integer revenu=null;
		Integer loyer=null;
		
		
		if(selectedClient.getRevenuPension()!=null && !selectedClient.getRevenuPension().equals(""))
		{
		revenu=Integer.parseInt(selectedClient.getRevenuPension());
		}
		if(selectedClient.getChargeLoyer()!=null && !selectedClient.getChargeLoyer().equals(""))
		{
		loyer=Integer.parseInt(selectedClient.getChargeLoyer());	
		}
		if(revenu!=null && loyer!=null)
		{
		DecimalFormat df = new DecimalFormat ( ) ;
		df.setMaximumFractionDigits ( 2 ) ; //arrondi à 2 chiffres apres la virgules
		df.setMinimumFractionDigits ( 2 ) ;
		df.setDecimalSeparatorAlwaysShown ( true ) ;
		//df.format(((float)loyer/(float)revenu)*100)
		tauxEndettementAvantCredit= df.format(((float)loyer/(float)revenu)*100)+" "+"%";
		}
		
		if(revenu!=null && loyer!=null)
		{
		DecimalFormat df = new DecimalFormat ( ) ;
		df.setMaximumFractionDigits ( 2 ) ; //arrondi à 2 chiffres apres la virgules
		df.setMinimumFractionDigits ( 2 ) ;
		df.setDecimalSeparatorAlwaysShown ( true ) ;
		tauxEndettementApresCredit=df.format(((float)(loyer+mensualite)/(float)revenu)*100)+" "+"%"; 
		}	
	}
	
	public void computeSimulation() throws IOException
	{
		if(listArmotissementBean!=null)
		{
			listArmotissementBean.clear();
		}
		double interest ;
		double principal ;
		int numPayments ;
		double balance ;
		double payment ;
		
		interest = taux/1200 ;
		principal = capital ;
		numPayments = nbreMois ;
		
		double curInterest = 0, curPrincipal = 0 ;
		double totalPayments = 0, totalPrincipal = 0, totalInterest = 0 ;
		
		payment = (principal * interest) / (1 - Math.pow((1 + interest), -numPayments)) ;
		mensualite=payment;
		coutTotal=mensualite*nbreMois;
		
		DecimalFormat f = new DecimalFormat();
		f.setMaximumFractionDigits(2);
		coutTotalOutput=f.format(coutTotal);
		mensualiteOutput=f.format(mensualite);
		 
		balance = principal ; curInterest = 0 ;
		System.out.println ("Period, Payment, Principal, Interest, Balance") ;
		System.out.println (1+" "+payment+" "+principal+" "+interest+" "+balance);

		//Loop through each payment period.

		for (int period = 1 ; period <= numPayments ; period++) {

		//Calculate the interest portion of the current payment.

		curInterest = balance * interest ;

		//For the final payment, adjust for rounding by setting the payment to the current interest portion plus the outstanding balance.

		if (period == numPayments) {

		payment = balance + curInterest ;

		}

		//Calculate the current principal payment and the current balance. Print out the results.

		curPrincipal = payment - curInterest ;

		balance -= curPrincipal ;
		
		NumberFormat format=NumberFormat.getInstance();
		format.setMaximumFractionDigits(2); //nb de chiffres apres la virgule

		String periodFormat=format.format(period);
		String curPrincipalFormat=format.format(curPrincipal);
		String curInterestFormat=format.format(curInterest);
		String balanceFormat=format.format(balance);
		String paymentFormat=format.format(payment);
		
		//Gestion mois
        Calendar currentMonth = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM yyyy");
        System.out.println("Curret month of date : "
                + dateFormat.format(currentMonth.getTime()));
        // Increment month
        currentMonth.add(Calendar.MONTH, period);
        System.out.println("Next month : "
        + dateFormat.format(currentMonth.getTime()));

		
   	 ArmotissementBean armotissementBean=new ArmotissementBean();
	 armotissementBean.setMois(dateFormat.format(currentMonth.getTime()));
	 armotissementBean.setCapital(balanceFormat);
	 armotissementBean.setRemboursement(paymentFormat);
	 armotissementBean.setInteret(curInterestFormat);
	 listArmotissementBean.add(armotissementBean);
	}
		
	//Affichage du tableau de synthèse après calcul de la simulation
	displaySyntheseAfterCalculate();
	FacesContext.getCurrentInstance().getExternalContext().redirect("simulationCredit.xhtml");
	}
	

	
	
	
	
	public void accorderCredit() throws IOException
	{
		Credit credit=new Credit();
		credit.setIdentifiant(selectedClient.getIdentifiant().toString());
		credit.setNom(selectedClient.getNom());
		credit.setPrenom(selectedClient.getPrenom());
		credit.setStatut("Validé");
		credit.setDate(new Date());
		credit.setMontant(capitalOutput);
		credit.setValidePar(getUserName());
		scoringService.saveCredit(credit);
		
		Date aujourdhui = new Date();
		DateFormat fullDateFormat = DateFormat.getDateTimeInstance(
				DateFormat.MEDIUM,
				DateFormat.MEDIUM);
		
		//Génération de document
		Document document = new Document(PageSize.A4);
		try {
		PdfWriter.getInstance(document,
		new FileOutputStream("c:/test/simulation.pdf"));
		document.open();
		Image image = Image.getInstance("c:/test/ecobank.jpeg");
		document.add(image);
		document.add(new Paragraph("Lomé, le "+fullDateFormat.format(aujourdhui)));
		document.add(new Paragraph("A l'attention de " +getNomAndPrenom()));
		document.add(new Paragraph("Objet : Votre dossier de crédit"));
		document.add(new Paragraph("Nous avons le plaisir de vous annoncer notre accord pour votre demande de crédit d'un montant de "+ capital+" "+"FCFA."));
		document.add(new Paragraph("Votre mensualité sera de "+ mensualiteOutput+" "+"FCFA."));
		document.add(new Paragraph("Vous pouvez passer à l'agence pour finaliser votre dossier."));
		document.add(new Paragraph("En espérant vous rencontrez très rapidement, veuillez agréer Mesdames, Messieurs mes sincères salutations."));
		document.add(new Paragraph("Votre conseiller"));
		document.add(new Paragraph(getUserName()));
		} catch (DocumentException de) {
		de.printStackTrace();
		} catch (IOException ioe) {
		ioe.printStackTrace();
		}
		document.close();
		
		//
		FacesContext.getCurrentInstance().getExternalContext().redirect("simulationCredit.xhtml");
	}
	
	
	public StreamedContent editerContratPdf() throws DocumentException, MalformedURLException, IOException 
	{
		Date aujourdhui = new Date();
		DateFormat fullDateFormat = DateFormat.getDateTimeInstance(
				DateFormat.MEDIUM,
				DateFormat.MEDIUM);
		
		    Document document = new Document(PageSize.A4, 50, 50, 50, 50);
	        OutputStream out = new ByteArrayOutputStream();
	        PdfWriter writer = PdfWriter.getInstance(document, out);
	        document.open();
			Image image = Image.getInstance("c:/test/ecobank.jpeg");
			document.add(image);
			document.add(new Paragraph("Lomé, le "+fullDateFormat.format(aujourdhui)));
			document.add(new Paragraph("A l'attention de " +getNomAndPrenom()));
			document.add(new Paragraph("Objet : Votre dossier de crédit"));
			document.add(new Paragraph("Nous avons le plaisir de vous annoncer notre accord pour votre demande de crédit d'un montant de "+ capital+" "+"FCFA."));
			document.add(new Paragraph("Votre mensualité sera de "+ mensualiteOutput+" "+"FCFA."));
			document.add(new Paragraph("Vous pouvez passer à l'agence pour finaliser votre dossier."));
			document.add(new Paragraph("En espérant vous rencontrez très rapidement, veuillez agréer Mesdames, Messieurs mes sincères salutations."));
			document.add(new Paragraph("Votre conseiller"));
			document.add(new Paragraph(getUserName()));
			document.close();
		    out.close();

	        
			InputStream in =new ByteArrayInputStream(((ByteArrayOutputStream)out).toByteArray());
			StreamedContent streamedContent = new DefaultStreamedContent(in, "application/pdf");         
		    return streamedContent;
		   }
	
	public StreamedContent getEditerContratPdf() throws DocumentException, MalformedURLException, IOException
	{
		Date aujourdhui = new Date();
		DateFormat fullDateFormat = DateFormat.getDateTimeInstance(
				DateFormat.MEDIUM,
				DateFormat.MEDIUM);
		
		
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
	            PdfPCell cell1 = new PdfPCell(new Paragraph("Lomé	, le "+fullDateFormat.format(aujourdhui)));
	            cell1.setBorder(0);
	            cell1.setHorizontalAlignment(Element.ALIGN_RIGHT);
	            
	            PdfPCell cell2 = new PdfPCell(new Paragraph(""));
	            cell2.setBorder(0);
	            cell2.setHorizontalAlignment(Element.ALIGN_RIGHT);

	            
	            PdfPCell cell3 = new PdfPCell(new Paragraph("A l'attention de " +getNomAndPrenom()));
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
			
			
			document.add(new Paragraph("Nous avons le plaisir de vous annoncer notre accord pour votre demande de crédit d'un montant de "+ capital+" "+"FCFA."));
			
			Paragraph prefaceter = new Paragraph();
			ajouterLigneVide(prefaceter, 1);
			document.add(new Phrase(""));
			document.add(prefaceter);
			 
			 
			document.add(new Paragraph("Votre mensualité sera de "+ mensualiteOutput+" "+"FCFA."));
			
			Paragraph prefacefour = new Paragraph();
			ajouterLigneVide(prefacefour, 1);
			document.add(new Phrase(""));
			document.add(prefacefour);
			
			document.add(new Paragraph("Vous pouvez passer à l'agence pour finaliser votre dossier."));
			document.add(new Paragraph("En espérant vous rencontrez très rapidement, veuillez agréer Mesdames, Messieurs mes sincères salutations."));
			
			Paragraph preface3 = new Paragraph();
			ajouterLigneVide(preface3, 1);
			document.add(new Phrase(""));
			document.add(preface3);
			
			
			document.add(new Paragraph("Votre conseiller"));
			document.add(new Paragraph(getUserName()));
			
		      
			document.close();
		    out.close();
			InputStream in =new ByteArrayInputStream(((ByteArrayOutputStream)out).toByteArray());
			StreamedContent streamedContent = new DefaultStreamedContent(in, "application/pdf","contrat.pdf");         
		    return streamedContent;
	}
	
	
	private void ajouterLigneVide(Paragraph paragraph, int number) {
	    for (int i = 0; i < number; i++) {
	        paragraph.add(new Paragraph(" "));
	    }
	}
	
	public void envoyerVers() throws IOException
	{
		Credit credit=new Credit();
		credit.setIdentifiant(selectedClient.getIdentifiant().toString());
		credit.setNom(selectedClient.getNom());
		credit.setPrenom(selectedClient.getPrenom());
		credit.setStatut("A traiter");
		credit.setDate(new Date());
		credit.setMontant(capital+"");
		credit.setValidePar(getUserName());
		credit.setEnvoyerPar(getUserName());
		DecimalFormat f = new DecimalFormat();
		f.setMaximumFractionDigits(2);
		credit.setMensualite(f.format(mensualite));
		if(currentUer!=null)
		{
		credit.setEnvoyerVers(currentUer);	
		}
//		if(autocompleteUser!=null)
//		{
//		credit.setEnvoyerVers(autocompleteUser.getNomAndPrenom());
//		}
		scoringService.saveCredit(credit);
		saveHistorique(credit);
		//sendEmail(credit);
		init();
		FacesContext.getCurrentInstance().addMessage("rechercheForm", new FacesMessage(FacesMessage.SEVERITY_INFO,"Instruction crédit :", "Votre demande a bien été envoyée à"+currentUer));
		FacesContext.getCurrentInstance().getExternalContext().redirect("recherche.xhtml");
	}
	
	public void saveHistorique(Credit credit)
	{
		Historique historique=new Historique();
		historique.setDate(new  Date());
		historique.setEnvoyerPar(credit.getEnvoyerPar());
		historique.setEnvoyerVers(credit.getEnvoyerVers());
		historique.setMensualite(credit.getMensualite());
		historique.setMontant(credit.getMontant());
		historique.setNom(credit.getNom());
		historique.setPrenom(credit.getPrenom());
		historique.setStatut(credit.getStatut());
		historique.setIdCredit(credit.getIdentifiant());
		scoringService.saveHistorique(historique);
	}
	
	public void sendEmail(Credit credit)
	{
		final String username = "madjey.mensah@gmail";
		final String password = "Cerruti39";
		User currentUser=null;
		StringTokenizer stringTokenizer=new StringTokenizer(credit.getEnvoyerVers());
		while (stringTokenizer.hasMoreTokens()) {
			List<User> listCurrentUser=scoringService.findUserByName(stringTokenizer.nextToken());
			if(listCurrentUser!=null && !listCurrentUser.isEmpty())
			{
				currentUser=listCurrentUser.get(0);
			}
	     }
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
			message.setFrom(new InternetAddress("madjey.mensah@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(currentUser.getEmail()));
			message.setSubject("Mail de la part de Octroi de crédit");
			message.setText("Cher"+" "+credit.getEnvoyerVers()+" . "+"Une tâche vient de vous êtes envoyée pour traitement de la part de"+" "+ credit.getEnvoyerPar());
			Transport.send(message);
			System.out.println("Done");
 
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	public void abandonner() throws IOException
	{
		FacesContext.getCurrentInstance().getExternalContext().redirect("recherche.xhtml");
	}

    public List<User> completeUser(String query) {
        List<User> allUsers = scoringService.findAllUser();
        List<User> filteredUser = new ArrayList<User>();
         
        for (int i = 0; i < allUsers.size(); i++) {
        	User currentUser = allUsers.get(i);
            if(currentUser.getNom().toLowerCase().startsWith(query)) {
            	filteredUser.add(currentUser);
            }
        }
         
        return filteredUser;
    }
    
    

    
    
    public List<String> completeText(String query) {
        List<String> results = new ArrayList<String>();
        for(int i = 0; i < 10; i++) {
            results.add(query + i);
        }
         
        return results;
    }
    
    public List<String> completePays(String query) {
        List<String> results = new ArrayList<String>();
        List<String> listPays= new ArrayList<String>();
        results.add("FRANCE");
        results.add("Allemagne");
        
        for (String currentPays : results)
        {
		if(currentPays.startsWith(query))	
		{
		listPays.add(currentPays);	
		}
		}

        return listPays;
    }
    public void onRowEdit(RowEditEvent event) {
		init();
    }
     
    public void onRowCancel(RowEditEvent event) {
        FacesContext.getCurrentInstance().addMessage(null, null);
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
	
	public String getNomAndPrenom() {
		String retour="";
		if(selectedClient!=null)
		{
			StringBuffer stringBuffer = new StringBuffer("");	
			stringBuffer.append(selectedClient.getNom());
			stringBuffer.append(" ");
			stringBuffer.append(selectedClient.getPrenom());
			retour=stringBuffer.toString();
		}
		return retour;
	}
	
	public String getAutocompleteUserName() {
		String retour="";
		if(user!=null)
		{
			StringBuffer stringBuffer = new StringBuffer("");	
			stringBuffer.append(user.getNom());
			stringBuffer.append(" ");
			stringBuffer.append(user.getPrenom());
			retour=stringBuffer.toString();
		}
		return retour;
	}
	
	public String formaterDate(Date date)
	{
		SimpleDateFormat  simpleFormat = new SimpleDateFormat("dd-MM-yyyy");
		String retour=simpleFormat.format(date);
		return retour;
	}
	
	
	
	public String getStatutStyle(String statut){
		String retourstyle = null;
	if(statut!=null){
		if(statut.equals("à traiter"))
		{
			retourstyle=	"background-color: #428bca;color: #fff;text-align: center;border-radius: .25em;font-weight: bold;font-size: 100%;    line-height: 1;padding: .2em .6em .3em;";
		}
		else 		if(statut.equals("Non contactable"))
		{
			retourstyle=	"background-color: #d9534f;color: #fff;text-align: center;border-radius: .25em;font-weight: bold;font-size: 100%;    line-height: 1;padding: .2em .6em .3em;";	
		}
		else 		if(statut.equals("A transférer"))
		{
			retourstyle=	"background-color: #5cb85c;color: #fff;text-align: center;border-radius: .25em;font-weight: bold;font-size: 100%;    line-height: 1;padding: .2em .6em .3em;";	
		}
		else 		if(statut.equals("Traité"))
		{
			retourstyle=	"background-color: #f0ad4e;color: #fff;text-align: center;border-radius: .25em;font-weight: bold;font-size: 100%;    line-height: 1;padding: .2em .6em .3em;";	
		}
	}
	return retourstyle;
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

	public List<EligibiliteBean> getListEligibilite() {
		return listEligibilite;
	}

	public void setListEligibilite(List<EligibiliteBean> listEligibilite) {
		this.listEligibilite = listEligibilite;
	}

	public String getListeDeroulante() {
		return listeDeroulante;
	}

	public void setListeDeroulante(String listeDeroulante) {
		this.listeDeroulante = listeDeroulante;
	}

	public String getValeurListeDeroulante() {
		return valeurListeDeroulante;
	}

	public void setValeurListeDeroulante(String valeurListeDeroulante) {
		this.valeurListeDeroulante = valeurListeDeroulante;
	}
	
	public void initListeDeroulante()
	{
		valeurListeDeroulante="toto";
	}

	public String getNomClient() {
		return nomClient;
	}

	public void setNomClient(String nomClient) {
		this.nomClient = nomClient;
	}

	public String getNumeroDossier() {
		return numeroDossier;
	}

	public void setNumeroDossier(String numeroDossier) {
		this.numeroDossier = numeroDossier;
	}

	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}
	
	  public String onFlowProcess(FlowEvent event) {
	        if(skip) {
	            skip = false;   //reset in case user goes back
	            return "confirm";
	        }
	        else {
	            return event.getNewStep();
	        }
	    }

	public Integer getIdentifiant() {
		return identifiant;
	}

	public void setIdentifiant(Integer identifiant) {
		this.identifiant = identifiant;
	}

	public String getCivilite() {
		return civilite;
	}

	public void setCivilite(String civilite) {
		this.civilite = civilite;
	}

	public String getPrenomClient() {
		return prenomClient;
	}

	public void setPrenomClient(String prenomClient) {
		this.prenomClient = prenomClient;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public String getCommuneNaissance() {
		return communeNaissance;
	}

	public void setCommuneNaissance(String communeNaissance) {
		this.communeNaissance = communeNaissance;
	}

	public String getAdresseRue() {
		return adresseRue;
	}

	public void setAdresseRue(String adresseRue) {
		this.adresseRue = adresseRue;
	}

	public String getAdressecodePostal() {
		return adressecodePostal;
	}

	public void setAdressecodePostal(String adressecodePostal) {
		this.adressecodePostal = adressecodePostal;
	}

	public String getAdressePays() {
		return adressePays;
	}

	public void setAdressePays(String adressePays) {
		this.adressePays = adressePays;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSituationFamilial() {
		return situationFamilial;
	}

	public void setSituationFamilial(String situationFamilial) {
		this.situationFamilial = situationFamilial;
	}

	public Integer getNombreEnfant() {
		return nombreEnfant;
	}

	public void setNombreEnfant(Integer nombreEnfant) {
		this.nombreEnfant = nombreEnfant;
	}

	public Date getDateNaissancePremierEnfant() {
		return dateNaissancePremierEnfant;
	}

	public void setDateNaissancePremierEnfant(Date dateNaissancePremierEnfant) {
		this.dateNaissancePremierEnfant = dateNaissancePremierEnfant;
	}

	public Date getDateNaissanceDeuxiemeEnfant() {
		return dateNaissanceDeuxiemeEnfant;
	}

	public void setDateNaissanceDeuxiemeEnfant(Date dateNaissanceDeuxiemeEnfant) {
		this.dateNaissanceDeuxiemeEnfant = dateNaissanceDeuxiemeEnfant;
	}

	public String getModeHabitation() {
		return modeHabitation;
	}

	public void setModeHabitation(String modeHabitation) {
		this.modeHabitation = modeHabitation;
	}

	public Integer getNombrePersonneEnCharge() {
		return nombrePersonneEnCharge;
	}

	public void setNombrePersonneEnCharge(Integer nombrePersonneEnCharge) {
		this.nombrePersonneEnCharge = nombrePersonneEnCharge;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getAnciennete() {
		return anciennete;
	}

	public void setAnciennete(String anciennete) {
		this.anciennete = anciennete;
	}

	public String getNomEmployeur() {
		return nomEmployeur;
	}

	public void setNomEmployeur(String nomEmployeur) {
		this.nomEmployeur = nomEmployeur;
	}

	public String getTypeContrat() {
		return typeContrat;
	}

	public void setTypeContrat(String typeContrat) {
		this.typeContrat = typeContrat;
	}

	public String getRevenuPension() {
		return revenuPension;
	}

	public void setRevenuPension(String revenuPension) {
		this.revenuPension = revenuPension;
	}

	public String getChargeLoyer() {
		return chargeLoyer;
	}

	public void setChargeLoyer(String chargeLoyer) {
		this.chargeLoyer = chargeLoyer;
	}

	public String getChargePremierPret() {
		return chargePremierPret;
	}

	public void setChargePremierPret(String chargePremierPret) {
		this.chargePremierPret = chargePremierPret;
	}

	public String getChargeDeuxiemePret() {
		return chargeDeuxiemePret;
	}

	public void setChargeDeuxiemePret(String chargeDeuxiemePret) {
		this.chargeDeuxiemePret = chargeDeuxiemePret;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public ScoringService getScoringService() {
		return scoringService;
	}

	public void setScoringService(ScoringService scoringService) {
		this.scoringService = scoringService;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getScoreHomme() {
		return scoreHomme;
	}

	public void setScoreHomme(Integer scoreHomme) {
		this.scoreHomme = scoreHomme;
	}

	public Integer getScoreFemme() {
		return scoreFemme;
	}

	public void setScoreFemme(Integer scoreFemme) {
		this.scoreFemme = scoreFemme;
	}

	public Boolean getActifBooleanHomme() {
		return actifBooleanHomme;
	}

	public void setActifBooleanHomme(Boolean actifBooleanHomme) {
		this.actifBooleanHomme = actifBooleanHomme;
	}

	public Boolean getActifBooleanFemme() {
		return actifBooleanFemme;
	}

	public void setActifBooleanFemme(Boolean actifBooleanFemme) {
		this.actifBooleanFemme = actifBooleanFemme;
	}

	public Integer getScoreOuvrier() {
		return scoreOuvrier;
	}

	public void setScoreOuvrier(Integer scoreOuvrier) {
		this.scoreOuvrier = scoreOuvrier;
	}

	public Boolean getActifBooleanOuvrier() {
		return actifBooleanOuvrier;
	}

	public void setActifBooleanOuvrier(Boolean actifBooleanOuvrier) {
		this.actifBooleanOuvrier = actifBooleanOuvrier;
	}

	public Integer getScoreCadre() {
		return scoreCadre;
	}

	public void setScoreCadre(Integer scoreCadre) {
		this.scoreCadre = scoreCadre;
	}

	public Boolean getActifBooleanCadre() {
		return actifBooleanCadre;
	}

	public void setActifBooleanCadre(Boolean actifBooleanCadre) {
		this.actifBooleanCadre = actifBooleanCadre;
	}

	public Integer getScoreFonctionPublique() {
		return scoreFonctionPublique;
	}

	public void setScoreFonctionPublique(Integer scoreFonctionPublique) {
		this.scoreFonctionPublique = scoreFonctionPublique;
	}

	public Boolean getActifBooleanFonctionPublique() {
		return actifBooleanFonctionPublique;
	}

	public void setActifBooleanFonctionPublique(Boolean actifBooleanFonctionPublique) {
		this.actifBooleanFonctionPublique = actifBooleanFonctionPublique;
	}

	public Integer getScoreSecteurPrive() {
		return scoreSecteurPrive;
	}

	public void setScoreSecteurPrive(Integer scoreSecteurPrive) {
		this.scoreSecteurPrive = scoreSecteurPrive;
	}

	public Boolean getActifBooleanSecteurPrive() {
		return actifBooleanSecteurPrive;
	}

	public void setActifBooleanSecteurPrive(Boolean actifBooleanSecteurPrive) {
		this.actifBooleanSecteurPrive = actifBooleanSecteurPrive;
	}

	public Integer getScoreCelibataire() {
		return scoreCelibataire;
	}

	public void setScoreCelibataire(Integer scoreCelibataire) {
		this.scoreCelibataire = scoreCelibataire;
	}

	public Boolean getActifBooleanCelibataire() {
		return actifBooleanCelibataire;
	}

	public void setActifBooleanCelibataire(Boolean actifBooleanCelibataire) {
		this.actifBooleanCelibataire = actifBooleanCelibataire;
	}

	public Integer getScoreMarie() {
		return scoreMarie;
	}

	public void setScoreMarie(Integer scoreMarie) {
		this.scoreMarie = scoreMarie;
	}

	public Boolean getActifBooleanMarie() {
		return actifBooleanMarie;
	}

	public void setActifBooleanMarie(Boolean actifBooleanMarie) {
		this.actifBooleanMarie = actifBooleanMarie;
	}

	public Integer getScoreLocataire() {
		return scoreLocataire;
	}

	public void setScoreLocataire(Integer scoreLocataire) {
		this.scoreLocataire = scoreLocataire;
	}

	public Boolean getActifBooleanLocataire() {
		return actifBooleanLocataire;
	}

	public void setActifBooleanLocataire(Boolean actifBooleanLocataire) {
		this.actifBooleanLocataire = actifBooleanLocataire;
	}

	public Integer getScorePropriétaire() {
		return scorePropriétaire;
	}

	public void setScorePropriétaire(Integer scorePropriétaire) {
		this.scorePropriétaire = scorePropriétaire;
	}

	public Boolean getActifBooleanPropriétaire() {
		return actifBooleanPropriétaire;
	}

	public void setActifBooleanPropriétaire(Boolean actifBooleanPropriétaire) {
		this.actifBooleanPropriétaire = actifBooleanPropriétaire;
	}

	public Integer getRevenuSup() {
		return revenuSup;
	}

	public void setRevenuSup(Integer revenuSup) {
		this.revenuSup = revenuSup;
	}

	public Boolean getActifBooleanRevenuSup() {
		return actifBooleanRevenuSup;
	}

	public void setActifBooleanRevenuSup(Boolean actifBooleanRevenuSup) {
		this.actifBooleanRevenuSup = actifBooleanRevenuSup;
	}

	public Integer getRevenuInf() {
		return revenuInf;
	}

	public void setRevenuInf(Integer revenuInf) {
		this.revenuInf = revenuInf;
	}

	public Boolean getActifBooleanRevenuInf() {
		return actifBooleanRevenuInf;
	}

	public void setActifBooleanRevenuInf(Boolean actifBooleanRevenuInf) {
		this.actifBooleanRevenuInf = actifBooleanRevenuInf;
	}

	public Integer getNombreEnfantInf() {
		return nombreEnfantInf;
	}

	public void setNombreEnfantInf(Integer nombreEnfantInf) {
		this.nombreEnfantInf = nombreEnfantInf;
	}

	public Boolean getActifBooleanEnfantInf() {
		return actifBooleanEnfantInf;
	}

	public void setActifBooleanEnfantInf(Boolean actifBooleanEnfantInf) {
		this.actifBooleanEnfantInf = actifBooleanEnfantInf;
	}

	public Integer getNombreEnfantSup() {
		return nombreEnfantSup;
	}

	public void setNombreEnfantSup(Integer nombreEnfantSup) {
		this.nombreEnfantSup = nombreEnfantSup;
	}

	public Boolean getActifBooleanEnfantSup() {
		return actifBooleanEnfantSup;
	}

	public void setActifBooleanEnfantSup(Boolean actifBooleanEnfantSup) {
		this.actifBooleanEnfantSup = actifBooleanEnfantSup;
	}

	public Integer getAgeInf() {
		return ageInf;
	}

	public void setAgeInf(Integer ageInf) {
		this.ageInf = ageInf;
	}

	public Boolean getActifBooleanAgeInf() {
		return actifBooleanAgeInf;
	}

	public void setActifBooleanAgeInf(Boolean actifBooleanAgeInf) {
		this.actifBooleanAgeInf = actifBooleanAgeInf;
	}

	public Integer getAgeSup() {
		return ageSup;
	}

	public void setAgeSup(Integer ageSup) {
		this.ageSup = ageSup;
	}

	public Boolean getActifBooleanAgeSup() {
		return actifBooleanAgeSup;
	}

	public void setActifBooleanAgeSup(Boolean actifBooleanAgeSup) {
		this.actifBooleanAgeSup = actifBooleanAgeSup;
	}


	public double getCapital() {
		return capital;
	}

	public void setCapital(double capital) {
		this.capital = capital;
	}

	public Float getTaux() {
		return taux;
	}

	public void setTaux(Float taux) {
		this.taux = taux;
	}

	public int getNbreMois() {
		return nbreMois;
	}

	public void setNbreMois(int nbreMois) {
		this.nbreMois = nbreMois;
	}

	public double getMensualite() {
		return mensualite;
	}

	public void setMensualite(double mensualite) {
		this.mensualite = mensualite;
	}

	public double getCoutTotal() {
		return coutTotal;
	}

	public void setCoutTotal(double coutTotal) {
		this.coutTotal = coutTotal;
	}

	public boolean isShowTable() {
		return showTable;
	}

	public void setShowTable(boolean showTable) {
		this.showTable = showTable;
	}

	

	public List<Client> getListSearchClient() {
		return listSearchClient;
	}

	public void setListSearchClient(List<Client> listSearchClient) {
		this.listSearchClient = listSearchClient;
	}

	public Integer getSearchIdentifiant() {
		return searchIdentifiant;
	}

	public void setSearchIdentifiant(Integer searchIdentifiant) {
		this.searchIdentifiant = searchIdentifiant;
	}

	public String getSearchNom() {
		return searchNom;
	}

	public void setSearchNom(String searchNom) {
		this.searchNom = searchNom;
	}

	public Client getSelectedClient() {
		return selectedClient;
	}

	public void setSelectedClient(Client selectedClient) {
		this.selectedClient = selectedClient;
	}

	public String getCoutTotalOutput() {
		return coutTotalOutput;
	}

	public void setCoutTotalOutput(String coutTotalOutput) {
		this.coutTotalOutput = coutTotalOutput;
	}

	public String getMensualiteOutput() {
		return mensualiteOutput;
	}

	public void setMensualiteOutput(String mensualiteOutput) {
		this.mensualiteOutput = mensualiteOutput;
	}

	public String getCapitalOutput() {
		return capitalOutput;
	}

	public void setCapitalOutput(String capitalOutput) {
		this.capitalOutput = capitalOutput;
	}

	public String getInteretOutput() {
		return interetOutput;
	}

	public void setInteretOutput(String interetOutput) {
		this.interetOutput = interetOutput;
	}

	public List<ArmotissementBean> getListArmotissementBean() {
		return listArmotissementBean;
	}

	public void setListArmotissementBean(
			List<ArmotissementBean> listArmotissementBean) {
		this.listArmotissementBean = listArmotissementBean;
	}

	public boolean isShowAmortissement() {
		return showAmortissement;
	}

	public void setShowAmortissement(boolean showAmortissement) {
		this.showAmortissement = showAmortissement;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getAutocompleteUser() {
		return autocompleteUser;
	}

	public void setAutocompleteUser(User autocompleteUser) {
		this.autocompleteUser = autocompleteUser;
	}

	public List<Credit> getListCredit() {
		return listCredit;
	}

	public void setListCredit(List<Credit> listCredit) {
		this.listCredit = listCredit;
	}

	public String getCredit() {
		return credit;
	}

	public void setCredit(String credit) {
		this.credit = credit;
	}

	public String getActivitePro() {
		return activitePro;
	}

	public void setActivitePro(String activitePro) {
		this.activitePro = activitePro;
	}

	public String getCaution() {
		return caution;
	}

	public void setCaution(String caution) {
		this.caution = caution;
	}

	public String getDemandeCredit() {
		return demandeCredit;
	}

	public void setDemandeCredit(String demandeCredit) {
		this.demandeCredit = demandeCredit;
	}

	public String getCarteBancaire() {
		return carteBancaire;
	}

	public void setCarteBancaire(String carteBancaire) {
		this.carteBancaire = carteBancaire;
	}

	public String getImpaye() {
		return impaye;
	}

	public void setImpaye(String impaye) {
		this.impaye = impaye;
	}

	public String getNomUtilisateur() {
		return nomUtilisateur;
	}

	public void setNomUtilisateur(String nomUtilisateur) {
		this.nomUtilisateur = nomUtilisateur;
	}

	public String getPrenomUtilisateur() {
		return prenomUtilisateur;
	}

	public void setPrenomUtilisateur(String prenomUtilisateur) {
		this.prenomUtilisateur = prenomUtilisateur;
	}

	public String getEmailUtilisateur() {
		return emailUtilisateur;
	}

	public void setEmailUtilisateur(String emailUtilisateur) {
		this.emailUtilisateur = emailUtilisateur;
	}

	public String getLoginUtilisateur() {
		return loginUtilisateur;
	}

	public void setLoginUtilisateur(String loginUtilisateur) {
		this.loginUtilisateur = loginUtilisateur;
	}

	public String getMdpUtilisateur() {
		return mdpUtilisateur;
	}

	public void setMdpUtilisateur(String mdpUtilisateur) {
		this.mdpUtilisateur = mdpUtilisateur;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public Credit getSelectedCredit() {
		return selectedCredit;
	}

	public void setSelectedCredit(Credit selectedCredit) {
		this.selectedCredit = selectedCredit;
	}

	public List<Historique> getListHistorique() {
		return listHistorique;
	}

	public void setListHistorique(List<Historique> listHistorique) {
		this.listHistorique = listHistorique;
	}

	public Integer getSeuilscoreAccepte() {
		return seuilscoreAccepte;
	}

	public void setSeuilscoreAccepte(Integer seuilscoreAccepte) {
		this.seuilscoreAccepte = seuilscoreAccepte;
	}

	public Integer getSeuilscoreRefuse() {
		return seuilscoreRefuse;
	}

	public void setSeuilscoreRefuse(Integer seuilscoreRefuse) {
		this.seuilscoreRefuse = seuilscoreRefuse;
	}

	public String getCategorieSocioProfessionel() {
		return categorieSocioProfessionel;
	}

	public void setCategorieSocioProfessionel(String categorieSocioProfessionel) {
		this.categorieSocioProfessionel = categorieSocioProfessionel;
	}

	public String getSecteurActivite() {
		return secteurActivite;
	}

	public void setSecteurActivite(String secteurActivite) {
		this.secteurActivite = secteurActivite;
	}

	public List<String> getListCivilites() {
		return listCivilites;
	}

	public void setListCivilites(List<String> listCivilites) {
		this.listCivilites = listCivilites;
	}

	public String getPaysNaissance() {
		return paysNaissance;
	}

	public void setPaysNaissance(String paysNaissance) {
		this.paysNaissance = paysNaissance;
	}

	public String getAdresseVille() {
		return adresseVille;
	}

	public void setAdresseVille(String adresseVille) {
		this.adresseVille = adresseVille;
	}

	public String getTelephoneBureau() {
		return telephoneBureau;
	}

	public void setTelephoneBureau(String telephoneBureau) {
		this.telephoneBureau = telephoneBureau;
	}

	public boolean isShowSynthese() {
		return showSynthese;
	}

	public void setShowSynthese(boolean showSynthese) {
		this.showSynthese = showSynthese;
	}

	public String getTauxEndettementAvantCredit() {
		return tauxEndettementAvantCredit;
	}

	public void setTauxEndettementAvantCredit(String tauxEndettementAvantCredit) {
		this.tauxEndettementAvantCredit = tauxEndettementAvantCredit;
	}

	public String getTauxEndettementApresCredit() {
		return tauxEndettementApresCredit;
	}

	public void setTauxEndettementApresCredit(String tauxEndettementApresCredit) {
		this.tauxEndettementApresCredit = tauxEndettementApresCredit;
	}

	public List<Piece> getListPiece() {
		return listPiece;
	}

	public void setListPiece(List<Piece> listPiece) {
		this.listPiece = listPiece;
	}

	public void setEditerContratPdf(StreamedContent editerContratPdf) {
		this.editerContratPdf = editerContratPdf;
	}

	public String getMensualiteMaximum() {
		return mensualiteMaximum;
	}

	public void setMensualiteMaximum(String mensualiteMaximum) {
		this.mensualiteMaximum = mensualiteMaximum;
	}

	public String getExemple() {
		return exemple;
	}

	public void setExemple(String exemple) {
		this.exemple = exemple;
	}

	public List<String> getListUser() {
		return listUser;
	}

	public void setListUser(List<String> listUser) {
		this.listUser = listUser;
	}

	public String getCurrentUer() {
		return currentUer;
	}

	public void setCurrentUer(String currentUer) {
		this.currentUer = currentUer;
	}

	
}