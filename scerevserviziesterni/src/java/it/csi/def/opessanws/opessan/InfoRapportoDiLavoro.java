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
 * <p>Classe Java per InfoRapportoDiLavoro complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="InfoRapportoDiLavoro">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="azienda" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="dataInizioRappLavoro" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="posContrattuale" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rapportoSSN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipoRuolo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipoRappLavoro" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tipoContratto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="qualifica" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codRegionale" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="indicatoreDisp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="distrettoCompetenza" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ambitoCompetenza" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dataScadenza" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="dataFine" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataInizioSosp" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataFineSosp" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="motivo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="causa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="incarichi" type="{http://opessan.opessanws.def.csi.it/}Incarichi" minOccurs="0"/>
 *         &lt;element name="infoMassimali" type="{http://opessan.opessanws.def.csi.it/}InfoMassimali" minOccurs="0"/>
 *         &lt;element name="dataVariazione" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InfoRapportoDiLavoro", propOrder = {
    "azienda",
    "dataInizioRappLavoro",
    "posContrattuale",
    "rapportoSSN",
    "tipoRuolo",
    "tipoRappLavoro",
    "tipoContratto",
    "qualifica",
    "codRegionale",
    "indicatoreDisp",
    "distrettoCompetenza",
    "ambitoCompetenza",
    "dataScadenza",
    "dataFine",
    "dataInizioSosp",
    "dataFineSosp",
    "motivo",
    "causa",
    "incarichi",
    "infoMassimali",
    "dataVariazione"
})
public class InfoRapportoDiLavoro {

    @XmlElement(required = true)
    protected String azienda;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataInizioRappLavoro;
    protected String posContrattuale;
    protected String rapportoSSN;
    protected String tipoRuolo;
    @XmlElement(required = true)
    protected String tipoRappLavoro;
    protected String tipoContratto;
    @XmlElement(required = true)
    protected String qualifica;
    protected String codRegionale;
    protected String indicatoreDisp;
    protected String distrettoCompetenza;
    protected String ambitoCompetenza;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataScadenza;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataFine;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataInizioSosp;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataFineSosp;
    protected String motivo;
    protected String causa;
    protected Incarichi incarichi;
    protected InfoMassimali infoMassimali;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataVariazione;

