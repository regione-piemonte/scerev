/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevaurabatch.dao;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import it.csi.scerev.scerevaurabatch.dto.ScerevDAmbitoDTO;
import it.csi.scerev.scerevaurabatch.dto.ScerevDDistrettoDTO;
import it.csi.scerev.scerevaurabatch.logger.BatchLoggerFactory;



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
	
	public Integer getPkAsl(String codAsl) throws SQLException{
		try {
			this.ps = this.conn.prepareStatement(SQLStatements.GETPKASL);
			this.ps.setString(1, codAsl);	
			ResultSet rs = this.ps.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}		
		} catch (SQLException sqEx) {
			BatchLoggerFactory.getLogger(this).info(
					"Si e' verificato un errore SQL. Get Pk Asl. Elaborazione Batch terminata con errori ="
							+ sqEx.getLocalizedMessage());
			throw new SQLException();
		} finally {
			this.ps.close();
		}
		return null;
	}
	
	public ScerevDDistrettoDTO getDistretto(Integer pk) throws SQLException {
		try {
			this.ps = this.conn.prepareStatement(SQLStatements.GETDISTRETTO);
			this.ps.setInt(1, pk);	
			ResultSet rs = this.ps.executeQuery();
			if (rs.next()) {
				return new ScerevDDistrettoDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4) != 0 ? rs.getInt(4) : null, 
						new java.util.Date(rs.getDate(5).getTime()), null);
			}		
		} catch (SQLException sqEx) {
			BatchLoggerFactory.getLogger(this).info(
					"Si e' verificato un errore SQL. GetDistretto. Elaborazione Batch terminata con errori ="
							+ sqEx.getLocalizedMessage());
			throw new SQLException();
		} finally {
			this.ps.close();
		}
		return null;
	}
	
	public ScerevDAmbitoDTO getAmbito(Integer pk) throws SQLException {
		try {
			this.ps = this.conn.prepareStatement(SQLStatements.GETAMBITO);
			this.ps.setInt(1, pk);	
			ResultSet rs = this.ps.executeQuery();
			if (rs.next()) {
				return new ScerevDAmbitoDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), 
						new java.util.Date(rs.getDate(5).getTime()), null);
			}		
		} catch (SQLException sqEx) {
			BatchLoggerFactory.getLogger(this).info(
					"Si e' verificato un errore SQL. GetAmbito. Elaborazione Batch terminata con errori ="
							+ sqEx.getLocalizedMessage());
			throw new SQLException();
		} finally {
			this.ps.close();
		}
		return null;
	}

	public List<ScerevDDistrettoDTO> getAllDistretti() throws SQLException {
		List<ScerevDDistrettoDTO> l = new ArrayList<ScerevDDistrettoDTO>();
		try {
			this.ps = this.conn.prepareStatement(SQLStatements.GETALLDISTRETTI);	
			ResultSet rs = this.ps.executeQuery();
			while (rs.next()) {
				l.add(new ScerevDDistrettoDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4) != 0 ? rs.getInt(4) : null, 
						new java.util.Date(rs.getDate(5).getTime()), null));
			}		
		} catch (SQLException sqEx) {
			BatchLoggerFactory.getLogger(this).info(
					"Si e' verificato un errore SQL. GetAllDistretti. Elaborazione Batch terminata con errori ="
							+ sqEx.getLocalizedMessage());
			throw new SQLException();
		} finally {
			this.ps.close();
		}
		return l;
	}
	
	public List<Integer> getAllPKDistretti() throws SQLException {
		List<Integer> l = new ArrayList<Integer>();
		try {
			this.ps = this.conn.prepareStatement(SQLStatements.GETALLPKDISTRETTI);	
			ResultSet rs = this.ps.executeQuery();
			while (rs.next()) {
				l.add(rs.getInt(1));
			}		
		} catch (SQLException sqEx) {
			BatchLoggerFactory.getLogger(this).info(
					"Si e' verificato un errore SQL. GetAllPkDistretti. Elaborazione Batch terminata con errori ="
							+ sqEx.getLocalizedMessage());
			throw new SQLException();
		} finally {
			this.ps.close();
		}
		return l;
	}

	public List<ScerevDAmbitoDTO> getAllAmbiti() throws SQLException {
		List<ScerevDAmbitoDTO> l = new ArrayList<ScerevDAmbitoDTO>();
		try {
			this.ps = this.conn.prepareStatement(SQLStatements.GETALLAMBITI);	
			ResultSet rs = this.ps.executeQuery();
			while (rs.next()) {
				l.add( new ScerevDAmbitoDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), 
						new java.util.Date(rs.getDate(5).getTime()), null));
			}		
		} catch (SQLException sqEx) {
			BatchLoggerFactory.getLogger(this).info(
					"Si e' verificato un errore SQL. GetAllAmbiti. Elaborazione Batch terminata con errori ="
							+ sqEx.getLocalizedMessage());
			throw new SQLException();
		} finally {
			this.ps.close();
		}
		return l;
	}
	
	public List<Integer> getAllPKAmbiti() throws SQLException {
		List<Integer> l = new ArrayList<Integer>();
		try {
			this.ps = this.conn.prepareStatement(SQLStatements.GETALLPKAMBITI);	
			ResultSet rs = this.ps.executeQuery();
			while (rs.next()) {
				l.add(rs.getInt(1));
			}		
		} catch (SQLException sqEx) {
			BatchLoggerFactory.getLogger(this).info(
					"Si e' verificato un errore SQL. GetAllPkAmbiti. Elaborazione Batch terminata con errori ="
							+ sqEx.getLocalizedMessage());
			throw new SQLException();
		} finally {
			this.ps.close();
		}
		return l;
	}

	public void invalidaDistretto(Integer pkS) throws SQLException {
		try {

			this.ps = this.conn.prepareStatement(SQLStatements.INVALIDADISTRETTO);
			this.ps.setInt(1, pkS);

			this.ps.executeUpdate();
			this.conn.commit();
		} catch (SQLException e) {
			this.conn.rollback();
			throw e;
		} finally {
			try {
				this.ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void invalidaAmbito(Integer pkS) throws SQLException {
		try {

			this.ps = this.conn.prepareStatement(SQLStatements.INVALIDAAMBITO);
			this.ps.setInt(1, pkS);

			this.ps.executeUpdate();
			
			this.conn.commit();
		} catch (SQLException e) {
			this.conn.rollback();
			throw e;
		} finally {
			try {
				this.ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void updateDistretto(ScerevDDistrettoDTO distrettoAura) throws SQLException {
		try {
			this.ps = this.conn.prepareStatement(SQLStatements.UPDATEDISTRETTO);
			this.ps.setString(1, distrettoAura.getCodice_distretto());
			this.ps.setString(2, distrettoAura.getDenominazione_distretto());
			this.ps.setInt(3, distrettoAura.getFk_azienda());
			this.ps.setDate(4, new java.sql.Date(distrettoAura.getData_inizio_validita().getTime()));
			this.ps.setInt(5, distrettoAura.getPk_distretto());

			this.ps.executeUpdate();
			this.conn.commit();
		} catch (SQLException e) {
			this.conn.rollback();
			throw e;
		} finally {
			try {
				this.ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
	}

	public void updateAmbito(ScerevDAmbitoDTO ambitoAura) throws SQLException {
		try {
			this.ps = this.conn.prepareStatement(SQLStatements.UPDATEAMBITO);
			this.ps.setString(1, ambitoAura.getCodice_ambito());
			this.ps.setString(2, ambitoAura.getDenominazione_ambito());
			this.ps.setInt(3, ambitoAura.getFk_distretto());
			this.ps.setDate(4, new java.sql.Date(ambitoAura.getData_inizio_validita().getTime()));
			this.ps.setInt(5, ambitoAura.getPk_ambito());

			this.ps.executeUpdate();
			this.conn.commit();
		} catch (SQLException e) {
			this.conn.rollback();
			throw e;
		} finally {
			try {
				this.ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void insertDistretto(ScerevDDistrettoDTO distrettoAura) throws SQLException {
		try {
			this.ps = this.conn.prepareStatement(SQLStatements.INSERTDISTRETTO);
			this.ps.setInt(1, distrettoAura.getPk_distretto());
			this.ps.setString(2, distrettoAura.getCodice_distretto());
			this.ps.setString(3, distrettoAura.getDenominazione_distretto());
			this.ps.setInt(4, distrettoAura.getFk_azienda());
			this.ps.setDate(5, new java.sql.Date(distrettoAura.getData_inizio_validita().getTime()));

			this.ps.executeUpdate();
			this.conn.commit();
		} catch (SQLException e) {
			this.conn.rollback();
			throw e;
		} finally {
			try {
				this.ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void insertAmbito(ScerevDAmbitoDTO ambitoAura) throws SQLException {
		try {
			this.ps = this.conn.prepareStatement(SQLStatements.INSERTAMBITO);
			this.ps.setInt(1, ambitoAura.getPk_ambito());
			this.ps.setString(2, ambitoAura.getCodice_ambito());
			this.ps.setString(3, ambitoAura.getDenominazione_ambito());
			this.ps.setInt(4, ambitoAura.getFk_distretto());
			this.ps.setDate(5, new java.sql.Date(ambitoAura.getData_inizio_validita().getTime()));

			this.ps.executeUpdate();
			this.conn.commit();
		} catch (SQLException e) {
			this.conn.rollback();
			throw e;
		} finally {
			try {
				this.ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void insertLogAura(String azione, String xmlRequest, String xmlResponse, String esito) throws SQLException {
		try {
			this.ps = this.conn.prepareStatement(SQLStatements.INSERTLOGAURA);
			this.ps.setString(1, azione);
			this.ps.setString(2, xmlRequest);
			this.ps.setString(3, xmlResponse);
			this.ps.setDate(4, new java.sql.Date(System.currentTimeMillis()));
			this.ps.setDate(5, new java.sql.Date(System.currentTimeMillis()));
			this.ps.setString(6, esito);

			this.ps.executeUpdate();
			this.conn.commit();
		} catch (SQLException e) {
			this.conn.rollback();
			throw e;
		} finally {
			try {
				this.ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void insertAudit(String idApp, String ipAddress, String utente, String utenteDel, String utenteBen,
			String operazione, String oggOper, String idRequest, String xCodServizio, String keyOper)
			throws SQLException {
		try {
			this.ps = this.conn.prepareStatement(SQLStatements.INSERTAUDIT);
			this.ps.setString(1, idApp);
			this.ps.setString(2, ipAddress);
			this.ps.setString(3, utente);
			this.ps.setString(4, utenteDel);
			this.ps.setString(5, utenteBen);
			this.ps.setString(6, operazione);
			this.ps.setString(7, oggOper);
			this.ps.setString(8, idRequest);
			this.ps.setString(9, xCodServizio);
			this.ps.setString(10, keyOper);

			this.ps.executeUpdate();
			commit();

		} catch (SQLException e) {
			this.conn.rollback();
			throw e;
		} finally {
			try {
				this.ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
