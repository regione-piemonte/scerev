/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevbe.dto;

import java.math.BigDecimal;
import java.util.Date;

public class DatiDecesso {
	private Date data;
	private BigDecimal idNazione;
	private BigDecimal idComune;
	private BigDecimal idFonte;
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public BigDecimal getIdNazione() {
		return idNazione;
	}
	public void setIdNazione(BigDecimal idNazione) {
		this.idNazione = idNazione;
	}
	public BigDecimal getIdComune() {
		return idComune;
	}
	public void setIdComune(BigDecimal idComune) {
		this.idComune = idComune;
	}
	public BigDecimal getIdFonte() {
		return idFonte;
	}
	public void setIdFonte(BigDecimal idFonte) {
		this.idFonte = idFonte;
	}
	
	

}
