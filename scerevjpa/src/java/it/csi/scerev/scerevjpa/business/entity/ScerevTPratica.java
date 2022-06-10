/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevjpa.business.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;


/**
 * The persistent class for the scerev_t_pratica database table.
 * 
 */
@Entity
@Table(name="scerev_t_pratica")
@SequenceGenerator(name="\"scerev_t_pratica_pk_pratica_seq_generator\"",  sequenceName="\"scerev_t_pratica_pk_pratica_seq\"", initialValue=1, allocationSize=1)
@NamedQueries({
	@NamedQuery(name="ScerevTPratica.findAll", query="SELECT s FROM ScerevTPratica s"),
	@NamedQuery(name="ScerevTPratica.findPerNumeroPratica", query="SELECT s FROM ScerevTPratica s where s.numeroPratica = :numeroPratica"),
	@NamedQuery(name="ScerevTPratica.findPerCF", query="SELECT s FROM ScerevTPratica s where s.cfAssistito = :cfAssistito order by s.dataCreazione desc"),
	@NamedQuery(name="ScerevTPratica.findPraticaPerCFeStato", query="SELECT s FROM ScerevTPratica s where s.cfAssistito = :cfAssistito and s.scerevDPraticaStato.codicePraticaStato in (:codicePraticaStato) "),
	@NamedQuery(name="ScerevTPratica.findPraticaPerCFeDataRevocaAssistenza", query="SELECT s FROM ScerevTPratica s where s.cfAssistito = :citId and"
			+ " s.dataRevocaAssistenza IS NOT NULL"
			+ " order by s.dataModifica desc"),
	@NamedQuery(name="ScerevTPratica.findCambioMedicoAssistenzaTemporanea", query=
	"SELECT s FROM ScerevTPratica s " + 
	"	where s.cfAssistito = :codFiscale" + 
	"	and s.scerevDAsl2.codiceAzienda = :idASL" + 
	"	and (not(s.dataFineAssistenzaImposta = :dataIllimitata)" + 
	"		 or s.dataFineAssistenzaImposta is null) " + 
	"	and s.scerevDPraticaStato.codicePraticaStato = '8'" + //pratica chiusa
	"	and s.idAuraMedico <> :idMedico" + 
	"	and s.dataRevocaMedico is null" + 
	"	and (current_timestamp between coalesce(s.dataInizioAssistenzaImposta, current_timestamp) and coalesce(s.dataFineAssistenzaImposta, current_timestamp))" + 
	"	and s.dataCancellazione is null"
	),
	@NamedQuery(name="ScerevTPratica.findPerRevoca", query="SELECT s FROM ScerevTPratica s where s.cfAssistito = :citId and "
			+ "s.idAuraMedico = :idAuraMedico AND "
			+ "s.dataRevocaMedico IS NULL AND "
			+ "s.scerevDPraticaStato.codicePraticaStato = '8' "
			+ "order by s.dataCreazione desc"),
	@NamedQuery(name="ScerevTPratica.findPerRevocaNuovaRichiesta", query="SELECT s FROM ScerevTPratica s where s.cfAssistito = :citId and "
			+ "s.numeroPratica <> :numPratica AND "
			+ "s.dataRevocaMedico IS NULL AND "
			+ "s.scerevDPraticaStato.codicePraticaStato = '8' "
			+ "order by s.dataCreazione desc")
})
public class ScerevTPratica implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="pk_pratica")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="\"scerev_t_pratica_pk_pratica_seq_generator\"")
	private Integer pkPratica;

	@Column(name="ambito_domicilio_dichiarato")
	private String ambitoDomicilioDichiarato;

	@Column(name="asl_residenza")
	private String aslResidenza;

	@Column(name="cf_assistito")
	private String cfAssistito;

	@Column(name="cf_delegato")
	private String cfDelegato;

	@Column(name="cf_operatore")
	private String cfOperatore;

	@Column(name="codice_asl_precedente")
	private String codiceAslPrecedente;

	@Column(name="cognome_assistito")
	private String cognomeAssistito;

	@Column(name="cognome_delegato")
	private String cognomeDelegato;

	@Column(name="cognome_medico")
	private String cognomeMedico;

	@Column(name="cognome_medico_precedente")
	private String cognomeMedicoPrecedente;

	@Column(name="cognome_operatore")
	private String cognomeOperatore;

	@Column(name="data_cancellazione")
	private Timestamp dataCancellazione;

	@Column(name="data_chiusura")
	private Timestamp dataChiusura;

	@Column(name="data_creazione")
	private Timestamp dataCreazione;

	@Column(name="data_fine_assistenza_imposta")
	private Timestamp dataFineAssistenzaImposta;

	@Column(name="data_fine_assistenza_richiesta")
	private Timestamp dataFineAssistenzaRichiesta;

	@Column(name="data_inizio_assistenza_imposta")
	private Timestamp dataInizioAssistenzaImposta;

	@Column(name="data_modifica")
	private Timestamp dataModifica;

	@Column(name="data_nascita_assistito")
	private Timestamp dataNascitaAssistito;

	@Column(name="data_revoca_assistenza")
	private Timestamp dataRevocaAssistenza;

	@Column(name="data_revoca_medico")
	private Timestamp dataRevocaMedico;

	@Column(name="deroga_associazione")
	private Boolean derogaAssociazione;

	@Column(name="deroga_massimale")
	private Boolean derogaMassimale;

	@Column(name="deroga_massimale_temp")
	private Boolean derogaMassimaleTemp;

	@Column(name="deroga_pediatra")
	private Boolean derogaPediatra;

	@Column(name="deroga_territoriale")
	private Boolean derogaTerritoriale;

	@Column(name="distretto_domicilio_dichiarato")
	private String distrettoDomicilioDichiarato;

	@Column(name="id_aura_medico")
	private String idAuraMedico;
	
	@Column(name="flag_autolimitato_medico")
	private Boolean flagAutolimitatoMedico;

	@Column(name="luogo_nascita_assistito")
	private String luogoNascitaAssistito;

	@Column(name="medico_generico")
	private Boolean medicoGenerico;

	@Column(name="medico_revocato")
	private Boolean medicoRevocato;

	@Column(name="nome_assistito")
	private String nomeAssistito;

	@Column(name="nome_delegato")
	private String nomeDelegato;

	@Column(name="nome_medico")
	private String nomeMedico;

	@Column(name="nome_medico_precedente")
	private String nomeMedicoPrecedente;

	@Column(name="nome_operatore")
	private String nomeOperatore;

	@Column(name="numero_pratica")
	private Integer numeroPratica;

	@Column(name="ricongiungimento_familiare")
	private Boolean ricongiungimentoFamiliare;

	@Column(name="sesso_assistito")
	private String sessoAssistito;

	@Column(name="tipologia_gestione_pratica")
	private String tipologiaGestionePratica;

	@Column(name="tipologia_medico")
	private String tipologiaMedico;
	
	@Column(name="codice_fiscale_medico")
	private String codiceFiscaleMedico;
	
	@Column(name="recapito_telefonico_assistito")
	private String recapitoTelefonicoAssistito;
	
	@Column(name="motivo_revoca_medico")
	private String motivoRevocaMedico;

	//bi-directional many-to-one association to ScerevRPraticaPraticaTipo
	@OneToMany(mappedBy="scerevTPratica")
