/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevboweb.business.be.impl;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.stereotype.Component;

import it.csi.scerev.scerevboweb.business.SpringApplicationContextHelper;
import it.csi.scerev.scerevboweb.business.be.DistrettiApi;
import it.csi.scerev.scerevboweb.business.facade.interfaces.ListeFacadeIf;
import it.csi.scerev.scerevboweb.dto.Distretti;
import it.csi.scerev.scerevboweb.util.Constants;
import it.csi.scerev.scerevutil.business.SharedConstants;
@Path("")
@Component

public class DistrettiApiServiceImpl implements DistrettiApi {

	@GET 	
	@Path("/distretti")
    @Produces({ "application/json" })
	public Response getAllDistretti() {				
		
		
		ArrayList<Distretti> response = null;
		try {
			ListeFacadeIf listeFacade = (ListeFacadeIf) SpringApplicationContextHelper.getBean(SharedConstants.LISTE_FACADE);
			response = listeFacade.getAllDistretti();
			return Response.status(Status.OK).entity(response).header(Constants.COMPONENT_NAME, ""+System.currentTimeMillis()).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).header(Constants.COMPONENT_NAME, ""+System.currentTimeMillis()).entity(e).build();
		}
	}
	
}
