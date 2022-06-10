/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevbe.business.facade.interfaces;

import it.csi.scerev.scerevbe.dto.ModelCambioMedicoRichiesta;
import it.csi.scerev.scerevbe.dto.ModelCittadinoInfoCustom;
import it.csi.scerev.scerevbe.dto.ModelCittadinoSistemaEsterno;
import it.csi.scerev.scerevbe.dto.ModelControlloAllegati;
import it.csi.scerev.scerevbe.dto.Payload1;
import it.csi.scerev.scerevbe.dto.Payload2;
import it.csi.scerev.scerevbe.dto.Payload4;
import it.csi.scerev.scerevjpa.business.entity.ScerevLAudit;
import it.csi.scerev.scerevjpa.business.entity.ScerevTPratica;
import it.csi.scerev.scerevutil.business.exception.DomainException;

public interface PraticaFacadeIf {
	
	
	ModelCambioMedicoRichiesta elaboraRichiesta(Payload1 payload, ScerevLAudit audit, String citId, String shib) throws Exception;

	ModelCittadinoInfoCustom getCittadinoInfo(String citId, ScerevLAudit audit) throws Exception;
	
	ModelCambioMedicoRichiesta rettificaRichiesta(ScerevLAudit audit, String citId, String richiestaId,
			Payload2 payload) throws Exception;

	void chkPraticaPendente(String citId) throws DomainException;

	ScerevTPratica aggiornamentoPraticaRichiestaNuovoMedico(Payload1 input, String citId, ModelCittadinoSistemaEsterno model, int tipoRichiesta, String shib, ScerevLAudit audit);

	ModelControlloAllegati controlloAllegati(Payload4 payload, ScerevLAudit audit, String citId,
			String shibIdentitaCodiceFiscale);

}
