/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevbe.business.facade.impl;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.NotFoundException;
import javax.xml.datatype.XMLGregorianCalendar;

import it.csi.def.opessanws.opessan.SoggettoOpessanBody;
import it.csi.def.opessanws.opessan.Associazioni.AssociazioneAggregazione;
import it.csi.def.opessanws.opessan.InfoRapportoDiLavoro;
import it.csi.scerev.scerevbe.business.facade.interfaces.CittadinoFacadeIf;
import it.csi.scerev.scerevbe.business.serviziesterni.ServiziEsterniClient;
import it.csi.scerev.scerevbe.dto.ModelAmbulatorioLoccsiMedico;
import it.csi.scerev.scerevbe.dto.ModelAsl;
import it.csi.scerev.scerevbe.dto.ModelCambioMedicoRichiestaDatiAggiuntivi.LavoroTipologiaEnum;
import it.csi.scerev.scerevbe.dto.ModelCittadinoDomicilio;
import it.csi.scerev.scerevbe.dto.ModelCittadinoInfoCustom;
import it.csi.scerev.scerevbe.dto.ModelCittadinoResidenza;
import it.csi.scerev.scerevbe.dto.Payload;
import it.csi.scerev.scerevbe.dto.Payload3;
import it.csi.scerev.scerevbe.enums.MotivazioneDomicilioEnum;
import it.csi.scerev.scerevbe.util.Constants;
import it.csi.scerev.scerevjpa.business.dao.interfaces.DataDaoIf;
import it.csi.scerev.scerevjpa.business.entity.ScerevDAsl;
import it.csi.scerev.scerevjpa.business.entity.ScerevDPraticaStato;
import it.csi.scerev.scerevjpa.business.entity.ScerevLAudit;
import it.csi.scerev.scerevjpa.business.entity.ScerevTCronologia;
import it.csi.scerev.scerevjpa.business.entity.ScerevTFile;
import it.csi.scerev.scerevjpa.business.entity.ScerevTMonitoraggio;
import it.csi.scerev.scerevjpa.business.entity.ScerevTPratica;
import it.csi.scerev.scerevserviziesterni.aura.find.DatiAnagrafici;
import it.csi.scerev.scerevserviziesterni.aura.get.SoggettoAuraBodyNew;
import it.csi.scerev.scerevserviziesterni.aura.getUltimoMovimentoDiRevoca.ResponseDelegate;
import it.csi.scerev.scerevserviziesterni.aura.getasldistrettoambito.ASLDistrettoAmbitoResponse;
import it.csi.scerev.scerevserviziesterni.ensemble.interrogamefscerev.InterrogaMefScerevResult;
import it.csi.scerev.scerevutil.business.Checker;
import it.csi.scerev.scerevutil.business.Converter;
import it.csi.scerev.scerevutil.business.SharedConstants;
import it.csi.scerev.scerevutil.business.exception.AllegatoNotFoundException;
import it.csi.scerev.scerevutil.business.exception.AuraNotFoundException;
import it.csi.scerev.scerevutil.business.exception.OpessanNotFoundException;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.export.SimplePdfReportConfiguration;



public class CittadinoFacade implements CittadinoFacadeIf {
	private DataDaoIf dataDao;		

	public DataDaoIf getDataDao() {
		return dataDao;
	}

	public void setDataDao(DataDaoIf dataDao) {
		this.dataDao = dataDao;
	}

	/**
	 * Effettua il calcolo della correttezza per la tripla ASL di
	 * Residenza/Domicilio/Assistenza
	 * 
	 * @throws AuraNotFoundException
	 */
	public boolean checkCorrettezzaResidenzaDomicilioAssistenza(Payload payload, String citId,
			ScerevLAudit audit, DatiAnagrafici cittadinoFind, SoggettoAuraBodyNew cittadinoGet) throws AuraNotFoundException {
		ModelAsl aslResidenza = calcolaResidenza(payload.getResidenza(), citId, audit, cittadinoFind, cittadinoGet);
		ModelAsl aslDomicilio = calcolaDomicilio(payload.getDomicilio(), citId, audit, cittadinoFind, cittadinoGet);
		ModelAsl aslIscrizione = calcolaIscrizione(payload);
		
		if(aslResidenza.equals(aslDomicilio) && aslResidenza.equals(aslIscrizione)) return true; // 1-1-1
		else if(aslResidenza.equals(aslDomicilio) && !aslResidenza.equals(aslIscrizione)) return false; //return true; // 1-1-2
		else if(!aslResidenza.equals(aslDomicilio) && aslDomicilio.equals(aslIscrizione)) return true; // 1-2-2
		else if(aslResidenza.equals(aslIscrizione) && !aslDomicilio.equals(aslIscrizione)) return false; // 1-2-1
		else if(!aslResidenza.equals(aslIscrizione) && !aslDomicilio.equals(aslIscrizione) && !aslResidenza.equals(aslDomicilio)) return false; // 1-2-3
		else if(aslResidenza.getDescrizione().equals(SharedConstants.EXTRA_REGIONE) && aslDomicilio.equals(aslIscrizione)) return true; // extra-1-1
		else if(aslResidenza.getDescrizione().equals(SharedConstants.EXTRA_REGIONE) && !aslDomicilio.equals(aslIscrizione)) return false; // extra-1-2
		else if(aslResidenza.equals(aslIscrizione) && aslDomicilio.getDescrizione().equals(SharedConstants.EXTRA_REGIONE)) return false; // 1-extra-1
		else if(!aslResidenza.equals(aslIscrizione) && aslDomicilio.getDescrizione().equals(SharedConstants.EXTRA_REGIONE)) return false; // 1-extra-2
		else if(aslResidenza.getDescrizione().equals(SharedConstants.EXTRA_REGIONE) && aslDomicilio.getDescrizione().equals(SharedConstants.EXTRA_REGIONE)) return false; // extra-extra-1
		return false;
	}

	public ModelAsl calcolaIscrizione(Payload payload) {
		ModelAsl iscrizioneReturn = new ModelAsl();
		iscrizioneReturn.setDescrizione(payload.getMedico().getAsl().getDescrizione());
		iscrizioneReturn.setId(payload.getMedico().getAsl().getId());
		return iscrizioneReturn;
	}

	public ModelAsl calcolaDomicilio(ModelCittadinoDomicilio domicilio, String citId, ScerevLAudit audit, DatiAnagrafici cittadinoFind, SoggettoAuraBodyNew cittadinoGet) throws AuraNotFoundException {
		ModelAsl domicilioReturn = new ModelAsl();
		if (domicilio == null) {
			// Se NULL, si recupera l’ASL di domicilio dal servizio di AURA AnagrafeSanitaria
			if(cittadinoFind == null ||	cittadinoGet == null)
				throw new AuraNotFoundException();

			String aslDomicilio = cittadinoGet.getInfoSan() != null ? cittadinoGet.getInfoSan().getAslDomicilio() : null;
			ScerevDAsl asl = dataDao.getAsl(aslDomicilio);

			// Se nella tabella del DB, SCEREV_D_ASL non si trova il codice dell’asl di
			// domicilio (oppure si trova, ma il valore del corrispettivo CODICE_REGIONE è
			// diverso da 010),
			// significa che l’asl ricercata non appartiene alla regione Piemonte, perciò
			// sia l’id che la descrizione dell’ASL di domicilio da
			// restituire in output assumeranno il valore ‘Extra-regione'
			if (asl == null || asl.getCodiceRegione() != "010") {
				domicilioReturn.setDescrizione(SharedConstants.EXTRA_REGIONE);
				domicilioReturn.setId(SharedConstants.EXTRA_REGIONE);
			} else {
				domicilioReturn.setDescrizione(asl.getDenominazioneAzienda());
				domicilioReturn.setId(aslDomicilio);
			}
		} else {
			// Se valorizzato, si recupera l’ASL di domicilio dal servizio di AURA getASLDistrettoAmbito.
			// Si invoca per mezzo del comune di domicilio e restituisce ASL, distretto e ambito di domicilio.
			ASLDistrettoAmbitoResponse mapReturn = ServiziEsterniClient.getInstance().getASLDistrettoAmbito(domicilio.getComune(), SharedConstants.MEDICO, audit);
			String descrAmbito = mapReturn.getAmbito();
			String descrDistretto = mapReturn.getDistretto();

			if (descrAmbito == null && descrDistretto == null) {
				// Se i campi descrAmbito e descrDistretto sono entrambi NULL
				// allora sia l’id che la descrizione dell’ASL di domicilio assumeranno il
				// valore ‘Extra-regione’.
				domicilioReturn.setDescrizione(SharedConstants.EXTRA_REGIONE);
				domicilioReturn.setId(SharedConstants.EXTRA_REGIONE);
			} else {
				domicilioReturn.setDescrizione(mapReturn.getAsl());
				domicilioReturn.setId(mapReturn.getCodiceAsl());
			}
		}
		return domicilioReturn;
	}

	public ModelAsl calcolaResidenza(ModelCittadinoResidenza residenza, String citId, ScerevLAudit audit, DatiAnagrafici cittadinoFind, SoggettoAuraBodyNew cittadinoGet) throws AuraNotFoundException {
		ModelAsl residenzaReturn = new ModelAsl();
		if (residenza == null) {
			// Se NULL, si recupera l’ASL di residenza dal servizio di AURA AnagrafeSanitaria
			
			if(cittadinoFind == null ||	cittadinoGet == null)
				throw new AuraNotFoundException();

			String aslResidenza = cittadinoGet.getInfoSan() != null ? cittadinoGet.getInfoSan().getAslResidenza() : null;
			ScerevDAsl asl = dataDao.getAsl(aslResidenza);

			// Se nella tabella del DB, SCEREV_D_ASL non si trova il codice dell’asl di
			// residenza (oppure si trova, ma il valore del corrispettivo CODICE_REGIONE è
			// diverso da 010),
			// significa che l’asl ricercata non appartiene alla regione Piemonte, perciò
			// sia l’id che la descrizione dell’ASL di residenza da
			// restituire in output assumeranno il valore ‘Extra-regione'
			if (asl == null || asl.getCodiceRegione() != "010") {
				residenzaReturn.setDescrizione(SharedConstants.EXTRA_REGIONE);
				residenzaReturn.setId(SharedConstants.EXTRA_REGIONE);
			} else {
				residenzaReturn.setDescrizione(asl.getDenominazioneAzienda());
				residenzaReturn.setId(aslResidenza);
			}
		} else {
			// Se valorizzato, si recupera l’ASL di residenza dal servizio di AURA getASLDistrettoAmbito.
			// Si invoca per mezzo del comune di residenza e restituisce ASL, distretto e ambito di residenza.
			ASLDistrettoAmbitoResponse mapReturn = ServiziEsterniClient.getInstance().getASLDistrettoAmbito(residenza.getComune(), SharedConstants.MEDICO, audit);
			
			String descrAmbito = mapReturn.getAmbito();
			String descrDistretto = mapReturn.getDistretto();

			if (descrAmbito == null && descrDistretto == null) {
				// Se i campi descrAmbito e descrDistretto sono entrambi NULL
				// allora sia l’id che la descrizione dell’ASL di residenza assumeranno il
				// valore ‘Extra-regione’.
				residenzaReturn.setDescrizione(SharedConstants.EXTRA_REGIONE);
				residenzaReturn.setId(SharedConstants.EXTRA_REGIONE);
			} else {
				residenzaReturn.setDescrizione(mapReturn.getAsl());
				residenzaReturn.setId(mapReturn.getCodiceAsl());
			}
		}
		return residenzaReturn;
	}
	
