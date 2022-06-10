/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevbe.enums;

public enum MotivazioneDomicilioEnum {
	LAVORO("1"),
	STUDIO("2"),
	CURA("3"),
	RICONGIUNGIMENTO_FAMILIARE("4"),
	RICONGIUNGIMENTO_FAMILIARE_LAVORATORE("5"),
	RICONGIUNGIMENTO_FAMILIARE_MOTIVI_DI_SALUTE("6");
	

    private final String key;

    MotivazioneDomicilioEnum(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
    
}
