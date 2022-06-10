/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevjpa.business.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the scerev_d_cittadinanza database table.
 * 
 */
@Entity
@Table(name="scerev_d_cittadinanza")
@NamedQueries({
	@NamedQuery(name="ScerevDCittadinanza.findAll", query="SELECT s FROM ScerevDCittadinanza s"),
	@NamedQuery(name="ScerevDCittadinanza.findAllAttiveOggi", query="SELECT s FROM ScerevDCittadinanza s where coalesce(s.dataFineValidita , current_timestamp) >= current_timestamp"),
	@NamedQuery(name="ScerevDCittadinanza.findCittadinanzaByCodice", query="SELECT s FROM ScerevDCittadinanza s where coalesce(s.dataFineValidita , current_timestamp) >= current_timestamp AND s.codiceCittadinanza = :codCittadinanza")
})
public class ScerevDCittadinanza implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="pk_cittadinanza")
	private Integer pkCittadinanza;

	/*
	 * Codice per identificare una cittadinanza. Utile per popolare il menu a tendina 'cittadinanza'
	 */
	@Column(name="codice_cittadinanza")
	private String codiceCittadinanza;

	private Boolean comunitario;

	/*
	 * Data valorizzata indica la cancellazione logica del record
	 */
	@Column(name="data_cancellazione")
	private Timestamp dataCancellazione;

	/*
	 * Data creazione/inserimento del record
	 */
	@Column(name="data_creazione")
	private Timestamp dataCreazione;

	/*
	 * Data fine validita del record
	 */
	@Temporal(TemporalType.DATE)
	@Column(name="data_fine_validita")
	private Date dataFineValidita;

	/*
	 * Data inizio validita del record
	 */
	@Temporal(TemporalType.DATE)
	@Column(name="data_inizio_validita")
	private Date dataInizioValidita;

	/*
	 * Data pari al primo inserimento o dell'avvenuta modifica del record
	 */
	@Column(name="data_modifica")
	private Timestamp dataModifica;

	/*
	 * Valore della cittadinanza, ovvero valore che verrà visualizzato nel menu a tendina
	 */
	@Column(name="valore_cittadinanza")
	private String valoreCittadinanza;

	//bi-directional many-to-one association to ScerevTPratica
	@OneToMany(mappedBy="scerevDCittadinanza")
	private List<ScerevTPratica> scerevTPraticas;

	public ScerevDCittadinanza() {
	}

	public Integer getPkCittadinanza() {
		return this.pkCittadinanza;
	}

	public void setPkCittadinanza(Integer pkCittadinanza) {
		this.pkCittadinanza = pkCittadinanza;
	}

	public String getCodiceCittadinanza() {
		return this.codiceCittadinanza;
	}

	public void setCodiceCittadinanza(String codiceCittadinanza) {
		this.codiceCittadinanza = codiceCittadinanza;
	}

	public Boolean getComunitario() {
		return this.comunitario;
	}

	public void setComunitario(Boolean comunitario) {
		this.comunitario = comunitario;
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

	public String getValoreCittadinanza() {
		return this.valoreCittadinanza;
	}

	public void setValoreCittadinanza(String valoreCittadinanza) {
		this.valoreCittadinanza = valoreCittadinanza;
	}

	public List<ScerevTPratica> getScerevTPraticas() {
		return this.scerevTPraticas;
	}

	public void setScerevTPraticas(List<ScerevTPratica> scerevTPraticas) {
		this.scerevTPraticas = scerevTPraticas;
	}

	public ScerevTPratica addScerevTPratica(ScerevTPratica scerevTPratica) {
		getScerevTPraticas().add(scerevTPratica);
		scerevTPratica.setScerevDCittadinanza(this);

		return scerevTPratica;
	}

	public ScerevTPratica removeScerevTPratica(ScerevTPratica scerevTPratica) {
		getScerevTPraticas().remove(scerevTPratica);
		scerevTPratica.setScerevDCittadinanza(null);

		return scerevTPratica;
	}

}