	/**
	 * Controllo sulla variazione di una tra ASL di Residenza o ASL di Domicilio
	 * rispetto a quelle presenti su AURA
	 * @throws AuraNotFoundException 
	 * 
	 */
	public boolean checkVariazioneASLResidenzaDomicilio(Payload payload, String citId, ScerevLAudit audit, DatiAnagrafici cittadinoFind, SoggettoAuraBodyNew cittadinoGet) throws AuraNotFoundException {
		// In caso di rinnovo assistenza il flag sara' sempre true
		if(payload.getRinnovoAssistenza() != null && payload.getRinnovoAssistenza()) return true;
		
		boolean variazioneResidenza;
		boolean variazioneDomicilio;
		boolean variazioneAssistenza;
		
		if(payload.getMedico().getAsl() != null) variazioneAssistenza = checkAssistenza(payload, citId, audit, cittadinoFind, cittadinoGet);
		else variazioneAssistenza = false;
		
		if(payload.getResidenza()!=null) variazioneResidenza = checkResidenza(payload, citId, audit, cittadinoFind, cittadinoGet);
		else variazioneResidenza = false;
		
		if(payload.getDomicilio()!=null) variazioneDomicilio = checkDomicilio(payload, citId, audit, cittadinoFind, cittadinoGet);
		else variazioneDomicilio = false;
		
		return variazioneAssistenza || variazioneResidenza || variazioneDomicilio;
	}

	private boolean checkAssistenza(Payload payload, String citId, ScerevLAudit audit, DatiAnagrafici cittadinoFind, SoggettoAuraBodyNew cittadinoGet) throws AuraNotFoundException {	
		ModelAsl aslIscrizione = calcolaIscrizione(payload);
		
		if(cittadinoFind == null || cittadinoGet == null) {
			//jira 23 se cittadino non presente su AURA variazione a true
			return true;
			//throw new AuraNotFoundException();
			
		}

		if(cittadinoGet.getInfoSan() == null || cittadinoGet.getInfoSan().getAslAssistenza()==null) return true;
		String asl = cittadinoGet.getInfoSan().getAslAssistenza().substring(3,6); 
		if(!asl.equals(aslIscrizione.getId())) return true;
//		if(!cittadinoGet.getInfoSan().getAslDomicilio().equals(mapReturn.getAsl())) return true;
		
		return false;
	}

	private boolean checkDomicilio(Payload payload, String citId, ScerevLAudit audit, DatiAnagrafici cittadinoFind, SoggettoAuraBodyNew cittadinoGet) throws AuraNotFoundException {
		ASLDistrettoAmbitoResponse mapReturn;
		try {
			mapReturn = ServiziEsterniClient.getInstance().getASLDistrettoAmbito(payload.getDomicilio().getComune(), SharedConstants.MEDICO, audit);
		} catch (Exception e) {
			throw new AuraNotFoundException();
		}
		if(cittadinoFind == null || cittadinoGet == null) {
			//jira 23 se cittadino non presente su AURA variazione a true
			return true;
			//throw new AuraNotFoundException();
			
		}

		if(cittadinoGet.getInfoSan() == null || cittadinoGet.getInfoSan().getAslDomicilio()==null) return true;
		String asl = cittadinoGet.getInfoSan().getAslDomicilio().substring(3,6); 
		if(!asl.equals(mapReturn.getCodiceAsl())) return true;
//		if(!cittadinoGet.getInfoSan().getAslDomicilio().equals(mapReturn.getAsl())) return true;
		
		return false;
	}

	private boolean checkResidenza(Payload payload, String citId, ScerevLAudit audit, DatiAnagrafici cittadinoFind, SoggettoAuraBodyNew cittadinoGet) throws AuraNotFoundException {
		ASLDistrettoAmbitoResponse mapReturn;
		try {
			mapReturn = ServiziEsterniClient.getInstance().getASLDistrettoAmbito(payload.getResidenza().getComune(), SharedConstants.MEDICO, audit);
		} catch (Exception e) {
			throw new AuraNotFoundException();
		}
		if(cittadinoFind == null || cittadinoGet == null) {
			//jira 23 se cittadino non presente su AURA variazione a true
			return true;
			//throw new AuraNotFoundException();
		}
		if(cittadinoGet.getInfoSan() == null || cittadinoGet.getInfoSan().getAslResidenza()==null) return true;
		String asl = cittadinoGet.getInfoSan().getAslResidenza().substring(3,6); 
		if(!asl.equals(mapReturn.getCodiceAsl())) return true;
		//if(!cittadinoGet.getInfoSan().getAslResidenza().equals(mapReturn.getAsl())) return true;
		
		return false;
	}
	
	/**
	 * Calcolo della Deroga Territoriale: Se Ambito Assistito <> Ambito Medico
	 * allora Deroga Territoriale:= TRUE
	 * 
	 * @throws AuraNotFoundException
	 */
	public boolean checkDerogaTerritoriale(Payload payload, String citId, ScerevLAudit audit, DatiAnagrafici cittadinoFind, SoggettoAuraBodyNew cittadinoGet) throws AuraNotFoundException {
		ModelAsl x = new ModelAsl();
		x.setId("301");
		x.setDescrizione("TO");	
		ModelAsl y = new ModelAsl();
		y.setId("211");
		y.setDescrizione("CN2");
		
		// Il calcolo della Deroga Territoriale è effettuato prendendo in analisi ASL di iscrizione e ASL di domicilio: 
		// Se queste due ASL coincidono (sia id che descrizione) e sono diverse da X (dove X sta per una variabile i cui
		// valori sono descritti nella tabella successiva), allora si considera:		

		// In caso di rinnovo assistenza e domicilio invariato il flag sara' sempre false
		if( payload.getRinnovoAssistenza() != null && payload.getRinnovoAssistenza() 
				&& isDomicilioInvariato(payload, cittadinoGet) )
			return false;
			
		// 03/12/2020 si controlla solo l'id
		ModelAsl aslIscrizione = calcolaIscrizione(payload);
		
		if(aslIscrizione.getId().equalsIgnoreCase(y.getId())) {
			String assistito = getDistrettoDomicilio(payload, citId, audit, cittadinoFind, cittadinoGet);
			String medico = payload.getMedico().getDistretto().getDescrizione();
			
			if(!assistito.equals(medico)) return true;
		}
		
		ModelAsl aslDomicilio = calcolaDomicilio(payload.getDomicilio(), citId, audit, cittadinoFind, cittadinoGet);

		if(aslIscrizione.getId().equalsIgnoreCase(aslDomicilio.getId()) && 
				!aslIscrizione.getId().equalsIgnoreCase(x.getId()) && 
				!aslIscrizione.getId().equalsIgnoreCase(y.getId())) {
			String assistito = getAmbitoDiDomicilio(payload, citId, audit, cittadinoFind, cittadinoGet);
			String medico = payload.getMedico().getAmbito().getAmbitoDescrizione();
			
			if(assistito != null && medico != null && !assistito.equalsIgnoreCase(medico)) return true;
			else {
				String distAssistito = getDistrettoDomicilio(payload, citId, audit, cittadinoFind, cittadinoGet);
				String distMedico = payload.getMedico().getDistretto().getDescrizione();
				if(!distAssistito.equalsIgnoreCase(distMedico)) return true;
			}
		}
		return false;
	}

	private boolean isDomicilioInvariato(Payload payload, SoggettoAuraBodyNew cittadinoGet) {
		return payload.getDomicilio() == null || 
				(cittadinoGet != null && 
				cittadinoGet.getInfoAnag() != null && cittadinoGet.getInfoAnag().getDomicilio() != null && 
				payload.getDomicilio().getCap().equalsIgnoreCase(cittadinoGet.getInfoAnag().getDomicilio().getCap()) &&
				payload.getDomicilio().getCivico().equalsIgnoreCase(cittadinoGet.getInfoAnag().getDomicilio().getNumCivico()) &&
				payload.getDomicilio().getCodComune().equalsIgnoreCase(cittadinoGet.getInfoAnag().getDomicilio().getCodComune()) &&
				payload.getDomicilio().getComune().equalsIgnoreCase(cittadinoGet.getInfoAnag().getDomicilio().getDescComune()) &&
				payload.getDomicilio().getIndirizzo().equalsIgnoreCase(cittadinoGet.getInfoAnag().getDomicilio().getIndirizzo()));
	}

	private String getDistrettoDomicilio(Payload payload, String citId, ScerevLAudit audit, DatiAnagrafici cittadinoFind, SoggettoAuraBodyNew cittadinoGet) throws AuraNotFoundException {
		ASLDistrettoAmbitoResponse mapReturn;
		String tipologia = null;
		try {
			tipologia = payload.getMedico().getTipologia().getId();
		} catch (Exception e) {
			tipologia = SharedConstants.MEDICO;
		}
		if(payload.getDomicilio() == null) {
			try {	
				if(cittadinoFind == null || cittadinoGet == null)
					throw new AuraNotFoundException();
				if(cittadinoGet.getInfoAnag().getDomicilio() != null) { 
					mapReturn = ServiziEsterniClient.getInstance().getASLDistrettoAmbito(cittadinoGet.getInfoAnag().getDomicilio().getDescComune(), tipologia, audit);
				} else return "";
			} catch (Exception e) {
				throw new AuraNotFoundException();
			}
		} else {
			mapReturn = ServiziEsterniClient.getInstance().getASLDistrettoAmbito(payload.getDomicilio().getComune(), tipologia, audit);
		}
		return mapReturn.getDistretto();		
	}

