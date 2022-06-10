/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.scerev.scerevserviziesterni.aura.getUltimoMovimentoDiRevoca;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the it.csi.scerev.scerevserviziesterni.aura.getUltimoMovimentoDiRevoca package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: it.csi.scerev.scerevserviziesterni.aura.getUltimoMovimentoDiRevoca
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetUltimoMovimentoDiRevocaResponse }
     * 
     */
    public GetUltimoMovimentoDiRevocaResponse createGetUltimoMovimentoDiRevocaResponse() {
        return new GetUltimoMovimentoDiRevocaResponse();
    }

    /**
     * Create an instance of {@link Response }
     * 
     */
    public Response createResponse() {
        return new Response();
    }

    /**
     * Create an instance of {@link GetUltimoMovimentoDiRevoca }
     * 
     */
    public GetUltimoMovimentoDiRevoca createGetUltimoMovimentoDiRevoca() {
        return new GetUltimoMovimentoDiRevoca();
    }

    /**
     * Create an instance of {@link Request }
     * 
     */
    public Request createRequest() {
        return new Request();
    }

    /**
     * Create an instance of {@link ResponseDelegate }
     * 
     */
    public ResponseDelegate createResponseDelegate() {
        return new ResponseDelegate();
    }

    /**
     * Create an instance of {@link EnsMessagebody }
     * 
     */
    public EnsMessagebody createEnsMessagebody() {
        return new EnsMessagebody();
    }

    /**
     * Create an instance of {@link EnsResponse }
     * 
     */
    public EnsResponse createEnsResponse() {
        return new EnsResponse();
    }

    /**
     * Create an instance of {@link EnsRequest }
     * 
     */
    public EnsRequest createEnsRequest() {
        return new EnsRequest();
    }

}
