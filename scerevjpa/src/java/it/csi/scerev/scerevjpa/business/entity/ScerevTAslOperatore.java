/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevjpa.business.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the scerev_t_asl_operatore database table.
 * 
 */
@Entity
@Table(name="scerev_t_asl_operatore")
@NamedQueries({
@NamedQuery(name="ScerevTAslOperatore.findAll", query="SELECT s FROM ScerevTAslOperatore s"),
@NamedQuery(name="ScerevTAslOperatore.findbyCfOperatore", query="SELECT s FROM ScerevTAslOperatore s where s.cfOperatore = :cfOp AND s.dataFineValidita = null")
})
public class ScerevTAslOperatore implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="pk_asl_operatore")
	private Integer pkAslOperatore;

	@Column(name="cf_operatore")
	private String cfOperatore;

	@Column(name="cognome_operatore")
	private String cognomeOperatore;

	@Temporal(TemporalType.DATE)
	@Column(name="data_fine_validita")
	private Date dataFineValidita;

	@Temporal(TemporalType.DATE)
	@Column(name="data_inizio_validita")
	private Date dataInizioValidita;

	@Column(name="nome_operatore")
	private String nomeOperatore;

	//bi-directional many-to-one association to ScerevDAsl
	@ManyToOne
	@JoinColumn(name="fk_asl_operatore")
	private ScerevDAsl scerevDAsl;

	public ScerevTAslOperatore() {
	}

	public String getCfOperatore() {
		return this.cfOperatore;
	}

	public void setCfOperatore(String cfOperatore) {
		this.cfOperatore = cfOperatore;
	}

	public String getCognomeOperatore() {
		return this.cognomeOperatore;
	}

	public void setCognomeOperatore(String cognomeOperatore) {
		this.cognomeOperatore = cognomeOperatore;
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

	public String getNomeOperatore() {
		return this.nomeOperatore;
	}

	public void setNomeOperatore(String nomeOperatore) {
		this.nomeOperatore = nomeOperatore;
	}

	public Integer getPkAslOperatore() {
		return this.pkAslOperatore;
	}

	public void setPkAslOperatore(Integer pkAslOperatore) {
		this.pkAslOperatore = pkAslOperatore;
	}

	public ScerevDAsl getScerevDAsl() {
		return this.scerevDAsl;
	}

	public void setScerevDAsl(ScerevDAsl scerevDAsl) {
		this.scerevDAsl = scerevDAsl;
	}

}
