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
 * The persistent class for the scerev_d_motivo_soggiorno database table.
 * 
 */
@Entity
@Table(name="scerev_d_motivo_soggiorno")
@NamedQueries({
	@NamedQuery(name="ScerevDMotivoSoggiorno.findAll", query="SELECT s FROM ScerevDMotivoSoggiorno s"),
	@NamedQuery(name="ScerevDMotivoSoggiorno.findAllAttiviOggi", query="SELECT s FROM ScerevDMotivoSoggiorno s where coalesce(s.dataFineValidita , current_timestamp) >= current_timestamp" ),
	@NamedQuery(name="ScerevDMotivoSoggiorno.findById", query="SELECT s FROM ScerevDMotivoSoggiorno s where s.codiceMotivoSoggiorno = :id" ),
	@NamedQuery(name="ScerevDMotivoSoggiorno.findByIdPerAllegati", query="SELECT s FROM ScerevDMotivoSoggiorno s left join fetch s.scerevRMotivoSoggiornoDocumentoTipos where s.codiceMotivoSoggiorno = :id" )
})
public class ScerevDMotivoSoggiorno implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="pk_motivo_soggiorno")
	private Integer pkMotivoSoggiorno;

	@Column(name="codice_motivo_soggiorno")
	private String codiceMotivoSoggiorno;

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

	@Column(name="valore_motivo_soggiorno")
	private String valoreMotivoSoggiorno;
	
	@Column(name="cittadino_comunitario")
	private Boolean cittadinoComunitario; 

	@Column(name = "tipologia_assistenza")
	private String tipologiaAssistenza;
	
	//bi-directional many-to-one association to ScerevTPratica
	@OneToMany(mappedBy="scerevDMotivoSoggiorno")
	private List<ScerevTPratica> scerevTPraticas;
	
	//bi-directional many-to-one association to ScerevRMotivoSoggiornoDocumentoTipo
	@OneToMany(mappedBy="scerevDMotivoSoggiorno")
	private List<ScerevRMotivoSoggiornoDocumentoTipo> scerevRMotivoSoggiornoDocumentoTipos;

	public ScerevDMotivoSoggiorno() {
	}

	public Integer getPkMotivoSoggiorno() {
		return this.pkMotivoSoggiorno;
	}

	public void setPkMotivoSoggiorno(Integer pkMotivoSoggiorno) {
		this.pkMotivoSoggiorno = pkMotivoSoggiorno;
	}

	public String getCodiceMotivoSoggiorno() {
		return this.codiceMotivoSoggiorno;
	}

	public void setCodiceMotivoSoggiorno(String codiceMotivoSoggiorno) {
		this.codiceMotivoSoggiorno = codiceMotivoSoggiorno;
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

	public String getValoreMotivoSoggiorno() {
		return this.valoreMotivoSoggiorno;
	}

	public void setValoreMotivoSoggiorno(String valoreMotivoSoggiorno) {
		this.valoreMotivoSoggiorno = valoreMotivoSoggiorno;
	}
	
	public Boolean getCittadinoComunitario() {
		return cittadinoComunitario;
	}

	public void setCittadinoComunitario(Boolean cittadinoComunitario) {
		this.cittadinoComunitario = cittadinoComunitario;
	}
	
	public String getTipologiaAssistenza() {
		return tipologiaAssistenza;
	}

	public void setTipologiaAssistenza(String tipologiaAssistenza) {
		this.tipologiaAssistenza = tipologiaAssistenza;
	}

	public List<ScerevTPratica> getScerevTPraticas() {
		return this.scerevTPraticas;
	}

	public void setScerevTPraticas(List<ScerevTPratica> scerevTPraticas) {
		this.scerevTPraticas = scerevTPraticas;
	}

	public ScerevTPratica addScerevTPratica(ScerevTPratica scerevTPratica) {
		getScerevTPraticas().add(scerevTPratica);
		scerevTPratica.setScerevDMotivoSoggiorno(this);

		return scerevTPratica;
	}

	public ScerevTPratica removeScerevTPratica(ScerevTPratica scerevTPratica) {
		getScerevTPraticas().remove(scerevTPratica);
		scerevTPratica.setScerevDMotivoSoggiorno(null);

		return scerevTPratica;
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
		scerevRMotivoSoggiornoDocumentoTipo.setScerevDMotivoSoggiorno(this);
		
		return scerevRMotivoSoggiornoDocumentoTipo;
	}
	
	public ScerevRMotivoSoggiornoDocumentoTipo removeScerevRMotivoSoggiornoDocumentoTipo(ScerevRMotivoSoggiornoDocumentoTipo scerevRMotivoSoggiornoDocumentoTipo) {
		getScerevRMotivoSoggiornoDocumentoTipos().remove(scerevRMotivoSoggiornoDocumentoTipo);
		scerevRMotivoSoggiornoDocumentoTipo.setScerevDMotivoSoggiorno(null);
		
		return scerevRMotivoSoggiornoDocumentoTipo;
	}

}
