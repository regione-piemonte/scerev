/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevboweb.business.be.impl;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import it.csi.def.opessanws.opessan.SoggettoOpessanBody;
import it.csi.scerev.scerevboweb.business.SpringApplicationContextHelper;
import it.csi.scerev.scerevboweb.business.be.PraticaApi;
import it.csi.scerev.scerevboweb.business.facade.impl.MessaggioFacade;
import it.csi.scerev.scerevboweb.business.facade.impl.PraticaFacade;
import it.csi.scerev.scerevboweb.business.facade.interfaces.AuditFacadeIf;
//import it.csi.scerev.scerevboweb.business.facade.interfaces.PraticaFacadeIf;
import it.csi.scerev.scerevboweb.business.ruoli.Amministratore;
import it.csi.scerev.scerevboweb.business.ruoli.Operatore;
import it.csi.scerev.scerevboweb.business.serviziesterni.ServiziEsterniClient;
import it.csi.scerev.scerevboweb.dto.AccettaRichiesta;
import it.csi.scerev.scerevboweb.dto.ChiudeRichiesta;
import it.csi.scerev.scerevboweb.dto.Cittadino;
import it.csi.scerev.scerevboweb.dto.DettaglioOutput;
import it.csi.scerev.scerevboweb.dto.GenericResponse;
import it.csi.scerev.scerevboweb.dto.Messaggio;
import it.csi.scerev.scerevboweb.dto.ModelCronologia;
import it.csi.scerev.scerevboweb.dto.NotaInterna;
import it.csi.scerev.scerevboweb.dto.RicercaOutput;
import it.csi.scerev.scerevboweb.dto.RicercaRichiesteInput;
import it.csi.scerev.scerevboweb.dto.RicercaRichiesteOutput;
import it.csi.scerev.scerevboweb.dto.UpdatePratica;
import it.csi.scerev.scerevboweb.dto.UserInfo;
import it.csi.scerev.scerevboweb.filter.IrideIdAdapterFilter;
import it.csi.scerev.scerevboweb.util.Constants;
import it.csi.scerev.scerevjpa.business.entity.ScerevLAudit;
import it.csi.scerev.scerevjpa.business.entity.ScerevTFile;
import it.csi.scerev.scerevjpa.business.entity.ScerevTPratica;
import it.csi.scerev.scerevserviziesterni.aura.find.DatiAnagrafici;
import it.csi.scerev.scerevserviziesterni.aura.get.SoggettoAuraBodyNew;
import it.csi.scerev.scerevserviziesterni.aura.getnumeroassistitiincaricomedico.NumeroAssistitiInCaricoMedicoResponse;
import it.csi.scerev.scerevserviziesterni.ensemble.interrogamefscerev.InterrogaMefScerevResult;
import it.csi.scerev.scerevutil.business.Checker;
import it.csi.scerev.scerevutil.business.RestUtils;
import it.csi.scerev.scerevutil.business.SharedConstants;
import it.csi.scerev.scerevutil.business.Util;
import it.csi.scerev.scerevutil.business.exception.AuraException;
import it.csi.scerev.scerevutil.business.exception.AuraNotFoundException;
import it.csi.scerev.scerevutil.business.exception.IntegritaException;
import it.csi.scerev.scerevutil.business.exception.OpessanNotFoundException;
import it.csi.scerev.scerevutil.business.exception.ParametriObbligatoriException;


@Path("")
@Component
public class PraticaApiServiceImpl implements PraticaApi {

	private final static Logger log = Logger.getLogger(PraticaApiServiceImpl.class.getName());
	
	final String idApp = Constants.COMPONENT_NAME;
	private MessaggioFacade messaggioFacade;
	private PraticaFacade praticaFacade;

	/*
	 * SCEREV-BO-SER-001 Ricerca Pratica
	 */
	@POST
	@Path("/ricerca")
	@Produces(MediaType.APPLICATION_JSON)
	@Amministratore
	@Operatore
	public Response getListaPratiche(@Context HttpServletRequest httpRequest,
			@HeaderParam("X-Request-ID") String xRequestId, 
			RicercaRichiesteInput input) {
		Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();
		RicercaRichiesteOutput response = null;

		UserInfo userInfo = (UserInfo) httpRequest.getSession().getAttribute(IrideIdAdapterFilter.USERINFO_SESSIONATTR);
		praticaFacade = (PraticaFacade) SpringApplicationContextHelper.getBean(SharedConstants.PRATICA_FACADE);
		
		final String idApp = Constants.COMPONENT_NAME;
		final String ipAddress = httpRequest.getRemoteAddr();
		final String utente = userInfo.getNome() + " " + userInfo.getCognome(); 
		final String utenteDel = null;
		final String utenteBen = Constants.COMPONENT_WEB_NAME;
		final String operazione = SharedConstants.OPERAZIONE_GET;
		final String oggOper = new Object() {}.getClass().getEnclosingMethod().getName();
		final String idRequest = xRequestId;
		final String codServizio = Constants.COMPONENT_WEB_NAME;
		ScerevLAudit audit = null;
		AuditFacadeIf auditFacade = null;
		
		try {
			// 5.1.1 Tracciatura della chiamata
			auditFacade = (AuditFacadeIf) SpringApplicationContextHelper.getBean(SharedConstants.AUDIT_FACADE);
			audit = new ScerevLAudit(idApp, ipAddress, utente, utenteDel, utenteBen, operazione, oggOper + " - request", null, idRequest, codServizio);
			auditFacade.insertAudit(new ScerevLAudit(audit));

			// 5.1.2 Verifica presenza parametri obbligatori/congruit dei dati
			praticaFacade.checkDataTables(input.getDataTablesParameter());
			praticaFacade.checkRicercaParameters(input.getRicerca());
			if (!Checker.isUuidValido(idRequest)) throw new IntegritaException();
			
			// Chiamata ad AURA
			DatiAnagrafici cittadino = ServiziEsterniClient.getInstance().anagrafeSanitariaAttiva(
					ServiziEsterniClient.getInstance().findCittadino(
							userInfo.getCodFisc(), null, null, null, null), null).keySet().iterator().next();			
			userInfo.setIdAura(cittadino.getIdProfiloAnagrafico().toString());
			// Se Amministratore non serve definire ASL di lavoro - In caso contrario bisogna prelevare l'informazione da OPESSAN
			if(userInfo.getRuolo().equalsIgnoreCase(Constants.OPERATORE_IRIDE)) {
//				SoggettoOpessanBody operatore = ServiziEsterniClient.getInstance().getDettaglioOperatore(new BigDecimal(userInfo.getIdAura()), audit);
//				userInfo.setCodASL(praticaFacade.getAslByDenominazione(praticaFacade.getRapportoDiLavoro(operatore.getInfoRappLavoro().getInfo()).getAzienda()));
				userInfo.setCodASL(praticaFacade.getAslOperatore(userInfo.getCodFisc()));
			}		
			
			if(userInfo.getRuolo().equalsIgnoreCase(Constants.OPERATORE_IRIDE) && (input.getRicerca().getAsl() != null && 
					!(userInfo.getCodASL().contains(input.getRicerca().getAsl())))) throw new Exception("ASL non di competenza");

			// convert inCarico to Operator
			if(input.getRicerca().getInCarico() != null && input.getRicerca().getInCarico() == true) {
				input.getRicerca().setCognomeOperatore(userInfo.getCognome());
				input.getRicerca().setNomeOperatore(userInfo.getNome());
			}
			
			// 5.1.3 Ricerca lista pratiche		
			response = praticaFacade.getListaPratiche(input, userInfo);

			// 5.1.4 Tracciatura della risposta
			audit = new ScerevLAudit(idApp, ipAddress, utente, utenteDel, utenteBen, operazione, oggOper + " - response", SharedConstants.KEY_OPER_OK, idRequest, codServizio);
			auditFacade.insertAudit(new ScerevLAudit(audit));
			
			return corsedResponse.status(Status.OK).entity(response).build();
		} catch (AuraException e) {
			return auraFaultResponse(corsedResponse, null);
		} catch(IntegritaException e) {
			return integritaFailedResponse(corsedResponse, audit, e);			
		} catch (Exception e) {		
			e.printStackTrace();
			GenericResponse errore = new GenericResponse();
			errore.setId("500");
			errore.setDescrizione(e.getMessage());	
			
			audit = new ScerevLAudit(idApp, ipAddress, utente, utenteDel, utenteBen, operazione,oggOper + " - response", errore.toString(), idRequest, codServizio);
			auditFacade.insertAudit(new ScerevLAudit(audit));
			
			return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(errore).build();
		}
	}

