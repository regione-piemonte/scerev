/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevjpa.business.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the scerev_d_asl database table.
 * 
 */
@Entity
@Table(name="scerev_d_asl")
@NamedQueries({
	@NamedQuery(name="ScerevDAsl.findDenominazione", query="SELECT s FROM ScerevDAsl s WHERE s.denominazioneAzienda = :denominazioneAzienda"),
	@NamedQuery(name="ScerevDAsl.find", query="SELECT s FROM ScerevDAsl s WHERE s.codiceAzienda = :codiceAzienda"),
	@NamedQuery(name="ScerevDAsl.findAll", query="SELECT s FROM ScerevDAsl s WHERE s.dataFineValidita = null")
})

public class ScerevDAsl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="pk_azienda")
	private Integer pkAzienda;

	@Column(name="codice_azienda")
	private String codiceAzienda;

	@Column(name="codice_regione")
	private String codiceRegione;

	@Column(name="data_cancellazione")
	private Timestamp dataCancellazione;

	@Column(name="data_creazione")
	private Timestamp dataCreazione;

	@Temporal(TemporalType.DATE)
	@Column(name="data_fine_validita")
	private Date dataFineValidita;

	@Temporal(TemporalType.DATE)
	@Column(name="data_inizio_validita")
	private Date dataInizioValidita;

	@Column(name="data_modifica")
	private Timestamp dataModifica;

	@Column(name="denominazione_azienda")
	private String denominazioneAzienda;

	//bi-directional many-to-one association to ScerevDDistretto
	@OneToMany(mappedBy="scerevDAsl")
	private List<ScerevDDistretto> scerevDDistrettos;

	//bi-directional many-to-one association to ScerevTPratica
	@OneToMany(mappedBy="scerevDAsl1")
	private List<ScerevTPratica> scerevTPraticas1;

	//bi-directional many-to-one association to ScerevTPratica
	@OneToMany(mappedBy="scerevDAsl2")
	private List<ScerevTPratica> scerevTPraticas2;

	//bi-directional many-to-one association to ScerevTPratica
	@OneToMany(mappedBy="scerevDAsl3")
	private List<ScerevTPratica> scerevTPraticas3;

	//bi-directional many-to-one association to ScerevTAslOperatore
	@OneToMany(mappedBy="scerevDAsl")
	private List<ScerevTAslOperatore> scerevTAslOperatores;
	
	public ScerevDAsl() {
	}

	public Integer getPkAzienda() {
		return this.pkAzienda;
	}

	public void setPkAzienda(Integer pkAzienda) {
		this.pkAzienda = pkAzienda;
	}

	public String getCodiceAzienda() {
		return this.codiceAzienda;
	}

	public void setCodiceAzienda(String codiceAzienda) {
		this.codiceAzienda = codiceAzienda;
	}

	public String getCodiceRegione() {
		return this.codiceRegione;
	}

	public void setCodiceRegione(String codiceRegione) {
		this.codiceRegione = codiceRegione;
	}

	public Timestamp getDataCancellazione() {
		return this.dataCancellazione;
	}

	public void setDataCancellazione(Timestamp dataCancellazione) {
		this.dataCancellazione = dataCancellazione;
	}

	public Timestamp getDataCreazione() {
		return this.dataCreazione;
	}

	public void setDataCreazione(Timestamp dataCreazione) {
		this.dataCreazione = dataCreazione;
	}

	public Date getDataFineValidita() {
		return this.dataFineValidita;
	}

	public void setDataFineValidita(Date dataFineValidita) {
		this.dataFineValidita = dataFineValidita;
	}

	public Date getDataInizioValidita() {
		return this.dataInizioValidita;
	}

	public void setDataInizioValidita(Date dataInizioValidita) {
		this.dataInizioValidita = dataInizioValidita;
	}

	public Timestamp getDataModifica() {
		return this.dataModifica;
	}

	public void setDataModifica(Timestamp dataModifica) {
		this.dataModifica = dataModifica;
	}

	public String getDenominazioneAzienda() {
		return this.denominazioneAzienda;
	}

	public void setDenominazioneAzienda(String denominazioneAzienda) {
		this.denominazioneAzienda = denominazioneAzienda;
	}

	public List<ScerevDDistretto> getScerevDDistrettos() {
		return this.scerevDDistrettos;
	}

	public void setScerevDDistrettos(List<ScerevDDistretto> scerevDDistrettos) {
		this.scerevDDistrettos = scerevDDistrettos;
	}

	public ScerevDDistretto addScerevDDistretto(ScerevDDistretto scerevDDistretto) {
		getScerevDDistrettos().add(scerevDDistretto);
		scerevDDistretto.setScerevDAsl(this);

		return scerevDDistretto;
	}

	public ScerevDDistretto removeScerevDDistretto(ScerevDDistretto scerevDDistretto) {
		getScerevDDistrettos().remove(scerevDDistretto);
		scerevDDistretto.setScerevDAsl(null);

		return scerevDDistretto;
	}

	public List<ScerevTPratica> getScerevTPraticas1() {
		return this.scerevTPraticas1;
	}

	public void setScerevTPraticas1(List<ScerevTPratica> scerevTPraticas1) {
		this.scerevTPraticas1 = scerevTPraticas1;
	}

	public ScerevTPratica addScerevTPraticas1(ScerevTPratica scerevTPraticas1) {
		getScerevTPraticas1().add(scerevTPraticas1);
		scerevTPraticas1.setScerevDAsl1(this);

		return scerevTPraticas1;
	}

	public ScerevTPratica removeScerevTPraticas1(ScerevTPratica scerevTPraticas1) {
		getScerevTPraticas1().remove(scerevTPraticas1);
		scerevTPraticas1.setScerevDAsl1(null);

		return scerevTPraticas1;
	}

	public List<ScerevTPratica> getScerevTPraticas2() {
		return this.scerevTPraticas2;
	}

	public void setScerevTPraticas2(List<ScerevTPratica> scerevTPraticas2) {
		this.scerevTPraticas2 = scerevTPraticas2;
	}

	public ScerevTPratica addScerevTPraticas2(ScerevTPratica scerevTPraticas2) {
		getScerevTPraticas2().add(scerevTPraticas2);
		scerevTPraticas2.setScerevDAsl2(this);

		return scerevTPraticas2;
	}

	public ScerevTPratica removeScerevTPraticas2(ScerevTPratica scerevTPraticas2) {
		getScerevTPraticas2().remove(scerevTPraticas2);
		scerevTPraticas2.setScerevDAsl2(null);

		return scerevTPraticas2;
	}

	public List<ScerevTPratica> getScerevTPraticas3() {
		return this.scerevTPraticas3;
	}

	public void setScerevTPraticas3(List<ScerevTPratica> scerevTPraticas3) {
		this.scerevTPraticas3 = scerevTPraticas3;
	}

	public ScerevTPratica addScerevTPraticas3(ScerevTPratica scerevTPraticas3) {
		getScerevTPraticas3().add(scerevTPraticas3);
		scerevTPraticas3.setScerevDAsl3(this);

		return scerevTPraticas3;
	}

	public ScerevTPratica removeScerevTPraticas3(ScerevTPratica scerevTPraticas3) {
		getScerevTPraticas3().remove(scerevTPraticas3);
		scerevTPraticas3.setScerevDAsl3(null);

		return scerevTPraticas3;
	}


	public List<ScerevTAslOperatore> getScerevTAslOperatores() {
		return this.scerevTAslOperatores;
	}

	public void setScerevTAslOperatores(List<ScerevTAslOperatore> scerevTAslOperatores) {
		this.scerevTAslOperatores = scerevTAslOperatores;
	}

	public ScerevTAslOperatore addScerevTAslOperatore(ScerevTAslOperatore scerevTAslOperatore) {
		getScerevTAslOperatores().add(scerevTAslOperatore);
		scerevTAslOperatore.setScerevDAsl(this);

		return scerevTAslOperatore;
	}

	public ScerevTAslOperatore removeScerevTAslOperatore(ScerevTAslOperatore scerevTAslOperatore) {
		getScerevTAslOperatores().remove(scerevTAslOperatore);
		scerevTAslOperatore.setScerevDAsl(null);

		return scerevTAslOperatore;
	}
}
