package com.recouvrement.model;

// Generated 18 oct. 2014 01:15:12 by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Sms generated by hbm2java
 */
@Entity
@Table(name = "sms", catalog = "ref")
public class Sms implements java.io.Serializable {

	private Integer identifiant;
	private String corpsSms;
	private String type;

	public Sms() {
	}

	public Sms(String corpsSms, String type) {
		this.corpsSms = corpsSms;
		this.type = type;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "identifiant", unique = true, nullable = false)
	public Integer getIdentifiant() {
		return this.identifiant;
	}

	public void setIdentifiant(Integer identifiant) {
		this.identifiant = identifiant;
	}

	@Column(name = "corpsSMS", length = 50)
	public String getCorpsSms() {
		return this.corpsSms;
	}

	public void setCorpsSms(String corpsSms) {
		this.corpsSms = corpsSms;
	}

	@Column(name = "type", length = 50)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

}