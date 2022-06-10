/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevbe.dto;

import java.util.Objects;

public class ModelDocumentoLettura   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [implicit-camel-case] 
  
  private String id = null;
  private String tipo = null;
  private String descrizione = null;
  private String nomeFile = null;

  // nome originario nello yaml: id
  public String getId() {
		return id;
  }
  public void setId(String id) {
		this.id = id;
  }
  
  /**
   * Identificativo univoco - Tipologia dell&#39;allegato richiesto
   **/
  


  // nome originario nello yaml: tipo 
  public String getTipo() {
    return tipo;
  }
  public void setTipo(String tipo) {
    this.tipo = tipo;
  }

  /**
   * Descrizione dell&#39;allegato richiesto
   **/
  


  // nome originario nello yaml: descrizione 
  public String getDescrizione() {
    return descrizione;
  }
  public void setDescrizione(String descrizione) {
    this.descrizione = descrizione;
  }

  /**
   **/
  


  // nome originario nello yaml: nomeFile 
  public String getNomeFile() {
    return nomeFile;
  }
  public void setNomeFile(String nomeFile) {
    this.nomeFile = nomeFile;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ModelDocumentoLettura modelDocumentoLettura = (ModelDocumentoLettura) o;
    return Objects.equals(tipo, modelDocumentoLettura.tipo) &&
        Objects.equals(descrizione, modelDocumentoLettura.descrizione) &&
        Objects.equals(nomeFile, modelDocumentoLettura.nomeFile);
  }

  @Override
  public int hashCode() {
    return Objects.hash(tipo, descrizione, nomeFile);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ModelDocumentoLettura {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    tipo: ").append(toIndentedString(tipo)).append("\n");
    sb.append("    descrizione: ").append(toIndentedString(descrizione)).append("\n");
    sb.append("    nomeFile: ").append(toIndentedString(nomeFile)).append("\n");
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

