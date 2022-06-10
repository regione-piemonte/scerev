/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevboweb.business.be;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Produces({ "application/json" })

public interface StatoPraticaApi {
	
	 @GET  
	 @Produces({ "application/json" })
	 public Response getStatoPratica(Integer tab);
	 
}
