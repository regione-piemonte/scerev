/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevbe.dto;

import it.csi.scerev.scerevjpa.business.entity.ScerevDTipologiaMedico;

public class ModelMedicoTipologiaCustom extends ModelMedicoTipologia  {

	public ModelMedicoTipologiaCustom(ScerevDTipologiaMedico dbTipologiaMedico) {
		this.setId(dbTipologiaMedico.getCodiceTipologiaMedico());
		this.setDescrizione(dbTipologiaMedico.getValoreTipologiaMedico());
	}
}

