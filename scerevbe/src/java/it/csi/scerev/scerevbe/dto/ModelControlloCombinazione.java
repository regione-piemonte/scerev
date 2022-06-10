/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevbe.dto;

import java.util.Objects;

public class ModelControlloCombinazione   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [implicit-camel-case] 
  
  private Boolean ammessa = null;
  private Boolean derogaTerritoriale = null;
  private Boolean derogaAssociazione = null;
  private Boolean derogaPls = null;
  private Boolean derogaMmg = null;
  private Boolean variazioneAsl = null;
  private Boolean ricusazione = null;
  private ModelAsl aslIscrizione = null;
  private ModelAsl aslDomicilio = null;
  private ModelAsl aslResidenza = null;

  /**
   * true se la combinazione � ammessa, false altrimenti.  Se false il front-end blocca la procedura notificando all&#39;utente l&#39;inammissibilit� della soluzione scelta 
   **/
  


  // nome originario nello yaml: ammessa 
  public Boolean isAmmessa() {
    return ammessa;
  }
  public void setAmmessa(Boolean ammessa) {
    this.ammessa = ammessa;
  }

  /**
   * true se c&#39;� deroga territoriale, false altrimenti.  Se true il front-end richieder� all&#39;utente il consenso del medico 
   **/
  


  // nome originario nello yaml: derogaTerritoriale 
  public Boolean isDerogaTerritoriale() {
    return derogaTerritoriale;
  }
  public void setDerogaTerritoriale(Boolean derogaTerritoriale) {
    this.derogaTerritoriale = derogaTerritoriale;
  }

  /**
   * true se c&#39;� deroga per associazione, false altrimenti  Se true il front-end richieder� all&#39;utente il consenso del medico 
   **/
  


  // nome originario nello yaml: derogaAssociazione 
  public Boolean isDerogaAssociazione() {
    return derogaAssociazione;
  }
  public void setDerogaAssociazione(Boolean derogaAssociazione) {
    this.derogaAssociazione = derogaAssociazione;
  }

  /**
   * true se c&#39;� deroga per pediatra, false altrimenti  Se true il front-end richieder� all&#39;utente il consenso del medico 
   **/
  


  // nome originario nello yaml: derogaPls 
  public Boolean isDerogaPls() {
    return derogaPls;
  }
  public void setDerogaPls(Boolean derogaPls) {
    this.derogaPls = derogaPls;
  }

  /**
   * true se c&#39;� deroga per medico generale, false altrimenti  Se true il front-end richieder� all&#39;utente il consenso del medico 
   **/
  


  // nome originario nello yaml: derogaMmg 
  public Boolean isDerogaMmg() {
    return derogaMmg;
  }
  public void setDerogaMmg(Boolean derogaMmg) {
    this.derogaMmg = derogaMmg;
  }

  /**
   * true se c&#39;� una variazione dell&#39;asl di domicilio o residenza rispetto a quelle presenti nel sistema, false altrimenti 
   **/
  


  // nome originario nello yaml: variazioneAsl 
  public Boolean isVariazioneAsl() {
    return variazioneAsl;
  }
  public void setVariazioneAsl(Boolean variazioneAsl) {
    this.variazioneAsl = variazioneAsl;
  }

  /**
   **/
  


  // nome originario nello yaml: aslIscrizione 
  public ModelAsl getAslIscrizione() {
    return aslIscrizione;
  }
  public void setAslIscrizione(ModelAsl aslIscrizione) {
    this.aslIscrizione = aslIscrizione;
  }

  /**
   **/
  


  // nome originario nello yaml: aslDomicilio 
  public ModelAsl getAslDomicilio() {
    return aslDomicilio;
  }
  public void setAslDomicilio(ModelAsl aslDomicilio) {
    this.aslDomicilio = aslDomicilio;
  }

  /**
   **/
  


  // nome originario nello yaml: aslResidenza 
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
    ModelControlloCombinazione modelControlloCombinazione = (ModelControlloCombinazione) o;
    return Objects.equals(ammessa, modelControlloCombinazione.ammessa) &&
        Objects.equals(derogaTerritoriale, modelControlloCombinazione.derogaTerritoriale) &&
        Objects.equals(derogaAssociazione, modelControlloCombinazione.derogaAssociazione) &&
        Objects.equals(derogaPls, modelControlloCombinazione.derogaPls) &&
        Objects.equals(derogaMmg, modelControlloCombinazione.derogaMmg) &&
        Objects.equals(variazioneAsl, modelControlloCombinazione.variazioneAsl) &&
        Objects.equals(aslIscrizione, modelControlloCombinazione.aslIscrizione) &&
        Objects.equals(aslDomicilio, modelControlloCombinazione.aslDomicilio) &&
        Objects.equals(aslResidenza, modelControlloCombinazione.aslResidenza) &&
        Objects.equals(ricusazione, modelControlloCombinazione.ricusazione);
  }

  @Override
  public int hashCode() {
    return Objects.hash(ammessa, derogaTerritoriale, derogaAssociazione, derogaPls, derogaMmg, variazioneAsl, aslIscrizione, aslDomicilio, aslResidenza, ricusazione);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ModelControlloCombinazione {\n");
    
    sb.append("    ammessa: ").append(toIndentedString(ammessa)).append("\n");
    sb.append("    derogaTerritoriale: ").append(toIndentedString(derogaTerritoriale)).append("\n");
    sb.append("    derogaAssociazione: ").append(toIndentedString(derogaAssociazione)).append("\n");
    sb.append("    derogaPls: ").append(toIndentedString(derogaPls)).append("\n");
    sb.append("    derogaMmg: ").append(toIndentedString(derogaMmg)).append("\n");
    sb.append("    variazioneAsl: ").append(toIndentedString(variazioneAsl)).append("\n");
    sb.append("    aslIscrizione: ").append(toIndentedString(aslIscrizione)).append("\n");
    sb.append("    aslDomicilio: ").append(toIndentedString(aslDomicilio)).append("\n");
    sb.append("    aslResidenza: ").append(toIndentedString(aslResidenza)).append("\n");
    sb.append("    ricusazione: ").append(toIndentedString(ricusazione)).append("\n");
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
  
  
	public Boolean getRicusazione() {
		return ricusazione;
	}
	
	public void setRicusazione(Boolean ricusazione) {
		this.ricusazione = ricusazione;
	}
}

