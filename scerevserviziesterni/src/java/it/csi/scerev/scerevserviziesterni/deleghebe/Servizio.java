/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.scerev.scerevserviziesterni.deleghebe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java per servizio complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="servizio">
 *   &lt;complexContent>
 *     &lt;extension base="{http://deleghebe.csi.it/}codifica">
 *       &lt;sequence>
 *         &lt;element name="descrizioneEstesa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="arruolabile" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="delegabile" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="notificabile" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="obbligatorioArruolamento" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="minore" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numeroGiorniDelegabili" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="dataInizioValidita" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataFineValidita" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "servizio", propOrder = {
    "descrizioneEstesa",
    "arruolabile",
    "delegabile",
    "notificabile",
    "obbligatorioArruolamento",
    "minore",
    "url",
    "numeroGiorniDelegabili",
    "dataInizioValidita",
    "dataFineValidita"
})
public class Servizio
    extends Codifica
{

    protected String descrizioneEstesa;
    protected Boolean arruolabile;
    protected Boolean delegabile;
    protected Boolean notificabile;
    protected Boolean obbligatorioArruolamento;
    protected Boolean minore;
    protected String url;
    protected Integer numeroGiorniDelegabili;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataInizioValidita;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataFineValidita;

    /**
     * Recupera il valore della proprietà descrizioneEstesa.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescrizioneEstesa() {
        return descrizioneEstesa;
    }

    /**
     * Imposta il valore della proprietà descrizioneEstesa.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescrizioneEstesa(String value) {
        this.descrizioneEstesa = value;
    }

    /**
     * Recupera il valore della proprietà arruolabile.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isArruolabile() {
        return arruolabile;
    }

    /**
     * Imposta il valore della proprietà arruolabile.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setArruolabile(Boolean value) {
        this.arruolabile = value;
    }

    /**
     * Recupera il valore della proprietà delegabile.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDelegabile() {
        return delegabile;
    }

    /**
     * Imposta il valore della proprietà delegabile.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDelegabile(Boolean value) {
        this.delegabile = value;
    }

    /**
     * Recupera il valore della proprietà notificabile.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isNotificabile() {
        return notificabile;
    }

    /**
     * Imposta il valore della proprietà notificabile.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setNotificabile(Boolean value) {
        this.notificabile = value;
    }

    /**
     * Recupera il valore della proprietà obbligatorioArruolamento.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isObbligatorioArruolamento() {
        return obbligatorioArruolamento;
    }

    /**
     * Imposta il valore della proprietà obbligatorioArruolamento.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setObbligatorioArruolamento(Boolean value) {
        this.obbligatorioArruolamento = value;
    }

    /**
     * Recupera il valore della proprietà minore.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isMinore() {
        return minore;
    }

    /**
     * Imposta il valore della proprietà minore.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setMinore(Boolean value) {
        this.minore = value;
    }

    /**
     * Recupera il valore della proprietà url.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUrl() {
        return url;
    }

    /**
     * Imposta il valore della proprietà url.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUrl(String value) {
        this.url = value;
    }

    /**
     * Recupera il valore della proprietà numeroGiorniDelegabili.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNumeroGiorniDelegabili() {
        return numeroGiorniDelegabili;
    }

    /**
     * Imposta il valore della proprietà numeroGiorniDelegabili.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNumeroGiorniDelegabili(Integer value) {
        this.numeroGiorniDelegabili = value;
    }

    /**
     * Recupera il valore della proprietà dataInizioValidita.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataInizioValidita() {
        return dataInizioValidita;
    }

    /**
     * Imposta il valore della proprietà dataInizioValidita.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataInizioValidita(XMLGregorianCalendar value) {
        this.dataInizioValidita = value;
    }

    /**
     * Recupera il valore della proprietà dataFineValidita.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataFineValidita() {
        return dataFineValidita;
    }

    /**
     * Imposta il valore della proprietà dataFineValidita.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataFineValidita(XMLGregorianCalendar value) {
        this.dataFineValidita = value;
    }

}
