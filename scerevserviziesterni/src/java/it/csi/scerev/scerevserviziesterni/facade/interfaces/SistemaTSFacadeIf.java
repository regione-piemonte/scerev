/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevserviziesterni.facade.interfaces;

import java.util.Map;

public interface SistemaTSFacadeIf {		
	public Map<String, Object> findCittadino(String codiceFiscale, Map<String,String> conn) throws Exception;
}
