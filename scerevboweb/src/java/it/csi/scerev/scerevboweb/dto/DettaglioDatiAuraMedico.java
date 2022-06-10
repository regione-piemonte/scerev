/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevboweb.dto;

public class DettaglioDatiAuraMedico {
	private String id;
	private String nome;
	private String cognome;
	private String dataIscrizioneAsl;
	private String dataFineIscrizioneAsl;
	private String residenzaInAura;
	private String domicilioInAura;
	private String aslDomicilioInAura;
	private String tipologiaProfiloSanitario;
	private String aslIscrizione;
	private String movimento;
	private String dataMovimento;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public String getDataIscrizioneAsl() {
		return dataIscrizioneAsl;
	}

	public void setDataIscrizioneAsl(String dataIscrizioneAsl) {
		this.dataIscrizioneAsl = dataIscrizioneAsl;
	}

	public String getDataFineIscrizioneAsl() {
		return dataFineIscrizioneAsl;
	}

	public void setDataFineIscrizioneAsl(String dataFineIscrizioneAsl) {
		this.dataFineIscrizioneAsl = dataFineIscrizioneAsl;
	}

	public String getResidenzaInAura() {
		return residenzaInAura;
	}

	public void setResidenzaInAura(String residenzaInAura) {
		this.residenzaInAura = residenzaInAura;
	}

	public String getDomicilioInAura() {
		return domicilioInAura;
	}

	public void setDomicilioInAura(String domicilioInAura) {
		this.domicilioInAura = domicilioInAura;
	}

	public String getAslDomicilioInAura() {
		return aslDomicilioInAura;
	}

	public void setAslDomicilioInAura(String aslDomicilioInAura) {
		this.aslDomicilioInAura = aslDomicilioInAura;
	}

	public String getTipologiaProfiloSanitario() {
		return tipologiaProfiloSanitario;
	}

	public void setTipologiaProfiloSanitario(String tipologiaProfiloSanitario) {
		this.tipologiaProfiloSanitario = tipologiaProfiloSanitario;
	}

	public String getAslIscrizione() {
		return aslIscrizione;
	}

	public void setAslIscrizione(String aslIscrizione) {
		this.aslIscrizione = aslIscrizione;
	}

	public String getMovimento() {
		return movimento;
	}

	public void setMovimento(String movimento) {
		this.movimento = movimento;
	}

	public String getDataMovimento() {
		return dataMovimento;
	}

	public void setDataMovimento(String dataMovimento) {
		this.dataMovimento = dataMovimento;
	}

	@Override
	public String toString() {
		return "DettaglioDatiAuraMedico [id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", dataIscrizioneAsl="
				+ dataIscrizioneAsl + ", dataFineIscrizioneAsl=" + dataFineIscrizioneAsl + ", residenzaInAura="
				+ residenzaInAura + ", domiciioInAura=" + domicilioInAura + ", aslDomicilioInAura=" + aslDomicilioInAura
				+ ", tipologiaProfiloSanitario=" + tipologiaProfiloSanitario + ", aslIscrizione=" + aslIscrizione + "]";
	}
}
