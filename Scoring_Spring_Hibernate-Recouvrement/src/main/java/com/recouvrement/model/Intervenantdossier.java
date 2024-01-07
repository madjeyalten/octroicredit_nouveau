package com.recouvrement.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the intervenantdossier database table.
 * 
 */
@Entity
@Table(name="intervenantdossier")
public class Intervenantdossier implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int identifiant;

	@Column(name="id_dossiercontentieux")
	private int idDossiercontentieux;
	
	@Column(name="id_intervenant")
	private int idIntervenant;

    public Intervenantdossier() {
    }

	public int getIdentifiant() {
		return this.identifiant;
	}

	public void setIdentifiant(int identifiant) {
		this.identifiant = identifiant;
	}

	public int getIdDossiercontentieux() {
		return this.idDossiercontentieux;
	}

	public void setIdDossiercontentieux(int idDossiercontentieux) {
		this.idDossiercontentieux = idDossiercontentieux;
	}

	public int getIdIntervenant() {
		return idIntervenant;
	}

	public void setIdIntervenant(int idIntervenant) {
		this.idIntervenant = idIntervenant;
	}
	
}