	/*
	 * SCEREV-BO-SER-002 Carica Pratica
	 */
	@GET
	@Path("/dettaglio/{numPratica}")
	@Produces(MediaType.APPLICATION_JSON)
	@Amministratore
	@Operatore
	public Response getPratica(@Context HttpServletRequest httpRequest,
			@HeaderParam("X-Request-ID") String xRequestId,
			@PathParam("numPratica") Integer numPratica) {
		Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();
		DettaglioOutput response = null;

		UserInfo userInfo = (UserInfo) httpRequest.getSession().getAttribute(IrideIdAdapterFilter.USERINFO_SESSIONATTR);
		praticaFacade = (PraticaFacade) SpringApplicationContextHelper.getBean(SharedConstants.PRATICA_FACADE);
		
		final String idApp = Constants.COMPONENT_NAME;
		final String ipAddress = httpRequest.getRemoteAddr();
		final String utente = userInfo.getNome() + " " + userInfo.getCognome(); 
		final String utenteDel = null;
		String utenteBen = Constants.COMPONENT_WEB_NAME;
		final String operazione = SharedConstants.OPERAZIONE_GET;
		final String oggOper = new Object() {}.getClass().getEnclosingMethod().getName();
		final String idRequest = xRequestId;
		final String codServizio = Constants.COMPONENT_WEB_NAME;
		
		ScerevLAudit audit = null;
		AuditFacadeIf auditFacade = null;

		try {
			// Tracciatura della chiamata
			auditFacade = (AuditFacadeIf) SpringApplicationContextHelper.getBean(SharedConstants.AUDIT_FACADE);
			audit = new ScerevLAudit(idApp, ipAddress, utente, utenteDel, utenteBen, operazione, oggOper + " - request", null, idRequest, codServizio);
			auditFacade.insertAudit(new ScerevLAudit(audit));
			
			// Validazione input
			if (!Checker.isUuidValido(idRequest)) throw new IntegritaException();

			// Recupero dettagli singola pratica
			ScerevTPratica pratica = praticaFacade.getPratica(numPratica);
			
			// Update utente ben x audit
			utenteBen = pratica.getCognomeAssistito() + " " +pratica.getNomeAssistito();
			
			// Chiamate ad Aura	
			Map<DatiAnagrafici, SoggettoAuraBodyNew> auraMap = null;
			DatiAnagrafici cittadino = null;
			SoggettoAuraBodyNew cittadinoExtra = null;
			try {
				auraMap = ServiziEsterniClient.getInstance().anagrafeSanitariaAttiva(
						ServiziEsterniClient.getInstance().findCittadino(pratica.getCfAssistito(), null, null, null, audit), audit);
				if(auraMap != null) {
					cittadino = auraMap.keySet().iterator().next();
					cittadinoExtra = auraMap.get(cittadino);					
				} else throw new AuraNotFoundException();
			} catch(AuraNotFoundException e) {
				cittadino = null;
				cittadinoExtra = null;
			}	
			
			auraMap = null;
			DatiAnagrafici parente;
			SoggettoAuraBodyNew parenteExtra = null;
			String erroreParente = null;
			if(pratica.getScerevTInfoSupplementari()!= null && pratica.getScerevTInfoSupplementari().getCfParente() != null) {
				try {
					auraMap = ServiziEsterniClient.getInstance().anagrafeSanitariaAttiva(
							ServiziEsterniClient.getInstance().findCittadino(pratica.getScerevTInfoSupplementari().getCfParente(), null, null, null, audit), audit);
					
					if(auraMap != null) {
						parente = auraMap.keySet().iterator().next();
						parenteExtra = auraMap.get(parente);					
					} else throw new AuraNotFoundException();
					
				} catch(AuraNotFoundException e) {
					parente = null;
					parenteExtra = null;
					erroreParente = "Codice fiscale del parente non trovato";
				}
			}			
			NumeroAssistitiInCaricoMedicoResponse numeroAssistiti = ServiziEsterniClient.getInstance().getNumeroAssistitiInCaricoMedico(new BigDecimal(pratica.getIdAuraMedico()), audit);			
			
			// Chiamata ad Opessan
			SoggettoOpessanBody operatoreMedico = ServiziEsterniClient.getInstance().getDettaglioOperatore(new BigDecimal(pratica.getIdAuraMedico()), audit);
			
			// Produzione DTO output
			response = praticaFacade.dettaglioPraticaAssembly(cittadino, cittadinoExtra, pratica, operatoreMedico, numeroAssistiti, parenteExtra, erroreParente);
			
			// Aggiunta motivazioni x combo
			response.setMotivazioni(praticaFacade.getAllMotivazioni());
			response.setTipologieProfilo(praticaFacade.getTipologiaProfiloListDTO(pratica));

			// Tracciatura della risposta
			audit = new ScerevLAudit(idApp, ipAddress, utente, utenteDel, utenteBen, operazione, oggOper + " - response", SharedConstants.KEY_OPER_OK, idRequest, codServizio);
			auditFacade.insertAudit(new ScerevLAudit(audit));
			
			return corsedResponse.status(Status.OK).entity(response).build();
		} catch(AuraException e) {
			return auraFaultResponse(corsedResponse, null);
		} catch(AuraNotFoundException e) {			
			return auraFailedResponse(corsedResponse, null);
		} catch(IntegritaException e) {
			return integritaFailedResponse(corsedResponse, audit, null);			
		} catch(OpessanNotFoundException e) {
			return opessanFailedResponse(corsedResponse, null);			
		} catch (Exception e) {			
			GenericResponse errore = new GenericResponse();
			errore.setId("500");
			errore.setDescrizione(e.getMessage());		
			
			audit = new ScerevLAudit(idApp, ipAddress, utente, utenteDel, utenteBen, operazione,oggOper + " - response", errore.toString(), idRequest, codServizio);
			auditFacade.insertAudit(new ScerevLAudit(audit));
			return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(errore).build();
		}
	}
	
