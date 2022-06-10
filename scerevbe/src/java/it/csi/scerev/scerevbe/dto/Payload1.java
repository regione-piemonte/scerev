/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevbe.dto;

import java.util.Objects;

import javax.validation.constraints.NotNull;

public class Payload1   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [implicit-camel-case] 
  
  private Boolean bozza = null;
  private Boolean rinnovoAssistenza = null;
  private ModelControlloCombinazione controlloCombinazione = null;
  private ModelAmbulatorioLoccsiMedico medico = null;
  private ModelCittadinanza cittadinanza = null;
  private ModelCittadinoDomicilio domicilio = null;
  private ModelCittadinoResidenza residenza = null;
  private ModelCittadinoRecapiti recapiti = null;
  private ModelCambioMedicoMedicoAltraRegione medicoAltraRegione = null;
  private ModelCambioMedicoRichiestaDatiAggiuntivi datiAggiuntivi = null;
  private ModelDelegato delegato = null;
  private ModelAllegati allegati = null;

  /**
   * true se si tratta di una bozza, false altrimenti
   **/
  


  // nome originario nello yaml: bozza 
  @NotNull
  public Boolean isBozza() {
    return bozza;
  }
  public void setBozza(Boolean bozza) {
    this.bozza = bozza;
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
  
  
  
  // nome originario nello yaml: controlloCombinazione 
  @NotNull
  public ModelControlloCombinazione getControlloCombinazione() {
    return controlloCombinazione;
  }
  public void setControlloCombinazione(ModelControlloCombinazione controlloCombinazione) {
    this.controlloCombinazione = controlloCombinazione;
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
  @NotNull
  public ModelCambioMedicoMedicoAltraRegione getMedicoAltraRegione() {
    return medicoAltraRegione;
  }
  public void setMedicoAltraRegione(ModelCambioMedicoMedicoAltraRegione medicoAltraRegione) {
    this.medicoAltraRegione = medicoAltraRegione;
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

  /**
   **/
  


  // nome originario nello yaml: delegato 
  public ModelDelegato getDelegato() {
    return delegato;
  }
  public void setDelegato(ModelDelegato delegato) {
    this.delegato = delegato;
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
    Payload1 payload1 = (Payload1) o;
    return Objects.equals(bozza, payload1.bozza) && 
        Objects.equals(rinnovoAssistenza, payload1.rinnovoAssistenza) &&
        Objects.equals(controlloCombinazione, payload1.controlloCombinazione) &&
        Objects.equals(medico, payload1.medico) &&
        Objects.equals(cittadinanza, payload1.cittadinanza) &&
        Objects.equals(domicilio, payload1.domicilio) &&
        Objects.equals(residenza, payload1.residenza) &&
        Objects.equals(recapiti, payload1.recapiti) &&
        Objects.equals(medicoAltraRegione, payload1.medicoAltraRegione) &&
        Objects.equals(datiAggiuntivi, payload1.datiAggiuntivi) &&
        Objects.equals(delegato, payload1.delegato) &&
        Objects.equals(allegati, payload1.allegati);
  }

  @Override
  public int hashCode() {
    return Objects.hash(bozza, rinnovoAssistenza, controlloCombinazione, medico, cittadinanza, domicilio, residenza, recapiti, medicoAltraRegione, datiAggiuntivi, delegato, allegati);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Payload1 {\n");
    
    sb.append("    bozza: ").append(toIndentedString(bozza)).append("\n");
    sb.append("    rinnovoAssistenza: ").append(toIndentedString(rinnovoAssistenza)).append("\n");
    sb.append("    controlloCombinazione: ").append(toIndentedString(controlloCombinazione)).append("\n");
    sb.append("    medico: ").append(toIndentedString(medico)).append("\n");
    sb.append("    cittadinanza: ").append(toIndentedString(cittadinanza)).append("\n");
    sb.append("    domicilio: ").append(toIndentedString(domicilio)).append("\n");
    sb.append("    residenza: ").append(toIndentedString(residenza)).append("\n");
    sb.append("    recapiti: ").append(toIndentedString(recapiti)).append("\n");
    sb.append("    medicoAltraRegione: ").append(toIndentedString(medicoAltraRegione)).append("\n");
    sb.append("    datiAggiuntivi: ").append(toIndentedString(datiAggiuntivi)).append("\n");
    sb.append("    delegato: ").append(toIndentedString(delegato)).append("\n");
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

