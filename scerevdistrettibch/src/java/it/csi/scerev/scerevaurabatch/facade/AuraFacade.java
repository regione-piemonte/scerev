/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevaurabatch.facade;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.Handler;

import it.csi.scerev.scerevaurabatch.aura.getambito.GetAmbito;
import it.csi.scerev.scerevaurabatch.aura.getambito.GetAmbitoResp3;
import it.csi.scerev.scerevaurabatch.aura.getambito.GetAmbitoSoap;
import it.csi.scerev.scerevaurabatch.aura.getambito.GetAmbito_Service;
import it.csi.scerev.scerevaurabatch.aura.getdistretto.GetDistretto;
import it.csi.scerev.scerevaurabatch.aura.getdistretto.GetDistrettoResult;
import it.csi.scerev.scerevaurabatch.aura.getdistretto.GetDistrettoSoap;
import it.csi.scerev.scerevaurabatch.aura.getdistretto.GetDistretto_Service;
import it.csi.scerev.scerevaurabatch.util.WSSecurityHeaderSOAPHandler;

public class AuraFacade {
	
	
	public Map<String, Object> getAmbito(HashMap<String, String> conn) throws Exception {
		
		Map<String, Object> mapReturn = new HashMap<String, Object>();
		String xmlRequest = "";
		String xmlResponse = "";
		StringWriter sw = new StringWriter();
		try {			
			GetAmbito_Service service = new GetAmbito_Service(conn.get("endpoint"));
			GetAmbitoSoap port = service.getGetAmbitoSoap();
			setWSSecurity((BindingProvider) port, conn);	
			
			JAXBContext jaxbReq = JAXBContext.newInstance(GetAmbito.class);
			Marshaller jaxbMarshallerReq = jaxbReq.createMarshaller();
			jaxbMarshallerReq.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshallerReq.marshal(new GetAmbito(), sw);
			xmlRequest = returnString(sw);
			mapReturn.put("request", xmlRequest);
			
			GetAmbitoResp3 resp = port.getAmbito();
			
			JAXBContext jaxbRes = JAXBContext.newInstance(GetAmbitoResp3.class);
			Marshaller jaxbMarshallerRes = jaxbRes.createMarshaller();
			jaxbMarshallerRes.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshallerRes.marshal(resp, sw);
			xmlResponse = returnString(sw);
			mapReturn.put("response", xmlResponse);
			
			mapReturn.put("value", resp.getAmbiti());
			mapReturn.put("esito", resp.getCodiceEsito());
		} catch (Exception e) {
			e.printStackTrace();
			mapReturn.put("value", null);
			mapReturn.put("response", null);
			mapReturn.put("error", e.getMessage());
			mapReturn.put("esito", "0");
		}
		return mapReturn;
	}
	
	public Map<String, Object> getDistretto(HashMap<String, String> conn) throws Exception {
		
		Map<String, Object> mapReturn = new HashMap<String, Object>();
		String xmlRequest = "";
		String xmlResponse = "";
		StringWriter sw = new StringWriter();
		try {
			GetDistretto_Service service = new GetDistretto_Service(conn.get("endpoint"));
			GetDistrettoSoap port = service.getGetDistrettoSoap();
			setWSSecurity((BindingProvider) port, conn);	
			
			JAXBContext jaxbReq = JAXBContext.newInstance(GetDistretto.class);
			Marshaller jaxbMarshallerReq = jaxbReq.createMarshaller();
			jaxbMarshallerReq.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshallerReq.marshal(new GetDistretto(), sw);
			xmlRequest = returnString(sw);
			mapReturn.put("request", xmlRequest);
			
			GetDistrettoResult resp = port.getDistretto();
			
			JAXBContext jaxbRes = JAXBContext.newInstance(GetDistrettoResult.class);
			Marshaller jaxbMarshallerRes = jaxbRes.createMarshaller();
			jaxbMarshallerRes.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshallerRes.marshal(resp, sw);
			xmlResponse = returnString(sw);
			mapReturn.put("response", xmlResponse);

			
			mapReturn.put("value", resp.getDistretti());
			mapReturn.put("esito", resp.getCodiceEsito());
		} catch (Exception e) {
			e.printStackTrace();
			mapReturn.put("value", null);
			mapReturn.put("response", null);
			mapReturn.put("error", e.getMessage());
			mapReturn.put("esito", "0");
		}
		return mapReturn;
	}
		
	private void setWSSecurity(BindingProvider prov, Map<String, String> conn) {
		prov.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, conn.get("endpoint"));

		// end tom
		List<Handler> handlerChain = new ArrayList<Handler>();
		handlerChain.add(new WSSecurityHeaderSOAPHandler(conn.get("username"), conn.get("password")));
		prov.getBinding().setHandlerChain(handlerChain);
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