	/*
	 * SCEREV-BO-SER-004.2 Aggiorna Pratica
	 */
	@POST
	@Path("/respinge")
	@Produces(MediaType.APPLICATION_JSON)
	@Amministratore
	@Operatore
	public Response respingeRichiesta(@Context HttpServletRequest httpRequest,
	@HeaderParam("X-Request-ID") String xRequestId, UpdatePratica pratica) {
		Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();
		
		UserInfo userInfo = (UserInfo) httpRequest.getSession().getAttribute(IrideIdAdapterFilter.USERINFO_SESSIONATTR);
		praticaFacade = (PraticaFacade) SpringApplicationContextHelper.getBean(SharedConstants.PRATICA_FACADE);
		
		GenericResponse response = new GenericResponse();
		
		final String idApp = Constants.COMPONENT_NAME;
		final String ipAddress = httpRequest.getRemoteAddr();
		final String utente = userInfo.getNome() + " " + userInfo.getCognome(); 
		final String utenteDel = null;
		String utenteBen = Constants.COMPONENT_WEB_NAME;
		final String operazione = SharedConstants.OPERAZIONE_GET;
		final String oggOper = new Object() {}.getClass().getEnclosingMethod().getName();
		final String idRequest = xRequestId;
		final String codServizio = Constants.COMPONENT_WEB_NAME;
		ScerevLAudit audit = null;
		AuditFacadeIf auditFacade = null;
		
		try {
			// 5.2.1 Tracciatura della chiamata
			auditFacade = (AuditFacadeIf) SpringApplicationContextHelper.getBean(SharedConstants.AUDIT_FACADE);
			audit = new ScerevLAudit(idApp, ipAddress, utente, utenteDel, utenteBen, operazione, oggOper + " - request", null, idRequest, codServizio);
			auditFacade.insertAudit(new ScerevLAudit(audit));

			// 5.3.2 Verifica presenza parametri obbligatori/congruit dei dati
			if (!Checker.isUuidValido(idRequest)) throw new IntegritaException();
			praticaFacade.checkRespingeParameters(pratica);
			
			// 5.2.3 Update db
			ScerevTPratica praticaEntity = praticaFacade.respingeRichiesta(pratica, userInfo);
			
			// Update utenteben x Audit
			utenteBen = praticaEntity.getCognomeAssistito() + " " +praticaEntity.getNomeAssistito();
			String cfDelegato = null;
			String delegato = null;
			
			if(praticaEntity.getCfDelegato() != null) {
				cfDelegato = praticaEntity.getCfDelegato();
				delegato = praticaEntity.getCognomeDelegato() + " " + praticaEntity.getNomeDelegato();
			}
			
			String tipologieRichiesta = praticaFacade.getTipologieRichiesta(praticaEntity.getPkPratica());
			boolean deleganteMinorenne = tipologieRichiesta.contains("Delegante minorenne");
			
			// 18/12/2020
			String notaAssistito = formatNota(pratica.getNota_assistito());
			
			// 5.2.4 Invio notifiche
			boolean invio = ServiziEsterniClient.getInstance().sendMessage(oggOper, utenteBen, 
					praticaEntity.getCfAssistito(), praticaEntity.getNumeroPratica().toString(), 
					notaAssistito, delegato, cfDelegato, null, null, audit, deleganteMinorenne);

			// Operazione effettuata correttamente
			if(invio) {
				response.setId("200");
				response.setDescrizione(Status.OK.getReasonPhrase());
			} else { // Operazione invio fallita 
				messaggioFacade = (MessaggioFacade) SpringApplicationContextHelper.getBean(SharedConstants.MESSAGGIO_FACADE);
				response.setId("500");
				response.setDescrizione(messaggioFacade.getMessaggioPerCodice(SharedConstants.ERROR_NOTIFICATORE).getMessaggiostr());				
			}

			// 5.2.5 Tracciatura della risposta
			audit = new ScerevLAudit(idApp, ipAddress, utente, utenteDel, utenteBen, operazione, oggOper + " - response", SharedConstants.KEY_OPER_OK, idRequest, codServizio);
			auditFacade.insertAudit(new ScerevLAudit(audit));
			
			return corsedResponse.status(Status.OK).entity(response).build();
		} catch(ParametriObbligatoriException e) {
			return parametriObbligatoriFailedResponse(corsedResponse, audit, e);					
		} catch(IntegritaException e) {
			return integritaFailedResponse(corsedResponse, audit, null);					
		} catch (Exception e) {		
			response.setId("500");
			response.setDescrizione(e.getMessage());		
			
			audit = new ScerevLAudit(idApp, ipAddress, utente, utenteDel, utenteBen, operazione,oggOper + " - response", response.toString(), idRequest, codServizio);
			auditFacade.insertAudit(new ScerevLAudit(audit));
			
			return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(response).build();
		}
	}


	/*
	 * SCEREV-BO-SER-004.3 Richiede Revisione
	 */
	@POST
	@Path("/richiedi-rettifica")
	@Produces(MediaType.APPLICATION_JSON)
	@Amministratore
	@Operatore
	public Response richiedeIntegrazione(@Context HttpServletRequest httpRequest,
			@HeaderParam("X-Request-ID") String xRequestId, UpdatePratica pratica) {
		Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();
		
		UserInfo userInfo = (UserInfo) httpRequest.getSession().getAttribute(IrideIdAdapterFilter.USERINFO_SESSIONATTR);
		praticaFacade = (PraticaFacade) SpringApplicationContextHelper.getBean(SharedConstants.PRATICA_FACADE);
		
		GenericResponse response = new GenericResponse();
		
		final String idApp = Constants.COMPONENT_NAME;
		final String ipAddress = httpRequest.getRemoteAddr();
		final String utente = userInfo.getNome() + " " + userInfo.getCognome(); 
		final String utenteDel = null;
		String utenteBen = Constants.COMPONENT_WEB_NAME;
		final String operazione = SharedConstants.OPERAZIONE_GET;
		final String oggOper = new Object() {}.getClass().getEnclosingMethod().getName();
		final String idRequest = xRequestId;
		final String codServizio = Constants.COMPONENT_WEB_NAME;
		ScerevLAudit audit = null;
		AuditFacadeIf auditFacade = null;
		
		try {
			// 5.2.1 Tracciatura della chiamata
			auditFacade = (AuditFacadeIf) SpringApplicationContextHelper.getBean(SharedConstants.AUDIT_FACADE);
			audit = new ScerevLAudit(idApp, ipAddress, utente, utenteDel, utenteBen, operazione, oggOper + " - request", null, idRequest, codServizio);
			auditFacade.insertAudit(new ScerevLAudit(audit));

			// 5.3.2 Verifica presenza parametri obbligatori/congruit dei dati
			if (!Checker.isUuidValido(idRequest)) throw new IntegritaException();
			praticaFacade.checkRichiestaRevisioneParameters(pratica);
			
			// 5.2.3 Update db
			ScerevTPratica praticaEntity = praticaFacade.richiedeIntegrazione(pratica, userInfo);
			
			// Update utenteben x Audit
			utenteBen = praticaEntity.getCognomeAssistito() + " " +praticaEntity.getNomeAssistito();
			String cfDelegato = null;
			String delegato = null;
			
			if(praticaEntity.getCfDelegato() != null) {
				cfDelegato = praticaEntity.getCfDelegato();
				delegato = praticaEntity.getCognomeDelegato() + " " + praticaEntity.getNomeDelegato();
			}
			
			String tipologieRichiesta = praticaFacade.getTipologieRichiesta(praticaEntity.getPkPratica());
			boolean deleganteMinorenne = tipologieRichiesta.contains("Delegante minorenne");
			
			// 5.2.4 Invio notifiche
			boolean invio = ServiziEsterniClient.getInstance().sendMessage(oggOper, utenteBen, 
					praticaEntity.getCfAssistito(), praticaEntity.getNumeroPratica().toString(), praticaEntity.getScerevDMotivazione().getValoreMotivazione(), delegato, cfDelegato, null, null, audit, deleganteMinorenne);

			// Operazione effettuata correttamente
			if(invio) {
				response.setId("200");
				response.setDescrizione(Status.OK.getReasonPhrase());
			} else { // Operazione invio fallita effettuata correttamente
				messaggioFacade = (MessaggioFacade) SpringApplicationContextHelper.getBean(SharedConstants.MESSAGGIO_FACADE);
				response.setId("500");
				response.setDescrizione(messaggioFacade.getMessaggioPerCodice(SharedConstants.ERROR_NOTIFICATORE).getMessaggiostr());				
			}

			// 5.2.5 Tracciatura della risposta
			audit = new ScerevLAudit(idApp, ipAddress, utente, utenteDel, utenteBen, operazione, oggOper + " - response", SharedConstants.KEY_OPER_OK, idRequest, codServizio);
			auditFacade.insertAudit(new ScerevLAudit(audit));
			
			return corsedResponse.status(Status.OK).entity(response).build();
		} catch(ParametriObbligatoriException e) {
			return parametriObbligatoriFailedResponse(corsedResponse, audit, e);					
		} catch(IntegritaException e) {
			return integritaFailedResponse(corsedResponse, audit, null);					
		} catch (Exception e) {		
			response.setId("500");
			response.setDescrizione(e.getMessage());		
			
			audit = new ScerevLAudit(idApp, ipAddress, utente, utenteDel, utenteBen, operazione,oggOper + " - response", response.toString(), idRequest, codServizio);
			auditFacade.insertAudit(new ScerevLAudit(audit));
			
			return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(response).build();
		}
	}