	private String getAmbitoDiDomicilio(Payload payload, String citId, ScerevLAudit audit, DatiAnagrafici cittadinoFind, SoggettoAuraBodyNew cittadinoGet) throws AuraNotFoundException {
		ASLDistrettoAmbitoResponse mapReturn;
		String tipologia = null;
		try {
			tipologia = payload.getMedico().getTipologia().getId();
		} catch (Exception e) {
			tipologia = SharedConstants.MEDICO;
		}
		if(payload.getDomicilio() == null) {
			try {	
				if(cittadinoFind == null || cittadinoGet == null)
					throw new AuraNotFoundException();
				if(cittadinoGet.getInfoAnag().getDomicilio() != null) { 
					mapReturn = ServiziEsterniClient.getInstance().getASLDistrettoAmbito(cittadinoGet.getInfoAnag().getDomicilio().getDescComune(), tipologia, audit);
				} else return "";
			} catch (Exception e) {
				throw new AuraNotFoundException();
			}
		} else {
			mapReturn = ServiziEsterniClient.getInstance().getASLDistrettoAmbito(payload.getDomicilio().getComune(), tipologia, audit);
		}
		return mapReturn.getAmbito() ;		
	}
	
	/**
	 * Calcolo della Deroga per Associazione: Nel caso in cui tra i due Medici siano
	 * iscritti ad una stessa associazione, allora Deroga per Associazione sarà
	 * TRUE. In tutti gli altri casi FALSE
	 * @throws Exception 
	 */
	public boolean checkDerogaAssociazione(Payload payload, String citId, ScerevLAudit audit, DatiAnagrafici cittadinoFind, SoggettoAuraBodyNew cittadinoGet) throws Exception {
		
		// In caso di rinnovo assistenza il flag sara' sempre false
		if(payload.getRinnovoAssistenza() != null && payload.getRinnovoAssistenza()) return false;
		
		List<AssociazioneAggregazione> medicoSceltoList;
		List<AssociazioneAggregazione> medicoPrecedenteList;
		try {
			medicoSceltoList = ServiziEsterniClient.getInstance().getDettaglioOperatore(payload.getMedico().getId(), audit).getAssociazioniAggregazioni().getAssociazioneAggregazione();				
			
			if(cittadinoFind == null || cittadinoGet == null)
				throw new AuraNotFoundException();
			if (cittadinoGet.getInfoSan()==null || cittadinoGet.getInfoSan().getTipoMovimento() == null || cittadinoGet.getInfoSan().getTipoMovimento().equalsIgnoreCase(SharedConstants.REVOCA)) {
				return false;
			}
			else if (cittadinoGet.getInfoSan().getIdMedico()==null) {
				return false;
			}
			else
			medicoPrecedenteList = ServiziEsterniClient.getInstance().getDettaglioOperatore(cittadinoGet.getInfoSan().getIdMedico().toString(), audit).getAssociazioniAggregazioni().getAssociazioneAggregazione();
			
			for(AssociazioneAggregazione associazione : medicoSceltoList) {
				//SCEREV dovrà utilizzare esclusivamente le associazioni valide 				
				if(isAssociazioneValida(associazione)) {
					for(AssociazioneAggregazione associazionePrecedente : medicoPrecedenteList) {
						if(isAssociazioneValida(associazionePrecedente)){
							if(associazione.getDenominazione().equals(associazionePrecedente.getDenominazione())) return true;
						}
					}
				}
			}			
		} catch (AuraNotFoundException e) {
			//throw new AuraNotFoundException();
			//jira 23 se cittadino non presente su AURA deroga associazione a FALSE
			return false;
		} catch (OpessanNotFoundException e) {
//			throw new OpessanNotFoundException();
			// 22/12/2020
			return false;
		} catch (Exception e) {
			throw new Exception();
		} 
		return false;
	}

	private boolean isAssociazioneValida(AssociazioneAggregazione associazione) {
		if ( // data cessazione Null o se popolata, posteriore alla data di sistema
			(associazione.getDataCessazione() == null || Converter.getData(associazione.getDataCessazione()).after(new Date())) &&
			// dataInizioAdesione Anteriore alla data di sistema
			(Converter.getData(associazione.getDataInizioAdesione()).before(new Date())) &&
					// dataFineAdesione null o se popolata, posteriore alla data di sistema
			(associazione.getDataFineAdesione() == null	|| Converter.getData(associazione.getDataFineAdesione()).after(new Date())))
				return true;
		return false;
	}
	
	/**
	 * Calcolo della Deroga per Pediatra
	 * @throws AuraNotFoundException 
	 */
	public boolean checkDerogaMedicoPLS(Payload payload, String citId, ScerevLAudit audit, DatiAnagrafici cittadinoFind) throws AuraNotFoundException {			
		InterrogaMefScerevResult findCittadinoSuTS;
		XMLGregorianCalendar dataNascita;

		if (cittadinoFind != null)
			dataNascita = cittadinoFind.getDataNascita();
		else {
			// throw new AuraNotFoundException();
			// jira 23 se cittadino non presente su AURA recupeare dal mef la dataNascita
			try {
				findCittadinoSuTS = ServiziEsterniClient.getInstance().findCittadinoSuTS(citId, audit);
				dataNascita = findCittadinoSuTS.getDatanascita();
			} catch (Exception e) {
				throw new AuraNotFoundException();
			}
		}
		
		String tipologiaMedicoScelto = payload.getMedico().getTipologia().getId();
		Long eta = Converter.CalcolaEta(dataNascita);
		
		if(tipologiaMedicoScelto.equalsIgnoreCase(SharedConstants.PEDIATRA) && (14<=eta) && (eta<16) ) 
			return true;	
				
		return false;
	}
	
	/**
	 * Calcolo della Deroga per Medico di Medicina Generale 
	 * @throws AuraNotFoundException 
	 */
	public boolean checkDerogaMedicoMMG(Payload payload, String citId, ScerevLAudit audit, DatiAnagrafici cittadinoFind) throws AuraNotFoundException {			
		InterrogaMefScerevResult findCittadinoSuTS;
		XMLGregorianCalendar dataNascita;
		if(cittadinoFind != null)
			dataNascita = cittadinoFind.getDataNascita();
		else {
			//throw new AuraNotFoundException();
			//jira 23 se cittadino non presente su AURA recupeare dal mef la dataNascita
			try {
				findCittadinoSuTS = ServiziEsterniClient.getInstance().findCittadinoSuTS(citId, audit);
				dataNascita = findCittadinoSuTS.getDatanascita();
			} catch (Exception e1) {
				throw new AuraNotFoundException();
			}
		}
		
		String tipologiaMedicoScelto = payload.getMedico().getTipologia().getId();
		Long eta = Converter.CalcolaEta(dataNascita);
		
		if(tipologiaMedicoScelto.equalsIgnoreCase(SharedConstants.MEDICO) && (6<=eta) && (eta<14) )
			return true;
				
		return false;
	}

	/**
	 * Il sistema aggiorna la pratica presente nel DB, nella tabella
	 * SCEREV_T_PRATICA con il campo NUMERO_PRARICA= richiesta_id (ricevuto in
	 * input)
	 * @throws Exception 
	 */
	public String annullaRimuoviPratica(String richiestaId) throws Exception {
		ScerevTPratica pratica = dataDao.getPraticaPerNumero(Integer.parseInt(richiestaId));
		if(pratica == null) throw new Exception("Pratica non trovata");
		
		String messageReturn = null;
		if(pratica.getScerevDPraticaStato().getValorePraticaStato().equalsIgnoreCase(SharedConstants.STATO_BOZZA))
			messageReturn = dataDao.getMessaggioPerCodice("MSG24").getValoreMsg();
		else 
			messageReturn = dataDao.getMessaggioPerCodice("MSG23").getValoreMsg();
		
		List<ScerevDPraticaStato> listStati = dataDao.getStatiPratica();
		ScerevDPraticaStato statoAnnullato = null;		
		for(ScerevDPraticaStato stato : listStati) {
			if(stato.getValorePraticaStato().equalsIgnoreCase(SharedConstants.STATO_ANNULLATA)) statoAnnullato = stato;
		}
		
		pratica.setScerevDPraticaStato(statoAnnullato);		
		dataDao.updatePratica(pratica);		
		
		ScerevTCronologia cronologia = new ScerevTCronologia();
		cronologia.setScerevTPratica(pratica);
		cronologia.setUtente(pratica.getNomeAssistito()+" "+pratica.getCognomeAssistito());
		cronologia.setScerevDPraticaStato(statoAnnullato);
		cronologia.setDataOraInserimento(new Timestamp(System.currentTimeMillis()));
		cronologia.setDataCreazione(new Timestamp(System.currentTimeMillis()));
		cronologia.setDataModifica(new Timestamp(System.currentTimeMillis()));
		dataDao.insertCronologia(cronologia);			
		
		return messageReturn;
	}
	
