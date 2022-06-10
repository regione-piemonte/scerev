/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.scerev.scerevserviziesterni.aura.getasldistrettoambito;

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
 *         &lt;element name="ASLDistrettoAmbitoResp" type="{http://GetASLDistrettoAmbito.central.services.auraws.aura.csi.it}ASLDistrettoAmbitoResponse"/>
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
    "aslDistrettoAmbitoResp"
})
@XmlRootElement(name = "GetASLDistrettoAmbitoResponse")
public class GetASLDistrettoAmbitoResponse {

    @XmlElement(name = "ASLDistrettoAmbitoResp", required = true)
    protected ASLDistrettoAmbitoResponse aslDistrettoAmbitoResp;

    /**
     * Recupera il valore della proprietà aslDistrettoAmbitoResp.
     * 
     * @return
     *     possible object is
     *     {@link ASLDistrettoAmbitoResponse }
     *     
     */
    public ASLDistrettoAmbitoResponse getASLDistrettoAmbitoResp() {
        return aslDistrettoAmbitoResp;
    }

    /**
     * Imposta il valore della proprietà aslDistrettoAmbitoResp.
     * 
     * @param value
     *     allowed object is
     *     {@link ASLDistrettoAmbitoResponse }
     *     
     */
    public void setASLDistrettoAmbitoResp(ASLDistrettoAmbitoResponse value) {
        this.aslDistrettoAmbitoResp = value;
    }

}
