/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevbe.dto;

import java.util.Objects;

import javax.validation.constraints.NotNull;

public class Payload   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [implicit-camel-case] 
  
  private Boolean medicoAltraRegione = null;
  private Boolean rinnovoAssistenza = null;
  private ModelCittadinanza cittadinanza = null;
  private ModelCittadinoDomicilio domicilio = null;
  private ModelCittadinoResidenza residenza = null;
  private ModelAmbulatorioLoccsiMedico medico = null;

  /**
   * true se il cittadino ha un medico in altra regione, false altrimenti
   **/
  


  // nome originario nello yaml: medicoAltraRegione 
  public Boolean isMedicoAltraRegione() {
    return medicoAltraRegione;
  }
  public void setMedicoAltraRegione(Boolean medicoAltraRegione) {
    this.medicoAltraRegione = medicoAltraRegione;
  }

  /**
   **/
  
  
  
  public Boolean getRinnovoAssistenza() {
	  return rinnovoAssistenza;
  }
  public void setRinnovoAssistenza(Boolean rinnovoAssistenza) {
	  this.rinnovoAssistenza = rinnovoAssistenza;
  }
  
  /**
   **/
  


  // nome originario nello yaml: cittadinanza 
  @NotNull
  public ModelCittadinanza getCittadinanza() {
    return cittadinanza;
  }
  public void setCittadinanza(ModelCittadinanza cittadinanza) {
    this.cittadinanza = cittadinanza;
  }

  /**
   **/
  


  // nome originario nello yaml: domicilio 
  @NotNull
  public ModelCittadinoDomicilio getDomicilio() {
    return domicilio;
  }
  public void setDomicilio(ModelCittadinoDomicilio domicilio) {
    this.domicilio = domicilio;
  }

  /**
   **/
  


  // nome originario nello yaml: residenza 
  @NotNull
  public ModelCittadinoResidenza getResidenza() {
    return residenza;
  }
  public void setResidenza(ModelCittadinoResidenza residenza) {
    this.residenza = residenza;
  }

  /**
   **/
  


  // nome originario nello yaml: medico 
  @NotNull
  public ModelAmbulatorioLoccsiMedico getMedico() {
    return medico;
  }
  public void setMedico(ModelAmbulatorioLoccsiMedico medico) {
    this.medico = medico;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Payload payload = (Payload) o;
    return Objects.equals(medicoAltraRegione, payload.medicoAltraRegione) &&
        Objects.equals(rinnovoAssistenza, payload.rinnovoAssistenza) &&
        Objects.equals(cittadinanza, payload.cittadinanza) &&
        Objects.equals(domicilio, payload.domicilio) &&
        Objects.equals(residenza, payload.residenza) &&
        Objects.equals(medico, payload.medico);
  }

  @Override
  public int hashCode() {
    return Objects.hash(medicoAltraRegione, rinnovoAssistenza, cittadinanza, domicilio, residenza, medico);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Payload {\n");
    
    sb.append("    medicoAltraRegione: ").append(toIndentedString(medicoAltraRegione)).append("\n");
    sb.append("    rinnovoAssistenza: ").append(toIndentedString(rinnovoAssistenza)).append("\n");
    sb.append("    cittadinanza: ").append(toIndentedString(cittadinanza)).append("\n");
    sb.append("    domicilio: ").append(toIndentedString(domicilio)).append("\n");
    sb.append("    residenza: ").append(toIndentedString(residenza)).append("\n");
    sb.append("    medico: ").append(toIndentedString(medico)).append("\n");
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

