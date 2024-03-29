package com.recouvrement.model;

// Generated 30 nov. 2014 01:21:08 by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Historiquepayement generated by hbm2java
 */
@Entity
@Table(name = "historiquepayement", catalog = "ref")
public class Historiquepayement implements java.io.Serializable {

	private Integer idPayement;
	private Date datePayement;
	private int montantPayement;
	private String idClient;
	private String idDossier;

	public Historiquepayement() {
	}

	public Historiquepayement(int montantPayement) {
		this.montantPayement = montantPayement;
	}

	public Historiquepayement(Date datePayement, int montantPayement,
			String idClient, String idDossier) {
		this.datePayement = datePayement;
		this.montantPayement = montantPayement;
		this.idClient = idClient;
		this.idDossier = idDossier;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idPayement", unique = true, nullable = false)
	public Integer getIdPayement() {
		return this.idPayement;
	}

	public void setIdPayement(Integer idPayement) {
		this.idPayement = idPayement;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "datePayement", length = 10)
	public Date getDatePayement() {
		return this.datePayement;
	}

	public void setDatePayement(Date datePayement) {
		this.datePayement = datePayement;
	}

	@Column(name = "montantPayement", nullable = false)
	public int getMontantPayement() {
		return this.montantPayement;
	}

	public void setMontantPayement(int montantPayement) {
		this.montantPayement = montantPayement;
	}

	@Column(name = "idClient", length = 50)
	public String getIdClient() {
		return this.idClient;
	}

	public void setIdClient(String idClient) {
		this.idClient = idClient;
	}

	@Column(name = "idDossier", length = 50)
	public String getIdDossier() {
		return this.idDossier;
	}

	public void setIdDossier(String idDossier) {
		this.idDossier = idDossier;
	}

}
