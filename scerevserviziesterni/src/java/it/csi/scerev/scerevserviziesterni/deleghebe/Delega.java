/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.scerev.scerevserviziesterni.deleghebe;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per delega complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="delega">
 *   &lt;complexContent>
 *     &lt;extension base="{http://deleghebe.csi.it/}entita">
 *       &lt;sequence>
 *         &lt;element name="servizi" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="servizio" type="{http://deleghebe.csi.it/}delegaServ" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="delegante" type="{http://deleghebe.csi.it/}cittadino" minOccurs="0"/>
 *         &lt;element name="delegato" type="{http://deleghebe.csi.it/}cittadino" minOccurs="0"/>
 *         &lt;element name="delegatoInput" type="{http://deleghebe.csi.it/}delegato" minOccurs="0"/>
 *         &lt;element name="uuid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "delega", propOrder = {
    "servizi",
    "delegante",
    "delegato",
    "delegatoInput",
    "uuid"
})
public class Delega
    extends Entita
{

    protected Delega.Servizi servizi;
    protected Cittadino delegante;
    protected Cittadino delegato;
    protected Delegato delegatoInput;
    protected String uuid;

    /**
     * Recupera il valore della proprietà servizi.
     * 
     * @return
     *     possible object is
     *     {@link Delega.Servizi }
     *     
     */
    public Delega.Servizi getServizi() {
        return servizi;
    }

    /**
     * Imposta il valore della proprietà servizi.
     * 
     * @param value
     *     allowed object is
     *     {@link Delega.Servizi }
     *     
     */
    public void setServizi(Delega.Servizi value) {
        this.servizi = value;
    }

    /**
     * Recupera il valore della proprietà delegante.
     * 
     * @return
     *     possible object is
     *     {@link Cittadino }
     *     
     */
    public Cittadino getDelegante() {
        return delegante;
    }

    /**
     * Imposta il valore della proprietà delegante.
     * 
     * @param value
     *     allowed object is
     *     {@link Cittadino }
     *     
     */
    public void setDelegante(Cittadino value) {
        this.delegante = value;
    }

    /**
     * Recupera il valore della proprietà delegato.
     * 
     * @return
     *     possible object is
     *     {@link Cittadino }
     *     
     */
    public Cittadino getDelegato() {
        return delegato;
    }

    /**
     * Imposta il valore della proprietà delegato.
     * 
     * @param value
     *     allowed object is
     *     {@link Cittadino }
     *     
     */
    public void setDelegato(Cittadino value) {
        this.delegato = value;
    }

    /**
     * Recupera il valore della proprietà delegatoInput.
     * 
     * @return
     *     possible object is
     *     {@link Delegato }
     *     
     */
    public Delegato getDelegatoInput() {
        return delegatoInput;
    }

    /**
     * Imposta il valore della proprietà delegatoInput.
     * 
     * @param value
     *     allowed object is
     *     {@link Delegato }
     *     
     */
    public void setDelegatoInput(Delegato value) {
        this.delegatoInput = value;
    }

    /**
     * Recupera il valore della proprietà uuid.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * Imposta il valore della proprietà uuid.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUuid(String value) {
        this.uuid = value;
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
     *         &lt;element name="servizio" type="{http://deleghebe.csi.it/}delegaServ" maxOccurs="unbounded" minOccurs="0"/>
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
        "servizio"
    })
    public static class Servizi {

        protected List<DelegaServ> servizio;

        /**
         * Gets the value of the servizio property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the servizio property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getServizio().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link DelegaServ }
         * 
         * 
         */
        public List<DelegaServ> getServizio() {
            if (servizio == null) {
                servizio = new ArrayList<DelegaServ>();
            }
            return this.servizio;
        }

    }

}