	/**
	 * effettua la revoca del medico su db
	 */
	// 14/12/2020
	public String revocaMedico(SoggettoAuraBodyNew sog, SoggettoOpessanBody detOp, String citId, String shib, String motivo) {
		InfoRapportoDiLavoro rappLav = getRapportoDiLavoro(detOp.getInfoRappLavoro().getInfo());
		ScerevTPratica pRev = new ScerevTPratica();
		
		pRev.setTipologiaGestionePratica(SharedConstants.PRATICA_CHIUSURA_AUTOMATICA);
		pRev.setScerevDPraticaStato(dataDao.getStatoPratica(SharedConstants.STATO_DOMANDA_REVOCA));
		pRev.setCfDelegato(shib);
		pRev.setCfAssistito(citId);
		pRev.setNomeAssistito(sog.getInfoAnag().getDatiPrimari().getNome());
		pRev.setCognomeAssistito(sog.getInfoAnag().getDatiPrimari().getCognome());
		// 19/01/2021
//		pRev.setLuogoNascitaAssistito(sog.getInfoAnag().getDatiPrimari().getDescComuneNascita());
		pRev.setLuogoNascitaAssistito(sog.getInfoAnag().getDatiPrimari().getDescComuneNascita() != null ? 
				sog.getInfoAnag().getDatiPrimari().getDescComuneNascita() : 
					sog.getInfoAnag().getDatiPrimari().getDescStatoNascita());
		pRev.setDataNascitaAssistito(new Timestamp(sog.getInfoAnag().getDatiPrimari().getDataNascita().toGregorianCalendar().getTimeInMillis()));
		pRev.setSessoAssistito(sog.getInfoAnag().getDatiPrimari().getSesso());
		pRev.setScerevDCittadinanza(dataDao.getCittadinanzaByCod(sog.getInfoAnag().getDatiPrimari().getCodCittadinanza()));
		pRev.setScerevDAsl2(dataDao.getAsl(sog.getInfoSan().getAslAssistenza()));
		ScerevDAsl aslRes = dataDao.getAsl(sog.getInfoSan().getAslResidenza());
		if(aslRes != null) pRev.setAslResidenza(aslRes.getDenominazioneAzienda());
		else pRev.setAslResidenza(SharedConstants.EXTRA_REGIONE);
		pRev.setIdAuraMedico(sog.getInfoSan().getIdMedico().toString());
		pRev.setNomeMedico(sog.getInfoSan().getNomeMedico());
		pRev.setCognomeMedico(sog.getInfoSan().getCognomeMedico());
		pRev.setCodiceFiscaleMedico(sog.getInfoSan().getCodiceFiscaleMedico());
		pRev.setScerevDAsl3(dataDao.getAslByDenominazione(rappLav.getAzienda()));
		pRev.setScerevDDistretto(dataDao.getDistrettoByDesc(rappLav.getDistrettoCompetenza()));
		pRev.setTipologiaMedico(getQualificaMMGPLS(rappLav));
		pRev.setDataChiusura(new Timestamp(System.currentTimeMillis()));
		pRev.setDataRevocaMedico(new Timestamp(System.currentTimeMillis()));
		pRev.setDataCreazione(new Timestamp(System.currentTimeMillis()));
		pRev.setDataModifica(new Timestamp(System.currentTimeMillis()));
		pRev.setMotivoRevocaMedico(motivo);
		dataDao.updatePratica(pRev);
		
		return dataDao.getMessaggioPerCodice("MSG21").getValoreMsg();
	}
	
	
	/**
	 * Serve ad estrapolare il Rapporto di lavoro in essere. 
	 * FROM OPESSAN
	 */
	public InfoRapportoDiLavoro getRapportoDiLavoro(List<InfoRapportoDiLavoro> infoList) {
		for(InfoRapportoDiLavoro info : infoList) {
			if(Converter.getData(info.getDataInizioRappLavoro()).before(new Date())){
				if ( (info.getDataFine()== null || Converter.getData(info.getDataFine()).after(new Date())) && 
						(info.getDataInizioSosp() == null || 
							(info.getDataInizioSosp() != null && info.getDataFineSosp() != null && 
							 Converter.getData(info.getDataFineSosp()).before(new Date()))) ) {
					if(isQualificaMMGPLS(info)) { //FIXME: TEMP
						return info;
					}
				}
			}				
		}
		return null;
	}

	//FIXME: TEMP
	private boolean isQualificaMMGPLS(InfoRapportoDiLavoro info) {
		return info.getQualifica().equalsIgnoreCase("Titolare Generico") ||
				info.getQualifica().equalsIgnoreCase("Titolare Pediatra") ||
				info.getQualifica().equalsIgnoreCase("Supplente Generico") ||
				info.getQualifica().equalsIgnoreCase("Supplente Pediatra");
	}
	
	private String getQualificaMMGPLS(InfoRapportoDiLavoro info) {
		return info.getQualifica().equalsIgnoreCase("Titolare Generico") ||
				info.getQualifica().equalsIgnoreCase("Supplente Generico") ? SharedConstants.MEDICO :
				info.getQualifica().equalsIgnoreCase("Titolare Pediatra") ||
				info.getQualifica().equalsIgnoreCase("Supplente Pediatra") ? SharedConstants.PEDIATRA :
					null;
	}
	
