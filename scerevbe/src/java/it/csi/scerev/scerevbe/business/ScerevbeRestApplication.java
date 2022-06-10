/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevbe.business;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import it.csi.scerev.scerevbe.business.be.impl.CittadinanzeApiServiceImpl;
import it.csi.scerev.scerevbe.business.be.impl.CittadiniApiServiceImpl;
import it.csi.scerev.scerevbe.business.be.impl.DisponibilitaApiServiceImpl;
import it.csi.scerev.scerevbe.business.be.impl.TipologieMedicoApiServiceImpl;
import it.csi.scerev.scerevbe.business.be.impl.MotivazioniDomicilioApiServiceImpl;
import it.csi.scerev.scerevbe.business.be.impl.MotivazioniSoggiornoApiServiceImpl;
import it.csi.scerev.scerevbe.business.be.impl.ServizioAttivoServiceImpl;
import it.csi.scerev.scerevbe.business.be.impl.TestApiServiceImpl;

@ApplicationPath("restfacade/be")
public class ScerevbeRestApplication extends Application {
	private Set<Object> singletons = new HashSet<Object>();
	private Set<Class<?>> empty = new HashSet<Class<?>>();

	public ScerevbeRestApplication() {
		singletons.add(new CittadinanzeApiServiceImpl());
		singletons.add(new MotivazioniSoggiornoApiServiceImpl());
		singletons.add(new MotivazioniDomicilioApiServiceImpl());
		singletons.add(new TipologieMedicoApiServiceImpl());
		singletons.add(new CittadiniApiServiceImpl());
		singletons.add(new DisponibilitaApiServiceImpl());
		singletons.add(new ServizioAttivoServiceImpl());
		singletons.add(new TestApiServiceImpl());
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
