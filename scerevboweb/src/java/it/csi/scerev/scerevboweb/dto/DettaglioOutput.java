/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevboweb.dto;

import java.util.List;

public class DettaglioOutput {
	
	private DettaglioDatiMedico dati_medico_richiesto;
	private DettaglioDatiRichiedente dati_richiedente;
	private DettaglioDatiAuraMedico dati_aura_medico;
	private DettaglioDatiMedicoExtra dati_medico_extra;
	private DettaglioInformazioniSupplementari informazioni_supplementari;
	private DettaglioDatiDelegato dati_delegato;
	private List<DettaglioAllegato> allegati;
	private List<Motivazione> motivazioni;
	private List<TipologiaProfilo> tipologieProfilo;
	private String inCarico;
	private String stato;
	private int numPratica;
	private String tipologieRichiesta;
	private DettaglioDeroghe deroghe;
	private String dataInizioAss;
	private String dataFineAss;
	private String cfInCarico;
	private String msgIfClosed;

	
	public DettaglioOutput() {
		
	}
	
	public String getInCarico() {
		return inCarico;
	}

	public void setInCarico(String inCarico) {
		this.inCarico = inCarico;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public DettaglioDatiMedico getDati_medico_richiesto() {
		return dati_medico_richiesto;
	}

	public void setDati_medico_richiesto(DettaglioDatiMedico dati_medico_richiesto) {
		this.dati_medico_richiesto = dati_medico_richiesto;
	}

	public DettaglioDatiRichiedente getDati_richiedente() {
		return dati_richiedente;
	}

	public void setDati_richiedente(DettaglioDatiRichiedente dati_richiedente) {
		this.dati_richiedente = dati_richiedente;
	}

	public DettaglioDatiAuraMedico getDati_aura_medico() {
		return dati_aura_medico;
	}

	public void setDati_aura_medico(DettaglioDatiAuraMedico dati_aura_medico) {
		this.dati_aura_medico = dati_aura_medico;
	}

	public DettaglioDatiMedicoExtra getDati_medico_extra() {
		return dati_medico_extra;
	}

	public void setDati_medico_extra(DettaglioDatiMedicoExtra dati_medico_extra) {
		this.dati_medico_extra = dati_medico_extra;
	}

	public DettaglioInformazioniSupplementari getInformazioni_supplementari() {
		return informazioni_supplementari;
	}

	public void setInformazioni_supplementari(DettaglioInformazioniSupplementari informazioni_supplementari) {
		this.informazioni_supplementari = informazioni_supplementari;
	}

	public List<DettaglioAllegato> getAllegati() {
		return allegati;
	}

	public void setAllegati(List<DettaglioAllegato> allegati) {
		this.allegati = allegati;
	}

	public List<Motivazione> getMotivazioni() {
		return motivazioni;
	}

	public void setMotivazioni(List<Motivazione> motivazioni) {
		this.motivazioni = motivazioni;
	}

	public List<TipologiaProfilo> getTipologieProfilo() {
		return tipologieProfilo;
	}

	public void setTipologieProfilo(List<TipologiaProfilo> tipologieProfilo) {
		this.tipologieProfilo = tipologieProfilo;
	}

	@Override
	public String toString() {
		return "DettaglioOutput [dati_medico_richiesto=" + dati_medico_richiesto + ", dati_richiedente="
				+ dati_richiedente + ", dati_aura_medico=" + dati_aura_medico + ", dati_medico_extra="
				+ dati_medico_extra + ", informazioni_supplementari=" + informazioni_supplementari + ", allegati="
				+ allegati + ", motivazioni=" + motivazioni + "]";
	}

	
	public void setNumPratica(Integer numeroPratica) {
		this.numPratica = numeroPratica;		
	}
	
	public int getNumPratica() {
		return this.numPratica;		
	}

	public String getTipologieRichiesta() {
		return tipologieRichiesta;
	}

	public void setTipologieRichiesta(String tipologieRichiesta) {
		this.tipologieRichiesta = tipologieRichiesta;
	}

	public DettaglioDeroghe getDeroghe() {
		return deroghe;
	}

	public void setDeroghe(DettaglioDeroghe deroghe) {
		this.deroghe = deroghe;
	}

	public String getDataInizioAss() {
		return dataInizioAss;
	}

	public void setDataInizioAss(String dataInizioAss) {
		this.dataInizioAss = dataInizioAss;
	}

	public String getDataFineAss() {
		return dataFineAss;
	}

	public void setDataFineAss(String dataFineAss) {
		this.dataFineAss = dataFineAss;
	}

	public String getCfInCarico() {
		return cfInCarico;
		
	}
	
	public void setCfInCarico(String cfOperatore) {
		this.cfInCarico = cfOperatore;
	}

	public String getMsgIfClosed() {
		return msgIfClosed;
	}

	public void setMsgIfClosed(String msgIfClosed) {
		this.msgIfClosed = msgIfClosed;
	}

	public DettaglioDatiDelegato getDati_delegato() {
		return dati_delegato;
	}

	public void setDati_delegato(DettaglioDatiDelegato dati_delegato) {
		this.dati_delegato = dati_delegato;
	}
	
}
