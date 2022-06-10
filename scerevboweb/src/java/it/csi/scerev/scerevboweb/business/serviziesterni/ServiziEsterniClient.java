/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevboweb.business.serviziesterni;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import it.csi.def.opessanws.opessan.SoggettoOpessanBody;
import it.csi.iride2.policy.entity.Identita;
import it.csi.scerev.scerevboweb.business.SpringApplicationContextHelper;
import it.csi.scerev.scerevboweb.business.facade.interfaces.AuditFacadeIf;
import it.csi.scerev.scerevboweb.business.facade.interfaces.MessaggioFacadeIf;
import it.csi.scerev.scerevboweb.dto.ConnectionInfo;
import it.csi.scerev.scerevjpa.business.entity.ScerevLAudit;
import it.csi.scerev.scerevserviziesterni.aura.find.DatiAnagrafici;
import it.csi.scerev.scerevserviziesterni.aura.get.SoggettoAuraBodyNew;
import it.csi.scerev.scerevserviziesterni.aura.getasldistrettoambito.ASLDistrettoAmbitoResponse;
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
import it.csi.scerev.scerevserviziesterni.facade.impl.IrideFacade;
import it.csi.scerev.scerevserviziesterni.facade.impl.NotificatoreFacade;
import it.csi.scerev.scerevserviziesterni.facade.impl.OpessanFacade;
import it.csi.scerev.scerevserviziesterni.facade.impl.SistemaTSFacade;
import it.csi.scerev.scerevserviziesterni.iride.base.InternalException;
import it.csi.scerev.scerevserviziesterni.iride.base.Ruolo;
import it.csi.scerev.scerevutil.business.Converter;
import it.csi.scerev.scerevutil.business.SharedConstants;
import it.csi.scerev.scerevutil.business.exception.AuraException;
import it.csi.scerev.scerevutil.business.exception.AuraNotFoundException;
import it.csi.scerev.scerevutil.business.exception.IrideNotFoundException;
import it.csi.scerev.scerevutil.business.exception.OpessanNotFoundException;
import it.csi.scerev.scerevutil.business.exception.SistemaTSNotFoundException;

public class ServiziEsterniClient {
	private String irideBaseEndpoint;
	
	private String auraFindEndpoint;
	private String auraGetEndpoint;
	private String auraGetNumeroAssistitiInCaricoMedicoEndpoint;
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
	
	private Map<String,String> notificatoreParams;
	

	private final static Logger log = Logger.getLogger(ServiziEsterniClient.class.getName());
	
	private static ServiziEsterniClient instance;

	private ServiziEsterniClient() {
	}