	/*
	 * SCEREV-BO-SER-004.1 Aggiorna Pratica
	 */
	@POST
	@Path("/accetta")
	@Produces(MediaType.APPLICATION_JSON)
	@Amministratore
	@Operatore
	public Response accettaRichiesta(@Context HttpServletRequest httpRequest, @HeaderParam("X-Request-ID") String xRequestId,
			AccettaRichiesta richiesta) {
		Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();

		UserInfo userInfo = (UserInfo) httpRequest.getSession().getAttribute(IrideIdAdapterFilter.USERINFO_SESSIONATTR);
		praticaFacade = (PraticaFacade) SpringApplicationContextHelper.getBean(SharedConstants.PRATICA_FACADE);

		GenericResponse response = new GenericResponse();
		
		final String idApp = Constants.COMPONENT_NAME;
		final String ipAddress = httpRequest.getRemoteAddr();
		final String utente = userInfo.getNome() + " " + userInfo.getCognome(); 
		final String utenteDel = null;
		String utenteBen = Constants.COMPONENT_WEB_NAME;
		final String operazione = SharedConstants.OPERAZIONE_GET;
		final String oggOper = new Object() {}.getClass().getEnclosingMethod().getName();
		final String idRequest = xRequestId;
		final String codServizio = Constants.COMPONENT_WEB_NAME;
		ScerevLAudit audit = null;
		AuditFacadeIf auditFacade = null;
		
		try {
			// 5.1.1 Tracciatura della chiamata
			auditFacade = (AuditFacadeIf) SpringApplicationContextHelper.getBean(SharedConstants.AUDIT_FACADE);
			audit = new ScerevLAudit(idApp, ipAddress, utente, utenteDel, utenteBen, operazione, oggOper + " - request", null, idRequest, codServizio);
			auditFacade.insertAudit(new ScerevLAudit(audit));
		
			
			// 5.1.2 Verifica presenza parametri obbligatori/congruit dei dati
			if (!Checker.isUuidValido(idRequest)) throw new IntegritaException();
			praticaFacade.checkAccettaParameters(richiesta);

			// Fill dei dati da AURA
			Cittadino cittadino;
			List<DatiAnagrafici> datiCittadinoAura = null;
			DatiAnagrafici cittadinoAura = null;
			SoggettoAuraBodyNew cittadinoAuraExtra = null;
			InterrogaMefScerevResult cittadinoScerev = null;
			Map<DatiAnagrafici, SoggettoAuraBodyNew> auraMap;
			try {
				datiCittadinoAura = ServiziEsterniClient.getInstance().findCittadino(richiesta.getCodiceFiscaleAssistito(), null, null, null, audit);
				auraMap = ServiziEsterniClient.getInstance().anagrafeSanitariaAttiva(datiCittadinoAura, audit);
				if(auraMap != null) {
					cittadinoAura = auraMap.keySet().iterator().next();
					cittadinoAuraExtra = auraMap.get(cittadinoAura);
					cittadino = new Cittadino(cittadinoAura, cittadinoAuraExtra);
				} else throw new AuraNotFoundException();

			} catch (AuraNotFoundException e) {
				log.error("[PraticaApiServiceImpl::accettaRichiesta] - AuraNotFoundException", e);
				cittadinoScerev = ServiziEsterniClient.getInstance().findCittadinoSuTS(richiesta.getCodiceFiscaleAssistito(), audit);
				cittadino = new Cittadino(cittadinoScerev);
			}
			
			// 5.1.3 Verifica date assistenza
			praticaFacade.checkAccettaRichiestaDtInit(richiesta, cittadino);
			
			// 5.1.4 Aggiornamento su Aura			
			praticaFacade.updateOnAura(richiesta, cittadinoAura, cittadinoAuraExtra, cittadinoScerev, userInfo.getCodFisc(), audit);
			

			// 5.1.5 Aggiornamento Stato Pratica e Cronologia
			ScerevTPratica praticaEntity = praticaFacade.accettaRichiesta(richiesta, userInfo);

			// Update utenteben x Audit
			utenteBen = praticaEntity.getCognomeAssistito() + " " +praticaEntity.getNomeAssistito();

			String cfDelegato = null;
			String delegato = null;
			String sysDate = richiesta.getDurataAssistenzaDa();
			String medico = praticaEntity.getCognomeMedico() + " " + praticaEntity.getNomeMedico();
			
			if(praticaEntity.getCfDelegato() != null) {
				cfDelegato = praticaEntity.getCfDelegato();
				delegato = praticaEntity.getCognomeDelegato() + " " + praticaEntity.getNomeDelegato();
			}
			
			String tipologieRichiesta = praticaFacade.getTipologieRichiesta(praticaEntity.getPkPratica());
			boolean deleganteMinorenne = tipologieRichiesta.contains("Delegante minorenne");
			
			// 5.1.6 Invio notifiche
			boolean invio = ServiziEsterniClient.getInstance().sendMessage(oggOper, utenteBen, 
					praticaEntity.getCfAssistito(), praticaEntity.getNumeroPratica().toString(), null, delegato, cfDelegato, medico, sysDate, audit, deleganteMinorenne);

			// Operazione effettuata correttamente
			if(invio) {
				response.setId("200");
				response.setDescrizione(Status.OK.getReasonPhrase());
			} else { // Operazione invio fallita 
				messaggioFacade = (MessaggioFacade) SpringApplicationContextHelper.getBean(SharedConstants.MESSAGGIO_FACADE);
				response.setId("500");
				response.setDescrizione(messaggioFacade.getMessaggioPerCodice(SharedConstants.ERROR_NOTIFICATORE).getMessaggiostr());				
			}

			// 5.1.7 Tracciatura della risposta
			audit = new ScerevLAudit(idApp, ipAddress, utente, utenteDel, utenteBen, operazione, oggOper + " - response", SharedConstants.KEY_OPER_OK, idRequest, codServizio);

			auditFacade.insertAudit(new ScerevLAudit(audit));
			
			return corsedResponse.status(Status.OK).entity(response).build();
		} catch (AuraException e) {
			log.error("[PraticaApiServiceImpl::accettaRichiesta] - AuraException", e);
			return auraFaultResponse(corsedResponse, null);
		} catch(ParametriObbligatoriException e) {
			log.error("[PraticaApiServiceImpl::accettaRichiesta] - ParametriObbligatoriException", e);
			return parametriObbligatoriFailedResponse(corsedResponse, audit, e);			
		} catch(IntegritaException e) {
			log.error("[PraticaApiServiceImpl::accettaRichiesta] - IntegritaException", e);
			return integritaFailedResponse(corsedResponse, audit, e);			
		} catch(OpessanNotFoundException e) {
			log.error("[PraticaApiServiceImpl::accettaRichiesta] - OpessanNotFoundException", e);
			return opessanFailedResponse(corsedResponse, null);			
		} catch (Exception e) {
			log.error("[PraticaApiServiceImpl::accettaRichiesta] - Exception", e);
			response.setId("500");
			response.setDescrizione(e.getMessage());		
			
			audit = new ScerevLAudit(idApp, ipAddress, utente, utenteDel, utenteBen, operazione,oggOper + " - response", SharedConstants.KEY_OPER_OK, idRequest, codServizio);
			auditFacade.insertAudit(new ScerevLAudit(audit));
			return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(response).build();
		}
	}

