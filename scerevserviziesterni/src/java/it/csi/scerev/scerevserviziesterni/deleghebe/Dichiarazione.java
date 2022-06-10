/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.scerev.scerevserviziesterni.deleghebe;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per dichiarazione complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="dichiarazione">
 *   &lt;complexContent>
 *     &lt;extension base="{http://deleghebe.csi.it/}entita">
 *       &lt;sequence>
 *         &lt;element name="dettagli" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="dettaglio" type="{http://deleghebe.csi.it/}dichiarazioneDettaglio" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="cittadino" type="{http://deleghebe.csi.it/}cittadino" minOccurs="0"/>
 *         &lt;element name="modo" type="{http://deleghebe.csi.it/}modoDichiarazione" minOccurs="0"/>
 *         &lt;element name="stato" type="{http://deleghebe.csi.it/}statoDichiarazione" minOccurs="0"/>
 *         &lt;element name="tipo" type="{http://deleghebe.csi.it/}tipoDichiarazione" minOccurs="0"/>
 *         &lt;element name="uuid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dichiarazione", propOrder = {
    "dettagli",
    "cittadino",
    "modo",
    "stato",
    "tipo",
    "uuid"
})
public class Dichiarazione
    extends Entita
{

    protected Dichiarazione.Dettagli dettagli;
    protected Cittadino cittadino;
    protected ModoDichiarazione modo;
    protected StatoDichiarazione stato;
    protected TipoDichiarazione tipo;
    protected String uuid;

    /**
     * Recupera il valore della propriet� dettagli.
     * 
     * @return
     *     possible object is
     *     {@link Dichiarazione.Dettagli }
     *     
     */
    public Dichiarazione.Dettagli getDettagli() {
        return dettagli;
    }

    /**
     * Imposta il valore della propriet� dettagli.
     * 
     * @param value
     *     allowed object is
     *     {@link Dichiarazione.Dettagli }
     *     
     */
    public void setDettagli(Dichiarazione.Dettagli value) {
        this.dettagli = value;
    }

    /**
     * Recupera il valore della propriet� cittadino.
     * 
     * @return
     *     possible object is
     *     {@link Cittadino }
     *     
     */
    public Cittadino getCittadino() {
        return cittadino;
    }

    /**
     * Imposta il valore della propriet� cittadino.
     * 
     * @param value
     *     allowed object is
     *     {@link Cittadino }
     *     
     */
    public void setCittadino(Cittadino value) {
        this.cittadino = value;
    }

    /**
     * Recupera il valore della propriet� modo.
     * 
     * @return
     *     possible object is
     *     {@link ModoDichiarazione }
     *     
     */
    public ModoDichiarazione getModo() {
        return modo;
    }

    /**
     * Imposta il valore della propriet� modo.
     * 
     * @param value
     *     allowed object is
     *     {@link ModoDichiarazione }
     *     
     */
    public void setModo(ModoDichiarazione value) {
        this.modo = value;
    }

    /**
     * Recupera il valore della propriet� stato.
     * 
     * @return
     *     possible object is
     *     {@link StatoDichiarazione }
     *     
     */
    public StatoDichiarazione getStato() {
        return stato;
    }

    /**
     * Imposta il valore della propriet� stato.
     * 
     * @param value
     *     allowed object is
     *     {@link StatoDichiarazione }
     *     
     */
    public void setStato(StatoDichiarazione value) {
        this.stato = value;
    }

    /**
     * Recupera il valore della propriet� tipo.
     * 
     * @return
     *     possible object is
     *     {@link TipoDichiarazione }
     *     
     */
    public TipoDichiarazione getTipo() {
        return tipo;
    }

    /**
     * Imposta il valore della propriet� tipo.
     * 
     * @param value
     *     allowed object is
     *     {@link TipoDichiarazione }
     *     
     */
    public void setTipo(TipoDichiarazione value) {
        this.tipo = value;
    }

    /**
     * Recupera il valore della propriet� uuid.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * Imposta il valore della propriet� uuid.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUuid(String value) {
        this.uuid = value;
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
     *         &lt;element name="dettaglio" type="{http://deleghebe.csi.it/}dichiarazioneDettaglio" maxOccurs="unbounded" minOccurs="0"/>
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
        "dettaglio"
    })
    public static class Dettagli {

        protected List<DichiarazioneDettaglio> dettaglio;

        /**
         * Gets the value of the dettaglio property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the dettaglio property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getDettaglio().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link DichiarazioneDettaglio }
         * 
         * 
         */
        public List<DichiarazioneDettaglio> getDettaglio() {
            if (dettaglio == null) {
                dettaglio = new ArrayList<DichiarazioneDettaglio>();
            }
            return this.dettaglio;
        }

    }

}
