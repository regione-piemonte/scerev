/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevjpa.business.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;


/**
 * The persistent class for the scerev_d_msg database table.
 * 
 */
@Entity
@Table(name="scerev_d_msg")
@NamedQueries({ 
	@NamedQuery(name = "ScerevDMsg.findAll", query = "SELECT s FROM ScerevDMsg s"),
	@NamedQuery(name = "ScerevDMsg.findMessaggioPerCodice", query = "SELECT s FROM ScerevDMsg s WHERE s.codiceMsg = :codMsg AND s.dataFineValidita IS NULL") 
})
public class ScerevDMsg implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="pk_msg")
	private Integer pkMsg;

	@Column(name="codice_msg")
	private String codiceMsg;

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

	@Column(name="descrizione_msg")
	private String descrizioneMsg;

	@Column(name="tipo_msg")
	private String tipoMsg;

	@Column(name="valore_msg")
	private String valoreMsg;

	public ScerevDMsg() {
	}

	public Integer getPkMsg() {
		return this.pkMsg;
	}

	public void setPkMsg(Integer pkMsg) {
		this.pkMsg = pkMsg;
	}

	public String getCodiceMsg() {
		return this.codiceMsg;
	}

	public void setCodiceMsg(String codiceMsg) {
		this.codiceMsg = codiceMsg;
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

	public String getDescrizioneMsg() {
		return this.descrizioneMsg;
	}

	public void setDescrizioneMsg(String descrizioneMsg) {
		this.descrizioneMsg = descrizioneMsg;
	}

	public String getTipoMsg() {
		return this.tipoMsg;
	}

	public void setTipoMsg(String tipoMsg) {
		this.tipoMsg = tipoMsg;
	}

	public String getValoreMsg() {
		return this.valoreMsg;
	}

	public void setValoreMsg(String valoreMsg) {
		this.valoreMsg = valoreMsg;
	}

}
