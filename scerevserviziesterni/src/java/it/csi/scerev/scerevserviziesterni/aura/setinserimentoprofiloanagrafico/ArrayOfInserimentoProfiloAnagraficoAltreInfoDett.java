/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.scerev.scerevserviziesterni.aura.setinserimentoprofiloanagrafico;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per ArrayOfInserimentoProfiloAnagraficoAltreInfoDett complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfInserimentoProfiloAnagraficoAltreInfoDett">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="InserimentoProfiloAnagraficoAltreInfoDett" type="{http://SetInserimentoProfiloAnagrafico.central.services.auraws.aura.csi.it}InserimentoProfiloAnagraficoAltreInfoDett" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfInserimentoProfiloAnagraficoAltreInfoDett", propOrder = {
    "inserimentoProfiloAnagraficoAltreInfoDett"
})
public class ArrayOfInserimentoProfiloAnagraficoAltreInfoDett {

    @XmlElement(name = "InserimentoProfiloAnagraficoAltreInfoDett", nillable = true)
    protected List<InserimentoProfiloAnagraficoAltreInfoDett> inserimentoProfiloAnagraficoAltreInfoDett;

    /**
     * Gets the value of the inserimentoProfiloAnagraficoAltreInfoDett property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the inserimentoProfiloAnagraficoAltreInfoDett property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInserimentoProfiloAnagraficoAltreInfoDett().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link InserimentoProfiloAnagraficoAltreInfoDett }
     * 
     * 
     */
    public List<InserimentoProfiloAnagraficoAltreInfoDett> getInserimentoProfiloAnagraficoAltreInfoDett() {
        if (inserimentoProfiloAnagraficoAltreInfoDett == null) {
            inserimentoProfiloAnagraficoAltreInfoDett = new ArrayList<InserimentoProfiloAnagraficoAltreInfoDett>();
        }
        return this.inserimentoProfiloAnagraficoAltreInfoDett;
    }

}
