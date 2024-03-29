/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevbe.dto;

import java.util.Date;
import java.util.Objects;

public class ModelAmbulatorioLoccsiMedico {
	// verra' utilizzata la seguente strategia serializzazione degli attributi:
	// [implicit-camel-case]

	private String id = null;
	private String nome = null;
	private String cognome = null;
	private String sesso = null;
	private Date dataNascita = null;
	private String codiceFiscale = null;
	private String mail = null;
	private String specializzazioni = null;
	private Boolean autolimitato = null;
	private Integer massimale = null;
	private Integer massimaleDeroga = null;
	private Integer massimaleAssistenzaTemporanea = null;
	private Integer massimaleInfanzia = null;
	private String codiceRegionale = null;
	private ModelMedicoDistretto distretto = null;
	private ModelMedicoAmbito ambito = null;
	private ModelMedicoTipologia tipologia = null;
	private ModelAsl asl = null;
	private ModelDisponibilitaMedicoMonitorato disponibilita = null;

	/**
	 * Identificativo univoco
	 **/

	// nome originario nello yaml: id
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Il nome del medico trovato
	 **/

	// nome originario nello yaml: nome
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Il cognome del medico trovato
	 **/

	// nome originario nello yaml: cognome
	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	/**
	 * Il sesso del medico
	 **/

	// nome originario nello yaml: sesso
	public String getSesso() {
		return sesso;
	}

	public void setSesso(String sesso) {
		this.sesso = sesso;
	}

	/**
	 * La data di nascita del medico
	 **/

