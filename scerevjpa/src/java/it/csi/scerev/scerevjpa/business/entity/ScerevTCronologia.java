/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevjpa.business.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;


/**
 * The persistent class for the scerev_t_cronologia database table.
 * 
 */
@Entity
@Table(name="scerev_t_cronologia")
@SequenceGenerator(name="\"scerev_t_cronologia_pk_cronologia_seq_generator\"",  sequenceName="\"scerev_t_cronologia_pk_cronologia_seq\"", initialValue=1, allocationSize=1)
@NamedQuery(name="ScerevTCronologia.findAll_di_una_pratica", 
            query="SELECT s FROM ScerevTCronologia"
	    	        + " s WHERE s.scerevTPratica.pkPratica = :idPraticaScelta "
	    	        + " ORDER BY s.dataOraInserimento ASC")

public class ScerevTCronologia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="pk_cronologia")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="\"scerev_t_cronologia_pk_cronologia_seq_generator\"")
	private Integer pkCronologia;

	@Temporal(TemporalType.DATE)
	@Column(name="data_cancellazione")
	private Date dataCancellazione;

	@Temporal(TemporalType.DATE)
	@Column(name="data_creazione")
	private Date dataCreazione;

	@Temporal(TemporalType.DATE)
	@Column(name="data_modifica")
	private Date dataModifica;

	@Column(name="data_ora_inserimento")
	private Timestamp dataOraInserimento;

	@Column(name="nota_interna")
	private String notaInterna;

	@Column(name="nota_richiedente")
	private String notaRichiedente;

	private String utente;

	//bi-directional many-to-one association to ScerevDPraticaStato
	@ManyToOne
	@JoinColumn(name="fk_pratica_stato")
	private ScerevDPraticaStato scerevDPraticaStato;

	//bi-directional many-to-one association to ScerevTPratica
	@ManyToOne
	@JoinColumn(name="fk_pratica")
	private ScerevTPratica scerevTPratica;

	public ScerevTCronologia() {
	}

	public Integer getPkCronologia() {
		return this.pkCronologia;
	}

	public void setPkCronologia(Integer pkCronologia) {
		this.pkCronologia = pkCronologia;
	}

	public Date getDataCancellazione() {
		return this.dataCancellazione;
	}

	public void setDataCancellazione(Date dataCancellazione) {
		this.dataCancellazione = dataCancellazione;
	}

	public Date getDataCreazione() {
		return this.dataCreazione;
	}

	public void setDataCreazione(Date dataCreazione) {
		this.dataCreazione = dataCreazione;
	}

	public Date getDataModifica() {
		return this.dataModifica;
	}

	public void setDataModifica(Date dataModifica) {
		this.dataModifica = dataModifica;
	}

	public Timestamp getDataOraInserimento() {
		return this.dataOraInserimento;
	}

	public void setDataOraInserimento(Timestamp dataOraInserimento) {
		this.dataOraInserimento = dataOraInserimento;
	}

	public String getNotaInterna() {
		return this.notaInterna;
	}

	public void setNotaInterna(String notaInterna) {
		this.notaInterna = notaInterna;
	}

	public String getNotaRichiedente() {
		return this.notaRichiedente;
	}

	public void setNotaRichiedente(String notaRichiedente) {
		this.notaRichiedente = notaRichiedente;
	}

	public String getUtente() {
		return this.utente;
	}

	public void setUtente(String utente) {
		this.utente = utente;
	}

	public ScerevDPraticaStato getScerevDPraticaStato() {
		return this.scerevDPraticaStato;
	}

	public void setScerevDPraticaStato(ScerevDPraticaStato scerevDPraticaStato) {
		this.scerevDPraticaStato = scerevDPraticaStato;
	}

	public ScerevTPratica getScerevTPratica() {
		return this.scerevTPratica;
	}

	public void setScerevTPratica(ScerevTPratica scerevTPratica) {
		this.scerevTPratica = scerevTPratica;
	}

}
