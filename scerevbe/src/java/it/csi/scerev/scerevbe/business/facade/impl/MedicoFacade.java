/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevbe.business.facade.impl;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import it.csi.scerev.scerevbe.business.facade.interfaces.MedicoFacadeIf;
import it.csi.scerev.scerevbe.business.serviziesterni.ServiziEsterniClient;
import it.csi.scerev.scerevbe.dto.ModelAmbulatorioLoccsiMedico;
import it.csi.scerev.scerevbe.dto.ModelDisponibilita;
import it.csi.scerev.scerevbe.util.Constants;
import it.csi.scerev.scerevjpa.business.dao.interfaces.DataDaoIf;
import it.csi.scerev.scerevjpa.business.entity.ScerevLAudit;
import it.csi.scerev.scerevserviziesterni.aura.get.InfoSanitarie;
import it.csi.scerev.scerevserviziesterni.aura.get.SoggettoAuraBodyNew;
import it.csi.scerev.scerevserviziesterni.aura.getnumeroassistitiincaricomedico.NumeroAssistitiInCaricoMedicoResponse;
import it.csi.scerev.scerevserviziesterni.aura.setprofilosanitario.DatiIscrizioneSanitaria;
import it.csi.scerev.scerevserviziesterni.aura.setprofilosanitario.ProfiloSanitario;
import it.csi.scerev.scerevserviziesterni.aura.setprofilosanitario.ProfiloSanitarioBody;
import it.csi.scerev.scerevserviziesterni.aura.setprofilosanitario.ProfiloSanitarioHeader;
import it.csi.scerev.scerevutil.business.Converter;

public class MedicoFacade implements MedicoFacadeIf{
	private DataDaoIf dataDao;
	
	public DataDaoIf getDataDao() {
		return dataDao;
	}

	public void setDataDao(DataDaoIf dataDao) {
		this.dataDao = dataDao;
	}

	/**
	 * Calcolo massimali medico
	 * @throws Exception 
	 * @throws IllegalAccessException 
	 * @throws  
	 */
	public ModelDisponibilita getMassimaliMedico(ModelAmbulatorioLoccsiMedico singleModel, ScerevLAudit audit) throws Exception {
		NumeroAssistitiInCaricoMedicoResponse ritorno = ServiziEsterniClient.getInstance().getNumeroAssistitiInCaricoMedico(new BigDecimal(singleModel.getId()), audit);
		if(ritorno.getAssistitiDomiciliati() == null && ritorno.getAssistitiDomiciliatiComunitari() == null && ritorno.getAssistitiExtraComunitari() == null && ritorno.getAssistitiNeonati() == null && ritorno.getAssistitiTemporanei() == null)
			return null;
		int S1 = singleModel.getMassimale() != null ? singleModel.getMassimale() : Constants.MASSIMALE_ILLIMITATO;
		int S2 = singleModel.getMassimaleDeroga() != null ? singleModel.getMassimaleDeroga() : Constants.MASSIMALE_ILLIMITATO;
		int S3 = singleModel.getMassimaleAssistenzaTemporanea() != null ? singleModel.getMassimaleAssistenzaTemporanea() : Constants.MASSIMALE_ILLIMITATO ;
		//int S4 = singleModel.getMassimaleInfanzia() != null ? singleModel.getMassimaleInfanzia() : Constants.MASSIMALE_ILLIMITATO;
		
		boolean A = singleModel.isAutolimitato();
		
		int P1 = ritorno.getAssistitiTemporanei() != null ? ritorno.getAssistitiTemporanei().intValue() : 0;
		int P2 = ritorno.getAssistitiNetti() != null ? ritorno.getAssistitiNetti().intValue() : 0;
		//int P3 = ritorno.getAssistitiNeonati() != null ? ritorno.getAssistitiNeonati().intValue() : 0;
				
		int D1 = S3 - P1; // Disponibilità Ass. Temporanea
		int D2 = S1 - P2; // Disponibilità Ass. Illimitata
		int D3 = S2 - P2; // Disponibilità Ass. Illimitata per ricongiungimento
		//int D4 = S4 - P3; // Disponibilità Ass. illimitata per fascia età
		
		boolean f1 = (D1 > 0) ? true : false; // Selezionabile assistenza temporanea
		boolean f2 = (D2 > 0) ? true : false; // Selezionabile
		boolean f3 = ((D3 > 0 && !A) || (D2 > 0 && A)) ? true : false; // Selezionabile ricongiungimento familiare
		//boolean f4 = (D4 > 0) ? true : false; // Selezionabile infanzia
		boolean f5 = (!f2) ? true : false; // Monitorabile
		
		ModelDisponibilita disp = new ModelDisponibilita();
		disp.setSelezionabileAssistenzaTemporanea(f1);
		disp.setSelezionabile(f2);
		disp.setSelezionabileRicongiungimentoFamiliare(f3);
		//disp.setSelezionabileInfanzia(f4);
		disp.setSelezionabileInfanzia(true); //TODO momentaneamente fisso a true  
		disp.setMonitorabile(f5);		
		
		disp.setMedicoId(singleModel.getId());
		disp.setMedicoCodiceFiscale(singleModel.getCodiceFiscale());
		return disp;
	}

