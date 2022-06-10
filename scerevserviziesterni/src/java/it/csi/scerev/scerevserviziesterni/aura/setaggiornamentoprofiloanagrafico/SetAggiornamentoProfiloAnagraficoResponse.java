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
 *         &lt;element name="SetAggiornamentoProfiloAnagraficoResult" type="{http://SetAggiornamentoProfiloAnagrafico.central.services.auraws.aura.csi.it}AggiornamentoProfiloAnagraficoRes"/>
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
    "setAggiornamentoProfiloAnagraficoResult"
})
@XmlRootElement(name = "SetAggiornamentoProfiloAnagraficoResponse")
public class SetAggiornamentoProfiloAnagraficoResponse {

    @XmlElement(name = "SetAggiornamentoProfiloAnagraficoResult", required = true)
    protected AggiornamentoProfiloAnagraficoRes setAggiornamentoProfiloAnagraficoResult;

    /**
     * Recupera il valore della proprietà setAggiornamentoProfiloAnagraficoResult.
     * 
     * @return
     *     possible object is
     *     {@link AggiornamentoProfiloAnagraficoRes }
     *     
     */
    public AggiornamentoProfiloAnagraficoRes getSetAggiornamentoProfiloAnagraficoResult() {
        return setAggiornamentoProfiloAnagraficoResult;
    }

    /**
     * Imposta il valore della proprietà setAggiornamentoProfiloAnagraficoResult.
     * 
     * @param value
     *     allowed object is
     *     {@link AggiornamentoProfiloAnagraficoRes }
     *     
     */
    public void setSetAggiornamentoProfiloAnagraficoResult(AggiornamentoProfiloAnagraficoRes value) {
        this.setAggiornamentoProfiloAnagraficoResult = value;
    }

}
