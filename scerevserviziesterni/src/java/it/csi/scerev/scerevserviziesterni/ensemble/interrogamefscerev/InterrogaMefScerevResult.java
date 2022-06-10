/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.scerev.scerevserviziesterni.ensemble.interrogamefscerev;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java per InterrogaMefScerevResult complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="InterrogaMefScerevResult">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="codiceEsito" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="256"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="capResidenza" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="Codicefiscale" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="16"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Cognome" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="50"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="comuneResidenza" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Comunenasc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Datadecesso" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="Datanascita" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="errorCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="esitoMef" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="findType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idComunenascita" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idStatonascita" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="indirizzoResidenza" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="100"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Nazionenasc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Nome" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Privacy" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="provinciaResidenza" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Provincianasc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sceltaInDeroga" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Sesso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codiceTipoAssistito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codiceRegioneAssistenza" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dataFineValidita" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dataInizioValidita" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InterrogaMefScerevResult", propOrder = {
    "codiceEsito",
    "capResidenza",
    "codicefiscale",
    "cognome",
    "comuneResidenza",
    "comunenasc",
    "datadecesso",
    "datanascita",
    "errorCode",
    "esitoMef",
    "findType",
    "idComunenascita",
    "idStatonascita",
    "indirizzoResidenza",
    "nazionenasc",
    "nome",
    "privacy",
    "provinciaResidenza",
    "provincianasc",
    "sceltaInDeroga",
    "sesso",
    "codiceTipoAssistito",
    "codiceRegioneAssistenza",
    "dataFineValidita",
    "dataInizioValidita"
    
})
@XmlRootElement
public class InterrogaMefScerevResult {

    protected String codiceEsito;
    protected String capResidenza;
    @XmlElement(name = "Codicefiscale")
    protected String codicefiscale;
    @XmlElement(name = "Cognome")
    protected String cognome;
    protected String comuneResidenza;
    @XmlElement(name = "Comunenasc")
    protected String comunenasc;
    @XmlElement(name = "Datadecesso")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar datadecesso;
    @XmlElement(name = "Datanascita")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar datanascita;
    protected String errorCode;
    protected String esitoMef;
    protected String findType;
    protected String idComunenascita;
    protected String idStatonascita;
    protected String indirizzoResidenza;
    @XmlElement(name = "Nazionenasc")
    protected String nazionenasc;
    @XmlElement(name = "Nome")
    protected String nome;
    @XmlElement(name = "Privacy")
    protected BigDecimal privacy;
    protected String provinciaResidenza;
    @XmlElement(name = "Provincianasc")
    protected String provincianasc;
    protected String sceltaInDeroga;
    @XmlElement(name = "Sesso")
    protected String sesso;
    protected String codiceTipoAssistito;
    protected String codiceRegioneAssistenza;
    protected String dataFineValidita;
    protected String dataInizioValidita;

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
     * Recupera il valore della proprietà capResidenza.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCapResidenza() {
        return capResidenza;
    }

