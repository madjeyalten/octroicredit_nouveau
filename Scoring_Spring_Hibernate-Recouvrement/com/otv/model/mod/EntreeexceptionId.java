package com.otv.model.mod;

// Generated 9 juin 2014 00:43:33 by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * EntreeexceptionId generated by hbm2java
 */
@Embeddable
public class EntreeexceptionId implements java.io.Serializable {

	private String libelle;
	private Long credit;
	private Long debit;
	private Long solde;
	private String mois;

	public EntreeexceptionId() {
	}

	public EntreeexceptionId(String libelle, Long credit, Long debit,
			Long solde, String mois) {
		this.libelle = libelle;
		this.credit = credit;
		this.debit = debit;
		this.solde = solde;
		this.mois = mois;
	}

	@Column(name = "libelle", length = 50)
	public String getLibelle() {
		return this.libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	@Column(name = "credit", precision = 10, scale = 0)
	public Long getCredit() {
		return this.credit;
	}

	public void setCredit(Long credit) {
		this.credit = credit;
	}

	@Column(name = "debit", precision = 10, scale = 0)
	public Long getDebit() {
		return this.debit;
	}

	public void setDebit(Long debit) {
		this.debit = debit;
	}

	@Column(name = "solde", precision = 10, scale = 0)
	public Long getSolde() {
		return this.solde;
	}

	public void setSolde(Long solde) {
		this.solde = solde;
	}

	@Column(name = "mois", length = 50)
	public String getMois() {
		return this.mois;
	}

	public void setMois(String mois) {
		this.mois = mois;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof EntreeexceptionId))
			return false;
		EntreeexceptionId castOther = (EntreeexceptionId) other;

		return ((this.getLibelle() == castOther.getLibelle()) || (this
				.getLibelle() != null && castOther.getLibelle() != null && this
				.getLibelle().equals(castOther.getLibelle())))
				&& ((this.getCredit() == castOther.getCredit()) || (this
						.getCredit() != null && castOther.getCredit() != null && this
						.getCredit().equals(castOther.getCredit())))
				&& ((this.getDebit() == castOther.getDebit()) || (this
						.getDebit() != null && castOther.getDebit() != null && this
						.getDebit().equals(castOther.getDebit())))
				&& ((this.getSolde() == castOther.getSolde()) || (this
						.getSolde() != null && castOther.getSolde() != null && this
						.getSolde().equals(castOther.getSolde())))
				&& ((this.getMois() == castOther.getMois()) || (this.getMois() != null
						&& castOther.getMois() != null && this.getMois()
						.equals(castOther.getMois())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getLibelle() == null ? 0 : this.getLibelle().hashCode());
		result = 37 * result
				+ (getCredit() == null ? 0 : this.getCredit().hashCode());
		result = 37 * result
				+ (getDebit() == null ? 0 : this.getDebit().hashCode());
		result = 37 * result
				+ (getSolde() == null ? 0 : this.getSolde().hashCode());
		result = 37 * result
				+ (getMois() == null ? 0 : this.getMois().hashCode());
		return result;
	}

}
