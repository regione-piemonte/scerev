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
 * <p>Classe Java per AggiornamentoProfiloAnagraficoAltreInfoDett complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="AggiornamentoProfiloAnagraficoAltreInfoDett">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CodInformazione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descInformazione" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="100"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ValInformazione" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="100"/>
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
@XmlType(name = "AggiornamentoProfiloAnagraficoAltreInfoDett", propOrder = {
    "codInformazione",
    "descInformazione",
    "valInformazione"
})
public class AggiornamentoProfiloAnagraficoAltreInfoDett {

    @XmlElement(name = "CodInformazione")
    protected String codInformazione;
    protected String descInformazione;
    @XmlElement(name = "ValInformazione")
    protected String valInformazione;

    /**
     * Recupera il valore della proprietà codInformazione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodInformazione() {
        return codInformazione;
    }

    /**
     * Imposta il valore della proprietà codInformazione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodInformazione(String value) {
        this.codInformazione = value;
    }

    /**
     * Recupera il valore della proprietà descInformazione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescInformazione() {
        return descInformazione;
    }

    /**
     * Imposta il valore della proprietà descInformazione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescInformazione(String value) {
        this.descInformazione = value;
    }

    /**
     * Recupera il valore della proprietà valInformazione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValInformazione() {
        return valInformazione;
    }

    /**
     * Imposta il valore della proprietà valInformazione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValInformazione(String value) {
        this.valInformazione = value;
    }

}
