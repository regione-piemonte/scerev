/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevboweb.business.facade.impl;

import java.util.ArrayList;
import java.util.List;

import it.csi.scerev.scerevboweb.business.facade.interfaces.ListeFacadeIf;
import it.csi.scerev.scerevboweb.dto.Azione;
import it.csi.scerev.scerevboweb.dto.Distretti;
import it.csi.scerev.scerevboweb.util.Constants;
import it.csi.scerev.scerevjpa.business.dao.interfaces.DataDaoIf;
import it.csi.scerev.scerevjpa.business.entity.ScerevDAzione;

public class ListeFacade implements ListeFacadeIf {
	
	private DataDaoIf dataDao;
	
	public DataDaoIf getDataDao() {
		return dataDao;
	}

	public void setDataDao(DataDaoIf dataDao) {
		this.dataDao = dataDao;
	}
	
	public ArrayList<Distretti> getAllDistretti() {
//		List<ScerevDAsl> listDb = dataDao.getAllAsl();
		
		ArrayList<Distretti> lista = new ArrayList<Distretti>();
//		for(ScerevDAsl s : listDb) {
//			lista.add(new Asl(s));
//		}
		
		return lista;
		

	}

	
	public ArrayList<Azione> getAllAzioniAmministratore() {
		List<ScerevDAzione> listJpa = dataDao.getAzioniByGroupDesc(Constants.AMMINISTRATORE_IRIDE);
		ArrayList<Azione> returnList = new ArrayList<Azione>();
		for (ScerevDAzione azioneJpa : listJpa) {
			returnList.add(new Azione(azioneJpa));
		}
		return returnList;
	}

	@Override
	public ArrayList<Azione> getAllAzioniOperatore() {
		List<ScerevDAzione> listJpa = dataDao.getAzioniByGroupDesc(Constants.OPERATORE_IRIDE);
		ArrayList<Azione> returnList = new ArrayList<Azione>();
		for (ScerevDAzione azioneJpa : listJpa) {
			returnList.add(new Azione(azioneJpa));
		}
		return returnList;
	}
}
