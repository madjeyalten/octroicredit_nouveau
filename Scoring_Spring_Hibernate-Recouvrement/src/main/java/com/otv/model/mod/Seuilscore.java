package com.otv.model.mod;

// Generated 2 sept. 2014 21:45:58 by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Scoreparametrage generated by hbm2java
 */
@Entity
@Table(name = "seuilscore", catalog = "ref")
public class Seuilscore implements java.io.Serializable {

	private String identifiant;
	private Integer seuilcoreAccepte;
	private Integer seuilscoreRefuse;
	

	public Seuilscore() {
	}

	public Seuilscore(String identifiant) {
		this.identifiant = identifiant;
	}

	
	public Seuilscore(String identifiant, Integer seuilcoreAccepte,
			Integer seuilscoreRefuse) {
		super();
		this.identifiant = identifiant;
		this.seuilcoreAccepte = seuilcoreAccepte;
		this.seuilscoreRefuse = seuilscoreRefuse;
	}

	@Id
	@Column(name = "identifiant", unique = true, nullable = false, length = 50)
	public String getIdentifiant() {
		return this.identifiant;
	}

	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}
	
	

	@Column(name = "seuilcoreAccepte")
	public Integer getSeuilcoreAccepte() {
		return seuilcoreAccepte;
	}

	public void setSeuilcoreAccepte(Integer seuilcoreAccepte) {
		this.seuilcoreAccepte = seuilcoreAccepte;
	}

	
	@Column(name = "seuilscoreRefuse")
	public Integer getSeuilscoreRefuse() {
		return seuilscoreRefuse;
	}

	public void setSeuilscoreRefuse(Integer seuilscoreRefuse) {
		this.seuilscoreRefuse = seuilscoreRefuse;
	}
	
	

}
