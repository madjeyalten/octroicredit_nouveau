package com.otv.managed.bean;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
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
import com.otv.model.mod.Revenucourant;
import com.otv.model.mod.Revenuexceptionnel;
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
@ManagedBean(name = "revenuCourantBean")
@Component("revenuCourantBean")
@SessionScoped
public class RevenuCourantBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final String SUCCESS = "success";
	private static final String ERROR = "error";
	private String libelle;
	private Long debit;

	public List<Revenucourant> listRevenuCourant;

	@Autowired
	private transient DepenseService depenseService;

	private int id;
	private String name;

	@PostConstruct
	public void init() {
		listRevenuCourant = depenseService.findAllRevenucourant();
	}

	 

	
	public String createRevenuCourant() {
	    System.out.println("Création de revenu courant");
	    FacesContext.getCurrentInstance().addMessage("form1", new FacesMessage(FacesMessage.SEVERITY_INFO,"Création Dépense :", "Votre revenu courant a été correctement enregistrée"));
	    
	    //création d'un patient
	    Revenucourant revenucourant=new Revenucourant();
	    revenucourant.setLibelle(libelle);
	    revenucourant.setDebit(debit);
	    depenseService.saveRevenucourant(revenucourant);
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




	public List<Revenucourant> getListRevenuCourant() {
		return listRevenuCourant;
	}




	public void setListRevenuCourant(List<Revenucourant> listRevenuCourant) {
		this.listRevenuCourant = listRevenuCourant;
	}
	

}