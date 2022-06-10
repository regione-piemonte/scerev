/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevbe.business.facade.interfaces;

import it.csi.scerev.scerevbe.dto.ModelAmbulatorioLoccsiMedico;
import it.csi.scerev.scerevbe.dto.ModelDisponibilita;
import it.csi.scerev.scerevjpa.business.entity.ScerevLAudit;
import it.csi.scerev.scerevserviziesterni.aura.get.SoggettoAuraBodyNew;
import it.csi.scerev.scerevserviziesterni.aura.setprofilosanitario.ProfiloSanitario;

public interface MedicoFacadeIf {
	ModelDisponibilita getMassimaliMedico(ModelAmbulatorioLoccsiMedico singleModel, ScerevLAudit audit) throws Exception;
	ProfiloSanitario costruisciReqSetProfiloSanitario(String citId, SoggettoAuraBodyNew sog, String tipoMov, String motivo) throws Exception;
}
