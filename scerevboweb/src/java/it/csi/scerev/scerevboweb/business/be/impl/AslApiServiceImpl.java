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
import it.csi.scerev.scerevboweb.business.be.AslApi;
import it.csi.scerev.scerevboweb.business.facade.interfaces.AslFacadeIf;
import it.csi.scerev.scerevboweb.dto.Asl;
import it.csi.scerev.scerevboweb.dto.AslDistrettoAmbito;
@Path("")
@Component

public class AslApiServiceImpl implements AslApi {

	@GET 	
	@Path("/asl")
    @Produces({ "application/json" })
	public Response getAllAsl() {				
		
		
		ArrayList<Asl> response = null;
		try {
			AslFacadeIf listeFacade = (AslFacadeIf) SpringApplicationContextHelper.getBean("aslFacade");
			response = listeFacade.getAllAsl();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.ok(response).header("someheader", ""+System.currentTimeMillis()).build();
	}
	
	@GET 	
	@Path("/aslDistrettoAmbito")
    @Produces({ "application/json" })
	public Response getAllAslDistrettoAmbito() {				
		
		
		ArrayList<AslDistrettoAmbito> response = null;
		try {
			AslFacadeIf listeFacade = (AslFacadeIf) SpringApplicationContextHelper.getBean("aslFacade");
			response = listeFacade.getAllAslDistrettoAmbito();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.ok(response).header("someheader", ""+System.currentTimeMillis()).build();
	}
	
}
