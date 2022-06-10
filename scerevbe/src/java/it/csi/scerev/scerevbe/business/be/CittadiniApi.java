/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
/**********************************************
 * CSI PIEMONTE 
 **********************************************/
package it.csi.scerev.scerevbe.business.be;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import it.csi.scerev.scerevbe.dto.ModelAmbulatorioLoccsiMedico;
import it.csi.scerev.scerevbe.dto.Payload;
import it.csi.scerev.scerevbe.dto.Payload1;
import it.csi.scerev.scerevbe.dto.Payload2;
import it.csi.scerev.scerevbe.dto.Payload3;
import it.csi.scerev.scerevbe.dto.Payload4;
import it.csi.scerev.scerevbe.dto.PayloadRevocaMedico;

@Path("/cittadini")

public interface CittadiniApi {

	@DELETE
	@Path("/{cit_id}/assistenza")
	public Response cittadiniCitIdAssistenzaDelete(
			@HeaderParam("Shib-Identita-CodiceFiscale") String shibIdentitaCodiceFiscale,
			@HeaderParam("X-Request-Id") String xRequestId, @HeaderParam("X-Forwarded-For") String xForwardedFor,
			@HeaderParam("X-Codice-Servizio") String xCodiceServizio, @PathParam("cit_id") String citId,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@POST
	@Path("/{cit_id}/cambio-medico/richiesta")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	public Response cittadiniCitIdCambioMedicoRichiestaPost(
			@HeaderParam("Shib-Identita-CodiceFiscale") String shibIdentitaCodiceFiscale,
			@HeaderParam("X-Request-Id") String xRequestId, @HeaderParam("X-Forwarded-For") String xForwardedFor,
			@HeaderParam("X-Codice-Servizio") String xCodiceServizio, @PathParam("cit_id") String citId,
			Payload1 payload, @Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("/{cit_id}/cambio-medico/richiesta/{richiesta_id}/pdf")
	@Produces({ "application/json","application/pdf" })
	public Response cittadiniCitIdCambioMedicoRichiestaRichiestaIdPdfGet(
			@HeaderParam("Shib-Identita-CodiceFiscale") String shibIdentitaCodiceFiscale,
			@HeaderParam("X-Request-Id") String xRequestId, @HeaderParam("X-Forwarded-For") String xForwardedFor,
			@HeaderParam("X-Codice-Servizio") String xCodiceServizio, @PathParam("cit_id") String citId,
			@PathParam("richiesta_id") String richiestaId, @QueryParam("content_disposition") String contentDisposition,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@POST
	@Path("/{cit_id}/cambio-medico/richiesta/{richiesta_id}/rettifica")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	public Response cittadiniCitIdCambioMedicoRichiestaRichiestaIdRettificaPost(
			@HeaderParam("Shib-Identita-CodiceFiscale") String shibIdentitaCodiceFiscale,
			@HeaderParam("X-Request-Id") String xRequestId, @HeaderParam("X-Forwarded-For") String xForwardedFor,
			@HeaderParam("X-Codice-Servizio") String xCodiceServizio, @PathParam("cit_id") String citId,
			@PathParam("richiesta_id") String richiestaId, Payload2 payload, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);

	@GET
	@Path("/{cit_id}/certificato-revoca/pdf")
	@Produces({ "application/json","application/pdf" })
	public Response cittadiniCitIdCertificatoRevocaPdfGet(
			@HeaderParam("Shib-Identita-CodiceFiscale") String shibIdentitaCodiceFiscale,
			@HeaderParam("X-Request-Id") String xRequestId, @HeaderParam("X-Forwarded-For") String xForwardedFor,
			@HeaderParam("X-Codice-Servizio") String xCodiceServizio, @PathParam("cit_id") String citId,
			@QueryParam("content_disposition") String contentDisposition, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);

    @POST
    @Path("/{cit_id}/controllo-allegati")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    public Response cittadiniCitIdControlloAllegatiPost(
    		@HeaderParam("Shib-Identita-CodiceFiscale") String shibIdentitaCodiceFiscale,
    		@HeaderParam("X-Request-Id") String xRequestId, @HeaderParam("X-Forwarded-For") String xForwardedFor,
    		@HeaderParam("X-Codice-Servizio") String xCodiceServizio, @PathParam("cit_id") String citId, 
    		Payload4 payload, @Context SecurityContext securityContext,	@Context HttpHeaders httpHeaders , 
    		@Context HttpServletRequest httpRequest);
	
	@POST
	@Path("/{cit_id}/controllo-combinazione")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	public Response cittadiniCitIdControlloCombinazionePost(
			@HeaderParam("Shib-Identita-CodiceFiscale") String shibIdentitaCodiceFiscale,
			@HeaderParam("X-Request-Id") String xRequestId, @HeaderParam("X-Forwarded-For") String xForwardedFor,
			@HeaderParam("X-Codice-Servizio") String xCodiceServizio, @PathParam("cit_id") String citId,
			Payload payload, @Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("/{cit_id}/info")
	@Produces({ "application/json" })
	public Response cittadiniCitIdInfoGet(@HeaderParam("Shib-Identita-CodiceFiscale") String shibIdentitaCodiceFiscale,
			@HeaderParam("X-Request-Id") String xRequestId, @HeaderParam("X-Forwarded-For") String xForwardedFor,
			@HeaderParam("X-Codice-Servizio") String xCodiceServizio, @PathParam("cit_id") String citId,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@DELETE
	@Path("/{cit_id}/info/richiesta_cambio/{richiesta_id}")
	@Produces({ "application/json" })
	public Response cittadiniCitIdInfoRichiestaCambioRichiestaIdDelete(
			@HeaderParam("Shib-Identita-CodiceFiscale") String shibIdentitaCodiceFiscale,
			@HeaderParam("X-Request-Id") String xRequestId, @HeaderParam("X-Forwarded-For") String xForwardedFor,
			@HeaderParam("X-Codice-Servizio") String xCodiceServizio, @PathParam("cit_id") String citId,
			@PathParam("richiesta_id") String richiestaId, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);

	@GET
	@Path("/{cit_id}/medici-monitorati")
	@Produces({ "application/json" })
	public Response cittadiniCitIdMediciMonitoratiGet(
			@HeaderParam("Shib-Identita-CodiceFiscale") String shibIdentitaCodiceFiscale,
			@HeaderParam("X-Request-Id") String xRequestId, @HeaderParam("X-Forwarded-For") String xForwardedFor,
			@HeaderParam("X-Codice-Servizio") String xCodiceServizio, @PathParam("cit_id") String citId,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@DELETE
	@Path("/{cit_id}/medici-monitorati/{medico_id}")
	@Produces({ "application/json" })
	public Response cittadiniCitIdMediciMonitoratiMedicoIdDelete(
			@HeaderParam("Shib-Identita-CodiceFiscale") String shibIdentitaCodiceFiscale,
			@HeaderParam("X-Request-Id") String xRequestId, @HeaderParam("X-Forwarded-For") String xForwardedFor,
			@HeaderParam("X-Codice-Servizio") String xCodiceServizio, @PathParam("cit_id") String citId,
			@PathParam("medico_id") String medicoId, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);

	@POST
	@Path("/{cit_id}/medici-monitorati")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	public Response cittadiniCitIdMediciMonitoratiPost(
			@HeaderParam("Shib-Identita-CodiceFiscale") String shibIdentitaCodiceFiscale,
			@HeaderParam("X-Request-Id") String xRequestId, @HeaderParam("X-Forwarded-For") String xForwardedFor,
			@HeaderParam("X-Codice-Servizio") String xCodiceServizio, @PathParam("cit_id") String citId,
			ModelAmbulatorioLoccsiMedico payload, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);

	@POST
    @Path("/{cit_id}/revoca-medico")
    @Produces({ "application/json" })
    public Response cittadiniCitIdRevocaMedicoPost(
    		@HeaderParam("Shib-Identita-CodiceFiscale") String shibIdentitaCodiceFiscale,
    		@HeaderParam("X-Request-Id") String xRequestId,	@HeaderParam("X-Forwarded-For") String xForwardedFor,
    		@HeaderParam("X-Codice-Servizio") String xCodiceServizio, @PathParam("cit_id") String citId,  
    		@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , 
    		@Context HttpServletRequest httpRequest, PayloadRevocaMedico payloadRevocaMedico );

	@POST
	@Path("/{cit_id}/modulo-delegante/pdf")
	@Consumes({ "application/json" })
	@Produces({ "application/json","application/pdf" })
	public Response cittadiniCitIdModuloDelegantePdfPost(
			@HeaderParam("Shib-Identita-CodiceFiscale") String shibIdentitaCodiceFiscale,
			@HeaderParam("X-Request-Id") String xRequestId, @HeaderParam("X-Forwarded-For") String xForwardedFor,
			@HeaderParam("X-Codice-Servizio") String xCodiceServizio, @PathParam("cit_id") String citId,
			Payload3 payload, @QueryParam("content_disposition") String contentDisposition,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("/{cit_id}/tesserino-medico/pdf")
	@Produces({ "application/json","application/pdf" })
	public Response cittadiniCitIdTesserinoMedicoPdfGet(
			@HeaderParam("Shib-Identita-CodiceFiscale") String shibIdentitaCodiceFiscale,
			@HeaderParam("X-Request-Id") String xRequestId, @HeaderParam("X-Forwarded-For") String xForwardedFor,
			@HeaderParam("X-Codice-Servizio") String xCodiceServizio, @PathParam("cit_id") String citId,
			@QueryParam("content_disposition") String contentDisposition, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
	
	@GET
    @Path("/{cit_id}/allegato/{allegato_id}")   
    @Produces({"application/json", "application/pdf", "image/png", "image/gif", "image/jpeg" })
    public Response cittadiniCitIdAllegatoAllegatoIdGet(
    		@HeaderParam("Shib-Identita-CodiceFiscale") String shibIdentitaCodiceFiscale,
    		@HeaderParam("X-Request-Id") String xRequestId,@HeaderParam("X-Forwarded-For") String xForwardedFor,
    		@HeaderParam("X-Codice-Servizio") String xCodiceServizio, @PathParam("cit_id") String citId, 
    		@PathParam("allegato_id") String allegatoId, @Context SecurityContext securityContext, 
    		@Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
}
