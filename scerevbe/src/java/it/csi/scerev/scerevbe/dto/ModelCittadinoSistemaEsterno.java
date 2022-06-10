/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevbe.dto;

import java.util.Date;

import it.csi.scerev.scerevserviziesterni.aura.find.DatiAnagrafici;
import it.csi.scerev.scerevserviziesterni.ensemble.interrogamefscerev.InterrogaMefScerevResult;
import it.csi.scerev.scerevutil.business.Converter;

public class ModelCittadinoSistemaEsterno {
	
	protected String capResidenza;
	protected String capDomicilio;
	protected String codicefiscale;
	protected String cognome;
	protected String comuneResidenza;
	protected String comuneDomicilio;
	protected String comunenasc;
	protected Date datadecesso;
	protected Date datanascita;
	protected String idComunenascita;
	protected String idStatonascita;
	protected String indirizzoResidenza;
	protected String nazionenasc;
	protected String nome;
	protected String provinciaResidenza;
	protected String provincianasc;
	protected String provnasc;
	protected String sesso;
	
	protected String aslResidenza;
	protected String aslIscrizione;
	protected String aslDomicilio;
	
	public String getCapResidenza() {
		return capResidenza;
	}
	public void setCapResidenza(String capResidenza) {
		this.capResidenza = capResidenza;
	}
	public String getCodicefiscale() {
		return codicefiscale;
	}
	public void setCodicefiscale(String codicefiscale) {
		this.codicefiscale = codicefiscale;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getComuneResidenza() {
		return comuneResidenza;
	}
	public void setComuneResidenza(String comuneResidenza) {
		this.comuneResidenza = comuneResidenza;
	}
	public String getComunenasc() {
		return comunenasc;
	}
	public void setComunenasc(String comunenasc) {
		this.comunenasc = comunenasc;
	}
	public Date getDatadecesso() {
		return datadecesso;
	}
	public void setDatadecesso(Date datadecesso) {
		this.datadecesso = datadecesso;
	}
	public Date getDatanascita() {
		return datanascita;
	}
	public void setDatanascita(Date datanascita) {
		this.datanascita = datanascita;
	}
	public String getIdComunenascita() {
		return idComunenascita;
	}
	public void setIdComunenascita(String idComunenascita) {
		this.idComunenascita = idComunenascita;
	}
	public String getIdStatonascita() {
		return idStatonascita;
	}
	public void setIdStatonascita(String idStatonascita) {
		this.idStatonascita = idStatonascita;
	}
	public String getIndirizzoResidenza() {
		return indirizzoResidenza;
	}
	public void setIndirizzoResidenza(String indirizzoResidenza) {
		this.indirizzoResidenza = indirizzoResidenza;
	}
	public String getNazionenasc() {
		return nazionenasc;
	}
	public void setNazionenasc(String nazionenasc) {
		this.nazionenasc = nazionenasc;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getProvinciaResidenza() {
		return provinciaResidenza;
	}
	public void setProvinciaResidenza(String provinciaResidenza) {
		this.provinciaResidenza = provinciaResidenza;
	}
	public String getProvincianasc() {
		return provincianasc;
	}
	public void setProvincianasc(String provincianasc) {
		this.provincianasc = provincianasc;
	}
	public String getProvnasc() {
		return provnasc;
	}
	public void setProvnasc(String provnasc) {
		this.provnasc = provnasc;
	}
	public String getSesso() {
		return sesso;
	}
	public void setSesso(String sesso) {
		this.sesso = sesso;
	}
	
	public String getAslResidenza() {
		return aslResidenza;
	}
	public void setAslResidenza(String aslResidenza) {
		this.aslResidenza = aslResidenza;
	}
	public String getAslIscrizione() {
		return aslIscrizione;
	}
	public void setAslIscrizione(String aslIscrizione) {
		this.aslIscrizione = aslIscrizione;
	}
	public String getAslDomicilio() {
		return aslDomicilio;
	}
	public void setAslDomicilio(String aslDomicilio) {
		this.aslDomicilio = aslDomicilio;
	}
	
	
	public String getCapDomicilio() {
		return capDomicilio;
	}
	public void setCapDomicilio(String capDomicilio) {
		this.capDomicilio = capDomicilio;
	}
	
	public String getComuneDomicilio() {
		return comuneDomicilio;
	}
	public void setComuneDomicilio(String comuneDomicilio) {
		this.comuneDomicilio = comuneDomicilio;
	}
	public ModelCittadinoSistemaEsterno(DatiAnagrafici in) {
		this.cognome = in.getCognome();
		this.nome = in.getNome();
		this.codicefiscale = in.getCodiceFiscale();
		this.sesso = in.getSesso();
		this.datanascita = Converter.getData(in.getDataNascita());
		this.idStatonascita = in.getCodiceStatoNascita();
		this.comunenasc = in.getComuneNascita();
		this.provincianasc = in.getProvinciaNascita();
		this.datadecesso = Converter.getData(in.getDataDecesso());
		this.idComunenascita = in.getCodiceComuneNascita();
	}
	public ModelCittadinoSistemaEsterno(InterrogaMefScerevResult in) {
		this.cognome = in.getCognome();
		this.nome = in.getNome();
		this.codicefiscale = in.getCodicefiscale();
		this.sesso = in.getSesso();
		this.datanascita = Converter.getData(in.getDatanascita());
		this.idStatonascita = in.getIdStatonascita();
		this.comunenasc = in.getComunenasc();
		this.provincianasc = in.getProvincianasc();
		this.datadecesso = Converter.getData(in.getDatadecesso());
		this.idComunenascita = in.getIdComunenascita();
	}
}
