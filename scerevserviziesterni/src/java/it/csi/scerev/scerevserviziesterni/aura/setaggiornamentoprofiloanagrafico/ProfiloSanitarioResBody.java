/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.scerev.scerevserviziesterni.aura.setaggiornamentoprofiloanagrafico;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java per ProfiloSanitarioResBody complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ProfiloSanitarioResBody">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idAura">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="10"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="idProfiloSanitario">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="10"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="aslAssistenza" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="dataInizioASL" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="dataFineASL" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="idMedico">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="10"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="cognomeMedico">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="50"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="nomeMedico">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="50"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="codiceFiscaleMedico">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="16"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="codRegionaleMedico">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="7"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="codiceTesseraTEAM" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="20"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="codTipoProfiloSanitario">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="2"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="descTipoProfiloSanitario">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="100"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="AslResidenza" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AslDomicilio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codDistrettoDomicilio" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="2"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="DescDistrettoDomicilio" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="100"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="TipoMovimento">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="1"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="DataMovimento" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="DataDecorrenza" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="statoInvioMEF">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="1"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="codiceTesseraRegionale">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="11"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="dataFineSospensione" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProfiloSanitarioResBody", propOrder = {
    "idAura",
    "idProfiloSanitario",
    "aslAssistenza",
    "dataInizioASL",
    "dataFineASL",
    "idMedico",
    "cognomeMedico",
    "nomeMedico",
    "codiceFiscaleMedico",
    "codRegionaleMedico",
    "codiceTesseraTEAM",
    "codTipoProfiloSanitario",
    "descTipoProfiloSanitario",
    "aslResidenza",
    "aslDomicilio",
    "codDistrettoDomicilio",
    "descDistrettoDomicilio",
    "tipoMovimento",
    "dataMovimento",
    "dataDecorrenza",
    "statoInvioMEF",
    "codiceTesseraRegionale",
    "dataFineSospensione"
})
public class ProfiloSanitarioResBody {

