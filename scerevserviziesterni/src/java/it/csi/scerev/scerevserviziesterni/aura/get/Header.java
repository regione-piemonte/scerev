/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.scerev.scerevserviziesterni.aura.get;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java per Header complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="Header">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idAuraRicondotto" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="numeroTicket" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idNotifica" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codiceRitorno" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="requestDateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="eventi" type="{http://AnagrafeSanitaria.central.services.auraws.aura.csi.it}ArrayOfeventoEvento" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Header", propOrder = {
    "idAuraRicondotto",
    "numeroTicket",
    "idNotifica",
    "codiceRitorno",
    "requestDateTime",
    "eventi"
})
public class Header {

    protected BigDecimal idAuraRicondotto;
    protected String numeroTicket;
    protected String idNotifica;
    protected String codiceRitorno;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar requestDateTime;
    protected ArrayOfeventoEvento eventi;

    /**
     * Recupera il valore della propriet� idAuraRicondotto.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getIdAuraRicondotto() {
        return idAuraRicondotto;
    }

    /**
     * Imposta il valore della propriet� idAuraRicondotto.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setIdAuraRicondotto(BigDecimal value) {
        this.idAuraRicondotto = value;
    }

    /**
     * Recupera il valore della propriet� numeroTicket.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroTicket() {
        return numeroTicket;
    }

    /**
     * Imposta il valore della propriet� numeroTicket.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroTicket(String value) {
        this.numeroTicket = value;
    }

    /**
     * Recupera il valore della propriet� idNotifica.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdNotifica() {
        return idNotifica;
    }

    /**
     * Imposta il valore della propriet� idNotifica.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdNotifica(String value) {
        this.idNotifica = value;
    }

    /**
     * Recupera il valore della propriet� codiceRitorno.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceRitorno() {
        return codiceRitorno;
    }

    /**
     * Imposta il valore della propriet� codiceRitorno.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceRitorno(String value) {
        this.codiceRitorno = value;
    }

    /**
     * Recupera il valore della propriet� requestDateTime.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getRequestDateTime() {
        return requestDateTime;
    }

    /**
     * Imposta il valore della propriet� requestDateTime.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setRequestDateTime(XMLGregorianCalendar value) {
        this.requestDateTime = value;
    }

    /**
     * Recupera il valore della propriet� eventi.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfeventoEvento }
     *     
     */
    public ArrayOfeventoEvento getEventi() {
        return eventi;
    }

    /**
     * Imposta il valore della propriet� eventi.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfeventoEvento }
     *     
     */
    public void setEventi(ArrayOfeventoEvento value) {
        this.eventi = value;
    }

}
