/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.scerev.scerevserviziesterni.aura.get;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java per InfoSanitarie complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="InfoSanitarie">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="aslAssistenza" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codiceFiscaleMedico" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codiceTesseraTEAM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codRegionaleMedico" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cognomeMedico" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dataFineASL" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataFineAssMedico" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataFineSSN" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataInizioASL" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataInizioAssMedico" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataInizioSSN" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="idMedico" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="idProfiloSanitario" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="nomeMedico" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codTipoProfiloSanitario" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="2"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="descTipoProfiloSanitario" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="100"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="aslResidenza" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="aslDomicilio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codDistrettoDomicilio" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="6"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="descDistrettoDomicilio" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="100"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="tipoMovimento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dataMovimento" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataDecorrenza" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="motivoFineAssistenza" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="100"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="statoInvioMEF" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="100"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="codiceTesseraRegionale" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="16"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="codDistrettoResidenza" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="6"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="descDistrettoResidenza" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="100"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InfoSanitarie", propOrder = {
    "aslAssistenza",
    "codiceFiscaleMedico",
    "codiceTesseraTEAM",
    "codRegionaleMedico",
    "cognomeMedico",
    "dataFineASL",
    "dataFineAssMedico",
    "dataFineSSN",
    "dataInizioASL",
    "dataInizioAssMedico",
    "dataInizioSSN",
    "idMedico",
    "idProfiloSanitario",
    "nomeMedico",
    "codTipoProfiloSanitario",
    "descTipoProfiloSanitario",
    "aslResidenza",
    "aslDomicilio",
    "codDistrettoDomicilio",
    "descDistrettoDomicilio",
    "tipoMovimento",
    "dataMovimento",
    "dataDecorrenza",
    "motivoFineAssistenza",
    "statoInvioMEF",
    "codiceTesseraRegionale",
    "codDistrettoResidenza",
    "descDistrettoResidenza"
})
public class InfoSanitarie {

    protected String aslAssistenza;
    protected String codiceFiscaleMedico;
    protected String codiceTesseraTEAM;
    protected String codRegionaleMedico;
    protected String cognomeMedico;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataFineASL;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataFineAssMedico;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataFineSSN;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataInizioASL;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataInizioAssMedico;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataInizioSSN;
    protected BigDecimal idMedico;
    protected BigDecimal idProfiloSanitario;
    protected String nomeMedico;
    protected String codTipoProfiloSanitario;
    protected String descTipoProfiloSanitario;
    protected String aslResidenza;
    protected String aslDomicilio;
    protected String codDistrettoDomicilio;
    protected String descDistrettoDomicilio;
    protected String tipoMovimento;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataMovimento;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataDecorrenza;
    protected String motivoFineAssistenza;
    protected String statoInvioMEF;
    protected String codiceTesseraRegionale;
    protected String codDistrettoResidenza;
    protected String descDistrettoResidenza;

