package com.otv.managed.bean;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.ToggleEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.otv.model.mod.Depensecourante;
import com.otv.model.mod.Revenuordepense;
import com.otv.model.mod.User;
import com.otv.user.service.DepenseService;

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
@ManagedBean(name = "depenseCouranteBean")
@Component("depenseCouranteBean")
@SessionScoped
public class DepenseCouranteBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final String SUCCESS = "success";
	private static final String ERROR = "error";
	private String login;
	private String mdpasse;
	private String loginAndPassword;
	private String nom;
	private String prenom;
	private String libelle;
	private Long debit;
	private Long montant;
	private String type;
	private Date date;
	private boolean seePanel;
	private Revenuordepense selectedRevenuordepense;

	public List<Depensecourante> listDepensecourante;
	public List<Revenuordepense> listDepenseOrRevenu;
	public List<Revenuordepense> listDepenseOrRevenuMoisCurrent=new ArrayList<Revenuordepense>();
	public List<Revenuordepense> listDepenseOrRevenuMoisSuivant=new ArrayList<Revenuordepense>();
	public List<Revenuordepense> listDepenseOrRevenuMoisApres=new ArrayList<Revenuordepense>();
	public List<Revenuordepense> listDepenseOrRevenuMoisApresApres=new ArrayList<Revenuordepense>();
	public List<String> listLibelle;
	private String initCarroussel;
	private String selectedMonth;

	@Autowired
	private transient DepenseService depenseService;

	private int id;
	private String name;

	@PostConstruct
	public void init() {
		listDepensecourante = depenseService.findAllDepenseCourante();
		listDepenseOrRevenu=depenseService.findAllRevenuordepense();
		listDepenseOrRevenuMoisCurrent.clear();
		listDepenseOrRevenuMoisSuivant.clear();
		listDepenseOrRevenuMoisApres.clear();
		
		//Revenu courant
		for (Revenuordepense revenuordepense : listDepenseOrRevenu) {
			initListRecuurrente(revenuordepense);
		}
		
//		for (Revenuordepense revenuordepense : listDepenseOrRevenu) {
//			if((revenuordepense.getDate().getMonth()==new Date().getMonth()+1) && (revenuordepense.getType().equals("REVEXC")||revenuordepense.getType().equals("DEPEXC")))
//			{
//				listDepenseOrRevenuMoisSuivant.add(revenuordepense);
//			}
//			if(revenuordepense.getType().equals("REVREC")||revenuordepense.getType().equals("DEPREC"))
//			{
//				listDepenseOrRevenuMoisSuivant.add(revenuordepense);
//			}
//		}
//		
//		for (Revenuordepense revenuordepense : listDepenseOrRevenu) {
//			if((revenuordepense.getDate().getMonth()==new Date().getMonth()+2) && (revenuordepense.getType().equals("REVEXC")||revenuordepense.getType().equals("DEPEXC")))
//			{
//				listDepenseOrRevenuMoisApres.add(revenuordepense);
//			}
//			if(revenuordepense.getType().equals("REVREC")||revenuordepense.getType().equals("DEPREC"))
//			{
//				listDepenseOrRevenuMoisApres.add(revenuordepense);
//			}
//		}
		
		
		if(listLibelle!=null)
		{
			listLibelle.clear();	
		}
		listLibelle=new ArrayList<String>();
		listLibelle.add("Mes dépenses récurrentes");
		seePanel=false;
	}



	private void initListRecuurrente(Revenuordepense revenuordepense) {
		if(selectedMonth==null || selectedMonth.equals(""))
		{
		if((revenuordepense.getDate().getMonth()==new Date().getMonth()) && (revenuordepense.getType().equals("REVEXC")||revenuordepense.getType().equals("DEPEXC")))
		{
			listDepenseOrRevenuMoisCurrent.add(revenuordepense);
		}
		}
		//janvier
		else if(selectedMonth!=null && selectedMonth.equals("janvier"))
		{
			if((revenuordepense.getDate().getMonth()==0) && (revenuordepense.getType().equals("REVEXC")||revenuordepense.getType().equals("DEPEXC")))
			{
				listDepenseOrRevenuMoisCurrent.add(revenuordepense);
			}
		}
		//février
		else if(selectedMonth!=null && selectedMonth.equals("fevrier"))
		{
			if((revenuordepense.getDate().getMonth()==1) && (revenuordepense.getType().equals("REVEXC")||revenuordepense.getType().equals("DEPEXC")))
			{
				listDepenseOrRevenuMoisCurrent.add(revenuordepense);
			}
		}
		//Mars
		else if(selectedMonth!=null && selectedMonth.equals("mars"))
		{
			if((revenuordepense.getDate().getMonth()==2) && (revenuordepense.getType().equals("REVEXC")||revenuordepense.getType().equals("DEPEXC")))
			{
				listDepenseOrRevenuMoisCurrent.add(revenuordepense);
			}
		}
		//Avril
		else if(selectedMonth!=null && selectedMonth.equals("avril"))
		{
			if((revenuordepense.getDate().getMonth()==3) && (revenuordepense.getType().equals("REVEXC")||revenuordepense.getType().equals("DEPEXC")))
			{
				listDepenseOrRevenuMoisCurrent.add(revenuordepense);
			}
		}
		//Mai
		else if(selectedMonth!=null && selectedMonth.equals("mai"))
		{
			if((revenuordepense.getDate().getMonth()==4) && (revenuordepense.getType().equals("REVEXC")||revenuordepense.getType().equals("DEPEXC")))
			{
				listDepenseOrRevenuMoisCurrent.add(revenuordepense);
			}
		}
		//Juin
		else if(selectedMonth!=null && selectedMonth.equals("juin"))
		{
			if((revenuordepense.getDate().getMonth()==5) && (revenuordepense.getType().equals("REVEXC")||revenuordepense.getType().equals("DEPEXC")))
			{
				listDepenseOrRevenuMoisCurrent.add(revenuordepense);
			}
		}
		//Juillet
		else if(selectedMonth!=null && selectedMonth.equals("juillet"))
		{
			if((revenuordepense.getDate().getMonth()==6) && (revenuordepense.getType().equals("REVEXC")||revenuordepense.getType().equals("DEPEXC")))
			{
				listDepenseOrRevenuMoisCurrent.add(revenuordepense);
			}
		}
		//Aout
		else if(selectedMonth!=null && selectedMonth.equals("aout"))
		{
			if((revenuordepense.getDate().getMonth()==7) && (revenuordepense.getType().equals("REVEXC")||revenuordepense.getType().equals("DEPEXC")))
			{
				listDepenseOrRevenuMoisCurrent.add(revenuordepense);
			}
		}
		//Septembre
		else if(selectedMonth!=null && selectedMonth.equals("septembre"))
		{
			if((revenuordepense.getDate().getMonth()==8) && (revenuordepense.getType().equals("REVEXC")||revenuordepense.getType().equals("DEPEXC")))
			{
				listDepenseOrRevenuMoisCurrent.add(revenuordepense);
			}
		}
		//Octobre
		else if(selectedMonth!=null && selectedMonth.equals("octobre"))
		{
			if((revenuordepense.getDate().getMonth()==9) && (revenuordepense.getType().equals("REVEXC")||revenuordepense.getType().equals("DEPEXC")))
			{
				listDepenseOrRevenuMoisCurrent.add(revenuordepense);
			}
		}
		//Novembre
		else if(selectedMonth!=null && selectedMonth.equals("novembre"))
		{
			if((revenuordepense.getDate().getMonth()==10) && (revenuordepense.getType().equals("REVEXC")||revenuordepense.getType().equals("DEPEXC")))
			{
				listDepenseOrRevenuMoisCurrent.add(revenuordepense);
			}
		}
		//Décembre
		else if(selectedMonth!=null && selectedMonth.equals("decembre"))
		{
			if((revenuordepense.getDate().getMonth()==11) && (revenuordepense.getType().equals("REVEXC")||revenuordepense.getType().equals("DEPEXC")))
			{
				listDepenseOrRevenuMoisCurrent.add(revenuordepense);
			}
		}
		
		//Revenu et dépense réccurente
		if(revenuordepense.getType().equals("REVREC")||revenuordepense.getType().equals("DEPREC"))
		{
			listDepenseOrRevenuMoisCurrent.add(revenuordepense);
		}
	}



	  public void onRowToggle(ToggleEvent event) {
	    }


	// public List<String> complete(String parameter) {
	// List<String> results = new ArrayList<String>();
	//
	// List<Total> listcurrentTotal = balanceService
	// .findTotalByCriteria(parameter);
	// for (Total total : listcurrentTotal) {
	// results.add(total.getId().getCompte());
	// }
	// return results;
	// }

