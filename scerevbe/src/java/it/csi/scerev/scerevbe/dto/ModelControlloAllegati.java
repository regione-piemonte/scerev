/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevbe.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ModelControlloAllegati   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [implicit-camel-case] 
  
  private List<ModelAllegatoTipo> allegati = new ArrayList<ModelAllegatoTipo>();
  private Boolean assistenzaTemporaneaStranieri = null;

  /**
   **/
  


  // nome originario nello yaml: allegati 
  public List<ModelAllegatoTipo> getAllegati() {
    return allegati;
  }
  public void setAllegati(List<ModelAllegatoTipo> allegati) {
    this.allegati = allegati;
  }

  /**
   * true se l&#39;assistenza è temporanea, false se illimitata.  Se true il front-end verificherà i massimali dei temporanei. 
   **/
  


  // nome originario nello yaml: assistenzaTemporaneaStranieri 
  public Boolean isAssistenzaTemporaneaStranieri() {
    return assistenzaTemporaneaStranieri;
  }
  public void setAssistenzaTemporaneaStranieri(Boolean assistenzaTemporaneaStranieri) {
    this.assistenzaTemporaneaStranieri = assistenzaTemporaneaStranieri;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ModelControlloAllegati modelControlloAllegati = (ModelControlloAllegati) o;
    return Objects.equals(allegati, modelControlloAllegati.allegati) &&
        Objects.equals(assistenzaTemporaneaStranieri, modelControlloAllegati.assistenzaTemporaneaStranieri);
  }

  @Override
  public int hashCode() {
    return Objects.hash(allegati, assistenzaTemporaneaStranieri);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ModelControlloAllegati {\n");
    
    sb.append("    allegati: ").append(toIndentedString(allegati)).append("\n");
    sb.append("    assistenzaTemporaneaStranieri: ").append(toIndentedString(assistenzaTemporaneaStranieri)).append("\n");
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

