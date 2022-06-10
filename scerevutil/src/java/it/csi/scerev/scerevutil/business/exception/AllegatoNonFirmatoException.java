/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevutil.business.exception;

public class AllegatoNonFirmatoException extends Exception {
	private static final long serialVersionUID = 6244012757515316137L;
	private String codMsg;
	private String nomeFile;

	public AllegatoNonFirmatoException(String codMsg, String nomeFile) {
		this.codMsg = codMsg;
		this.nomeFile = nomeFile;
	}
	
	public AllegatoNonFirmatoException() {
		super();
	}
	
	public String getCodMsg() {
		return codMsg;
	}

	public void setCodMsg(String codMsg) {
		this.codMsg = codMsg;
	}

	public String getNomeFile() {
		return nomeFile;
	}

	public void setNomeFile(String nomeFile) {
		this.nomeFile = nomeFile;
	}
	
}
