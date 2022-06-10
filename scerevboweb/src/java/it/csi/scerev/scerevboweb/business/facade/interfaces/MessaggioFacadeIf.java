/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevboweb.business.facade.interfaces;

import it.csi.scerev.scerevboweb.dto.Messaggio;

public interface MessaggioFacadeIf {
	public Messaggio getMessaggioPerCodice(String codMessaggio);
}
