/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevbe.business.facade.impl;

import java.util.HashMap;
import java.util.List;

import it.csi.scerev.scerevbe.business.facade.interfaces.ControlloFacadeIf;
import it.csi.scerev.scerevbe.dto.ModelAmbulatorioLoccsiMedico;
import it.csi.scerev.scerevbe.dto.Payload;
import it.csi.scerev.scerevbe.dto.Payload1;
import it.csi.scerev.scerevbe.dto.Payload2;
import it.csi.scerev.scerevbe.dto.Payload3;
import it.csi.scerev.scerevbe.dto.Payload4;
import it.csi.scerev.scerevjpa.business.dao.interfaces.DataDaoIf;
import it.csi.scerev.scerevutil.business.Checker;
import it.csi.scerev.scerevutil.business.Converter;
import it.csi.scerev.scerevutil.business.exception.IntegritaException;
import it.csi.scerev.scerevutil.business.exception.ParametriObbligatoriException;

public class ControlloFacade implements ControlloFacadeIf{
	private DataDaoIf dataDao;
	
	public DataDaoIf getDataDao() {
		return dataDao;
	}

	public void setDataDao(DataDaoIf dataDao) {
		this.dataDao = dataDao;
	}
	
	private String isTST() {
		return dataDao.getMessaggioPerCodice(it.csi.scerev.scerevutil.business.SharedConstants.TST).getValoreMsg();
	}	
	
	public void chkDatiObbligatoriInformazioniAssistito(String shibIdentitaCodiceFiscale, String xRequestId,
			String xForwardedFor, String xCodiceServizio, String citId) throws ParametriObbligatoriException {
		String outValidation = Checker.checkObbligatori(getMapCommonData(shibIdentitaCodiceFiscale, xRequestId, xForwardedFor, xCodiceServizio, citId));
		if (outValidation != null)
			throw new ParametriObbligatoriException(outValidation);
	}

	private HashMap<Object, Object> getMapCommonData(String shibIdentitaCodiceFiscale, String xRequestId,
			String xForwardedFor, String xCodiceServizio, String citId) {
		HashMap<Object, Object> inputValidation = new HashMap<Object, Object>();
		inputValidation.put("Shib-Identita-CodiceFiscale", shibIdentitaCodiceFiscale);
		inputValidation.put("X-Request-ID", xRequestId);
		inputValidation.put("X-Forwarded-For", xForwardedFor);
		inputValidation.put("X-Codice-Servizio", xCodiceServizio);
		if(citId!=null) inputValidation.put("cit_id", citId);
		return inputValidation;
	}
	
	public void chkDatiObbligatoriRettificaRichiesta(String shibIdentitaCodiceFiscale, String xRequestId,
			String xForwardedFor, String xCodiceServizio, String citId, String richiestaId, Payload2 payload)
			throws Exception {
		HashMap<Object, Object> inputValidation = getMapCommonData(shibIdentitaCodiceFiscale, xRequestId, xForwardedFor, xCodiceServizio, citId);
		inputValidation.put("richiestaId", richiestaId);
		inputValidation.put("payload", payload);
		String outValidation = Checker.checkObbligatori(inputValidation);
		if (outValidation != null)
			throw new ParametriObbligatoriException(null, outValidation);
	}
	
	public void chkDatiObbligatoriModuloDelegante(String shibIdentitaCodiceFiscale, String xRequestId,
			String xForwardedFor, String xCodiceServizio, String citId, Payload3 payload)
			throws ParametriObbligatoriException {
		HashMap<Object, Object> inputValidation = getMapCommonData(shibIdentitaCodiceFiscale, xRequestId, xForwardedFor, xCodiceServizio, citId);
		inputValidation.put("payload", payload);
		String outValidation = Checker.checkObbligatori(inputValidation);
		if (outValidation != null)
			throw new ParametriObbligatoriException(null, outValidation);
	}
	
	public void chkDatiObbligatoriTesserinoMedico(String shibIdentitaCodiceFiscale, String xRequestId,
			String xForwardedFor, String xCodiceServizio, String citId) throws ParametriObbligatoriException {
		HashMap<Object, Object> inputValidation = getMapCommonData(shibIdentitaCodiceFiscale, xRequestId, xForwardedFor, xCodiceServizio, citId);
		String outValidation = Checker.checkObbligatori(inputValidation);
		if (outValidation != null)
			throw new ParametriObbligatoriException(null, outValidation);
	}
	
