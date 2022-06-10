/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevbe.business.be.impl;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.SecurityContext;

import it.csi.def.opessanws.opessan.SoggettoOpessanBody;
import it.csi.scerev.scerevbe.business.SpringApplicationContextHelper;
import it.csi.scerev.scerevbe.business.be.CittadiniApi;
import it.csi.scerev.scerevbe.business.facade.interfaces.AuditFacadeIf;
import it.csi.scerev.scerevbe.business.facade.interfaces.CittadinoFacadeIf;
import it.csi.scerev.scerevbe.business.facade.interfaces.ControlloFacadeIf;
import it.csi.scerev.scerevbe.business.facade.interfaces.ListeFacadeIf;
import it.csi.scerev.scerevbe.business.facade.interfaces.MedicoFacadeIf;
import it.csi.scerev.scerevbe.business.facade.interfaces.MessaggioFacadeIf;
import it.csi.scerev.scerevbe.business.facade.interfaces.PraticaFacadeIf;
import it.csi.scerev.scerevbe.business.serviziesterni.ServiziEsterniClient;
import it.csi.scerev.scerevbe.dto.Errore;
import it.csi.scerev.scerevbe.dto.ErroreDettaglio;
import it.csi.scerev.scerevbe.dto.MedicoMonitoratoCittadino;
import it.csi.scerev.scerevbe.dto.ModelAmbulatorioLoccsiMedico;
import it.csi.scerev.scerevbe.dto.ModelCambioMedicoRichiesta;
import it.csi.scerev.scerevbe.dto.ModelCittadinoInfoCustom;
import it.csi.scerev.scerevbe.dto.ModelControlloAllegati;
import it.csi.scerev.scerevbe.dto.ModelControlloCombinazione;
import it.csi.scerev.scerevbe.dto.ModelMessaggio;
import it.csi.scerev.scerevbe.dto.Payload;
import it.csi.scerev.scerevbe.dto.Payload1;
import it.csi.scerev.scerevbe.dto.Payload2;
import it.csi.scerev.scerevbe.dto.Payload3;
import it.csi.scerev.scerevbe.dto.Payload4;
import it.csi.scerev.scerevbe.dto.PayloadRevocaMedico;
import it.csi.scerev.scerevbe.util.Constants;
import it.csi.scerev.scerevjpa.business.entity.ScerevLAudit;
import it.csi.scerev.scerevjpa.business.entity.ScerevTFile;
import it.csi.scerev.scerevjpa.business.entity.ScerevTMonitoraggio;
import it.csi.scerev.scerevserviziesterni.aura.find.DatiAnagrafici;
import it.csi.scerev.scerevserviziesterni.aura.get.SoggettoAuraBodyNew;
import it.csi.scerev.scerevserviziesterni.aura.setprofilosanitario.ProfiloSanitario;
import it.csi.scerev.scerevserviziesterni.aura.setprofilosanitario.ProfiloSanitarioRes;
import it.csi.scerev.scerevutil.business.Checker;
import it.csi.scerev.scerevutil.business.SharedConstants;
import it.csi.scerev.scerevutil.business.Util;
import it.csi.scerev.scerevutil.business.exception.AllegatoNonFirmatoException;
import it.csi.scerev.scerevutil.business.exception.AllegatoNotFoundException;
import it.csi.scerev.scerevutil.business.exception.AuraException;
import it.csi.scerev.scerevutil.business.exception.AuraNotFoundException;
import it.csi.scerev.scerevutil.business.exception.DomainException;
import it.csi.scerev.scerevutil.business.exception.IntegritaException;
import it.csi.scerev.scerevutil.business.exception.ParametriObbligatoriException;

public class CittadiniApiServiceImpl implements CittadiniApi {

	private final static Logger log = Logger.getLogger(CittadiniApiServiceImpl.class.getName());

//	@Path("/{cit_id}/assistenza")
	public Response cittadiniCitIdAssistenzaDelete(String shibIdentitaCodiceFiscale, String xRequestId,
			String xForwardedFor, String xCodiceServizio, String citId, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return null;
	}

	public Response cittadiniCitIdCambioMedicoRichiestaPost(String shibIdentitaCodiceFiscale,
			@HeaderParam("X-Request-ID") String xRequestId,
			@HeaderParam("X-Forwarded-For") String xForwardedFor,
			@HeaderParam("X-Codice-Servizio") String xCodiceServizio,
			String citId, Payload1 payload, SecurityContext securityContext, 
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		
		final String idApp = Constants.COMPONENT_NAME;
		final String ipAddress = xForwardedFor;
		final String utente = shibIdentitaCodiceFiscale == null ? Constants.COMPONENT_NAME : shibIdentitaCodiceFiscale;
		final String utenteDel = (shibIdentitaCodiceFiscale == null || shibIdentitaCodiceFiscale.equalsIgnoreCase(citId)) ? null : shibIdentitaCodiceFiscale;
		final String utenteBen = citId == null ? Constants.COMPONENT_NAME : citId;
		final String operazione = SharedConstants.OPERAZIONE_POST;
		final String oggOper = new Object(){}.getClass().getEnclosingMethod().getName();
		final String idRequest = xRequestId;
		final String codServizio = xCodiceServizio;

		AuditFacadeIf auditFacade = (AuditFacadeIf) SpringApplicationContextHelper
				.getBean(SharedConstants.AUDIT_FACADE);
		ScerevLAudit audit = null;
		try {
			log.info(oggOper + " " + payload);
			// 1. Tracciatura della chiamata
			audit = new ScerevLAudit(idApp, ipAddress, utente, utenteDel, utenteBen, operazione, oggOper + " - request",
					null, idRequest, codServizio);
			auditFacade.insertAudit(audit);

			ControlloFacadeIf controlloFacade = (ControlloFacadeIf) SpringApplicationContextHelper
					.getBean(SharedConstants.CONTROLLO_FACADE);
			controlloFacade.chkDatiObbligatoriRichiestaNuovoMedico(shibIdentitaCodiceFiscale, xRequestId, xForwardedFor,
					xCodiceServizio, citId, payload);
			controlloFacade.chkCongruitaInputCitIdShib(citId, shibIdentitaCodiceFiscale);
			// controlloFacade.chkCongruitaInput(payload);

			PraticaFacadeIf praticaFacade = (PraticaFacadeIf) SpringApplicationContextHelper
					.getBean(SharedConstants.PRATICA_FACADE);
			praticaFacade.chkPraticaPendente(citId);
			ModelCambioMedicoRichiesta output = praticaFacade.elaboraRichiesta(payload, audit, citId,
					shibIdentitaCodiceFiscale);
			return Response.status(Status.OK).header(Constants.X_REQUEST_ID, "" + xRequestId).entity(output).build();
		} catch (final Exception e) {
			e.printStackTrace();
			String errorCode = Constants.ERROR_500_RICHIESTANUOVOMEDICO;
			return genericCatchException(e, errorCode, audit, oggOper);
		}
	}

