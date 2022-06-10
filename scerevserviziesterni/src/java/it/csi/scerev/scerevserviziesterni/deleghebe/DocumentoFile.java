/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.scerev.scerevserviziesterni.deleghebe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per documentoFile complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="documentoFile">
 *   &lt;complexContent>
 *     &lt;extension base="{http://deleghebe.csi.it/}entita">
 *       &lt;sequence>
 *         &lt;element name="base64File" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dimensioneInBytes" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="nome" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "documentoFile", propOrder = {
    "base64File",
    "dimensioneInBytes",
    "nome"
})
public class DocumentoFile
    extends Entita
{

    protected String base64File;
    protected Integer dimensioneInBytes;
    protected String nome;

    /**
     * Recupera il valore della proprietà base64File.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBase64File() {
        return base64File;
    }

    /**
     * Imposta il valore della proprietà base64File.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBase64File(String value) {
        this.base64File = value;
    }

    /**
     * Recupera il valore della proprietà dimensioneInBytes.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getDimensioneInBytes() {
        return dimensioneInBytes;
    }

    /**
     * Imposta il valore della proprietà dimensioneInBytes.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setDimensioneInBytes(Integer value) {
        this.dimensioneInBytes = value;
    }

    /**
     * Recupera il valore della proprietà nome.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNome() {
        return nome;
    }

    /**
     * Imposta il valore della proprietà nome.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNome(String value) {
        this.nome = value;
    }

}
