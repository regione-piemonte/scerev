/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevboweb.dto;

import java.util.Date;

import it.csi.scerev.scerevserviziesterni.aura.find.DatiAnagrafici;
import it.csi.scerev.scerevserviziesterni.aura.get.SoggettoAuraBodyNew;
import it.csi.scerev.scerevserviziesterni.ensemble.interrogamefscerev.InterrogaMefScerevResult;
import it.csi.scerev.scerevutil.business.Converter;

public class Cittadino {
	protected String codiceFiscale;
    protected String cognome;
    protected String nome;
    protected String sesso;  
    protected Date dataNascita;
    protected String codiceStatoNascita;
    protected String statoNascita;
    protected String comuneNascita;
    protected String codiceComuneNascita;
    protected String provinciaNascita;    
    protected Date dataInizioSSN;
    protected Date dataInizioAssistenza;
    protected Date dataInizioResidenza;


	public Cittadino(DatiAnagrafici cittadinoAura, SoggettoAuraBodyNew cittadinoAuraExtra) {
		this.codiceFiscale = cittadinoAura.getCodiceFiscale();
		this.cognome = cittadinoAura.getCognome();
		this.nome = cittadinoAura.getNome();
		this.sesso = cittadinoAura.getSesso();
		this.dataNascita = Converter.getData(cittadinoAura.getDataNascita());
		this.codiceStatoNascita = cittadinoAura.getCodiceStatoNascita();
		this.statoNascita = cittadinoAura.getStatoNascita();
		this.comuneNascita = cittadinoAura.getComuneNascita();
		this.codiceComuneNascita = cittadinoAura.getCodiceComuneNascita();
		this.provinciaNascita = cittadinoAura.getProvinciaNascita();
		this.dataInizioSSN = Converter.getData(cittadinoAuraExtra.getInfoSan() == null ? null : cittadinoAuraExtra.getInfoSan().getDataInizioSSN());
		this.dataInizioAssistenza = Converter.getData(cittadinoAuraExtra.getInfoSan() == null ? null : cittadinoAuraExtra.getInfoSan().getDataInizioASL()); 
		this.dataInizioResidenza = cittadinoAuraExtra.getInfoAnag().getResidenza() != null ? Converter.getData(cittadinoAuraExtra.getInfoAnag().getResidenza().getDataInizio()) : null;
	}
	
	public Cittadino(FakeCittadinoMefScerev cittadinoScerev) {
	}

	public Cittadino(InterrogaMefScerevResult cittadinoMef) {
		this.codiceFiscale = cittadinoMef.getCodicefiscale();
		this.cognome = cittadinoMef.getCognome();
		this.nome = cittadinoMef.getNome();
		this.sesso = cittadinoMef.getSesso();
		this.dataNascita = Converter.getData(cittadinoMef.getDatanascita());
		this.codiceStatoNascita = cittadinoMef.getIdStatonascita().substring(0, 2);
		this.statoNascita = cittadinoMef.getNazionenasc();
		this.comuneNascita = cittadinoMef.getComunenasc();
		this.codiceComuneNascita = "001272"; //TODO Mockato
		this.provinciaNascita = cittadinoMef.getProvincianasc();
		this.dataInizioSSN = Converter.getData("1970-01-01"); //TODO Mockato
		this.dataInizioAssistenza = null;
		this.dataInizioResidenza = null;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}
	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSesso() {
		return sesso;
	}
	public void setSesso(String sesso) {
		this.sesso = sesso;
	}
	public Date getDataNascita() {
		return dataNascita;
	}
	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}
	public String getCodiceStatoNascita() {
		return codiceStatoNascita;
	}
	public void setCodiceStatoNascita(String codiceStatoNascita) {
		this.codiceStatoNascita = codiceStatoNascita;
	}
	public String getStatoNascita() {
		return statoNascita;
	}
	public void setStatoNascita(String statoNascita) {
		this.statoNascita = statoNascita;
	}
	public String getComuneNascita() {
		return comuneNascita;
	}
	public void setComuneNascita(String comuneNascita) {
		this.comuneNascita = comuneNascita;
	}
	public String getCodiceComuneNascita() {
		return codiceComuneNascita;
	}
	public void setCodiceComuneNascita(String codiceComuneNascita) {
		this.codiceComuneNascita = codiceComuneNascita;
	}
	public String getProvinciaNascita() {
		return provinciaNascita;
	}
	public void setProvinciaNascita(String provinciaNascita) {
		this.provinciaNascita = provinciaNascita;
	}
	public Date getDataInizioSSN() {
		return dataInizioSSN;
	}
	public void setDataInizioSSN(Date dataInizioSSN) {
		this.dataInizioSSN = dataInizioSSN;
	}
    
	public Date getDataInizioAssistenza() {
		return dataInizioAssistenza;
	}

	public void setDataInizioAssistenza(Date dataInizioAssistenza) {
		this.dataInizioAssistenza = dataInizioAssistenza;
	}

	public Date getDataInizioResidenza() {
		return dataInizioResidenza;
	}

	public void setDataInizioResidenza(Date dataInizioResidenza) {
		this.dataInizioResidenza = dataInizioResidenza;
	}

	
}
