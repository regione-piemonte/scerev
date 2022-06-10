/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevlibbatch.dao;


public class SQLStatements {
	
	
	public static final String GETPARAMETRO = "select valore_msg from scerev_d_msg where codice_msg = ?";
	public static final String GETCFNOMECOGNOME = "select cf_medico,nome_medico,cognome_medico from scerev_t_monitoraggio where id_aura_medico = ? and data_cancellazione is null";
	public static final String GETSCEREVINFO = "select cf_assistito,cf_medico,selezionabile,selezionabile_ricongiungimento_familiare,selezionabile_assistenza_temporanea,selezionabile_infanzia from scerev_t_monitoraggio where id_aura_medico = ? and data_cancellazione is null";
	public static final String UPDATEMONITORAGGIO = "update scerev_t_monitoraggio set data_modifica = current_timestamp, selezionabile = ?,selezionabile_ricongiungimento_familiare = ?, selezionabile_assistenza_temporanea = ?, selezionabile_infanzia = ? where id_aura_medico = ? and cf_assistito = ? and data_cancellazione is null";
	public static final String INSERTAUDIT = "insert into scerev_l_audit (data_ora,id_app,ip_address,utente,utente_del,utente_ben,operazione,ogg_oper,idrequest,xcod_servizio,key_oper) "
				+" values (current_timestamp,?,?,?,?,?,?,?,?,?,?)";
	public static final String GETIDAURAS = "select distinct id_aura_medico from scerev_t_monitoraggio where data_cancellazione is null";
/*
//	seleziona i file on stato caricato che sono da elaborare
	public static final String GETFILEDAELABORARE = "select a.pk_id_file_csv_upload, a.nome_file_csv, a.data_upload_file, a.file_csv, a.audit_cf_autore_operazione,d.cod_regionale_asr,a.fk_id_asr_competente "
			+ "from cpd_t_file_csv_upload a,cpd_r_stato_assunto_file_csv_upload b,cpd_d_stato_file_csv_upload c,cpd_c_asr d "
			+ "where a.pk_id_file_csv_upload= b.fk_id_file_csv_upload "
			+ "and b.fk_id_stato_file_csv_upload=c.pk_id_stato_file_csv_upload "
			+ "and d.pk_id_asr = a.fk_id_asr_competente "
			+ "and c.cod_stato_file_csv_upload = 'CAR' "
			+ "and b.data_uscita is null "
		    + "and a.pk_id_file_csv_upload not in ( "
		    + "select b.fk_id_file_csv_upload "
		    + "from cpd_t_file_csv_upload a,cpd_r_stato_assunto_file_csv_upload b,cpd_d_stato_file_csv_upload c,cpd_c_asr d " 
		    + "where a.pk_id_file_csv_upload= b.fk_id_file_csv_upload "
		    + "and b.fk_id_stato_file_csv_upload=c.pk_id_stato_file_csv_upload " 
		    + "and d.pk_id_asr = a.fk_id_asr_competente "
		    + "and c.cod_stato_file_csv_upload <> 'CAR')";

	public static final String GETNOMEFILEARRICCHITO = "select a.nome_file_csv_arricchito from "
			+ "cpd_r_stato_assunto_file_csv_upload a,cpd_d_stato_file_csv_upload b "
			+ "where a.fk_id_file_csv_upload =15 and "
			+ "b.pk_id_stato_file_csv_upload=a.fk_id_stato_file_csv_upload and b.cod_stato_file_csv_upload='ELA' "
			+ "and position(TO_CHAR(current_date, 'yyyyMMdd') in a.nome_file_csv_arricchito)>0 ";
	

	public static final String GETDATIAGGIUNTIVI = "select b.cod_regionale_asr, "
			+ "b.cbill_asr, "
			+ "c.numero_cc_postale, "
			+ "c.desc_intestatario_cc_postale, "
			+ "f.desc_template_lettera_accompagn, "
			+ "b.nome_comune_ubicazione_sede, "
			+ "b.desc_ente_creditore_cedola, "
			+ "b.desc_settore_ente_creditore_cedola, "
			+ "b.desc_info_ente_creditore_cedola, "
			+ "b.desc_sito_web_ente_creditore_cedola, "
			+ "b.desc_dove_pagare_su_territorio, "
			+ "b.desc_sito_web_poste_ita_pagamento_cedola, "
			+ "c.autorizz_poste_stampa_cedola, "
			+ "e.cod_motivo_pagamento, "
			+ "c.data_autorizz_poste_stampa_cedola, "
			+ "b.cod_fiscale_ente_creditore_cedola, "
			+ "g.denominazione_tipol_entrata "
			+ "from cpd_r_tipologia_entrata_asr a,cpd_c_asr b, cpd_c_conto_corrente_postale_asr c,cpd_d_codice_versamento d, "
			+ "cpd_d_motivo_pagamento e,cpd_d_template_lettera_accompagnamento f,cpd_d_tipologia_entrata g "
			+ "where a.fk_id_asr = b.pk_id_asr and b.pk_id_asr= ? and "
			+ "a.fk_id_codice_versamento = d.pk_id_codice_versamento and "
			+ "a.fk_id_conto_corrente_postale_asr = c.pk_id_cc_postale_asr and c.fk_id_asr=b.pk_id_asr and "
			+ "a.fk_id_motivo_pagamento = e.pk_id_motivo_pagamento and a.fk_id_template_lettera_accompagn = f.pk_id_template_lettera_accompagn and "
			+ "a.fk_id_tipologia_entrata = g.pk_id_tipol_entrata and a.fk_id_tipologia_entrata = ?";
//			+ "((a.data_Fine_Validita is null and  a.data_Inizio_Validita <= '?') or ('?' between a.data_Inizio_Validita and a.data_Fine_Validita))";

	public static final String GETPOSIZIONEDEBITORIA = "select LPAD(cast(nextval('cpd_t_posizione_debitoria_numero_posizione_debitoria_seq') as text),'16','0') as posizione";
	
	public static final String GETIUVPK = "SELECT pk_id_iuv, iuv, fk_id_codice_versamento, application_code, aux_digit  FROM cpd_r_iuv_mod3_asr_cod_versamento "
											+ "where pk_id_iuv = ?";
											
	public static final String GETPARAMETRO = "select valore_ammesso from cpd_c_parametro_configurazione where cod_parametro_configurazione=?";
	
	public static final String GETIUV = "SELECT a.pk_id_iuv, iuv, a.fk_id_codice_versamento, a.application_code, a.aux_digit  FROM cpd_r_iuv_mod3_asr_cod_versamento a,cpd_c_asr b,"
										+ "cpd_r_tipologia_entrata_asr c where "
										+ "a.fk_id_asr = b.pk_id_asr and b.pk_id_asr= ? and "
										+ "a.fk_id_codice_versamento = c.fk_id_codice_versamento and "
										+ "c.fk_id_tipologia_entrata = ? and "
										+ "a.data_consumo is null order by a.pk_id_iuv asc";
	
	public static final String SETIUVCONSUMATO ="update cpd_r_iuv_mod3_asr_cod_versamento set data_consumo = ? where pk_id_iuv = ?";
	
	public static final String CONTAIUVDISPONIBILI = "SELECT count(pk_id_iuv) FROM cpd_r_iuv_mod3_asr_cod_versamento a, cpd_c_asr b, "
													+ "cpd_r_tipologia_entrata_asr c where "
													+ "a.fk_id_asr = b.pk_id_asr and b.pk_id_asr= ? and "
													+ "a.fk_id_codice_versamento = c.fk_id_codice_versamento and "
													+ "c.fk_id_tipologia_entrata = ? and "
													+ "a.data_consumo is null";
	public static final String COUNTDEBITORE ="select count(pk_id_debitore) from cpd_t_debitore "
												+ "where codice_identificativo_debitore =?";
	
	public static final String GETDEBITORE ="select pk_id_debitore from cpd_t_debitore "
			+ "where codice_identificativo_debitore =?";
	
	public static final String INSERTDEBITORE ="INSERT INTO cpdasr.cpd_t_debitore( "
			+ "codice_identificativo_debitore, "
			+ "fk_id_asr_competente_anagrafica, "
			+ "id_debitore_ambito_asr, "
			+ "tipo_debitore, "
			+ "cognome_pf, "
			+ "nome_pf, "
			+ "sesso_pf, "
			+ "data_nascita_pf, "
			+ "id_aura_pf, "
			+ "codice_fiscale_pf, "
			+ "codice_eni_pf, "
			+ "codice_stp_pf, "
			+ "email_pf, "
			+ "nome_stato_nascita_pf, "
			+ "nome_comune_nascita_pf, "
			+ "denominazione_cittadinanza_pf, "
			+ "nome_stato_domicilio_pf, "
			+ "sigla_provincia_domicilio_pf, "
			+ "nome_comune_domicilio_pf, "
			+ "cap_domicilio_pf, "
			+ "indirizzo_domicilio_pf, "
			+ "numero_civico_domicilio_pf, "
			+ "ragione_sociale_pg, "
			+ "partita_iva_pg, "
			+ "email_sede_legale_pg, "
			+ "sigla_provincia_sede_legale_pg, "
			+ "nome_comune_sede_legale_pg, "
			+ "cap_sede_legale_pg, "
			+ "indirizzo_sede_legale, "
			+ "num_civico_sede_legale_pg, "
			+ "audit_data_inserimento, "
			+ "audit_cf_autore_operazione) "
			+ "	VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, current_timestamp, ?)";
	
	public static final String UPDATEDEBITORE = "UPDATE cpdasr.cpd_t_debitore "
			+ "SET id_debitore_ambito_asr=?, "
			+ "cognome_pf=?, "
			+ "nome_pf=?, "
			+ "sesso_pf=?, "
			+ "data_nascita_pf=?, " 
			+ "id_aura_pf=?, "
			+ "email_pf=?, "
			+ "nome_stato_nascita_pf=?, " 
			+ "nome_comune_nascita_pf=?,  "
			+ "denominazione_cittadinanza_pf=?, " 
			+ "nome_stato_domicilio_pf=?, "
			+ "sigla_provincia_domicilio_pf=?, " 
			+ "nome_comune_domicilio_pf=?, "
			+ "cap_domicilio_pf=?, "
			+ "indirizzo_domicilio_pf=?, " 
			+ "numero_civico_domicilio_pf=?, " 
			+ "ragione_sociale_pg=?, "
			+ "email_sede_legale_pg=?, "
			+ "sigla_provincia_sede_legale_pg=?, "
			+ "nome_comune_sede_legale_pg=?, "
			+ "cap_sede_legale_pg=?, "
			+ "indirizzo_sede_legale=?, "
			+ "num_civico_sede_legale_pg=?, "
			+ "audit_data_modifica=current_timestamp, "
			+ "audit_cf_autore_operazione=? "
			+ "WHERE codice_identificativo_debitore=?";
	
	public static final String INSERTPOSIZIONEDEBITORIA ="INSERT INTO cpdasr.cpd_t_posizione_debitoria( "
			+"numero_posizione_debitoria, "
			+ "fk_id_tipologia_entrata_asr, "
			+ "fk_id_debitore, "
			+ "fk_id_iuv_mod3, "
			+ "fk_id_algoritmo_calcolo_interessi, "
			+ "fk_id_file_csv_upload, "
			+ "data_apertura_posiz_debitoria, "
			+ "numero_pratica_pagamento, "
			+ "importo_netto, "
			+ "percentuale_iva, "
			+ "importo_solo_iva, "
			+ "importo_oneri_gestione, "
			+ "importo_marca_da_bollo, "
			+ "data_scadenza_pagamento, "
			+ "id_pd_ambito_asr, "
			+ "info_prestazione_erogata, "
			+ "cod_prestazione_erogata, "
			+ "cod_servizio_cpd, "
			+ "testo_oggetto_pagamento_lettera, "
			+ "tipo_bollettino_postale_cedola, "
			+ "stringa_barcode, "
			+ "stringa_qr_code, "
			+ "stringa_data_matrix, "
			+ "cod_avviso_pagamenti_psp_cedola, "
			+ "testo_numero_protocollo_lettera, "
			+ "testo_data_protocollo_lettera, "
			+ "testo_denominazione_distretto_asr_lettera, "
			+ "testo_direttore_distretto_asr_lettera, "
			+ "testo_info_apertura_distretto_lettera, "
			+ "audit_data_inserimento, "
			+ "audit_cf_autore_operazione,"
			+ "data_creazione_prenotazione) "
			+ "VALUES (?, ?, ?, ?, ?, ?, current_date, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, current_timestamp, ?,current_date)";
			
	public static final String INSPESA ="INSERT INTO cpdasr.cpd_d_tipo_spesa( "
			+ "cod_tipo_spesa, denominazione_tipo_spesa, flag_tipo_spesa) "
			+ "	VALUES (?, ?, ?)";
	
	public static final String INSSTATOPD ="INSERT INTO cpdasr.cpd_r_stato_assunto_posizione_debitoria ("
			+ "fk_id_posizione_debitoria, "
			+ "fk_id_stato_posiz_debitoria, "
			+ "data_ingresso, "
			+ "importo_non_corrisposto, "
			+ "importo_da_pagare_esclusi_interessi, "
			+ "audit_data_inserimento, "
			+ "audit_cf_autore_operazione) "
			+ "VALUES (?, ?, current_timestamp,?, ?, current_timestamp, ?)";
	
	public static final String GETSTATOPD ="select pk_id_stato_posiz_debitoria from cpd_d_stato_posizione_debitoria "
			+ "where cod_stato_posiz_debitoria =?";
	
	public static final String UPDATESTATOFILE ="update cpd_r_stato_assunto_file_csv_upload set data_uscita = current_timestamp, "
			+ "audit_data_modifica = current_timestamp, audit_cf_autore_operazione = ? "
			+ "			where pk_id_stato_assunto_file_csv_upload in( "
			+ "			select a.pk_id_stato_assunto_file_csv_upload from cpd_r_stato_assunto_file_csv_upload a,cpd_t_file_csv_upload b, "
			+ "			cpd_d_stato_file_csv_upload c "
			+ "			where a.fk_id_file_csv_upload = b.pk_id_file_csv_upload "
			+ "			and b.pk_id_file_csv_upload = ? and a.fk_id_stato_file_csv_upload = c.pk_id_stato_file_csv_upload "
			+ "			and c.cod_stato_file_csv_upload = ?)";

	public static final String INSERTSTATOFILEELA ="INSERT INTO cpdasr.cpd_r_stato_assunto_file_csv_upload( "
			+ "fk_id_file_csv_upload, "
			+ "fk_id_stato_file_csv_upload, "
			+ "data_ingresso, "
			+ "file_csv_arricchito, "
			+ "nome_file_csv_arricchito, "
			+ "audit_data_inserimento, "
			+ "audit_cf_autore_operazione) "
			+ "VALUES (?, ?, current_timestamp, ?, ?, current_timestamp, ?)";
	
	public static final String GETSTATOFILECSV ="select pk_id_stato_file_csv_upload from cpd_d_stato_file_csv_upload "
			+ "where cod_stato_file_csv_upload =?";
*/
}
