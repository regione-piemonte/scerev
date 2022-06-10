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
 * The persistent class for the scerev_d_pratica_tipo database table.
 * 
 */
@Entity
@Table(name="scerev_d_pratica_tipo")

@NamedQueries({
	@NamedQuery(name="ScerevDPraticaTipo.find", query="SELECT s FROM ScerevDPraticaTipo s where s.valorePraticaTipo = :valorePratica"),
	@NamedQuery(name="ScerevDPraticaTipo.findAllValide", query="SELECT s FROM ScerevDPraticaTipo s where s.dataFineValidita is null"),
	@NamedQuery(name="ScerevDPraticaTipo.findByCodice", query="SELECT s FROM ScerevDPraticaTipo s where s.codicePraticaTipo = :codicePratica")
})
public class ScerevDPraticaTipo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="pk_pratica_tipo")
	private Integer pkPraticaTipo;

	@Column(name="codice_pratica_tipo")
	private String codicePraticaTipo;

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

	@Column(name="valore_pratica_tipo")
	private String valorePraticaTipo;

	//bi-directional many-to-one association to ScerevRPraticaPraticaTipo
	@OneToMany(mappedBy="scerevDPraticaTipo")
	private List<ScerevRPraticaPraticaTipo> scerevRPraticaPraticaTipos;

	public ScerevDPraticaTipo() {
	}

	public Integer getPkPraticaTipo() {
		return this.pkPraticaTipo;
	}

	public void setPkPraticaTipo(Integer pkPraticaTipo) {
		this.pkPraticaTipo = pkPraticaTipo;
	}

	public String getCodicePraticaTipo() {
		return this.codicePraticaTipo;
	}

	public void setCodicePraticaTipo(String codicePraticaTipo) {
		this.codicePraticaTipo = codicePraticaTipo;
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

	public String getValorePraticaTipo() {
		return this.valorePraticaTipo;
	}

	public void setValorePraticaTipo(String valorePraticaTipo) {
		this.valorePraticaTipo = valorePraticaTipo;
	}

	public List<ScerevRPraticaPraticaTipo> getScerevRPraticaPraticaTipos() {
		return this.scerevRPraticaPraticaTipos;
	}

	public void setScerevRPraticaPraticaTipos(List<ScerevRPraticaPraticaTipo> scerevRPraticaPraticaTipos) {
		this.scerevRPraticaPraticaTipos = scerevRPraticaPraticaTipos;
	}

	public ScerevRPraticaPraticaTipo addScerevRPraticaPraticaTipo(ScerevRPraticaPraticaTipo scerevRPraticaPraticaTipo) {
		getScerevRPraticaPraticaTipos().add(scerevRPraticaPraticaTipo);
		scerevRPraticaPraticaTipo.setScerevDPraticaTipo(this);

		return scerevRPraticaPraticaTipo;
	}

	public ScerevRPraticaPraticaTipo removeScerevRPraticaPraticaTipo(ScerevRPraticaPraticaTipo scerevRPraticaPraticaTipo) {
		getScerevRPraticaPraticaTipos().remove(scerevRPraticaPraticaTipo);
		scerevRPraticaPraticaTipo.setScerevDPraticaTipo(null);

		return scerevRPraticaPraticaTipo;
	}

}
