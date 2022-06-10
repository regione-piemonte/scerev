/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevbe.dto;

import java.util.Objects;

public class ModelCittadinoRecapiti   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [implicit-camel-case] 
  
  private String telefono = null;
  private String telefonoSecondario = null;
  private String indirizzoEmail = null;

  /**
   * Il numero di telefono inserito dall&#39;utente
   **/
  


  // nome originario nello yaml: telefono 
  public String getTelefono() {
    return telefono;
  }
  public void setTelefono(String telefono) {
    this.telefono = telefono;
  }

  /**
   * Il numero di telefono secondario inserito dall&#39;utente
   **/
  


  // nome originario nello yaml: telefonoSecondario 
  public String getTelefonoSecondario() {
    return telefonoSecondario;
  }
  public void setTelefonoSecondario(String telefonoSecondario) {
    this.telefonoSecondario = telefonoSecondario;
  }

  /**
   * L&#39;indirizzo email inserito dall&#39;utente
   **/
  


  // nome originario nello yaml: indirizzoEmail 
  public String getIndirizzoEmail() {
    return indirizzoEmail;
  }
  public void setIndirizzoEmail(String indirizzoEmail) {
    this.indirizzoEmail = indirizzoEmail;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ModelCittadinoRecapiti modelCittadinoRecapiti = (ModelCittadinoRecapiti) o;
    return Objects.equals(telefono, modelCittadinoRecapiti.telefono) &&
        Objects.equals(telefonoSecondario, modelCittadinoRecapiti.telefonoSecondario) &&
        Objects.equals(indirizzoEmail, modelCittadinoRecapiti.indirizzoEmail);
  }

  @Override
  public int hashCode() {
    return Objects.hash(telefono, telefonoSecondario, indirizzoEmail);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ModelCittadinoRecapiti {\n");
    
    sb.append("    telefono: ").append(toIndentedString(telefono)).append("\n");
    sb.append("    telefonoSecondario: ").append(toIndentedString(telefonoSecondario)).append("\n");
    sb.append("    indirizzoEmail: ").append(toIndentedString(indirizzoEmail)).append("\n");
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

