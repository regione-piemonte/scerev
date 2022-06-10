/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.scerev.scerevserviziesterni.aura.setinserimentoprofiloanagrafico;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per InserimentoProfiloAnagraficoBody complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="InserimentoProfiloAnagraficoBody">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DatiPrimari" type="{http://SetInserimentoProfiloAnagrafico.central.services.auraws.aura.csi.it}InserimentoProfiloAnagraficoDatiPrimari"/>
 *         &lt;element name="DatiResidenza" type="{http://SetInserimentoProfiloAnagrafico.central.services.auraws.aura.csi.it}InserimentoProfiloAnagraficoResidenza"/>
 *         &lt;element name="DatiDomicilio" type="{http://SetInserimentoProfiloAnagrafico.central.services.auraws.aura.csi.it}InserimentoProfiloAnagraficoDomicilio" minOccurs="0"/>
 *         &lt;element name="AltreInfo" type="{http://SetInserimentoProfiloAnagrafico.central.services.auraws.aura.csi.it}InserimentoProfiloAnagraficoAltreInfo" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InserimentoProfiloAnagraficoBody", propOrder = {
    "datiPrimari",
    "datiResidenza",
    "datiDomicilio",
    "altreInfo"
})
public class InserimentoProfiloAnagraficoBody {

    @XmlElement(name = "DatiPrimari", required = true)
    protected InserimentoProfiloAnagraficoDatiPrimari datiPrimari;
    @XmlElement(name = "DatiResidenza", required = true)
    protected InserimentoProfiloAnagraficoResidenza datiResidenza;
    @XmlElement(name = "DatiDomicilio")
    protected InserimentoProfiloAnagraficoDomicilio datiDomicilio;
    @XmlElement(name = "AltreInfo")
    protected InserimentoProfiloAnagraficoAltreInfo altreInfo;

    /**
     * Recupera il valore della proprietà datiPrimari.
     * 
     * @return
     *     possible object is
     *     {@link InserimentoProfiloAnagraficoDatiPrimari }
     *     
     */
    public InserimentoProfiloAnagraficoDatiPrimari getDatiPrimari() {
        return datiPrimari;
    }

    /**
     * Imposta il valore della proprietà datiPrimari.
     * 
     * @param value
     *     allowed object is
     *     {@link InserimentoProfiloAnagraficoDatiPrimari }
     *     
     */
    public void setDatiPrimari(InserimentoProfiloAnagraficoDatiPrimari value) {
        this.datiPrimari = value;
    }

    /**
     * Recupera il valore della proprietà datiResidenza.
     * 
     * @return
     *     possible object is
     *     {@link InserimentoProfiloAnagraficoResidenza }
     *     
     */
    public InserimentoProfiloAnagraficoResidenza getDatiResidenza() {
        return datiResidenza;
    }

    /**
     * Imposta il valore della proprietà datiResidenza.
     * 
     * @param value
     *     allowed object is
     *     {@link InserimentoProfiloAnagraficoResidenza }
     *     
     */
    public void setDatiResidenza(InserimentoProfiloAnagraficoResidenza value) {
        this.datiResidenza = value;
    }

    /**
     * Recupera il valore della proprietà datiDomicilio.
     * 
     * @return
     *     possible object is
     *     {@link InserimentoProfiloAnagraficoDomicilio }
     *     
     */
    public InserimentoProfiloAnagraficoDomicilio getDatiDomicilio() {
        return datiDomicilio;
    }

    /**
     * Imposta il valore della proprietà datiDomicilio.
     * 
     * @param value
     *     allowed object is
     *     {@link InserimentoProfiloAnagraficoDomicilio }
     *     
     */
    public void setDatiDomicilio(InserimentoProfiloAnagraficoDomicilio value) {
        this.datiDomicilio = value;
    }

    /**
     * Recupera il valore della proprietà altreInfo.
     * 
     * @return
     *     possible object is
     *     {@link InserimentoProfiloAnagraficoAltreInfo }
     *     
     */
    public InserimentoProfiloAnagraficoAltreInfo getAltreInfo() {
        return altreInfo;
    }

    /**
     * Imposta il valore della proprietà altreInfo.
     * 
     * @param value
     *     allowed object is
     *     {@link InserimentoProfiloAnagraficoAltreInfo }
     *     
     */
    public void setAltreInfo(InserimentoProfiloAnagraficoAltreInfo value) {
        this.altreInfo = value;
    }

}
