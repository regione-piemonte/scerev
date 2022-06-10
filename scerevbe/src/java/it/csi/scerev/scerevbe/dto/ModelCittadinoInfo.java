/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevbe.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public class ModelCittadinoInfo   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [implicit-camel-case] 
  
  private BigDecimal idAura = null;
  private String codiceFiscale = null;
  private String nome = null;
  private String cognome = null;
  private Date dataNascita = null;
  private String sesso = null;
  private Date dataInizioAssistenza = null;
  private Date dataFineAssistenza = null;
  private Date dataInizioAssistenzaMedico = null;
  private String comuneNascita = null;
  private Boolean medicoAltraRegione = null;
  private Date dataRevocaMedico = null;
  private Date dataRevocaAssistenza = null;
  private String statoAssistenza = null;
  private ModelAsl aslAssistenza = null;
  private ModelCittadinanza cittadinanza = null;
  private ModelCittadinoDomicilio domicilio = null;
  private ModelCittadinoResidenza residenza = null;
  private ModelCittadinoRecapiti recapiti = null;
  private ModelCittadinoInfoMedico medico = null;
  private ModelCambioMedicoRichiesta richiestaCambio = null;

  /**
   * L&#39;id dell&#39;assistito su AURA se presente, altrimenti null 
   **/
  


  // nome originario nello yaml: idAura 
  public BigDecimal getIdAura() {
    return idAura;
  }
  public void setIdAura(BigDecimal idAura) {
    this.idAura = idAura;
  }

  /**
   * Il codice fiscale del cittadino
   **/
  


  // nome originario nello yaml: codiceFiscale 
  public String getCodiceFiscale() {
    return codiceFiscale;
  }
  public void setCodiceFiscale(String codiceFiscale) {
    this.codiceFiscale = codiceFiscale;
  }

  /**
   * Nome del cittadino
   **/
  


  // nome originario nello yaml: nome 
  public String getNome() {
    return nome;
  }
  public void setNome(String nome) {
    this.nome = nome;
  }

  /**
   * Cognome del cittadino
   **/
  


  // nome originario nello yaml: cognome 
  public String getCognome() {
    return cognome;
  }
  public void setCognome(String cognome) {
    this.cognome = cognome;
  }

  /**
   * La data di nascita del cittadino
   **/
  


  // nome originario nello yaml: dataNascita 
  public Date getDataNascita() {
    return dataNascita;
  }
  public void setDataNascita(Date dataNascita) {
    this.dataNascita = dataNascita;
  }

  /**
   * Il sesso del cittadino
   **/
  


  // nome originario nello yaml: sesso 
  public String getSesso() {
    return sesso;
  }
  public void setSesso(String sesso) {
    this.sesso = sesso;
  }

  /**
   * La data di inizio dell&#39;assistenza
   **/
  


  // nome originario nello yaml: dataInizioAssistenza 
  public Date getDataInizioAssistenza() {
    return dataInizioAssistenza;
  }
  public void setDataInizioAssistenza(Date dataInizioAssistenza) {
    this.dataInizioAssistenza = dataInizioAssistenza;
  }

  /**
   * La data di fine assistenza o null se l&#39;assistenza � illimitata. Se la data_fine_assistenza � impostata allora si tratta di assistenza temporanea.
   **/
  


  // nome originario nello yaml: dataFineAssistenza 
  public Date getDataFineAssistenza() {
    return dataFineAssistenza;
  }
  public void setDataFineAssistenza(Date dataFineAssistenza) {
    this.dataFineAssistenza = dataFineAssistenza;
  }

  /**
   * La data di inizio assistenza con il medico attuale
   **/
  


  // nome originario nello yaml: dataInizioAssistenzaMedico 
  public Date getDataInizioAssistenzaMedico() {
    return dataInizioAssistenzaMedico;
  }
  public void setDataInizioAssistenzaMedico(Date dataInizioAssistenzaMedico) {
    this.dataInizioAssistenzaMedico = dataInizioAssistenzaMedico;
  }

  /**
   * Il comune di nascita
   **/
  


  // nome originario nello yaml: comuneNascita 
  public String getComuneNascita() {
    return comuneNascita;
  }
  public void setComuneNascita(String comuneNascita) {
    this.comuneNascita = comuneNascita;
  }

  /**
   * true se il cittadino ha un medico in un&#39;altra regione, false altrimenti
   **/
  


  // nome originario nello yaml: medicoAltraRegione 
  public Boolean isMedicoAltraRegione() {
    return medicoAltraRegione;
  }
  public void setMedicoAltraRegione(Boolean medicoAltraRegione) {
    this.medicoAltraRegione = medicoAltraRegione;
  }

  /**
   * La data di reovoca del medico
   **/
  


  // nome originario nello yaml: dataRevocaMedico 
  public Date getDataRevocaMedico() {
    return dataRevocaMedico;
  }
  public void setDataRevocaMedico(Date dataRevocaMedico) {
    this.dataRevocaMedico = dataRevocaMedico;
  }

  /**
   * La data di reovoca assistenza. Se valorizzato vuol dire che l&#39;assistenza � nello stato \&quot;Revocata\&quot;
   **/
  


  // nome originario nello yaml: dataRevocaAssistenza 
  public Date getDataRevocaAssistenza() {
    return dataRevocaAssistenza;
  }
  public void setDataRevocaAssistenza(Date dataRevocaAssistenza) {
    this.dataRevocaAssistenza = dataRevocaAssistenza;
  }

  /**
   * Lo stato dell&#39;assitenza
   **/
  


  // nome originario nello yaml: statoAssistenza 
  public String getStatoAssistenza() {
    return statoAssistenza;
  }
  public void setStatoAssistenza(String statoAssistenza) {
    this.statoAssistenza = statoAssistenza;
  }

  /**
   **/
  


  // nome originario nello yaml: aslAssistenza 
  public ModelAsl getAslAssistenza() {
    return aslAssistenza;
  }
  public void setAslAssistenza(ModelAsl aslAssistenza) {
    this.aslAssistenza = aslAssistenza;
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
  


  // nome originario nello yaml: richiestaCambio 
  public ModelCambioMedicoRichiesta getRichiestaCambio() {
    return richiestaCambio;
  }
  public void setRichiestaCambio(ModelCambioMedicoRichiesta richiestaCambio) {
    this.richiestaCambio = richiestaCambio;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ModelCittadinoInfo modelCittadinoInfo = (ModelCittadinoInfo) o;
    return Objects.equals(idAura, modelCittadinoInfo.idAura) &&
        Objects.equals(codiceFiscale, modelCittadinoInfo.codiceFiscale) &&
        Objects.equals(nome, modelCittadinoInfo.nome) &&
        Objects.equals(cognome, modelCittadinoInfo.cognome) &&
        Objects.equals(dataNascita, modelCittadinoInfo.dataNascita) &&
        Objects.equals(sesso, modelCittadinoInfo.sesso) &&
        Objects.equals(dataInizioAssistenza, modelCittadinoInfo.dataInizioAssistenza) &&
        Objects.equals(dataFineAssistenza, modelCittadinoInfo.dataFineAssistenza) &&
        Objects.equals(dataInizioAssistenzaMedico, modelCittadinoInfo.dataInizioAssistenzaMedico) &&
        Objects.equals(comuneNascita, modelCittadinoInfo.comuneNascita) &&
        Objects.equals(medicoAltraRegione, modelCittadinoInfo.medicoAltraRegione) &&
        Objects.equals(dataRevocaMedico, modelCittadinoInfo.dataRevocaMedico) &&
        Objects.equals(dataRevocaAssistenza, modelCittadinoInfo.dataRevocaAssistenza) &&
        Objects.equals(statoAssistenza, modelCittadinoInfo.statoAssistenza) &&
        Objects.equals(aslAssistenza, modelCittadinoInfo.aslAssistenza) &&
        Objects.equals(cittadinanza, modelCittadinoInfo.cittadinanza) &&
        Objects.equals(domicilio, modelCittadinoInfo.domicilio) &&
        Objects.equals(residenza, modelCittadinoInfo.residenza) &&
        Objects.equals(recapiti, modelCittadinoInfo.recapiti) &&
        Objects.equals(medico, modelCittadinoInfo.medico) &&
        Objects.equals(richiestaCambio, modelCittadinoInfo.richiestaCambio);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idAura, codiceFiscale, nome, cognome, dataNascita, sesso, dataInizioAssistenza, dataFineAssistenza, dataInizioAssistenzaMedico, comuneNascita, medicoAltraRegione, dataRevocaMedico, dataRevocaAssistenza, statoAssistenza, aslAssistenza, cittadinanza, domicilio, residenza, recapiti, medico, richiestaCambio);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ModelCittadinoInfo {\n");
    
    sb.append("    idAura: ").append(toIndentedString(idAura)).append("\n");
    sb.append("    codiceFiscale: ").append(toIndentedString(codiceFiscale)).append("\n");
    sb.append("    nome: ").append(toIndentedString(nome)).append("\n");
    sb.append("    cognome: ").append(toIndentedString(cognome)).append("\n");
    sb.append("    dataNascita: ").append(toIndentedString(dataNascita)).append("\n");
    sb.append("    sesso: ").append(toIndentedString(sesso)).append("\n");
    sb.append("    dataInizioAssistenza: ").append(toIndentedString(dataInizioAssistenza)).append("\n");
    sb.append("    dataFineAssistenza: ").append(toIndentedString(dataFineAssistenza)).append("\n");
    sb.append("    dataInizioAssistenzaMedico: ").append(toIndentedString(dataInizioAssistenzaMedico)).append("\n");
    sb.append("    comuneNascita: ").append(toIndentedString(comuneNascita)).append("\n");
    sb.append("    medicoAltraRegione: ").append(toIndentedString(medicoAltraRegione)).append("\n");
    sb.append("    dataRevocaMedico: ").append(toIndentedString(dataRevocaMedico)).append("\n");
    sb.append("    dataRevocaAssistenza: ").append(toIndentedString(dataRevocaAssistenza)).append("\n");
    sb.append("    statoAssistenza: ").append(toIndentedString(statoAssistenza)).append("\n");
    sb.append("    aslAssistenza: ").append(toIndentedString(aslAssistenza)).append("\n");
    sb.append("    cittadinanza: ").append(toIndentedString(cittadinanza)).append("\n");
    sb.append("    domicilio: ").append(toIndentedString(domicilio)).append("\n");
    sb.append("    residenza: ").append(toIndentedString(residenza)).append("\n");
    sb.append("    recapiti: ").append(toIndentedString(recapiti)).append("\n");
    sb.append("    medico: ").append(toIndentedString(medico)).append("\n");
    sb.append("    richiestaCambio: ").append(toIndentedString(richiestaCambio)).append("\n");
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