	public void chkDatiObbligatoriCertificatoRevoca(String shibIdentitaCodiceFiscale, String xRequestId,
			String xForwardedFor, String xCodiceServizio, String citId) throws ParametriObbligatoriException {
		HashMap<Object, Object> inputValidation = getMapCommonData(shibIdentitaCodiceFiscale, xRequestId, xForwardedFor, xCodiceServizio, citId);
		String outValidation = Checker.checkObbligatori(inputValidation);
		if (outValidation != null)
			throw new ParametriObbligatoriException(null, outValidation);
	}
	
	public void chkCongruitaInput(String citId, String richiestaId, String shib) throws IntegritaException {
		if (!Checker.isNumericString(richiestaId))
			throw new IntegritaException(null, "richiesta_id");
		
		this.chkCongruitaInputCitIdShib(citId, shib);
	}

	public void chkCongruitaInput(String citId) throws IntegritaException {
		if (!Checker.isCodiceFiscale(citId, isTST()))
			throw new IntegritaException(null, "{cit_id}");
	}
	
	public void chkCongruitaInputCitIdShib(String citId, String shib) throws IntegritaException {
		if (!Checker.isCodiceFiscale(citId, isTST()) && citId != null)
			throw new IntegritaException(null, "{cit_id}");
		
		if (!Checker.isCodiceFiscale(shib, isTST()) && shib != null)
			throw new IntegritaException(null, "{Shib-Identita-CodiceFiscale}");
		
	}

