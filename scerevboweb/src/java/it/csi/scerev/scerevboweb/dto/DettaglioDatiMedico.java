/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevboweb.dto;

public class DettaglioDatiMedico {
	
	private String nome;
	private String cognome;
	private String tipologia;
	private String richiesto_fino;
	private String asl;
	private String distretto;
	private String ambito;
	private String massimale;
	private String autolimitato;
	private String massimale_deroga;
	private String massimale_scelte_temp;
	private String massimale_ris_fascia;
	private String assistiti_netti;
	private String ass_06;
	private String assistiti_lordi;
	private String dispo_piena;
	private String disp_ric;
	private String dispo_temp;
	private String dispo_06;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getTipologia() {
		return tipologia;
	}
	public void setTipologia(String tipologia) {
		this.tipologia = tipologia;
	}
	public String getRichiesto_fino() {
		return richiesto_fino;
	}
	public void setRichiesto_fino(String richiesto_fino) {
		this.richiesto_fino = richiesto_fino;
	}
	public String getAsl() {
		return asl;
	}
	public void setAsl(String asl) {
		this.asl = asl;
	}
	public String getDistretto() {
		return distretto;
	}
	public void setDistretto(String distretto) {
		this.distretto = distretto;
	}
	public String getAmbito() {
		return ambito;
	}
	public void setAmbito(String ambito) {
		this.ambito = ambito;
	}
	public String getMassimale() {
		return massimale;
	}
	public void setMassimale(String massimale) {
		this.massimale = massimale;
	}
	public String getAutolimitato() {
		return autolimitato;
	}
	public void setAutolimitato(String autolimitato) {
		this.autolimitato = autolimitato;
	}
	public String getMassimale_deroga() {
		return massimale_deroga;
	}
	public void setMassimale_deroga(String massimale_deroga) {
		this.massimale_deroga = massimale_deroga;
	}
	public String getMassimale_scelte_temp() {
		return massimale_scelte_temp;
	}
	public void setMassimale_scelte_temp(String massimale_scelte_temp) {
		this.massimale_scelte_temp = massimale_scelte_temp;
	}
	public String getMassimale_ris_fascia() {
		return massimale_ris_fascia;
	}
	public void setMassimale_ris_fascia(String massimale_ris_fascia) {
		this.massimale_ris_fascia = massimale_ris_fascia;
	}
	public String getAssistiti_netti() {
		return assistiti_netti;
	}
	public void setAssistiti_netti(String assistiti_netti) {
		this.assistiti_netti = assistiti_netti;
	}
	public String getAss_06() {
		return ass_06;
	}
	public void setAss_06(String ass_06) {
		this.ass_06 = ass_06;
	}
	public String getAssistiti_lordi() {
		return assistiti_lordi;
	}
	public void setAssistiti_lordi(String assistiti_lordi) {
		this.assistiti_lordi = assistiti_lordi;
	}
	public String getDispo_piena() {
		return dispo_piena;
	}
	public void setDispo_piena(String dispo_piena) {
		this.dispo_piena = dispo_piena;
	}
	public String getDisp_ric() {
		return disp_ric;
	}
	public void setDisp_ric(String disp_ric) {
		this.disp_ric = disp_ric;
	}
	public String getDispo_temp() {
		return dispo_temp;
	}
	public void setDispo_temp(String dispo_temp) {
		this.dispo_temp = dispo_temp;
	}
	public String getDispo_06() {
		return dispo_06;
	}
	public void setDispo_06(String dispo_06) {
		this.dispo_06 = dispo_06;
	}
	@Override
	public String toString() {
		return "DettaglioDatiMedico [nome=" + nome + ", cognome=" + cognome + ", tipologia=" + tipologia
				+ ", richiesto_fino=" + richiesto_fino + ", asl=" + asl + ", distretto=" + distretto + ", ambito="
				+ ambito + ", massimale=" + massimale + ", autolimitato=" + autolimitato + ", massimale_deroga="
				+ massimale_deroga + ", massimale_scelte_temp=" + massimale_scelte_temp + ", massimale_ris_fascia="
				+ massimale_ris_fascia + ", assistiti_netti=" + assistiti_netti + ", ass_06=" + ass_06
				+ ", assistiti_lordi=" + assistiti_lordi + ", dispo_piena=" + dispo_piena + ", disp_ric=" + disp_ric
				+ ", dispo_temp=" + dispo_temp + ", dispo_06=" + dispo_06 + "]";
	}

}
