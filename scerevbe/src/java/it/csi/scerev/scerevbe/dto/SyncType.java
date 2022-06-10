/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevbe.dto;

import java.util.Date;

import it.csi.scerev.scerevserviziesterni.aura.setaggiornamentoprofiloanagrafico.AggiornamentoProfiloAnagrafico;
import it.csi.scerev.scerevserviziesterni.aura.setaggiornamentoprofiloanagrafico.AggiornamentoProfiloAnagraficoAltreInfo;
import it.csi.scerev.scerevserviziesterni.aura.setaggiornamentoprofiloanagrafico.AggiornamentoProfiloAnagraficoDatiPrimari;
import it.csi.scerev.scerevserviziesterni.aura.setaggiornamentoprofiloanagrafico.AggiornamentoProfiloAnagraficoDomicilio;
import it.csi.scerev.scerevserviziesterni.aura.setaggiornamentoprofiloanagrafico.AggiornamentoProfiloAnagraficoResidenza;
import it.csi.scerev.scerevserviziesterni.aura.setinserimentoprofiloanagrafico.InserimentoProfiloAnagrafico;
import it.csi.scerev.scerevutil.business.Converter;

public class SyncType{
	protected AggiornamentoProfiloAnagraficoDatiPrimari datiPrimari;
    protected AggiornamentoProfiloAnagraficoResidenza datiResidenza;
    protected AggiornamentoProfiloAnagraficoDomicilio datiDomicilio;
    protected AggiornamentoProfiloAnagraficoAltreInfo altreInformazioni;
	
