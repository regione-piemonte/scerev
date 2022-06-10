/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.scerev.scerevserviziesterni.ensemble.interrogamefscerev;

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
 *         &lt;element name="InterrogaMefScerevResp" type="{http://InterrogaMefScerev.central.services.auraws.aura.csi.it}InterrogaMefScerevResult"/>
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
    "interrogaMefScerevResp"
})
@XmlRootElement(name = "InterrogaMefScerevResponse")
public class InterrogaMefScerevResponse {

    @XmlElement(name = "InterrogaMefScerevResp", required = true)
    protected InterrogaMefScerevResult interrogaMefScerevResp;

    /**
     * Recupera il valore della proprietà interrogaMefScerevResp.
     * 
     * @return
     *     possible object is
     *     {@link InterrogaMefScerevResult }
     *     
     */
    public InterrogaMefScerevResult getInterrogaMefScerevResp() {
        return interrogaMefScerevResp;
    }

    /**
     * Imposta il valore della proprietà interrogaMefScerevResp.
     * 
     * @param value
     *     allowed object is
     *     {@link InterrogaMefScerevResult }
     *     
     */
    public void setInterrogaMefScerevResp(InterrogaMefScerevResult value) {
        this.interrogaMefScerevResp = value;
    }

}
