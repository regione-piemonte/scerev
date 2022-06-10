/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevjpa.business.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the scerev_r_azioni_gruppo_utenti database table.
 * 
 */
@Entity
@Table(name="scerev_r_azioni_gruppo_utenti")
@NamedQuery(name="ScerevRAzioniGruppoUtenti.findAll", query="SELECT s FROM ScerevRAzioniGruppoUtenti s")
public class ScerevRAzioniGruppoUtenti implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="pk_azioni_gruppo_utenti")
	private Integer pkAzioniGruppoUtenti;

	//bi-directional many-to-one association to ScerevDAzione
	@ManyToOne
	@JoinColumn(name="fk_azione")
	private ScerevDAzione scerevDAzione;

	//bi-directional many-to-one association to ScerevDGruppoUtenti
	@ManyToOne
	@JoinColumn(name="fk_gruppo_utenti")
	private ScerevDGruppoUtenti scerevDGruppoUtenti;

	public ScerevRAzioniGruppoUtenti() {
	}

	public Integer getPkAzioniGruppoUtenti() {
		return this.pkAzioniGruppoUtenti;
	}

	public void setPkAzioniGruppoUtenti(Integer pkAzioniGruppoUtenti) {
		this.pkAzioniGruppoUtenti = pkAzioniGruppoUtenti;
	}

	public ScerevDAzione getScerevDAzione() {
		return this.scerevDAzione;
	}

	public void setScerevDAzione(ScerevDAzione scerevDAzione) {
		this.scerevDAzione = scerevDAzione;
	}

	public ScerevDGruppoUtenti getScerevDGruppoUtenti() {
		return this.scerevDGruppoUtenti;
	}

	public void setScerevDGruppoUtenti(ScerevDGruppoUtenti scerevDGruppoUtenti) {
		this.scerevDGruppoUtenti = scerevDGruppoUtenti;
	}

}
