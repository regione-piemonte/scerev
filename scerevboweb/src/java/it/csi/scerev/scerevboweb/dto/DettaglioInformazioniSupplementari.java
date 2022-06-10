/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevboweb.dto;

import java.sql.Timestamp;

public class DettaglioInformazioniSupplementari {
	private String id;
	private String professione;
	private String partitaIva;
	private String nIscrizione;
	private String nomeFamiliare;
	private String cognomeFamiliare;
	private String codiceFiscale;
	private String rapportoParentela;
	private String medicoFamiliare;
	private Boolean lavoro_tipo;
	private String lavoro_dittaOdatore;
	private String lavoro_nrTelefonoDatore;
	private String lavoro_comune;
	private String lavoro_provincia;
	private String lavoro_indirizzo;
	private String lavoro_civico;
	private Timestamp lavoro_dataFineContratto;
	private String studio_nomeIstituto;
	private String studio_comuneIstituto;
	private String studio_provinciaIstituto;
	private String studio_indirizzoIstituto;
	private String studio_civicoIstituto;
	private Timestamp studio_dataFineIscrizione;
	private String medicoDiverso;
	private String motivoDomicilio;
	private String motivoSoggiorno;
	private String residenzaFamiliare;
	private String domicilioFamiliare;
	private String dataNascitaFamiliare;
	private String tipoProfiloFamiliare;
	private String dataFineAslFamiliare;
	private String motivoRevoca;
	private String motivazioneDerogaTerritoriale;
	
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProfessione() {
		return professione;
	}

	public void setProfessione(String professione) {
		this.professione = professione;
	}

	public String getPartitaIva() {
		return partitaIva;
	}

	public void setPartitaIva(String partitaIva) {
		this.partitaIva = partitaIva;
	}

	public String getnIscrizione() {
		return nIscrizione;
	}

	public void setnIscrizione(String nIscrizione) {
		this.nIscrizione = nIscrizione;
	}

	public String getNomeFamiliare() {
		return nomeFamiliare;
	}

	public void setNomeFamiliare(String nomeFamiliare) {
		this.nomeFamiliare = nomeFamiliare;
	}

	public String getCognomeFamiliare() {
		return cognomeFamiliare;
	}

