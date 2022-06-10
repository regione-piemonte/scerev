/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevlibbatch.facade;

import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.Handler;
import javax.xml.ws.soap.SOAPFaultException;

import it.csi.scerev.scerevlibbatch.exception.OpessanNotFoundException;
import it.csi.scerev.scerevlibbatch.opessan.def.opessanws.opessan.GetMassimaliMedico;
import it.csi.scerev.scerevlibbatch.opessan.def.opessanws.opessan.GetMassimaliMedicoResponse.Return;
import it.csi.scerev.scerevlibbatch.opessan.def.opessanws.opessan.MassimaliMediciReqBody;
import it.csi.scerev.scerevlibbatch.opessan.def.opessanws.opessan.MassimaliMediciRequest;
import it.csi.scerev.scerevlibbatch.opessan.dto.opessanws.opessan.common.request.Header;
import it.csi.scerev.scerevlibbatch.opessan.services.opessanws.opessan.MassimaliMedici;
import it.csi.scerev.scerevlibbatch.opessan.services.opessanws.opessan.MassimaliMedici_Service;
import it.csi.scerev.scerevlibbatch.opessan.services.opessanws.opessan.massimaliMedici.WSSecurityHeaderSOAPHandler;



public class OpessanFacade {

	/***
	 * getDettaglioOperatore su Opessan
	 * 
	 * @param Map<String, String> bodyMap con parametri "azienda" e "idAura"
	 * @param Map<String, String> headerMap con parametri "idAsr", "receiver" e "sender"
	 * @param Map contenente endpoint, username, password
	 * @return map contenente value(SoggettoOpessanBody), log request(String), log response(String)
	 */
	public Map<String, Object> getMassimaliMedici(Map<String, String> bodyMap, Map<String, String> headerMap, Map<String, String> conn)
			throws Exception {		
		String xmlRequest = "";
		String xmlResponse = "";
		Map<String, Object> mapReturn = new HashMap<String, Object>();
		StringWriter sw = new StringWriter();
		try {
			MassimaliMedici_Service service = new MassimaliMedici_Service();
			MassimaliMedici port = service.getMassimaliMediciCXFImplPort();
			setWSSecurity((BindingProvider) port, conn);			
			
			GetMassimaliMedico.Arg0 input = new GetMassimaliMedico.Arg0();
			Header header = new Header();
			MassimaliMediciReqBody body = new MassimaliMediciReqBody();
			
			body.setIdAura(new BigDecimal(bodyMap.get("idAura")));			
			input.setBody(body);
						
			header.setSender(headerMap.get("sender"));
			input.setHeader(header);			
			
			JAXBContext jaxbReq = JAXBContext.newInstance(GetMassimaliMedico.Arg0.class);
			Marshaller jaxbMarshallerReq = jaxbReq.createMarshaller();
			jaxbMarshallerReq.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshallerReq.marshal(input, sw);
			xmlRequest = returnString(sw);			
			mapReturn.put("request", xmlRequest);	

			Return response = port.getMassimaliMedico(input);

			JAXBContext jaxbRes = JAXBContext.newInstance(Return.class);
			Marshaller jaxbMarshallerRes = jaxbRes.createMarshaller();
			jaxbMarshallerRes.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshallerRes.marshal(response, sw);
			xmlResponse = returnString(sw);	
			mapReturn.put("response", xmlResponse);
			
			mapReturn.put("value", response.getBody());	
			
		} catch (JAXBException e) {
			throw new Exception("Errore durante la conversione OBJ->XML: " + e.getMessage());
		} catch (SOAPFaultException e) {
			// FIX LOCALE PER DISALLINEAMENTO NODI OPESSAN DA ELIMINARE IN AMBIENTI CSI
			return getMassimaliMedici(bodyMap, headerMap, conn);			
		} catch (Exception e) {
			throw new OpessanNotFoundException();
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
