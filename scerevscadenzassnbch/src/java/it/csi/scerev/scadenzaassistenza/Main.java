/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scadenzaassistenza;

import java.io.FileNotFoundException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import it.csi.scerev.scadenzaassistenza.dao.DBConnectionManager;
import it.csi.scerev.scadenzaassistenza.dao.DataDAO;
import it.csi.scerev.scadenzaassistenza.dao.FileManager;
import it.csi.scerev.scadenzaassistenza.deleghebe.DelegaCittadino;
import it.csi.scerev.scadenzaassistenza.dto.ScadenzaAssistenza;
import it.csi.scerev.scadenzaassistenza.exception.BatchException;
import it.csi.scerev.scadenzaassistenza.exception.ConnectionSoapException;
import it.csi.scerev.scadenzaassistenza.logger.BatchLoggerFactory;
import it.csi.scerev.scadenzaassistenza.serviziesterni.ServiziEsterniClient;
import it.csi.scerev.scadenzaassistenza.util.SharedConstants;


public class Main {
	private Connection conn;
	private DataDAO dao = null;
	private ServiziEsterniClient serviziEsterni = null;

	public static void main(String[] args) {
		new Main().run();

	}

	/**
	 * Crea la connessione a db
	 * 
	 * @throws ConnectionException
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	private void init() throws ConnectionSoapException {
		try {
			BatchLoggerFactory.getLogger(this).info("--- CONNESSIONE AL DATABASE IN CORSO ---");
			this.conn = DBConnectionManager.getConnection();
			this.conn.setAutoCommit(false);
			dao = new DataDAO(this.conn);
			serviziEsterni = ServiziEsterniClient.getInstance(dao);
			BatchLoggerFactory.getLogger(this).info("--- CONNESSIONE EFFETTUATA ---");
					
		} catch (SQLException e) {
			throw new ConnectionSoapException("Database connection fail: " + e.getMessage());
		} catch (ClassNotFoundException e) {
			throw new ConnectionSoapException("Database connection fail: " + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void run() {
		String ipAddress;
		try {
			ipAddress = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e1) {
			ipAddress=null;
			e1.printStackTrace();
		}
		
		// audit params
		final String idApp = "SCEREVBE";
		final String utente = "SCEREVBE";
		final String utenteDel = null;
		final String utenteBen = "SCEREVBE";
		final String operazione = "read";
		final String nomeServizio = "scadenzaAssistenza"; // OGG_OPER
		final String keyOper = null;
		final String idRequest = null;
		final String xCodServizio = null;
		
		try {
			
			BatchLoggerFactory.getLogger(this).info("--- AVVIO BATCH ---");
			// Stabilisco la connessione con il Database
			init();
			
			// Traccio la chiamata al batch
			dao.insertAudit(idApp, ipAddress, utente, utenteDel, utenteBen, operazione, nomeServizio + " - request",
						idRequest, xCodServizio, keyOper);
			
			// Recupero il file csv dal file system e lo converto
			List<ScadenzaAssistenza> csv = FileManager.getCSVFromClass(ScadenzaAssistenza.class);
			
			for(ScadenzaAssistenza s : csv) {
				String beneficiario = s.getNome() + " " + s.getCognome();
				String fineAsl = s.getDataFineAsl();

				// invio notifica al beneficiario
				if(!serviziEsterni.sendMessage(nomeServizio, beneficiario, s.getCodiceFiscale(), null, null, fineAsl))
					throw new BatchException("Errore invio notifiche al Beneficiario");

				// Per ogni record presente chiamo il servizio getDelegati di DELEGHE
				List<DelegaCittadino> delegati = serviziEsterni.chiamaGetDelegati(s.getCodiceFiscale());
				
				if(delegati != null && !delegati.isEmpty()) {
					for(DelegaCittadino del : delegati) {
						String delegato = del.getNome() + " " + del.getCognome();
						
						// invio notifica al delegato
						if(!serviziEsterni.sendMessage(nomeServizio, beneficiario, s.getCodiceFiscale(), del.getCodiceFiscale(), delegato, fineAsl))
							throw new BatchException("Errore invio notifiche al Delegato");
					}
				}
				
			}
	
//			// Traccio esito batch
			dao.insertAudit(idApp, ipAddress, utente, utenteDel, utenteBen, operazione, nomeServizio + " - response",
					idRequest, xCodServizio, "OK");
			
		} catch (Exception e) {
			e.printStackTrace();
			String msg = null;
			try {
				if(e instanceof BatchException)
					msg = ((BatchException) e).getMsg();
				else if(e instanceof FileNotFoundException)
					msg = ((FileNotFoundException) e).getMessage();
				else msg = dao.getParametro(SharedConstants.ERROR_CODE_INTERNO_SERVER);
				
				BatchLoggerFactory.getLogger(this).error("--- ERRORE GENERICO: " + msg);
				// log error
				dao.insertAudit(idApp, ipAddress, utente, utenteDel, utenteBen, operazione,
						nomeServizio + " - response", idRequest, xCodServizio, "KO - " + msg);

			} catch (SQLException e2) {
				BatchLoggerFactory.getLogger(this).error("--- ERRORE GENERICO: " + e2.getMessage());
				e2.printStackTrace();
			}

			System.exit(100);
		} finally {
			BatchLoggerFactory.getLogger(this).info("--- END BATCH ---");
			dao.closeAll();
		}
	}

}

