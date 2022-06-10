/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevbe.util;

public class Constants {
	public final static String COMPONENT_NAME 							= "scerev";
	public final static String AURA_COMPONENT_NAME 						= "SCEREV";
	public static final String UTENTE_DEFAULT_SCEREV_FO 				= "UTENTE_DEFAULT_SCEREV_FO";
	public static final String ID_APP									= "SCEREVBE";
	
	//errori specializzati per servizi
	public final static String ERROR_500_GETLISTACITTADINANZE 			= "ERR54";
	public final static String ERROR_500_ANNULLARICHIESTARIMUOVIBOZZA	= "ERR62";
	public final static String ERROR_500_GETLISTAMOTIVAZIONSOGGIORNO 	= "ERR55";
	public final static String ERROR_500_GETLISTAMOTIVAZIONIDOMICILIO 	= "ERR56";
	public final static String ERROR_500_GETLISTATIPOLOGIEMEDICO 		= "ERR57";
	public final static String ERROR_500_GETINFORMAZIONIASSISTITO		= "ERR62";
	public final static String ERROR_500_CONTROLLOCOMBINAZIONE			= "ERR62";
	public final static String ERROR_500_STAMPACERTIFICATOREVOCA  		= "ERR62";
	public final static String ERROR_500_STAMPATESSERINOMEDICO 			= "ERR62";
	public final static String ERROR_500_STAMPAALLEGATO		 			= "ERR62";
	public final static String ERROR_500_RICHIESTANUOVOMEDICO			= "ERR84";
	public final static String ERROR_500_STAMPAMODULODELEGANTE			= "ERR62";
	public final static String ERROR_500_CONTROLLOALLEGATI				= "ERR62";
	public final static String ERROR_500_REVOCAMEDICO					= "ERR62";
	public final static String ERROR_AURA_NF_GETINFORMAZIONIASSISTITO 	= "ERR58";
	public static final String ERROR_AURA_CITTADINO_DECEDUTO 			= "ERR80";
	public static final String ERROR_AURA_DISALLINEAMENTO_CF_MEF		= "ERR81";
	public static final String ESISTE_PRATICA_PENDENTE 					= "ERR82";
	public static final String ERROR_PRATICA_NON_GESTITA 				= "ERR83";
	public final static String ERROR_AURA_TS_NF						 	= "ERR86";

	public static final String ERROR_404_NOT_FOUND 						= "ERR58";
	public static final String ERROR_MEDICO_GIA_MONITORATO				= "ERR85";
	
	public final static String STATO_ASSISTENZA_ATTIVA					= "ATTIVA";
	public final static String STATO_ASSISTENZA_NON_ATTIVA				= "NON ATTIVA";

	public final static String ENCRYPTION_KEY_PARAM 					= "encryption.key";
	
	public static final String STAMPA_RICHIESTA_MEDICO_PER_MINORENNE 	= "/report/RicMedMin.jasper";
	public static final String STAMPA_RICHIESTA_MEDICO_PER_MAGGIORENNE 	= "/report/RicMedMag.jasper";
	public static final String STAMPA_MODULO_DELEGANTE                  = "/report/ModuloDelegante.jasper";
	public static final String REPORT_MESSAGGIO_1 						= "MESSAGGIO1";
	public static final String REPORT_MESSAGGIO_2 						= "MESSAGGIO2";
	public static final String REPORT_MESSAGGIO_3 						= "MESSAGGIO3";
	
	public static final String X_REQUEST_ID		 						= "X-Request-ID";
	
	public static final int MASSIMALE_ILLIMITATO						= 9999999; 
	
}
