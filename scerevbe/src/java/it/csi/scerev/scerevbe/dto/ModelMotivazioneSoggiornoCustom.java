/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevbe.dto;

import it.csi.scerev.scerevjpa.business.entity.ScerevDMotivoSoggiorno;

public class ModelMotivazioneSoggiornoCustom extends ModelMotivazioneSoggiorno  {

	public ModelMotivazioneSoggiornoCustom(ScerevDMotivoSoggiorno dbMotivoSoggiorno) {
		this.setId(dbMotivoSoggiorno.getCodiceMotivoSoggiorno());
		this.setDescrizione(dbMotivoSoggiorno.getValoreMotivoSoggiorno());
		this.setCittadiniComunitari(dbMotivoSoggiorno.getCittadinoComunitario());
	}
}
