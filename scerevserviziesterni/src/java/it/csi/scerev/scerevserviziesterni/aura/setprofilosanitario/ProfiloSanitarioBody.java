/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.scerev.scerevserviziesterni.aura.setprofilosanitario;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per ProfiloSanitarioBody complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ProfiloSanitarioBody">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idAuraAssistito">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="10"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="cfAuraAssistito">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="16"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="idAuraMedico">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="10"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="CfAuraMedico">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="16"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="TipoMovimento" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="1"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ricongiungimentoFam" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="DatiIscrizioneSanitaria" type="{http://SetProfiloSanitario.central.services.auraws.aura.csi.it}DatiIscrizioneSanitaria" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProfiloSanitarioBody", propOrder = {
    "idAuraAssistito",
    "cfAuraAssistito",
    "idAuraMedico",
    "cfAuraMedico",
    "tipoMovimento",
    "ricongiungimentoFam",
    "datiIscrizioneSanitaria"
})
public class ProfiloSanitarioBody {

    @XmlElement(required = true)
    protected String idAuraAssistito;
    @XmlElement(required = true)
    protected String cfAuraAssistito;
    @XmlElement(required = true)
    protected String idAuraMedico;
    @XmlElement(name = "CfAuraMedico", required = true)
    protected String cfAuraMedico;
    @XmlElement(name = "TipoMovimento")
    protected String tipoMovimento;
    protected Long ricongiungimentoFam;
    @XmlElement(name = "DatiIscrizioneSanitaria")
    protected DatiIscrizioneSanitaria datiIscrizioneSanitaria;

    /**
     * Recupera il valore della proprietà idAuraAssistito.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdAuraAssistito() {
        return idAuraAssistito;
    }

    /**
     * Imposta il valore della proprietà idAuraAssistito.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdAuraAssistito(String value) {
        this.idAuraAssistito = value;
    }

    /**
     * Recupera il valore della proprietà cfAuraAssistito.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCfAuraAssistito() {
        return cfAuraAssistito;
    }

    /**
     * Imposta il valore della proprietà cfAuraAssistito.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCfAuraAssistito(String value) {
        this.cfAuraAssistito = value;
    }

    /**
     * Recupera il valore della proprietà idAuraMedico.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdAuraMedico() {
        return idAuraMedico;
    }

    /**
     * Imposta il valore della proprietà idAuraMedico.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdAuraMedico(String value) {
        this.idAuraMedico = value;
    }

    /**
     * Recupera il valore della proprietà cfAuraMedico.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCfAuraMedico() {
        return cfAuraMedico;
    }

    /**
     * Imposta il valore della proprietà cfAuraMedico.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCfAuraMedico(String value) {
        this.cfAuraMedico = value;
    }

    /**
     * Recupera il valore della proprietà tipoMovimento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoMovimento() {
        return tipoMovimento;
    }

    /**
     * Imposta il valore della proprietà tipoMovimento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoMovimento(String value) {
        this.tipoMovimento = value;
    }

    /**
     * Recupera il valore della proprietà ricongiungimentoFam.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getRicongiungimentoFam() {
        return ricongiungimentoFam;
    }

    /**
     * Imposta il valore della proprietà ricongiungimentoFam.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setRicongiungimentoFam(Long value) {
        this.ricongiungimentoFam = value;
    }

    /**
     * Recupera il valore della proprietà datiIscrizioneSanitaria.
     * 
     * @return
     *     possible object is
     *     {@link DatiIscrizioneSanitaria }
     *     
     */
    public DatiIscrizioneSanitaria getDatiIscrizioneSanitaria() {
        return datiIscrizioneSanitaria;
    }

    /**
     * Imposta il valore della proprietà datiIscrizioneSanitaria.
     * 
     * @param value
     *     allowed object is
     *     {@link DatiIscrizioneSanitaria }
     *     
     */
    public void setDatiIscrizioneSanitaria(DatiIscrizioneSanitaria value) {
        this.datiIscrizioneSanitaria = value;
    }

}
