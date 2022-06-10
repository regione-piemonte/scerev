/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevboweb.dto;

public class ConsensoMedico {
	private String nome;
	private int pkAllegato;
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getPkAllegato() {
		return pkAllegato;
	}
	public void setPkAllegato(int pkAllegato) {
		this.pkAllegato = pkAllegato;
	}
}
