/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevboweb.business;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import it.csi.scerev.scerevboweb.business.be.impl.PingApiServiceImpl;
import it.csi.scerev.scerevboweb.business.be.impl.PraticaApiServiceImpl;

@ApplicationPath("restfacade/be")
public class ScerevbowebRestApplication extends Application {
	private Set<Object> singletons = new HashSet<Object>();
	private Set<Class<?>> empty = new HashSet<Class<?>>();

	public ScerevbowebRestApplication() {
		// singletons.add(new ScerevbowclBE());
		singletons.add(new PingApiServiceImpl());
		singletons.add(new PraticaApiServiceImpl());

	}

	@Override
	public Set<Class<?>> getClasses() {
		return empty;
	}

	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}

}
