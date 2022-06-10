/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevjpa.business.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the scerev_t_localita database table.
 * 
 */
@Entity
@Table(name="scerev_t_localita")
@SequenceGenerator(name="\"scerev_t_localita_pk_localita_seq_generator\"",  sequenceName="\"scerev_t_localita_pk_localita_seq\"", initialValue=1, allocationSize=1)
@NamedQueries({
	@NamedQuery(name="ScerevTLocalita.findAll", query="SELECT s FROM ScerevTLocalita s"),
	@NamedQuery(name="ScerevTLocalita.find", query="SELECT s FROM ScerevTLocalita s WHERE s.scerevDNazione = :nazione AND "
			+ "s.comune = :comune AND s.cap = :cap AND s.indirizzo = :indirizzo AND s.numeroCivico = :civico ")
})
public class ScerevTLocalita implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="pk_localita")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="\"scerev_t_localita_pk_localita_seq_generator\"")
	private Integer pkLocalita;

	private String cap;

	private String comune;

	@Column(name="data_cancellazione")
	private Timestamp dataCancellazione;

	@Column(name="data_creazione")
	private Timestamp dataCreazione;

	@Column(name="data_modifica")
	private Timestamp dataModifica;

	private String indirizzo;

	@Column(name="numero_civico")
	private String numeroCivico;
	
	@Column(name="codice_istat_comune")
	private String codiceIstatComune;

	//bi-directional many-to-one association to ScerevDNazione
	@ManyToOne
	@JoinColumn(name="fk_nazione")
	private ScerevDNazione scerevDNazione;

	//bi-directional many-to-one association to ScerevTPratica
	@OneToMany(mappedBy="scerevTLocalita1")
	private List<ScerevTPratica> scerevTPraticas1;

	//bi-directional many-to-one association to ScerevTPratica
	@OneToMany(mappedBy="scerevTLocalita2")
	private List<ScerevTPratica> scerevTPraticas2;

	public ScerevTLocalita() {
	}

	public Integer getPkLocalita() {
		return this.pkLocalita;
	}

	public void setPkLocalita(Integer pkLocalita) {
		this.pkLocalita = pkLocalita;
	}

	public String getCap() {
		return this.cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	public String getComune() {
		return this.comune;
	}

	public void setComune(String comune) {
		this.comune = comune;
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

	public String getIndirizzo() {
		return this.indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getNumeroCivico() {
		return this.numeroCivico;
	}

	public void setNumeroCivico(String numeroCivico) {
		this.numeroCivico = numeroCivico;
	}

	public ScerevDNazione getScerevDNazione() {
		return this.scerevDNazione;
	}

	public void setScerevDNazione(ScerevDNazione scerevDNazione) {
		this.scerevDNazione = scerevDNazione;
	}
	
	public String getCodiceIstatComune() {
		return codiceIstatComune;
	}

	public void setCodiceIstatComune(String codiceIstatComune) {
		this.codiceIstatComune = codiceIstatComune;
	}

	public List<ScerevTPratica> getScerevTPraticas1() {
		return this.scerevTPraticas1;
	}

	public void setScerevTPraticas1(List<ScerevTPratica> scerevTPraticas1) {
		this.scerevTPraticas1 = scerevTPraticas1;
	}

	public ScerevTPratica addScerevTPraticas1(ScerevTPratica scerevTPraticas1) {
		getScerevTPraticas1().add(scerevTPraticas1);
		scerevTPraticas1.setScerevTLocalita1(this);

		return scerevTPraticas1;
	}

	public ScerevTPratica removeScerevTPraticas1(ScerevTPratica scerevTPraticas1) {
		getScerevTPraticas1().remove(scerevTPraticas1);
		scerevTPraticas1.setScerevTLocalita1(null);

		return scerevTPraticas1;
	}

	public List<ScerevTPratica> getScerevTPraticas2() {
		return this.scerevTPraticas2;
	}

	public void setScerevTPraticas2(List<ScerevTPratica> scerevTPraticas2) {
		this.scerevTPraticas2 = scerevTPraticas2;
	}

	public ScerevTPratica addScerevTPraticas2(ScerevTPratica scerevTPraticas2) {
		getScerevTPraticas2().add(scerevTPraticas2);
		scerevTPraticas2.setScerevTLocalita2(this);

		return scerevTPraticas2;
	}

	public ScerevTPratica removeScerevTPraticas2(ScerevTPratica scerevTPraticas2) {
		getScerevTPraticas2().remove(scerevTPraticas2);
		scerevTPraticas2.setScerevTLocalita2(null);

		return scerevTPraticas2;
	}

}
