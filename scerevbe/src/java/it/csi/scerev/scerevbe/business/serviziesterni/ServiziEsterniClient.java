/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevbe.business.serviziesterni;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import it.csi.def.opessanws.opessan.SoggettoOpessanBody;
import it.csi.scerev.scerevbe.business.SpringApplicationContextHelper;
import it.csi.scerev.scerevbe.business.facade.interfaces.AuditFacadeIf;
import it.csi.scerev.scerevbe.business.facade.interfaces.ListeFacadeIf;
import it.csi.scerev.scerevbe.business.facade.interfaces.MessaggioFacadeIf;
import it.csi.scerev.scerevbe.dto.ConnectionInfo;
import it.csi.scerev.scerevbe.util.Constants;
import it.csi.scerev.scerevjpa.business.entity.ScerevDAmbito;
import it.csi.scerev.scerevjpa.business.entity.ScerevDDistretto;
import it.csi.scerev.scerevjpa.business.entity.ScerevLAudit;
import it.csi.scerev.scerevjpa.business.entity.ScerevLogAura;
import it.csi.scerev.scerevserviziesterni.aura.find.DatiAnagrafici;
import it.csi.scerev.scerevserviziesterni.aura.get.SoggettoAuraBodyNew;
import it.csi.scerev.scerevserviziesterni.aura.getUltimoMovimentoDiRevoca.Request;
import it.csi.scerev.scerevserviziesterni.aura.getUltimoMovimentoDiRevoca.Response;
import it.csi.scerev.scerevserviziesterni.aura.getUltimoMovimentoDiRevoca.ResponseDelegate;
import it.csi.scerev.scerevserviziesterni.aura.getambito.AmbitoDettaglio2;
import it.csi.scerev.scerevserviziesterni.aura.getambito.ArrayOfAmbitoDettaglio2;
import it.csi.scerev.scerevserviziesterni.aura.getasldistrettoambito.ASLDistrettoAmbitoResponse;
import it.csi.scerev.scerevserviziesterni.aura.getdistretto.ArrayOfDistrettoDettaglio;
import it.csi.scerev.scerevserviziesterni.aura.getdistretto.DistrettoDettaglio;
import it.csi.scerev.scerevserviziesterni.aura.getnumeroassistitiincaricomedico.NumeroAssistitiInCaricoMedicoResponse;
import it.csi.scerev.scerevserviziesterni.aura.setaggiornamentoprofiloanagrafico.AggiornamentoProfiloAnagrafico;
import it.csi.scerev.scerevserviziesterni.aura.setaggiornamentoprofiloanagrafico.AggiornamentoProfiloAnagraficoRes;
import it.csi.scerev.scerevserviziesterni.aura.setinserimentoprofiloanagrafico.InserimentoProfiloAnagrafico;
import it.csi.scerev.scerevserviziesterni.aura.setinserimentoprofiloanagrafico.InserimentoProfiloAnagraficoRes;
import it.csi.scerev.scerevserviziesterni.aura.setprofilosanitario.ProfiloSanitario;
import it.csi.scerev.scerevserviziesterni.aura.setprofilosanitario.ProfiloSanitarioRes;
import it.csi.scerev.scerevserviziesterni.aura.setprofilosanitario.ProfiloSanitarioResFooter;
import it.csi.scerev.scerevserviziesterni.ensemble.interrogamefscerev.InterrogaMefScerevResult;
import it.csi.scerev.scerevserviziesterni.facade.impl.AuraFacade;
import it.csi.scerev.scerevserviziesterni.facade.impl.EnsembleFacade;
import it.csi.scerev.scerevserviziesterni.facade.impl.OpessanFacade;
import it.csi.scerev.scerevserviziesterni.facade.impl.SistemaTSFacade;
import it.csi.scerev.scerevutil.business.Converter;
import it.csi.scerev.scerevutil.business.SharedConstants;
import it.csi.scerev.scerevutil.business.exception.AuraException;
import it.csi.scerev.scerevutil.business.exception.AuraNotFoundException;
import it.csi.scerev.scerevutil.business.exception.OpessanNotFoundException;
import it.csi.scerev.scerevutil.business.exception.SistemaTSNotFoundException;

public class ServiziEsterniClient {
	private String auraFindEndpoint;
	private String auraGetEndpoint;
	private String auraUsername;
	private String auraPassword;
	
	private String opessanDettaglioOperatoreEndpoint;
	private String opessanUsername;
	private String opessanPassword;

	private String auraGetNumeroAssistitiEndpoint;
	private String auraGetNumeroAssistitiUsername;
	private String auraGetNumeroAssistitiPassword;

	private String auraGetAslDistrettoAmbito;
	private String auraGetAslDistrettoAmbitoUsername;
	private String auraGetAslDistrettoAmbitoPassword;

	private String auraGetAmbitoEndpoint;
	private String auraGetAmbitoUsername;
	private String auraGetAmbitoPassword;

	private String auraGetDistrettoEndpoint;
	private String auraGetDistrettoUsername;
	private String auraGetDistrettoPassword;

	private String auraSetInserimentoProfiloAnagraficoEndpoint;
	private String auraSetInserimentoProfiloAnagraficoUsername;
	private String auraSetInserimentoProfiloAnagraficoPassword;

	private String auraSetAggiornamentoProfiloAnagraficoEndpoint;
	private String auraSetAggiornamentoProfiloAnagraficoUsername;
	private String auraSetAggiornamentoProfiloAnagraficoPassword;

	private String auraSetProfiloSanitarioEndpoint;
	private String auraSetProfiloSanitarioUsername;
	private String auraSetProfiloSanitarioPassword;

	private String interrogaMefScerevEndpoint;
	private String interrogaMefScerevEndpointUsername;
	private String interrogaMefScerevEndpointPassword;

	private String auraGetUltimoMovimentoDiRevocaEndpoint;
	private String auraGetUltimoMovimentoDiRevocaUsername;
	private String auraGetUltimoMovimentoDiRevocaPassword;
	
