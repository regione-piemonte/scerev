/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.def.opessanws.opessan;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java per InfoProfessionali complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="InfoProfessionali">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="dataLaurea" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataSpecializzazione" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataTitolarita" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="nomeAlboProf" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="siglaProvAlboProf" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numIscrizioneAlbo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dataIscrizAlbo" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataCancAlbo" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataInizioInformat" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataInizioSospAlbo" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataFineSospAlbo" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InfoProfessionali", propOrder = {
    "dataLaurea",
    "dataSpecializzazione",
    "dataTitolarita",
    "nomeAlboProf",
    "siglaProvAlboProf",
    "numIscrizioneAlbo",
    "dataIscrizAlbo",
    "dataCancAlbo",
    "dataInizioInformat",
    "dataInizioSospAlbo",
    "dataFineSospAlbo"
})
public class InfoProfessionali {

    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataLaurea;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataSpecializzazione;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataTitolarita;
    protected String nomeAlboProf;
    protected String siglaProvAlboProf;
    protected String numIscrizioneAlbo;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataIscrizAlbo;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataCancAlbo;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataInizioInformat;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataInizioSospAlbo;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataFineSospAlbo;

    /**
     * Recupera il valore della propriet� dataLaurea.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataLaurea() {
        return dataLaurea;
    }

    /**
     * Imposta il valore della propriet� dataLaurea.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataLaurea(XMLGregorianCalendar value) {
        this.dataLaurea = value;
    }

    /**
     * Recupera il valore della propriet� dataSpecializzazione.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataSpecializzazione() {
        return dataSpecializzazione;
    }

    /**
     * Imposta il valore della propriet� dataSpecializzazione.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataSpecializzazione(XMLGregorianCalendar value) {
        this.dataSpecializzazione = value;
    }

    /**
     * Recupera il valore della propriet� dataTitolarita.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataTitolarita() {
        return dataTitolarita;
    }

    /**
     * Imposta il valore della propriet� dataTitolarita.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataTitolarita(XMLGregorianCalendar value) {
        this.dataTitolarita = value;
    }

    /**
     * Recupera il valore della propriet� nomeAlboProf.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomeAlboProf() {
        return nomeAlboProf;
    }

    /**
     * Imposta il valore della propriet� nomeAlboProf.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomeAlboProf(String value) {
        this.nomeAlboProf = value;
    }

    /**
     * Recupera il valore della propriet� siglaProvAlboProf.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSiglaProvAlboProf() {
        return siglaProvAlboProf;
    }

    /**
     * Imposta il valore della propriet� siglaProvAlboProf.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSiglaProvAlboProf(String value) {
        this.siglaProvAlboProf = value;
    }

    /**
     * Recupera il valore della propriet� numIscrizioneAlbo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumIscrizioneAlbo() {
        return numIscrizioneAlbo;
    }

    /**
     * Imposta il valore della propriet� numIscrizioneAlbo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumIscrizioneAlbo(String value) {
        this.numIscrizioneAlbo = value;
    }

    /**
     * Recupera il valore della propriet� dataIscrizAlbo.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataIscrizAlbo() {
        return dataIscrizAlbo;
    }

    /**
     * Imposta il valore della propriet� dataIscrizAlbo.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataIscrizAlbo(XMLGregorianCalendar value) {
        this.dataIscrizAlbo = value;
    }

    /**
     * Recupera il valore della propriet� dataCancAlbo.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataCancAlbo() {
        return dataCancAlbo;
    }

    /**
     * Imposta il valore della propriet� dataCancAlbo.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataCancAlbo(XMLGregorianCalendar value) {
        this.dataCancAlbo = value;
    }

    /**
     * Recupera il valore della propriet� dataInizioInformat.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataInizioInformat() {
        return dataInizioInformat;
    }

    /**
     * Imposta il valore della propriet� dataInizioInformat.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataInizioInformat(XMLGregorianCalendar value) {
        this.dataInizioInformat = value;
    }

    /**
     * Recupera il valore della propriet� dataInizioSospAlbo.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataInizioSospAlbo() {
        return dataInizioSospAlbo;
    }

    /**
     * Imposta il valore della propriet� dataInizioSospAlbo.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataInizioSospAlbo(XMLGregorianCalendar value) {
        this.dataInizioSospAlbo = value;
    }

    /**
     * Recupera il valore della propriet� dataFineSospAlbo.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataFineSospAlbo() {
        return dataFineSospAlbo;
    }

    /**
     * Imposta il valore della propriet� dataFineSospAlbo.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataFineSospAlbo(XMLGregorianCalendar value) {
        this.dataFineSospAlbo = value;
    }

}