	/*
	 * SCEREV-BO-SER-004.4 Chiude Richiesta
	 */
	@POST
	@Path("/chiude")
	@Produces(MediaType.APPLICATION_JSON)
	@Amministratore
	@Operatore
	public Response chiudeRichiesta(@Context HttpServletRequest httpRequest,
			@HeaderParam("X-Request-ID") String xRequestId, ChiudeRichiesta richiesta) {
		Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();
		UserInfo userInfo = (UserInfo) httpRequest.getSession().getAttribute(IrideIdAdapterFilter.USERINFO_SESSIONATTR);
		praticaFacade = (PraticaFacade) SpringApplicationContextHelper.getBean(SharedConstants.PRATICA_FACADE);

		GenericResponse response = new GenericResponse();

		final String idApp = Constants.COMPONENT_NAME;
		final String ipAddress = httpRequest.getRemoteAddr();
		final String utente = userInfo.getNome() + " " + userInfo.getCognome();
		final String utenteDel = null;
		String utenteBen = Constants.COMPONENT_WEB_NAME;
		final String operazione = SharedConstants.OPERAZIONE_GET;
		final String oggOper = new Object() {
		}.getClass().getEnclosingMethod().getName();
		final String idRequest = xRequestId;
		final String codServizio = Constants.COMPONENT_WEB_NAME;
		ScerevLAudit audit = null;
		AuditFacadeIf auditFacade = null;

		try {
			// 5.2.1 Tracciatura della chiamata
			auditFacade = (AuditFacadeIf) SpringApplicationContextHelper.getBean(SharedConstants.AUDIT_FACADE);
			audit = new ScerevLAudit(idApp, ipAddress, utente, utenteDel, utenteBen, operazione, oggOper + " - request",
					null, idRequest, codServizio);
			auditFacade.insertAudit(new ScerevLAudit(audit));

			// 5.3.2 Verifica presenza parametri obbligatori/congruit dei dati
			if (!Checker.isUuidValido(idRequest))
				throw new IntegritaException();
			praticaFacade.checkChiudeRichiestaParameters(richiesta);

			// 5.2.3 Update DB
			ScerevTPratica praticaEntity = praticaFacade.chiudeRichiesta(richiesta, userInfo);

			// Update utenteben x Audit
			utenteBen = praticaEntity.getCognomeAssistito() + " " + praticaEntity.getNomeAssistito();

			// 5.2.5 Tracciatura della risposta
			response.setId("200");
			response.setDescrizione(Status.OK.getReasonPhrase());

			audit = new ScerevLAudit(idApp, ipAddress, utente, utenteDel, utenteBen, operazione,
					oggOper + " - response", SharedConstants.KEY_OPER_OK, idRequest, codServizio);
			auditFacade.insertAudit(new ScerevLAudit(audit));

			return corsedResponse.status(Status.OK).entity(response).build();
		} catch (ParametriObbligatoriException e) {
			return parametriObbligatoriFailedResponse(corsedResponse, audit, e);
		} catch (IntegritaException e) {
			return integritaFailedResponse(corsedResponse, audit, null);
		} catch (Exception e) {
			response.setId("500");
			response.setDescrizione(e.getMessage());

			audit = new ScerevLAudit(idApp, ipAddress, utente, utenteDel, utenteBen, operazione,
					oggOper + " - response", response.toString(), idRequest, codServizio);
			auditFacade.insertAudit(new ScerevLAudit(audit));
			return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(response).build();
		}
	}
	
	/*
	 * SCEREV-BO-SER-004.5 Aggiunge nota interna
	 */
	@POST
	@Path("/nota-interna")
	@Produces(MediaType.APPLICATION_JSON)
	@Amministratore
	@Operatore
	public Response aggiungeNotaInterna(@Context HttpServletRequest httpRequest,
			@HeaderParam("X-Request-ID") String xRequestId,
			NotaInterna nota) {
		Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();

		UserInfo userInfo = (UserInfo) httpRequest.getSession().getAttribute(IrideIdAdapterFilter.USERINFO_SESSIONATTR);
		praticaFacade = (PraticaFacade) SpringApplicationContextHelper.getBean(SharedConstants.PRATICA_FACADE);
		GenericResponse response = new GenericResponse();

		final String idApp = Constants.COMPONENT_NAME;
		final String ipAddress = httpRequest.getRemoteAddr();
		final String utente = userInfo.getNome() + " " + userInfo.getCognome();
		final String utenteDel = null;
		String utenteBen = Constants.COMPONENT_WEB_NAME;
		final String operazione = SharedConstants.OPERAZIONE_GET;
		final String oggOper = new Object() {}.getClass().getEnclosingMethod().getName();
		final String idRequest = xRequestId;
		final String codServizio = Constants.COMPONENT_WEB_NAME;
		ScerevLAudit audit = null;
		AuditFacadeIf auditFacade = null;
		messaggioFacade = (MessaggioFacade) SpringApplicationContextHelper.getBean(SharedConstants.MESSAGGIO_FACADE);
		try {
			// 5.5.1 Tracciatura della chiamata
			auditFacade = (AuditFacadeIf) SpringApplicationContextHelper.getBean(SharedConstants.AUDIT_FACADE);
			audit = new ScerevLAudit(idApp, ipAddress, utente, utenteDel, utenteBen, operazione, oggOper + " - request",null, idRequest, codServizio);
			auditFacade.insertAudit(new ScerevLAudit(audit));

			// 5.5.2 Verifica presenza parametri obbligatori/congruit dei dati
			if (!Checker.isUuidValido(idRequest))
				throw new IntegritaException();
			praticaFacade.checkNotaInternaParameters(nota);

			// 5.5.3 Inserimento record in cronologia pratica
			praticaFacade.aggiungiCronologiaNotaInterna(nota, userInfo);
			
			// 5.5.5 Tracciatura della risposta
			response.setId("200");			
			response.setDescrizione(messaggioFacade.getMessaggioPerCodice("MSG31").getMessaggiostr());
			
			audit = new ScerevLAudit(idApp, ipAddress, utente, utenteDel, utenteBen, operazione, oggOper + " - response", SharedConstants.KEY_OPER_OK, idRequest, codServizio);
			auditFacade.insertAudit(new ScerevLAudit(audit));

			return corsedResponse.status(Status.OK).entity(response).build();
		} catch (ParametriObbligatoriException e) {
			return parametriObbligatoriFailedResponse(corsedResponse, audit, e);
		} catch (IntegritaException e) {
			return integritaFailedResponse(corsedResponse, audit, null);
		} catch (Exception e) {						
			response.setId("500");
			response.setDescrizione(e.getMessage());
			
			audit = new ScerevLAudit(idApp, ipAddress, utente, utenteDel, utenteBen, operazione,oggOper + " - response", response.toString(), idRequest, codServizio);
			auditFacade.insertAudit(new ScerevLAudit(audit));
			
			return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(response).build();
		}
	}
	
