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
 * The persistent class for the scerev_d_distretto database table.
 * 
 */
@Entity
@Table(name="scerev_d_distretto")
@NamedQueries({@NamedQuery(name="ScerevDDistretto.findAll", query="SELECT s FROM ScerevDDistretto s"),
	@NamedQuery(name="ScerevDDistretto.findByPk", query="SELECT s FROM ScerevDDistretto s where s.pkDistretto = :pk_distretto"),
	@NamedQuery(name="ScerevDDistretto.find", query="SELECT s FROM ScerevDDistretto s where s.codiceDistretto = :codDistretto"),
	@NamedQuery(name="ScerevDDistretto.findByDesc", query="SELECT s FROM ScerevDDistretto s where s.denominazioneDistretto = :denominazioneDistretto")
})
public class ScerevDDistretto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="pk_distretto")
	private Integer pkDistretto;

	@Column(name="codice_distretto")
	private String codiceDistretto;

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

	@Column(name="denominazione_distretto")
	private String denominazioneDistretto;

	//bi-directional many-to-one association to ScerevDAmbito
	@OneToMany(mappedBy="scerevDDistretto")
	private List<ScerevDAmbito> scerevDAmbitos;

	//bi-directional many-to-one association to ScerevDAsl
	@ManyToOne
	@JoinColumn(name="fk_azienda")
	private ScerevDAsl scerevDAsl;

	//bi-directional many-to-one association to ScerevTPratica
	@OneToMany(mappedBy="scerevDDistretto")
	private List<ScerevTPratica> scerevTPraticas;

	public ScerevDDistretto() {
	}

	public Integer getPkDistretto() {
		return this.pkDistretto;
	}

	public void setPkDistretto(Integer pkDistretto) {
		this.pkDistretto = pkDistretto;
	}

	public String getCodiceDistretto() {
		return this.codiceDistretto;
	}

	public void setCodiceDistretto(String codiceDistretto) {
		this.codiceDistretto = codiceDistretto;
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

	public String getDenominazioneDistretto() {
		return this.denominazioneDistretto;
	}

	public void setDenominazioneDistretto(String denominazioneDistretto) {
		this.denominazioneDistretto = denominazioneDistretto;
	}

	public List<ScerevDAmbito> getScerevDAmbitos() {
		return this.scerevDAmbitos;
	}

	public void setScerevDAmbitos(List<ScerevDAmbito> scerevDAmbitos) {
		this.scerevDAmbitos = scerevDAmbitos;
	}

	public ScerevDAmbito addScerevDAmbito(ScerevDAmbito scerevDAmbito) {
		getScerevDAmbitos().add(scerevDAmbito);
		scerevDAmbito.setScerevDDistretto(this);

		return scerevDAmbito;
	}

	public ScerevDAmbito removeScerevDAmbito(ScerevDAmbito scerevDAmbito) {
		getScerevDAmbitos().remove(scerevDAmbito);
		scerevDAmbito.setScerevDDistretto(null);

		return scerevDAmbito;
	}

	public ScerevDAsl getScerevDAsl() {
		return this.scerevDAsl;
	}

	public void setScerevDAsl(ScerevDAsl scerevDAsl) {
		this.scerevDAsl = scerevDAsl;
	}

	public List<ScerevTPratica> getScerevTPraticas() {
		return this.scerevTPraticas;
	}

	public void setScerevTPraticas(List<ScerevTPratica> scerevTPraticas) {
		this.scerevTPraticas = scerevTPraticas;
	}

	public ScerevTPratica addScerevTPratica(ScerevTPratica scerevTPratica) {
		getScerevTPraticas().add(scerevTPratica);
		scerevTPratica.setScerevDDistretto(this);

		return scerevTPratica;
	}

	public ScerevTPratica removeScerevTPratica(ScerevTPratica scerevTPratica) {
		getScerevTPraticas().remove(scerevTPratica);
		scerevTPratica.setScerevDDistretto(null);

		return scerevTPratica;
	}

}
