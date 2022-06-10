/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.scerev.scerevserviziesterni.aura.getasldistrettoambito;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element name="comuneDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipologiaMedico" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "comuneDesc", "tipologiaMedico"
})
@XmlRootElement(name = "GetASLDistrettoAmbito")
public class GetASLDistrettoAmbito {

    protected String comuneDesc;
    protected String tipologiaMedico;

    /**
     * Recupera il valore della proprietà comuneDesc.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComuneDesc() {
        return comuneDesc;
    }

    /**
     * Imposta il valore della proprietà comuneDesc.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComuneDesc(String value) {
        this.comuneDesc = value;
    }

    /**
     * Recupera il valore della proprietà tipologiaMedico.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
	public String getTipologiaMedico() {
		return tipologiaMedico;
	}
	
    /**
     * Imposta il valore della proprietà tipologiaMedico.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
	public void setTipologiaMedico(String tipologiaMedico) {
		this.tipologiaMedico = tipologiaMedico;
	}

    
    
}
