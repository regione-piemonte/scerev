/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.scerev.scerevserviziesterni.aura.setinserimentoprofiloanagrafico;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java per InserimentoProfiloAnagraficoHeader complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="InserimentoProfiloAnagraficoHeader">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idAsr" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="sender">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="6"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="requestDateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="webServiceURI" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cfUtilizzatoreServizio">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="16"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
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
@XmlType(name = "InserimentoProfiloAnagraficoHeader", propOrder = {
    "idAsr",
    "sender",
    "requestDateTime",
    "webServiceURI",
    "cfUtilizzatoreServizio"
})
public class InserimentoProfiloAnagraficoHeader {

    @XmlElement(required = true)
    protected String idAsr;
    @XmlElement(required = true)
    protected String sender;
    @XmlElement(required = true)
//    @XmlSchemaType(name = "dateTime")
    protected String requestDateTime;
    protected String webServiceURI;
    @XmlElement(required = true)
    protected String cfUtilizzatoreServizio;

    /**
     * Recupera il valore della proprietà idAsr.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdAsr() {
        return idAsr;
    }

    /**
     * Imposta il valore della proprietà idAsr.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdAsr(String value) {
        this.idAsr = value;
    }

    /**
     * Recupera il valore della proprietà sender.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSender() {
        return sender;
    }

    /**
     * Imposta il valore della proprietà sender.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSender(String value) {
        this.sender = value;
    }

    /**
     * Recupera il valore della proprietà requestDateTime.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public String getRequestDateTime() {
        return requestDateTime;
    }

    /**
     * Imposta il valore della proprietà requestDateTime.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setRequestDateTime(String value) {
        this.requestDateTime = value;
    }

    /**
     * Recupera il valore della proprietà webServiceURI.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWebServiceURI() {
        return webServiceURI;
    }

    /**
     * Imposta il valore della proprietà webServiceURI.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWebServiceURI(String value) {
        this.webServiceURI = value;
    }

    /**
     * Recupera il valore della proprietà cfUtilizzatoreServizio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCfUtilizzatoreServizio() {
        return cfUtilizzatoreServizio;
    }

    /**
     * Imposta il valore della proprietà cfUtilizzatoreServizio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCfUtilizzatoreServizio(String value) {
        this.cfUtilizzatoreServizio = value;
    }

}
