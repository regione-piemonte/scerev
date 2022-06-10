/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.scerev.scerevserviziesterni.aura.setaggiornamentoprofiloanagrafico;

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
 *         &lt;element name="DatiProfilo" type="{http://SetAggiornamentoProfiloAnagrafico.central.services.auraws.aura.csi.it}AggiornamentoProfiloAnagrafico" minOccurs="0"/>
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
    "datiProfilo"
})
@XmlRootElement(name = "SetAggiornamentoProfiloAnagrafico")
public class SetAggiornamentoProfiloAnagrafico {

    @XmlElement(name = "DatiProfilo")
    protected AggiornamentoProfiloAnagrafico datiProfilo;

    /**
     * Recupera il valore della proprietà datiProfilo.
     * 
     * @return
     *     possible object is
     *     {@link AggiornamentoProfiloAnagrafico }
     *     
     */
    public AggiornamentoProfiloAnagrafico getDatiProfilo() {
        return datiProfilo;
    }

    /**
     * Imposta il valore della proprietà datiProfilo.
     * 
     * @param value
     *     allowed object is
     *     {@link AggiornamentoProfiloAnagrafico }
     *     
     */
    public void setDatiProfilo(AggiornamentoProfiloAnagrafico value) {
        this.datiProfilo = value;
    }

}
