/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
/**********************************************
 * CSI PIEMONTE 
 **********************************************/
package it.csi.scerev.scerevbe.business.be;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import it.csi.scerev.scerevbe.dto.ModelAmbulatorioLoccsiMedico;

@Path("/disponibilita")




public interface DisponibilitaApi  {
   
    @POST
    
    @Consumes({ "application/json" })
    @Produces({ "application/json" })

    public Response disponibilitaPost(
    		@HeaderParam("X-Request-Id") String xRequestId,@HeaderParam("X-Forwarded-For") String xForwardedFor,
    		@HeaderParam("X-Codice-Servizio") String xCodiceServizio, List<ModelAmbulatorioLoccsiMedico> payload,
    		@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
}
