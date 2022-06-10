/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
/**********************************************
 * CSI PIEMONTE 
 **********************************************/
package it.csi.scerev.scerevbe.business.be.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.SecurityContext;

import it.csi.scerev.scerevbe.business.SpringApplicationContextHelper;
import it.csi.scerev.scerevbe.business.be.DisponibilitaApi;
import it.csi.scerev.scerevbe.business.facade.interfaces.AuditFacadeIf;
import it.csi.scerev.scerevbe.business.facade.interfaces.ControlloFacadeIf;
import it.csi.scerev.scerevbe.business.facade.interfaces.MedicoFacadeIf;
import it.csi.scerev.scerevbe.business.facade.interfaces.MessaggioFacadeIf;
import it.csi.scerev.scerevbe.dto.Errore;
import it.csi.scerev.scerevbe.dto.ErroreDettaglio;
import it.csi.scerev.scerevbe.dto.ModelAmbulatorioLoccsiMedico;
import it.csi.scerev.scerevbe.dto.ModelDisponibilita;
import it.csi.scerev.scerevbe.dto.ModelMessaggio;
import it.csi.scerev.scerevbe.util.Constants;
import it.csi.scerev.scerevjpa.business.entity.ScerevLAudit;
import it.csi.scerev.scerevutil.business.SharedConstants;
import it.csi.scerev.scerevutil.business.Util;
import it.csi.scerev.scerevutil.business.exception.AuraException;
import it.csi.scerev.scerevutil.business.exception.AuraNotFoundException;
import it.csi.scerev.scerevutil.business.exception.IntegritaException;
import it.csi.scerev.scerevutil.business.exception.ParametriObbligatoriException;

@Path("/disponibilita")

public class DisponibilitaApiServiceImpl implements DisponibilitaApi {
	private final static Logger log = Logger.getLogger(DisponibilitaApiServiceImpl.class.getName());
	
	//getMassimaleMedico
	public Response disponibilitaPost(
			@HeaderParam("X-Request-Id") String xRequestId, @HeaderParam("X-Forwarded-For") String xForwardedFor,
			@HeaderParam("X-Codice-Servizio") String xCodiceServizio, List<ModelAmbulatorioLoccsiMedico> payload,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest) {

		final String idApp = Constants.COMPONENT_NAME;
		final String ipAddress = xForwardedFor;
		final String utente = Constants.COMPONENT_NAME;
		final String utenteDel = null;
		final String utenteBen = Constants.COMPONENT_NAME;
		final String operazione = "read";
		final String oggOper = new Object() {}.getClass().getEnclosingMethod().getName();
		final String idRequest = xRequestId;
		final String codServizio = xCodiceServizio;
		
		List<ModelDisponibilita> response = new ArrayList<ModelDisponibilita>();

		AuditFacadeIf auditFacade = (AuditFacadeIf) SpringApplicationContextHelper.getBean(SharedConstants.AUDIT_FACADE);
		ScerevLAudit audit = null;
		try {
			log.info(oggOper+" "+payload);
			
			// 5.1.1. Tracciatura della chiamata
			audit = new ScerevLAudit(idApp, ipAddress, utente, utenteDel, utenteBen, operazione, oggOper + " - request",
					null, idRequest, codServizio);
			auditFacade.insertAudit(audit);
			
			// 5.1.2 Verifica presenza parametri obbligatori/congruità dei dati
			ControlloFacadeIf controlloFacade = (ControlloFacadeIf) SpringApplicationContextHelper.getBean(SharedConstants.CONTROLLO_FACADE);
			controlloFacade.chkDatiObbligatoriMassimali(xRequestId,xForwardedFor,xCodiceServizio,payload);
			//controlloFacade.chkCongruitaInputCitIdShib(null, null);
			controlloFacade.chkCongruitaInput(payload);
			
			// 5.1.3 
			MedicoFacadeIf medicoFacade = (MedicoFacadeIf) SpringApplicationContextHelper.getBean(SharedConstants.MEDICO_FACADE);
			for(ModelAmbulatorioLoccsiMedico singleModel : payload) {
				try {
				ModelDisponibilita singleDisponibilita = medicoFacade.getMassimaliMedico(singleModel, audit);
				if(singleDisponibilita != null)
					response.add(singleDisponibilita);
				}
				finally {
					continue;
				}
				
			}
			
			// 5.1.1. Tracciatura della risposta
			audit = new ScerevLAudit(idApp, ipAddress, utente, utenteDel, utenteBen, operazione, oggOper + " - response",
					"OK", idRequest, codServizio);
			auditFacade.insertAudit(audit);
			
			return Response.status(Status.OK).header(Constants.X_REQUEST_ID, ""+xRequestId)
					.entity(response).build();
		} catch (final Exception e) {
			e.printStackTrace();
			String errorCode = Constants.ERROR_500_GETLISTACITTADINANZE;	
			return genericCatchException(e, errorCode, audit, oggOper);
		}
		
	}
	