	private static ServiziEsterniClient instance;
	
	private final static Logger log = Logger.getLogger(ServiziEsterniClient.class.getName());

	private ServiziEsterniClient() {
	}

	public static ServiziEsterniClient getInstance() {
		if (instance == null) {
			instance = new ServiziEsterniClient();
			MessaggioFacadeIf auraIf = (MessaggioFacadeIf) SpringApplicationContextHelper.getBean("messaggioFacade");
			instance.setAuraFindEndpoint(auraIf.getMessaggioPerCodice("AURA_FIND_ENDPOINT").getDescrizione());
			instance.setAuraGetEndpoint(auraIf.getMessaggioPerCodice("AURA_GET_ENDPOINT").getDescrizione());
			instance.setAuraPassword(auraIf.getMessaggioPerCodice("AURA_PASSWORD").getDescrizione());
			instance.setAuraUsername(auraIf.getMessaggioPerCodice("AURA_USERNAME").getDescrizione());
			
			instance.setAuraGetNumeroAssistitiEndpoint(auraIf.getMessaggioPerCodice("AURA_GETNUMEROASSISTITI").getDescrizione());
			instance.setAuraGetNumeroAssistitiPassword(auraIf.getMessaggioPerCodice("AURA_GETNUMEROASSISTITI_PASSWORD").getDescrizione());
			instance.setAuraGetNumeroAssistitiUsername(auraIf.getMessaggioPerCodice("AURA_GETNUMEROASSISTITI_USERNAME").getDescrizione());
			
			instance.setAuraGetAslDistrettoAmbito(auraIf.getMessaggioPerCodice("AURA_GETASLDISTRETTOAMBITO").getDescrizione());
			instance.setAuraGetAslDistrettoAmbitoPassword(auraIf.getMessaggioPerCodice("AURA_GETASLDISTRETTOAMBITO_PASSWORD").getDescrizione());
			instance.setAuraGetAslDistrettoAmbitoUsername(auraIf.getMessaggioPerCodice("AURA_GETASLDISTRETTOAMBITO_USERNAME").getDescrizione());
			
			instance.setAuraGetAmbitoEndpoint(auraIf.getMessaggioPerCodice("AURA_GETAMBITO").getDescrizione());
			instance.setAuraGetAmbitoPassword(auraIf.getMessaggioPerCodice("AURA_GETAMBITO_PASSWORD").getDescrizione());
			instance.setAuraGetAmbitoUsername(auraIf.getMessaggioPerCodice("AURA_GETAMBITO_USERNAME").getDescrizione());
			
			instance.setAuraGetDistrettoEndpoint(auraIf.getMessaggioPerCodice("AURA_GETDISTRETTO").getDescrizione());
			instance.setAuraGetDistrettoPassword(auraIf.getMessaggioPerCodice("AURA_GETDISTRETTO_PASSWORD").getDescrizione());
			instance.setAuraGetDistrettoUsername(auraIf.getMessaggioPerCodice("AURA_GETDISTRETTO_USERNAME").getDescrizione());
			
			instance.setAuraSetInserimentoProfiloAnagraficoEndpoint(auraIf.getMessaggioPerCodice("AURA_SETINSERIMENTOPROFILOANAGRAFICO").getDescrizione());
			instance.setAuraSetInserimentoProfiloAnagraficoPassword(auraIf.getMessaggioPerCodice("AURA_SETINSERIMENTOPROFILOANAGRAFICO_PASSWORD").getDescrizione());
			instance.setAuraSetInserimentoProfiloAnagraficoUsername(auraIf.getMessaggioPerCodice("AURA_SETINSERIMENTOPROFILOANAGRAFICO_USERNAME").getDescrizione());
			
			instance.setAuraSetAggiornamentoProfiloAnagraficoEndpoint(auraIf.getMessaggioPerCodice("AURA_SETAGGIORNAMENTOPROFILOANAGRAFICO").getDescrizione());
			instance.setAuraSetAggiornamentoProfiloAnagraficoPassword(auraIf.getMessaggioPerCodice("AURA_SETAGGIORNAMENTOPROFILOANAGRAFICO_PASSWORD").getDescrizione());
			instance.setAuraSetAggiornamentoProfiloAnagraficoUsername(auraIf.getMessaggioPerCodice("AURA_SETAGGIORNAMENTOPROFILOANAGRAFICO_USERNAME").getDescrizione());
			
			instance.setAuraSetProfiloSanitarioEndpoint(auraIf.getMessaggioPerCodice("AURA_SETPROFILOSANITARIO").getDescrizione());
			instance.setAuraSetProfiloSanitarioPassword(auraIf.getMessaggioPerCodice("AURA_SETPROFILOSANITARIO_PASSWORD").getDescrizione());
			instance.setAuraSetProfiloSanitarioUsername(auraIf.getMessaggioPerCodice("AURA_SETPROFILOSANITARIO_USERNAME").getDescrizione());
			
			instance.setInterrogaMefScerevEndpoint(auraIf.getMessaggioPerCodice("MEF_INTERROGAMEFSCEREV").getDescrizione());
			instance.setInterrogaMefScerevEndpointPassword(auraIf.getMessaggioPerCodice("MEF_INTERROGAMEFSCEREV_PASSWORD").getDescrizione());
			instance.setInterrogaMefScerevEndpointUsername(auraIf.getMessaggioPerCodice("MEF_INTERROGAMEFSCEREV_USERNAME").getDescrizione());
			
			instance.setAuraGetUltimoMovimentoDiRevocaEndpoint(auraIf.getMessaggioPerCodice("AURA_GETULTIMOMOVIMENTODIREVOCA").getDescrizione());
			instance.setAuraGetUltimoMovimentoDiRevocaUsername(auraIf.getMessaggioPerCodice("AURA_GETULTIMOMOVIMENTODIREVOCA_USERNAME").getDescrizione());
			instance.setAuraGetUltimoMovimentoDiRevocaPassword(auraIf.getMessaggioPerCodice("AURA_GETULTIMOMOVIMENTODIREVOCA_PASSWORD").getDescrizione());
			
			instance.opessanDettaglioOperatoreEndpoint =(
					auraIf.getMessaggioPerCodice("OPESSAN_DETTAGLIO_OPERATORE_ENDPOINT").getDescrizione());
			instance.opessanPassword =  (auraIf.getMessaggioPerCodice("OPESSAN_PASSWORD").getDescrizione());
			instance.opessanUsername= (auraIf.getMessaggioPerCodice("OPESSAN_USERNAME").getDescrizione());
			
			// Allineamento distretti - ambiti - TODO DA ABILITARE IN TEST-PROD
//			List<ScerevDDistretto> listaDistretti = instance.getDistretto(null);
//			List<ScerevDAmbito> listaAmbiti = instance.getAmbito(null);			
//			ListeFacadeIf listeIf = (ListeFacadeIf) SpringApplicationContextHelper.getBean("listeFacade");
//			if(listaDistretti!=null)listeIf.updateDistretti(listaDistretti);
//			if(listaAmbiti!=null)listeIf.updateAmbiti(listaAmbiti);
			
		}
		return instance;
	}

