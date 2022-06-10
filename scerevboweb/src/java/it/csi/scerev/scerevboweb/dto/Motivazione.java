/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevboweb.dto;

import it.csi.scerev.scerevjpa.business.entity.ScerevDMotivazione;

public class Motivazione {
	private String codMotivazione;
	private String descMotivazione;
	private String tipoMotivazione;
	
	public Motivazione(ScerevDMotivazione motiv) {
		this.codMotivazione = motiv.getCodiceMotivazione();
		this.descMotivazione = motiv.getValoreMotivazione();
		this.tipoMotivazione = motiv.getTipoMotivazione();
	}
	public String getCodMotivazione() {
		return codMotivazione;
	}
	public void setCodMotivazione(String codMotivazione) {
		this.codMotivazione = codMotivazione;
	}
	public String getDescMotivazione() {
		return descMotivazione;
	}
	public void setDescMotivazione(String descMotivazione) {
		this.descMotivazione = descMotivazione;
	}
	@Override
	public String toString() {
		return "Motivazione [codMotivazione=" + codMotivazione + ", descMotivazione=" + descMotivazione + "]";
	}
	public String getTipoMotivazione() {
		return tipoMotivazione;
	}
	public void setTipoMotivazione(String tipoMotivazione) {
		this.tipoMotivazione = tipoMotivazione;
	}
}
