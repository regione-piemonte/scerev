/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.scerev.scerevserviziesterni.deleghebe;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per getDelegati complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="getDelegati">
 *   &lt;complexContent>
 *     &lt;extension base="{http://deleghebe.csi.it/}serviceRequest">
 *       &lt;sequence>
 *         &lt;element name="cittadinoDelegante" type="{http://deleghebe.csi.it/}cittadino" form="unqualified"/>
 *         &lt;element name="cittadinoDelegato" type="{http://deleghebe.csi.it/}cittadino" minOccurs="0" form="unqualified"/>
 *         &lt;element name="codiciServizio" minOccurs="0" form="unqualified">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="codiceServizio" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0" form="unqualified"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="statiDelega" minOccurs="0" form="unqualified">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="statoDelega" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0" form="unqualified"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getDelegati", propOrder = {
    "cittadinoDelegante",
    "cittadinoDelegato",
    "codiciServizio",
    "statiDelega"
})
public class GetDelegati
    extends ServiceRequest
{

    @XmlElement(namespace = "", required = true)
    protected Cittadino cittadinoDelegante;
    @XmlElement(namespace = "")
    protected Cittadino cittadinoDelegato;
    @XmlElement(namespace = "")
    protected GetDelegati.CodiciServizio codiciServizio;
    @XmlElement(namespace = "")
    protected GetDelegati.StatiDelega statiDelega;

    /**
     * Recupera il valore della propriet� cittadinoDelegante.
     * 
     * @return
     *     possible object is
     *     {@link Cittadino }
     *     
     */
    public Cittadino getCittadinoDelegante() {
        return cittadinoDelegante;
    }

    /**
     * Imposta il valore della propriet� cittadinoDelegante.
     * 
     * @param value
     *     allowed object is
     *     {@link Cittadino }
     *     
     */
    public void setCittadinoDelegante(Cittadino value) {
        this.cittadinoDelegante = value;
    }

    /**
     * Recupera il valore della propriet� cittadinoDelegato.
     * 
     * @return
     *     possible object is
     *     {@link Cittadino }
     *     
     */
    public Cittadino getCittadinoDelegato() {
        return cittadinoDelegato;
    }

    /**
     * Imposta il valore della propriet� cittadinoDelegato.
     * 
     * @param value
     *     allowed object is
     *     {@link Cittadino }
     *     
     */
    public void setCittadinoDelegato(Cittadino value) {
        this.cittadinoDelegato = value;
    }

    /**
     * Recupera il valore della propriet� codiciServizio.
     * 
     * @return
     *     possible object is
     *     {@link GetDelegati.CodiciServizio }
     *     
     */
    public GetDelegati.CodiciServizio getCodiciServizio() {
        return codiciServizio;
    }

    /**
     * Imposta il valore della propriet� codiciServizio.
     * 
     * @param value
     *     allowed object is
     *     {@link GetDelegati.CodiciServizio }
     *     
     */
    public void setCodiciServizio(GetDelegati.CodiciServizio value) {
        this.codiciServizio = value;
    }

    /**
     * Recupera il valore della propriet� statiDelega.
     * 
     * @return
     *     possible object is
     *     {@link GetDelegati.StatiDelega }
     *     
     */
    public GetDelegati.StatiDelega getStatiDelega() {
        return statiDelega;
    }

    /**
     * Imposta il valore della propriet� statiDelega.
     * 
     * @param value
     *     allowed object is
     *     {@link GetDelegati.StatiDelega }
     *     
     */
    public void setStatiDelega(GetDelegati.StatiDelega value) {
        this.statiDelega = value;
    }


    /**
     * <p>Classe Java per anonymous complex type.
     * 
     * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="codiceServizio" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0" form="unqualified"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "codiceServizio"
    })
    public static class CodiciServizio {

        @XmlElement(namespace = "")
        protected List<String> codiceServizio;

        /**
         * Gets the value of the codiceServizio property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the codiceServizio property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getCodiceServizio().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getCodiceServizio() {
            if (codiceServizio == null) {
                codiceServizio = new ArrayList<String>();
            }
            return this.codiceServizio;
        }

    }


    /**
     * <p>Classe Java per anonymous complex type.
     * 
     * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="statoDelega" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0" form="unqualified"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "statoDelega"
    })
    public static class StatiDelega {

        @XmlElement(namespace = "")
        protected List<String> statoDelega;

        /**
         * Gets the value of the statoDelega property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the statoDelega property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getStatoDelega().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getStatoDelega() {
            if (statoDelega == null) {
                statoDelega = new ArrayList<String>();
            }
            return this.statoDelega;
        }

    }

}