    /**
     * Recupera il valore della proprietà azienda.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAzienda() {
        return azienda;
    }

    /**
     * Imposta il valore della proprietà azienda.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAzienda(String value) {
        this.azienda = value;
    }

    /**
     * Recupera il valore della proprietà dataInizioRappLavoro.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataInizioRappLavoro() {
        return dataInizioRappLavoro;
    }

    /**
     * Imposta il valore della proprietà dataInizioRappLavoro.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataInizioRappLavoro(XMLGregorianCalendar value) {
        this.dataInizioRappLavoro = value;
    }

    /**
     * Recupera il valore della proprietà posContrattuale.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPosContrattuale() {
        return posContrattuale;
    }

    /**
     * Imposta il valore della proprietà posContrattuale.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPosContrattuale(String value) {
        this.posContrattuale = value;
    }

    /**
     * Recupera il valore della proprietà rapportoSSN.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRapportoSSN() {
        return rapportoSSN;
    }

    /**
     * Imposta il valore della proprietà rapportoSSN.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRapportoSSN(String value) {
        this.rapportoSSN = value;
    }

    /**
     * Recupera il valore della proprietà tipoRuolo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoRuolo() {
        return tipoRuolo;
    }

    /**
     * Imposta il valore della proprietà tipoRuolo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoRuolo(String value) {
        this.tipoRuolo = value;
    }

    /**
     * Recupera il valore della proprietà tipoRappLavoro.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoRappLavoro() {
        return tipoRappLavoro;
    }

    /**
     * Imposta il valore della proprietà tipoRappLavoro.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoRappLavoro(String value) {
        this.tipoRappLavoro = value;
    }

    /**
     * Recupera il valore della proprietà tipoContratto.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoContratto() {
        return tipoContratto;
    }

    /**
     * Imposta il valore della proprietà tipoContratto.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoContratto(String value) {
        this.tipoContratto = value;
    }

    /**
     * Recupera il valore della proprietà qualifica.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQualifica() {
        return qualifica;
    }

    /**
     * Imposta il valore della proprietà qualifica.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQualifica(String value) {
        this.qualifica = value;
    }

    /**
     * Recupera il valore della proprietà codRegionale.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodRegionale() {
        return codRegionale;
    }

    /**
     * Imposta il valore della proprietà codRegionale.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodRegionale(String value) {
        this.codRegionale = value;
    }

    /**
     * Recupera il valore della proprietà indicatoreDisp.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIndicatoreDisp() {
        return indicatoreDisp;
    }

    /**
     * Imposta il valore della proprietà indicatoreDisp.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIndicatoreDisp(String value) {
        this.indicatoreDisp = value;
    }

    /**
     * Recupera il valore della proprietà distrettoCompetenza.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDistrettoCompetenza() {
        return distrettoCompetenza;
    }

    /**
     * Imposta il valore della proprietà distrettoCompetenza.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDistrettoCompetenza(String value) {
        this.distrettoCompetenza = value;
    }

    /**
     * Recupera il valore della proprietà ambitoCompetenza.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAmbitoCompetenza() {
        return ambitoCompetenza;
    }

    /**
     * Imposta il valore della proprietà ambitoCompetenza.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAmbitoCompetenza(String value) {
        this.ambitoCompetenza = value;
    }

    /**
     * Recupera il valore della proprietà dataScadenza.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataScadenza() {
        return dataScadenza;
    }

    /**
     * Imposta il valore della proprietà dataScadenza.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataScadenza(XMLGregorianCalendar value) {
        this.dataScadenza = value;
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
     * Recupera il valore della proprietà dataInizioSosp.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataInizioSosp() {
        return dataInizioSosp;
    }

    /**
     * Imposta il valore della proprietà dataInizioSosp.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataInizioSosp(XMLGregorianCalendar value) {
        this.dataInizioSosp = value;
    }

    /**
     * Recupera il valore della proprietà dataFineSosp.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataFineSosp() {
        return dataFineSosp;
    }

    /**
     * Imposta il valore della proprietà dataFineSosp.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataFineSosp(XMLGregorianCalendar value) {
        this.dataFineSosp = value;
    }

    /**
     * Recupera il valore della proprietà motivo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMotivo() {
        return motivo;
    }

    /**
     * Imposta il valore della proprietà motivo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMotivo(String value) {
        this.motivo = value;
    }

    /**
     * Recupera il valore della proprietà causa.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCausa() {
        return causa;
    }

    /**
     * Imposta il valore della proprietà causa.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCausa(String value) {
        this.causa = value;
    }

    /**
     * Recupera il valore della proprietà incarichi.
     * 
     * @return
     *     possible object is
     *     {@link Incarichi }
     *     
     */
    public Incarichi getIncarichi() {
        return incarichi;
    }

    /**
     * Imposta il valore della proprietà incarichi.
     * 
     * @param value
     *     allowed object is
     *     {@link Incarichi }
     *     
     */
    public void setIncarichi(Incarichi value) {
        this.incarichi = value;
    }

    /**
     * Recupera il valore della proprietà infoMassimali.
     * 
     * @return
     *     possible object is
     *     {@link InfoMassimali }
     *     
     */
    public InfoMassimali getInfoMassimali() {
        return infoMassimali;
    }

    /**
     * Imposta il valore della proprietà infoMassimali.
     * 
     * @param value
     *     allowed object is
     *     {@link InfoMassimali }
     *     
     */
    public void setInfoMassimali(InfoMassimali value) {
        this.infoMassimali = value;
    }

    /**
     * Recupera il valore della proprietà dataVariazione.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataVariazione() {
        return dataVariazione;
    }

    /**
     * Imposta il valore della proprietà dataVariazione.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataVariazione(XMLGregorianCalendar value) {
        this.dataVariazione = value;
    }

}
