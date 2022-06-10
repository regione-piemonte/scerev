/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.scerev.scerevserviziesterni.deleghebe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per serviceRequest complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="serviceRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="richiedente" type="{http://deleghebe.csi.it/}richiedente" form="unqualified"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "serviceRequest", propOrder = {
    "richiedente"
})
@XmlSeeAlso({
    GetDeleganti.class,
    GetDelegati.class,
    SaveDelegati.class,
    RinunciaDelegato.class,
    IsAlive.class,
    InserisciCittadino.class,
    AggiornaDelega.class,
    RicercaDeleghe.class,
    InserisciDichiarazione.class,
    AggiornaDichiarazione.class,
    RicercaDichiarazioni.class,
    ElencoGenitori.class,
    RicercaCfOperatore.class,
    InserisciDelega.class,
    AggiornaCittadino.class,
    RicercaCittadini.class
})
public class ServiceRequest {

    @XmlElement(namespace = "", required = true)
    protected Richiedente richiedente;

    /**
     * Recupera il valore della propriet� richiedente.
     * 
     * @return
     *     possible object is
     *     {@link Richiedente }
     *     
     */
    public Richiedente getRichiedente() {
        return richiedente;
    }

    /**
     * Imposta il valore della propriet� richiedente.
     * 
     * @param value
     *     allowed object is
     *     {@link Richiedente }
     *     
     */
    public void setRichiedente(Richiedente value) {
        this.richiedente = value;
    }

}
