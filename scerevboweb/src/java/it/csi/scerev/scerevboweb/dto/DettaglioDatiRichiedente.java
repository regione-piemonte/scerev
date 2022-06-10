/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevboweb.dto;

public class DettaglioDatiRichiedente {
	private String id;
	private String nome;
	private String cognome;
	private String sesso;
	private String data_nascita;
	private String codice_fiscale;
	private String eta;
	private String luogo_nascita;
	private String cittadinanza;
	private String residenza_dichiarata;
	private String domicilio_dichiarato;
	private String asl_residenza_dichiarata;
	private String asl_domicilio_dichiarato;
	private String distretto_domicilio_dichiarato;
	private String ambito_domicilio_dichiarato;
	private String data_fine_assistenza;
	private String telefono;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getSesso() {
		return sesso;
	}

	public void setSesso(String sesso) {
		this.sesso = sesso;
	}

	public String getData_nascita() {
		return data_nascita;
	}

	public void setData_nascita(String data_nascita) {
		this.data_nascita = data_nascita;
	}

	public String getCodice_fiscale() {
		return codice_fiscale;
	}

	public void setCodice_fiscale(String codice_fiscale) {
		this.codice_fiscale = codice_fiscale;
	}

	public String getEta() {
		return eta;
	}

	public void setEta(String eta) {
		this.eta = eta;
	}

	public String getLuogo_nascita() {
		return luogo_nascita;
	}

	public void setLuogo_nascita(String luogo_nascita) {
		this.luogo_nascita = luogo_nascita;
	}

	public String getCittadinanza() {
		return cittadinanza;
	}

	public void setCittadinanza(String cittadinanza) {
		this.cittadinanza = cittadinanza;
	}

	public String getResidenza_dichiarata() {
		return residenza_dichiarata;
	}

	public void setResidenza_dichiarata(String residenza_dichiarata) {
		this.residenza_dichiarata = residenza_dichiarata;
	}

	public String getDomicilio_dichiarato() {
		return domicilio_dichiarato;
	}

	public void setDomicilio_dichiarato(String domicilio_dichiarato) {
		this.domicilio_dichiarato = domicilio_dichiarato;
	}

	public String getData_fine_assistenza() {
		return data_fine_assistenza;
	}

	public void setData_fine_assistenza(String data_fine_assistenza) {
		this.data_fine_assistenza = data_fine_assistenza;
	}

	public String getAsl_residenza_dichiarata() {
		return asl_residenza_dichiarata;
	}

	public void setAsl_residenza_dichiarata(String asl_residenza_dichiarata) {
		this.asl_residenza_dichiarata = asl_residenza_dichiarata;
	}

	public String getAsl_domicilio_dichiarato() {
		return asl_domicilio_dichiarato;
	}

	public void setAsl_domicilio_dichiarato(String asl_domicilio_dichiarato) {
		this.asl_domicilio_dichiarato = asl_domicilio_dichiarato;
	}

	public String getDistretto_domicilio_dichiarato() {
		return distretto_domicilio_dichiarato;
	}

	public void setDistretto_domicilio_dichiarato(String distretto_domicilio_dichiarato) {
		this.distretto_domicilio_dichiarato = distretto_domicilio_dichiarato;
	}

	public String getAmbito_domicilio_dichiarato() {
		return ambito_domicilio_dichiarato;
	}

	public void setAmbito_domicilio_dichiarato(String ambito_domicilio_dichiarato) {
		this.ambito_domicilio_dichiarato = ambito_domicilio_dichiarato;
	}

	@Override
	public String toString() {
		return "DettaglioDatiRichiedente [id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", sesso=" + sesso
				+ ", data_nascita=" + data_nascita + ", codice_fiscale=" + codice_fiscale + ", eta=" + eta
				+ ", luogo_nascita=" + luogo_nascita + ", cittadinanza=" + cittadinanza + ", residenza_dichiarata="
				+ residenza_dichiarata + ", domicilio_dichiarato=" + domicilio_dichiarato
				+ ", asl_residenza_dichiarata=" + asl_residenza_dichiarata + ", asl_domicilio_dichiarato="
				+ asl_domicilio_dichiarato + ", distretto_domicilio_dichiarato=" + distretto_domicilio_dichiarato
				+ ", ambito_domicilio_dichiarato=" + ambito_domicilio_dichiarato + "]";
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
}
