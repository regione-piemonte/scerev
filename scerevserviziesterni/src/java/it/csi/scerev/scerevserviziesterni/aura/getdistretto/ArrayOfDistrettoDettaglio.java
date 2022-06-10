/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.scerev.scerevserviziesterni.aura.getdistretto;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per ArrayOfDistrettoDettaglio complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfDistrettoDettaglio">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DistrettoDettaglio" type="{http://GetDistretto.central.services.auraws.aura.csi.it}DistrettoDettaglio" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfDistrettoDettaglio", propOrder = {
    "distrettoDettaglio"
})
public class ArrayOfDistrettoDettaglio {

    @XmlElement(name = "DistrettoDettaglio", nillable = true)
    protected List<DistrettoDettaglio> distrettoDettaglio;

    /**
     * Gets the value of the distrettoDettaglio property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the distrettoDettaglio property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDistrettoDettaglio().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DistrettoDettaglio }
     * 
     * 
     */
    public List<DistrettoDettaglio> getDistrettoDettaglio() {
        if (distrettoDettaglio == null) {
            distrettoDettaglio = new ArrayList<DistrettoDettaglio>();
        }
        return this.distrettoDettaglio;
    }

}