	public void chkDatiObbligatoriRichiestaNuovoMedico(
			String shibIdentitaCodiceFiscale, 
			String xRequestId,
			String xForwardedFor, 
			String xCodiceServizio, 
			String citId, 
			Payload1 payload) throws ParametriObbligatoriException {
		if(payload == null) throw new ParametriObbligatoriException("payload");	
		HashMap<Object, Object> inputValidation = getMapCommonData(shibIdentitaCodiceFiscale, xRequestId, xForwardedFor, xCodiceServizio, citId);
		try {
			inputValidation.putAll(Converter.convertObjToMap(payload));//flatClass(new HashMap<String, Object>(), payload));
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		String outValidation = Checker.checkObbligatori(inputValidation);
		if (outValidation != null)
			throw new ParametriObbligatoriException(outValidation);
	}
	
	public void chkDatiObbligatoriControlloCombinazione(
			String shibIdentitaCodiceFiscale, 
			String xRequestId,
			String xForwardedFor, 
			String xCodiceServizio, 
			String citId, 
			Payload payload) throws ParametriObbligatoriException {
		HashMap<Object, Object> inputValidation = getMapCommonData(shibIdentitaCodiceFiscale, xRequestId, xForwardedFor, xCodiceServizio, citId);
		if(payload == null) throw new ParametriObbligatoriException("payload");		
		try {
			inputValidation.putAll(Converter.convertObjToMap(payload));//flatClass(new HashMap<String, Object>(), payload));
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		String outValidation = Checker.checkObbligatori(inputValidation);
		if (outValidation != null)
			throw new ParametriObbligatoriException(outValidation);
	}
	
	public void chkDatiObbligatoriAnnullaRichiesta(
			String shibIdentitaCodiceFiscale, 
			String xRequestId,
			String xForwardedFor, 
			String xCodiceServizio, 
			String citId) throws ParametriObbligatoriException {
		HashMap<Object, Object> inputValidation = getMapCommonData(shibIdentitaCodiceFiscale, xRequestId, xForwardedFor, xCodiceServizio, citId);
		String outValidation = Checker.checkObbligatori(inputValidation);
		if (outValidation != null)
			throw new ParametriObbligatoriException(outValidation);
	}

	public void chkDatiObbligatoriRevocaMedico(
			String shibIdentitaCodiceFiscale, 
			String xRequestId,
			String xForwardedFor, 
			String xCodiceServizio, 
			String citId,
			String motivo) throws ParametriObbligatoriException {
		HashMap<Object, Object> inputValidation = getMapCommonData(shibIdentitaCodiceFiscale, xRequestId, xForwardedFor, xCodiceServizio, citId);
		inputValidation.put("motivo_revoca", motivo);
		String outValidation = Checker.checkObbligatori(inputValidation);
		if (outValidation != null)
			throw new ParametriObbligatoriException(outValidation);
	}
	
	public void chkCongruitaInput(Payload1 payload) throws IntegritaException, ParametriObbligatoriException {
		String ricongiungimentoCodiceFiscale = payload.getDatiAggiuntivi().getRicongiungimentoCodiceFiscale();
		if (!Checker.isCodiceFiscale(ricongiungimentoCodiceFiscale, isTST()))
			throw new IntegritaException("Ricongiungimento codice fiscale formalmente errato");
	}

	public void chkCongruitaInput(Payload payload) throws IntegritaException {
		if(!Checker.isCodiceFiscale(payload.getMedico().getCodiceFiscale(), isTST())) throw new IntegritaException("Codice fiscale formalmente errato");
//		if(payload.getMedico().getDataNascita()==null) throw new IntegritaException("Data nascita assente");
	}

	public void chkCongruitaInput(List<ModelAmbulatorioLoccsiMedico> payload) throws IntegritaException {
		for(ModelAmbulatorioLoccsiMedico single : payload) {
			if(!Checker.isCodiceFiscale(single.getCodiceFiscale(), isTST())) throw new IntegritaException("Codice fiscale formalmente errato");
//			if(single.getDataNascita()==null) throw new IntegritaException("Data nascita assente");
		}
	}

	public void chkDatiObbligatoriMassimali(String xRequestId, String xForwardedFor,
			String xCodiceServizio, List<ModelAmbulatorioLoccsiMedico> payload) throws ParametriObbligatoriException {
		HashMap<Object, Object> inputValidation = getMapCommonData(null, xRequestId, xForwardedFor, xCodiceServizio, null);
		String outValidation = Checker.checkObbligatoriNoShib(inputValidation);
		if (outValidation != null)
			throw new ParametriObbligatoriException(outValidation);
		
	}
	
	@Override
	public void chkDatiObbligatoriControlloAllegati(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
			String xCodiceServizio, String citId, Payload4 payload) throws ParametriObbligatoriException {
		HashMap<Object, Object> inputValidation = getMapCommonData(null, xRequestId, xForwardedFor, xCodiceServizio, null);
		if(payload == null) throw new ParametriObbligatoriException("payload");		
		try {
			inputValidation.putAll(Converter.convertObjToMap(payload));//flatClass(new HashMap<String, Object>(), payload));
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		String outValidation = Checker.checkObbligatoriNoShib(inputValidation);
		if (outValidation != null)
			throw new ParametriObbligatoriException(outValidation);
		
	}

	@Override
	public void chkDatiObbligatoriMediciMonitorati(String shibIdentitaCodiceFiscale, String xRequestId,
			String xForwardedFor, String xCodiceServizio, String citId) throws ParametriObbligatoriException {
		
		String parametro = "Shib-Identita-CodiceFiscale";
		if(!Checker.isValorizzato(shibIdentitaCodiceFiscale))
			throw new ParametriObbligatoriException(parametro);
		
		parametro = "X-Request-Id";
		if(!Checker.isValorizzato(xRequestId))
			throw new ParametriObbligatoriException(parametro);		
		parametro = "X-Forwarded-For ";
		if(!Checker.isValorizzato(xForwardedFor))
			throw new ParametriObbligatoriException(parametro);		
		parametro = "X-Codice-Servizio";
		if(!Checker.isValorizzato(xCodiceServizio))
			throw new ParametriObbligatoriException(parametro);		
		parametro = "Id Cittadino";
		if(!Checker.isValorizzato(citId))
			throw new ParametriObbligatoriException(parametro);		
	}

	@Override
	public void chkIntegritaMediciMonitorati(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
			String xCodiceServizio, String citId) throws IntegritaException {
		
		
		String parametro = "";
		
		parametro = "Codice fiscale non valido presente";
		if(!Checker.isCodiceFiscale(shibIdentitaCodiceFiscale) || !Checker.isCodiceFiscale(citId))
			throw new IntegritaException(parametro);
		
		parametro = "X-Request-Id";
		if(!Checker.isUuidValido(xRequestId))
			throw new IntegritaException(parametro);
		
		
	}

	@Override
	public void chkDatiObbligatoriStampaAllegato(String shibIdentitaCodiceFiscale, String xRequestId,
			String xForwardedFor, String xCodiceServizio, String citId, String allegatoId) throws ParametriObbligatoriException {
		HashMap<Object, Object> inputValidation = getMapCommonData(shibIdentitaCodiceFiscale, xRequestId, xForwardedFor, xCodiceServizio, citId);
		inputValidation.put("allegatoId", allegatoId);
		String outValidation = Checker.checkObbligatori(inputValidation);
		if (outValidation != null)
			throw new ParametriObbligatoriException(outValidation);
		
	}
	
	

}