	// stampaRichiesta
	public Response cittadiniCitIdCambioMedicoRichiestaRichiestaIdPdfGet(
			@HeaderParam("Shib-Identita-CodiceFiscale") String shibIdentitaCodiceFiscale,
			@HeaderParam("X-Request-ID") String xRequestId,
			@HeaderParam("X-Forwarded-For") String xForwardedFor,
			@HeaderParam("X-Codice-Servizio") String xCodiceServizio,
			String citId, String richiestaId, String contentDisposition,
			SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		
		final String idApp = Constants.COMPONENT_NAME;
		final String ipAddress = xForwardedFor;
		final String utente = shibIdentitaCodiceFiscale == null ? Constants.COMPONENT_NAME : shibIdentitaCodiceFiscale;
		final String utenteDel = (shibIdentitaCodiceFiscale == null || shibIdentitaCodiceFiscale.equalsIgnoreCase(citId)) ? null : shibIdentitaCodiceFiscale;
		final String utenteBen = citId == null ? Constants.COMPONENT_NAME : citId;
		final String operazione = SharedConstants.OPERAZIONE_GET;
		final String oggOper = new Object(){}.getClass().getEnclosingMethod().getName();
		final String idRequest = xRequestId;
		final String codServizio = xCodiceServizio;

		AuditFacadeIf auditFacade = (AuditFacadeIf) SpringApplicationContextHelper
				.getBean(SharedConstants.AUDIT_FACADE);
		ScerevLAudit audit = null;
		try {
			// 1. Tracciatura della chiamata
			audit = new ScerevLAudit(idApp, ipAddress, utente, utenteDel, utenteBen, operazione, oggOper + " - request",
					null, idRequest, codServizio);
			auditFacade.insertAudit(audit);

			ControlloFacadeIf controlloFacade = (ControlloFacadeIf) SpringApplicationContextHelper
					.getBean(SharedConstants.CONTROLLO_FACADE);
			controlloFacade.chkDatiObbligatoriInformazioniAssistito(shibIdentitaCodiceFiscale, xRequestId,
					xForwardedFor, xCodiceServizio, citId);
			controlloFacade.chkCongruitaInput(citId, richiestaId, shibIdentitaCodiceFiscale);

			CittadinoFacadeIf cittadinoFacade = (CittadinoFacadeIf) SpringApplicationContextHelper
					.getBean(SharedConstants.CITTADINO_FACADE);
			byte[] pdf = cittadinoFacade.stampaRichiesta_getFile(richiestaId, audit);

			// 4. Tracciatura risposta
			audit = new ScerevLAudit(idApp, ipAddress, utente, utenteDel, utenteBen, operazione,
					oggOper + " - response", SharedConstants.KEY_OPER_OK, idRequest, codServizio);
			auditFacade.insertAudit(audit);

			return Response.status(Status.OK)
					.header("Content-Disposition",
							"attachment; filename=Richiesta" + System.currentTimeMillis() + ".pdf")
					.header("Access-Control-Expose-Headers", "Content-Disposition")
					.header(Constants.X_REQUEST_ID, "" + xRequestId).entity(pdf).build();

		} catch (final Exception e) {
			e.printStackTrace();
			String errorCode = SharedConstants.ERROR_CODE_INTERNO_SERVER;
			return genericCatchException(e, errorCode, audit, oggOper);
		}
	}

	public Response cittadiniCitIdCambioMedicoRichiestaRichiestaIdRettificaPost(
			@HeaderParam("Shib-Identita-CodiceFiscale") String shibIdentitaCodiceFiscale,
			@HeaderParam("X-Request-Id") String xRequestId, @HeaderParam("X-Forwarded-For") String xForwardedFor,
			@HeaderParam("X-Codice-Servizio") String xCodiceServizio, @PathParam("cit_id") String citId,
			@PathParam("richiesta_id") String richiestaId, Payload2 payload, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest) {
		
		final String idApp = Constants.COMPONENT_NAME;
		final String ipAddress = xForwardedFor;
		final String utente = shibIdentitaCodiceFiscale == null ? Constants.COMPONENT_NAME : shibIdentitaCodiceFiscale;
		final String utenteDel = (shibIdentitaCodiceFiscale == null || shibIdentitaCodiceFiscale.equalsIgnoreCase(citId)) ? null : shibIdentitaCodiceFiscale;
		final String utenteBen = citId == null ? Constants.COMPONENT_NAME : citId;
		final String operazione = SharedConstants.OPERAZIONE_POST;
		final String oggOper = new Object(){}.getClass().getEnclosingMethod().getName();
		final String idRequest = xRequestId;
		final String codServizio = xCodiceServizio;

		AuditFacadeIf auditFacade = (AuditFacadeIf) SpringApplicationContextHelper
				.getBean(SharedConstants.AUDIT_FACADE);
		ScerevLAudit audit = null;
		try {
			log.info(oggOper + " " + payload);

			// 1. Tracciatura della chiamata
			audit = new ScerevLAudit(idApp, ipAddress, utente, utenteDel, utenteBen, operazione, oggOper + " - request",
					null, idRequest, codServizio);
			auditFacade.insertAudit(audit);

			ControlloFacadeIf controlloFacade = (ControlloFacadeIf) SpringApplicationContextHelper
					.getBean(SharedConstants.CONTROLLO_FACADE);
			controlloFacade.chkDatiObbligatoriRettificaRichiesta(shibIdentitaCodiceFiscale, xRequestId, xForwardedFor,
					xCodiceServizio, citId, richiestaId, payload);
			controlloFacade.chkCongruitaInput(citId, richiestaId, shibIdentitaCodiceFiscale);

			PraticaFacadeIf praticaFacade = (PraticaFacadeIf) SpringApplicationContextHelper
					.getBean(SharedConstants.PRATICA_FACADE);
			ModelCambioMedicoRichiesta output = praticaFacade.rettificaRichiesta(audit, citId, richiestaId, payload);

			// 1. Tracciatura della risposta
			audit = new ScerevLAudit(idApp, ipAddress, utente, utenteDel, utenteBen, operazione,
					oggOper + " - response", SharedConstants.KEY_OPER_OK, idRequest, codServizio);
			auditFacade.insertAudit(audit);
			return Response.status(Status.OK).header(Constants.X_REQUEST_ID, "" + xRequestId).entity(output).build();
		} catch (final Exception e) {
			e.printStackTrace();
			String errorCode = Constants.ERROR_500_GETINFORMAZIONIASSISTITO;
			return genericCatchException(e, errorCode, audit, oggOper);
		}
	}

	// stampaCertificatoRevoca
	public Response cittadiniCitIdCertificatoRevocaPdfGet(String shibIdentitaCodiceFiscale, 
			String xRequestId, String xForwardedFor, String xCodiceServizio, 
			String citId, String contentDisposition, SecurityContext securityContext, 
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		
		final String idApp = Constants.COMPONENT_NAME;
		final String ipAddress = xForwardedFor;
		final String utente = shibIdentitaCodiceFiscale == null ? Constants.COMPONENT_NAME : shibIdentitaCodiceFiscale;
		final String utenteDel = (shibIdentitaCodiceFiscale == null || shibIdentitaCodiceFiscale.equalsIgnoreCase(citId)) ? null : shibIdentitaCodiceFiscale;
		final String utenteBen = citId == null ? Constants.COMPONENT_NAME : citId;
		final String operazione = SharedConstants.OPERAZIONE_GET;
		final String oggOper = new Object(){}.getClass().getEnclosingMethod().getName();
		final String idRequest = xRequestId;
		final String codServizio = xCodiceServizio;
		
		AuditFacadeIf auditFacade = (AuditFacadeIf) SpringApplicationContextHelper
				.getBean(SharedConstants.AUDIT_FACADE);
		ScerevLAudit audit = null;

		try {

			// 1. Tracciatura della chiamata
			audit = new ScerevLAudit(idApp, ipAddress, utente, utenteDel, utenteBen, operazione, oggOper + " - request",
					null, idRequest, codServizio);
			auditFacade.insertAudit(audit);

			// 2. Verifica parametri obbligatori

			ControlloFacadeIf controlloFacade = (ControlloFacadeIf) SpringApplicationContextHelper
					.getBean(SharedConstants.CONTROLLO_FACADE);
			controlloFacade.chkDatiObbligatoriCertificatoRevoca(shibIdentitaCodiceFiscale, xRequestId, xForwardedFor,
					xCodiceServizio, citId);
			controlloFacade.chkCongruitaInputCitIdShib(citId, shibIdentitaCodiceFiscale);

			CittadinoFacadeIf cittadinoFacade = (CittadinoFacadeIf) SpringApplicationContextHelper
					.getBean(SharedConstants.CITTADINO_FACADE);
			byte[] pdf = cittadinoFacade.certificatoRevoca_getFile(citId, audit);

			// 4. Tracciatura risposta
			audit = new ScerevLAudit(idApp, ipAddress, utente, utenteDel, utenteBen, operazione,
					oggOper + " - response", SharedConstants.KEY_OPER_OK, idRequest, codServizio);
			auditFacade.insertAudit(audit);

			return Response.status(Status.OK)
					.header("Content-Disposition",
							"attachment; filename=CertificatoRevoca" + System.currentTimeMillis() + ".pdf")
					.header("Access-Control-Expose-Headers", "Content-Disposition")
					.header(Constants.X_REQUEST_ID, "" + xRequestId).entity(pdf).build();

		} catch (final Exception e) {
			e.printStackTrace();
			String errorCode = Constants.ERROR_500_STAMPACERTIFICATOREVOCA;
			return genericCatchException(e, errorCode, audit, oggOper);
		}
	}

