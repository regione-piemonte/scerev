/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.scerev.scerevserviziesterni.aura.get;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per InfoAnagraficheNew complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="InfoAnagraficheNew">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="datiPrimari" type="{http://AnagrafeSanitaria.central.services.auraws.aura.csi.it}DatiPrimari" minOccurs="0"/>
 *         &lt;element name="domicilio" type="{http://AnagrafeSanitaria.central.services.auraws.aura.csi.it}DatiSecondariNew" minOccurs="0"/>
 *         &lt;element name="residenza" type="{http://AnagrafeSanitaria.central.services.auraws.aura.csi.it}DatiSecondariNew" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InfoAnagraficheNew", propOrder = {
    "datiPrimari",
    "domicilio",
    "residenza"
})
public class InfoAnagraficheNew {

    protected DatiPrimari datiPrimari;
    protected DatiSecondariNew domicilio;
    protected DatiSecondariNew residenza;

    /**
     * Recupera il valore della propriet� datiPrimari.
     * 
     * @return
     *     possible object is
     *     {@link DatiPrimari }
     *     
     */
    public DatiPrimari getDatiPrimari() {
        return datiPrimari;
    }

    /**
     * Imposta il valore della propriet� datiPrimari.
     * 
     * @param value
     *     allowed object is
     *     {@link DatiPrimari }
     *     
     */
    public void setDatiPrimari(DatiPrimari value) {
        this.datiPrimari = value;
    }

    /**
     * Recupera il valore della propriet� domicilio.
     * 
     * @return
     *     possible object is
     *     {@link DatiSecondariNew }
     *     
     */
    public DatiSecondariNew getDomicilio() {
        return domicilio;
    }

    /**
     * Imposta il valore della propriet� domicilio.
     * 
     * @param value
     *     allowed object is
     *     {@link DatiSecondariNew }
     *     
     */
    public void setDomicilio(DatiSecondariNew value) {
        this.domicilio = value;
    }

    /**
     * Recupera il valore della propriet� residenza.
     * 
     * @return
     *     possible object is
     *     {@link DatiSecondariNew }
     *     
     */
    public DatiSecondariNew getResidenza() {
        return residenza;
    }

    /**
     * Imposta il valore della propriet� residenza.
     * 
     * @param value
     *     allowed object is
     *     {@link DatiSecondariNew }
     *     
     */
    public void setResidenza(DatiSecondariNew value) {
        this.residenza = value;
    }

}
