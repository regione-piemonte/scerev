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
 * The persistent class for the scerev_d_motivo_domicilio database table.
 * 
 */
@Entity
@Table(name="scerev_d_motivo_domicilio")
@NamedQueries({
	@NamedQuery(name="ScerevDMotivoDomicilio.findAll", query="SELECT s FROM ScerevDMotivoDomicilio s"),
	@NamedQuery(name="ScerevDMotivoDomicilio.findAllAttiviOggi", query="SELECT s FROM ScerevDMotivoDomicilio s where coalesce(s.dataFineValidita , current_timestamp) >= current_timestamp" ),
	@NamedQuery(name="ScerevDMotivoDomicilio.findById", query="SELECT s FROM ScerevDMotivoDomicilio s where s.codiceMotivoDomicilio = :id" )
})

public class ScerevDMotivoDomicilio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="pk_motivo_domicilio")
	private Integer pkMotivoDomicilio;

	@Column(name="codice_motivo_domicilio")
	private String codiceMotivoDomicilio;

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

	@Column(name="valore_motivo_domicilio")
	private String valoreMotivoDomicilio;

	//bi-directional many-to-one association to ScerevTPratica
	@OneToMany(mappedBy="scerevDMotivoDomicilio")
	private List<ScerevTPratica> scerevTPraticas;

	public ScerevDMotivoDomicilio() {
	}

	public Integer getPkMotivoDomicilio() {
		return this.pkMotivoDomicilio;
	}

	public void setPkMotivoDomicilio(Integer pkMotivoDomicilio) {
		this.pkMotivoDomicilio = pkMotivoDomicilio;
	}

	public String getCodiceMotivoDomicilio() {
		return this.codiceMotivoDomicilio;
	}

	public void setCodiceMotivoDomicilio(String codiceMotivoDomicilio) {
		this.codiceMotivoDomicilio = codiceMotivoDomicilio;
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

	public String getValoreMotivoDomicilio() {
		return this.valoreMotivoDomicilio;
	}

	public void setValoreMotivoDomicilio(String valoreMotivoDomicilio) {
		this.valoreMotivoDomicilio = valoreMotivoDomicilio;
	}

	public List<ScerevTPratica> getScerevTPraticas() {
		return this.scerevTPraticas;
	}

	public void setScerevTPraticas(List<ScerevTPratica> scerevTPraticas) {
		this.scerevTPraticas = scerevTPraticas;
	}

	public ScerevTPratica addScerevTPratica(ScerevTPratica scerevTPratica) {
		getScerevTPraticas().add(scerevTPratica);
		scerevTPratica.setScerevDMotivoDomicilio(this);

		return scerevTPratica;
	}

	public ScerevTPratica removeScerevTPratica(ScerevTPratica scerevTPratica) {
		getScerevTPraticas().remove(scerevTPratica);
		scerevTPratica.setScerevDMotivoDomicilio(null);

		return scerevTPratica;
	}

}
