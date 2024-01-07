package com.otv.model.mod;

// Generated 9 juin 2014 00:43:33 by Hibernate Tools 3.4.0.CR1

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Entreecourante generated by hbm2java
 */
@Entity
@Table(name = "entreecourante", catalog = "ref")
public class Entreecourante implements java.io.Serializable {

	private EntreecouranteId id;

	public Entreecourante() {
	}

	public Entreecourante(EntreecouranteId id) {
		this.id = id;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "libelle", column = @Column(name = "libelle", length = 50)),
			@AttributeOverride(name = "credit", column = @Column(name = "credit", precision = 10, scale = 0)),
			@AttributeOverride(name = "debit", column = @Column(name = "debit", precision = 10, scale = 0)),
			@AttributeOverride(name = "solde", column = @Column(name = "solde", precision = 10, scale = 0)),
			@AttributeOverride(name = "mois", column = @Column(name = "mois", length = 50)) })
	public EntreecouranteId getId() {
		return this.id;
	}

	public void setId(EntreecouranteId id) {
		this.id = id;
	}

}