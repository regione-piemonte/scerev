/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevboweb.business.be.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.stereotype.Component;

import it.csi.iride2.policy.entity.Identita;
import it.csi.scerev.scerevboweb.business.SpringApplicationContextHelper;
import it.csi.scerev.scerevboweb.business.be.LoginApi;
import it.csi.scerev.scerevboweb.business.facade.impl.MessaggioFacade;
import it.csi.scerev.scerevboweb.business.facade.impl.PraticaFacade;
import it.csi.scerev.scerevboweb.business.facade.interfaces.AslFacadeIf;
import it.csi.scerev.scerevboweb.business.facade.interfaces.AuditFacadeIf;
import it.csi.scerev.scerevboweb.business.serviziesterni.ServiziEsterniClient;
import it.csi.scerev.scerevboweb.dto.Asl;
import it.csi.scerev.scerevboweb.dto.GenericResponse;
import it.csi.scerev.scerevboweb.dto.Messaggio;
import it.csi.scerev.scerevboweb.dto.UserInfo;
import it.csi.scerev.scerevboweb.filter.IrideIdAdapterFilter;
import it.csi.scerev.scerevboweb.util.Constants;
import it.csi.scerev.scerevjpa.business.entity.ScerevLAudit;
import it.csi.scerev.scerevserviziesterni.aura.find.DatiAnagrafici;
import it.csi.scerev.scerevserviziesterni.iride.base.InternalException;
import it.csi.scerev.scerevserviziesterni.iride.base.Ruolo;
import it.csi.scerev.scerevutil.business.RestUtils;
import it.csi.scerev.scerevutil.business.SharedConstants;
import it.csi.scerev.scerevutil.business.exception.IrideNotFoundException;

@Path("")
@Component
public class LoginApiServiceImpl implements LoginApi {

	@GET
	@Path("/login")
	@Produces({ "application/json" })
	public Response login(@Context HttpServletRequest httpRequest) {
		Response.ResponseBuilder corsedResponse = RestUtils.getCorsedResponse();		
		MessaggioFacade messaggioFacade;
		UserInfo userInfo = (UserInfo) httpRequest.getSession().getAttribute(IrideIdAdapterFilter.USERINFO_SESSIONATTR);
		PraticaFacade praticaFacade = (PraticaFacade) SpringApplicationContextHelper.getBean(SharedConstants.PRATICA_FACADE);
		AslFacadeIf listeFacade = (AslFacadeIf) SpringApplicationContextHelper.getBean("aslFacade");
				
		final String idApp = Constants.COMPONENT_NAME;
		final String ipAddress = httpRequest.getRemoteAddr();
		final String utente = userInfo.getNome() + " " + userInfo.getCognome(); 
		final String utenteDel = null;
		final String utenteBen = Constants.COMPONENT_WEB_NAME;
		final String operazione = SharedConstants.OPERAZIONE_GET;
		final String oggOper = new Object() {}.getClass().getEnclosingMethod().getName();
		final String idRequest = null;
		final String codServizio = Constants.COMPONENT_WEB_NAME;
		AuditFacadeIf auditFacade = null;
		ScerevLAudit audit = null;
		
		try {
			// 5.1.1 Tracciatura della chiamata
			auditFacade = (AuditFacadeIf) SpringApplicationContextHelper.getBean(SharedConstants.AUDIT_FACADE);
			audit = new ScerevLAudit(idApp, ipAddress, utente, utenteDel, utenteBen, operazione, oggOper + " - request", null, idRequest, codServizio);
			auditFacade.insertAudit(new ScerevLAudit(audit));
			
			// Chiamata ad AURA
			DatiAnagrafici cittadino = ServiziEsterniClient.getInstance().anagrafeSanitariaAttiva(
					ServiziEsterniClient.getInstance().findCittadino(
							userInfo.getCodFisc(), null, null, null, null), null).keySet().iterator().next();
//			DatiAnagrafici cittadino = ServiziEsterniClient.getInstance().findCittadino(userInfo.getCodFisc(), null, null, null, audit).get(0);			
			userInfo.setIdAura(cittadino.getIdProfiloAnagrafico().toString());
			for(Asl asl : listeFacade.getAllAsl()) {
				if(asl.getId().equals(userInfo.getCodASL())) userInfo.setASL(asl.getDescrizione());
			}			
			// Chiamata a Iride per il ruolo
			Identita identita = (Identita) httpRequest.getSession().getAttribute("iride2_id");
			List<Ruolo> ruoli = ServiziEsterniClient.getInstance().findRuoli(identita, audit);
			userInfo.setRuolo(ruoli.get(0).getCodiceRuolo());
			
			// Se Amministratore non serve definire ASL di lavoro - In caso contrario bisogna prelevare l'informazione da OPESSAN
			if(userInfo.getRuolo().equalsIgnoreCase(Constants.OPERATORE_IRIDE)) {
				
//				SoggettoOpessanBody operatore = ServiziEsterniClient.getInstance().getDettaglioOperatore(new BigDecimal(userInfo.getIdAura()), audit);
//				userInfo.setCodASL(praticaFacade.getAslByDenominazione(praticaFacade.getRapportoDiLavoro(operatore.getInfoRappLavoro().getInfo()).getAzienda()));
				
				userInfo.setCodASL(praticaFacade.getAslOperatore(userInfo.getCodFisc()));
			}													
			httpRequest.getSession().setAttribute(IrideIdAdapterFilter.USERINFO_SESSIONATTR,userInfo);
			
			// 5.1.4 Tracciatura della risposta
			audit = new ScerevLAudit(idApp, ipAddress, utente, utenteDel, utenteBen, operazione, oggOper + " - response", SharedConstants.KEY_OPER_OK, idRequest, codServizio);
			auditFacade.insertAudit(new ScerevLAudit(audit));
			
			return corsedResponse.status(Status.OK).entity(userInfo).build();			
		} catch (Exception e) {	
			e.printStackTrace();
			String error;
			if(e instanceof InternalException) error = SharedConstants.ERROR_CODE_IRIDE;
			else if(e instanceof IrideNotFoundException) error = SharedConstants.ERROR_CODE_IRIDE_NOT_FOUND;
			else error = SharedConstants.ERROR_CODE_INTERNO_SERVER;
			messaggioFacade = (MessaggioFacade) SpringApplicationContextHelper.getBean(SharedConstants.MESSAGGIO_FACADE);
			Messaggio msg = messaggioFacade.getMessaggioPerCodice(error);	
			GenericResponse errore = new GenericResponse();
			errore.setId("500");
			errore.setDescrizione(msg.getMessaggiostr());	

			audit = new ScerevLAudit(idApp, ipAddress, utente, utenteDel, utenteBen, operazione, oggOper + " - response", errore.toString(), idRequest, codServizio);
			auditFacade.insertAudit(new ScerevLAudit(audit));
			
			return corsedResponse.status(Status.INTERNAL_SERVER_ERROR).entity(errore).build();
		}
	}	

}
