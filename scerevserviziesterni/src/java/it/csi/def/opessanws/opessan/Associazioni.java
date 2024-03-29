/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.def.opessanws.opessan;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java per Associazioni complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="Associazioni">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="associazioneAggregazione" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="codAzienda" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="codice" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="denominazione" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="tipo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="cognomeRef" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="nomeRef" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="indirizzo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="numCivico" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="cap" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="comune" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="tel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="dataCessazione" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *                   &lt;element name="dataInizioAdesione" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *                   &lt;element name="dataFineAdesione" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
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
@XmlType(name = "Associazioni", propOrder = {
    "associazioneAggregazione"
})
public class Associazioni {

    @XmlElement(nillable = true)
    protected List<Associazioni.AssociazioneAggregazione> associazioneAggregazione;

    /**
     * Gets the value of the associazioneAggregazione property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the associazioneAggregazione property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAssociazioneAggregazione().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Associazioni.AssociazioneAggregazione }
     * 
     * 
     */
    public List<Associazioni.AssociazioneAggregazione> getAssociazioneAggregazione() {
        if (associazioneAggregazione == null) {
            associazioneAggregazione = new ArrayList<Associazioni.AssociazioneAggregazione>();
        }
        return this.associazioneAggregazione;
    }


    /**
     * <p>Classe Java per anonymous complex type.
     * 
     * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="codAzienda" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="codice" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="denominazione" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="tipo" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="cognomeRef" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="nomeRef" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="indirizzo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="numCivico" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="cap" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="comune" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="tel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="dataCessazione" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
     *         &lt;element name="dataInizioAdesione" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
     *         &lt;element name="dataFineAdesione" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "codAzienda",
        "codice",
        "denominazione",
        "tipo",
        "cognomeRef",
        "nomeRef",
        "indirizzo",
        "numCivico",
        "cap",
        "comune",
        "tel",
        "dataCessazione",
        "dataInizioAdesione",
        "dataFineAdesione"
    })
    public static class AssociazioneAggregazione {

        @XmlElement(required = true)
        protected String codAzienda;
        @XmlElement(required = true)
        protected String codice;
        @XmlElement(required = true)
        protected String denominazione;
        @XmlElement(required = true)
        protected String tipo;
        protected String cognomeRef;
        protected String nomeRef;
        protected String indirizzo;
        protected String numCivico;
        protected String cap;
        protected String comune;
        protected String tel;
        @XmlSchemaType(name = "dateTime")
        protected XMLGregorianCalendar dataCessazione;
        @XmlSchemaType(name = "dateTime")
        protected XMLGregorianCalendar dataInizioAdesione;
        @XmlSchemaType(name = "dateTime")
        protected XMLGregorianCalendar dataFineAdesione;

        /**
         * Recupera il valore della proprietÓ codAzienda.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCodAzienda() {
            return codAzienda;
        }

        /**
         * Imposta il valore della proprietÓ codAzienda.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCodAzienda(String value) {
            this.codAzienda = value;
        }

        /**
         * Recupera il valore della proprietÓ codice.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCodice() {
            return codice;
        }

        /**
         * Imposta il valore della proprietÓ codice.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCodice(String value) {
            this.codice = value;
        }

        /**
         * Recupera il valore della proprietÓ denominazione.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDenominazione() {
            return denominazione;
        }

        /**
         * Imposta il valore della proprietÓ denominazione.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDenominazione(String value) {
            this.denominazione = value;
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
         * Recupera il valore della proprietÓ cognomeRef.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCognomeRef() {
            return cognomeRef;
        }

        /**
         * Imposta il valore della proprietÓ cognomeRef.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCognomeRef(String value) {
            this.cognomeRef = value;
        }

        /**
         * Recupera il valore della proprietÓ nomeRef.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNomeRef() {
            return nomeRef;
        }

        /**
         * Imposta il valore della proprietÓ nomeRef.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNomeRef(String value) {
            this.nomeRef = value;
        }

        /**
         * Recupera il valore della proprietÓ indirizzo.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getIndirizzo() {
            return indirizzo;
        }

        /**
         * Imposta il valore della proprietÓ indirizzo.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setIndirizzo(String value) {
            this.indirizzo = value;
        }

        /**
         * Recupera il valore della proprietÓ numCivico.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNumCivico() {
            return numCivico;
        }

        /**
         * Imposta il valore della proprietÓ numCivico.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNumCivico(String value) {
            this.numCivico = value;
        }

        /**
         * Recupera il valore della proprietÓ cap.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCap() {
            return cap;
        }

        /**
         * Imposta il valore della proprietÓ cap.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCap(String value) {
            this.cap = value;
        }

        /**
         * Recupera il valore della proprietÓ comune.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getComune() {
            return comune;
        }

        /**
         * Imposta il valore della proprietÓ comune.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setComune(String value) {
            this.comune = value;
        }

        /**
         * Recupera il valore della proprietÓ tel.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTel() {
            return tel;
        }

        /**
         * Imposta il valore della proprietÓ tel.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTel(String value) {
            this.tel = value;
        }

        /**
         * Recupera il valore della proprietÓ dataCessazione.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getDataCessazione() {
            return dataCessazione;
        }

        /**
         * Imposta il valore della proprietÓ dataCessazione.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setDataCessazione(XMLGregorianCalendar value) {
            this.dataCessazione = value;
        }

        /**
         * Recupera il valore della proprietÓ dataInizioAdesione.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getDataInizioAdesione() {
            return dataInizioAdesione;
        }

        /**
         * Imposta il valore della proprietÓ dataInizioAdesione.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setDataInizioAdesione(XMLGregorianCalendar value) {
            this.dataInizioAdesione = value;
        }

        /**
         * Recupera il valore della proprietÓ dataFineAdesione.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getDataFineAdesione() {
            return dataFineAdesione;
        }

        /**
         * Imposta il valore della proprietÓ dataFineAdesione.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setDataFineAdesione(XMLGregorianCalendar value) {
            this.dataFineAdesione = value;
        }

    }

}
