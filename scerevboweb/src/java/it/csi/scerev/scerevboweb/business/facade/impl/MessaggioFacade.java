/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevboweb.business.facade.impl;

import it.csi.scerev.scerevboweb.business.facade.interfaces.MessaggioFacadeIf;
import it.csi.scerev.scerevboweb.dto.Messaggio;
import it.csi.scerev.scerevjpa.business.dao.interfaces.DataDaoIf;

public class MessaggioFacade implements MessaggioFacadeIf {
	private DataDaoIf dataDao;

	public DataDaoIf getDataDao() {
		return dataDao;
	}

	public void setDataDao(DataDaoIf dataDao) {
		this.dataDao = dataDao;
	}

	public Messaggio getMessaggioPerCodice(String codMessaggio) {
		return new Messaggio(dataDao.getMessaggioPerCodice(codMessaggio));
	}
}
