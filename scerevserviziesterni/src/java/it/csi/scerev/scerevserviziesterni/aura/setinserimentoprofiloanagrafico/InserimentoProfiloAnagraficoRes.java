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
 * <p>Classe Java per InserimentoProfiloAnagraficoRes complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="InserimentoProfiloAnagraficoRes">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="header" type="{http://SetInserimentoProfiloAnagrafico.central.services.auraws.aura.csi.it}InserimentoProfiloAnagraficoResHeader"/>
 *         &lt;element name="codiceRitorno">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="1"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="body" type="{http://SetInserimentoProfiloAnagrafico.central.services.auraws.aura.csi.it}InserimentoProfiloAnagraficoResBody" minOccurs="0"/>
 *         &lt;element name="footer" type="{http://SetInserimentoProfiloAnagrafico.central.services.auraws.aura.csi.it}InserimentoProfiloAnagraficoResFooter" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InserimentoProfiloAnagraficoRes", propOrder = {
    "header",
    "codiceRitorno",
    "body",
    "footer"
})
@XmlRootElement
public class InserimentoProfiloAnagraficoRes {

    @XmlElement(required = true)
    protected InserimentoProfiloAnagraficoResHeader header;
    @XmlElement(required = true)
    protected String codiceRitorno;
    protected InserimentoProfiloAnagraficoResBody body;
    protected InserimentoProfiloAnagraficoResFooter footer;

    /**
     * Recupera il valore della proprietà header.
     * 
     * @return
     *     possible object is
     *     {@link InserimentoProfiloAnagraficoResHeader }
     *     
     */
    public InserimentoProfiloAnagraficoResHeader getHeader() {
        return header;
    }

    /**
     * Imposta il valore della proprietà header.
     * 
     * @param value
     *     allowed object is
     *     {@link InserimentoProfiloAnagraficoResHeader }
     *     
     */
    public void setHeader(InserimentoProfiloAnagraficoResHeader value) {
        this.header = value;
    }

    /**
     * Recupera il valore della proprietà codiceRitorno.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceRitorno() {
        return codiceRitorno;
    }

    /**
     * Imposta il valore della proprietà codiceRitorno.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceRitorno(String value) {
        this.codiceRitorno = value;
    }

    /**
     * Recupera il valore della proprietà body.
     * 
     * @return
     *     possible object is
     *     {@link InserimentoProfiloAnagraficoResBody }
     *     
     */
    public InserimentoProfiloAnagraficoResBody getBody() {
        return body;
    }

    /**
     * Imposta il valore della proprietà body.
     * 
     * @param value
     *     allowed object is
     *     {@link InserimentoProfiloAnagraficoResBody }
     *     
     */
    public void setBody(InserimentoProfiloAnagraficoResBody value) {
        this.body = value;
    }

    /**
     * Recupera il valore della proprietà footer.
     * 
     * @return
     *     possible object is
     *     {@link InserimentoProfiloAnagraficoResFooter }
     *     
     */
    public InserimentoProfiloAnagraficoResFooter getFooter() {
        return footer;
    }

    /**
     * Imposta il valore della proprietà footer.
     * 
     * @param value
     *     allowed object is
     *     {@link InserimentoProfiloAnagraficoResFooter }
     *     
     */
    public void setFooter(InserimentoProfiloAnagraficoResFooter value) {
        this.footer = value;
    }

}
