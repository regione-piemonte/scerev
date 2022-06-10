/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.scerev.scerevserviziesterni.aura.setinserimentoprofiloanagrafico;

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
 *         &lt;element name="DatiProfilo" type="{http://SetInserimentoProfiloAnagrafico.central.services.auraws.aura.csi.it}InserimentoProfiloAnagrafico" minOccurs="0"/>
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
@XmlRootElement(name = "SetInserimentoProfiloAnagrafico")
public class SetInserimentoProfiloAnagrafico {

    @XmlElement(name = "DatiProfilo")
    protected InserimentoProfiloAnagrafico datiProfilo;

    /**
     * Recupera il valore della proprietà datiProfilo.
     * 
     * @return
     *     possible object is
     *     {@link InserimentoProfiloAnagrafico }
     *     
     */
    public InserimentoProfiloAnagrafico getDatiProfilo() {
        return datiProfilo;
    }

    /**
     * Imposta il valore della proprietà datiProfilo.
     * 
     * @param value
     *     allowed object is
     *     {@link InserimentoProfiloAnagrafico }
     *     
     */
    public void setDatiProfilo(InserimentoProfiloAnagrafico value) {
        this.datiProfilo = value;
    }

}
