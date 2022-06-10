/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevboweb.dto;

import it.csi.scerev.scerevjpa.business.entity.ScerevDMsg;
import it.csi.scerev.scerevutil.business.SharedConstants;

public class Messaggio {
	private String codice;
	private String tipo;
	private String messaggiostr;
	private int messaggionum;
	
	public Messaggio(ScerevDMsg msg) throws NumberFormatException{
		try {
			this.codice = msg.getCodiceMsg();
			this.tipo = msg.getTipoMsg();
			if(SharedConstants.STRING_TYPE.equalsIgnoreCase(msg.getTipoMsg())) this.messaggiostr = msg.getValoreMsg();
			else if (SharedConstants.NUMERIC_TYPE.equalsIgnoreCase(msg.getTipoMsg())) this.messaggionum = Integer.parseInt(msg.getValoreMsg());
		} catch(NumberFormatException e) {
			throw new NumberFormatException("Errore nel cast da ScerevDMsg a Messaggio. Controllare il formato di CODICE_MSG: "+msg.getCodiceMsg());			
		}
	}
	
	public Messaggio() {
	}

	public String getCodice() {
		return codice;
	}
	public void setCodice(String codice) {
		this.codice = codice;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getMessaggiostr() {
		return messaggiostr;
	}
	public void setMessaggiostr(String messaggiostr) {
		this.messaggiostr = messaggiostr;
	}
	public int getMessaggionum() {
		return messaggionum;
	}
	public void setMessaggionum(int messaggionum) {
		this.messaggionum = messaggionum;
	}

}
