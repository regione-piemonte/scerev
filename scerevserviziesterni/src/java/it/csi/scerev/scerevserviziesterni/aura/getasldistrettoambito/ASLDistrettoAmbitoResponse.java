/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.scerev.scerevserviziesterni.aura.getasldistrettoambito;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per ASLDistrettoAmbitoResponse complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ASLDistrettoAmbitoResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="codiceEsito" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="1000"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="comuneId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codiceAsl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Asl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Distretto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Ambito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ASLDistrettoAmbitoResponse", propOrder = {
    "codiceEsito",
    "comuneId",
    "codiceAsl",
    "asl",
    "distretto",
    "ambito"
})
@XmlRootElement
public class ASLDistrettoAmbitoResponse {

    protected String codiceEsito;
    protected String comuneId;
    protected String codiceAsl;
    @XmlElement(name = "Asl")
    protected String asl;
    @XmlElement(name = "Distretto")
    protected String distretto;
    @XmlElement(name = "Ambito")
    protected String ambito;

    /**
     * Recupera il valore della proprietà codiceEsito.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceEsito() {
        return codiceEsito;
    }

    /**
     * Imposta il valore della proprietà codiceEsito.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceEsito(String value) {
        this.codiceEsito = value;
    }

    /**
     * Recupera il valore della proprietà comuneId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComuneId() {
        return comuneId;
    }

    /**
     * Imposta il valore della proprietà comuneId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComuneId(String value) {
        this.comuneId = value;
    }

    /**
     * Recupera il valore della proprietà codiceAsl.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceAsl() {
        return codiceAsl;
    }

    /**
     * Imposta il valore della proprietà codiceAsl.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceAsl(String value) {
        this.codiceAsl = value;
    }

    /**
     * Recupera il valore della proprietà asl.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAsl() {
        return asl;
    }

    /**
     * Imposta il valore della proprietà asl.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAsl(String value) {
        this.asl = value;
    }

    /**
     * Recupera il valore della proprietà distretto.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDistretto() {
        return distretto;
    }

    /**
     * Imposta il valore della proprietà distretto.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDistretto(String value) {
        this.distretto = value;
    }

    /**
     * Recupera il valore della proprietà ambito.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAmbito() {
        return ambito;
    }

    /**
     * Imposta il valore della proprietà ambito.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAmbito(String value) {
        this.ambito = value;
    }

}
