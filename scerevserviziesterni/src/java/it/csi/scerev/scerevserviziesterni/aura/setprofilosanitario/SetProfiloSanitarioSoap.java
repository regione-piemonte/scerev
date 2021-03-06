/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.scerev.scerevserviziesterni.aura.setprofilosanitario;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "SetProfiloSanitarioSoap", targetNamespace = "http://SetProfiloSanitario.central.services.auraws.aura.csi.it")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface SetProfiloSanitarioSoap {


    /**
     * 
     * @param profiloSanitario
     * @return
     *     returns it.csi.aura.auraws.services.central.setprofilosanitario.ProfiloSanitarioRes
     */
    @WebMethod(operationName = "SetProfiloSanitario", action = "http://SetProfiloSanitario.central.services.auraws.aura.csi.it/AURA.WS.SetProfiloSanitario.SetProfiloSanitario")
    @WebResult(name = "SetProfiloSanitarioResult", targetNamespace = "http://SetProfiloSanitario.central.services.auraws.aura.csi.it")
    @RequestWrapper(localName = "SetProfiloSanitario", targetNamespace = "http://SetProfiloSanitario.central.services.auraws.aura.csi.it", className = "it.csi.aura.auraws.services.central.setprofilosanitario.SetProfiloSanitario")
    @ResponseWrapper(localName = "SetProfiloSanitarioResponse", targetNamespace = "http://SetProfiloSanitario.central.services.auraws.aura.csi.it", className = "it.csi.aura.auraws.services.central.setprofilosanitario.SetProfiloSanitarioResponse")
    public ProfiloSanitarioRes setProfiloSanitario(
        @WebParam(name = "ProfiloSanitario", targetNamespace = "http://SetProfiloSanitario.central.services.auraws.aura.csi.it")
        ProfiloSanitario profiloSanitario);

}
