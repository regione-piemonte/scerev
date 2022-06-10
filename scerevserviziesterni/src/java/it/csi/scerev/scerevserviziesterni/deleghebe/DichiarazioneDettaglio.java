/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.scerev.scerevserviziesterni.deleghebe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per dichiarazioneDettaglio complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="dichiarazioneDettaglio">
 *   &lt;complexContent>
 *     &lt;extension base="{http://deleghebe.csi.it/}entita">
 *       &lt;sequence>
 *         &lt;element name="cittadino1" type="{http://deleghebe.csi.it/}cittadino" minOccurs="0"/>
 *         &lt;element name="cittadino2" type="{http://deleghebe.csi.it/}cittadino" minOccurs="0"/>
 *         &lt;element name="dichiarazione" type="{http://deleghebe.csi.it/}dichiarazione" minOccurs="0"/>
 *         &lt;element name="documento" type="{http://deleghebe.csi.it/}documento" minOccurs="0"/>
 *         &lt;element name="notaRevocaBlocco" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ruoloCittadino1" type="{http://deleghebe.csi.it/}dichiarazioneRuolo" minOccurs="0"/>
 *         &lt;element name="ruoloCittadino2" type="{http://deleghebe.csi.it/}dichiarazioneRuolo" minOccurs="0"/>
 *         &lt;element name="stato" type="{http://deleghebe.csi.it/}dichiarazioneDettaglioStato" minOccurs="0"/>
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
@XmlType(name = "dichiarazioneDettaglio", propOrder = {
    "cittadino1",
    "cittadino2",
    "dichiarazione",
    "documento",
    "notaRevocaBlocco",
    "ruoloCittadino1",
    "ruoloCittadino2",
    "stato",
    "uuid"
})
public class DichiarazioneDettaglio
    extends Entita
{

    protected Cittadino cittadino1;
    protected Cittadino cittadino2;
    protected Dichiarazione dichiarazione;
    protected Documento documento;
    protected String notaRevocaBlocco;
    protected DichiarazioneRuolo ruoloCittadino1;
    protected DichiarazioneRuolo ruoloCittadino2;
    protected DichiarazioneDettaglioStato stato;
    protected String uuid;

    /**
     * Recupera il valore della proprietà cittadino1.
     * 
     * @return
     *     possible object is
     *     {@link Cittadino }
     *     
     */
    public Cittadino getCittadino1() {
        return cittadino1;
    }

    /**
     * Imposta il valore della proprietà cittadino1.
     * 
     * @param value
     *     allowed object is
     *     {@link Cittadino }
     *     
     */
    public void setCittadino1(Cittadino value) {
        this.cittadino1 = value;
    }

    /**
     * Recupera il valore della proprietà cittadino2.
     * 
     * @return
     *     possible object is
     *     {@link Cittadino }
     *     
     */
    public Cittadino getCittadino2() {
        return cittadino2;
    }

    /**
     * Imposta il valore della proprietà cittadino2.
     * 
     * @param value
     *     allowed object is
     *     {@link Cittadino }
     *     
     */
    public void setCittadino2(Cittadino value) {
        this.cittadino2 = value;
    }

    /**
     * Recupera il valore della proprietà dichiarazione.
     * 
     * @return
     *     possible object is
     *     {@link Dichiarazione }
     *     
     */
    public Dichiarazione getDichiarazione() {
        return dichiarazione;
    }

    /**
     * Imposta il valore della proprietà dichiarazione.
     * 
     * @param value
     *     allowed object is
     *     {@link Dichiarazione }
     *     
     */
    public void setDichiarazione(Dichiarazione value) {
        this.dichiarazione = value;
    }

    /**
     * Recupera il valore della proprietà documento.
     * 
     * @return
     *     possible object is
     *     {@link Documento }
     *     
     */
    public Documento getDocumento() {
        return documento;
    }

    /**
     * Imposta il valore della proprietà documento.
     * 
     * @param value
     *     allowed object is
     *     {@link Documento }
     *     
     */
    public void setDocumento(Documento value) {
        this.documento = value;
    }

    /**
     * Recupera il valore della proprietà notaRevocaBlocco.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNotaRevocaBlocco() {
        return notaRevocaBlocco;
    }

    /**
     * Imposta il valore della proprietà notaRevocaBlocco.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNotaRevocaBlocco(String value) {
        this.notaRevocaBlocco = value;
    }

    /**
     * Recupera il valore della proprietà ruoloCittadino1.
     * 
     * @return
     *     possible object is
     *     {@link DichiarazioneRuolo }
     *     
     */
    public DichiarazioneRuolo getRuoloCittadino1() {
        return ruoloCittadino1;
    }

    /**
     * Imposta il valore della proprietà ruoloCittadino1.
     * 
     * @param value
     *     allowed object is
     *     {@link DichiarazioneRuolo }
     *     
     */
    public void setRuoloCittadino1(DichiarazioneRuolo value) {
        this.ruoloCittadino1 = value;
    }

    /**
     * Recupera il valore della proprietà ruoloCittadino2.
     * 
     * @return
     *     possible object is
     *     {@link DichiarazioneRuolo }
     *     
     */
    public DichiarazioneRuolo getRuoloCittadino2() {
        return ruoloCittadino2;
    }

    /**
     * Imposta il valore della proprietà ruoloCittadino2.
     * 
     * @param value
     *     allowed object is
     *     {@link DichiarazioneRuolo }
     *     
     */
    public void setRuoloCittadino2(DichiarazioneRuolo value) {
        this.ruoloCittadino2 = value;
    }

    /**
     * Recupera il valore della proprietà stato.
     * 
     * @return
     *     possible object is
     *     {@link DichiarazioneDettaglioStato }
     *     
     */
    public DichiarazioneDettaglioStato getStato() {
        return stato;
    }

    /**
     * Imposta il valore della proprietà stato.
     * 
     * @param value
     *     allowed object is
     *     {@link DichiarazioneDettaglioStato }
     *     
     */
    public void setStato(DichiarazioneDettaglioStato value) {
        this.stato = value;
    }

    /**
     * Recupera il valore della proprietà uuid.
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
     * Imposta il valore della proprietà uuid.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUuid(String value) {
        this.uuid = value;
    }

}
