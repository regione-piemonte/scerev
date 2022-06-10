/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevserviziesterni.facade.impl;

import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.Handler;

import it.csi.scerev.scerevserviziesterni.aura.getUltimoMovimentoDiRevoca.GetUltimoMovimentoDiRevoca;
import it.csi.scerev.scerevserviziesterni.aura.getUltimoMovimentoDiRevoca.GetUltimoMovimentoDiRevocaSoap;
import it.csi.scerev.scerevserviziesterni.aura.getUltimoMovimentoDiRevoca.GetUltimoMovimentoDiRevoca_Service;
import it.csi.scerev.scerevserviziesterni.aura.getUltimoMovimentoDiRevoca.Request;
import it.csi.scerev.scerevserviziesterni.aura.getUltimoMovimentoDiRevoca.Response;
import it.csi.scerev.scerevserviziesterni.aura.getambito.GetAmbitoResp3;
import it.csi.scerev.scerevserviziesterni.aura.getambito.GetAmbitoSoap;
import it.csi.scerev.scerevserviziesterni.aura.getambito.GetAmbito_Service;
import it.csi.scerev.scerevserviziesterni.aura.getasldistrettoambito.ASLDistrettoAmbitoResponse;
import it.csi.scerev.scerevserviziesterni.aura.getasldistrettoambito.GetASLDistrettoAmbitoSoap;
import it.csi.scerev.scerevserviziesterni.aura.getasldistrettoambito.GetASLDistrettoAmbito_Service;
import it.csi.scerev.scerevserviziesterni.aura.getdistretto.GetDistrettoResult;
import it.csi.scerev.scerevserviziesterni.aura.getdistretto.GetDistrettoSoap;
import it.csi.scerev.scerevserviziesterni.aura.getdistretto.GetDistretto_Service;
import it.csi.scerev.scerevserviziesterni.aura.getnumeroassistitiincaricomedico.GetNumeroAssistitiInCaricoMedicoSoap;
import it.csi.scerev.scerevserviziesterni.aura.getnumeroassistitiincaricomedico.GetNumeroAssistitiInCaricoMedico_Service;
import it.csi.scerev.scerevserviziesterni.aura.getnumeroassistitiincaricomedico.NumeroAssistitiInCaricoMedicoResponse;
import it.csi.scerev.scerevserviziesterni.aura.setaggiornamentoprofiloanagrafico.AggiornamentoProfiloAnagrafico;
import it.csi.scerev.scerevserviziesterni.aura.setaggiornamentoprofiloanagrafico.AggiornamentoProfiloAnagraficoRes;
import it.csi.scerev.scerevserviziesterni.aura.setaggiornamentoprofiloanagrafico.SetAggiornamentoProfiloAnagraficoSoap;
import it.csi.scerev.scerevserviziesterni.aura.setaggiornamentoprofiloanagrafico.SetAggiornamentoProfiloAnagrafico_Service;
import it.csi.scerev.scerevserviziesterni.aura.setinserimentoprofiloanagrafico.InserimentoProfiloAnagrafico;
import it.csi.scerev.scerevserviziesterni.aura.setinserimentoprofiloanagrafico.InserimentoProfiloAnagraficoRes;
import it.csi.scerev.scerevserviziesterni.aura.setinserimentoprofiloanagrafico.SetInserimentoProfiloAnagraficoSoap;
import it.csi.scerev.scerevserviziesterni.aura.setinserimentoprofiloanagrafico.SetInserimentoProfiloAnagrafico_Service;
import it.csi.scerev.scerevserviziesterni.aura.setprofilosanitario.ProfiloSanitario;
import it.csi.scerev.scerevserviziesterni.aura.setprofilosanitario.ProfiloSanitarioRes;
import it.csi.scerev.scerevserviziesterni.aura.setprofilosanitario.SetProfiloSanitarioSoap;
import it.csi.scerev.scerevserviziesterni.aura.setprofilosanitario.SetProfiloSanitario_Service;

public class EnsembleFacade {
	
