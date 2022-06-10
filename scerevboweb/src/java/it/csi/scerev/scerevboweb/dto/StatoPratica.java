/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevboweb.dto;

import it.csi.scerev.scerevjpa.business.entity.ScerevDPraticaStato;

public class StatoPratica {

	private int id;
	private String descrizione;
	
	public StatoPratica() {
	}
	
	public StatoPratica(ScerevDPraticaStato stato) {
		this.id = stato.getPkPraticaStato();
		this.descrizione = stato.getValorePraticaStato();
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getDescrizione() {
		return descrizione;
	}
	
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
}
