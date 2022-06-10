/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevlibbatch.serviziesterni;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import it.csi.scerev.scerevlibbatch.dao.DataDAO;
import it.csi.scerev.scerevlibbatch.dto.ConnectionInfo;
import it.csi.scerev.scerevlibbatch.exception.ConnectionException;
import it.csi.scerev.scerevlibbatch.exception.OpessanNotFoundException;
import it.csi.scerev.scerevlibbatch.facade.NotificatoreFacade;
import it.csi.scerev.scerevlibbatch.facade.OpessanFacade;
import it.csi.scerev.scerevlibbatch.opessan.def.opessanws.opessan.MassimaliMediciBody;
import it.csi.scerev.scerevlibbatch.util.Converter;
import it.csi.scerev.scerevlibbatch.util.SharedConstants;

public class ServiziEsterniClient {
	
	private String opessanMassimaliMediciEndpoint;
	private String opessanUsername;
	private String opessanPassword;

	private Map<String,String> notificatoreParams;
	
	private final static Logger log = Logger.getLogger(ServiziEsterniClient.class.getName());
	
	private static ServiziEsterniClient instance;

	private ServiziEsterniClient() {
	}

	public static ServiziEsterniClient getInstance(DataDAO dao) {
		if (instance == null) {
			instance = new ServiziEsterniClient();
			try {
				
				instance.opessanMassimaliMediciEndpoint = (dao.getParametro("OPESSAN_MASSIMALI_MEDICO_ENDPOINT"));
				instance.opessanPassword				= (dao.getParametro("OPESSAN_PASSWORD"));
				instance.opessanUsername				= (dao.getParametro("OPESSAN_USERNAME"));


				instance.notificatoreParams = new HashMap<String,String>();	
				
				instance.notificatoreParams.put("NOTIFICATORE_TOKEN",dao.getParametro("NOTIFICATORE_TOKEN"));
				instance.notificatoreParams.put("NOTIFICATORE_ENDPOINT", dao.getParametro("NOTIFICATORE_ENDPOINT"));
				
				instance.notificatoreParams.put(SharedConstants.NOTIFICATORE_TEMPLATE, dao.getParametro(SharedConstants.NOTIFICATORE_TEMPLATE));
				
				instance.notificatoreParams.put("mediciMonitorati"+SharedConstants.EMAIL_SUBJECT_BENEFICIARIO,dao.getParametro("mediciMonitorati"+SharedConstants.EMAIL_SUBJECT_BENEFICIARIO));
				instance.notificatoreParams.put("mediciMonitorati"+SharedConstants.EMAIL_BENEFICIARIO,dao.getParametro("mediciMonitorati"+SharedConstants.EMAIL_BENEFICIARIO));				
				instance.notificatoreParams.put("mediciMonitorati"+SharedConstants.PUSH_TITLE_BENEFICIARIO ,dao.getParametro("mediciMonitorati"+SharedConstants.PUSH_TITLE_BENEFICIARIO));
				instance.notificatoreParams.put("mediciMonitorati"+SharedConstants.PUSH_BENEFICIARIO,dao.getParametro("mediciMonitorati"+SharedConstants.PUSH_BENEFICIARIO));

				instance.notificatoreParams.put("mediciMonitorati"+SharedConstants.MEX_TITLE_BENEFICIARIO ,dao.getParametro("mediciMonitorati"+SharedConstants.MEX_TITLE_BENEFICIARIO));
				instance.notificatoreParams.put("mediciMonitorati"+SharedConstants.MEX_BENEFICIARIO,dao.getParametro("mediciMonitorati"+SharedConstants.MEX_BENEFICIARIO));				
				instance.notificatoreParams.put("mediciMonitorati"+SharedConstants.MEX_CALL_TO_ACTION_BENEFICIARIO,dao.getParametro("mediciMonitorati"+SharedConstants.MEX_CALL_TO_ACTION_BENEFICIARIO));
				
//
//				
//				instance.notificatoreParams.put("prendiInCarico"+SharedConstants.EMAIL_SUBJECT_BENEFICIARIO,dao.getParametro("prendiInCarico"+SharedConstants.EMAIL_SUBJECT_BENEFICIARIO));
//				instance.notificatoreParams.put("prendiInCarico"+SharedConstants.EMAIL_BENEFICIARIO,dao.getParametro("prendiInCarico"+SharedConstants.EMAIL_BENEFICIARIO));				
//				instance.notificatoreParams.put("prendiInCarico"+SharedConstants.EMAIL_SUBJECT_DELEGATO,dao.getParametro("prendiInCarico"+SharedConstants.EMAIL_SUBJECT_DELEGATO));
//				instance.notificatoreParams.put("prendiInCarico"+SharedConstants.EMAIL_DELEGATO ,dao.getParametro("prendiInCarico"+SharedConstants.EMAIL_DELEGATO));				
//				instance.notificatoreParams.put("prendiInCarico"+SharedConstants.PUSH_TITLE_BENEFICIARIO ,dao.getParametro("prendiInCarico"+SharedConstants.PUSH_TITLE_BENEFICIARIO));
//				instance.notificatoreParams.put("prendiInCarico"+SharedConstants.PUSH_BENEFICIARIO,dao.getParametro("prendiInCarico"+SharedConstants.PUSH_BENEFICIARIO));				
//				instance.notificatoreParams.put("prendiInCarico"+SharedConstants.PUSH_TITLE_DELEGATO,dao.getParametro("prendiInCarico"+SharedConstants.PUSH_TITLE_DELEGATO));
//				instance.notificatoreParams.put("prendiInCarico"+SharedConstants.PUSH_DELEGATO,dao.getParametro("prendiInCarico"+SharedConstants.PUSH_DELEGATO));
//				instance.notificatoreParams.put("prendiInCarico"+SharedConstants.EMAIL_BENEFICIARIO_BY_DELEGATO,dao.getParametro("prendiInCarico"+SharedConstants.EMAIL_BENEFICIARIO_BY_DELEGATO));
//				instance.notificatoreParams.put("prendiInCarico"+SharedConstants.PUSH_BENEFICIARIO_BY_DELEGATO,dao.getParametro("prendiInCarico"+SharedConstants.PUSH_BENEFICIARIO_BY_DELEGATO));
//				
//				instance.notificatoreParams.put("accettaRichiesta"+SharedConstants.EMAIL_SUBJECT_BENEFICIARIO,dao.getParametro("accettaRichiesta"+SharedConstants.EMAIL_SUBJECT_BENEFICIARIO));
//				instance.notificatoreParams.put("accettaRichiesta"+SharedConstants.EMAIL_BENEFICIARIO,dao.getParametro("accettaRichiesta"+SharedConstants.EMAIL_BENEFICIARIO));				
//				instance.notificatoreParams.put("accettaRichiesta"+SharedConstants.EMAIL_SUBJECT_DELEGATO,dao.getParametro("accettaRichiesta"+SharedConstants.EMAIL_SUBJECT_DELEGATO));
//				instance.notificatoreParams.put("accettaRichiesta"+SharedConstants.EMAIL_DELEGATO ,dao.getParametro("accettaRichiesta"+SharedConstants.EMAIL_DELEGATO));				
//				instance.notificatoreParams.put("accettaRichiesta"+SharedConstants.PUSH_TITLE_BENEFICIARIO ,dao.getParametro("accettaRichiesta"+SharedConstants.PUSH_TITLE_BENEFICIARIO));
//				instance.notificatoreParams.put("accettaRichiesta"+SharedConstants.PUSH_BENEFICIARIO,dao.getParametro("accettaRichiesta"+SharedConstants.PUSH_BENEFICIARIO));				
//				instance.notificatoreParams.put("accettaRichiesta"+SharedConstants.PUSH_TITLE_DELEGATO,dao.getParametro("accettaRichiesta"+SharedConstants.PUSH_TITLE_DELEGATO));
//				instance.notificatoreParams.put("accettaRichiesta"+SharedConstants.PUSH_DELEGATO,dao.getParametro("accettaRichiesta"+SharedConstants.PUSH_DELEGATO));
//				instance.notificatoreParams.put("accettaRichiesta"+SharedConstants.EMAIL_BENEFICIARIO_BY_DELEGATO,dao.getParametro("accettaRichiesta"+SharedConstants.EMAIL_BENEFICIARIO_BY_DELEGATO));
//				instance.notificatoreParams.put("accettaRichiesta"+SharedConstants.PUSH_BENEFICIARIO_BY_DELEGATO,dao.getParametro("accettaRichiesta"+SharedConstants.PUSH_BENEFICIARIO_BY_DELEGATO));
//				
//				instance.notificatoreParams.put("respingeRichiesta"+SharedConstants.EMAIL_SUBJECT_BENEFICIARIO,dao.getParametro("respingeRichiesta"+SharedConstants.EMAIL_SUBJECT_BENEFICIARIO));
//				instance.notificatoreParams.put("respingeRichiesta"+SharedConstants.EMAIL_BENEFICIARIO,dao.getParametro("respingeRichiesta"+SharedConstants.EMAIL_BENEFICIARIO));				
//				instance.notificatoreParams.put("respingeRichiesta"+SharedConstants.EMAIL_SUBJECT_DELEGATO,dao.getParametro("respingeRichiesta"+SharedConstants.EMAIL_SUBJECT_DELEGATO));
//				instance.notificatoreParams.put("respingeRichiesta"+SharedConstants.EMAIL_DELEGATO ,dao.getParametro("respingeRichiesta"+SharedConstants.EMAIL_DELEGATO));				
//				instance.notificatoreParams.put("respingeRichiesta"+SharedConstants.PUSH_TITLE_BENEFICIARIO ,dao.getParametro("respingeRichiesta"+SharedConstants.PUSH_TITLE_BENEFICIARIO));
//				instance.notificatoreParams.put("respingeRichiesta"+SharedConstants.PUSH_BENEFICIARIO,dao.getParametro("respingeRichiesta"+SharedConstants.PUSH_BENEFICIARIO));				
//				instance.notificatoreParams.put("respingeRichiesta"+SharedConstants.PUSH_TITLE_DELEGATO,dao.getParametro("respingeRichiesta"+SharedConstants.PUSH_TITLE_DELEGATO));
//				instance.notificatoreParams.put("respingeRichiesta"+SharedConstants.PUSH_DELEGATO,dao.getParametro("respingeRichiesta"+SharedConstants.PUSH_DELEGATO));
//				instance.notificatoreParams.put("respingeRichiesta"+SharedConstants.EMAIL_BENEFICIARIO_BY_DELEGATO,dao.getParametro("respingeRichiesta"+SharedConstants.EMAIL_BENEFICIARIO_BY_DELEGATO));
//				instance.notificatoreParams.put("respingeRichiesta"+SharedConstants.PUSH_BENEFICIARIO_BY_DELEGATO,dao.getParametro("respingeRichiesta"+SharedConstants.PUSH_BENEFICIARIO_BY_DELEGATO));
////				instance.notificatoreParams.put("respingeRichiesta"+SharedConstants.SMS_BENEFICIARIO ,dao.getParametro("respingeRichiesta"+SharedConstants.SMS_BENEFICIARIO));
//				
//				instance.notificatoreParams.put("richiedeIntegrazione"+SharedConstants.EMAIL_SUBJECT_BENEFICIARIO,dao.getParametro("richiedeIntegrazione"+SharedConstants.EMAIL_SUBJECT_BENEFICIARIO));
//				instance.notificatoreParams.put("richiedeIntegrazione"+SharedConstants.EMAIL_BENEFICIARIO,dao.getParametro("richiedeIntegrazione"+SharedConstants.EMAIL_BENEFICIARIO));				
//				instance.notificatoreParams.put("richiedeIntegrazione"+SharedConstants.EMAIL_SUBJECT_DELEGATO,dao.getParametro("richiedeIntegrazione"+SharedConstants.EMAIL_SUBJECT_DELEGATO));
//				instance.notificatoreParams.put("richiedeIntegrazione"+SharedConstants.EMAIL_DELEGATO ,dao.getParametro("richiedeIntegrazione"+SharedConstants.EMAIL_DELEGATO));				
//				instance.notificatoreParams.put("richiedeIntegrazione"+SharedConstants.PUSH_TITLE_BENEFICIARIO ,dao.getParametro("richiedeIntegrazione"+SharedConstants.PUSH_TITLE_BENEFICIARIO));
//				instance.notificatoreParams.put("richiedeIntegrazione"+SharedConstants.PUSH_BENEFICIARIO,dao.getParametro("richiedeIntegrazione"+SharedConstants.PUSH_BENEFICIARIO));				
//				instance.notificatoreParams.put("richiedeIntegrazione"+SharedConstants.PUSH_TITLE_DELEGATO,dao.getParametro("richiedeIntegrazione"+SharedConstants.PUSH_TITLE_DELEGATO));
//				instance.notificatoreParams.put("richiedeIntegrazione"+SharedConstants.PUSH_DELEGATO,dao.getParametro("richiedeIntegrazione"+SharedConstants.PUSH_DELEGATO));				
//				instance.notificatoreParams.put("richiedeIntegrazione"+SharedConstants.EMAIL_BENEFICIARIO_BY_DELEGATO,dao.getParametro("richiedeIntegrazione"+SharedConstants.EMAIL_BENEFICIARIO_BY_DELEGATO));
//				instance.notificatoreParams.put("richiedeIntegrazione"+SharedConstants.PUSH_BENEFICIARIO_BY_DELEGATO,dao.getParametro("richiedeIntegrazione"+SharedConstants.PUSH_BENEFICIARIO_BY_DELEGATO));
////				instance.notificatoreParams.put("richiedeIntegrazione"+SharedConstants.SMS_BENEFICIARIO ,dao.getParametro("richiedeIntegrazione"+SharedConstants.SMS_BENEFICIARIO));		
				
				
//				ListeFacadeIf listIf = (ListeFacadeIf) SpringApplicationContextHelper.getBean("listFacade");
//				instance.listaOperazioniAmministratore = listIf.getAllAzioniAmministratore();
//				instance.listaOperazioniOperatore = listIf.getAllAzioniOperatore();				
								
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return instance;
	}


	public MassimaliMediciBody getMassimaliMedici(BigDecimal idAura
//			,scerevLAudit audit
			) throws Exception {
		OpessanFacade facade = new OpessanFacade();
		Map<String, String> bodyMap = new HashMap<String, String>();
		Map<String, String> headerMap = new HashMap<String, String>();
		Map<String, String> conn = Converter.convertObjToMapString(
				new ConnectionInfo(this.opessanMassimaliMediciEndpoint, this.opessanUsername, this.opessanPassword));
	
		bodyMap.put("idAura", idAura.toString());			

		headerMap.put("sender", "SCEREV_WS");
		Map<String, Object> returnMap = facade.getMassimaliMedici(bodyMap, headerMap, conn);

//		if(audit!=null) {
//			String xmlRequest = (String) returnMap.get("request");
//			AuditFacadeIf auditFacade = (AuditFacadeIf) SpringApplicationContextHelper
//					.getBean(SharedConstants.AUDIT_FACADE);
//			audit.setOggOper("getDettaglioOperatore - Request");
//			//audit.setKeyOper(xmlRequest);
//			auditFacade.insertAudit(new ScerevLAudit(audit));
//	
//			String xmlResponse = (String) returnMap.get("response");
//			audit.setOggOper("getDettaglioOperatore - Response");
//			audit.setKeyOper(SharedConstants.KEY_OPER_OK);
//			auditFacade.insertAudit(new ScerevLAudit(audit));
//			
//			log.info(xmlRequest);
//			log.info(xmlResponse);
//			
//		}
		MassimaliMediciBody value = (MassimaliMediciBody) returnMap.get("value");
		return value;
	}
	
	public boolean sendMessage(String webService, String beneficiario, String codFiscaleBen, String medicoMonitorato, String disponibilita
//			,String motivazione, String delegato, String codFiscaleDel, String medico, String data
//			,ScerevLAudit audit
			){		
		try {		
			Map<String, String> replacements = new HashMap<String, String>();
			replacements.put("@ASSISTITO@", beneficiario);
			replacements.put("@MEDICO_MONITORATO@", medicoMonitorato);
			replacements.put("@TIPO_DISPONIBILITA@", disponibilita);
			
			NotificatoreFacade facade = new NotificatoreFacade();
			Map<String, Object> returnMap = facade.sendMessage(webService, codFiscaleBen, null, replacements, notificatoreParams);
			
			String xmlResponse = (String) returnMap.get("value");
//			if(audit!=null) {
//				AuditFacadeIf auditFacade = (AuditFacadeIf) SpringApplicationContextHelper
//						.getBean(SharedConstants.AUDIT_FACADE);
//				audit.setOggOper("sendMessage - Request");
//				auditFacade.insertAudit(new ScerevLAudit(audit));
//		
//				xmlResponse = (String) returnMap.get("value");
//				audit.setOggOper("getDettaglioOperatore - Response");
//				//audit.setKeyOper(xmlResponse);
//				auditFacade.insertAudit(new ScerevLAudit(audit));
//			}			
			if(xmlResponse.equalsIgnoreCase("OK")) return true;		
			else return false;
					
		} catch (Exception e) {
			throw e;
		}
		
	}		
	


	public String getOpessanMassimaliMediciEndpoint() {
		return opessanMassimaliMediciEndpoint;
	}

	public void setOpessanMassimaliMediciEndpoint(String opessanMassimaliMediciEndpoint) {
		this.opessanMassimaliMediciEndpoint = opessanMassimaliMediciEndpoint;
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

	public Map<String, String> getNotificatoreParams() {
		return notificatoreParams;
	}

	public void setNotificatoreParams(Map<String, String> notificatoreParams) {
		this.notificatoreParams = notificatoreParams;
	}

}
