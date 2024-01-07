package com.otv.model.mod;

// Generated 7 avr. 2013 19:46:59 by Hibernate Tools 3.4.0.CR1

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Balance generated by hbm2java
 */
@Entity
@Table(name = "balance", catalog = "ref")
public class Balance implements java.io.Serializable {

	private BalanceId id;

	public Balance() {
	}

	public Balance(BalanceId id) {
		this.id = id;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "compte", column = @Column(name = "compte", length = 50)),
			@AttributeOverride(name = "intitule", column = @Column(name = "intitule", length = 250)),
			@AttributeOverride(name = "debitReport", column = @Column(name = "debit_Report", length = 50)),
			@AttributeOverride(name = "creditReport", column = @Column(name = "credit_Report", length = 50)),
			@AttributeOverride(name = "debitMouvement", column = @Column(name = "debit_Mouvement", length = 50)),
			@AttributeOverride(name = "creditMovement", column = @Column(name = "credit_Movement", length = 50)),
			@AttributeOverride(name = "debitSolde", column = @Column(name = "debit_Solde", length = 50)),
			@AttributeOverride(name = "creditSolde", column = @Column(name = "credit_Solde", length = 50)) })
	public BalanceId getId() {
		return this.id;
	}

	public void setId(BalanceId id) {
		this.id = id;
	}

}
