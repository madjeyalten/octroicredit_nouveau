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
import com.otv.model.mod.Balance;
import com.otv.model.mod.Simulation1;
import com.otv.model.mod.Simulation2;
import com.otv.model.mod.Simulation3;
import com.otv.model.mod.Total;
import com.otv.user.service.BalanceService;
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
@ManagedBean(name = "balanceBean")
@Component("balanceBean")
@SessionScoped
public class BalanceBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final String SUCCESS = "success";
	private static final String ERROR = "error";

	public List<Balance> listBalance;
	public List<Total> listTotal;
	public List<Total> listInitTotal;
	public List<Total> listTotalLike;
	public List<Total> listTotalRatio;
	public List<Simulation1> listSimulation1;
	public List<Simulation2> listSimulation2;
	public List<Simulation3> listSimulation3;
	public String searchtotal;
	public String criteria;
	private String filter = "";
	private String propertyName;
	private BigDecimal ratio;
	private Simulation1 seletedSimulation1;

	@Autowired
	private transient BalanceService balanceService;

	private int id;
	private String name;

	@PostConstruct
	public void init() {
		listBalance = balanceService.findAll();
		listTotal = balanceService.findAllTotal();
		listInitTotal = balanceService.findAllTotal();
		listTotalRatio = filterTotal(listInitTotal);
		listSimulation1 = balanceService.findSimulation1();
		listSimulation2 = balanceService.findSimulation2();
		listSimulation3 = balanceService.findSimulation3();
		computeRatio();
	}

	public List<Balance> getListBalance() {
		// listBalance = balanceService.findAll();
		return listBalance;
	}

	public void setListBalance(List<Balance> listBalance) {
		this.listBalance = listBalance;
	}

	public List<Total> getListTotal() {
		return listTotal;
	}

	public void setListTotal(List<Total> listTotal) {
		this.listTotal = listTotal;
	}

	public String getSearchtotal() {
		return searchtotal;
	}

	public void setSearchtotal(String searchtotal) {
		this.searchtotal = searchtotal;
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

	public String rechercher() {

		FacesContext ctx = FacesContext.getCurrentInstance();
		Map<String, String> request = ctx.getExternalContext()
				.getRequestParameterMap();
		searchtotal = request.get("form3" + NamingContainer.SEPARATOR_CHAR
				+ "enterId");
		if (searchtotal != null && !searchtotal.isEmpty()) {
			listTotal = balanceService.findTotalByCriteria(searchtotal);
			listTotalLike = balanceService.findTotalByCriteriaLike(searchtotal);
		} else {
			listTotal = listInitTotal;
			listTotalLike = new ArrayList<Total>();
		}
		return "search";
	}

	public String filter() {
		FacesContext ctx = FacesContext.getCurrentInstance();
		Map<String, String> request = ctx.getExternalContext()
				.getRequestParameterMap();
		searchtotal = request.get("form3" + NamingContainer.SEPARATOR_CHAR
				+ "enterId");
		if (searchtotal != null && !searchtotal.isEmpty()) {
			listTotal = balanceService.findTotalByCriteria(searchtotal);
			listTotalLike = balanceService.findTotalByCriteriaLike(searchtotal);
		} else {
			listTotal = listInitTotal;
			listTotalLike = new ArrayList<Total>();
		}
		return "search";
	}

	public String saveSimulation() {
		if (!checkSimulation1()) {
			balanceService.saveSimulation1(listTotalRatio);
		} else if (!checkSimulation2()) {
			balanceService.saveSimulation2(listTotalRatio);
		} else if (!checkSimulation3()) {
			balanceService.saveSimulation3(listTotalRatio);
		} else {
			balanceService.saveSimulation3(listTotalRatio);
		}
		FacesMessage msg = new FacesMessage("Action is successful");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return "save";
	}

	 public void onRowSelect(SelectEvent event) {  
	        FacesMessage msg = new FacesMessage("Simulation selected Selected", ((Simulation1) event.getObject()).getId().toString());  
//	        try {
//				FacesContext.getCurrentInstance().getExternalContext().dispatch("simulation1.xhtml");
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
	    } 
	 
	private boolean checkSimulation1() {
		List<Simulation1> listSimulation1 = balanceService.findSimulation1();
		return listSimulation1 != null && !listSimulation1.isEmpty();
	}

	private boolean checkSimulation2() {
		List<Simulation2> listSimulation2 = balanceService.findSimulation2();
		return listSimulation2 != null && !listSimulation2.isEmpty();
	}

	private boolean checkSimulation3() {
		List<Simulation3> listSimulation3 = balanceService.findSimulation3();
		return listSimulation3 != null && !listSimulation3.isEmpty();
	}

	private String surname;

	public String connect() {
		return "sucess";
	}

	public String getCriteria() {
		return criteria;
	}

	public void setCriteria(String criteria) {
		this.criteria = criteria;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public void onRowToggle(ToggleEvent event) {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Visibilité " + event.getVisibility(), "Compte:"
						+ ((Total) event.getData()).getId().getCompte());

		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public List<Total> getListTotalLike() {
		return listTotalLike;
	}

	public void setListTotalLike(List<Total> listTotalLike) {
		this.listTotalLike = listTotalLike;
	}

	public BigDecimal getRatio() {
		return ratio;
	}

	public void setRatio(BigDecimal ratio) {
		this.ratio = ratio;
	}

	public void onEdit(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Car Edited");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		computeRatio();
	}

	public void onCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Car Cancelled");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public List<Total> getListTotalRatio() {
		return listTotalRatio;
	}

	public void setListTotalRatio(List<Total> listTotalRatio) {
		this.listTotalRatio = listTotalRatio;
	}

	public List<Total> getListInitTotal() {
		return listInitTotal;
	}

	public void setListInitTotal(List<Total> listInitTotal) {
		this.listInitTotal = listInitTotal;
	}

	public List<Simulation1> getListSimulation1() {
		return listSimulation1;
	}

	public void setListSimulation1(List<Simulation1> listSimulation1) {
		this.listSimulation1 = listSimulation1;
	}

	public List<Simulation2> getListSimulation2() {
		return listSimulation2;
	}

	public void setListSimulation2(List<Simulation2> listSimulation2) {
		this.listSimulation2 = listSimulation2;
	}

	public List<Simulation3> getListSimulation3() {
		return listSimulation3;
	}

	public void setListSimulation3(List<Simulation3> listSimulation3) {
		this.listSimulation3 = listSimulation3;
	}

	List<Total> filterTotal(List<Total> listTotal) {
		List<Total> listTotalResult = new ArrayList<Total>();
		for (Total total : listTotal) {
			if (total.getId().getCompte().equals("Total 681")
					|| total.getId().getCompte().equals("Total 686")
					|| total.getId().getCompte().equals("Total 687")
					|| total.getId().getCompte().equals("Total 781")
					|| total.getId().getCompte().equals("Total 786")
					|| total.getId().getCompte().equals("Total 787")
					|| total.getId().getCompte().equals("Total 675")
					|| total.getId().getCompte().equals("Total 775")
					|| total.getId().getCompte().equals("Total 13")
					|| total.getId().getCompte().equals("Total 777")) {
				listTotalResult.add(total);
			}
		}
		return listTotalResult;

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

	private BigDecimal computeRatio() {
		BigDecimal resultRatio = new BigDecimal(0);

		BigDecimal total681 = new BigDecimal(0);

		BigDecimal total686 = new BigDecimal(0);

		BigDecimal total687 = new BigDecimal(0);

		BigDecimal total781 = new BigDecimal(0);

		BigDecimal total786 = new BigDecimal(0);

		BigDecimal total787 = new BigDecimal(0);

		BigDecimal total675 = new BigDecimal(0);

		BigDecimal total775 = new BigDecimal(0);

		BigDecimal total777 = new BigDecimal(0);
		BigDecimal total13 = new BigDecimal(0);

		try {
			for (Total total : this.listTotalRatio) {
				if (total.getId().getCompte().equals("Total 681")) {
					total681 = new BigDecimal(total.getId().getCreditMovement()
							.trim());
				}
				if (total.getId().getCompte().equals("Total 686")) {
					total686 = new BigDecimal(total.getId().getCreditMovement()
							.trim());
				}
				if (total.getId().getCompte().equals("Total 687")) {
					total687 = new BigDecimal(total.getId().getCreditMovement()
							.trim());
				}
				if (total.getId().getCompte().equals("Total 781")) {
					total781 = new BigDecimal(total.getId().getDebitMouvement()
							.trim());
				}
				if (total.getId().getCompte().equals("Total 786")) {
					total786 = new BigDecimal(total.getId().getDebitMouvement()
							.trim());
				}
				if (total.getId().getCompte().equals("Total 878")) {
					total787 = new BigDecimal(total.getId().getDebitMouvement()
							.trim());
				}
				if (total.getId().getCompte().equals("Total 675")) {
					total675 = new BigDecimal(total.getId().getCreditMovement()
							.trim());
				}
				if (total.getId().getCompte().equals("Total 775")) {
					total775 = new BigDecimal(total.getId().getDebitMouvement()
							.trim());
				}
				if (total.getId().getCompte().equals("Total 777")) {
					total777 = new BigDecimal(total.getId().getDebitMouvement()
							.trim());
				}
				if (total.getId().getCompte().equals("Total 13")) {
					if(total.getId().getCreditMovement()!=null && total.getId().getCreditMovement().equals("0"))
					{
						total13 = new BigDecimal(total.getId().getDebitMouvement()
								.trim());	
					}
					else
					{
					total13 = new BigDecimal(total.getId().getCreditMovement()
							.trim());
					}
				}
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		resultRatio = total13.add(total681).add(total686).add(total687)
				.subtract(total781).subtract(total786).subtract(total787)
				.subtract(total775).subtract(total777).add(total675);

		this.ratio = resultRatio;
		return resultRatio;
	}

	public void onCellEdit(CellEditor event) {
		FacesMessage msg = new FacesMessage("Car Edited");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onCellEdit(CellEditEvent event) {
		computeRatio();
	}

	public boolean isListSimulation1NotEmpty() {
		return listSimulation1 != null;
	}

	public boolean isListSimulation2NotEmpty() {
		listSimulation2=balanceService.findSimulation2();
		return listSimulation2 != null;
	}

	public boolean isListSimulation3NotEmpty() {
		return listSimulation3 != null;
	}

	public Simulation1 getSeletedSimulation1() {
		return seletedSimulation1;
	}

	public void setSeletedSimulation1(Simulation1 seletedSimulation1) {
		this.seletedSimulation1 = seletedSimulation1;
	}
	
	public String deconnect()
	{
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		     return "deconnexion";
	}
	
	

}