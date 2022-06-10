/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.scerev.scadenzaassistenza.deleghebe;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
/**
 * <p>Classe Java per getDelegantiResponse complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="getDelegantiResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://deleghebe.csi.it/}serviceResponse">
 *       &lt;sequence>
 *         &lt;element name="deleganti" minOccurs="0" form="unqualified">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="delegante" type="{http://deleghebe.csi.it/}delegaCittadino" maxOccurs="unbounded" minOccurs="0" form="unqualified"/>
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
@XmlType(name = "getDelegantiResponse", propOrder = {
    "deleganti"
})
public class GetDelegantiResponse
    extends ServiceResponse
{

    @XmlElement(namespace = "")
    protected GetDelegantiResponse.Deleganti deleganti;

    /**
     * Recupera il valore della proprieta deleganti.
     * 
     * @return
     *     possible object is
     *     {@link GetDelegantiResponse.Deleganti }
     *     
     */
    public GetDelegantiResponse.Deleganti getDeleganti() {
        return deleganti;
    }

    /**
     * Imposta il valore della proprieta deleganti.
     * 
     * @param value
     *     allowed object is
     *     {@link GetDelegantiResponse.Deleganti }
     *     
     */
    public void setDeleganti(GetDelegantiResponse.Deleganti value) {
        this.deleganti = value;
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
     *         &lt;element name="delegante" type="{http://deleghebe.csi.it/}delegaCittadino" maxOccurs="unbounded" minOccurs="0" form="unqualified"/>
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
        "delegante"
    })
    public static class Deleganti {

        @XmlElement(namespace = "")
        protected List<DelegaCittadino> delegante;

        /**
         * Gets the value of the delegante property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the delegante property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getDelegante().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link DelegaCittadino }
         * 
         * 
         */
        public List<DelegaCittadino> getDelegante() {
            if (delegante == null) {
                delegante = new ArrayList<DelegaCittadino>();
            }
            return this.delegante;
        }

    }

}
