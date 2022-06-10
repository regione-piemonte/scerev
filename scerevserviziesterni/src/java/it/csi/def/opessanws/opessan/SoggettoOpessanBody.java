/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.def.opessanws.opessan;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per SoggettoOpessanBody complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="SoggettoOpessanBody">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idAura" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="codiceFiscaleAura" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="infoRappLavoro" type="{http://opessan.opessanws.def.csi.it/}InfoRapportiLavoro" minOccurs="0"/>
 *         &lt;element name="infoAnag" type="{http://opessan.opessanws.def.csi.it/}InfoAnagrafiche" minOccurs="0"/>
 *         &lt;element name="altreInfo" type="{http://opessan.opessanws.def.csi.it/}AltreInfos" minOccurs="0"/>
 *         &lt;element name="infoProf" type="{http://opessan.opessanws.def.csi.it/}InfoProfessionali" minOccurs="0"/>
 *         &lt;element name="associazioniAggregazioni" type="{http://opessan.opessanws.def.csi.it/}Associazioni" minOccurs="0"/>
 *         &lt;element name="elencoStudiMedici" type="{http://opessan.opessanws.def.csi.it/}StudiMedici" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SoggettoOpessanBody", propOrder = {
    "idAura",
    "codiceFiscaleAura",
    "infoRappLavoro",
    "infoAnag",
    "altreInfo",
    "infoProf",
    "associazioniAggregazioni",
    "elencoStudiMedici"
})
public class SoggettoOpessanBody {

    protected BigDecimal idAura;
    protected String codiceFiscaleAura;
    protected InfoRapportiLavoro infoRappLavoro;
    protected InfoAnagrafiche infoAnag;
    protected AltreInfos altreInfo;
    protected InfoProfessionali infoProf;
    protected Associazioni associazioniAggregazioni;
    protected StudiMedici elencoStudiMedici;

    /**
     * Recupera il valore della proprietà idAura.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getIdAura() {
        return idAura;
    }

    /**
     * Imposta il valore della proprietà idAura.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setIdAura(BigDecimal value) {
        this.idAura = value;
    }

    /**
     * Recupera il valore della proprietà codiceFiscaleAura.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceFiscaleAura() {
        return codiceFiscaleAura;
    }

    /**
     * Imposta il valore della proprietà codiceFiscaleAura.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceFiscaleAura(String value) {
        this.codiceFiscaleAura = value;
    }

    /**
     * Recupera il valore della proprietà infoRappLavoro.
     * 
     * @return
     *     possible object is
     *     {@link InfoRapportiLavoro }
     *     
     */
    public InfoRapportiLavoro getInfoRappLavoro() {
        return infoRappLavoro;
    }

    /**
     * Imposta il valore della proprietà infoRappLavoro.
     * 
     * @param value
     *     allowed object is
     *     {@link InfoRapportiLavoro }
     *     
     */
    public void setInfoRappLavoro(InfoRapportiLavoro value) {
        this.infoRappLavoro = value;
    }

    /**
     * Recupera il valore della proprietà infoAnag.
     * 
     * @return
     *     possible object is
     *     {@link InfoAnagrafiche }
     *     
     */
    public InfoAnagrafiche getInfoAnag() {
        return infoAnag;
    }

    /**
     * Imposta il valore della proprietà infoAnag.
     * 
     * @param value
     *     allowed object is
     *     {@link InfoAnagrafiche }
     *     
     */
    public void setInfoAnag(InfoAnagrafiche value) {
        this.infoAnag = value;
    }

    /**
     * Recupera il valore della proprietà altreInfo.
     * 
     * @return
     *     possible object is
     *     {@link AltreInfos }
     *     
     */
    public AltreInfos getAltreInfo() {
        return altreInfo;
    }

    /**
     * Imposta il valore della proprietà altreInfo.
     * 
     * @param value
     *     allowed object is
     *     {@link AltreInfos }
     *     
     */
    public void setAltreInfo(AltreInfos value) {
        this.altreInfo = value;
    }

    /**
     * Recupera il valore della proprietà infoProf.
     * 
     * @return
     *     possible object is
     *     {@link InfoProfessionali }
     *     
     */
    public InfoProfessionali getInfoProf() {
        return infoProf;
    }

    /**
     * Imposta il valore della proprietà infoProf.
     * 
     * @param value
     *     allowed object is
     *     {@link InfoProfessionali }
     *     
     */
    public void setInfoProf(InfoProfessionali value) {
        this.infoProf = value;
    }

    /**
     * Recupera il valore della proprietà associazioniAggregazioni.
     * 
     * @return
     *     possible object is
     *     {@link Associazioni }
     *     
     */
    public Associazioni getAssociazioniAggregazioni() {
        return associazioniAggregazioni;
    }

    /**
     * Imposta il valore della proprietà associazioniAggregazioni.
     * 
     * @param value
     *     allowed object is
     *     {@link Associazioni }
     *     
     */
    public void setAssociazioniAggregazioni(Associazioni value) {
        this.associazioniAggregazioni = value;
    }

    /**
     * Recupera il valore della proprietà elencoStudiMedici.
     * 
     * @return
     *     possible object is
     *     {@link StudiMedici }
     *     
     */
    public StudiMedici getElencoStudiMedici() {
        return elencoStudiMedici;
    }

    /**
     * Imposta il valore della proprietà elencoStudiMedici.
     * 
     * @param value
     *     allowed object is
     *     {@link StudiMedici }
     *     
     */
    public void setElencoStudiMedici(StudiMedici value) {
        this.elencoStudiMedici = value;
    }

}
