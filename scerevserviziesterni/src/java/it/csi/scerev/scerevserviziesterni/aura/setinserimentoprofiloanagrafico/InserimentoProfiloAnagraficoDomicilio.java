/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.scerev.scerevserviziesterni.aura.setinserimentoprofiloanagrafico;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per InserimentoProfiloAnagraficoDomicilio complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="InserimentoProfiloAnagraficoDomicilio">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="codiceStatoDomicilio" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="3"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="codiceComuneDomicilio" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="6"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="descrizioneComuneDomicilio" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="255"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="indirizzoDomicilio" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="100"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="numerocivicoDomicilio" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="50"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="CAPDomicilio" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="5"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="TelefonoDomicilio" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="20"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="CodiceAslDomicilio" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="6"/>
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
@XmlType(name = "InserimentoProfiloAnagraficoDomicilio", propOrder = {
    "codiceStatoDomicilio",
    "codiceComuneDomicilio",
    "descrizioneComuneDomicilio",
    "indirizzoDomicilio",
    "numerocivicoDomicilio",
    "capDomicilio",
    "telefonoDomicilio",
    "codiceAslDomicilio"
})
public class InserimentoProfiloAnagraficoDomicilio {

    protected String codiceStatoDomicilio;
    protected String codiceComuneDomicilio;
    protected String descrizioneComuneDomicilio;
    protected String indirizzoDomicilio;
    protected String numerocivicoDomicilio;
    @XmlElement(name = "CAPDomicilio")
    protected String capDomicilio;
    @XmlElement(name = "TelefonoDomicilio")
    protected String telefonoDomicilio;
    @XmlElement(name = "CodiceAslDomicilio")
    protected String codiceAslDomicilio;

    /**
     * Recupera il valore della proprietà codiceStatoDomicilio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceStatoDomicilio() {
        return codiceStatoDomicilio;
    }

    /**
     * Imposta il valore della proprietà codiceStatoDomicilio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceStatoDomicilio(String value) {
        this.codiceStatoDomicilio = value;
    }

    /**
     * Recupera il valore della proprietà codiceComuneDomicilio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceComuneDomicilio() {
        return codiceComuneDomicilio;
    }

    /**
     * Imposta il valore della proprietà codiceComuneDomicilio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceComuneDomicilio(String value) {
        this.codiceComuneDomicilio = value;
    }

    /**
     * Recupera il valore della proprietà descrizioneComuneDomicilio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescrizioneComuneDomicilio() {
        return descrizioneComuneDomicilio;
    }

    /**
     * Imposta il valore della proprietà descrizioneComuneDomicilio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescrizioneComuneDomicilio(String value) {
        this.descrizioneComuneDomicilio = value;
    }

    /**
     * Recupera il valore della proprietà indirizzoDomicilio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIndirizzoDomicilio() {
        return indirizzoDomicilio;
    }

    /**
     * Imposta il valore della proprietà indirizzoDomicilio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIndirizzoDomicilio(String value) {
        this.indirizzoDomicilio = value;
    }

    /**
     * Recupera il valore della proprietà numerocivicoDomicilio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumerocivicoDomicilio() {
        return numerocivicoDomicilio;
    }

    /**
     * Imposta il valore della proprietà numerocivicoDomicilio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumerocivicoDomicilio(String value) {
        this.numerocivicoDomicilio = value;
    }

    /**
     * Recupera il valore della proprietà capDomicilio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCAPDomicilio() {
        return capDomicilio;
    }

    /**
     * Imposta il valore della proprietà capDomicilio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCAPDomicilio(String value) {
        this.capDomicilio = value;
    }

    /**
     * Recupera il valore della proprietà telefonoDomicilio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTelefonoDomicilio() {
        return telefonoDomicilio;
    }

    /**
     * Imposta il valore della proprietà telefonoDomicilio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTelefonoDomicilio(String value) {
        this.telefonoDomicilio = value;
    }

    /**
     * Recupera il valore della proprietà codiceAslDomicilio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceAslDomicilio() {
        return codiceAslDomicilio;
    }

    /**
     * Imposta il valore della proprietà codiceAslDomicilio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceAslDomicilio(String value) {
        this.codiceAslDomicilio = value;
    }

}
