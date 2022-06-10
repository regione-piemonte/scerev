/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.scerev.scerevserviziesterni.aura.getnumeroassistitiincaricomedico;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per NumeroAssistitiInCaricoMedicoResponse complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="NumeroAssistitiInCaricoMedicoResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="codiceEsito" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="1000"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="assistitiNetti" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="assistitiTemporanei" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="assistitiNeonati" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="assistitiUltra14" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="assistitiExtraComunitari" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="assistitiDomiciliati" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="assistitiDomiciliatiComunitari" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NumeroAssistitiInCaricoMedicoResponse", propOrder = {
    "codiceEsito",
    "assistitiNetti",
    "assistitiTemporanei",
    "assistitiNeonati",
    "assistitiUltra14",
    "assistitiExtraComunitari",
    "assistitiDomiciliati",
    "assistitiDomiciliatiComunitari"
})
@XmlRootElement
public class NumeroAssistitiInCaricoMedicoResponse {

    protected String codiceEsito;
    protected Long assistitiNetti;
    protected Long assistitiTemporanei;
    protected Long assistitiNeonati;
    protected Long assistitiUltra14;
    protected Long assistitiExtraComunitari;
    protected Long assistitiDomiciliati;
    protected Long assistitiDomiciliatiComunitari;

    /**
     * Recupera il valore della proprietà codiceEsito.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceEsito() {
        return codiceEsito;
    }

    /**
     * Imposta il valore della proprietà codiceEsito.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceEsito(String value) {
        this.codiceEsito = value;
    }

    /**
     * Recupera il valore della proprietà assistitiNetti.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getAssistitiNetti() {
        return assistitiNetti;
    }

    /**
     * Imposta il valore della proprietà assistitiNetti.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setAssistitiNetti(Long value) {
        this.assistitiNetti = value;
    }

    /**
     * Recupera il valore della proprietà assistitiTemporanei.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getAssistitiTemporanei() {
        return assistitiTemporanei;
    }

    /**
     * Imposta il valore della proprietà assistitiTemporanei.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setAssistitiTemporanei(Long value) {
        this.assistitiTemporanei = value;
    }

    /**
     * Recupera il valore della proprietà assistitiNeonati.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getAssistitiNeonati() {
        return assistitiNeonati;
    }

    /**
     * Imposta il valore della proprietà assistitiNeonati.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setAssistitiNeonati(Long value) {
        this.assistitiNeonati = value;
    }

    /**
     * Recupera il valore della proprietà assistitiUltra14.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getAssistitiUltra14() {
        return assistitiUltra14;
    }

    /**
     * Imposta il valore della proprietà assistitiUltra14.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setAssistitiUltra14(Long value) {
        this.assistitiUltra14 = value;
    }

    /**
     * Recupera il valore della proprietà assistitiExtraComunitari.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getAssistitiExtraComunitari() {
        return assistitiExtraComunitari;
    }

    /**
     * Imposta il valore della proprietà assistitiExtraComunitari.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setAssistitiExtraComunitari(Long value) {
        this.assistitiExtraComunitari = value;
    }

    /**
     * Recupera il valore della proprietà assistitiDomiciliati.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getAssistitiDomiciliati() {
        return assistitiDomiciliati;
    }

    /**
     * Imposta il valore della proprietà assistitiDomiciliati.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setAssistitiDomiciliati(Long value) {
        this.assistitiDomiciliati = value;
    }

    /**
     * Recupera il valore della proprietà assistitiDomiciliatiComunitari.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getAssistitiDomiciliatiComunitari() {
        return assistitiDomiciliatiComunitari;
    }

    /**
     * Imposta il valore della proprietà assistitiDomiciliatiComunitari.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setAssistitiDomiciliatiComunitari(Long value) {
        this.assistitiDomiciliatiComunitari = value;
    }

}