//	private List<ScerevRPraticaPraticaTipo> scerevRPraticaPraticaTipos;
	private Set<ScerevRPraticaPraticaTipo> scerevRPraticaPraticaTipos;

	//bi-directional many-to-one association to ScerevTCronologia
	@OneToMany(mappedBy="scerevTPratica")
//	private List<ScerevTCronologia> scerevTCronologias;
	private Set<ScerevTCronologia> scerevTCronologias;

	//bi-directional many-to-one association to ScerevTDocumento
	@OneToMany(mappedBy="scerevTPratica", cascade = CascadeType.MERGE)
//	private List<ScerevTDocumento> scerevTDocumentos;
	private Set<ScerevTDocumento> scerevTDocumentos;

	//bi-directional many-to-one association to ScerevDAmbito
	@ManyToOne
	@JoinColumn(name="fk_ambito_medico")
	private ScerevDAmbito scerevDAmbito;

	//bi-directional many-to-one association to ScerevDAsl
	@ManyToOne
	@JoinColumn(name="fk_azienda_domicilio_dichiarata")
	private ScerevDAsl scerevDAsl1;

	//bi-directional many-to-one association to ScerevDAsl
	@ManyToOne
	@JoinColumn(name="fk_azienda_iscrizione")
	private ScerevDAsl scerevDAsl2;

	//bi-directional many-to-one association to ScerevDAsl
	@ManyToOne
	@JoinColumn(name="fk_azienda_medico")
	private ScerevDAsl scerevDAsl3;

	//bi-directional many-to-one association to ScerevDCittadinanza
	@ManyToOne
	@JoinColumn(name="fk_cittadinanza")
	private ScerevDCittadinanza scerevDCittadinanza;

	//bi-directional many-to-one association to ScerevDDistretto
	@ManyToOne
	@JoinColumn(name="fk_distretto_medico")
	private ScerevDDistretto scerevDDistretto;

	//bi-directional many-to-one association to ScerevDMotivazione
	@ManyToOne
	@JoinColumn(name="fk_motivazione")
	private ScerevDMotivazione scerevDMotivazione;

	//bi-directional many-to-one association to ScerevDMotivoDomicilio
	@ManyToOne
	@JoinColumn(name="fk_motivo_domicilio")
	private ScerevDMotivoDomicilio scerevDMotivoDomicilio;

	//bi-directional many-to-one association to ScerevDMotivoSoggiorno
	@ManyToOne
	@JoinColumn(name="fk_motivo_soggiorno")
	private ScerevDMotivoSoggiorno scerevDMotivoSoggiorno;

	//bi-directional many-to-one association to ScerevDPraticaStato
	@ManyToOne
	@JoinColumn(name="fk_pratica_stato")
	private ScerevDPraticaStato scerevDPraticaStato;

	//bi-directional many-to-one association to ScerevDRegione
	@ManyToOne
	@JoinColumn(name="fk_regione")
	private ScerevDRegione scerevDRegione;

	//bi-directional many-to-one association to ScerevDTipologiaProfilo
	@ManyToOne
	@JoinColumn(name="fk_tipologia_profilo")
	private ScerevDTipologiaProfilo scerevDTipologiaProfilo;

	//bi-directional many-to-one association to ScerevTInfoSupplementari
	@ManyToOne
	@JoinColumn(name="fk_info_supplementari")
	private ScerevTInfoSupplementari scerevTInfoSupplementari;

	//bi-directional many-to-one association to ScerevTLocalita
	@ManyToOne
	@JoinColumn(name="fk_localita_residenza_dichiarata")
	private ScerevTLocalita scerevTLocalita1;

	//bi-directional many-to-one association to ScerevTLocalita
	@ManyToOne
	@JoinColumn(name="fk_localita_domicilio_dichiarato")
	private ScerevTLocalita scerevTLocalita2;

	public ScerevTPratica() {
	}

	public Integer getPkPratica() {
		return this.pkPratica;
	}

	public void setPkPratica(Integer pkPratica) {
		this.pkPratica = pkPratica;
	}

	public String getAmbitoDomicilioDichiarato() {
		return this.ambitoDomicilioDichiarato;
	}

	public void setAmbitoDomicilioDichiarato(String ambitoDomicilioDichiarato) {
		this.ambitoDomicilioDichiarato = ambitoDomicilioDichiarato;
	}

	public String getAslResidenza() {
		return this.aslResidenza;
	}

	public void setAslResidenza(String aslResidenza) {
		this.aslResidenza = aslResidenza;
	}

	public String getCfAssistito() {
		return this.cfAssistito;
	}

	public void setCfAssistito(String cfAssistito) {
		this.cfAssistito = cfAssistito;
	}

	public String getCfDelegato() {
		return this.cfDelegato;
	}

	public void setCfDelegato(String cfDelegato) {
		this.cfDelegato = cfDelegato;
	}

	public String getCfOperatore() {
		return this.cfOperatore;
	}

	public void setCfOperatore(String cfOperatore) {
		this.cfOperatore = cfOperatore;
	}

	public String getCodiceAslPrecedente() {
		return this.codiceAslPrecedente;
	}

	public void setCodiceAslPrecedente(String codiceAslPrecedente) {
		this.codiceAslPrecedente = codiceAslPrecedente;
	}

	public String getCognomeAssistito() {
		return this.cognomeAssistito;
	}

	public void setCognomeAssistito(String cognomeAssistito) {
		this.cognomeAssistito = cognomeAssistito;
	}

	public String getCognomeDelegato() {
		return this.cognomeDelegato;
	}

	public void setCognomeDelegato(String cognomeDelegato) {
		this.cognomeDelegato = cognomeDelegato;
	}

	public String getCognomeMedico() {
		return this.cognomeMedico;
	}

	public void setCognomeMedico(String cognomeMedico) {
		this.cognomeMedico = cognomeMedico;
	}

	public String getCognomeMedicoPrecedente() {
		return this.cognomeMedicoPrecedente;
	}

	public void setCognomeMedicoPrecedente(String cognomeMedicoPrecedente) {
		this.cognomeMedicoPrecedente = cognomeMedicoPrecedente;
	}

	public String getCognomeOperatore() {
		return this.cognomeOperatore;
	}

	public void setCognomeOperatore(String cognomeOperatore) {
		this.cognomeOperatore = cognomeOperatore;
	}

	public Timestamp getDataCancellazione() {
		return this.dataCancellazione;
	}

	public void setDataCancellazione(Timestamp dataCancellazione) {
		this.dataCancellazione = dataCancellazione;
	}

	public Timestamp getDataChiusura() {
		return this.dataChiusura;
	}

	public void setDataChiusura(Timestamp dataChiusura) {
		this.dataChiusura = dataChiusura;
	}

	public Timestamp getDataCreazione() {
		return this.dataCreazione;
	}

	public void setDataCreazione(Timestamp dataCreazione) {
		this.dataCreazione = dataCreazione;
	}

	public Timestamp getDataFineAssistenzaImposta() {
		return this.dataFineAssistenzaImposta;
	}

	public void setDataFineAssistenzaImposta(Timestamp dataFineAssistenzaImposta) {
		this.dataFineAssistenzaImposta = dataFineAssistenzaImposta;
	}

	public Timestamp getDataFineAssistenzaRichiesta() {
		return this.dataFineAssistenzaRichiesta;
	}

	public void setDataFineAssistenzaRichiesta(Timestamp dataFineAssistenzaRichiesta) {
		this.dataFineAssistenzaRichiesta = dataFineAssistenzaRichiesta;
	}

	public Timestamp getDataInizioAssistenzaImposta() {
		return this.dataInizioAssistenzaImposta;
	}

	public void setDataInizioAssistenzaImposta(Timestamp dataInizioAssistenzaImposta) {
		this.dataInizioAssistenzaImposta = dataInizioAssistenzaImposta;
	}

	public Timestamp getDataModifica() {
		return this.dataModifica;
	}

	public void setDataModifica(Timestamp dataModifica) {
		this.dataModifica = dataModifica;
	}

	public Timestamp getDataNascitaAssistito() {
		return this.dataNascitaAssistito;
	}

	public void setDataNascitaAssistito(Timestamp dataNascitaAssistito) {
		this.dataNascitaAssistito = dataNascitaAssistito;
	}

	public Timestamp getDataRevocaAssistenza() {
		return this.dataRevocaAssistenza;
	}

	public void setDataRevocaAssistenza(Timestamp dataRevocaAssistenza) {
		this.dataRevocaAssistenza = dataRevocaAssistenza;
	}

	public Timestamp getDataRevocaMedico() {
		return this.dataRevocaMedico;
	}

	public void setDataRevocaMedico(Timestamp dataRevocaMedico) {
		this.dataRevocaMedico = dataRevocaMedico;
	}

	public Boolean getDerogaAssociazione() {
		return this.derogaAssociazione;
	}

	public void setDerogaAssociazione(Boolean derogaAssociazione) {
		this.derogaAssociazione = derogaAssociazione;
	}

	public Boolean getDerogaMassimale() {
		return this.derogaMassimale;
	}

	public void setDerogaMassimale(Boolean derogaMassimale) {
		this.derogaMassimale = derogaMassimale;
	}

	public Boolean getDerogaMassimaleTemp() {
		return this.derogaMassimaleTemp;
	}

	public void setDerogaMassimaleTemp(Boolean derogaMassimaleTemp) {
		this.derogaMassimaleTemp = derogaMassimaleTemp;
	}

	public Boolean getDerogaPediatra() {
		return this.derogaPediatra;
	}

	public void setDerogaPediatra(Boolean derogaPediatra) {
		this.derogaPediatra = derogaPediatra;
	}

	public Boolean getDerogaTerritoriale() {
		return this.derogaTerritoriale;
	}

	public void setDerogaTerritoriale(Boolean derogaTerritoriale) {
		this.derogaTerritoriale = derogaTerritoriale;
	}

	public String getDistrettoDomicilioDichiarato() {
		return this.distrettoDomicilioDichiarato;
	}

	public void setDistrettoDomicilioDichiarato(String distrettoDomicilioDichiarato) {
		this.distrettoDomicilioDichiarato = distrettoDomicilioDichiarato;
	}

	public String getIdAuraMedico() {
		return this.idAuraMedico;
	}

	public void setIdAuraMedico(String idAuraMedico) {
		this.idAuraMedico = idAuraMedico;
	}

	public String getLuogoNascitaAssistito() {
		return this.luogoNascitaAssistito;
	}

	public void setLuogoNascitaAssistito(String luogoNascitaAssistito) {
		this.luogoNascitaAssistito = luogoNascitaAssistito;
	}

	public Boolean getMedicoGenerico() {
		return this.medicoGenerico;
	}

	public void setMedicoGenerico(Boolean medicoGenerico) {
		this.medicoGenerico = medicoGenerico;
	}

	public Boolean getMedicoRevocato() {
		return this.medicoRevocato;
	}

	public void setMedicoRevocato(Boolean medicoRevocato) {
		this.medicoRevocato = medicoRevocato;
	}

	public String getNomeAssistito() {
		return this.nomeAssistito;
	}

	public void setNomeAssistito(String nomeAssistito) {
		this.nomeAssistito = nomeAssistito;
	}

	public String getNomeDelegato() {
		return this.nomeDelegato;
	}

	public void setNomeDelegato(String nomeDelegato) {
		this.nomeDelegato = nomeDelegato;
	}

	public String getNomeMedico() {
		return this.nomeMedico;
	}

	public void setNomeMedico(String nomeMedico) {
		this.nomeMedico = nomeMedico;
	}

	public String getNomeMedicoPrecedente() {
		return this.nomeMedicoPrecedente;
	}

	public void setNomeMedicoPrecedente(String nomeMedicoPrecedente) {
		this.nomeMedicoPrecedente = nomeMedicoPrecedente;
	}

	public String getNomeOperatore() {
		return this.nomeOperatore;
	}

	public void setNomeOperatore(String nomeOperatore) {
		this.nomeOperatore = nomeOperatore;
	}

	public Integer getNumeroPratica() {
		return this.numeroPratica;
	}

	public void setNumeroPratica(Integer numeroPratica) {
		this.numeroPratica = numeroPratica;
	}

	public Boolean getRicongiungimentoFamiliare() {
		return this.ricongiungimentoFamiliare;
	}

	public void setRicongiungimentoFamiliare(Boolean ricongiungimentoFamiliare) {
		this.ricongiungimentoFamiliare = ricongiungimentoFamiliare;
	}

	public String getSessoAssistito() {
		return this.sessoAssistito;
	}

	public void setSessoAssistito(String sessoAssistito) {
		this.sessoAssistito = sessoAssistito;
	}

	public String getTipologiaGestionePratica() {
		return this.tipologiaGestionePratica;
	}

	public void setTipologiaGestionePratica(String tipologiaGestionePratica) {
		this.tipologiaGestionePratica = tipologiaGestionePratica;
	}

	public String getTipologiaMedico() {
		return this.tipologiaMedico;
	}

	public void setTipologiaMedico(String tipologiaMedico) {
		this.tipologiaMedico = tipologiaMedico;
	}

