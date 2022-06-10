/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.scerev.scerevserviziesterni.aura.getdistretto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java per DistrettoDettaglio complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="DistrettoDettaglio">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IdDistretto" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="Codice" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Descrizione" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CodiceAsl" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="InizioValidita" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="FineValidita" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DistrettoDettaglio", propOrder = {
    "idDistretto",
    "codice",
    "descrizione",
    "codiceAsl",
    "inizioValidita",
    "fineValidita"
})
public class DistrettoDettaglio {

    @XmlElement(name = "IdDistretto")
    protected long idDistretto;
    @XmlElement(name = "Codice", required = true)
    protected String codice;
    @XmlElement(name = "Descrizione", required = true)
    protected String descrizione;
    @XmlElement(name = "CodiceAsl")
    protected long codiceAsl;
    @XmlElement(name = "InizioValidita", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar inizioValidita;
    @XmlElement(name = "FineValidita")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fineValidita;

    /**
     * Recupera il valore della proprietà idDistretto.
     * 
     */
    public long getIdDistretto() {
        return idDistretto;
    }

    /**
     * Imposta il valore della proprietà idDistretto.
     * 
     */
    public void setIdDistretto(long value) {
        this.idDistretto = value;
    }

    /**
     * Recupera il valore della proprietà codice.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodice() {
        return codice;
    }

    /**
     * Imposta il valore della proprietà codice.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodice(String value) {
        this.codice = value;
    }

    /**
     * Recupera il valore della proprietà descrizione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescrizione() {
        return descrizione;
    }

    /**
     * Imposta il valore della proprietà descrizione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescrizione(String value) {
        this.descrizione = value;
    }

    /**
     * Recupera il valore della proprietà codiceAsl.
     * 
     */
    public long getCodiceAsl() {
        return codiceAsl;
    }

    /**
     * Imposta il valore della proprietà codiceAsl.
     * 
     */
    public void setCodiceAsl(long value) {
        this.codiceAsl = value;
    }

    /**
     * Recupera il valore della proprietà inizioValidita.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getInizioValidita() {
        return inizioValidita;
    }

    /**
     * Imposta il valore della proprietà inizioValidita.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setInizioValidita(XMLGregorianCalendar value) {
        this.inizioValidita = value;
    }

    /**
     * Recupera il valore della proprietà fineValidita.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFineValidita() {
        return fineValidita;
    }

    /**
     * Imposta il valore della proprietà fineValidita.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFineValidita(XMLGregorianCalendar value) {
        this.fineValidita = value;
    }

}
