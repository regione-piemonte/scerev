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

import org.springframework.stereotype.Component;

import it.csi.scerev.scerevboweb.business.SpringApplicationContextHelper;
import it.csi.scerev.scerevboweb.business.be.TipologiePraticaApi;
import it.csi.scerev.scerevboweb.business.facade.interfaces.PraticaFacadeIf;
import it.csi.scerev.scerevboweb.dto.TipologiaPratica;
import it.csi.scerev.scerevutil.business.SharedConstants;
@Path("")
@Component
public class TipologiePraticaApiServiceImpl implements TipologiePraticaApi {

	@GET 	
	@Path("/tipologie-pratica")
    @Produces({ "application/json" })
	public Response getTipologiePratica() {				
		
		
		ArrayList<TipologiaPratica> response = null;
		try {
			PraticaFacadeIf listeFacade = (PraticaFacadeIf) SpringApplicationContextHelper.getBean(SharedConstants.PRATICA_FACADE);
			response = listeFacade.getTipologiePratica();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.ok(response).header("someheader", ""+System.currentTimeMillis()).build();
	}
	
}
