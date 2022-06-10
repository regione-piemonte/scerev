/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevboweb.business.facade.interfaces;

import java.util.ArrayList;

import it.csi.scerev.scerevboweb.dto.Azione;
import it.csi.scerev.scerevboweb.dto.Distretti;

public interface ListeFacadeIf {
	public ArrayList<Distretti> getAllDistretti();
	public ArrayList<Azione> getAllAzioniAmministratore();
	public ArrayList<Azione> getAllAzioniOperatore();
}
