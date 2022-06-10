/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.def.opessanws.opessan;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per DatiSecondari complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="DatiSecondari">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cap" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codComune" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codStato" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descComune" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descStato" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codiceAsl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="indirizzo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numCivico" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="telefono" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="statoResidenza" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DatiSecondari", propOrder = {
    "cap",
    "codComune",
    "codStato",
    "descComune",
    "descStato",
    "codiceAsl",
    "indirizzo",
    "numCivico",
    "telefono",
    "statoResidenza"
})
public class DatiSecondari {

    protected String cap;
    protected String codComune;
    protected String codStato;
    protected String descComune;
    protected String descStato;
    protected String codiceAsl;
    protected String indirizzo;
    protected String numCivico;
    protected String telefono;
    protected Integer statoResidenza;

    /**
     * Recupera il valore della propriet� cap.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCap() {
        return cap;
    }

    /**
     * Imposta il valore della propriet� cap.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCap(String value) {
        this.cap = value;
    }

    /**
     * Recupera il valore della propriet� codComune.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodComune() {
        return codComune;
    }

    /**
     * Imposta il valore della propriet� codComune.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodComune(String value) {
        this.codComune = value;
    }

    /**
     * Recupera il valore della propriet� codStato.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodStato() {
        return codStato;
    }

    /**
     * Imposta il valore della propriet� codStato.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodStato(String value) {
        this.codStato = value;
    }

    /**
     * Recupera il valore della propriet� descComune.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescComune() {
        return descComune;
    }

    /**
     * Imposta il valore della propriet� descComune.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescComune(String value) {
        this.descComune = value;
    }

    /**
     * Recupera il valore della propriet� descStato.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescStato() {
        return descStato;
    }

    /**
     * Imposta il valore della propriet� descStato.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescStato(String value) {
        this.descStato = value;
    }

    /**
     * Recupera il valore della propriet� codiceAsl.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceAsl() {
        return codiceAsl;
    }

    /**
     * Imposta il valore della propriet� codiceAsl.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceAsl(String value) {
        this.codiceAsl = value;
    }

    /**
     * Recupera il valore della propriet� indirizzo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIndirizzo() {
        return indirizzo;
    }

    /**
     * Imposta il valore della propriet� indirizzo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIndirizzo(String value) {
        this.indirizzo = value;
    }

    /**
     * Recupera il valore della propriet� numCivico.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumCivico() {
        return numCivico;
    }

    /**
     * Imposta il valore della propriet� numCivico.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumCivico(String value) {
        this.numCivico = value;
    }

    /**
     * Recupera il valore della propriet� telefono.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Imposta il valore della propriet� telefono.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTelefono(String value) {
        this.telefono = value;
    }

    /**
     * Recupera il valore della propriet� statoResidenza.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getStatoResidenza() {
        return statoResidenza;
    }

    /**
     * Imposta il valore della propriet� statoResidenza.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setStatoResidenza(Integer value) {
        this.statoResidenza = value;
    }

}
