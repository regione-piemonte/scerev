/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.def.opessanws.opessan;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java per InfoMassimali complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="InfoMassimali">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="massimale" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="massInDeroga" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="massScelteTemp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="massScelta03anni" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dataVariazione" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InfoMassimali", propOrder = {
    "massimale",
    "massInDeroga",
    "massScelteTemp",
    "massScelta03Anni",
    "dataVariazione"
})
public class InfoMassimali {

    @XmlElement(required = true)
    protected String massimale;
    @XmlElement(required = true)
    protected String massInDeroga;
    protected String massScelteTemp;
    @XmlElement(name = "massScelta03anni")
    protected String massScelta03Anni;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataVariazione;

    /**
     * Recupera il valore della proprietà massimale.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMassimale() {
        return massimale;
    }

    /**
     * Imposta il valore della proprietà massimale.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMassimale(String value) {
        this.massimale = value;
    }

    /**
     * Recupera il valore della proprietà massInDeroga.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMassInDeroga() {
        return massInDeroga;
    }

    /**
     * Imposta il valore della proprietà massInDeroga.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMassInDeroga(String value) {
        this.massInDeroga = value;
    }

    /**
     * Recupera il valore della proprietà massScelteTemp.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMassScelteTemp() {
        return massScelteTemp;
    }

    /**
     * Imposta il valore della proprietà massScelteTemp.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMassScelteTemp(String value) {
        this.massScelteTemp = value;
    }

    /**
     * Recupera il valore della proprietà massScelta03Anni.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMassScelta03Anni() {
        return massScelta03Anni;
    }

    /**
     * Imposta il valore della proprietà massScelta03Anni.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMassScelta03Anni(String value) {
        this.massScelta03Anni = value;
    }

    /**
     * Recupera il valore della proprietà dataVariazione.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataVariazione() {
        return dataVariazione;
    }

    /**
     * Imposta il valore della proprietà dataVariazione.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataVariazione(XMLGregorianCalendar value) {
        this.dataVariazione = value;
    }

}
