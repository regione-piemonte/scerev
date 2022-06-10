/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
/**********************************************
 * CSI PIEMONTE 
 **********************************************/
package it.csi.scerev.scerevboweb.business.be;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

@Produces({ "application/json" })


public interface LoginApi  {
   
    public Response login(@Context HttpServletRequest httpRequest);
}
