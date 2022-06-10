/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevjpa.business.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the scerev_d_azione database table.
 * 
 */
@Entity
@Table(name="scerev_d_azione")
@NamedQuery(name="ScerevDAzione.findAll", query="SELECT s FROM ScerevDAzione s")
public class ScerevDAzione implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="pk_azione")
	private Integer pkAzione;

	@Column(name="cod_azione")
	private String codAzione;

	@Column(name="desc_azione")
	private String descAzione;

	//bi-directional many-to-one association to ScerevRAzioniGruppoUtenti
	@OneToMany(mappedBy="scerevDAzione")
	private List<ScerevRAzioniGruppoUtenti> scerevRAzioniGruppoUtentis;

	public ScerevDAzione() {
	}

	public Integer getPkAzione() {
		return this.pkAzione;
	}

	public void setPkAzione(Integer pkAzione) {
		this.pkAzione = pkAzione;
	}

	public String getCodAzione() {
		return this.codAzione;
	}

	public void setCodAzione(String codAzione) {
		this.codAzione = codAzione;
	}

	public String getDescAzione() {
		return this.descAzione;
	}

	public void setDescAzione(String descAzione) {
		this.descAzione = descAzione;
	}

	public List<ScerevRAzioniGruppoUtenti> getScerevRAzioniGruppoUtentis() {
		return this.scerevRAzioniGruppoUtentis;
	}

	public void setScerevRAzioniGruppoUtentis(List<ScerevRAzioniGruppoUtenti> scerevRAzioniGruppoUtentis) {
		this.scerevRAzioniGruppoUtentis = scerevRAzioniGruppoUtentis;
	}

	public ScerevRAzioniGruppoUtenti addScerevRAzioniGruppoUtenti(ScerevRAzioniGruppoUtenti scerevRAzioniGruppoUtenti) {
		getScerevRAzioniGruppoUtentis().add(scerevRAzioniGruppoUtenti);
		scerevRAzioniGruppoUtenti.setScerevDAzione(this);

		return scerevRAzioniGruppoUtenti;
	}

	public ScerevRAzioniGruppoUtenti removeScerevRAzioniGruppoUtenti(ScerevRAzioniGruppoUtenti scerevRAzioniGruppoUtenti) {
		getScerevRAzioniGruppoUtentis().remove(scerevRAzioniGruppoUtenti);
		scerevRAzioniGruppoUtenti.setScerevDAzione(null);

		return scerevRAzioniGruppoUtenti;
	}

}
