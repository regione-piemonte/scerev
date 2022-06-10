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
 * The persistent class for the scerev_d_tipologia_profilo database table.
 * 
 */
@Entity
@Table(name="scerev_d_tipologia_profilo")
@NamedQuery(name="ScerevDTipologiaProfilo.findAll", query="SELECT s FROM ScerevDTipologiaProfilo s where s.dataCancellazione IS NULL")
public class ScerevDTipologiaProfilo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="pk_tipologia_profilo")
	private Integer pkTipologiaProfilo;

	private String asstemp;

	@Column(name="codice_tipologia_profilo")
	private String codiceTipologiaProfilo;

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

	@Column(name="descr_ass_temp")
	private String descrAssTemp;

	@Column(name="valore_tipologia_profilo")
	private String valoreTipologiaProfilo;

	public ScerevDTipologiaProfilo() {
	}

	public Integer getPkTipologiaProfilo() {
		return this.pkTipologiaProfilo;
	}

	public void setPkTipologiaProfilo(Integer pkTipologiaProfilo) {
		this.pkTipologiaProfilo = pkTipologiaProfilo;
	}

	public String getAsstemp() {
		return this.asstemp;
	}

	public void setAsstemp(String asstemp) {
		this.asstemp = asstemp;
	}

	public String getCodiceTipologiaProfilo() {
		return this.codiceTipologiaProfilo;
	}

	public void setCodiceTipologiaProfilo(String codiceTipologiaProfilo) {
		this.codiceTipologiaProfilo = codiceTipologiaProfilo;
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

	public String getDescrAssTemp() {
		return this.descrAssTemp;
	}

	public void setDescrAssTemp(String descrAssTemp) {
		this.descrAssTemp = descrAssTemp;
	}

	public String getValoreTipologiaProfilo() {
		return this.valoreTipologiaProfilo;
	}

	public void setValoreTipologiaProfilo(String valoreTipologiaProfilo) {
		this.valoreTipologiaProfilo = valoreTipologiaProfilo;
	}

}
