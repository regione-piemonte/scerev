/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scadenzaassistenza.logger;

import org.apache.log4j.Logger;

public class BatchLoggerFactory {

	public static Logger getLogger(Class<?> clazz) {
		Logger logger = Logger.getLogger(clazz.getName());

		return logger;
	}

	public static Logger getLogger(Object obj) {
		Logger logger = Logger.getLogger(obj.getClass().getName());
		return logger;
	}
}
