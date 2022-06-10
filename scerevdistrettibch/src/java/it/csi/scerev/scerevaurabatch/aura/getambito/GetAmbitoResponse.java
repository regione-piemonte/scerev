/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.scerev.scerevaurabatch.aura.getambito;

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
 *         &lt;element name="GetAmbitoResponseRec" type="{http://GetAmbito.central.services.auraws.aura.csi.it}GetAmbitoResp3"/>
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
    "getAmbitoResponseRec"
})
@XmlRootElement(name = "GetAmbitoResponse")
public class GetAmbitoResponse {

    @XmlElement(name = "GetAmbitoResponseRec", required = true)
    protected GetAmbitoResp3 getAmbitoResponseRec;

    /**
     * Recupera il valore della proprietà getAmbitoResponseRec.
     * 
     * @return
     *     possible object is
     *     {@link GetAmbitoResp3 }
     *     
     */
    public GetAmbitoResp3 getGetAmbitoResponseRec() {
        return getAmbitoResponseRec;
    }

    /**
     * Imposta il valore della proprietà getAmbitoResponseRec.
     * 
     * @param value
     *     allowed object is
     *     {@link GetAmbitoResp3 }
     *     
     */
    public void setGetAmbitoResponseRec(GetAmbitoResp3 value) {
        this.getAmbitoResponseRec = value;
    }

}
