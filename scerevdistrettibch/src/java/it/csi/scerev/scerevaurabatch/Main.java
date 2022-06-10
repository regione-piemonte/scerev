/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevaurabatch;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.csi.scerev.scerevaurabatch.dao.DBConnectionManager;
import it.csi.scerev.scerevaurabatch.dao.DataDAO;
import it.csi.scerev.scerevaurabatch.dto.ScerevDAmbitoDTO;
import it.csi.scerev.scerevaurabatch.dto.ScerevDDistrettoDTO;
import it.csi.scerev.scerevaurabatch.exception.AuraNotFoundException;
import it.csi.scerev.scerevaurabatch.exception.BatchException;
import it.csi.scerev.scerevaurabatch.exception.ConnectionSoapException;
import it.csi.scerev.scerevaurabatch.logger.BatchLoggerFactory;
import it.csi.scerev.scerevaurabatch.serviziesterni.ServiziEsterniClient;
import it.csi.scerev.scerevaurabatch.util.SharedConstants;


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
		final String operazione = "insert/update";
		final String nomeServizio = "batchAuraDistrettiAmbiti"; // OGG_OPER
		final String keyOper = null;
		final String idRequest = null;
		final String xCodServizio = null;
		try {
			
			BatchLoggerFactory.getLogger(this).info("--- AVVIO BATCH ---");
			// Stabilisco la connessione con il Database
			init();
			
			// 1 Traccio la chiamata al batch
			dao.insertAudit(idApp, ipAddress, utente, utenteDel, utenteBen, operazione, nomeServizio + " - request",
						idRequest, xCodServizio, keyOper);
			
			// Recupero ambiti AURA
			List<ScerevDAmbitoDTO> ambiti = serviziEsterni.getAmbito(dao);

			// Recupero distretti da AURA
			List<ScerevDDistrettoDTO> distretti = serviziEsterni.getDistretto(dao);

			// Elaboro i distretti recuperati
			elaboraDistretti(distretti);
			
			// Elaboro gli ambiti recuperati
			elebotaAmbiti(ambiti);
			
			// Traccio esito batch
			dao.insertAudit(idApp, ipAddress, utente, utenteDel, utenteBen, operazione, nomeServizio + " - response",
					idRequest, xCodServizio, "OK");
			
		} catch (Exception e) {
			e.printStackTrace();
			String msg = null;
			try {
				if(e instanceof BatchException)
					msg = ((BatchException) e).getMsg();
				else if(e instanceof AuraNotFoundException)
					msg = ((AuraNotFoundException) e).getCodMsg();
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

	private void elebotaAmbiti(List<ScerevDAmbitoDTO> ambitiAura) throws SQLException {
		List<Integer> pkAura = listPkAmbiti(ambitiAura);
		List<Integer> pkScerev = dao.getAllPKAmbiti();
		if(pkAura.size() < pkScerev.size() ) {
		// Aura ha restituito meno ambiti di quelli presenti in scerev
			for(Integer pkS : pkScerev) {
			// Imposto la data fine validita' degli ambiti non restituiti da AURA
				if(!pkAura.contains(pkS)) {
					BatchLoggerFactory.getLogger(this).info("- AMBITO DA INVALIDARE: " + pkS);
					dao.invalidaAmbito(pkS);
				}
			}
		}
		
		for(ScerevDAmbitoDTO ambitoAura : ambitiAura) {
			ScerevDAmbitoDTO ambitoScerev = dao.getAmbito(ambitoAura.getPk_ambito());
			if(ambitoScerev != null) {
			// ambito presente in scerev
				if(!ambitoScerev.equals(ambitoAura)) {
					BatchLoggerFactory.getLogger(this).info("- AMBITO DIVERSO: "+ ambitoScerev.getPk_ambito());
					dao.updateAmbito(ambitoAura);
				}
			} else {
			// ambito non presente in scerev	
				BatchLoggerFactory.getLogger(this).info("- AMBITO NON IN SCEREV: " + ambitoAura.getPk_ambito());
				dao.insertAmbito(ambitoAura);
			}
		}
	}

	private void elaboraDistretti(List<ScerevDDistrettoDTO> distrettiAura) throws SQLException {
		List<Integer> pkAura = listPkDistretti(distrettiAura);
		List<Integer> pkScerev = dao.getAllPKDistretti();
		if(pkAura.size() < pkScerev.size() ) {
		// Aura ha restituito meno distretti di quelli presenti in scerev
			for(Integer pkS : pkScerev) {
			// Imposto la data fine validita' dei distretti non restituiti da AURA
				if(!pkAura.contains(pkS)) {
					BatchLoggerFactory.getLogger(this).info("- DISTRETTO DA INVALIDARE: " + pkS);
					dao.invalidaDistretto(pkS);
				}
			}
		}
		
		for(ScerevDDistrettoDTO distrettoAura : distrettiAura) {
			ScerevDDistrettoDTO distrettoScerev = dao.getDistretto(distrettoAura.getPk_distretto());
			if(distrettoScerev != null) {
			// distretto presente in scerev
				if(!distrettoScerev.equals(distrettoAura)) {
					BatchLoggerFactory.getLogger(this).info("- DISTRETTO DIVERSO: "+ distrettoScerev.getPk_distretto() );
					dao.updateDistretto(distrettoAura);
				}
			} else {
			// distretto non presente in scerev	
				BatchLoggerFactory.getLogger(this).info("- DISTRETTO NON IN SCEREV: " + distrettoAura.getPk_distretto());
				dao.insertDistretto(distrettoAura);
			}
		}
	}

	private List<Integer> listPkDistretti(List<ScerevDDistrettoDTO> distretti) {
		List<Integer> l = new ArrayList<Integer>();
		for(ScerevDDistrettoDTO d : distretti) {
			l.add(d.getPk_distretto());
		}
		return l;
	}
	

	private List<Integer> listPkAmbiti(List<ScerevDAmbitoDTO> ambiti) {
		List<Integer> l = new ArrayList<Integer>();
		for(ScerevDAmbitoDTO a : ambiti) {
			l.add(a.getPk_ambito());
		}
		return l;
	}

}

