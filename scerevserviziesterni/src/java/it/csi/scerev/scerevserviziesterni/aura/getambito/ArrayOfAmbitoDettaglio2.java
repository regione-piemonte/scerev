/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.scerev.scerevserviziesterni.aura.getambito;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per ArrayOfAmbitoDettaglio2 complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfAmbitoDettaglio2">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AmbitoDettaglio2" type="{http://GetAmbito.central.services.auraws.aura.csi.it}AmbitoDettaglio2" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfAmbitoDettaglio2", propOrder = {
    "ambitoDettaglio2"
})
public class ArrayOfAmbitoDettaglio2 {

    @XmlElement(name = "AmbitoDettaglio2", nillable = true)
    protected List<AmbitoDettaglio2> ambitoDettaglio2;

    /**
     * Gets the value of the ambitoDettaglio2 property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the ambitoDettaglio2 property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAmbitoDettaglio2().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AmbitoDettaglio2 }
     * 
     * 
     */
    public List<AmbitoDettaglio2> getAmbitoDettaglio2() {
        if (ambitoDettaglio2 == null) {
            ambitoDettaglio2 = new ArrayList<AmbitoDettaglio2>();
        }
        return this.ambitoDettaglio2;
    }

}
