/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.scerev.scerevserviziesterni.aura.setinserimentoprofiloanagrafico;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java per InserimentoProfiloAnagraficoAltreInfo complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="InserimentoProfiloAnagraficoAltreInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ListaAltreInfo" type="{http://SetInserimentoProfiloAnagrafico.central.services.auraws.aura.csi.it}ArrayOfInserimentoProfiloAnagraficoAltreInfoDett" minOccurs="0"/>
 *         &lt;element name="StatoContatto" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="1"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="DataInizioResidenza" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="DataFineResidenza" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="DataInizioDomicilio" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="DataFineDomicilio" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InserimentoProfiloAnagraficoAltreInfo", propOrder = {
    "listaAltreInfo",
    "statoContatto",
    "dataInizioResidenza",
    "dataFineResidenza",
    "dataInizioDomicilio",
    "dataFineDomicilio"
})
public class InserimentoProfiloAnagraficoAltreInfo {

    @XmlElement(name = "ListaAltreInfo")
    protected ArrayOfInserimentoProfiloAnagraficoAltreInfoDett listaAltreInfo;
    @XmlElement(name = "StatoContatto")
    protected String statoContatto;
    @XmlElement(name = "DataInizioResidenza")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dataInizioResidenza;
    @XmlElement(name = "DataFineResidenza")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dataFineResidenza;
    @XmlElement(name = "DataInizioDomicilio")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dataInizioDomicilio;
    @XmlElement(name = "DataFineDomicilio")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dataFineDomicilio;

    /**
     * Recupera il valore della proprietà listaAltreInfo.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfInserimentoProfiloAnagraficoAltreInfoDett }
     *     
     */
    public ArrayOfInserimentoProfiloAnagraficoAltreInfoDett getListaAltreInfo() {
        return listaAltreInfo;
    }

    /**
     * Imposta il valore della proprietà listaAltreInfo.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfInserimentoProfiloAnagraficoAltreInfoDett }
     *     
     */
    public void setListaAltreInfo(ArrayOfInserimentoProfiloAnagraficoAltreInfoDett value) {
        this.listaAltreInfo = value;
    }

    /**
     * Recupera il valore della proprietà statoContatto.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatoContatto() {
        return statoContatto;
    }

    /**
     * Imposta il valore della proprietà statoContatto.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatoContatto(String value) {
        this.statoContatto = value;
    }

    /**
     * Recupera il valore della proprietà dataInizioResidenza.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataInizioResidenza() {
        return dataInizioResidenza;
    }

    /**
     * Imposta il valore della proprietà dataInizioResidenza.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataInizioResidenza(XMLGregorianCalendar value) {
        this.dataInizioResidenza = value;
    }

    /**
     * Recupera il valore della proprietà dataFineResidenza.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataFineResidenza() {
        return dataFineResidenza;
    }

    /**
     * Imposta il valore della proprietà dataFineResidenza.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataFineResidenza(XMLGregorianCalendar value) {
        this.dataFineResidenza = value;
    }

    /**
     * Recupera il valore della proprietà dataInizioDomicilio.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataInizioDomicilio() {
        return dataInizioDomicilio;
    }

    /**
     * Imposta il valore della proprietà dataInizioDomicilio.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataInizioDomicilio(XMLGregorianCalendar value) {
        this.dataInizioDomicilio = value;
    }

    /**
     * Recupera il valore della proprietà dataFineDomicilio.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataFineDomicilio() {
        return dataFineDomicilio;
    }

    /**
     * Imposta il valore della proprietà dataFineDomicilio.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataFineDomicilio(XMLGregorianCalendar value) {
        this.dataFineDomicilio = value;
    }

}
