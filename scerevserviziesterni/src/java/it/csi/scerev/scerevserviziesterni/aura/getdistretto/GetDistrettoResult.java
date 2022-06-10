/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.scerev.scerevserviziesterni.aura.getdistretto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per GetDistrettoResult complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="GetDistrettoResult">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="codiceEsito" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="256"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Distretti" type="{http://GetDistretto.central.services.auraws.aura.csi.it}ArrayOfDistrettoDettaglio" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetDistrettoResult", propOrder = {
    "codiceEsito",
    "distretti"
})
@XmlRootElement
public class GetDistrettoResult {

    protected String codiceEsito;
    @XmlElement(name = "Distretti")
    protected ArrayOfDistrettoDettaglio distretti;

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
     * Recupera il valore della proprietà distretti.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfDistrettoDettaglio }
     *     
     */
    public ArrayOfDistrettoDettaglio getDistretti() {
        return distretti;
    }

    /**
     * Imposta il valore della proprietà distretti.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfDistrettoDettaglio }
     *     
     */
    public void setDistretti(ArrayOfDistrettoDettaglio value) {
        this.distretti = value;
    }

}
