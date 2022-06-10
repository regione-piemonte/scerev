/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevbe.dto;

import java.util.Objects;

public class Payload2   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [implicit-camel-case] 
  
  private String note = null;
  private ModelAllegati allegati = null;

  /**
   * Eventuali note che l&#39;utente inserisce per comunicare con l&#39;operatore
   **/
  


  // nome originario nello yaml: note 
  public String getNote() {
    return note;
  }
  public void setNote(String note) {
    this.note = note;
  }

  /**
   **/
  


  // nome originario nello yaml: allegati 
  public ModelAllegati getAllegati() {
    return allegati;
  }
  public void setAllegati(ModelAllegati allegati) {
    this.allegati = allegati;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Payload2 payload2 = (Payload2) o;
    return Objects.equals(note, payload2.note) &&
        Objects.equals(allegati, payload2.allegati);
  }

  @Override
  public int hashCode() {
    return Objects.hash(note, allegati);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Payload2 {\n");
    
    sb.append("    note: ").append(toIndentedString(note)).append("\n");
    sb.append("    allegati: ").append(toIndentedString(allegati)).append("\n");
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

