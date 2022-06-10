/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevjpa.business.entity;
import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the scerev_r_azioni_gruppo_utenti database table.
 * 
 */
@Entity
@Table(name="scerev_r_motivo_soggiorno_documento_tipo")
@NamedQuery(name="ScerevRMotivoSoggiornoDocumentoTipo.findAll", query="SELECT s FROM ScerevRMotivoSoggiornoDocumentoTipo s")
public class ScerevRMotivoSoggiornoDocumentoTipo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "pk_motivo_soggiorno_documento_tipo")
	private Integer pkMotivoSoggiornoDocumentoTipo;
	
	//bi-directional many-to-one association to ScerevDMotivoSoggiorno
	@ManyToOne
	@JoinColumn(name="fk_motivo_soggiorno")
	private ScerevDMotivoSoggiorno scerevDMotivoSoggiorno;

	//bi-directional many-to-one association to ScerevDDocumentoTipo
	@ManyToOne
	@JoinColumn(name="fk_documento_tipo")
	private ScerevDDocumentoTipo scerevDDocumentoTipo;

	public Integer getPkMotivoSoggiornoDocumentoTipo() {
		return pkMotivoSoggiornoDocumentoTipo;
	}

	public void setPkMotivoSoggiornoDocumentoTipo(Integer pkMotivoSoggiornoDocumentoTipo) {
		this.pkMotivoSoggiornoDocumentoTipo = pkMotivoSoggiornoDocumentoTipo;
	}

	public ScerevDMotivoSoggiorno getScerevDMotivoSoggiorno() {
		return scerevDMotivoSoggiorno;
	}

	public void setScerevDMotivoSoggiorno(ScerevDMotivoSoggiorno scerevDMotivoSoggiorno) {
		this.scerevDMotivoSoggiorno = scerevDMotivoSoggiorno;
	}

	public ScerevDDocumentoTipo getScerevDDocumentoTipo() {
		return scerevDDocumentoTipo;
	}

	public void setScerevDDocumentoTipo(ScerevDDocumentoTipo scerevDDocumentoTipo) {
		this.scerevDDocumentoTipo = scerevDDocumentoTipo;
	}

}
