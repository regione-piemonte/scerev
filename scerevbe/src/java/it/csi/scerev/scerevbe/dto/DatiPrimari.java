/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevbe.dto;

import java.math.BigDecimal;
import java.util.Date;

public class DatiPrimari {
	private String nome;
	private String cognome;
	private String sesso;
	private Date dataNascita;
	private String idNazioneNascita;
	private BigDecimal idComuneNascita;
	private String stato;
	private String consensoPrivacy;
	private Date dataConsensoPrivacy;
	
	public DatiPrimari(ModelCittadinoSistemaEsterno da) {
		this.nome = da.getNome();
		this.cognome = da.getCognome();
		this.dataNascita = da.getDatanascita();
		this.sesso = da.getSesso();
		this.idNazioneNascita = da.getIdStatonascita();
		
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
	public String getSesso() {
		return sesso;
	}
	public void setSesso(String sesso) {
		this.sesso = sesso;
	}
	public Date getDataNascita() {
		return dataNascita;
	}
	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}
	public String getIdNazioneNascita() {
		return idNazioneNascita;
	}
	public void setIdNazioneNascita(String idNazioneNascita) {
		this.idNazioneNascita = idNazioneNascita;
	}
	public BigDecimal getIdComuneNascita() {
		return idComuneNascita;
	}
	public void setIdComuneNascita(BigDecimal idComuneNascita) {
		this.idComuneNascita = idComuneNascita;
	}
	public String getStato() {
		return stato;
	}
	public void setStato(String stato) {
		this.stato = stato;
	}
	public String getConsensoPrivacy() {
		return consensoPrivacy;
	}
	public void setConsensoPrivacy(String consensoPrivacy) {
		this.consensoPrivacy = consensoPrivacy;
	}
	public Date getDataConsensoPrivacy() {
		return dataConsensoPrivacy;
	}
	public void setDataConsensoPrivacy(Date dataConsensoPrivacy) {
		this.dataConsensoPrivacy = dataConsensoPrivacy;
	}
}
