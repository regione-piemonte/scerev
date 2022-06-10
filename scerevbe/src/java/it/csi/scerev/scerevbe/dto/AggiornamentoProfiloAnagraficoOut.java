/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevbe.dto;

import java.math.BigDecimal;

public class AggiornamentoProfiloAnagraficoOut {
	private BigDecimal idAura;
	private BigDecimal idEsito;
	private BigDecimal idMessage;
	private String descMessage;
	public BigDecimal getIdAura() {
		return idAura;
	}
	public void setIdAura(BigDecimal idAura) {
		this.idAura = idAura;
	}
	public BigDecimal getIdEsito() {
		return idEsito;
	}
	public void setIdEsito(BigDecimal idEsito) {
		this.idEsito = idEsito;
	}
	public BigDecimal getIdMessage() {
		return idMessage;
	}
	public void setIdMessage(BigDecimal idMessage) {
		this.idMessage = idMessage;
	}
	public String getDescMessage() {
		return descMessage;
	}
	public void setDescMessage(String descMessage) {
		this.descMessage = descMessage;
	}
}
