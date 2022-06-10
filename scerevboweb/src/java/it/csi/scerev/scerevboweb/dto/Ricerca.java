/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevboweb.dto;

import org.codehaus.jackson.annotate.JsonProperty;

/** JSON PROPERTY SERVE PER MAPPING CON DB. VEDI CONVERTER.convertObjToMap
 */

public class Ricerca {

	@JsonProperty("numeroPratica")
	private Integer numRichiesta;
	@JsonProperty("scerevDPraticaStato.codicePraticaStato")
	private String stato;
	@JsonProperty("scerevDPraticaTipo.codicePraticaTipo")
	private String tipologia;
	@JsonProperty("dataCreazione>")
	private String dataInsDa;
	@JsonProperty("dataCreazione<")
	private String dataInsA;
	@JsonProperty("scerevDDistretto.denominazioneDistretto")
	private String distrettoDomicilio;
	@JsonProperty("scerevDAmbito.denominazioneAmbito")
	private String ambitoDomicilio;
	@JsonProperty("tipologiaMedico")
	private String tipologiaMedico;
	@JsonProperty("cognomeAssistito")
	private String cognomeAssistito;
	@JsonProperty("nomeAssistito")
	private String nomeAssistito;
	@JsonProperty("cfAssistito")
	private String codiceFiscale;
	@JsonProperty("dataNascitaAssistito")
	private String dataNascita;
	@JsonProperty("cognomeOperatore")
	private String cognomeOperatore;
	@JsonProperty("nomeOperatore")
	private String nomeOperatore;
	@JsonProperty("tipologiaMedico")
	private String medPed;
	@JsonProperty("scerevDDistretto.codiceDistretto")
	private String distretto;
//	@JsonProperty("scerevDAmbito.codiceAmbito")
//	private String ambito;
	@JsonProperty("dataCreazione")
	private String dataCreazione;
	@JsonProperty("cfOperatore")
	private Boolean inCarico;
	@JsonProperty("scerevDAsl2.codiceAzienda")
	private String asl;
	
	
	public Integer getNumRichiesta() {
		return numRichiesta;
	}

	public String getStato() {
		return stato;
	}

	public String getTipologia() {
		return tipologia;
	}

	public String getDataInsDa() {
		return dataInsDa;
	}

	public String getDataInsA() {
		return dataInsA;
	}

	public String getDistretto() {
		return distretto;
	}

////	public String getAmbito() {
//		return ambito;
//	}

	public String getCognomeAssistito() {
		return cognomeAssistito;
	}

	public String getNomeAssistito() {
		return nomeAssistito;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public String getDataNascita() {
		return dataNascita;
	}

	public String getCognomeOperatore() {
		return cognomeOperatore;
	}

	public String getNomeOperatore() {
		return nomeOperatore;
	}

	public Boolean isInCarico() {
		return inCarico;
	}

	public void setNumRichiesta(Integer numRichiesta) {
		this.numRichiesta = numRichiesta;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public void setTipologia(String tipologia) {
		this.tipologia = tipologia;
	}

	public void setDataInsDa(String dataInsDa) {
		this.dataInsDa = dataInsDa;
	}

	public void setDataInsA(String dataInsA) {
		this.dataInsA = dataInsA;
	}

	public void setDistretto(String distretto) {
		this.distretto = distretto;
	}

//	public void setAmbito(String ambito) {
//		this.ambito = ambito;
//	}

	public void setCognomeAssistito(String cognomeAssistito) {
		this.cognomeAssistito = cognomeAssistito;
	}

	public void setNomeAssistito(String nomeAssistito) {
		this.nomeAssistito = nomeAssistito;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public void setDataNascita(String dataNascita) {
		this.dataNascita = dataNascita;
	}

	public void setCognomeOperatore(String cognomeOperatore) {
		this.cognomeOperatore = cognomeOperatore;
	}

	public void setNomeOperatore(String nomeOperatore) {
		this.nomeOperatore = nomeOperatore;
	}

	public void setInCarico(boolean inCarico) {
		this.inCarico = inCarico;
	}

	public String getTipologiaMedico() {
		return tipologiaMedico;
	}

	public void setTipologiaMedico(String tipologiaMedico) {
		this.tipologiaMedico = tipologiaMedico;
	}

	public String getDistrettoDomicilio() {
		return distrettoDomicilio;
	}

	public void setDistrettoDomicilio(String distrettoDomicilio) {
		this.distrettoDomicilio = distrettoDomicilio;
	}

	public String getAmbitoDomicilio() {
		return ambitoDomicilio;
	}

	public void setAmbitoDomicilio(String ambitoDomicilio) {
		this.ambitoDomicilio = ambitoDomicilio;
	}

	public String getMedPed() {
		return medPed;
	}

	public void setMedPed(String medPed) {
		this.medPed = medPed;
	}

	public String getDataCreazione() {
		return dataCreazione;
	}

	public void setDataCreazione(String dataCreazione) {
		this.dataCreazione = dataCreazione;
	}

	public Boolean getInCarico() {
		return inCarico;
	}

	public void setInCarico(Boolean inCarico) {
		this.inCarico = inCarico;
	}

	public String getAsl() {
		return asl;
	}

	public void setAsl(String asl) {
		this.asl = asl;
	}
	
}
