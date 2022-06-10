/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevboweb.dto;

import it.csi.scerev.scerevjpa.business.entity.ScerevDPraticaTipo;

public class TipologiaPratica {
	
	private String id;
	private String descrizione;
	
	public TipologiaPratica() {
	}
	
	public TipologiaPratica(ScerevDPraticaTipo pratica) {
		this.id = pratica.getCodicePraticaTipo();
		this.descrizione = pratica.getValorePraticaTipo();
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getDescrizione() {
		return descrizione;
	}
	
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
}
