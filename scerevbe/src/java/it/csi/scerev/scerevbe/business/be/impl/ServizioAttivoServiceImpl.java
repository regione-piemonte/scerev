/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevbe.business.be.impl;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.Response.Status;

import it.csi.scerev.scerevbe.business.be.ServizioAttivoApi;
import it.csi.scerev.scerevbe.dto.ModelInfoServizio;
import it.csi.scerev.scerevbe.util.Constants;

public class ServizioAttivoServiceImpl implements ServizioAttivoApi {

	public Response servizioAttivoGet(
			@HeaderParam("X-Request-Id") String xRequestId, 
			@HeaderParam("X-Forwarded-For") String xForwardedFor, 
			@HeaderParam("X-Codice-Servizio") String xCodiceServizio,
			SecurityContext securityContext, 
			HttpHeaders httpHeaders, 
			HttpServletRequest httpRequest) {
		ModelInfoServizio out = new ModelInfoServizio();
		out.setData(new Date());
		try {
			final String methodName = new Object() {}.getClass().getEnclosingMethod().getName();
			out.setDescrizione(methodName);
			out.setServizioAttivo(Boolean.TRUE);
			out.setNome(methodName);
			return Response.status(Status.OK).header(Constants.X_REQUEST_ID, ""+xRequestId).entity(out).build();
		} catch (Exception e) {
			out.setDescrizione(e.getLocalizedMessage());
			out.setServizioAttivo(Boolean.FALSE);
			out.setNome(e.getLocalizedMessage());
			return Response.status(Status.INTERNAL_SERVER_ERROR).header(Constants.X_REQUEST_ID, ""+xRequestId).entity(out).build();
		}
	}
}
