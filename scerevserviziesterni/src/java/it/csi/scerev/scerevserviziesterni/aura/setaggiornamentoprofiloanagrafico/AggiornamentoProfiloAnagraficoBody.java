/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.scerev.scerevserviziesterni.aura.setaggiornamentoprofiloanagrafico;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import it.csi.scerev.scerevserviziesterni.aura.setprofilosanitario.ProfiloSanitarioBody;


/**
 * <p>Classe Java per AggiornamentoProfiloAnagraficoBody complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="AggiornamentoProfiloAnagraficoBody">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DatiPrimari" type="{http://SetAggiornamentoProfiloAnagrafico.central.services.auraws.aura.csi.it}AggiornamentoProfiloAnagraficoDatiPrimari"/>
 *         &lt;element name="DatiResidenza" type="{http://SetAggiornamentoProfiloAnagrafico.central.services.auraws.aura.csi.it}AggiornamentoProfiloAnagraficoResidenza"/>
 *         &lt;element name="DatiDomicilio" type="{http://SetAggiornamentoProfiloAnagrafico.central.services.auraws.aura.csi.it}AggiornamentoProfiloAnagraficoDomicilio" minOccurs="0"/>
 *         &lt;element name="AltreInfo" type="{http://SetAggiornamentoProfiloAnagrafico.central.services.auraws.aura.csi.it}AggiornamentoProfiloAnagraficoAltreInfo" minOccurs="0"/>
 *         &lt;element name="profiloSanitarioBody" type="{http://SetAggiornamentoProfiloAnagrafico.central.services.auraws.aura.csi.it}profiloSanitarioBody" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AggiornamentoProfiloAnagraficoBody", propOrder = {
    "datiPrimari",
    "datiResidenza",
    "datiDomicilio",
    "altreInfo",
    "profiloSanitarioBody"
})
public class AggiornamentoProfiloAnagraficoBody {

    @XmlElement(name = "DatiPrimari", required = true)
    protected AggiornamentoProfiloAnagraficoDatiPrimari datiPrimari;
    @XmlElement(name = "DatiResidenza", required = true)
    protected AggiornamentoProfiloAnagraficoResidenza datiResidenza;
    @XmlElement(name = "DatiDomicilio")
    protected AggiornamentoProfiloAnagraficoDomicilio datiDomicilio;
    @XmlElement(name = "AltreInfo")
    protected AggiornamentoProfiloAnagraficoAltreInfo altreInfo;
    @XmlElement(name = "ProfiloSanitarioBody")
    protected ProfiloSanitarioBody profiloSanitarioBody;

    /**
     * Recupera il valore della proprietà datiPrimari.
     * 
     * @return
     *     possible object is
     *     {@link AggiornamentoProfiloAnagraficoDatiPrimari }
     *     
     */
    public AggiornamentoProfiloAnagraficoDatiPrimari getDatiPrimari() {
        return datiPrimari;
    }

    /**
     * Imposta il valore della proprietà datiPrimari.
     * 
     * @param value
     *     allowed object is
     *     {@link AggiornamentoProfiloAnagraficoDatiPrimari }
     *     
     */
    public void setDatiPrimari(AggiornamentoProfiloAnagraficoDatiPrimari value) {
        this.datiPrimari = value;
    }

    /**
     * Recupera il valore della proprietà datiResidenza.
     * 
     * @return
     *     possible object is
     *     {@link AggiornamentoProfiloAnagraficoResidenza }
     *     
     */
    public AggiornamentoProfiloAnagraficoResidenza getDatiResidenza() {
        return datiResidenza;
    }

    /**
     * Imposta il valore della proprietà datiResidenza.
     * 
     * @param value
     *     allowed object is
     *     {@link AggiornamentoProfiloAnagraficoResidenza }
     *     
     */
    public void setDatiResidenza(AggiornamentoProfiloAnagraficoResidenza value) {
        this.datiResidenza = value;
    }

    /**
     * Recupera il valore della proprietà datiDomicilio.
     * 
     * @return
     *     possible object is
     *     {@link AggiornamentoProfiloAnagraficoDomicilio }
     *     
     */
    public AggiornamentoProfiloAnagraficoDomicilio getDatiDomicilio() {
        return datiDomicilio;
    }

    /**
     * Imposta il valore della proprietà datiDomicilio.
     * 
     * @param value
     *     allowed object is
     *     {@link AggiornamentoProfiloAnagraficoDomicilio }
     *     
     */
    public void setDatiDomicilio(AggiornamentoProfiloAnagraficoDomicilio value) {
        this.datiDomicilio = value;
    }

    /**
     * Recupera il valore della proprietà altreInfo.
     * 
     * @return
     *     possible object is
     *     {@link AggiornamentoProfiloAnagraficoAltreInfo }
     *     
     */
    public AggiornamentoProfiloAnagraficoAltreInfo getAltreInfo() {
        return altreInfo;
    }

    /**
     * Imposta il valore della proprietà altreInfo.
     * 
     * @param value
     *     allowed object is
     *     {@link AggiornamentoProfiloAnagraficoAltreInfo }
     *     
     */
    public void setAltreInfo(AggiornamentoProfiloAnagraficoAltreInfo value) {
        this.altreInfo = value;
    }

    /**
     * Recupera il valore della proprietà profiloSanitarioBody.
     * 
     * @return
     *     possible object is
     *     {@link ProfiloSanitarioBody }
     *     
     */
	public ProfiloSanitarioBody getProfiloSanitarioBody() {
		return profiloSanitarioBody;
	}

	/**
     * Imposta il valore della proprietà profiloSanitarioBody.
     * 
     * @param value
     *     allowed object is
     *     {@link ProfiloSanitarioBody }
     *     
     */
	public void setProfiloSanitarioBody(ProfiloSanitarioBody profiloSanitarioBody) {
		this.profiloSanitarioBody = profiloSanitarioBody;
	}
    
    

}