    /**
     * Imposta il valore della proprietà capResidenza.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCapResidenza(String value) {
        this.capResidenza = value;
    }

    /**
     * Recupera il valore della proprietà codicefiscale.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodicefiscale() {
        return codicefiscale;
    }

    /**
     * Imposta il valore della proprietà codicefiscale.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodicefiscale(String value) {
        this.codicefiscale = value;
    }

    /**
     * Recupera il valore della proprietà cognome.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCognome() {
        return cognome;
    }

    /**
     * Imposta il valore della proprietà cognome.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCognome(String value) {
        this.cognome = value;
    }

    /**
     * Recupera il valore della proprietà comuneResidenza.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComuneResidenza() {
        return comuneResidenza;
    }

    /**
     * Imposta il valore della proprietà comuneResidenza.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComuneResidenza(String value) {
        this.comuneResidenza = value;
    }

    /**
     * Recupera il valore della proprietà comunenasc.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComunenasc() {
        return comunenasc;
    }

    /**
     * Imposta il valore della proprietà comunenasc.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComunenasc(String value) {
        this.comunenasc = value;
    }

    /**
     * Recupera il valore della proprietà datadecesso.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDatadecesso() {
        return datadecesso;
    }

    /**
     * Imposta il valore della proprietà datadecesso.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDatadecesso(XMLGregorianCalendar value) {
        this.datadecesso = value;
    }

    /**
     * Recupera il valore della proprietà datanascita.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDatanascita() {
        return datanascita;
    }

    /**
     * Imposta il valore della proprietà datanascita.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDatanascita(XMLGregorianCalendar value) {
        this.datanascita = value;
    }

    /**
     * Recupera il valore della proprietà errorCode.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * Imposta il valore della proprietà errorCode.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrorCode(String value) {
        this.errorCode = value;
    }

    /**
     * Recupera il valore della proprietà esitoMef.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEsitoMef() {
        return esitoMef;
    }

    /**
     * Imposta il valore della proprietà esitoMef.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEsitoMef(String value) {
        this.esitoMef = value;
    }

    /**
     * Recupera il valore della proprietà findType.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFindType() {
        return findType;
    }

    /**
     * Imposta il valore della proprietà findType.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFindType(String value) {
        this.findType = value;
    }

    /**
     * Recupera il valore della proprietà idComunenascita.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdComunenascita() {
        return idComunenascita;
    }

    /**
     * Imposta il valore della proprietà idComunenascita.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdComunenascita(String value) {
        this.idComunenascita = value;
    }

    /**
     * Recupera il valore della proprietà idStatonascita.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdStatonascita() {
        return idStatonascita;
    }

    /**
     * Imposta il valore della proprietà idStatonascita.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdStatonascita(String value) {
        this.idStatonascita = value;
    }

    /**
     * Recupera il valore della proprietà indirizzoResidenza.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIndirizzoResidenza() {
        return indirizzoResidenza;
    }

    /**
     * Imposta il valore della proprietà indirizzoResidenza.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIndirizzoResidenza(String value) {
        this.indirizzoResidenza = value;
    }

    /**
     * Recupera il valore della proprietà nazionenasc.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNazionenasc() {
        return nazionenasc;
    }

    /**
     * Imposta il valore della proprietà nazionenasc.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNazionenasc(String value) {
        this.nazionenasc = value;
    }

    /**
     * Recupera il valore della proprietà nome.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNome() {
        return nome;
    }

    /**
     * Imposta il valore della proprietà nome.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNome(String value) {
        this.nome = value;
    }

    /**
     * Recupera il valore della proprietà privacy.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPrivacy() {
        return privacy;
    }

    /**
     * Imposta il valore della proprietà privacy.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPrivacy(BigDecimal value) {
        this.privacy = value;
    }

    /**
     * Recupera il valore della proprietà provinciaResidenza.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProvinciaResidenza() {
        return provinciaResidenza;
    }

    /**
     * Imposta il valore della proprietà provinciaResidenza.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProvinciaResidenza(String value) {
        this.provinciaResidenza = value;
    }

    /**
     * Recupera il valore della proprietà provincianasc.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProvincianasc() {
        return provincianasc;
    }

    /**
     * Imposta il valore della proprietà provincianasc.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProvincianasc(String value) {
        this.provincianasc = value;
    }

    /**
     * Recupera il valore della proprietà sceltaInDeroga.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSceltaInDeroga() {
        return sceltaInDeroga;
    }

    /**
     * Imposta il valore della proprietà sceltaInDeroga.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSceltaInDeroga(String value) {
        this.sceltaInDeroga = value;
    }

    /**
     * Recupera il valore della proprietà sesso.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSesso() {
        return sesso;
    }

    /**
     * Imposta il valore della proprietà sesso.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSesso(String value) {
        this.sesso = value;
    }

    /**
     * Recupera il valore della proprietà codiceTipoAssistito.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceTipoAssistito() {
        return codiceTipoAssistito;
    }

    /**
     * Imposta il valore della proprietà codiceTipoAssistito.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceTipoAssistito(String value) {
        this.codiceTipoAssistito = value;
    }

    /**
     * Recupera il valore della proprietà codiceRegioneAssistenza.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceRegioneAssistenza() {
        return codiceRegioneAssistenza;
    }

    /**
     * Imposta il valore della proprietà codiceRegioneAssistenza.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceRegioneAssistenza(String value) {
        this.codiceRegioneAssistenza = value;
    }

    /**
     * Recupera il valore della proprietà dataFineValidita.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
	public String getDataFineValidita() {
		return dataFineValidita;
	}

    /**
     * Imposta il valore della proprietà dataFineValidita.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
	public void setDataFineValidita(String dataFineValidita) {
		this.dataFineValidita = dataFineValidita;
	}

    /**
     * Recupera il valore della proprietà dataInizioValidita.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
	public String getDataInizioValidita() {
		return dataInizioValidita;
	}

    /**
     * Imposta il valore della proprietà dataInizioValidita.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
	public void setDataInizioValidita(String dataInizioValidita) {
		this.dataInizioValidita = dataInizioValidita;
	}

    
}
