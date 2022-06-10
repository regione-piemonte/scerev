/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevserviziesterni.facade.impl;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.handler.Handler;

import it.csi.scerev.scerevserviziesterni.aura.find.WSSecurityHeaderSOAPHandler;
import it.csi.scerev.scerevserviziesterni.ensemble.interrogamefscerev.InterrogaMefScerevResult;
import it.csi.scerev.scerevserviziesterni.ensemble.interrogamefscerev.InterrogaMefScerevSoap;
import it.csi.scerev.scerevserviziesterni.ensemble.interrogamefscerev.InterrogaMefScerev_Service;
import it.csi.scerev.scerevserviziesterni.facade.interfaces.SistemaTSFacadeIf;

public class SistemaTSFacade implements SistemaTSFacadeIf {

	/***
	 * Ricerca un cittadino su Sistema TS.
	 * 
	 * @param codiceFiscale
	 * @param conn:         Map contenente endpoint, username, password
	 * @return map contenente value(DatiAnagrafici), log request(String), log
	 *         response(String)
	 */
	public Map<String, Object> findCittadino(String codiceFiscale, Map<String, String> conn) throws Exception {

		String xmlRequest = "Chiamata a ws InterrogaMefScerev per CF: "+codiceFiscale;
		String xmlResponse = "";

		Map<String, Object> mapReturn = new HashMap<String, Object>();
		try {
			InterrogaMefScerev_Service stub = new InterrogaMefScerev_Service(conn.get("endpoint"));
			InterrogaMefScerevSoap port = stub.getInterrogaMefScerevSoap();					
			setWSSecurity((BindingProvider) port, conn);
			
			InterrogaMefScerevResult response = port.interrogaMefScerev(codiceFiscale);
			
			if(!response.getCodiceEsito().equals("0")) throw new Exception("CF non trovato");
			
			StringWriter sw = new StringWriter();			
			JAXBContext jaxbRes = JAXBContext.newInstance(InterrogaMefScerevResult.class);
			Marshaller jaxbMarshallerRes = jaxbRes.createMarshaller();
			jaxbMarshallerRes.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshallerRes.marshal(response, sw);
			xmlResponse = returnString(sw);
			
			mapReturn.put("response", xmlResponse);
			mapReturn.put("value", response);			
		} catch(WebServiceException e) {
			throw new Exception("Errore durante la chiamata a InterrogaMefScerev: " + e.getMessage());
		} catch (JAXBException e) {
			throw new Exception("Errore durante la conversione OBJ->XML: " + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			mapReturn.put("value", null);
			mapReturn.put("response", e.getMessage());
		}
		return mapReturn;
	}

	private void setWSSecurity(BindingProvider prov, Map<String, String> conn) {
		prov.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, conn.get("endpoint"));
		
		if(conn.get("username")!=null && conn.get("password")!=null) {
			List<Handler> handlerChain = new ArrayList<Handler>();
			handlerChain.add(new WSSecurityHeaderSOAPHandler(conn.get("username"), conn.get("password")));
			prov.getBinding().setHandlerChain(handlerChain);
		}
	}
	
	private String returnString(StringWriter sw) {
		try {
			String str = sw.toString();
			return str;
		} catch (Exception e) {
		}
		return "";
	}

}
