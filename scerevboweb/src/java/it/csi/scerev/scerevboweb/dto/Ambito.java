/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevboweb.dto;

import it.csi.scerev.scerevjpa.business.entity.ScerevDAmbito;

public class Ambito {
	private String id;
	private String descrizione;
	
	public Ambito() {
		
	}
	
	public Ambito(ScerevDAmbito ambito) {
		this.id = ambito.getCodiceAmbito();
		this.descrizione = ambito.getDenominazioneAmbito();
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
