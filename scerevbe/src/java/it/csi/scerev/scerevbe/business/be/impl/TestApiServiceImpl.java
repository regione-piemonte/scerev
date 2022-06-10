/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevbe.business.be.impl;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import it.csi.scerev.scerevbe.business.be.TestApi;
import it.csi.scerev.scerevbe.dto.ModelAsl;

public class TestApiServiceImpl implements TestApi {

	@Override
	public Response mediciMedicoIdAssociazioniGet(String xRequestId, String xForwardedFor, String xCodiceServizio,
			String medicoId, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {

		ModelAsl modelAsl = new ModelAsl();
		modelAsl.setId("1");
		modelAsl.setDescrizione("Test eseguito correttamente");

		return Response.ok(modelAsl).build();
	}

}
