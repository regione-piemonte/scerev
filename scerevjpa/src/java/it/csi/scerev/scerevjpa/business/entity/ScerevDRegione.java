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
 * The persistent class for the scerev_d_regione database table.
 * 
 */
@Entity
@Table(name="scerev_d_regione")
@NamedQueries({
    @NamedQuery(name="ScerevDRegione.findAll",
                query="SELECT s FROM ScerevDRegione s"),
    @NamedQuery(name="ScerevDRegione.findRegionePerCodice",
                query="SELECT s FROM ScerevDRegione s WHERE s.codiceRegione = :codRegione"),
    @NamedQuery(name="ScerevDRegione.findRegionePerNome",
    			query="SELECT s FROM ScerevDRegione s WHERE s.nomeRegione = :nomeReg")
})
public class ScerevDRegione implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="pk_regione")
	private Integer pkRegione;

	@Column(name="codice_regione")
	private String codiceRegione;

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

	@Column(name="nome_regione")
	private String nomeRegione;

	//bi-directional many-to-one association to ScerevTPratica
	@OneToMany(mappedBy="scerevDRegione")
	private List<ScerevTPratica> scerevTPraticas;

	public ScerevDRegione() {
	}

	public Integer getPkRegione() {
		return this.pkRegione;
	}

	public void setPkRegione(Integer pkRegione) {
		this.pkRegione = pkRegione;
	}

	public String getCodiceRegione() {
		return this.codiceRegione;
	}

	public void setCodiceRegione(String codiceRegione) {
		this.codiceRegione = codiceRegione;
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

	public String getNomeRegione() {
		return this.nomeRegione;
	}

	public void setNomeRegione(String nomeRegione) {
		this.nomeRegione = nomeRegione;
	}

	public List<ScerevTPratica> getScerevTPraticas() {
		return this.scerevTPraticas;
	}

	public void setScerevTPraticas(List<ScerevTPratica> scerevTPraticas) {
		this.scerevTPraticas = scerevTPraticas;
	}

	public ScerevTPratica addScerevTPratica(ScerevTPratica scerevTPratica) {
		getScerevTPraticas().add(scerevTPratica);
		scerevTPratica.setScerevDRegione(this);

		return scerevTPratica;
	}

	public ScerevTPratica removeScerevTPratica(ScerevTPratica scerevTPratica) {
		getScerevTPraticas().remove(scerevTPratica);
		scerevTPratica.setScerevDRegione(null);

		return scerevTPratica;
	}

}
