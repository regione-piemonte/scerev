/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevlibbatch.dto;

import java.util.Objects;

public class ModelDisponibilitaMedicoMonitorato {

	private boolean selezionabile;
	private boolean selezionabileRicongiungimentoFamiliare;
	private boolean selezionabileAssistenzaTemporanea;
	private boolean selezionabileInfanzia;
	public boolean isSelezionabile() {
		return selezionabile;
	}
	public void setSelezionabile(boolean selezionabile) {
		this.selezionabile = selezionabile;
	}
	public boolean isSelezionabileRicongiungimentoFamiliare() {
		return selezionabileRicongiungimentoFamiliare;
	}
	public void setSelezionabileRicongiungimentoFamiliare(boolean selezionabileRicongiungimentoFamiliare) {
		this.selezionabileRicongiungimentoFamiliare = selezionabileRicongiungimentoFamiliare;
	}
	public boolean isSelezionabileAssistenzaTemporanea() {
		return selezionabileAssistenzaTemporanea;
	}
	public void setSelezionabileAssistenzaTemporanea(boolean selezionabileAssistenzaTemporanea) {
		this.selezionabileAssistenzaTemporanea = selezionabileAssistenzaTemporanea;
	}
	public boolean isSelezionabileInfanzia() {
		return selezionabileInfanzia;
	}
	public void setSelezionabileInfanzia(boolean selezionabileInfanzia) {
		this.selezionabileInfanzia = selezionabileInfanzia;
	}
	
	@Override
	  public boolean equals(Object o) {
	    if (this == o) {
	      return true;
	    }
	    if (o == null || getClass() != o.getClass()) {
	      return false;
	    }
	    ModelDisponibilitaMedicoMonitorato modelDisponibilita = (ModelDisponibilitaMedicoMonitorato) o;
	    return selezionabile == modelDisponibilita.isSelezionabile() &&
	        selezionabileRicongiungimentoFamiliare == modelDisponibilita.isSelezionabileRicongiungimentoFamiliare() &&
	        selezionabileInfanzia == modelDisponibilita.isSelezionabileInfanzia() &&
	        selezionabileAssistenzaTemporanea == modelDisponibilita.isSelezionabileAssistenzaTemporanea();
	  }
	
	@Override
	  public int hashCode() {
	    return Objects.hash(selezionabile, selezionabileRicongiungimentoFamiliare,selezionabileInfanzia,selezionabileAssistenzaTemporanea);
	  }
	
	@Override
	  public String toString() {
	    StringBuilder sb = new StringBuilder();
	    sb.append("class ModelAsl {\n");
	    
	    sb.append("    selezionabile: ").append(toIndentedString(selezionabile)).append("\n");
	    sb.append("    selezionabileRicongiungimentoFamiliare: ").append(toIndentedString(selezionabileRicongiungimentoFamiliare)).append("\n");
	    sb.append("    selezionabileInfanzia: ").append(toIndentedString(selezionabileInfanzia)).append("\n");
	    sb.append("    selezionabileAssistenzaTemporanea: ").append(toIndentedString(selezionabileAssistenzaTemporanea)).append("\n");
	    sb.append("}");
	    return sb.toString();
	  }
	
	private String toIndentedString(Object o) {
	    if (o == null) {
	      return "null";
	    }
	    return o.toString().replace("\n", "\n    ");
	  }
	
	
}
