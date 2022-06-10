/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevboweb.dto;

import java.util.ArrayList;
import java.util.List;

import it.csi.scerev.scerevjpa.business.entity.ScerevDAmbito;
import it.csi.scerev.scerevjpa.business.entity.ScerevDDistretto;

public class Distretto {
	private String id;
	private String descrizione;
	private List<Ambito> ambiti;
	
	public Distretto() {
		
	}
	
	public Distretto(ScerevDDistretto distretto) {
		this.id = distretto.getCodiceDistretto();
		this.descrizione = distretto.getDenominazioneDistretto();
		this.ambiti = new ArrayList<Ambito>();
		for(ScerevDAmbito ambito : distretto.getScerevDAmbitos()) {
			this.ambiti.add(new Ambito(ambito));
		}
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
	public List<Ambito> getAmbiti() {
		return ambiti;
	}
	public void setAmbiti(List<Ambito> ambiti) {
		this.ambiti = ambiti;
	}
	
	
}
