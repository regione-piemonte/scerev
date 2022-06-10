/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.scerev.scerevlibbatch.opessan.def.opessanws.opessan;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import it.csi.scerev.scerevlibbatch.opessan.dto.opessanws.opessan.common.request.Header;


/**
 * <p>Classe Java per getMassimaliMedico complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="getMassimaliMedico">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="arg0" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="header" type="{http://opessan.opessanws.dto.csi.it/common/request/}Header"/>
 *                   &lt;element name="body" type="{http://opessan.opessanws.def.csi.it/}MassimaliMediciReqBody"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getMassimaliMedico", propOrder = {
    "arg0"
})
public class GetMassimaliMedico {

    protected GetMassimaliMedico.Arg0 arg0;

    /**
     * Recupera il valore della proprietà arg0.
     * 
     * @return
     *     possible object is
     *     {@link GetMassimaliMedico.Arg0 }
     *     
     */
    public GetMassimaliMedico.Arg0 getArg0() {
        return arg0;
    }

    /**
     * Imposta il valore della proprietà arg0.
     * 
     * @param value
     *     allowed object is
     *     {@link GetMassimaliMedico.Arg0 }
     *     
     */
    public void setArg0(GetMassimaliMedico.Arg0 value) {
        this.arg0 = value;
    }


    /**
     * <p>Classe Java per anonymous complex type.
     * 
     * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="header" type="{http://opessan.opessanws.dto.csi.it/common/request/}Header"/>
     *         &lt;element name="body" type="{http://opessan.opessanws.def.csi.it/}MassimaliMediciReqBody"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "header",
        "body"
    })
    @XmlRootElement
    public static class Arg0 {

        @XmlElement(required = true)
        protected Header header;
        @XmlElement(required = true)
        protected MassimaliMediciReqBody body;

        /**
         * Recupera il valore della proprietà header.
         * 
         * @return
         *     possible object is
         *     {@link Header }
         *     
         */
        public Header getHeader() {
            return header;
        }

        /**
         * Imposta il valore della proprietà header.
         * 
         * @param value
         *     allowed object is
         *     {@link Header }
         *     
         */
        public void setHeader(Header value) {
            this.header = value;
        }

        /**
         * Recupera il valore della proprietà body.
         * 
         * @return
         *     possible object is
         *     {@link MassimaliMediciReqBody }
         *     
         */
        public MassimaliMediciReqBody getBody() {
            return body;
        }

        /**
         * Imposta il valore della proprietà body.
         * 
         * @param value
         *     allowed object is
         *     {@link MassimaliMediciReqBody }
         *     
         */
        public void setBody(MassimaliMediciReqBody value) {
            this.body = value;
        }

    }

}
