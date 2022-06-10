/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevlibbatch;

import java.io.File;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.propertyeditors.StringArrayPropertyEditor;

import it.csi.scerev.scerevlibbatch.dao.DBConnectionManager;
import it.csi.scerev.scerevlibbatch.dao.DataDAO;
import it.csi.scerev.scerevlibbatch.dto.ModelAmbulatorioLoccsiMedico;
import it.csi.scerev.scerevlibbatch.dto.ModelDisponibilita;
import it.csi.scerev.scerevlibbatch.exception.BatchException;
import it.csi.scerev.scerevlibbatch.exception.ConnectionException;
import it.csi.scerev.scerevlibbatch.exception.ConnectionSoapException;
import it.csi.scerev.scerevlibbatch.exception.OpessanNotFoundException;
import it.csi.scerev.scerevlibbatch.facade.MassimaliFacade;
import it.csi.scerev.scerevlibbatch.facade.OpessanFacade;
import it.csi.scerev.scerevlibbatch.logger.BatchLoggerFactory;
import it.csi.scerev.scerevlibbatch.opessan.def.opessanws.opessan.MassimaliMediciBody;
import it.csi.scerev.scerevlibbatch.serviziesterni.ServiziEsterniClient;
import it.csi.scerev.scerevlibbatch.util.SharedConstants;

