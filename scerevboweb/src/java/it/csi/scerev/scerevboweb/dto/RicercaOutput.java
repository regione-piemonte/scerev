/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevboweb.dto;

public class RicercaOutput {
	private int numPratica;
	private String asl;
	private String assistito;
	private String med_ped;
	private String stato_pratica;
	private String data_richiesta;
	private String operatore;
	private String distretto;
	private String ambito;
	private String cf_assistito;
	
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
	public int getNumPratica() {
		return numPratica;
	}
	public String getAsl() {
		return asl;
	}
	public String getAssistito() {
		return assistito;
	}
	public String getMed_ped() {
		return med_ped;
	}
	public String getStato_pratica() {
		return stato_pratica;
	}
	public String getData_richiesta() {
		return data_richiesta;
	}
	public String getOperatore() {
		return operatore;
	}
	public void setNumPratica(int numPratica) {
		this.numPratica = numPratica;
	}
	public void setAsl(String asl) {
		this.asl = asl;
	}
	public void setAssistito(String assistito) {
		this.assistito = assistito;
	}
	public void setMed_ped(String med_ped) {
		this.med_ped = med_ped;
	}
	public void setStato_pratica(String stato_pratica) {
		this.stato_pratica = stato_pratica;
	}
	public void setData_richiesta(String data_richiesta) {
		this.data_richiesta = data_richiesta;
	}
	public void setOperatore(String operatore) {
		this.operatore = operatore;
	}
	public String getCf_Assistito() {
		return cf_assistito;
	}
	public void setCf_Assistito(String cf_assistito) {
		this.cf_assistito = cf_assistito;
	}
}