	// controlloCombinazione
	public Response cittadiniCitIdControlloCombinazionePost(
			@HeaderParam("Shib-Identita-CodiceFiscale") String shibIdentitaCodiceFiscale,
			@HeaderParam("X-Request-Id") String xRequestId, @HeaderParam("X-Forwarded-For") String xForwardedFor,
			@HeaderParam("X-Codice-Servizio") String xCodiceServizio, @PathParam("cit_id") String citId,
			Payload payload, @Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest) {

		final String idApp = Constants.COMPONENT_NAME;
		final String ipAddress = xForwardedFor;
		final String utente = shibIdentitaCodiceFiscale == null ? Constants.COMPONENT_NAME : shibIdentitaCodiceFiscale;
		final String utenteDel = (shibIdentitaCodiceFiscale == null || shibIdentitaCodiceFiscale.equalsIgnoreCase(citId)) ? null : shibIdentitaCodiceFiscale;
		final String utenteBen = citId == null ? Constants.COMPONENT_NAME : citId;
		final String operazione = SharedConstants.OPERAZIONE_POST;
		final String oggOper = new Object(){}.getClass().getEnclosingMethod().getName();
		final String idRequest = xRequestId;
		final String codServizio = xCodiceServizio;

		ModelControlloCombinazione response = new ModelControlloCombinazione();

		AuditFacadeIf auditFacade = (AuditFacadeIf) SpringApplicationContextHelper
				.getBean(SharedConstants.AUDIT_FACADE);
		ScerevLAudit audit = null;
		try {
			log.info(oggOper + " " + payload);

			// 5.1.1. Tracciatura della chiamata
			audit = new ScerevLAudit(idApp, ipAddress, utente, utenteDel, utenteBen, operazione, oggOper + " - request",
					null, idRequest, codServizio);
			auditFacade.insertAudit(audit);

			// 5.1.2 Verifica presenza parametri obbligatori/congruità dei dati
			ControlloFacadeIf controlloFacade = (ControlloFacadeIf) SpringApplicationContextHelper
					.getBean(SharedConstants.CONTROLLO_FACADE);
			controlloFacade.chkDatiObbligatoriControlloCombinazione(shibIdentitaCodiceFiscale, xRequestId,
					xForwardedFor, xCodiceServizio, citId, payload);
			controlloFacade.chkCongruitaInput(payload);
			controlloFacade.chkCongruitaInputCitIdShib(citId, shibIdentitaCodiceFiscale);

			Map<DatiAnagrafici, SoggettoAuraBodyNew> auraMap;
			DatiAnagrafici cittadinoFind = null;
			SoggettoAuraBodyNew cittadinoGet = null;
			try {
				auraMap = ServiziEsterniClient.getInstance().anagrafeSanitariaAttiva(
						ServiziEsterniClient.getInstance().findCittadino(citId, null, null, null, audit), audit);
				if(auraMap != null) {
					cittadinoFind = auraMap.keySet().iterator().next();
					cittadinoGet = auraMap.get(cittadinoFind);
				}
			} catch (AuraNotFoundException e) {
				cittadinoFind = null;
				cittadinoGet = null;
			} catch (AuraException e) {
				throw new AuraNotFoundException();
			}

			// 5.1.3 Controllo combinazione
			CittadinoFacadeIf cittadinoFacade = (CittadinoFacadeIf) SpringApplicationContextHelper
					.getBean(SharedConstants.CITTADINO_FACADE);
			
			// Controllo la ricusazione del medico solo se non si tratta di rinnovo assistenza
			boolean ricusato = (payload.getRinnovoAssistenza() != null && payload.getRinnovoAssistenza()) ? 
					false : cittadinoFacade.checkRicusazioneMedico(payload.getMedico(), cittadinoFind);
			if(ricusato) {
				response.setRicusazione(true);
				response.setAmmessa(false);
				response.setDerogaAssociazione(false);
				response.setDerogaTerritoriale(false);
				response.setDerogaPls(false);
				response.setDerogaMmg(false);
				response.setVariazioneAsl(false);
				response.setAslIscrizione(null);
				response.setAslDomicilio(null);
				response.setAslResidenza(null);
			} else {				
				boolean corretto = cittadinoFacade.checkCorrettezzaResidenzaDomicilioAssistenza(payload, citId, audit,
						cittadinoFind, cittadinoGet);
				boolean variazione = cittadinoFacade.checkVariazioneASLResidenzaDomicilio(payload, citId, audit,
						cittadinoFind, cittadinoGet);
				boolean derogaTerritoriale = cittadinoFacade.checkDerogaTerritoriale(payload, citId, audit, cittadinoFind,
						cittadinoGet);
				boolean derogaAssociazione = cittadinoFacade.checkDerogaAssociazione(payload, citId, audit, cittadinoFind,
						cittadinoGet);
				boolean derogaMedicoPLS = cittadinoFacade.checkDerogaMedicoPLS(payload, citId, audit, cittadinoFind);
				boolean derogaMedicoMMG = cittadinoFacade.checkDerogaMedicoMMG(payload, citId, audit, cittadinoFind);

				response.setRicusazione(false);
				response.setAmmessa(corretto);
				response.setDerogaAssociazione(derogaAssociazione);
				response.setDerogaTerritoriale(derogaTerritoriale);
				response.setDerogaPls(derogaMedicoPLS);
				response.setDerogaMmg(derogaMedicoMMG);
				response.setVariazioneAsl(variazione);
				response.setAslIscrizione(cittadinoFacade.calcolaIscrizione(payload));
				response.setAslDomicilio(cittadinoFacade.calcolaDomicilio(payload.getDomicilio(), citId, audit,
						cittadinoFind, cittadinoGet));
				response.setAslResidenza(cittadinoFacade.calcolaResidenza(payload.getResidenza(), citId, audit,
						cittadinoFind, cittadinoGet));
			}

			// 5.1.1. Tracciatura della risposta
			audit = new ScerevLAudit(idApp, ipAddress, utente, utenteDel, utenteBen, operazione,
					oggOper + " - response", SharedConstants.KEY_OPER_OK, idRequest, codServizio);
			auditFacade.insertAudit(audit);

			return Response.status(Status.OK).header(Constants.X_REQUEST_ID, "" + xRequestId).entity(response).build();
		} catch (final Exception e) {
			e.printStackTrace();
			String errorCode = Constants.ERROR_500_CONTROLLOCOMBINAZIONE;
			return genericCatchException(e, errorCode, audit, oggOper);
		}
	}

