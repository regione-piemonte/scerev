/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevserviziesterni.facade.interfaces;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public interface AuraFacadeIf {		
	public Map<String, Object> findCittadino(String codiceFiscale, String cognome, String nome, String dataNascita, Map<String,String> conn) throws Exception;
	public Map<String, Object> getCittadino(BigDecimal idAura, Map<String,String> conn) throws Exception;
//	public Map<String, Object> getNumeroAssistitiInCaricoMedico(BigDecimal idAuraMedico, HashMap<String, String> conn) throws Exception;
}
