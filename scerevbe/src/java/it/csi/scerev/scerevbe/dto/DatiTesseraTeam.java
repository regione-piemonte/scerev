/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevbe.dto;

import java.math.BigDecimal;
import java.util.Date;

public class DatiTesseraTeam {
	private String numeroTessera;
	private Date dataEmissione;
	private Date dataScadenza;
	private Date dataSpedizione;
	private String codiceRegioneEmittente;
	private String codiceAslEmittente;
	private String recapito;
	private BigDecimal idNazione;
	public String getNumeroTessera() {
		return numeroTessera;
	}
	public void setNumeroTessera(String numeroTessera) {
		this.numeroTessera = numeroTessera;
	}
	public Date getDataEmissione() {
		return dataEmissione;
	}
	public void setDataEmissione(Date dataEmissione) {
		this.dataEmissione = dataEmissione;
	}
	public Date getDataScadenza() {
		return dataScadenza;
	}
	public void setDataScadenza(Date dataScadenza) {
		this.dataScadenza = dataScadenza;
	}
	public String getCodiceRegioneEmittente() {
		return codiceRegioneEmittente;
	}
	public void setCodiceRegioneEmittente(String codiceRegioneEmittente) {
		this.codiceRegioneEmittente = codiceRegioneEmittente;
	}
	public String getCodiceAslEmittente() {
		return codiceAslEmittente;
	}
	public void setCodiceAslEmittente(String codiceAslEmittente) {
		this.codiceAslEmittente = codiceAslEmittente;
	}
	public String getRecapito() {
		return recapito;
	}
	public void setRecapito(String recapito) {
		this.recapito = recapito;
	}
	public BigDecimal getIdNazione() {
		return idNazione;
	}
	public void setIdNazione(BigDecimal idNazione) {
		this.idNazione = idNazione;
	}
	public Date getDataSpedizione() {
		return dataSpedizione;
	}
	public void setDataSpedizione(Date dataSpedizione) {
		this.dataSpedizione = dataSpedizione;
	}
	
}