public class Main {
	private Connection conn;
	private DataDAO dao = null;
	private MassimaliFacade massimaliFacade = null;
	private OpessanFacade opessanFacade = null;
	private ServiziEsterniClient serviziEsterni = null;
	private static final String webServiceNotificatore = "mediciMonitorati";

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
			massimaliFacade = new MassimaliFacade();
			opessanFacade = new OpessanFacade();
			serviziEsterni = ServiziEsterniClient.getInstance(dao);
			BatchLoggerFactory.getLogger(this).info("--- CONNESSIONE EFFETTUATA ---");
		} catch (SQLException e) {
			throw new ConnectionSoapException("Database connection fail: " + e.getMessage());
		} catch (ClassNotFoundException e) {
			throw new ConnectionSoapException("Database connection fail: " + e.getMessage());
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
		
		final String idApp = "SCEREVBE";
		final String utente = "SCEREVBE";
		final String utenteDel = null;
		final String utenteBen = "SCEREVBE";
		final String operazione = "insert/update";
		final String operazioneRead = "read";
		final String nomeServizio = "batchMonitoraggio"; // OGG_OPER
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
			
			// 2 Ricerco gli idAuras
			List<String> idAuras = dao.getIdAuraMedici();

			// 2.2 Nel caso non riesca a trovare nessuna risorsa eseguo questo blocco
			if (idAuras.size() == 0) {
				String msg = dao.getParametro(SharedConstants.MESSAGE_MEDICI_MONITORATI_NONTROVATI);
				System.out.println(msg);
				dao.insertAudit(idApp, ipAddress, utente, utenteDel, utenteBen, operazione,
						nomeServizio + " - response", idRequest, xCodServizio, "OK - " + msg);
				return;
			}

			// 3 Per ogni id Aura cerco di ricavarmi il payload relativo al medico
			List<ModelAmbulatorioLoccsiMedico> listaMedici = new ArrayList<ModelAmbulatorioLoccsiMedico>();
			for (String x : idAuras) {
				int idAura = Integer.parseInt(x);
				ModelAmbulatorioLoccsiMedico medico = new ModelAmbulatorioLoccsiMedico();
				medico.setId(x);
				medico.setCodiceFiscale((dao.getCfNomeCognomeMedico(x))[0]);

				// Traccio la chiamata al servizio opessanGetMassimali
				dao.insertAudit(idApp, ipAddress, utente, utenteDel, utenteBen, operazioneRead,
						"batchMonitoraggioOpessanGetMassimali" + " - request", idRequest, xCodServizio, null);

				try {
					
					MassimaliMediciBody massimaliMedico = serviziEsterni.getMassimaliMedici(new BigDecimal(idAura));
					if (massimaliMedico == null) {
						String msg = dao.getParametro(SharedConstants.ERROR_CODE_RISORSA_NON_TROVATA_OPESSAN);
						String[] nomecognome = dao.getCfNomeCognomeMedico(x);
						msg = msg.replaceAll("<nome e cognome>", nomecognome[1] + " " + nomecognome[2]);
						System.out.println(msg);
						dao.insertAudit(idApp, ipAddress, utente, utenteDel, utenteBen, operazioneRead,
								"batchMonitoraggioOpessanGetMassimali" + " - response", idRequest, xCodServizio,
								"KO - " + msg);
						continue;
					}

					medico.setAutolimitato((massimaliMedico.getAutolimitato() == null
							|| Integer.parseInt(massimaliMedico.getAutolimitato()) == 0) ? false : true);
					medico.setMassimale((massimaliMedico.getMassimale() == null) ? 0
							: Integer.parseInt(massimaliMedico.getMassimale()));
					medico.setMassimaleAssistenzaTemporanea((massimaliMedico.getMassimaleTemporaneo() == null) ? null
							: Integer.parseInt(massimaliMedico.getMassimaleTemporaneo()));
					medico.setMassimaleDeroga((massimaliMedico.getMassimaleInDeroga() == null) ? null
							: Integer.parseInt(massimaliMedico.getMassimaleInDeroga()));
					medico.setMassimaleInfanzia((massimaliMedico.getMassimaleOltre() == null) ? null
							: Integer.parseInt(massimaliMedico.getMassimaleOltre()));
					listaMedici.add(medico);

					dao.insertAudit(idApp, ipAddress, utente, utenteDel, utenteBen, operazioneRead,
							"batchMonitoraggioOpessanGetMassimali" + " - response", idRequest, xCodServizio, "OK");

				} catch (OpessanNotFoundException o) {

					// Rimando all'errore generico se ci sono problemi di accesso a Opessan
					String msg = dao.getParametro(SharedConstants.ERROR_CODE_OPESSAN_NOT_FOUND);
					throw new BatchException(msg);

				} catch (Exception e) {
					throw e;
				}

			}

			//4 Chiamata al servizio getDisponibilita
			dao.insertAudit(idApp, ipAddress, utente, utenteDel, utenteBen, operazioneRead,
					"batchMonitoraggioGetDisonibilita" + " - request", idRequest, xCodServizio, keyOper);
			List<ModelDisponibilita> disponibilita = massimaliFacade.getDisponibilita(listaMedici);
			if (disponibilita == null) {
				// Vorrà dire che il servizio non mi restituisce nulla
				String msg = dao.getParametro(SharedConstants.ERROR_CODE_RISORSA_GETMASSIMALI_NON_TROVATA);
				throw new BatchException("getDisponibilita - " + msg);
			}
			dao.insertAudit(idApp, ipAddress, utente, utenteDel, utenteBen, operazioneRead,
					"batchMonitoraggioGetDisonibilita" + " - response", idRequest, xCodServizio, "OK");

			for (ModelDisponibilita disp : disponibilita) {

				List<String[]> info = dao.getInfoScerevMonitoraggio(disp.getMedicoId());
				
				for (String[] xArr : info) {

					String[] arrS = xArr;
					String s = null;

					// 5 elaboro i dati
					if (arrS[2].equals("f") && disp.isSelezionabile())
						s = dao.getParametro(SharedConstants.SELEZIONABILE);
					if (arrS[3].equals("f") && disp.isSelezionabileRicongiungimentoFamiliare())
						s = (s == null) ? dao.getParametro(SharedConstants.SELEZIONABILE_RICONGIUNGIMENTO_FAMILIARE)
								: s + " - " + dao.getParametro(SharedConstants.SELEZIONABILE_RICONGIUNGIMENTO_FAMILIARE);
					if (arrS[4].equals("f") && disp.isSelezionabileAssistenzaTemporanea())
						s = (s == null) ? dao.getParametro(SharedConstants.SELEZIONABILE_ASSISTENZA_TEMPORANEA)
								: s + " - " + dao.getParametro(SharedConstants.SELEZIONABILE_ASSISTENZA_TEMPORANEA);
					if (arrS[5].equals("f") && disp.isSelezionabileInfanzia())
						s = (s == null) ? dao.getParametro(SharedConstants.SELEZIONABILE_INFANZIA) : s + " - " + dao.getParametro(SharedConstants.SELEZIONABILE_INFANZIA);

					if (s != null) {
						String[] nomecognome = dao.getCfNomeCognomeMedico(disp.getMedicoId());

						try {
							//6 Invio le notifiche
							boolean b = serviziEsterni.sendMessage(webServiceNotificatore, "", xArr[0],
									nomecognome[1] + " " + nomecognome[2], s);
							if (!b) {

								// L'email non è stata inviata e non aggiorno neanche il record
								String msg = dao.getParametro(SharedConstants.ERROR_CODE_NOTIFICA_NON_INVIATA).replaceAll("<cf_assistito>", arrS[0]); System.out.println(msg);
								dao.insertAudit(idApp, ipAddress, utente, utenteDel, utenteBen, operazione,
										"bacthMonitoraggioCallNotificatore", idRequest, xCodServizio, "KO - " + msg);
								continue;

							}
							//7 Se l'email è stata inviata aggiorno il record
							dao.updateDisponibilitaMedico(disp, arrS[0]);
							dao.commit();

						} catch (Exception e) {
							if (e instanceof IllegalStateException) {
								String msg = dao.getParametro(SharedConstants.ERROR_NOTIFICATORE);
								throw new BatchException("callNotificatore - " + msg);
							}throw e;
						}
					} else {
						//7 Se non c'è nessuna email da inviare aggiorno il record senza mandare nulla
						dao.updateDisponibilitaMedico(disp, arrS[0]);
						dao.commit();
					}

				}

			}
			
			//8 Traccio la risposta
			dao.insertAudit(idApp, ipAddress, utente, utenteDel, utenteBen, operazione, nomeServizio + " - response",
					idRequest, xCodServizio, "OK");

			
		} catch (Exception e) {
			e.printStackTrace();
			String msg = null;
			try {
				conn.rollback();
				
				if(e instanceof BatchException)
					msg = ((BatchException) e).getMsg();
				else msg = dao.getParametro(SharedConstants.ERROR_CODE_INTERNO_SERVER);
				
				BatchLoggerFactory.getLogger(this).error("--- ERRORE GENERICO: " + msg);
				dao.insertAudit(idApp, ipAddress, utente, utenteDel, utenteBen, operazione,
						nomeServizio + " - response", idRequest, xCodServizio, "KO - " + msg);

			} catch (SQLException e2) {
				// TODO Auto-generated catch block
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
