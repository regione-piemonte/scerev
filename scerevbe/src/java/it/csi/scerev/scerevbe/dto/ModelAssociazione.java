/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevbe.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ModelAssociazione   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [implicit-camel-case] 
  
  private String id = null;
  private String nome = null;
  private String cap = null;
  private String comune = null;
  private String indirizzo = null;
  private String numeroCivico = null;
  private String telefono = null;
  private List<ModelAssociazioneMedici> medici = new ArrayList<ModelAssociazioneMedici>();

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
   * Il nome dell&#39;associazione
   **/
  


  // nome originario nello yaml: nome 
  public String getNome() {
    return nome;
  }
  public void setNome(String nome) {
    this.nome = nome;
  }

  /**
   * Il CAP dell&#39;associazione
   **/
  


  // nome originario nello yaml: cap 
  public String getCap() {
    return cap;
  }
  public void setCap(String cap) {
    this.cap = cap;
  }

  /**
   * Il comune dell&#39;associazione
   **/
  


  // nome originario nello yaml: comune 
  public String getComune() {
    return comune;
  }
  public void setComune(String comune) {
    this.comune = comune;
  }

  /**
   * L&#39;indirizzo dell&#39;associazione
   **/
  


  // nome originario nello yaml: indirizzo 
  public String getIndirizzo() {
    return indirizzo;
  }
  public void setIndirizzo(String indirizzo) {
    this.indirizzo = indirizzo;
  }

  /**
   * Il numero civico dell&#39;associazione
   **/
  


  // nome originario nello yaml: numeroCivico 
  public String getNumeroCivico() {
    return numeroCivico;
  }
  public void setNumeroCivico(String numeroCivico) {
    this.numeroCivico = numeroCivico;
  }

  /**
   * Il telefono dell&#39;associazione
   **/
  


  // nome originario nello yaml: telefono 
  public String getTelefono() {
    return telefono;
  }
  public void setTelefono(String telefono) {
    this.telefono = telefono;
  }

  /**
   **/
  


  // nome originario nello yaml: medici 
  public List<ModelAssociazioneMedici> getMedici() {
    return medici;
  }
  public void setMedici(List<ModelAssociazioneMedici> medici) {
    this.medici = medici;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ModelAssociazione modelAssociazione = (ModelAssociazione) o;
    return Objects.equals(id, modelAssociazione.id) &&
        Objects.equals(nome, modelAssociazione.nome) &&
        Objects.equals(cap, modelAssociazione.cap) &&
        Objects.equals(comune, modelAssociazione.comune) &&
        Objects.equals(indirizzo, modelAssociazione.indirizzo) &&
        Objects.equals(numeroCivico, modelAssociazione.numeroCivico) &&
        Objects.equals(telefono, modelAssociazione.telefono) &&
        Objects.equals(medici, modelAssociazione.medici);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, nome, cap, comune, indirizzo, numeroCivico, telefono, medici);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ModelAssociazione {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    nome: ").append(toIndentedString(nome)).append("\n");
    sb.append("    cap: ").append(toIndentedString(cap)).append("\n");
    sb.append("    comune: ").append(toIndentedString(comune)).append("\n");
    sb.append("    indirizzo: ").append(toIndentedString(indirizzo)).append("\n");
    sb.append("    numeroCivico: ").append(toIndentedString(numeroCivico)).append("\n");
    sb.append("    telefono: ").append(toIndentedString(telefono)).append("\n");
    sb.append("    medici: ").append(toIndentedString(medici)).append("\n");
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

