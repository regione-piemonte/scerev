/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scadenzaassistenza.serviziesterni;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.WebServiceException;

import it.csi.scerev.scadenzaassistenza.dao.DataDAO;
import it.csi.scerev.scadenzaassistenza.deleghebe.ApplicazioneRichiedente;
import it.csi.scerev.scadenzaassistenza.deleghebe.DelegaCittadino;
import it.csi.scerev.scadenzaassistenza.deleghebe.DelegheCittadiniService_Service;
import it.csi.scerev.scadenzaassistenza.deleghebe.GetDelegati;
import it.csi.scerev.scadenzaassistenza.deleghebe.GetDelegatiResponse;
import it.csi.scerev.scadenzaassistenza.deleghebe.Richiedente;
import it.csi.scerev.scadenzaassistenza.util.SharedConstants;
import it.csi.scerev.scadenzaassistenza.facade.NotificatoreFacade;


public class ServiziEsterniClient {
	
	private String usernameDeleghe;
	private String passwordDeleghe;
	private String urlDeleghe;
	
	private Map<String,String> notificatoreParams;

	private static ServiziEsterniClient instance;
	
//	private final static org.apache.log4j.Logger log = BatchLoggerFactory.getLogger(ServiziEsterniClient.class);

	private ServiziEsterniClient() {
	}
	
