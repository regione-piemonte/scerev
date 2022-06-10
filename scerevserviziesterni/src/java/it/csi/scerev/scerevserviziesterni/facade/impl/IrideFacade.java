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
import javax.xml.bind.Marshaller;
import javax.xml.rpc.Stub;

import it.csi.scerev.scerevserviziesterni.facade.interfaces.IrideFacadeIf;
import it.csi.scerev.scerevserviziesterni.iride.base.Application;
import it.csi.scerev.scerevserviziesterni.iride.base.Identita;
import it.csi.scerev.scerevserviziesterni.iride.base.PolicyEnforcerBaseSoapBindingStub;
import it.csi.scerev.scerevserviziesterni.iride.base.Ruolo;

public class IrideFacade implements IrideFacadeIf {

	public Map<String, Object> findRuoli(Map<String, String> body, Map<String, String> conn) throws Exception {		
		String xmlRequest = "";
		String xmlResponse = "";
		StringWriter sw = new StringWriter();
		
		Map<String, Object> mapReturn = new HashMap<String, Object>();
		try {
			PolicyEnforcerBaseSoapBindingStub stub = new PolicyEnforcerBaseSoapBindingStub();
			stub._setProperty(Stub.ENDPOINT_ADDRESS_PROPERTY, conn.get("endpoint"));	
			System.out.println(conn.get("endpoint"));
			for(String s: body.keySet()) {
				System.out.println(s+" "+body.get(s));
			}
			
			Identita input = new Identita();
			input.setCodFiscale(body.get("codFiscale"));
			input.setCognome(body.get("cognome"));
			input.setIdProvider(body.get("idProvider"));
			input.setLivelloAutenticazione(Integer.parseInt(body.get("livelloAutenticazione")));
			input.setMac(body.get("mac"));
			input.setNome(body.get("nome"));
			input.setRappresentazioneInterna(body.get("rappresentazioneInterna"));
			input.setTimestamp(body.get("timestamp"));
			
			Application app = new Application();
			app.setId(body.get("id"));
			
			
			JAXBContext jaxbReq = JAXBContext.newInstance(Identita.class);
			Marshaller jaxbMarshallerReq = jaxbReq.createMarshaller();
			jaxbMarshallerReq.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshallerReq.marshal(input, sw);
			xmlRequest = returnString(sw);				
			mapReturn.put("request", xmlRequest);	
			
			Ruolo[] ruoli = stub.findRuoliForPersonaInApplication(input, app);
			
			if(ruoli != null && ruoli.length > 0) {
				JAXBContext jaxbRes = JAXBContext.newInstance(Ruolo.class);
				Marshaller jaxbMarshallerRes = jaxbRes.createMarshaller();
				jaxbMarshallerRes.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
				jaxbMarshallerRes.marshal(ruoli[0], sw);
				xmlResponse = returnString(sw);				
				mapReturn.put("response", xmlResponse);
			}
			
//			JAXBContext jaxbRes = JAXBContext.newInstance(Ruolo.class);
//			Marshaller jaxbMarshallerRes = jaxbRes.createMarshaller();
//			jaxbMarshallerRes.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//			jaxbMarshallerRes.marshal(ruoli, sw);
//			xmlResponse = returnString(sw);				
//			mapReturn.put("response", xmlResponse);
			
			List<Ruolo> ruoliOut = new ArrayList<Ruolo>();
			
			for(Ruolo r : ruoli) {
				ruoliOut.add(r);
			}
			
			mapReturn.put("value", ruoliOut);
		
			
		} catch (Exception e) {
			e.printStackTrace();			 
//			ArrayList<Ruolo> ruoli = new ArrayList<Ruolo>();
//			Ruolo r = new Ruolo();
//			r.setCodiceRuolo("OPERATORE_SUPERUSER");
//			r.setMnemonico("OPERATORE_SUPERUSER@SANITA");
//			ruoli.add(r);
//			mapReturn.put("value", ruoli);
		}		
		return mapReturn;
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
