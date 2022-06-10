/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevjpa.business.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the scerev_d_gruppo_utenti database table.
 * 
 */
@Entity
@Table(name="scerev_d_gruppo_utenti")
@NamedQuery(name="ScerevDGruppoUtenti.findAll", query="SELECT s FROM ScerevDGruppoUtenti s")
public class ScerevDGruppoUtenti implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="pk_gruppo_utenti")
	private Integer pkGruppoUtenti;

	@Column(name="cod_gruppo_utenti")
	private String codGruppoUtenti;

	@Column(name="desc_gruppo_utenti")
	private String descGruppoUtenti;

	//bi-directional many-to-one association to ScerevRAzioniGruppoUtenti
	@OneToMany(mappedBy="scerevDGruppoUtenti")
	private List<ScerevRAzioniGruppoUtenti> scerevRAzioniGruppoUtentis;

	public ScerevDGruppoUtenti() {
	}

	public Integer getPkGruppoUtenti() {
		return this.pkGruppoUtenti;
	}

	public void setPkGruppoUtenti(Integer pkGruppoUtenti) {
		this.pkGruppoUtenti = pkGruppoUtenti;
	}

	public String getCodGruppoUtenti() {
		return this.codGruppoUtenti;
	}

	public void setCodGruppoUtenti(String codGruppoUtenti) {
		this.codGruppoUtenti = codGruppoUtenti;
	}

	public String getDescGruppoUtenti() {
		return this.descGruppoUtenti;
	}

	public void setDescGruppoUtenti(String descGruppoUtenti) {
		this.descGruppoUtenti = descGruppoUtenti;
	}

	public List<ScerevRAzioniGruppoUtenti> getScerevRAzioniGruppoUtentis() {
		return this.scerevRAzioniGruppoUtentis;
	}

	public void setScerevRAzioniGruppoUtentis(List<ScerevRAzioniGruppoUtenti> scerevRAzioniGruppoUtentis) {
		this.scerevRAzioniGruppoUtentis = scerevRAzioniGruppoUtentis;
	}

	public ScerevRAzioniGruppoUtenti addScerevRAzioniGruppoUtenti(ScerevRAzioniGruppoUtenti scerevRAzioniGruppoUtenti) {
		getScerevRAzioniGruppoUtentis().add(scerevRAzioniGruppoUtenti);
		scerevRAzioniGruppoUtenti.setScerevDGruppoUtenti(this);

		return scerevRAzioniGruppoUtenti;
	}

	public ScerevRAzioniGruppoUtenti removeScerevRAzioniGruppoUtenti(ScerevRAzioniGruppoUtenti scerevRAzioniGruppoUtenti) {
		getScerevRAzioniGruppoUtentis().remove(scerevRAzioniGruppoUtenti);
		scerevRAzioniGruppoUtenti.setScerevDGruppoUtenti(null);

		return scerevRAzioniGruppoUtenti;
	}

}
