/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevbe.business.be.impl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.SecurityContext;

import it.csi.scerev.scerevbe.business.SpringApplicationContextHelper;
import it.csi.scerev.scerevbe.business.be.TipologieMedicoApi;
import it.csi.scerev.scerevbe.business.facade.impl.AuditFacade;
import it.csi.scerev.scerevbe.business.facade.impl.ListeFacade;
import it.csi.scerev.scerevbe.business.facade.impl.MessaggioFacade;
import it.csi.scerev.scerevbe.dto.Errore;
import it.csi.scerev.scerevbe.dto.ModelMedicoTipologia;
import it.csi.scerev.scerevbe.dto.ModelMessaggio;
import it.csi.scerev.scerevbe.util.Constants;
import it.csi.scerev.scerevjpa.business.entity.ScerevLAudit;
import it.csi.scerev.scerevutil.business.SharedConstants;
import it.csi.scerev.scerevutil.business.Util;

public class TipologieMedicoApiServiceImpl implements TipologieMedicoApi{


	private ListeFacade listeFacade;
	private MessaggioFacade messaggioFacade;
	private AuditFacade auditFacade;

	//getTipologieMedico
	public Response tipologieMedicoGet(String xRequestId, String xForwardedFor, String xCodiceServizio,
			SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
			
		
		final String idApp = Constants.COMPONENT_NAME;
		final String ipAddress = xForwardedFor;
		final String utente = Constants.COMPONENT_NAME;
		final String utenteDel = null;
		final String utenteBen = Constants.COMPONENT_NAME;
		final String operazione = "read";
		final String oggOper = new Object(){}.getClass().getEnclosingMethod().getName();
		final String idRequest = xRequestId;
		final String codServizio = xCodiceServizio;
		
		try {
			
			//1. Tracciatura della chiamata
			auditFacade = (AuditFacade) SpringApplicationContextHelper.getBean(SharedConstants.AUDIT_FACADE);
			ScerevLAudit audit = new ScerevLAudit(idApp, ipAddress, utente, utenteDel, utenteBen, operazione, oggOper+" - request", null, idRequest, codServizio);
			auditFacade.insertAudit(audit);
			
			//2. Verifica parametri obbligatori (eventuale terminazione con esito KO e tracciatura risposta).
			Map<String, String> outValidation = Util.validate(xRequestId, xForwardedFor, xCodiceServizio);
			for (Object key : outValidation.keySet()) {
				messaggioFacade = (MessaggioFacade) SpringApplicationContextHelper.getBean(SharedConstants.MESSAGGIO_FACADE);
				ModelMessaggio msg = messaggioFacade.getMessaggioPerCodice(key.toString());
				
				//inserisco log
				audit = new ScerevLAudit(idApp, ipAddress, utente, utenteDel, utenteBen, operazione, oggOper+" - response", SharedConstants.KEY_OPER_KO, idRequest, codServizio);
				auditFacade.insertAudit(audit);
				
				final Errore errore = new Errore();
				errore.setCode(msg.getCodice());
				errore.setTitle(Util.composeMessage(msg.getDescrizione(), outValidation.get(key)));
				errore.setStatus(Status.BAD_REQUEST.getStatusCode());
				return Response.status(Status.BAD_REQUEST).header(Constants.X_REQUEST_ID, ""+xRequestId).entity(errore).build();
			}

			//3. Ricerca Lista Tipologie Medico.
			listeFacade = (ListeFacade) SpringApplicationContextHelper.getBean(SharedConstants.LISTE_FACADE);
			List<ModelMedicoTipologia> listTipologieMedico = listeFacade.getTipologieMedico();
 
			//4. tracciatura risposta
			audit = new ScerevLAudit(idApp, ipAddress, utente, utenteDel, utenteBen, operazione, oggOper+" - response", SharedConstants.KEY_OPER_OK, idRequest, codServizio);
			auditFacade.insertAudit(audit);

			//4. Restituzione esito OK
			return Response.status(Status.OK).entity(listTipologieMedico).header(Constants.X_REQUEST_ID, ""+xRequestId).build();
		} catch (final Exception e) {
			e.printStackTrace();
			messaggioFacade = (MessaggioFacade) SpringApplicationContextHelper.getBean(SharedConstants.MESSAGGIO_FACADE);
			ModelMessaggio msg = messaggioFacade.getMessaggioPerCodice(Constants.ERROR_500_GETLISTATIPOLOGIEMEDICO);
			
			final Errore errore = new Errore();		
			errore.setCode(msg.getCodice());
			errore.setTitle(msg.getDescrizione());
			errore.setStatus(Status.INTERNAL_SERVER_ERROR.getStatusCode());
			return Response.status(Status.INTERNAL_SERVER_ERROR).header(Constants.X_REQUEST_ID, ""+xRequestId).entity(errore).build();
		}
	}
}
