/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.scerev.scerevserviziesterni.aura.setinserimentoprofiloanagrafico;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per InserimentoProfiloAnagraficoResFooter complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="InserimentoProfiloAnagraficoResFooter">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="listaErrori" type="{http://SetInserimentoProfiloAnagrafico.central.services.auraws.aura.csi.it}ArrayOfInserimentoProfiloAnagraficoResErrore" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InserimentoProfiloAnagraficoResFooter", propOrder = {
    "listaErrori"
})
public class InserimentoProfiloAnagraficoResFooter {

    protected ArrayOfInserimentoProfiloAnagraficoResErrore listaErrori;

    /**
     * Recupera il valore della proprietà listaErrori.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfInserimentoProfiloAnagraficoResErrore }
     *     
     */
    public ArrayOfInserimentoProfiloAnagraficoResErrore getListaErrori() {
        return listaErrori;
    }

    /**
     * Imposta il valore della proprietà listaErrori.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfInserimentoProfiloAnagraficoResErrore }
     *     
     */
    public void setListaErrori(ArrayOfInserimentoProfiloAnagraficoResErrore value) {
        this.listaErrori = value;
    }

}
