/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevboweb.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

import org.codehaus.jackson.annotate.JsonProperty;

import it.csi.scerev.scerevjpa.business.entity.ScerevTFile;

public class ModelFile   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
	private Integer pkFile = null;
	private String nameFile = null;
	private BigDecimal sizeFile = null;
	private byte[] file = null;;
	private String dataOraCreazione = null;
	private String dataOraModifica = null;
	private String dataOraCancellazione = null;

	
  @JsonProperty("pkFile") 
	 
  public Integer getPkFile() {
	return pkFile;
	  }
  public void setPkFile(Integer pkFile) {
	this.pkFile = pkFile;
	  }
  
  
  @JsonProperty("nameFile") 
	
  public String getNameFile() {
	return nameFile;
	  }
  public void setNameFile(String nameFile) {
	this.nameFile = nameFile;
	  }
	 
  
  @JsonProperty("sizeFile") 
	
  public BigDecimal getSizeFile() {
	return sizeFile;
	  }
  public void setSizeFile(BigDecimal sizeFile) {
	this.sizeFile = sizeFile;
	  }
  
  @JsonProperty("file") 
	
  public byte[] getFile() {
	return file;
	  }
  public void setFile(byte[] file) {
	this.file = file;
	  }
  
  @JsonProperty("dataOraCreazione") 
 
  public String getDataOraCreazione() {
    return dataOraCreazione;
  }
  public void setDataOraCreazione(Timestamp dataOraCreazione) {
    this.dataOraCreazione = dataOraCreazione.toString();
  }
  
  @JsonProperty("dataOraModifica") 
  
  public String getDataOraModifica() {
    return dataOraModifica;
  }
  public void setDataOraModifica(Timestamp dataOraModifica) {
    this.dataOraModifica = dataOraModifica.toString();
  }
  
  @JsonProperty("dataOraCancellazione") 
  
  public String getDataOraCancellazione() {
    return dataOraCancellazione;
  }
  public void setDataOraCancellazione(Timestamp dataOraCancellazione) {
    this.dataOraCancellazione = dataOraCancellazione.toString();
  }
  
  
  
	  
	  
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ModelFile modelFile = (ModelFile) o;
    return Objects.equals(pkFile, modelFile.pkFile) &&
        Objects.equals(nameFile, modelFile.nameFile) &&
        Objects.equals(sizeFile, modelFile.sizeFile) &&
        Objects.equals(file, modelFile.file) &&
        Objects.equals(dataOraCreazione, modelFile.dataOraCreazione) &&
        Objects.equals(dataOraModifica, modelFile.dataOraModifica) &&
        Objects.equals(dataOraCancellazione, modelFile.dataOraCancellazione);
  }

  @Override
  public int hashCode() {
    return Objects.hash(pkFile, nameFile, sizeFile, file, dataOraCreazione, dataOraModifica, dataOraCancellazione);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ModelFile {\n");
    sb.append("    pkFile: ").append(toIndentedString(pkFile)).append("\n");
    sb.append("    nameFile: ").append(toIndentedString(nameFile)).append("\n");
    sb.append("    sizeFile: ").append(toIndentedString(sizeFile)).append("\n");
    sb.append("    file: ").append(toIndentedString(file)).append("\n");
    sb.append("    dataOraCreazione: ").append(toIndentedString(dataOraCreazione)).append("\n");
    sb.append("    dataOraModifica: ").append(toIndentedString(dataOraModifica)).append("\n");
    sb.append("    dataOraCancellazione: ").append(toIndentedString(dataOraCancellazione)).append("\n");
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
  
  public ModelFile(ScerevTFile dbFile) {
	  
	  this.pkFile = dbFile.getPkFile();
	  this.nameFile = dbFile.getNameFile();
	  this.sizeFile = dbFile.getSizeFile();
	  this.file =  dbFile.getFile();
	  this.dataOraCreazione = dbFile.getDataCreazione().toString();
	  this.dataOraModifica = dbFile.getDataModifica().toString();
	  this.dataOraCancellazione = dbFile.getDataCancellazione().toString();

  }
  
}

