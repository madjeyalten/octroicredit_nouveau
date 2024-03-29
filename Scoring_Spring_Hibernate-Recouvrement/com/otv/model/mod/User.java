package com.otv.model.mod;

// Generated 12 oct. 2014 00:06:54 by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * User generated by hbm2java
 */
@Entity
@Table(name = "user", catalog = "ref")
public class User implements java.io.Serializable {

	private String login;
	private String mdpasse;
	private String nom;
	private String prenom;
	private String email;

	public User() {
	}

	public User(String login, String mdpasse, String nom, String prenom) {
		this.login = login;
		this.mdpasse = mdpasse;
		this.nom = nom;
		this.prenom = prenom;
	}

	public User(String login, String mdpasse, String nom, String prenom,
			String email) {
		this.login = login;
		this.mdpasse = mdpasse;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
	}

	@Id
	@Column(name = "login", unique = true, nullable = false, length = 50)
	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	@Column(name = "mdpasse", nullable = false, length = 50)
	public String getMdpasse() {
		return this.mdpasse;
	}

	public void setMdpasse(String mdpasse) {
		this.mdpasse = mdpasse;
	}

	@Column(name = "nom", nullable = false, length = 50)
	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	@Column(name = "prenom", nullable = false, length = 50)
	public String getPrenom() {
		return this.prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	@Column(name = "email", length = 50)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
