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
 * <p>Classe Java per AggiornamentoProfiloAnagraficoRes complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="AggiornamentoProfiloAnagraficoRes">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="header" type="{http://SetAggiornamentoProfiloAnagrafico.central.services.auraws.aura.csi.it}AggiornamentoProfiloAnagraficoResHeader"/>
 *         &lt;element name="codiceRitorno">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="1"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="body" type="{http://SetAggiornamentoProfiloAnagrafico.central.services.auraws.aura.csi.it}AggiornamentoProfiloAnagraficoResBody" minOccurs="0"/>
 *         &lt;element name="footer" type="{http://SetAggiornamentoProfiloAnagrafico.central.services.auraws.aura.csi.it}AggiornamentoProfiloAnagraficoResFooter" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AggiornamentoProfiloAnagraficoRes", propOrder = {
    "header",
    "codiceRitorno",
    "body",
    "footer",
    "profiloSanitarioBody"
})
@XmlRootElement
public class AggiornamentoProfiloAnagraficoRes {

    @XmlElement(required = true)
    protected AggiornamentoProfiloAnagraficoResHeader header;
    @XmlElement(required = true)
    protected String codiceRitorno;
    protected AggiornamentoProfiloAnagraficoResBody body;
    protected ArrayOfAggiornamentoProfiloAnagraficoResErrore footer;
    protected ProfiloSanitarioResBody profiloSanitarioBody;

    /**
     * Recupera il valore della proprietà header.
     * 
     * @return
     *     possible object is
     *     {@link AggiornamentoProfiloAnagraficoResHeader }
     *     
     */
    public AggiornamentoProfiloAnagraficoResHeader getHeader() {
        return header;
    }

    /**
     * Imposta il valore della proprietà header.
     * 
     * @param value
     *     allowed object is
     *     {@link AggiornamentoProfiloAnagraficoResHeader }
     *     
     */
    public void setHeader(AggiornamentoProfiloAnagraficoResHeader value) {
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
     *     {@link AggiornamentoProfiloAnagraficoResBody }
     *     
     */
    public AggiornamentoProfiloAnagraficoResBody getBody() {
        return body;
    }

    /**
     * Imposta il valore della proprietà body.
     * 
     * @param value
     *     allowed object is
     *     {@link AggiornamentoProfiloAnagraficoResBody }
     *     
     */
    public void setBody(AggiornamentoProfiloAnagraficoResBody value) {
        this.body = value;
    }

    /**
     * Recupera il valore della proprietà footer.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfAggiornamentoProfiloAnagraficoResErrore }
     *     
     */
    public ArrayOfAggiornamentoProfiloAnagraficoResErrore getFooter() {
        return footer;
    }

    /**
     * Imposta il valore della proprietà footer.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfAggiornamentoProfiloAnagraficoResErrore }
     *     
     */
    public void setFooter(ArrayOfAggiornamentoProfiloAnagraficoResErrore value) {
        this.footer = value;
    }

}
