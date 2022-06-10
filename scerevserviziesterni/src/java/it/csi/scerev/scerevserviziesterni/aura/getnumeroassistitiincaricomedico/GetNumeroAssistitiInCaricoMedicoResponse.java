/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.scerev.scerevserviziesterni.aura.getnumeroassistitiincaricomedico;

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
 *         &lt;element name="NumeroAssistitiInCaricoMedicoResp" type="{http://GetNumeroAssistitiInCaricoMedico.central.services.auraws.aura.csi.it}NumeroAssistitiInCaricoMedicoResponse"/>
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
    "numeroAssistitiInCaricoMedicoResp"
})
@XmlRootElement(name = "GetNumeroAssistitiInCaricoMedicoResponse")
public class GetNumeroAssistitiInCaricoMedicoResponse {

    @XmlElement(name = "NumeroAssistitiInCaricoMedicoResp", required = true)
    protected NumeroAssistitiInCaricoMedicoResponse numeroAssistitiInCaricoMedicoResp;

    /**
     * Recupera il valore della proprietà numeroAssistitiInCaricoMedicoResp.
     * 
     * @return
     *     possible object is
     *     {@link NumeroAssistitiInCaricoMedicoResponse }
     *     
     */
    public NumeroAssistitiInCaricoMedicoResponse getNumeroAssistitiInCaricoMedicoResp() {
        return numeroAssistitiInCaricoMedicoResp;
    }

    /**
     * Imposta il valore della proprietà numeroAssistitiInCaricoMedicoResp.
     * 
     * @param value
     *     allowed object is
     *     {@link NumeroAssistitiInCaricoMedicoResponse }
     *     
     */
    public void setNumeroAssistitiInCaricoMedicoResp(NumeroAssistitiInCaricoMedicoResponse value) {
        this.numeroAssistitiInCaricoMedicoResp = value;
    }

}
