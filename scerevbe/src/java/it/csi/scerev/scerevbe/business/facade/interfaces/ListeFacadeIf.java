/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevbe.business.facade.interfaces;

import java.math.BigDecimal;
import java.util.List;

import it.csi.scerev.scerevbe.dto.MedicoMonitoratoCittadino;
import it.csi.scerev.scerevbe.dto.ModelCittadinanza;
import it.csi.scerev.scerevbe.dto.ModelMedicoTipologia;
import it.csi.scerev.scerevbe.dto.ModelMotivazioneDomicilio;
import it.csi.scerev.scerevbe.dto.ModelMotivazioneSoggiorno;
import it.csi.scerev.scerevjpa.business.entity.ScerevDAmbito;
import it.csi.scerev.scerevjpa.business.entity.ScerevDAsl;
import it.csi.scerev.scerevjpa.business.entity.ScerevDDistretto;

public interface ListeFacadeIf {
	
	public List<ModelCittadinanza> getCittadinanze();
	public List<ModelMotivazioneSoggiorno> getMotivazioniSoggiorno();
	public List<ModelMotivazioneDomicilio> getMotivazioniDomicilio();
	public List<ModelMedicoTipologia> getTipologieMedico();
	public void updateDistretti(List<ScerevDDistretto> listaDistretti);
	public void updateAmbiti(List<ScerevDAmbito> listaAmbiti);
	public List<ScerevDAsl> getAsl();
	public ScerevDAsl getAsl(BigDecimal codiceAsl);
	public List<ScerevDDistretto> getDistretto(String idDistretto);
	public ScerevDDistretto getDistrettoByPk(Integer string);
	public List<MedicoMonitoratoCittadino> getMediciMonitoratiByCittadion(String citId);
}
