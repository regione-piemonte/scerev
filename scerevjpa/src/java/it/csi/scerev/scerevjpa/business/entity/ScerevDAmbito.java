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
 * The persistent class for the scerev_d_ambito database table.
 * 
 */
@Entity
@Table(name="scerev_d_ambito")
@NamedQueries({
	@NamedQuery(name="ScerevDAmbito.findAll", query="SELECT s FROM ScerevDAmbito s"),
	@NamedQuery(name="ScerevDAmbito.find", query="SELECT s FROM ScerevDAmbito s where s.codiceAmbito = :codAmbito"),
	@NamedQuery(name="ScerevDAmbito.findById", query="SELECT s FROM ScerevDAmbito s where s.pkAmbito = :id"),
	@NamedQuery(name="ScerevDAmbito.findByDesc", query="SELECT s FROM ScerevDAmbito s where s.denominazioneAmbito = :desc")
})
public class ScerevDAmbito implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="pk_ambito")
	private Integer pkAmbito;

	@Column(name="codice_ambito")
	private String codiceAmbito;

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

	@Column(name="denominazione_ambito")
	private String denominazioneAmbito;

	//bi-directional many-to-one association to ScerevDDistretto
	@ManyToOne
	@JoinColumn(name="fk_distretto")
	private ScerevDDistretto scerevDDistretto;

	//bi-directional many-to-one association to ScerevTPratica
	@OneToMany(mappedBy="scerevDAmbito")
	private List<ScerevTPratica> scerevTPraticas;

	public ScerevDAmbito() {
	}

	public Integer getPkAmbito() {
		return this.pkAmbito;
	}

	public void setPkAmbito(Integer pkAmbito) {
		this.pkAmbito = pkAmbito;
	}

	public String getCodiceAmbito() {
		return this.codiceAmbito;
	}

	public void setCodiceAmbito(String codiceAmbito) {
		this.codiceAmbito = codiceAmbito;
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

	public String getDenominazioneAmbito() {
		return this.denominazioneAmbito;
	}

	public void setDenominazioneAmbito(String denominazioneAmbito) {
		this.denominazioneAmbito = denominazioneAmbito;
	}

	public ScerevDDistretto getScerevDDistretto() {
		return this.scerevDDistretto;
	}

	public void setScerevDDistretto(ScerevDDistretto scerevDDistretto) {
		this.scerevDDistretto = scerevDDistretto;
	}

	public List<ScerevTPratica> getScerevTPraticas() {
		return this.scerevTPraticas;
	}

	public void setScerevTPraticas(List<ScerevTPratica> scerevTPraticas) {
		this.scerevTPraticas = scerevTPraticas;
	}

	public ScerevTPratica addScerevTPratica(ScerevTPratica scerevTPratica) {
		getScerevTPraticas().add(scerevTPratica);
		scerevTPratica.setScerevDAmbito(this);

		return scerevTPratica;
	}

	public ScerevTPratica removeScerevTPratica(ScerevTPratica scerevTPratica) {
		getScerevTPraticas().remove(scerevTPratica);
		scerevTPratica.setScerevDAmbito(null);

		return scerevTPratica;
	}

}
