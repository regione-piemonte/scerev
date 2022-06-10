/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevutil.business;

import java.util.HashMap;
import java.util.Map;

//import javax.persistence.TypedQuery;

public class Util {
	
//	public static  <T> T getSingleResult(TypedQuery<T> query) {
//		List<T> results = query.getResultList();
//		if (!results.isEmpty())
//			return (T) results.get(0);
//		else
//			return null;
//	}

//	public static DataInizioFine getDataInizioFine(String ggMM) {
//		DataInizioFine output = new DataInizioFine();
//			String today = Converter.getData(new Date());
//			output.setDataRichiesta(today);
//			output.setInizioValidita(today);
//			output.setFineValidita(getFineValidita(today, ggMM));
//			
//			return output;
//	}
	
//	private static String getFineValidita(String data, String ggMM) {
//
//		String annoCorrente = Converter.getAnno(new Date());
//		String ggmmaaaa = ggMM + "/" + annoCorrente;
//		Date scadCandidate = Converter.getData(ggmmaaaa);
//		Date today = Converter.getData(data);
//		if (scadCandidate.compareTo(today) <= 0) {
//			// torna scadCandidate+1anno
//			scadCandidate = Converter.aggiungiAnnoAData(scadCandidate, 1);
//			return Converter.getData(scadCandidate);
//		} else {
//			return ggmmaaaa;
//		}
//	}

	/**
	 * Validazione sintattica e semantica dei parametri di input di tutti i servizi rest
	 * @param xRequestId
	 * @param xForwardedFor
	 * @param xCodiceServizio
	 * @return
	 */
	public static Map<String, String> validate(String xRequestId, String xForwardedFor, String xCodiceServizio) {
		Map<String, String> errors = new HashMap<String, String>();
		if (xRequestId == null || xRequestId.trim().length()==0)
			errors.put("ERR09", "X-Request-ID");
		else if (xForwardedFor == null || xForwardedFor.trim().length()==0)
			errors.put("ERR09", "X-Forwarded-For");
		else if (xCodiceServizio == null || xCodiceServizio.trim().length()==0)
			errors.put("ERR09", "X-Codice-Servizio");
		else if (!Checker.isUuidValido(xRequestId))
			errors.put("ERR10", "X-Request-ID");
		return errors;
	}
	
	/**
	 * Costruzione onfly del messaggio di errore 
	 * @param messaggio messaggio registrato nel db (es. "Parametri obbligatori non presenti (SPECIFICARE)")
	 * @param dettaglio testo che prendera' il posto del "SPECIFICARE"
	 * @return messaggio di errore completo (es. "Parametri obbligatori non presenti (cognome))
	 */
	public static String composeMessage(String messaggio, String dettaglio) {
		if (Checker.isValorizzato(dettaglio)) {
			if (messaggio.contains("SPECIFICARE"))
				messaggio = messaggio.replaceAll("SPECIFICARE", dettaglio);
			else
				messaggio = messaggio+dettaglio;
		}
		return messaggio;
	}
}
