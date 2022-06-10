/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevlibbatch.dao;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import it.csi.scerev.scerevlibbatch.dto.ModelDisponibilita;
import it.csi.scerev.scerevlibbatch.logger.BatchLoggerFactory;



public class DataDAO {

	private Connection conn;
	private PreparedStatement ps;

	public DataDAO(Connection conn) {
		this.conn = conn;
	}
		
	public void commit() {
		try {
			this.conn.commit();
		} catch (SQLException e) {
			BatchLoggerFactory.getLogger(this.getClass()).info("ERROR WHILE COMMITTING: " + e.getMessage());
		}
	}

	public void rollback() {
		try {
			this.conn.rollback();
		} catch (SQLException e1) {
			BatchLoggerFactory.getLogger(this.getClass()).info("ERROR WHILE ROLLBACKING: " + e1.getMessage());
		}
	}

	public void closeAll() {
		try {
			if (this.ps != null)
				ps.close();

			if (this.conn != null)
				this.conn.close();

		} catch (SQLException e) {
			BatchLoggerFactory.getLogger(this.getClass()).info("ERROR WHILE CLOSING CONNECTION: " + e.getMessage());
		}
	}
	
	public String getParametro(String codParametro) throws SQLException{		
		try {
			this.ps = this.conn.prepareStatement(SQLStatements.GETPARAMETRO);
			this.ps.setString(1, codParametro);	
			ResultSet rs = this.ps.executeQuery();
			if (rs.next()) {
				return rs.getString(1);
			}		
		} catch (SQLException sqEx) {
			BatchLoggerFactory.getLogger(this).info(
					"Si e' verificato un errore SQL. Get Parametro. Elaborazione Batch terminata con errori ="
							+ sqEx.getLocalizedMessage());
			throw new SQLException();
		} finally {
			this.ps.close();
		}
		return null;
	}
	
	
	public List<String> getIdAuraMedici() throws SQLException{
		
		ArrayList<String> idAuras = new ArrayList<String>();
		try {
			
			this.ps = this.conn.prepareStatement(SQLStatements.GETIDAURAS);
			ResultSet rs = this.ps.executeQuery();
			while (rs.next()) {
				  idAuras.add(rs.getString(1));
			}
			return idAuras;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw e;
		}finally {
			try {
				this.ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
	
	public String[] getCfNomeCognomeMedico(String idAura) throws SQLException {
		
		ResultSet rs=null;
		try {
			this.ps = this.conn.prepareStatement(SQLStatements.GETCFNOMECOGNOME);
			this.ps.setString(1, idAura);	
			rs = this.ps.executeQuery();
			String[] cfnomecognome = null;
			if(rs.next()) {
				cfnomecognome = new String[3];
				cfnomecognome[0]=rs.getString(1);cfnomecognome[1]=rs.getString(2);cfnomecognome[2]=rs.getString(3);
			}
			return cfnomecognome;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw e;
		}finally {
			try {
				rs.close();
				this.ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
	}
	
	
	public List<String[]> getInfoScerevMonitoraggio(String idAura) throws SQLException{
		
		ResultSet rs=null;
		try {
			this.ps = this.conn.prepareStatement(SQLStatements.GETSCEREVINFO);
			this.ps.setString(1, idAura);	
			rs = this.ps.executeQuery();
			ArrayList<String[]> list = new ArrayList<String[]>();
			while(rs.next()) {
				String[] arrS = new String[6];
				arrS[0]=rs.getString(1);
				arrS[1]=rs.getString(2);
				arrS[2]=rs.getString(3);
				arrS[3]=rs.getString(4);
				arrS[4]=rs.getString(5);
				arrS[5]=rs.getString(6);
				list.add(arrS);
			}
			
			//Vuol dire che qualcosa è andato storto, perché almeno un recordo mi aspetto che me lo restituisca
			if(list.size()==0)
				return null;
			
			return list;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw e;
		}finally {
			try {
				rs.close();
				this.ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		
		
	}
	
	public void updateDisponibilitaMedico(ModelDisponibilita disponibilita, String cf) throws SQLException {
		
		try {
			
			this.ps = this.conn.prepareStatement(SQLStatements.UPDATEMONITORAGGIO);
			this.ps.setBoolean(1, disponibilita.isSelezionabile());
			this.ps.setBoolean(2, disponibilita.isSelezionabileRicongiungimentoFamiliare());
			this.ps.setBoolean(3, disponibilita.isSelezionabileAssistenzaTemporanea());
			this.ps.setBoolean(4, disponibilita.isSelezionabileInfanzia());
			this.ps.setString(5, disponibilita.getMedicoId());
			this.ps.setString(6, cf);
			
			this.ps.executeUpdate();
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw e;
		}finally {
			try {
				this.ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	
public void insertAudit(
		String idApp,
		String ipAddress,
		String utente,
		String utenteDel,
		String utenteBen,
		String operazione,
		String oggOper,
		String idRequest,
		String xCodServizio,
		String keyOper
	) throws SQLException {
		
		try {
			
			this.ps = this.conn.prepareStatement(SQLStatements.INSERTAUDIT);
			this.ps.setString(1, idApp);
			this.ps.setString(2, ipAddress);
			this.ps.setString(3, utente);
			this.ps.setString(4,utenteDel);
			this.ps.setString(5,utenteBen);
			this.ps.setString(6,operazione);
			this.ps.setString(7,oggOper);
			this.ps.setString(8,idRequest);
			this.ps.setString(9,xCodServizio);
			this.ps.setString(10,keyOper);
			
			this.ps.executeUpdate();
			commit();
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw e;
		}finally {
			try {
				this.ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	
	
	/*
	public ArrayList<FileDaElaborare> getFileDaElaborare() throws SQLException{		
		try {
			this.ps = this.conn.prepareStatement(SQLStatements.GETFILEDAELABORARE);			
			ResultSet rs = this.ps.executeQuery();
			ArrayList<FileDaElaborare> elencofile = new ArrayList<FileDaElaborare>();
			while (rs.next()) {
				FileDaElaborare singolofile = new FileDaElaborare();
				singolofile.setPk_id_file_csv_upload(rs.getInt("pk_id_file_csv_upload"));
				singolofile.setAudit_cf_autore_operazione(rs.getString("audit_cf_autore_operazione"));
				singolofile.setCod_regionale_asr(rs.getString("cod_regionale_asr"));
				singolofile.setData_upload_file(rs.getTimestamp("data_upload_file"));
				singolofile.setFile_csv(rs.getBytes("file_csv"));
				singolofile.setNome_file_csv(rs.getString("nome_file_csv"));
				singolofile.setFk_id_asr_competente(rs.getInt("fk_id_asr_competente"));
				elencofile.add(singolofile);
			}		
			
			return elencofile;
		} catch (SQLException sqEx) {
			BatchLoggerFactory.getLogger(this).info(
					"Si e' verificato un errore SQL. File da elaborare. Elaborazione Batch terminata con errori ="
							+ sqEx.getLocalizedMessage());
			throw new SQLException();
		} finally {
			this.ps.close();
		}
	}
	
	public String getNumeroPosizioneDebitoria() throws SQLException{		
		try {
			this.ps = this.conn.prepareStatement(SQLStatements.GETPOSIZIONEDEBITORIA);			
			ResultSet rs = this.ps.executeQuery();
			while (rs.next()) {
				return rs.getString("posizione");
			}		
		} catch (SQLException sqEx) {
			BatchLoggerFactory.getLogger(this).info(
					"Si e' verificato un errore SQL. Numero posizione debitoria. Elaborazione Batch terminata con errori ="
							+ sqEx.getLocalizedMessage());
			throw new SQLException();
		} finally {
			this.ps.close();
		}
		return null;
	}
	
	public String getParametro(String codParametro) throws SQLException{		
		try {
			this.ps = this.conn.prepareStatement(SQLStatements.GETPARAMETRO);
			this.ps.setString(1, codParametro);	
			ResultSet rs = this.ps.executeQuery();
			if (rs.next()) {
				return rs.getString(1);
			}		
		} catch (SQLException sqEx) {
			BatchLoggerFactory.getLogger(this).info(
					"Si e' verificato un errore SQL. Get Parametro. Elaborazione Batch terminata con errori ="
							+ sqEx.getLocalizedMessage());
			throw new SQLException();
		} finally {
			this.ps.close();
		}
		return null;
	}
	
	public DatiAggiuntivi getDatiAggiuntivi(int asr,int tipologiaentrata) throws SQLException{		
		try {
			this.ps = this.conn.prepareStatement(SQLStatements.GETDATIAGGIUNTIVI);
			this.ps.setInt(1, asr);
			this.ps.setInt(2, tipologiaentrata);
			ResultSet rs = this.ps.executeQuery();
			DatiAggiuntivi aggiuntivi = new DatiAggiuntivi();
			if (rs.next()) {
				aggiuntivi.setIdAsrCaricaCpd(rs.getString("cod_regionale_asr"));
				aggiuntivi.setCbill(rs.getString("cbill_asr"));
				aggiuntivi.setCcPostale(rs.getString("numero_cc_postale"));
				aggiuntivi.setIntestatarioCcPostale(rs.getString("desc_intestatario_cc_postale"));
				aggiuntivi.setTipoLettera(rs.getString("desc_template_lettera_accompagn"));
				aggiuntivi.setComuneAsl(rs.getString("nome_comune_ubicazione_sede"));
				aggiuntivi.setEnteCreditore(rs.getString("desc_ente_creditore_cedola"));
				aggiuntivi.setSettoreEnte(rs.getString("desc_settore_ente_creditore_cedola"));
				aggiuntivi.setInfoEnte(rs.getString("desc_info_ente_creditore_cedola"));
				aggiuntivi.setDelTuoEnte(rs.getString("desc_sito_web_ente_creditore_cedola"));
				aggiuntivi.setPagaSulTerritorio(rs.getString("desc_dove_pagare_su_territorio"));
				aggiuntivi.setDiPoste(rs.getString("desc_sito_web_poste_ita_pagamento_cedola"));
				aggiuntivi.setAutorizzazione(rs.getString("autorizz_poste_stampa_cedola"));
				aggiuntivi.setDataAutorizzazioneStampa(rs.getString("data_autorizz_poste_stampa_cedola"));
				aggiuntivi.setCfEnteCreditore(rs.getString("cod_fiscale_ente_creditore_cedola"));
				aggiuntivi.setOggettoPagamentoCedola(rs.getString("denominazione_tipol_entrata"));	
				aggiuntivi.setCodmotivopagamento(rs.getString("cod_motivo_pagamento"));	
				return aggiuntivi;
			}		
		} catch (SQLException sqEx) {
			BatchLoggerFactory.getLogger(this).info(
					"Si e' verificato un errore SQL. Get Dati Aggiuntivi. Elaborazione Batch terminata con errori ="
							+ sqEx.getLocalizedMessage());
			throw new SQLException();
		} finally {
			this.ps.close();
		}
		return null;
	}
	
	public Iuv getIuvPosizioneDebitoria(int asr,int tipolentrata) throws SQLException{		
		try {
			this.ps = this.conn.prepareStatement(SQLStatements.GETIUV);
			this.ps.setInt(1, asr);
			this.ps.setInt(2, tipolentrata);
			ResultSet rs = this.ps.executeQuery();
			Iuv iuv = new Iuv();
			if (rs.next()) {
				iuv.setApplicationcode(rs.getString("application_code"));
				iuv.setAuxdigit(rs.getString("aux_digit"));
				iuv.setIdcodiceversamento(rs.getInt("fk_id_codice_versamento"));
				iuv.setIdiuv(rs.getInt("pk_id_iuv"));
				iuv.setIuv(rs.getString("iuv"));
				return iuv;
			}		
		} catch (SQLException sqEx) {
			BatchLoggerFactory.getLogger(this).info(
					"Si e' verificato un errore SQL. Iuv Posizione debitoria. Elaborazione Batch terminata con errori ="
							+ sqEx.getLocalizedMessage());
			throw new SQLException();
		} finally {
			this.ps.close();
		}
		return null;
	}
	
	public int getContaIuv(int asr,int tipolentrata) throws SQLException{		
		try {
			this.ps = this.conn.prepareStatement(SQLStatements.CONTAIUVDISPONIBILI);
			this.ps.setInt(1, asr);
			this.ps.setInt(2, tipolentrata);
			ResultSet rs = this.ps.executeQuery();
			if (rs.next()) {
				 return rs.getInt(1);
			}		
		} catch (SQLException sqEx) {
			BatchLoggerFactory.getLogger(this).info(
					"Si e' verificato un errore SQL. Conta iuv. Elaborazione Batch terminata con errori ="
							+ sqEx.getLocalizedMessage());
			throw new SQLException();
		} finally {
			this.ps.close();
		}
		return 0;
	}
	
	public Iuv getIuvPk(int idiuv) throws SQLException{
		try {
			this.ps = this.conn.prepareStatement(SQLStatements.GETIUVPK);			
			this.ps.setInt(1, idiuv);			
			ResultSet rs = this.ps.executeQuery();
			Iuv iuv = new Iuv();
			if (rs.next()) {
				iuv.setApplicationcode(rs.getString("application_code"));
				iuv.setAuxdigit(rs.getString("aux_digit"));
				iuv.setIdcodiceversamento(rs.getInt("fk_id_codice_versamento"));
				iuv.setIdiuv(rs.getInt("pk_id_iuv"));
				iuv.setIuv(rs.getString("iuv"));
				return iuv;
			}
			this.ps.close();
			return null;

		} catch (SQLException sqEx) {
			BatchLoggerFactory.getLogger(this).error(
					"Si e' verificato un errore nel ritrovamento delle asl. Elaborazione Batch terminata con errori ="
							+ sqEx.getLocalizedMessage());
			throw new SQLException(sqEx.getMessage());
		}
	}
		
	
	
	public void updateIuv(Timestamp dataconsumo, int idiuv) {
		try { 
			BatchLoggerFactory.getLogger(this).info("update iuv: "+idiuv);
			this.ps = this.conn.prepareStatement(SQLStatements.SETIUVCONSUMATO);	
			this.ps.setTimestamp(1, dataconsumo);
			this.ps.setInt(2, idiuv);		
			int rs = this.ps.executeUpdate();
			this.ps.close();
			
		} catch (SQLException sqEx) {
			BatchLoggerFactory.getLogger(this).error(
					"Iuv non aggiornato: "
							+ sqEx.getLocalizedMessage());
			sqEx.printStackTrace();
		}
	}
	
	public int esisteDebitore(String codiceidentificativo) throws SQLException{		
		try {
			this.ps = this.conn.prepareStatement(SQLStatements.COUNTDEBITORE);
			this.ps.setString(1, codiceidentificativo);
			ResultSet rs = this.ps.executeQuery();
			if (rs.next()) {
				 return rs.getInt(1);
			}		
		} catch (SQLException sqEx) {
			BatchLoggerFactory.getLogger(this).info(
					"Si e' verificato un errore SQL. Conta debitore. Elaborazione Batch terminata con errori ="
							+ sqEx.getLocalizedMessage());
			throw new SQLException();
		} finally {
			this.ps.close();
		}
		return 0;
	}
	
	public int getDebitore(String codiceidentificativo) throws SQLException{		
		try {
			this.ps = this.conn.prepareStatement(SQLStatements.GETDEBITORE);
			this.ps.setString(1, codiceidentificativo);
			ResultSet rs = this.ps.executeQuery();
			if (rs.next()) {
				 return rs.getInt(1);
			}		
		} catch (SQLException sqEx) {
			BatchLoggerFactory.getLogger(this).info(
					"Si e' verificato un errore SQL. Conta debitore. Elaborazione Batch terminata con errori ="
							+ sqEx.getLocalizedMessage());
			throw new SQLException();
		} finally {
			this.ps.close();
		}
		return 0;
	}
	
	public void inserisciDebitore(CsvFileCompleto csvcompleto,int asr) {
		try { 
			CsvFile csviniziale = new CsvFile();
			csviniziale = csvcompleto.getFileiniziale();
			BatchLoggerFactory.getLogger(this).info("inserisci debitore: "+ csviniziale.getCognome() + " " + csviniziale.getNome() + " " + csviniziale.getRagioneSociale());
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		    Date parsed = null;
		    this.ps = this.conn.prepareStatement(SQLStatements.INSERTDEBITORE);
		    	 if (Checker.isValorizzato(csviniziale.getDataNascita())) {
		 			parsed = sdf.parse(csviniziale.getDataNascita());
		 			this.ps.setDate(8,new java.sql.Date(parsed.getTime()));
		 		    }
		 		    else
		 		    	this.ps.setDate(8,null);
			if (csviniziale.getTipologiaDebitore().equalsIgnoreCase("F")) {
				if (Checker.isValorizzato(csviniziale.getCodiceFiscale())) {
					this.ps.setString(1, csviniziale.getCodiceFiscale());
				}
				else if (Checker.isValorizzato(csviniziale.getCodiceEni())) {
					this.ps.setString(1, csviniziale.getCodiceEni());
				}
				else if (Checker.isValorizzato(csviniziale.getCodiceStp())) {
					this.ps.setString(1, csviniziale.getCodiceStp());
				}
			}
			else if (csviniziale.getTipologiaDebitore().equalsIgnoreCase("G")) {
					this.ps.setString(1, csviniziale.getPartitaIva());
			}
			this.ps.setInt(2,asr);
			this.ps.setString(3,csviniziale.getIdAnagraficoInternoDebitore());
			this.ps.setString(4,csviniziale.getTipologiaDebitore());
			this.ps.setString(5,csviniziale.getCognome());
			this.ps.setString(6,csviniziale.getNome());
			this.ps.setString(7,csviniziale.getSesso());
			if (Checker.isValorizzato(csviniziale.getIdAura()))
				this.ps.setInt(9,Converter.getInt(csviniziale.getIdAura()));
			else
				this.ps.setNull(9,java.sql.Types.NULL);
			this.ps.setString(10,csviniziale.getCodiceFiscale());
			this.ps.setString(11,csviniziale.getCodiceEni());
			this.ps.setString(12,csviniziale.getCodiceStp());
			this.ps.setString(13,csviniziale.getIndirizzoEmailPersonaFisica());
			this.ps.setString(14,csviniziale.getDescrizioneStatoNascita());
			this.ps.setString(15,csviniziale.getDescrizioneComuneNascita());
			this.ps.setString(16,csviniziale.getDescrizioneCittadinanza());
			this.ps.setString(17,csviniziale.getDescrizioneStatoDomicilio());
			this.ps.setString(18,csviniziale.getProvinciaDomicilio());
			this.ps.setString(19,csviniziale.getDescrizioneComuneDomicilio());
			this.ps.setString(20,csviniziale.getCapDomicilio());
			this.ps.setString(21,csviniziale.getIndirizzoDomicilio());
			this.ps.setString(22,csviniziale.getNumeroCivicoDomicilio());
			this.ps.setString(23,csviniziale.getRagioneSociale());
			this.ps.setString(24,csviniziale.getPartitaIva());
			this.ps.setString(25,csviniziale.getIndirizzoEmailSedeLegale());
			this.ps.setString(26,csviniziale.getProvinciaSedeLegale());
			this.ps.setString(27,csviniziale.getComuneSedeLegale());
			this.ps.setString(28,csviniziale.getCapSedeLegale());
			this.ps.setString(29,csviniziale.getIndirizzoSedeLegale());
			this.ps.setString(30,csviniziale.getNumeroCivicoSedeLegale());
			this.ps.setString(31,csvcompleto.getCfoperatore());
			this.ps.executeUpdate();
			this.ps.close();
			
		} catch (SQLException | ParseException sqEx) {
			BatchLoggerFactory.getLogger(this).error(
					"-inserimento debitore non riuscito: "
							+ sqEx.getLocalizedMessage());
			sqEx.printStackTrace();
		}
	}

	public void updateDebitore(CsvFileCompleto csvcompleto) {
		try { 
			CsvFile csviniziale = new CsvFile();
			csviniziale = csvcompleto.getFileiniziale();
			BatchLoggerFactory.getLogger(this).info("update debitore: "+ csviniziale.getCognome() + " " + csviniziale.getNome());
			this.ps = this.conn.prepareStatement(SQLStatements.UPDATEDEBITORE);
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		    Date parsed = null;
		    if (Checker.isValorizzato(csviniziale.getDataNascita())) {
			parsed = sdf.parse(csviniziale.getDataNascita());
			this.ps.setDate(5,new java.sql.Date(parsed.getTime()));
		    }
		    else
		    	this.ps.setDate(5,null);
			this.ps.setString(1,csviniziale.getIdAnagraficoInternoDebitore());
			this.ps.setString(2,csviniziale.getCognome());
			this.ps.setString(3,csviniziale.getNome());
			this.ps.setString(4,csviniziale.getSesso());
			if (Checker.isValorizzato(csviniziale.getIdAura()))
				this.ps.setInt(6,Converter.getInt(csviniziale.getIdAura()));
				else
					this.ps.setNull(6,java.sql.Types.NULL);
			this.ps.setString(7,csviniziale.getIndirizzoEmailPersonaFisica());
			this.ps.setString(8,csviniziale.getDescrizioneStatoNascita());
			this.ps.setString(9,csviniziale.getDescrizioneComuneNascita());
			this.ps.setString(10,csviniziale.getDescrizioneCittadinanza());
			this.ps.setString(11,csviniziale.getDescrizioneStatoDomicilio());
			this.ps.setString(12,csviniziale.getProvinciaDomicilio());
			this.ps.setString(13,csviniziale.getDescrizioneComuneDomicilio());
			this.ps.setString(14,csviniziale.getCapDomicilio());
			this.ps.setString(15,csviniziale.getIndirizzoDomicilio());
			this.ps.setString(16,csviniziale.getNumeroCivicoDomicilio());
			this.ps.setString(17,csviniziale.getRagioneSociale());
			this.ps.setString(18,csviniziale.getIndirizzoEmailSedeLegale());
			this.ps.setString(19,csviniziale.getProvinciaSedeLegale());
			this.ps.setString(20,csviniziale.getComuneSedeLegale());
			this.ps.setString(21,csviniziale.getCapSedeLegale());
			this.ps.setString(22,csviniziale.getIndirizzoSedeLegale());
			this.ps.setString(23,csviniziale.getNumeroCivicoSedeLegale());
			this.ps.setString(24,csvcompleto.getCfoperatore());
			if (csviniziale.getTipologiaDebitore().equalsIgnoreCase("F")) {
				if (Checker.isValorizzato(csviniziale.getCodiceFiscale())) {
					this.ps.setString(25, csviniziale.getCodiceFiscale());
				}
				else if (Checker.isValorizzato(csviniziale.getCodiceEni())) {
					this.ps.setString(25, csviniziale.getCodiceEni());
				}
				else if (Checker.isValorizzato(csviniziale.getCodiceStp())) {
					this.ps.setString(25, csviniziale.getCodiceStp());
				}
			}
			else if (csviniziale.getTipologiaDebitore().equalsIgnoreCase("G")) {
				this.ps.setString(25, csviniziale.getPartitaIva());
			}
			this.ps.executeUpdate();
			this.ps.close();
		} catch (SQLException | ParseException sqEx) {
			BatchLoggerFactory.getLogger(this).error(
					"Debitore non aggiornato: "
							+ sqEx.getLocalizedMessage());
			sqEx.printStackTrace();
		}
	}
	
	public int inserisciPosizioneDebitoria(CsvFileCompleto csvcompleto,int asr,int idcsv) {
		try { 
			BatchLoggerFactory.getLogger(this).info("inserisci posizione debitoria : "+ csvcompleto.getArricchito().getNumeroPosizioneDebitoria());
			String codprestazioneerogata = getParametro("COD_PRESTAZIONE_EROGATA");
			//trova debitore
			int iddebitore = 0;
			String codiceidentificativo = "";
			String denominazione ="";
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		    Date parsed = null;
			if (csvcompleto.getFileiniziale().getTipologiaDebitore().equalsIgnoreCase("F")) {
				denominazione = csvcompleto.getFileiniziale().getNome()+csvcompleto.getFileiniziale().getCognome();
				if (Checker.isValorizzato(csvcompleto.getFileiniziale().getCodiceFiscale())) {
					iddebitore = getDebitore(csvcompleto.getFileiniziale().getCodiceFiscale());
					codiceidentificativo =csvcompleto.getFileiniziale().getCodiceFiscale();
				} 
				else if (Checker.isValorizzato(csvcompleto.getFileiniziale().getCodiceEni())) {
					iddebitore = getDebitore(csvcompleto.getFileiniziale().getCodiceEni());
					codiceidentificativo =csvcompleto.getFileiniziale().getCodiceEni();
				}
				else if (Checker.isValorizzato(csvcompleto.getFileiniziale().getCodiceStp())) {
					iddebitore = getDebitore(csvcompleto.getFileiniziale().getCodiceStp());
					codiceidentificativo = csvcompleto.getFileiniziale().getCodiceStp();
				}
			}
			else if (csvcompleto.getFileiniziale().getTipologiaDebitore().equalsIgnoreCase("G")) {
				denominazione = csvcompleto.getFileiniziale().getRagioneSociale();
				iddebitore =getDebitore(csvcompleto.getFileiniziale().getPartitaIva());
				codiceidentificativo =csvcompleto.getFileiniziale().getPartitaIva();
			}
			if (iddebitore!=0) {
			this.ps = this.conn.prepareStatement(SQLStatements.INSERTPOSIZIONEDEBITORIA,Statement.RETURN_GENERATED_KEYS);
			this.ps.setString(1,csvcompleto.getArricchito().getNumeroPosizioneDebitoria());
			this.ps.setInt(2,Converter.getInt(csvcompleto.getFileiniziale().getCodiceTipologiaEntrata()));
			this.ps.setInt(3,iddebitore);
			this.ps.setInt(4,csvcompleto.getIuv());
			if (Checker.isValorizzato(csvcompleto.getFileiniziale().getAlgoritmoCalcoloInteressiDiMora()))
				this.ps.setInt(5,Converter.getInt(csvcompleto.getFileiniziale().getAlgoritmoCalcoloInteressiDiMora()));
			else
				this.ps.setNull(5,java.sql.Types.NULL);
			this.ps.setInt(6,idcsv);
			this.ps.setString(7,csvcompleto.getArricchito().getBarcode());
			this.ps.setBigDecimal(8,csvcompleto.getImportonetto());
			if (Checker.isValorizzato(csvcompleto.getFileiniziale().getPercentualeIva()))
				this.ps.setInt(9,Converter.getInt(csvcompleto.getFileiniziale().getPercentualeIva()));
			else
				this.ps.setNull(9,java.sql.Types.NULL);
			this.ps.setBigDecimal(10,csvcompleto.getImportosoloiva());
			this.ps.setBigDecimal(11,csvcompleto.getImportooneridigestione());
			this.ps.setBigDecimal(12,csvcompleto.getImportomarcadabollo());
		    if (Checker.isValorizzato(csvcompleto.getFileiniziale().getDataScadenza())) {
			parsed = sdf.parse(csvcompleto.getFileiniziale().getDataScadenza());
				this.ps.setDate(13,new java.sql.Date(parsed.getTime()));
		    }
		    else
		    	this.ps.setDate(13,null);
			this.ps.setString(14,csvcompleto.getFileiniziale().getIdAnagraficoInternoPd());
			this.ps.setString(15,csvcompleto.getFileiniziale().getPrestazioneErogata());
			this.ps.setString(16,codprestazioneerogata);
			this.ps.setString(17,csvcompleto.getCfoperatore());
//			this.ps.setString(18,csvcompleto.getArricchito().getOggettoPagamentoCedola());
			this.ps.setString(18,csvcompleto.getFileiniziale().getOggettoDelPagamento());
			this.ps.setString(19,csvcompleto.getArricchito().getTipo());
			this.ps.setString(20,csvcompleto.getArricchito().getBarcode());
			BigDecimal importodapagare = csvcompleto.getImportodapagare().setScale(2, RoundingMode.HALF_DOWN);
			String qrcode = "PAGOPA|002|" + csvcompleto.getArricchito().getCodiceAvviso() + "|" + csvcompleto.getArricchito().getCfEnteCreditore() 
	    			+ "|" + importodapagare.toString().replace(".", "").toUpperCase();
			    if (denominazione.length()>=40) {
			    	denominazione = denominazione.substring(0, 40);
			    }
			    else {
			    	denominazione = StringUtils.rightPad(denominazione,40," ");
			    }
			    String oggettopagamento =csvcompleto.getArricchito().getOggettoPagamentoCedola();
			    if (oggettopagamento.length()>=110) {
		    		 oggettopagamento = oggettopagamento.substring(0, 110);
				}
				else {
				  	oggettopagamento = StringUtils.rightPad(oggettopagamento,110," ");
				}
	    	String datamatrix = "codfase=NBPA;18" + csvcompleto.getArricchito().getCodiceAvviso() + "12" + 
	    			StringUtils.leftPad(csvcompleto.getArricchito().getCcPostale(),12,"0") + "10" +
	    			StringUtils.leftPad(importodapagare.toString().replace(".", ""),10,"0") + "3896P1" + csvcompleto.getArricchito().getCfEnteCreditore() + 
	    			StringUtils.rightPad(codiceidentificativo,16," ") +
	    			denominazione + StringUtils.rightPad(oggettopagamento,110," ") + StringUtils.rightPad("A",13," ").toUpperCase();
			this.ps.setString(21,qrcode);
			this.ps.setString(22,datamatrix);
			this.ps.setLong(23,new Long(csvcompleto.getArricchito().getCodiceAvviso()));
			this.ps.setString(24,csvcompleto.getFileiniziale().getProtAsl());
			this.ps.setString(25,csvcompleto.getFileiniziale().getDataProtAsl());
			this.ps.setString(26,csvcompleto.getFileiniziale().getDistretto());
			this.ps.setString(27,csvcompleto.getFileiniziale().getDirettore());
			this.ps.setString(28,csvcompleto.getFileiniziale().getInfoApertura());
			this.ps.setString(29,csvcompleto.getCfoperatore());
			this.ps.executeUpdate();	
			ResultSet rs = this.ps.getGeneratedKeys();
			if (rs.next()) {
			    return rs.getInt(1);
			}
			this.ps.close();
			}
			else {
				BatchLoggerFactory.getLogger(this).error(
						"-inserimento posizione debitoria non riuscito. Debitore non esistente ");
				throw new SQLException();
			}
			
		} catch (SQLException | ParseException sqEx) {
			BatchLoggerFactory.getLogger(this).error(
					"-inserimento posizione debitoria non riuscito: "
							+ sqEx.getLocalizedMessage());
			sqEx.printStackTrace();
			return 0;
		}
		return 0;
	}
	
	public int inserisciSpesa(String a,String b,String c) {
		try {
			
		this.ps = this.conn.prepareStatement(SQLStatements.INSPESA,Statement.RETURN_GENERATED_KEYS);
		this.ps.setString(1,a);
		this.ps.setString(2,b);
		this.ps.setString(3,c);
		this.ps.executeUpdate();
//		commit();
		ResultSet rs = this.ps.getGeneratedKeys();
		if (rs.next()) {
		    return rs.getInt(1);
		}
		this.ps.close();
		} catch (SQLException sqEx) {
			BatchLoggerFactory.getLogger(this).error(
					"-inserimento posizione debitoria non riuscito: "
							+ sqEx.getLocalizedMessage());
			sqEx.printStackTrace();
		}
		return 0;
	}
	
	public int getStatoPd(String codice) throws SQLException{		
		try {
			this.ps = this.conn.prepareStatement(SQLStatements.GETSTATOPD);
			this.ps.setString(1, codice);
			ResultSet rs = this.ps.executeQuery();
			if (rs.next()) {
				 return rs.getInt(1);
			}		
		} catch (SQLException sqEx) {
			BatchLoggerFactory.getLogger(this).info(
					"Si e' verificato un errore SQL. Get Stato Pd. Elaborazione Batch terminata con errori ="
							+ sqEx.getLocalizedMessage());
			throw new SQLException();
		} finally {
			this.ps.close();
		}
		return 0;
	}
	
	public int getStatoFileCsv(String codice) throws SQLException{		
		try {
			this.ps = this.conn.prepareStatement(SQLStatements.GETSTATOFILECSV);
			this.ps.setString(1, codice);
			ResultSet rs = this.ps.executeQuery();
			if (rs.next()) {
				 return rs.getInt(1);
			}		
		} catch (SQLException sqEx) {
			BatchLoggerFactory.getLogger(this).info(
					"Si e' verificato un errore SQL. Get Stato Pd. Elaborazione Batch terminata con errori ="
							+ sqEx.getLocalizedMessage());
			throw new SQLException();
		} finally {
			this.ps.close();
		}
		return 0;
	}
	
	public void inserisciStatoPd(int idPd,int idStatoPd,BigDecimal importo_non_corrisposto,BigDecimal importo,String cfoperatore) {
		try { 
			BatchLoggerFactory.getLogger(this).info("inserisci stato posizione debitoria : "+ idPd + " " + idStatoPd);
			this.ps = this.conn.prepareStatement(SQLStatements.INSSTATOPD);
			this.ps.setInt(1,idPd);
			this.ps.setInt(2,idStatoPd);
			this.ps.setBigDecimal(3,importo_non_corrisposto);
			this.ps.setBigDecimal(4,importo);
			this.ps.setString(5,cfoperatore);
			this.ps.executeUpdate();
			this.ps.close();
			
		} catch (SQLException sqEx) {
			BatchLoggerFactory.getLogger(this).error(
					"-inserimento stato pd non riuscito: "
							+ sqEx.getLocalizedMessage());
			sqEx.printStackTrace();
		}
	}
	
	public void updateStatoFileCar(String cfoperatore,int idfile, String stato) {
		try {
			
		this.ps = this.conn.prepareStatement(SQLStatements.UPDATESTATOFILE);
		this.ps.setString(1,cfoperatore);
		this.ps.setInt(2,idfile);
		this.ps.setString(3,stato);
		this.ps.executeUpdate();
		this.ps.close();
		} catch (SQLException sqEx) {
			BatchLoggerFactory.getLogger(this).error(
					"-aggiornamento stato file caricato non riuscito: "
							+ sqEx.getLocalizedMessage());
			sqEx.printStackTrace();
		}
	}
	
	public void insertStatoFileEla(int idfile,byte[] filearricchito,String nomefilearricchito,String cfoperatore,String stato) {
		try {
		int idstato = getStatoFileCsv(stato);
		this.ps = this.conn.prepareStatement(SQLStatements.INSERTSTATOFILEELA);
		this.ps.setInt(1,idfile);
		this.ps.setInt(2,idstato);
		this.ps.setBytes(3,filearricchito);
		this.ps.setString(4,nomefilearricchito);
		this.ps.setString(5,cfoperatore);
		this.ps.executeUpdate();
		this.ps.close();
		} catch (SQLException sqEx) {
			BatchLoggerFactory.getLogger(this).error(
					"-aggiornamento stato file caricato non riuscito: "
							+ sqEx.getLocalizedMessage());
			sqEx.printStackTrace();
		}
	}
	*/
}
