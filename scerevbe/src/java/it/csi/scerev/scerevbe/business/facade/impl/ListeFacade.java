/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevbe.business.facade.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import it.csi.scerev.scerevbe.business.facade.interfaces.ListeFacadeIf;
import it.csi.scerev.scerevbe.dto.MedicoMonitoratoCittadino;
import it.csi.scerev.scerevbe.dto.ModelCittadinanza;
import it.csi.scerev.scerevbe.dto.ModelCittadinanzaCustom;
import it.csi.scerev.scerevbe.dto.ModelMedicoTipologia;
import it.csi.scerev.scerevbe.dto.ModelMedicoTipologiaCustom;
import it.csi.scerev.scerevbe.dto.ModelMotivazioneDomicilio;
import it.csi.scerev.scerevbe.dto.ModelMotivazioneDomicilioCustom;
import it.csi.scerev.scerevbe.dto.ModelMotivazioneSoggiorno;
import it.csi.scerev.scerevbe.dto.ModelMotivazioneSoggiornoCustom;
import it.csi.scerev.scerevjpa.business.dao.interfaces.DataDaoIf;
import it.csi.scerev.scerevjpa.business.entity.ScerevDAmbito;
import it.csi.scerev.scerevjpa.business.entity.ScerevDAsl;
import it.csi.scerev.scerevjpa.business.entity.ScerevDCittadinanza;
import it.csi.scerev.scerevjpa.business.entity.ScerevDDistretto;
import it.csi.scerev.scerevjpa.business.entity.ScerevDTipologiaMedico;
import it.csi.scerev.scerevjpa.business.entity.ScerevTMonitoraggio;
import it.csi.scerev.scerevjpa.business.entity.ScerevDMotivoDomicilio;
import it.csi.scerev.scerevjpa.business.entity.ScerevDMotivoSoggiorno;

public class ListeFacade implements ListeFacadeIf {
	private DataDaoIf dataDao;
	
	public DataDaoIf getDataDao() {
		return dataDao;
	}

	public void setDataDao(DataDaoIf dataDao) {
		this.dataDao = dataDao;
	}
	
	public List<ModelCittadinanza> getCittadinanze() {
		List<ScerevDCittadinanza> elenco = dataDao.getListaCittadinanze();
		List<ModelCittadinanza> output = new ArrayList<ModelCittadinanza>();
		for (Iterator<ScerevDCittadinanza> iterator = elenco.iterator(); iterator.hasNext();) {
			ScerevDCittadinanza dbCittadinanza = (ScerevDCittadinanza) iterator.next();
			ModelCittadinanza model = new ModelCittadinanzaCustom(dbCittadinanza);
			output.add(model);
		}
		return output;
	}
	
	public List<ModelMotivazioneSoggiorno> getMotivazioniSoggiorno() {
		List<ScerevDMotivoSoggiorno> elenco = dataDao.getListaMotiviSoggiorno();
		List<ModelMotivazioneSoggiorno> output = new ArrayList<ModelMotivazioneSoggiorno>();
		for (Iterator<ScerevDMotivoSoggiorno> iterator = elenco.iterator(); iterator.hasNext();) {
			ScerevDMotivoSoggiorno dbMotivoSoggiorno = (ScerevDMotivoSoggiorno) iterator.next();
			ModelMotivazioneSoggiorno model = new ModelMotivazioneSoggiornoCustom(dbMotivoSoggiorno);
			output.add(model);
		}
		return output;
	}

	public List<ModelMotivazioneDomicilio> getMotivazioniDomicilio() {
		List<ScerevDMotivoDomicilio> elenco = dataDao.getListaMotiviDomicilio();
		List<ModelMotivazioneDomicilio> output = new ArrayList<ModelMotivazioneDomicilio>();
		for (Iterator<ScerevDMotivoDomicilio> iterator = elenco.iterator(); iterator.hasNext();) {
			ScerevDMotivoDomicilio dbMotivoDomicilio = (ScerevDMotivoDomicilio) iterator.next();
			ModelMotivazioneDomicilio model = new ModelMotivazioneDomicilioCustom(dbMotivoDomicilio);
			output.add(model);
		}
		return output;
	}
	
	public List<ModelMedicoTipologia> getTipologieMedico() {
		List<ScerevDTipologiaMedico> elenco = dataDao.getListaTipologieMedico();
		List<ModelMedicoTipologia> output = new ArrayList<ModelMedicoTipologia>();
		for (Iterator<ScerevDTipologiaMedico> iterator = elenco.iterator(); iterator.hasNext();) {
			ScerevDTipologiaMedico dbTipologiaMedico = (ScerevDTipologiaMedico) iterator.next();
			ModelMedicoTipologia model = new ModelMedicoTipologiaCustom(dbTipologiaMedico);
			output.add(model);
		}
		return output;
	}
	
	public void updateDistretti(List<ScerevDDistretto> listaDistretti) {
		for (ScerevDDistretto distretto : listaDistretti) {
			try {
				ScerevDDistretto tmp = dataDao.getDistrettoByPk(distretto.getPkDistretto());
				if (tmp != null) {
					dataDao.updateDistretto(distretto);
				} else
					dataDao.saveDistretto(distretto);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void updateAmbiti(List<ScerevDAmbito> listaAmbiti) {
		for(ScerevDAmbito ambito : listaAmbiti) {
			try {
				ScerevDAmbito tmp = dataDao.getAmbito(ambito.getPkAmbito());
				if(tmp!=null) 					
					dataDao.updateAmbito(ambito);
				else 
					dataDao.saveAmbito(ambito);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public List<ScerevDAsl> getAsl() {
		return dataDao.getAllAsl();		
	}

	public ScerevDAsl getAsl(BigDecimal codiceAsl) {
		return dataDao.getAsl(codiceAsl.toString());
	}


	public List<ScerevDDistretto> getDistretto(String idDistretto) {
		return dataDao.getDistretto(idDistretto);
	}

	@Override
	public ScerevDDistretto getDistrettoByPk(Integer string) {
		// TODO Auto-generated method stub
		return dataDao.getDistrettoByPk(string);
	}

	@Override
	public List<MedicoMonitoratoCittadino> getMediciMonitoratiByCittadion(String citId) {
		
		List<ScerevTMonitoraggio> lDao = dataDao.getMediciMonitoratiByCittadino(citId);
		List<MedicoMonitoratoCittadino> list = new ArrayList<MedicoMonitoratoCittadino>();
		MedicoMonitoratoCittadino out;
		for(ScerevTMonitoraggio med : lDao) {
			
			out = new MedicoMonitoratoCittadino();
			out.setCodiceFiscale(med.getCfMedico());
			out.setId(med.getIdAuraMedico());
			out.setNome(med.getNomeMedico());
			out.setCognome(med.getCognomeMedico());
			
			list.add(out);
			
		}
		
		return list;
		
	}

	
}
