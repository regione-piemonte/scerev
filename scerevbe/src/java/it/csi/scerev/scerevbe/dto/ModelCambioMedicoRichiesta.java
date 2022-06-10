/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevbe.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.codehaus.jackson.annotate.JsonValue;

public class ModelCambioMedicoRichiesta   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [implicit-camel-case] 
  
  private String id = null;

  /**
   * Indica la tipologia di richiesta
   */
  public enum TipologiaEnum {
    BOZZA("BOZZA"),

        AUTOMATICA("AUTOMATICA"),

        BACK_OFFICE("BACK_OFFICE");
    private String value;

    TipologiaEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }
  }

  private TipologiaEnum tipologia = null;
  private Boolean bozza = null;
  private Boolean annullabile = null;
  private Boolean rettificabile = null;
  private Boolean rinnovoAssistenza = null;
  private ModelCambioMedicoRichiestaStato stato = null;
  private ModelCittadinanza cittadinanza = null;
  private ModelCittadinoDomicilio domicilio = null;
  private ModelCittadinoResidenza residenza = null;
  private ModelCittadinoRecapiti recapiti = null;
  private ModelCittadinoInfoMedico medico = null;
  private ModelCambioMedicoMedicoAltraRegione medicoAltraRegione = null;
  private ModelCambioMedicoRichiestaDatiAggiuntivi datiAggiuntivi = null;
  private List<ModelDocumentoLettura> allegati = new ArrayList<ModelDocumentoLettura>();
  private List<ModelCambioMedicoRichiestaChatOperatore> chatOperatore = new ArrayList<ModelCambioMedicoRichiestaChatOperatore>();

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
   * Indica la tipologia di richiesta
   **/
  


  // nome originario nello yaml: tipologia 
  public TipologiaEnum getTipologia() {
    return tipologia;
  }
  public void setTipologia(TipologiaEnum tipologia) {
    this.tipologia = tipologia;
  }

  /**
   * true se si tratta di una bozza, false altrimenti  Permette di distinguere tra bozza e richiesta pendente 
   **/
  


  // nome originario nello yaml: bozza 
  public Boolean isBozza() {
    return bozza;
  }
  public void setBozza(Boolean bozza) {
    this.bozza = bozza;
  }

  /**
   * true se la richiesta è annullabile, false altrimenti  Permette al front-end di mostrare o meno il bottone \&quot;annulla\&quot; sulla richiesta del medico. 
   **/
  


  // nome originario nello yaml: annullabile 
  public Boolean isAnnullabile() {
    return annullabile;
  }
  public void setAnnullabile(Boolean annullabile) {
    this.annullabile = annullabile;
  }

  /**
   * true se è possibile rettificare la richiesta, false altrimenti  Permette al front-end di mostrare o meno il bottone \&quot;rettifica\&quot; sulla richiesta del medico. 
   **/
  


  // nome originario nello yaml: rettificabile 
  public Boolean isRettificabile() {
    return rettificabile;
  }
  public void setRettificabile(Boolean rettificabile) {
    this.rettificabile = rettificabile;
  }

  /**
   * 
   **/
  
  
  
  public Boolean getRinnovoAssistenza() {
	  return rinnovoAssistenza;
  }
  
  public void setRinnovoAssistenza(Boolean rinnovoAssistenza) {
	  this.rinnovoAssistenza = rinnovoAssistenza;
  }
  
  /**
   **/
  


  // nome originario nello yaml: stato 
  public ModelCambioMedicoRichiestaStato getStato() {
    return stato;
  }
  public void setStato(ModelCambioMedicoRichiestaStato stato) {
    this.stato = stato;
  }

  /**
   **/
  


  // nome originario nello yaml: cittadinanza 
  public ModelCittadinanza getCittadinanza() {
    return cittadinanza;
  }
  public void setCittadinanza(ModelCittadinanza cittadinanza) {
    this.cittadinanza = cittadinanza;
  }

  /**
   **/
  


  // nome originario nello yaml: domicilio 
  public ModelCittadinoDomicilio getDomicilio() {
    return domicilio;
  }
  public void setDomicilio(ModelCittadinoDomicilio domicilio) {
    this.domicilio = domicilio;
  }

  /**
   **/
  


  // nome originario nello yaml: residenza 
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
  


  // nome originario nello yaml: medico 
  public ModelCittadinoInfoMedico getMedico() {
    return medico;
  }
  public void setMedico(ModelCittadinoInfoMedico medico) {
    this.medico = medico;
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
  


  // nome originario nello yaml: datiAggiuntivi 
  public ModelCambioMedicoRichiestaDatiAggiuntivi getDatiAggiuntivi() {
    return datiAggiuntivi;
  }
  public void setDatiAggiuntivi(ModelCambioMedicoRichiestaDatiAggiuntivi datiAggiuntivi) {
    this.datiAggiuntivi = datiAggiuntivi;
  }

  /**
   **/
  


  // nome originario nello yaml: allegati 
  public List<ModelDocumentoLettura> getAllegati() {
    return allegati;
  }
  public void setAllegati(List<ModelDocumentoLettura> allegati) {
    this.allegati = allegati;
  }

  /**
   **/
  


  // nome originario nello yaml: chatOperatore 
  public List<ModelCambioMedicoRichiestaChatOperatore> getChatOperatore() {
    return chatOperatore;
  }
  public void setChatOperatore(List<ModelCambioMedicoRichiestaChatOperatore> chatOperatore) {
    this.chatOperatore = chatOperatore;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ModelCambioMedicoRichiesta modelCambioMedicoRichiesta = (ModelCambioMedicoRichiesta) o;
    return Objects.equals(id, modelCambioMedicoRichiesta.id) &&
        Objects.equals(tipologia, modelCambioMedicoRichiesta.tipologia) &&
        Objects.equals(bozza, modelCambioMedicoRichiesta.bozza) &&
        Objects.equals(annullabile, modelCambioMedicoRichiesta.annullabile) &&
        Objects.equals(rettificabile, modelCambioMedicoRichiesta.rettificabile) &&
        Objects.equals(rinnovoAssistenza, modelCambioMedicoRichiesta.rinnovoAssistenza) &&
        Objects.equals(stato, modelCambioMedicoRichiesta.stato) &&
        Objects.equals(cittadinanza, modelCambioMedicoRichiesta.cittadinanza) &&
        Objects.equals(domicilio, modelCambioMedicoRichiesta.domicilio) &&
        Objects.equals(residenza, modelCambioMedicoRichiesta.residenza) &&
        Objects.equals(recapiti, modelCambioMedicoRichiesta.recapiti) &&
        Objects.equals(medico, modelCambioMedicoRichiesta.medico) &&
        Objects.equals(medicoAltraRegione, modelCambioMedicoRichiesta.medicoAltraRegione) &&
        Objects.equals(datiAggiuntivi, modelCambioMedicoRichiesta.datiAggiuntivi) &&
        Objects.equals(allegati, modelCambioMedicoRichiesta.allegati) &&
        Objects.equals(chatOperatore, modelCambioMedicoRichiesta.chatOperatore);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, tipologia, bozza, annullabile, rettificabile, rinnovoAssistenza, stato, cittadinanza, domicilio, residenza, recapiti, medico, medicoAltraRegione, datiAggiuntivi, allegati, chatOperatore);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ModelCambioMedicoRichiesta {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    tipologia: ").append(toIndentedString(tipologia)).append("\n");
    sb.append("    bozza: ").append(toIndentedString(bozza)).append("\n");
    sb.append("    annullabile: ").append(toIndentedString(annullabile)).append("\n");
    sb.append("    rettificabile: ").append(toIndentedString(rettificabile)).append("\n");
    sb.append("    rinnovoAssistenza: ").append(toIndentedString(rinnovoAssistenza)).append("\n");
    sb.append("    stato: ").append(toIndentedString(stato)).append("\n");
    sb.append("    cittadinanza: ").append(toIndentedString(cittadinanza)).append("\n");
    sb.append("    domicilio: ").append(toIndentedString(domicilio)).append("\n");
    sb.append("    residenza: ").append(toIndentedString(residenza)).append("\n");
    sb.append("    recapiti: ").append(toIndentedString(recapiti)).append("\n");
    sb.append("    medico: ").append(toIndentedString(medico)).append("\n");
    sb.append("    medicoAltraRegione: ").append(toIndentedString(medicoAltraRegione)).append("\n");
    sb.append("    datiAggiuntivi: ").append(toIndentedString(datiAggiuntivi)).append("\n");
    sb.append("    allegati: ").append(toIndentedString(allegati)).append("\n");
    sb.append("    chatOperatore: ").append(toIndentedString(chatOperatore)).append("\n");
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