	@Override
	public ProfiloSanitario costruisciReqSetProfiloSanitario(String citId, SoggettoAuraBodyNew sog,
			String tipoMov, String motivo) throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		ProfiloSanitario request = new ProfiloSanitario();
		InfoSanitarie infoSan = sog.getInfoSan();
		request.setHeader(new ProfiloSanitarioHeader());
		request.getHeader().setIdAsr(infoSan.getAslAssistenza());
		request.getHeader().setRequestDateTime(Converter.getData(new Date(), "yyyy-MM-dd HH:mm:ss"));
		request.getHeader().setSender(Constants.AURA_COMPONENT_NAME);
		request.getHeader().setCfUtilizzatoreServizio(citId);
		
		request.setBody(new ProfiloSanitarioBody());
		request.getBody().setIdAuraAssistito(sog.getIdAura().toString());
		request.getBody().setCfAuraAssistito(sog.getInfoAnag().getDatiPrimari().getCodiceFiscale());
		request.getBody().setIdAuraMedico(infoSan.getIdMedico().toString());
		request.getBody().setCfAuraMedico(infoSan.getCodiceFiscaleMedico());
		request.getBody().setTipoMovimento(tipoMov);
		request.getBody().setRicongiungimentoFam(0L);
		
		request.getBody().setDatiIscrizioneSanitaria(new DatiIscrizioneSanitaria());
		request.getBody().getDatiIscrizioneSanitaria().setDataInizioASL(null);
		request.getBody().getDatiIscrizioneSanitaria().setDataFineASL(null);
		request.getBody().getDatiIscrizioneSanitaria().setCodTipoProfiloSanitario(null);
		request.getBody().getDatiIscrizioneSanitaria().setDataDecorrenza(Converter.getXMLGregorianCalendar(sdf.parse(Converter.getData(new Date(System.currentTimeMillis())))));
		request.getBody().getDatiIscrizioneSanitaria().setDataFineSospensione(null);
		request.getBody().getDatiIscrizioneSanitaria().setSimulazione(0L);
		request.getBody().getDatiIscrizioneSanitaria().setDerogaPediatra(0L);
		request.getBody().getDatiIscrizioneSanitaria().setMMG(0L);
		request.getBody().getDatiIscrizioneSanitaria().setDerogaAmbitoTerritoriale(0L);
		request.getBody().getDatiIscrizioneSanitaria().setDerogaAssociazione(0L);
		request.getBody().getDatiIscrizioneSanitaria().setDerogaMassimale(0L);
		request.getBody().getDatiIscrizioneSanitaria().setDerogaMassimaleTemporaneo(0L);
		request.getBody().getDatiIscrizioneSanitaria().setNote(getStringaMotivoRevoca(motivo));
		
		return request;
	}

	private String getStringaMotivoRevoca(String motivo) {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String s = dateFormat.format(System.currentTimeMillis());
		s = s.concat(" Motivo revoca: ");
		s = s.concat(motivo);
		return s;
	}
	
}
