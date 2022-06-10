/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevlibbatch.facade;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import it.csi.scerev.scerevlibbatch.configuration.Configuration;
import it.csi.scerev.scerevlibbatch.dto.ModelAmbulatorioLoccsiMedico;
import it.csi.scerev.scerevlibbatch.dto.ModelDisponibilita;
import it.csi.scerev.scerevlibbatch.logger.BatchLoggerFactory;
import it.csi.scerev.scerevlibbatch.util.Converter;


public class MassimaliFacade {

	public List<ModelDisponibilita> getDisponibilita(List<ModelAmbulatorioLoccsiMedico> listaMedici) throws Exception {
		CloseableHttpClient httpClient = HttpClients.createDefault();

		try {
			String endpoint = Configuration.get("endpoint.frontend");
			String serviceDisponibilita = Configuration.get("service.disponibilita");
			
			HttpPost request = new HttpPost(endpoint + serviceDisponibilita);
			JSONArray array = new JSONArray();
			for(ModelAmbulatorioLoccsiMedico m : listaMedici) {
				JSONObject o = getJson(m);
				array.put(o);
			}

			StringEntity params = new StringEntity(array.toString(), ContentType.APPLICATION_JSON);

			request.setHeader("Accept", "application/json");
			request.setHeader("Content-type", "application/json");
			request.setHeader("X-Request-Id", UUID.randomUUID().toString());
			request.setHeader("X-Forwarded-For", InetAddress.getLocalHost().getHostAddress());
			request.setHeader("X-Codice-Servizio", "SCEREV_BATCH_MONITORATI");
			request.setEntity(params);

			CloseableHttpResponse response = httpClient.execute(request);

			HttpEntity entity = response.getEntity();
			if (entity != null) {
				String retSrc = EntityUtils.toString(entity);
				System.out.println(retSrc);
				if (response.getStatusLine().getStatusCode() == 200) {
					List<ModelDisponibilita> out = getResponseDisponibilita(retSrc);
					
					//TODO
					BatchLoggerFactory.getLogger(this).info("ok");
					return out;
				} else {
					//TODO
					BatchLoggerFactory.getLogger(this).info("ko");
					return null;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			BatchLoggerFactory.getLogger(this)
			.info("ko");
		} finally {
			httpClient.close();
		}
		return null;
	}

	private List<ModelDisponibilita> getResponseDisponibilita(String retSrc) throws JSONException, JsonParseException, JsonMappingException, IOException {
		List<ModelDisponibilita> out = new ArrayList<ModelDisponibilita>();
		ObjectMapper mapper = new ObjectMapper();
		JSONArray arr = new JSONArray(retSrc);
		for(int i=0; i<arr.length(); i++) {
			 
			 ModelDisponibilita o = mapper.readValue(arr.get(i).toString(), ModelDisponibilita.class);
			 out.add(o);
		}
		return out;
	}

	private JSONObject getJson(ModelAmbulatorioLoccsiMedico m) throws IllegalArgumentException, IllegalAccessException {
		HashMap<String, Object> map = Converter.convertObjToMap(m);
		JSONObject json = new JSONObject(map);
		System.out.println(json);
		return json;
	}
}