	/*
	 * SCEREV-BO-SER-005 Carica Cronologia
	 */
	@GET
	@Path("/cronologia-pratica/{numPratica}")
	@Produces(MediaType.APPLICATION_JSON)
	@Amministratore
	@Operatore
	public Response storicoPraticaGet(@Context HttpServletRequest httpRequest, @HeaderParam("X-Request-ID") String xRequestId, @PathParam("numPratica") Integer numPratica_Selezionata) {	
		UserInfo userInfo = (UserInfo) httpRequest.getSession().getAttribute(IrideIdAdapterFilter.USERINFO_SESSIONATTR);
	
		final String idApp = Constants.COMPONENT_NAME;
		final String ipAddress = httpRequest.getRemoteAddr();
		final String utente = userInfo.getNome() + " " + userInfo.getCognome();
		final String utenteDel = null;
		      String utenteBen = null;
		final String operazione = "READ";
		final String oggOper = new Object(){}.getClass().getEnclosingMethod().getName();
		final String idRequest = xRequestId;
		final String codServizio = Constants.COMPONENT_WEB_NAME;
		
		try {	
			praticaFacade = (PraticaFacade) SpringApplicationContextHelper.getBean(SharedConstants.PRATICA_FACADE);
			ScerevTPratica praticaAssociata = praticaFacade.getPratica(numPratica_Selezionata);
			utenteBen = praticaAssociata.getCfAssistito();
			
			//1. Tracciatura della chiamata
			AuditFacadeIf auditFacade = (AuditFacadeIf) SpringApplicationContextHelper.getBean(SharedConstants.AUDIT_FACADE);
			ScerevLAudit audit = new ScerevLAudit(idApp, ipAddress, utente, utenteDel, utenteBen, operazione, oggOper + " - request", null, idRequest, codServizio);
			auditFacade.insertAudit(new ScerevLAudit(audit));
			
			//2. Verifica parametri obbligatori (eventuale terminazione con esito KO e tracciatura risposta).
			if (!Checker.isUuidValido(idRequest)) {
				messaggioFacade = (MessaggioFacade) SpringApplicationContextHelper.getBean(SharedConstants.MESSAGGIO_FACADE);
				Messaggio msg;
				if(idRequest == null)
					msg = messaggioFacade.getMessaggioPerCodice("ERR09");
				else
					msg = messaggioFacade.getMessaggioPerCodice("ERR10");
					 
				//inserisco log
				audit = new ScerevLAudit(idApp, ipAddress, utente, utenteDel, utenteBen, operazione, oggOper+" - request", SharedConstants.KEY_OPER_KO, idRequest, codServizio);
				auditFacade.insertAudit(new ScerevLAudit(audit));
				
				final GenericResponse errore = new GenericResponse();
				errore.setId(msg.getCodice());
				errore.setDescrizione(Util.composeMessage(msg.getMessaggiostr(), "X-Request-ID"));
				return Response.status(Status.BAD_REQUEST).header(Constants.COMPONENT_NAME, ""+System.currentTimeMillis()).entity(errore).build();
		   }

			//3. Ricerca Cronologia Pratica
			List<ModelCronologia> listCronologiaPratica = praticaFacade.getStoricoPratica(praticaAssociata.getPkPratica());
		
			
			//4. Tracciatura della risposta OK
			auditFacade = (AuditFacadeIf) SpringApplicationContextHelper.getBean(SharedConstants.AUDIT_FACADE);
			audit = new ScerevLAudit(idApp, ipAddress, utente, utenteDel, utenteBen, operazione, oggOper + " - response", SharedConstants.KEY_OPER_OK, idRequest, codServizio);
			auditFacade.insertAudit(new ScerevLAudit(audit));

			//5. Restituzione esito OK
			return Response.status(Status.OK).entity(listCronologiaPratica).header(Constants.COMPONENT_NAME, ""+System.currentTimeMillis()).build();
			
		} catch (Exception e) {		
			e.printStackTrace();
			messaggioFacade = (MessaggioFacade) SpringApplicationContextHelper.getBean(SharedConstants.MESSAGGIO_FACADE);
			Messaggio msg = messaggioFacade.getMessaggioPerCodice(SharedConstants.ERROR_CODE_BACKEND_NOTFOUND);
			final GenericResponse errore = new GenericResponse();
			errore.setId(msg.getCodice());
			errore.setDescrizione(msg.getMessaggiostr());
			return Response.status(Status.INTERNAL_SERVER_ERROR).header(Constants.COMPONENT_NAME, ""+System.currentTimeMillis()).entity(errore).build();
		}
	}
	
	/*
	 * SCEREV-BO-SER-003 DownloadDocumentoPratica
	 */
	@GET
	@Path("/download/{numPratica}/")
	@Produces("application/pdf")
	@Amministratore
	@Operatore
	public Response downloadAllegatoPratica(@Context HttpServletRequest httpRequest,
			@HeaderParam("X-Request-ID") String xRequestId, @PathParam("numPratica") Integer numPratica,
			@QueryParam("pkFile") Integer pkFile) {

		Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();

		UserInfo userInfo = (UserInfo) httpRequest.getSession().getAttribute(IrideIdAdapterFilter.USERINFO_SESSIONATTR);

		final String idApp = Constants.COMPONENT_NAME;
		final String ipAddress = httpRequest.getRemoteAddr();
		final String utente = userInfo.getNome() + " " + userInfo.getCognome();
		final String utenteDel = null;
		final String utenteBen = null;
		final String operazione = "READ";
		final String oggOper = new Object() {
		}.getClass().getEnclosingMethod().getName();
		final String idRequest = xRequestId;
		final String codServizio = Constants.COMPONENT_WEB_NAME;
		String fileName = null;
		File filePdf = null;
		byte[] fileScelto = null;

		Integer num_praticaScelta = numPratica;
		Integer pk_FileScelto = pkFile;

		try {

			praticaFacade = (PraticaFacade) SpringApplicationContextHelper.getBean(SharedConstants.PRATICA_FACADE);
			ScerevTPratica praticaAssociata = praticaFacade.getPratica(num_praticaScelta);

			// recupero il dettaglio della pratica, passando sempre il NumPratica in input
			DettaglioOutput dettagliPratica = (DettaglioOutput) getPratica(httpRequest, xRequestId, num_praticaScelta).getEntity();

			// 1. Tracciatura della chiamata
//			AuditFacadeIf auditFacade = (AuditFacadeIf) SpringApplicationContextHelper.getBean(SharedConstants.AUDIT_FACADE);
			ScerevLAudit audit = new ScerevLAudit(idApp, ipAddress, utente, utenteDel, utenteBen, operazione, oggOper + " - request", null, idRequest, codServizio);
			// auditFacade.insertAudit(new ScerevLAudit(audit));

			// 2. Verifica parametri obbligatori (eventuale terminazione con esito KO e tracciatura risposta).

			if (!Checker.isUuidValido(idRequest)) {
				messaggioFacade = (MessaggioFacade) SpringApplicationContextHelper.getBean(SharedConstants.MESSAGGIO_FACADE);
				// Messaggio msg;
				if (idRequest == null) {
					// errore ERR09 "PARAMETRI OBBLIGATORI NON PRESENTI"
					return integritaFailedResponse(corsedResponse, audit, null);
				} else {
					// errore ERR10 "MANCATA CONGRUITA' DEI DATI IN INPUT"
					corsedResponse = corsedResponse.type(MediaType.APPLICATION_JSON);
					String codiceGenericResponse = ("ERR10");
					String messaggioGenericResponse = messaggioFacade.getMessaggioPerCodice(codiceGenericResponse)
							.toString();
					IntegritaException errore = new IntegritaException(codiceGenericResponse, messaggioGenericResponse);
					return integritaFailedResponse(corsedResponse, audit, errore);
				}

			}

			if (pk_FileScelto != null) {
				// SCARICA ALLEGATO PRATICA	
				Integer pk_input = pkFile;
				ScerevTFile fileAssociato = null;
				fileAssociato = praticaFacade.getFileByPkFile(pk_input);
				fileName = fileAssociato.getNameFile();
				fileScelto = Base64.getDecoder().decode(fileAssociato.getFile()); // byte[] in cui c' il file da visualizzare e/o stampare

				corsedResponse.header("Content-Disposition", "attachment; filename=" + fileName.replaceAll(",", ""));
				return corsedResponse.header("Access-Control-Expose-Headers", "Content-Disposition").status(Status.OK).entity(fileScelto).build();
				
			} else {
				// GENERA STAMPA PRATICA
				// Recupero dati da inserire nel report Jasper
				Map<String, Object> parameters = new HashMap<String, Object>();
				parameters = praticaFacade.getReportParameters(praticaAssociata, dettagliPratica);

				byte[] byteArray = praticaFacade.getReportPratica(parameters);

				if (byteArray.length < 1000) // NO DATA
					return corsedResponse.status(Status.NO_CONTENT).entity(null).build();

				try {
					fileName = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
					filePdf = File.createTempFile(fileName, ".pdf");
					FileUtils.writeByteArrayToFile(filePdf, byteArray);
				} catch (IOException e) {
					e.printStackTrace();
					corsedResponse = corsedResponse.type(MediaType.APPLICATION_JSON);
					return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(e).build();
				}

				corsedResponse.header("Content-Disposition", "attachment; filename=" + fileName + ".pdf");	
				return corsedResponse.header("Access-Control-Expose-Headers", "Content-Disposition").status(Status.OK).entity(filePdf).build();			
			}

		} catch (Exception e) {
			corsedResponse = corsedResponse.type(MediaType.APPLICATION_JSON);
			e.printStackTrace();
			messaggioFacade = (MessaggioFacade) SpringApplicationContextHelper
					.getBean(SharedConstants.MESSAGGIO_FACADE);
			Messaggio msg = messaggioFacade.getMessaggioPerCodice(SharedConstants.ERROR_CODE_BACKEND_NOTFOUND);
			final GenericResponse errore = new GenericResponse();
			errore.setId(msg.getCodice());
			errore.setDescrizione(msg.getMessaggiostr());
			return Response.status(Status.INTERNAL_SERVER_ERROR)
					.header(Constants.COMPONENT_NAME, "" + System.currentTimeMillis()).entity(errore).build();
		}
	}
	
