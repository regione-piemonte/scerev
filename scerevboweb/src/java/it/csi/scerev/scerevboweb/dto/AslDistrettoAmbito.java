/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevboweb.dto;

import java.util.ArrayList;
import java.util.List;

import it.csi.scerev.scerevjpa.business.entity.ScerevDAsl;
import it.csi.scerev.scerevjpa.business.entity.ScerevDDistretto;

public class AslDistrettoAmbito extends Asl {

	private List<Distretto> distretti;

	public AslDistrettoAmbito() {
		
	}
	
	public AslDistrettoAmbito(ScerevDAsl asl) {
		super(asl);
		this.distretti = new ArrayList<Distretto>();
		for(ScerevDDistretto distretto : asl.getScerevDDistrettos()) {
			this.distretti.add(new Distretto(distretto));
		}
	}
	
	public List<Distretto> getDistretti() {
		return distretti;
	}

	public void setDistretti(List<Distretto> distretti) {
		this.distretti = distretti;
	}
}
