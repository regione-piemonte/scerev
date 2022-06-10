/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevbe.dto;

import java.util.Date;

import it.csi.scerev.scerevbe.util.Constants;
import it.csi.scerev.scerevserviziesterni.aura.find.DatiAnagrafici;
import it.csi.scerev.scerevserviziesterni.aura.get.SoggettoAuraBodyNew;
import it.csi.scerev.scerevserviziesterni.ensemble.interrogamefscerev.InterrogaMefScerevResult;
import it.csi.scerev.scerevutil.business.Converter;
import it.csi.scerev.scerevutil.business.SharedConstants;

public class ModelCittadinoInfoCustom extends ModelCittadinoInfo  {

	public ModelCittadinoInfoCustom(DatiAnagrafici da, SoggettoAuraBodyNew sog) {
		super();
		this.setIdAura(da.getIdProfiloAnagrafico());
		this.setCodiceFiscale(da.getCodiceFiscale());
		this.setNome(da.getNome());
		this.setCognome(da.getCognome());
		this.setDataNascita(Converter.getData(da.getDataNascita()));
		this.setSesso(da.getSesso());
		this.setComuneNascita(da.getComuneNascita());
		
		this.setResidenza(new ModelCittadinoResidenza());
		if(sog.getInfoAnag().getResidenza() != null) {
			this.getResidenza().setCap(sog.getInfoAnag().getResidenza().getCap());
			this.getResidenza().setCivico(sog.getInfoAnag().getResidenza().getNumCivico());
			this.getResidenza().setIndirizzo(sog.getInfoAnag().getResidenza().getIndirizzo());
			this.getResidenza().setComune(sog.getInfoAnag().getResidenza().getDescComune());
			this.getResidenza().setNazione(sog.getInfoAnag().getResidenza().getDescStato());
			this.getResidenza().setCodComune(sog.getInfoAnag().getResidenza().getCodComune());
//			this.getResidenza().setRegione(da.get);
		}
		
		this.setDomicilio(new ModelCittadinoDomicilio());
		if(sog.getInfoAnag().getDomicilio() != null) {
			this.getDomicilio().setCap(sog.getInfoAnag().getDomicilio().getCap());
			this.getDomicilio().setCivico(sog.getInfoAnag().getDomicilio().getNumCivico());
			this.getDomicilio().setIndirizzo(sog.getInfoAnag().getDomicilio().getIndirizzo());
			this.getDomicilio().setComune(sog.getInfoAnag().getDomicilio().getDescComune());
			this.getDomicilio().setCodComune(sog.getInfoAnag().getDomicilio().getCodComune());
		}
		
		this.setDataInizioAssistenza(sog.getInfoSan().getDataInizioASL() == null ? null : Converter.getData(Converter.getDataToString(sog.getInfoSan().getDataInizioASL())));
		Date fineAsl = sog.getInfoSan().getDataFineASL() == null || Converter.getData(Converter.getDataToString(sog.getInfoSan().getDataFineASL())).equals(Converter.getData(SharedConstants.DATA_ILLIMITATA)) ? null : Converter.getData(sog.getInfoSan().getDataFineASL());
		this.setDataFineAssistenza(fineAsl);

		if(sog.getInfoSan().getTipoMovimento() != null) { 
			if(sog.getInfoSan().getTipoMovimento().equalsIgnoreCase(SharedConstants.SCELTA)) {
				this.setDataInizioAssistenzaMedico(sog.getInfoSan().getDataDecorrenza() == null ? null : Converter.getData(sog.getInfoSan().getDataDecorrenza()));			
			} else if(sog.getInfoSan().getTipoMovimento().equalsIgnoreCase(SharedConstants.REVOCA)) {
				this.setDataRevocaMedico(sog.getInfoSan().getDataDecorrenza() == null ? null : Converter.getData(sog.getInfoSan().getDataDecorrenza()));			
			}
		}
		this.setDataRevocaAssistenza(fineAsl);
		Date today = new Date();//Lo stato attuale dell’assistenza (es: 'ATTIVA' o 'NON ATTIVA'). ATTIVA se la data di sistema e' posteriore a DataInizioASL e anteriore a DataFineASL.NON ATTIVA altrimenti.
		String statoAssistenza = (sog.getInfoSan().getDataInizioASL() == null && sog.getInfoSan().getDataFineASL() == null) ? null : (today.compareTo(Converter.getData(sog.getInfoSan().getDataInizioASL())) > 0 && today.compareTo(Converter.getData(sog.getInfoSan().getDataFineASL())) < 0) ? Constants.STATO_ASSISTENZA_ATTIVA : Constants.STATO_ASSISTENZA_NON_ATTIVA;
		this.setStatoAssistenza(statoAssistenza);
		this.setCittadinanza(new ModelCittadinanza());
		this.getCittadinanza().setDescrizione(sog.getInfoAnag().getDatiPrimari().getDescCittadinanza());
		this.getCittadinanza().setId(sog.getInfoAnag().getDatiPrimari().getCodCittadinanza());
		this.setAslAssistenza(new ModelAsl());
		this.getAslAssistenza().setId(sog.getInfoSan().getAslAssistenza());
		this.getAslAssistenza().setDescrizione(null);//letto da DB all'uscita dal costruttore
		this.setMedico(new ModelCittadinoInfoMedico());
		if(this.getDataRevocaMedico() == null) {
			this.getMedico().setId(sog.getInfoSan().getIdMedico() == null ? null : sog.getInfoSan().getIdMedico().toString());
			this.getMedico().setCodiceFiscale(sog.getInfoSan().getCodiceFiscaleMedico() == null ? null :sog.getInfoSan().getCodiceFiscaleMedico());
		}
	
		this.setMedicoAltraRegione(false); //calcolato eventualmente all'uscita dal costruttore
		
		ModelCittadinoRecapiti recapiti = new ModelCittadinoRecapiti();
		recapiti.setTelefono(sog.getInfoAnag() != null && sog.getInfoAnag().getResidenza() != null ?sog.getInfoAnag().getResidenza().getTelefono() : null);
		this.setRecapiti(recapiti);
	}