	/***
	 * 
	 * @param idAuraMedico	 
	 * @param conn:         Map contenente endpoint, username, password
	 * @return map contenente value(SoggettoAuraBodyNew), log request(String), log
	 *         response(String)
	 */
	public Map<String, Object> getNumeroAssistitiInCaricoMedico(BigDecimal idAuraMedico, HashMap<String, String> conn)
			throws Exception {
		Map<String, Object> mapReturn = new HashMap<String, Object>();
		
		String xmlResponse = "";
		StringWriter sw = new StringWriter();
		try {

			GetNumeroAssistitiInCaricoMedico_Service service = new GetNumeroAssistitiInCaricoMedico_Service(conn.get("endpoint"));
			GetNumeroAssistitiInCaricoMedicoSoap port = service.getGetNumeroAssistitiInCaricoMedicoSoap();
			setWSSecurity((BindingProvider) port, conn);
			
			NumeroAssistitiInCaricoMedicoResponse response = port.getNumeroAssistitiInCaricoMedico(idAuraMedico.toString());
			mapReturn.put("value", response);
			
			JAXBContext jaxbRes = JAXBContext.newInstance(NumeroAssistitiInCaricoMedicoResponse.class);
			Marshaller jaxbMarshallerRes = jaxbRes.createMarshaller();
			jaxbMarshallerRes.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshallerRes.marshal(response, sw);
			xmlResponse = returnString(sw);
			mapReturn.put("response", xmlResponse);
			mapReturn.put("request", idAuraMedico.toString());

		} catch (Exception e) {
			e.printStackTrace();
			mapReturn.put("value", null);
			mapReturn.put("response", e.getMessage());
		}
		return mapReturn;
	}

	public Map<String, Object> getAslDistrettoAmbito(String comune, String tipologiaMedico, HashMap<String, String> conn)
			throws Exception {
		
		Map<String, Object> mapReturn = new HashMap<String, Object>();
		String xmlResponse = "";
		StringWriter sw = new StringWriter();
		try {
			
			GetASLDistrettoAmbito_Service service = new GetASLDistrettoAmbito_Service(conn.get("endpoint"));
			GetASLDistrettoAmbitoSoap port = service.getGetASLDistrettoAmbitoSoap();				
			setWSSecurity((BindingProvider) port, conn);
			
			ASLDistrettoAmbitoResponse response = port.getASLDistrettoAmbito(comune, tipologiaMedico);			
			mapReturn.put("value", response);
			
			JAXBContext jaxbRes = JAXBContext.newInstance(ASLDistrettoAmbitoResponse.class);
			Marshaller jaxbMarshallerRes = jaxbRes.createMarshaller();
			jaxbMarshallerRes.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshallerRes.marshal(response, sw);
			xmlResponse = returnString(sw);
			mapReturn.put("request", comune);
			mapReturn.put("response", xmlResponse);			
			
		} catch (Exception e) {
			e.printStackTrace();
			mapReturn.put("value", null);
			mapReturn.put("response", e.getMessage());
		}
		return mapReturn;
	}
	
	public Map<String, Object> setInserimentoProfiloAnagrafico(InserimentoProfiloAnagrafico request, HashMap<String, String> conn)
			throws Exception {
		
		Map<String, Object> mapReturn = new HashMap<String, Object>();
		String xmlRequest = "";
		String xmlResponse = "";
		StringWriter sw = new StringWriter();

		try {
			
			SetInserimentoProfiloAnagrafico_Service service = new SetInserimentoProfiloAnagrafico_Service(conn.get("endpoint"));
			SetInserimentoProfiloAnagraficoSoap port = service.getSetInserimentoProfiloAnagraficoSoap();
			setWSSecurity((BindingProvider) port, conn);	
			
			JAXBContext jaxbRes = JAXBContext.newInstance(InserimentoProfiloAnagrafico.class);
			Marshaller jaxbMarshallerRes = jaxbRes.createMarshaller();
			jaxbMarshallerRes.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshallerRes.marshal(request, sw);
			xmlRequest = returnString(sw);
			mapReturn.put("request", xmlRequest);
			
			InserimentoProfiloAnagraficoRes response = port.setInserimentoProfiloAnagrafico(request);	
			
			jaxbRes = JAXBContext.newInstance(InserimentoProfiloAnagraficoRes.class);
			jaxbMarshallerRes = jaxbRes.createMarshaller();
			jaxbMarshallerRes.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshallerRes.marshal(response, sw);
			xmlResponse = returnString(sw);
			mapReturn.put("response", xmlResponse);
			
			mapReturn.put("value", response);
			
		} catch (Exception e) {
			e.printStackTrace();
			mapReturn.put("value", null);
			mapReturn.put("response", e.getMessage());
		}
		return mapReturn;
		
	}
		
