/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.scerev.scerevserviziesterni.deleghebe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per documento complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="documento">
 *   &lt;complexContent>
 *     &lt;extension base="{http://deleghebe.csi.it/}entita">
 *       &lt;sequence>
 *         &lt;element name="desc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="documentoTipo" type="{http://deleghebe.csi.it/}documentoTipo" minOccurs="0"/>
 *         &lt;element name="file" type="{http://deleghebe.csi.it/}documentoFile" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "documento", propOrder = {
    "desc",
    "documentoTipo",
    "file",
    "id"
})
public class Documento
    extends Entita
{

    protected String desc;
    protected DocumentoTipo documentoTipo;
    protected DocumentoFile file;
    protected Integer id;

    /**
     * Recupera il valore della proprietà desc.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Imposta il valore della proprietà desc.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDesc(String value) {
        this.desc = value;
    }

    /**
     * Recupera il valore della proprietà documentoTipo.
     * 
     * @return
     *     possible object is
     *     {@link DocumentoTipo }
     *     
     */
    public DocumentoTipo getDocumentoTipo() {
        return documentoTipo;
    }

    /**
     * Imposta il valore della proprietà documentoTipo.
     * 
     * @param value
     *     allowed object is
     *     {@link DocumentoTipo }
     *     
     */
    public void setDocumentoTipo(DocumentoTipo value) {
        this.documentoTipo = value;
    }

    /**
     * Recupera il valore della proprietà file.
     * 
     * @return
     *     possible object is
     *     {@link DocumentoFile }
     *     
     */
    public DocumentoFile getFile() {
        return file;
    }

    /**
     * Imposta il valore della proprietà file.
     * 
     * @param value
     *     allowed object is
     *     {@link DocumentoFile }
     *     
     */
    public void setFile(DocumentoFile value) {
        this.file = value;
    }

    /**
     * Recupera il valore della proprietà id.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getId() {
        return id;
    }

    /**
     * Imposta il valore della proprietà id.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setId(Integer value) {
        this.id = value;
    }

}
