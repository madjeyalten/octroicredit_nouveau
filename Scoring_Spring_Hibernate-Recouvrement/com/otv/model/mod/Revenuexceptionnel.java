package com.otv.model.mod;

// Generated 12 oct. 2014 00:06:54 by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Revenuexceptionnel generated by hbm2java
 */
@Entity
@Table(name = "revenuexceptionnel", catalog = "ref")
public class Revenuexceptionnel implements java.io.Serializable {

	private String libelle;
	private Long credit;
	private Long debit;
	private Long solde;
	private String dateValeur;

	public Revenuexceptionnel() {
	}

	public Revenuexceptionnel(String libelle) {
		this.libelle = libelle;
	}

	public Revenuexceptionnel(String libelle, Long credit, Long debit,
			Long solde, String dateValeur) {
		this.libelle = libelle;
		this.credit = credit;
		this.debit = debit;
		this.solde = solde;
		this.dateValeur = dateValeur;
	}

	@Id
	@Column(name = "libelle", unique = true, nullable = false, length = 50)
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

	@Column(name = "dateValeur")
	public String getDateValeur() {
		return this.dateValeur;
	}

	public void setDateValeur(String dateValeur) {
		this.dateValeur = dateValeur;
	}

}
