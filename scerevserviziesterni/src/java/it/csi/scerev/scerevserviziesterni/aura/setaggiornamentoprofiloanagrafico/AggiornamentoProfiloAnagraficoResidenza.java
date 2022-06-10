/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.scerev.scerevserviziesterni.aura.setaggiornamentoprofiloanagrafico;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per AggiornamentoProfiloAnagraficoResidenza complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="AggiornamentoProfiloAnagraficoResidenza">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="codiceStatoResidenza" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="3"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="codiceComuneResidenza" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="6"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="descrizioneComuneResidenza" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="255"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="indirizzoResidenza" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="100"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="numerocivicoResidenza" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="50"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="CAPresidenza" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="5"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="TelefonoResidenza" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="20"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="CodiceAslResidenza" minOccurs="0">
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
@XmlType(name = "AggiornamentoProfiloAnagraficoResidenza", propOrder = {
    "codiceStatoResidenza",
    "codiceComuneResidenza",
    "descrizioneComuneResidenza",
    "indirizzoResidenza",
    "numerocivicoResidenza",
    "caPresidenza",
    "telefonoResidenza",
    "codiceAslResidenza"
})
public class AggiornamentoProfiloAnagraficoResidenza {

    protected String codiceStatoResidenza;
    protected String codiceComuneResidenza;
    protected String descrizioneComuneResidenza;
    protected String indirizzoResidenza;
    protected String numerocivicoResidenza;
    @XmlElement(name = "CAPresidenza")
    protected String caPresidenza;
    @XmlElement(name = "TelefonoResidenza")
    protected String telefonoResidenza;
    @XmlElement(name = "CodiceAslResidenza")
    protected String codiceAslResidenza;

    /**
     * Recupera il valore della proprietà codiceStatoResidenza.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceStatoResidenza() {
        return codiceStatoResidenza;
    }

    /**
     * Imposta il valore della proprietà codiceStatoResidenza.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceStatoResidenza(String value) {
        this.codiceStatoResidenza = value;
    }

    /**
     * Recupera il valore della proprietà codiceComuneResidenza.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceComuneResidenza() {
        return codiceComuneResidenza;
    }

    /**
     * Imposta il valore della proprietà codiceComuneResidenza.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceComuneResidenza(String value) {
        this.codiceComuneResidenza = value;
    }

    /**
     * Recupera il valore della proprietà descrizioneComuneResidenza.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescrizioneComuneResidenza() {
        return descrizioneComuneResidenza;
    }

    /**
     * Imposta il valore della proprietà descrizioneComuneResidenza.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescrizioneComuneResidenza(String value) {
        this.descrizioneComuneResidenza = value;
    }

    /**
     * Recupera il valore della proprietà indirizzoResidenza.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIndirizzoResidenza() {
        return indirizzoResidenza;
    }

    /**
     * Imposta il valore della proprietà indirizzoResidenza.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIndirizzoResidenza(String value) {
        this.indirizzoResidenza = value;
    }

    /**
     * Recupera il valore della proprietà numerocivicoResidenza.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumerocivicoResidenza() {
        return numerocivicoResidenza;
    }

    /**
     * Imposta il valore della proprietà numerocivicoResidenza.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumerocivicoResidenza(String value) {
        this.numerocivicoResidenza = value;
    }

    /**
     * Recupera il valore della proprietà caPresidenza.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCAPresidenza() {
        return caPresidenza;
    }

    /**
     * Imposta il valore della proprietà caPresidenza.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCAPresidenza(String value) {
        this.caPresidenza = value;
    }

    /**
     * Recupera il valore della proprietà telefonoResidenza.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTelefonoResidenza() {
        return telefonoResidenza;
    }

    /**
     * Imposta il valore della proprietà telefonoResidenza.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTelefonoResidenza(String value) {
        this.telefonoResidenza = value;
    }

    /**
     * Recupera il valore della proprietà codiceAslResidenza.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceAslResidenza() {
        return codiceAslResidenza;
    }

    /**
     * Imposta il valore della proprietà codiceAslResidenza.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceAslResidenza(String value) {
        this.codiceAslResidenza = value;
    }

}
