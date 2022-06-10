/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevserviziesterni.facade.interfaces;

import java.util.Map;

public interface OpessanFacadeIf {		
	public Map<String, Object> getDettaglioOperatore(Map<String, String> bodyMap, Map<String, String> headerMap, Map<String, String> conn) throws Exception;

}