	/**
	 *  SCEREV-BO-CDU-004 Valuta Pratica
	 * @param httpRequest
	 * @param xRequestId
	 * @return
	 */
	@GET
	@Path("/prendi-in-carico/{numPratica}")
	@Produces(MediaType.APPLICATION_JSON)
	@Amministratore
	@Operatore
	public Response prendiInCarico(@Context HttpServletRequest httpRequest, @HeaderParam("X-Request-ID") String xRequestId,
			@PathParam("numPratica") String numPratica) {
		Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();
		UserInfo userInfo = (UserInfo) httpRequest.getSession().getAttribute(IrideIdAdapterFilter.USERINFO_SESSIONATTR);
		praticaFacade = (PraticaFacade) SpringApplicationContextHelper.getBean(SharedConstants.PRATICA_FACADE);
		GenericResponse response = new GenericResponse();

		final String idApp = Constants.COMPONENT_NAME;
		final String ipAddress = httpRequest.getRemoteAddr();
		final String utente = userInfo.getNome() + " " + userInfo.getCognome(); 
		final String utenteDel = null;
		String utenteBen = Constants.COMPONENT_WEB_NAME;
		final String operazione = SharedConstants.OPERAZIONE_GET;
		final String oggOper = new Object() {}.getClass().getEnclosingMethod().getName();
		final String idRequest = xRequestId;
		final String codServizio = Constants.COMPONENT_WEB_NAME;
		ScerevLAudit audit = new ScerevLAudit(idApp, ipAddress, utente, utenteDel, utenteBen, operazione, oggOper + " - request", null, idRequest, codServizio);
		
		try {					
			ScerevTPratica praticaEntity = praticaFacade.prendiInCarico(userInfo, numPratica);			
			
			utenteBen = praticaEntity.getCognomeAssistito() + " " +praticaEntity.getNomeAssistito();
			String cfDelegato = null;
			String delegato = null;
			
			if(praticaEntity.getCfDelegato() != null) {
				cfDelegato = praticaEntity.getCfDelegato();
				delegato = praticaEntity.getCognomeDelegato() + " " + praticaEntity.getNomeDelegato();
			}
			
			String tipologieRichiesta = praticaFacade.getTipologieRichiesta(praticaEntity.getPkPratica());
			boolean deleganteMinorenne = tipologieRichiesta.contains("Delegante minorenne");
			
			//Invio notifiche
			boolean invio = ServiziEsterniClient.getInstance().sendMessage(oggOper, utenteBen, 
					praticaEntity.getCfAssistito(), praticaEntity.getNumeroPratica().toString(), null, delegato, cfDelegato, null, null, audit, deleganteMinorenne);
			
			// Operazione effettuata correttamente
			if(invio) {
				response.setId("200");
				response.setDescrizione(Status.OK.getReasonPhrase());
			} else { // Operazione invio fallita 
				messaggioFacade = (MessaggioFacade) SpringApplicationContextHelper.getBean(SharedConstants.MESSAGGIO_FACADE);
				response.setId("500");
				response.setDescrizione(messaggioFacade.getMessaggioPerCodice(SharedConstants.ERROR_NOTIFICATORE).getMessaggiostr());				
			}
			
			return corsedResponse.status(Status.OK).entity(response).build();
		} catch (Exception e) {						
			response.setId("500");
			response.setDescrizione(e.getMessage());
		
			return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(response).build();
		}
	}
	
	@POST
	@Path("/downloadExportRicerca")
	@Produces("application/pdf")
	@Amministratore
	@Operatore
	public Response downloadExportRicerca(@Context HttpServletRequest httpRequest, @HeaderParam("X-Request-ID") String xRequestId,
			RicercaRichiesteInput input) {
		Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();

		UserInfo userInfo = (UserInfo) httpRequest.getSession().getAttribute(IrideIdAdapterFilter.USERINFO_SESSIONATTR);

		final String idApp = Constants.COMPONENT_NAME;
		final String ipAddress = httpRequest.getRemoteAddr();
		final String utente = userInfo.getNome() + " " + userInfo.getCognome();
		final String utenteDel = null;
		final String utenteBen =  Constants.COMPONENT_NAME;
		final String operazione = "READ";
		final String oggOper = new Object(){}.getClass().getEnclosingMethod().getName();
		final String idRequest = xRequestId;
		final String codServizio = Constants.COMPONENT_WEB_NAME;
		String fileName = null;
		ScerevLAudit audit = null;
		AuditFacadeIf auditFacade = null;
		try {

			// 1. Tracciatura della chiamata
			auditFacade = (AuditFacadeIf) SpringApplicationContextHelper.getBean(SharedConstants.AUDIT_FACADE);
			audit = new ScerevLAudit(idApp, ipAddress, utente, utenteDel, utenteBen, operazione, oggOper + " - request", null, idRequest, codServizio);
			auditFacade.insertAudit(new ScerevLAudit(audit));

			// 2. Verifica parametri obbligatori (eventuale terminazione con esito KO e tracciatura risposta).
			if (!Checker.isUuidValido(idRequest)) {
				messaggioFacade = (MessaggioFacade) SpringApplicationContextHelper.getBean(SharedConstants.MESSAGGIO_FACADE);
				// Messaggio msg;
				if (idRequest == null) {
					// errore ERR09 "PARAMETRI OBBLIGATORI NON PRESENTI"
					return integritaFailedResponse(corsedResponse, audit, null);
				} else {
					// errore ERR10 "MANCATA CONGRUITA' DEI DATI IN INPUT"
					corsedResponse = corsedResponse.type(MediaType.APPLICATION_JSON);
					String codiceGenericResponse = ("ERR10");
					String messaggioGenericResponse = messaggioFacade.getMessaggioPerCodice(codiceGenericResponse)
							.toString();
					IntegritaException errore = new IntegritaException(codiceGenericResponse, messaggioGenericResponse);
					return integritaFailedResponse(corsedResponse, audit, errore);
				}

			}
			
			input.getDataTablesParameter().setStart(0);
			input.getDataTablesParameter().setLength(0);
			Response listaPratiche = this.getListaPratiche(httpRequest, xRequestId, input);
			RicercaRichiesteOutput res = (RicercaRichiesteOutput) listaPratiche.getEntity();
			List<RicercaOutput> ricerca = res.getData();
			
			praticaFacade = (PraticaFacade) SpringApplicationContextHelper.getBean(SharedConstants.PRATICA_FACADE);
			File export = praticaFacade.getExportRicerca(ricerca);
			fileName = export.getName();
			
			// Tracciatura della risposta
			audit = new ScerevLAudit(idApp, ipAddress, utente, utenteDel, utenteBen, operazione, oggOper + " - response", SharedConstants.KEY_OPER_OK, idRequest, codServizio);
			auditFacade.insertAudit(new ScerevLAudit(audit));
			
			corsedResponse.header("Content-Disposition", "attachment; filename=" + fileName);
			return corsedResponse.header("Access-Control-Expose-Headers", "Content-Disposition").status(Status.OK)
					.entity(export).build();

		} catch (Exception e) {
			corsedResponse = corsedResponse.type(MediaType.APPLICATION_JSON);
			e.printStackTrace();
			messaggioFacade = (MessaggioFacade) SpringApplicationContextHelper
					.getBean(SharedConstants.MESSAGGIO_FACADE);
			Messaggio msg = messaggioFacade.getMessaggioPerCodice(SharedConstants.ERROR_CODE_BACKEND_NOTFOUND);
			final GenericResponse errore = new GenericResponse();
			errore.setId(msg.getCodice());
			errore.setDescrizione(msg.getMessaggiostr());
			
			audit = new ScerevLAudit(idApp, ipAddress, utente, utenteDel, utenteBen, operazione,oggOper + " - response", errore.toString(), idRequest, codServizio);
			auditFacade.insertAudit(new ScerevLAudit(audit));
			
			return Response.status(Status.INTERNAL_SERVER_ERROR)
					.header(Constants.COMPONENT_NAME, "" + System.currentTimeMillis()).entity(errore).build();
		}

	}
	
