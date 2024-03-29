/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.scerev.scerevserviziesterni.aura.getUltimoMovimentoDiRevoca;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java per ResponseDelegate complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ResponseDelegate">
 *   &lt;complexContent>
 *     &lt;extension base="{http://getUltimoMovimentoDiRevoca.central.services.auraws.aura.csi.it}Ens_Response">
 *       &lt;sequence>
 *         &lt;element name="idMovimento" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="idProfiloSanitario" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="idMedico" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="dataMov" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataInsert" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataTratt" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="derTerr" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="derPediatra" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="derNuovoNato" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="derMassimale" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="dataCont" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="derAssoc" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="derGenerico" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="stato" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="tipo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dataDeco" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="approvazione" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="causale" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="situazione" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="annullato" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="idCatScelta" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="derFami" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="derMaxTemp" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="idUserInsert" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="idRapLav" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="note" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idAsl" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="dataFineSospensione" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="idUserUpdate" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="dataUpdate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="etaRiservata" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResponseDelegate", propOrder = {
    "idMovimento",
    "idProfiloSanitario",
    "idMedico",
    "dataMov",
    "dataInsert",
    "dataTratt",
    "derTerr",
    "derPediatra",
    "derNuovoNato",
    "derMassimale",
    "dataCont",
    "derAssoc",
    "derGenerico",
    "stato",
    "tipo",
    "dataDeco",
    "approvazione",
    "causale",
    "situazione",
    "annullato",
    "idCatScelta",
    "derFami",
    "derMaxTemp",
    "idUserInsert",
    "idRapLav",
    "note",
    "idAsl",
    "dataFineSospensione",
    "idUserUpdate",
    "dataUpdate",
    "etaRiservata"
})
public class ResponseDelegate
    extends EnsResponse
{

    protected BigDecimal idMovimento;
    protected BigDecimal idProfiloSanitario;
    protected BigDecimal idMedico;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataMov;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataInsert;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataTratt;
    protected BigDecimal derTerr;
    protected BigDecimal derPediatra;
    protected BigDecimal derNuovoNato;
    protected BigDecimal derMassimale;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataCont;
    protected BigDecimal derAssoc;
    protected BigDecimal derGenerico;
    protected BigDecimal stato;
    protected String tipo;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataDeco;
    protected BigDecimal approvazione;
    protected String causale;
    protected BigDecimal situazione;
    protected BigDecimal annullato;
    protected BigDecimal idCatScelta;
    protected BigDecimal derFami;
    protected BigDecimal derMaxTemp;
    protected BigDecimal idUserInsert;
    protected BigDecimal idRapLav;
    protected String note;
    protected BigDecimal idAsl;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataFineSospensione;
    protected BigDecimal idUserUpdate;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataUpdate;
    protected BigDecimal etaRiservata;

    /**
     * Recupera il valore della proprietÓ idMovimento.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getIdMovimento() {
        return idMovimento;
    }

    /**
     * Imposta il valore della proprietÓ idMovimento.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setIdMovimento(BigDecimal value) {
        this.idMovimento = value;
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
     * Recupera il valore della proprietÓ dataMov.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataMov() {
        return dataMov;
    }

    /**
     * Imposta il valore della proprietÓ dataMov.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataMov(XMLGregorianCalendar value) {
        this.dataMov = value;
    }

    /**
     * Recupera il valore della proprietÓ dataInsert.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataInsert() {
        return dataInsert;
    }

    /**
     * Imposta il valore della proprietÓ dataInsert.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataInsert(XMLGregorianCalendar value) {
        this.dataInsert = value;
    }

    /**
     * Recupera il valore della proprietÓ dataTratt.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataTratt() {
        return dataTratt;
    }

    /**
     * Imposta il valore della proprietÓ dataTratt.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataTratt(XMLGregorianCalendar value) {
        this.dataTratt = value;
    }

    /**
     * Recupera il valore della proprietÓ derTerr.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getDerTerr() {
        return derTerr;
    }

    /**
     * Imposta il valore della proprietÓ derTerr.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setDerTerr(BigDecimal value) {
        this.derTerr = value;
    }

    /**
     * Recupera il valore della proprietÓ derPediatra.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getDerPediatra() {
        return derPediatra;
    }

    /**
     * Imposta il valore della proprietÓ derPediatra.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setDerPediatra(BigDecimal value) {
        this.derPediatra = value;
    }

    /**
     * Recupera il valore della proprietÓ derNuovoNato.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getDerNuovoNato() {
        return derNuovoNato;
    }

    /**
     * Imposta il valore della proprietÓ derNuovoNato.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setDerNuovoNato(BigDecimal value) {
        this.derNuovoNato = value;
    }

    /**
     * Recupera il valore della proprietÓ derMassimale.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getDerMassimale() {
        return derMassimale;
    }

    /**
     * Imposta il valore della proprietÓ derMassimale.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setDerMassimale(BigDecimal value) {
        this.derMassimale = value;
    }

    /**
     * Recupera il valore della proprietÓ dataCont.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataCont() {
        return dataCont;
    }

    /**
     * Imposta il valore della proprietÓ dataCont.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataCont(XMLGregorianCalendar value) {
        this.dataCont = value;
    }

    /**
     * Recupera il valore della proprietÓ derAssoc.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getDerAssoc() {
        return derAssoc;
    }

    /**
     * Imposta il valore della proprietÓ derAssoc.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setDerAssoc(BigDecimal value) {
        this.derAssoc = value;
    }

    /**
     * Recupera il valore della proprietÓ derGenerico.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getDerGenerico() {
        return derGenerico;
    }

    /**
     * Imposta il valore della proprietÓ derGenerico.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setDerGenerico(BigDecimal value) {
        this.derGenerico = value;
    }

    /**
     * Recupera il valore della proprietÓ stato.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getStato() {
        return stato;
    }

    /**
     * Imposta il valore della proprietÓ stato.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setStato(BigDecimal value) {
        this.stato = value;
    }

    /**
     * Recupera il valore della proprietÓ tipo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Imposta il valore della proprietÓ tipo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipo(String value) {
        this.tipo = value;
    }

    /**
     * Recupera il valore della proprietÓ dataDeco.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataDeco() {
        return dataDeco;
    }

    /**
     * Imposta il valore della proprietÓ dataDeco.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataDeco(XMLGregorianCalendar value) {
        this.dataDeco = value;
    }

    /**
     * Recupera il valore della proprietÓ approvazione.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getApprovazione() {
        return approvazione;
    }

    /**
     * Imposta il valore della proprietÓ approvazione.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setApprovazione(BigDecimal value) {
        this.approvazione = value;
    }

    /**
     * Recupera il valore della proprietÓ causale.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCausale() {
        return causale;
    }

    /**
     * Imposta il valore della proprietÓ causale.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCausale(String value) {
        this.causale = value;
    }

    /**
     * Recupera il valore della proprietÓ situazione.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getSituazione() {
        return situazione;
    }

    /**
     * Imposta il valore della proprietÓ situazione.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setSituazione(BigDecimal value) {
        this.situazione = value;
    }

    /**
     * Recupera il valore della proprietÓ annullato.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAnnullato() {
        return annullato;
    }

    /**
     * Imposta il valore della proprietÓ annullato.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAnnullato(BigDecimal value) {
        this.annullato = value;
    }

    /**
     * Recupera il valore della proprietÓ idCatScelta.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getIdCatScelta() {
        return idCatScelta;
    }

    /**
     * Imposta il valore della proprietÓ idCatScelta.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setIdCatScelta(BigDecimal value) {
        this.idCatScelta = value;
    }

    /**
     * Recupera il valore della proprietÓ derFami.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getDerFami() {
        return derFami;
    }

    /**
     * Imposta il valore della proprietÓ derFami.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setDerFami(BigDecimal value) {
        this.derFami = value;
    }

    /**
     * Recupera il valore della proprietÓ derMaxTemp.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getDerMaxTemp() {
        return derMaxTemp;
    }

    /**
     * Imposta il valore della proprietÓ derMaxTemp.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setDerMaxTemp(BigDecimal value) {
        this.derMaxTemp = value;
    }

    /**
     * Recupera il valore della proprietÓ idUserInsert.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getIdUserInsert() {
        return idUserInsert;
    }

    /**
     * Imposta il valore della proprietÓ idUserInsert.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setIdUserInsert(BigDecimal value) {
        this.idUserInsert = value;
    }

    /**
     * Recupera il valore della proprietÓ idRapLav.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getIdRapLav() {
        return idRapLav;
    }

    /**
     * Imposta il valore della proprietÓ idRapLav.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setIdRapLav(BigDecimal value) {
        this.idRapLav = value;
    }

    /**
     * Recupera il valore della proprietÓ note.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNote() {
        return note;
    }

    /**
     * Imposta il valore della proprietÓ note.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNote(String value) {
        this.note = value;
    }

    /**
     * Recupera il valore della proprietÓ idAsl.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getIdAsl() {
        return idAsl;
    }

    /**
     * Imposta il valore della proprietÓ idAsl.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setIdAsl(BigDecimal value) {
        this.idAsl = value;
    }

    /**
     * Recupera il valore della proprietÓ dataFineSospensione.
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
     * Imposta il valore della proprietÓ dataFineSospensione.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataFineSospensione(XMLGregorianCalendar value) {
        this.dataFineSospensione = value;
    }

    /**
     * Recupera il valore della proprietÓ idUserUpdate.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getIdUserUpdate() {
        return idUserUpdate;
    }

    /**
     * Imposta il valore della proprietÓ idUserUpdate.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setIdUserUpdate(BigDecimal value) {
        this.idUserUpdate = value;
    }

    /**
     * Recupera il valore della proprietÓ dataUpdate.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataUpdate() {
        return dataUpdate;
    }

    /**
     * Imposta il valore della proprietÓ dataUpdate.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataUpdate(XMLGregorianCalendar value) {
        this.dataUpdate = value;
    }

    /**
     * Recupera il valore della proprietÓ etaRiservata.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getEtaRiservata() {
        return etaRiservata;
    }

    /**
     * Imposta il valore della proprietÓ etaRiservata.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setEtaRiservata(BigDecimal value) {
        this.etaRiservata = value;
    }

}
