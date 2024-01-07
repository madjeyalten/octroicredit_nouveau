package com.recouvrement.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the intervenant database table.
 * 
 */
@Entity
@Table(name="intervenant")
public class Intervenant implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int identifiant;

	private String adresseintervenant;

	private String commentaire;

	private String emailintervenant;

	private String libelle;

	private String telephoneintervenant;

	private String typeintervenant;

    public Intervenant() {
    }

	public int getIdentifiant() {
		return this.identifiant;
	}

	public void setIdentifiant(int identifiant) {
		this.identifiant = identifiant;
	}

	public String getAdresseintervenant() {
		return this.adresseintervenant;
	}

	public void setAdresseintervenant(String adresseintervenant) {
		this.adresseintervenant = adresseintervenant;
	}

	public String getCommentaire() {
		return this.commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public String getEmailintervenant() {
		return this.emailintervenant;
	}

	public void setEmailintervenant(String emailintervenant) {
		this.emailintervenant = emailintervenant;
	}

	public String getLibelle() {
		return this.libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getTelephoneintervenant() {
		return this.telephoneintervenant;
	}

	public void setTelephoneintervenant(String telephoneintervenant) {
		this.telephoneintervenant = telephoneintervenant;
	}

	public String getTypeintervenant() {
		return this.typeintervenant;
	}

	public void setTypeintervenant(String typeintervenant) {
		this.typeintervenant = typeintervenant;
	}

}