	public List<DatiAnagrafici> findCittadino(String codiceFiscale, String cognome, String nome, String dataNascita, ScerevLAudit audit) 
			throws IllegalArgumentException, IllegalAccessException, AuraException, AuraNotFoundException, Exception{
		
		AuraFacade facade = new AuraFacade();
		
		Map<String, Object> returnMap = facade.findCittadino(codiceFiscale, cognome, nome, dataNascita,
				Converter.convertObjToMapString(new ConnectionInfo(this.auraFindEndpoint, this.auraUsername, this.auraPassword)));

		String xmlRequest = (String) returnMap.get("request");
		AuditFacadeIf auditFacade = (AuditFacadeIf) SpringApplicationContextHelper.getBean(SharedConstants.AUDIT_FACADE);
		audit.setOggOper("AnagrafeFind - Request");
		audit.setKeyOper(null);
		auditFacade.insertAudit(new ScerevLAudit(audit));
				
		List<DatiAnagrafici> value = (List<DatiAnagrafici>) returnMap.get("value");
		
		String xmlResponse = null;
		try {
			xmlResponse = (String) returnMap.get("response");
		} catch (Exception e) {
			xmlResponse = "Errore connessione AnagrafeFind";
		}
		log.info(xmlRequest);
		log.info(xmlResponse);
		
		if(value==null) {
			audit.setOggOper("AnagrafeFind - Response");
			audit.setKeyOper(SharedConstants.KEY_OPER_KO);		
			auditFacade.insertAudit(new ScerevLAudit(audit));
			throw new AuraException(xmlResponse);
		} else if(value.size() == 0) {
			audit.setOggOper("AnagrafeFind - Response");
			audit.setKeyOper(SharedConstants.KEY_OPER_KO);		
			auditFacade.insertAudit(new ScerevLAudit(audit));
			
			throw new AuraNotFoundException();
		}else {
			
			audit.setOggOper("AnagrafeFind - Response");
			audit.setKeyOper(SharedConstants.KEY_OPER_OK);		
			auditFacade.insertAudit(new ScerevLAudit(audit));
			
		}
		
		return value;
	}

	public InterrogaMefScerevResult findCittadinoSuTS(String codiceFiscale, ScerevLAudit audit) 
			throws IllegalArgumentException, IllegalAccessException, Exception {
		SistemaTSFacade facade = new SistemaTSFacade();

		Map<String, Object> returnMap = facade.findCittadino(codiceFiscale,
				Converter.convertObjToMapString(new ConnectionInfo(this.interrogaMefScerevEndpoint,
						this.interrogaMefScerevEndpointUsername, this.interrogaMefScerevEndpointPassword)));

		String xmlRequest = (String) returnMap.get("request");
		AuditFacadeIf auditFacade = (AuditFacadeIf) SpringApplicationContextHelper.getBean(SharedConstants.AUDIT_FACADE);
		audit.setOggOper("AnagrafeFind - Request");
		audit.setKeyOper(SharedConstants.KEY_OPER_OK);
		auditFacade.insertAudit(new ScerevLAudit(audit));
		
		String xmlResponse = (String) returnMap.get("response");
		audit.setOggOper("AnagrafeFind - Response");
		audit.setKeyOper(SharedConstants.KEY_OPER_OK);
		auditFacade.insertAudit(new ScerevLAudit(audit));
		
		log.info(xmlRequest);
		log.info(xmlResponse);
		
		InterrogaMefScerevResult value = (InterrogaMefScerevResult) returnMap.get("value");
		
		if(value == null) throw new SistemaTSNotFoundException();
		
		return value;
	}
	
	public SoggettoAuraBodyNew anagrafeSanitaria(BigDecimal idProfiloAnagrafico, ScerevLAudit audit)
			throws IllegalArgumentException, IllegalAccessException, Exception {
		AuraFacade facade = new AuraFacade();

		Map<String, Object> returnMap = facade.getCittadino(idProfiloAnagrafico, Converter
				.convertObjToMapString(new ConnectionInfo(this.auraGetEndpoint, this.auraUsername, this.auraPassword)));

		String xmlRequest = (String) returnMap.get("request");
		AuditFacadeIf auditFacade = (AuditFacadeIf) SpringApplicationContextHelper.getBean(SharedConstants.AUDIT_FACADE);
		if(audit != null) {
			audit.setOggOper("AnagrafeSanitaria - Request");
			audit.setKeyOper(null);
			auditFacade.insertAudit(new ScerevLAudit(audit));
		}
		String xmlResponse = (String) returnMap.get("response");

		log.info(xmlRequest);
		log.info(xmlResponse);
		

		if (returnMap.get("value") == null) {
			throw new AuraException(xmlResponse);
		} else if(returnMap.get("value") instanceof String && ((String)returnMap.get("value")).equals(SharedConstants.ERRORE_AURA)) {
			throw new AuraNotFoundException();
		}
		
		if(audit != null) {
			audit.setOggOper("AnagrafeSanitaria - Response");
			audit.setKeyOper(SharedConstants.KEY_OPER_OK);
			auditFacade.insertAudit(new ScerevLAudit(audit));
		}
		
		SoggettoAuraBodyNew value = (SoggettoAuraBodyNew) returnMap.get("value");
		return value;
	}
	
