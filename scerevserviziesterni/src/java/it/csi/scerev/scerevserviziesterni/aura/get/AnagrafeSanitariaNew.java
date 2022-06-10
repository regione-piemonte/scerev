/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.scerev.scerevserviziesterni.aura.get;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "AnagrafeSanitariaNew", targetNamespace = "http://AnagrafeSanitaria.central.services.auraws.aura.csi.it")
public class AnagrafeSanitariaNew
    extends Service
{

    private final static URL ANAGRAFESANITARIANEW_WSDL_LOCATION = null;
    private final static WebServiceException ANAGRAFESANITARIANEW_EXCEPTION = new WebServiceException();
    private final static QName ANAGRAFESANITARIANEW_QNAME = new QName("http://AnagrafeSanitaria.central.services.auraws.aura.csi.it", "AnagrafeSanitariaNew");

//    static {
//        URL url = null;
//        WebServiceException e = null;
//        try {
//            url = new URL("https://portale.ruparpiemonte.it/sanitxens/aura/AURA.WS.AnagrafeSanitariaNew.cls?wsdl");
//        } catch (MalformedURLException ex) {
//            e = new WebServiceException(ex);
//        }
//        ANAGRAFESANITARIANEW_WSDL_LOCATION = url;
//        ANAGRAFESANITARIANEW_EXCEPTION = e;
//    }
//
//    public AnagrafeSanitariaNew() {
//        super(__getWsdlLocation(), ANAGRAFESANITARIANEW_QNAME);
//    }

//    public AnagrafeSanitariaNew(WebServiceFeature... features) {
//        super(__getWsdlLocation(), ANAGRAFESANITARIANEW_QNAME, features);
//    }
//
    public AnagrafeSanitariaNew(URL wsdlLocation) {
        super(wsdlLocation, ANAGRAFESANITARIANEW_QNAME);
    }

    public AnagrafeSanitariaNew(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, ANAGRAFESANITARIANEW_QNAME, features);
    }

    public AnagrafeSanitariaNew(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public AnagrafeSanitariaNew(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }
    
    public AnagrafeSanitariaNew(String string) {
    	super(__getWsdlLocation(string), ANAGRAFESANITARIANEW_QNAME);
	}

    /**
     * 
     * @return
     *     returns AnagrafeSanitariaNewSoap
     */
    @WebEndpoint(name = "AnagrafeSanitariaNewSoap")
    public AnagrafeSanitariaNewSoap getAnagrafeSanitariaNewSoap() {
        return super.getPort(new QName("http://AnagrafeSanitaria.central.services.auraws.aura.csi.it", "AnagrafeSanitariaNewSoap"), AnagrafeSanitariaNewSoap.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns AnagrafeSanitariaNewSoap
     */
    @WebEndpoint(name = "AnagrafeSanitariaNewSoap")
    public AnagrafeSanitariaNewSoap getAnagrafeSanitariaNewSoap(WebServiceFeature... features) {
        return super.getPort(new QName("http://AnagrafeSanitaria.central.services.auraws.aura.csi.it", "AnagrafeSanitariaNewSoap"), AnagrafeSanitariaNewSoap.class, features);
    }

//    private static URL __getWsdlLocation() {
//        if (ANAGRAFESANITARIANEW_EXCEPTION!= null) {
//            throw ANAGRAFESANITARIANEW_EXCEPTION;
//        }
//        return ANAGRAFESANITARIANEW_WSDL_LOCATION;
//    }

    private static URL __getWsdlLocation(String endpoint) {
    	URL baseUrl;
    	URL url = null;
		baseUrl = AnagrafeSanitariaNew.class.getResource(".");
		try {
			url = new URL(baseUrl, endpoint+"?WSDL");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return url;
    }
}
