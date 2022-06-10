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
 * The persistent class for the scerev_d_grado_parentela database table.
 * 
 */
@Entity
@Table(name="scerev_d_grado_parentela")
@NamedQueries({
	@NamedQuery(name="ScerevDGradoParentela.findAll", query="SELECT s FROM ScerevDGradoParentela s"),
	@NamedQuery(name="ScerevDGradoParentela.findByValore", query="SELECT s FROM ScerevDGradoParentela s WHERE UPPER(s.valoreGradoParentela) = :valore")
})
public class ScerevDGradoParentela implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="pk_grado_parentela")
	private Integer pkGradoParentela;

	@Column(name="codice_grado_parentela")
	private String codiceGradoParentela;

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

	@Column(name="valore_grado_parentela")
	private String valoreGradoParentela;

	//bi-directional many-to-one association to ScerevTInfoSupplementari
	@OneToMany(mappedBy="scerevDGradoParentela")
	private List<ScerevTInfoSupplementari> scerevTInfoSupplementaris;

	public ScerevDGradoParentela() {
	}

	public Integer getPkGradoParentela() {
		return this.pkGradoParentela;
	}

	public void setPkGradoParentela(Integer pkGradoParentela) {
		this.pkGradoParentela = pkGradoParentela;
	}

	public String getCodiceGradoParentela() {
		return this.codiceGradoParentela;
	}

	public void setCodiceGradoParentela(String codiceGradoParentela) {
		this.codiceGradoParentela = codiceGradoParentela;
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

	public String getValoreGradoParentela() {
		return this.valoreGradoParentela;
	}

	public void setValoreGradoParentela(String valoreGradoParentela) {
		this.valoreGradoParentela = valoreGradoParentela;
	}

	public List<ScerevTInfoSupplementari> getScerevTInfoSupplementaris() {
		return this.scerevTInfoSupplementaris;
	}

	public void setScerevTInfoSupplementaris(List<ScerevTInfoSupplementari> scerevTInfoSupplementaris) {
		this.scerevTInfoSupplementaris = scerevTInfoSupplementaris;
	}

	public ScerevTInfoSupplementari addScerevTInfoSupplementari(ScerevTInfoSupplementari scerevTInfoSupplementari) {
		getScerevTInfoSupplementaris().add(scerevTInfoSupplementari);
		scerevTInfoSupplementari.setScerevDGradoParentela(this);

		return scerevTInfoSupplementari;
	}

	public ScerevTInfoSupplementari removeScerevTInfoSupplementari(ScerevTInfoSupplementari scerevTInfoSupplementari) {
		getScerevTInfoSupplementaris().remove(scerevTInfoSupplementari);
		scerevTInfoSupplementari.setScerevDGradoParentela(null);

		return scerevTInfoSupplementari;
	}

}
