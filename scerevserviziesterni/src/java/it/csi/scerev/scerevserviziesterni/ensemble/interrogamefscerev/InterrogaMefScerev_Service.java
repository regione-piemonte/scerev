/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.scerev.scerevserviziesterni.ensemble.interrogamefscerev;

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
@WebServiceClient(name = "InterrogaMefScerev", targetNamespace = "http://InterrogaMefScerev.central.services.auraws.aura.csi.it")
public class InterrogaMefScerev_Service
    extends Service
{

    private static final URL INTERROGAMEFSCEREV_WSDL_LOCATION = null;
    private static final WebServiceException INTERROGAMEFSCEREV_EXCEPTION = new WebServiceException();
    private final static QName INTERROGAMEFSCEREV_QNAME = new QName("http://InterrogaMefScerev.central.services.auraws.aura.csi.it", "InterrogaMefScerev");

//    static {
//        URL url = null;
//        WebServiceException e = null;
//        try {
//            url = new URL("file:/C:/Users/Administrator/Desktop/AURA-SER-01-InterrogaMefScerev.wsdl");
//        } catch (MalformedURLException ex) {
//            e = new WebServiceException(ex);
//        }
//        INTERROGAMEFSCEREV_WSDL_LOCATION = url;
//        INTERROGAMEFSCEREV_EXCEPTION = e;
//    }
//
//    public InterrogaMefScerev_Service() {
//        super(__getWsdlLocation(), INTERROGAMEFSCEREV_QNAME);
//    }
//
//    public InterrogaMefScerev_Service(WebServiceFeature... features) {
//        super(__getWsdlLocation(), INTERROGAMEFSCEREV_QNAME, features);
//    }

    public InterrogaMefScerev_Service(URL wsdlLocation) {
        super(wsdlLocation, INTERROGAMEFSCEREV_QNAME);
    }

    public InterrogaMefScerev_Service(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, INTERROGAMEFSCEREV_QNAME, features);
    }

    public InterrogaMefScerev_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public InterrogaMefScerev_Service(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    public InterrogaMefScerev_Service(String string) {
    	super(__getWsdlLocation(string), INTERROGAMEFSCEREV_QNAME);
	}

	/**
     * 
     * @return
     *     returns InterrogaMefScerevSoap
     */
    @WebEndpoint(name = "InterrogaMefScerevSoap")
    public InterrogaMefScerevSoap getInterrogaMefScerevSoap() {
        return super.getPort(new QName("http://InterrogaMefScerev.central.services.auraws.aura.csi.it", "InterrogaMefScerevSoap"), InterrogaMefScerevSoap.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns InterrogaMefScerevSoap
     */
    @WebEndpoint(name = "InterrogaMefScerevSoap")
    public InterrogaMefScerevSoap getInterrogaMefScerevSoap(WebServiceFeature... features) {
        return super.getPort(new QName("http://InterrogaMefScerev.central.services.auraws.aura.csi.it", "InterrogaMefScerevSoap"), InterrogaMefScerevSoap.class, features);
    }

    private static URL __getWsdlLocation(String endpoint) {
    	URL baseUrl;
    	URL url = null;
		baseUrl = InterrogaMefScerev_Service.class.getResource(".");
		try {
			url = new URL(baseUrl, endpoint+"?WSDL");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return url;

    }
}