	public static ServiziEsterniClient getInstance(DataDAO dao) {
		if (instance == null) {
			instance = new ServiziEsterniClient();
			try {			
				instance.notificatoreParams = new HashMap<String,String>();	
				
				instance.notificatoreParams.put("NOTIFICATORE_TOKEN",dao.getParametro("NOTIFICATORE_TOKEN"));
				instance.notificatoreParams.put("NOTIFICATORE_ENDPOINT", dao.getParametro("NOTIFICATORE_ENDPOINT"));
				
				instance.notificatoreParams.put(SharedConstants.NOTIFICATORE_TEMPLATE, dao.getParametro(SharedConstants.NOTIFICATORE_TEMPLATE));
				
				instance.notificatoreParams.put("scadenzaAssistenza"+SharedConstants.EMAIL_SUBJECT_BENEFICIARIO,dao.getParametro("scadenzaAssistenza"+SharedConstants.EMAIL_SUBJECT_BENEFICIARIO));
				instance.notificatoreParams.put("scadenzaAssistenza"+SharedConstants.EMAIL_BENEFICIARIO,dao.getParametro("scadenzaAssistenza"+SharedConstants.EMAIL_BENEFICIARIO));				
				
				instance.notificatoreParams.put("scadenzaAssistenza"+SharedConstants.PUSH_TITLE_BENEFICIARIO ,dao.getParametro("scadenzaAssistenza"+SharedConstants.PUSH_TITLE_BENEFICIARIO));
				instance.notificatoreParams.put("scadenzaAssistenza"+SharedConstants.PUSH_BENEFICIARIO,dao.getParametro("scadenzaAssistenza"+SharedConstants.PUSH_BENEFICIARIO));

				instance.notificatoreParams.put("scadenzaAssistenza"+SharedConstants.MEX_TITLE_BENEFICIARIO ,dao.getParametro("scadenzaAssistenza"+SharedConstants.MEX_TITLE_BENEFICIARIO));
				instance.notificatoreParams.put("scadenzaAssistenza"+SharedConstants.MEX_BENEFICIARIO,dao.getParametro("scadenzaAssistenza"+SharedConstants.MEX_BENEFICIARIO));				
				instance.notificatoreParams.put("scadenzaAssistenza"+SharedConstants.MEX_CALL_TO_ACTION_BENEFICIARIO,dao.getParametro("scadenzaAssistenza"+SharedConstants.MEX_CALL_TO_ACTION_BENEFICIARIO));				
				
				instance.notificatoreParams.put("scadenzaAssistenza"+SharedConstants.EMAIL_SUBJECT_DELEGATO,dao.getParametro("scadenzaAssistenza"+SharedConstants.EMAIL_SUBJECT_DELEGATO));
				instance.notificatoreParams.put("scadenzaAssistenza"+SharedConstants.EMAIL_DELEGATO,dao.getParametro("scadenzaAssistenza"+SharedConstants.EMAIL_DELEGATO));				
				
				instance.notificatoreParams.put("scadenzaAssistenza"+SharedConstants.PUSH_TITLE_DELEGATO,dao.getParametro("scadenzaAssistenza"+SharedConstants.PUSH_TITLE_DELEGATO));
				instance.notificatoreParams.put("scadenzaAssistenza"+SharedConstants.PUSH_DELEGATO,dao.getParametro("scadenzaAssistenza"+SharedConstants.PUSH_DELEGATO));

				instance.notificatoreParams.put("scadenzaAssistenza"+SharedConstants.MEX_TITLE_DELEGATO,dao.getParametro("scadenzaAssistenza"+SharedConstants.MEX_TITLE_DELEGATO));
				instance.notificatoreParams.put("scadenzaAssistenza"+SharedConstants.MEX_DELEGATO,dao.getParametro("scadenzaAssistenza"+SharedConstants.MEX_DELEGATO));				
				instance.notificatoreParams.put("scadenzaAssistenza"+SharedConstants.MEX_CALL_TO_ACTION_DELEGATO,dao.getParametro("scadenzaAssistenza"+SharedConstants.MEX_CALL_TO_ACTION_DELEGATO));
				
				instance.setUsernameDeleghe(dao.getParametro(SharedConstants.DELEGHE_USERNAME));
				instance.setPasswordDeleghe(dao.getParametro(SharedConstants.DELEGHE_PASSWORD));
				instance.setUrlDeleghe(dao.getParametro(SharedConstants.DELEGHE) + "?wsdl");
				java.net.Authenticator.setDefault(new java.net.Authenticator() {

					@Override
					protected java.net.PasswordAuthentication getPasswordAuthentication() {
						return new java.net.PasswordAuthentication(instance.getUsernameDeleghe(),
								instance.getPasswordDeleghe().toCharArray());
					}
				});
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return instance;
	}
	
	public static ServiziEsterniClient getInstance() {
		return instance;
	}

	public boolean sendMessage(String webService, String beneficiario, String codFiscaleBen, String codFiscaleDel, String delegato, String dataScadenza
//			,String motivazione, String delegato, String codFiscaleDel, String medico, String data
//			,ScerevLAudit audit
			){		
		try {		
			Map<String, String> replacements = new HashMap<String, String>();
			replacements.put("@BENEFICIARIO@", beneficiario);
			replacements.put("@DELEGATO@", delegato);
			replacements.put("@DATA_SCADENZA_ASSISTENZA@", dataScadenza);
			
			NotificatoreFacade facade = new NotificatoreFacade();
			Map<String, Object> returnMap = facade.sendMessage(webService, codFiscaleBen, codFiscaleDel, replacements, notificatoreParams);
			
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
	
	
	public List<DelegaCittadino> chiamaGetDelegati(String codicefiscale) throws Exception {

		String endpoint = this.getUrlDeleghe();
		DelegheCittadiniService_Service service = new DelegheCittadiniService_Service();
		it.csi.scerev.scadenzaassistenza.deleghebe.DelegheCittadiniService port = service.getDelegheCittadiniServiceImplPort();

		BindingProvider prov = (BindingProvider) port;

		prov.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpoint);

		prov.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, this.usernameDeleghe);
		prov.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, this.passwordDeleghe);

		GetDelegati cRequest = new GetDelegati();
		List<DelegaCittadino> elencoDelegati = new ArrayList<DelegaCittadino>();

		// --------------Dati Delega---------------

		it.csi.scerev.scadenzaassistenza.deleghebe.Cittadino cittadinodelegante = new it.csi.scerev.scadenzaassistenza.deleghebe.Cittadino();
		Richiedente richiedente = new Richiedente();
		ApplicazioneRichiedente applicazionerichiedente = new ApplicazioneRichiedente();
		it.csi.scerev.scadenzaassistenza.deleghebe.GetDelegati.CodiciServizio codiciservizio = new it.csi.scerev.scadenzaassistenza.deleghebe.GetDelegati.CodiciServizio();
		it.csi.scerev.scadenzaassistenza.deleghebe.GetDelegati.StatiDelega statidelega = new it.csi.scerev.scadenzaassistenza.deleghebe.GetDelegati.StatiDelega();
		applicazionerichiedente.setCodice("SCEREV");
		applicazionerichiedente.setIdRequest("");
		richiedente.setServizio(applicazionerichiedente);
		richiedente.setCodiceFiscale(codicefiscale);
		cittadinodelegante.setCodiceFiscale(codicefiscale);
		cRequest.setRichiedente(richiedente);
		cRequest.setCittadinoDelegante(cittadinodelegante);
		codiciservizio.getCodiceServizio().add(0, "SCEREV");
		statidelega.getStatoDelega().add(0, "ATTIVA");
		statidelega.getStatoDelega().add(1, "AGGIORNATA");
		statidelega.getStatoDelega().add(2, "IN_SCADENZA");
		cRequest.setCodiciServizio(codiciservizio);
		cRequest.setStatiDelega(statidelega);

		GetDelegatiResponse dati = null;

		try {
			System.out.println("--- Request getDelegati ---");
			JAXBContext jaxReq = JAXBContext.newInstance(GetDelegati.class);
			Marshaller jaxMreq = jaxReq.createMarshaller();
			jaxMreq.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxMreq.marshal(cRequest, System.out);
			System.out.println("---------------------------\n");
			
			dati = port.getDelegatiService(cRequest);
			
			System.out.println("--- Response getDelegati ---");
			JAXBContext jaxRes = JAXBContext.newInstance(GetDelegatiResponse.class);
			Marshaller jaxMres = jaxRes.createMarshaller();
			jaxMres.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxMres.marshal(dati, System.out);
			System.out.println("---------------------------\n");
			
		} catch (WebServiceException ws) {
			ws.printStackTrace();
			throw ws;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		// if (response.getEsito().equalsIgnoreCase("SUCCESSO")) {
		if (dati.getDelegati() != null) {
			for (DelegaCittadino delega : dati.getDelegati().getDelegato()) {
//				if (Converter.CalcolaEta(delega.getDataDiNascita()) <= 18) {
					// popola la delega
					elencoDelegati.add(delega);
//				}
			}
			// }
		} else {
			return null;
		}
		return elencoDelegati;
	}
	

	public String getUsernameDeleghe() {
		return usernameDeleghe;
	}

	public void setUsernameDeleghe(String usernameDeleghe) {
		this.usernameDeleghe = usernameDeleghe;
	}

	public String getPasswordDeleghe() {
		return passwordDeleghe;
	}

	public void setPasswordDeleghe(String passwordDeleghe) {
		this.passwordDeleghe = passwordDeleghe;
	}

	public String getUrlDeleghe() {
		return urlDeleghe;
	}

	public void setUrlDeleghe(String urlDeleghe) {
		this.urlDeleghe = urlDeleghe;
	}



}