//	public List<ScerevRPraticaPraticaTipo> getScerevRPraticaPraticaTipos() {
//		return this.scerevRPraticaPraticaTipos;
//	}
//
//	public void setScerevRPraticaPraticaTipos(List<ScerevRPraticaPraticaTipo> scerevRPraticaPraticaTipos) {
//		this.scerevRPraticaPraticaTipos = scerevRPraticaPraticaTipos;
//	}
	
	public Set<ScerevRPraticaPraticaTipo> getScerevRPraticaPraticaTipos() {
	return this.scerevRPraticaPraticaTipos;
}

	public void setScerevRPraticaPraticaTipos(Set<ScerevRPraticaPraticaTipo> scerevRPraticaPraticaTipos) {
	this.scerevRPraticaPraticaTipos = scerevRPraticaPraticaTipos;
}
	

	public ScerevRPraticaPraticaTipo addScerevRPraticaPraticaTipo(ScerevRPraticaPraticaTipo scerevRPraticaPraticaTipo) {
		getScerevRPraticaPraticaTipos().add(scerevRPraticaPraticaTipo);
		scerevRPraticaPraticaTipo.setScerevTPratica(this);

		return scerevRPraticaPraticaTipo;
	}
	
	public Boolean getFlagAutolimitatoMedico() {
		return this.flagAutolimitatoMedico;
	}
	
	public void setFlagAutolimitatoMedico(Boolean flagAutolimitatoMedico) {
		this.flagAutolimitatoMedico = flagAutolimitatoMedico;
	}

	public ScerevRPraticaPraticaTipo removeScerevRPraticaPraticaTipo(ScerevRPraticaPraticaTipo scerevRPraticaPraticaTipo) {
		getScerevRPraticaPraticaTipos().remove(scerevRPraticaPraticaTipo);
		scerevRPraticaPraticaTipo.setScerevTPratica(null);

		return scerevRPraticaPraticaTipo;
	}

