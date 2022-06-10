/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevaurabatch.dto;

import java.util.Date;

public class ScerevDDistrettoDTO {
	private Integer pk_distretto;
	private String codice_distretto;
	private String denominazione_distretto;
	private Integer fk_azienda;
	private Date data_inizio_validita;
	private Date data_fine_validita;
		
	public ScerevDDistrettoDTO() {}

	public ScerevDDistrettoDTO(Integer pk_distretto, String codice_distretto, String denominazione_distretto,
			Integer fk_azienda, Date data_inizio_validita, Date data_fine_validita) {
		super();
		this.pk_distretto = pk_distretto;
		this.codice_distretto = codice_distretto;
		this.denominazione_distretto = denominazione_distretto;
		this.fk_azienda = fk_azienda;
		this.data_inizio_validita = data_inizio_validita;
		this.data_fine_validita = data_fine_validita;
	}

	public Integer getPk_distretto() {
		return pk_distretto;
	}

	public void setPk_distretto(Integer pk_distretto) {
		this.pk_distretto = pk_distretto;
	}

	public String getCodice_distretto() {
		return codice_distretto;
	}

	public void setCodice_distretto(String codice_distretto) {
		this.codice_distretto = codice_distretto;
	}

	public String getDenominazione_distretto() {
		return denominazione_distretto;
	}

	public void setDenominazione_distretto(String denominazione_distretto) {
		this.denominazione_distretto = denominazione_distretto;
	}

	public Integer getFk_azienda() {
		return fk_azienda;
	}

	public void setFk_azienda(Integer fk_azienda) {
		this.fk_azienda = fk_azienda;
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
		return "ScerevDDistrettoDTO [pk_distretto=" + pk_distretto + ", codice_distretto=" + codice_distretto
				+ ", denominazione_distretto=" + denominazione_distretto + ", fk_azienda=" + fk_azienda
				+ ", data_inizio_validita=" + data_inizio_validita + ", data_fine_validita=" + data_fine_validita + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codice_distretto == null) ? 0 : codice_distretto.hashCode());
		result = prime * result + ((data_inizio_validita == null) ? 0 : data_inizio_validita.hashCode());
		result = prime * result + ((denominazione_distretto == null) ? 0 : denominazione_distretto.hashCode());
		result = prime * result + ((fk_azienda == null) ? 0 : fk_azienda.hashCode());
		result = prime * result + ((pk_distretto == null) ? 0 : pk_distretto.hashCode());
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
		ScerevDDistrettoDTO other = (ScerevDDistrettoDTO) obj;
		if (codice_distretto == null) {
			if (other.codice_distretto != null)
				return false;
		} else if (!codice_distretto.equals(other.codice_distretto))
			return false;
		if (data_inizio_validita == null) {
			if (other.data_inizio_validita != null)
				return false;
		} else if (!data_inizio_validita.equals(other.data_inizio_validita))
			return false;
		if (denominazione_distretto == null) {
			if (other.denominazione_distretto != null)
				return false;
		} else if (!denominazione_distretto.equals(other.denominazione_distretto))
			return false;
		if (fk_azienda == null) {
			if (other.fk_azienda != null)
				return false;
		} else if (!fk_azienda.equals(other.fk_azienda))
			return false;
		if (pk_distretto == null) {
			if (other.pk_distretto != null)
				return false;
		} else if (!pk_distretto.equals(other.pk_distretto))
			return false;
		return true;
	}
	
	
}
