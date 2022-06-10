/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.services.opessanws.opessan;

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
@WebServiceClient(name = "GetDettaglioOperatore", targetNamespace = "http://opessan.opessanws.services.csi.it/")
public class GetDettaglioOperatore_Service
    extends Service
{

    private final static URL GETDETTAGLIOOPERATORE_WSDL_LOCATION = null;
    private final static WebServiceException GETDETTAGLIOOPERATORE_EXCEPTION = new WebServiceException();
    private final static QName GETDETTAGLIOOPERATORE_QNAME = new QName("http://opessan.opessanws.services.csi.it/", "GetDettaglioOperatore");

//    static {
//        URL url = null;
//        WebServiceException e = null;
//        try {
//            url = new URL("https://tst-applogic.reteunitaria.piemonte.it:1449/opessanwsj/services/getDettaglioOperatoreUserTkn?wsdl");
//        } catch (MalformedURLException ex) {
//            e = new WebServiceException(ex);
//        }
//        GETDETTAGLIOOPERATORE_WSDL_LOCATION = url;
//        GETDETTAGLIOOPERATORE_EXCEPTION = e;
//    }
//
//    public GetDettaglioOperatore_Service() {
//        super(__getWsdlLocation(), GETDETTAGLIOOPERATORE_QNAME);
//    }

//    public GetDettaglioOperatore_Service(WebServiceFeature... features) {
//        super(__getWsdlLocation(), GETDETTAGLIOOPERATORE_QNAME, features);
//    }
//
    public GetDettaglioOperatore_Service(URL wsdlLocation) {
        super(wsdlLocation, GETDETTAGLIOOPERATORE_QNAME);
    }

    public GetDettaglioOperatore_Service(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, GETDETTAGLIOOPERATORE_QNAME, features);
    }

    public GetDettaglioOperatore_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public GetDettaglioOperatore_Service(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }
    
    public GetDettaglioOperatore_Service(String string) {
    	super(__getWsdlLocation(string), GETDETTAGLIOOPERATORE_QNAME);
	}

    /**
     * 
     * @return
     *     returns GetDettaglioOperatore
     */
    @WebEndpoint(name = "GetDettaglioOperatoreCXFImplPort")
    public GetDettaglioOperatore getGetDettaglioOperatoreCXFImplPort() {
        return super.getPort(new QName("http://opessan.opessanws.services.csi.it/", "GetDettaglioOperatoreCXFImplPort"), GetDettaglioOperatore.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns GetDettaglioOperatore
     */
    @WebEndpoint(name = "GetDettaglioOperatoreCXFImplPort")
    public GetDettaglioOperatore getGetDettaglioOperatoreCXFImplPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://opessan.opessanws.services.csi.it/", "GetDettaglioOperatoreCXFImplPort"), GetDettaglioOperatore.class, features);
    }

//    private static URL __getWsdlLocation() {
//        if (GETDETTAGLIOOPERATORE_EXCEPTION!= null) {
//            throw GETDETTAGLIOOPERATORE_EXCEPTION;
//        }
//        return GETDETTAGLIOOPERATORE_WSDL_LOCATION;
//    }
    
    private static URL __getWsdlLocation(String endpoint) {
    	URL baseUrl;
    	URL url = null;
		baseUrl = GetDettaglioOperatore_Service.class.getResource(".");
		try {
			url = new URL(baseUrl, endpoint+"?WSDL");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return url;
    }

}
