/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.scerev.scerevserviziesterni.deleghebe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per inserisciCittadino complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="inserisciCittadino">
 *   &lt;complexContent>
 *     &lt;extension base="{http://deleghebe.csi.it/}serviceRequest">
 *       &lt;sequence>
 *         &lt;element name="cittadino" type="{http://deleghebe.csi.it/}cittadino" minOccurs="0" form="unqualified"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "inserisciCittadino", propOrder = {
    "cittadino"
})
public class InserisciCittadino
    extends ServiceRequest
{

    @XmlElement(namespace = "")
    protected Cittadino cittadino;

    /**
     * Recupera il valore della propriet� cittadino.
     * 
     * @return
     *     possible object is
     *     {@link Cittadino }
     *     
     */
    public Cittadino getCittadino() {
        return cittadino;
    }

    /**
     * Imposta il valore della propriet� cittadino.
     * 
     * @param value
     *     allowed object is
     *     {@link Cittadino }
     *     
     */
    public void setCittadino(Cittadino value) {
        this.cittadino = value;
    }

}
