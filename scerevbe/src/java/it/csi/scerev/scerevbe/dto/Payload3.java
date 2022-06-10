/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevbe.dto;

import java.util.Objects;

import javax.validation.constraints.NotNull;

public class Payload3   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [implicit-camel-case] 
  
  private ModelAmbulatorioLoccsiMedico medico = null;
  private ModelCittadinanza cittadinanza = null;
  private ModelCittadinoDomicilio domicilio = null;
  private ModelCittadinoResidenza residenza = null;
  private ModelCittadinoRecapiti recapiti = null;
  private ModelCambioMedicoMedicoAltraRegione medicoAltraRegione = null;
  private ModelGeneralitaAssistito generalitaAssistito = null;
  private ModelCambioMedicoRichiestaDatiAggiuntivi datiAggiuntivi = null;

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
  


  // nome originario nello yaml: recapiti 
  public ModelCittadinoRecapiti getRecapiti() {
    return recapiti;
  }
  public void setRecapiti(ModelCittadinoRecapiti recapiti) {
    this.recapiti = recapiti;
  }

  /**
   **/
  


  // nome originario nello yaml: medicoAltraRegione 
  public ModelCambioMedicoMedicoAltraRegione getMedicoAltraRegione() {
    return medicoAltraRegione;
  }
  public void setMedicoAltraRegione(ModelCambioMedicoMedicoAltraRegione medicoAltraRegione) {
    this.medicoAltraRegione = medicoAltraRegione;
  }

  /**
   **/
  


  // nome originario nello yaml: generalitaAssistito 
  public ModelGeneralitaAssistito getGeneralitaAssistito() {
    return generalitaAssistito;
  }
  public void setGeneralitaAssistito(ModelGeneralitaAssistito generalitaAssistito) {
    this.generalitaAssistito = generalitaAssistito;
  }

  /**
   **/
  


  // nome originario nello yaml: datiAggiuntivi 
  public ModelCambioMedicoRichiestaDatiAggiuntivi getDatiAggiuntivi() {
    return datiAggiuntivi;
  }
  public void setDatiAggiuntivi(ModelCambioMedicoRichiestaDatiAggiuntivi datiAggiuntivi) {
    this.datiAggiuntivi = datiAggiuntivi;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Payload3 payload3 = (Payload3) o;
    return Objects.equals(medico, payload3.medico) &&
        Objects.equals(cittadinanza, payload3.cittadinanza) &&
        Objects.equals(domicilio, payload3.domicilio) &&
        Objects.equals(residenza, payload3.residenza) &&
        Objects.equals(recapiti, payload3.recapiti) &&
        Objects.equals(medicoAltraRegione, payload3.medicoAltraRegione) &&
        Objects.equals(generalitaAssistito, payload3.generalitaAssistito) &&
        Objects.equals(datiAggiuntivi, payload3.datiAggiuntivi);
  }

  @Override
  public int hashCode() {
    return Objects.hash(medico, cittadinanza, domicilio, residenza, recapiti, medicoAltraRegione, generalitaAssistito, datiAggiuntivi);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Payload3 {\n");
    
    sb.append("    medico: ").append(toIndentedString(medico)).append("\n");
    sb.append("    cittadinanza: ").append(toIndentedString(cittadinanza)).append("\n");
    sb.append("    domicilio: ").append(toIndentedString(domicilio)).append("\n");
    sb.append("    residenza: ").append(toIndentedString(residenza)).append("\n");
    sb.append("    recapiti: ").append(toIndentedString(recapiti)).append("\n");
    sb.append("    medicoAltraRegione: ").append(toIndentedString(medicoAltraRegione)).append("\n");
    sb.append("    generalitaAssistito: ").append(toIndentedString(generalitaAssistito)).append("\n");
    sb.append("    datiAggiuntivi: ").append(toIndentedString(datiAggiuntivi)).append("\n");
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

