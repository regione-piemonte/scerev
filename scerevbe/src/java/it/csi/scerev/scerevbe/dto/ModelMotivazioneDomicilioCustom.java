/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevbe.dto;

import it.csi.scerev.scerevjpa.business.entity.ScerevDMotivoDomicilio;

public class ModelMotivazioneDomicilioCustom extends ModelMotivazioneDomicilio  {

	public ModelMotivazioneDomicilioCustom(ScerevDMotivoDomicilio dbMotivoDomicilio) {
		this.setId(dbMotivoDomicilio.getCodiceMotivoDomicilio());
		this.setDescrizione(dbMotivoDomicilio.getValoreMotivoDomicilio());
	}
}
