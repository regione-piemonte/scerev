/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevutil.business.exception;

public class OpessanNotFoundException extends Exception {

	private String codMsg;
	/**
	 * 
	 */
	private static final long serialVersionUID = 2691005476277171615L;

	public String getCodMsg() {
		return codMsg;
	}

	public void setCodMsg(String codMsg) {
		this.codMsg = codMsg;
	}


}
