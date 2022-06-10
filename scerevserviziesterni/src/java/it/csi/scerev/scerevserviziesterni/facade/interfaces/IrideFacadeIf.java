/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevserviziesterni.facade.interfaces;

import java.util.List;
import java.util.Map;

import it.csi.scerev.scerevserviziesterni.iride.base.Ruolo;

public interface IrideFacadeIf {
	Map<String, Object> findRuoli(Map<String, String> body, Map<String, String> conn) throws Exception;
}