	public SoggettoOpessanBody getDettaglioOperatore(String idMedico, ScerevLAudit audit) throws Exception {
		OpessanFacade facade = new OpessanFacade();
		Map<String, String> bodyMap = new HashMap<String, String>();
		Map<String, String> headerMap = new HashMap<String, String>();
		Map<String, String> conn = Converter.convertObjToMapString(new ConnectionInfo(this.opessanDettaglioOperatoreEndpoint, this.opessanUsername, this.opessanPassword));

		bodyMap.put("idAura", idMedico);		
		
		Map<String, Object> returnMap = facade.getDettaglioOperatore(bodyMap, headerMap, conn);
		
		String xmlRequest = (String) returnMap.get("request");
		String xmlResponse = (String) returnMap.get("response");
		log.info(xmlRequest);
		log.info(xmlResponse);

		if(audit!=null) {
			AuditFacadeIf auditFacade = (AuditFacadeIf) SpringApplicationContextHelper.getBean(SharedConstants.AUDIT_FACADE);
			audit.setOggOper("getDettaglioOperatore - Request");
			audit.setKeyOper(null);
			auditFacade.insertAudit(new ScerevLAudit(audit));
	
			audit.setOggOper("getDettaglioOperatore - Response");
			audit.setKeyOper(SharedConstants.KEY_OPER_OK);
			auditFacade.insertAudit(new ScerevLAudit(audit));
			
		}
		SoggettoOpessanBody value = (SoggettoOpessanBody) returnMap.get("value");
		if (value == null)
			throw new OpessanNotFoundException();

		return value;
	}

	public InserimentoProfiloAnagraficoRes setInserimentoProfiloAnagrafico(InserimentoProfiloAnagrafico in, ScerevLAudit audit) throws Exception {
		EnsembleFacade facade = new EnsembleFacade();

		Map<String, Object> returnMap = facade.setInserimentoProfiloAnagrafico(in,
				Converter.convertObjToMapString(new ConnectionInfo(this.auraSetInserimentoProfiloAnagraficoEndpoint,
						this.auraSetInserimentoProfiloAnagraficoUsername,
						this.auraSetInserimentoProfiloAnagraficoPassword)));
		
		String xmlRequest = (String) returnMap.get("request");
		String xmlResponse = (String) returnMap.get("response");
		log.info(xmlRequest);
		log.info(xmlResponse);

		if(audit!=null) {
			AuditFacadeIf auditFacade = (AuditFacadeIf) SpringApplicationContextHelper.getBean(SharedConstants.AUDIT_FACADE);
			audit.setOggOper(new Object(){}.getClass().getEnclosingMethod().getName()+" - Request");
			audit.setKeyOper(null);
			auditFacade.insertAudit(new ScerevLAudit(audit));
	
			audit.setOggOper(new Object(){}.getClass().getEnclosingMethod().getName()+" - Response");
			audit.setKeyOper(SharedConstants.KEY_OPER_OK);
			auditFacade.insertAudit(new ScerevLAudit(audit));			

		}
		InserimentoProfiloAnagraficoRes value = (InserimentoProfiloAnagraficoRes) returnMap.get("value");

		if (value == null)
			throw new AuraNotFoundException();

		return value;
	}
		
	public AggiornamentoProfiloAnagraficoRes setAggiornamentoProfiloAnagrafico(AggiornamentoProfiloAnagrafico in, ScerevLAudit audit) throws Exception  {
		EnsembleFacade facade = new EnsembleFacade();

		Map<String, Object> returnMap = facade.setAggiornamentoProfiloAnagrafico(in,
				Converter.convertObjToMapString(new ConnectionInfo(this.auraSetAggiornamentoProfiloAnagraficoEndpoint,
						this.auraSetAggiornamentoProfiloAnagraficoUsername,
						this.auraSetAggiornamentoProfiloAnagraficoPassword)));
		
		String xmlRequest = (String) returnMap.get("request");
		String xmlResponse = (String) returnMap.get("response");
		log.info(xmlRequest);
		log.info(xmlResponse);

		if(audit!=null) {
			AuditFacadeIf auditFacade = (AuditFacadeIf) SpringApplicationContextHelper.getBean(SharedConstants.AUDIT_FACADE);
			audit.setOggOper(new Object(){}.getClass().getEnclosingMethod().getName()+" - Request");
			audit.setKeyOper(null);
			auditFacade.insertAudit(new ScerevLAudit(audit));
			
			audit.setOggOper(new Object(){}.getClass().getEnclosingMethod().getName()+" - Response");
			audit.setKeyOper(SharedConstants.KEY_OPER_OK);
			auditFacade.insertAudit(new ScerevLAudit(audit));
			
		}
		AggiornamentoProfiloAnagraficoRes value = (AggiornamentoProfiloAnagraficoRes) returnMap.get("value");

		if (value == null)
			throw new AuraNotFoundException();

		return value;
	}
	
