/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevjpa.business.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the scerev_d_documento_tipo database table.
 * 
 */
@Entity
@Table(name="scerev_d_documento_tipo")
@NamedQuery(name="ScerevDDocumentoTipo.findAll", query="SELECT s FROM ScerevDDocumentoTipo s")
public class ScerevDDocumentoTipo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="pk_documento_tipo")
	private Integer pkDocumentoTipo;

	@Column(name="codice_documento_tipo")
	private String codiceDocumentoTipo;

	@Column(name="data_cancellazione")
	private Timestamp dataCancellazione;

	@Column(name="data_creazione")
	private Timestamp dataCreazione;

	@Temporal(TemporalType.DATE)
	@Column(name="data_fine_validita")
	private Date dataFineValidita;

	@Temporal(TemporalType.DATE)
	@Column(name="data_inizio_validita")
	private Date dataInizioValidita;

	@Column(name="data_modifica")
	private Timestamp dataModifica;

	@Column(name="descrizione_documento_tipo")
	private String descrizioneDocumentoTipo;

	//bi-directional many-to-one association to ScerevTDocumento
	@OneToMany(mappedBy="scerevDDocumentoTipo")
	private List<ScerevTDocumento> scerevTDocumentos;
	
	//bi-directional many-to-one association to ScerevRMotivoSoggiornoDocumentoTipo
	@OneToMany(mappedBy="scerevDMotivoSoggiorno")
	private List<ScerevRMotivoSoggiornoDocumentoTipo> scerevRMotivoSoggiornoDocumentoTipos;

	
	public ScerevDDocumentoTipo() {
	}

	public Integer getPkDocumentoTipo() {
		return this.pkDocumentoTipo;
	}

	public void setPkDocumentoTipo(Integer pkDocumentoTipo) {
		this.pkDocumentoTipo = pkDocumentoTipo;
	}

	public String getCodiceDocumentoTipo() {
		return this.codiceDocumentoTipo;
	}

	public void setCodiceDocumentoTipo(String codiceDocumentoTipo) {
		this.codiceDocumentoTipo = codiceDocumentoTipo;
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

	public Date getDataFineValidita() {
		return this.dataFineValidita;
	}

	public void setDataFineValidita(Date dataFineValidita) {
		this.dataFineValidita = dataFineValidita;
	}

	public Date getDataInizioValidita() {
		return this.dataInizioValidita;
	}

	public void setDataInizioValidita(Date dataInizioValidita) {
		this.dataInizioValidita = dataInizioValidita;
	}

	public Timestamp getDataModifica() {
		return this.dataModifica;
	}

	public void setDataModifica(Timestamp dataModifica) {
		this.dataModifica = dataModifica;
	}

	public String getDescrizioneDocumentoTipo() {
		return this.descrizioneDocumentoTipo;
	}

	public void setDescrizioneDocumentoTipo(String descrizioneDocumentoTipo) {
		this.descrizioneDocumentoTipo = descrizioneDocumentoTipo;
	}

	public List<ScerevTDocumento> getScerevTDocumentos() {
		return this.scerevTDocumentos;
	}

	public void setScerevTDocumentos(List<ScerevTDocumento> scerevTDocumentos) {
		this.scerevTDocumentos = scerevTDocumentos;
	}

	public ScerevTDocumento addScerevTDocumento(ScerevTDocumento scerevTDocumento) {
		getScerevTDocumentos().add(scerevTDocumento);
		scerevTDocumento.setScerevDDocumentoTipo(this);

		return scerevTDocumento;
	}

	public ScerevTDocumento removeScerevTDocumento(ScerevTDocumento scerevTDocumento) {
		getScerevTDocumentos().remove(scerevTDocumento);
		scerevTDocumento.setScerevDDocumentoTipo(null);

		return scerevTDocumento;
	}
	
	public List<ScerevRMotivoSoggiornoDocumentoTipo> getScerevRMotivoSoggiornoDocumentoTipos() {
		return scerevRMotivoSoggiornoDocumentoTipos;
	}

	public void setScerevRMotivoSoggiornoDocumentoTipos(
			List<ScerevRMotivoSoggiornoDocumentoTipo> scerevRMotivoSoggiornoDocumentoTipos) {
		this.scerevRMotivoSoggiornoDocumentoTipos = scerevRMotivoSoggiornoDocumentoTipos;
	}
	
	public ScerevRMotivoSoggiornoDocumentoTipo addScerevRMotivoSoggiornoDocumentoTipo(ScerevRMotivoSoggiornoDocumentoTipo scerevRMotivoSoggiornoDocumentoTipo) {
		getScerevRMotivoSoggiornoDocumentoTipos().add(scerevRMotivoSoggiornoDocumentoTipo);
		scerevRMotivoSoggiornoDocumentoTipo.setScerevDDocumentoTipo(this);
		
		return scerevRMotivoSoggiornoDocumentoTipo;
	}
	
	public ScerevRMotivoSoggiornoDocumentoTipo removeScerevRMotivoSoggiornoDocumentoTipo(ScerevRMotivoSoggiornoDocumentoTipo scerevRMotivoSoggiornoDocumentoTipo) {
		getScerevRMotivoSoggiornoDocumentoTipos().remove(scerevRMotivoSoggiornoDocumentoTipo);
		scerevRMotivoSoggiornoDocumentoTipo.setScerevDDocumentoTipo(null);
		
		return scerevRMotivoSoggiornoDocumentoTipo;
	}

}
