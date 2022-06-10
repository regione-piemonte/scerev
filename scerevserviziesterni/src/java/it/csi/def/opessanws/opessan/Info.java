/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.def.opessanws.opessan;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per Info complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="Info">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="codInformazione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descInformazione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="valInformazione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Info", propOrder = {
    "codInformazione",
    "descInformazione",
    "valInformazione"
})
public class Info {

    protected String codInformazione;
    protected String descInformazione;
    protected String valInformazione;

    /**
     * Recupera il valore della propriet� codInformazione.
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
     * Imposta il valore della propriet� codInformazione.
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
     * Recupera il valore della propriet� descInformazione.
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
     * Imposta il valore della propriet� descInformazione.
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
     * Recupera il valore della propriet� valInformazione.
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
     * Imposta il valore della propriet� valInformazione.
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