	/***
	 * integritaFailedResponse: valorizzare "e" con una IntegritaException in caso di messaggio personalizzato. In caso contrario valorizzare con null.
	 * @param corsedResponse
	 * @param e
	 * @return
	 */
	private Response integritaFailedResponse(ResponseBuilder corsedResponse, ScerevLAudit audit, IntegritaException e) {
		final GenericResponse errore = new GenericResponse();
		if(e==null) {
			messaggioFacade = (MessaggioFacade) SpringApplicationContextHelper.getBean(SharedConstants.MESSAGGIO_FACADE);
			Messaggio msg = messaggioFacade.getMessaggioPerCodice(SharedConstants.ERROR_CODE_INTEGRITA);
	
			// inserisco log
			audit.setKeyOper(SharedConstants.KEY_OPER_KO);
			AuditFacadeIf auditFacade = (AuditFacadeIf) SpringApplicationContextHelper.getBean(SharedConstants.AUDIT_FACADE);
			auditFacade.insertAudit(new ScerevLAudit(new ScerevLAudit(audit)));
	
			errore.setId(msg.getCodice());
			errore.setDescrizione(Util.composeMessage(msg.getMessaggiostr(), audit.getIdrequest()));
		} else {
			errore.setId(e.getCodMsg());
			errore.setDescrizione(e.getMessage());		
		}
		return corsedResponse.status(Status.BAD_REQUEST).entity(errore).build();
	}	
	
	/***
	 * parametriObbligatoriFailedResponse: valorizzare "e" con una ParametriObbligatoriException in caso di messaggio personalizzato. In caso contrario valorizzare con null.
	 * @param corsedResponse
	 * @param e
	 * @return
	 */
	private Response parametriObbligatoriFailedResponse(ResponseBuilder corsedResponse, ScerevLAudit audit, ParametriObbligatoriException e) {
		final GenericResponse errore = new GenericResponse();
		if(e==null) {
			messaggioFacade = (MessaggioFacade) SpringApplicationContextHelper.getBean(SharedConstants.MESSAGGIO_FACADE);
			Messaggio msg = messaggioFacade.getMessaggioPerCodice(SharedConstants.ERROR_CODE_PARAMETRI_OBBLIGATORI);
	
			// inserisco log
			audit.setKeyOper(SharedConstants.KEY_OPER_KO);
			AuditFacadeIf auditFacade = (AuditFacadeIf) SpringApplicationContextHelper.getBean(SharedConstants.AUDIT_FACADE);
			auditFacade.insertAudit(new ScerevLAudit(audit));
	
			errore.setId(msg.getCodice());
			errore.setDescrizione(Util.composeMessage(msg.getMessaggiostr(), audit.getIdrequest()));			
		} else {
			errore.setId(e.getCodMsg());
			errore.setDescrizione(e.getMessage());		
		}
		return corsedResponse.status(Status.BAD_REQUEST).entity(errore).build();
	}	
	
	/***
	 * AuraFailedResponse: valorizzare "e" con una AuraException in caso di messaggio personalizzato. In caso contrario valorizzare con null.
	 * @param corsedResponse
	 * @param e
	 * @return
	 */
	private Response auraFaultResponse(ResponseBuilder corsedResponse, AuraException e) {
		final GenericResponse errore = new GenericResponse();
		if(e==null) {
			messaggioFacade = (MessaggioFacade) SpringApplicationContextHelper.getBean(SharedConstants.MESSAGGIO_FACADE);
			Messaggio msg = messaggioFacade.getMessaggioPerCodice(SharedConstants.ERROR_CODE_AURA_NOT_FOUND);			
			errore.setId(msg.getCodice());
			errore.setDescrizione(msg.getMessaggiostr());			
		} else {
			errore.setId(e.getCodMsg());
			errore.setDescrizione(e.getMessage());		
		}
		return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(errore).build();
	}
	
	/***
	 * AuraFailedResponse: valorizzare "e" con una AuraNotFoundException in caso di messaggio personalizzato. In caso contrario valorizzare con null.
	 * @param corsedResponse
	 * @param e
	 * @return
	 */
	private Response auraFailedResponse(ResponseBuilder corsedResponse, AuraNotFoundException e) {
		final GenericResponse errore = new GenericResponse();
		if(e==null) {
			messaggioFacade = (MessaggioFacade) SpringApplicationContextHelper.getBean(SharedConstants.MESSAGGIO_FACADE);
			Messaggio msg = messaggioFacade.getMessaggioPerCodice(SharedConstants.MESSAGE_AURA_NOTFOUND);			
			errore.setId(msg.getCodice());
			errore.setDescrizione(msg.getMessaggiostr());			
		} else {
			errore.setId(e.getCodMsg());
			errore.setDescrizione(e.getMessage());		
		}
		return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(errore).build();
	}

	/***
	 * opessanFailedResponse: valorizzare "e" con una OpessanNotFoundException in caso di messaggio personalizzato. In caso contrario valorizzare con null.
	 * @param corsedResponse
	 * @param e
	 * @return
	 */
	private Response opessanFailedResponse(ResponseBuilder corsedResponse, OpessanNotFoundException e) {
		final GenericResponse errore = new GenericResponse();
		if(e==null) {
			messaggioFacade = (MessaggioFacade) SpringApplicationContextHelper.getBean(SharedConstants.MESSAGGIO_FACADE);
			Messaggio msg = messaggioFacade.getMessaggioPerCodice(SharedConstants.MESSAGE_AURA_NOTFOUND);			
			errore.setId(msg.getCodice());
			errore.setDescrizione(msg.getMessaggiostr());			
		} else {
			errore.setId(e.getCodMsg());
			errore.setDescrizione(e.getMessage());		
		}
		return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(errore).build();
	}
	

	private String formatNota(String nota) {
		return nota.trim().replaceAll("\n", " ").replaceAll("  ", " ");
	}

}
