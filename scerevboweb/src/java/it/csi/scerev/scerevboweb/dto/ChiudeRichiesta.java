/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevboweb.dto;

public class ChiudeRichiesta {
	private String numeroPratica;
	private boolean flMedicoRevocato;
	private String notaInterna;
	
	public String getNumeroPratica() {
		return numeroPratica;
	}
	public void setNumeroPratica(String numeroPratica) {
		this.numeroPratica = numeroPratica;
	}
	public boolean isFlMedicoRevocato() {
		return flMedicoRevocato;
	}
	public void setFlMedicoRevocato(boolean flMedicoRevocato) {
		this.flMedicoRevocato = flMedicoRevocato;
	}
	public String getNotaInterna() {
		return notaInterna;
	}
	public void setNotaInterna(String notaInterna) {
		this.notaInterna = notaInterna;
	}
}
