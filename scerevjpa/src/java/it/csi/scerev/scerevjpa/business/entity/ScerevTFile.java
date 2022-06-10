/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevjpa.business.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnTransformer;


/**
 * The persistent class for the scerev_t_file database table.
 * 
 */
@Entity
@Table(name="scerev_t_file") 
@SequenceGenerator(name="\"scerev_t_file_pk_file_seq_generator\"",  sequenceName="\"scerev_t_file_pk_file_seq\"", initialValue=1, allocationSize=1)
@NamedQueries({
	@NamedQuery(name="ScerevTFile.findAll", query="SELECT s FROM ScerevTFile s"),
	@NamedQuery(name="ScerevTFile.findByPkFile", query="SELECT s FROM ScerevTFile s WHERE s.pkFile = :pkFile_Selezionato")
})
public class ScerevTFile implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="pk_file")
	
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="\"scerev_t_file_pk_file_seq_generator\"")
	private Integer pkFile;

	@Column(name="data_cancellazione")
	private Timestamp dataCancellazione;

	@Column(name="data_creazione")
	private Timestamp dataCreazione;

	@Column(name="data_modifica")
	private Timestamp dataModifica;

	@ColumnTransformer(
	    read =  "pgp_sym_decrypt_bytea( file, '${encryption.key}' )",
	    write = "pgp_sym_encrypt_bytea( ?, '${encryption.key}' )"
	)
	@Column(columnDefinition = "bytea")
	private byte[] file;

	@Column(name="name_file")
	private String nameFile;

	@Column(name="size_file")
	private BigDecimal sizeFile;

	//bi-directional many-to-one association to ScerevTDocumento
	@OneToMany(mappedBy="scerevTFile")
	private List<ScerevTDocumento> scerevTDocumentos;

	public ScerevTFile() {
	}

	public Integer getPkFile() {
		return this.pkFile;
	}

	public void setPkFile(Integer pkFile) {
		this.pkFile = pkFile;
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

	public byte[] getFile() {
		return this.file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}

	public String getNameFile() {
		return this.nameFile;
	}

	public void setNameFile(String nameFile) {
		this.nameFile = nameFile;
	}

	public BigDecimal getSizeFile() {
		return this.sizeFile;
	}

	public void setSizeFile(BigDecimal sizeFile) {
		this.sizeFile = sizeFile;
	}

	public List<ScerevTDocumento> getScerevTDocumentos() {
		return this.scerevTDocumentos;
	}

	public void setScerevTDocumentos(List<ScerevTDocumento> scerevTDocumentos) {
		this.scerevTDocumentos = scerevTDocumentos;
	}

	public ScerevTDocumento addScerevTDocumento(ScerevTDocumento scerevTDocumento) {
		getScerevTDocumentos().add(scerevTDocumento);
		scerevTDocumento.setScerevTFile(this);

		return scerevTDocumento;
	}

	public ScerevTDocumento removeScerevTDocumento(ScerevTDocumento scerevTDocumento) {
		getScerevTDocumentos().remove(scerevTDocumento);
		scerevTDocumento.setScerevTFile(null);

		return scerevTDocumento;
	}

}
