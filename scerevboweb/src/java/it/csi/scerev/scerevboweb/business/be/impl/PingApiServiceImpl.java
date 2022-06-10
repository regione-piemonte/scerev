/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevboweb.business.be.impl;

import java.lang.reflect.Method;
//import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;

//import it.csi.scerev.scerevboweb.business.SpringApplicationContextHelper;
import it.csi.scerev.scerevboweb.business.be.PingApi;
//import it.csi.scerev.scerevboweb.business.facade.interfaces.PraticaFacadeIf;
import it.csi.scerev.scerevboweb.business.ruoli.Amministratore;
import it.csi.scerev.scerevboweb.business.ruoli.Operatore;
//import it.csi.scerev.scerevboweb.business.serviziesterni.ServiziEsterniClient;
//import it.csi.scerev.scerevjpa.business.entity.ScerevTPratica;
//import it.csi.scerev.scerevserviziesterni.aura.find.DatiAnagrafici;
//import it.csi.scerev.scerevutil.business.SharedConstants;
@Path("")
@Component
public class PingApiServiceImpl implements PingApi{

	@GET 		
	@Path("/ping1")
    @Produces({ "application/json" })
	@Amministratore
	@Operatore
	public Response ping(@Context HttpServletRequest httpRequest) {	
				
		String response = "OK";
		try {
//			PraticaFacadeIf praticaIf = (PraticaFacadeIf) SpringApplicationContextHelper.getBean(SharedConstants.PRATICA_FACADE);
//			ScerevTPratica pratica = praticaIf.getPratica(35);
			return Response.ok(response).header("someheader", ""+System.currentTimeMillis()).build();		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.ok(response).header("someheader", ""+System.currentTimeMillis()).build();
	}
	
	@GET 		
	@Path("/ping1/{id}")
    @Produces({ "application/json" })
	@Operatore
	public Response ping(@Context HttpServletRequest httpRequest, @PathParam("id") String id) {	
		
//		ServiziEsterniClient servizi = ServiziEsterniClient.getInstance();
//		List<DatiAnagrafici> lista = null;
		String response = "OK";
		try {
			Method questo = new Object() {}.getClass().getEnclosingMethod();
//			questo.getAnnotation(OPERAZIONEMAESTRO.class);
//			lista = servizi.findCittadino("SCNVCN95A20L109F", null, null, null);
			System.out.println(questo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.ok(response).header("someheader", ""+System.currentTimeMillis()).build();
	}
}
