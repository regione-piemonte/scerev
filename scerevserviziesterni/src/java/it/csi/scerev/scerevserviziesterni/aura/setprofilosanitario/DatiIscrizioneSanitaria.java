/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.scerev.scerevserviziesterni.aura.setprofilosanitario;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java per DatiIscrizioneSanitaria complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="DatiIscrizioneSanitaria">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="dataInizioASL" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="dataFineASL" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="codTipoProfiloSanitario" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="2"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="dataDecorrenza" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="DataFineSospensione" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="Simulazione" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="DerogaPediatra" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="MMG" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="DerogaAmbitoTerritoriale" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="DerogaAssociazione" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="DerogaMassimale" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="DerogaMassimaleTemporaneo" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="Note" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DatiIscrizioneSanitaria", propOrder = {
    "dataInizioASL",
    "dataFineASL",
    "codTipoProfiloSanitario",
    "dataDecorrenza",
    "dataFineSospensione",
    "simulazione",
    "derogaPediatra",
    "mmg",
    "derogaAmbitoTerritoriale",
    "derogaAssociazione",
    "derogaMassimale",
    "derogaMassimaleTemporaneo",
    "note"
})
public class DatiIscrizioneSanitaria {

    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dataInizioASL;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dataFineASL;
    protected String codTipoProfiloSanitario;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dataDecorrenza;
    @XmlElement(name = "DataFineSospensione")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dataFineSospensione;
    @XmlElement(name = "Simulazione")
    protected Long simulazione;
    @XmlElement(name = "DerogaPediatra")
    protected Long derogaPediatra;
    @XmlElement(name = "MMG")
    protected Long mmg;
    @XmlElement(name = "DerogaAmbitoTerritoriale")
    protected Long derogaAmbitoTerritoriale;
    @XmlElement(name = "DerogaAssociazione")
    protected Long derogaAssociazione;
    @XmlElement(name = "DerogaMassimale")
    protected Long derogaMassimale;
    @XmlElement(name = "DerogaMassimaleTemporaneo")
    protected Long derogaMassimaleTemporaneo;
    @XmlElement(name = "Note")
    protected String note;

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

    /**
     * Recupera il valore della proprietà simulazione.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getSimulazione() {
        return simulazione;
    }

    /**
     * Imposta il valore della proprietà simulazione.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setSimulazione(Long value) {
        this.simulazione = value;
    }

    /**
     * Recupera il valore della proprietà derogaPediatra.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getDerogaPediatra() {
        return derogaPediatra;
    }

    /**
     * Imposta il valore della proprietà derogaPediatra.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setDerogaPediatra(Long value) {
        this.derogaPediatra = value;
    }

    /**
     * Recupera il valore della proprietà mmg.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getMMG() {
        return mmg;
    }

    /**
     * Imposta il valore della proprietà mmg.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setMMG(Long value) {
        this.mmg = value;
    }

    /**
     * Recupera il valore della proprietà derogaAmbitoTerritoriale.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getDerogaAmbitoTerritoriale() {
        return derogaAmbitoTerritoriale;
    }

    /**
     * Imposta il valore della proprietà derogaAmbitoTerritoriale.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setDerogaAmbitoTerritoriale(Long value) {
        this.derogaAmbitoTerritoriale = value;
    }

    /**
     * Recupera il valore della proprietà derogaAssociazione.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getDerogaAssociazione() {
        return derogaAssociazione;
    }

    /**
     * Imposta il valore della proprietà derogaAssociazione.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setDerogaAssociazione(Long value) {
        this.derogaAssociazione = value;
    }

    /**
     * Recupera il valore della proprietà derogaMassimale.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getDerogaMassimale() {
        return derogaMassimale;
    }

    /**
     * Imposta il valore della proprietà derogaMassimale.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setDerogaMassimale(Long value) {
        this.derogaMassimale = value;
    }

    /**
     * Recupera il valore della proprietà derogaMassimaleTemporaneo.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getDerogaMassimaleTemporaneo() {
        return derogaMassimaleTemporaneo;
    }

    /**
     * Imposta il valore della proprietà derogaMassimaleTemporaneo.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setDerogaMassimaleTemporaneo(Long value) {
        this.derogaMassimaleTemporaneo = value;
    }

    /**
     * Recupera il valore della proprietà note.
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
     * Imposta il valore della proprietà note.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNote(String value) {
        this.note = value;
    }

}