	public SyncType(Object obj) {
		
		if (obj instanceof AggiornamentoProfiloAnagrafico) {
			AggiornamentoProfiloAnagrafico modifica = ((AggiornamentoProfiloAnagrafico) obj);
			this.datiDomicilio = modifica.getBody().getDatiDomicilio();
			AggiornamentoProfiloAnagraficoDatiPrimari dati = new AggiornamentoProfiloAnagraficoDatiPrimari();	
			dati.setCodiceCittadinanza(modifica.getBody().getDatiPrimari().getCodiceCittadinanza());
			dati.setCodiceComuneNascitaItaliano(modifica.getBody().getDatiPrimari().getCodiceComuneNascitaItaliano());
			dati.setCognome(modifica.getBody().getDatiPrimari().getCognome());
			dati.setCodiceFiscale(modifica.getBody().getDatiPrimari().getCodiceFiscale());
			dati.setCodiceProvinciaNascita(modifica.getBody().getDatiPrimari().getCodiceProvinciaNascita());
			dati.setCodiceStatoNascita(modifica.getBody().getDatiPrimari().getCodiceStatoNascita());
			dati.setDataDecesso(modifica.getBody().getDatiPrimari().getDataDecesso());
			dati.setDataNascita(modifica.getBody().getDatiPrimari().getDataNascita());
			dati.setDescrizioneComuneNascita(modifica.getBody().getDatiPrimari().getDescrizioneComuneNascita());
			dati.setDescrizioneStatoNascita(modifica.getBody().getDatiPrimari().getDescrizioneStatoNascita());
			dati.setNome(modifica.getBody().getDatiPrimari().getNome());
			dati.setSesso(modifica.getBody().getDatiPrimari().getSesso());
			dati.setIdAura(modifica.getBody().getDatiPrimari().getIdAura());
			this.datiPrimari = dati;
			this.datiResidenza = modifica.getBody().getDatiResidenza();
			this.altreInformazioni = modifica.getBody().getAltreInfo();
		} else if (obj instanceof InserimentoProfiloAnagrafico){
			InserimentoProfiloAnagrafico modifica = ((InserimentoProfiloAnagrafico) obj);
			AggiornamentoProfiloAnagraficoDatiPrimari dati = new AggiornamentoProfiloAnagraficoDatiPrimari();	
			dati.setCodiceCittadinanza(modifica.getBody().getDatiPrimari().getCodiceCittadinanza());
			dati.setCodiceComuneNascitaItaliano(modifica.getBody().getDatiPrimari().getCodiceComuneNascitaItaliano());
			dati.setCognome(modifica.getBody().getDatiPrimari().getCognome());
			dati.setCodiceFiscale(modifica.getBody().getDatiPrimari().getCodiceFiscale());
			dati.setCodiceProvinciaNascita(modifica.getBody().getDatiPrimari().getCodiceProvinciaNascita());
			dati.setCodiceStatoNascita(modifica.getBody().getDatiPrimari().getCodiceStatoNascita());
			dati.setDataDecesso(modifica.getBody().getDatiPrimari().getDataDecesso());
			dati.setDataNascita(modifica.getBody().getDatiPrimari().getDataNascita());
			dati.setDescrizioneComuneNascita(modifica.getBody().getDatiPrimari().getDescrizioneComuneNascita());
			dati.setDescrizioneStatoNascita(modifica.getBody().getDatiPrimari().getDescrizioneStatoNascita());
			dati.setNome(modifica.getBody().getDatiPrimari().getNome());
			dati.setSesso(modifica.getBody().getDatiPrimari().getSesso());
			this.datiPrimari = dati;
			
			AggiornamentoProfiloAnagraficoResidenza residenza = new AggiornamentoProfiloAnagraficoResidenza();
			residenza.setCAPresidenza(modifica.getBody().getDatiResidenza().getCAPresidenza());
			residenza.setCodiceAslResidenza(modifica.getBody().getDatiResidenza().getCodiceAslResidenza());
			residenza.setCodiceComuneResidenza(modifica.getBody().getDatiResidenza().getCodiceComuneResidenza());
			residenza.setCodiceStatoResidenza(modifica.getBody().getDatiResidenza().getCodiceStatoResidenza());
			residenza.setDescrizioneComuneResidenza(modifica.getBody().getDatiResidenza().getDescrizioneComuneResidenza());
			residenza.setIndirizzoResidenza(modifica.getBody().getDatiResidenza().getIndirizzoResidenza());
			residenza.setNumerocivicoResidenza(modifica.getBody().getDatiResidenza().getNumerocivicoResidenza());
			residenza.setTelefonoResidenza(modifica.getBody().getDatiResidenza().getTelefonoResidenza());
			this.datiResidenza = residenza;
			
			AggiornamentoProfiloAnagraficoAltreInfo altreInfo = new AggiornamentoProfiloAnagraficoAltreInfo();
			altreInfo.setDataInizioResidenza(modifica.getBody().getAltreInfo() == null ? modifica.getBody().getAltreInfo().getDataInizioResidenza() : Converter.getXMLGregorianCalendar(new Date()));
			altreInfo.setDataInizioDomicilio(modifica.getBody().getAltreInfo() == null ? modifica.getBody().getAltreInfo().getDataInizioDomicilio() : Converter.getXMLGregorianCalendar(new Date()));
			altreInfo.setStatoContatto("3"); //3 = Verificato

			AggiornamentoProfiloAnagraficoDomicilio domicilio = new AggiornamentoProfiloAnagraficoDomicilio();			
			domicilio.setCodiceComuneDomicilio(modifica.getBody().getDatiDomicilio().getCodiceComuneDomicilio());
			domicilio.setDescrizioneComuneDomicilio(modifica.getBody().getDatiDomicilio().getDescrizioneComuneDomicilio());
			domicilio.setIndirizzoDomicilio(modifica.getBody().getDatiDomicilio().getIndirizzoDomicilio());
			domicilio.setNumerocivicoDomicilio(modifica.getBody().getDatiDomicilio().getNumerocivicoDomicilio());
			domicilio.setCAPDomicilio(modifica.getBody().getDatiDomicilio().getCAPDomicilio());
			domicilio.setCodiceStatoDomicilio(modifica.getBody().getDatiDomicilio().getCodiceStatoDomicilio());
			domicilio.setCodiceAslDomicilio(modifica.getBody().getDatiDomicilio().getCodiceAslDomicilio());
			
			this.datiDomicilio = domicilio;			
			this.altreInformazioni = altreInfo;
		}
	}

	public AggiornamentoProfiloAnagraficoDatiPrimari getDatiPrimari() {
		return datiPrimari;
	}

	public void setDatiPrimari(AggiornamentoProfiloAnagraficoDatiPrimari datiPrimari) {
		this.datiPrimari = datiPrimari;
	}

	public AggiornamentoProfiloAnagraficoResidenza getDatiResidenza() {
		return datiResidenza;
	}

	public void setDatiResidenza(AggiornamentoProfiloAnagraficoResidenza datiResidenza) {
		this.datiResidenza = datiResidenza;
	}

	public AggiornamentoProfiloAnagraficoDomicilio getDatiDomicilio() {
		return datiDomicilio;
	}

	public void setDatiDomicilio(AggiornamentoProfiloAnagraficoDomicilio datiDomicilio) {
		this.datiDomicilio = datiDomicilio;
	}

	public AggiornamentoProfiloAnagraficoAltreInfo getAltreInformazioni() {
		return altreInformazioni;
	}

	public void setAltreInformazioni(AggiornamentoProfiloAnagraficoAltreInfo altreInformazioni) {
		this.altreInformazioni = altreInformazioni;
	}	

	
}
