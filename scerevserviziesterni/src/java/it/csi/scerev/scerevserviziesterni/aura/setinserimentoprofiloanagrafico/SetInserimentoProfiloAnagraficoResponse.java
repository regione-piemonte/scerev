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
 *         &lt;element name="SetInserimentoProfiloAnagraficoResult" type="{http://SetInserimentoProfiloAnagrafico.central.services.auraws.aura.csi.it}InserimentoProfiloAnagraficoRes"/>
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
    "setInserimentoProfiloAnagraficoResult"
})
@XmlRootElement(name = "SetInserimentoProfiloAnagraficoResponse")
public class SetInserimentoProfiloAnagraficoResponse {

    @XmlElement(name = "SetInserimentoProfiloAnagraficoResult", required = true)
    protected InserimentoProfiloAnagraficoRes setInserimentoProfiloAnagraficoResult;

    /**
     * Recupera il valore della proprietà setInserimentoProfiloAnagraficoResult.
     * 
     * @return
     *     possible object is
     *     {@link InserimentoProfiloAnagraficoRes }
     *     
     */
    public InserimentoProfiloAnagraficoRes getSetInserimentoProfiloAnagraficoResult() {
        return setInserimentoProfiloAnagraficoResult;
    }

    /**
     * Imposta il valore della proprietà setInserimentoProfiloAnagraficoResult.
     * 
     * @param value
     *     allowed object is
     *     {@link InserimentoProfiloAnagraficoRes }
     *     
     */
    public void setSetInserimentoProfiloAnagraficoResult(InserimentoProfiloAnagraficoRes value) {
        this.setInserimentoProfiloAnagraficoResult = value;
    }

}