	// informazioniAssistito
	public Response cittadiniCitIdInfoGet(String shibIdentitaCodiceFiscale,
			@HeaderParam("X-Request-ID") String xRequestId,
			@HeaderParam("X-Forwarded-For") String xForwardedFor,
			@HeaderParam("X-Codice-Servizio") String xCodiceServizio,
			String citId,
			SecurityContext securityContext,
			HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		final String idApp = Constants.COMPONENT_NAME;
		final String ipAddress = xForwardedFor;
		final String utente = shibIdentitaCodiceFiscale == null ? Constants.COMPONENT_NAME : shibIdentitaCodiceFiscale;
		final String utenteDel = (shibIdentitaCodiceFiscale == null || shibIdentitaCodiceFiscale.equalsIgnoreCase(citId)) ? null : shibIdentitaCodiceFiscale;
		final String utenteBen = citId == null ? Constants.COMPONENT_NAME : citId;
		final String operazione = SharedConstants.OPERAZIONE_GET;
		final String oggOper = new Object(){}.getClass().getEnclosingMethod().getName();
		final String idRequest = xRequestId;
		final String codServizio = xCodiceServizio;

		AuditFacadeIf auditFacade = (AuditFacadeIf) SpringApplicationContextHelper
				.getBean(SharedConstants.AUDIT_FACADE);
		ScerevLAudit audit = null;
		try {
			// 1. Tracciatura della chiamata
			audit = new ScerevLAudit(idApp, ipAddress, utente, utenteDel, utenteBen, operazione, oggOper + " - request",
					null, idRequest, codServizio);
			auditFacade.insertAudit(audit);

			ControlloFacadeIf controlloFacade = (ControlloFacadeIf) SpringApplicationContextHelper
					.getBean(SharedConstants.CONTROLLO_FACADE);
			controlloFacade.chkDatiObbligatoriInformazioniAssistito(shibIdentitaCodiceFiscale, xRequestId,
					xForwardedFor, xCodiceServizio, citId);
			controlloFacade.chkCongruitaInputCitIdShib(citId, shibIdentitaCodiceFiscale);

			PraticaFacadeIf praticaFacade = (PraticaFacadeIf) SpringApplicationContextHelper
					.getBean(SharedConstants.PRATICA_FACADE);
			ModelCittadinoInfoCustom output = praticaFacade.getCittadinoInfo(citId, audit);

			// 1. Tracciatura della response
			audit = new ScerevLAudit(idApp, ipAddress, utente, utenteDel, utenteBen, operazione,
					oggOper + " - response", SharedConstants.KEY_OPER_OK, idRequest, codServizio);
			auditFacade.insertAudit(audit);

			return Response.status(Status.OK).header(Constants.X_REQUEST_ID, "" + xRequestId).entity(output).build();
		} catch (final AuraNotFoundException e) {

			final Errore errore = new Errore();
			e.printStackTrace();
			int statusCode = Status.NOT_FOUND.getStatusCode();
			String errorCode = Constants.ERROR_AURA_NF_GETINFORMAZIONIASSISTITO;
			String detail = null;

			MessaggioFacadeIf messaggioFacade = (MessaggioFacadeIf) SpringApplicationContextHelper
					.getBean(SharedConstants.MESSAGGIO_FACADE);
			ModelMessaggio msg = messaggioFacade.getMessaggioPerCodice(errorCode);

			errore.setCode(msg.getCodice());
			errore.setStatus(statusCode);

			errore.setTitle(msg.getDescrizione());

			detail = " - " + Util.composeMessage(msg.getDescrizione(), detail);

			audit = new ScerevLAudit(idApp, ipAddress, utente, utenteDel, utenteBen, operazione,
					oggOper + " - response", SharedConstants.KEY_OPER_KO + " " + detail, idRequest, codServizio);
			auditFacade.insertAudit(audit);

			return Response.status(Status.INTERNAL_SERVER_ERROR).header(Constants.X_REQUEST_ID, "" + xRequestId)
					.entity(errore).build();

		} catch (final Exception e) {
			e.printStackTrace();
			String errorCode = Constants.ERROR_500_GETINFORMAZIONIASSISTITO;
			return genericCatchException(e, errorCode, audit, oggOper);
		}
	}

	// mediciMonitorati
	public Response cittadiniCitIdMediciMonitoratiGet(
			@HeaderParam("Shib-Identita-CodiceFiscale") String shibIdentitaCodiceFiscale,
			@HeaderParam("X-Request-ID") String xRequestId, @HeaderParam("X-Forwarded-For") String xForwardedFor,
			@HeaderParam("X-Codice-Servizio") String xCodiceServizio, @PathParam("cit_id") String citId,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest) {

		AuditFacadeIf auditFacade = null;	

		final String idApp = Constants.ID_APP;
		final String ipAddress = xForwardedFor;
		final String utente = shibIdentitaCodiceFiscale == null ? Constants.COMPONENT_NAME : shibIdentitaCodiceFiscale;
		final String utenteDel = (shibIdentitaCodiceFiscale == null || shibIdentitaCodiceFiscale.equalsIgnoreCase(citId)) ? null : shibIdentitaCodiceFiscale;
		final String utenteBen = citId == null ? Constants.COMPONENT_NAME : citId;
		final String operazione = SharedConstants.OPERAZIONE_GET;
		final String oggOper = new Object(){}.getClass().getEnclosingMethod().getName();
		final String idRequest = xRequestId;
		final String codServizio = xCodiceServizio;
		
		ScerevLAudit audit = new ScerevLAudit(idApp, ipAddress, utente, utenteDel, utenteBen, operazione,
				oggOper + " - request", null, idRequest, codServizio);

		try {
			// Traccio la chiamata
			auditFacade = (AuditFacadeIf) SpringApplicationContextHelper.getBean(SharedConstants.AUDIT_FACADE);
			auditFacade.insertAudit(audit);

			ControlloFacadeIf controlloFacade = (ControlloFacadeIf) SpringApplicationContextHelper
					.getBean(SharedConstants.CONTROLLO_FACADE);
			ListeFacadeIf listeFacade = (ListeFacadeIf) SpringApplicationContextHelper
					.getBean(SharedConstants.LISTE_FACADE);
			
			// Controllo i dati obbligatori
			controlloFacade.chkDatiObbligatoriMediciMonitorati(shibIdentitaCodiceFiscale, xRequestId, xForwardedFor,
					xCodiceServizio, citId);

			// Controllo l'integrità dei dati
			controlloFacade.chkIntegritaMediciMonitorati(shibIdentitaCodiceFiscale, xRequestId, xForwardedFor,
					xCodiceServizio, citId);

			// Ottengo la lista dei medici
			List<MedicoMonitoratoCittadino> list = listeFacade.getMediciMonitoratiByCittadion(citId);

			
			// Traccio la risposta in caso di esito positivo
			audit = new ScerevLAudit(idApp, ipAddress, utente, utenteDel, utenteBen, operazione,
					oggOper + " - response", SharedConstants.KEY_OPER_OK, idRequest, codServizio);
			auditFacade.insertAudit(audit);
			return Response.status(Status.OK).entity(list).header(Constants.X_REQUEST_ID, "" + xRequestId).build();

		} catch (Exception e) {
			e.printStackTrace();
			String errorCode = "ERR62";
			return genericCatchException(e, errorCode, audit, oggOper);
		}
	}

