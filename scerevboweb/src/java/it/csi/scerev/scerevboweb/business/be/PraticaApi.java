/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
/**********************************************
 * CSI PIEMONTE 
 **********************************************/
package it.csi.scerev.scerevboweb.business.be;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import it.csi.scerev.scerevboweb.dto.AccettaRichiesta;
import it.csi.scerev.scerevboweb.dto.ChiudeRichiesta;
import it.csi.scerev.scerevboweb.dto.NotaInterna;
import it.csi.scerev.scerevboweb.dto.RicercaRichiesteInput;
import it.csi.scerev.scerevboweb.dto.UpdatePratica;

public interface PraticaApi {
	public Response getListaPratiche(@Context HttpServletRequest httpRequest,
			@HeaderParam("X-Request-ID") String xRequestId, RicercaRichiesteInput input);

	public Response getPratica(@Context HttpServletRequest httpRequest, @HeaderParam("X-Request-ID") String xRequestId,
			@PathParam("numPratica") Integer numPratica);

	public Response respingeRichiesta(@Context HttpServletRequest httpRequest,
			@HeaderParam("X-Request-ID") String xRequestId, UpdatePratica pratica);

	public Response richiedeIntegrazione(@Context HttpServletRequest httpRequest,
			@HeaderParam("X-Request-ID") String xRequestId, UpdatePratica pratica);

	public Response accettaRichiesta(@Context HttpServletRequest httpRequest,
			@HeaderParam("X-Request-ID") String xRequestId, AccettaRichiesta richiesta);

	public Response chiudeRichiesta(@Context HttpServletRequest httpRequest,
			@HeaderParam("X-Request-ID") String xRequestId, ChiudeRichiesta pratica);

	public Response aggiungeNotaInterna(@Context HttpServletRequest httpRequest,
			@HeaderParam("X-Request-ID") String xRequestId, NotaInterna nota);

	public Response storicoPraticaGet(@Context HttpServletRequest httpRequest,
			@HeaderParam("X-Request-ID") String xRequestId, Integer numPratica_Selezionata);

	public Response downloadAllegatoPratica(@Context HttpServletRequest httpRequest, 
			@HeaderParam("X-Request-ID") String xRequestId, 
			@PathParam("numPratica") Integer numPratica,
			@QueryParam("pkFile") Integer pkFile);
	
	public Response downloadExportRicerca(@Context HttpServletRequest httpRequest, 
			@HeaderParam("X-Request-ID") String xRequestId,
			RicercaRichiesteInput input);
}
