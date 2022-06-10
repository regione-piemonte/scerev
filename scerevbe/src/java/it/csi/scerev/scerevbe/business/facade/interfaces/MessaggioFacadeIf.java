/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevbe.business.facade.interfaces;

import it.csi.scerev.scerevbe.dto.ModelMessaggio;

public interface MessaggioFacadeIf {
	public ModelMessaggio getMessaggioPerCodice(String codMessaggio);
}
