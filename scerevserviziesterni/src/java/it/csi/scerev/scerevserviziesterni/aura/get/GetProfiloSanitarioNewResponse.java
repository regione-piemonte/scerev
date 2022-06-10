/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.scerev.scerevserviziesterni.aura.get;

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
 *         &lt;element name="GetProfiloSanitarioNewResult" type="{http://AnagrafeSanitaria.central.services.auraws.aura.csi.it}soggettoAuraNewMsg"/>
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
    "getProfiloSanitarioNewResult"
})
@XmlRootElement(name = "GetProfiloSanitarioNewResponse")
public class GetProfiloSanitarioNewResponse {

    @XmlElement(name = "GetProfiloSanitarioNewResult", required = true)
    protected SoggettoAuraNewMsg getProfiloSanitarioNewResult;

    /**
     * Recupera il valore della proprietà getProfiloSanitarioNewResult.
     * 
     * @return
     *     possible object is
     *     {@link SoggettoAuraNewMsg }
     *     
     */
    public SoggettoAuraNewMsg getGetProfiloSanitarioNewResult() {
        return getProfiloSanitarioNewResult;
    }

    /**
     * Imposta il valore della proprietà getProfiloSanitarioNewResult.
     * 
     * @param value
     *     allowed object is
     *     {@link SoggettoAuraNewMsg }
     *     
     */
    public void setGetProfiloSanitarioNewResult(SoggettoAuraNewMsg value) {
        this.getProfiloSanitarioNewResult = value;
    }

}
