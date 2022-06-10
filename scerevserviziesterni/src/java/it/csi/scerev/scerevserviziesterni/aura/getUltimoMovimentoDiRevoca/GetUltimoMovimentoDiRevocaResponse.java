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
 * <p>Classe Java per anonymous complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="getUltimoMovimentoDiRevocaResult" type="{http://getUltimoMovimentoDiRevoca.central.services.auraws.aura.csi.it}Response"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "getUltimoMovimentoDiRevocaResult"
})
@XmlRootElement(name = "getUltimoMovimentoDiRevocaResponse")
public class GetUltimoMovimentoDiRevocaResponse {

    @XmlElement(required = true)
    protected Response getUltimoMovimentoDiRevocaResult;

    /**
     * Recupera il valore della proprietà getUltimoMovimentoDiRevocaResult.
     * 
     * @return
     *     possible object is
     *     {@link Response }
     *     
     */
    public Response getGetUltimoMovimentoDiRevocaResult() {
        return getUltimoMovimentoDiRevocaResult;
    }

    /**
     * Imposta il valore della proprietà getUltimoMovimentoDiRevocaResult.
     * 
     * @param value
     *     allowed object is
     *     {@link Response }
     *     
     */
    public void setGetUltimoMovimentoDiRevocaResult(Response value) {
        this.getUltimoMovimentoDiRevocaResult = value;
    }

}
