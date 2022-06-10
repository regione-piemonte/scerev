/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.scerev.scerevaurabatch.aura.getdistretto;

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
 *         &lt;element name="GetDistrettoResp" type="{http://GetDistretto.central.services.auraws.aura.csi.it}GetDistrettoResult"/>
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
    "getDistrettoResp"
})
@XmlRootElement(name = "GetDistrettoResponse")
public class GetDistrettoResponse {

    @XmlElement(name = "GetDistrettoResp", required = true)
    protected GetDistrettoResult getDistrettoResp;

    /**
     * Recupera il valore della proprietà getDistrettoResp.
     * 
     * @return
     *     possible object is
     *     {@link GetDistrettoResult }
     *     
     */
    public GetDistrettoResult getGetDistrettoResp() {
        return getDistrettoResp;
    }

    /**
     * Imposta il valore della proprietà getDistrettoResp.
     * 
     * @param value
     *     allowed object is
     *     {@link GetDistrettoResult }
     *     
     */
    public void setGetDistrettoResp(GetDistrettoResult value) {
        this.getDistrettoResp = value;
    }

}
