/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevboweb.dto;

public class DettaglioDeroghe {
	
	private boolean derogaPediatra;
	private boolean medicoGenerico;
	private boolean ricongiungimentoFamiliare;
	private boolean derogaTerritoriale;
	private boolean derogaAssociazione;
	private boolean derogaMassimale;
	private boolean derogaMassimaleTemp;
	private boolean medicoRevocato;
	
	public DettaglioDeroghe() {
		
	}
	
	public boolean isDerogaPediatra() {
		return derogaPediatra;
	}
	public void setDerogaPediatra(boolean derogaPediatra) {
		this.derogaPediatra = derogaPediatra;
	}
	public boolean isMedicoGenerico() {
		return medicoGenerico;
	}
	public void setMedicoGenerico(boolean medicoGenerico) {
		this.medicoGenerico = medicoGenerico;
	}
	public boolean isRicongiungimentoFamiliare() {
		return ricongiungimentoFamiliare;
	}
	public void setRicongiungimentoFamiliare(boolean ricongiungimentoFamiliare) {
		this.ricongiungimentoFamiliare = ricongiungimentoFamiliare;
	}
	public boolean isDerogaTerritoriale() {
		return derogaTerritoriale;
	}
	public void setDerogaTerritoriale(boolean derogaTerritoriale) {
		this.derogaTerritoriale = derogaTerritoriale;
	}
	public boolean isDerogaAssociazione() {
		return derogaAssociazione;
	}
	public void setDerogaAssociazione(boolean derogaAssociazione) {
		this.derogaAssociazione = derogaAssociazione;
	}
	public boolean isDerogaMassimale() {
		return derogaMassimale;
	}
	public void setDerogaMassimale(boolean derogaMassimale) {
		this.derogaMassimale = derogaMassimale;
	}
	public boolean isDerogaMassimaleTemp() {
		return derogaMassimaleTemp;
	}
	public void setDerogaMassimaleTemp(boolean derogaMassimaleTemp) {
		this.derogaMassimaleTemp = derogaMassimaleTemp;
	}
	public boolean isMedicoRevocato() {
		return medicoRevocato;
	}
	public void setMedicoRevocato(boolean medicoRevocato) {
		this.medicoRevocato = medicoRevocato;
	}
	
	
}
