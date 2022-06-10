/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevboweb.business.facade.impl;

import it.csi.scerev.scerevboweb.business.facade.interfaces.AuditFacadeIf;
import it.csi.scerev.scerevjpa.business.dao.interfaces.DataDaoIf;
import it.csi.scerev.scerevjpa.business.entity.ScerevLAudit;

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
}
