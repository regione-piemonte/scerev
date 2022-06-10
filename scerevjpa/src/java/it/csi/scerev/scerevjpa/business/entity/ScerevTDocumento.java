/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevjpa.business.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the scerev_t_documento database table.
 * 
 */
@Entity
@Table(name="scerev_t_documento")
@SequenceGenerator(name="\"scerev_t_documento_pk_documento_seq_generator\"",  sequenceName="\"scerev_t_documento_pk_documento_seq\"", initialValue=1, allocationSize=1)
@NamedQueries({
	@NamedQuery(name="ScerevTDocumento.findByPratica", query="SELECT s FROM ScerevTDocumento s WHERE s.scerevTPratica.pkPratica = :pkPratica_Selezionata"),
	@NamedQuery(name="ScerevTDocumento.findAll", query="SELECT s FROM ScerevTDocumento s")
//	@NamedQuery(name="ScerevTDocumento.findFileSpecifico_per_pkPratica_pkFile",
//	            query="SELECT s"
//	               + " FROM ScerevTFile s "
//	               + " JOIN FETCH s.scerevTDocumento d"
//	               + " WHERE d.scerevTFile.pkFile = :pkFileSelezionato"
//	               + " AND d.scerevTPratica.pkPratica = :pkPraticaSelezionata")
})

public class ScerevTDocumento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="pk_documento")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="\"scerev_t_documento_pk_documento_seq_generator\"")
	private Integer pkDocumento;

	@Column(name="data_cancellazione")
	private Timestamp dataCancellazione;

	@Column(name="data_creazione")
	private Timestamp dataCreazione;

	@Column(name="data_modifica")
	private Timestamp dataModifica;

	//bi-directional many-to-one association to ScerevDDocumentoTipo
	@ManyToOne
	@JoinColumn(name="fk_documento_tipo")
	private ScerevDDocumentoTipo scerevDDocumentoTipo;

	//bi-directional many-to-one association to ScerevTFile
	@ManyToOne
	@JoinColumn(name="fk_file")
	private ScerevTFile scerevTFile;

	//bi-directional many-to-one association to ScerevTPratica
	@ManyToOne
	@JoinColumn(name="fk_pratica")
	private ScerevTPratica scerevTPratica;

	public ScerevTDocumento() {
	}

	public Integer getPkDocumento() {
		return this.pkDocumento;
	}

	public void setPkDocumento(Integer pkDocumento) {
		this.pkDocumento = pkDocumento;
	}

	public Timestamp getDataCancellazione() {
		return this.dataCancellazione;
	}

	public void setDataCancellazione(Timestamp dataCancellazione) {
		this.dataCancellazione = dataCancellazione;
	}

	public Timestamp getDataCreazione() {
		return this.dataCreazione;
	}

	public void setDataCreazione(Timestamp dataCreazione) {
		this.dataCreazione = dataCreazione;
	}

	public Timestamp getDataModifica() {
		return this.dataModifica;
	}

	public void setDataModifica(Timestamp dataModifica) {
		this.dataModifica = dataModifica;
	}

	public ScerevDDocumentoTipo getScerevDDocumentoTipo() {
		return this.scerevDDocumentoTipo;
	}

	public void setScerevDDocumentoTipo(ScerevDDocumentoTipo scerevDDocumentoTipo) {
		this.scerevDDocumentoTipo = scerevDDocumentoTipo;
	}

	public ScerevTFile getScerevTFile() {
		return this.scerevTFile;
	}

	public void setScerevTFile(ScerevTFile scerevTFile) {
		this.scerevTFile = scerevTFile;
	}

	public ScerevTPratica getScerevTPratica() {
		return this.scerevTPratica;
	}

	public void setScerevTPratica(ScerevTPratica scerevTPratica) {
		this.scerevTPratica = scerevTPratica;
	}

}
