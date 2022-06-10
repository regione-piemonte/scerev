/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.scerev.scerevserviziesterni.aura.getUltimoMovimentoDiRevoca;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per Response complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="Response">
 *   &lt;complexContent>
 *     &lt;extension base="{http://getUltimoMovimentoDiRevoca.central.services.auraws.aura.csi.it}Ens_Response">
 *       &lt;sequence>
 *         &lt;element name="codiceEsito" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="500"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ResponseDelegate" type="{http://getUltimoMovimentoDiRevoca.central.services.auraws.aura.csi.it}ResponseDelegate" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Response", propOrder = {
    "codiceEsito",
    "responseDelegate"
})
@XmlRootElement
public class Response
    extends EnsResponse
{

    protected String codiceEsito;
    @XmlElement(name = "ResponseDelegate")
    protected ResponseDelegate responseDelegate;

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
     * Recupera il valore della proprietà responseDelegate.
     * 
     * @return
     *     possible object is
     *     {@link ResponseDelegate }
     *     
     */
    public ResponseDelegate getResponseDelegate() {
        return responseDelegate;
    }

    /**
     * Imposta il valore della proprietà responseDelegate.
     * 
     * @param value
     *     allowed object is
     *     {@link ResponseDelegate }
     *     
     */
    public void setResponseDelegate(ResponseDelegate value) {
        this.responseDelegate = value;
    }

}
