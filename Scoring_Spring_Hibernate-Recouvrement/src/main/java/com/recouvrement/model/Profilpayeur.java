package com.recouvrement.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the profilpayeur database table.
 * 
 */
@Entity
@Table(name="profilpayeur")
public class Profilpayeur implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int identifiant;

	private int bonpayeur;

	private int mauvaispayeur;

	private int tresbonpayeur;

	private int tresmauvaispayeur;

    public Profilpayeur() {
    }

	public int getIdentifiant() {
		return this.identifiant;
	}

	public void setIdentifiant(int identifiant) {
		this.identifiant = identifiant;
	}

	public int getBonpayeur() {
		return this.bonpayeur;
	}

	public void setBonpayeur(int bonpayeur) {
		this.bonpayeur = bonpayeur;
	}

	public int getMauvaispayeur() {
		return this.mauvaispayeur;
	}

	public void setMauvaispayeur(int mauvaispayeur) {
		this.mauvaispayeur = mauvaispayeur;
	}

	public int getTresbonpayeur() {
		return this.tresbonpayeur;
	}

	public void setTresbonpayeur(int tresbonpayeur) {
		this.tresbonpayeur = tresbonpayeur;
	}

	public int getTresmauvaispayeur() {
		return this.tresmauvaispayeur;
	}

	public void setTresmauvaispayeur(int tresmauvaispayeur) {
		this.tresmauvaispayeur = tresmauvaispayeur;
	}

}