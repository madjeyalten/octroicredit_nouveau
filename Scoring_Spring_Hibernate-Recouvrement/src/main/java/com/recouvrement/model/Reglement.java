package com.recouvrement.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the reglement database table.
 * 
 */
@Entity
@Table(name="reglement")
public class Reglement implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int identifiant;

	private String commentaire;

	private String devisereglement;

	private String modereglement;

	private int montantreglement;

	private String typereglement;

	//bi-directional one-to-one association to Dossiercontentieux
	@OneToOne
	@JoinColumn(name="iddossiercontentieux")
	private Dossiercontentieux dossiercontentieux;

    public Reglement() {
    }

	public int getIdentifiant() {
		return this.identifiant;
	}

	public void setIdentifiant(int identifiant) {
		this.identifiant = identifiant;
	}

	public String getCommentaire() {
		return this.commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public String getDevisereglement() {
		return this.devisereglement;
	}

	public void setDevisereglement(String devisereglement) {
		this.devisereglement = devisereglement;
	}

	public String getModereglement() {
		return this.modereglement;
	}

	public void setModereglement(String modereglement) {
		this.modereglement = modereglement;
	}

	public int getMontantreglement() {
		return this.montantreglement;
	}

	public void setMontantreglement(int montantreglement) {
		this.montantreglement = montantreglement;
	}

	public String getTypereglement() {
		return this.typereglement;
	}

	public void setTypereglement(String typereglement) {
		this.typereglement = typereglement;
	}

	public Dossiercontentieux getDossiercontentieux() {
		return this.dossiercontentieux;
	}

	public void setDossiercontentieux(Dossiercontentieux dossiercontentieux) {
		this.dossiercontentieux = dossiercontentieux;
	}
	
}