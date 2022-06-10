/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.scerev.scadenzaassistenza.deleghebe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per getDelegatiService complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="getDelegatiService">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="getDelegati" type="{http://deleghebe.csi.it/}getDelegati" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getDelegatiService", propOrder = {
    "getDelegati"
})
public class GetDelegatiService {

    protected GetDelegati getDelegati;

    /**
     * Recupera il valore della proprieta getDelegati.
     * 
     * @return
     *     possible object is
     *     {@link GetDelegati }
     *     
     */
    public GetDelegati getGetDelegati() {
        return getDelegati;
    }

    /**
     * Imposta il valore della proprieta getDelegati.
     * 
     * @param value
     *     allowed object is
     *     {@link GetDelegati }
     *     
     */
    public void setGetDelegati(GetDelegati value) {
        this.getDelegati = value;
    }

}
