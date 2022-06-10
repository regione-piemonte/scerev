/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevbe.dto;

import java.math.BigDecimal;

public class ElencoAltriDati {
	private BigDecimal idTipoInfo;
	private String descrTipoInfo;
	private BigDecimal idAltriDati;
	private String descrAltriDati;
	public BigDecimal getIdTipoInfo() {
		return idTipoInfo;
	}
	public void setIdTipoInfo(BigDecimal idTipoInfo) {
		this.idTipoInfo = idTipoInfo;
	}
	public String getDescrTipoInfo() {
		return descrTipoInfo;
	}
	public void setDescrTipoInfo(String descrTipoInfo) {
		this.descrTipoInfo = descrTipoInfo;
	}
	public BigDecimal getIdAltriDati() {
		return idAltriDati;
	}
	public void setIdAltriDati(BigDecimal idAltriDati) {
		this.idAltriDati = idAltriDati;
	}
	public String getDescrAltriDati() {
		return descrAltriDati;
	}
	public void setDescrAltriDati(String descrAltriDati) {
		this.descrAltriDati = descrAltriDati;
	}
}