	// rimuoviMedicoMonitorato
	public Response cittadiniCitIdMediciMonitoratiMedicoIdDelete(
			@HeaderParam("Shib-Identita-CodiceFiscale") String shibIdentitaCodiceFiscale,
			@HeaderParam("X-Request-Id") String xRequestId, @HeaderParam("X-Forwarded-For") String xForwardedFor,
			@HeaderParam("X-Codice-Servizio") String xCodiceServizio, @PathParam("cit_id") String citId,
			@PathParam("medico_id") String medicoId, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest) {

		Errore errore = new Errore();
		AuditFacadeIf auditFacade = null;

		final String idApp = Constants.ID_APP;
		final String ipAddress = xForwardedFor;
		final String utente = shibIdentitaCodiceFiscale == null ? Constants.COMPONENT_NAME : shibIdentitaCodiceFiscale;
		final String utenteDel = (shibIdentitaCodiceFiscale == null || shibIdentitaCodiceFiscale.equalsIgnoreCase(citId)) ? null : shibIdentitaCodiceFiscale;
		final String utenteBen = citId == null ? Constants.COMPONENT_NAME : citId;
		final String operazione = SharedConstants.OPERAZIONE_DELETE;
		final String oggOper = new Object(){}.getClass().getEnclosingMethod().getName();
		final String idRequest = xRequestId;
		final String codServizio = xCodiceServizio;
		
		ScerevLAudit audit = new ScerevLAudit(idApp, ipAddress, utente, utenteDel, utenteBen, operazione,
				oggOper + " - request", null, idRequest, codServizio);

		try {
			// Traccio la chiamata
			auditFacade = (AuditFacadeIf) SpringApplicationContextHelper.getBean(SharedConstants.AUDIT_FACADE);
			auditFacade.insertAudit(audit);
					
			ControlloFacadeIf controlloFacade = (ControlloFacadeIf) SpringApplicationContextHelper
					.getBean(SharedConstants.CONTROLLO_FACADE);
			CittadinoFacadeIf cittadinoFacade = (CittadinoFacadeIf) SpringApplicationContextHelper
					.getBean(SharedConstants.CITTADINO_FACADE);
			MessaggioFacadeIf messaggioFacade = (MessaggioFacadeIf) SpringApplicationContextHelper
					.getBean(SharedConstants.MESSAGGIO_FACADE);

			// Controllo dati obbligatori: si può riprendere un vecchio checker, si ha solo
			controlloFacade.chkDatiObbligatoriMediciMonitorati(shibIdentitaCodiceFiscale, xRequestId, xForwardedFor,
					xCodiceServizio, citId);
			if (!Checker.isValorizzato(medicoId)) {
				throw new ParametriObbligatoriException("Id medico");
			}

			// Integrità dati
			controlloFacade.chkIntegritaMediciMonitorati(shibIdentitaCodiceFiscale, xRequestId, xForwardedFor,
					xCodiceServizio, citId);

			// Recupero il medico monitorato
			ScerevTMonitoraggio med = cittadinoFacade.getMedicoMonitoratoByShib(citId, medicoId);

			// Se non trovo niente restituisco questo
			if (med == null) {
				ModelMessaggio msg = messaggioFacade.getMessaggioPerCodice(Constants.ERROR_404_NOT_FOUND);
				errore.setStatus(Status.NOT_FOUND.getStatusCode());
				errore.setCode(msg.getCodice());
				errore.setTitle(msg.getDescrizione());
				audit = new ScerevLAudit(idApp, ipAddress, utente, utenteDel, utenteBen, operazione,
						oggOper + " - response", SharedConstants.KEY_OPER_KO + ": " + errore.getTitle(), idRequest,
						codServizio);
				auditFacade.insertAudit(audit);
				return Response.status(Status.NOT_FOUND).entity(errore)
						.header(Constants.X_REQUEST_ID, "" + xRequestId).build();
			}

			ModelMessaggio msg = messaggioFacade.getMessaggioPerCodice("MSG33");
			String risposta = msg.getDescrizione().replaceAll("<nome e cognome>",
					med.getNomeMedico() + " " + med.getCognomeMedico());

			// Rimuovo il medico
			cittadinoFacade.rimuoviMedicoMonitorato(med);

			// Traccio la risposta in caso di esito positivo
			audit = new ScerevLAudit(idApp, ipAddress, utente, utenteDel, utenteBen, operazione,
					oggOper + " - response", SharedConstants.KEY_OPER_OK, idRequest, codServizio);
			auditFacade.insertAudit(audit);
			return Response.status(Status.OK).entity(risposta).header(Constants.X_REQUEST_ID, "" + xRequestId).build();

		} catch (Exception e) {
			e.printStackTrace();
			String errorCode = "ERR62";
			return genericCatchException(e, errorCode, audit, oggOper);			
		}//
	}

	// monitoraMedico
	public Response cittadiniCitIdMediciMonitoratiPost(
			@HeaderParam("Shib-Identita-CodiceFiscale") String shibIdentitaCodiceFiscale,
			@HeaderParam("X-Request-Id") String xRequestId, @HeaderParam("X-Forwarded-For") String xForwardedFor,
			@HeaderParam("X-Codice-Servizio") String xCodiceServizio, @PathParam("cit_id") String citId,
			ModelAmbulatorioLoccsiMedico payload, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest) {

		Errore errore = new Errore();
		AuditFacadeIf auditFacade = null;

		final String idApp = Constants.ID_APP;
		final String ipAddress = xForwardedFor;
		final String utente = shibIdentitaCodiceFiscale == null ? Constants.COMPONENT_NAME : shibIdentitaCodiceFiscale;
		final String utenteDel = (shibIdentitaCodiceFiscale == null || shibIdentitaCodiceFiscale.equalsIgnoreCase(citId)) ? null : shibIdentitaCodiceFiscale;
		final String utenteBen = citId == null ? Constants.COMPONENT_NAME : citId;
		final String operazione = SharedConstants.OPERAZIONE_POST;
		final String oggOper = new Object(){}.getClass().getEnclosingMethod().getName();
		final String idRequest = xRequestId;
		final String codServizio = xCodiceServizio;
		
		ScerevLAudit audit = new ScerevLAudit(idApp, ipAddress, utente, utenteDel, utenteBen, operazione,
				oggOper + " - request", null, idRequest, codServizio);

		try {
			
			// Traccio la chiamata
			auditFacade = (AuditFacadeIf) SpringApplicationContextHelper.getBean(SharedConstants.AUDIT_FACADE);
			auditFacade.insertAudit(audit);

			ControlloFacadeIf controlloFacade = (ControlloFacadeIf) SpringApplicationContextHelper
					.getBean(SharedConstants.CONTROLLO_FACADE);
			CittadinoFacadeIf cittadinoFacade = (CittadinoFacadeIf) SpringApplicationContextHelper
					.getBean(SharedConstants.CITTADINO_FACADE);
			MessaggioFacadeIf messaggioFacade = (MessaggioFacadeIf) SpringApplicationContextHelper
					.getBean(SharedConstants.MESSAGGIO_FACADE);

			// Controllo dati obbligatori
			controlloFacade.chkDatiObbligatoriMediciMonitorati(shibIdentitaCodiceFiscale, xRequestId, xForwardedFor,
					xCodiceServizio, citId);
			if(payload==null)
				throw new ParametriObbligatoriException("Payload");
					
			// Integrità dati
			controlloFacade.chkIntegritaMediciMonitorati(shibIdentitaCodiceFiscale, xRequestId, xForwardedFor,
					xCodiceServizio, citId);
			
			// Controllo se il medico è stato già monitorato
			if (cittadinoFacade.chkPresenzaMedicoMonitorato(shibIdentitaCodiceFiscale, payload.getId())) {
				ModelMessaggio msg = messaggioFacade.getMessaggioPerCodice(Constants.ERROR_MEDICO_GIA_MONITORATO);
				errore.setCode(msg.getCodice());
				errore.setTitle(msg.getDescrizione());
				errore.setStatus(Status.BAD_REQUEST.getStatusCode());

				// Traccio la risposta per questa eventualità
				audit = new ScerevLAudit(idApp, ipAddress, utente, utenteDel, utenteBen, operazione,
						oggOper + " - response", SharedConstants.KEY_OPER_KO + ":" + " " + errore.getTitle(), idRequest,
						codServizio);
				auditFacade.insertAudit(audit);
				return Response.status(Status.BAD_REQUEST).entity(errore)
						.header(Constants.X_REQUEST_ID, "" + xRequestId).build();
			}

			// Monitora il medico
			cittadinoFacade.monitoraMedico(shibIdentitaCodiceFiscale, xRequestId, xForwardedFor, xCodiceServizio, citId,
					payload);

			ModelMessaggio msg = messaggioFacade.getMessaggioPerCodice("MSG32");
			
			String risposta = msg.getDescrizione().replaceAll("<nome e cognome>",
					payload.getNome() + " " + payload.getCognome());

			// Traccio la risposta con esito positivo
			audit = new ScerevLAudit(idApp, ipAddress, utente, utenteDel, utenteBen, operazione,
					oggOper + " - response", SharedConstants.KEY_OPER_OK, idRequest, codServizio);
			auditFacade.insertAudit(audit);
			return Response.status(Status.OK).entity(risposta).header(Constants.X_REQUEST_ID, "" + xRequestId).build();
		} catch (Exception e) {
			e.printStackTrace();
			String errorCode = "ERR62";
			return genericCatchException(e, errorCode, audit, oggOper);
		}

	}

