/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.scerev.scerevserviziesterni.aura.setprofilosanitario;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per ProfiloSanitario complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ProfiloSanitario">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="header" type="{http://SetProfiloSanitario.central.services.auraws.aura.csi.it}ProfiloSanitarioHeader"/>
 *         &lt;element name="body" type="{http://SetProfiloSanitario.central.services.auraws.aura.csi.it}ProfiloSanitarioBody"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProfiloSanitario", propOrder = {
    "header",
    "body"
})
@XmlRootElement
public class ProfiloSanitario {

    @XmlElement(required = true)
    protected ProfiloSanitarioHeader header;
    @XmlElement(required = true)
    protected ProfiloSanitarioBody body;

    /**
     * Recupera il valore della proprietà header.
     * 
     * @return
     *     possible object is
     *     {@link ProfiloSanitarioHeader }
     *     
     */
    public ProfiloSanitarioHeader getHeader() {
        return header;
    }

    /**
     * Imposta il valore della proprietà header.
     * 
     * @param value
     *     allowed object is
     *     {@link ProfiloSanitarioHeader }
     *     
     */
    public void setHeader(ProfiloSanitarioHeader value) {
        this.header = value;
    }

    /**
     * Recupera il valore della proprietà body.
     * 
     * @return
     *     possible object is
     *     {@link ProfiloSanitarioBody }
     *     
     */
    public ProfiloSanitarioBody getBody() {
        return body;
    }

    /**
     * Imposta il valore della proprietà body.
     * 
     * @param value
     *     allowed object is
     *     {@link ProfiloSanitarioBody }
     *     
     */
    public void setBody(ProfiloSanitarioBody value) {
        this.body = value;
    }

}
