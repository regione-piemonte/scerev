/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scadenzaassistenza.dao;

public class SQLStatements {
	
	public static final String GETPARAMETRO 	= "select valore_msg from scerev_d_msg where codice_msg = ?";
	
	public static final String INSERTAUDIT 		= "insert into scerev_l_audit (data_ora, id_app, ip_address, utente, utente_del, "
												+ "utente_ben, operazione,ogg_oper,idrequest,xcod_servizio,key_oper) "
												+" values (current_timestamp,?,?,?,?,?,?,?,?,?,?)";
}