	// revocaMedico
	public Response cittadiniCitIdRevocaMedicoPost(String shibIdentitaCodiceFiscale, String xRequestId,
			String xForwardedFor, String xCodiceServizio, String citId, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest, PayloadRevocaMedico payloadRevocaMedico) {
		final String idApp = Constants.COMPONENT_NAME;
		final String ipAddress = xForwardedFor;
		final String utente = shibIdentitaCodiceFiscale == null ? Constants.COMPONENT_NAME : shibIdentitaCodiceFiscale;
		final String utenteDel = (shibIdentitaCodiceFiscale == null || shibIdentitaCodiceFiscale.equalsIgnoreCase(citId)) ? null : shibIdentitaCodiceFiscale;
		final String utenteBen = citId == null ? Constants.COMPONENT_NAME : citId;
		final String operazione = SharedConstants.OPERAZIONE_POST;
		final String oggOper = new Object(){}.getClass().getEnclosingMethod().getName();
		final String idRequest = xRequestId;
		final String codServizio = xCodiceServizio;

		String messageReturn;

		AuditFacadeIf auditFacade = (AuditFacadeIf) SpringApplicationContextHelper
				.getBean(SharedConstants.AUDIT_FACADE);
		ScerevLAudit audit = null;
		try {
			// 5.1.2. Tracciatura della chiamata
			audit = new ScerevLAudit(idApp, ipAddress, utente, utenteDel, utenteBen, operazione, oggOper + " - request",
					null, idRequest, codServizio);
			auditFacade.insertAudit(audit);

			// 5.1.3 Verifica presenza parametri obbligatori/congruità dei dati
			ControlloFacadeIf controlloFacade = (ControlloFacadeIf) SpringApplicationContextHelper
					.getBean(SharedConstants.CONTROLLO_FACADE);
			controlloFacade.chkDatiObbligatoriRevocaMedico(shibIdentitaCodiceFiscale, xRequestId, xForwardedFor,
					xCodiceServizio, citId, payloadRevocaMedico.getMotivoRevoca());
			controlloFacade.chkCongruitaInputCitIdShib(citId, shibIdentitaCodiceFiscale);
			
			// 5.1.4 Connessione ad Aura
			DatiAnagrafici cittadino = null;
			SoggettoAuraBodyNew sog = null;
			Map<DatiAnagrafici, SoggettoAuraBodyNew> auraMap = ServiziEsterniClient.getInstance().anagrafeSanitariaAttiva(
						ServiziEsterniClient.getInstance().findCittadino(citId, null, null, null, audit), audit);
			if(auraMap != null) {
				cittadino = auraMap.keySet().iterator().next();
				sog = auraMap.get(cittadino);
			} else throw new AuraNotFoundException();

			// 14/12/2020
			// Connessione a OPESSAN ser getDettaglioOperatore
			SoggettoOpessanBody detOp = ServiziEsterniClient.getInstance().getDettaglioOperatore(sog.getInfoSan().getIdMedico().toString(), audit);
			
			// Aggiornamento profilo sanitario su AURA
			MedicoFacadeIf medicoFacade = (MedicoFacadeIf) SpringApplicationContextHelper
					.getBean(SharedConstants.MEDICO_FACADE);
			ProfiloSanitario profSan = medicoFacade.costruisciReqSetProfiloSanitario(citId, sog,
					SharedConstants.REVOCA, payloadRevocaMedico.getMotivoRevoca());
			ProfiloSanitarioRes out = ServiziEsterniClient.getInstance().setProfiloSanitario(profSan, audit);
			if (out.getCodiceRitorno() == null || !out.getCodiceRitorno().equals("1"))
				throw new AuraException(SharedConstants.ERROR_CODE_AURA_INSERTUPDATE_PROFILOANAGRAFICO);

			// 5.1.5 Aggiornamento pratica
			CittadinoFacadeIf cittadinoFacade = (CittadinoFacadeIf) SpringApplicationContextHelper
					.getBean(SharedConstants.CITTADINO_FACADE);
			messageReturn = cittadinoFacade.revocaMedico(sog, detOp, citId, !citId.equalsIgnoreCase(shibIdentitaCodiceFiscale) ? shibIdentitaCodiceFiscale : null, payloadRevocaMedico.getMotivoRevoca());

			// 5.1.6 Tracciatura della risposta
			audit = new ScerevLAudit(idApp, ipAddress, utente, utenteDel, utenteBen, operazione,
					oggOper + " - response", messageReturn, idRequest, codServizio);
			auditFacade.insertAudit(audit);

			return Response.status(Status.OK).header(Constants.X_REQUEST_ID, "" + xRequestId).entity(messageReturn)
					.build();
		} catch (final Exception e) {
			e.printStackTrace();
			String errorCode = Constants.ERROR_500_REVOCAMEDICO;
			return genericCatchException(e, errorCode, audit, oggOper);
		}
	}

