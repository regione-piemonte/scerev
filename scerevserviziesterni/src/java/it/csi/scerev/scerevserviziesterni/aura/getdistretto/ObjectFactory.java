/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.scerev.scerevserviziesterni.aura.getdistretto;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the it.csi.aura.auraws.services.central.getdistretto package. 
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
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: it.csi.aura.auraws.services.central.getdistretto
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetDistretto }
     * 
     */
    public GetDistretto createGetDistretto() {
        return new GetDistretto();
    }

    /**
     * Create an instance of {@link GetDistrettoResponse }
     * 
     */
    public GetDistrettoResponse createGetDistrettoResponse() {
        return new GetDistrettoResponse();
    }

    /**
     * Create an instance of {@link GetDistrettoResult }
     * 
     */
    public GetDistrettoResult createGetDistrettoResult() {
        return new GetDistrettoResult();
    }

    /**
     * Create an instance of {@link ArrayOfDistrettoDettaglio }
     * 
     */
    public ArrayOfDistrettoDettaglio createArrayOfDistrettoDettaglio() {
        return new ArrayOfDistrettoDettaglio();
    }

    /**
     * Create an instance of {@link DistrettoDettaglio }
     * 
     */
    public DistrettoDettaglio createDistrettoDettaglio() {
        return new DistrettoDettaglio();
    }

}
