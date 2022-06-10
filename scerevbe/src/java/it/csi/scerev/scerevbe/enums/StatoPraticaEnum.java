/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevbe.enums;

public enum StatoPraticaEnum {
	BOZZA("1"),
	INVIATA("2"),
	ANNULLATA("3"),
	IN_LAVORAZIONE("4"),
	IN_REVISIONE("5"),
	COMPLETATA("6"),
	RESPINTA("7"),
	CHIUSA("8");

    private final String key;

    StatoPraticaEnum(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
    
//    BOZZA("1","BOZZA"),
//	INVIATA("2","INVIATA"),
//	ANNULLATA("3","ANNULLATA"),
//	IN_LAVORAZIONE("4","IN LAVORAZIONE"),
//	IN_REVISIONE("5","IN REVISIONE"),
//	COMPLETATA("6","COMPLETATA"),
//	RESPINTA("7","RESPINTA"),
//	CHIUSA("8","CHIUSA");
//
//    private final String key;
//    private final String value;
//
//    StatoPraticaEnum(String key, String value) {
//        this.key = key;
//        this.value = value;
//    }
//
//    public String getKey() {
//        return key;
//    }
//    public String getValue() {
//        return value;
//    }
}
