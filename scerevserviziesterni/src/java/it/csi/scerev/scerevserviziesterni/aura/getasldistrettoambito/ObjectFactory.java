/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.scerev.scerevserviziesterni.aura.getasldistrettoambito;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the it.csi.aura.auraws.services.central.getasldistrettoambito package. 
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
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: it.csi.aura.auraws.services.central.getasldistrettoambito
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetASLDistrettoAmbito }
     * 
     */
    public GetASLDistrettoAmbito createGetASLDistrettoAmbito() {
        return new GetASLDistrettoAmbito();
    }

    /**
     * Create an instance of {@link GetASLDistrettoAmbitoResponse }
     * 
     */
    public GetASLDistrettoAmbitoResponse createGetASLDistrettoAmbitoResponse() {
        return new GetASLDistrettoAmbitoResponse();
    }

    /**
     * Create an instance of {@link ASLDistrettoAmbitoResponse }
     * 
     */
    public ASLDistrettoAmbitoResponse createASLDistrettoAmbitoResponse() {
        return new ASLDistrettoAmbitoResponse();
    }

}
