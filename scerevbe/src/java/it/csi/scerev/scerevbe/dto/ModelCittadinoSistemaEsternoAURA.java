/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevbe.dto;

import java.math.BigDecimal;
import java.util.Date;

import it.csi.scerev.scerevserviziesterni.aura.find.DatiAnagrafici;
import it.csi.scerev.scerevserviziesterni.aura.get.SoggettoAuraBodyNew;
import it.csi.scerev.scerevutil.business.Converter;

public class ModelCittadinoSistemaEsternoAURA extends ModelCittadinoSistemaEsterno {
	private BigDecimal idAura;
	private Date dataFineASL;
	private Date dataDecesso;
	
	private String codComuneNascita;
	private String codPronvinciaNascita; 
	
	private Date dataInizioResidenza;
	private String nazioneResidenza;
	private String civicoResidenza;
	
	private String descComuneResidenza;

	private Date dataInizioDomicilio;
	private String codComuneDomicilio;
	private String indirizzoDomicilio;
	private String civicoDomicilio;
	private String capDomicilio;
	
	private String aslAssistenza;
	private String descComune;
	
	private String telefonoResidenza;
	private String telefonoDomicilio;
	
	private Date dataInizioAsl;
	private String tipoProfilo;

	private String descStatoNascita;
	
	public String getAslAssistenza() {
		return aslAssistenza;
	}

	public void setAslAssistenza(String aslAssistenza) {
		this.aslAssistenza = aslAssistenza;
	}

	public Date getDataInizioDomicilio() {
		return dataInizioDomicilio;
	}

	public void setDataInizioDomicilio(Date dataInizioDomicilio) {
		this.dataInizioDomicilio = dataInizioDomicilio;
	}

	public String getCivicoDomicilio() {
		return civicoDomicilio;
	}

	public void setCivicoDomicilio(String civicoDomicilio) {
		this.civicoDomicilio = civicoDomicilio;
	}

	public String getCapDomicilio() {
		return capDomicilio;
	}

	public void setCapDomicilio(String capDomicilio) {
		this.capDomicilio = capDomicilio;
	}

	public String getCodComuneDomicilio() {
		return codComuneDomicilio;
	}

	public void setCodComuneDomicilio(String codComuneDomicilio) {
		this.codComuneDomicilio = codComuneDomicilio;
	}

	public Date getDataInizioResidenza() {
		return dataInizioResidenza;
	}

	public void setDataInizioResidenza(Date dataInizioResidenza) {
		this.dataInizioResidenza = dataInizioResidenza;
	}

	public String getCivicoResidenza() {
		return civicoResidenza;
	}

	public void setCivicoResidenza(String civicoResidenza) {
		this.civicoResidenza = civicoResidenza;
	}

	public String getNazioneResidenza() {
		return nazioneResidenza;
	}

	public String getCodComuneNascita() {
		return codComuneNascita;
	}

	public void setCodComuneNascita(String codComuneNascita) {
		this.codComuneNascita = codComuneNascita;
	}

	public String getCodPronvinciaNascita() {
		return codPronvinciaNascita;
	}

	public void setCodPronvinciaNascita(String codPronvinciaNascita) {
		this.codPronvinciaNascita = codPronvinciaNascita;
	}

	public void setNazioneResidenza(String nazioneResidenza) {
		this.nazioneResidenza = nazioneResidenza;
	}

	public BigDecimal getIdAura() {
		return idAura;
	}

	public void setIdAura(BigDecimal idAura) {
		this.idAura = idAura;
	}

	public Date getDataFineASL() {
		return dataFineASL;
	}

	public void setDataFineASL(Date dataFineASL) {
		this.dataFineASL = dataFineASL;
	}

	public Date getDataDecesso() {
		return dataDecesso;
	}

	public void setDataDecesso(Date dataDecesso) {
		this.dataDecesso = dataDecesso;
	}
	
	

	public String getIndirizzoDomicilio() {
		return indirizzoDomicilio;
	}

	public void setIndirizzoDomicilio(String indirizzoDomicilio) {
		this.indirizzoDomicilio = indirizzoDomicilio;
	}

