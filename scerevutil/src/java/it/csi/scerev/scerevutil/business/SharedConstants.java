/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevutil.business;

public class SharedConstants {
	// Types
	public final static String STRING_TYPE = "STRING";
	public final static String NUMERIC_TYPE = "NUMERIC";		

	//Facade Section 
	public final static String AUDIT_FACADE		= "auditFacade";
	public final static String MESSAGGIO_FACADE = "messaggioFacade";
	public final static String LISTE_FACADE		= "listeFacade";
	public static final String PRATICA_FACADE	= "praticaFacade";
	public static final String CITTADINO_FACADE = "cittadinoFacade";
	public static final String MEDICO_FACADE = "medicoFacade";
	public static final String CONTROLLO_FACADE = "controlloFacade";

	//operations
	public final static String OPERAZIONE_GET	= "READ";
	public final static String OPERAZIONE_POST 	= "INSERT/UPDATE";
	public final static String OPERAZIONE_DELETE= "UPDATE";
	public final static String KEY_OPER_OK = "OK";
	public final static String KEY_OPER_KO = "KO";
	
	// Errori condivisi
	public final static String ERROR_CODE_BACKEND_NOTFOUND = "ERR01";
	public final static String MESSAGE_OPESSAN_NOTFOUND = "ERR04";
	public final static String ERROR_CODE_PARAMETRI_OBBLIGATORI = "ERR09";
	public final static String ERROR_CODE_INTEGRITA = "ERR10";	
	public final static String ERROR_NOTIFICATORE = "ERR70";	
	public final static String ERROR_DATE_INCONSISTENTI = "ERR72";	
	public final static String ERROR_DATE_INCONSISTENTI_2 = "ERR73";	
	public static final String ERROR_DATE_START_INCONSISTENTE = "ERR74";
	public static final String ERROR_DATE_START_INCONSISTENTE_2 = "ERR75";
	public static final String ERROR_DATE_INCONSISTENTI_3 = "ERR76";
	public static final String ERROR_DATE_INCONSISTENTI_4 = "ERR77";
	public static final String ERROR_DATE_INCONSISTENTI_5 = "ERR78";
	public final static String ERROR_CODE_AURA_NOT_FOUND = "ERR15";
	public final static String ERROR_CODE_AURA_INSERTUPDATE_PROFILOANAGRAFICO = "ERR15"; //ERR20 not found
	public final static String ERROR_CODE_INTERNO_SERVER = "ERR62";
	public final static String ERROR_CODE_IRIDE = "ERR64";
	public final static String ERROR_CODE_IRIDE_NOT_FOUND = "ERR65";
	public final static String ERROR_CODE_AURA_DECEDUTO = "ERR80";
	public final static String ERROR_CODE_AURA_CF_MEF_DIVERSO = "ERR81";
	public final static String ERROR_CODE_ALLEGATO_NON_FIRMATO = "ERR90";
	
	// Messaggi condivisi
	public final static String MESSAGE_AURA_NOTFOUND = "MSG13";
	public final static String MESSAGE_RESIDENZA_VARIATA = "MSG14";

	public final static String TST = "IS_TST";	
	
//	public static final String CITTADINANZA_ITALIANA = "IT";	
	public final static String SCEREVIRIDE = "SCEREV";
	
	public static final String ROLE_ADMINISTRATOR = "OPERATORE_SUPERUSER";
	public static final String ROLE_OPERATORE = "OPERATORE_ASL_DISTRETTO";
	
	// Notificatore
	public static final String EMAIL_SUBJECT_BENEFICIARIO = "_email_subject_beneficiario";
	public static final String EMAIL_BENEFICIARIO = "_email_beneficiario";
	public static final String EMAIL_BENEFICIARIO_BY_DELEGATO = "_email_beneficiario_by_delegato";
	
	public static final String EMAIL_SUBJECT_DELEGATO = "_email_subject_delegato";
	public static final String EMAIL_DELEGATO = "_email_delegato";
	
	public static final String PUSH_TITLE_BENEFICIARIO = "_push_title_beneficiario";
	public static final String PUSH_BENEFICIARIO = "_push_beneficiario";
	public static final String PUSH_BENEFICIARIO_BY_DELEGATO = "_push_beneficiario_by_delegato";
	
	public static final String PUSH_TITLE_DELEGATO = "_push_title_delegato";
	public static final String PUSH_DELEGATO = "_push_delegato";
	
	public static final String SMS_BENEFICIARIO = "_sms_beneficiario";
	public static final String NOTIFICATORE_TEMPLATE = "NOTIFICATORE_TEMPLATE";
	