	public Map<String, Object> setAggiornamentoProfiloAnagrafico(AggiornamentoProfiloAnagrafico request, HashMap<String, String> conn) {
		
		Map<String, Object> mapReturn = new HashMap<String, Object>();
		String xmlRequest = "";
		String xmlResponse = "";
		StringWriter sw = new StringWriter();
		AggiornamentoProfiloAnagraficoRes response = null;
		try {
			
			SetAggiornamentoProfiloAnagrafico_Service service = new SetAggiornamentoProfiloAnagrafico_Service(conn.get("endpoint"));
			SetAggiornamentoProfiloAnagraficoSoap port = service.getSetAggiornamentoProfiloAnagraficoSoap();
			setWSSecurity((BindingProvider) port, conn);				
					
			JAXBContext jaxbRes = JAXBContext.newInstance(AggiornamentoProfiloAnagrafico.class);
			Marshaller jaxbMarshallerRes = jaxbRes.createMarshaller();
			jaxbMarshallerRes.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshallerRes.marshal(request, sw);
			xmlRequest = returnString(sw);
			mapReturn.put("request", xmlRequest);

			response = port.setAggiornamentoProfiloAnagrafico(request);
			
			jaxbRes = JAXBContext.newInstance(AggiornamentoProfiloAnagraficoRes.class);
			jaxbMarshallerRes = jaxbRes.createMarshaller();
			jaxbMarshallerRes.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshallerRes.marshal(response, sw);
			xmlResponse = returnString(sw);
			mapReturn.put("response", xmlResponse);
			
			mapReturn.put("value", response);
			
		} catch (Exception e) {
			e.printStackTrace();
			mapReturn.put("value", response);
			mapReturn.put("response", e.getMessage());
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
	
	public Map<String, Object> getAmbito(HashMap<String, String> conn)
			throws Exception {
		
		Map<String, Object> mapReturn = new HashMap<String, Object>();
		String xmlResponse = "";
		StringWriter sw = new StringWriter();
		try {			
			GetAmbito_Service service = new GetAmbito_Service(conn.get("endpoint"));
			GetAmbitoSoap port = service.getGetAmbitoSoap();
			setWSSecurity((BindingProvider) port, conn);	
			
			GetAmbitoResp3 resp = port.getAmbito();
			
			JAXBContext jaxbRes = JAXBContext.newInstance(GetAmbitoResp3.class);
			Marshaller jaxbMarshallerRes = jaxbRes.createMarshaller();
			jaxbMarshallerRes.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshallerRes.marshal(resp, sw);
			xmlResponse = returnString(sw);
			mapReturn.put("response", xmlResponse);
			
			mapReturn.put("value", resp.getAmbiti());
			
		} catch (Exception e) {
			e.printStackTrace();
			mapReturn.put("value", null);
			mapReturn.put("response", e.getMessage());
		}
		return mapReturn;
	}
	
	public Map<String, Object> getDistretto(HashMap<String, String> conn)
			throws Exception {
		
		Map<String, Object> mapReturn = new HashMap<String, Object>();
		String xmlResponse = "";
		StringWriter sw = new StringWriter();
		try {
			GetDistretto_Service service = new GetDistretto_Service(conn.get("endpoint"));
			GetDistrettoSoap port = service.getGetDistrettoSoap();
			setWSSecurity((BindingProvider) port, conn);	
			
			GetDistrettoResult resp = port.getDistretto();
			
			JAXBContext jaxbRes = JAXBContext.newInstance(GetDistrettoResult.class);
			Marshaller jaxbMarshallerRes = jaxbRes.createMarshaller();
			jaxbMarshallerRes.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshallerRes.marshal(resp, sw);
			xmlResponse = returnString(sw);
			mapReturn.put("response", xmlResponse);

			
			mapReturn.put("value", resp.getDistretti());
			
		} catch (Exception e) {
			e.printStackTrace();
			mapReturn.put("value", null);
			mapReturn.put("response", e.getMessage());
		}
		return mapReturn;
	}
		
	private void setWSSecurity(BindingProvider prov, Map<String, String> conn) {
		prov.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, conn.get("endpoint"));

		// end tom
		List<Handler> handlerChain = new ArrayList<Handler>();
		handlerChain.add(new it.csi.scerev.scerevserviziesterni.aura.find.WSSecurityHeaderSOAPHandler(conn.get("username"), conn.get("password")));
		prov.getBinding().setHandlerChain(handlerChain);
		
			
	}
			
	public Map<String, Object> setProfiloSanitario(ProfiloSanitario request, HashMap<String, String> conn)
			throws Exception {
		
		Map<String, Object> mapReturn = new HashMap<String, Object>();
		String xmlRequest = "";
		String xmlResponse = "";
		StringWriter sw = new StringWriter();
		try {
			
			SetProfiloSanitario_Service service = new SetProfiloSanitario_Service(conn.get("endpoint"));
			SetProfiloSanitarioSoap port = service.getSetProfiloSanitarioSoap();
			setWSSecurity((BindingProvider) port, conn);	
			
			JAXBContext jaxbRes = JAXBContext.newInstance(ProfiloSanitario.class);
			Marshaller jaxbMarshallerRes = jaxbRes.createMarshaller();
			jaxbMarshallerRes.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshallerRes.marshal(request, sw);
			xmlRequest = returnString(sw);
			mapReturn.put("request", xmlRequest);
			
			ProfiloSanitarioRes response = port.setProfiloSanitario(request);
			
			jaxbRes = JAXBContext.newInstance(ProfiloSanitarioRes.class);
			jaxbMarshallerRes = jaxbRes.createMarshaller();
			jaxbMarshallerRes.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshallerRes.marshal(response, sw);
			xmlResponse = returnString(sw);
			mapReturn.put("response", xmlResponse);

			
			mapReturn.put("value", response);
			mapReturn.put("returnCode", response.getCodiceRitorno());
			mapReturn.put("footer", response.getFooter());
			
		} catch (Exception e) {
			e.printStackTrace();
			mapReturn.put("value", null);
			mapReturn.put("response", e.getMessage());
		}
		return mapReturn;
		
	}
	
	public Map<String, Object> getUltimoMovimentoDiRevoca(Request request, HashMap<String, String> conn)
			throws Exception {
		
		Map<String, Object> mapReturn = new HashMap<String, Object>();
		String xmlRequest = "";
		String xmlResponse = "";
		StringWriter sw = new StringWriter();
		try {
			
			GetUltimoMovimentoDiRevoca_Service service = new GetUltimoMovimentoDiRevoca_Service(conn.get("endpoint"));
			GetUltimoMovimentoDiRevocaSoap port = service.getGetUltimoMovimentoDiRevocaSoap();
			setWSSecurity((BindingProvider) port, conn);	
			
			JAXBContext jaxbRes = JAXBContext.newInstance(Request.class);
			Marshaller jaxbMarshallerRes = jaxbRes.createMarshaller();
			jaxbMarshallerRes.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshallerRes.marshal(request, sw);
			xmlRequest = returnString(sw);
			mapReturn.put("request", xmlRequest);
			
			Response response = port.getUltimoMovimentoDiRevoca(request);
			
			sw = new StringWriter();
			jaxbRes = JAXBContext.newInstance(Response.class);
			jaxbMarshallerRes = jaxbRes.createMarshaller();
			jaxbMarshallerRes.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshallerRes.marshal(response, sw);
			xmlResponse = returnString(sw);
			mapReturn.put("response", xmlResponse);

			
			mapReturn.put("value", response);
			mapReturn.put("returnCode", response.getCodiceEsito());
			
		} catch (Exception e) {
			e.printStackTrace();
			mapReturn.put("value", null);
			mapReturn.put("response", e.getMessage());
		}
		return mapReturn;
		
	}
}
