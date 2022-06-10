/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevbe.dto;

import it.csi.scerev.scerevjpa.business.entity.ScerevDCittadinanza;

public class ModelCittadinanzaCustom extends ModelCittadinanza {

	public ModelCittadinanzaCustom(ScerevDCittadinanza dbCittadinanza) {
		this.setId(dbCittadinanza.getCodiceCittadinanza());
		this.setDescrizione(dbCittadinanza.getValoreCittadinanza());
		this.setComunitario(dbCittadinanza.getComunitario());
	}
}