	/**
	 * metodo che produce il file per StampaCertificatoRevoca
	 * @throws Exception 
	 * @throws AuraNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 * 
	 * */	
	public byte[] certificatoRevoca_getFile(String cit_Id, ScerevLAudit audit) throws AuraNotFoundException, Exception {
		System.setProperty("java.awt.headless", "true");
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		byte[] fileFinale = null;
		String nomeBeneficiario = null;
		String cognomeBeneficiario = null;
		String codFiscBeneficiario = null;
		String cittadinanzaBeneficiario = null;
		String luogoNascitaBeneficiario = null;
		Date dataNascitaBeneficiario_date = null;
		String dataNascitaBeneficiario_string = null;
		String residenzaBeneficiario = null;
		String domicilioBeneficiario = null;
		String denominazione_Asl = null;
		Timestamp dataRevocaAssistenza_timestamp = null;
		Date dataRevocaAssistenza_date = null;
		String dataRevocaAssistenza_string = null;

		// Cerco l'ultima pratica inerente il cittadino interessato, avente il campo "Data_Revoca_Assistenza" popolato
		ScerevTPratica pratica = dataDao.getUltimaPraticaAssistenzaRevocataCitt(cit_Id);

		if (pratica == null)
			throw new NotFoundException("Non ci sono pratiche revocate per questo codice fiscale");

		// recupero ASL di iscrizione
		ScerevDAsl asl_iscrizione_fromPratica = pratica.getScerevDAsl2();
		ScerevDAsl asl_iscrizione_info = dataDao.getAsl(asl_iscrizione_fromPratica.getCodiceAzienda());

		// Ricerco dati cittadino dato il cit_Id
		List<DatiAnagrafici> datiCittadini = ServiziEsterniClient.getInstance().findCittadino(cit_Id, null, null, null,	audit);
		
		if (datiCittadini != null && isDatiAnagraficiAttivi(datiCittadini)) {			
			// Dati Cittadino:
			nomeBeneficiario = pratica.getNomeAssistito();
			cognomeBeneficiario = pratica.getCognomeAssistito();
			codFiscBeneficiario = pratica.getCfAssistito();
			cittadinanzaBeneficiario = pratica.getScerevDCittadinanza().getValoreCittadinanza();
			luogoNascitaBeneficiario = pratica.getLuogoNascitaAssistito();
			dataNascitaBeneficiario_date = pratica.getDataNascitaAssistito();
			dataNascitaBeneficiario_string = Converter.getData(dataNascitaBeneficiario_date);
			residenzaBeneficiario = pratica.getScerevTLocalita1().getComune();
			domicilioBeneficiario = pratica.getScerevTLocalita2().getComune();

		} else {
			throw new AuraNotFoundException();
		}

		// Dati Revoca:
		denominazione_Asl = asl_iscrizione_info.getDenominazioneAzienda();
		dataRevocaAssistenza_timestamp = pratica.getDataRevocaAssistenza();
		dataRevocaAssistenza_date = new Date(dataRevocaAssistenza_timestamp.getTime());
		dataRevocaAssistenza_string = Converter.getData(dataRevocaAssistenza_date);

		// popolo i parametri da passare al report
		Map<String, Object> parameters = new HashMap<String, Object>();

		String nome_e_cognome_beneficiario = nomeBeneficiario + " " + cognomeBeneficiario;

		parameters.put("NOME+COGNOME_RICHIEDENTE", nome_e_cognome_beneficiario);
		parameters.put("CODICE_FISCALE_RICHIEDENTE", codFiscBeneficiario);
		parameters.put("CITTADINANZA_RICHIEDENTE", cittadinanzaBeneficiario);
		parameters.put("LUOGO_NASCITA_RICHIEDENTE", luogoNascitaBeneficiario);
		parameters.put("GIORNO_NASCITA_RICHIEDENTE", dataNascitaBeneficiario_string);
		parameters.put("RESIDENZA_RICHIEDENTE", residenzaBeneficiario);
		parameters.put("DOMICILIO_RICHIEDENTE", domicilioBeneficiario);
		parameters.put("ASL_ISCRIZIONE", denominazione_Asl);
		parameters.put("DATA_REVOCA_ASSISTENZA", dataRevocaAssistenza_string);

		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		InputStream input = classloader.getResourceAsStream("/report/CertificatoRevoca.jrxml");

		JasperReport jasperReport = null;
		try {
			jasperReport = JasperCompileManager.compileReport(input);

			JasperPrint jasperPrint = null;
			jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());

			JRPdfExporter exporter = new JRPdfExporter();

			exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));

			SimplePdfReportConfiguration reportConfig = new SimplePdfReportConfiguration();
			reportConfig.setSizePageToContent(true);
			reportConfig.setForceLineBreakPolicy(false);
			SimplePdfExporterConfiguration exportConfig = new SimplePdfExporterConfiguration();

			exportConfig.setMetadataAuthor("SISTEMA PIEMONTE");
			// exportConfig.setEncrypted(true);
			exportConfig.setAllowedPermissionsHint("PRINTING");

			exporter.setConfiguration(reportConfig);
			exporter.setConfiguration(exportConfig);
			exporter.exportReport();

		} catch (JRException e) {
			e.printStackTrace();
		}

		fileFinale = outputStream.toByteArray();
		if (fileFinale.length < 1000) { // NO DATA
			return null;
		}
		return fileFinale;
	}
	
	private boolean isDatiAnagraficiAttivi(List<DatiAnagrafici> datiCittadini) throws Exception {
		if(datiCittadini != null) {
			for(DatiAnagrafici da : datiCittadini) {
				try {
					SoggettoAuraBodyNew sog = ServiziEsterniClient.getInstance().anagrafeSanitaria(da.getIdProfiloAnagrafico(), null);
					if(sog.getInfoAnag().getDatiPrimari().getStatoProfiloAnagrafico().equalsIgnoreCase("1")) {
						return true;
					}
				} catch (AuraNotFoundException e) {
				} catch (Exception e) {
					e.printStackTrace();
					throw e; 
				}
			}
		}
		return false;
	}

	/**
	 *  metodo che produce il file per StampaTesserinoMedico 
	 * @throws Exception 
	 * @throws AuraNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws  
	 *  */
	public byte[] tesserinoMedico_getFile(String cit_Id, ScerevLAudit audit) throws  AuraNotFoundException, Exception {
		System.setProperty("java.awt.headless", "true");
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		byte[] fileFinale = null;
		String nomeMedico = null;
		String cognomeMedico = null;
		String codRegionaleMedico = null;
		Date dataSceltaMedico_date = null;
		String dataSceltaMedico_string = null;
		String nomeBeneficiario = null;
		String cognomeBeneficiario = null;
		String codFiscBeneficiario = null;
		String dataNascitaBeneficiario_string = null;
		String residenzaBeneficiario = null;
		String domicilioBeneficiario = null;
		String codiceDistrettoDomicilio = null;
		String codiceTesseraRegionale = null;
		String nome_e_cognome_medico = null;
		String cfAssistito = cit_Id;
		String dataRilascio = null;
		String dataScadenza = null;

		// procedura per recuperare la "data_creazione" della pratica in stato
		// Completata/Chiusa per il param "dataSceltaMedico"
		// viene creata una lista contenente le pratiche riferite allo stesso assistito,
		// filtrando per entrambi gli stati e
		// viene estratta la pratica avente la data_creazione più recente
		List<String> listaStati = new ArrayList<String>();
		listaStati.add("6"); // Completata
		listaStati.add("8"); // Chiusa
		List<ScerevTPratica> ListaPratiche_perCFeStato = dataDao.getPraticaByCFandStato(cfAssistito, listaStati);
		List<Timestamp> listaDateCreazione = new ArrayList<Timestamp>();
		Timestamp data_piu_recente = null;
		if(ListaPratiche_perCFeStato != null && !ListaPratiche_perCFeStato.isEmpty()) {
			for (int i = 0; i < ListaPratiche_perCFeStato.size(); i++) {
				
				listaDateCreazione.add(ListaPratiche_perCFeStato.get(i).getDataCreazione());
			}

			// ricavo la data piu recente
			data_piu_recente = Collections.max(listaDateCreazione);
		}
		// Ricerco dati cittadino e dati medico dato il cit_Id
		List<DatiAnagrafici> datiCittadini = ServiziEsterniClient.getInstance().findCittadino(cit_Id, null, null, null,
				audit);
		Map<DatiAnagrafici, SoggettoAuraBodyNew> auraMap;
		if (datiCittadini != null) {
			auraMap = ServiziEsterniClient.getInstance().anagrafeSanitariaAttiva(datiCittadini, audit);
			if(auraMap != null) {
				
				DatiAnagrafici datiCittadinoAura = auraMap.keySet().iterator().next();
				SoggettoAuraBodyNew sog = auraMap.get(datiCittadinoAura);
				ModelCittadinoInfoCustom datiCittadinoInteressato = new ModelCittadinoInfoCustom(datiCittadinoAura, sog);
				datiCittadinoInteressato.getAslAssistenza()
				.setDescrizione(datiCittadinoInteressato.getAslAssistenza().getId() == null ? null
						: dataDao.getAsl(datiCittadinoInteressato.getAslAssistenza().getId())
						.getDenominazioneAzienda());
				
				// Dati Cittadino:
				
				nomeBeneficiario = datiCittadinoInteressato.getNome();
				cognomeBeneficiario = datiCittadinoInteressato.getCognome();
				codFiscBeneficiario = datiCittadinoInteressato.getCodiceFiscale();
				dataNascitaBeneficiario_string = Converter.getData(datiCittadinoInteressato.getDataNascita());
				residenzaBeneficiario = datiCittadinoInteressato.getResidenza().getComune();
				domicilioBeneficiario = datiCittadinoInteressato.getDomicilio().getComune();
				codiceDistrettoDomicilio = sog.getInfoSan().getCodDistrettoDomicilio() != null ? sog.getInfoSan().getCodDistrettoDomicilio() : "";
				codiceTesseraRegionale = sog.getInfoSan().getCodiceTesseraRegionale();
				
				// Dati Medico
				
				nomeMedico = sog.getInfoSan().getNomeMedico();
				cognomeMedico = sog.getInfoSan().getCognomeMedico();
				nome_e_cognome_medico = nomeMedico + " " + cognomeMedico;
				codRegionaleMedico = sog.getInfoSan().getCodRegionaleMedico();
				if(data_piu_recente == null) { 
					if(sog.getInfoSan().getTipoMovimento().equalsIgnoreCase(SharedConstants.SCELTA)) {
						data_piu_recente = new Timestamp(sog.getInfoSan().getDataDecorrenza().toGregorianCalendar().getTimeInMillis());
					} else throw new Exception();
				}
				dataSceltaMedico_date = data_piu_recente;
				dataSceltaMedico_string = Converter.getData(dataSceltaMedico_date);
				
				dataRilascio = Converter.getData(new Date());
				String dataFineAssistenza = Converter.getData(Converter.getData(sog.getInfoSan().getDataFineASL()));
				if(Checker.isValorizzato(dataFineAssistenza) && !SharedConstants.DATA_ILLIMITATA.equalsIgnoreCase(dataFineAssistenza)) {
					dataScadenza = dataFineAssistenza;
				}
				
			} else	throw new AuraNotFoundException();
			
		} else {
			throw new AuraNotFoundException();
		}

		// popolo i parametri da passare al report
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("NOME_ASSISTITO", nomeBeneficiario);
		parameters.put("COGNOME_ASSISTITO", cognomeBeneficiario);
		parameters.put("CODICE_FISCALE_ASSISTITO", codFiscBeneficiario);
		parameters.put("DATA_NASCITA_ASSISTITO", dataNascitaBeneficiario_string);
		parameters.put("COMUNE_RESIDENZA_ASSISTITO", residenzaBeneficiario);
		parameters.put("COMUNE_DOMICILIO_ASSISTITO", domicilioBeneficiario);
		parameters.put("CODICE_DISTRETTO_APPARTENENZA_ASSISTITO", codiceDistrettoDomicilio);
		parameters.put("CODICE_TESSERINO_ASSISTITO", codiceTesseraRegionale);
		parameters.put("COGNOME+NOME_MEDICO", nome_e_cognome_medico);
		parameters.put("COD_REGIONALE_MEDICO", codRegionaleMedico);
		parameters.put("DATA_SCELTA_MEDICO", dataSceltaMedico_string);
		parameters.put("DATA_RILASCIO", dataRilascio);
		parameters.put("DATA_SCADENZA", dataScadenza);

		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		InputStream input = classloader.getResourceAsStream("/report/TesserinoMedico.jrxml");

		JasperReport jasperReport = null;

		jasperReport = JasperCompileManager.compileReport(input);

		JasperPrint jasperPrint = null;
		jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());

		JRPdfExporter exporter = new JRPdfExporter();

		exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));

		SimplePdfReportConfiguration reportConfig = new SimplePdfReportConfiguration();
		reportConfig.setSizePageToContent(true);
		reportConfig.setForceLineBreakPolicy(false);
		SimplePdfExporterConfiguration exportConfig = new SimplePdfExporterConfiguration();

		exportConfig.setMetadataAuthor("SISTEMA PIEMONTE");
		// exportConfig.setEncrypted(true);
		exportConfig.setAllowedPermissionsHint("PRINTING");

		exporter.setConfiguration(reportConfig);
		exporter.setConfiguration(exportConfig);
		exporter.exportReport();

		fileFinale = outputStream.toByteArray();
		if (fileFinale.length < 1000) { // NO DATA
			return null;
		}

		return fileFinale;		

	}

	/*metodo che produce il file per stampaRichiesta*/

	public byte[] stampaRichiesta_getFile(String richiestaId, ScerevLAudit audit) {
		System.setProperty("java.awt.headless", "true");
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		byte[] fileFinale = null;

		ScerevTPratica pratica = dataDao.getPraticaPerNumero(Converter.getInt(richiestaId));
		boolean assMaggiorenne = Checker.isMaggiorenne(Converter.getData(pratica.getDataNascitaAssistito()));

		boolean isMinorenne = pratica.getCfDelegato() != null && pratica.getNomeDelegato() != null && pratica.getCognomeDelegato() != null && !assMaggiorenne;
		final String reportName = isMinorenne ? Constants.STAMPA_RICHIESTA_MEDICO_PER_MINORENNE : Constants.STAMPA_RICHIESTA_MEDICO_PER_MAGGIORENNE;

		String messaggio1 = dataDao.getMessaggioPerCodice(Constants.REPORT_MESSAGGIO_1).getValoreMsg();
		String messaggio2 = dataDao.getMessaggioPerCodice(Constants.REPORT_MESSAGGIO_2).getValoreMsg();
		String messaggio3 = dataDao.getMessaggioPerCodice(Constants.REPORT_MESSAGGIO_3).getValoreMsg();
		
		String nomeCognome = pratica.getNomeAssistito()+" "+pratica.getCognomeAssistito();
		String codiceFiscale = pratica.getCfAssistito();
		String dataNascita = pratica.getDataNascitaAssistito() == null ? null : Converter.getData(Converter.getData(pratica.getDataNascitaAssistito()));
		String residenza   = pratica.getScerevTLocalita1().getIndirizzo()+", "+pratica.getScerevTLocalita1().getNumeroCivico()+" "+pratica.getScerevTLocalita1().getComune()
							 +" "+pratica.getScerevTLocalita1().getScerevDNazione().getNomeNazione();
		String domicilio   = pratica.getScerevTLocalita2().getIndirizzo()+", "+pratica.getScerevTLocalita2().getNumeroCivico()+" "+pratica.getScerevTLocalita2().getComune()
							 +" "+pratica.getScerevTLocalita2().getScerevDNazione().getNomeNazione();
		
		boolean isAssisTemBoolean		= (pratica.getDataFineAssistenzaRichiesta() != null || pratica.getDataFineAssistenzaImposta() != null);
		
		String codiceMotivoDomicilio	= pratica.getScerevDMotivoDomicilio() == null ? null : pratica.getScerevDMotivoDomicilio().getCodiceMotivoDomicilio();
		String motivazioneDomicilio 	= isAssisTemBoolean && pratica.getScerevDMotivoDomicilio() != null ? pratica.getScerevDMotivoDomicilio().getValoreMotivoDomicilio() : null;
		String luogoNascita 			= pratica.getLuogoNascitaAssistito();
		String motivazioneSoggiorno 	= pratica.getScerevDMotivoSoggiorno() != null ? dataDao.getMotivoSoggiorno(pratica.getScerevDMotivoSoggiorno().getCodiceMotivoSoggiorno()).getValoreMotivoSoggiorno() : null;
		String aslAssistenza 			= pratica.getScerevDAsl3().getDenominazioneAzienda();
		String extraRegioneASL 			= pratica.getCodiceAslPrecedente();
		String extraRegioneNomeRegione 	= pratica.getScerevDRegione() == null ? null : pratica.getScerevDRegione().getNomeRegione();
		String extraRegioneMedico 		= pratica.getCognomeMedicoPrecedente() == null ? null : pratica.getNomeMedicoPrecedente()+" "+pratica.getCognomeMedicoPrecedente(); 
		String isAssistenzaFuoriRegione = (extraRegioneASL != null && extraRegioneNomeRegione != null && extraRegioneMedico != null) ? "true" : "false";//Questa sezione deve apparire solo se i corrispettivi campi nel db sono popolati
		String nomeCognomeMedico 		= pratica.getCognomeMedico() == null ? null : pratica.getNomeMedico()+" "+pratica.getCognomeMedico();
		String dataFineAssisTemporanea 	= pratica.getDataFineAssistenzaRichiesta() == null ? null : Converter.getData(Converter.getData(pratica.getDataFineAssistenzaRichiesta()));
		String isAssistenzaTemporanea 	= dataFineAssisTemporanea == null ? "false" : "true";
		
		
		boolean isAssPermDerogaXRicongb = !isAssisTemBoolean && (pratica.getScerevTInfoSupplementari() != null && pratica.getScerevTInfoSupplementari().getNomeParente() != null && pratica.getScerevTInfoSupplementari().getCognomeParente() != null && pratica.getScerevTInfoSupplementari().getCfParente() != null && pratica.getScerevTInfoSupplementari().getScerevDGradoParentela() != null);
		String isAssPermDerogaXRicong	= isAssPermDerogaXRicongb ? "true" : "false";
		String familiareNomeCognome 	= null;
		String familiareCodiceFiscale 	= null;
		String familiareGradoParentela 	= null;
		String familiareNomeCognome2 	= null;
		String familiareCodiceFiscale2 	= null;
		String familiareGradoParentela2	= null;
		String studioIstituto 			= null;
		String studioIndirizzo 			= null;
		String studioComune 			= null;
		String studioProvincia			= null;
		String lavoroProfessione 		= null;
		String lavoroPiva 				= null;
		String lavoroNIscrizione 		= null;
		String lavoroAzienda 			= null;
		String lavoroTelefonoAzienda 	= null;
		String lavoroIndirizzoAzienda 	= null;
		String lavoroDataFineContratto 	= null;
		String lavoroComuneAzienda 		= null;
		String studioDataFineIscrizione = null;
				
		String nomeCognomeDelegato		= null;
		if (isMinorenne) {
			nomeCognomeDelegato = pratica.getNomeDelegato()+" "+pratica.getCognomeDelegato();//+" "+pratica.getCfDelegato();
			codiceFiscale = pratica.getCfDelegato();
		}
		
		String motivazioneDomanda = pratica.getScerevTInfoSupplementari() != null ?
				motivazioneDomanda = pratica.getScerevTInfoSupplementari().getMotivazioneDomanda() : null;
		
		if (MotivazioneDomicilioEnum.RICONGIUNGIMENTO_FAMILIARE.getKey().equalsIgnoreCase(codiceMotivoDomicilio)
				|| MotivazioneDomicilioEnum.RICONGIUNGIMENTO_FAMILIARE_LAVORATORE.getKey().equalsIgnoreCase(codiceMotivoDomicilio)
				|| MotivazioneDomicilioEnum.RICONGIUNGIMENTO_FAMILIARE_MOTIVI_DI_SALUTE.getKey().equalsIgnoreCase(codiceMotivoDomicilio)) {
			familiareNomeCognome = pratica.getScerevTInfoSupplementari().getNomeParente()+" "+pratica.getScerevTInfoSupplementari().getCognomeParente();
			familiareCodiceFiscale = pratica.getScerevTInfoSupplementari().getCfParente();
			familiareGradoParentela = pratica.getScerevTInfoSupplementari().getScerevDGradoParentela().getValoreGradoParentela();
		} else if (MotivazioneDomicilioEnum.LAVORO.getKey().equalsIgnoreCase(codiceMotivoDomicilio)) {
			lavoroProfessione = pratica.getScerevTInfoSupplementari().getTipologiaLavoro().equals(Boolean.TRUE) ? pratica.getScerevTInfoSupplementari().getProfessione() : null;
			lavoroPiva = pratica.getScerevTInfoSupplementari().getTipologiaLavoro().equals(Boolean.TRUE) ? pratica.getScerevTInfoSupplementari().getPartitaIva() : null;
			lavoroNIscrizione = pratica.getScerevTInfoSupplementari().getTipologiaLavoro().equals(Boolean.TRUE) ? pratica.getScerevTInfoSupplementari().getNumeroIscrizione() : null;
			lavoroAzienda = pratica.getScerevTInfoSupplementari().getTipologiaLavoro().equals(Boolean.FALSE) ? pratica.getScerevTInfoSupplementari().getDittaDatoreLavoro() : null;
			lavoroTelefonoAzienda = pratica.getScerevTInfoSupplementari().getTipologiaLavoro().equals(Boolean.FALSE) ? pratica.getScerevTInfoSupplementari().getNumeroTelefonoDatore() : null;
			lavoroIndirizzoAzienda = pratica.getScerevTInfoSupplementari().getTipologiaLavoro().equals(Boolean.FALSE) ? pratica.getScerevTInfoSupplementari().getIndirizzoLavoro()+" - "+pratica.getScerevTInfoSupplementari().getNumeroCivicoLavoro() : null;
			lavoroDataFineContratto = pratica.getScerevTInfoSupplementari().getTipologiaLavoro().equals(Boolean.FALSE) ? Converter.getData(Converter.getData(pratica.getScerevTInfoSupplementari().getDataFineContratto())) : null;
			lavoroComuneAzienda = pratica.getScerevTInfoSupplementari().getTipologiaLavoro().equals(Boolean.FALSE) ? pratica.getScerevTInfoSupplementari().getComuneLavoro()+ " - "+pratica.getScerevTInfoSupplementari().getProvinciaLavoro() : null;
		} else if (MotivazioneDomicilioEnum.STUDIO.getKey().equalsIgnoreCase(codiceMotivoDomicilio)) {
			studioIstituto = pratica.getScerevTInfoSupplementari().getNomeIstituto();
			studioIndirizzo = pratica.getScerevTInfoSupplementari().getIndirizzoSedeStudio()+" - "+pratica.getScerevTInfoSupplementari().getCivicoSedeStudio();
			studioComune = pratica.getScerevTInfoSupplementari().getComuneSedeStudio();//+" - "+pratica.getScerevTInfoSupplementari().getProvinciaSedeStudio();
			studioProvincia = pratica.getScerevTInfoSupplementari().getProvinciaSedeStudio();
			studioDataFineIscrizione = Converter.getData(Converter.getData(pratica.getScerevTInfoSupplementari().getDataFineIscrizione()));
		}

		if (isAssPermDerogaXRicongb) {
			familiareNomeCognome2 = pratica.getScerevTInfoSupplementari().getNomeParente()+" "+pratica.getScerevTInfoSupplementari().getCognomeParente();
			familiareCodiceFiscale2 = pratica.getScerevTInfoSupplementari().getCfParente();
			familiareGradoParentela2 = pratica.getScerevTInfoSupplementari().getScerevDGradoParentela().getValoreGradoParentela();
		}
		
		//popolo i parametri da passare al report
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("NOME_COGNOME", nomeCognome);
		parameters.put("CODICE_FISCALE", codiceFiscale);
		parameters.put("DATA_NASCITA", dataNascita);
		parameters.put("LUOGO_NASCITA", luogoNascita);
		parameters.put("RESIDENZA", residenza);
		parameters.put("DOMICILIO", domicilio);
		parameters.put("MESSAGGIO_1", messaggio1);
		parameters.put("MESSAGGIO_2", messaggio2);
		parameters.put("MESSAGGIO_3", messaggio3);
		parameters.put("MOTIVAZIONE_DOMICILIO", motivazioneDomicilio);
		parameters.put("LAVORO_PROFESSIONE", lavoroProfessione);
		parameters.put("LAVORO_PIVA", lavoroPiva);
		parameters.put("LAVORO_N_ISCRIZIONE", lavoroNIscrizione);
		parameters.put("LAVORO_AZIENDA", lavoroAzienda);
		parameters.put("LAVORO_TELEFONO_AZIENDA", lavoroTelefonoAzienda);
		parameters.put("LAVORO_INDIRIZZO_AZIENDA", lavoroIndirizzoAzienda);
		parameters.put("LAVORO_DATA_FINE_CONTRATTO", lavoroDataFineContratto);
		parameters.put("LAVORO_COMUNE_AZIENDA", lavoroComuneAzienda);
		parameters.put("STUDIO_ISTITUTO", studioIstituto);
		parameters.put("STUDIO_INDIRIZZO", studioIndirizzo);
		parameters.put("STUDIO_COMUNE", studioComune);
		parameters.put("STUDIO_PROVINCIA", studioProvincia);
		parameters.put("STUDIO_DATA_FINE_ISCRIZIONE", studioDataFineIscrizione);
		parameters.put("FAMILIARE_NOME_COGNOME", familiareNomeCognome);
		parameters.put("FAMILIARE_CODICE_FISCALE", familiareCodiceFiscale);
		parameters.put("FAMILIARE_GRADO_PARENTELA", familiareGradoParentela);
		parameters.put("FAMILIARE_NOME_COGNOME_PERMANENTE", familiareNomeCognome2);
		parameters.put("FAMILIARE_CODICE_FISCALE_PERMANENTE", familiareCodiceFiscale2);
		parameters.put("FAMILIARE_GRADO_PARENTELA_PERMANENTE", familiareGradoParentela2);
		parameters.put("IS_ASSISTENZA_FUORI_REGIONE", isAssistenzaFuoriRegione);
		parameters.put("EXTRA_REGIONE_ASL", extraRegioneASL);
		parameters.put("EXTRA_REGIONE_NOME_REGIONE", extraRegioneNomeRegione);
		parameters.put("EXTRA_REGIONE_MEDICO", extraRegioneMedico);
		parameters.put("MOTIVAZIONE_SOGGIORNO", motivazioneSoggiorno);
		parameters.put("NOME_COGNOME_MEDICO", nomeCognomeMedico);
		parameters.put("ASL_ASSISTENZA", aslAssistenza);
		parameters.put("DATA_FINE_ASSISTENZA_TEMPORANEA", dataFineAssisTemporanea);
		parameters.put("IS_ASSISTENZA_TEMPORANEA", isAssistenzaTemporanea);
		parameters.put("IS_ASS_PERM_DEROGA_X_RICONGIUNGIMENTO", isAssPermDerogaXRicong);
		parameters.put("MOTIVAZIONE_DOMANDA", motivazioneDomanda);

		if (isMinorenne) {
			parameters.put("NOME_COGNOME_DELEGATO", nomeCognomeDelegato);
		}
		
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		InputStream input = classloader.getResourceAsStream(reportName);

//		JasperReport jasperReport = null;
		try {
//			jasperReport = JasperCompileManager.compileReport(reportName);

			JasperPrint jasperPrint = null;
//			jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
			jasperPrint = JasperFillManager.fillReport(input, parameters, new JREmptyDataSource());

			JRPdfExporter exporter = new JRPdfExporter();

			exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));

			SimplePdfReportConfiguration reportConfig = new SimplePdfReportConfiguration();
			reportConfig.setSizePageToContent(true);
			reportConfig.setForceLineBreakPolicy(false);
			SimplePdfExporterConfiguration exportConfig = new SimplePdfExporterConfiguration();

			exportConfig.setMetadataAuthor(SharedConstants.REPORT_METADATA_AUTHOR);
			// exportConfig.setEncrypted(true);
			exportConfig.setAllowedPermissionsHint(SharedConstants.REPORT_ALLOWED_PERMISSIONS_HINT);

			exporter.setConfiguration(reportConfig);
			exporter.setConfiguration(exportConfig);
			exporter.exportReport();

		} catch (JRException e) {
			e.printStackTrace();
		}	

		fileFinale = outputStream.toByteArray();
		if (fileFinale.length < 1000) { // NO DATA
			return null;
		}
		return fileFinale;
	}

	public byte[] stampaModuloDelegante_getFile(String citId, Payload3 payload, ScerevLAudit audit) {
		System.setProperty("java.awt.headless", "true");
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		byte[] fileFinale = null;
	
		final String reportName = Constants.STAMPA_MODULO_DELEGANTE;

		String messaggio1 = dataDao.getMessaggioPerCodice(Constants.REPORT_MESSAGGIO_1).getValoreMsg();
		String messaggio2 = dataDao.getMessaggioPerCodice(Constants.REPORT_MESSAGGIO_2).getValoreMsg();
		String messaggio3 = dataDao.getMessaggioPerCodice(Constants.REPORT_MESSAGGIO_3).getValoreMsg();
		
		String nomeCognome = payload.getGeneralitaAssistito().getNome()+" "+payload.getGeneralitaAssistito().getCognome();
		String codiceFiscale = citId;
		String dataNascita = payload.getGeneralitaAssistito().getDataNascita() == null ? null : Converter.getData(payload.getGeneralitaAssistito().getDataNascita());
		String residenza   = payload.getResidenza().getIndirizzo()+", "+payload.getResidenza().getCivico()+" "+payload.getResidenza().getComune()
							 +" "+payload.getResidenza().getNazione();
		String domicilio   = payload.getDomicilio().getIndirizzo()+", "+payload.getDomicilio().getCivico()+" "+payload.getDomicilio().getComune();
		String luogoNascita 			= payload.getGeneralitaAssistito().getComuneNascita();
							
		String codiceMotivoDomicilio    = null;
		String motivazioneDomicilio     = null;
		String motivazioneSoggiorno     = null;
		boolean isAssisTemBoolean = false;
		
		String aslAssistenza 			= payload.getMedico().getAsl().getDescrizione();
		
		String extraRegioneASL 			= null;
		String extraRegioneNomeRegione 	= null;
		String extraRegioneMedico 		= null;
		if(payload.getMedicoAltraRegione() != null) {
			extraRegioneASL 			= payload.getMedicoAltraRegione().getAziendaSanitaria();
			extraRegioneNomeRegione 	= payload.getMedicoAltraRegione().getRegione();
			extraRegioneMedico 		= payload.getMedicoAltraRegione().getCognome() == null ? null : payload.getMedicoAltraRegione().getNome()+" "+payload.getMedicoAltraRegione().getCognome(); 
		}
		String isAssistenzaFuoriRegione = (extraRegioneASL != null && extraRegioneNomeRegione != null && extraRegioneMedico != null) ? "true" : "false";//Questi campi devono apparire solo se la sezione “medico_Altra_regione” del payload è popolata
		String nomeCognomeMedico 		= payload.getMedico().getNome()+" "+payload.getMedico().getCognome();
		
		String dataFineAssisTemporanea = null;
		String isAssistenzaTemporanea = null;
		
		String isAssPermDerogaXRicong	= null;
		String familiareNomeCognome 	= null;
		String familiareCodiceFiscale 	= null;
		String familiareGradoParentela 	= null;
		String familiareNomeCognome2 	= null;
		String familiareCodiceFiscale2 	= null;
		String familiareGradoParentela2	= null;
		String studioIstituto 			= null;
		String studioIndirizzo 			= null;
		String studioComune 			= null;
		String studioProvincia 			= null;
		String lavoroProfessione 		= null;
		String lavoroPiva 				= null;
		String lavoroNIscrizione 		= null;
		String lavoroAzienda 			= null;
		String lavoroTelefonoAzienda 	= null;
		String lavoroIndirizzoAzienda 	= null;
		String lavoroDataFineContratto 	= null;
		String lavoroComuneAzienda 		= null;
		String studioDataFineIscrizione = null;
		String motivazioneDomanda 		= null;
		
		if(payload.getDatiAggiuntivi() != null) {
			isAssisTemBoolean = (payload.getDatiAggiuntivi().getMotivazioneDomicilio() != null || payload.getDatiAggiuntivi().getAssitenzaTemporaneaDataFineValidita() != null);
			
			if(isAssisTemBoolean) {
				if(payload.getDatiAggiuntivi().getMotivazioneDomicilio() != null) { 
					if(Checker.isValorizzato(payload.getDatiAggiuntivi().getMotivazioneDomicilio().getDescrizione())) {
						codiceMotivoDomicilio = payload.getDatiAggiuntivi().getMotivazioneDomicilio().getId();
						motivazioneDomicilio = payload.getDatiAggiuntivi().getMotivazioneDomicilio().getDescrizione();
					} else {
						motivazioneDomicilio = dataDao.getMotivoDomicilio(payload.getDatiAggiuntivi().getMotivazioneDomicilio().getId()).getValoreMotivoDomicilio();
					}
				}
			}

			if(payload.getDatiAggiuntivi().getMotivazioneSoggiorno() != null) {			
				if(Checker.isValorizzato(payload.getDatiAggiuntivi().getMotivazioneSoggiorno().getDescrizione())){
					motivazioneSoggiorno = payload.getDatiAggiuntivi().getMotivazioneSoggiorno().getDescrizione();
				}
				else{
					motivazioneSoggiorno = dataDao.getMotivoSoggiorno(payload.getDatiAggiuntivi().getMotivazioneSoggiorno().getId()).getValoreMotivoSoggiorno();
				}
			}

			dataFineAssisTemporanea = payload.getDatiAggiuntivi().getAssitenzaTemporaneaDataFineValidita() == null ? null : Converter.getData(payload.getDatiAggiuntivi().getAssitenzaTemporaneaDataFineValidita());
			isAssistenzaTemporanea 	= dataFineAssisTemporanea == null ? "false" : "true";
			boolean isAssPermDerogaXRicongb = !isAssisTemBoolean && (payload.getDatiAggiuntivi() != null && payload.getDatiAggiuntivi().getRicongiungimentoNome() != null && payload.getDatiAggiuntivi().getRicongiungimentoCognome() != null && payload.getDatiAggiuntivi().getRicongiungimentoCodiceFiscale() != null && payload.getDatiAggiuntivi().getRicongiungimentoParentela() != null);
			isAssPermDerogaXRicong	= isAssPermDerogaXRicongb ? "true" : "false";
			
			if (MotivazioneDomicilioEnum.RICONGIUNGIMENTO_FAMILIARE.getKey().equalsIgnoreCase(codiceMotivoDomicilio)
					|| MotivazioneDomicilioEnum.RICONGIUNGIMENTO_FAMILIARE_LAVORATORE.getKey().equalsIgnoreCase(codiceMotivoDomicilio)
					|| MotivazioneDomicilioEnum.RICONGIUNGIMENTO_FAMILIARE_MOTIVI_DI_SALUTE.getKey().equalsIgnoreCase(codiceMotivoDomicilio)) {
				familiareNomeCognome = payload.getDatiAggiuntivi().getRicongiungimentoNome()+" "+payload.getDatiAggiuntivi().getRicongiungimentoCognome();
				familiareCodiceFiscale = payload.getDatiAggiuntivi().getRicongiungimentoCodiceFiscale();
				familiareGradoParentela = payload.getDatiAggiuntivi().getRicongiungimentoParentela();
			} else if (MotivazioneDomicilioEnum.LAVORO.getKey().equalsIgnoreCase(codiceMotivoDomicilio)) {
				if(payload.getDatiAggiuntivi().getLavoroTipologia().equals(LavoroTipologiaEnum.AUTONOMO)) {
					lavoroProfessione = payload.getDatiAggiuntivi().getLavoroProfessione();
					lavoroPiva = payload.getDatiAggiuntivi().getLavoroPartitaIva();
					lavoroNIscrizione = payload.getDatiAggiuntivi().getLavoroNumeroIscrizione();				
				} else if (payload.getDatiAggiuntivi().getLavoroTipologia().equals(LavoroTipologiaEnum.SUBORDINATO)) {
					lavoroAzienda = payload.getDatiAggiuntivi().getLavoroAzienda();
					lavoroTelefonoAzienda = payload.getDatiAggiuntivi().getLavoroAziendaTelefono();
					lavoroIndirizzoAzienda = payload.getDatiAggiuntivi().getLavoroAziendaIndirizzo() + " - " + payload.getDatiAggiuntivi().getLavoroAziendaCivico();
					lavoroDataFineContratto = Converter.getData(payload.getDatiAggiuntivi().getLavoroDataFineValiditaContratto());
					lavoroComuneAzienda = payload.getDatiAggiuntivi().getLavoroAziendaComune() + " - " + payload.getDatiAggiuntivi().getLavoroAziendaProvincia();				
				}
			} else if (MotivazioneDomicilioEnum.STUDIO.getKey().equalsIgnoreCase(codiceMotivoDomicilio)) {
				studioIstituto = payload.getDatiAggiuntivi().getStudioNomeIstituto();
				studioIndirizzo = payload.getDatiAggiuntivi().getStudioIstitutoIndirizzo()+" - "+payload.getDatiAggiuntivi().getStudioIstitutoCivico();
				studioComune = payload.getDatiAggiuntivi().getStudioIstitutoComune();//+" - "+payload.getDatiAggiuntivi().getStudioIstitutoProvincia();
				studioProvincia = payload.getDatiAggiuntivi().getStudioIstitutoProvincia();
				studioDataFineIscrizione = Converter.getData(payload.getDatiAggiuntivi().getStudioDataTermineIscrizione());
			}

			if (isAssPermDerogaXRicongb) {
				familiareNomeCognome2 = payload.getDatiAggiuntivi().getRicongiungimentoNome()+" "+payload.getDatiAggiuntivi().getRicongiungimentoCognome();
				familiareCodiceFiscale2 = payload.getDatiAggiuntivi().getRicongiungimentoCodiceFiscale();
				familiareGradoParentela2 = payload.getDatiAggiuntivi().getRicongiungimentoParentela();
			}
			
			motivazioneDomanda = payload.getDatiAggiuntivi().getMotivazioneDomanda();
		}
		

		
		//popolo i parametri da passare al report
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("NOME_COGNOME", nomeCognome);
		parameters.put("CODICE_FISCALE", codiceFiscale);
		parameters.put("DATA_NASCITA", dataNascita);
		parameters.put("LUOGO_NASCITA", luogoNascita);
		parameters.put("RESIDENZA", residenza);
		parameters.put("DOMICILIO", domicilio);
		parameters.put("MESSAGGIO_1", messaggio1);
		parameters.put("MESSAGGIO_2", messaggio2);
		parameters.put("MESSAGGIO_3", messaggio3);
		parameters.put("MOTIVAZIONE_DOMICILIO", motivazioneDomicilio);
		parameters.put("LAVORO_PROFESSIONE", lavoroProfessione);
		parameters.put("LAVORO_PIVA", lavoroPiva);
		parameters.put("LAVORO_N_ISCRIZIONE", lavoroNIscrizione);
		parameters.put("LAVORO_AZIENDA", lavoroAzienda);
		parameters.put("LAVORO_TELEFONO_AZIENDA", lavoroTelefonoAzienda);
		parameters.put("LAVORO_INDIRIZZO_AZIENDA", lavoroIndirizzoAzienda);
		parameters.put("LAVORO_DATA_FINE_CONTRATTO", lavoroDataFineContratto);
		parameters.put("LAVORO_COMUNE_AZIENDA", lavoroComuneAzienda);
		parameters.put("STUDIO_ISTITUTO", studioIstituto);
		parameters.put("STUDIO_INDIRIZZO", studioIndirizzo);
		parameters.put("STUDIO_COMUNE", studioComune);
		parameters.put("STUDIO_PROVINCIA", studioProvincia);
		parameters.put("STUDIO_DATA_FINE_ISCRIZIONE", studioDataFineIscrizione);
		parameters.put("FAMILIARE_NOME_COGNOME", familiareNomeCognome);
		parameters.put("FAMILIARE_CODICE_FISCALE", familiareCodiceFiscale);
		parameters.put("FAMILIARE_GRADO_PARENTELA", familiareGradoParentela);
		parameters.put("FAMILIARE_NOME_COGNOME_PERMANENTE", familiareNomeCognome2);
		parameters.put("FAMILIARE_CODICE_FISCALE_PERMANENTE", familiareCodiceFiscale2);
		parameters.put("FAMILIARE_GRADO_PARENTELA_PERMANENTE", familiareGradoParentela2);
		parameters.put("IS_ASSISTENZA_FUORI_REGIONE", isAssistenzaFuoriRegione);
		parameters.put("EXTRA_REGIONE_ASL", extraRegioneASL);
		parameters.put("EXTRA_REGIONE_NOME_REGIONE", extraRegioneNomeRegione);
		parameters.put("EXTRA_REGIONE_MEDICO", extraRegioneMedico);
		parameters.put("MOTIVAZIONE_SOGGIORNO", motivazioneSoggiorno);
		parameters.put("NOME_COGNOME_MEDICO", nomeCognomeMedico);
		parameters.put("ASL_ASSISTENZA", aslAssistenza);
		parameters.put("DATA_FINE_ASSISTENZA_TEMPORANEA", dataFineAssisTemporanea);
		parameters.put("IS_ASSISTENZA_TEMPORANEA", isAssistenzaTemporanea);
		parameters.put("IS_ASS_PERM_DEROGA_X_RICONGIUNGIMENTO", isAssPermDerogaXRicong);
		parameters.put("MOTIVAZIONE_DOMANDA", motivazioneDomanda);

		
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		InputStream input = classloader.getResourceAsStream(reportName);

//		JasperReport jasperReport = null;
		try {
//			jasperReport = JasperCompileManager.compileReport(reportName);

			JasperPrint jasperPrint = null;
//			jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
			jasperPrint = JasperFillManager.fillReport(input, parameters, new JREmptyDataSource());

			JRPdfExporter exporter = new JRPdfExporter();

			exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));

			SimplePdfReportConfiguration reportConfig = new SimplePdfReportConfiguration();
			reportConfig.setSizePageToContent(true);
			reportConfig.setForceLineBreakPolicy(false);
			SimplePdfExporterConfiguration exportConfig = new SimplePdfExporterConfiguration();

			exportConfig.setMetadataAuthor(SharedConstants.REPORT_METADATA_AUTHOR);
			// exportConfig.setEncrypted(true);
			exportConfig.setAllowedPermissionsHint(SharedConstants.REPORT_ALLOWED_PERMISSIONS_HINT);

			exporter.setConfiguration(reportConfig);
			exporter.setConfiguration(exportConfig);
			exporter.exportReport();

		} catch (JRException e) {
			e.printStackTrace();
		}	

		fileFinale = outputStream.toByteArray();
		if (fileFinale.length < 1000) { // NO DATA
			return null;
		}
		return fileFinale;
	}

	@Override
	public void monitoraMedico(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
			String xCodiceServizio, String citId, ModelAmbulatorioLoccsiMedico payload) {
		
		
		ScerevTMonitoraggio monitoraggio = new ScerevTMonitoraggio();
		
		monitoraggio.setCfAssistito(shibIdentitaCodiceFiscale);
		monitoraggio.setIdAuraMedico(payload.getId());
		monitoraggio.setCfMedico(payload.getCodiceFiscale());
		monitoraggio.setNomeMedico(payload.getNome());
		monitoraggio.setCognomeMedico(payload.getCognome());
		monitoraggio.setDataCreazione(new Timestamp(System.currentTimeMillis()));
		monitoraggio.setDataModifica(new Timestamp(System.currentTimeMillis()));
		monitoraggio.setSelezionabile(payload.getDisponibilita().isSelezionabile());
		monitoraggio.setSelezionabileAssistenzaTemporanea(payload.getDisponibilita().isSelezionabileAssistenzaTemporanea());
		monitoraggio.setSelezionabileInfanzia(payload.getDisponibilita().isSelezionabileInfanzia());
		monitoraggio.setSelezionabileRicongiungimentoFamigliare(payload.getDisponibilita().isSelezionabileRicongiungimentoFamiliare());
		
		dataDao.insertScerevTMonitoraggio(monitoraggio);
		
		
	}

	@Override
	public boolean chkPresenzaMedicoMonitorato(String shibId, String idAura) {
		List<ScerevTMonitoraggio> l = dataDao.getMediciMonitoratiByCittadino(shibId, idAura);
		if(l.size()>0)
			return true;
		return false;
	}

	@Override
	public ScerevTMonitoraggio getMedicoMonitoratoByShib(String shibId, String idMedico) {
		List<ScerevTMonitoraggio> l = dataDao.getMediciMonitoratiByCittadino(shibId, idMedico);
		if(l.size()==1)
			return l.get(0);
		return null;
	}

	@Override
	public void rimuoviMedicoMonitorato(ScerevTMonitoraggio med) {
		
		med.setDataCancellazione(new Timestamp(System.currentTimeMillis()));
		dataDao.saveMonitoraggio(med);
		
	}

	@Override
	public ScerevTFile getAllegato(String citId, String allegatoId) throws AllegatoNotFoundException {
		ScerevTFile file = dataDao.getAllegatoByIdAndCf(citId, allegatoId);
		if(file == null) throw new AllegatoNotFoundException(Constants.ERROR_404_NOT_FOUND, "allegato non trovato");
		return file;
	}

	@Override
	public boolean checkRicusazioneMedico(ModelAmbulatorioLoccsiMedico medico, DatiAnagrafici cittadinoFind) throws Exception {
		if(cittadinoFind != null) {
			String idMedico = medico.getId();
			String idAssistito = cittadinoFind.getIdProfiloAnagrafico().toString();
			
			ResponseDelegate ultimoMovimentoDiRevoca = ServiziEsterniClient.getInstance().getUltimoMovimentoDiRevoca(idAssistito, idMedico);
			
			if(ultimoMovimentoDiRevoca != null) {
				return SharedConstants.CAUSALE_RICUSAZIONE.equalsIgnoreCase(ultimoMovimentoDiRevoca.getCausale());
			}
		}
		return false;
	}
}
