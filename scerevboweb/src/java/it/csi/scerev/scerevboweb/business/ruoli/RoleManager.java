/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevboweb.business.ruoli;

import java.lang.reflect.Method;
import java.util.List;

import it.csi.iride2.policy.entity.Identita;
import it.csi.scerev.scerevboweb.business.serviziesterni.ServiziEsterniClient;
import it.csi.scerev.scerevboweb.util.Constants;
import it.csi.scerev.scerevserviziesterni.iride.base.Ruolo;

public class RoleManager {

	/**
	 * Da adattare in relazione ai ruoli previsti nella propria applicazione
	 * @param metodo
	 * @param user
	 * @throws Exception 
	 */
	public static Ruolo checkRoles(Method metodo, Identita user) throws Exception {
		List<Ruolo> ruoli = ServiziEsterniClient.getInstance().findRuoli(user, null);
		
		Ruolo ruoloAdmin = null;
		Ruolo ruoloOperatore = null;
		for(Ruolo ruolo : ruoli) {
			if(ruolo.getCodiceRuolo().equalsIgnoreCase(Constants.AMMINISTRATORE_IRIDE)) ruoloAdmin = ruolo;
			if(ruolo.getCodiceRuolo().equalsIgnoreCase(Constants.OPERATORE_IRIDE)) ruoloOperatore = ruolo;
		}
		
		if(ruoloAdmin == null && ruoloOperatore == null) throw new Exception("Nessun ruolo associato all'utente");
		
		if(metodo.getAnnotationsByType(Amministratore.class).length>0 && ruoloAdmin != null) return ruoloAdmin;
		else if(metodo.getAnnotationsByType(Operatore.class).length>0 && ruoloOperatore != null) return ruoloOperatore;
		else return null;		
	}
	
}