//	public String rechercher() {
//
//		FacesContext ctx = FacesContext.getCurrentInstance();
//		Map<String, String> request = ctx.getExternalContext()
//				.getRequestParameterMap();
//		searchtotal = request.get("form3" + NamingContainer.SEPARATOR_CHAR
//				+ "enterId");
//		if (searchtotal != null && !searchtotal.isEmpty()) {
//			listTotal = balanceService.findTotalByCriteria(searchtotal);
//			listTotalLike = balanceService.findTotalByCriteriaLike(searchtotal);
//		} else {
//			listTotal = listInitTotal;
//			listTotalLike = new ArrayList<Total>();
//		}
//		return "search";
//	}


//	public String saveSimulation() {
//		if (!checkSimulation1()) {
//			balanceService.saveSimulation1(listTotalRatio);
//		} else if (!checkSimulation2()) {
//			balanceService.saveSimulation2(listTotalRatio);
//		} else if (!checkSimulation3()) {
//			balanceService.saveSimulation3(listTotalRatio);
//		} else {
//			balanceService.saveSimulation3(listTotalRatio);
//		}
//		FacesMessage msg = new FacesMessage("Action is successful");
//		FacesContext.getCurrentInstance().addMessage(null, msg);
//		return "save";
//	}

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
	 


	private String surname;

	public String connect() throws IOException {
		FacesContext.getCurrentInstance().getExternalContext().redirect("accueilBis.xhtml");
		if (depenseService.checkHabilitation(login, mdpasse)) {
			return "connect";
		} else {
			return "";
		}
	}
	
	public String createDepenseCourante() {
	    System.out.println("Création de dépense courante");
	    FacesContext.getCurrentInstance().addMessage("form1", new FacesMessage(FacesMessage.SEVERITY_INFO,"Création Dépense :", "Votre dépense courante a été correctement enregistrée"));
	    
	    //création d'un patient
	    Depensecourante depensecourante=new Depensecourante();
	    depensecourante.setLibelle(libelle);
	    depensecourante.setDebit(debit);
	    depenseService.saveDepenseCourante(depensecourante);
	    //mise à jour des champs à vide
	    resetParameters();
			return "newDepenseCourante";
		}

	public String createDepenseOrRevenu() {
	    System.out.println("Création de dépense ou revenu");
	    FacesContext.getCurrentInstance().addMessage("form1", new FacesMessage(FacesMessage.SEVERITY_INFO,"Création :", "Votre enregistrement a été correctement enregistrée"));
	    
	    //création d'un patient
	    Revenuordepense revenuordepense=new Revenuordepense();
	    revenuordepense.setLibelle(libelle);
	    revenuordepense.setMontant(montant);
	    revenuordepense.setType(type);
	    revenuordepense.setDate(date);
	    depenseService.saveDepenseOrRevenu(revenuordepense);
	    //mise à jour des champs à vide
	    //Mise à jour PostConstruct
	    init();
	    seePanel=false;
	    resetParameters();
	    try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("accueilBis.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			return "newDepenseCourante";
		}
	
	public void ajouter()
	{
		seePanel=true;
	}

	private void resetParameters()
	{
		libelle="";
		debit=null;
	}
	public String formateType(String type) {
		String retour=null;
		if(type!=null && type.equals("DEPREC"))
		{
			retour="Dépense";
		}
		else if(type!=null && type.equals("DEPEXC"))
		{
			retour="Dépense";	
		}
		else if(type!=null && type.equals("REVREC"))
		{
			retour="Revenu";	
		}
		else if(type!=null && type.equals("REVEXC"))
		{
			retour="Revenu";	
		}
		return retour;
	}
	
	
	public String formateNature(String type) {
		String retour=null;
		if(type!=null && type.equals("DEPREC"))
		{
			retour="Récurrente";
		}
		else if(type!=null && type.equals("DEPEXC"))
		{
			retour="Exceptionnelle";	
		}
		else if(type!=null && type.equals("REVREC"))
		{
			retour="Reccurent";	
		}
		else if(type!=null && type.equals("REVEXC"))
		{
			retour="Exceptionnel";	
		}
		return retour;
	}
	
	
	
	public String getImage(String type) {
		String retour=null;
		if(type!=null && type.equals("DEPREC"))
		{
			retour="img/moins.jpg";
		}
		else if(type!=null && type.equals("DEPEXC"))
		{
			retour="img/moins.jpg";
		}
		else if(type!=null && type.equals("REVREC"))
		{
			retour="img/plus.jpg";
		}
		else if(type!=null && type.equals("REVEXC"))
		{
			retour="img/plus.jpg";
		}
		return retour;
	}
	
	
//	public void onRowToggle(ToggleEvent event) {
//		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
//				"Visibilité " + event.getVisibility(), "Compte:"
//						+ ((Total) event.getData()).getId().getCompte());
//
//		FacesContext.getCurrentInstance().addMessage(null, msg);
//	}



    public void onRowEdit(RowEditEvent event) {
		Revenuordepense currentRevenuordepense=((Revenuordepense)event.getObject());  
		depenseService.saveDepenseOrRevenu(currentRevenuordepense);
		init();
    }
     
    public void onRowCancel(RowEditEvent event) {
        FacesContext.getCurrentInstance().addMessage(null, null);
    }

	public boolean showPanel()
	{
		return seePanel;
	}
	
	public boolean hidePanel()
	{
		return seePanel;
	}



	// private float computeRatio() {
	// float ratioResult = 0;
	// float ratio681 = 0;
	// for (Total total : this.listTotalRatio) {
	// if (total.getId().getCompte().equals("Total 681")) {
	// ratio681=Float.parseFloat(total.getId().getDebitMouvement().trim());
	// }
	// }
	// this.ratio=ratio681;
	// return ratio681;
	// }



	 public void onCellEdit(CellEditEvent event) {
		 DataTable dataTable =  (DataTable) event.getSource();
		 Revenuordepense currentRevenuordepense = (Revenuordepense) dataTable.getRowData(); 
		 depenseService.saveDepenseOrRevenu(currentRevenuordepense);
		 init();
		 initSolde();
		 RequestContext.getCurrentInstance().update("currentForm");
		 try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("accueilBis.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	 
	 public void onRowDblClckSelect(final SelectEvent event) {
		    //YourObject obj = (YourObject) event.getObject();
		    RequestContext context = RequestContext.getCurrentInstance();
	        context.execute("confirmDialog.show();");
		    System.out.println("");
		}
	 
	 public void onContextMenu() {
		 System.out.println();
	 }

	public String deconnect()
	{
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		     return "deconnexion";
	}



	public List<Depensecourante> getListDepensecourante() {
		return listDepensecourante;
	}



	public void setListDepensecourante(List<Depensecourante> listDepensecourante) {
		this.listDepensecourante = listDepensecourante;
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
		List<User> listUser = depenseService.findUserByLogin(login);
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
	
	public String formaterDate(Date date)
	{
		SimpleDateFormat  simpleFormat = new SimpleDateFormat("dd-MM-yyyy");
		String retour=simpleFormat.format(date);
		return retour;
	}
	
	public String formateAmount(Revenuordepense item)
	{
		String retour=null;
		if(item!=null)
		{
		if(item.getType().equals("DEPREC")|| item.getType().equals("DEPEXC"))
		{
			StringBuilder builder=new StringBuilder("-");
			if(item.getMontant()!=null)
			{
				retour=builder.append(item.getMontant()).toString();
			}
		}
		else
		{
			StringBuilder builder=new StringBuilder("+");
			if(item.getMontant()!=null)
			{
				retour=builder.append(item.getMontant()).toString();
			}
		}
		}
		return retour;
	}
	

	
	public String initSolde()
	{
		String retour;
		Float balance=new Float(0);
		Float balanceRevenu=new Float(0);
		Float balanceDepense=new Float(0);
		List<Revenuordepense> listRevenuOrDepense=depenseService.findAllRevenuordepense();
		for (Revenuordepense revenuordepense : listRevenuOrDepense) {
			if(revenuordepense.getType().equals("REVREC"))
			balanceRevenu=balanceRevenu+revenuordepense.getMontant();	
		}
		
		for (Revenuordepense revenuordepense : listRevenuOrDepense) {
			if(revenuordepense.getType().equals("DEPREC"))
				balanceDepense=balanceDepense+revenuordepense.getMontant();	
		}
		
		for (Revenuordepense revenuordepense : listRevenuOrDepense) {
			if(revenuordepense.getType().equals("REVEXC"))
			{
			if(revenuordepense.getDate().getMonth()==new Date().getMonth())
			{
			balanceRevenu=balanceRevenu+revenuordepense.getMontant();		
			}
			}
		}
		
		for (Revenuordepense revenuordepense : listRevenuOrDepense) {
			if(revenuordepense.getType().equals("DEPEXC"))
			{
			if(revenuordepense.getDate().getMonth()==new Date().getMonth())
			{
				balanceDepense=balanceDepense+revenuordepense.getMontant();		
			}
			}
		}
		
		balance=balanceRevenu-balanceDepense;
		if(balance>0)
		{
		retour="+"+balance.toString();
		}
		else
		retour=balance.toString();
		return retour;
	}
	
	
	public Float initSoldeMoisCourant()
	{
		String retour;
		Float balanceInit=initSoldePreviousMonth();
		Float balance=new Float(0);
		Float balanceRevenu=new Float(0);
		Float balanceDepense=new Float(0);
		List<Revenuordepense> listRevenuOrDepense=depenseService.findAllRevenuordepense();
		for (Revenuordepense revenuordepense : listRevenuOrDepense) {
			if(revenuordepense.getType().equals("REVREC"))
			balanceRevenu=balanceRevenu+revenuordepense.getMontant();	
		}
		
		for (Revenuordepense revenuordepense : listRevenuOrDepense) {
			if(revenuordepense.getType().equals("DEPREC"))
				balanceDepense=balanceDepense+revenuordepense.getMontant();	
		}
		
		for (Revenuordepense revenuordepense : listRevenuOrDepense) {
			if(revenuordepense.getType().equals("REVEXC"))
			{
			if(selectedMonth==null ||  selectedMonth.equals(""))
			{
			if(revenuordepense.getDate().getMonth()==new Date().getMonth())
			{
			balanceRevenu=balanceRevenu+revenuordepense.getMontant();		
			}
			}
			else
			{
			balanceRevenu = initRevExceptionnelByCurrentMonth(balanceRevenu,
					revenuordepense);
			}
			}
		}
		
		for (Revenuordepense revenuordepense : listRevenuOrDepense) {
			if(revenuordepense.getType().equals("DEPEXC"))
			{
			if(selectedMonth==null ||  selectedMonth.equals(""))
			{
			if(revenuordepense.getDate().getMonth()==new Date().getMonth())
			{
				balanceDepense=balanceDepense+revenuordepense.getMontant();		
			}
			}
			else
			{
				balanceDepense = initDepExceptionnelByMonth(balanceDepense,
						revenuordepense);
			}
			}
		}
		balance=balanceInit+(balanceRevenu-balanceDepense);
		return balance;
	}



	private Float initDepExceptionnelByMonth(Float balanceDepense,
			Revenuordepense revenuordepense) {
		if(selectedMonth.equals("janvier"))
		{
			if(revenuordepense.getDate().getMonth()==0)
			{
				balanceDepense=balanceDepense+revenuordepense.getMontant();			
			}	
		}
		if(selectedMonth.equals("fevrier"))
		{
			if(revenuordepense.getDate().getMonth()==1)
			{
				balanceDepense=balanceDepense+revenuordepense.getMontant();			
			}	
		}
		if(selectedMonth.equals("mars"))
		{
			if(revenuordepense.getDate().getMonth()==2)
			{
				balanceDepense=balanceDepense+revenuordepense.getMontant();			
			}	
		}
		if(selectedMonth.equals("avril"))
		{
			if(revenuordepense.getDate().getMonth()==3)
			{
				balanceDepense=balanceDepense+revenuordepense.getMontant();			
			}	
		}
		if(selectedMonth.equals("mai"))
		{
			if(revenuordepense.getDate().getMonth()==4)
			{
				balanceDepense=balanceDepense+revenuordepense.getMontant();			
			}	
		}
		if(selectedMonth.equals("juin"))
		{
			if(revenuordepense.getDate().getMonth()==5)
			{
				balanceDepense=balanceDepense+revenuordepense.getMontant();			
			}	
		}
		if(selectedMonth.equals("juillet"))
		{
			if(revenuordepense.getDate().getMonth()==6)
			{
				balanceDepense=balanceDepense+revenuordepense.getMontant();			
			}	
		}
		if(selectedMonth.equals("aout"))
		{
			if(revenuordepense.getDate().getMonth()==7)
			{
				balanceDepense=balanceDepense+revenuordepense.getMontant();			
			}	
		}
		if(selectedMonth.equals("septembre"))
		{
			if(revenuordepense.getDate().getMonth()==8)
			{
				balanceDepense=balanceDepense+revenuordepense.getMontant();			
			}	
		}
		if(selectedMonth.equals("octobre"))
		{
			if(revenuordepense.getDate().getMonth()==9)
			{
				balanceDepense=balanceDepense+revenuordepense.getMontant();			
			}	
		}
		if(selectedMonth.equals("novembre"))
		{
			if(revenuordepense.getDate().getMonth()==10)
			{
				balanceDepense=balanceDepense+revenuordepense.getMontant();			
			}	
		}
		if(selectedMonth.equals("decembre"))
		{
			if(revenuordepense.getDate().getMonth()==11)
			{
				balanceDepense=balanceDepense+revenuordepense.getMontant();			
			}	
		}
		return balanceDepense;
	}
	
	
	
	private Float initDepExceptionnelByPreviousMonth(Float balanceDepense,
			Revenuordepense revenuordepense) {
//		if(selectedMonth.equals("janvier"))
//		{
//			if(revenuordepense.getDate().getMonth()==0)
//			{
//				balanceDepense=balanceDepense+revenuordepense.getMontant();			
//			}	
//		}
		if(selectedMonth.equals("fevrier"))
		{
			if(revenuordepense.getDate().getMonth()==0)
			{
				balanceDepense=balanceDepense+revenuordepense.getMontant();			
			}	
		}
		if(selectedMonth.equals("mars"))
		{
			if(revenuordepense.getDate().getMonth()==1)
			{
				balanceDepense=balanceDepense+revenuordepense.getMontant();			
			}	
		}
		if(selectedMonth.equals("avril"))
		{
			if(revenuordepense.getDate().getMonth()==2)
			{
				balanceDepense=balanceDepense+revenuordepense.getMontant();			
			}	
		}
		if(selectedMonth.equals("mai"))
		{
			if(revenuordepense.getDate().getMonth()==3)
			{
				balanceDepense=balanceDepense+revenuordepense.getMontant();			
			}	
		}
		if(selectedMonth.equals("juin"))
		{
			if(revenuordepense.getDate().getMonth()==4)
			{
				balanceDepense=balanceDepense+revenuordepense.getMontant();			
			}	
		}
		if(selectedMonth.equals("juillet"))
		{
			if(revenuordepense.getDate().getMonth()==5)
			{
				balanceDepense=balanceDepense+revenuordepense.getMontant();			
			}	
		}
		if(selectedMonth.equals("aout"))
		{
			if(revenuordepense.getDate().getMonth()==6)
			{
				balanceDepense=balanceDepense+revenuordepense.getMontant();			
			}	
		}
		if(selectedMonth.equals("septembre"))
		{
			if(revenuordepense.getDate().getMonth()==7)
			{
				balanceDepense=balanceDepense+revenuordepense.getMontant();			
			}	
		}
		if(selectedMonth.equals("octobre"))
		{
			if(revenuordepense.getDate().getMonth()==8)
			{
				balanceDepense=balanceDepense+revenuordepense.getMontant();			
			}	
		}
		if(selectedMonth.equals("novembre"))
		{
			if(revenuordepense.getDate().getMonth()==9)
			{
				balanceDepense=balanceDepense+revenuordepense.getMontant();			
			}	
		}
		if(selectedMonth.equals("decembre"))
		{
			if(revenuordepense.getDate().getMonth()==10)
			{
				balanceDepense=balanceDepense+revenuordepense.getMontant();			
			}	
		}
		return balanceDepense;
	}



	private Float initRevExceptionnelByCurrentMonth(Float balanceRevenu,
			Revenuordepense revenuordepense) {
		balanceRevenu = initDepExceptionnelByMonth(balanceRevenu,
				revenuordepense);
		return balanceRevenu;
	}
	
	public Float initSoldePreviousMonth()
	{
		Float balanceInit=new Float(0);
		if(selectedMonth==null ||  selectedMonth.equals(""))
		{
		if(new Date().getMonth()==1)	
		{
		balanceInit=initSoldeJanvier();	
		}
		if(new Date().getMonth()==2)	
		{
		balanceInit=initSoldeJanvier()+initSoldeFevrier();	
		}
		if(new Date().getMonth()==3)	
		{
		balanceInit=initSoldeJanvier()+initSoldeFevrier()+initSoldeMars();	
		}
		if(new Date().getMonth()==4)	
		{
		balanceInit=initSoldeJanvier()+initSoldeFevrier()+initSoldeMars()+initSoldeAvril();	
		}
		if(new Date().getMonth()==5)	
		{
		balanceInit=initSoldeJanvier()+initSoldeFevrier()+initSoldeMars()+initSoldeAvril()+initSoldeMai();	
		}
		if(new Date().getMonth()==6)	
		{
			balanceInit=initSoldeJanvier()+initSoldeFevrier()+initSoldeMars()+initSoldeAvril()+initSoldeMai()+initSoldeJuin();	
		}
		if(new Date().getMonth()==7)	
		{
			balanceInit=initSoldeJanvier()+initSoldeFevrier()+initSoldeMars()+initSoldeAvril()+initSoldeMai()+initSoldeJuin()+initSoldeJuillet();	
		}
		if(new Date().getMonth()==8)	
		{
			balanceInit=initSoldeJanvier()+initSoldeFevrier()+initSoldeMars()+initSoldeAvril()+initSoldeMai()+initSoldeJuin()+initSoldeJuillet()+initSoldeAout();	
		}
		if(new Date().getMonth()==9)	
		{
			balanceInit=initSoldeJanvier()+initSoldeFevrier()+initSoldeMars()+initSoldeAvril()+initSoldeMai()+initSoldeJuin()+initSoldeJuillet()+initSoldeAout()+initSoldeSeptembre();	
		}
		if(new Date().getMonth()==10)	
		{
			balanceInit=initSoldeJanvier()+initSoldeFevrier()+initSoldeMars()+initSoldeAvril()+initSoldeMai()+initSoldeJuin()+initSoldeJuillet()+initSoldeAout()+initSoldeSeptembre()+initSoldeOctobre();	
		}
		if(new Date().getMonth()==11)	
		{
			balanceInit=initSoldeJanvier()+initSoldeFevrier()+initSoldeMars()+initSoldeAvril()+initSoldeMai()+initSoldeJuin()+initSoldeJuillet()+initSoldeAout()+initSoldeSeptembre()+initSoldeOctobre()+initSoldeNovembre();	
		}
		}
		else
		{
			if(selectedMonth!=null && selectedMonth.equals("fevrier"))
			{
			balanceInit=initSoldeJanvier();	
			}
			if(selectedMonth!=null && selectedMonth.equals("mars"))
			{
			balanceInit=initSoldeJanvier()+initSoldeFevrier();	
			}
			if(selectedMonth!=null && selectedMonth.equals("avril"))	
			{
			balanceInit=initSoldeJanvier()+initSoldeFevrier()+initSoldeMars();	
			}
			if(selectedMonth!=null && selectedMonth.equals("mai"))	
			{
			balanceInit=initSoldeJanvier()+initSoldeFevrier()+initSoldeMars()+initSoldeAvril();	
			}
			if(selectedMonth!=null && selectedMonth.equals("juin"))
			{
			balanceInit=initSoldeJanvier()+initSoldeFevrier()+initSoldeMars()+initSoldeAvril()+initSoldeMai();	
			}
			if(selectedMonth!=null && selectedMonth.equals("juillet"))
			{
				balanceInit=initSoldeJanvier()+initSoldeFevrier()+initSoldeMars()+initSoldeAvril()+initSoldeMai()+initSoldeJuin();	
			}
			if(selectedMonth!=null && selectedMonth.equals("aout"))
			{
				balanceInit=initSoldeJanvier()+initSoldeFevrier()+initSoldeMars()+initSoldeAvril()+initSoldeMai()+initSoldeJuin()+initSoldeJuillet();	
			}
			if(selectedMonth!=null && selectedMonth.equals("septembre"))
			{
				balanceInit=initSoldeJanvier()+initSoldeFevrier()+initSoldeMars()+initSoldeAvril()+initSoldeMai()+initSoldeJuin()+initSoldeJuillet()+initSoldeAout();	
			}
			if(selectedMonth!=null && selectedMonth.equals("octobre"))	
			{
				balanceInit=initSoldeJanvier()+initSoldeFevrier()+initSoldeMars()+initSoldeAvril()+initSoldeMai()+initSoldeJuin()+initSoldeJuillet()+initSoldeAout()+initSoldeSeptembre();	
			}
			if(selectedMonth!=null && selectedMonth.equals("novembre"))
			{
				balanceInit=initSoldeJanvier()+initSoldeFevrier()+initSoldeMars()+initSoldeAvril()+initSoldeMai()+initSoldeJuin()+initSoldeJuillet()+initSoldeAout()+initSoldeSeptembre()+initSoldeOctobre();	
			}
			if(selectedMonth!=null && selectedMonth.equals("decembre"))	
			{
				balanceInit=initSoldeJanvier()+initSoldeFevrier()+initSoldeMars()+initSoldeAvril()+initSoldeMai()+initSoldeJuin()+initSoldeJuillet()+initSoldeAout()+initSoldeSeptembre()+initSoldeOctobre()+initSoldeNovembre();	
			}	
		}
		return balanceInit;	
	}
	
	
	
	public Float initSoldeJanvier()
	{
		String retour;
		Float balance=new Float(0);
		Float balanceRevenu=new Float(0);
		Float balanceDepense=new Float(0);
		List<Revenuordepense> listRevenuOrDepense=depenseService.findAllRevenuordepense();
		for (Revenuordepense revenuordepense : listRevenuOrDepense) {
			if(revenuordepense.getType().equals("REVREC"))
			balanceRevenu=balanceRevenu+revenuordepense.getMontant();	
		}
		
		for (Revenuordepense revenuordepense : listRevenuOrDepense) {
			if(revenuordepense.getType().equals("DEPREC"))
				balanceDepense=balanceDepense+revenuordepense.getMontant();	
		}
		
		for (Revenuordepense revenuordepense : listRevenuOrDepense) {
			if(revenuordepense.getType().equals("REVEXC"))
			{
				if(revenuordepense.getDate().getMonth()==0)
				{
					balanceRevenu=balanceRevenu+revenuordepense.getMontant();		
				}
			}
		}
		
		for (Revenuordepense revenuordepense : listRevenuOrDepense) {
			if(revenuordepense.getType().equals("DEPEXC"))
			{
				if(revenuordepense.getDate().getMonth()==0)
				{
					balanceDepense=balanceDepense+revenuordepense.getMontant();		
				}
			}
		}
		balance=balanceRevenu-balanceDepense;
		return balance;	
	}
	
	
	public Float initSoldeFevrier()
	{
		String retour;
		Float balance=new Float(0);
		Float balanceRevenu=new Float(0);
		Float balanceDepense=new Float(0);
		List<Revenuordepense> listRevenuOrDepense=depenseService.findAllRevenuordepense();
		for (Revenuordepense revenuordepense : listRevenuOrDepense) {
			if(revenuordepense.getType().equals("REVREC"))
			balanceRevenu=balanceRevenu+revenuordepense.getMontant();	
		}
		
		for (Revenuordepense revenuordepense : listRevenuOrDepense) {
			if(revenuordepense.getType().equals("DEPREC"))
				balanceDepense=balanceDepense+revenuordepense.getMontant();	
		}
		
		for (Revenuordepense revenuordepense : listRevenuOrDepense) {
			if(revenuordepense.getType().equals("REVEXC"))
			{
				if(revenuordepense.getDate().getMonth()==1)
				{
					balanceRevenu=balanceRevenu+revenuordepense.getMontant();		
				}
			}
		}
		
		for (Revenuordepense revenuordepense : listRevenuOrDepense) {
			if(revenuordepense.getType().equals("DEPEXC"))
			{
				if(revenuordepense.getDate().getMonth()==1)
				{
					balanceDepense=balanceDepense+revenuordepense.getMontant();		
				}
			}
		}
		balance=balanceRevenu-balanceDepense;
		return balance;	
	}
	
	
	public Float initSoldeMars()
	{
		String retour;
		Float balance=new Float(0);
		Float balanceRevenu=new Float(0);
		Float balanceDepense=new Float(0);
		List<Revenuordepense> listRevenuOrDepense=depenseService.findAllRevenuordepense();
		for (Revenuordepense revenuordepense : listRevenuOrDepense) {
			if(revenuordepense.getType().equals("REVREC"))
			balanceRevenu=balanceRevenu+revenuordepense.getMontant();	
		}
		
		for (Revenuordepense revenuordepense : listRevenuOrDepense) {
			if(revenuordepense.getType().equals("DEPREC"))
				balanceDepense=balanceDepense+revenuordepense.getMontant();	
		}
		
		for (Revenuordepense revenuordepense : listRevenuOrDepense) {
			if(revenuordepense.getType().equals("REVEXC"))
			{
				if(revenuordepense.getDate().getMonth()==2)
				{
					balanceRevenu=balanceRevenu+revenuordepense.getMontant();		
				}
			}
		}
		
		for (Revenuordepense revenuordepense : listRevenuOrDepense) {
			if(revenuordepense.getType().equals("DEPEXC"))
			{
				if(revenuordepense.getDate().getMonth()==2)
				{
					balanceDepense=balanceDepense+revenuordepense.getMontant();		
				}
			}
		}
		balance=balanceRevenu-balanceDepense;
		return balance;	
	}
	
	public Float initSoldeAvril()
	{
		String retour;
		Float balance=new Float(0);
		Float balanceRevenu=new Float(0);
		Float balanceDepense=new Float(0);
		List<Revenuordepense> listRevenuOrDepense=depenseService.findAllRevenuordepense();
		for (Revenuordepense revenuordepense : listRevenuOrDepense) {
			if(revenuordepense.getType().equals("REVREC"))
			balanceRevenu=balanceRevenu+revenuordepense.getMontant();	
		}
		
		for (Revenuordepense revenuordepense : listRevenuOrDepense) {
			if(revenuordepense.getType().equals("DEPREC"))
				balanceDepense=balanceDepense+revenuordepense.getMontant();	
		}
		
		for (Revenuordepense revenuordepense : listRevenuOrDepense) {
			if(revenuordepense.getType().equals("REVEXC"))
			{
				if(revenuordepense.getDate().getMonth()==3)
				{
					balanceRevenu=balanceRevenu+revenuordepense.getMontant();		
				}
			}
		}
		
		for (Revenuordepense revenuordepense : listRevenuOrDepense) {
			if(revenuordepense.getType().equals("DEPEXC"))
			{
				if(revenuordepense.getDate().getMonth()==3)
				{
					balanceDepense=balanceDepense+revenuordepense.getMontant();		
				}
			}
		}
		balance=balanceRevenu-balanceDepense;
		return balance;	
	}
	
	
	
	public Float initSoldeMai()
	{
		String retour;
		Float balance=new Float(0);
		Float balanceRevenu=new Float(0);
		Float balanceDepense=new Float(0);
		List<Revenuordepense> listRevenuOrDepense=depenseService.findAllRevenuordepense();
		for (Revenuordepense revenuordepense : listRevenuOrDepense) {
			if(revenuordepense.getType().equals("REVREC"))
			balanceRevenu=balanceRevenu+revenuordepense.getMontant();	
		}
		
		for (Revenuordepense revenuordepense : listRevenuOrDepense) {
			if(revenuordepense.getType().equals("DEPREC"))
				balanceDepense=balanceDepense+revenuordepense.getMontant();	
		}
		
		for (Revenuordepense revenuordepense : listRevenuOrDepense) {
			if(revenuordepense.getType().equals("REVEXC"))
			{
				if(revenuordepense.getDate().getMonth()==4)
				{
					balanceRevenu=balanceRevenu+revenuordepense.getMontant();		
				}
			}
		}
		
		for (Revenuordepense revenuordepense : listRevenuOrDepense) {
			if(revenuordepense.getType().equals("DEPEXC"))
			{
				if(revenuordepense.getDate().getMonth()==4)
				{
					balanceDepense=balanceDepense+revenuordepense.getMontant();		
				}
			}
		}
		balance=balanceRevenu-balanceDepense;
		return balance;	
	}
	
	public Float initSoldeJuin()
	{
		String retour;
		Float balance=new Float(0);
		Float balanceRevenu=new Float(0);
		Float balanceDepense=new Float(0);
		List<Revenuordepense> listRevenuOrDepense=depenseService.findAllRevenuordepense();
		for (Revenuordepense revenuordepense : listRevenuOrDepense) {
			if(revenuordepense.getType().equals("REVREC"))
			balanceRevenu=balanceRevenu+revenuordepense.getMontant();	
		}
		
		for (Revenuordepense revenuordepense : listRevenuOrDepense) {
			if(revenuordepense.getType().equals("DEPREC"))
				balanceDepense=balanceDepense+revenuordepense.getMontant();	
		}
		
		for (Revenuordepense revenuordepense : listRevenuOrDepense) {
			if(revenuordepense.getType().equals("REVEXC"))
			{
				if(revenuordepense.getDate().getMonth()==5)
				{
					balanceRevenu=balanceRevenu+revenuordepense.getMontant();		
				}
			}
		}
		
		for (Revenuordepense revenuordepense : listRevenuOrDepense) {
			if(revenuordepense.getType().equals("DEPEXC"))
			{
				if(revenuordepense.getDate().getMonth()==5)
				{
					balanceDepense=balanceDepense+revenuordepense.getMontant();		
				}
			}
		}
		balance=balanceRevenu-balanceDepense;
		return balance;	
	}
	
	public Float initSoldeJuillet()
	{
		String retour;
		Float balance=new Float(0);
		Float balanceRevenu=new Float(0);
		Float balanceDepense=new Float(0);
		List<Revenuordepense> listRevenuOrDepense=depenseService.findAllRevenuordepense();
		for (Revenuordepense revenuordepense : listRevenuOrDepense) {
			if(revenuordepense.getType().equals("REVREC"))
			balanceRevenu=balanceRevenu+revenuordepense.getMontant();	
		}
		
		for (Revenuordepense revenuordepense : listRevenuOrDepense) {
			if(revenuordepense.getType().equals("DEPREC"))
				balanceDepense=balanceDepense+revenuordepense.getMontant();	
		}
		
		for (Revenuordepense revenuordepense : listRevenuOrDepense) {
			if(revenuordepense.getType().equals("REVEXC"))
			{
				if(revenuordepense.getDate().getMonth()==6)
				{
					balanceRevenu=balanceRevenu+revenuordepense.getMontant();		
				}
			}
		}
		
		for (Revenuordepense revenuordepense : listRevenuOrDepense) {
			if(revenuordepense.getType().equals("DEPEXC"))
			{
				if(revenuordepense.getDate().getMonth()==6)
				{
					balanceDepense=balanceDepense+revenuordepense.getMontant();		
				}
			}
		}
		balance=balanceRevenu-balanceDepense;
		return balance;	
	}
	
	public Float initSoldeAout()
	{
		String retour;
		Float balance=new Float(0);
		Float balanceRevenu=new Float(0);
		Float balanceDepense=new Float(0);
		List<Revenuordepense> listRevenuOrDepense=depenseService.findAllRevenuordepense();
		for (Revenuordepense revenuordepense : listRevenuOrDepense) {
			if(revenuordepense.getType().equals("REVREC"))
			balanceRevenu=balanceRevenu+revenuordepense.getMontant();	
		}
		
		for (Revenuordepense revenuordepense : listRevenuOrDepense) {
			if(revenuordepense.getType().equals("DEPREC"))
				balanceDepense=balanceDepense+revenuordepense.getMontant();	
		}
		
		for (Revenuordepense revenuordepense : listRevenuOrDepense) {
			if(revenuordepense.getType().equals("REVEXC"))
			{
				if(revenuordepense.getDate().getMonth()==7)
				{
					balanceRevenu=balanceRevenu+revenuordepense.getMontant();		
				}
			}
		}
		
		for (Revenuordepense revenuordepense : listRevenuOrDepense) {
			if(revenuordepense.getType().equals("DEPEXC"))
			{
				if(revenuordepense.getDate().getMonth()==7)
				{
					balanceDepense=balanceDepense+revenuordepense.getMontant();		
				}
			}
		}
		balance=balanceRevenu-balanceDepense;
		return balance;	
	}
	
	public Float initSoldeSeptembre()
	{
		String retour;
		Float balance=new Float(0);
		Float balanceRevenu=new Float(0);
		Float balanceDepense=new Float(0);
		List<Revenuordepense> listRevenuOrDepense=depenseService.findAllRevenuordepense();
		for (Revenuordepense revenuordepense : listRevenuOrDepense) {
			if(revenuordepense.getType().equals("REVREC"))
			balanceRevenu=balanceRevenu+revenuordepense.getMontant();	
		}
		
		for (Revenuordepense revenuordepense : listRevenuOrDepense) {
			if(revenuordepense.getType().equals("DEPREC"))
				balanceDepense=balanceDepense+revenuordepense.getMontant();	
		}
		
		for (Revenuordepense revenuordepense : listRevenuOrDepense) {
			if(revenuordepense.getType().equals("REVEXC"))
			{
				if(revenuordepense.getDate().getMonth()==8)
				{
					balanceRevenu=balanceRevenu+revenuordepense.getMontant();		
				}
			}
		}
		
		for (Revenuordepense revenuordepense : listRevenuOrDepense) {
			if(revenuordepense.getType().equals("DEPEXC"))
			{
				if(revenuordepense.getDate().getMonth()==8)
				{
					balanceDepense=balanceDepense+revenuordepense.getMontant();		
				}
			}
		}
		balance=balanceRevenu-balanceDepense;
		return balance;	
	}
	
	
	public Float initSoldeOctobre()
	{
		String retour;
		Float balance=new Float(0);
		Float balanceRevenu=new Float(0);
		Float balanceDepense=new Float(0);
		List<Revenuordepense> listRevenuOrDepense=depenseService.findAllRevenuordepense();
		for (Revenuordepense revenuordepense : listRevenuOrDepense) {
			if(revenuordepense.getType().equals("REVREC"))
			balanceRevenu=balanceRevenu+revenuordepense.getMontant();	
		}
		
		for (Revenuordepense revenuordepense : listRevenuOrDepense) {
			if(revenuordepense.getType().equals("DEPREC"))
				balanceDepense=balanceDepense+revenuordepense.getMontant();	
		}
		
		for (Revenuordepense revenuordepense : listRevenuOrDepense) {
			if(revenuordepense.getType().equals("REVEXC"))
			{
				if(revenuordepense.getDate().getMonth()==9)
				{
					balanceRevenu=balanceRevenu+revenuordepense.getMontant();		
				}
			}
		}
		
		for (Revenuordepense revenuordepense : listRevenuOrDepense) {
			if(revenuordepense.getType().equals("DEPEXC"))
			{
				if(revenuordepense.getDate().getMonth()==9)
				{
					balanceDepense=balanceDepense+revenuordepense.getMontant();		
				}
			}
		}
		balance=balanceRevenu-balanceDepense;
		return balance;	
	}
	
	public Float initSoldeNovembre()
	{
		String retour;
		Float balance=new Float(0);
		Float balanceRevenu=new Float(0);
		Float balanceDepense=new Float(0);
		List<Revenuordepense> listRevenuOrDepense=depenseService.findAllRevenuordepense();
		for (Revenuordepense revenuordepense : listRevenuOrDepense) {
			if(revenuordepense.getType().equals("REVREC"))
			balanceRevenu=balanceRevenu+revenuordepense.getMontant();	
		}
		
		for (Revenuordepense revenuordepense : listRevenuOrDepense) {
			if(revenuordepense.getType().equals("DEPREC"))
				balanceDepense=balanceDepense+revenuordepense.getMontant();	
		}
		
		for (Revenuordepense revenuordepense : listRevenuOrDepense) {
			if(revenuordepense.getType().equals("REVEXC"))
			{
				if(revenuordepense.getDate().getMonth()==10)
				{
					balanceRevenu=balanceRevenu+revenuordepense.getMontant();		
				}
			}
		}
		
		for (Revenuordepense revenuordepense : listRevenuOrDepense) {
			if(revenuordepense.getType().equals("DEPEXC"))
			{
				if(revenuordepense.getDate().getMonth()==10)
				{
					balanceDepense=balanceDepense+revenuordepense.getMontant();		
				}
			}
		}
		balance=balanceRevenu-balanceDepense;
		return balance;	
	}
	
	public Float initSoldeDecembre()
	{
		String retour;
		Float balance=new Float(0);
		Float balanceRevenu=new Float(0);
		Float balanceDepense=new Float(0);
		List<Revenuordepense> listRevenuOrDepense=depenseService.findAllRevenuordepense();
		for (Revenuordepense revenuordepense : listRevenuOrDepense) {
			if(revenuordepense.getType().equals("REVREC"))
			balanceRevenu=balanceRevenu+revenuordepense.getMontant();	
		}
		
		for (Revenuordepense revenuordepense : listRevenuOrDepense) {
			if(revenuordepense.getType().equals("DEPREC"))
				balanceDepense=balanceDepense+revenuordepense.getMontant();	
		}
		
		for (Revenuordepense revenuordepense : listRevenuOrDepense) {
			if(revenuordepense.getType().equals("REVEXC"))
			{
				if(revenuordepense.getDate().getMonth()==11)
				{
					balanceRevenu=balanceRevenu+revenuordepense.getMontant();		
				}
			}
		}
		
		for (Revenuordepense revenuordepense : listRevenuOrDepense) {
			if(revenuordepense.getType().equals("DEPEXC"))
			{
				if(revenuordepense.getDate().getMonth()==11)
				{
					balanceDepense=balanceDepense+revenuordepense.getMontant();		
				}
			}
		}
		balance=balanceRevenu-balanceDepense;
		return balance;	
	}
	
	
	public Float initSoldeMoisSuivant()
	{
		String retour;
		Float balanceInit=initSoldeMoisCourant();
		Float balanceRevenu=new Float(0);
		Float balance=new Float(0);
		Float balanceDepense=new Float(0);
		List<Revenuordepense> listRevenuOrDepense=depenseService.findAllRevenuordepense();
		for (Revenuordepense revenuordepense : listRevenuOrDepense) {
			if(revenuordepense.getType().equals("REVREC"))
			balanceRevenu=balanceRevenu+revenuordepense.getMontant();	
		}
		
		for (Revenuordepense revenuordepense : listRevenuOrDepense) {
			if(revenuordepense.getType().equals("DEPREC"))
				balanceDepense=balanceDepense+revenuordepense.getMontant();	
		}
		
		for (Revenuordepense revenuordepense : listRevenuOrDepense) {
			if(revenuordepense.getType().equals("REVEXC"))
			{
			if(revenuordepense.getDate().getMonth()==new Date().getMonth()+1)
			{
			balanceRevenu=balanceRevenu+revenuordepense.getMontant();		
			}
			}
		}
		
		for (Revenuordepense revenuordepense : listRevenuOrDepense) {
			if(revenuordepense.getType().equals("DEPEXC"))
			{
			if(revenuordepense.getDate().getMonth()==new Date().getMonth()+1)
			{
				balanceDepense=balanceDepense+revenuordepense.getMontant();		
			}
			}
		}
		balance=balanceInit+(balanceRevenu-balanceDepense);
		return balance;
	}
	
	
	public Float initSoldeMoisApres()
	{
		String retour;
		Float balanceInit=initSoldeMoisSuivant();
		Float balance=new Float(0);
		Float balanceRevenu=new Float(0);
		Float balanceDepense=new Float(0);
		List<Revenuordepense> listRevenuOrDepense=depenseService.findAllRevenuordepense();
		for (Revenuordepense revenuordepense : listRevenuOrDepense) {
			if(revenuordepense.getType().equals("REVREC"))
			balanceRevenu=balanceRevenu+revenuordepense.getMontant();	
		}
		
		for (Revenuordepense revenuordepense : listRevenuOrDepense) {
			if(revenuordepense.getType().equals("DEPREC"))
				balanceDepense=balanceDepense+revenuordepense.getMontant();	
		}
		
		for (Revenuordepense revenuordepense : listRevenuOrDepense) {
			if(revenuordepense.getType().equals("REVEXC"))
			{
			if(revenuordepense.getDate().getMonth()==new Date().getMonth()+2)
			{
			balanceRevenu=balanceRevenu+revenuordepense.getMontant();		
			}
			}
		}
		
		for (Revenuordepense revenuordepense : listRevenuOrDepense) {
			if(revenuordepense.getType().equals("DEPEXC"))
			{
			if(revenuordepense.getDate().getMonth()==new Date().getMonth()+2)
			{
				balanceDepense=balanceDepense+revenuordepense.getMontant();		
			}
			}
		}
		balance=balanceInit+(balanceRevenu-balanceDepense);
		return balance;
	}
	
	
	public String computeSolde(Revenuordepense item)
	{
		String retour;
		Float balanceInit=initSoldePreviousMonth();
		Float balance=new Float(0);
		Float balanceRevenu=new Float(0);
		Float balanceDepense=new Float(0);
		List<Revenuordepense> listRevenu=depenseService.findRevenuByDate(item.getDate());
		for (Revenuordepense revenuordepense : listRevenu) {
			if(selectedMonth==null || selectedMonth.equals(""))
			{
			if((revenuordepense.getDate().getMonth()==new Date().getMonth()))
			{
				balanceRevenu=balanceRevenu+revenuordepense.getMontant();	
			}
			}
			else
			{
				balanceRevenu = initRevExceptionelForSoldeByMonth(balanceRevenu,
						revenuordepense);
			}	
		}
		
		List<Revenuordepense> listRevenuRec=depenseService.findListRevReccurent();
		for (Revenuordepense revenuordepense : listRevenuRec) {
			balanceRevenu=balanceRevenu+revenuordepense.getMontant();	
		}
		
		List<Revenuordepense> listDepense=depenseService.findDepenseDate(item.getDate());
		for (Revenuordepense revenuordepense : listDepense) {
			if(selectedMonth==null || selectedMonth.equals(""))
			{
				if((revenuordepense.getDate().getMonth()==new Date().getMonth()))
				{
					balanceDepense=balanceDepense+revenuordepense.getMontant();	
				}
			}
			else
			{
				balanceDepense = initDepExceptionnelForSolde(balanceDepense,
						revenuordepense);
			}
		}
		
		List<Revenuordepense> listDepenseRec=depenseService.findDepRevReccurent();
		for (Revenuordepense revenuordepense : listDepenseRec) {
			balanceDepense=balanceDepense+revenuordepense.getMontant();	
		}
		balance=balanceInit+(balanceRevenu-balanceDepense);
		if(balance>0)
		{
		retour="+"+balance.toString();
		}
		else
		retour=balance.toString();
		return retour;
	}



	private Float initDepExceptionnelForSolde(Float balanceDepense,
			Revenuordepense revenuordepense) {
		if(selectedMonth!=null && selectedMonth.equals("janvier"))
		{
			if((revenuordepense.getDate().getMonth()==0))
			{
				balanceDepense=balanceDepense+revenuordepense.getMontant();	
			}
		}
		if(selectedMonth!=null && selectedMonth.equals("fevrier"))
		{
			if((revenuordepense.getDate().getMonth()==1))
			{
				balanceDepense=balanceDepense+revenuordepense.getMontant();	
			}
		}
		if(selectedMonth!=null && selectedMonth.equals("mars"))
		{
			if((revenuordepense.getDate().getMonth()==2))
			{
				balanceDepense=balanceDepense+revenuordepense.getMontant();	
			}
		}
		if(selectedMonth!=null && selectedMonth.equals("avril"))
		{
			if((revenuordepense.getDate().getMonth()==3))
			{
				balanceDepense=balanceDepense+revenuordepense.getMontant();	
			}
		}
		if(selectedMonth!=null && selectedMonth.equals("mai"))
		{
			if((revenuordepense.getDate().getMonth()==4))
			{
				balanceDepense=balanceDepense+revenuordepense.getMontant();	
			}
		}
		if(selectedMonth!=null && selectedMonth.equals("juin"))
		{
			if((revenuordepense.getDate().getMonth()==5))
			{
				balanceDepense=balanceDepense+revenuordepense.getMontant();	
			}
		}
		if(selectedMonth!=null && selectedMonth.equals("juillet"))
		{
			if((revenuordepense.getDate().getMonth()==6))
			{
				balanceDepense=balanceDepense+revenuordepense.getMontant();	
			}
		}
		if(selectedMonth!=null && selectedMonth.equals("aout"))
		{
			if((revenuordepense.getDate().getMonth()==7))
			{
				balanceDepense=balanceDepense+revenuordepense.getMontant();	
			}
		}
		if(selectedMonth!=null && selectedMonth.equals("septembre"))
		{
			if((revenuordepense.getDate().getMonth()==8))
			{
				balanceDepense=balanceDepense+revenuordepense.getMontant();	
			}
		}
		if(selectedMonth!=null && selectedMonth.equals("octobre"))
		{
			if((revenuordepense.getDate().getMonth()==9))
			{
				balanceDepense=balanceDepense+revenuordepense.getMontant();	
			}
		}
		if(selectedMonth!=null && selectedMonth.equals("novembre"))
		{
			if((revenuordepense.getDate().getMonth()==10))
			{
				balanceDepense=balanceDepense+revenuordepense.getMontant();	
			}
		}
		if(selectedMonth!=null && selectedMonth.equals("decembre"))
		{
			if((revenuordepense.getDate().getMonth()==11))
			{
				balanceDepense=balanceDepense+revenuordepense.getMontant();	
			}
		}
		return balanceDepense;
	}



	private Float initRevExceptionelForSoldeByMonth(Float balanceRevenu,
			Revenuordepense revenuordepense) {
		balanceRevenu = initDepExceptionnelForSolde(balanceRevenu,
				revenuordepense);
		return balanceRevenu;
	}
	
	
	public String computeSoldeMoisSuivant(Revenuordepense item)
	{
		String retour;
		Float currentBalance=initSoldeMoisCourant();
		Float balance=new Float(0);
		Float balanceRevenu=new Float(0);
		Float balanceDepense=new Float(0);
		List<Revenuordepense> listRevenu=depenseService.findRevenuByDate(item.getDate());
		for (Revenuordepense revenuordepense : listRevenu) {
			if(revenuordepense.getDate().getMonth()==new Date().getMonth()+1)
			{
				balanceRevenu=balanceRevenu+revenuordepense.getMontant();	
			}
			
		}
		
		List<Revenuordepense> listRevenuRec=depenseService.findListRevReccurent();
		for (Revenuordepense revenuordepense : listRevenuRec) {
			balanceRevenu=balanceRevenu+revenuordepense.getMontant();	
		}
		
		List<Revenuordepense> listDepense=depenseService.findDepenseDate(item.getDate());
		for (Revenuordepense revenuordepense : listDepense) {
			if(revenuordepense.getDate().getMonth()==new Date().getMonth()+1)
			{
				balanceDepense=balanceDepense+revenuordepense.getMontant();	
			}
			
		}
		
		List<Revenuordepense> listDepenseRec=depenseService.findDepRevReccurent();
		for (Revenuordepense revenuordepense : listDepenseRec) {
			balanceDepense=balanceDepense+revenuordepense.getMontant();	
		}
		
		balance=currentBalance+(balanceRevenu-balanceDepense);
		if(balance>0)
		{
		retour="+"+balance.toString();
		}
		else
		retour=balance.toString();
		return retour;
	}
	
	public String computeSoldeMoisApres(Revenuordepense item)
	{
		String retour;
		Float currentBalance=initSoldeMoisSuivant();
		Float balance=new Float(0);
		Float balanceRevenu=new Float(0);
		Float balanceDepense=new Float(0);
		List<Revenuordepense> listRevenu=depenseService.findRevenuByDate(item.getDate());
		for (Revenuordepense revenuordepense : listRevenu) {
			if(revenuordepense.getDate().getMonth()==new Date().getMonth()+2)
			{
				balanceRevenu=balanceRevenu+revenuordepense.getMontant();
			}
				
		}
		
		List<Revenuordepense> listRevenuRec=depenseService.findListRevReccurent();
		for (Revenuordepense revenuordepense : listRevenuRec) {
			balanceRevenu=balanceRevenu+revenuordepense.getMontant();	
		}
		
		List<Revenuordepense> listDepense=depenseService.findDepenseDate(item.getDate());
		for (Revenuordepense revenuordepense : listDepense) {
			if(revenuordepense.getDate().getMonth()==new Date().getMonth()+2)
			{
				balanceDepense=balanceDepense+revenuordepense.getMontant();	
			}
		}
		
		List<Revenuordepense> listDepenseRec=depenseService.findDepRevReccurent();
		for (Revenuordepense revenuordepense : listDepenseRec) {
			balanceDepense=balanceDepense+revenuordepense.getMontant();	
		}
		
		balance=currentBalance+(balanceRevenu-balanceDepense);
		if(balance>0)
		{
		retour="+"+balance.toString();
		}
		else
		retour=balance.toString();
		return retour;
	}
	



	public String getInitCarroussel() {
		String retour="xxxx";
		if(new Date().getMonth()==5)
		{
		retour="Mois de Juin";	
		}
		else 
		if(new Date().getMonth()==6)
		{
		retour="Mois de Juillet";	
		}
		else
		if(new Date().getMonth()==6)
		{
		retour="Mois d'Août";	
		}
		retour="Tableau de bord";
		return retour;
	}
	
	public void updateListByMonth()
	{
		if(selectedMonth==null ||  selectedMonth.equals(""))
		{
			return;
		}
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("accueilBis.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void selectOneMenuListener(ValueChangeEvent event) {
		Object newValue = event.getNewValue();
		selectedMonth=(String) event.getNewValue();
		init();
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("accueilBis.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   // Object newValue = event.getNewValue(); 
	}
	
	
	public String initHeader()
	{
		String header = null;
		if(selectedMonth==null ||  selectedMonth.equals(""))
		{
			if(new Date().getMonth()==5)
			{
				header="Mois de Juin";	
			}
			else 
			if(new Date().getMonth()==6)
			{
				header="Mois de Juillet";	
			}
			else
			if(new Date().getMonth()==7)
			{
				header="Mois d'Août";	
			}
		}
		else
		{
		header=selectedMonth;
		}
		return header;
		
	}

	public void setInitCarroussel(String initCarroussel) {
		this.initCarroussel = initCarroussel;
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

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public Long getDebit() {
		return debit;
	}

	public void setDebit(Long debit) {
		this.debit = debit;
	}



	public List<String> getListLibelle() {
		return listLibelle;
	}

	public void setListLibelle(List<String> listLibelle) {
		this.listLibelle = listLibelle;
	}

	public List<Revenuordepense> getListDepenseOrRevenu() {
		return listDepenseOrRevenu;
	}
	
	public void setListDepenseOrRevenu(List<Revenuordepense> listDepenseOrRevenu) {
		this.listDepenseOrRevenu = listDepenseOrRevenu;
	}

	public Long getMontant() {
		return montant;
	}
	
	public void setMontant(Long montant) {
		this.montant = montant;
	}

	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public boolean isSeePanel() {
		return seePanel;
	}

	public void setSeePanel(boolean seePanel) {
		this.seePanel = seePanel;
	}




	public List<Revenuordepense> getListDepenseOrRevenuMoisSuivant() {
		return listDepenseOrRevenuMoisSuivant;
	}



	public void setListDepenseOrRevenuMoisSuivant(
			List<Revenuordepense> listDepenseOrRevenuMoisSuivant) {
		this.listDepenseOrRevenuMoisSuivant = listDepenseOrRevenuMoisSuivant;
	}



	public List<Revenuordepense> getListDepenseOrRevenuMoisApres() {
		return listDepenseOrRevenuMoisApres;
	}



	public void setListDepenseOrRevenuMoisApres(
			List<Revenuordepense> listDepenseOrRevenuMoisApres) {
		this.listDepenseOrRevenuMoisApres = listDepenseOrRevenuMoisApres;
	}



	public List<Revenuordepense> getListDepenseOrRevenuMoisApresApres() {
		return listDepenseOrRevenuMoisApresApres;
	}



	public void setListDepenseOrRevenuMoisApresApres(
			List<Revenuordepense> listDepenseOrRevenuMoisApresApres) {
		this.listDepenseOrRevenuMoisApresApres = listDepenseOrRevenuMoisApresApres;
	}



	public List<Revenuordepense> getListDepenseOrRevenuMoisCurrent() {
		return listDepenseOrRevenuMoisCurrent;
	}



	public void setListDepenseOrRevenuMoisCurrent(
			List<Revenuordepense> listDepenseOrRevenuMoisCurrent) {
		this.listDepenseOrRevenuMoisCurrent = listDepenseOrRevenuMoisCurrent;
	}
	
	public void test(ValueChangeEvent event)
	{
		System.out.println("test");
	}

	 public void deleteRevenuordepense() {
		depenseService.deleteRevenuOrDepense(selectedRevenuordepense);
	}
	
	public Revenuordepense getSelectedRevenuordepense() {
		return selectedRevenuordepense;
	}
	
	public void setSelectedRevenuordepense(Revenuordepense selectedRevenuordepense) {
		this.selectedRevenuordepense = selectedRevenuordepense;
	}



	public String getSelectedMonth() {
		return selectedMonth;
	}



	public void setSelectedMonth(String selectedMonth) {
		this.selectedMonth = selectedMonth;
	}
	
	
}