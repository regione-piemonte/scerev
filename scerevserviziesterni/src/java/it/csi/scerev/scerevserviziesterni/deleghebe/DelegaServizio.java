/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.scerev.scerevserviziesterni.deleghebe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java per delegaServizio complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="delegaServizio">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="UUID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codiceServizio" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="dataInizioDelega" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="dataFineDelega" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="statoDelega" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="dataRevoca" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataRinuncia" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="tipoDelega" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="gradoDelega" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "delegaServizio", propOrder = {
    "uuid",
    "codiceServizio",
    "dataInizioDelega",
    "dataFineDelega",
    "statoDelega",
    "dataRevoca",
    "dataRinuncia",
    "tipoDelega",
    "gradoDelega"
})
public class DelegaServizio {

    @XmlElement(name = "UUID")
    protected String uuid;
    @XmlElement(required = true)
    protected String codiceServizio;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataInizioDelega;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataFineDelega;
    @XmlElement(required = true)
    protected String statoDelega;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataRevoca;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataRinuncia;
    protected String tipoDelega;
    protected String gradoDelega;

    /**
     * Recupera il valore della propriet� uuid.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUUID() {
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
    public void setUUID(String value) {
        this.uuid = value;
    }

    /**
     * Recupera il valore della propriet� codiceServizio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceServizio() {
        return codiceServizio;
    }

    /**
     * Imposta il valore della propriet� codiceServizio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceServizio(String value) {
        this.codiceServizio = value;
    }

    /**
     * Recupera il valore della propriet� dataInizioDelega.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataInizioDelega() {
        return dataInizioDelega;
    }

    /**
     * Imposta il valore della propriet� dataInizioDelega.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataInizioDelega(XMLGregorianCalendar value) {
        this.dataInizioDelega = value;
    }

    /**
     * Recupera il valore della propriet� dataFineDelega.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataFineDelega() {
        return dataFineDelega;
    }

    /**
     * Imposta il valore della propriet� dataFineDelega.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataFineDelega(XMLGregorianCalendar value) {
        this.dataFineDelega = value;
    }

    /**
     * Recupera il valore della propriet� statoDelega.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatoDelega() {
        return statoDelega;
    }

    /**
     * Imposta il valore della propriet� statoDelega.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatoDelega(String value) {
        this.statoDelega = value;
    }

    /**
     * Recupera il valore della propriet� dataRevoca.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataRevoca() {
        return dataRevoca;
    }

    /**
     * Imposta il valore della propriet� dataRevoca.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataRevoca(XMLGregorianCalendar value) {
        this.dataRevoca = value;
    }

    /**
     * Recupera il valore della propriet� dataRinuncia.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataRinuncia() {
        return dataRinuncia;
    }

    /**
     * Imposta il valore della propriet� dataRinuncia.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataRinuncia(XMLGregorianCalendar value) {
        this.dataRinuncia = value;
    }

    /**
     * Recupera il valore della propriet� tipoDelega.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoDelega() {
        return tipoDelega;
    }

    /**
     * Imposta il valore della propriet� tipoDelega.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoDelega(String value) {
        this.tipoDelega = value;
    }

    /**
     * Recupera il valore della propriet� gradoDelega.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGradoDelega() {
        return gradoDelega;
    }

    /**
     * Imposta il valore della propriet� gradoDelega.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGradoDelega(String value) {
        this.gradoDelega = value;
    }

}
