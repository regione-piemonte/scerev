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
 * <p>Classe Java per InserimentoProfiloAnagrafico complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="InserimentoProfiloAnagrafico">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="header" type="{http://SetInserimentoProfiloAnagrafico.central.services.auraws.aura.csi.it}InserimentoProfiloAnagraficoHeader"/>
 *         &lt;element name="body" type="{http://SetInserimentoProfiloAnagrafico.central.services.auraws.aura.csi.it}InserimentoProfiloAnagraficoBody"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InserimentoProfiloAnagrafico", propOrder = {
    "header",
    "body"
})
@XmlRootElement
public class InserimentoProfiloAnagrafico {

    @XmlElement(required = true)
    protected InserimentoProfiloAnagraficoHeader header;
    @XmlElement(required = true)
    protected InserimentoProfiloAnagraficoBody body;

    /**
     * Recupera il valore della proprietà header.
     * 
     * @return
     *     possible object is
     *     {@link InserimentoProfiloAnagraficoHeader }
     *     
     */
    public InserimentoProfiloAnagraficoHeader getHeader() {
        return header;
    }

    /**
     * Imposta il valore della proprietà header.
     * 
     * @param value
     *     allowed object is
     *     {@link InserimentoProfiloAnagraficoHeader }
     *     
     */
    public void setHeader(InserimentoProfiloAnagraficoHeader value) {
        this.header = value;
    }

    /**
     * Recupera il valore della proprietà body.
     * 
     * @return
     *     possible object is
     *     {@link InserimentoProfiloAnagraficoBody }
     *     
     */
    public InserimentoProfiloAnagraficoBody getBody() {
        return body;
    }

    /**
     * Imposta il valore della proprietà body.
     * 
     * @param value
     *     allowed object is
     *     {@link InserimentoProfiloAnagraficoBody }
     *     
     */
    public void setBody(InserimentoProfiloAnagraficoBody value) {
        this.body = value;
    }

}
