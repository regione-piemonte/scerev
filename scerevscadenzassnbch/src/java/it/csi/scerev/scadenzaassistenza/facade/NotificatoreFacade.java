/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scadenzaassistenza.facade;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import it.csi.scerev.scadenzaassistenza.notificatore.NotificatoreUtil;
import it.csi.scerev.scadenzaassistenza.util.SharedConstants;


@Service
public class NotificatoreFacade {
	
	private static final String BENEFICIARIO = "beneficiario";
	private static final String DELEGATO = "delegato";
	
	private NotificatoreUtil notificatoreUtil = NotificatoreUtil.getInstance();
	
	private final static Logger log = Logger.getLogger(NotificatoreFacade.class.getName());

	/**
	 * Interfaccia di servizio al Notificatore
	 * 
	 * @param webService     servizio cosi come mappato nella tabella dei parametri
	 *                       (es. "setRichiestaEsenzione")
	 * @param cfDestinatario codice fiscale del destinatario
	 * @param replacements   replacements mappa delle sostituzioni dei valori (es.
	 *                       [["@NUMPRATICA@","12345"],...,["@CODESENZIONE@","E01"]])
	 * @throws CheckException
	 */
	public Map<String, Object> sendMessage(String webService, String cfDestinatario, String cfDestinatarioDel, Map<String, String> replacements, Map<String, String> daoParameters) {
		Map<String, Object> mapReturn = new HashMap<String, Object>();
		try {
			Properties prop = null;
			if (replacements.containsKey("@DELEGATO@") && replacements.get("@DELEGATO@") != null &&
					replacements.containsKey("@BENEFICIARIO@") && replacements.get("@BENEFICIARIO@") != null) {
				//PROPERTIES per Delegato se presente
				prop = getPropCondivise(daoParameters);

				String emailSubjectDelegato = daoParameters.get(webService +SharedConstants.EMAIL_SUBJECT_DELEGATO) != null ? !daoParameters.get(webService +SharedConstants.EMAIL_SUBJECT_DELEGATO).isEmpty() ?  daoParameters.get(webService +SharedConstants.EMAIL_SUBJECT_DELEGATO) : null : null;
				String emailDelegato = daoParameters.get(webService + SharedConstants.EMAIL_DELEGATO) != null ? !daoParameters.get(webService +SharedConstants.EMAIL_DELEGATO).isEmpty() ?  daoParameters.get(webService + SharedConstants.EMAIL_DELEGATO) : null : null;
				if (emailDelegato != null) {
					prop.put(webService+SharedConstants.EMAIL_SUBJECT_DELEGATO, emailSubjectDelegato);
					prop.put(webService+SharedConstants.EMAIL_DELEGATO, emailDelegato);
				}
				
				String pushTitleDelegato = daoParameters.get(webService + SharedConstants.PUSH_TITLE_DELEGATO) != null ? !daoParameters.get(webService + SharedConstants.PUSH_TITLE_DELEGATO).isEmpty() ?  daoParameters.get(webService + SharedConstants.PUSH_TITLE_DELEGATO) : null : null;
				String pushDelegato = daoParameters.get(webService +SharedConstants.PUSH_DELEGATO) != null ? !daoParameters.get(webService + SharedConstants.PUSH_DELEGATO).isEmpty() ?  daoParameters.get(webService + SharedConstants.PUSH_DELEGATO) : null : null;
				if (pushDelegato != null) {
					prop.put(webService+SharedConstants.PUSH_TITLE_DELEGATO, pushTitleDelegato);
					prop.put(webService+SharedConstants.PUSH_DELEGATO, pushDelegato);
				}
				
				String mexTitleDelegato = daoParameters.get(webService + SharedConstants.MEX_TITLE_DELEGATO) != null ? !daoParameters.get(webService + SharedConstants.MEX_TITLE_DELEGATO).isEmpty() ?  daoParameters.get(webService + SharedConstants.MEX_TITLE_DELEGATO) : null : null;
				String mexDelegato = daoParameters.get(webService + SharedConstants.MEX_DELEGATO) != null ? !daoParameters.get(webService + SharedConstants.MEX_DELEGATO).isEmpty() ?  daoParameters.get(webService + SharedConstants.MEX_DELEGATO) : null : null;
				String mexCallToActionDelegato = daoParameters.get(webService + SharedConstants.MEX_CALL_TO_ACTION_DELEGATO) != null ? !daoParameters.get(webService + SharedConstants.MEX_CALL_TO_ACTION_DELEGATO).isEmpty() ? daoParameters.get(webService + SharedConstants.MEX_CALL_TO_ACTION_DELEGATO) : null : null;  
				if (mexDelegato != null) {
					prop.put(webService+SharedConstants.MEX_TITLE_DELEGATO, mexTitleDelegato);
					prop.put(webService+SharedConstants.MEX_DELEGATO, mexDelegato);
					prop.put(webService+SharedConstants.MEX_CALL_TO_ACTION_DELEGATO, mexCallToActionDelegato);
				}
				
				if (emailDelegato != null || pushDelegato  != null || mexDelegato != null) {
					log.info("NotificatoreFacade::sendMessage: effettuo la chiamata al notificatore (2)");
					stampaPropertyNotificatore(prop);
					notificatoreUtil.setProp(prop);
					notificatoreUtil.callNotificatore(webService, cfDestinatarioDel, DELEGATO, replacements);
				}
			} else if (replacements.containsKey("@BENEFICIARIO@") && replacements.get("@BENEFICIARIO@") != null) {
				//Preparo PROPERTIES
				prop = getPropCondivise(daoParameters);
				
				String emailSubjectBeneficiario = daoParameters.get(webService +SharedConstants.EMAIL_SUBJECT_BENEFICIARIO) != null ? !daoParameters.get(webService +SharedConstants.EMAIL_SUBJECT_BENEFICIARIO).isEmpty() ?  daoParameters.get(webService +SharedConstants.EMAIL_SUBJECT_BENEFICIARIO) : null : null;
				String emailBeneficiario =  daoParameters.get(webService + SharedConstants.EMAIL_BENEFICIARIO) != null ? !daoParameters.get(webService + SharedConstants.EMAIL_BENEFICIARIO).isEmpty() ?  daoParameters.get(webService + SharedConstants.EMAIL_BENEFICIARIO) : null : null;
				if (emailBeneficiario != null) {
					prop.put(webService+SharedConstants.EMAIL_SUBJECT_BENEFICIARIO, emailSubjectBeneficiario);
					prop.put(webService+SharedConstants.EMAIL_BENEFICIARIO, emailBeneficiario);
				}
				
				String pushTitleBeneficiario = daoParameters.get(webService + SharedConstants.PUSH_TITLE_BENEFICIARIO) != null ? !daoParameters.get(webService + SharedConstants.PUSH_TITLE_BENEFICIARIO).isEmpty() ?  daoParameters.get(webService + SharedConstants.PUSH_TITLE_BENEFICIARIO) : null : null;
				String pushBeneficiario = daoParameters.get(webService + SharedConstants.PUSH_BENEFICIARIO) != null ? !daoParameters.get(webService + SharedConstants.PUSH_BENEFICIARIO).isEmpty() ?  daoParameters.get(webService + SharedConstants.PUSH_BENEFICIARIO) : null : null;
				
				if (pushBeneficiario != null) {
					prop.put(webService+SharedConstants.PUSH_TITLE_BENEFICIARIO, pushTitleBeneficiario);
					prop.put(webService+SharedConstants.PUSH_BENEFICIARIO, pushBeneficiario);
				}
				
				String mexTitleBeneficiario = daoParameters.get(webService + SharedConstants.MEX_TITLE_BENEFICIARIO) != null ? !daoParameters.get(webService + SharedConstants.MEX_TITLE_BENEFICIARIO).isEmpty() ?  daoParameters.get(webService + SharedConstants.MEX_TITLE_BENEFICIARIO) : null : null;
				String mexBeneficiario = daoParameters.get(webService + SharedConstants.MEX_BENEFICIARIO) != null ? !daoParameters.get(webService + SharedConstants.MEX_BENEFICIARIO).isEmpty() ?  daoParameters.get(webService + SharedConstants.MEX_BENEFICIARIO) : null : null;
				String mexCallToActionBeneficiario = daoParameters.get(webService + SharedConstants.MEX_CALL_TO_ACTION_BENEFICIARIO) != null ? !daoParameters.get(webService + SharedConstants.MEX_CALL_TO_ACTION_BENEFICIARIO).isEmpty() ? daoParameters.get(webService + SharedConstants.MEX_CALL_TO_ACTION_BENEFICIARIO) : null : null;  
				
				if (mexBeneficiario != null) {
					prop.put(webService+SharedConstants.MEX_TITLE_BENEFICIARIO, mexTitleBeneficiario);
					prop.put(webService+SharedConstants.MEX_BENEFICIARIO, mexBeneficiario);
					prop.put(webService+SharedConstants.MEX_CALL_TO_ACTION_BENEFICIARIO, mexCallToActionBeneficiario);
				}
				if (emailBeneficiario != null || pushBeneficiario  != null || mexBeneficiario != null) {				
					log.info("NotificatoreFacade::sendMessage: effettuo la chiamata al notificatore (1)");
					stampaPropertyNotificatore(prop);
					notificatoreUtil.setProp(prop);
					notificatoreUtil.callNotificatore(webService, cfDestinatario, BENEFICIARIO, replacements);
				}
			}
		
			mapReturn.put("value", "OK");
		} catch (Exception e) {
			e.printStackTrace();
			if(e instanceof IllegalStateException) throw e;
			log.log(Level.SEVERE, "NotificatoreFacade::sendMessage: Exception", e);
			mapReturn.put("value", e.getMessage());
		}
		return mapReturn;
	}

	private void stampaPropertyNotificatore(Properties prop) {
		for (Object key : prop.keySet()) {
			StringBuilder sb = new StringBuilder();
			sb.append(key);
			sb.append(": ");
			sb.append(prop.getProperty(key.toString()));
			sb.append("\n");
			log.info("Property notificatore: \n" + sb.toString());
		}
	}

	private Properties getPropCondivise(Map<String, String> daoParameters) {
		Properties prop = new Properties();
		String xAuthenticationString = daoParameters.get("NOTIFICATORE_TOKEN");
		String endpoint = daoParameters.get("NOTIFICATORE_ENDPOINT");
		String template = daoParameters.get(SharedConstants.NOTIFICATORE_TEMPLATE);
		prop.put("xAuthenticationString", xAuthenticationString);
		prop.put("endpoint", endpoint);
		prop.put(SharedConstants.NOTIFICATORE_TEMPLATE, template);
		return prop;
	}

	public NotificatoreUtil getNotificatoreUtil() {
		return notificatoreUtil;
	}

	public void setNotificatoreUtil(NotificatoreUtil notificatoreUtil) {
		this.notificatoreUtil = notificatoreUtil;
	}
}
