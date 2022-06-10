/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevbe.dto;

import java.util.Objects;

public class ModelMotivazioneSoggiorno   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [implicit-camel-case] 
  
  private String id = null;
  private String descrizione = null;
  private Boolean cittadiniComunitari = null;

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
   * testo della motivazione
   **/
  


  // nome originario nello yaml: descrizione 
  public String getDescrizione() {
    return descrizione;
  }
  public void setDescrizione(String descrizione) {
    this.descrizione = descrizione;
  }

  /**
   * true se la motivazione di soggiorno è per cittadini comunitari, false altrimenti
   **/
  


  // nome originario nello yaml: cittadini_comunitari 
  public Boolean isCittadiniComunitari() {
    return cittadiniComunitari;
  }
  public void setCittadiniComunitari(Boolean cittadiniComunitari) {
    this.cittadiniComunitari = cittadiniComunitari;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ModelMotivazioneSoggiorno modelMotivazioneSoggiorno = (ModelMotivazioneSoggiorno) o;
    return Objects.equals(id, modelMotivazioneSoggiorno.id) &&
        Objects.equals(descrizione, modelMotivazioneSoggiorno.descrizione) &&
        Objects.equals(cittadiniComunitari, modelMotivazioneSoggiorno.cittadiniComunitari);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, descrizione, cittadiniComunitari);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ModelMotivazioneSoggiorno {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    descrizione: ").append(toIndentedString(descrizione)).append("\n");
    sb.append("    cittadiniComunitari: ").append(toIndentedString(cittadiniComunitari)).append("\n");
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

