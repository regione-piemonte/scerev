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
 * The persistent class for the scerev_d_nazione database table.
 * 
 */
@Entity
@Table(name="scerev_d_nazione")
@NamedQueries({
	@NamedQuery(name="ScerevDNazione.findAll", query="SELECT s FROM ScerevDNazione s"),
	@NamedQuery(name="ScerevDNazione.findNazionePerNome", query="SELECT s FROM ScerevDNazione s  where s.nomeNazione = :nomeNazione"),
	@NamedQuery(name="ScerevDNazione.findNazionePerCod", query="SELECT s FROM ScerevDNazione s  where s.codiceNazione = :cod")
})
public class ScerevDNazione implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="pk_nazione")
	private Integer pkNazione;

	@Column(name="codice_nazione")
	private String codiceNazione;

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

	@Column(name="nome_nazione")
	private String nomeNazione;

	//bi-directional many-to-one association to ScerevTLocalita
	@OneToMany(mappedBy="scerevDNazione")
	private List<ScerevTLocalita> scerevTLocalitas;

	public ScerevDNazione() {
	}

	public Integer getPkNazione() {
		return this.pkNazione;
	}

	public void setPkNazione(Integer pkNazione) {
		this.pkNazione = pkNazione;
	}

	public String getCodiceNazione() {
		return this.codiceNazione;
	}

	public void setCodiceNazione(String codiceNazione) {
		this.codiceNazione = codiceNazione;
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

	public String getNomeNazione() {
		return this.nomeNazione;
	}

	public void setNomeNazione(String nomeNazione) {
		this.nomeNazione = nomeNazione;
	}

	public List<ScerevTLocalita> getScerevTLocalitas() {
		return this.scerevTLocalitas;
	}

	public void setScerevTLocalitas(List<ScerevTLocalita> scerevTLocalitas) {
		this.scerevTLocalitas = scerevTLocalitas;
	}

	public ScerevTLocalita addScerevTLocalita(ScerevTLocalita scerevTLocalita) {
		getScerevTLocalitas().add(scerevTLocalita);
		scerevTLocalita.setScerevDNazione(this);

		return scerevTLocalita;
	}

	public ScerevTLocalita removeScerevTLocalita(ScerevTLocalita scerevTLocalita) {
		getScerevTLocalitas().remove(scerevTLocalita);
		scerevTLocalita.setScerevDNazione(null);

		return scerevTLocalita;
	}

}
