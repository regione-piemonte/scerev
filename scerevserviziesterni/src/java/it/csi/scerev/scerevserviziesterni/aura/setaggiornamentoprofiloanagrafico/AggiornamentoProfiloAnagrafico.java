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
 * <p>Classe Java per AggiornamentoProfiloAnagrafico complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="AggiornamentoProfiloAnagrafico">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="header" type="{http://SetAggiornamentoProfiloAnagrafico.central.services.auraws.aura.csi.it}AggiornamentoProfiloAnagraficoHeader"/>
 *         &lt;element name="body" type="{http://SetAggiornamentoProfiloAnagrafico.central.services.auraws.aura.csi.it}AggiornamentoProfiloAnagraficoBody"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AggiornamentoProfiloAnagrafico", propOrder = {
    "header",
    "body"
})
@XmlRootElement
public class AggiornamentoProfiloAnagrafico {

    @XmlElement(required = true)
    protected AggiornamentoProfiloAnagraficoHeader header;
    @XmlElement(required = true)
    protected AggiornamentoProfiloAnagraficoBody body;

    /**
     * Recupera il valore della propriet� header.
     * 
     * @return
     *     possible object is
     *     {@link AggiornamentoProfiloAnagraficoHeader }
     *     
     */
    public AggiornamentoProfiloAnagraficoHeader getHeader() {
        return header;
    }

    /**
     * Imposta il valore della propriet� header.
     * 
     * @param value
     *     allowed object is
     *     {@link AggiornamentoProfiloAnagraficoHeader }
     *     
     */
    public void setHeader(AggiornamentoProfiloAnagraficoHeader value) {
        this.header = value;
    }

    /**
     * Recupera il valore della propriet� body.
     * 
     * @return
     *     possible object is
     *     {@link AggiornamentoProfiloAnagraficoBody }
     *     
     */
    public AggiornamentoProfiloAnagraficoBody getBody() {
        return body;
    }

    /**
     * Imposta il valore della propriet� body.
     * 
     * @param value
     *     allowed object is
     *     {@link AggiornamentoProfiloAnagraficoBody }
     *     
     */
    public void setBody(AggiornamentoProfiloAnagraficoBody value) {
        this.body = value;
    }

}