    /**
     * Recupera il valore della proprietÓ aslAssistenza.
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
     * Imposta il valore della proprietÓ aslAssistenza.
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
     * Recupera il valore della proprietÓ codiceFiscaleMedico.
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
     * Imposta il valore della proprietÓ codiceFiscaleMedico.
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
     * Recupera il valore della proprietÓ codiceTesseraTEAM.
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
     * Imposta il valore della proprietÓ codiceTesseraTEAM.
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
     * Recupera il valore della proprietÓ codRegionaleMedico.
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
     * Imposta il valore della proprietÓ codRegionaleMedico.
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
     * Recupera il valore della proprietÓ cognomeMedico.
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
     * Imposta il valore della proprietÓ cognomeMedico.
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
     * Recupera il valore della proprietÓ dataFineASL.
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
     * Imposta il valore della proprietÓ dataFineASL.
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
     * Recupera il valore della proprietÓ dataFineAssMedico.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataFineAssMedico() {
        return dataFineAssMedico;
    }

    /**
     * Imposta il valore della proprietÓ dataFineAssMedico.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataFineAssMedico(XMLGregorianCalendar value) {
        this.dataFineAssMedico = value;
    }

    /**
     * Recupera il valore della proprietÓ dataFineSSN.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataFineSSN() {
        return dataFineSSN;
    }

    /**
     * Imposta il valore della proprietÓ dataFineSSN.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataFineSSN(XMLGregorianCalendar value) {
        this.dataFineSSN = value;
    }

    /**
     * Recupera il valore della proprietÓ dataInizioASL.
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
     * Imposta il valore della proprietÓ dataInizioASL.
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
     * Recupera il valore della proprietÓ dataInizioAssMedico.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataInizioAssMedico() {
        return dataInizioAssMedico;
    }

    /**
     * Imposta il valore della proprietÓ dataInizioAssMedico.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataInizioAssMedico(XMLGregorianCalendar value) {
        this.dataInizioAssMedico = value;
    }

    /**
     * Recupera il valore della proprietÓ dataInizioSSN.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataInizioSSN() {
        return dataInizioSSN;
    }

    /**
     * Imposta il valore della proprietÓ dataInizioSSN.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataInizioSSN(XMLGregorianCalendar value) {
        this.dataInizioSSN = value;
    }

    /**
     * Recupera il valore della proprietÓ idMedico.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getIdMedico() {
        return idMedico;
    }

    /**
     * Imposta il valore della proprietÓ idMedico.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setIdMedico(BigDecimal value) {
        this.idMedico = value;
    }

    /**
     * Recupera il valore della proprietÓ idProfiloSanitario.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getIdProfiloSanitario() {
        return idProfiloSanitario;
    }

    /**
     * Imposta il valore della proprietÓ idProfiloSanitario.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setIdProfiloSanitario(BigDecimal value) {
        this.idProfiloSanitario = value;
    }

    /**
     * Recupera il valore della proprietÓ nomeMedico.
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
     * Imposta il valore della proprietÓ nomeMedico.
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
     * Recupera il valore della proprietÓ codTipoProfiloSanitario.
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
     * Imposta il valore della proprietÓ codTipoProfiloSanitario.
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
     * Recupera il valore della proprietÓ descTipoProfiloSanitario.
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
     * Imposta il valore della proprietÓ descTipoProfiloSanitario.
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
     * Recupera il valore della proprietÓ aslResidenza.
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
     * Imposta il valore della proprietÓ aslResidenza.
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
     * Recupera il valore della proprietÓ aslDomicilio.
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
     * Imposta il valore della proprietÓ aslDomicilio.
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
     * Recupera il valore della proprietÓ codDistrettoDomicilio.
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
     * Imposta il valore della proprietÓ codDistrettoDomicilio.
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
     * Recupera il valore della proprietÓ descDistrettoDomicilio.
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
     * Imposta il valore della proprietÓ descDistrettoDomicilio.
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
     * Recupera il valore della proprietÓ tipoMovimento.
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
     * Imposta il valore della proprietÓ tipoMovimento.
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
     * Recupera il valore della proprietÓ dataMovimento.
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
     * Imposta il valore della proprietÓ dataMovimento.
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
     * Recupera il valore della proprietÓ dataDecorrenza.
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
     * Imposta il valore della proprietÓ dataDecorrenza.
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
     * Recupera il valore della proprietÓ motivoFineAssistenza.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMotivoFineAssistenza() {
        return motivoFineAssistenza;
    }

    /**
     * Imposta il valore della proprietÓ motivoFineAssistenza.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMotivoFineAssistenza(String value) {
        this.motivoFineAssistenza = value;
    }

    /**
     * Recupera il valore della proprietÓ statoInvioMEF.
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
     * Imposta il valore della proprietÓ statoInvioMEF.
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
     * Recupera il valore della proprietÓ codiceTesseraRegionale.
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
     * Imposta il valore della proprietÓ codiceTesseraRegionale.
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
     * Recupera il valore della proprietÓ codDistrettoResidenza.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodDistrettoResidenza() {
        return codDistrettoResidenza;
    }

    /**
     * Imposta il valore della proprietÓ codDistrettoResidenza.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodDistrettoResidenza(String value) {
        this.codDistrettoResidenza = value;
    }

    /**
     * Recupera il valore della proprietÓ descDistrettoResidenza.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescDistrettoResidenza() {
        return descDistrettoResidenza;
    }

    /**
     * Imposta il valore della proprietÓ descDistrettoResidenza.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescDistrettoResidenza(String value) {
        this.descDistrettoResidenza = value;
    }

}
