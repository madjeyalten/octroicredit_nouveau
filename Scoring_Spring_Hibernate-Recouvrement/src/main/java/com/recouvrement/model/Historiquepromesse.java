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
 * Historiquepromesse generated by hbm2java
 */
@Entity
@Table(name = "historiquepromesse", catalog = "ref")
public class Historiquepromesse implements java.io.Serializable {

	private Integer idpromesse;
	private Date dateEnregistrementPromesse;
	private String idClient;
	private String idDossier;

	public Historiquepromesse() {
	}

	public Historiquepromesse(Date dateEnregistrementPromesse, String idClient,
			String idDossier) {
		this.dateEnregistrementPromesse = dateEnregistrementPromesse;
		this.idClient = idClient;
		this.idDossier = idDossier;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idpromesse", unique = true, nullable = false)
	public Integer getIdpromesse() {
		return this.idpromesse;
	}

	public void setIdpromesse(Integer idpromesse) {
		this.idpromesse = idpromesse;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "dateEnregistrementPromesse", length = 10)
	public Date getDateEnregistrementPromesse() {
		return this.dateEnregistrementPromesse;
	}

	public void setDateEnregistrementPromesse(Date dateEnregistrementPromesse) {
		this.dateEnregistrementPromesse = dateEnregistrementPromesse;
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