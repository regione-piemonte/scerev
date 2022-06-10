/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevboweb.business.be.impl;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;

import it.csi.scerev.scerevboweb.business.SpringApplicationContextHelper;
import it.csi.scerev.scerevboweb.business.be.StatoPraticaApi;
import it.csi.scerev.scerevboweb.business.facade.interfaces.PraticaFacadeIf;
import it.csi.scerev.scerevboweb.dto.StatoPratica;

@Path("")
@Component
public class StatoPraticaApiServiceImpl implements StatoPraticaApi {

	@GET
	@Path("/stato-pratica/{tab}")
	@Produces({ "application/json" })
	public Response getStatoPratica(@PathParam("tab") Integer tab) {
		ArrayList<StatoPratica> response = null;

		try {
			PraticaFacadeIf listeFacade = (PraticaFacadeIf) SpringApplicationContextHelper.getBean("praticaFacade");
			response = listeFacade.getStatoPratica(tab);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return Response.ok(response).header("someheader", "" + System.currentTimeMillis()).build();
	}

}
