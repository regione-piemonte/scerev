/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevbe.dto;

import java.math.BigDecimal;

public class AggiornamentoProfiloAnagraficoIn {
	private String idUser;
	private BigDecimal idAura;
	private String idNazioneCittadinanza;
	private DatiCodiceFiscale datiCodiceFiscale;
	private DatiPrimari datiPrimari;
	private DatiResidenzaDomicilio datiResidenza;
	private DatiResidenzaDomicilio datiDomicilio;
	private DatiTesseraTeam datiTesseraTeam;
	private DatiDecesso datiDecesso;
	private DatiAltroRecapito datiAltroRecapito;
	private ElencoAltriDati elencoAltriDati;
	
	public String getIdUser() {
		return idUser;
	}
	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}
	public BigDecimal getIdAura() {
		return idAura;
	}
	public void setIdAura(BigDecimal idAura) {
		this.idAura = idAura;
	}
	public String getIdNazioneCittadinanza() {
		return idNazioneCittadinanza;
	}
	public void setIdNazioneCittadinanza(String idNazioneCittadinanza) {
		this.idNazioneCittadinanza = idNazioneCittadinanza;
	}
	public DatiCodiceFiscale getDatiCodiceFiscale() {
		return datiCodiceFiscale;
	}
	public void setDatiCodiceFiscale(DatiCodiceFiscale datiCodiceFiscale) {
		this.datiCodiceFiscale = datiCodiceFiscale;
	}
	public DatiPrimari getDatiPrimari() {
		return datiPrimari;
	}
	public void setDatiPrimari(DatiPrimari datiPrimari) {
		this.datiPrimari = datiPrimari;
	}
	public DatiResidenzaDomicilio getDatiResidenza() {
		return datiResidenza;
	}
	public void setDatiResidenza(DatiResidenzaDomicilio datiResidenza) {
		this.datiResidenza = datiResidenza;
	}
	public DatiResidenzaDomicilio getDatiDomicilio() {
		return datiDomicilio;
	}
	public void setDatiDomicilio(DatiResidenzaDomicilio datiDomicilio) {
		this.datiDomicilio = datiDomicilio;
	}
	public DatiTesseraTeam getDatiTesseraTeam() {
		return datiTesseraTeam;
	}
	public void setDatiTesseraTeam(DatiTesseraTeam datiTesseraTeam) {
		this.datiTesseraTeam = datiTesseraTeam;
	}
	public DatiDecesso getDatiDecesso() {
		return datiDecesso;
	}
	public void setDatiDecesso(DatiDecesso datiDecesso) {
		this.datiDecesso = datiDecesso;
	}
	public DatiAltroRecapito getDatiAltroRecapito() {
		return datiAltroRecapito;
	}
	public void setDatiAltroRecapito(DatiAltroRecapito datiAltroRecapito) {
		this.datiAltroRecapito = datiAltroRecapito;
	}
	public ElencoAltriDati getElencoAltriDati() {
		return elencoAltriDati;
	}
	public void setElencoAltriDati(ElencoAltriDati elencoAltriDati) {
		this.elencoAltriDati = elencoAltriDati;
	}
	
//	public AggiornamentoProfiloAnagraficoIn(
//			BigDecimal idAura, 
//			ModelCittadinoResidenza residenza,
//			ModelCittadinoDomicilio domicilio) {
//		this.idAura = idAura;
//		this.datiResidenza = residenza == null ? null : new DatiResidenzaDomicilio(residenza);
//		this.datiDomicilio = datiDomicilio == null ? null : new DatiResidenzaDomicilio(domicilio);
//	}
	
	public AggiornamentoProfiloAnagraficoIn () {}
	
	public AggiornamentoProfiloAnagraficoIn(ModelCittadinoSistemaEsterno da) {
		da.getAslDomicilio();
		da.getAslIscrizione();
		da.getAslResidenza();
		da.getCapResidenza();
		da.getCodicefiscale();
		da.getCognome();
		da.getComunenasc();
		da.getComuneResidenza();
		da.getDatadecesso();
		da.getDatanascita();
		da.getIdComunenascita();
		da.getIdStatonascita();
		da.getIndirizzoResidenza();
		da.getNazionenasc();
		da.getNome();
		da.getProvincianasc();
		da.getProvinciaResidenza();
		da.getProvnasc();
		da.getSesso();
	}
}
