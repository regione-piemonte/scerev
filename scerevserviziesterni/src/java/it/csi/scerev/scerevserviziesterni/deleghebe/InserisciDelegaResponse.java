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
 * <p>Classe Java per inserisciDelegaResponse complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="inserisciDelegaResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://deleghebe.csi.it/}serviceResponse">
 *       &lt;sequence>
 *         &lt;element name="delega" type="{http://deleghebe.csi.it/}delega" minOccurs="0" form="unqualified"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "inserisciDelegaResponse", propOrder = {
    "delega"
})
public class InserisciDelegaResponse
    extends ServiceResponse
{

    @XmlElement(namespace = "")
    protected Delega delega;

    /**
     * Recupera il valore della propriet� delega.
     * 
     * @return
     *     possible object is
     *     {@link Delega }
     *     
     */
    public Delega getDelega() {
        return delega;
    }

    /**
     * Imposta il valore della propriet� delega.
     * 
     * @param value
     *     allowed object is
     *     {@link Delega }
     *     
     */
    public void setDelega(Delega value) {
        this.delega = value;
    }

}
