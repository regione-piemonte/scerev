/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.scerev.scerevlibbatch.opessan.def.opessanws.opessan;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java per MassimaliMediciBody complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="MassimaliMediciBody">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="pienaDisponibilita" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="massimale" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="massimaleInDeroga" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="massimaleOltre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="massimaleTemporaneo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="autolimitato" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dataUltimaModifica" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="idMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descrMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MassimaliMediciBody", propOrder = {
    "pienaDisponibilita",
    "massimale",
    "massimaleInDeroga",
    "massimaleOltre",
    "massimaleTemporaneo",
    "autolimitato",
    "dataUltimaModifica",
    "idMessage",
    "descrMessage"
})
public class MassimaliMediciBody {

    protected String pienaDisponibilita;
    protected String massimale;
    protected String massimaleInDeroga;
    protected String massimaleOltre;
    protected String massimaleTemporaneo;
    protected String autolimitato;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataUltimaModifica;
    protected String idMessage;
    protected String descrMessage;

    /**
     * Recupera il valore della proprietà pienaDisponibilita.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPienaDisponibilita() {
        return pienaDisponibilita;
    }

    /**
     * Imposta il valore della proprietà pienaDisponibilita.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPienaDisponibilita(String value) {
        this.pienaDisponibilita = value;
    }

    /**
     * Recupera il valore della proprietà massimale.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMassimale() {
        return massimale;
    }

    /**
     * Imposta il valore della proprietà massimale.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMassimale(String value) {
        this.massimale = value;
    }

    /**
     * Recupera il valore della proprietà massimaleInDeroga.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMassimaleInDeroga() {
        return massimaleInDeroga;
    }

    /**
     * Imposta il valore della proprietà massimaleInDeroga.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMassimaleInDeroga(String value) {
        this.massimaleInDeroga = value;
    }

    /**
     * Recupera il valore della proprietà massimaleOltre.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMassimaleOltre() {
        return massimaleOltre;
    }

    /**
     * Imposta il valore della proprietà massimaleOltre.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMassimaleOltre(String value) {
        this.massimaleOltre = value;
    }

    /**
     * Recupera il valore della proprietà massimaleTemporaneo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMassimaleTemporaneo() {
        return massimaleTemporaneo;
    }

    /**
     * Imposta il valore della proprietà massimaleTemporaneo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMassimaleTemporaneo(String value) {
        this.massimaleTemporaneo = value;
    }

    /**
     * Recupera il valore della proprietà autolimitato.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAutolimitato() {
        return autolimitato;
    }

    /**
     * Imposta il valore della proprietà autolimitato.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAutolimitato(String value) {
        this.autolimitato = value;
    }

    /**
     * Recupera il valore della proprietà dataUltimaModifica.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataUltimaModifica() {
        return dataUltimaModifica;
    }

    /**
     * Imposta il valore della proprietà dataUltimaModifica.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataUltimaModifica(XMLGregorianCalendar value) {
        this.dataUltimaModifica = value;
    }

    /**
     * Recupera il valore della proprietà idMessage.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdMessage() {
        return idMessage;
    }

    /**
     * Imposta il valore della proprietà idMessage.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdMessage(String value) {
        this.idMessage = value;
    }

    /**
     * Recupera il valore della proprietà descrMessage.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescrMessage() {
        return descrMessage;
    }

    /**
     * Imposta il valore della proprietà descrMessage.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescrMessage(String value) {
        this.descrMessage = value;
    }

}
