/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevbe.business.facade.interfaces;

import it.csi.scerev.scerevjpa.business.entity.ScerevLAudit;
import it.csi.scerev.scerevjpa.business.entity.ScerevLogAura;

public interface AuditFacadeIf {
	
	public void insertAudit(ScerevLAudit audit);

	ScerevLogAura insertUpdateLogAura(ScerevLogAura logAura);
}
