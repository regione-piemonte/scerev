/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevbe.dto;

import java.util.Date;
import java.util.Objects;

public class ModelCambioMedicoRichiestaChatOperatore   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [implicit-camel-case] 
  
  private Boolean cittadino = null;
  private Date data = null;
  private String testo = null;

  /**
   * true se il messaggio/nota appartiene al cittadino, false altrimenti
   **/
  


  // nome originario nello yaml: cittadino 
  public Boolean isCittadino() {
    return cittadino;
  }
  public void setCittadino(Boolean cittadino) {
    this.cittadino = cittadino;
  }

  /**
   * La data in cui � stato mandato il messaggio/nota
   **/
  


  // nome originario nello yaml: data 
  public Date getData() {
    return data;
  }
  public void setData(Date data) {
    this.data = data;
  }

  /**
   * Il testo della nota
   **/
  


  // nome originario nello yaml: testo 
  public String getTesto() {
    return testo;
  }
  public void setTesto(String testo) {
    this.testo = testo;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ModelCambioMedicoRichiestaChatOperatore modelCambioMedicoRichiestaChatOperatore = (ModelCambioMedicoRichiestaChatOperatore) o;
    return Objects.equals(cittadino, modelCambioMedicoRichiestaChatOperatore.cittadino) &&
        Objects.equals(data, modelCambioMedicoRichiestaChatOperatore.data) &&
        Objects.equals(testo, modelCambioMedicoRichiestaChatOperatore.testo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(cittadino, data, testo);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ModelCambioMedicoRichiestaChatOperatore {\n");
    
    sb.append("    cittadino: ").append(toIndentedString(cittadino)).append("\n");
    sb.append("    data: ").append(toIndentedString(data)).append("\n");
    sb.append("    testo: ").append(toIndentedString(testo)).append("\n");
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
}

