/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scadenzaassistenza.dao;


import java.sql.*;

import it.csi.scerev.scadenzaassistenza.logger.BatchLoggerFactory;



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
			BatchLoggerFactory.getLogger(this).info(
					"Si e' verificato un errore SQL. insertAudit. Elaborazione Batch terminata con errori ="
							+ e.getLocalizedMessage());
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
