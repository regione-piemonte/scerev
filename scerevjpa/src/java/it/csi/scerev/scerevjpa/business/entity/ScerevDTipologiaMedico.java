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
 * The persistent class for the scerev_d_tipologia_medico database table.
 * 
 */
@Entity
@Table(name="scerev_d_tipologia_medico")
@NamedQueries({
	@NamedQuery(name="ScerevDTipologiaMedico.findAll", query="SELECT s FROM ScerevDTipologiaMedico s"),
	@NamedQuery(name="ScerevDTipologiaMedico.findAllValide", query="SELECT s FROM ScerevDTipologiaMedico s where coalesce(s.dataFineValidita , current_timestamp) >= current_timestamp")
})
public class ScerevDTipologiaMedico implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="pk_tipologia_medico")
	private Integer pkTipologiaMedico;

	@Column(name="codice_tipologia_medico")
	private String codiceTipologiaMedico;

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

	@Column(name="valore_tipologia_medico")
	private String valoreTipologiaMedico;

	public ScerevDTipologiaMedico() {
	}

	public Integer getPkTipologiaMedico() {
		return this.pkTipologiaMedico;
	}

	public void setPkTipologiaMedico(Integer pkTipologiaMedico) {
		this.pkTipologiaMedico = pkTipologiaMedico;
	}

	public String getCodiceTipologiaMedico() {
		return this.codiceTipologiaMedico;
	}

	public void setCodiceTipologiaMedico(String codiceTipologiaMedico) {
		this.codiceTipologiaMedico = codiceTipologiaMedico;
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

	public String getValoreTipologiaMedico() {
		return this.valoreTipologiaMedico;
	}

	public void setValoreTipologiaMedico(String valoreTipologiaMedico) {
		this.valoreTipologiaMedico = valoreTipologiaMedico;
	}

}
