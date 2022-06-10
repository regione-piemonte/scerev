/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.scerev.scadenzaassistenza.deleghebe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per rinunciaDelegatoService complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="rinunciaDelegatoService">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="rinunciaDelegato" type="{http://deleghebe.csi.it/}rinunciaDelegato" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "rinunciaDelegatoService", propOrder = {
    "rinunciaDelegato"
})
public class RinunciaDelegatoService {

    protected RinunciaDelegato rinunciaDelegato;

    /**
     * Recupera il valore della proprieta rinunciaDelegato.
     * 
     * @return
     *     possible object is
     *     {@link RinunciaDelegato }
     *     
     */
    public RinunciaDelegato getRinunciaDelegato() {
        return rinunciaDelegato;
    }

    /**
     * Imposta il valore della proprieta rinunciaDelegato.
     * 
     * @param value
     *     allowed object is
     *     {@link RinunciaDelegato }
     *     
     */
    public void setRinunciaDelegato(RinunciaDelegato value) {
        this.rinunciaDelegato = value;
    }

}
