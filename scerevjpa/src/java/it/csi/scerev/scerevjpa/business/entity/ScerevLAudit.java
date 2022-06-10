/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevjpa.business.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the scerev_l_audit database table.
 * 
 */
@Entity
@Table(name="scerev_l_audit")
@SequenceGenerator(name="\"scerev_l_audit_generator\"",  sequenceName="\"scerev_l_audit_pk_audit_seq\"", initialValue=1, allocationSize=1)
@NamedQuery(name="ScerevLAudit.findAll", query="SELECT s FROM ScerevLAudit s")
public class ScerevLAudit implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="pk_audit")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="\"scerev_l_audit_generator\"")
	private Integer pkAudit;

	@Column(name="data_ora")
	private Timestamp dataOra;

	@Column(name="id_app")
	private String idApp;

	private String idrequest;

	@Column(name="ip_address")
	private String ipAddress;

	@Column(name="ogg_oper")
	private String oggOper;

	private String operazione;

	private String utente;

	@Column(name="utente_ben")
	private String utenteBen;

	@Column(name="utente_del")
	private String utenteDel;

	@Column(name="key_oper")
	private String keyOper;

	@Column(name="xcod_servizio")
	private String xcodServizio;

	public ScerevLAudit() {
	}

	public Integer getPkAudit() {
		return this.pkAudit;
	}

	public void setPkAudit(Integer pkAudit) {
		this.pkAudit = pkAudit;
	}

	public Timestamp getDataOra() {
		return this.dataOra;
	}

	public void setDataOra(Timestamp dataOra) {
		this.dataOra = dataOra;
	}

	public String getIdApp() {
		return this.idApp;
	}

	public void setIdApp(String idApp) {
		this.idApp = idApp;
	}

	public String getIdrequest() {
		return this.idrequest;
	}

	public void setIdrequest(String idrequest) {
		this.idrequest = idrequest;
	}

	public String getIpAddress() {
		return this.ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getOggOper() {
		return this.oggOper;
	}

	public void setOggOper(String oggOper) {
		this.oggOper = oggOper;
	}

	public String getOperazione() {
		return this.operazione;
	}

	public void setOperazione(String operazione) {
		this.operazione = operazione;
	}

	public String getUtente() {
		return this.utente;
	}

	public void setUtente(String utente) {
		this.utente = utente;
	}

	public String getUtenteBen() {
		return this.utenteBen;
	}

	public void setUtenteBen(String utenteBen) {
		this.utenteBen = utenteBen;
	}

	public String getUtenteDel() {
		return this.utenteDel;
	}

	public void setUtenteDel(String utenteDel) {
		this.utenteDel = utenteDel;
	}

	public String getKeyOper() {
		return keyOper;
	}

	public void setKeyOper(String keyOper) {
		this.keyOper = keyOper;
	}

	public String getXcodServizio() {
		return this.xcodServizio;
	}

	public void setXcodServizio(String xcodServizio) {
		this.xcodServizio = xcodServizio;
	}
	
	public ScerevLAudit (
			String idApp,
			String ipAddress, 
			String user, 
			String userDelegato, 
			String userBeneficiario, 
			String operation, 
			String operationObject, 
			String keyOper, 
			String idRequest, 
			String codServizio) {
		this.idApp = idApp;
		this.ipAddress = ipAddress;
		this.utente = user;
		this.utenteDel = userDelegato;
		this.utenteBen = userBeneficiario;
		this.operazione = operation;
		this.oggOper = operationObject;
		this.keyOper = keyOper;
		this.idrequest = idRequest;
		this.xcodServizio = codServizio;
	}

	public ScerevLAudit(ScerevLAudit audit) {
		this.dataOra = audit.getDataOra();
		this.idApp = audit.getIdApp();
		this.idrequest = audit.getIdrequest();
		this.ipAddress = audit.getIpAddress();
		this.keyOper = audit.getKeyOper();
		this.oggOper = audit.getOggOper();
		this.operazione = audit.getOperazione();
		this.utente = audit.getUtente();
		this.utenteBen = audit.getUtenteBen();
		this.utenteDel = audit.getUtenteDel();
		this.xcodServizio = audit.getXcodServizio();
	}

}
