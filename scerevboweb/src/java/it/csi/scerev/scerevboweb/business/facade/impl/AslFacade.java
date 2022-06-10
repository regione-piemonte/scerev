/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevboweb.business.facade.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import it.csi.scerev.scerevboweb.business.facade.interfaces.AslFacadeIf;
import it.csi.scerev.scerevboweb.dto.Asl;
import it.csi.scerev.scerevboweb.dto.AslDistrettoAmbito;
import it.csi.scerev.scerevjpa.business.dao.interfaces.DataDaoIf;
import it.csi.scerev.scerevjpa.business.entity.ScerevDAsl;
import it.csi.scerev.scerevjpa.business.entity.ScerevDDistretto;

public class AslFacade implements AslFacadeIf {
	
	private DataDaoIf dataDao;
	
	public DataDaoIf getDataDao() {
		return dataDao;
	}

	public void setDataDao(DataDaoIf dataDao) {
		this.dataDao = dataDao;
	}
	
	public ArrayList<Asl> getAllAsl() {
		List<ScerevDAsl> listDb = dataDao.getAllAsl();
		
		ArrayList<Asl> lista = new ArrayList<Asl>();
		for(ScerevDAsl s : listDb) {
			lista.add(new Asl(s));
		}
		
		return lista;
		

	}
	
	public ScerevDAsl getAsl(BigDecimal codiceAsl) {
		return dataDao.getAsl(codiceAsl.toString());
	}
	
	public List<ScerevDDistretto> getDistretto(String idDistretto) {
		return dataDao.getDistretto(idDistretto);
	}
	
	public ArrayList<AslDistrettoAmbito> getAllAslDistrettoAmbito() {
		List<ScerevDAsl> listDb = dataDao.getAllAslDistrettoAmbito();
		
		ArrayList<AslDistrettoAmbito> lista = new ArrayList<AslDistrettoAmbito>();
		for(ScerevDAsl s : listDb) {
			lista.add(new AslDistrettoAmbito(s));
		}
		
		return lista;
		

	}
}