	private Response genericCatchException(Exception e, String errorCode, ScerevLAudit audit, String oggOper) {		
		final Errore errore = new Errore();
		AuditFacadeIf auditFacade = (AuditFacadeIf) SpringApplicationContextHelper.getBean(SharedConstants.AUDIT_FACADE);
		int statusCode = Status.INTERNAL_SERVER_ERROR.getStatusCode();
		
		String detail = null;
		if (e instanceof ParametriObbligatoriException) {
			statusCode = Status.BAD_REQUEST.getStatusCode();
			errorCode = it.csi.scerev.scerevutil.business.SharedConstants.ERROR_CODE_PARAMETRI_OBBLIGATORI;
			detail = ((ParametriObbligatoriException)e).getMessage();
			
		} else if (e instanceof IntegritaException) {
			statusCode = Status.BAD_REQUEST.getStatusCode();
			errorCode = it.csi.scerev.scerevutil.business.SharedConstants.ERROR_CODE_INTEGRITA;
			detail = ((IntegritaException)e).getMessage();
			
		}  else if (e instanceof AuraNotFoundException) {
			statusCode = Status.BAD_REQUEST.getStatusCode();
			errorCode = it.csi.scerev.scerevutil.business.SharedConstants.ERROR_CODE_AURA_NOT_FOUND;
		} else if (e instanceof AuraException) {
			statusCode = Status.INTERNAL_SERVER_ERROR.getStatusCode();
			errorCode = it.csi.scerev.scerevutil.business.SharedConstants.ERROR_CODE_AURA_INSERTUPDATE_PROFILOANAGRAFICO;
		} else {
			List<ErroreDettaglio> detailList = new ArrayList<ErroreDettaglio>();
			ErroreDettaglio dett = new ErroreDettaglio();
			dett.setChiave("500");
			dett.setValore(e.getMessage());
			detailList.add(dett);
			errore.setDetail(detailList);
		}	
		
		MessaggioFacadeIf messaggioFacade = (MessaggioFacadeIf) SpringApplicationContextHelper.getBean(SharedConstants.MESSAGGIO_FACADE);
		ModelMessaggio msg = messaggioFacade.getMessaggioPerCodice(errorCode);		
		
		if (detail == null)
			errore.setTitle(msg.getDescrizione());
		else 
			errore.setTitle(Util.composeMessage(msg.getDescrizione(), detail));
		errore.setStatus(statusCode);
		errore.setCode(msg.getCodice());
		
		detail = " - " + Util.composeMessage(msg.getDescrizione(), detail);
		
		audit = new ScerevLAudit(audit);
		audit.setOggOper(oggOper + " - response");
		audit.setKeyOper(SharedConstants.KEY_OPER_KO+" "+detail);
		auditFacade.insertAudit(audit);

		return Response.status(Status.INTERNAL_SERVER_ERROR)
				.header(Constants.X_REQUEST_ID, ""+audit.getIdrequest()).entity(errore).build();
	}
}
