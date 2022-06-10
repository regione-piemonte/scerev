/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevboweb.dto;

public class AccettaRichiesta {
	private String codiceFiscaleAssistito;
	private String numeroPratica;
	private String tipologia;
	private String durataAssistenzaDa;
	private String durataAssistenzaA;
	private boolean flMedicoGenerico;
	private boolean flDerogaPediatra;
	private boolean flDerogaTerritoriale;
	private boolean flDerogaAssociazione;
	private boolean flDerogaMassimale;
	private boolean flDerogaMassimaleTmp;
	private boolean flRicongFamigliare;
	private boolean flMedicoRevocato;
	private String notaInterna;
	private boolean daChiudere;

	public String getCodiceFiscaleAssistito() {
		return codiceFiscaleAssistito;
	}

	public void setCodiceFiscaleAssistito(String codiceFiscaleAssistito) {
		this.codiceFiscaleAssistito = codiceFiscaleAssistito;
	}

	public String getNumeroPratica() {
		return numeroPratica;
	}

	public void setNumeroPratica(String numeroPratica) {
		this.numeroPratica = numeroPratica;
	}

	public String getTipologia() {
		return tipologia;
	}

	public void setTipologia(String tipologia) {
		this.tipologia = tipologia;
	}

	public String getDurataAssistenzaDa() {
		return durataAssistenzaDa;
	}

	public void setDurataAssistenzaDa(String durataAssistenzaDa) {
		this.durataAssistenzaDa = durataAssistenzaDa;
	}

	public String getDurataAssistenzaA() {
		return durataAssistenzaA;
	}

	public void setDurataAssistenzaA(String durataAssistenzaA) {
		this.durataAssistenzaA = durataAssistenzaA;
	}

	public boolean isFlMedicoGenerico() {
		return flMedicoGenerico;
	}

	public void setFlMedicoGenerico(boolean flMedicoGenerico) {
		this.flMedicoGenerico = flMedicoGenerico;
	}
	
	public boolean isFlDerogaPediatra() {
		return flDerogaPediatra;
	}
	
	public void setFlDerogaPediatra(boolean flDerogaPediatra) {
		this.flDerogaPediatra = flDerogaPediatra;
	}

	public boolean isFlDerogaTerritoriale() {
		return flDerogaTerritoriale;
	}

	public void setFlDerogaTerritoriale(boolean flDerogaTerritoriale) {
		this.flDerogaTerritoriale = flDerogaTerritoriale;
	}

	public boolean isFlDerogaAssociazione() {
		return flDerogaAssociazione;
	}

	public void setFlDerogaAssociazione(boolean flDerogaAssociazione) {
		this.flDerogaAssociazione = flDerogaAssociazione;
	}

	public boolean isFlDerogaMassimale() {
		return flDerogaMassimale;
	}

	public void setFlDerogaMassimale(boolean flDerogaMassimale) {
		this.flDerogaMassimale = flDerogaMassimale;
	}

	public boolean isFlDerogaMassimaleTmp() {
		return flDerogaMassimaleTmp;
	}

	public void setFlDerogaMassimaleTmp(boolean flDerogaMassimaleTmp) {
		this.flDerogaMassimaleTmp = flDerogaMassimaleTmp;
	}

	public boolean isFlRicongFamigliare() {
		return flRicongFamigliare;
	}

	public void setFlRicongFamigliare(boolean flRicongFamigliare) {
		this.flRicongFamigliare = flRicongFamigliare;
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

	@Override
	public String toString() {
		return "AccettaRichiesta [codiceFiscaleAssistito=" + codiceFiscaleAssistito + ", numeroPratica=" + numeroPratica
				+ ", tipologia=" + tipologia + ", durataAssistenzaDa=" + durataAssistenzaDa + ", durataAssistenzaA="
				+ durataAssistenzaA + ", flMedicoGenerico=" + flMedicoGenerico + ", flDerogaPediatra=" + flDerogaPediatra 
				+ ", flDerogaTerritoriale="	+ flDerogaTerritoriale + ", flDerigaAssociazione=" + flDerogaAssociazione 
				+ ", flDerogaMassimale=" + flDerogaMassimale + ", flDerogaMassimaleTmp=" + flDerogaMassimaleTmp 
				+ ", flRicongFamigliare=" + flRicongFamigliare + ", flMedicoRevocato=" + flMedicoRevocato + ", notaInterna=" + notaInterna + "]";
	}

	public boolean isDaChiudere() {
		return daChiudere;
	}

	public void setDaChiudere(boolean daChiudere) {
		this.daChiudere = daChiudere;
	}

}
