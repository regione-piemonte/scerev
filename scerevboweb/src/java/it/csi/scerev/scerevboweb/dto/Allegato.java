/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevboweb.dto;

public class Allegato {
	String nomeAllegato;
	String pkAllegato;
	
	public Allegato(String nome, String pk){
		this.nomeAllegato = nome;
		this.pkAllegato = pk;
	}

	public String getNomeAllegato() {
		return nomeAllegato;
	}

	public void setNomeAllegato(String nomeAllegato) {
		this.nomeAllegato = nomeAllegato;
	}

	public String getPkAllegato() {
		return pkAllegato;
	}

	public void setPkAllegato(String pkAllegato) {
		this.pkAllegato = pkAllegato;
	}

}
