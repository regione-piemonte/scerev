/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevboweb.dto;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Objects;

import org.codehaus.jackson.annotate.JsonProperty;

import it.csi.scerev.scerevjpa.business.entity.ScerevDPraticaStato;
import it.csi.scerev.scerevjpa.business.entity.ScerevTCronologia;

public class ModelCronologia   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
	private String dataOraInserimento = null;
	private String utente = null;
	private String statoRichiesta = null;
	private String notaRichiedente = null;
	private String notaInterna = null;



  @JsonProperty("dataOraInserimento") 
 
  public String getDataOraInserimento() {
    return dataOraInserimento;
  }
  public void setDataOraInserimento(Timestamp dataOraInserimento) {
    this.dataOraInserimento = dataOraInserimento.toString();
  }
  
  

  @JsonProperty("utente") 
 
  public String getUtente() {
    return utente;
  }
  public void setUtente(String utente) {
    this.utente = utente;
  }

  

  @JsonProperty("statoRichiesta") 
  
  public String getStatoRichiesta() {
	    return statoRichiesta;
	  }
	  public void setStatoRichiesta(ScerevDPraticaStato statoRichiesta) {
	    this.statoRichiesta = statoRichiesta.getValorePraticaStato();
	  }
  
	  
  
  @JsonProperty("notaRichiedente") 
  
  public String getNotaRichiedente() {
	    return notaRichiedente;
	  }
	  public void setNotaRichiedente(String notaRichiedente) {
	    this.notaRichiedente = notaRichiedente;
	  }
  
  
  @JsonProperty("notaInterna") 
  
  public String getNotaInterna() {
	    return notaInterna;
	  }
	  public void setNotaInterna(String notaInterna) {
	    this.notaInterna = notaInterna;
	  }

	  
	  
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ModelCronologia modelCronologia = (ModelCronologia) o;
    return Objects.equals(dataOraInserimento, modelCronologia.dataOraInserimento) &&
        Objects.equals(utente, modelCronologia.utente) &&
        Objects.equals(statoRichiesta, modelCronologia.statoRichiesta) &&
        Objects.equals(notaRichiedente, modelCronologia.notaRichiedente) &&
        Objects.equals(notaInterna, modelCronologia.notaInterna);
  }

  @Override
  public int hashCode() {
    return Objects.hash(dataOraInserimento, utente, statoRichiesta, notaRichiedente, notaInterna);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ModelCronologia {\n");
    sb.append("    dataOraInserimento: ").append(toIndentedString(dataOraInserimento)).append("\n");
    sb.append("    utente: ").append(toIndentedString(utente)).append("\n");
    sb.append("    statoRichiesta: ").append(toIndentedString(statoRichiesta)).append("\n");
    sb.append("    notaRichiedente: ").append(toIndentedString(notaRichiedente)).append("\n");
    sb.append("    notaInterna: ").append(toIndentedString(notaInterna)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
  
  public ModelCronologia(ScerevTCronologia dbCronologia) {
	  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	  this.dataOraInserimento = sdf.format(dbCronologia.getDataOraInserimento());
	  this.utente = dbCronologia.getUtente();
	  this.statoRichiesta = dbCronologia.getScerevDPraticaStato().getValorePraticaStato();
	  this.notaRichiedente = dbCronologia.getNotaRichiedente();
	  this.notaInterna =  dbCronologia.getNotaInterna();
  }
  
}

