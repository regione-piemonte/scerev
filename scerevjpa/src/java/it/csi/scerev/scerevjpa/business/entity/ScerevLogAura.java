/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevjpa.business.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the scerev_log_aura database table.
 * 
 */
@Entity
@Table(name="scerev_log_aura")
@NamedQuery(name="ScerevLogAura.findAll", query="SELECT s FROM ScerevLogAura s")
public class ScerevLogAura implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SCEREV_LOG_AURA_PKLOGAURA_GENERATOR", sequenceName = "scerev_log_aura_pk_log_aura_seq" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SCEREV_LOG_AURA_PKLOGAURA_GENERATOR")
	@Column(name="pk_log_aura")
	private Integer pkLogAura;

	private String azione;

	@Column(name="codice_esito")
	private String codiceEsito;

	@Temporal(TemporalType.DATE)
	@Column(name="data_ins")
	private Date dataIns;

	@Temporal(TemporalType.DATE)
	@Column(name="data_upd")
	private Date dataUpd;

	@Column(name="id_user_assistito")
	private String idUserAssistito;

	@Column(name="id_user_chiamante")
	private String idUserChiamante;


	@Column(name="xml_input")
	private String xmlInput;

	@Column(name="xml_output")
	private String xmlOutput;

	public ScerevLogAura() {
	}

	public String getAzione() {
		return this.azione;
	}

	public void setAzione(String azione) {
		this.azione = azione;
	}

	public String getCodiceEsito() {
		return this.codiceEsito;
	}

	public void setCodiceEsito(String codiceEsito) {
		this.codiceEsito = codiceEsito;
	}

	public Date getDataIns() {
		return this.dataIns;
	}

	public void setDataIns(Date dataIns) {
		this.dataIns = dataIns;
	}

	public Date getDataUpd() {
		return this.dataUpd;
	}

	public void setDataUpd(Date dataUpd) {
		this.dataUpd = dataUpd;
	}

	public String getIdUserAssistito() {
		return this.idUserAssistito;
	}

	public void setIdUserAssistito(String idUserAssistito) {
		this.idUserAssistito = idUserAssistito;
	}

	public String getIdUserChiamante() {
		return this.idUserChiamante;
	}

	public void setIdUserChiamante(String idUserChiamante) {
		this.idUserChiamante = idUserChiamante;
	}

	public Integer getPkLogAura() {
		return this.pkLogAura;
	}

	public void setPkLogAura(Integer pkLogAura) {
		this.pkLogAura = pkLogAura;
	}

	public String getXmlInput() {
		return this.xmlInput;
	}

	public void setXmlInput(String xmlInput) {
		this.xmlInput = xmlInput;
	}

	public String getXmlOutput() {
		return this.xmlOutput;
	}

	public void setXmlOutput(String xmlOutput) {
		this.xmlOutput = xmlOutput;
	}

}
