/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevboweb.dto;

import java.sql.Timestamp;
import java.util.Objects;

import org.codehaus.jackson.annotate.JsonProperty;

import it.csi.scerev.scerevjpa.business.entity.ScerevTDocumento;

public class ModelDocumento {
	// verra' utilizzata la seguente strategia serializzazione degli attributi:
	// [explicit-as-modeled]

	private Integer pkDocumento = null;
	private Integer fkDocumentoTipo = null;
	private Integer fkFile = null;
	private Integer fkPratica = null;
	private String dataOraCreazione = null;
	private String dataOraModifica = null;
	private String dataOraCancellazione = null;

	@JsonProperty("pkDocumento")

	public Integer getpkDocumento() {
		return pkDocumento;
	}

	public void setpkDocumento(Integer pkDocumento) {
		this.pkDocumento = pkDocumento;
	}

	@JsonProperty("fkDocumentoTipo")

	public Integer getfkDocumentoTipo() {
		return fkDocumentoTipo;
	}

	public void setfkDocumentoTipo(Integer fkDocumentoTipo) {
		this.fkDocumentoTipo = fkDocumentoTipo;
	}

	@JsonProperty("fkFile")

	public Integer getfkFile() {
		return fkFile;
	}

	public void setfkFile(Integer fkFile) {
		this.fkFile = fkFile;
	}

	@JsonProperty("fkPratica")

	public Integer getfkPratica() {
		return fkPratica;
	}

	public void setfkPratica(Integer fkPratica) {
		this.fkPratica = fkPratica;
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
		ModelDocumento modelFile = (ModelDocumento) o;
		return Objects.equals(pkDocumento, modelFile.pkDocumento)
				&& Objects.equals(fkDocumentoTipo, modelFile.fkDocumentoTipo)
				&& Objects.equals(fkFile, modelFile.fkFile) && Objects.equals(fkPratica, modelFile.fkPratica)
				&& Objects.equals(dataOraCreazione, modelFile.dataOraCreazione)
				&& Objects.equals(dataOraModifica, modelFile.dataOraModifica)
				&& Objects.equals(dataOraCancellazione, modelFile.dataOraCancellazione);
	}

	@Override
	public int hashCode() {
		return Objects.hash(pkDocumento, fkDocumentoTipo, fkFile, fkPratica, dataOraCreazione, dataOraModifica,
				dataOraCancellazione);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class ModelFile {\n");
		sb.append("    pkDocumento: ").append(toIndentedString(pkDocumento)).append("\n");
		sb.append("    fkDocumentoTipo: ").append(toIndentedString(fkDocumentoTipo)).append("\n");
		sb.append("    fkFile: ").append(toIndentedString(fkFile)).append("\n");
		sb.append("    fkPratica: ").append(toIndentedString(fkPratica)).append("\n");
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

	public ModelDocumento(ScerevTDocumento dbFile) {
		try {
			this.pkDocumento = dbFile.getPkDocumento();
			this.fkDocumentoTipo = dbFile.getScerevDDocumentoTipo().getPkDocumentoTipo();
			this.fkFile = dbFile.getScerevTFile().getPkFile();
			this.fkPratica = dbFile.getScerevTPratica().getPkPratica();
			this.dataOraCreazione = dbFile.getDataCreazione().toString();
			this.dataOraModifica = dbFile.getDataModifica().toString();
			this.dataOraCancellazione = dbFile.getDataCancellazione().toString();
		} catch (Exception e) {
		}

	}

}
