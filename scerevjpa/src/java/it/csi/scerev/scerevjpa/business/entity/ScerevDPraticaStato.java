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
 * The persistent class for the scerev_d_pratica_stato database table.
 * 
 */
@Entity
@Table(name="scerev_d_pratica_stato")
@NamedQueries({
	@NamedQuery(name="ScerevDPraticaStato.findAll", query="SELECT s FROM ScerevDPraticaStato s"),
	@NamedQuery(name="ScerevDPraticaStato.findAllValide", query="SELECT s FROM ScerevDPraticaStato s WHERE s.pkPraticaStato = 2 OR s.pkPraticaStato = 4 OR s.pkPraticaStato = 5 OR s.pkPraticaStato = 6"),
	@NamedQuery(name="ScerevDPraticaStato.findAllValideArchivio", query="SELECT s FROM ScerevDPraticaStato s WHERE s.pkPraticaStato = 7 OR s.pkPraticaStato = 8 OR s.pkPraticaStato = 9"),
	@NamedQuery(name="ScerevDPraticaStato.findByValore", query="SELECT s FROM ScerevDPraticaStato s WHERE s.valorePraticaStato = :desc")
})
public class ScerevDPraticaStato implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="pk_pratica_stato")
	private Integer pkPraticaStato;

	@Column(name="codice_pratica_stato")
	private String codicePraticaStato;

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

	@Column(name="valore_pratica_stato")
	private String valorePraticaStato;

	//bi-directional many-to-one association to ScerevTCronologia
	@OneToMany(mappedBy="scerevDPraticaStato")
	private List<ScerevTCronologia> scerevTCronologias;

	//bi-directional many-to-one association to ScerevTPratica
	@OneToMany(mappedBy="scerevDPraticaStato")
	private List<ScerevTPratica> scerevTPraticas;

	public ScerevDPraticaStato() {
	}

	public Integer getPkPraticaStato() {
		return this.pkPraticaStato;
	}

	public void setPkPraticaStato(Integer pkPraticaStato) {
		this.pkPraticaStato = pkPraticaStato;
	}

	public String getCodicePraticaStato() {
		return this.codicePraticaStato;
	}

	public void setCodicePraticaStato(String codicePraticaStato) {
		this.codicePraticaStato = codicePraticaStato;
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

	public String getValorePraticaStato() {
		return this.valorePraticaStato;
	}

	public void setValorePraticaStato(String valorePraticaStato) {
		this.valorePraticaStato = valorePraticaStato;
	}

	public List<ScerevTCronologia> getScerevTCronologias() {
		return this.scerevTCronologias;
	}

	public void setScerevTCronologias(List<ScerevTCronologia> scerevTCronologias) {
		this.scerevTCronologias = scerevTCronologias;
	}

	public ScerevTCronologia addScerevTCronologia(ScerevTCronologia scerevTCronologia) {
		getScerevTCronologias().add(scerevTCronologia);
		scerevTCronologia.setScerevDPraticaStato(this);

		return scerevTCronologia;
	}

	public ScerevTCronologia removeScerevTCronologia(ScerevTCronologia scerevTCronologia) {
		getScerevTCronologias().remove(scerevTCronologia);
		scerevTCronologia.setScerevDPraticaStato(null);

		return scerevTCronologia;
	}

	public List<ScerevTPratica> getScerevTPraticas() {
		return this.scerevTPraticas;
	}

	public void setScerevTPraticas(List<ScerevTPratica> scerevTPraticas) {
		this.scerevTPraticas = scerevTPraticas;
	}

	public ScerevTPratica addScerevTPratica(ScerevTPratica scerevTPratica) {
		getScerevTPraticas().add(scerevTPratica);
		scerevTPratica.setScerevDPraticaStato(this);

		return scerevTPratica;
	}

	public ScerevTPratica removeScerevTPratica(ScerevTPratica scerevTPratica) {
		getScerevTPraticas().remove(scerevTPratica);
		scerevTPratica.setScerevDPraticaStato(null);

		return scerevTPratica;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pkPraticaStato == null) ? 0 : pkPraticaStato.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ScerevDPraticaStato other = (ScerevDPraticaStato) obj;
		if (pkPraticaStato == null) {
			if (other.pkPraticaStato != null)
				return false;
		} else if (!pkPraticaStato.equals(other.pkPraticaStato))
			return false;
		return true;
	}

}
