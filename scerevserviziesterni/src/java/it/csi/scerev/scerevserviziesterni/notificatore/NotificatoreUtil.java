/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevserviziesterni.notificatore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.axis.utils.IOUtils;

import it.csi.scerev.scerevutil.business.SharedConstants;


public class NotificatoreUtil {
	private static NotificatoreUtil instance;

	private static final Charset STD_CHARSET = Charset.forName("UTF-8");

	private final static Logger log = Logger.getLogger(NotificatoreUtil.class.getName());

	private Properties prop;
	
	public static NotificatoreUtil getInstance() {
		if(instance==null){
			instance=new NotificatoreUtil();
		}
		
		return instance;
		
	}

	private class JSONObj {
		private int indent = 0;
		private String name;
		private Object value;
		private List<JSONObj> elements = null;

		public JSONObj() {
			this.elements = new ArrayList<>();
		}

		public JSONObj(String name, JSONObj parent) {
			this.name = name;
			this.elements = new ArrayList<>();
			this.indent = parent.indent + 1;
			parent.put(name, this);
		}

		private JSONObj(String name, Object value, int indent) {
			this.name = name;
			this.value = value;
			this.indent = indent;
		}

		public void put(String name, Object value) {
			elements.add(new JSONObj(name, value, indent + 1));
		}

		public String toString() {
			StringBuilder sb = new StringBuilder();
			if (elements != null) {
				if (!elements.isEmpty()) {
					sb.append("{\n");
					for (Iterator<JSONObj> itr = elements.iterator(); itr.hasNext();) {
						JSONObj jsonObject = itr.next();
						sb.append(jsonObject.toString()).append(itr.hasNext() ? ",\n" : "\n");
					}
					sb.append(getIndentation()).append("}");
				}
			} else {
				boolean quotation = !(value instanceof JSONObj) && !value.getClass().isArray();
				sb.append(getIndentation()).append('"').append(name).append('"').append(": ");
				if (quotation)
					sb.append('"');
				if (value.getClass().isArray())
					sb.append(toString((Object[]) value));
				else
					sb.append(value.toString());
				if (quotation)
					sb.append('"');
			}
			return sb.toString();
		}

		private String toString(Object[] array) {
			StringBuilder sb = new StringBuilder();
			sb.append("[");
			for (int i = 0; i < array.length; i++) {
				boolean quotation = !(array[i] instanceof JSONObj) && !array[i].getClass().isArray();
				if (quotation)
					sb.append('"');
				sb.append(array[i].toString());
				if (quotation)
					sb.append('"');
				sb.append((i < array.length - 1) ? "," : "");
			}
			sb.append("]");
			return sb.toString();
		}

		private String getIndentation() {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < indent; i++)
				sb.append("\t");
			return sb.toString();
		}
	}

	public void callNotificatore(String webService, String cfDestinatario, String recipient, Map<String, String> replacements) {

		try {
			
			String xAuthenticationString = prop.getProperty("xAuthenticationString");
			URL url = new URL(prop.getProperty("endpoint"));
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("x-authentication", xAuthenticationString);

			String jsonBody = getJsonBody(webService, cfDestinatario, recipient, replacements);
			OutputStream os = conn.getOutputStream();
			os.write(jsonBody.getBytes(STD_CHARSET));
			os.flush();

			if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
				log.log(java.util.logging.Level.SEVERE, "NotificatoreUtil::callNotificatore: Response code diverso da HTTP 201 Created");
				throw new IllegalStateException("Errore del servizio di notifica: HTTP error code : " + conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream()), STD_CHARSET));

			//log.debug(IOUtils.toString(br));

			conn.disconnect();

		} catch (MalformedURLException e) {
			log.log(Level.SEVERE, "NotificatoreUtil::callNotificatore: MalformedURLException", e);
			e.printStackTrace();
			throw new IllegalStateException("Errore url endpoint: ", e);
		} catch (IOException e) {
			log.log(Level.SEVERE, "NotificatoreUtil::callNotificatore: IOException", e);
			e.printStackTrace();
			throw new IllegalStateException("Errore richiamo servizio notificatore: ", e);
		}
	}

	private String getJsonBody(String webService, String cfDestinatario, String recipient, Map<String, String> replacements) {

		NotificatoreUtil.JSONObj joRoot = new JSONObj();
		joRoot.put("uuid", UUID.randomUUID());

		NotificatoreUtil.JSONObj joPLoad = new JSONObj("payload", joRoot);
		joPLoad.put("id", UUID.randomUUID());
		joPLoad.put("user_id", cfDestinatario);
		joPLoad.put("tag", prop.getProperty("tag"));

		if (prop.getProperty(webService + "_push_title_"+recipient) != null) {
			NotificatoreUtil.JSONObj joPush = new JSONObj("push", joPLoad);
			joPush.put("title", prop.getProperty(webService + "_push_title_"+recipient));
			joPush.put("body", replace(prop.getProperty(webService + "_push_" + recipient), replacements));
		}

		if (prop.getProperty(webService + "_email_subject_"+recipient) != null) {
			NotificatoreUtil.JSONObj joEmail = new JSONObj("email", joPLoad);
			joEmail.put("subject", prop.getProperty(webService + "_email_subject_"+recipient));
			joEmail.put("body", replace(prop.getProperty(webService + "_email_" + recipient), replacements));
			joEmail.put("template_id", prop.getProperty(SharedConstants.NOTIFICATORE_TEMPLATE));
		}

		if (prop.getProperty(webService + "_sms_beneficiario") != null) {
			NotificatoreUtil.JSONObj joSms = new JSONObj("sms", joPLoad);
			joSms.put("content", replace(prop.getProperty(webService + "_sms_beneficiario"), replacements));
		}

		if (prop.getProperty(webService + "_mex_title_"+recipient) != null) {
			NotificatoreUtil.JSONObj joMex = new JSONObj("mex", joPLoad);
			joMex.put("title", prop.getProperty(webService + "_mex_title_"+recipient));
			joMex.put("body", replace(prop.getProperty(webService + "_mex_" + recipient), replacements));
			joMex.put("call_to_action", prop.getProperty(webService + "_mex_callToAction_" + recipient));
		}
		
		String jsonBody = joRoot.toString();

//		log.debug("endpoint: " + prop.getProperty("endpoint"));
//		log.debug(jsonBody);

		return jsonBody;
	}

	private String replace(String text, Map<String, String> replacements) {
		for (Entry<String, String> entry : replacements.entrySet()) {
			text = text.replaceAll(entry.getKey(), entry.getValue());
		}
		return text;
	}

	public Properties getProp() {
		return prop;
	}

	public void setProp(Properties prop) {
		this.prop = prop;
	}
}
