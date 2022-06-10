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
 * The persistent class for the scerev_d_motivazione database table.
 * 
 */
@Entity
@Table(name="scerev_d_motivazione")
@NamedQuery(name="ScerevDMotivazione.findAll", query="SELECT s FROM ScerevDMotivazione s")
public class ScerevDMotivazione implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="pk_motivazione")
	private Integer pkMotivazione;

	@Column(name="codice_motivazione")
	private String codiceMotivazione;

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

	@Column(name="tipo_motivazione")
	private String tipoMotivazione;

	@Column(name="valore_motivazione")
	private String valoreMotivazione;

	//bi-directional many-to-one association to ScerevTPratica
	@OneToMany(mappedBy="scerevDMotivazione")
	private List<ScerevTPratica> scerevTPraticas;

	public ScerevDMotivazione() {
	}

	public Integer getPkMotivazione() {
		return this.pkMotivazione;
	}

	public void setPkMotivazione(Integer pkMotivazione) {
		this.pkMotivazione = pkMotivazione;
	}

	public String getCodiceMotivazione() {
		return this.codiceMotivazione;
	}

	public void setCodiceMotivazione(String codiceMotivazione) {
		this.codiceMotivazione = codiceMotivazione;
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

	public String getTipoMotivazione() {
		return this.tipoMotivazione;
	}

	public void setTipoMotivazione(String tipoMotivazione) {
		this.tipoMotivazione = tipoMotivazione;
	}

	public String getValoreMotivazione() {
		return this.valoreMotivazione;
	}

	public void setValoreMotivazione(String valoreMotivazione) {
		this.valoreMotivazione = valoreMotivazione;
	}

	public List<ScerevTPratica> getScerevTPraticas() {
		return this.scerevTPraticas;
	}

	public void setScerevTPraticas(List<ScerevTPratica> scerevTPraticas) {
		this.scerevTPraticas = scerevTPraticas;
	}

	public ScerevTPratica addScerevTPratica(ScerevTPratica scerevTPratica) {
		getScerevTPraticas().add(scerevTPratica);
		scerevTPratica.setScerevDMotivazione(this);

		return scerevTPratica;
	}

	public ScerevTPratica removeScerevTPratica(ScerevTPratica scerevTPratica) {
		getScerevTPraticas().remove(scerevTPratica);
		scerevTPratica.setScerevDMotivazione(null);

		return scerevTPratica;
	}

}