	public ASLDistrettoAmbitoResponse getASLDistrettoAmbito(String descComune, String tipologiaMedico, ScerevLAudit audit) {
		try {
			EnsembleFacade facade = new EnsembleFacade();
			
			Map<String, Object> returnMap = facade.getAslDistrettoAmbito(descComune.toUpperCase(),
					tipologiaMedico.toUpperCase(), Converter.convertObjToMapString(new ConnectionInfo(this.auraGetAslDistrettoAmbito, this.auraGetAslDistrettoAmbitoUsername, this.auraGetAslDistrettoAmbitoPassword)));
			String xmlRequest = (String) returnMap.get("request");
			String xmlResponse = (String) returnMap.get("response");
			log.info(xmlRequest);
			log.info(xmlResponse);

			if (audit != null) {
				AuditFacadeIf auditFacade = (AuditFacadeIf) SpringApplicationContextHelper
						.getBean(SharedConstants.AUDIT_FACADE);
				audit.setOggOper("getAslDistrettoAmbito - Request");
				audit.setKeyOper(null);
				auditFacade.insertAudit(new ScerevLAudit(audit));

				audit.setOggOper("getAslDistrettoAmbito - Response");
				audit.setKeyOper(SharedConstants.KEY_OPER_OK);
				auditFacade.insertAudit(new ScerevLAudit(audit));
				
				log.info(xmlRequest);
				log.info(xmlResponse);
			}	
			ASLDistrettoAmbitoResponse value = (ASLDistrettoAmbitoResponse) returnMap.get("value");		

			if (value == null)
				throw new AuraNotFoundException();
			
			return value;
		} catch (Exception e) {
			return null;
		}
		
	}
	
	public NumeroAssistitiInCaricoMedicoResponse getNumeroAssistitiInCaricoMedico(BigDecimal idAuraMedico, ScerevLAudit audit)
			throws IllegalArgumentException, IllegalAccessException, Exception {
		EnsembleFacade facade = new EnsembleFacade();

		Map<String, Object> returnMap = facade.getNumeroAssistitiInCaricoMedico(idAuraMedico,
				Converter.convertObjToMapString(new ConnectionInfo(this.auraGetNumeroAssistitiEndpoint, this.auraGetNumeroAssistitiUsername, this.auraGetNumeroAssistitiPassword)));
		
		String xmlResponse = (String) returnMap.get("response");
		String xmlRequest = (String) returnMap.get("request");
		if(audit!=null) {
			AuditFacadeIf auditFacade = (AuditFacadeIf) SpringApplicationContextHelper
					.getBean(SharedConstants.AUDIT_FACADE);
			audit.setOggOper("getNumeroAssistitiInCaricoMedico - Request");
			audit.setKeyOper(null);
			auditFacade.insertAudit(new ScerevLAudit(audit));
			
			audit.setOggOper("getNumeroAssistitiInCaricoMedico - Response");
			audit.setKeyOper(SharedConstants.KEY_OPER_OK);
			auditFacade.insertAudit(new ScerevLAudit(audit));			
			
		}
		
		log.info(xmlRequest);
		log.info(xmlResponse);		
		
		NumeroAssistitiInCaricoMedicoResponse value = (NumeroAssistitiInCaricoMedicoResponse) returnMap.get("value");

		if (value == null)
			throw new AuraNotFoundException();

		return value;
	}
	
