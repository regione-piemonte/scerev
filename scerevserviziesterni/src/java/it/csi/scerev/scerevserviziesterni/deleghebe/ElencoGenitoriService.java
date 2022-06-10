/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.scerev.scerevserviziesterni.deleghebe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per elencoGenitoriService complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="elencoGenitoriService">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="elencoGenitore" type="{http://deleghebe.csi.it/}elencoGenitori" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "elencoGenitoriService", propOrder = {
    "elencoGenitore"
})
public class ElencoGenitoriService {

    protected ElencoGenitori elencoGenitore;

    /**
     * Recupera il valore della proprietÓ elencoGenitore.
     * 
     * @return
     *     possible object is
     *     {@link ElencoGenitori }
     *     
     */
    public ElencoGenitori getElencoGenitore() {
        return elencoGenitore;
    }

    /**
     * Imposta il valore della proprietÓ elencoGenitore.
     * 
     * @param value
     *     allowed object is
     *     {@link ElencoGenitori }
     *     
     */
    public void setElencoGenitore(ElencoGenitori value) {
        this.elencoGenitore = value;
    }

}
