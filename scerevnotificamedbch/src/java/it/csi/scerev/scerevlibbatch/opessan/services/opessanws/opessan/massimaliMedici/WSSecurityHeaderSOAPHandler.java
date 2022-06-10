/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevlibbatch.opessan.services.opessanws.opessan.massimaliMedici;

import java.io.ByteArrayOutputStream;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Set;
import java.util.TimeZone;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPHeader;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;



public class WSSecurityHeaderSOAPHandler implements SOAPHandler<SOAPMessageContext> {

	private static final String SOAP_ELEMENT_PASSWORD = "Password";
	private static final String SOAP_ELEMENT_USERNAME = "Username";
	private static final String SOAP_ELEMENT_USERNAME_TOKEN = "UsernameToken";
	private static final String SOAP_ELEMENT_SECURITY = "Security";
	private static final String NAMESPACE_SECURITY = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd";
	private static final String PREFIX_SECURITY = "wsse";

	private String usernameText;
	private String passwordText;

	public WSSecurityHeaderSOAPHandler(String usernameText, String passwordText) {
		this.usernameText = usernameText;
		this.passwordText = passwordText;
	}

	public boolean handleMessage(SOAPMessageContext soapMessageContext) {

		Boolean outboundProperty = (Boolean) soapMessageContext.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
		
		if (outboundProperty.booleanValue()) {

			try {
		        SecureRandom rand = SecureRandom.getInstance("SHA1PRNG");
		        rand.setSeed(System.currentTimeMillis());
		        byte[] nonceBytes = new byte[16];
		        rand.nextBytes(nonceBytes);
		        
		        //Make the created date
		        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		        df.setTimeZone(TimeZone.getTimeZone("UTC"));
		        String createdDate = df.format(Calendar.getInstance().getTime());
		        byte[] createdDateBytes = createdDate.getBytes("UTF-8");
		        
		        //Make the password
		        byte[] passwordBytes = this.passwordText.getBytes("UTF-8");
		        
		        //SHA-1 hash the bunch of it.
		        ByteArrayOutputStream baos = new ByteArrayOutputStream();
		        baos.write(nonceBytes);
		        baos.write(createdDateBytes);
		        baos.write(passwordBytes);
		        MessageDigest md = MessageDigest.getInstance("SHA-1");
		        byte[] digestedPassword = md.digest(baos.toByteArray());
		        
		        //Encode the password and nonce for sending                   
		        String passwordB64 = new String(org.apache.commons.codec.binary.Base64.encodeBase64(digestedPassword), "UTF-8");
		        String nonceB64 = new String(org.apache.commons.codec.binary.Base64.encodeBase64(nonceBytes), "UTF-8");
		        
		        //Now create the header with all the appropriate elements
		        SOAPEnvelope envelope = soapMessageContext.getMessage().getSOAPPart().getEnvelope();
		        SOAPHeader header = envelope.getHeader();
		        if (header == null) {
					header = envelope.addHeader();
				};
//		        SOAPHeader header = envelope.addHeader();
		        SOAPElement security = header.addChildElement("Security", "wsse", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd");
		        SOAPElement usernameToken = security.addChildElement("UsernameToken", "wsse");
		        
		        SOAPElement username = usernameToken.addChildElement("Username", "wsse");
		        username.addTextNode(this.usernameText);
		        
		        SOAPElement password = usernameToken.addChildElement("Password", "wsse");
		        password.setAttribute("Type", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordDigest");
		        password.addTextNode(passwordB64);
		        
		        SOAPElement nonce = usernameToken.addChildElement("Nonce", "wsse");
		        nonce.addTextNode(nonceB64);
		        
		        SOAPElement created = usernameToken.addChildElement("Created", "wsu", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd");
		        created.addTextNode(createdDate);

			} catch (Exception e) {
				throw new RuntimeException("Error on wsSecurityHandler: " + e.getMessage());
			}

		}

		return true;
	}

	@Override
	public void close(MessageContext context) {
	}

	@Override
	public boolean handleFault(SOAPMessageContext context) {
		return true;
	}

	@Override
	public Set<QName> getHeaders() {
		return null;
	}
}
