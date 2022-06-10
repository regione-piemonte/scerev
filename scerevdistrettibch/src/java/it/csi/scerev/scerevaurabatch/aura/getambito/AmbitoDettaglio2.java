/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.scerev.scerevaurabatch.aura.getambito;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java per AmbitoDettaglio2 complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="AmbitoDettaglio2">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IdAmbito" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="Codice" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Descrizione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IdDistretto" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="InizioValidita" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
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
@XmlType(name = "AmbitoDettaglio2", propOrder = {
    "idAmbito",
    "codice",
    "descrizione",
    "idDistretto",
    "inizioValidita",
    "fineValidita"
})
public class AmbitoDettaglio2 {

    @XmlElement(name = "IdAmbito")
    protected Long idAmbito;
    @XmlElement(name = "Codice")
    protected String codice;
    @XmlElement(name = "Descrizione")
    protected String descrizione;
    @XmlElement(name = "IdDistretto")
    protected Long idDistretto;
    @XmlElement(name = "InizioValidita")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar inizioValidita;
    @XmlElement(name = "FineValidita")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fineValidita;

    /**
     * Recupera il valore della proprietà idAmbito.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdAmbito() {
        return idAmbito;
    }

    /**
     * Imposta il valore della proprietà idAmbito.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdAmbito(Long value) {
        this.idAmbito = value;
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
     * Recupera il valore della proprietà idDistretto.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdDistretto() {
        return idDistretto;
    }

    /**
     * Imposta il valore della proprietà idDistretto.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdDistretto(Long value) {
        this.idDistretto = value;
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
