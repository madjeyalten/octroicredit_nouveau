package com.otv.managed.bean;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.NamingContainer;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.component.celleditor.CellEditor;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.ToggleEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import org.primefaces.component.celleditor.CellEditor;
import com.otv.model.mod.Depensecourante;
import com.otv.model.mod.Depenseexception;
import com.otv.model.mod.User;
import com.otv.user.service.DepenseService;
import javax.faces.context.FacesContext;

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
@ManagedBean(name = "depenseExceptionBean")
@Component("depenseExceptionBean")
@SessionScoped
public class DepenseExceptionBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final String SUCCESS = "success";
	private static final String ERROR = "error";
	private String libelle;
	private Long debit;
	private Date dateValeur;

	public List<Depenseexception> listDepenseException;

	@Autowired
	private transient DepenseService depenseService;

	private int id;
	private String name;

	@PostConstruct
	public void init() {
		listDepenseException = depenseService.findAllDepenseException();
	}

	 

	
	public String createDepenseException() {
	    System.out.println("Création de dépense exceptionnelle");
	    FacesContext.getCurrentInstance().addMessage("form1", new FacesMessage(FacesMessage.SEVERITY_INFO,"Création Dépense :", "Votre dépense exceptionnelle a été correctement enregistrée"));
	    String mois=null;
	    //création d'un patient
	    Depenseexception depenseexception=new Depenseexception();
	    depenseexception.setLibelle(libelle);
	    depenseexception.setDebit(debit);
	    //gestion date
	    DateFormat mediumDateFormat = DateFormat.getDateTimeInstance(
	    DateFormat.MEDIUM,
	    DateFormat.MEDIUM);
	    String cuurrentDate=mediumDateFormat.format(dateValeur);
	    if(cuurrentDate.contains("janvier"))
	    {
	    	mois="janvier";
	    }
	    else if(cuurrentDate.contains("fevrier"))
	    {
	    	mois="fevrier";
	    }
	    else if(cuurrentDate.contains("mars"))
	    {
	    	mois="mars";
	    }
	    else if(cuurrentDate.contains("avril"))
	    {
	    	mois="avril";
	    }
	    else if(cuurrentDate.contains("mai"))
	    {
	    	mois="mai";
	    }
	    else if(cuurrentDate.contains("juin"))
	    {
	    	mois="juin";
	    }
	    else if(cuurrentDate.contains("juillet"))
	    {
	    	mois="juillet";
	    }
	    else if(cuurrentDate.contains("août"))
	    {
	    	mois="août";
	    }
	    depenseexception.setDateValeur(mois);
	    depenseService.saveDepenseException(depenseexception);
	    //mise à jour des champs à vide
	    resetParameters();
			return "newDepenseException";
		}

	private void resetParameters()
	{
		libelle="";
		debit=null;
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




	public List<Depenseexception> getListDepenseException() {
		return listDepenseException;
	}

	public void setListDepenseException(List<Depenseexception> listDepenseException) {
		this.listDepenseException = listDepenseException;
	}

	public Date getDateValeur() {
		return dateValeur;
	}
	public void setDateValeur(Date dateValeur) {
		this.dateValeur = dateValeur;
	}

}