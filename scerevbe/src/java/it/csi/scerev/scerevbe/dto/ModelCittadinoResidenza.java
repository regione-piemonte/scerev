/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevbe.dto;

import java.util.Objects;

public class ModelCittadinoResidenza   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [implicit-camel-case] 
  
  private String nazione = null;
  private String regione = null;
  private String comune = null;
  private String codComune = null;
  private String cap = null;
  private String indirizzo = null;
  private String civico = null;

  /**
   * La nazione inserita dall&#39;utente
   **/
  


  // nome originario nello yaml: nazione 
  public String getNazione() {
    return nazione;
  }
  public void setNazione(String nazione) {
    this.nazione = nazione;
  }

  /**
   * Il nome della regione inserito dall&#39;utente
   **/
  


  // nome originario nello yaml: regione 
  public String getRegione() {
    return regione;
  }
  public void setRegione(String regione) {
    this.regione = regione;
  }

  /**
   * Il nome del comune inserito dall&#39;utente
   **/
  


  // nome originario nello yaml: comune 
  public String getComune() {
    return comune;
  }
  public void setComune(String comune) {
    this.comune = comune;
  }

  /**
   * codice istat comune
   **/
  


  // nome originario nello yaml: codComune 
  public String getCodComune() {
    return codComune;
  }
  public void setCodComune(String codComune) {
    this.codComune = codComune;
  }

  /**
   * Il CAP inserito dall&#39;uente
   **/
  


  // nome originario nello yaml: cap 
  public String getCap() {
    return cap;
  }
  public void setCap(String cap) {
    this.cap = cap;
  }

  /**
   * L&#39;indirizzo inserito dall&#39;utente
   **/
  


  // nome originario nello yaml: indirizzo 
  public String getIndirizzo() {
    return indirizzo;
  }
  public void setIndirizzo(String indirizzo) {
    this.indirizzo = indirizzo;
  }

  /**
   * Il numero civico inserito dall&#39;utente
   **/
  


  // nome originario nello yaml: civico 
  public String getCivico() {
    return civico;
  }
  public void setCivico(String civico) {
    this.civico = civico;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ModelCittadinoResidenza modelCittadinoResidenza = (ModelCittadinoResidenza) o;
    return Objects.equals(nazione, modelCittadinoResidenza.nazione) &&
        Objects.equals(regione, modelCittadinoResidenza.regione) &&
        Objects.equals(comune, modelCittadinoResidenza.comune) &&
        Objects.equals(codComune, modelCittadinoResidenza.codComune) &&
        Objects.equals(cap, modelCittadinoResidenza.cap) &&
        Objects.equals(indirizzo, modelCittadinoResidenza.indirizzo) &&
        Objects.equals(civico, modelCittadinoResidenza.civico);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nazione, regione, comune, codComune, cap, indirizzo, civico);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ModelCittadinoResidenza {\n");
    
    sb.append("    nazione: ").append(toIndentedString(nazione)).append("\n");
    sb.append("    regione: ").append(toIndentedString(regione)).append("\n");
    sb.append("    comune: ").append(toIndentedString(comune)).append("\n");
    sb.append("    codComune: ").append(toIndentedString(codComune)).append("\n");
    sb.append("    cap: ").append(toIndentedString(cap)).append("\n");
    sb.append("    indirizzo: ").append(toIndentedString(indirizzo)).append("\n");
    sb.append("    civico: ").append(toIndentedString(civico)).append("\n");
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

