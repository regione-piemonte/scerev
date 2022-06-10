/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scadenzaassistenza.dto;


public class ScadenzaAssistenza {
	private String idAura;
	private String cognome;
	private String nome;
	private String dataNascita;
	private String sesso;
	private String codiceFiscale;
	private String dataFineAsl;
	private String idAsl;
	private String codiceAsl;
	private String descrizioneAsl;
	private String stato;
	
	public ScadenzaAssistenza() {}
	
	public ScadenzaAssistenza(String[] record) {
		this.idAura = record[0];
		this.cognome = record[1];
		this.nome = record[2];
		this.dataNascita = record[3];
		this.sesso = record[4];
		this.codiceFiscale = record[5];
		this.dataFineAsl = record[6];
		this.idAsl = record[7];
		this.codiceAsl = record[8];
		this.descrizioneAsl = record[9];
		this.stato = record[10];
	}

	public String getIdAura() {
		return idAura;
	}

	public void setIdAura(String idAura) {
		this.idAura = idAura;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDataNascita() {
		return dataNascita;
	}

	public void setDataNascita(String dataNascita) {
		this.dataNascita = dataNascita;
	}

	public String getSesso() {
		return sesso;
	}

	public void setSesso(String sesso) {
		this.sesso = sesso;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public String getDataFineAsl() {
		return dataFineAsl;
	}

	public void setDataFineAsl(String dataFineAsl) {
		this.dataFineAsl = dataFineAsl;
	}

	public String getIdAsl() {
		return idAsl;
	}

	public void setIdAsl(String idAsl) {
		this.idAsl = idAsl;
	}

	public String getCodiceAsl() {
		return codiceAsl;
	}

	public void setCodiceAsl(String codiceAsl) {
		this.codiceAsl = codiceAsl;
	}

	public String getDescrizioneAsl() {
		return descrizioneAsl;
	}

	public void setDescrizioneAsl(String descrizioneAsl) {
		this.descrizioneAsl = descrizioneAsl;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}
	
}
