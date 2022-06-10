/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevbe.dto;

import it.csi.scerev.scerevserviziesterni.ensemble.interrogamefscerev.InterrogaMefScerevResult;

public class ModelCittadinoSistemaEsternoTS extends ModelCittadinoSistemaEsterno  {
	
	private String codiceTipoAssistito;
	
	private String codiceRegioneResidenza;
	
	private String dataInizioValidita;
	
	private String dataFineValidita;
	
	private String statoNasc;

	public String getCodiceTipoAssistito() {
		return codiceTipoAssistito;
	}

	public void setCodiceTipoAssistito(String codiceTipoAssistito) {
		this.codiceTipoAssistito = codiceTipoAssistito;
	}

	public String getCodiceRegioneResidenza() {
		return codiceRegioneResidenza;
	}

	public void setCodiceRegioneResidenza(String codiceRegioneResidenza) {
		this.codiceRegioneResidenza = codiceRegioneResidenza;
	}

	public String getDataInizioValidita() {
		return dataInizioValidita;
	}

	public void setDataInizioValidita(String dataInizioValidita) {
		this.dataInizioValidita = dataInizioValidita;
	}

	public String getDataFineValidita() {
		return dataFineValidita;
	}

	public void setDataFineValidita(String dataFineValidita) {
		this.dataFineValidita = dataFineValidita;
	}

	public String getStatoNasc() {
		return statoNasc;
	}
	
	public void setStatoNasc(String statoNasc) {
		this.statoNasc = statoNasc;
	}
	
	public ModelCittadinoSistemaEsternoTS(InterrogaMefScerevResult in) {
		super(in);
		this.codiceTipoAssistito = in.getCodiceTipoAssistito();
		this.codiceRegioneResidenza = in.getCodiceRegioneAssistenza();
		this.dataInizioValidita = in.getDataInizioValidita();
		this.dataFineValidita = in.getDataFineValidita();
		this.statoNasc = in.getNazionenasc();
	}

}
