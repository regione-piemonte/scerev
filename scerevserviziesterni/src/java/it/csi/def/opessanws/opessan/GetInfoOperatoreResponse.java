/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.def.opessanws.opessan;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import it.csi.dto.opessanws.opessan.common.response.Footer;
import it.csi.dto.opessanws.opessan.common.response.Header;


/**
 * <p>Classe Java per getInfoOperatoreResponse complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="getInfoOperatoreResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="header" type="{http://opessan.opessanws.dto.csi.it/common/response/}Header"/>
 *                   &lt;element name="body" type="{http://opessan.opessanws.def.csi.it/}SoggettoOpessanBody" minOccurs="0"/>
 *                   &lt;element name="footer" type="{http://opessan.opessanws.dto.csi.it/common/response/}Footer" minOccurs="0"/>
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
@XmlType(name = "getInfoOperatoreResponse", propOrder = {
    "_return"
})
public class GetInfoOperatoreResponse {

    @XmlElement(name = "return")
    protected GetInfoOperatoreResponse.Return _return;

    /**
     * Recupera il valore della proprietÓ return.
     * 
     * @return
     *     possible object is
     *     {@link GetInfoOperatoreResponse.Return }
     *     
     */
    public GetInfoOperatoreResponse.Return getReturn() {
        return _return;
    }

    /**
     * Imposta il valore della proprietÓ return.
     * 
     * @param value
     *     allowed object is
     *     {@link GetInfoOperatoreResponse.Return }
     *     
     */
    public void setReturn(GetInfoOperatoreResponse.Return value) {
        this._return = value;
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
     *         &lt;element name="header" type="{http://opessan.opessanws.dto.csi.it/common/response/}Header"/>
     *         &lt;element name="body" type="{http://opessan.opessanws.def.csi.it/}SoggettoOpessanBody" minOccurs="0"/>
     *         &lt;element name="footer" type="{http://opessan.opessanws.dto.csi.it/common/response/}Footer" minOccurs="0"/>
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
        "body",
        "footer"
    })
    @XmlRootElement
    public static class Return {

        @XmlElement(required = true)
        protected Header header;
        protected SoggettoOpessanBody body;
        protected Footer footer;

        /**
         * Recupera il valore della proprietÓ header.
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
         * Imposta il valore della proprietÓ header.
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
         * Recupera il valore della proprietÓ body.
         * 
         * @return
         *     possible object is
         *     {@link SoggettoOpessanBody }
         *     
         */
        public SoggettoOpessanBody getBody() {
            return body;
        }

        /**
         * Imposta il valore della proprietÓ body.
         * 
         * @param value
         *     allowed object is
         *     {@link SoggettoOpessanBody }
         *     
         */
        public void setBody(SoggettoOpessanBody value) {
            this.body = value;
        }

        /**
         * Recupera il valore della proprietÓ footer.
         * 
         * @return
         *     possible object is
         *     {@link Footer }
         *     
         */
        public Footer getFooter() {
            return footer;
        }

        /**
         * Imposta il valore della proprietÓ footer.
         * 
         * @param value
         *     allowed object is
         *     {@link Footer }
         *     
         */
        public void setFooter(Footer value) {
            this.footer = value;
        }

    }

}
