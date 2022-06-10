/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevbe.business.facade.impl;

import it.csi.scerev.scerevbe.business.facade.interfaces.AuditFacadeIf;
import it.csi.scerev.scerevjpa.business.dao.interfaces.DataDaoIf;
import it.csi.scerev.scerevjpa.business.entity.ScerevLAudit;
import it.csi.scerev.scerevjpa.business.entity.ScerevLogAura;

public class AuditFacade implements AuditFacadeIf{
	private DataDaoIf dataDao;
	
	public DataDaoIf getDataDao() {
		return dataDao;
	}

	public void setDataDao(DataDaoIf dataDao) {
		this.dataDao = dataDao;
	}

	public void insertAudit(ScerevLAudit audit) {
		dataDao.insertAudit(audit);
	}

	@Override
	public ScerevLogAura insertUpdateLogAura(ScerevLogAura logAura) {
		return dataDao.insertUpdateLogAura(logAura);
	}
}