//	public List<ScerevTCronologia> getScerevTCronologias() {
//		return this.scerevTCronologias;
//	}
//
//	public void setScerevTCronologias(List<ScerevTCronologia> scerevTCronologias) {
//		this.scerevTCronologias = scerevTCronologias;
//	}
	
	public Set<ScerevTCronologia> getScerevTCronologias() {
		return this.scerevTCronologias;
	}

	public void setScerevTCronologias(Set<ScerevTCronologia> scerevTCronologias) {
		this.scerevTCronologias = scerevTCronologias;
	}
	
	public ScerevTCronologia addScerevTCronologia(ScerevTCronologia scerevTCronologia) {
		getScerevTCronologias().add(scerevTCronologia);
		scerevTCronologia.setScerevTPratica(this);

		return scerevTCronologia;
	}

	public ScerevTCronologia removeScerevTCronologia(ScerevTCronologia scerevTCronologia) {
		getScerevTCronologias().remove(scerevTCronologia);
		scerevTCronologia.setScerevTPratica(null);

		return scerevTCronologia;
	}

//	public List<ScerevTDocumento> getScerevTDocumentos() {
//		return this.scerevTDocumentos;
//	}
//
//	public void setScerevTDocumentos(List<ScerevTDocumento> scerevTDocumentos) {
//		this.scerevTDocumentos = scerevTDocumentos;
//	}
	
	public Set<ScerevTDocumento> getScerevTDocumentos() {
		return this.scerevTDocumentos;
	}

	public void setScerevTDocumentos(Set<ScerevTDocumento> scerevTDocumentos) {
		this.scerevTDocumentos = scerevTDocumentos;
	}

	public ScerevTDocumento addScerevTDocumento(ScerevTDocumento scerevTDocumento) {
		getScerevTDocumentos().add(scerevTDocumento);
		scerevTDocumento.setScerevTPratica(this);

		return scerevTDocumento;
	}

	public ScerevTDocumento removeScerevTDocumento(ScerevTDocumento scerevTDocumento) {
		getScerevTDocumentos().remove(scerevTDocumento);
		scerevTDocumento.setScerevTPratica(null);

		return scerevTDocumento;
	}

	public ScerevDAmbito getScerevDAmbito() {
		return this.scerevDAmbito;
	}

	public void setScerevDAmbito(ScerevDAmbito scerevDAmbito) {
		this.scerevDAmbito = scerevDAmbito;
	}

	public ScerevDAsl getScerevDAsl1() {
		return this.scerevDAsl1;
	}

	public void setScerevDAsl1(ScerevDAsl scerevDAsl1) {
		this.scerevDAsl1 = scerevDAsl1;
	}

	public ScerevDAsl getScerevDAsl2() {
		return this.scerevDAsl2;
	}

	public void setScerevDAsl2(ScerevDAsl scerevDAsl2) {
		this.scerevDAsl2 = scerevDAsl2;
	}

	public ScerevDAsl getScerevDAsl3() {
		return this.scerevDAsl3;
	}

	public void setScerevDAsl3(ScerevDAsl scerevDAsl3) {
		this.scerevDAsl3 = scerevDAsl3;
	}

	public ScerevDCittadinanza getScerevDCittadinanza() {
		return this.scerevDCittadinanza;
	}

	public void setScerevDCittadinanza(ScerevDCittadinanza scerevDCittadinanza) {
		this.scerevDCittadinanza = scerevDCittadinanza;
	}

	public ScerevDDistretto getScerevDDistretto() {
		return this.scerevDDistretto;
	}

	public void setScerevDDistretto(ScerevDDistretto scerevDDistretto) {
		this.scerevDDistretto = scerevDDistretto;
	}

	public ScerevDMotivazione getScerevDMotivazione() {
		return this.scerevDMotivazione;
	}

	public void setScerevDMotivazione(ScerevDMotivazione scerevDMotivazione) {
		this.scerevDMotivazione = scerevDMotivazione;
	}

	public ScerevDMotivoDomicilio getScerevDMotivoDomicilio() {
		return this.scerevDMotivoDomicilio;
	}

	public void setScerevDMotivoDomicilio(ScerevDMotivoDomicilio scerevDMotivoDomicilio) {
		this.scerevDMotivoDomicilio = scerevDMotivoDomicilio;
	}

	public ScerevDMotivoSoggiorno getScerevDMotivoSoggiorno() {
		return this.scerevDMotivoSoggiorno;
	}

	public void setScerevDMotivoSoggiorno(ScerevDMotivoSoggiorno scerevDMotivoSoggiorno) {
		this.scerevDMotivoSoggiorno = scerevDMotivoSoggiorno;
	}

	public ScerevDPraticaStato getScerevDPraticaStato() {
		return this.scerevDPraticaStato;
	}

	public void setScerevDPraticaStato(ScerevDPraticaStato scerevDPraticaStato) {
		this.scerevDPraticaStato = scerevDPraticaStato;
	}

	public ScerevDRegione getScerevDRegione() {
		return this.scerevDRegione;
	}

	public void setScerevDRegione(ScerevDRegione scerevDRegione) {
		this.scerevDRegione = scerevDRegione;
	}

	public ScerevDTipologiaProfilo getScerevDTipologiaProfilo() {
		return this.scerevDTipologiaProfilo;
	}

	public void setScerevDTipologiaProfilo(ScerevDTipologiaProfilo scerevDTipologiaProfilo) {
		this.scerevDTipologiaProfilo = scerevDTipologiaProfilo;
	}

	public ScerevTInfoSupplementari getScerevTInfoSupplementari() {
		return this.scerevTInfoSupplementari;
	}

	public void setScerevTInfoSupplementari(ScerevTInfoSupplementari scerevTInfoSupplementari) {
		this.scerevTInfoSupplementari = scerevTInfoSupplementari;
	}

	public ScerevTLocalita getScerevTLocalita1() {
		return this.scerevTLocalita1;
	}

	public void setScerevTLocalita1(ScerevTLocalita scerevTLocalita1) {
		this.scerevTLocalita1 = scerevTLocalita1;
	}

	public ScerevTLocalita getScerevTLocalita2() {
		return this.scerevTLocalita2;
	}

	public void setScerevTLocalita2(ScerevTLocalita scerevTLocalita2) {
		this.scerevTLocalita2 = scerevTLocalita2;
	}

	public String getCodiceFiscaleMedico() {
		return codiceFiscaleMedico;
	}

	public void setCodiceFiscaleMedico(String codiceFiscaleMedico) {
		this.codiceFiscaleMedico = codiceFiscaleMedico;
	}

	public String getRecapitoTelefonicoAssistito() {
		return recapitoTelefonicoAssistito;
	}

	public void setRecapitoTelefonicoAssistito(String recapitoTelefonicoAssistito) {
		this.recapitoTelefonicoAssistito = recapitoTelefonicoAssistito;
	}

	public String getMotivoRevocaMedico() {
		return motivoRevocaMedico;
	}

	public void setMotivoRevocaMedico(String motivoRevocaMedico) {
		this.motivoRevocaMedico = motivoRevocaMedico;
	}
	
}
