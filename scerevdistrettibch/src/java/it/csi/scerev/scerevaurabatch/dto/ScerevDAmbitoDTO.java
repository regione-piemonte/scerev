/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevaurabatch.dto;

import java.util.Date;

public class ScerevDAmbitoDTO {
	private Integer pk_ambito;
	private String codice_ambito;
	private String denominazione_ambito;
	private Integer fk_distretto;
	private Date data_inizio_validita;
	private Date data_fine_validita;
	
	
	public ScerevDAmbitoDTO() {}
	
	public ScerevDAmbitoDTO(Integer pk_ambito, String codice_ambito, String denominazione_ambito, Integer fk_distretto,
			Date data_inizio_validita, Date data_fine_validita) {
		super();
		this.pk_ambito = pk_ambito;
		this.codice_ambito = codice_ambito;
		this.denominazione_ambito = denominazione_ambito;
		this.fk_distretto = fk_distretto;
		this.data_inizio_validita = data_inizio_validita;
		this.data_fine_validita = data_fine_validita;
	}

	public Integer getPk_ambito() {
		return pk_ambito;
	}
	public void setPk_ambito(Integer pk_ambito) {
		this.pk_ambito = pk_ambito;
	}
	public String getCodice_ambito() {
		return codice_ambito;
	}
	public void setCodice_ambito(String codice_ambito) {
		this.codice_ambito = codice_ambito;
	}
	public String getDenominazione_ambito() {
		return denominazione_ambito;
	}
	public void setDenominazione_ambito(String denominazione_ambito) {
		this.denominazione_ambito = denominazione_ambito;
	}
	public Integer getFk_distretto() {
		return fk_distretto;
	}
	public void setFk_distretto(Integer fk_distretto) {
		this.fk_distretto = fk_distretto;
	}

	public Date getData_inizio_validita() {
		return data_inizio_validita;
	}

	public void setData_inizio_validita(Date data_inizio_validita) {
		this.data_inizio_validita = data_inizio_validita;
	}

	public Date getData_fine_validita() {
		return data_fine_validita;
	}

	public void setData_fine_validita(Date data_fine_validita) {
		this.data_fine_validita = data_fine_validita;
	}

	@Override
	public String toString() {
		return "ScerevDAmbitoDTO [pk_ambito=" + pk_ambito + ", codice_ambito=" + codice_ambito
				+ ", denominazione_ambito=" + denominazione_ambito + ", fk_distretto=" + fk_distretto
				+ ", data_inizio_validita=" + data_inizio_validita + ", data_fine_validita=" + data_fine_validita + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codice_ambito == null) ? 0 : codice_ambito.hashCode());
		result = prime * result + ((data_inizio_validita == null) ? 0 : data_inizio_validita.hashCode());
		result = prime * result + ((denominazione_ambito == null) ? 0 : denominazione_ambito.hashCode());
		result = prime * result + ((fk_distretto == null) ? 0 : fk_distretto.hashCode());
		result = prime * result + ((pk_ambito == null) ? 0 : pk_ambito.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ScerevDAmbitoDTO other = (ScerevDAmbitoDTO) obj;
		if (codice_ambito == null) {
			if (other.codice_ambito != null)
				return false;
		} else if (!codice_ambito.equals(other.codice_ambito))
			return false;
		if (data_inizio_validita == null) {
			if (other.data_inizio_validita != null)
				return false;
		} else if (!data_inizio_validita.equals(other.data_inizio_validita))
			return false;
		if (denominazione_ambito == null) {
			if (other.denominazione_ambito != null)
				return false;
		} else if (!denominazione_ambito.equals(other.denominazione_ambito))
			return false;
		if (fk_distretto == null) {
			if (other.fk_distretto != null)
				return false;
		} else if (!fk_distretto.equals(other.fk_distretto))
			return false;
		if (pk_ambito == null) {
			if (other.pk_ambito != null)
				return false;
		} else if (!pk_ambito.equals(other.pk_ambito))
			return false;
		return true;
	}
	
	
}
