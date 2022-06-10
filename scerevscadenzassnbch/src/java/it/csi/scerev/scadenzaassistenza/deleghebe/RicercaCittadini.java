/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.scerev.scadenzaassistenza.deleghebe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per ricercaCittadini complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ricercaCittadini">
 *   &lt;complexContent>
 *     &lt;extension base="{http://deleghebe.csi.it/}serviceRequest">
 *       &lt;sequence>
 *         &lt;element name="cittadino" type="{http://deleghebe.csi.it/}cittadino" minOccurs="0" form="unqualified"/>
 *         &lt;element name="dichiarazioneRuolo" type="{http://deleghebe.csi.it/}dichiarazioneRuolo" minOccurs="0" form="unqualified"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ricercaCittadini", propOrder = {
    "cittadino",
    "dichiarazioneRuolo"
})
public class RicercaCittadini
    extends ServiceRequest
{

    @XmlElement(namespace = "")
    protected Cittadino cittadino;
    @XmlElement(namespace = "")
    protected DichiarazioneRuolo dichiarazioneRuolo;

    /**
     * Recupera il valore della proprieta cittadino.
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
     * Imposta il valore della proprieta cittadino.
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
     * Recupera il valore della proprieta dichiarazioneRuolo.
     * 
     * @return
     *     possible object is
     *     {@link DichiarazioneRuolo }
     *     
     */
    public DichiarazioneRuolo getDichiarazioneRuolo() {
        return dichiarazioneRuolo;
    }

    /**
     * Imposta il valore della proprieta dichiarazioneRuolo.
     * 
     * @param value
     *     allowed object is
     *     {@link DichiarazioneRuolo }
     *     
     */
    public void setDichiarazioneRuolo(DichiarazioneRuolo value) {
        this.dichiarazioneRuolo = value;
    }

}
