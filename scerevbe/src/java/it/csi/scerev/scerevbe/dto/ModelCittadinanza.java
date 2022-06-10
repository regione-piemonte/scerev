/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevbe.dto;

import java.util.Objects;

public class ModelCittadinanza   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [implicit-camel-case] 
  
  private String id = null;
  private String descrizione = null;
  private Boolean comunitario = null;

  /**
   * Identificativo univoco
   **/
  


  // nome originario nello yaml: id 
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }

  /**
   * descrizione della cittadinanza
   **/
  


  // nome originario nello yaml: descrizione 
  public String getDescrizione() {
    return descrizione;
  }
  public void setDescrizione(String descrizione) {
    this.descrizione = descrizione;
  }

  /**
   * true se si tratta di un Paese comunitario, false altrimenti
   **/
  


  // nome originario nello yaml: comunitario 
  public Boolean isComunitario() {
    return comunitario;
  }
  public void setComunitario(Boolean comunitario) {
    this.comunitario = comunitario;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ModelCittadinanza modelCittadinanza = (ModelCittadinanza) o;
    return Objects.equals(id, modelCittadinanza.id) &&
        Objects.equals(descrizione, modelCittadinanza.descrizione) &&
        Objects.equals(comunitario, modelCittadinanza.comunitario);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, descrizione, comunitario);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ModelCittadinanza {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    descrizione: ").append(toIndentedString(descrizione)).append("\n");
    sb.append("    comunitario: ").append(toIndentedString(comunitario)).append("\n");
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

