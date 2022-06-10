/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevbe.dto;

import java.util.Objects;

import javax.validation.constraints.NotNull;

public class Payload4   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [implicit-camel-case] 
  
  private ModelCittadinanza cittadinanza = null;
  private ModelMotivazioneSoggiorno motivazioneSoggiorno = null;
  private ModelAsl aslIscrizione = null;
  private ModelAsl aslDomicilio = null;
  private ModelAsl aslResidenza = null;

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
  


  // nome originario nello yaml: motivazioneSoggiorno
  @NotNull
  public ModelMotivazioneSoggiorno getMotivazioneSoggiorno() {
    return motivazioneSoggiorno;
  }
  public void setMotivazioneSoggiorno(ModelMotivazioneSoggiorno motivazioneSoggiorno) {
    this.motivazioneSoggiorno = motivazioneSoggiorno;
  }

  /**
   **/
  


  // nome originario nello yaml: aslIscrizione 
  @NotNull
  public ModelAsl getAslIscrizione() {
    return aslIscrizione;
  }
  public void setAslIscrizione(ModelAsl aslIscrizione) {
    this.aslIscrizione = aslIscrizione;
  }

  /**
   **/
  


  // nome originario nello yaml: aslDomicilio 
  @NotNull
  public ModelAsl getAslDomicilio() {
    return aslDomicilio;
  }
  public void setAslDomicilio(ModelAsl aslDomicilio) {
    this.aslDomicilio = aslDomicilio;
  }

  /**
   **/
  


  // nome originario nello yaml: aslResidenza 
  @NotNull
  public ModelAsl getAslResidenza() {
    return aslResidenza;
  }
  public void setAslResidenza(ModelAsl aslResidenza) {
    this.aslResidenza = aslResidenza;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Payload4 payload4 = (Payload4) o;
    return Objects.equals(cittadinanza, payload4.cittadinanza) &&
        Objects.equals(motivazioneSoggiorno, payload4.motivazioneSoggiorno) &&
        Objects.equals(aslIscrizione, payload4.aslIscrizione) &&
        Objects.equals(aslDomicilio, payload4.aslDomicilio) &&
        Objects.equals(aslResidenza, payload4.aslResidenza);
  }

  @Override
  public int hashCode() {
    return Objects.hash(cittadinanza, motivazioneSoggiorno, aslIscrizione, aslDomicilio, aslResidenza);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Payload4 {\n");
    
    sb.append("    cittadinanza: ").append(toIndentedString(cittadinanza)).append("\n");
    sb.append("    motivazioneSoggiorno: ").append(toIndentedString(motivazioneSoggiorno)).append("\n");
    sb.append("    aslIscrizione: ").append(toIndentedString(aslIscrizione)).append("\n");
    sb.append("    aslDomicilio: ").append(toIndentedString(aslDomicilio)).append("\n");
    sb.append("    aslResidenza: ").append(toIndentedString(aslResidenza)).append("\n");
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