	public static ServiziEsterniClient getInstance() {
		if (instance == null) {
			instance = new ServiziEsterniClient();
			try {
				MessaggioFacadeIf msgIf = (MessaggioFacadeIf) SpringApplicationContextHelper.getBean("messaggioFacade");
				instance.auraFindEndpoint = (msgIf.getMessaggioPerCodice("AURA_FIND_ENDPOINT").getMessaggiostr());
				instance.auraGetEndpoint=(msgIf.getMessaggioPerCodice("AURA_GET_ENDPOINT").getMessaggiostr());
				instance.auraGetNumeroAssistitiInCaricoMedicoEndpoint =(
						msgIf.getMessaggioPerCodice("AURA_GET_NUM_ASSISTITI_ENDPOINT").getMessaggiostr());
				instance.auraPassword= (msgIf.getMessaggioPerCodice("AURA_PASSWORD").getMessaggiostr());
				instance.auraUsername =(msgIf.getMessaggioPerCodice("AURA_USERNAME").getMessaggiostr());

				instance.opessanDettaglioOperatoreEndpoint =(
						msgIf.getMessaggioPerCodice("OPESSAN_DETTAGLIO_OPERATORE_ENDPOINT").getMessaggiostr());
				instance.opessanPassword =  (msgIf.getMessaggioPerCodice("OPESSAN_PASSWORD").getMessaggiostr());
				instance.opessanUsername= (msgIf.getMessaggioPerCodice("OPESSAN_USERNAME").getMessaggiostr());

				instance.irideBaseEndpoint= (msgIf.getMessaggioPerCodice("IRIDE_BASE_ENDPOINT").getMessaggiostr());

				instance.notificatoreParams = new HashMap<String,String>();	
				
				instance.notificatoreParams.put("NOTIFICATORE_TOKEN",msgIf.getMessaggioPerCodice("NOTIFICATORE_TOKEN").getMessaggiostr());
				instance.notificatoreParams.put("NOTIFICATORE_ENDPOINT", msgIf.getMessaggioPerCodice("NOTIFICATORE_ENDPOINT").getMessaggiostr());
				
				instance.setAuraGetNumeroAssistitiEndpoint(msgIf.getMessaggioPerCodice("AURA_GETNUMEROASSISTITI").getMessaggiostr());
				instance.setAuraGetNumeroAssistitiPassword(msgIf.getMessaggioPerCodice("AURA_GETNUMEROASSISTITI_PASSWORD").getMessaggiostr());
				instance.setAuraGetNumeroAssistitiUsername(msgIf.getMessaggioPerCodice("AURA_GETNUMEROASSISTITI_USERNAME").getMessaggiostr());
				
				instance.setAuraGetAslDistrettoAmbito(msgIf.getMessaggioPerCodice("AURA_GETASLDISTRETTOAMBITO").getMessaggiostr());
				instance.setAuraGetAslDistrettoAmbitoPassword(msgIf.getMessaggioPerCodice("AURA_GETASLDISTRETTOAMBITO_PASSWORD").getMessaggiostr());
				instance.setAuraGetAslDistrettoAmbitoUsername(msgIf.getMessaggioPerCodice("AURA_GETASLDISTRETTOAMBITO_USERNAME").getMessaggiostr());
				
				instance.setAuraGetAmbitoEndpoint(msgIf.getMessaggioPerCodice("AURA_GETAMBITO").getMessaggiostr());
				instance.setAuraGetAmbitoPassword(msgIf.getMessaggioPerCodice("AURA_GETAMBITO_PASSWORD").getMessaggiostr());
				instance.setAuraGetAmbitoUsername(msgIf.getMessaggioPerCodice("AURA_GETAMBITO_USERNAME").getMessaggiostr());
				
				instance.setAuraGetDistrettoEndpoint(msgIf.getMessaggioPerCodice("AURA_GETDISTRETTO").getMessaggiostr());
				instance.setAuraGetDistrettoPassword(msgIf.getMessaggioPerCodice("AURA_GETDISTRETTO_PASSWORD").getMessaggiostr());
				instance.setAuraGetDistrettoUsername(msgIf.getMessaggioPerCodice("AURA_GETDISTRETTO_USERNAME").getMessaggiostr());
				
				instance.setAuraSetInserimentoProfiloAnagraficoEndpoint(msgIf.getMessaggioPerCodice("AURA_SETINSERIMENTOPROFILOANAGRAFICO").getMessaggiostr());
				instance.setAuraSetInserimentoProfiloAnagraficoPassword(msgIf.getMessaggioPerCodice("AURA_SETINSERIMENTOPROFILOANAGRAFICO_PASSWORD").getMessaggiostr());
				instance.setAuraSetInserimentoProfiloAnagraficoUsername(msgIf.getMessaggioPerCodice("AURA_SETINSERIMENTOPROFILOANAGRAFICO_USERNAME").getMessaggiostr());
				
				instance.setAuraSetAggiornamentoProfiloAnagraficoEndpoint(msgIf.getMessaggioPerCodice("AURA_SETAGGIORNAMENTOPROFILOANAGRAFICO").getMessaggiostr());
				instance.setAuraSetAggiornamentoProfiloAnagraficoPassword(msgIf.getMessaggioPerCodice("AURA_SETAGGIORNAMENTOPROFILOANAGRAFICO_PASSWORD").getMessaggiostr());
				instance.setAuraSetAggiornamentoProfiloAnagraficoUsername(msgIf.getMessaggioPerCodice("AURA_SETAGGIORNAMENTOPROFILOANAGRAFICO_USERNAME").getMessaggiostr());
				
				instance.setAuraSetProfiloSanitarioEndpoint(msgIf.getMessaggioPerCodice("AURA_SETPROFILOSANITARIO").getMessaggiostr());
				instance.setAuraSetProfiloSanitarioPassword(msgIf.getMessaggioPerCodice("AURA_SETPROFILOSANITARIO_PASSWORD").getMessaggiostr());
				instance.setAuraSetProfiloSanitarioUsername(msgIf.getMessaggioPerCodice("AURA_SETPROFILOSANITARIO_USERNAME").getMessaggiostr());
				
				instance.setInterrogaMefScerevEndpoint(msgIf.getMessaggioPerCodice("MEF_INTERROGAMEFSCEREV").getMessaggiostr());
				instance.setInterrogaMefScerevEndpointPassword(msgIf.getMessaggioPerCodice("MEF_INTERROGAMEFSCEREV_PASSWORD").getMessaggiostr());
				instance.setInterrogaMefScerevEndpointUsername(msgIf.getMessaggioPerCodice("MEF_INTERROGAMEFSCEREV_USERNAME").getMessaggiostr());

				instance.notificatoreParams.put("prendiInCarico"+SharedConstants.EMAIL_SUBJECT_BENEFICIARIO,msgIf.getMessaggioPerCodice("prendiInCarico"+SharedConstants.EMAIL_SUBJECT_BENEFICIARIO).getMessaggiostr());
				instance.notificatoreParams.put("prendiInCarico"+SharedConstants.EMAIL_BENEFICIARIO,msgIf.getMessaggioPerCodice("prendiInCarico"+SharedConstants.EMAIL_BENEFICIARIO).getMessaggiostr());				
				instance.notificatoreParams.put("prendiInCarico"+SharedConstants.EMAIL_SUBJECT_DELEGATO,msgIf.getMessaggioPerCodice("prendiInCarico"+SharedConstants.EMAIL_SUBJECT_DELEGATO).getMessaggiostr());
				instance.notificatoreParams.put("prendiInCarico"+SharedConstants.EMAIL_DELEGATO ,msgIf.getMessaggioPerCodice("prendiInCarico"+SharedConstants.EMAIL_DELEGATO).getMessaggiostr());				
				instance.notificatoreParams.put("prendiInCarico"+SharedConstants.PUSH_TITLE_BENEFICIARIO ,msgIf.getMessaggioPerCodice("prendiInCarico"+SharedConstants.PUSH_TITLE_BENEFICIARIO).getMessaggiostr());
				instance.notificatoreParams.put("prendiInCarico"+SharedConstants.PUSH_BENEFICIARIO,msgIf.getMessaggioPerCodice("prendiInCarico"+SharedConstants.PUSH_BENEFICIARIO).getMessaggiostr());				
				instance.notificatoreParams.put("prendiInCarico"+SharedConstants.PUSH_TITLE_DELEGATO,msgIf.getMessaggioPerCodice("prendiInCarico"+SharedConstants.PUSH_TITLE_DELEGATO).getMessaggiostr());
				instance.notificatoreParams.put("prendiInCarico"+SharedConstants.PUSH_DELEGATO,msgIf.getMessaggioPerCodice("prendiInCarico"+SharedConstants.PUSH_DELEGATO).getMessaggiostr());
				instance.notificatoreParams.put("prendiInCarico"+SharedConstants.EMAIL_BENEFICIARIO_BY_DELEGATO,msgIf.getMessaggioPerCodice("prendiInCarico"+SharedConstants.EMAIL_BENEFICIARIO_BY_DELEGATO).getMessaggiostr());
				instance.notificatoreParams.put("prendiInCarico"+SharedConstants.PUSH_BENEFICIARIO_BY_DELEGATO,msgIf.getMessaggioPerCodice("prendiInCarico"+SharedConstants.PUSH_BENEFICIARIO_BY_DELEGATO).getMessaggiostr());
				
				instance.notificatoreParams.put("prendiInCarico"+SharedConstants.MEX_TITLE_BENEFICIARIO ,msgIf.getMessaggioPerCodice("prendiInCarico"+SharedConstants.MEX_TITLE_BENEFICIARIO).getMessaggiostr());
				instance.notificatoreParams.put("prendiInCarico"+SharedConstants.MEX_BENEFICIARIO,msgIf.getMessaggioPerCodice("prendiInCarico"+SharedConstants.MEX_BENEFICIARIO).getMessaggiostr());				
				instance.notificatoreParams.put("prendiInCarico"+SharedConstants.MEX_TITLE_DELEGATO,msgIf.getMessaggioPerCodice("prendiInCarico"+SharedConstants.MEX_TITLE_DELEGATO).getMessaggiostr());
				instance.notificatoreParams.put("prendiInCarico"+SharedConstants.MEX_DELEGATO,msgIf.getMessaggioPerCodice("prendiInCarico"+SharedConstants.MEX_DELEGATO).getMessaggiostr());
				instance.notificatoreParams.put("prendiInCarico"+SharedConstants.MEX_BENEFICIARIO_BY_DELEGATO,msgIf.getMessaggioPerCodice("prendiInCarico"+SharedConstants.MEX_BENEFICIARIO_BY_DELEGATO).getMessaggiostr());
				instance.notificatoreParams.put("prendiInCarico"+SharedConstants.MEX_CALL_TO_ACTION_BENEFICIARIO,msgIf.getMessaggioPerCodice("prendiInCarico"+SharedConstants.MEX_CALL_TO_ACTION_BENEFICIARIO).getMessaggiostr());
				instance.notificatoreParams.put("prendiInCarico"+SharedConstants.MEX_CALL_TO_ACTION_DELEGATO,msgIf.getMessaggioPerCodice("prendiInCarico"+SharedConstants.MEX_CALL_TO_ACTION_DELEGATO).getMessaggiostr());
				
				instance.notificatoreParams.put("accettaRichiesta"+SharedConstants.EMAIL_SUBJECT_BENEFICIARIO,msgIf.getMessaggioPerCodice("accettaRichiesta"+SharedConstants.EMAIL_SUBJECT_BENEFICIARIO).getMessaggiostr());
				instance.notificatoreParams.put("accettaRichiesta"+SharedConstants.EMAIL_BENEFICIARIO,msgIf.getMessaggioPerCodice("accettaRichiesta"+SharedConstants.EMAIL_BENEFICIARIO).getMessaggiostr());				
				instance.notificatoreParams.put("accettaRichiesta"+SharedConstants.EMAIL_SUBJECT_DELEGATO,msgIf.getMessaggioPerCodice("accettaRichiesta"+SharedConstants.EMAIL_SUBJECT_DELEGATO).getMessaggiostr());
				instance.notificatoreParams.put("accettaRichiesta"+SharedConstants.EMAIL_DELEGATO ,msgIf.getMessaggioPerCodice("accettaRichiesta"+SharedConstants.EMAIL_DELEGATO).getMessaggiostr());				
				instance.notificatoreParams.put("accettaRichiesta"+SharedConstants.PUSH_TITLE_BENEFICIARIO ,msgIf.getMessaggioPerCodice("accettaRichiesta"+SharedConstants.PUSH_TITLE_BENEFICIARIO).getMessaggiostr());
				instance.notificatoreParams.put("accettaRichiesta"+SharedConstants.PUSH_BENEFICIARIO,msgIf.getMessaggioPerCodice("accettaRichiesta"+SharedConstants.PUSH_BENEFICIARIO).getMessaggiostr());				
				instance.notificatoreParams.put("accettaRichiesta"+SharedConstants.PUSH_TITLE_DELEGATO,msgIf.getMessaggioPerCodice("accettaRichiesta"+SharedConstants.PUSH_TITLE_DELEGATO).getMessaggiostr());
				instance.notificatoreParams.put("accettaRichiesta"+SharedConstants.PUSH_DELEGATO,msgIf.getMessaggioPerCodice("accettaRichiesta"+SharedConstants.PUSH_DELEGATO).getMessaggiostr());
				instance.notificatoreParams.put("accettaRichiesta"+SharedConstants.EMAIL_BENEFICIARIO_BY_DELEGATO,msgIf.getMessaggioPerCodice("accettaRichiesta"+SharedConstants.EMAIL_BENEFICIARIO_BY_DELEGATO).getMessaggiostr());
				instance.notificatoreParams.put("accettaRichiesta"+SharedConstants.PUSH_BENEFICIARIO_BY_DELEGATO,msgIf.getMessaggioPerCodice("accettaRichiesta"+SharedConstants.PUSH_BENEFICIARIO_BY_DELEGATO).getMessaggiostr());
				
				instance.notificatoreParams.put("accettaRichiesta"+SharedConstants.MEX_TITLE_BENEFICIARIO ,msgIf.getMessaggioPerCodice("accettaRichiesta"+SharedConstants.MEX_TITLE_BENEFICIARIO).getMessaggiostr());
				instance.notificatoreParams.put("accettaRichiesta"+SharedConstants.MEX_BENEFICIARIO,msgIf.getMessaggioPerCodice("accettaRichiesta"+SharedConstants.MEX_BENEFICIARIO).getMessaggiostr());				
				instance.notificatoreParams.put("accettaRichiesta"+SharedConstants.MEX_TITLE_DELEGATO,msgIf.getMessaggioPerCodice("accettaRichiesta"+SharedConstants.MEX_TITLE_DELEGATO).getMessaggiostr());
				instance.notificatoreParams.put("accettaRichiesta"+SharedConstants.MEX_DELEGATO,msgIf.getMessaggioPerCodice("accettaRichiesta"+SharedConstants.MEX_DELEGATO).getMessaggiostr());
				instance.notificatoreParams.put("accettaRichiesta"+SharedConstants.MEX_BENEFICIARIO_BY_DELEGATO,msgIf.getMessaggioPerCodice("accettaRichiesta"+SharedConstants.MEX_BENEFICIARIO_BY_DELEGATO).getMessaggiostr());
				instance.notificatoreParams.put("accettaRichiesta"+SharedConstants.MEX_CALL_TO_ACTION_BENEFICIARIO,msgIf.getMessaggioPerCodice("accettaRichiesta"+SharedConstants.MEX_CALL_TO_ACTION_BENEFICIARIO).getMessaggiostr());
				instance.notificatoreParams.put("accettaRichiesta"+SharedConstants.MEX_CALL_TO_ACTION_DELEGATO,msgIf.getMessaggioPerCodice("accettaRichiesta"+SharedConstants.MEX_CALL_TO_ACTION_DELEGATO).getMessaggiostr());
				
				instance.notificatoreParams.put("respingeRichiesta"+SharedConstants.EMAIL_SUBJECT_BENEFICIARIO,msgIf.getMessaggioPerCodice("respingeRichiesta"+SharedConstants.EMAIL_SUBJECT_BENEFICIARIO).getMessaggiostr());
				instance.notificatoreParams.put("respingeRichiesta"+SharedConstants.EMAIL_BENEFICIARIO,msgIf.getMessaggioPerCodice("respingeRichiesta"+SharedConstants.EMAIL_BENEFICIARIO).getMessaggiostr());				
				instance.notificatoreParams.put("respingeRichiesta"+SharedConstants.EMAIL_SUBJECT_DELEGATO,msgIf.getMessaggioPerCodice("respingeRichiesta"+SharedConstants.EMAIL_SUBJECT_DELEGATO).getMessaggiostr());
				instance.notificatoreParams.put("respingeRichiesta"+SharedConstants.EMAIL_DELEGATO ,msgIf.getMessaggioPerCodice("respingeRichiesta"+SharedConstants.EMAIL_DELEGATO).getMessaggiostr());				
				instance.notificatoreParams.put("respingeRichiesta"+SharedConstants.PUSH_TITLE_BENEFICIARIO ,msgIf.getMessaggioPerCodice("respingeRichiesta"+SharedConstants.PUSH_TITLE_BENEFICIARIO).getMessaggiostr());
				instance.notificatoreParams.put("respingeRichiesta"+SharedConstants.PUSH_BENEFICIARIO,msgIf.getMessaggioPerCodice("respingeRichiesta"+SharedConstants.PUSH_BENEFICIARIO).getMessaggiostr());				
				instance.notificatoreParams.put("respingeRichiesta"+SharedConstants.PUSH_TITLE_DELEGATO,msgIf.getMessaggioPerCodice("respingeRichiesta"+SharedConstants.PUSH_TITLE_DELEGATO).getMessaggiostr());
				instance.notificatoreParams.put("respingeRichiesta"+SharedConstants.PUSH_DELEGATO,msgIf.getMessaggioPerCodice("respingeRichiesta"+SharedConstants.PUSH_DELEGATO).getMessaggiostr());
				instance.notificatoreParams.put("respingeRichiesta"+SharedConstants.EMAIL_BENEFICIARIO_BY_DELEGATO,msgIf.getMessaggioPerCodice("respingeRichiesta"+SharedConstants.EMAIL_BENEFICIARIO_BY_DELEGATO).getMessaggiostr());
				instance.notificatoreParams.put("respingeRichiesta"+SharedConstants.PUSH_BENEFICIARIO_BY_DELEGATO,msgIf.getMessaggioPerCodice("respingeRichiesta"+SharedConstants.PUSH_BENEFICIARIO_BY_DELEGATO).getMessaggiostr());
//				instance.notificatoreParams.put("respingeRichiesta"+SharedConstants.SMS_BENEFICIARIO ,msgIf.getMessaggioPerCodice("respingeRichiesta"+SharedConstants.SMS_BENEFICIARIO).getMessaggiostr());

				instance.notificatoreParams.put("respingeRichiesta"+SharedConstants.MEX_TITLE_BENEFICIARIO ,msgIf.getMessaggioPerCodice("respingeRichiesta"+SharedConstants.MEX_TITLE_BENEFICIARIO).getMessaggiostr());
				instance.notificatoreParams.put("respingeRichiesta"+SharedConstants.MEX_BENEFICIARIO,msgIf.getMessaggioPerCodice("respingeRichiesta"+SharedConstants.MEX_BENEFICIARIO).getMessaggiostr());				
				instance.notificatoreParams.put("respingeRichiesta"+SharedConstants.MEX_TITLE_DELEGATO,msgIf.getMessaggioPerCodice("respingeRichiesta"+SharedConstants.MEX_TITLE_DELEGATO).getMessaggiostr());
				instance.notificatoreParams.put("respingeRichiesta"+SharedConstants.MEX_DELEGATO,msgIf.getMessaggioPerCodice("respingeRichiesta"+SharedConstants.MEX_DELEGATO).getMessaggiostr());
				instance.notificatoreParams.put("respingeRichiesta"+SharedConstants.MEX_BENEFICIARIO_BY_DELEGATO,msgIf.getMessaggioPerCodice("respingeRichiesta"+SharedConstants.MEX_BENEFICIARIO_BY_DELEGATO).getMessaggiostr());
				instance.notificatoreParams.put("respingeRichiesta"+SharedConstants.MEX_CALL_TO_ACTION_BENEFICIARIO,msgIf.getMessaggioPerCodice("respingeRichiesta"+SharedConstants.MEX_CALL_TO_ACTION_BENEFICIARIO).getMessaggiostr());
				instance.notificatoreParams.put("respingeRichiesta"+SharedConstants.MEX_CALL_TO_ACTION_DELEGATO,msgIf.getMessaggioPerCodice("respingeRichiesta"+SharedConstants.MEX_CALL_TO_ACTION_DELEGATO).getMessaggiostr());
				
				instance.notificatoreParams.put("richiedeIntegrazione"+SharedConstants.EMAIL_SUBJECT_BENEFICIARIO,msgIf.getMessaggioPerCodice("richiedeIntegrazione"+SharedConstants.EMAIL_SUBJECT_BENEFICIARIO).getMessaggiostr());
				instance.notificatoreParams.put("richiedeIntegrazione"+SharedConstants.EMAIL_BENEFICIARIO,msgIf.getMessaggioPerCodice("richiedeIntegrazione"+SharedConstants.EMAIL_BENEFICIARIO).getMessaggiostr());				
				instance.notificatoreParams.put("richiedeIntegrazione"+SharedConstants.EMAIL_SUBJECT_DELEGATO,msgIf.getMessaggioPerCodice("richiedeIntegrazione"+SharedConstants.EMAIL_SUBJECT_DELEGATO).getMessaggiostr());
				instance.notificatoreParams.put("richiedeIntegrazione"+SharedConstants.EMAIL_DELEGATO ,msgIf.getMessaggioPerCodice("richiedeIntegrazione"+SharedConstants.EMAIL_DELEGATO).getMessaggiostr());				
				instance.notificatoreParams.put("richiedeIntegrazione"+SharedConstants.PUSH_TITLE_BENEFICIARIO ,msgIf.getMessaggioPerCodice("richiedeIntegrazione"+SharedConstants.PUSH_TITLE_BENEFICIARIO).getMessaggiostr());
				instance.notificatoreParams.put("richiedeIntegrazione"+SharedConstants.PUSH_BENEFICIARIO,msgIf.getMessaggioPerCodice("richiedeIntegrazione"+SharedConstants.PUSH_BENEFICIARIO).getMessaggiostr());				
				instance.notificatoreParams.put("richiedeIntegrazione"+SharedConstants.PUSH_TITLE_DELEGATO,msgIf.getMessaggioPerCodice("richiedeIntegrazione"+SharedConstants.PUSH_TITLE_DELEGATO).getMessaggiostr());
				instance.notificatoreParams.put("richiedeIntegrazione"+SharedConstants.PUSH_DELEGATO,msgIf.getMessaggioPerCodice("richiedeIntegrazione"+SharedConstants.PUSH_DELEGATO).getMessaggiostr());				
				instance.notificatoreParams.put("richiedeIntegrazione"+SharedConstants.EMAIL_BENEFICIARIO_BY_DELEGATO,msgIf.getMessaggioPerCodice("richiedeIntegrazione"+SharedConstants.EMAIL_BENEFICIARIO_BY_DELEGATO).getMessaggiostr());
				instance.notificatoreParams.put("richiedeIntegrazione"+SharedConstants.PUSH_BENEFICIARIO_BY_DELEGATO,msgIf.getMessaggioPerCodice("richiedeIntegrazione"+SharedConstants.PUSH_BENEFICIARIO_BY_DELEGATO).getMessaggiostr());
//				instance.notificatoreParams.put("richiedeIntegrazione"+SharedConstants.SMS_BENEFICIARIO ,msgIf.getMessaggioPerCodice("richiedeIntegrazione"+SharedConstants.SMS_BENEFICIARIO).getMessaggiostr());
				
				instance.notificatoreParams.put("richiedeIntegrazione"+SharedConstants.MEX_TITLE_BENEFICIARIO ,msgIf.getMessaggioPerCodice("richiedeIntegrazione"+SharedConstants.MEX_TITLE_BENEFICIARIO).getMessaggiostr());
				instance.notificatoreParams.put("richiedeIntegrazione"+SharedConstants.MEX_BENEFICIARIO,msgIf.getMessaggioPerCodice("richiedeIntegrazione"+SharedConstants.MEX_BENEFICIARIO).getMessaggiostr());				
				instance.notificatoreParams.put("richiedeIntegrazione"+SharedConstants.MEX_TITLE_DELEGATO,msgIf.getMessaggioPerCodice("richiedeIntegrazione"+SharedConstants.MEX_TITLE_DELEGATO).getMessaggiostr());
				instance.notificatoreParams.put("richiedeIntegrazione"+SharedConstants.MEX_DELEGATO,msgIf.getMessaggioPerCodice("richiedeIntegrazione"+SharedConstants.MEX_DELEGATO).getMessaggiostr());
				instance.notificatoreParams.put("richiedeIntegrazione"+SharedConstants.MEX_BENEFICIARIO_BY_DELEGATO,msgIf.getMessaggioPerCodice("richiedeIntegrazione"+SharedConstants.MEX_BENEFICIARIO_BY_DELEGATO).getMessaggiostr());
				instance.notificatoreParams.put("richiedeIntegrazione"+SharedConstants.MEX_CALL_TO_ACTION_BENEFICIARIO,msgIf.getMessaggioPerCodice("richiedeIntegrazione"+SharedConstants.MEX_CALL_TO_ACTION_BENEFICIARIO).getMessaggiostr());
				instance.notificatoreParams.put("richiedeIntegrazione"+SharedConstants.MEX_CALL_TO_ACTION_DELEGATO,msgIf.getMessaggioPerCodice("richiedeIntegrazione"+SharedConstants.MEX_CALL_TO_ACTION_DELEGATO).getMessaggiostr());
				
				instance.notificatoreParams.put(SharedConstants.NOTIFICATORE_TEMPLATE, msgIf.getMessaggioPerCodice(SharedConstants.NOTIFICATORE_TEMPLATE).getMessaggiostr());
				
//				ListeFacadeIf listIf = (ListeFacadeIf) SpringApplicationContextHelper.getBean("listFacade");
//				instance.listaOperazioniAmministratore = listIf.getAllAzioniAmministratore();
//				instance.listaOperazioniOperatore = listIf.getAllAzioniOperatore();				
								
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return instance;
	}

	public List<DatiAnagrafici> findCittadino(String codiceFiscale, String cognome, String nome, String dataNascita, ScerevLAudit audit) 
			throws IllegalArgumentException, IllegalAccessException, AuraException, AuraNotFoundException, Exception{
		
		AuraFacade facade = new AuraFacade();
		
		Map<String, Object> returnMap = facade.findCittadino(codiceFiscale, cognome, nome, dataNascita,
				Converter.convertObjToMapString(new ConnectionInfo(this.auraFindEndpoint, this.auraUsername, this.auraPassword)));

		String xmlRequest = (String) returnMap.get("request");
		String xmlResponse = (String) returnMap.get("response");
		List<DatiAnagrafici> value = (List<DatiAnagrafici>) returnMap.get("value");
		AuditFacadeIf auditFacade = (AuditFacadeIf) SpringApplicationContextHelper.getBean(SharedConstants.AUDIT_FACADE);
		
		if(audit!=null) {			
			audit.setOggOper("AnagrafeFind - Request");
			audit.setKeyOper(null);
			auditFacade.insertAudit(new ScerevLAudit(audit));
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
		}								
		
		log.info(xmlRequest);
		log.info(xmlResponse);
		
		
		
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

	public SoggettoAuraBodyNew getCittadino(BigDecimal idProfiloAnagrafico, ScerevLAudit audit)
			throws IllegalArgumentException, IllegalAccessException, Exception {
		AuraFacade facade = new AuraFacade();

		Map<String, Object> returnMap = facade.getCittadino(idProfiloAnagrafico, Converter
				.convertObjToMapString(new ConnectionInfo(this.auraGetEndpoint, this.auraUsername, this.auraPassword)));

		String xmlRequest = (String) returnMap.get("request");
		String xmlResponse = (String) returnMap.get("response");
		if(audit!=null) {
			AuditFacadeIf auditFacade = (AuditFacadeIf) SpringApplicationContextHelper
					.getBean(SharedConstants.AUDIT_FACADE);
			audit.setOggOper("AnagrafeFind - Request");
			////audit.setKeyOper(xmlRequest);
			auditFacade.insertAudit(new ScerevLAudit(audit));
	
			audit.setOggOper("AnagrafeFind - Response");
			audit.setKeyOper(SharedConstants.KEY_OPER_OK);
			auditFacade.insertAudit(new ScerevLAudit(audit));
			
		}

		if (returnMap.get("value") == null) {
			throw new AuraException(xmlResponse);
		} else if(returnMap.get("value") instanceof String && ((String)returnMap.get("value")).equals(SharedConstants.ERRORE_AURA)) {
			throw new AuraNotFoundException();
		}

		log.info(xmlRequest);
		log.info(xmlResponse);
		
		SoggettoAuraBodyNew value = (SoggettoAuraBodyNew) returnMap.get("value");
		return value;
	}
	
	public Map<DatiAnagrafici, SoggettoAuraBodyNew> anagrafeSanitariaAttiva(List<DatiAnagrafici> auraFind, ScerevLAudit audit) throws IllegalArgumentException, IllegalAccessException, Exception {
		Map<DatiAnagrafici, SoggettoAuraBodyNew> auraGet = null;
		if (auraFind != null) {
			for (DatiAnagrafici da : auraFind) {
				SoggettoAuraBodyNew anagrafeSanitaria = getCittadino(da.getIdProfiloAnagrafico(), audit);
				if (anagrafeSanitaria.getInfoAnag().getDatiPrimari().getStatoProfiloAnagrafico().equalsIgnoreCase("1")) {
					auraGet = new HashMap<DatiAnagrafici, SoggettoAuraBodyNew>();
					auraGet.put(da, anagrafeSanitaria);
					return auraGet;
				}
			}
		}
		return auraGet;
	}
	
	public SoggettoOpessanBody getDettaglioOperatore(BigDecimal idAuraMedico, ScerevLAudit audit) throws Exception {
		OpessanFacade facade = new OpessanFacade();
		Map<String, String> bodyMap = new HashMap<String, String>();
		Map<String, String> headerMap = new HashMap<String, String>();
		Map<String, String> conn = Converter
				.convertObjToMapString(new ConnectionInfo(this.opessanDettaglioOperatoreEndpoint, this.opessanUsername, this.opessanPassword));
	
		bodyMap.put("idAura", idAuraMedico.toString());			
		
//		headerMap.get("idAsr");
//		headerMap.get("receiver");
//		headerMap.get("sender");
		
		Map<String, Object> returnMap = facade.getDettaglioOperatore(bodyMap, headerMap, conn);

		if(audit!=null) {
			String xmlRequest = (String) returnMap.get("request");
			AuditFacadeIf auditFacade = (AuditFacadeIf) SpringApplicationContextHelper
					.getBean(SharedConstants.AUDIT_FACADE);
			audit.setOggOper("getDettaglioOperatore - Request");
			//audit.setKeyOper(xmlRequest);
			auditFacade.insertAudit(new ScerevLAudit(audit));
	
			String xmlResponse = (String) returnMap.get("response");
			audit.setOggOper("getDettaglioOperatore - Response");
			audit.setKeyOper(SharedConstants.KEY_OPER_OK);
			auditFacade.insertAudit(new ScerevLAudit(audit));
			
			log.info(xmlRequest);
			log.info(xmlResponse);
			
		}
		SoggettoOpessanBody value = (SoggettoOpessanBody) returnMap.get("value");
		if (value == null)
			throw new OpessanNotFoundException();

		return value;
	}

	public List<Ruolo> findRuoli(Identita identita, ScerevLAudit audit) throws Exception {
			IrideFacade facade = new IrideFacade();
			Map<String, String> body = Converter.convertObjToMapString(identita);
			Map<String, String> conn = Converter.convertObjToMapString(new ConnectionInfo(this.irideBaseEndpoint, null, null));
			body.put("id", SharedConstants.SCEREVIRIDE);
			
			Map<String, Object> returnMap = facade.findRuoli(body, conn);
			
			if(audit!=null) {
				String xmlRequest = (String) returnMap.get("request");
				AuditFacadeIf auditFacade = (AuditFacadeIf) SpringApplicationContextHelper
						.getBean(SharedConstants.AUDIT_FACADE);
				audit.setOggOper("findRuoliForPersonaInApplication - Request");
				//audit.setKeyOper(xmlRequest);
				auditFacade.insertAudit(new ScerevLAudit(audit));
		
				String xmlResponse = (String) returnMap.get("response");
				audit.setOggOper("findRuoliForPersonaInApplication - Response");
				audit.setKeyOper(SharedConstants.KEY_OPER_OK);
				auditFacade.insertAudit(new ScerevLAudit(audit));
				
				log.info(xmlRequest);
				log.info(xmlResponse);
			}
						
			List<Ruolo> value = (List<Ruolo>) returnMap.get("value");

			if (value == null)
				throw new InternalException();
			
			if(value.size() == 0)
				throw new IrideNotFoundException();			
			
			return value;
							
	}
	
	public boolean sendMessage(String webService, String beneficiario, String codFiscaleBen, String numPratica, String motivazione, String delegato, String codFiscaleDel, String medico, String data, ScerevLAudit audit, boolean deleganteMinorenne) {		
		try {		
			Map<String, String> replacements = new HashMap<String, String>();
			replacements.put("@BENEFICIARIO@", beneficiario);
			replacements.put("@NUMPRATICA@", numPratica);
			replacements.put("@MOTIVAZIONE@", motivazione);
			replacements.put("@DELEGATO@", delegato);
			replacements.put("@MEDICO@", medico);
			replacements.put("@SYSTEMDATE@", data);
			
			if(deleganteMinorenne) notificatoreParams.put("tag", "sanita,r_piemon,medico,pediatra,cambio,comunicazioni,figlio_minore");
			else 				   notificatoreParams.put("tag", "sanita,r_piemon,medico,pediatra,cambio,comunicazioni");
			
			NotificatoreFacade facade = new NotificatoreFacade();
			Map<String, Object> returnMap = facade.sendMessage(webService, codFiscaleBen, codFiscaleDel, replacements, notificatoreParams);
			
			String xmlResponse = "OK";
			if(audit!=null) {
				AuditFacadeIf auditFacade = (AuditFacadeIf) SpringApplicationContextHelper
						.getBean(SharedConstants.AUDIT_FACADE);
				audit.setOggOper("sendMessage - Request");
				auditFacade.insertAudit(new ScerevLAudit(audit));
		
				xmlResponse = (String) returnMap.get("value");
				audit.setOggOper("getDettaglioOperatore - Response");
				//audit.setKeyOper(xmlResponse);
				auditFacade.insertAudit(new ScerevLAudit(audit));
			}			
			if(xmlResponse.equalsIgnoreCase("OK")) return true;		
			else return false;
		} catch (Exception e) {
			return false;
		}
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
			
			Map<String, Object> returnMap = facade.getAslDistrettoAmbito(descComune.toUpperCase(), tipologiaMedico.toUpperCase(),
					Converter.convertObjToMapString(new ConnectionInfo(this.auraGetAslDistrettoAmbito, this.auraGetAslDistrettoAmbitoUsername, this.auraGetAslDistrettoAmbitoPassword)));
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

	public String getIrideBaseEndpoint() {
		return irideBaseEndpoint;
	}

	public void setIrideBaseEndpoint(String irideBaseEndpoint) {
		this.irideBaseEndpoint = irideBaseEndpoint;
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

	public String getAuraGetNumeroAssistitiInCaricoMedicoEndpoint() {
		return auraGetNumeroAssistitiInCaricoMedicoEndpoint;
	}

	public void setAuraGetNumeroAssistitiInCaricoMedicoEndpoint(String auraGetNumeroAssistitiInCaricoMedicoEndpoint) {
		this.auraGetNumeroAssistitiInCaricoMedicoEndpoint = auraGetNumeroAssistitiInCaricoMedicoEndpoint;
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

	public void setAuraSetAggiornamentoProfiloAnagraficoPassword(String auraSetAggiornamentoProfiloAnagraficoPassword) {
		this.auraSetAggiornamentoProfiloAnagraficoPassword = auraSetAggiornamentoProfiloAnagraficoPassword;
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

	public Map<String, String> getNotificatoreParams() {
		return notificatoreParams;
	}

	public void setNotificatoreParams(Map<String, String> notificatoreParams) {
		this.notificatoreParams = notificatoreParams;
	}

}
