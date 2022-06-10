/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevjpa.business.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the scerev_t_monitoraggio database table.
 * 
 */
@Entity
@Table(name="scerev_t_monitoraggio")
@NamedQuery(name="ScerevTMonitoraggio.findAll", query="SELECT s FROM ScerevTMonitoraggio s")
public class ScerevTMonitoraggio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SCEREV_T_MONITORAGGIO_PKMONITORAGGIO_GENERATOR", sequenceName="SCEREV_T_MONITORAGGIO_PK_MONITORAGGIO_SEQ", initialValue=1, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SCEREV_T_MONITORAGGIO_PKMONITORAGGIO_GENERATOR")
	@Column(name="pk_monitoraggio")
	private Integer pkMonitoraggio;

	@Column(name="cf_assistito")
	private String cfAssistito;

	@Column(name="data_cancellazione")
	private Timestamp dataCancellazione;

	@Column(name="data_creazione")
	private Timestamp dataCreazione;

	@Column(name="data_modifica")
	private Timestamp dataModifica;
	
	@Column(name="nome_medico")
	private String nomeMedico;
	
	@Column(name="cognome_medico")
	private String cognomeMedico;
	
	@Column(name="id_aura_medico")
	private String idAuraMedico;
	
	@Column(name="cf_medico")
	private String cfMedico;
	
	private Boolean selezionabile;
	
	@Column(name="selezionabile_ricongiungimento_familiare")
	private Boolean selezionabileRicongiungimentoFamigliare;
	
	@Column(name="selezionabile_assistenza_temporanea")
	private Boolean selezionabileAssistenzaTemporanea;
	
	public Boolean getSelezionabile() {
		return selezionabile;
	}

	public void setSelezionabile(Boolean selezionabile) {
		this.selezionabile = selezionabile;
	}

	public Boolean getSelezionabileRicongiungimentoFamigliare() {
		return selezionabileRicongiungimentoFamigliare;
	}

	public void setSelezionabileRicongiungimentoFamigliare(Boolean selezionabileRicongiungimentoFamigliare) {
		this.selezionabileRicongiungimentoFamigliare = selezionabileRicongiungimentoFamigliare;
	}

	public Boolean getSelezionabileAssistenzaTemporanea() {
		return selezionabileAssistenzaTemporanea;
	}

	public void setSelezionabileAssistenzaTemporanea(Boolean selezionabileAssistenzaTemporanea) {
		this.selezionabileAssistenzaTemporanea = selezionabileAssistenzaTemporanea;
	}

	public Boolean getSelezionabileInfanzia() {
		return selezionabileInfanzia;
	}

	public void setSelezionabileInfanzia(Boolean selezionabileInfanzia) {
		this.selezionabileInfanzia = selezionabileInfanzia;
	}

	@Column(name="selezionabile_infanzia")
	private Boolean selezionabileInfanzia;
	
	
	public String getNomeMedico() {
		return nomeMedico;
	}

	public void setNomeMedico(String nomeMedico) {
		this.nomeMedico = nomeMedico;
	}

	public String getCognomeMedico() {
		return cognomeMedico;
	}

	public void setCognomeMedico(String cognomeMedico) {
		this.cognomeMedico = cognomeMedico;
	}

	

	
	public String getCfMedico() {
		return cfMedico;
	}

	public void setCfMedico(String cfMedico) {
		this.cfMedico = cfMedico;
	}

	

	public ScerevTMonitoraggio() {
	}

	public Integer getPkMonitoraggio() {
		return this.pkMonitoraggio;
	}

	public void setPkMonitoraggio(Integer pkMonitoraggio) {
		this.pkMonitoraggio = pkMonitoraggio;
	}

	public String getCfAssistito() {
		return this.cfAssistito;
	}

	public void setCfAssistito(String cfAssistito) {
		this.cfAssistito = cfAssistito;
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

	public Timestamp getDataModifica() {
		return this.dataModifica;
	}

	public void setDataModifica(Timestamp dataModifica) {
		this.dataModifica = dataModifica;
	}

	public String getIdAuraMedico() {
		return this.idAuraMedico;
	}

	public void setIdAuraMedico(String idAuraMedico) {
		this.idAuraMedico = idAuraMedico;
	}

}