	// nome originario nello yaml: dataNascita
	public Date getDataNascita() {
		return dataNascita;
	}

	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}

	/**
	 * Il codice fiscale del medico
	 **/

	// nome originario nello yaml: codiceFiscale
	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	/**
	 * La mail del medico
	 **/

	// nome originario nello yaml: mail
	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	/**
	 * Le specializzazioni del medico
	 **/

	// nome originario nello yaml: specializzazioni
	public String getSpecializzazioni() {
		return specializzazioni;
	}

	public void setSpecializzazioni(String specializzazioni) {
		this.specializzazioni = specializzazioni;
	}

	/**
	 * true se il medico si � autolimitato, fale altrimenti
	 **/

	// nome originario nello yaml: autolimitato
	public Boolean isAutolimitato() {
		return autolimitato;
	}

	public void setAutolimitato(Boolean autolimitato) {
		this.autolimitato = autolimitato;
	}

	/**
	 * Il massimale del medico
	 **/

	// nome originario nello yaml: massimale
	public Integer getMassimale() {
		return massimale;
	}

	public void setMassimale(Integer massimale) {
		this.massimale = massimale;
	}

	/**
	 * Il massimale del medico per deroghe
	 **/

	// nome originario nello yaml: massimaleDeroga
	public Integer getMassimaleDeroga() {
		return massimaleDeroga;
	}

	public void setMassimaleDeroga(Integer massimaleDeroga) {
		this.massimaleDeroga = massimaleDeroga;
	}

	/**
	 * Il massimale per le assistenze temporanee
	 **/

	// nome originario nello yaml: massimaleAssistenzaTemporanea
	public Integer getMassimaleAssistenzaTemporanea() {
		return massimaleAssistenzaTemporanea;
	}

	public void setMassimaleAssistenzaTemporanea(Integer massimaleAssistenzaTemporanea) {
		this.massimaleAssistenzaTemporanea = massimaleAssistenzaTemporanea;
	}

	/**
	 * Il massimale per minorenni in una determinata fascia di et�
	 **/

	// nome originario nello yaml: massimaleInfanzia
	public Integer getMassimaleInfanzia() {
		return massimaleInfanzia;
	}

	public void setMassimaleInfanzia(Integer massimaleInfanzia) {
		this.massimaleInfanzia = massimaleInfanzia;
	}

	/**
	 * Un codice univoco del medico a livello regionale
	 **/

	// nome originario nello yaml: codiceRegionale
	public String getCodiceRegionale() {
		return codiceRegionale;
	}

	public void setCodiceRegionale(String codiceRegionale) {
		this.codiceRegionale = codiceRegionale;
	}

	/**
	 **/

	// nome originario nello yaml: distretto
	public ModelMedicoDistretto getDistretto() {
		return distretto;
	}

	public void setDistretto(ModelMedicoDistretto distretto) {
		this.distretto = distretto;
	}

	/**
	 **/

	// nome originario nello yaml: ambito
	public ModelMedicoAmbito getAmbito() {
		return ambito;
	}

	public void setAmbito(ModelMedicoAmbito ambito) {
		this.ambito = ambito;
	}

	/**
	 **/

	// nome originario nello yaml: tipologia
	public ModelMedicoTipologia getTipologia() {
		return tipologia;
	}

	public void setTipologia(ModelMedicoTipologia tipologia) {
		this.tipologia = tipologia;
	}

	/**
	 **/

	// nome originario nello yaml: asl
	public ModelAsl getAsl() {
		return asl;
	}

	public void setAsl(ModelAsl asl) {
		this.asl = asl;
	}

	public ModelDisponibilitaMedicoMonitorato getDisponibilita() {
		return disponibilita;
	}

	public void setDisponibilita(ModelDisponibilitaMedicoMonitorato disponibilita) {
		this.disponibilita = disponibilita;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ModelAmbulatorioLoccsiMedico modelAmbulatorioLoccsiMedico = (ModelAmbulatorioLoccsiMedico) o;
		return Objects.equals(id, modelAmbulatorioLoccsiMedico.id)
				&& Objects.equals(nome, modelAmbulatorioLoccsiMedico.nome)
				&& Objects.equals(cognome, modelAmbulatorioLoccsiMedico.cognome)
				&& Objects.equals(sesso, modelAmbulatorioLoccsiMedico.sesso)
				&& Objects.equals(dataNascita, modelAmbulatorioLoccsiMedico.dataNascita)
				&& Objects.equals(codiceFiscale, modelAmbulatorioLoccsiMedico.codiceFiscale)
				&& Objects.equals(mail, modelAmbulatorioLoccsiMedico.mail)
				&& Objects.equals(specializzazioni, modelAmbulatorioLoccsiMedico.specializzazioni)
				&& Objects.equals(autolimitato, modelAmbulatorioLoccsiMedico.autolimitato)
				&& Objects.equals(massimale, modelAmbulatorioLoccsiMedico.massimale)
				&& Objects.equals(massimaleDeroga, modelAmbulatorioLoccsiMedico.massimaleDeroga)
				&& Objects.equals(massimaleAssistenzaTemporanea,
						modelAmbulatorioLoccsiMedico.massimaleAssistenzaTemporanea)
				&& Objects.equals(massimaleInfanzia, modelAmbulatorioLoccsiMedico.massimaleInfanzia)
				&& Objects.equals(codiceRegionale, modelAmbulatorioLoccsiMedico.codiceRegionale)
				&& Objects.equals(distretto, modelAmbulatorioLoccsiMedico.distretto)
				&& Objects.equals(ambito, modelAmbulatorioLoccsiMedico.ambito)
				&& Objects.equals(tipologia, modelAmbulatorioLoccsiMedico.tipologia)
				&& Objects.equals(asl, modelAmbulatorioLoccsiMedico.asl)
				&& Objects.equals(disponibilita, modelAmbulatorioLoccsiMedico.disponibilita);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, nome, cognome, sesso, dataNascita, codiceFiscale, mail, specializzazioni, autolimitato,
				massimale, massimaleDeroga, massimaleAssistenzaTemporanea, massimaleInfanzia, codiceRegionale,
				distretto, ambito, tipologia, asl, disponibilita);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class ModelAmbulatorioLoccsiMedico {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    nome: ").append(toIndentedString(nome)).append("\n");
		sb.append("    cognome: ").append(toIndentedString(cognome)).append("\n");
		sb.append("    sesso: ").append(toIndentedString(sesso)).append("\n");
		sb.append("    dataNascita: ").append(toIndentedString(dataNascita)).append("\n");
		sb.append("    codiceFiscale: ").append(toIndentedString(codiceFiscale)).append("\n");
		sb.append("    mail: ").append(toIndentedString(mail)).append("\n");
		sb.append("    specializzazioni: ").append(toIndentedString(specializzazioni)).append("\n");
		sb.append("    autolimitato: ").append(toIndentedString(autolimitato)).append("\n");
		sb.append("    massimale: ").append(toIndentedString(massimale)).append("\n");
		sb.append("    massimaleDeroga: ").append(toIndentedString(massimaleDeroga)).append("\n");
		sb.append("    massimaleAssistenzaTemporanea: ").append(toIndentedString(massimaleAssistenzaTemporanea))
				.append("\n");
		sb.append("    massimaleInfanzia: ").append(toIndentedString(massimaleInfanzia)).append("\n");
		sb.append("    codiceRegionale: ").append(toIndentedString(codiceRegionale)).append("\n");
		sb.append("    distretto: ").append(toIndentedString(distretto)).append("\n");
		sb.append("    ambito: ").append(toIndentedString(ambito)).append("\n");
		sb.append("    tipologia: ").append(toIndentedString(tipologia)).append("\n");
		sb.append("    asl: ").append(toIndentedString(asl)).append("\n");
		sb.append("    disponibilita: ").append(toIndentedString(disponibilita)).append("\n");
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
