/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevutil.business.exception;

public class ParametriObbligatoriException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6566744962579058185L;

	private String codMsg;
	
	public ParametriObbligatoriException(String valoreMsg) {
		super(valoreMsg);
	}
	
	public ParametriObbligatoriException(String codMsg, String valoreMsg) {
		super(valoreMsg);
		this.codMsg = codMsg;
	}

	public String getCodMsg() {
		return codMsg;
	}

	public void setCodMsg(String codMsg) {
		this.codMsg = codMsg;
	}
}
