/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevaurabatch.serviziesterni;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import it.csi.scerev.scerevaurabatch.aura.getambito.AmbitoDettaglio2;
import it.csi.scerev.scerevaurabatch.aura.getambito.ArrayOfAmbitoDettaglio2;
import it.csi.scerev.scerevaurabatch.aura.getdistretto.ArrayOfDistrettoDettaglio;
import it.csi.scerev.scerevaurabatch.aura.getdistretto.DistrettoDettaglio;
import it.csi.scerev.scerevaurabatch.dao.DataDAO;
import it.csi.scerev.scerevaurabatch.dto.ConnectionInfo;
import it.csi.scerev.scerevaurabatch.dto.ScerevDAmbitoDTO;
import it.csi.scerev.scerevaurabatch.dto.ScerevDDistrettoDTO;
import it.csi.scerev.scerevaurabatch.exception.AuraNotFoundException;
import it.csi.scerev.scerevaurabatch.facade.AuraFacade;
import it.csi.scerev.scerevaurabatch.logger.BatchLoggerFactory;
import it.csi.scerev.scerevaurabatch.util.Converter;
import it.csi.scerev.scerevaurabatch.util.SharedConstants;


public class ServiziEsterniClient {
	
	private String auraGetAmbitoEndpoint;
	private String auraGetAmbitoUsername;
	private String auraGetAmbitoPassword;

	private String auraGetDistrettoEndpoint;
	private String auraGetDistrettoUsername;
	private String auraGetDistrettoPassword;

	private static ServiziEsterniClient instance;
	
//	private final static org.apache.log4j.Logger log = BatchLoggerFactory.getLogger(ServiziEsterniClient.class);

	private ServiziEsterniClient() {
	}
	
	public static ServiziEsterniClient getInstance(DataDAO dao) {
		if (instance == null) {
			instance = new ServiziEsterniClient();
			try {			
				instance.setAuraGetAmbitoEndpoint(dao.getParametro(SharedConstants.AURA_GETAMBITO_ENDPOINT));
				instance.setAuraGetAmbitoUsername(dao.getParametro(SharedConstants.AURA_GETAMBITO_USERNAME));
				instance.setAuraGetAmbitoPassword(dao.getParametro(SharedConstants.AURA_GETAMBITO_PASSWORD));
				
				instance.setAuraGetDistrettoEndpoint(dao.getParametro(SharedConstants.AURA_GETDISTRETTO_ENDPOINT));
				instance.setAuraGetDistrettoUsername(dao.getParametro(SharedConstants.AURA_GETDISTRETTO_USERNAME));
				instance.setAuraGetDistrettoPassword(dao.getParametro(SharedConstants.AURA_GETDISTRETTO_PASSWORD));						
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return instance;
	}

	
	public List<ScerevDDistrettoDTO> getDistretto(DataDAO dao) throws Exception {
		try {
			AuraFacade facade = new AuraFacade();
			Map<String, Object> returnMap = facade.getDistretto(
					Converter.convertObjToMapString(new ConnectionInfo(
							this.auraGetDistrettoEndpoint, this.auraGetDistrettoUsername, this.auraGetDistrettoPassword)));
			String xmlRequest = (String) returnMap.get("request");
			String xmlResponse = (String) returnMap.get("response");
			String esito = (String) returnMap.get("esito");
//			log.info(xmlRequest);
//			log.info(xmlResponse);
			
			dao.insertLogAura("getDistretto", xmlRequest, xmlResponse, esito);

			ArrayOfDistrettoDettaglio value = (ArrayOfDistrettoDettaglio) returnMap.get("value");

			if (value == null)
				throw new AuraNotFoundException((String)returnMap.get("error"));
			
			List<DistrettoDettaglio> listaDistretti = value.getDistrettoDettaglio();
			List<ScerevDDistrettoDTO> listReturn = new ArrayList<ScerevDDistrettoDTO>();
			
			for (DistrettoDettaglio single : listaDistretti) {
				ScerevDDistrettoDTO distretto = new ScerevDDistrettoDTO((int) single.getIdDistretto(),
						single.getCodice(), single.getDescrizione(), dao.getPkAsl(String.valueOf(single.getCodiceAsl())),
						Converter.getData(single.getInizioValidita()), Converter.getData(single.getFineValidita()));
				
				listReturn.add(distretto);
			}
			
			return listReturn;			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public List<ScerevDAmbitoDTO> getAmbito(DataDAO dao) throws Exception {
		AuraFacade facade = new AuraFacade();
		Map<String, Object> returnMap = facade
				.getAmbito(Converter.convertObjToMapString(new ConnectionInfo(this.auraGetAmbitoEndpoint,
						this.auraGetAmbitoUsername, this.auraGetAmbitoPassword)));

		String xmlRequest = (String) returnMap.get("request");
		String xmlResponse = (String) returnMap.get("response");
		String esito = (String) returnMap.get("esito");

		ArrayOfAmbitoDettaglio2 value = (ArrayOfAmbitoDettaglio2) returnMap.get("value");
//			log.info(xmlRequest);
//			log.info(xmlResponse);

		dao.insertLogAura("getAmbito", xmlRequest, xmlResponse, esito);

		if (value == null)
			throw new AuraNotFoundException((String)returnMap.get("error"));

		List<AmbitoDettaglio2> listaAmbiti = value.getAmbitoDettaglio2();
		List<ScerevDAmbitoDTO> listReturn = new ArrayList<ScerevDAmbitoDTO>();

		for (AmbitoDettaglio2 single : listaAmbiti) {
			ScerevDAmbitoDTO ambito = new ScerevDAmbitoDTO(single.getIdAmbito().intValue(), single.getCodice(),
					single.getDescrizione(), single.getIdDistretto().intValue(),
					Converter.getData(single.getInizioValidita()), Converter.getData(single.getFineValidita()));

			listReturn.add(ambito);
		}

		return listReturn;
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

}
