/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevjpa.business.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the scerev_r_pratica_pratica_tipo database table.
 * 
 */
@Entity
@Table(name="scerev_r_pratica_pratica_tipo")
@SequenceGenerator(name="\"scerev_r_pratica_pratica_tipo_pk_pratica_pratica_tipo_seq_generator\"",  sequenceName="\"scerev_r_pratica_pratica_tipo_pk_pratica_pratica_tipo_seq\"", initialValue=1, allocationSize=1)
@NamedQuery(name="ScerevRPraticaPraticaTipo.findAll", query="SELECT s FROM ScerevRPraticaPraticaTipo s")
public class ScerevRPraticaPraticaTipo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="pk_pratica_pratica_tipo")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="\"scerev_r_pratica_pratica_tipo_pk_pratica_pratica_tipo_seq_generator\"")
	private Integer pkPraticaPraticaTipo;

	@Column(name="data_cancellazione")
	private Timestamp dataCancellazione;

	@Column(name="data_creazione")
	private Timestamp dataCreazione;

	@Column(name="data_modifica")
	private Timestamp dataModifica;

	//bi-directional many-to-one association to ScerevDPraticaTipo
	@ManyToOne
	@JoinColumn(name="fk_pratica_tipo")
	private ScerevDPraticaTipo scerevDPraticaTipo;

	//bi-directional many-to-one association to ScerevTPratica
	@ManyToOne
	@JoinColumn(name="fk_pratica")
	private ScerevTPratica scerevTPratica;

	public ScerevRPraticaPraticaTipo() {
	}

	public Integer getPkPraticaPraticaTipo() {
		return this.pkPraticaPraticaTipo;
	}

	public void setPkPraticaPraticaTipo(Integer pkPraticaPraticaTipo) {
		this.pkPraticaPraticaTipo = pkPraticaPraticaTipo;
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

	public ScerevDPraticaTipo getScerevDPraticaTipo() {
		return this.scerevDPraticaTipo;
	}

	public void setScerevDPraticaTipo(ScerevDPraticaTipo scerevDPraticaTipo) {
		this.scerevDPraticaTipo = scerevDPraticaTipo;
	}

	public ScerevTPratica getScerevTPratica() {
		return this.scerevTPratica;
	}

	public void setScerevTPratica(ScerevTPratica scerevTPratica) {
		this.scerevTPratica = scerevTPratica;
	}

}
