/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevboweb.dto;

import java.util.List;

public class DettaglioAllegato {
	private String tipoAllegato;
	private List<Allegato> allegati;

	public String getTipoAllegato() {
		return tipoAllegato;
	}

	public void setTipoAllegato(String nomeAllegato) {
		this.tipoAllegato = nomeAllegato;
	}


	@Override
	public String toString() {
		return "DettaglioAllegato [tipoAllegato=" + tipoAllegato + ", allegati=" + allegati + "]";
	}

	public List<Allegato> getAllegati() {
		return allegati;
	}

	public void setAllegati(List<Allegato> allegati) {
		this.allegati = allegati;
	}
}
