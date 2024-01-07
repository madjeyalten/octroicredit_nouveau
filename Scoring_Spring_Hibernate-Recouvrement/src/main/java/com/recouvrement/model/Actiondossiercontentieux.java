package com.recouvrement.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the actiondossiercontentieux database table.
 * 
 */
@Entity
@Table(name="actiondossiercontentieux")
public class Actiondossiercontentieux implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int identifiant;

	private String actionby;

	private String commentaire;

    @Temporal( TemporalType.DATE)
	private Date dateAction;

	private String typeaction;

	//bi-directional one-to-one association to Dossiercontentieux
	@OneToOne
	@JoinColumn(name="numDossiercontentieux")
	private Dossiercontentieux dossiercontentieux;

    public Actiondossiercontentieux() {
    }

	public int getIdentifiant() {
		return this.identifiant;
	}

	public void setIdentifiant(int identifiant) {
		this.identifiant = identifiant;
	}

	public String getActionby() {
		return this.actionby;
	}

	public void setActionby(String actionby) {
		this.actionby = actionby;
	}

	public String getCommentaire() {
		return this.commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public Date getDateAction() {
		return this.dateAction;
	}

	public void setDateAction(Date dateAction) {
		this.dateAction = dateAction;
	}

	public String getTypeaction() {
		return this.typeaction;
	}

	public void setTypeaction(String typeaction) {
		this.typeaction = typeaction;
	}

	public Dossiercontentieux getDossiercontentieux() {
		return this.dossiercontentieux;
	}

	public void setDossiercontentieux(Dossiercontentieux dossiercontentieux) {
		this.dossiercontentieux = dossiercontentieux;
	}
	
}