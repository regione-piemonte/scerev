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
 * <p>Classe Java per anonymous complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SetProfiloSanitarioResult" type="{http://SetProfiloSanitario.central.services.auraws.aura.csi.it}ProfiloSanitarioRes"/>
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
    "setProfiloSanitarioResult"
})
@XmlRootElement(name = "SetProfiloSanitarioResponse")
public class SetProfiloSanitarioResponse {

    @XmlElement(name = "SetProfiloSanitarioResult", required = true)
    protected ProfiloSanitarioRes setProfiloSanitarioResult;

    /**
     * Recupera il valore della proprietà setProfiloSanitarioResult.
     * 
     * @return
     *     possible object is
     *     {@link ProfiloSanitarioRes }
     *     
     */
    public ProfiloSanitarioRes getSetProfiloSanitarioResult() {
        return setProfiloSanitarioResult;
    }

    /**
     * Imposta il valore della proprietà setProfiloSanitarioResult.
     * 
     * @param value
     *     allowed object is
     *     {@link ProfiloSanitarioRes }
     *     
     */
    public void setSetProfiloSanitarioResult(ProfiloSanitarioRes value) {
        this.setProfiloSanitarioResult = value;
    }

}
