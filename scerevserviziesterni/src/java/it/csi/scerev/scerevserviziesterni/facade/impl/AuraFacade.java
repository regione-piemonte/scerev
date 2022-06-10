/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevserviziesterni.facade.impl;

import java.io.StringWriter;
import java.math.BigDecimal;
import java.net.SocketTimeoutException;
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

import it.csi.scerev.scerevserviziesterni.aura.find.AnagrafeFind;
import it.csi.scerev.scerevserviziesterni.aura.find.AnagrafeFindSoap;
import it.csi.scerev.scerevserviziesterni.aura.find.DatiAnagrafici;
import it.csi.scerev.scerevserviziesterni.aura.find.DatiAnagraficiMsg;
import it.csi.scerev.scerevserviziesterni.aura.find.FindProfiliAnagraficiRequest;
import it.csi.scerev.scerevserviziesterni.aura.find.WSSecurityHeaderSOAPHandler;
import it.csi.scerev.scerevserviziesterni.aura.get.AnagrafeSanitariaNew;
import it.csi.scerev.scerevserviziesterni.aura.get.AnagrafeSanitariaNewSoap;
import it.csi.scerev.scerevserviziesterni.aura.get.SoggettoAuraBodyNew;
import it.csi.scerev.scerevserviziesterni.aura.get.SoggettoAuraNewMsg;
import it.csi.scerev.scerevserviziesterni.facade.interfaces.AuraFacadeIf;
import it.csi.scerev.scerevutil.business.SharedConstants;

public class AuraFacade implements AuraFacadeIf {

	/***
	 * Ricerca un cittadino su Aura.
	 * 
	 * @param codiceFiscale
	 * @param cognome
	 * @param nome
	 * @param dataNascita
	 * @param conn:         Map contenente endpoint, username, password
	 * @return map contenente value(DatiAnagrafici), log request(String), log
	 *         response(String)
	 */
	public Map<String, Object> findCittadino(String codiceFiscale, String cognome, String nome, String dataNascita,
			Map<String, String> conn) throws Exception {

		String xmlRequest = "";
		String xmlResponse = "";

		Map<String, Object> mapReturn = new HashMap<String, Object>();
		try {
			AnagrafeFind service = new AnagrafeFind(conn.get("endpoint"));
			AnagrafeFindSoap port = service.getAnagrafeFindSoap();
			setWSSecurity((BindingProvider) port, conn);

			FindProfiliAnagraficiRequest req = new FindProfiliAnagraficiRequest();
			req.setCodiceFiscale(codiceFiscale);
			req.setCognome(cognome);
			req.setNome(nome);
			req.setDataNascita(dataNascita);
			req.setFlagDecesso("0");
			req.setIdEnte("SCEREV");
			DatiAnagraficiMsg dati = null;

			StringWriter sw = new StringWriter();

			JAXBContext jaxbReq = JAXBContext.newInstance(FindProfiliAnagraficiRequest.class);
			Marshaller jaxbMarshallerReq = jaxbReq.createMarshaller();
			jaxbMarshallerReq.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshallerReq.marshal(req, sw);
			xmlRequest = returnString(sw);
			mapReturn.put("request", xmlRequest);

			dati = port.findProfiliAnagrafici(req);
			if(dati != null && (dati.getHeader() != null || dati.getBody() != null || dati.getFooter() != null)) {
				JAXBContext jaxbRes = JAXBContext.newInstance(DatiAnagraficiMsg.class);
				Marshaller jaxbMarshallerRes = jaxbRes.createMarshaller();
				jaxbMarshallerRes.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
				jaxbMarshallerRes.marshal(dati, sw);
				xmlResponse = returnString(sw);
				mapReturn.put("response", xmlResponse);
				
				List<DatiAnagrafici> listaOut = new ArrayList<DatiAnagrafici>();
				if(dati.getBody() != null && dati.getBody().getElencoProfili() != null && dati.getBody().getElencoProfili().getDatianagrafici() != null)
					listaOut = dati.getBody().getElencoProfili().getDatianagrafici();				
				mapReturn.put("value", listaOut);

			} else throw new Exception("Errore durante la chiamata a AnagrafeFind");

		} catch(WebServiceException e) {
			throw new Exception("Errore durante la chiamata a AnagrafeFind: " + e.getMessage());
		} catch (JAXBException e) {
			throw new Exception("Errore durante la conversione OBJ->XML: " + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			mapReturn.put("value", null);
			mapReturn.put("response", e.getMessage());
		}
		return mapReturn;
	}

	/***
	 * Ricerca approfondita di un cittadino su Aura (AnagrafeSanitariaNew).
	 * 
	 * @param idAura	 
	 * @param conn:         Map contenente endpoint, username, password
	 * @return map contenente value(SoggettoAuraBodyNew), log request(String), log
	 *         response(String)
	 */
	public Map<String, Object> getCittadino(BigDecimal idAura, Map<String, String> conn) throws Exception {
		String xmlResponse = "";

		Map<String, Object> mapReturn = new HashMap<String, Object>();
		try {
			AnagrafeSanitariaNew service = new AnagrafeSanitariaNew(conn.get("endpoint"));
			AnagrafeSanitariaNewSoap port = service.getAnagrafeSanitariaNewSoap();
			setWSSecurity((BindingProvider) port, conn);

			SoggettoAuraNewMsg dati = null;

			StringWriter sw = new StringWriter();
			mapReturn.put("request", idAura.toString());

			dati = port.getProfiloSanitarioNew(idAura.toString());
			if(dati != null && (dati.getHeader() != null || dati.getBody() != null || dati.getFooter() != null)) {
				JAXBContext jaxbRes = JAXBContext.newInstance(SoggettoAuraNewMsg.class);
				Marshaller jaxbMarshallerRes = jaxbRes.createMarshaller();
				jaxbMarshallerRes.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
				jaxbMarshallerRes.marshal(dati, sw);
				xmlResponse = returnString(sw);
				mapReturn.put("response", xmlResponse);
				SoggettoAuraBodyNew listaOut = dati.getBody();
				if(listaOut == null) mapReturn.put("value", SharedConstants.ERRORE_AURA);
				else mapReturn.put("value", listaOut);
			} else throw new Exception("Errore durante la chiamata a AnagrafeSanitaria");
		} catch(WebServiceException e) {
			throw new Exception("Errore durante la chiamata a AnagrafeSanitaria: " + e.getMessage());
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

		if (conn.get("username") != null && conn.get("password") != null) {
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
