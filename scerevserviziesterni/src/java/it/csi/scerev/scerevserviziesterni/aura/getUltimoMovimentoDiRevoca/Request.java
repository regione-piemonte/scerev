/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.scerev.scerevserviziesterni.aura.getUltimoMovimentoDiRevoca;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per Request complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="Request">
 *   &lt;complexContent>
 *     &lt;extension base="{http://getUltimoMovimentoDiRevoca.central.services.auraws.aura.csi.it}Ens_Request">
 *       &lt;sequence>
 *         &lt;element name="idMedico" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idAssistito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Request", propOrder = {
    "idMedico",
    "idAssistito"
})
@XmlRootElement
public class Request
    extends EnsRequest
{

    protected String idMedico;
    protected String idAssistito;

    /**
     * Recupera il valore della proprietà idMedico.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdMedico() {
        return idMedico;
    }

    /**
     * Imposta il valore della proprietà idMedico.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdMedico(String value) {
        this.idMedico = value;
    }

    /**
     * Recupera il valore della proprietà idAssistito.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdAssistito() {
        return idAssistito;
    }

    /**
     * Imposta il valore della proprietà idAssistito.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdAssistito(String value) {
        this.idAssistito = value;
    }

}
