/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.scerev.scerevlibbatch.opessan.def.opessanws.opessan;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the it.csi.def.opessanws.opessan package. 
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

    private final static QName _GetMassimaliMedicoResponse_QNAME = new QName("http://opessan.opessanws.def.csi.it/", "getMassimaliMedicoResponse");
    private final static QName _GetMassimaliMedico_QNAME = new QName("http://opessan.opessanws.def.csi.it/", "getMassimaliMedico");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: it.csi.def.opessanws.opessan
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetMassimaliMedicoResponse }
     * 
     */
    public GetMassimaliMedicoResponse createGetMassimaliMedicoResponse() {
        return new GetMassimaliMedicoResponse();
    }

    /**
     * Create an instance of {@link GetMassimaliMedico }
     * 
     */
    public GetMassimaliMedico createGetMassimaliMedico() {
        return new GetMassimaliMedico();
    }

    /**
     * Create an instance of {@link MassimaliMediciRequest }
     * 
     */
    public MassimaliMediciRequest createMassimaliMediciRequest() {
        return new MassimaliMediciRequest();
    }

    /**
     * Create an instance of {@link MassimaliMediciReqBody }
     * 
     */
    public MassimaliMediciReqBody createMassimaliMediciReqBody() {
        return new MassimaliMediciReqBody();
    }

    /**
     * Create an instance of {@link MassimaliMediciResponse }
     * 
     */
    public MassimaliMediciResponse createMassimaliMediciResponse() {
        return new MassimaliMediciResponse();
    }

    /**
     * Create an instance of {@link MassimaliMediciBody }
     * 
     */
    public MassimaliMediciBody createMassimaliMediciBody() {
        return new MassimaliMediciBody();
    }

    /**
     * Create an instance of {@link GetMassimaliMedicoResponse.Return }
     * 
     */
    public GetMassimaliMedicoResponse.Return createGetMassimaliMedicoResponseReturn() {
        return new GetMassimaliMedicoResponse.Return();
    }

    /**
     * Create an instance of {@link GetMassimaliMedico.Arg0 }
     * 
     */
    public GetMassimaliMedico.Arg0 createGetMassimaliMedicoArg0() {
        return new GetMassimaliMedico.Arg0();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetMassimaliMedicoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://opessan.opessanws.def.csi.it/", name = "getMassimaliMedicoResponse")
    public JAXBElement<GetMassimaliMedicoResponse> createGetMassimaliMedicoResponse(GetMassimaliMedicoResponse value) {
        return new JAXBElement<GetMassimaliMedicoResponse>(_GetMassimaliMedicoResponse_QNAME, GetMassimaliMedicoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetMassimaliMedico }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://opessan.opessanws.def.csi.it/", name = "getMassimaliMedico")
    public JAXBElement<GetMassimaliMedico> createGetMassimaliMedico(GetMassimaliMedico value) {
        return new JAXBElement<GetMassimaliMedico>(_GetMassimaliMedico_QNAME, GetMassimaliMedico.class, null, value);
    }

}