    @XmlElement(required = true)
    protected String idAura;
    @XmlElement(required = true)
    protected String idProfiloSanitario;
    @XmlElement(required = true)
    protected String aslAssistenza;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dataInizioASL;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dataFineASL;
    @XmlElement(required = true)
    protected String idMedico;
    @XmlElement(required = true)
    protected String cognomeMedico;
    @XmlElement(required = true)
    protected String nomeMedico;
    @XmlElement(required = true)
    protected String codiceFiscaleMedico;
    @XmlElement(required = true)
    protected String codRegionaleMedico;
    protected String codiceTesseraTEAM;
    @XmlElement(required = true)
    protected String codTipoProfiloSanitario;
    @XmlElement(required = true)
    protected String descTipoProfiloSanitario;
    @XmlElement(name = "AslResidenza")
    protected String aslResidenza;
    @XmlElement(name = "AslDomicilio")
    protected String aslDomicilio;
    protected String codDistrettoDomicilio;
    @XmlElement(name = "DescDistrettoDomicilio")
    protected String descDistrettoDomicilio;
    @XmlElement(name = "TipoMovimento", required = true)
    protected String tipoMovimento;
    @XmlElement(name = "DataMovimento", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataMovimento;
    @XmlElement(name = "DataDecorrenza", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dataDecorrenza;
    @XmlElement(required = true)
    protected String statoInvioMEF;
    @XmlElement(required = true)
    protected String codiceTesseraRegionale;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dataFineSospensione;

    /**
     * Recupera il valore della proprietà idAura.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdAura() {
        return idAura;
    }

    /**
     * Imposta il valore della proprietà idAura.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdAura(String value) {
        this.idAura = value;
    }

    /**
     * Recupera il valore della proprietà idProfiloSanitario.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdProfiloSanitario() {
        return idProfiloSanitario;
    }

    /**
     * Imposta il valore della proprietà idProfiloSanitario.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdProfiloSanitario(String value) {
        this.idProfiloSanitario = value;
    }

    /**
     * Recupera il valore della proprietà aslAssistenza.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAslAssistenza() {
        return aslAssistenza;
    }

    /**
     * Imposta il valore della proprietà aslAssistenza.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAslAssistenza(String value) {
        this.aslAssistenza = value;
    }

    /**
     * Recupera il valore della proprietà dataInizioASL.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataInizioASL() {
        return dataInizioASL;
    }

    /**
     * Imposta il valore della proprietà dataInizioASL.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataInizioASL(XMLGregorianCalendar value) {
        this.dataInizioASL = value;
    }

    /**
     * Recupera il valore della proprietà dataFineASL.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataFineASL() {
        return dataFineASL;
    }

    /**
     * Imposta il valore della proprietà dataFineASL.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataFineASL(XMLGregorianCalendar value) {
        this.dataFineASL = value;
    }

    /**
     * Recupera il valore della proprietà idMedico.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdMedico() {
        return idMedico;
    }

    /**
     * Imposta il valore della proprietà idMedico.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdMedico(String value) {
        this.idMedico = value;
    }

    /**
     * Recupera il valore della proprietà cognomeMedico.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCognomeMedico() {
        return cognomeMedico;
    }

    /**
     * Imposta il valore della proprietà cognomeMedico.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCognomeMedico(String value) {
        this.cognomeMedico = value;
    }

    /**
     * Recupera il valore della proprietà nomeMedico.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomeMedico() {
        return nomeMedico;
    }

    /**
     * Imposta il valore della proprietà nomeMedico.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomeMedico(String value) {
        this.nomeMedico = value;
    }

    /**
     * Recupera il valore della proprietà codiceFiscaleMedico.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceFiscaleMedico() {
        return codiceFiscaleMedico;
    }

    /**
     * Imposta il valore della proprietà codiceFiscaleMedico.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceFiscaleMedico(String value) {
        this.codiceFiscaleMedico = value;
    }

    /**
     * Recupera il valore della proprietà codRegionaleMedico.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodRegionaleMedico() {
        return codRegionaleMedico;
    }

    /**
     * Imposta il valore della proprietà codRegionaleMedico.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodRegionaleMedico(String value) {
        this.codRegionaleMedico = value;
    }

    /**
     * Recupera il valore della proprietà codiceTesseraTEAM.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceTesseraTEAM() {
        return codiceTesseraTEAM;
    }

    /**
     * Imposta il valore della proprietà codiceTesseraTEAM.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceTesseraTEAM(String value) {
        this.codiceTesseraTEAM = value;
    }

    /**
     * Recupera il valore della proprietà codTipoProfiloSanitario.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodTipoProfiloSanitario() {
        return codTipoProfiloSanitario;
    }

    /**
     * Imposta il valore della proprietà codTipoProfiloSanitario.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodTipoProfiloSanitario(String value) {
        this.codTipoProfiloSanitario = value;
    }

    /**
     * Recupera il valore della proprietà descTipoProfiloSanitario.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescTipoProfiloSanitario() {
        return descTipoProfiloSanitario;
    }

    /**
     * Imposta il valore della proprietà descTipoProfiloSanitario.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescTipoProfiloSanitario(String value) {
        this.descTipoProfiloSanitario = value;
    }

    /**
     * Recupera il valore della proprietà aslResidenza.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAslResidenza() {
        return aslResidenza;
    }

    /**
     * Imposta il valore della proprietà aslResidenza.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAslResidenza(String value) {
        this.aslResidenza = value;
    }

    /**
     * Recupera il valore della proprietà aslDomicilio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAslDomicilio() {
        return aslDomicilio;
    }

    /**
     * Imposta il valore della proprietà aslDomicilio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAslDomicilio(String value) {
        this.aslDomicilio = value;
    }

    /**
     * Recupera il valore della proprietà codDistrettoDomicilio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodDistrettoDomicilio() {
        return codDistrettoDomicilio;
    }

    /**
     * Imposta il valore della proprietà codDistrettoDomicilio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodDistrettoDomicilio(String value) {
        this.codDistrettoDomicilio = value;
    }

    /**
     * Recupera il valore della proprietà descDistrettoDomicilio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescDistrettoDomicilio() {
        return descDistrettoDomicilio;
    }

    /**
     * Imposta il valore della proprietà descDistrettoDomicilio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescDistrettoDomicilio(String value) {
        this.descDistrettoDomicilio = value;
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
     * Recupera il valore della proprietà dataMovimento.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataMovimento() {
        return dataMovimento;
    }

    /**
     * Imposta il valore della proprietà dataMovimento.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataMovimento(XMLGregorianCalendar value) {
        this.dataMovimento = value;
    }

    /**
     * Recupera il valore della proprietà dataDecorrenza.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataDecorrenza() {
        return dataDecorrenza;
    }

    /**
     * Imposta il valore della proprietà dataDecorrenza.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataDecorrenza(XMLGregorianCalendar value) {
        this.dataDecorrenza = value;
    }

    /**
     * Recupera il valore della proprietà statoInvioMEF.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatoInvioMEF() {
        return statoInvioMEF;
    }

    /**
     * Imposta il valore della proprietà statoInvioMEF.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatoInvioMEF(String value) {
        this.statoInvioMEF = value;
    }

    /**
     * Recupera il valore della proprietà codiceTesseraRegionale.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceTesseraRegionale() {
        return codiceTesseraRegionale;
    }

    /**
     * Imposta il valore della proprietà codiceTesseraRegionale.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceTesseraRegionale(String value) {
        this.codiceTesseraRegionale = value;
    }

    /**
     * Recupera il valore della proprietà dataFineSospensione.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataFineSospensione() {
        return dataFineSospensione;
    }

    /**
     * Imposta il valore della proprietà dataFineSospensione.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataFineSospensione(XMLGregorianCalendar value) {
        this.dataFineSospensione = value;
    }

}
