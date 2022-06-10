/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevutil.business.exception;

public class IntegritaException extends Exception {
	private String codMsg;
	/**
	 * 
	 */
	private static final long serialVersionUID = -6566744962579058185L;

	public IntegritaException(String codMsg, String valoreMsg) {
		super(valoreMsg);
		this.codMsg = codMsg;
	}
	
	public IntegritaException(String valoreMsg) {
		super(valoreMsg);
	}
	

	public IntegritaException() {
		super();
	}
	
	public String getCodMsg() {
		return codMsg;
	}

	public void setCodMsg(String codMsg) {
		this.codMsg = codMsg;
	}

}
