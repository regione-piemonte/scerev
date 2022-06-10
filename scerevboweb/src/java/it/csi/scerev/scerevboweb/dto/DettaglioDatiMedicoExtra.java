/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevboweb.dto;

public class DettaglioDatiMedicoExtra {
	private String id;
	private String nome;
	private String cognome;
	private String aziendaSanitaria;
	private String regione;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getAziendaSanitaria() {
		return aziendaSanitaria;
	}

	public void setAziendaSanitaria(String aziendaSanitaria) {
		this.aziendaSanitaria = aziendaSanitaria;
	}

	public String getRegione() {
		return regione;
	}

	public void setRegione(String regione) {
		this.regione = regione;
	}

	@Override
	public String toString() {
		return "DettaglioDatiMedicoExtra [id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", aziendaSanitaria="
				+ aziendaSanitaria + ", regione=" + regione + "]";
	}
}
