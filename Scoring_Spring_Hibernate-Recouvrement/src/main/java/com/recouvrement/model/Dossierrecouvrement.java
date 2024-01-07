package com.recouvrement.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the dossierrecouvrement database table.
 * 
 */
@Entity
@Table(name="dossierrecouvrement")
public class Dossierrecouvrement implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String identifiant;

	private String assigne;

	private String commentairePromesse;

    @Temporal( TemporalType.DATE)
	private Date dateCreation;

    @Temporal( TemporalType.DATE)
	private Date datePromesse;

    @Temporal( TemporalType.DATE)
	private Date dateReview;

	private BigDecimal mensualite;

	private BigDecimal montantEncours;

	private int montantPromesse;

	private Long montantRetard;

	private int nbreDossier;

	private int nbreJourRetard;

	private String statusAuto;

	private String statusPromesse;

	private String statusTraitement;

	//bi-directional one-to-one association to Client
	@OneToOne
	@JoinColumn(name="numclient")
	private Client client;

    public Dossierrecouvrement() {
    }

	public String getIdentifiant() {
		return this.identifiant;
	}

	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}

	public String getAssigne() {
		return this.assigne;
	}

	public void setAssigne(String assigne) {
		this.assigne = assigne;
	}

	public String getCommentairePromesse() {
		return this.commentairePromesse;
	}

	public void setCommentairePromesse(String commentairePromesse) {
		this.commentairePromesse = commentairePromesse;
	}

	public Date getDateCreation() {
		return this.dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public Date getDatePromesse() {
		return this.datePromesse;
	}

	public void setDatePromesse(Date datePromesse) {
		this.datePromesse = datePromesse;
	}

	public Date getDateReview() {
		return this.dateReview;
	}

	public void setDateReview(Date dateReview) {
		this.dateReview = dateReview;
	}

	public BigDecimal getMensualite() {
		return this.mensualite;
	}

	public void setMensualite(BigDecimal mensualite) {
		this.mensualite = mensualite;
	}

	public BigDecimal getMontantEncours() {
		return this.montantEncours;
	}

	public void setMontantEncours(BigDecimal montantEncours) {
		this.montantEncours = montantEncours;
	}

	public int getMontantPromesse() {
		return this.montantPromesse;
	}

	public void setMontantPromesse(int montantPromesse) {
		this.montantPromesse = montantPromesse;
	}

	public Long getMontantRetard() {
		return this.montantRetard;
	}

	public void setMontantRetard(Long montantRetard) {
		this.montantRetard = montantRetard;
	}

	public int getNbreDossier() {
		return this.nbreDossier;
	}

	public void setNbreDossier(int nbreDossier) {
		this.nbreDossier = nbreDossier;
	}

	public int getNbreJourRetard() {
		return this.nbreJourRetard;
	}

	public void setNbreJourRetard(int nbreJourRetard) {
		this.nbreJourRetard = nbreJourRetard;
	}

	public String getStatusAuto() {
		return this.statusAuto;
	}

	public void setStatusAuto(String statusAuto) {
		this.statusAuto = statusAuto;
	}

	public String getStatusPromesse() {
		return this.statusPromesse;
	}

	public void setStatusPromesse(String statusPromesse) {
		this.statusPromesse = statusPromesse;
	}

	public String getStatusTraitement() {
		return this.statusTraitement;
	}

	public void setStatusTraitement(String statusTraitement) {
		this.statusTraitement = statusTraitement;
	}

	public Client getClient() {
		return this.client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
	
}