/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevaurabatch.dao;

public class SQLStatements {
	
	public static final String GETPARAMETRO 	= "select valore_msg from scerev_d_msg where codice_msg = ?";
	
	public static final String GETPKASL 		= "select pk_azienda from scerev_d_asl where codice_azienda = ?";
	
	public static final String GETDISTRETTO 	= "select pk_distretto, codice_distretto, denominazione_distretto, "
												+ "fk_azienda, data_inizio_validita, data_fine_validita "
												+ "from scerev_d_distretto where pk_distretto = ?";
	
	public static final String GETAMBITO 		= "select pk_ambito, codice_ambito, denominazione_ambito, "
										 		+ "fk_distretto, data_inizio_validita, data_fine_validita "
										 		+ "from scerev_d_ambito where pk_ambito = ?";
	
	public static final String GETALLDISTRETTI 	= "select pk_distretto, codice_distretto, denominazione_distretto, "
												+ "fk_azienda, data_inizio_validita, data_fine_validita "
												+ "from scerev_d_distretto";
	
	public static final String GETALLAMBITI		= "select pk_ambito, codice_ambito, denominazione_ambito, "
												+ "fk_distretto, data_inizio_validita, data_fine_validita "
												+ "from scerev_d_ambito";
	
	public static final String GETALLPKDISTRETTI= "select pk_distretto from scerev_d_distretto "
												+ "where data_fine_validita is null";
	
	public static final String GETALLPKAMBITI	= "select pk_ambito from scerev_d_ambito "
												+ "where data_fine_validita is null";
	
	public static final String INVALIDADISTRETTO= "update scerev_d_distretto set data_fine_validita = current_date, data_modifica = current_date "
												+ "where pk_distretto = ?";
	
	public static final String INVALIDAAMBITO 	= "update scerev_d_ambito set data_fine_validita = current_date, data_modifica = current_date "
												+ "where pk_ambito = ?";
	
	public static final String UPDATEDISTRETTO 	= "update scerev_d_distretto set codice_distretto = ?, denominazione_distretto = ?, "
												+ "fk_azienda = ?, data_inizio_validita = ?, data_modifica = current_date where pk_distretto = ?";
	
	public static final String UPDATEAMBITO 	= "update scerev_d_ambito set codice_ambito = ?, denominazione_ambito = ?, "
												+ "fk_distretto = ?, data_inizio_validita = ?, data_modifica = current_date where pk_ambito = ?";
	
	public static final String INSERTDISTRETTO	= "insert into scerev_d_distretto (pk_distretto, codice_distretto, denominazione_distretto, "
												+ "fk_azienda, data_inizio_validita, data_creazione, data_modifica) values "
												+ "(?, ?, ?, ?, ?, current_date, current_date)";
	
	public static final String INSERTAMBITO		= "insert into scerev_d_ambito (pk_ambito, codice_ambito, denominazione_ambito, "
												+ "fk_distretto, data_inizio_validita, data_creazione, data_modifica) values "
												+ "(?, ?, ?, ?, ?, current_date, current_date)";
	
	public static final String INSERTLOGAURA	= "insert into scerev_log_aura (id_user_chiamante, azione, xml_input, xml_output, data_ins, data_upd, codice_esito) "
												+ "values ('BATCHAMBITIEDISTRETTI', ?, ?, ?, ?, ?, ?)";
	
	public static final String INSERTAUDIT 		= "insert into scerev_l_audit (data_ora, id_app, ip_address, utente, utente_del, "
												+ "utente_ben, operazione,ogg_oper,idrequest,xcod_servizio,key_oper) "
												+" values (current_timestamp,?,?,?,?,?,?,?,?,?,?)";
}