	public List<ScerevDDistretto> getDistretto(ScerevLAudit audit) {
		try {
			EnsembleFacade facade = new EnsembleFacade();
			Map<String, Object> returnMap = facade
					.getDistretto(Converter.convertObjToMapString(new ConnectionInfo(this.auraGetDistrettoEndpoint,
							this.auraGetDistrettoUsername, this.auraGetDistrettoPassword)));
			String xmlRequest = (String) returnMap.get("request");
			String xmlResponse = (String) returnMap.get("response");
			log.info(xmlRequest);
			log.info(xmlResponse);

			if (audit != null) {
				AuditFacadeIf auditFacade = (AuditFacadeIf) SpringApplicationContextHelper
						.getBean(SharedConstants.AUDIT_FACADE);
				audit.setOggOper("getDistretto - Request");
				audit.setKeyOper(null);
				auditFacade.insertAudit(new ScerevLAudit(audit));

				audit.setOggOper("getDistretto - Response");
				audit.setKeyOper(SharedConstants.KEY_OPER_OK);
				auditFacade.insertAudit(new ScerevLAudit(audit));
				
			}
			ArrayOfDistrettoDettaglio value = (ArrayOfDistrettoDettaglio) returnMap.get("value");

			if (value == null)
				throw new AuraNotFoundException();
			
			List<DistrettoDettaglio> listaDistretti = value.getDistrettoDettaglio();
			List<ScerevDDistretto> listReturn = new ArrayList<ScerevDDistretto>();

			ListeFacadeIf listeIf = (ListeFacadeIf) SpringApplicationContextHelper.getBean("listeFacade");
			
			for (DistrettoDettaglio single : listaDistretti) {
				try {					
					ScerevDDistretto distretto = new ScerevDDistretto();
					distretto.setPkDistretto((int)single.getIdDistretto());
					distretto.setCodiceDistretto(single.getCodice());
					if(single.getFineValidita()!=null) distretto.setDataFineValidita(Converter.getData(single.getFineValidita()));
					distretto.setDataInizioValidita(Converter.getData(single.getInizioValidita()));
					distretto.setScerevDAsl(listeIf.getAsl(new BigDecimal(single.getCodiceAsl())));
					distretto.setDataCreazione(new Timestamp(new Date().getTime()));
					distretto.setDataModifica(new Timestamp(new Date().getTime()));
					distretto.setDenominazioneDistretto(single.getDescrizione());
					distretto.setPkDistretto((int) single.getIdDistretto());
					listReturn.add(distretto);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			return listReturn;			
		} catch (Exception e) {
			return null;
		}
	}
	
	public List<ScerevDAmbito> getAmbito(ScerevLAudit audit){
		try {
			EnsembleFacade facade = new EnsembleFacade();
			Map<String, Object> returnMap = facade
					.getAmbito(Converter.convertObjToMapString(new ConnectionInfo(this.auraGetAmbitoEndpoint,
							this.auraGetAmbitoUsername, this.auraGetAmbitoPassword)));

			String xmlRequest = (String) returnMap.get("request");
			String xmlResponse = (String) returnMap.get("response");
			if (audit != null) {
				AuditFacadeIf auditFacade = (AuditFacadeIf) SpringApplicationContextHelper
						.getBean(SharedConstants.AUDIT_FACADE);
				audit.setOggOper("getAmbito - Request");
				audit.setKeyOper(null);
				auditFacade.insertAudit(new ScerevLAudit(audit));

				audit.setOggOper("getAmbito - Response");
				audit.setKeyOper(SharedConstants.KEY_OPER_OK);
				auditFacade.insertAudit(new ScerevLAudit(audit));			
			}
			ArrayOfAmbitoDettaglio2 value = (ArrayOfAmbitoDettaglio2) returnMap.get("value");
			log.info(xmlRequest);
			log.info(xmlResponse);

			if (value == null)
				throw new AuraNotFoundException();
			
			List<AmbitoDettaglio2> listaAmbiti = value.getAmbitoDettaglio2();
			List<ScerevDAmbito> listReturn = new ArrayList<ScerevDAmbito>();

			ListeFacadeIf listeIf = (ListeFacadeIf) SpringApplicationContextHelper.getBean("listeFacade");
			
			for (AmbitoDettaglio2 single : listaAmbiti) {
				if(single.getFineValidita()!=null) continue;
				ScerevDAmbito ambito = new ScerevDAmbito();
				ambito.setCodiceAmbito(single.getCodice());
				ambito.setDataFineValidita(Converter.getData(single.getFineValidita()));
				ambito.setDataInizioValidita(Converter.getData(single.getInizioValidita()));
				ambito.setDataCreazione(new Timestamp(new Date().getTime()));
				ambito.setDataModifica(new Timestamp(new Date().getTime()));
				ambito.setDenominazioneAmbito(single.getDescrizione());
				ambito.setPkAmbito(single.getIdAmbito().intValue());
		
				ambito.setScerevDDistretto(listeIf.getDistrettoByPk(single.getIdDistretto().intValue()));
				listReturn.add(ambito);
			}
			
			return listReturn;			
		} catch (Exception e) {
			return null;
		}
	}

	public ProfiloSanitarioRes setProfiloSanitario(ProfiloSanitario request, ScerevLAudit audit) throws Exception {
		EnsembleFacade facade = new EnsembleFacade();

		Map<String, Object> returnMap = facade.setProfiloSanitario(request,
				Converter.convertObjToMapString(new ConnectionInfo(this.auraSetProfiloSanitarioEndpoint,
						this.auraSetProfiloSanitarioUsername, this.auraSetProfiloSanitarioPassword)));
		
		String xmlRequest = (String) returnMap.get("request");
		String xmlResponse = (String) returnMap.get("response");
		log.info(xmlRequest);
		log.info(xmlResponse);
		
		if(audit!=null) {
			AuditFacadeIf auditFacade = (AuditFacadeIf) SpringApplicationContextHelper.getBean(SharedConstants.AUDIT_FACADE);
			audit.setOggOper(new Object(){}.getClass().getEnclosingMethod().getName()+" - Request");
			audit.setKeyOper(null);
			auditFacade.insertAudit(new ScerevLAudit(audit));
	
			audit.setOggOper(new Object(){}.getClass().getEnclosingMethod().getName()+" - Response");
			audit.setKeyOper(SharedConstants.KEY_OPER_OK);
			auditFacade.insertAudit(new ScerevLAudit(audit));
		}
		ProfiloSanitarioRes value = (ProfiloSanitarioRes) returnMap.get("value");
		if (returnMap.get("returnCode") != null) {
			String returnCode = (String) returnMap.get("returnCode");
			if ("0".equals(returnCode)) {//1 = servizio eseguito correttamente ; 0 = servizio fallito.
				ProfiloSanitarioResFooter footer = (ProfiloSanitarioResFooter) returnMap.get("footer");
				String detail = footer.getListaErrori().getProfiloSanitarioResErrore().get(0).getCodice()+" - "+footer.getListaErrori().getProfiloSanitarioResErrore().get(0).getDescrizione();
				throw new AuraException(detail);
			}
		}
		if (value == null)
			throw new AuraNotFoundException();
		return value;
	}
	
	public Map<DatiAnagrafici, SoggettoAuraBodyNew> anagrafeSanitariaAttiva(List<DatiAnagrafici> auraFind, ScerevLAudit audit) throws IllegalArgumentException, IllegalAccessException, Exception {
		Map<DatiAnagrafici, SoggettoAuraBodyNew> auraGet = null;
		if (auraFind != null) {
			for (DatiAnagrafici da : auraFind) {
				SoggettoAuraBodyNew anagrafeSanitaria = anagrafeSanitaria(da.getIdProfiloAnagrafico(), audit);
				if (anagrafeSanitaria.getInfoAnag().getDatiPrimari().getStatoProfiloAnagrafico().equalsIgnoreCase("1")) {
					auraGet = new HashMap<DatiAnagrafici, SoggettoAuraBodyNew>();
					auraGet.put(da, anagrafeSanitaria);
					return auraGet;
				}
			}
		}
		return auraGet;
	}
	
	public ResponseDelegate getUltimoMovimentoDiRevoca(String idAssistito, String idMedico)
			throws Exception {
		EnsembleFacade facade = new EnsembleFacade();
		Request req = new Request();
		req.setIdAssistito(idAssistito);
		req.setIdMedico(idMedico);
		Map<String, Object> returnMap = facade.getUltimoMovimentoDiRevoca(req,
				Converter.convertObjToMapString(new ConnectionInfo(this.auraGetUltimoMovimentoDiRevocaEndpoint,
						this.auraGetUltimoMovimentoDiRevocaUsername, this.auraGetUltimoMovimentoDiRevocaPassword)));
		String xmlRequest = (String) returnMap.get("request");
		String xmlResponse = (String) returnMap.get("response");
		String returnCode = (String) returnMap.get("returnCode");

		log.info(xmlRequest);
		log.info(xmlResponse);

		AuditFacadeIf auditFacade = (AuditFacadeIf) SpringApplicationContextHelper
				.getBean(SharedConstants.AUDIT_FACADE);

		ScerevLogAura logAura = new ScerevLogAura();
		logAura.setIdUserChiamante(Constants.ID_APP);
		logAura.setIdUserAssistito(idAssistito);
		logAura.setAzione(new Object(){}.getClass().getEnclosingMethod().getName());
		logAura.setXmlInput(xmlRequest);
		logAura = auditFacade.insertUpdateLogAura(logAura);
		
		logAura.setXmlOutput(xmlResponse);
		logAura.setCodiceEsito(returnCode);
		logAura = auditFacade.insertUpdateLogAura(logAura);
	
		if (returnCode != null && returnCode.equalsIgnoreCase("1")) {
			Response value = (Response) returnMap.get("value");
			if (value == null)
				throw new AuraNotFoundException();

			return value.getResponseDelegate();
		} else
			throw new AuraNotFoundException();

	}
	
	public String getAuraFindEndpoint() {
		return auraFindEndpoint;
	}

	public void setAuraFindEndpoint(String auraFindEndpoint) {
		this.auraFindEndpoint = auraFindEndpoint;
	}

	public String getAuraGetEndpoint() {
		return auraGetEndpoint;
	}

	public void setAuraGetEndpoint(String auraGetEndpoint) {
		this.auraGetEndpoint = auraGetEndpoint;
	}

	public String getAuraUsername() {
		return auraUsername;
	}

	public void setAuraUsername(String auraUsername) {
		this.auraUsername = auraUsername;
	}

	public String getAuraPassword() {
		return auraPassword;
	}

	public void setAuraPassword(String auraPassword) {
		this.auraPassword = auraPassword;
	}

	public String getOpessanDettaglioOperatoreEndpoint() {
		return opessanDettaglioOperatoreEndpoint;
	}

	public void setOpessanDettaglioOperatoreEndpoint(String opessanDettaglioOperatoreEndpoint) {
		this.opessanDettaglioOperatoreEndpoint = opessanDettaglioOperatoreEndpoint;
	}

	public String getOpessanUsername() {
		return opessanUsername;
	}

	public void setOpessanUsername(String opessanUsername) {
		this.opessanUsername = opessanUsername;
	}

	public String getOpessanPassword() {
		return opessanPassword;
	}

	public void setOpessanPassword(String opessanPassword) {
		this.opessanPassword = opessanPassword;
	}

	public String getAuraGetNumeroAssistitiEndpoint() {
		return auraGetNumeroAssistitiEndpoint;
	}

	public void setAuraGetNumeroAssistitiEndpoint(String auraGetNumeroAssistitiEndpoint) {
		this.auraGetNumeroAssistitiEndpoint = auraGetNumeroAssistitiEndpoint;
	}

	public String getAuraGetNumeroAssistitiUsername() {
		return auraGetNumeroAssistitiUsername;
	}

	public void setAuraGetNumeroAssistitiUsername(String auraGetNumeroAssistitiUsername) {
		this.auraGetNumeroAssistitiUsername = auraGetNumeroAssistitiUsername;
	}

	public String getAuraGetNumeroAssistitiPassword() {
		return auraGetNumeroAssistitiPassword;
	}

	public void setAuraGetNumeroAssistitiPassword(String auraGetNumeroAssistitiPassword) {
		this.auraGetNumeroAssistitiPassword = auraGetNumeroAssistitiPassword;
	}

	public String getAuraGetAslDistrettoAmbito() {
		return auraGetAslDistrettoAmbito;
	}

	public void setAuraGetAslDistrettoAmbito(String auraGetAslDistrettoAmbito) {
		this.auraGetAslDistrettoAmbito = auraGetAslDistrettoAmbito;
	}

	public String getAuraGetAslDistrettoAmbitoUsername() {
		return auraGetAslDistrettoAmbitoUsername;
	}

	public void setAuraGetAslDistrettoAmbitoUsername(String auraGetAslDistrettoAmbitoUsername) {
		this.auraGetAslDistrettoAmbitoUsername = auraGetAslDistrettoAmbitoUsername;
	}

	public String getAuraGetAslDistrettoAmbitoPassword() {
		return auraGetAslDistrettoAmbitoPassword;
	}

	public void setAuraGetAslDistrettoAmbitoPassword(String auraGetAslDistrettoAmbitoPassword) {
		this.auraGetAslDistrettoAmbitoPassword = auraGetAslDistrettoAmbitoPassword;
	}

	public String getAuraGetAmbitoEndpoint() {
		return auraGetAmbitoEndpoint;
	}

	public void setAuraGetAmbitoEndpoint(String auraGetAmbitoEndpoint) {
		this.auraGetAmbitoEndpoint = auraGetAmbitoEndpoint;
	}

	public String getAuraGetAmbitoUsername() {
		return auraGetAmbitoUsername;
	}

	public void setAuraGetAmbitoUsername(String auraGetAmbitoUsername) {
		this.auraGetAmbitoUsername = auraGetAmbitoUsername;
	}

	public String getAuraGetAmbitoPassword() {
		return auraGetAmbitoPassword;
	}

	public void setAuraGetAmbitoPassword(String auraGetAmbitoPassword) {
		this.auraGetAmbitoPassword = auraGetAmbitoPassword;
	}

	public String getAuraGetDistrettoEndpoint() {
		return auraGetDistrettoEndpoint;
	}

	public void setAuraGetDistrettoEndpoint(String auraGetDistrettoEndpoint) {
		this.auraGetDistrettoEndpoint = auraGetDistrettoEndpoint;
	}

	public String getAuraGetDistrettoUsername() {
		return auraGetDistrettoUsername;
	}

	public void setAuraGetDistrettoUsername(String auraGetDistrettoUsername) {
		this.auraGetDistrettoUsername = auraGetDistrettoUsername;
	}

	public String getAuraGetDistrettoPassword() {
		return auraGetDistrettoPassword;
	}

	public void setAuraGetDistrettoPassword(String auraGetDistrettoPassword) {
		this.auraGetDistrettoPassword = auraGetDistrettoPassword;
	}

	public String getAuraSetInserimentoProfiloAnagraficoEndpoint() {
		return auraSetInserimentoProfiloAnagraficoEndpoint;
	}

	public void setAuraSetInserimentoProfiloAnagraficoEndpoint(String auraSetInserimentoProfiloAnagraficoEndpoint) {
		this.auraSetInserimentoProfiloAnagraficoEndpoint = auraSetInserimentoProfiloAnagraficoEndpoint;
	}

	public String getAuraSetInserimentoProfiloAnagraficoUsername() {
		return auraSetInserimentoProfiloAnagraficoUsername;
	}

	public void setAuraSetInserimentoProfiloAnagraficoUsername(String auraSetInserimentoProfiloAnagraficoUsername) {
		this.auraSetInserimentoProfiloAnagraficoUsername = auraSetInserimentoProfiloAnagraficoUsername;
	}

	public String getAuraSetInserimentoProfiloAnagraficoPassword() {
		return auraSetInserimentoProfiloAnagraficoPassword;
	}

	public void setAuraSetInserimentoProfiloAnagraficoPassword(String auraSetInserimentoProfiloAnagraficoPassword) {
		this.auraSetInserimentoProfiloAnagraficoPassword = auraSetInserimentoProfiloAnagraficoPassword;
	}

	public String getAuraSetAggiornamentoProfiloAnagraficoEndpoint() {
		return auraSetAggiornamentoProfiloAnagraficoEndpoint;
	}

	public void setAuraSetAggiornamentoProfiloAnagraficoEndpoint(String auraSetAggiornamentoProfiloAnagraficoEndpoint) {
		this.auraSetAggiornamentoProfiloAnagraficoEndpoint = auraSetAggiornamentoProfiloAnagraficoEndpoint;
	}

	public String getAuraSetAggiornamentoProfiloAnagraficoUsername() {
		return auraSetAggiornamentoProfiloAnagraficoUsername;
	}

	public void setAuraSetAggiornamentoProfiloAnagraficoUsername(String auraSetAggiornamentoProfiloAnagraficoUsername) {
		this.auraSetAggiornamentoProfiloAnagraficoUsername = auraSetAggiornamentoProfiloAnagraficoUsername;
	}

	public String getAuraSetAggiornamentoProfiloAnagraficoPassword() {
		return auraSetAggiornamentoProfiloAnagraficoPassword;
	}

	public void setAuraSetAggiornamentoProfiloAnagraficoPassword(String auraSetAggiornamentoProfiloAnagrafico) {
		this.auraSetAggiornamentoProfiloAnagraficoPassword = auraSetAggiornamentoProfiloAnagrafico;
	}

	public String getAuraSetProfiloSanitarioEndpoint() {
		return auraSetProfiloSanitarioEndpoint;
	}

	public void setAuraSetProfiloSanitarioEndpoint(String auraSetProfiloSanitarioEndpoint) {
		this.auraSetProfiloSanitarioEndpoint = auraSetProfiloSanitarioEndpoint;
	}

	public String getAuraSetProfiloSanitarioUsername() {
		return auraSetProfiloSanitarioUsername;
	}

	public void setAuraSetProfiloSanitarioUsername(String auraSetProfiloSanitarioUsername) {
		this.auraSetProfiloSanitarioUsername = auraSetProfiloSanitarioUsername;
	}

	public String getAuraSetProfiloSanitarioPassword() {
		return auraSetProfiloSanitarioPassword;
	}

	public void setAuraSetProfiloSanitarioPassword(String auraSetProfiloSanitarioPassword) {
		this.auraSetProfiloSanitarioPassword = auraSetProfiloSanitarioPassword;
	}

	public String getInterrogaMefScerevEndpoint() {
		return interrogaMefScerevEndpoint;
	}

	public void setInterrogaMefScerevEndpoint(String interrogaMefScerevEndpoint) {
		this.interrogaMefScerevEndpoint = interrogaMefScerevEndpoint;
	}

	public String getInterrogaMefScerevEndpointUsername() {
		return interrogaMefScerevEndpointUsername;
	}

	public void setInterrogaMefScerevEndpointUsername(String interrogaMefScerevEndpointUsername) {
		this.interrogaMefScerevEndpointUsername = interrogaMefScerevEndpointUsername;
	}

	public String getInterrogaMefScerevEndpointPassword() {
		return interrogaMefScerevEndpointPassword;
	}

	public void setInterrogaMefScerevEndpointPassword(String interrogaMefScerevEndpointPassword) {
		this.interrogaMefScerevEndpointPassword = interrogaMefScerevEndpointPassword;
	}

	public String getAuraGetUltimoMovimentoDiRevocaEndpoint() {
		return auraGetUltimoMovimentoDiRevocaEndpoint;
	}

	public void setAuraGetUltimoMovimentoDiRevocaEndpoint(String auraGetUltimoMovimentoDiRevocaEndpoint) {
		this.auraGetUltimoMovimentoDiRevocaEndpoint = auraGetUltimoMovimentoDiRevocaEndpoint;
	}

	public String getAuraGetUltimoMovimentoDiRevocaUsername() {
		return auraGetUltimoMovimentoDiRevocaUsername;
	}

	public void setAuraGetUltimoMovimentoDiRevocaUsername(String auraGetUltimoMovimentoDiRevocaUsername) {
		this.auraGetUltimoMovimentoDiRevocaUsername = auraGetUltimoMovimentoDiRevocaUsername;
	}

	public String getAuraGetUltimoMovimentoDiRevocaPassword() {
		return auraGetUltimoMovimentoDiRevocaPassword;
	}

	public void setAuraGetUltimoMovimentoDiRevocaPassword(String auraGetUltimoMovimentoDiRevocaPassword) {
		this.auraGetUltimoMovimentoDiRevocaPassword = auraGetUltimoMovimentoDiRevocaPassword;
	}

}
