package com.otv.model.mod;

// Generated 7 avr. 2013 19:46:59 by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * BalanceId generated by hbm2java
 */
@Embeddable
public class BalanceId implements java.io.Serializable {

	private String compte;
	private String intitule;
	private String debitReport;
	private String creditReport;
	private String debitMouvement;
	private String creditMovement;
	private String debitSolde;
	private String creditSolde;

	public BalanceId() {
	}

	public BalanceId(String compte, String intitule, String debitReport,
			String creditReport, String debitMouvement, String creditMovement,
			String debitSolde, String creditSolde) {
		this.compte = compte;
		this.intitule = intitule;
		this.debitReport = debitReport;
		this.creditReport = creditReport;
		this.debitMouvement = debitMouvement;
		this.creditMovement = creditMovement;
		this.debitSolde = debitSolde;
		this.creditSolde = creditSolde;
	}

	@Column(name = "compte", length = 50)
	public String getCompte() {
		return this.compte;
	}

	public void setCompte(String compte) {
		this.compte = compte;
	}

	@Column(name = "intitule", length = 250)
	public String getIntitule() {
		return this.intitule;
	}

	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}

	@Column(name = "debit_Report", length = 50)
	public String getDebitReport() {
		return this.debitReport;
	}

	public void setDebitReport(String debitReport) {
		this.debitReport = debitReport;
	}

	@Column(name = "credit_Report", length = 50)
	public String getCreditReport() {
		return this.creditReport;
	}

	public void setCreditReport(String creditReport) {
		this.creditReport = creditReport;
	}

	@Column(name = "debit_Mouvement", length = 50)
	public String getDebitMouvement() {
		return this.debitMouvement;
	}

	public void setDebitMouvement(String debitMouvement) {
		this.debitMouvement = debitMouvement;
	}

	@Column(name = "credit_Movement", length = 50)
	public String getCreditMovement() {
		return this.creditMovement;
	}

	public void setCreditMovement(String creditMovement) {
		this.creditMovement = creditMovement;
	}

	@Column(name = "debit_Solde", length = 50)
	public String getDebitSolde() {
		return this.debitSolde;
	}

	public void setDebitSolde(String debitSolde) {
		this.debitSolde = debitSolde;
	}

	@Column(name = "credit_Solde", length = 50)
	public String getCreditSolde() {
		return this.creditSolde;
	}

	public void setCreditSolde(String creditSolde) {
		this.creditSolde = creditSolde;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof BalanceId))
			return false;
		BalanceId castOther = (BalanceId) other;

		return ((this.getCompte() == castOther.getCompte()) || (this
				.getCompte() != null && castOther.getCompte() != null && this
				.getCompte().equals(castOther.getCompte())))
				&& ((this.getIntitule() == castOther.getIntitule()) || (this
						.getIntitule() != null
						&& castOther.getIntitule() != null && this
						.getIntitule().equals(castOther.getIntitule())))
				&& ((this.getDebitReport() == castOther.getDebitReport()) || (this
						.getDebitReport() != null
						&& castOther.getDebitReport() != null && this
						.getDebitReport().equals(castOther.getDebitReport())))
				&& ((this.getCreditReport() == castOther.getCreditReport()) || (this
						.getCreditReport() != null
						&& castOther.getCreditReport() != null && this
						.getCreditReport().equals(castOther.getCreditReport())))
				&& ((this.getDebitMouvement() == castOther.getDebitMouvement()) || (this
						.getDebitMouvement() != null
						&& castOther.getDebitMouvement() != null && this
						.getDebitMouvement().equals(
								castOther.getDebitMouvement())))
				&& ((this.getCreditMovement() == castOther.getCreditMovement()) || (this
						.getCreditMovement() != null
						&& castOther.getCreditMovement() != null && this
						.getCreditMovement().equals(
								castOther.getCreditMovement())))
				&& ((this.getDebitSolde() == castOther.getDebitSolde()) || (this
						.getDebitSolde() != null
						&& castOther.getDebitSolde() != null && this
						.getDebitSolde().equals(castOther.getDebitSolde())))
				&& ((this.getCreditSolde() == castOther.getCreditSolde()) || (this
						.getCreditSolde() != null
						&& castOther.getCreditSolde() != null && this
						.getCreditSolde().equals(castOther.getCreditSolde())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getCompte() == null ? 0 : this.getCompte().hashCode());
		result = 37 * result
				+ (getIntitule() == null ? 0 : this.getIntitule().hashCode());
		result = 37
				* result
				+ (getDebitReport() == null ? 0 : this.getDebitReport()
						.hashCode());
		result = 37
				* result
				+ (getCreditReport() == null ? 0 : this.getCreditReport()
						.hashCode());
		result = 37
				* result
				+ (getDebitMouvement() == null ? 0 : this.getDebitMouvement()
						.hashCode());
		result = 37
				* result
				+ (getCreditMovement() == null ? 0 : this.getCreditMovement()
						.hashCode());
		result = 37
				* result
				+ (getDebitSolde() == null ? 0 : this.getDebitSolde()
						.hashCode());
		result = 37
				* result
				+ (getCreditSolde() == null ? 0 : this.getCreditSolde()
						.hashCode());
		return result;
	}

}
