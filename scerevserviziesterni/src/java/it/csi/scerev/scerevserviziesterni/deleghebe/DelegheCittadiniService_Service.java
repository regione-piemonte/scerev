/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.scerev.scerevserviziesterni.deleghebe;

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
@WebServiceClient(name = "DelegheCittadiniService", targetNamespace = "http://deleghebe.csi.it/")
public class DelegheCittadiniService_Service
    extends Service
{

    private final static URL DELEGHECITTADINISERVICE_WSDL_LOCATION;
    private final static WebServiceException DELEGHECITTADINISERVICE_EXCEPTION;
    private final static QName DELEGHECITTADINISERVICE_QNAME = new QName("http://deleghebe.csi.it/", "DelegheCittadiniService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("file:/C:/Users/Administrator/Desktop/DelegheCittadiniService.wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        DELEGHECITTADINISERVICE_WSDL_LOCATION = url;
        DELEGHECITTADINISERVICE_EXCEPTION = e;
    }

    public DelegheCittadiniService_Service() {
        super(__getWsdlLocation(), DELEGHECITTADINISERVICE_QNAME);
    }
//
//    public DelegheCittadiniService_Service(WebServiceFeature... features) {
//        super(__getWsdlLocation(), DELEGHECITTADINISERVICE_QNAME, features);
//    }
//
//    public DelegheCittadiniService_Service(URL wsdlLocation) {
//        super(wsdlLocation, DELEGHECITTADINISERVICE_QNAME);
//    }
//
//    public DelegheCittadiniService_Service(URL wsdlLocation, WebServiceFeature... features) {
//        super(wsdlLocation, DELEGHECITTADINISERVICE_QNAME, features);
//    }
//
//    public DelegheCittadiniService_Service(URL wsdlLocation, QName serviceName) {
//        super(wsdlLocation, serviceName);
//    }
//
//    public DelegheCittadiniService_Service(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
//        super(wsdlLocation, serviceName, features);
//    }

    /**
     * 
     * @return
     *     returns DelegheCittadiniService
     */
    @WebEndpoint(name = "DelegheCittadiniServiceImplPort")
    public DelegheCittadiniService getDelegheCittadiniServiceImplPort() {
        return super.getPort(new QName("http://deleghebe.csi.it/", "DelegheCittadiniServiceImplPort"), DelegheCittadiniService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns DelegheCittadiniService
     */
    @WebEndpoint(name = "DelegheCittadiniServiceImplPort")
    public DelegheCittadiniService getDelegheCittadiniServiceImplPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://deleghebe.csi.it/", "DelegheCittadiniServiceImplPort"), DelegheCittadiniService.class, features);
    }

    private static URL __getWsdlLocation() {
        if (DELEGHECITTADINISERVICE_EXCEPTION!= null) {
            throw DELEGHECITTADINISERVICE_EXCEPTION;
        }
        return DELEGHECITTADINISERVICE_WSDL_LOCATION;
    }

}