	public static final String COD_CITTADINANZA_ITALIANA = "COD_CITTADINANZA_ITALIANA";
	public static final String COD_REGIONE_PIEMONTE = "COD_REGIONE_PIEMONTE";
	
	public static final String MEX_TITLE_BENEFICIARIO = "_mex_title_beneficiario";
	public static final String MEX_BENEFICIARIO = "_mex_beneficiario";
	public static final String MEX_BENEFICIARIO_BY_DELEGATO = "_mex_beneficiario_by_delegato";

	public static final String MEX_TITLE_DELEGATO = "_mex_title_delegato";
	public static final String MEX_DELEGATO = "_mex_delegato";
	
	public static final String MEX_CALL_TO_ACTION_BENEFICIARIO = "_mex_callToAction_beneficiario";
	public static final String MEX_CALL_TO_ACTION_DELEGATO = "_mex_callToAction_delegato";
	
	
	// Stato 
	public static final String STATO_IN_LAVORAZIONE = "In Lavorazione";
	public static final String STATO_COMPLETATA = "Completata";
	public static final String STATO_INVIATA = "Inviata";
	public static final String STATO_ANNULLATA = "Annullata";
	public static final String STATO_BOZZA = "Bozza";
	public static final String STATO_CHIUSA = "Chiusa";
	public static final String STATO_DOMANDA_REVOCA = "Domanda di revoca";
	
	public static final String EXTRA_REGIONE = "Extra-regione";

	public static final String SCELTA = "S";
	public static final String REVOCA = "R";
	public static final String PEDIATRA = "PLS";
	public static final String MEDICO = "MMG";
	

	public static final String CITTADINANZA_ITALIANA = "ITALIANA (ITALIA)";
	
	//Pratica Tipo
	public static final String DELEGANTE_MAGGIORENNE_TYPE = "Delegante maggiorenne";
	public static final String DELEGANTE_MINORENNE_TYPE = "Delegante minorenne";
	public static final String ASSISTITO_ITALIANO_TYPE = "Assistito italiano";
	public static final String ASSISTITO_COMUNITARIO_TYPE = "Assistito straniero comunitario";
	public static final String ASSISTITO_EXTRACOMUNITARIO_TYPE = "Assistito straniero non comunitario";
	public static final String VARIAZIONE_INDIRIZZO_RESIDENZA_TYPE = "Variazione indirizzo di residenza e di domicilio";
	public static final String VARIAZIONE_RESIDENZA_TYPE = "Variazione solo indirizzo di residenza";
	public static final String NO_VARIAZIONE_TYPE = "No variazione indirizzo di residenza e di domicilio";
	public static final String VARIAZIONE_DOMICILIO_TYPE = "Variazione solo indirizzo di domicilio";
	public static final String DEROGA_STUDIO_TYPE = "Domicilio per studio";
	public static final String DEROGA_CURA_TYPE = "Domicilio per cura";
	public static final String DEROGA_LAVORO_TYPE = "Domicilio per lavoro";
	public static final String DOMICILIO_RICONG_TYPE = "Domicilio per ricongiungimento familiare";
	public static final String DEROGA_RICONG_TYPE = "Deroga per ricongiungimento familiare";
	public static final String DEROGA_PLS_TYPE = "Deroga pediatra";
	public static final String DEROGA_TERR_TYPE = "Deroga per ambito territoriale";
	public static final String DEROGA_ASSOC_TYPE = "Deroga per associazione";
	public static final String COD_DOMICILIO_RICONG_5_TYPE = "Motivi domicilio 5";
	public static final String COD_DOMICILIO_RICONG_6_TYPE = "Motivi domicilio 6";
	public static final String RINNOVO_ASSISTENZA_TYPE = "Rinnovo assistenza";

	public static final String DATA_ILLIMITATA = "31/12/9999";
	public static final String REPORT_METADATA_AUTHOR = "SISTEMA PIEMONTE";
	public static final String REPORT_ALLOWED_PERMISSIONS_HINT = "PRINTING";
	
	
	public static final String ASSISTENZA_ILLIMITATA = "illimitata";
	public static final String ASSISTENZA_TEMPORANEA = "temporanea";

	public static final String PRATICA_CHIUSURA_AUTOMATICA = "AUTOMATICA";
	
	public static final String ERRORE_AURA = "AURA_ERROR";
	
	public static final String ID_STATO_ITALIA_MEF = "10006030";
	public static final String MESSAGGIO_36 = "MSG36";
	
	public static final String CAUSALE_RICUSAZIONE = "25";

}
