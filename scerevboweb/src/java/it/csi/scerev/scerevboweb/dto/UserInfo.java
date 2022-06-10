/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevboweb.dto;

import java.util.List;

public class UserInfo implements Comparable<UserInfo> {

	private String cognome;

	private String nome;

	private String codFisc;

	private List<String> codASL;
	
	private String ASL;

	private String idAura;

	private String ruolo;

	public String getIdAura() {
		return idAura;
	}

	public void setIdAura(String idAura) {
		this.idAura = idAura;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCodFisc() {
		return codFisc;
	}

	public void setCodFisc(String codFisc) {
		this.codFisc = codFisc;
	}

	public List<String> getCodASL() {
		return codASL;
	}

	public void setCodASL(List<String> codASL) {
		this.codASL = codASL;
	}

	public String getASL() {
		return ASL;
	}

	public void setASL(String aSL) {
		ASL = aSL;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codFisc == null) ? 0 : codFisc.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserInfo other = (UserInfo) obj;
		if (codFisc == null) {
			if (other.codFisc != null)
				return false;
		} else if (!codFisc.equals(other.codFisc))
			return false;
		return true;
	}

	@Override
	public int compareTo(UserInfo o) {
		if (this.equals(o))
			return 0;
		return 1;
	}

	
	public String getRuolo() {
		return ruolo;
	}

	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}

}
