package com.recouvrement.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the dossiercontentieux database table.
 * 
 */
@Entity
@Table(name="dossiercontentieux")
public class Dossiercontentieux implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int identifiant;

	private String assigne;

	private String commentaire;

    @Temporal( TemporalType.DATE)
	private Date dateMiseencontentieux;

	private String status;

	//bi-directional one-to-one association to Dossierrecouvrement
	@OneToOne
	@JoinColumn(name="numDossier")
	private Dossierrecouvrement dossierrecouvrement;

    public Dossiercontentieux() {
    }

	public int getIdentifiant() {
		return this.identifiant;
	}

	public void setIdentifiant(int identifiant) {
		this.identifiant = identifiant;
	}

	public String getAssigne() {
		return this.assigne;
	}

	public void setAssigne(String assigne) {
		this.assigne = assigne;
	}

	public String getCommentaire() {
		return this.commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public Date getDateMiseencontentieux() {
		return this.dateMiseencontentieux;
	}

	public void setDateMiseencontentieux(Date dateMiseencontentieux) {
		this.dateMiseencontentieux = dateMiseencontentieux;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Dossierrecouvrement getDossierrecouvrement() {
		return this.dossierrecouvrement;
	}

	public void setDossierrecouvrement(Dossierrecouvrement dossierrecouvrement) {
		this.dossierrecouvrement = dossierrecouvrement;
	}
	
}