	public void setCognomeFamiliare(String cognomeFamiliare) {
		this.cognomeFamiliare = cognomeFamiliare;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public String getRapportoParentela() {
		return rapportoParentela;
	}

	public void setRapportoParentela(String rapportoParentela) {
		this.rapportoParentela = rapportoParentela;
	}

	public String getMedicoFamiliare() {
		return medicoFamiliare;
	}

	public void setMedicoFamiliare(String medicoFamiliare) {
		this.medicoFamiliare = medicoFamiliare;
	}
	
	public Boolean getTipoLavoro() {
		return lavoro_tipo;
	}

	public void setTipoLavoro(Boolean lavoro_tipo) {
		this.lavoro_tipo = lavoro_tipo;
	}
	
	public String getDittaODatoreDiLavoro() {
		return lavoro_dittaOdatore;
	}

	public void setDittaODatoreDiLavoro(String lavoro_dittaOdatore) {
		this.lavoro_dittaOdatore = lavoro_dittaOdatore;
	}
	
	public String getNrTelefonoDatoreLavoro() {
		return lavoro_nrTelefonoDatore;
	}

	public void setNrTelefonoDatoreLavoro(String lavoro_nrTelefonoDatore) {
		this.lavoro_nrTelefonoDatore = lavoro_nrTelefonoDatore;
	}
	
	public String getComuneLavoro() {
		return lavoro_comune;
	}

	public void setComuneLavoro(String lavoro_comune) {
		this.lavoro_comune = lavoro_comune;
	}
	
	public String getProvinciaLavoro() {
		return lavoro_provincia;
	}

	public void setProvinciaLavoro(String lavoro_provincia) {
		this.lavoro_provincia = lavoro_provincia;
	}
	
	public String getIndirizzoLavoro() {
		return lavoro_indirizzo;
	}

	public void setIndirizzoLavoro(String lavoro_indirizzo) {
		this.lavoro_indirizzo = lavoro_indirizzo;
	}
	
	public String getCivicoLavoro() {
		return lavoro_civico;
	}

	public void setCivicoLavoro(String lavoro_civico) {
		this.lavoro_civico = lavoro_civico;
	}
	
	public Timestamp getDataFineContrattoLavoro() {
		return lavoro_dataFineContratto;
	}

	public void setDataFineContrattoLavoro(Timestamp lavoro_dataFineContratto) {
		this.lavoro_dataFineContratto = lavoro_dataFineContratto;
	}
	
	public String getNomeIstitutoStudio() {
		return studio_nomeIstituto;
	}

	public void setNomeIstitutoStudio(String studio_nomeIstituto) {
		this.studio_nomeIstituto = studio_nomeIstituto;
	}
	
	public String getComuneIstitutoStudio() {
		return studio_comuneIstituto;
	}

	public void setComuneIstitutoStudio(String studio_comuneIstituto) {
		this.studio_comuneIstituto = studio_comuneIstituto;
	}
	
	public String getProvinciaIstitutoStudio() {
		return studio_provinciaIstituto;
	}

	public void setProvinciaIstitutoStudio(String studio_provinciaIstituto) {
		this.studio_provinciaIstituto = studio_provinciaIstituto;
	}
	
	public String getIndirizzoIstitutoStudio() {
		return studio_indirizzoIstituto;
	}

	public void setIndirizzoIstitutoStudio(String studio_indirizzoIstituto) {
		this.studio_indirizzoIstituto = studio_indirizzoIstituto;
	}
	
	public String getCivicoIstitutoStudio() {
		return studio_civicoIstituto;
	}

	public void setCivicoIstitutoStudio(String studio_civicoIstituto) {
		this.studio_civicoIstituto = studio_civicoIstituto;
	}
	
	public Timestamp getDataFineIscrizioneStudio() {
		return studio_dataFineIscrizione;
	}

	public void setDataFineIscrizioneStudio(Timestamp studio_dataFineIscrizione) {
		this.studio_dataFineIscrizione = studio_dataFineIscrizione;
	}
	
	
	
	@Override
	public String toString() {
		return "DettaglioInformazioniSupplementari [id=" + id + ", professione=" + professione + ", partitaIva="
				+ partitaIva + ", nIscrizione=" + nIscrizione + ", nomeFamiliare=" + nomeFamiliare
				+ ", cognomeFamiliare=" + cognomeFamiliare + ", codiceFiscale=" + codiceFiscale + ", rapportoParentela="
				+ rapportoParentela + ", medicoFamiliare=" + medicoFamiliare + "]";
	}

	public String getMedicoDiverso() {
		return medicoDiverso;
	}

	public void setMedicoDiverso(String medicoDiverso) {
		this.medicoDiverso = medicoDiverso;
	}

	public String getMotivoDomicilio() {
		return motivoDomicilio;
	}

	public void setMotivoDomicilio(String motivoDomicilio) {
		this.motivoDomicilio = motivoDomicilio;
	}

	public String getMotivoSoggiorno() {
		return motivoSoggiorno;
	}

	public void setMotivoSoggiorno(String motivoSoggiorno) {
		this.motivoSoggiorno = motivoSoggiorno;
	}

	public String getResidenzaFamiliare() {
		return residenzaFamiliare;
	}

	public void setResidenzaFamiliare(String residenzaFamiliare) {
		this.residenzaFamiliare = residenzaFamiliare;
	}

	public String getDomicilioFamiliare() {
		return domicilioFamiliare;
	}

	public void setDomicilioFamiliare(String domicilioFamiliare) {
		this.domicilioFamiliare = domicilioFamiliare;
	}

	public String getDataNascitaFamiliare() {
		return dataNascitaFamiliare;
	}

	public void setDataNascitaFamiliare(String dataNascitaFamiliare) {
		this.dataNascitaFamiliare = dataNascitaFamiliare;
	}

	public String getTipoProfiloFamiliare() {
		return tipoProfiloFamiliare;
	}

	public void setTipoProfiloFamiliare(String tipoProfiloFamiliare) {
		this.tipoProfiloFamiliare = tipoProfiloFamiliare;
	}

	public String getDataFineAslFamiliare() {
		return dataFineAslFamiliare;
	}

	public void setDataFineAslFamiliare(String dataFineAslFamiliare) {
		this.dataFineAslFamiliare = dataFineAslFamiliare;
	}

	public String getMotivoRevoca() {
		return motivoRevoca;
	}

	public void setMotivoRevoca(String motivoRevoca) {
		this.motivoRevoca = motivoRevoca;
	}

	public String getMotivazioneDerogaTerritoriale() {
		return motivazioneDerogaTerritoriale;
	}

	public void setMotivazioneDerogaTerritoriale(String motivazioneDerogaTerritoriale) {
		this.motivazioneDerogaTerritoriale = motivazioneDerogaTerritoriale;
	}

	

}
