/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevbe.dto;

import java.util.Objects;

public class ModelCambioMedicoRichiestaAllegati   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [implicit-camel-case] 
  
  private ModelDocumentoLettura identitaFronte = null;
  private ModelDocumentoLettura identitaRetro = null;
  private ModelDocumentoLettura consensoMedico = null;
  private ModelDocumentoLettura certificatoMedico = null;
  private ModelDocumentoLettura soggiorno = null;
  private ModelDocumentoLettura delegante = null;

  /**
   **/
  


  // nome originario nello yaml: identitaFronte 
  public ModelDocumentoLettura getIdentitaFronte() {
    return identitaFronte;
  }
  public void setIdentitaFronte(ModelDocumentoLettura identitaFronte) {
    this.identitaFronte = identitaFronte;
  }

  /**
   **/
  


  // nome originario nello yaml: identitaRetro 
  public ModelDocumentoLettura getIdentitaRetro() {
    return identitaRetro;
  }
  public void setIdentitaRetro(ModelDocumentoLettura identitaRetro) {
    this.identitaRetro = identitaRetro;
  }

  /**
   **/
  


  // nome originario nello yaml: consensoMedico 
  public ModelDocumentoLettura getConsensoMedico() {
    return consensoMedico;
  }
  public void setConsensoMedico(ModelDocumentoLettura consensoMedico) {
    this.consensoMedico = consensoMedico;
  }

  /**
   **/
  


  // nome originario nello yaml: certificatoMedico 
  public ModelDocumentoLettura getCertificatoMedico() {
    return certificatoMedico;
  }
  public void setCertificatoMedico(ModelDocumentoLettura certificatoMedico) {
    this.certificatoMedico = certificatoMedico;
  }

  /**
   **/
  


  // nome originario nello yaml: soggiorno 
  public ModelDocumentoLettura getSoggiorno() {
    return soggiorno;
  }
  public void setSoggiorno(ModelDocumentoLettura soggiorno) {
    this.soggiorno = soggiorno;
  }

  /**
   **/
  


  // nome originario nello yaml: delegante 
  public ModelDocumentoLettura getDelegante() {
    return delegante;
  }
  public void setDelegante(ModelDocumentoLettura delegante) {
    this.delegante = delegante;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ModelCambioMedicoRichiestaAllegati modelCambioMedicoRichiestaAllegati = (ModelCambioMedicoRichiestaAllegati) o;
    return Objects.equals(identitaFronte, modelCambioMedicoRichiestaAllegati.identitaFronte) &&
        Objects.equals(identitaRetro, modelCambioMedicoRichiestaAllegati.identitaRetro) &&
        Objects.equals(consensoMedico, modelCambioMedicoRichiestaAllegati.consensoMedico) &&
        Objects.equals(certificatoMedico, modelCambioMedicoRichiestaAllegati.certificatoMedico) &&
        Objects.equals(soggiorno, modelCambioMedicoRichiestaAllegati.soggiorno) &&
        Objects.equals(delegante, modelCambioMedicoRichiestaAllegati.delegante);
  }

  @Override
  public int hashCode() {
    return Objects.hash(identitaFronte, identitaRetro, consensoMedico, certificatoMedico, soggiorno, delegante);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ModelCambioMedicoRichiestaAllegati {\n");
    
    sb.append("    identitaFronte: ").append(toIndentedString(identitaFronte)).append("\n");
    sb.append("    identitaRetro: ").append(toIndentedString(identitaRetro)).append("\n");
    sb.append("    consensoMedico: ").append(toIndentedString(consensoMedico)).append("\n");
    sb.append("    certificatoMedico: ").append(toIndentedString(certificatoMedico)).append("\n");
    sb.append("    soggiorno: ").append(toIndentedString(soggiorno)).append("\n");
    sb.append("    delegante: ").append(toIndentedString(delegante)).append("\n");
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