	public ModelCittadinoSistemaEsternoAURA(DatiAnagrafici in, SoggettoAuraBodyNew sog) {//in = find, sog = anagrafeSanitaria
		super(in);
		Date dataDecesso = sog.getInfoAnag().getDatiPrimari().getDataDecesso() == null ? null : Converter.getData(sog.getInfoAnag().getDatiPrimari().getDataDecesso());
		this.datadecesso = dataDecesso;	
		this.setIdAura(in.getIdProfiloAnagrafico());
		this.setCodComuneNascita(sog.getInfoAnag().getDatiPrimari().getCodComuneNascita());
		this.setComunenasc(in.getComuneNascita());	
		this.descStatoNascita = sog.getInfoAnag().getDatiPrimari().getDescStatoNascita();
		//this.setCodPronvinciaNascita(sog.getInfoAnag().getDatiPrimari().pro);
		
		if(sog.getInfoAnag().getResidenza() != null) {
			this.setNazioneResidenza(sog.getInfoAnag().getResidenza().getCodStato());
			this.setComuneResidenza(sog.getInfoAnag().getResidenza().getCodComune());
			this.setDescComuneResidenza(sog.getInfoAnag().getResidenza().getDescComune());
			this.setIndirizzoResidenza(sog.getInfoAnag().getResidenza().getIndirizzo());
			this.setCivicoResidenza(sog.getInfoAnag().getResidenza().getNumCivico());
			this.setCapResidenza(sog.getInfoAnag().getResidenza().getCap());
			this.setDataInizioResidenza(sog.getInfoAnag().getResidenza().getDataInizio() == null ? null : Converter.getData(sog.getInfoAnag().getResidenza().getDataInizio()));
			this.setTelefonoResidenza(sog.getInfoAnag().getResidenza().getTelefono());
		}
		
		if(sog.getInfoAnag().getDomicilio() != null) {
			this.setCapDomicilio(sog.getInfoAnag().getDomicilio().getCap());
			this.setCodComuneDomicilio(sog.getInfoAnag().getDomicilio().getCodComune());
			this.setDescComune(sog.getInfoAnag().getDomicilio().getDescComune());
			this.setIndirizzoDomicilio(sog.getInfoAnag().getDomicilio().getIndirizzo());
			this.setCivicoDomicilio(sog.getInfoAnag().getDomicilio().getNumCivico());
			this.setDataInizioDomicilio(sog.getInfoAnag().getDomicilio().getDataInizio() == null ? null : Converter.getData(sog.getInfoAnag().getDomicilio().getDataInizio()));
			this.setTelefonoDomicilio(sog.getInfoAnag().getDomicilio().getTelefono());
		}
		
		if(sog.getInfoSan() != null) {			
			this.setAslResidenza(sog.getInfoSan().getAslResidenza());
			this.setAslAssistenza(sog.getInfoSan().getAslAssistenza());
			Date dataInizioASL = sog.getInfoSan().getDataInizioASL() == null ? null : Converter.getData(sog.getInfoSan().getDataInizioASL());
			this.dataInizioAsl = dataInizioASL;
			Date dataFineASL = sog.getInfoSan().getDataFineASL() == null ? null : Converter.getData(sog.getInfoSan().getDataFineASL());
			this.dataFineASL = dataFineASL;
			this.tipoProfilo = sog.getInfoSan().getCodTipoProfiloSanitario();
		}
	}

	public String getDescComune() {
		return descComune;
	}

	public void setDescComune(String descComune) {
		this.descComune = descComune;
	}

	public String getDescComuneResidenza() {
		return descComuneResidenza;
	}

	public void setDescComuneResidenza(String descComuneResidenza) {
		this.descComuneResidenza = descComuneResidenza;
	}

	public String getTelefonoResidenza() {
		return telefonoResidenza;
	}

	public void setTelefonoResidenza(String telefonoResidenza) {
		this.telefonoResidenza = telefonoResidenza;
	}

	public String getTelefonoDomicilio() {
		return telefonoDomicilio;
	}

	public void setTelefonoDomicilio(String telefonoDomicilio) {
		this.telefonoDomicilio = telefonoDomicilio;
	}

	public Date getDataInizioAsl() {
		return dataInizioAsl;
	}

	public void setDataInizioAsl(Date dataInizioAsl) {
		this.dataInizioAsl = dataInizioAsl;
	}
	
	public String getTipoProfilo() {
		return tipoProfilo;
	}

	public void setTipoProfilo(String tipoProfilo) {
		this.tipoProfilo = tipoProfilo;
	}

	public String getDescStatoNascita() {
		return descStatoNascita;
	}

	public void setDescStatoNascita(String descStatoNascita) {
		this.descStatoNascita = descStatoNascita;
	}
	
}
