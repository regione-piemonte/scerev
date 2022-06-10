/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevutil.business.exception;

public class DomainException extends Exception {
	private static final long serialVersionUID = 6244012757515316137L;
	private String codMsg;

	public DomainException(String codMsg) {
		this.codMsg = codMsg;
	}
	
	public DomainException() {
		super();
	}
	
	public String getCodMsg() {
		return codMsg;
	}

	public void setCodMsg(String codMsg) {
		this.codMsg = codMsg;
	}

}
