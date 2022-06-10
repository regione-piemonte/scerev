/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.scerev.scerevserviziesterni.aura.setprofilosanitario;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per ProfiloSanitarioRes complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ProfiloSanitarioRes">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="header" type="{http://SetProfiloSanitario.central.services.auraws.aura.csi.it}ProfiloSanitarioResHeader"/>
 *         &lt;element name="codiceRitorno">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="1"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="body" type="{http://SetProfiloSanitario.central.services.auraws.aura.csi.it}ProfiloSanitarioResBody" minOccurs="0"/>
 *         &lt;element name="footer" type="{http://SetProfiloSanitario.central.services.auraws.aura.csi.it}ProfiloSanitarioResFooter" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProfiloSanitarioRes", propOrder = {
    "header",
    "codiceRitorno",
    "body",
    "footer"
})
@XmlRootElement
public class ProfiloSanitarioRes {

    @XmlElement(required = true)
    protected ProfiloSanitarioResHeader header;
    @XmlElement(required = true)
    protected String codiceRitorno;
    protected ProfiloSanitarioResBody body;
    protected ProfiloSanitarioResFooter footer;

    /**
     * Recupera il valore della proprietà header.
     * 
     * @return
     *     possible object is
     *     {@link ProfiloSanitarioResHeader }
     *     
     */
    public ProfiloSanitarioResHeader getHeader() {
        return header;
    }

    /**
     * Imposta il valore della proprietà header.
     * 
     * @param value
     *     allowed object is
     *     {@link ProfiloSanitarioResHeader }
     *     
     */
    public void setHeader(ProfiloSanitarioResHeader value) {
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
     *     {@link ProfiloSanitarioResBody }
     *     
     */
    public ProfiloSanitarioResBody getBody() {
        return body;
    }

    /**
     * Imposta il valore della proprietà body.
     * 
     * @param value
     *     allowed object is
     *     {@link ProfiloSanitarioResBody }
     *     
     */
    public void setBody(ProfiloSanitarioResBody value) {
        this.body = value;
    }

    /**
     * Recupera il valore della proprietà footer.
     * 
     * @return
     *     possible object is
     *     {@link ProfiloSanitarioResFooter }
     *     
     */
    public ProfiloSanitarioResFooter getFooter() {
        return footer;
    }

    /**
     * Imposta il valore della proprietà footer.
     * 
     * @param value
     *     allowed object is
     *     {@link ProfiloSanitarioResFooter }
     *     
     */
    public void setFooter(ProfiloSanitarioResFooter value) {
        this.footer = value;
    }

}
