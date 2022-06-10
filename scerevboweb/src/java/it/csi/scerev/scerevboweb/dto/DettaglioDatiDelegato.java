/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevboweb.dto;

public class DettaglioDatiDelegato {
	private String nome;
	private String cognome;
	private String codice_fiscale;

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

	public String getCodice_fiscale() {
		return codice_fiscale;
	}

	public void setCodice_fiscale(String codice_fiscale) {
		this.codice_fiscale = codice_fiscale;
	}

	@Override
	public String toString() {
		return "DettaglioDatiDelegato [nome=" + nome + ", cognome=" + cognome + ", codice_fiscale=" + codice_fiscale
				+ "]";
	}

	
}
