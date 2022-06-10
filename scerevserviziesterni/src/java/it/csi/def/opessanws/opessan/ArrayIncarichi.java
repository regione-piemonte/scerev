/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.def.opessanws.opessan;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java per ArrayIncarichi complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ArrayIncarichi">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="dataInizio" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="brancaSpec" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="unitaOperativa" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="totOreSett" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="dataFine" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="flagAnnullato" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayIncarichi", propOrder = {
    "dataInizio",
    "brancaSpec",
    "unitaOperativa",
    "totOreSett",
    "dataFine",
    "flagAnnullato"
})
public class ArrayIncarichi {

    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataInizio;
    protected String brancaSpec;
    @XmlElement(required = true)
    protected String unitaOperativa;
    protected int totOreSett;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataFine;
    protected boolean flagAnnullato;

    /**
     * Recupera il valore della proprietà dataInizio.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataInizio() {
        return dataInizio;
    }

    /**
     * Imposta il valore della proprietà dataInizio.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataInizio(XMLGregorianCalendar value) {
        this.dataInizio = value;
    }

    /**
     * Recupera il valore della proprietà brancaSpec.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBrancaSpec() {
        return brancaSpec;
    }

    /**
     * Imposta il valore della proprietà brancaSpec.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBrancaSpec(String value) {
        this.brancaSpec = value;
    }

    /**
     * Recupera il valore della proprietà unitaOperativa.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnitaOperativa() {
        return unitaOperativa;
    }

    /**
     * Imposta il valore della proprietà unitaOperativa.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnitaOperativa(String value) {
        this.unitaOperativa = value;
    }

    /**
     * Recupera il valore della proprietà totOreSett.
     * 
     */
    public int getTotOreSett() {
        return totOreSett;
    }

    /**
     * Imposta il valore della proprietà totOreSett.
     * 
     */
    public void setTotOreSett(int value) {
        this.totOreSett = value;
    }

    /**
     * Recupera il valore della proprietà dataFine.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataFine() {
        return dataFine;
    }

    /**
     * Imposta il valore della proprietà dataFine.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataFine(XMLGregorianCalendar value) {
        this.dataFine = value;
    }

    /**
     * Recupera il valore della proprietà flagAnnullato.
     * 
     */
    public boolean isFlagAnnullato() {
        return flagAnnullato;
    }

    /**
     * Imposta il valore della proprietà flagAnnullato.
     * 
     */
    public void setFlagAnnullato(boolean value) {
        this.flagAnnullato = value;
    }

}
