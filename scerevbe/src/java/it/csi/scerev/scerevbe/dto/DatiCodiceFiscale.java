/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevbe.dto;

public class DatiCodiceFiscale {
	
	private String codiceFiscale;
	private String statoCodiceFiscale;
	
	public DatiCodiceFiscale(String citId) {
		this.codiceFiscale = citId;
		this.statoCodiceFiscale = citId;
	}
	public String getCodiceFiscale() {
		return codiceFiscale;
	}
	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}
	public String getStatoCodiceFiscale() {
		return statoCodiceFiscale;
	}
	public void setStatoCodiceFiscale(String statoCodiceFiscale) {
		this.statoCodiceFiscale = statoCodiceFiscale;
	}
	
	

}
