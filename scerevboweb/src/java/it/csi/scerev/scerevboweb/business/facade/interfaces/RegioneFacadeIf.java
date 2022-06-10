/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevboweb.business.facade.interfaces;

import it.csi.scerev.scerevjpa.business.entity.ScerevDRegione;

public interface RegioneFacadeIf {
	public ScerevDRegione getRegionePerCodice(String codRegione);
}
