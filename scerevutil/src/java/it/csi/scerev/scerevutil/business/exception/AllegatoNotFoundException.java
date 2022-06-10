/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevutil.business.exception;

public class AllegatoNotFoundException extends Exception {
	private static final long serialVersionUID = -750450314597308115L;
	private String codMsg;
	
	public AllegatoNotFoundException() {
		super();
	}
	
	public AllegatoNotFoundException(String valoreMsg) {
		super(valoreMsg);
	}
	
	public AllegatoNotFoundException(String codMsg, String valoreMsg) {
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
