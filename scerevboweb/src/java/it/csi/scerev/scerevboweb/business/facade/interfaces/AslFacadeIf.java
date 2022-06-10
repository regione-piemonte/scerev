/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevboweb.business.facade.interfaces;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import it.csi.scerev.scerevboweb.dto.Asl;
import it.csi.scerev.scerevboweb.dto.AslDistrettoAmbito;
import it.csi.scerev.scerevjpa.business.entity.ScerevDAsl;
import it.csi.scerev.scerevjpa.business.entity.ScerevDDistretto;

public interface AslFacadeIf {
	public ArrayList<Asl> getAllAsl();

	public ArrayList<AslDistrettoAmbito> getAllAslDistrettoAmbito();

	public ScerevDAsl getAsl(BigDecimal bigDecimal);

	public List<ScerevDDistretto> getDistretto(String string);
}
