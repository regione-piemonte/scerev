/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevboweb.dto;

import it.csi.scerev.scerevjpa.business.entity.ScerevDAsl;

public class Asl {
	private String id;
	private String descrizione;
	
	public Asl() {
	}
	
	public Asl(ScerevDAsl asl) {
		this.id = asl.getCodiceAzienda();
		this.descrizione = asl.getDenominazioneAzienda();
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
