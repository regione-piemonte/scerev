/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevboweb.dto;

import it.csi.scerev.scerevjpa.business.entity.ScerevDTipologiaProfilo;

public class TipologiaProfilo {
	private String codTipologia;
	private String descTipologia;
	
	public TipologiaProfilo(ScerevDTipologiaProfilo tipologia) {
		this.codTipologia = tipologia.getCodiceTipologiaProfilo();
		this.descTipologia = tipologia.getValoreTipologiaProfilo();
	}
	public String getCodTipologia() {
		return codTipologia;
	}
	public void setCodTipologia(String codTipologia) {
		this.codTipologia = codTipologia;
	}
	public String getDescTipologia() {
		return descTipologia;
	}
	public void setDescTipologia(String descTipologia) {
		this.descTipologia = descTipologia;
	}

}
