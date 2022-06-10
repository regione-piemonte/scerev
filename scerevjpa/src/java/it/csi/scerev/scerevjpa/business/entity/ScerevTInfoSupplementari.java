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
 * The persistent class for the scerev_t_info_supplementari database table.
 * 
 */
@Entity
@Table(name="scerev_t_info_supplementari")
@SequenceGenerator(name="\"scerev_t_info_supplementari_pk_info_supplementari_seq_generator\"",  sequenceName="\"scerev_t_info_supplementari_pk_info_supplementari_seq\"", initialValue=1, allocationSize=1)
@NamedQuery(name="ScerevTInfoSupplementari.findAll", query="SELECT s FROM ScerevTInfoSupplementari s")
public class ScerevTInfoSupplementari implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="pk_info_supplementari")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="\"scerev_t_info_supplementari_pk_info_supplementari_seq_generator\"")
	private Integer pkInfoSupplementari;

	@Column(name="cf_parente")
	private String cfParente;

	@Column(name="civico_sede_studio")
	private String civicoSedeStudio;

	@Column(name="cognome_parente")
	private String cognomeParente;

	@Column(name="comune_lavoro")
	private String comuneLavoro;

	@Column(name="comune_sede_studio")
	private String comuneSedeStudio;

	@Column(name="data_cancellazione")
	private Timestamp dataCancellazione;

	@Column(name="data_creazione")
	private Timestamp dataCreazione;

	@Column(name="data_fine_contratto")
	private Timestamp dataFineContratto;

	@Column(name="data_fine_iscrizione")
	private Timestamp dataFineIscrizione;

	@Column(name="data_modifica")
	private Timestamp dataModifica;

	@Column(name="ditta_datore_lavoro")
	private String dittaDatoreLavoro;

	@Column(name="indirizzo_lavoro")
	private String indirizzoLavoro;

	@Column(name="indirizzo_sede_studio")
	private String indirizzoSedeStudio;

	@Column(name="nome_istituto")
	private String nomeIstituto;

	@Column(name="nome_parente")
	private String nomeParente;

	@Column(name="numero_civico_lavoro")
	private String numeroCivicoLavoro;

	@Column(name="numero_iscrizione")
	private String numeroIscrizione;

	@Column(name="numero_telefono_datore")
	private String numeroTelefonoDatore;

	@Column(name="partita_iva")
	private String partitaIva;

	private String professione;

	@Column(name="provincia_lavoro")
	private String provinciaLavoro;

	@Column(name="provincia_sede_studio")
	private String provinciaSedeStudio;

	@Column(name="tipologia_lavoro")
	private Boolean tipologiaLavoro;
	
	@Column(name="motivazione_domanda")
	private String motivazioneDomanda;

	//bi-directional many-to-one association to ScerevDGradoParentela
	@ManyToOne
	@JoinColumn(name="fk_grado_parentela")
	private ScerevDGradoParentela scerevDGradoParentela;

	//bi-directional many-to-one association to ScerevTPratica
	@OneToMany(mappedBy="scerevTInfoSupplementari")
	private List<ScerevTPratica> scerevTPraticas;

	public ScerevTInfoSupplementari() {
	}

	public Integer getPkInfoSupplementari() {
		return this.pkInfoSupplementari;
	}

	public void setPkInfoSupplementari(Integer pkInfoSupplementari) {
		this.pkInfoSupplementari = pkInfoSupplementari;
	}

	public String getCfParente() {
		return this.cfParente;
	}

	public void setCfParente(String cfParente) {
		this.cfParente = cfParente;
	}

	public String getCivicoSedeStudio() {
		return this.civicoSedeStudio;
	}

	public void setCivicoSedeStudio(String civicoSedeStudio) {
		this.civicoSedeStudio = civicoSedeStudio;
	}

	public String getCognomeParente() {
		return this.cognomeParente;
	}

	public void setCognomeParente(String cognomeParente) {
		this.cognomeParente = cognomeParente;
	}

	public String getComuneLavoro() {
		return this.comuneLavoro;
	}

	public void setComuneLavoro(String comuneLavoro) {
		this.comuneLavoro = comuneLavoro;
	}

	public String getComuneSedeStudio() {
		return this.comuneSedeStudio;
	}

	public void setComuneSedeStudio(String comuneSedeStudio) {
		this.comuneSedeStudio = comuneSedeStudio;
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

	public Timestamp getDataFineContratto() {
		return this.dataFineContratto;
	}

	public void setDataFineContratto(Timestamp dataFineContratto) {
		this.dataFineContratto = dataFineContratto;
	}

	public Timestamp getDataFineIscrizione() {
		return this.dataFineIscrizione;
	}

	public void setDataFineIscrizione(Timestamp dataFineIscrizione) {
		this.dataFineIscrizione = dataFineIscrizione;
	}

	public Timestamp getDataModifica() {
		return this.dataModifica;
	}

	public void setDataModifica(Timestamp dataModifica) {
		this.dataModifica = dataModifica;
	}

	public String getDittaDatoreLavoro() {
		return this.dittaDatoreLavoro;
	}

	public void setDittaDatoreLavoro(String dittaDatoreLavoro) {
		this.dittaDatoreLavoro = dittaDatoreLavoro;
	}

	public String getIndirizzoLavoro() {
		return this.indirizzoLavoro;
	}

	public void setIndirizzoLavoro(String indirizzoLavoro) {
		this.indirizzoLavoro = indirizzoLavoro;
	}

	public String getIndirizzoSedeStudio() {
		return this.indirizzoSedeStudio;
	}

	public void setIndirizzoSedeStudio(String indirizzoSedeStudio) {
		this.indirizzoSedeStudio = indirizzoSedeStudio;
	}

	public String getNomeIstituto() {
		return this.nomeIstituto;
	}

	public void setNomeIstituto(String nomeIstituto) {
		this.nomeIstituto = nomeIstituto;
	}

	public String getNomeParente() {
		return this.nomeParente;
	}

	public void setNomeParente(String nomeParente) {
		this.nomeParente = nomeParente;
	}

	public String getNumeroCivicoLavoro() {
		return this.numeroCivicoLavoro;
	}

	public void setNumeroCivicoLavoro(String numeroCivicoLavoro) {
		this.numeroCivicoLavoro = numeroCivicoLavoro;
	}

	public String getNumeroIscrizione() {
		return this.numeroIscrizione;
	}

	public void setNumeroIscrizione(String numeroIscrizione) {
		this.numeroIscrizione = numeroIscrizione;
	}

	public String getNumeroTelefonoDatore() {
		return this.numeroTelefonoDatore;
	}

	public void setNumeroTelefonoDatore(String numeroTelefonoDatore) {
		this.numeroTelefonoDatore = numeroTelefonoDatore;
	}

	public String getPartitaIva() {
		return this.partitaIva;
	}

	public void setPartitaIva(String partitaIva) {
		this.partitaIva = partitaIva;
	}

	public String getProfessione() {
		return this.professione;
	}

	public void setProfessione(String professione) {
		this.professione = professione;
	}

	public String getProvinciaLavoro() {
		return this.provinciaLavoro;
	}

	public void setProvinciaLavoro(String provinciaLavoro) {
		this.provinciaLavoro = provinciaLavoro;
	}

	public String getProvinciaSedeStudio() {
		return this.provinciaSedeStudio;
	}

	public void setProvinciaSedeStudio(String provinciaSedeStudio) {
		this.provinciaSedeStudio = provinciaSedeStudio;
	}

	public Boolean getTipologiaLavoro() {
		return this.tipologiaLavoro;
	}

	public void setTipologiaLavoro(Boolean tipologiaLavoro) {
		this.tipologiaLavoro = tipologiaLavoro;
	}

	public String getMotivazioneDomanda() {
		return motivazioneDomanda;
	}

	public void setMotivazioneDomanda(String motivazioneDomanda) {
		this.motivazioneDomanda = motivazioneDomanda;
	}

	public ScerevDGradoParentela getScerevDGradoParentela() {
		return this.scerevDGradoParentela;
	}

	public void setScerevDGradoParentela(ScerevDGradoParentela scerevDGradoParentela) {
		this.scerevDGradoParentela = scerevDGradoParentela;
	}

	public List<ScerevTPratica> getScerevTPraticas() {
		return this.scerevTPraticas;
	}

	public void setScerevTPraticas(List<ScerevTPratica> scerevTPraticas) {
		this.scerevTPraticas = scerevTPraticas;
	}

	public ScerevTPratica addScerevTPratica(ScerevTPratica scerevTPratica) {
		getScerevTPraticas().add(scerevTPratica);
		scerevTPratica.setScerevTInfoSupplementari(this);

		return scerevTPratica;
	}

	public ScerevTPratica removeScerevTPratica(ScerevTPratica scerevTPratica) {
		getScerevTPraticas().remove(scerevTPratica);
		scerevTPratica.setScerevTInfoSupplementari(null);

		return scerevTPratica;
	}

}
