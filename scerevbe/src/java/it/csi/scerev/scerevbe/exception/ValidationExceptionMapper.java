/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevbe.exception;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;

import it.csi.scerev.scerevbe.business.SpringApplicationContextHelper;
import it.csi.scerev.scerevbe.business.facade.impl.MessaggioFacade;
import it.csi.scerev.scerevbe.dto.Errore;
import it.csi.scerev.scerevbe.dto.ModelMessaggio;
import it.csi.scerev.scerevbe.util.Constants;
import it.csi.scerev.scerevutil.business.SharedConstants;
import it.csi.scerev.scerevutil.business.Util;

public class ValidationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

	private MessaggioFacade messaggioFacade;
	
	public Response toResponse(final ConstraintViolationException ex) {
		return Response.status(Status.BAD_REQUEST)
				.header(Constants.COMPONENT_NAME, ""+System.currentTimeMillis())
				.entity(prepareMessage(ex)).build();
	}

	private Errore prepareMessage(ConstraintViolationException ex) {
		for (ConstraintViolation<?> cv : ex. getConstraintViolations()) {
			
			messaggioFacade = (MessaggioFacade) SpringApplicationContextHelper.getBean(SharedConstants.MESSAGGIO_FACADE);
			ModelMessaggio messaggio = messaggioFacade.getMessaggioPerCodice(cv.getMessage());
			
//			//inserisco log
//			audit = new ScerevLAudit(idApp, ipAddress, utente, utenteDel, utenteBen, operazione, oggOper+" - request", Constants.KEY_OPER_KO, idRequest, codServizio);
//			auditFacade.insertAudit(audit);
			
			final Errore errore = new Errore();
			errore.setCode(messaggio.getCodice());
			errore.setTitle(Util.composeMessage(messaggio.getDescrizione(), "fieldName"));
			errore.setStatus(Status.BAD_REQUEST.getStatusCode());
			return errore;
		}
		return null;
	}
}
