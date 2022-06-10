/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.scerev.scerevserviziesterni.aura.getambito;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per GetAmbitoResp3 complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="GetAmbitoResp3">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="codiceEsito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Ambiti" type="{http://GetAmbito.central.services.auraws.aura.csi.it}ArrayOfAmbitoDettaglio2" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetAmbitoResp3", propOrder = {
    "codiceEsito",
    "ambiti"
})
@XmlRootElement
public class GetAmbitoResp3 {

    protected String codiceEsito;
    @XmlElement(name = "Ambiti")
    protected ArrayOfAmbitoDettaglio2 ambiti;

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
     * Recupera il valore della proprietà ambiti.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfAmbitoDettaglio2 }
     *     
     */
    public ArrayOfAmbitoDettaglio2 getAmbiti() {
        return ambiti;
    }

    /**
     * Imposta il valore della proprietà ambiti.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfAmbitoDettaglio2 }
     *     
     */
    public void setAmbiti(ArrayOfAmbitoDettaglio2 value) {
        this.ambiti = value;
    }

}
