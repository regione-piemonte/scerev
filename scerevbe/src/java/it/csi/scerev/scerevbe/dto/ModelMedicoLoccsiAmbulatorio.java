/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevbe.dto;

import java.util.Objects;

import org.codehaus.jackson.annotate.JsonProperty;

public class ModelMedicoLoccsiAmbulatorio   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private Integer id = null;
  private String provincia = null;
  private String comune = null;
  private String cap = null;
  private String indirizzo = null;
  private String telefono = null;
  private String email = null;
  private String note = null;
  private Integer rapportoLavoroId = null;
  private ModelAmbulatorioTipo tipo = null;
  private GeoJsonPunto coordinate = null;
  private ModelAmbulatorioLoccsiMedico medico = null;

  /**
   * Identificativo univoco
   **/
  

  @JsonProperty("id") 
 
  public Integer getId() {
    return id;
  }
  public void setId(Integer id) {
    this.id = id;
  }

  /**
   * La provincia dell&#39;ambulatorio
   **/
  

  @JsonProperty("provincia") 
 
  public String getProvincia() {
    return provincia;
  }
  public void setProvincia(String provincia) {
    this.provincia = provincia;
  }

  /**
   * Il comune dell&#39;ambulatorio
   **/
  

  @JsonProperty("comune") 
 
  public String getComune() {
    return comune;
  }
  public void setComune(String comune) {
    this.comune = comune;
  }

  /**
   * Il CAP dell&#39;ambulatorio
   **/
  

  @JsonProperty("cap") 
 
  public String getCap() {
    return cap;
  }
  public void setCap(String cap) {
    this.cap = cap;
  }

  /**
   * Indirizzo dell&#39;ambulatorio
   **/
  

  @JsonProperty("indirizzo") 
 
  public String getIndirizzo() {
    return indirizzo;
  }
  public void setIndirizzo(String indirizzo) {
    this.indirizzo = indirizzo;
  }

  /**
   * Il telefono dell&#39;ambulatorio
   **/
  

  @JsonProperty("telefono") 
 
  public String getTelefono() {
    return telefono;
  }
  public void setTelefono(String telefono) {
    this.telefono = telefono;
  }

  /**
   * E-mail dell&#39;ambulatorio
   **/
  

  @JsonProperty("email") 
 
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * Note dell&#39;ambulatorio
   **/
  

  @JsonProperty("note") 
 
  public String getNote() {
    return note;
  }
  public void setNote(String note) {
    this.note = note;
  }

  /**
   **/
  

  @JsonProperty("rapporto_lavoro_id") 
 
  public Integer getRapportoLavoroId() {
    return rapportoLavoroId;
  }
  public void setRapportoLavoroId(Integer rapportoLavoroId) {
    this.rapportoLavoroId = rapportoLavoroId;
  }

  /**
   **/
  

  @JsonProperty("tipo") 
 
  public ModelAmbulatorioTipo getTipo() {
    return tipo;
  }
  public void setTipo(ModelAmbulatorioTipo tipo) {
    this.tipo = tipo;
  }

  /**
   **/
  

  @JsonProperty("coordinate") 
 
  public GeoJsonPunto getCoordinate() {
    return coordinate;
  }
  public void setCoordinate(GeoJsonPunto coordinate) {
    this.coordinate = coordinate;
  }

  /**
   **/
  

  @JsonProperty("medico") 
 
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
    ModelMedicoLoccsiAmbulatorio modelMedicoLoccsiAmbulatorio = (ModelMedicoLoccsiAmbulatorio) o;
    return Objects.equals(id, modelMedicoLoccsiAmbulatorio.id) &&
        Objects.equals(provincia, modelMedicoLoccsiAmbulatorio.provincia) &&
        Objects.equals(comune, modelMedicoLoccsiAmbulatorio.comune) &&
        Objects.equals(cap, modelMedicoLoccsiAmbulatorio.cap) &&
        Objects.equals(indirizzo, modelMedicoLoccsiAmbulatorio.indirizzo) &&
        Objects.equals(telefono, modelMedicoLoccsiAmbulatorio.telefono) &&
        Objects.equals(email, modelMedicoLoccsiAmbulatorio.email) &&
        Objects.equals(note, modelMedicoLoccsiAmbulatorio.note) &&
        Objects.equals(rapportoLavoroId, modelMedicoLoccsiAmbulatorio.rapportoLavoroId) &&
        Objects.equals(tipo, modelMedicoLoccsiAmbulatorio.tipo) &&
        Objects.equals(coordinate, modelMedicoLoccsiAmbulatorio.coordinate) &&
        Objects.equals(medico, modelMedicoLoccsiAmbulatorio.medico);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, provincia, comune, cap, indirizzo, telefono, email, note, rapportoLavoroId, tipo, coordinate, medico);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ModelMedicoLoccsiAmbulatorio {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    provincia: ").append(toIndentedString(provincia)).append("\n");
    sb.append("    comune: ").append(toIndentedString(comune)).append("\n");
    sb.append("    cap: ").append(toIndentedString(cap)).append("\n");
    sb.append("    indirizzo: ").append(toIndentedString(indirizzo)).append("\n");
    sb.append("    telefono: ").append(toIndentedString(telefono)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    note: ").append(toIndentedString(note)).append("\n");
    sb.append("    rapportoLavoroId: ").append(toIndentedString(rapportoLavoroId)).append("\n");
    sb.append("    tipo: ").append(toIndentedString(tipo)).append("\n");
    sb.append("    coordinate: ").append(toIndentedString(coordinate)).append("\n");
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

