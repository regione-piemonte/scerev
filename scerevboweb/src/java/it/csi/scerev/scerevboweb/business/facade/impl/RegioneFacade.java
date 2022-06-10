/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevboweb.business.facade.impl;

import it.csi.scerev.scerevboweb.business.facade.interfaces.RegioneFacadeIf;
import it.csi.scerev.scerevjpa.business.dao.interfaces.DataDaoIf;
import it.csi.scerev.scerevjpa.business.entity.ScerevDRegione;

public class RegioneFacade implements RegioneFacadeIf{
	private DataDaoIf dataDao;
	
	public DataDaoIf getDataDao() {
		return dataDao;
	}

	public void setDataDao(DataDaoIf dataDao) {
		this.dataDao = dataDao;
	}
	
	public ScerevDRegione getRegionePerCodice(String codRegione) {
		return dataDao.getRegionePerCodice(codRegione);
	}
}