	public ModelCittadinoInfoCustom(InterrogaMefScerevResult soggTS) {
		super();
		this.setCodiceFiscale(soggTS.getCodicefiscale());
		this.setNome(soggTS.getNome());
		this.setCognome(soggTS.getCognome());
		this.setDataNascita(Converter.getData(soggTS.getDatanascita()));
		this.setSesso(soggTS.getSesso());
		this.setComuneNascita(soggTS.getComunenasc());				
		
		this.setResidenza(new ModelCittadinoResidenza());
		this.getResidenza().setCap(soggTS.getCapResidenza());
		//this.getResidenza().setCivico(soggTS.geti);
		this.getResidenza().setIndirizzo(soggTS.getIndirizzoResidenza());
		this.getResidenza().setComune(soggTS.getComuneResidenza());
//		this.getResidenza().setNazione(soggTS.getna);
//		this.getResidenza().setCodComune(soggTS.getcom);
		
//		this.setDomicilio(new ModelCittadinoDomicilio());
//		this.getDomicilio().setCap(soggTS.);
//		this.getDomicilio().setCivico(sog.getInfoAnag().getDomicilio().getNumCivico());
//		this.getDomicilio().setIndirizzo(sog.getInfoAnag().getDomicilio().getIndirizzo());
//		this.getDomicilio().setComune(sog.getInfoAnag().getDomicilio().getDescComune());
//		this.getDomicilio().setCodComune(sog.getInfoAnag().getDomicilio().getCodComune());
		
//		this.setDataInizioAssistenza(Converter.getData(soggTS.getdata));
//		this.setDataFineAssistenza(Converter.getData(sog.getInfoSan().getDataFineASL()));
//		this.setDataInizioAssistenzaMedico(Converter.getData(sog.getInfoSan().getDataDecorrenza()));
//		this.setDataRevocaMedico(Converter.getData(sog.getInfoSan().getDataDecorrenza()));
//		this.setDataRevocaAssistenza(Converter.getData(sog.getInfoSan().getDataFineASL()));
//		Date today = new Date();//Lo stato attuale dell’assistenza (es: 'ATTIVA' o 'NON ATTIVA'). ATTIVA se la data di sistema e' posteriore a DataInizioASL e anteriore a DataFineASL.NON ATTIVA altrimenti.
//		String statoAssistenza = (sog.getInfoSan().getDataInizioASL() == null && sog.getInfoSan().getDataFineASL() == null) ? null : (today.compareTo(Converter.getData(sog.getInfoSan().getDataInizioASL())) > 0 && today.compareTo(Converter.getData(sog.getInfoSan().getDataFineASL())) < 0) ? Constants.STATO_ASSISTENZA_ATTIVA : Constants.STATO_ASSISTENZA_NON_ATTIVA;
//		this.setStatoAssistenza(statoAssistenza);
		this.setCittadinanza(new ModelCittadinanza());
//		this.getCittadinanza().setDescrizione(soggTS.asl;
//		this.getCittadinanza().setId(sog.getInfoAnag().getDatiPrimari().getCodCittadinanza());
//		this.setAslAssistenza(new ModelAsl());
//		this.getAslAssistenza().setId(sog.getInfoSan().getAslAssistenza());
//		this.getAslAssistenza().setDescrizione(null);//letto da DB all'uscita dal costruttore
//		this.setMedico(new ModelCittadinoInfoMedico());
//		this.getMedico().setId(sog.getInfoSan().getIdMedico() == null ? null : sog.getInfoSan().getIdMedico().toString());
//		this.getMedico().setCodiceFiscale(sog.getInfoSan().getCodiceFiscaleMedico());
	}

}

