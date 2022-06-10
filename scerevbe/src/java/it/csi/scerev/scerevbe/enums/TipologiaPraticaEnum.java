/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevbe.enums;

public enum TipologiaPraticaEnum {
	BOZZA("BOZZA"),
	AUTOMATICA("AUTOMATICA"),
	BACK_OFFICE("BACK-OFFICE");

	private final String key;

	TipologiaPraticaEnum(String key) {
		this.key = key;
	}

	public String getKey() {
		return key;
	}
}
