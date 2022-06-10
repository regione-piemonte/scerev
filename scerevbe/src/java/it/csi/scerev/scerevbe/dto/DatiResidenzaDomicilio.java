/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevbe.dto;

import java.math.BigDecimal;
import java.util.Date;

public class DatiResidenzaDomicilio {
	private BigDecimal idNazione;
	private BigDecimal idComune;
	private String indirizzo;
	private String numeroCivico;
	private String cap;
	private String telefono;
	private BigDecimal idTipoTelefono;
	private String telefono2;
	private BigDecimal idTipoTelefono2;
	private String note;
	private BigDecimal idAsl;
	private String descCircoscrizione;
	private Date inizioValidita;
	private Date fineValidita;
	private BigDecimal idIndirizzo;
	private BigDecimal idNumeroCivico;
	private String stato;
	
	public DatiResidenzaDomicilio(ModelCittadinoResidenza residenza) {
		this.cap = residenza.getCap();
		this.numeroCivico = residenza.getCivico();
//		this.idComune = residenza.getComune();
		this.indirizzo = residenza.getIndirizzo();
		this.stato = residenza.getNazione();
//		residenza.getRegione();
	}
	public DatiResidenzaDomicilio(ModelCittadinoDomicilio domicilio) {
		this.cap = domicilio.getCap();
		this.numeroCivico = domicilio.getCivico();
//		this.idComune = domicilio.getComune();
		this.indirizzo = domicilio.getIndirizzo();
//		domicilio.getRegione();
	}
	public BigDecimal getIdNazione() {
		return idNazione;
	}
	public void setIdNazione(BigDecimal idNazione) {
		this.idNazione = idNazione;
	}
	public BigDecimal getIdComune() {
		return idComune;
	}
	public void setIdComune(BigDecimal idComune) {
		this.idComune = idComune;
	}
	public String getIndirizzo() {
		return indirizzo;
	}
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
	public String getNumeroCivico() {
		return numeroCivico;
	}
	public void setNumeroCivico(String numeroCivico) {
		this.numeroCivico = numeroCivico;
	}
	public String getCap() {
		return cap;
	}
	public void setCap(String cap) {
		this.cap = cap;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public BigDecimal getIdTipoTelefono() {
		return idTipoTelefono;
	}
	public void setIdTipoTelefono(BigDecimal idTipoTelefono) {
		this.idTipoTelefono = idTipoTelefono;
	}
	public String getTelefono2() {
		return telefono2;
	}
	public void setTelefono2(String telefono2) {
		this.telefono2 = telefono2;
	}
	public BigDecimal getIdTipoTelefono2() {
		return idTipoTelefono2;
	}
	public void setIdTipoTelefono2(BigDecimal idTipoTelefono2) {
		this.idTipoTelefono2 = idTipoTelefono2;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public BigDecimal getIdAsl() {
		return idAsl;
	}
	public void setIdAsl(BigDecimal idAsl) {
		this.idAsl = idAsl;
	}
	public String getDescCircoscrizione() {
		return descCircoscrizione;
	}
	public void setDescCircoscrizione(String descCircoscrizione) {
		this.descCircoscrizione = descCircoscrizione;
	}
	public Date getInizioValidita() {
		return inizioValidita;
	}
	public void setInizioValidita(Date inizioValidita) {
		this.inizioValidita = inizioValidita;
	}
	public Date getFineValidita() {
		return fineValidita;
	}
	public void setFineValidita(Date fineValidita) {
		this.fineValidita = fineValidita;
	}
	public BigDecimal getIdIndirizzo() {
		return idIndirizzo;
	}
	public void setIdIndirizzo(BigDecimal idIndirizzo) {
		this.idIndirizzo = idIndirizzo;
	}
	public BigDecimal getIdNumeroCivico() {
		return idNumeroCivico;
	}
	public void setIdNumeroCivico(BigDecimal idNumeroCivico) {
		this.idNumeroCivico = idNumeroCivico;
	}
	public String getStato() {
		return stato;
	}
	public void setStato(String stato) {
		this.stato = stato;
	}

}
