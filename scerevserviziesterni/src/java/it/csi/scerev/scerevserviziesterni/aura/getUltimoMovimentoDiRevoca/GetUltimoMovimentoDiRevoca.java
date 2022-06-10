/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.scerev.scerevserviziesterni.aura.getUltimoMovimentoDiRevoca;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element name="pInput" type="{http://getUltimoMovimentoDiRevoca.central.services.auraws.aura.csi.it}Request" minOccurs="0"/>
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
    "pInput"
})
@XmlRootElement(name = "getUltimoMovimentoDiRevoca")
public class GetUltimoMovimentoDiRevoca {

    protected Request pInput;

    /**
     * Recupera il valore della proprietà pInput.
     * 
     * @return
     *     possible object is
     *     {@link Request }
     *     
     */
    public Request getPInput() {
        return pInput;
    }

    /**
     * Imposta il valore della proprietà pInput.
     * 
     * @param value
     *     allowed object is
     *     {@link Request }
     *     
     */
    public void setPInput(Request value) {
        this.pInput = value;
    }

}
