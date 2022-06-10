/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevutil.business.exception;

public class AuraException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8537384549145104851L;
	private String codMsg;
	
	public AuraException(String codMsg) {
		super(codMsg);
	}

	public String getCodMsg() {
		return codMsg;
	}

	public void setCodMsg(String codMsg) {
		this.codMsg = codMsg;
	}
}