	// stampaModuloDelegante
	public Response cittadiniCitIdModuloDelegantePdfPost(String shibIdentitaCodiceFiscale, String xRequestId,
			String xForwardedFor, String xCodiceServizio, String citId, Payload3 payload, String contentDisposition,
			SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {

		final String idApp = Constants.COMPONENT_NAME;
		final String ipAddress = xForwardedFor;
		final String utente = shibIdentitaCodiceFiscale == null ? Constants.COMPONENT_NAME : shibIdentitaCodiceFiscale;
		final String utenteDel = (shibIdentitaCodiceFiscale == null || shibIdentitaCodiceFiscale.equalsIgnoreCase(citId)) ? null : shibIdentitaCodiceFiscale;
		final String utenteBen = citId == null ? Constants.COMPONENT_NAME : citId;
		final String operazione = SharedConstants.OPERAZIONE_GET;
		final String oggOper = new Object(){}.getClass().getEnclosingMethod().getName();
		final String idRequest = xRequestId;
		final String codServizio = xCodiceServizio;

		AuditFacadeIf auditFacade = (AuditFacadeIf) SpringApplicationContextHelper
				.getBean(SharedConstants.AUDIT_FACADE);
		ScerevLAudit audit = null;
		try {

			// 1. Tracciatura della chiamata
			audit = new ScerevLAudit(idApp, ipAddress, utente, utenteDel, utenteBen, operazione, oggOper + " - request",
					null, idRequest, codServizio);
			auditFacade.insertAudit(audit);

			// 2. Verifica parametri obbligatori

			ControlloFacadeIf controlloFacade = (ControlloFacadeIf) SpringApplicationContextHelper
					.getBean(SharedConstants.CONTROLLO_FACADE);
			controlloFacade.chkDatiObbligatoriModuloDelegante(shibIdentitaCodiceFiscale, xRequestId, xForwardedFor,
					xCodiceServizio, citId, payload);
			controlloFacade.chkCongruitaInputCitIdShib(citId, shibIdentitaCodiceFiscale);

			CittadinoFacadeIf cittadinoFacade = (CittadinoFacadeIf) SpringApplicationContextHelper
					.getBean(SharedConstants.CITTADINO_FACADE);
			byte[] pdf = cittadinoFacade.stampaModuloDelegante_getFile(citId, payload, audit);

			// 4. Tracciatura risposta
			audit = new ScerevLAudit(idApp, ipAddress, utente, utenteDel, utenteBen, operazione,
					oggOper + " - response", SharedConstants.KEY_OPER_OK, idRequest, codServizio);
			auditFacade.insertAudit(audit);

			return Response.status(Status.OK)
					.header("Content-Disposition",
							"attachment; filename=ModuloDelegante" + System.currentTimeMillis() + ".pdf")
					.header("Access-Control-Expose-Headers", "Content-Disposition")
					.header("Content-Type", "application/pdf").header(Constants.X_REQUEST_ID, "" + xRequestId)
					.entity(pdf).build();
		} catch (final Exception e) {
			e.printStackTrace();
			String errorCode = Constants.ERROR_500_STAMPAMODULODELEGANTE;
			return genericCatchException(e, errorCode, audit, oggOper);
		}
	}

	// stampaTesserinoMedico
	public Response cittadiniCitIdTesserinoMedicoPdfGet(String shibIdentitaCodiceFiscale, String xRequestId,
			String xForwardedFor, String xCodiceServizio, String citId, String contentDisposition,
			SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {

		final String idApp = Constants.COMPONENT_NAME;
		final String ipAddress = xForwardedFor;
		final String utente = shibIdentitaCodiceFiscale == null ? Constants.COMPONENT_NAME : shibIdentitaCodiceFiscale;
		final String utenteDel = (shibIdentitaCodiceFiscale == null || shibIdentitaCodiceFiscale.equalsIgnoreCase(citId)) ? null : shibIdentitaCodiceFiscale;
		final String utenteBen = citId == null ? Constants.COMPONENT_NAME : citId;
		final String operazione = SharedConstants.OPERAZIONE_GET;
		final String oggOper = new Object(){}.getClass().getEnclosingMethod().getName();
		final String idRequest = xRequestId;
		final String codServizio = xCodiceServizio;

		AuditFacadeIf auditFacade = (AuditFacadeIf) SpringApplicationContextHelper
				.getBean(SharedConstants.AUDIT_FACADE);
		ScerevLAudit audit = null;
		try {

			// 1. Tracciatura della chiamata
			audit = new ScerevLAudit(idApp, ipAddress, utente, utenteDel, utenteBen, operazione, oggOper + " - request",
					null, idRequest, codServizio);
			auditFacade.insertAudit(audit);

			// 2. Verifica parametri obbligatori e congruità

			ControlloFacadeIf controlloFacade = (ControlloFacadeIf) SpringApplicationContextHelper
					.getBean(SharedConstants.CONTROLLO_FACADE);
			controlloFacade.chkDatiObbligatoriTesserinoMedico(shibIdentitaCodiceFiscale, xRequestId, xForwardedFor,
					xCodiceServizio, citId);
			controlloFacade.chkCongruitaInputCitIdShib(citId, shibIdentitaCodiceFiscale);

			CittadinoFacadeIf cittadinoFacade = (CittadinoFacadeIf) SpringApplicationContextHelper
					.getBean(SharedConstants.CITTADINO_FACADE);
			byte[] pdf = cittadinoFacade.tesserinoMedico_getFile(citId, audit);

			// 4. Tracciatura risposta
			audit = new ScerevLAudit(idApp, ipAddress, utente, utenteDel, utenteBen, operazione,
					oggOper + " - response", SharedConstants.KEY_OPER_OK, idRequest, codServizio);
			auditFacade.insertAudit(audit);

			return Response.status(Status.OK)
					.header("Content-Disposition",
							"attachment; filename=TesserinoMedico" + System.currentTimeMillis() + ".pdf")
					.header("Access-Control-Expose-Headers", "Content-Disposition")
					.header("Content-Type", "application/pdf").header(Constants.X_REQUEST_ID, "" + xRequestId)
					.entity(pdf).build();
		} catch (final Exception e) {
			e.printStackTrace();
			String errorCode = Constants.ERROR_500_STAMPATESSERINOMEDICO;
			return genericCatchException(e, errorCode, audit, oggOper);
		}
	}

	// annullaRichiesta/RimuoviBozza
	public Response cittadiniCitIdInfoRichiestaCambioRichiestaIdDelete(
			@HeaderParam("Shib-Identita-CodiceFiscale") String shibIdentitaCodiceFiscale,
			@HeaderParam("X-Request-Id") String xRequestId, @HeaderParam("X-Forwarded-For") String xForwardedFor,
			@HeaderParam("X-Codice-Servizio") String xCodiceServizio, @PathParam("cit_id") String citId,
			@PathParam("richiestaId") String richiestaId, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest) {
		final String idApp = Constants.COMPONENT_NAME;
		final String ipAddress = xForwardedFor;
		final String utente = shibIdentitaCodiceFiscale == null ? Constants.COMPONENT_NAME : shibIdentitaCodiceFiscale;
		final String utenteDel = (shibIdentitaCodiceFiscale == null || shibIdentitaCodiceFiscale.equalsIgnoreCase(citId)) ? null : shibIdentitaCodiceFiscale;
		final String utenteBen = citId == null ? Constants.COMPONENT_NAME : citId;
		final String operazione = SharedConstants.OPERAZIONE_DELETE;
		final String oggOper = new Object(){}.getClass().getEnclosingMethod().getName();
		final String idRequest = xRequestId;
		final String codServizio = xCodiceServizio;

		String messageReturn;

		AuditFacadeIf auditFacade = (AuditFacadeIf) SpringApplicationContextHelper
				.getBean(SharedConstants.AUDIT_FACADE);
		ScerevLAudit audit = null;
		try {
			// 5.1.1. Tracciatura della chiamata
			audit = new ScerevLAudit(idApp, ipAddress, utente, utenteDel, utenteBen, operazione, oggOper + " - request",
					null, idRequest, codServizio);
			auditFacade.insertAudit(audit);

			// 5.1.2 Verifica presenza parametri obbligatori/congruità dei dati
			ControlloFacadeIf controlloFacade = (ControlloFacadeIf) SpringApplicationContextHelper
					.getBean(SharedConstants.CONTROLLO_FACADE);
			controlloFacade.chkDatiObbligatoriAnnullaRichiesta(shibIdentitaCodiceFiscale, xRequestId, xForwardedFor,
					xCodiceServizio, citId);
			controlloFacade.chkCongruitaInputCitIdShib(citId, shibIdentitaCodiceFiscale);

			// 5.1.3 Aggiorna pratica
			CittadinoFacadeIf cittadinoFacade = (CittadinoFacadeIf) SpringApplicationContextHelper
					.getBean(SharedConstants.CITTADINO_FACADE);
			messageReturn = cittadinoFacade.annullaRimuoviPratica(richiestaId);

			// 5.1.1. Tracciatura della risposta
			audit = new ScerevLAudit(idApp, ipAddress, utente, utenteDel, utenteBen, operazione,
					oggOper + " - response", messageReturn, idRequest, codServizio);
			auditFacade.insertAudit(audit);

			return Response.status(Status.OK).header(Constants.X_REQUEST_ID, "" + xRequestId).entity(messageReturn)
					.build();
		} catch (final Exception e) {
			e.printStackTrace();
			String errorCode = Constants.ERROR_500_ANNULLARICHIESTARIMUOVIBOZZA;
			return genericCatchException(e, errorCode, audit, oggOper);
		}
	}

	@Override
	public Response cittadiniCitIdControlloAllegatiPost(String shibIdentitaCodiceFiscale, String xRequestId,
			String xForwardedFor, String xCodiceServizio, String citId, Payload4 payload,
			SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		final String idApp = Constants.COMPONENT_NAME;
		final String ipAddress = xForwardedFor;
		final String utente = shibIdentitaCodiceFiscale == null ? Constants.COMPONENT_NAME : shibIdentitaCodiceFiscale;
		final String utenteDel = (shibIdentitaCodiceFiscale == null || shibIdentitaCodiceFiscale.equalsIgnoreCase(citId)) ? null : shibIdentitaCodiceFiscale;
		final String utenteBen = citId == null ? Constants.COMPONENT_NAME : citId;
		final String operazione = SharedConstants.OPERAZIONE_POST;
		final String oggOper = new Object(){}.getClass().getEnclosingMethod().getName();
		final String idRequest = xRequestId;
		final String codServizio = xCodiceServizio;

		AuditFacadeIf auditFacade = (AuditFacadeIf) SpringApplicationContextHelper
				.getBean(SharedConstants.AUDIT_FACADE);
		ScerevLAudit audit = null;
		try {
			log.info(oggOper + " " + payload);
			// 1. Tracciatura della chiamata
			audit = new ScerevLAudit(idApp, ipAddress, utente, utenteDel, utenteBen, operazione, oggOper + " - request",
					null, idRequest, codServizio);
			auditFacade.insertAudit(audit);

			ControlloFacadeIf controlloFacade = (ControlloFacadeIf) SpringApplicationContextHelper
					.getBean(SharedConstants.CONTROLLO_FACADE);
			controlloFacade.chkDatiObbligatoriControlloAllegati(shibIdentitaCodiceFiscale, xRequestId, xForwardedFor,
					xCodiceServizio, citId, payload);
			controlloFacade.chkCongruitaInputCitIdShib(citId, shibIdentitaCodiceFiscale);
			// controlloFacade.chkCongruitaInput(payload);

			PraticaFacadeIf praticaFacade = (PraticaFacadeIf) SpringApplicationContextHelper
					.getBean(SharedConstants.PRATICA_FACADE);
			ModelControlloAllegati output = praticaFacade.controlloAllegati(payload, audit, citId,
					shibIdentitaCodiceFiscale);

			// 1. Tracciatura della response
			audit = new ScerevLAudit(idApp, ipAddress, utente, utenteDel, utenteBen, operazione,
					oggOper + " - response", SharedConstants.KEY_OPER_OK, idRequest, codServizio);
			auditFacade.insertAudit(audit);

			return Response.status(Status.OK).header(Constants.X_REQUEST_ID, "" + xRequestId).entity(output).build();
		} catch (final Exception e) {
			e.printStackTrace();
			String errorCode = Constants.ERROR_500_CONTROLLOALLEGATI;
			return genericCatchException(e, errorCode, audit, oggOper);
		}
	}

	@Override
	public Response cittadiniCitIdAllegatoAllegatoIdGet(String shibIdentitaCodiceFiscale, String xRequestId,
			String xForwardedFor, String xCodiceServizio, String citId, String allegatoId, 
			SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		final String idApp = Constants.COMPONENT_NAME;
		final String ipAddress = xForwardedFor;
		final String utente = shibIdentitaCodiceFiscale == null ? Constants.COMPONENT_NAME : shibIdentitaCodiceFiscale;
		final String utenteDel = (shibIdentitaCodiceFiscale == null || shibIdentitaCodiceFiscale.equalsIgnoreCase(citId)) ? null : shibIdentitaCodiceFiscale;
		final String utenteBen = citId == null ? Constants.COMPONENT_NAME : citId;
		final String operazione = SharedConstants.OPERAZIONE_GET;
		final String oggOper = new Object(){}.getClass().getEnclosingMethod().getName();
		final String idRequest = xRequestId;
		final String codServizio = xCodiceServizio;

		AuditFacadeIf auditFacade = (AuditFacadeIf) SpringApplicationContextHelper
				.getBean(SharedConstants.AUDIT_FACADE);
		ScerevLAudit audit = null;
		try {

			// 1. Tracciatura della chiamata
			audit = new ScerevLAudit(idApp, ipAddress, utente, utenteDel, utenteBen, operazione, oggOper + " - request",
					null, idRequest, codServizio);
			auditFacade.insertAudit(audit);

			// 2. Verifica parametri obbligatori e congruità

			ControlloFacadeIf controlloFacade = (ControlloFacadeIf) SpringApplicationContextHelper
					.getBean(SharedConstants.CONTROLLO_FACADE);
			controlloFacade.chkDatiObbligatoriStampaAllegato(shibIdentitaCodiceFiscale, xRequestId, xForwardedFor,
					xCodiceServizio, citId, allegatoId);
			controlloFacade.chkCongruitaInputCitIdShib(citId, shibIdentitaCodiceFiscale);

			// 3. Recupero il file allegato
			CittadinoFacadeIf cittadinoFacade = (CittadinoFacadeIf) SpringApplicationContextHelper
					.getBean(SharedConstants.CITTADINO_FACADE);
			
			ScerevTFile allegato = cittadinoFacade.getAllegato(citId, allegatoId);
			byte[] file = Base64.getDecoder().decode(allegato.getFile());
			
			// 4. Tracciatura risposta
			audit = new ScerevLAudit(idApp, ipAddress, utente, utenteDel, utenteBen, operazione,
					oggOper + " - response", SharedConstants.KEY_OPER_OK, idRequest, codServizio);
			auditFacade.insertAudit(audit);
			
			return Response.status(Status.OK)
					.header("Content-Disposition", "attachment; filename=" + allegato.getNameFile().replaceAll(",", ""))
					.header("Access-Control-Expose-Headers", "Content-Disposition")
					.header(Constants.X_REQUEST_ID, "" + xRequestId).entity(file).build();
		} catch (final Exception e) {
			e.printStackTrace();
			String errorCode = Constants.ERROR_500_STAMPAALLEGATO;
			return genericCatchException(e, errorCode, audit, oggOper);
		}
	}
	
	private Response genericCatchException(Exception e, String errorCode, ScerevLAudit audit, String oggOper) {
		final Errore errore = new Errore();
		AuditFacadeIf auditFacade = (AuditFacadeIf) SpringApplicationContextHelper
				.getBean(SharedConstants.AUDIT_FACADE);
		int statusCode = Status.INTERNAL_SERVER_ERROR.getStatusCode();
		
		String detail = null;
		String dettaglioAura = null;
		String nomeFileNonFirmato = null;
		if (e instanceof ParametriObbligatoriException) {
			statusCode = Status.BAD_REQUEST.getStatusCode();
			errorCode = it.csi.scerev.scerevutil.business.SharedConstants.ERROR_CODE_PARAMETRI_OBBLIGATORI;
			detail = ((ParametriObbligatoriException) e).getMessage();
			
		} else if (e instanceof IntegritaException) {
			statusCode = Status.BAD_REQUEST.getStatusCode();
			errorCode = it.csi.scerev.scerevutil.business.SharedConstants.ERROR_CODE_INTEGRITA;
			detail = ((IntegritaException) e).getMessage();
			
		} else if (e instanceof AuraNotFoundException) {
			statusCode = Status.BAD_REQUEST.getStatusCode();
			errorCode = it.csi.scerev.scerevutil.business.SharedConstants.ERROR_CODE_AURA_NOT_FOUND;
		} else if (e instanceof AuraException) {
			statusCode = Status.BAD_REQUEST.getStatusCode();
			errorCode = it.csi.scerev.scerevutil.business.SharedConstants.ERROR_CODE_AURA_INSERTUPDATE_PROFILOANAGRAFICO;
			dettaglioAura = ((AuraException) e).getMessage();
		} else if (e instanceof DomainException) {
			statusCode = Status.BAD_REQUEST.getStatusCode();
			errorCode = ((DomainException) e).getCodMsg();
		} else if (e instanceof AllegatoNonFirmatoException) {
			statusCode = Status.BAD_REQUEST.getStatusCode();
			errorCode = ((AllegatoNonFirmatoException) e).getCodMsg();
			nomeFileNonFirmato = ((AllegatoNonFirmatoException) e).getNomeFile();
		} else if (e instanceof AllegatoNotFoundException) {
			statusCode = Status.NOT_FOUND.getStatusCode();
			errorCode = ((AllegatoNotFoundException) e).getCodMsg();
		} else {
			List<ErroreDettaglio> detailList = new ArrayList<ErroreDettaglio>();
			ErroreDettaglio dett = new ErroreDettaglio();
			dett.setChiave("500");
			dett.setValore(e.getMessage());
			detailList.add(dett);
			errore.setDetail(detailList);
		}
		
		MessaggioFacadeIf messaggioFacade = (MessaggioFacadeIf) SpringApplicationContextHelper
				.getBean(SharedConstants.MESSAGGIO_FACADE);
		ModelMessaggio msg = messaggioFacade.getMessaggioPerCodice(errorCode);
		
		if (detail == null) {
			if (nomeFileNonFirmato == null) {
				errore.setTitle(msg.getDescrizione());
			} else {
				String titleMsgNonFirmato = msg.getDescrizione().replace("{1}", nomeFileNonFirmato);
				errore.setTitle(titleMsgNonFirmato);
			}
		}
		else {
			errore.setTitle(Util.composeMessage(msg.getDescrizione(), detail));
		}
		errore.setStatus(statusCode);
		errore.setCode(msg.getCodice());
		
		if (dettaglioAura != null) {
			List<ErroreDettaglio> detailList = new ArrayList<ErroreDettaglio>();
			String[] split = dettaglioAura.split("-");
			ErroreDettaglio dett = new ErroreDettaglio();
			dett.setChiave(split[0].trim());
			if (split.length > 1)
				dett.setValore(split[1].trim());
			detailList.add(dett);
			errore.setDetail(detailList);
		}
		
		detail = " - " + Util.composeMessage(msg.getDescrizione(), detail);
		
		audit = new ScerevLAudit(audit);
		audit.setOggOper(oggOper + " - response");
		audit.setKeyOper(SharedConstants.KEY_OPER_KO + " " + detail);
		auditFacade.insertAudit(audit);
		
		return Response.status(Status.INTERNAL_SERVER_ERROR).header(Constants.X_REQUEST_ID, "" + audit.getIdrequest())
				.entity(errore).build();
	}

}
