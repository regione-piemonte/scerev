/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevbe.dto;

import java.util.Objects;

public class ModelMedicoAmbito   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [implicit-camel-case] 
  
  private String ambitoCodice = null;
  private String ambitoDescrizione = null;

  /**
   * Il codice dell&#39;ambito del medico
   **/
  


  // nome originario nello yaml: ambitoCodice 
  public String getAmbitoCodice() {
    return ambitoCodice;
  }
  public void setAmbitoCodice(String ambitoCodice) {
    this.ambitoCodice = ambitoCodice;
  }

  /**
   * Una descrizione testuale dell&#39;ambito del medico
   **/
  


  // nome originario nello yaml: ambitoDescrizione 
  public String getAmbitoDescrizione() {
    return ambitoDescrizione;
  }
  public void setAmbitoDescrizione(String ambitoDescrizione) {
    this.ambitoDescrizione = ambitoDescrizione;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ModelMedicoAmbito modelMedicoAmbito = (ModelMedicoAmbito) o;
    return Objects.equals(ambitoCodice, modelMedicoAmbito.ambitoCodice) &&
        Objects.equals(ambitoDescrizione, modelMedicoAmbito.ambitoDescrizione);
  }

  @Override
  public int hashCode() {
    return Objects.hash(ambitoCodice, ambitoDescrizione);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ModelMedicoAmbito {\n");
    
    sb.append("    ambitoCodice: ").append(toIndentedString(ambitoCodice)).append("\n");
    sb.append("    ambitoDescrizione: ").append(toIndentedString(ambitoDescrizione)).append("\n");
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

