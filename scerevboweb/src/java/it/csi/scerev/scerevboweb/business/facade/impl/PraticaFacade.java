/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevboweb.business.facade.impl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.NoResultException;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.codehaus.jackson.annotate.JsonProperty;

import it.csi.def.opessanws.opessan.InfoRapportoDiLavoro;
import it.csi.def.opessanws.opessan.SoggettoOpessanBody;
import it.csi.scerev.scerevboweb.business.SpringApplicationContextHelper;
import it.csi.scerev.scerevboweb.business.facade.interfaces.PraticaFacadeIf;
import it.csi.scerev.scerevboweb.business.serviziesterni.ServiziEsterniClient;
import it.csi.scerev.scerevboweb.dto.AccettaRichiesta;
import it.csi.scerev.scerevboweb.dto.Allegato;
import it.csi.scerev.scerevboweb.dto.ChiudeRichiesta;
import it.csi.scerev.scerevboweb.dto.Cittadino;
import it.csi.scerev.scerevboweb.dto.DettaglioAllegato;
import it.csi.scerev.scerevboweb.dto.DettaglioDatiAuraMedico;
import it.csi.scerev.scerevboweb.dto.DettaglioDatiDelegato;
import it.csi.scerev.scerevboweb.dto.DettaglioDatiMedico;
import it.csi.scerev.scerevboweb.dto.DettaglioDatiMedicoExtra;
import it.csi.scerev.scerevboweb.dto.DettaglioDatiRichiedente;
import it.csi.scerev.scerevboweb.dto.DettaglioDeroghe;
import it.csi.scerev.scerevboweb.dto.DettaglioInformazioniSupplementari;
import it.csi.scerev.scerevboweb.dto.DettaglioOutput;
import it.csi.scerev.scerevboweb.dto.Messaggio;
import it.csi.scerev.scerevboweb.dto.ModelAllegato;
import it.csi.scerev.scerevboweb.dto.ModelCronologia;
import it.csi.scerev.scerevboweb.dto.Motivazione;
import it.csi.scerev.scerevboweb.dto.NotaInterna;
import it.csi.scerev.scerevboweb.dto.Ricerca;
import it.csi.scerev.scerevboweb.dto.RicercaOutput;
import it.csi.scerev.scerevboweb.dto.RicercaRichiesteInput;
import it.csi.scerev.scerevboweb.dto.RicercaRichiesteOutput;
import it.csi.scerev.scerevboweb.dto.StatoPratica;
import it.csi.scerev.scerevboweb.dto.TipologiaPratica;
import it.csi.scerev.scerevboweb.dto.TipologiaProfilo;
import it.csi.scerev.scerevboweb.dto.UpdatePratica;
import it.csi.scerev.scerevboweb.dto.UserInfo;
import it.csi.scerev.scerevboweb.dto.datatables.DataTableRequest;
import it.csi.scerev.scerevboweb.dto.datatables.OrderBy;
import it.csi.scerev.scerevboweb.util.Constants;
import it.csi.scerev.scerevjpa.business.dao.interfaces.DataDaoIf;
import it.csi.scerev.scerevjpa.business.entity.ScerevDAsl;
import it.csi.scerev.scerevjpa.business.entity.ScerevDMotivazione;
import it.csi.scerev.scerevjpa.business.entity.ScerevDPraticaStato;
import it.csi.scerev.scerevjpa.business.entity.ScerevDPraticaTipo;
import it.csi.scerev.scerevjpa.business.entity.ScerevDTipologiaProfilo;
import it.csi.scerev.scerevjpa.business.entity.ScerevLAudit;
import it.csi.scerev.scerevjpa.business.entity.ScerevTAslOperatore;
import it.csi.scerev.scerevjpa.business.entity.ScerevTCronologia;
import it.csi.scerev.scerevjpa.business.entity.ScerevTDocumento;
import it.csi.scerev.scerevjpa.business.entity.ScerevTFile;
import it.csi.scerev.scerevjpa.business.entity.ScerevTLocalita;
import it.csi.scerev.scerevjpa.business.entity.ScerevTPratica;
import it.csi.scerev.scerevserviziesterni.aura.find.DatiAnagrafici;
import it.csi.scerev.scerevserviziesterni.aura.get.DatiSecondariNew;
import it.csi.scerev.scerevserviziesterni.aura.get.SoggettoAuraBodyNew;
import it.csi.scerev.scerevserviziesterni.aura.getnumeroassistitiincaricomedico.NumeroAssistitiInCaricoMedicoResponse;
import it.csi.scerev.scerevserviziesterni.aura.setaggiornamentoprofiloanagrafico.AggiornamentoProfiloAnagrafico;
import it.csi.scerev.scerevserviziesterni.aura.setaggiornamentoprofiloanagrafico.AggiornamentoProfiloAnagraficoAltreInfo;
import it.csi.scerev.scerevserviziesterni.aura.setaggiornamentoprofiloanagrafico.AggiornamentoProfiloAnagraficoBody;
import it.csi.scerev.scerevserviziesterni.aura.setaggiornamentoprofiloanagrafico.AggiornamentoProfiloAnagraficoDatiPrimari;
import it.csi.scerev.scerevserviziesterni.aura.setaggiornamentoprofiloanagrafico.AggiornamentoProfiloAnagraficoDomicilio;
import it.csi.scerev.scerevserviziesterni.aura.setaggiornamentoprofiloanagrafico.AggiornamentoProfiloAnagraficoHeader;
import it.csi.scerev.scerevserviziesterni.aura.setaggiornamentoprofiloanagrafico.AggiornamentoProfiloAnagraficoRes;
import it.csi.scerev.scerevserviziesterni.aura.setaggiornamentoprofiloanagrafico.AggiornamentoProfiloAnagraficoResidenza;
import it.csi.scerev.scerevserviziesterni.aura.setinserimentoprofiloanagrafico.InserimentoProfiloAnagrafico;
import it.csi.scerev.scerevserviziesterni.aura.setinserimentoprofiloanagrafico.InserimentoProfiloAnagraficoAltreInfo;
import it.csi.scerev.scerevserviziesterni.aura.setinserimentoprofiloanagrafico.InserimentoProfiloAnagraficoBody;
import it.csi.scerev.scerevserviziesterni.aura.setinserimentoprofiloanagrafico.InserimentoProfiloAnagraficoDatiPrimari;
import it.csi.scerev.scerevserviziesterni.aura.setinserimentoprofiloanagrafico.InserimentoProfiloAnagraficoDomicilio;
import it.csi.scerev.scerevserviziesterni.aura.setinserimentoprofiloanagrafico.InserimentoProfiloAnagraficoHeader;
import it.csi.scerev.scerevserviziesterni.aura.setinserimentoprofiloanagrafico.InserimentoProfiloAnagraficoRes;
import it.csi.scerev.scerevserviziesterni.aura.setinserimentoprofiloanagrafico.InserimentoProfiloAnagraficoResidenza;
import it.csi.scerev.scerevserviziesterni.aura.setprofilosanitario.DatiIscrizioneSanitaria;
import it.csi.scerev.scerevserviziesterni.aura.setprofilosanitario.ProfiloSanitario;
import it.csi.scerev.scerevserviziesterni.aura.setprofilosanitario.ProfiloSanitarioBody;
import it.csi.scerev.scerevserviziesterni.aura.setprofilosanitario.ProfiloSanitarioHeader;
import it.csi.scerev.scerevserviziesterni.aura.setprofilosanitario.ProfiloSanitarioRes;
import it.csi.scerev.scerevserviziesterni.ensemble.interrogamefscerev.InterrogaMefScerevResult;
import it.csi.scerev.scerevutil.business.Checker;
import it.csi.scerev.scerevutil.business.Converter;
import it.csi.scerev.scerevutil.business.SharedConstants;
import it.csi.scerev.scerevutil.business.Util;
import it.csi.scerev.scerevutil.business.exception.AuraException;
import it.csi.scerev.scerevutil.business.exception.DomainException;
import it.csi.scerev.scerevutil.business.exception.IntegritaException;
import it.csi.scerev.scerevutil.business.exception.ParametriObbligatoriException;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.export.SimplePdfReportConfiguration;


public class PraticaFacade implements PraticaFacadeIf {
	private DataDaoIf dataDao;

	public DataDaoIf getDataDao() {
		return dataDao;
	}

	public void setDataDao(DataDaoIf dataDao) {
		this.dataDao = dataDao;
	}
	
	/**
	 * Restituisce la lista delle pratiche filtrate per RicercaRichiesteInput object
	 * @param userInfo 
	 */
	public RicercaRichiesteOutput getListaPratiche(RicercaRichiesteInput input, UserInfo userInfo) {				
		// cognome-nome assistito caps
		if(input.getRicerca().getNomeAssistito()!=null)input.getRicerca().setNomeAssistito(input.getRicerca().getNomeAssistito().toUpperCase());
		if(input.getRicerca().getCognomeAssistito()!=null)input.getRicerca().setCognomeAssistito(input.getRicerca().getCognomeAssistito().toUpperCase());
		
		// check controllo tab
		String stato = null; 	
		List<ScerevDPraticaStato> listStati  = null;
		if(input.getRicerca().getStato().equalsIgnoreCase("aperte") || input.getRicerca().getStato().equalsIgnoreCase("archivio"))  {
			stato = input.getRicerca().getStato();
			if(stato.equalsIgnoreCase("aperte")) listStati = dataDao.getListaStatoPratica(0);
			else listStati = dataDao.getListaStatoPratica(1);			
			input.getRicerca().setStato(null);
		}
		
		HashMap<String, Object> ricercaMap;
		try {
			ricercaMap = Converter.convertObjToMap(input.getRicerca());
			ricercaMap = addDataTablesParameters(ricercaMap,input);
			if(input.getRicerca() != null && input.getRicerca().isInCarico() != null && input.getRicerca().isInCarico())
				ricercaMap.put("cfOperatore", userInfo.getCodFisc());
			else
				ricercaMap.remove("cfOperatore");
		} catch (IllegalArgumentException | IllegalAccessException e) {
			return null;
		}		
		
		// Add filtro multi-stati
		if(stato!=null) {
			String concatStati = new String();
			for(ScerevDPraticaStato statoEntity : listStati ) {
				concatStati += (statoEntity.getCodicePraticaStato()+";");
			}
			ricercaMap.put("scerevDPraticaStato.codicePraticaStato", concatStati);
		}		
		
		// 06/11/2020 filtri multi-asl operatore
		if(isUtenteOperatore(userInfo) && !Checker.isValorizzato(input.getRicerca().getAsl())) {
			String concatAsl = new String();
			for(String s : userInfo.getCodASL()) {
				concatAsl += (s+";");
			}
			ricercaMap.put("utenteOperatore", concatAsl);
		}
		
		// Add 1 day to dataA
		if(ricercaMap.containsKey("dataCreazione<")) {
			Date dataA = (Date)ricercaMap.get("dataCreazione<");
			dataA = Converter.aggiungiGiorniAData(dataA, 1);
			ricercaMap.put("dataCreazione<", dataA);
		}
		
		List<ScerevTPratica> pratiche = dataDao.getListaPratiche(ricercaMap, false);

		RicercaRichiesteOutput response = new RicercaRichiesteOutput();					
		ArrayList<RicercaOutput> lista = new ArrayList<RicercaOutput>();
		for(ScerevTPratica pratica : pratiche) {						
			RicercaOutput elemento = new RicercaOutput();
			elemento.setAsl(pratica.getAslResidenza());
			elemento.setAssistito(pratica.getNomeAssistito()+" "+pratica.getCognomeAssistito());
			elemento.setData_richiesta(Converter.getStringFromTimestamp(pratica.getDataCreazione()));
			elemento.setMed_ped(pratica.getNomeMedico() + " "+pratica.getCognomeMedico());
			elemento.setNumPratica(pratica.getNumeroPratica());
			if(pratica.getNomeOperatore()!=null) elemento.setOperatore(pratica.getNomeOperatore()+ " "+pratica.getCognomeOperatore());
			elemento.setStato_pratica(pratica.getScerevDPraticaStato().getValorePraticaStato());
			if(pratica.getScerevDAmbito()!=null)elemento.setAmbito(pratica.getScerevDAmbito().getDenominazioneAmbito());
			elemento.setDistretto(pratica.getScerevDDistretto().getDenominazioneDistretto());	
			elemento.setCf_Assistito(pratica.getCfAssistito());
			lista.add(elemento);
		}			
		response.setData(lista);
		response.setRecordsFiltered(""+dataDao.getListaPratiche(ricercaMap, true).size());
		response.setRecordsTotal(response.getRecordsFiltered());
		return response;
	}
	
	private boolean isUtenteOperatore(UserInfo userInfo) {
		return userInfo.getRuolo().equalsIgnoreCase(Constants.OPERATORE_IRIDE);
	}

	/*
	 * Aggiungo i parametri utili al Datatable Angular
	 * più ordinamenti sono distinti dal simbolo ;
	 */
	private HashMap<String, Object> addDataTablesParameters(HashMap<String, Object> ricercaMap, RicercaRichiesteInput input) {
		ricercaMap.put("limit", input.getDataTablesParameter().getLength());
		
		Field[] allFields = Ricerca.class.getDeclaredFields();   
		String paramOrder = "";
		String order = "";
		for(OrderBy ordinamento : input.getDataTablesParameter().getOrder()) {
			 for (Field field : allFields) {
				 if(field.getName().equalsIgnoreCase(input.getDataTablesParameter().getColumns().get(ordinamento.getColumn()).getData())) {
					 paramOrder+=field.getAnnotation(JsonProperty.class).value()+";";
				 }
			 }
			 order+= ordinamento.getDir()+";";				
		}
		ricercaMap.put("orderBy", paramOrder);				
		ricercaMap.put("order", order);		
		ricercaMap.put("start", input.getDataTablesParameter().getStart());
		return ricercaMap;
	}
	
	public void checkDataTables(DataTableRequest input) throws IntegritaException {
		if(
//				!(input.getLength()>0) || 
				!(input.getOrder().size()>0) || !(input.getStart()>=0)) throw new IntegritaException("Parametri datatables non presenti");
		
	}

	public void checkRicercaParameters(Ricerca ricerca) throws ParametriObbligatoriException, IntegritaException {
		
		if(Checker.isValorizzato(ricerca.getCodiceFiscale()) && !Checker.isCodiceFiscale(ricerca.getCodiceFiscale(), isTST())) throw new IntegritaException(Util.composeMessage(dataDao.getMessaggioPerCodice(SharedConstants.ERROR_CODE_INTEGRITA).getValoreMsg()," Codice fiscale formalmente errato"));
		//if(ricerca.getAmbitoDomicilio()!=null && dataDao.getAmbito(ricerca.getAmbitoDomicilio()).size()==0) throw new IntegritaException(Util.composeMessage(dataDao.getMessaggioPerCodice(SharedConstants.ERROR_CODE_INTEGRITA).getValoreMsg()," Ambito errato"));
		if(ricerca.getDataCreazione()!=null && !Checker.isDataItalian(ricerca.getDataCreazione())) throw new IntegritaException(Util.composeMessage(dataDao.getMessaggioPerCodice(SharedConstants.ERROR_CODE_INTEGRITA).getValoreMsg()," Data creazione assente o formato errato (dd/MM/yyyy)"));
		if(ricerca.getDataInsA()!=null && !Checker.isDataItalian(ricerca.getDataInsA())) throw new IntegritaException(Util.composeMessage(dataDao.getMessaggioPerCodice(SharedConstants.ERROR_CODE_INTEGRITA).getValoreMsg()," Data inserimento A assente o formato errato (dd/MM/yyyy)"));
		if(ricerca.getDataInsDa()!=null && !Checker.isDataItalian(ricerca.getDataInsDa())) throw new IntegritaException(Util.composeMessage(dataDao.getMessaggioPerCodice(SharedConstants.ERROR_CODE_INTEGRITA).getValoreMsg()," Data inserimento Da assente o formato errato (dd/MM/yyyy)"));
		if(ricerca.getDataNascita()!=null && !Checker.isDataItalian(ricerca.getDataNascita())) throw new IntegritaException(Util.composeMessage(dataDao.getMessaggioPerCodice(SharedConstants.ERROR_CODE_INTEGRITA).getValoreMsg()," Data nascita assente o formato errato (dd/MM/yyyy)"));
	
		//In caso di stringhe ""
		if(!Checker.isValorizzato(ricerca.getAmbitoDomicilio())) ricerca.setAmbitoDomicilio(null);
		if(!Checker.isValorizzato(ricerca.getAsl())) ricerca.setAsl(null);
		if(!Checker.isValorizzato(ricerca.getCodiceFiscale())) ricerca.setCodiceFiscale(null);
		if(!Checker.isValorizzato(ricerca.getCognomeAssistito())) ricerca.setCognomeAssistito(null);
		if(!Checker.isValorizzato(ricerca.getCognomeOperatore())) ricerca.setCognomeOperatore(null);
		if(!Checker.isValorizzato(ricerca.getDataCreazione())) ricerca.setDataCreazione(null);
		if(!Checker.isValorizzato(ricerca.getDataInsA())) ricerca.setDataInsA(null);
		if(!Checker.isValorizzato(ricerca.getDataInsDa())) ricerca.setDataInsDa(null);
		if(!Checker.isValorizzato(ricerca.getDataNascita())) ricerca.setDataNascita(null);
		if(!Checker.isValorizzato(ricerca.getDistretto())) ricerca.setDistretto(null);
		if(!Checker.isValorizzato(ricerca.getDistrettoDomicilio())) ricerca.setDistrettoDomicilio(null);
		if(!Checker.isValorizzato(ricerca.getMedPed())) ricerca.setMedPed(null);
		if(!Checker.isValorizzato(ricerca.getNomeAssistito())) ricerca.setNomeAssistito(null);
		if(!Checker.isValorizzato(ricerca.getNomeOperatore())) ricerca.setNomeOperatore(null);
		if(!Checker.isValorizzato(ricerca.getStato())) ricerca.setStato(null);
		if(!Checker.isValorizzato(ricerca.getTipologia())) ricerca.setTipologia(null);
		if(!Checker.isValorizzato(ricerca.getTipologiaMedico())) ricerca.setTipologiaMedico(null);
		
	}
	
	public List<ModelCronologia> getStoricoPratica(Integer pk_praticaSelezionata) {
		List<ScerevTCronologia> elenco = dataDao.getCronologiaPratica(pk_praticaSelezionata);
		List<ModelCronologia> output = new ArrayList<ModelCronologia>();
		for (Iterator<ScerevTCronologia> iterator = elenco.iterator(); iterator.hasNext();) {
			ScerevTCronologia dbCronologiaPratica = (ScerevTCronologia) iterator.next();
			ModelCronologia model = new ModelCronologia(dbCronologiaPratica);
			output.add(model);
		}
		return output;
	}
	
	/**
	 * Restituisce la lista di tutte le tipologie di una pratica
	 */
	public ArrayList<TipologiaPratica> getTipologiePratica() {
		List<ScerevDPraticaTipo> listDb = dataDao.getListaTipologiePratica();
						
		ArrayList<TipologiaPratica> lista = new ArrayList<TipologiaPratica>();
		for(ScerevDPraticaTipo s : listDb) {
			lista.add(new TipologiaPratica(s));
		}
		
		return lista;		
	}

	/**
	 * Check su db per verificare in quale ambiente ci troviamo: NB è utilizzato per la validazione dei CF in ambiente di test
	 * @return
	 */
	private String isTST() {
		return dataDao.getMessaggioPerCodice(it.csi.scerev.scerevutil.business.SharedConstants.TST).getValoreMsg();
	}
	
	public ArrayList<StatoPratica> getStatoPratica(int tab) {
		List<ScerevDPraticaStato> listDb = dataDao.getListaStatoPratica(tab);
		
		ArrayList<StatoPratica> lista = new ArrayList<StatoPratica>();
		for(ScerevDPraticaStato s : listDb) {
			lista.add(new StatoPratica(s));
		}
		
		return lista;		
	}

	/**
	 * Ricerca puntuale della pratica
	 */
	public ScerevTPratica getPratica(int numPratica) {
		try {
			return dataDao.getPraticaPerNumero(numPratica);
		} catch (NoResultException e) {
			return null;
		}
	}
		
	public List<ScerevTDocumento> getDocumentoByPratica(Integer pkPratica_Selezionata) {
		return dataDao.getDocumentoByPratica(pkPratica_Selezionata);
	}
	
	public ScerevTFile getFileByPkFile(Integer pkFile_Selezionato) {
		return dataDao.getFileByPkFile_Selezionato(pkFile_Selezionato);
	}

	/**
	 * Serve per comporre l'oggetto DettaglioOutput utile per il dettaglio delle pratiche 
	 */
	public DettaglioOutput dettaglioPraticaAssembly(DatiAnagrafici cittadino,
			SoggettoAuraBodyNew cittadinoExtra, ScerevTPratica pratica, SoggettoOpessanBody operatoreMedico, NumeroAssistitiInCaricoMedicoResponse numeroAssistiti, SoggettoAuraBodyNew parenteExtra, String erroreParente) {
		DettaglioOutput output = new DettaglioOutput();
		
		output.setAllegati(addAllegati(pratica));
		output.setDati_delegato(addDatiDelegato(pratica));
		output.setDati_richiedente(addDatiRichiedente(pratica,cittadinoExtra));
		output.setDati_medico_extra(addDatiMedicoExtra(pratica));				
		output.setDati_medico_richiesto(addDatiMedicoRichiesto(operatoreMedico, pratica, cittadinoExtra, numeroAssistiti));						
		output.setInformazioni_supplementari(addInformazioniSupplementari(pratica,parenteExtra, erroreParente));
		output.setDati_aura_medico(addDatiAuraMedico(pratica, cittadinoExtra));		
		output.setStato(pratica.getScerevDPraticaStato().getValorePraticaStato());
		if(pratica.getCfOperatore()!=null) {
			output.setInCarico(pratica.getNomeOperatore() + " " +pratica.getCognomeOperatore());
			output.setCfInCarico(pratica.getCfOperatore());
		}
		output.setNumPratica(pratica.getNumeroPratica());
		output.setTipologieRichiesta(getTipologieRichiesta(pratica.getPkPratica()));
		output.setDeroghe(addDeroghe(pratica));
		
		output.setDataInizioAss(null);
		output.setDataFineAss(null);
		if(pratica.getScerevDPraticaStato().getValorePraticaStato().equalsIgnoreCase(SharedConstants.STATO_COMPLETATA) || 
	    		pratica.getScerevDPraticaStato().getValorePraticaStato().equalsIgnoreCase(SharedConstants.STATO_CHIUSA))
		{
			if(pratica.getDataInizioAssistenzaImposta() != null )
			{
				output.setDataInizioAss(Converter.getData(Converter.getData(pratica.getDataInizioAssistenzaImposta())));
			}
			if(pratica.getDataFineAssistenzaImposta() != null)
			{
				String d = Converter.getData(Converter.getDataWithoutTime(Converter.getData(pratica.getDataFineAssistenzaImposta())));
				if(!d.equalsIgnoreCase(SharedConstants.DATA_ILLIMITATA)) {					
					output.setDataFineAss(Converter.getData(Converter.getData(pratica.getDataFineAssistenzaImposta())));
				}
			}
		}
		
		if(pratica.getScerevDPraticaStato().getValorePraticaStato().equalsIgnoreCase(SharedConstants.STATO_CHIUSA) 
				&& pratica.getTipologiaGestionePratica().equalsIgnoreCase(SharedConstants.PRATICA_CHIUSURA_AUTOMATICA))
		{
			output.setMsgIfClosed("in Automatico - Data: " + Converter.getData(pratica.getDataChiusura(), "dd/MM/yyyy HH:mm"));
		} else if(pratica.getScerevDPraticaStato().getValorePraticaStato().equalsIgnoreCase(SharedConstants.STATO_DOMANDA_REVOCA)) {
			output.setMsgIfClosed("- Data: " + Converter.getData(pratica.getDataChiusura(), "dd/MM/yyyy HH:mm"));
		}
		return output;
	}

	private DettaglioDatiDelegato addDatiDelegato(ScerevTPratica pratica) {
		DettaglioDatiDelegato delegato = null;
		if(Checker.isValorizzato(pratica.getCfDelegato()) || Checker.isValorizzato(pratica.getNomeDelegato()) || Checker.isValorizzato(pratica.getCognomeDelegato())) {
			delegato = new DettaglioDatiDelegato();
			delegato.setCodice_fiscale(pratica.getCfDelegato());
			delegato.setNome(pratica.getNomeDelegato());
			delegato.setCognome(pratica.getCognomeDelegato());
		}
		return delegato;
	}

	private DettaglioDeroghe addDeroghe(ScerevTPratica pratica) {
		DettaglioDeroghe der = new DettaglioDeroghe();
		der.setDerogaAssociazione(pratica.getDerogaAssociazione() != null ? pratica.getDerogaAssociazione() : false);
		der.setDerogaMassimale(pratica.getDerogaMassimale() != null ? pratica.getDerogaMassimale() : false);
		der.setDerogaMassimaleTemp(pratica.getDerogaMassimaleTemp() != null ? pratica.getDerogaMassimaleTemp() : false);
		der.setDerogaPediatra(pratica.getDerogaPediatra() != null ? pratica.getDerogaPediatra() : false);
		der.setDerogaTerritoriale(pratica.getDerogaTerritoriale() != null ? pratica.getDerogaTerritoriale() : false);
		der.setMedicoGenerico(pratica.getMedicoGenerico() != null ? pratica.getMedicoGenerico() : false);
		der.setMedicoRevocato(pratica.getMedicoRevocato() != null ? pratica.getMedicoRevocato() : false);
		der.setRicongiungimentoFamiliare(pratica.getRicongiungimentoFamiliare() != null ? pratica.getRicongiungimentoFamiliare() : false);
		return der;
	}

	public String getTipologieRichiesta(Integer pkPratica) {
		String tipologie = null;
		List<ScerevDPraticaTipo> tipi = dataDao.getTipologieRichiesta(pkPratica);
		if(tipi != null && !tipi.isEmpty()) {
			tipologie = "";
			for(ScerevDPraticaTipo t : tipi) {
				tipologie += t.getValorePraticaTipo() + " - ";
			}
			tipologie = tipologie.substring(0, (tipologie.length()-3) );
		}
		return tipologie;
	}

	/**
	 * Sottofunzione di dettaglioPraticaAssembly
	 * @param pratica
	 * @return
	 */
	private List<DettaglioAllegato> addAllegati(ScerevTPratica pratica) {	
		try {
			List<ScerevTDocumento> docList = getDocumentoByPratica(pratica.getPkPratica());
			Map<String, List<Allegato>> mappaAllegati = new HashMap<String, List<Allegato>>();			
			List<DettaglioAllegato> allegati = new ArrayList<DettaglioAllegato>();
			
			for(ScerevTDocumento doc : docList) {				
				List<Allegato> listaAllegati = new ArrayList<Allegato>();
				if(mappaAllegati.containsKey(doc.getScerevDDocumentoTipo().getDescrizioneDocumentoTipo())) listaAllegati = mappaAllegati.get(doc.getScerevDDocumentoTipo().getDescrizioneDocumentoTipo());
				listaAllegati.add(new Allegato(doc.getScerevTFile().getNameFile(),doc.getScerevTFile().getPkFile().toString()));				
				mappaAllegati.put(doc.getScerevDDocumentoTipo().getDescrizioneDocumentoTipo(), listaAllegati);
			}						
			
			for(String tipo : mappaAllegati.keySet()){
				DettaglioAllegato allegato = new DettaglioAllegato();
				allegato.setTipoAllegato(tipo);			
				allegato.setAllegati(mappaAllegati.get(tipo));
				allegati.add(allegato);
			}		
			return allegati;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Sottofunzione di dettaglioPraticaAssembly
	 * @param pratica
	 * @param cittadinoExtra
	 * @return
	 */
	private DettaglioDatiAuraMedico addDatiAuraMedico(ScerevTPratica pratica, SoggettoAuraBodyNew cittadinoExtra) {
		DettaglioDatiAuraMedico dati_aura_medico = new DettaglioDatiAuraMedico();
		try {
			dati_aura_medico.setDomicilioInAura(cittadinoExtra.getInfoAnag().getDomicilio() != null && isDatiSecondariAvvalorato(cittadinoExtra.getInfoAnag().getDomicilio())
					? cittadinoExtra.getInfoAnag().getDomicilio().getIndirizzo() + ", " + cittadinoExtra.getInfoAnag().getDomicilio().getNumCivico() + ", "+ cittadinoExtra.getInfoAnag().getDomicilio().getDescComune()
					: null);
			dati_aura_medico.setResidenzaInAura(cittadinoExtra.getInfoAnag().getResidenza() != null && isDatiSecondariAvvalorato(cittadinoExtra.getInfoAnag().getResidenza())
					? cittadinoExtra.getInfoAnag().getResidenza().getIndirizzo() + ", " + cittadinoExtra.getInfoAnag().getResidenza().getNumCivico() + ", "+ cittadinoExtra.getInfoAnag().getResidenza().getDescComune()
				    : null);
			if(cittadinoExtra.getInfoSan() != null) {
				ScerevDAsl aslDom = dataDao.getAsl(cittadinoExtra.getInfoSan().getAslDomicilio());
				ScerevDAsl aslIsc = dataDao.getAsl(cittadinoExtra.getInfoSan().getAslAssistenza());
				dati_aura_medico.setAslDomicilioInAura(aslDom != null ? aslDom.getDenominazioneAzienda() : cittadinoExtra.getInfoSan().getAslDomicilio());
				dati_aura_medico.setAslIscrizione(aslIsc != null ? aslIsc.getDenominazioneAzienda() : cittadinoExtra.getInfoSan().getAslAssistenza());

				String mov = cittadinoExtra.getInfoSan().getTipoMovimento();
				if(mov != null) {
					dati_aura_medico.setMovimento(mov.equalsIgnoreCase(SharedConstants.SCELTA) ? "Scelta" : "Revoca");
					dati_aura_medico.setDataMovimento(Converter.getData(Converter.getData(cittadinoExtra.getInfoSan().getDataMovimento())));
				}
				
				dati_aura_medico.setCognome(cittadinoExtra.getInfoSan().getCognomeMedico());
				dati_aura_medico.setNome(cittadinoExtra.getInfoSan().getNomeMedico());		
				
				dati_aura_medico.setDataFineIscrizioneAsl(cittadinoExtra.getInfoSan().getDataFineASL() != null && Converter.getData(Converter.getDataToString(cittadinoExtra.getInfoSan().getDataFineASL())).equals(Converter.getData(SharedConstants.DATA_ILLIMITATA)) ? "Assistenza senza scadenza" : Converter.getDataToString(cittadinoExtra.getInfoSan().getDataFineASL()));			
				dati_aura_medico.setDataIscrizioneAsl(Converter.getData(Converter.getData(cittadinoExtra.getInfoSan().getDataInizioASL())));			
				dati_aura_medico.setTipologiaProfiloSanitario(cittadinoExtra.getInfoSan().getDescTipoProfiloSanitario());
			}
		} catch (Exception e) {
			e.printStackTrace();
			dati_aura_medico.setId(dataDao.getMessaggioPerCodice(SharedConstants.MESSAGE_AURA_NOTFOUND).getValoreMsg());
		}
		return dati_aura_medico;
	}

	private boolean isDatiSecondariAvvalorato(DatiSecondariNew ds) {
		return ds.getIndirizzo() != null && ds.getNumCivico() != null && ds.getDescComune() != null;
	}

	/**
	 * Sottofunzione di dettaglioPraticaAssembly
	 * @param pratica
	 * @param parenteExtra
	 * @param erroreParente 
	 * @return
	 */
	private DettaglioInformazioniSupplementari addInformazioniSupplementari(ScerevTPratica pratica, SoggettoAuraBodyNew parenteExtra, String erroreParente) {
		DettaglioInformazioniSupplementari informazioni_supplementari = new DettaglioInformazioniSupplementari();
		try {
			if(pratica.getScerevTInfoSupplementari()!=null) {
				informazioni_supplementari.setCodiceFiscale(pratica.getScerevTInfoSupplementari().getCfParente());
				informazioni_supplementari.setCognomeFamiliare(pratica.getScerevTInfoSupplementari().getCognomeParente());
				informazioni_supplementari.setnIscrizione(pratica.getScerevTInfoSupplementari().getNumeroIscrizione());
				informazioni_supplementari.setNomeFamiliare(pratica.getScerevTInfoSupplementari().getNomeParente());
				informazioni_supplementari.setPartitaIva(pratica.getScerevTInfoSupplementari().getPartitaIva());
				informazioni_supplementari.setProfessione(pratica.getScerevTInfoSupplementari().getProfessione());
				informazioni_supplementari.setTipoLavoro(pratica.getScerevTInfoSupplementari().getTipologiaLavoro());
				informazioni_supplementari.setDittaODatoreDiLavoro(pratica.getScerevTInfoSupplementari().getDittaDatoreLavoro());
				informazioni_supplementari.setNrTelefonoDatoreLavoro(pratica.getScerevTInfoSupplementari().getNumeroTelefonoDatore());
				informazioni_supplementari.setComuneLavoro(pratica.getScerevTInfoSupplementari().getComuneLavoro());
				informazioni_supplementari.setProvinciaLavoro(pratica.getScerevTInfoSupplementari().getProvinciaLavoro());
				informazioni_supplementari.setIndirizzoLavoro(pratica.getScerevTInfoSupplementari().getIndirizzoLavoro());
				informazioni_supplementari.setCivicoLavoro(pratica.getScerevTInfoSupplementari().getNumeroCivicoLavoro());
				informazioni_supplementari.setDataFineContrattoLavoro(pratica.getScerevTInfoSupplementari().getDataFineContratto());
				informazioni_supplementari.setNomeIstitutoStudio(pratica.getScerevTInfoSupplementari().getNomeIstituto());
				informazioni_supplementari.setComuneIstitutoStudio(pratica.getScerevTInfoSupplementari().getComuneSedeStudio());
				informazioni_supplementari.setProvinciaIstitutoStudio(pratica.getScerevTInfoSupplementari().getProvinciaSedeStudio());
				informazioni_supplementari.setIndirizzoIstitutoStudio(pratica.getScerevTInfoSupplementari().getIndirizzoSedeStudio());
				informazioni_supplementari.setCivicoIstitutoStudio(pratica.getScerevTInfoSupplementari().getCivicoSedeStudio());
				informazioni_supplementari.setDataFineIscrizioneStudio(pratica.getScerevTInfoSupplementari().getDataFineIscrizione());
				if(pratica.getScerevTInfoSupplementari().getScerevDGradoParentela()!=null)informazioni_supplementari.setRapportoParentela(pratica.getScerevTInfoSupplementari().getScerevDGradoParentela().getValoreGradoParentela());
				if(pratica.getScerevDMotivoDomicilio() != null) {
					informazioni_supplementari.setMotivoDomicilio(pratica.getScerevDMotivoDomicilio().getValoreMotivoDomicilio() + 
							(pratica.getScerevDMotivoDomicilio().getValoreMotivoDomicilio().equalsIgnoreCase("Lavoro") ? 
									pratica.getScerevTInfoSupplementari().getTipologiaLavoro() != null && 
									pratica.getScerevTInfoSupplementari().getTipologiaLavoro() ? " autonomo" : " subordinato" : ""));
				}
				if(pratica.getScerevDMotivoSoggiorno() != null) {
					informazioni_supplementari.setMotivoSoggiorno(pratica.getScerevDMotivoSoggiorno().getValoreMotivoSoggiorno());
				}
				
				informazioni_supplementari.setMotivazioneDerogaTerritoriale(pratica.getScerevTInfoSupplementari().getMotivazioneDomanda());
			}			
			
			if(parenteExtra != null) {
				if(parenteExtra.getInfoSan() != null && parenteExtra.getInfoSan().getTipoMovimento() != null 
						&& parenteExtra.getInfoSan().getTipoMovimento().equalsIgnoreCase(SharedConstants.SCELTA) 
						&& parenteExtra.getInfoSan().getCognomeMedico() !=null && parenteExtra.getInfoSan().getNomeMedico() !=null ) {
					informazioni_supplementari.setMedicoFamiliare((parenteExtra.getInfoSan().getCognomeMedico() + " " + parenteExtra.getInfoSan().getNomeMedico()));
					if(!pratica.getCodiceFiscaleMedico().equalsIgnoreCase(parenteExtra.getInfoSan().getCodiceFiscaleMedico())) {
						informazioni_supplementari.setMedicoDiverso("non coincide col medico richiesto");
					}
				} else {
					informazioni_supplementari.setMedicoDiverso("il parente non ha medico");
				}
				informazioni_supplementari.setDomicilioFamiliare(parenteExtra.getInfoAnag().getDomicilio() != null && isDatiSecondariAvvalorato(parenteExtra.getInfoAnag().getDomicilio())
						? parenteExtra.getInfoAnag().getDomicilio().getIndirizzo() + ", " + parenteExtra.getInfoAnag().getDomicilio().getNumCivico() + ", "+ parenteExtra.getInfoAnag().getDomicilio().getDescComune()
						: null);
				informazioni_supplementari.setResidenzaFamiliare(parenteExtra.getInfoAnag().getResidenza() != null && isDatiSecondariAvvalorato(parenteExtra.getInfoAnag().getResidenza())
						? parenteExtra.getInfoAnag().getResidenza().getIndirizzo() + ", " + parenteExtra.getInfoAnag().getResidenza().getNumCivico() + ", "+ parenteExtra.getInfoAnag().getResidenza().getDescComune()
					    : null);
				informazioni_supplementari.setDataNascitaFamiliare(Converter.getDataToString(parenteExtra.getInfoAnag().getDatiPrimari().getDataNascita()));
				
				if(parenteExtra.getInfoSan() != null) {
					informazioni_supplementari.setTipoProfiloFamiliare(parenteExtra.getInfoSan().getDescTipoProfiloSanitario());
					informazioni_supplementari.setDataFineAslFamiliare(parenteExtra.getInfoSan().getDataFineASL() != null && Converter.getData(Converter.getDataToString(parenteExtra.getInfoSan().getDataFineASL())).equals(Converter.getData(SharedConstants.DATA_ILLIMITATA)) ? "Assistenza senza scadenza" : Converter.getDataToString(parenteExtra.getInfoSan().getDataFineASL()));			
				}
			} else {				
				informazioni_supplementari.setMedicoDiverso(erroreParente);
			}
			
			informazioni_supplementari.setMotivoRevoca(pratica.getMotivoRevocaMedico());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return informazioni_supplementari;
	}

	/**
	 * Sottofunzione di dettaglioPraticaAssembly
	 * @param operatoreMedico
	 * @param pratica
	 * @param cittadinoExtra
	 * @param numeroAssistiti
	 * @return
	 */
	private DettaglioDatiMedico addDatiMedicoRichiesto(SoggettoOpessanBody operatoreMedico, ScerevTPratica pratica,
			SoggettoAuraBodyNew cittadinoExtra, NumeroAssistitiInCaricoMedicoResponse numeroAssistiti) {

		DettaglioDatiMedico dati_medico_richiesto = new DettaglioDatiMedico();

		try {
			dati_medico_richiesto.setNome(pratica.getNomeMedico());
			dati_medico_richiesto.setCognome(pratica.getCognomeMedico());
			dati_medico_richiesto.setTipologia(pratica.getTipologiaMedico());
			dati_medico_richiesto.setRichiesto_fino(pratica.getDataFineAssistenzaRichiesta() == null ? null
					: Converter.getStringFromTimestamp(pratica.getDataFineAssistenzaRichiesta()));
			if (pratica.getScerevDAsl3() != null)
				dati_medico_richiesto.setAsl(pratica.getScerevDAsl3().getDenominazioneAzienda());
			if (pratica.getScerevDDistretto() != null)
				dati_medico_richiesto.setDistretto(pratica.getScerevDDistretto().getDenominazioneDistretto());
			if (pratica.getScerevDAmbito() != null)
				dati_medico_richiesto.setAmbito(pratica.getScerevDAmbito().getDenominazioneAmbito());
			if (pratica.getFlagAutolimitatoMedico() != null)
				dati_medico_richiesto.setAutolimitato(pratica.getFlagAutolimitatoMedico().toString());
			dati_medico_richiesto.setAssistiti_netti(String.valueOf(numeroAssistiti.getAssistitiNetti()));
			dati_medico_richiesto.setAss_06(numeroAssistiti.getAssistitiNeonati().toString());

			InfoRapportoDiLavoro rapportoDiLavoro = getRapportoDiLavoro(operatoreMedico.getInfoRappLavoro().getInfo());
			if (rapportoDiLavoro != null) {
				dati_medico_richiesto.setMassimale(rapportoDiLavoro.getInfoMassimali().getMassimale());
				dati_medico_richiesto.setMassimale_deroga(rapportoDiLavoro.getInfoMassimali().getMassInDeroga());
				dati_medico_richiesto.setMassimale_scelte_temp(rapportoDiLavoro.getInfoMassimali().getMassScelteTemp());
				dati_medico_richiesto
						.setMassimale_ris_fascia(rapportoDiLavoro.getInfoMassimali().getMassScelta03Anni());
			}

			// lordi = netti + tmp
			String assistitiLordi = Integer.toString((numeroAssistiti.getAssistitiNetti().intValue())
					+ numeroAssistiti.getAssistitiTemporanei().intValue());
			dati_medico_richiesto.setAssistiti_lordi(assistitiLordi);

			// Massimale – Assistiti Netti
			try {
				String dispPiena = Integer.toString((Integer.parseInt(dati_medico_richiesto.getMassimale())
						- (Integer.parseInt(dati_medico_richiesto.getAssistiti_netti()))));
				dati_medico_richiesto.setDispo_piena(dispPiena);
			} catch (NumberFormatException e) {
				dati_medico_richiesto.setDispo_piena(null);
			}

			// Massimale in Deroga – Assistiti Netti
			try {
				String dispRic = Integer.toString((Integer.parseInt(dati_medico_richiesto.getMassimale_deroga())
						- (Integer.parseInt(dati_medico_richiesto.getAssistiti_netti()))));
				dati_medico_richiesto.setDisp_ric(dispRic);
			} catch (NumberFormatException e) {
				dati_medico_richiesto.setDisp_ric(null);
			}
			// [Massimale Temporanei– (Assistiti Lordi - Assistiti Netti)]
			try {
				String dispTemp = Integer.toString(Integer.parseInt(dati_medico_richiesto.getMassimale_scelte_temp())
						- (Integer.parseInt(dati_medico_richiesto.getAssistiti_lordi())
								- Integer.parseInt(dati_medico_richiesto.getAssistiti_netti())));
				dati_medico_richiesto.setDispo_temp(dispTemp);
			} catch (NumberFormatException e) {
				dati_medico_richiesto.setDispo_temp(null);
			}
			// Massimale per fascia d’età – Assistiti illimitati tra 0-6
			try {
				String dispFasc = Integer.toString(Integer.parseInt(dati_medico_richiesto.getMassimale_ris_fascia())
						- Integer.parseInt(dati_medico_richiesto.getAss_06()));
				dati_medico_richiesto.setDispo_06(dispFasc);
			} catch (NumberFormatException e) {
				dati_medico_richiesto.setDispo_06(null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dati_medico_richiesto;
	}

	/**
	 * Serve ad estrapolare il Rapporto di lavoro in essere. 
	 * FROM OPESSAN
	 */
	public InfoRapportoDiLavoro getRapportoDiLavoro(List<InfoRapportoDiLavoro> infoList) {
		for(InfoRapportoDiLavoro info : infoList) {
			if(Converter.getData(info.getDataInizioRappLavoro()).before(new Date())){
				if (info.getDataFine()== null || Converter.getData(info.getDataFine()).after(new Date())) {
					if(isQualificaMMGPLS(info)) {
						return info;
					}
				}
			}				
		}
		return null;
	}

	private boolean isQualificaMMGPLS(InfoRapportoDiLavoro info) {
		return info.getQualifica().equalsIgnoreCase("Titolare Generico") ||
				info.getQualifica().equalsIgnoreCase("Titolare Pediatra") ||
				info.getQualifica().equalsIgnoreCase("Supplente Generico") ||
				info.getQualifica().equalsIgnoreCase("Supplente Pediatra");
	}

	/**
	 * Sottofunzione di dettaglioPraticaAssembly
	 * @param pratica
	 * @return
	 */
	private DettaglioDatiMedicoExtra addDatiMedicoExtra(ScerevTPratica pratica) {
		DettaglioDatiMedicoExtra dati_medico_extra = new DettaglioDatiMedicoExtra();
		if(pratica.getScerevDRegione()!=null) {			
			try {
				dati_medico_extra.setAziendaSanitaria(pratica.getCodiceAslPrecedente()); //Non e' il codice ma la denominazione. Non si possono avere codici di asl fuori regione Piemonte
				dati_medico_extra.setCognome(pratica.getCognomeMedicoPrecedente());
				dati_medico_extra.setNome(pratica.getNomeMedicoPrecedente());
				dati_medico_extra.setRegione(pratica.getScerevDRegione().getNomeRegione());
			} catch (Exception e ) {}
		} else if(Checker.isValorizzato(pratica.getAslResidenza()) && 
				pratica.getAslResidenza().equalsIgnoreCase(SharedConstants.EXTRA_REGIONE)) {
			dati_medico_extra.setId(dataDao.getMessaggioPerCodice(SharedConstants.MESSAGGIO_36).getValoreMsg());
		} else {
			return null;
		}
		return dati_medico_extra;
	}

	/**
	 * Sottofunzione di dettaglioPraticaAssembly
	 * @param pratica
	 * @param cittadinoExtra
	 * @return
	 */
	private DettaglioDatiRichiedente addDatiRichiedente(ScerevTPratica pratica, SoggettoAuraBodyNew cittadinoExtra) {
		DettaglioDatiRichiedente dati_richiedente = new DettaglioDatiRichiedente();
		try {
			dati_richiedente.setAmbito_domicilio_dichiarato(pratica.getAmbitoDomicilioDichiarato());
			if(pratica.getScerevDAsl1()!=null) dati_richiedente.setAsl_domicilio_dichiarato(pratica.getScerevDAsl1().getDenominazioneAzienda());
			dati_richiedente.setDistretto_domicilio_dichiarato(pratica.getDistrettoDomicilioDichiarato());
			dati_richiedente.setAsl_residenza_dichiarata(pratica.getAslResidenza());
			
			String residenzaDb = null;
			String domicilioDb = null;
			if(pratica.getScerevTLocalita1() != null) residenzaDb = pratica.getScerevTLocalita1().getIndirizzo()+", "+ pratica.getScerevTLocalita1().getNumeroCivico()+" - "+pratica.getScerevTLocalita1().getComune();
			if(pratica.getScerevTLocalita2()!=null) domicilioDb = pratica.getScerevTLocalita2().getIndirizzo()+", "+ pratica.getScerevTLocalita2().getNumeroCivico()+" - "+pratica.getScerevTLocalita2().getComune();

			dati_richiedente.setResidenza_dichiarata(residenzaDb);
			dati_richiedente.setDomicilio_dichiarato(domicilioDb);
			
			dati_richiedente.setCittadinanza(pratica.getScerevDCittadinanza().getValoreCittadinanza());
			dati_richiedente.setCodice_fiscale(pratica.getCfAssistito());
			if(pratica.getDataNascitaAssistito() != null) dati_richiedente.setData_nascita(Converter.getStringFromTimestamp(pratica.getDataNascitaAssistito()));		
			try {
				XMLGregorianCalendar c = Converter.getXMLGregorianCalendar(Converter.getData(pratica.getDataNascitaAssistito()));
				dati_richiedente.setEta(Converter.CalcolaEta(c).toString());
			} catch(Exception e) {}
			
			dati_richiedente.setLuogo_nascita(pratica.getLuogoNascitaAssistito());
			dati_richiedente.setSesso(pratica.getSessoAssistito());
			dati_richiedente.setNome(pratica.getNomeAssistito());
			dati_richiedente.setCognome(pratica.getCognomeAssistito());
			if(cittadinoExtra != null && cittadinoExtra.getInfoSan()!=null && cittadinoExtra.getInfoSan().getDataFineASL()!= null) dati_richiedente.setData_fine_assistenza(Converter.getDataToString(cittadinoExtra.getInfoSan().getDataFineASL()));
			
			dati_richiedente.setTelefono(pratica.getRecapitoTelefonicoAssistito());
			
		} catch (Exception e ) {}
		return dati_richiedente;		
	}
	
	public byte[] getReportPratica(Map<String, Object> parameters) {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		/* 2020-10-25: Fix problema stampa (StackOverflowError):
		   1) Utilizzato il file DettagliPratica.jasper al posto del DettagliPratica.jrxml per evitare una compilazione ulteriore a runtime
		   2) Aggiunta dipendenza tnr-1.0.0 nell'ivy-xml per il carattere Times New Roman
		*/ 
		InputStream input = classloader.getResourceAsStream(Constants.REPORT_DETTAGLI_PRATICA); // fix problema stampa

//		JasperReport jasperReport = null;
		try {
			//jasperReport = JasperCompileManager.compileReport(input); // fix problema stampa
		
			JasperPrint jasperPrint = null;
			//jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource()); // fix problema stampa
			jasperPrint = JasperFillManager.fillReport(input, parameters, new JREmptyDataSource());
				
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

		return outputStream.toByteArray();
	}
	
	/**
	 * Check dei parametri in input del servizio RespingiPratica
	 */
	public void checkRespingeParameters(UpdatePratica pratica) throws ParametriObbligatoriException, IntegritaException{
		if(pratica.getId()==0) throw new ParametriObbligatoriException(Util.composeMessage(dataDao.getMessaggioPerCodice(SharedConstants.ERROR_CODE_PARAMETRI_OBBLIGATORI).getValoreMsg()," Numero Pratica non presente"));	
		if(pratica.getMotivazione()==null || pratica.getMotivazione().equalsIgnoreCase("")) throw new ParametriObbligatoriException(Util.composeMessage(dataDao.getMessaggioPerCodice(SharedConstants.ERROR_CODE_PARAMETRI_OBBLIGATORI).getValoreMsg()," Motivazione non presente"));		
	}
	
	/**
	 * Check dei parametri in input del servizio RichiedeRevisionePratica
	 */
	public void checkRichiestaRevisioneParameters(UpdatePratica pratica) throws ParametriObbligatoriException, IntegritaException{
		if(pratica.getId()==0) throw new ParametriObbligatoriException(Util.composeMessage(dataDao.getMessaggioPerCodice(SharedConstants.ERROR_CODE_PARAMETRI_OBBLIGATORI).getValoreMsg()," Numero Pratica non presente"));	
		if(pratica.getMotivazione()==null || pratica.getMotivazione().equalsIgnoreCase("")) throw new ParametriObbligatoriException(Util.composeMessage(dataDao.getMessaggioPerCodice(SharedConstants.ERROR_CODE_PARAMETRI_OBBLIGATORI).getValoreMsg()," Motivazione non presente"));
		if(pratica.getNota_assistito()==null || pratica.getNota_assistito().equalsIgnoreCase("")) throw new ParametriObbligatoriException(Util.composeMessage(dataDao.getMessaggioPerCodice(SharedConstants.ERROR_CODE_PARAMETRI_OBBLIGATORI).getValoreMsg()," Nota richiedente non presente"));		
	}
		
	public Map<String, Object> getReportParameters(ScerevTPratica praticaAssociata, DettaglioOutput dettagliPratica) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		
			// solo se MotivoDomicilio = "Motivi di Cura", devo fare apparire questa stringa nel report
//			if(praticaAssociata.getScerevDMotivoDomicilio() != null && praticaAssociata.getScerevDMotivoDomicilio().getValoreMotivoDomicilio().equalsIgnoreCase("Motivi di Cura")) parameters.put("CURA_MOTIVOCURA", "Motivi di Cura");	
		
		    parameters.put("INTESTAZIONE_NUM_PRATICA", praticaAssociata.getNumeroPratica());
	    	parameters.put("INTESTAZIONE_STATO_PRATICA", praticaAssociata.getScerevDPraticaStato().getValorePraticaStato());
	    	
	    	//VISUALIZZATO SOLO SE PRESA IN CARICO nome e cognome dell'operatore che ha preso in carico, 
		    if(praticaAssociata.getNomeOperatore() != null)parameters.put("INTESTAZIONE_RICHIESTA_IN_CARICO_A", praticaAssociata.getNomeOperatore() + " " + praticaAssociata.getCognomeOperatore() );
	    	  
		    // VISUALIZZATO SOLO SE RICHIESTA INSERITA DA DELEGATO
		    if(praticaAssociata.getNomeDelegato()!=null) parameters.put("INTESTAZIONE_NOME_DELEGATO", praticaAssociata.getNomeDelegato());
		    
		    // VISUALIZZATO SOLO SE RICHIESTA INSERITA DA DELEGATO
		    if(praticaAssociata.getCognomeDelegato()!=null) parameters.put("INTESTAZIONE_COGNOME_DELEGATO", praticaAssociata.getCognomeDelegato());
		    
		    // VISUALIZZATO SOLO SE RICHIESTA INSERITA DA DELEGATO
		    if(praticaAssociata.getCfDelegato()!=null) parameters.put("INTESTAZIONE_CODICE_FISCALE_DELEGATO", praticaAssociata.getCfDelegato());
		    
		    parameters.put("DATI_ASSISTITO_NOME_ASSISTITO", dettagliPratica.getDati_richiedente().getNome());
		    parameters.put("DATI_ASSISTITO_COGNOME_ASSISTITO", dettagliPratica.getDati_richiedente().getCognome());
		    parameters.put("DATI_ASSISTITO_TIPOLOGIA_RICHIESTA", getTipologieRichiesta(praticaAssociata.getPkPratica()).replaceAll(" - ", ", "));
		    parameters.put("DATI_ASSISTITO_CODICE_FISCALE_ASSISTITO",dettagliPratica.getDati_richiedente().getCodice_fiscale() );
		    parameters.put("DATI_ASSISTITO_LUOGO_NASCITA_ASSISTITO", dettagliPratica.getDati_richiedente().getLuogo_nascita());
		    parameters.put("DATI_ASSISTITO_DATA_NASCITA_ASSISTITO", dettagliPratica.getDati_richiedente().getData_nascita());
		    parameters.put("DATI_ASSISTITO_ETA_ASSISTITO", dettagliPratica.getDati_richiedente().getEta());
		    parameters.put("DATI_ASSISTITO_CITTADINANZA", dettagliPratica.getDati_richiedente().getCittadinanza());
		    parameters.put("DATI_ASSISTITO_RESIDENZA_DICHIARATA", dettagliPratica.getDati_richiedente().getResidenza_dichiarata());
		    parameters.put("DATI_ASSISTITO_DOMICILIO_DICHIARATO", dettagliPratica.getDati_richiedente().getDomicilio_dichiarato());
		    parameters.put("DATI_ASSISTITO_ASL_DOMICILIO_DICHIARATO", dettagliPratica.getDati_richiedente().getAsl_domicilio_dichiarato());
		    parameters.put("DATI_ASSISTITO_ASL_RESIDENZA_DICHIARATA", dettagliPratica.getDati_richiedente().getAsl_residenza_dichiarata());
		    parameters.put("DATI_ASSISTITO_DISTRETTO_DOMICILIO_DICHIARATO", dettagliPratica.getDati_richiedente().getDistretto_domicilio_dichiarato());
		    parameters.put("DATI_ASSISTITO_AMBITO_DOMICILIO_DICHIARATO", dettagliPratica.getDati_richiedente().getAmbito_domicilio_dichiarato());
		    parameters.put("DATI_ASSISTITO_ASS_SANITARIA_FINO_AL", dettagliPratica.getDati_richiedente().getData_fine_assistenza() != null && 
		    		Converter.getData( dettagliPratica.getDati_richiedente().getData_fine_assistenza() ).equals(
		    		Converter.getData(SharedConstants.DATA_ILLIMITATA) ) ? "Assistenza senza scadenza" : dettagliPratica.getDati_richiedente().getData_fine_assistenza());
		    parameters.put("DATI_ASSISTITO_TELEFONO", dettagliPratica.getDati_richiedente().getTelefono());
		    parameters.put("AURA_NOME_MEDICO", dettagliPratica.getDati_aura_medico().getNome());
		    parameters.put("AURA_COGNOME_MEDICO", dettagliPratica.getDati_aura_medico().getCognome());
		    parameters.put("AURA_RESIDENZA_AURA",dettagliPratica.getDati_aura_medico().getResidenzaInAura());
		    parameters.put("AURA_DOMICILIO_AURA", dettagliPratica.getDati_aura_medico().getDomicilioInAura());
		    parameters.put("AURA_ASL_DOMICILIO", dettagliPratica.getDati_aura_medico().getAslDomicilioInAura());
		    parameters.put("AURA_ASL_ISCRIZIONE", dettagliPratica.getDati_aura_medico().getAslIscrizione());
		    parameters.put("AURA_TIPOLOGIA_PROFILO_SANITARIO", dettagliPratica.getDati_aura_medico().getTipologiaProfiloSanitario());
		    parameters.put("AURA_DATA_ISCRIZIONE_ASL", dettagliPratica.getDati_aura_medico().getDataIscrizioneAsl());
		    parameters.put("AURA_DATA_FINE_ISCRIZIONE_ASL", dettagliPratica.getDati_aura_medico().getDataFineIscrizioneAsl());
		    parameters.put("MEDEXTRAREG_NOME_MEDICO_PRECEDENTE", dettagliPratica.getDati_medico_extra() == null ? null : dettagliPratica.getDati_medico_extra().getNome());
		    parameters.put("MEDEXTRAREG_COGNOME_MEDICO_PRECEDENTE", dettagliPratica.getDati_medico_extra() == null ? null : dettagliPratica.getDati_medico_extra().getCognome());
		    parameters.put("MEDEXTRAREG_REGIONE", dettagliPratica.getDati_medico_extra() == null ? null : dettagliPratica.getDati_medico_extra().getRegione());
		    parameters.put("MEDEXTRAREG_AZIENDA_SANITARIA", dettagliPratica.getDati_medico_extra() == null ? null : dettagliPratica.getDati_medico_extra().getAziendaSanitaria());
		    parameters.put("GENERICO_MOTIVAZIONE_SOGGIORNO_ITALIA", praticaAssociata.getScerevDMotivoSoggiorno() != null ? praticaAssociata.getScerevDMotivoSoggiorno().getValoreMotivoSoggiorno() : null);
		    parameters.put("GENERICO_MOTIVAZIONE_DOMICILIO", praticaAssociata.getScerevDMotivoDomicilio() != null ? praticaAssociata.getScerevDMotivoDomicilio().getValoreMotivoDomicilio().toUpperCase() : null);
		    if(dettagliPratica.getInformazioni_supplementari() != null) {
			    parameters.put("LAVORO_TIPO_LAVORO", dettagliPratica.getInformazioni_supplementari().getTipoLavoro() == null ? null : dettagliPratica.getInformazioni_supplementari().getTipoLavoro() ? "AUTONOMO" : "SUBORDINATO");
			    
			    //se TipoLavoro = true, allora visualizzo la seziona LAVORO AUTONOMO
			    if(praticaAssociata.getScerevTInfoSupplementari() != null && praticaAssociata.getScerevTInfoSupplementari().getTipologiaLavoro() != null) {
				    if(praticaAssociata.getScerevTInfoSupplementari().getTipologiaLavoro()) {
					    parameters.put("LAVOROAUTON_PROFESSIONE", dettagliPratica.getInformazioni_supplementari().getProfessione());
					    parameters.put("LAVOROAUTON_PARTITA_IVA", dettagliPratica.getInformazioni_supplementari().getPartitaIva());
					    parameters.put("LAVOROAUTON_NUMERO_DI_ISCRIZIONE", dettagliPratica.getInformazioni_supplementari().getnIscrizione());
				    } else {
				    //altrimenti visualizzo la sezione LAVORO SUBORDINATO
					    parameters.put("LAVOROSUB_DITTA_O_DATORE_LAVORO", dettagliPratica.getInformazioni_supplementari().getDittaODatoreDiLavoro());
					    parameters.put("LAVOROSUB_NR_TELEFONO_DATORE_LAVORO", dettagliPratica.getInformazioni_supplementari().getNrTelefonoDatoreLavoro());
//					    parameters.put("LAVOROSUB_COMUNE_LAVORO", dettagliPratica.getInformazioni_supplementari().getComuneLavoro());
//					    parameters.put("LAVOROSUB_PROVINCIA_LAVORO", dettagliPratica.getInformazioni_supplementari().getProvinciaLavoro());
					    parameters.put("LAVOROSUB_INDIRIZZO_LAVORO", ""+dettagliPratica.getInformazioni_supplementari().getIndirizzoLavoro() + ", "+ dettagliPratica.getInformazioni_supplementari().getCivicoLavoro());
//					    parameters.put("LAVOROSUB_CIVICO_LAVORO", dettagliPratica.getInformazioni_supplementari().getCivicoLavoro());
					    parameters.put("LAVOROSUB_DATA_FINE_CONTRATTO_LAVORO", dettagliPratica.getInformazioni_supplementari().getDataFineContrattoLavoro() == null ? null : Converter.getStringFromTimestamp(dettagliPratica.getInformazioni_supplementari().getDataFineContrattoLavoro()));     
				    }
			    }
			    parameters.put("STUDIO_NOME_ISTITUTO", dettagliPratica.getInformazioni_supplementari().getNomeIstitutoStudio());
			    parameters.put("STUDIO_COMUNE_ISTITUTO", dettagliPratica.getInformazioni_supplementari().getComuneIstitutoStudio());
			    parameters.put("STUDIO_PROVINCIA_ISTITUTO", dettagliPratica.getInformazioni_supplementari().getProvinciaIstitutoStudio());
			    parameters.put("STUDIO_INDIRIZZO_ISTITUTO", ""+dettagliPratica.getInformazioni_supplementari().getIndirizzoIstitutoStudio()+", "+dettagliPratica.getInformazioni_supplementari().getCivicoIstitutoStudio());
//			    parameters.put("STUDIO_CIVICO_ISTITUTO", dettagliPratica.getInformazioni_supplementari().getCivicoIstitutoStudio());
			    parameters.put("STUDIO_DATA_FINE_ISCRIZIONE", dettagliPratica.getInformazioni_supplementari().getDataFineIscrizioneStudio() == null ? null : Converter.getStringFromTimestamp(dettagliPratica.getInformazioni_supplementari().getDataFineIscrizioneStudio()));
			    parameters.put("RICONG_NOME_FAMILIARE", dettagliPratica.getInformazioni_supplementari().getNomeFamiliare());
			    parameters.put("RICONG_COGNOME_FAMILIARE", dettagliPratica.getInformazioni_supplementari().getCognomeFamiliare());
			    parameters.put("RICONG_CODICE_FISCALE_FAMILIARE", dettagliPratica.getInformazioni_supplementari().getCodiceFiscale()); 
			    parameters.put("RICONG_GRADI_PARENTELA", dettagliPratica.getInformazioni_supplementari().getRapportoParentela());
			    parameters.put("RICONG_COGNOME_E_NOME_MEDICO_FAMILIARE", dettagliPratica.getInformazioni_supplementari().getMedicoFamiliare());
			    parameters.put("RICONG_DATA_NASCITA_FAMILIARE", dettagliPratica.getInformazioni_supplementari().getDataNascitaFamiliare());
			    parameters.put("RICONG_TIPO_PROFILO_FAMILIARE", dettagliPratica.getInformazioni_supplementari().getTipoProfiloFamiliare());
			    parameters.put("RICONG_FINE_ASL_FAMILIARE", dettagliPratica.getInformazioni_supplementari().getDataFineAslFamiliare());
			    parameters.put("RICONG_RES_AURA_FAMILIARE", dettagliPratica.getInformazioni_supplementari().getResidenzaFamiliare());
			    parameters.put("RICONG_DOM_AURA_FAMILIARE", dettagliPratica.getInformazioni_supplementari().getDomicilioFamiliare());
			    
			    parameters.put("MOTIVAZIONE_DOMANDA", dettagliPratica.getInformazioni_supplementari().getMotivazioneDerogaTerritoriale());
		    }
		    parameters.put("DEROGA_RICONG_FAM", 
		    		(praticaAssociata.getRicongiungimentoFamiliare() != null && 
		    		 praticaAssociata.getRicongiungimentoFamiliare()) && 
		    		(dettagliPratica.getInformazioni_supplementari().getNomeFamiliare() != null || 
		    		 dettagliPratica.getInformazioni_supplementari().getCognomeFamiliare() != null ||
		    		 dettagliPratica.getInformazioni_supplementari().getCodiceFiscale() != null ||
		    		 dettagliPratica.getInformazioni_supplementari().getRapportoParentela() != null) 
		    );
		    
		    parameters.put("ALLEGATI", getAllegatiReportDataSource(dettagliPratica));
		    
		    parameters.put("MEDICORICHIESTO_NOME", dettagliPratica.getDati_medico_richiesto().getNome());
		    parameters.put("MEDICORICHIESTO_COGNOME", dettagliPratica.getDati_medico_richiesto().getCognome());
		    parameters.put("MEDICORICHIESTO_TIPOLOGIA", dettagliPratica.getDati_medico_richiesto().getTipologia());
		    parameters.put("MEDICORICHIESTO_ASL", dettagliPratica.getDati_medico_richiesto().getAsl());
		    parameters.put("MEDICORICHIESTO_DISTRETTO", dettagliPratica.getDati_medico_richiesto().getDistretto());
		    parameters.put("MEDICORICHIESTO_AMBITO", dettagliPratica.getDati_medico_richiesto().getAmbito());
		    parameters.put("MEDICORICHIESTO_RICHIESTO_MEDICO_FINO_AL", dettagliPratica.getDati_medico_richiesto().getRichiesto_fino());
		    parameters.put("MEDICORICHIESTO_FLAG_AUTOLIMITATO", dettagliPratica.getDati_medico_richiesto().getAutolimitato() == null ? null : 
		    	dettagliPratica.getDati_medico_richiesto().getAutolimitato().equalsIgnoreCase("false") ? "NO" : "SI");
		    parameters.put("MEDICORICHIESTO_MASSIMALE", dettagliPratica.getDati_medico_richiesto().getMassimale());
		    parameters.put("MEDICORICHIESTO_MASSIMALE_IN_DEROGA", dettagliPratica.getDati_medico_richiesto().getMassimale_deroga());
		    parameters.put("MEDICORICHIESTO_MASSIMALE_TEMPORANEI", dettagliPratica.getDati_medico_richiesto().getMassimale_scelte_temp());
		    parameters.put("MEDICORICHIESTO_MASSIMALE_PER_FASCIA_ETA", dettagliPratica.getDati_medico_richiesto().getMassimale_ris_fascia());
		    parameters.put("MEDICORICHIESTO_ASSISTITI_NETTI", dettagliPratica.getDati_medico_richiesto().getAssistiti_netti());
		    parameters.put("MEDICORICHIESTO_ASSISTITI_NETTI_0-6", dettagliPratica.getDati_medico_richiesto().getAss_06());
		    parameters.put("MEDICORICHIESTO_ASSISTITI_LORDI", dettagliPratica.getDati_medico_richiesto().getAssistiti_lordi());
		    parameters.put("MEDICORICHIESTO_DISPONIBILITA_PIENA", dettagliPratica.getDati_medico_richiesto().getDispo_piena());
		    parameters.put("MEDICORICHIESTO_DISPONIBILITA_RICONG", dettagliPratica.getDati_medico_richiesto().getDisp_ric());
		    parameters.put("MEDICORICHIESTO_DISPONIBILITA_TEMP", dettagliPratica.getDati_medico_richiesto().getDispo_temp());
		    parameters.put("MEDICORICHIESTO_DISPONIBILITA_0-6", dettagliPratica.getDati_medico_richiesto().getDispo_06());
		    
		    if( (praticaAssociata.getScerevDPraticaStato().getValorePraticaStato().equalsIgnoreCase(SharedConstants.STATO_COMPLETATA) || 
		    		praticaAssociata.getScerevDPraticaStato().getValorePraticaStato().equalsIgnoreCase(SharedConstants.STATO_CHIUSA))) {
			    if(praticaAssociata.getScerevDTipologiaProfilo()!=null) parameters.put("ACCETRICHIESTA_TIPOLOGIA_PROFILO", praticaAssociata.getScerevDTipologiaProfilo().getValoreTipologiaProfilo());
			    parameters.put("ACCETRICHIESTA_DURATA_ASSISTENZA_DA", praticaAssociata.getDataInizioAssistenzaImposta() == null ? praticaAssociata.getDataCreazione() == null ? null : Converter.getStringFromTimestamp(praticaAssociata.getDataCreazione()) : Converter.getStringFromTimestamp(praticaAssociata.getDataInizioAssistenzaImposta()));
			    parameters.put("ACCETRICHIESTA_DURATA_ASSISTENZA_A", praticaAssociata.getDataFineAssistenzaImposta() == null ? praticaAssociata.getDataFineAssistenzaRichiesta() == null ? null :  Converter.getStringFromTimestamp(praticaAssociata.getDataFineAssistenzaRichiesta()) : praticaAssociata.getDataFineAssistenzaImposta().equals(
			    		new Timestamp(Converter.getData(SharedConstants.DATA_ILLIMITATA).getTime())) ? "Assistenza senza scadenza" : Converter.getStringFromTimestamp(praticaAssociata.getDataFineAssistenzaImposta()));
			    if(praticaAssociata.getMedicoGenerico()!=null && praticaAssociata.getMedicoGenerico()) parameters.put("ACCETRICHIESTA_MEDICO_GENERICO", "SI");
			    else parameters.put("ACCETRICHIESTA_MEDICO_GENERICO", "NO");
			    if(praticaAssociata.getDerogaPediatra()!=null && praticaAssociata.getDerogaPediatra()) parameters.put("ACCETRICHIESTA_DEROGA_PEDIATRA", "SI");
			    else parameters.put("ACCETRICHIESTA_DEROGA_PEDIATRA", "NO");
			    if(praticaAssociata.getDerogaTerritoriale()!=null && praticaAssociata.getDerogaTerritoriale()) parameters.put("ACCETRICHIESTA_DEROGA_TERRITORIALE", "SI");
			    else parameters.put("ACCETRICHIESTA_DEROGA_TERRITORIALE", "NO");
			    if(praticaAssociata.getDerogaAssociazione() != null && praticaAssociata.getDerogaAssociazione()) parameters.put("ACCETRICHIESTA_DEROGA_ASSOCIAZIONE", "SI");
			    else parameters.put("ACCETRICHIESTA_DEROGA_ASSOCIAZIONE", "NO");
			    if(praticaAssociata.getDerogaMassimale() != null && praticaAssociata.getDerogaMassimale()) parameters.put("ACCETRICHIESTA_DEROGA_MASSIMALE", "SI");
			    else parameters.put("ACCETRICHIESTA_DEROGA_MASSIMALE", "NO");
			    if(praticaAssociata.getDerogaMassimaleTemp() != null && praticaAssociata.getDerogaMassimaleTemp()) parameters.put("ACCETRICHIESTA_DEROGA_MASSIMALE_TEMP", "SI");
			    else parameters.put("ACCETRICHIESTA_DEROGA_MASSIMALE_TEMP", "NO");
			    if(praticaAssociata.getRicongiungimentoFamiliare() != null && praticaAssociata.getRicongiungimentoFamiliare()) parameters.put("ACCETRICHIESTA_RICONG_FAMILIARE", "SI");
			    else parameters.put("ACCETRICHIESTA_RICONG_FAMILIARE", "NO");
			    if(praticaAssociata.getMedicoRevocato() != null && praticaAssociata.getMedicoRevocato()) parameters.put("ACCETRICHIESTA_MEDICO_REVOCATO", "SI");
			    else parameters.put("ACCETRICHIESTA_MEDICO_REVOCATO", "NO");
		    }
		
		return parameters;
	}

	private JRBeanCollectionDataSource getAllegatiReportDataSource(DettaglioOutput dettagliPratica) {
		if (dettagliPratica.getAllegati() == null || dettagliPratica.getAllegati().isEmpty() ) return null;

		List<ModelAllegato> allegati = new ArrayList<ModelAllegato>();
		for(DettaglioAllegato d : dettagliPratica.getAllegati()) {
			for(Allegato a : d.getAllegati()) {
				allegati.add(new ModelAllegato(d.getTipoAllegato(), a.getNomeAllegato()));
			}
		}
		return new JRBeanCollectionDataSource(allegati);
	}

	/**
	 * Funzione per respingere la richiesta in input
	 */
	public ScerevTPratica respingeRichiesta(UpdatePratica pratica, UserInfo userInfo) {		
		ScerevTPratica praticaEntity = getPratica(pratica.getId());
		List<ScerevDPraticaStato> listaStati = dataDao.getStatiPratica();
		List<ScerevDMotivazione> listaMotivazioni = dataDao.getAllMotivazioni();
		
		// Seleziono lo stato "Respinto"
		ScerevDPraticaStato statoRespinto = null;		
		for(ScerevDPraticaStato stato : listaStati) {
			if(stato.getValorePraticaStato().equalsIgnoreCase("respinta")) statoRespinto = stato;
		}
		
		// Seleziono la motivazione
		ScerevDMotivazione motivazioneRicercata = null;
		for(ScerevDMotivazione motivazione : listaMotivazioni) {
			if(motivazione.getCodiceMotivazione().equalsIgnoreCase(pratica.getMotivazione())) motivazioneRicercata = motivazione;
		}
		
		// FK_PRATICA_STATO: questo campo deve essere valorizzato con il valore PK_PRATICA_STATO tale per cui il relativo campo VALORE_PRATICA_STATO nella tabella SCEREV_D_PRATICA_STATO sia uguale a “respinta”;
		praticaEntity.setScerevDPraticaStato(statoRespinto);
		// CF_OPERATORE, valorizzato con il codice fiscale dell’operatore recuperato da Shibboleth dopo l’autenticazione;
		praticaEntity.setCfOperatore(userInfo.getCodFisc());
		// NOME_OPERATORE, valorizzato con il nome dell’operatore recuperato da Shibboleth dopo l’autenticazione;
		praticaEntity.setNomeOperatore(userInfo.getNome());
		// COGNOME_OPERATORE, valorizzato con il cognome dell’operatore recuperato da Shibboleth dopo l’autenticazione;
		praticaEntity.setCognomeOperatore(userInfo.getCognome());
		// FK_MOTIVAZIONE, questo campo deve essere valorizzato con il valore PK_MOTIVAZIONE tale per cui il relativo campo VALORE_MOTIVAZIONE nella tabella SCEREV_D_MOTIVAZIONE sia uguale alla motivazione inserita dall’operatore e ricevuta in input;
		praticaEntity.setScerevDMotivazione(motivazioneRicercata);
		// DATA MODIFICA, data di sistema.
		praticaEntity.setDataModifica(new Timestamp(System.currentTimeMillis()));

		// Update del record in ScerevTPratica
		praticaEntity = dataDao.updatePratica(praticaEntity);
		
		// Compongo oggetto cronologia
		ScerevTCronologia cronologia = new ScerevTCronologia();
		cronologia.setScerevTPratica(praticaEntity);
		cronologia.setNotaRichiedente(pratica.getNota_assistito());
		cronologia.setNotaInterna(pratica.getNota_interna());
		cronologia.setUtente("OPERATORE: "+userInfo.getNome()+" "+userInfo.getCognome());
		cronologia.setScerevDPraticaStato(statoRespinto);
		cronologia.setDataOraInserimento(new Timestamp(System.currentTimeMillis()));
		cronologia.setDataCreazione(new Timestamp(System.currentTimeMillis()));
		cronologia.setDataModifica(new Timestamp(System.currentTimeMillis()));
		dataDao.insertCronologia(cronologia);
		
		return praticaEntity;
	}	
	
	/**
	 * Funzione per richiedere la revisione/integrazione della pratica in input
	 */
	public ScerevTPratica richiedeIntegrazione(UpdatePratica pratica, UserInfo userInfo) {		
		ScerevTPratica praticaEntity = getPratica(pratica.getId());
		List<ScerevDPraticaStato> listaStati = dataDao.getStatiPratica();
		List<ScerevDMotivazione> listaMotivazioni = dataDao.getAllMotivazioni();
		
		// Seleziono lo stato "In revisione"
		ScerevDPraticaStato stato_InRevisione = null;		
		for(ScerevDPraticaStato stato : listaStati) {
			if(stato.getValorePraticaStato().equalsIgnoreCase("In revisione")) stato_InRevisione = stato;
		}
		
		// Seleziono la motivazione
		ScerevDMotivazione motivazioneRicercata = null;
		for(ScerevDMotivazione motivazione : listaMotivazioni) {
			if(motivazione.getCodiceMotivazione().equalsIgnoreCase(pratica.getMotivazione())) motivazioneRicercata = motivazione;
		}
		
		// FK_PRATICA_STATO: questo campo deve essere valorizzato con il valore PK_PRATICA_STATO tale per cui il relativo campo VALORE_PRATICA_STATO nella tabella SCEREV_D_PRATICA_STATO sia uguale a “In revisione”;
		praticaEntity.setScerevDPraticaStato(stato_InRevisione);
		// CF_OPERATORE, valorizzato con il codice fiscale dell’operatore recuperato da Shibboleth dopo l’autenticazione;
		praticaEntity.setCfOperatore(userInfo.getCodFisc());
		// NOME_OPERATORE, valorizzato con il nome dell’operatore recuperato da Shibboleth dopo l’autenticazione;
		praticaEntity.setNomeOperatore(userInfo.getNome());
		// COGNOME_OPERATORE, valorizzato con il cognome dell’operatore recuperato da Shibboleth dopo l’autenticazione;
		praticaEntity.setCognomeOperatore(userInfo.getCognome());
		// FK_MOTIVAZIONE, questo campo deve essere valorizzato con il valore PK_MOTIVAZIONE tale per cui il relativo campo VALORE_MOTIVAZIONE nella tabella SCEREV_D_MOTIVAZIONE sia uguale alla motivazione inserita dall’operatore e ricevuta in input;
		praticaEntity.setScerevDMotivazione(motivazioneRicercata);
		// DATA MODIFICA, data di sistema.
		praticaEntity.setDataModifica(new Timestamp(System.currentTimeMillis()));

		// Update del record in ScerevTPratica
		praticaEntity = dataDao.updatePratica(praticaEntity);
		
		// Compongo oggetto cronologia
		ScerevTCronologia cronologia = new ScerevTCronologia();
		cronologia.setScerevTPratica(praticaEntity);
		cronologia.setNotaRichiedente(pratica.getNota_assistito());
		cronologia.setNotaInterna(pratica.getNota_interna());
		cronologia.setUtente("OPERATORE: "+userInfo.getNome()+" "+userInfo.getCognome());
		cronologia.setScerevDPraticaStato(stato_InRevisione);
		cronologia.setDataOraInserimento(new Timestamp(System.currentTimeMillis()));
		cronologia.setDataCreazione(new Timestamp(System.currentTimeMillis()));
		cronologia.setDataModifica(new Timestamp(System.currentTimeMillis()));
		dataDao.insertCronologia(cronologia);
		
		return praticaEntity;
	}
	
	/**
	 * Funzione per chiudere la richiesta in input
	 */
	public ScerevTPratica chiudeRichiesta(ChiudeRichiesta pratica, UserInfo userInfo) {		
		ScerevTPratica praticaEntity = getPratica(Integer.parseInt(pratica.getNumeroPratica()));
		List<ScerevDPraticaStato> listaStati = dataDao.getStatiPratica();
		
		// Seleziono lo stato "Chiuso"
		ScerevDPraticaStato statoChiuso = null;		
		for(ScerevDPraticaStato stato : listaStati) {
			if(stato.getValorePraticaStato().equalsIgnoreCase("chiusa")) statoChiuso = stato;
		}
		
		// FK_PRATICA_STATO: questo campo deve essere valorizzato con il valore PK_PRATICA_STATO tale per cui il relativo campo VALORE_PRATICA_STATO nella tabella SCEREV_D_PRATICA_STATO sia uguale a “chiusa”;
		praticaEntity.setScerevDPraticaStato(statoChiuso);
		// CF_OPERATORE, valorizzato con il codice fiscale dell’operatore recuperato da Shibboleth dopo l’autenticazione;
		praticaEntity.setCfOperatore(userInfo.getCodFisc());
		// NOME_OPERATORE, valorizzato con il nome dell’operatore recuperato da Shibboleth dopo l’autenticazione;
		praticaEntity.setNomeOperatore(userInfo.getNome());
		// COGNOME_OPERATORE, valorizzato con il cognome dell’operatore recuperato da Shibboleth dopo l’autenticazione;
		praticaEntity.setCognomeOperatore(userInfo.getCognome());
		// FK_MOTIVAZIONE, questo campo deve essere valorizzato con il valore PK_MOTIVAZIONE tale per cui il relativo campo VALORE_MOTIVAZIONE nella tabella SCEREV_D_MOTIVAZIONE sia uguale alla motivazione inserita dall’operatore e ricevuta in input;
//		praticaEntity.setScerevDMotivazione(motivazioneRicercata);
//		// DATA MODIFICA, data di sistema.
		praticaEntity.setDataModifica(new Timestamp(System.currentTimeMillis()));
		
		praticaEntity.setMedicoRevocato(true);

		// Update del record in ScerevTPratica
		praticaEntity = dataDao.updatePratica(praticaEntity);
		
		// Compongo oggetto cronologia
		ScerevTCronologia cronologia = new ScerevTCronologia();
		cronologia.setScerevTPratica(praticaEntity);
		cronologia.setNotaInterna(pratica.getNotaInterna());
		cronologia.setUtente("OPERATORE: "+userInfo.getNome()+" "+userInfo.getCognome());
		cronologia.setScerevDPraticaStato(statoChiuso);
		cronologia.setDataOraInserimento(new Timestamp(System.currentTimeMillis()));
		cronologia.setDataCreazione(new Timestamp(System.currentTimeMillis()));
		cronologia.setDataModifica(new Timestamp(System.currentTimeMillis()));
		dataDao.insertCronologia(cronologia);
		
		return praticaEntity;
	}
	
	/**
	 * Ritorna la lista delle motivazioni ScerevDMotivazione
	 */
	public List<Motivazione> getAllMotivazioni(){
		List<Motivazione> responseList = new ArrayList<Motivazione>();
		for(ScerevDMotivazione motiv : dataDao.getAllMotivazioni()) {
			responseList.add(new Motivazione(motiv));
		}
		return responseList;
	}

	/**
	 * Check dei parametri in input del servizio accettaRichiesta
	 * @throws IntegritaException, ParametriObbligatoriException
	 * @throws ParametriObbligatoriException 
	 */
	public void checkAccettaParameters(AccettaRichiesta richiesta) throws IntegritaException, ParametriObbligatoriException {
		if(!Checker.isValorizzato(richiesta.getCodiceFiscaleAssistito()) || !Checker.isCodiceFiscale(richiesta.getCodiceFiscaleAssistito(), isTST())) throw new ParametriObbligatoriException(Util.composeMessage(dataDao.getMessaggioPerCodice(SharedConstants.ERROR_CODE_PARAMETRI_OBBLIGATORI).getValoreMsg()," Codice fiscale formalmente errato"));
		if(!Checker.isValorizzato(richiesta.getNumeroPratica())) throw new ParametriObbligatoriException(Util.composeMessage(dataDao.getMessaggioPerCodice(SharedConstants.ERROR_CODE_PARAMETRI_OBBLIGATORI).getValoreMsg()," Numero Pratica non presente"));	
		if(!Checker.isValorizzato(richiesta.getTipologia())) throw new ParametriObbligatoriException(Util.composeMessage(dataDao.getMessaggioPerCodice(SharedConstants.ERROR_CODE_PARAMETRI_OBBLIGATORI).getValoreMsg()," Tipologia non presente"));	

		if(!Checker.isValorizzato(richiesta.getDurataAssistenzaDa()) || !Checker.isDataItalian(richiesta.getDurataAssistenzaDa())) throw new ParametriObbligatoriException(Util.composeMessage(dataDao.getMessaggioPerCodice(SharedConstants.ERROR_CODE_INTEGRITA).getValoreMsg()," Data assistenza assente o formato errata (dd/MM/yyyy)"));
		if(Checker.isValorizzato(richiesta.getDurataAssistenzaA()) && !Checker.isDataItalian(richiesta.getDurataAssistenzaA())) throw new ParametriObbligatoriException(Util.composeMessage(dataDao.getMessaggioPerCodice(SharedConstants.ERROR_CODE_INTEGRITA).getValoreMsg()," Data assistenza assente o formato errata (dd/MM/yyyy)"));
	}

	/**
	 * Check dei parametri temporali in input del servizio accettaRichiesta
	 */
	public void checkAccettaRichiestaDtInit(AccettaRichiesta richiesta, Cittadino cittadino) throws IntegritaException {		
		Date assistenzaDa = Converter.getData(richiesta.getDurataAssistenzaDa());		
		Date nascita = cittadino.getDataNascita();
//		Date iniAsl = cittadino.getDataInizioAssistenza();
//		Date iniRes = cittadino.getDataInizioResidenza();
		Date now = Converter.getDataWithoutTime(new Date(System.currentTimeMillis()));
		
		ScerevDTipologiaProfilo profilo = getTipologiaByCodice(richiesta.getTipologia());
		
		//-	Deve essere successiva alla data di nascita dell’assistito (recuperata dalla sessione, sezione Dati Richiedente)
		// In caso di esito negativo si mostrerà a video il messaggio d’errore: ERR72 Date non consistenti: data di inizio assistenza anteriore alla data di nascita dell’assistito
		if(assistenzaDa.before(nascita)) throw new IntegritaException(dataDao.getMessaggioPerCodice(SharedConstants.ERROR_DATE_INCONSISTENTI).getValoreMsg());
		
		// Deve essere successiva alla data corrente (data di sistema)
		// In caso di esito negativo si mostrerà a video il messaggio d’errore: ERR73 Date non consistenti: la data di inizio assistenza deve essere successiva alla data di sistema
		//if(assistenzaDa.after(now)) throw new IntegritaException(dataDao.getMessaggioPerCodice(SharedConstants.ERROR_DATE_INCONSISTENTI_2).getValoreMsg());
		
		// In caso di cittadino presente in AURA per il quale si sta modificando un’assistenza, la data di inizio assistenza indicata dall’operatore deve essere successiva alla data di inizio 
		// della vecchia assistenza (per mezzo del Codice fiscale dell’assistito, ricevuto in input, si invoca il servizio AnagrafeFind di AURA, dal quale si recupera l’IdAURA dell’assistito e 
		// tramite questo si invoca il servizio AnagrafeSanitaria. Si deve recuperare il campo dataInizioASL nella sezione InfoSan – dati Sanitari. Se NON è popolato, si sta creando una nuova assistenza: 
		// caso specificato nel passo successivo) In caso di esito negativo si mostrerà a video il messaggio d’errore: ERR74 Date non consistenti: la data di inizio assistenza deve essere successiva alla data di inizio dell’assistenza precedente
//		if(iniAsl != null) {
//			if(assistenzaDa.before(iniAsl)) throw new IntegritaException(dataDao.getMessaggioPerCodice(SharedConstants.ERROR_DATE_START_INCONSISTENTE).getValoreMsg());			
//		} else if(iniRes != null) {
//			if(assistenzaDa.before(iniRes)) throw new IntegritaException(dataDao.getMessaggioPerCodice(SharedConstants.ERROR_DATE_START_INCONSISTENTE_2).getValoreMsg());
//		}
		
		if(Checker.isValorizzato(richiesta.getDurataAssistenzaA())) {
			Date assistenzaA = Converter.getData(richiesta.getDurataAssistenzaA());
			
			// Non deve essere antecedente alla data di inizio
			// In caso di esito negativo si mostrerà a video il messaggio d’errore: ERR76 Date non consistenti: data di fine assistenza anteriore alla data di inizio assistenza
			if(assistenzaA.before(assistenzaDa)) throw new IntegritaException(dataDao.getMessaggioPerCodice(SharedConstants.ERROR_DATE_INCONSISTENTI_3).getValoreMsg());
			
			// Deve essere successiva alla data corrente (data di sistema)
			// In caso di esito negativo si mostrerà a video il messaggio d’errore: ERR77 Date non consistenti: la data di fine assistenza deve essere successiva alla data di sistema
			if(!assistenzaA.after(now)) throw new IntegritaException(dataDao.getMessaggioPerCodice(SharedConstants.ERROR_DATE_INCONSISTENTI_4).getValoreMsg());
			
			// deve essere a scadenza (sarà popolata dall’operatore) se la tipologia profilo selezionata dall’operatore (ricevuta in input al servizio) prevede un'assistenza temporanea:
			// cioè se la tipologia profilo ha il corrispettivo campo DESC_ASS_TEMP popolato con SI
			if(!profilo.getDescrAssTemp().equalsIgnoreCase("SI")) throw new IntegritaException(dataDao.getMessaggioPerCodice(SharedConstants.ERROR_DATE_INCONSISTENTI_5).getValoreMsg());						
		}else {
			// Deve essere pertinente alla tipologia di profilo sanitario selezionata:
			// deve essere illimitata (NON deve essere compilata dall’operatore) se la tipologia profilo selezionata dall’operatore (ricevuta in input al servizio) 
			// prevede un'assistenza illimitata: cioè se la tipologia profilo ha il corrispettivo campo DESC_ASS_TEMP popolato con NO
			if(!profilo.getDescrAssTemp().equalsIgnoreCase("NO")) throw new IntegritaException(dataDao.getMessaggioPerCodice(SharedConstants.ERROR_DATE_INCONSISTENTI_5).getValoreMsg());
		}
	}

	/**
	 * Ritorna la lista delle tipologie profilo
	 * @return
	 */
	public List<ScerevDTipologiaProfilo> getTipologiaProfiloList() {
		return dataDao.getListaTipologieProfilo();
	}
		
	/**
	 * Ritorna la lista delle tipologie profilo internal DTO
	 * @return
	 */
	public List<TipologiaProfilo> getTipologiaProfiloListDTO(ScerevTPratica pratica) {
		List<TipologiaProfilo> listReturn = new ArrayList<TipologiaProfilo>();
		if( (pratica.getScerevDPraticaStato().getValorePraticaStato().equalsIgnoreCase(SharedConstants.STATO_COMPLETATA) || 
				pratica.getScerevDPraticaStato().getValorePraticaStato().equalsIgnoreCase(SharedConstants.STATO_CHIUSA)) && 
				pratica.getScerevDTipologiaProfilo() != null) {
			listReturn.add(new TipologiaProfilo(pratica.getScerevDTipologiaProfilo()));
		} else {
			List<ScerevDTipologiaProfilo> listDao = dataDao.getListaTipologieProfilo();
			for(ScerevDTipologiaProfilo tipologia : listDao) {
				listReturn.add(new TipologiaProfilo(tipologia));
			}
		}
		return listReturn;
	}
	
	/**
	 * Check dei parametri del servizio di aggiunta della nota interna
	 * @param nota
	 * @throws ParametriObbligatoriException 
	 */
	public void checkNotaInternaParameters(NotaInterna nota) throws ParametriObbligatoriException {
		if(nota.getNumeroPratica()==null) throw new ParametriObbligatoriException(Util.composeMessage(dataDao.getMessaggioPerCodice(SharedConstants.ERROR_CODE_PARAMETRI_OBBLIGATORI).getValoreMsg(), "Numero Pratica non presente"));	
		if(nota.getNotaInterna()==null) throw new ParametriObbligatoriException(Util.composeMessage(dataDao.getMessaggioPerCodice(SharedConstants.ERROR_CODE_PARAMETRI_OBBLIGATORI).getValoreMsg()," Nota non presente"));		
	}
	
	/**
	 * Check dei parametri del servizio di aggiunta della nota interna
	 * @param nota
	 * @throws ParametriObbligatoriException 
	 */
	public void checkChiudeRichiestaParameters(ChiudeRichiesta richiesta) throws ParametriObbligatoriException {
		if(richiesta.getNumeroPratica()==null) 
			throw new ParametriObbligatoriException(Util.composeMessage(dataDao.getMessaggioPerCodice(SharedConstants.ERROR_CODE_PARAMETRI_OBBLIGATORI).getValoreMsg(),"Numero Pratica non presente"));
		if(richiesta.isFlMedicoRevocato() == false) 
			throw new ParametriObbligatoriException(Util.composeMessage(dataDao.getMessaggioPerCodice(SharedConstants.ERROR_CODE_PARAMETRI_OBBLIGATORI).getValoreMsg(),"Parametro \"il medico è stato revocato\" non presente"));
	}

	/**
	 * Prendo la pratica dal db e la confronto con AURA	
	 * @param richiesta
	 * @param cittadinoAura
	 * @param cittadinoAuraExtra
	 * @param cittadinoScerev
	 * @throws Exception
	 */
	public void updateOnAura(AccettaRichiesta richiesta, DatiAnagrafici cittadinoAura, SoggettoAuraBodyNew cittadinoAuraExtra,
			InterrogaMefScerevResult cittadinoScerev, String shib, ScerevLAudit audit
			) throws Exception {
		ScerevTPratica pratica = getPratica(Integer.parseInt(richiesta.getNumeroPratica()));
		if(cittadinoAura!=null) {
			ScerevTLocalita residenza = pratica.getScerevTLocalita1(); 
			ScerevTLocalita domicilio = pratica.getScerevTLocalita2(); 
			String stringaResidenzaDB = residenza.getIndirizzo() + " " + residenza.getNumeroCivico() + " " + residenza.getComune();
			String stringaResidenzaAura = cittadinoAuraExtra.getInfoAnag().getResidenza() != null 
					? cittadinoAuraExtra.getInfoAnag().getResidenza().getIndirizzo() + " " + cittadinoAuraExtra.getInfoAnag().getResidenza().getNumCivico() + " " + cittadinoAuraExtra.getInfoAnag().getResidenza().getDescComune()
					: "";
			String stringaDomicilioDB = domicilio.getIndirizzo() + " " + domicilio.getNumeroCivico() + " " + domicilio.getComune();
			String stringaDomicilioAura = cittadinoAuraExtra.getInfoAnag().getDomicilio() != null
					? cittadinoAuraExtra.getInfoAnag().getDomicilio().getIndirizzo() + " " + cittadinoAuraExtra.getInfoAnag().getDomicilio().getNumCivico() + " " + cittadinoAuraExtra.getInfoAnag().getDomicilio().getDescComune()
					: "";
			
			// il sistema invoca il servizio “Aggiornamento Profilo Anagrafico” 
			if(!(stringaResidenzaDB.equalsIgnoreCase(stringaResidenzaAura) && stringaDomicilioDB.equalsIgnoreCase(stringaDomicilioAura))) {
				AggiornamentoProfiloAnagrafico in = costruisciAggiornamentoProfiloAnagraficoIn(cittadinoAura, cittadinoAuraExtra, pratica, shib);
				
				// 23/10/2020 l'aggiornamento del profilo anagrafico aggiorna anche il profilo sanitario
				ProfiloSanitario inProfSan = costruisciSetProfiloSanitario(richiesta, cittadinoAura, cittadinoAuraExtra, null, pratica, shib);
				in.getBody().setProfiloSanitarioBody(inProfSan.getBody());
				
				AggiornamentoProfiloAnagraficoRes profiloAgg = ServiziEsterniClient.getInstance().setAggiornamentoProfiloAnagrafico(in, audit);
				
				if(profiloAgg == null || !profiloAgg.getCodiceRitorno().equalsIgnoreCase("1")) {
					String err = profiloAgg.getFooter().getAggiornamentoProfiloAnagraficoResErrore().get(0).getCodice() + ": " + profiloAgg.getFooter().getAggiornamentoProfiloAnagraficoResErrore().get(0).getDescrizione();
					throw new Exception(err);
				}
				
				if(profiloAgg.getBody() != null && profiloAgg.getBody().getDataDecesso() != null) {
					MessaggioFacade messaggioFacade = (MessaggioFacade) SpringApplicationContextHelper.getBean(SharedConstants.MESSAGGIO_FACADE);
					Messaggio msg = messaggioFacade.getMessaggioPerCodice(SharedConstants.ERROR_CODE_AURA_DECEDUTO);
					String err = msg.getCodice() + ": " + msg.getMessaggiostr();
					throw new Exception(err);
				}
				if(profiloAgg.getBody() != null && profiloAgg.getBody().getCodiceFiscaleMEF() != null && !profiloAgg.getBody().getCodiceFiscaleMEF().equalsIgnoreCase(richiesta.getCodiceFiscaleAssistito())) {
					MessaggioFacade messaggioFacade = (MessaggioFacade) SpringApplicationContextHelper.getBean(SharedConstants.MESSAGGIO_FACADE);
					Messaggio msg = messaggioFacade.getMessaggioPerCodice(SharedConstants.ERROR_CODE_AURA_CF_MEF_DIVERSO);
					String err = msg.getCodice() + ": " + msg.getMessaggiostr();
					throw new Exception(err);
				}

			} else {
				ProfiloSanitario in = costruisciSetProfiloSanitario(richiesta, cittadinoAura, cittadinoAuraExtra, null, pratica, shib);
				ProfiloSanitarioRes out = ServiziEsterniClient.getInstance().setProfiloSanitario(in, audit);
				if (out.getCodiceRitorno() == null || !out.getCodiceRitorno().equals("1") )
					throw new AuraException(SharedConstants.ERROR_CODE_AURA_INSERTUPDATE_PROFILOANAGRAFICO);
			}
			
		}else if(cittadinoScerev!=null){
			InserimentoProfiloAnagrafico in = costruisciInserimentoProfiloAnagraficoIn(richiesta, cittadinoScerev, pratica, shib);
			InserimentoProfiloAnagraficoRes out = ServiziEsterniClient.getInstance().setInserimentoProfiloAnagrafico(in, audit);
			if (out == null || !out.getCodiceRitorno().equals("1") )
				throw new AuraException(SharedConstants.ERROR_CODE_AURA_INSERTUPDATE_PROFILOANAGRAFICO);
			else {
				if (out.getBody().getDataDecesso() != null)
					throw new DomainException(SharedConstants.ERROR_CODE_AURA_DECEDUTO);
				else if (!out.getBody().getCodiceFiscaleMEF().equalsIgnoreCase(richiesta.getCodiceFiscaleAssistito()))
					throw new DomainException(SharedConstants.ERROR_CODE_AURA_CF_MEF_DIVERSO);
				
				ProfiloSanitario in1 = costruisciSetProfiloSanitario(richiesta, null, null, out, pratica, shib);
				ProfiloSanitarioRes out1 = ServiziEsterniClient.getInstance().setProfiloSanitario(in1, audit);
				if (out1.getCodiceRitorno() == null || !out1.getCodiceRitorno().equals("1") )
					throw new AuraException(SharedConstants.ERROR_CODE_AURA_INSERTUPDATE_PROFILOANAGRAFICO);
			}
		} else 
			throw new Exception("Nessun cittadino è stato trovato");			
	}
	
	private ProfiloSanitario costruisciSetProfiloSanitario(AccettaRichiesta richiesta, DatiAnagrafici cittadinoAura, SoggettoAuraBodyNew cittadinoAuraExtra, 
			InserimentoProfiloAnagraficoRes ins, ScerevTPratica pratica, String shib) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		BigDecimal idAura = null;
		String cf = null;
		Date dataInizioASL = null;
		Date dataFineASL = null;
		
		ProfiloSanitario request = new ProfiloSanitario();
		request.setHeader(new ProfiloSanitarioHeader());
		request.getHeader().setSender("SCEREV");
		request.getHeader().setIdAsr(pratica.getScerevDAsl2().getCodiceRegione() + pratica.getScerevDAsl2().getCodiceAzienda());
		request.getHeader().setRequestDateTime(Converter.getData(new Date(), "yyyy-MM-dd HH:mm:ss"));
		request.getHeader().setCfUtilizzatoreServizio(shib);		
		
		if(ins == null) {
			idAura = cittadinoAura.getIdProfiloAnagrafico();
			cf = cittadinoAura.getCodiceFiscale();
			dataInizioASL = cittadinoAuraExtra.getInfoSan() != null && cittadinoAuraExtra.getInfoSan().getAslAssistenza() != null && 
					cittadinoAuraExtra.getInfoSan().getAslAssistenza().equalsIgnoreCase(request.getHeader().getIdAsr()) ? 
							null : Checker.isValorizzato(richiesta.getDurataAssistenzaDa()) ? Converter.getData(richiesta.getDurataAssistenzaDa()) : new Date(System.currentTimeMillis());

			dataFineASL = Checker.isValorizzato(richiesta.getDurataAssistenzaA()) ? Converter.getData(richiesta.getDurataAssistenzaA()) : Converter.getData(SharedConstants.DATA_ILLIMITATA);
			
		} else {
			idAura = new BigDecimal(ins.getBody().getIdAura());
			cf = ins.getBody().getCodiceFiscaleMEF();
			dataInizioASL = Checker.isValorizzato(richiesta.getDurataAssistenzaDa()) ? Converter.getData(richiesta.getDurataAssistenzaDa()) : new Date(System.currentTimeMillis());
			dataFineASL   = Checker.isValorizzato(richiesta.getDurataAssistenzaA())  ? Converter.getData(richiesta.getDurataAssistenzaA())  : Converter.getData(SharedConstants.DATA_ILLIMITATA);
		}
		
		request.setBody(new ProfiloSanitarioBody());
		request.getBody().setIdAuraAssistito(idAura.toString());
		request.getBody().setCfAuraAssistito(cf);
		
		request.getBody().setIdAuraMedico(pratica.getIdAuraMedico());
		request.getBody().setCfAuraMedico(pratica.getCodiceFiscaleMedico());
		request.getBody().setTipoMovimento("S");
		request.getBody().setRicongiungimentoFam(richiesta.isFlRicongFamigliare() ? 1L :  0L);
		
		request.getBody().setDatiIscrizioneSanitaria(new DatiIscrizioneSanitaria());
		request.getBody().getDatiIscrizioneSanitaria().setDataInizioASL(Converter.getXMLGregorianCalendar(dataInizioASL == null ? null : sdf.parse(Converter.getData(dataInizioASL))));
		request.getBody().getDatiIscrizioneSanitaria().setDataFineASL(Converter.getXMLGregorianCalendar(dataFineASL == null ? null : sdf.parse(Converter.getData(dataFineASL))));
		request.getBody().getDatiIscrizioneSanitaria().setDataDecorrenza(Converter.getXMLGregorianCalendar(sdf.parse(Converter.getData(new Date(System.currentTimeMillis())))));

		request.getBody().getDatiIscrizioneSanitaria().setCodTipoProfiloSanitario(richiesta.getTipologia());
		request.getBody().getDatiIscrizioneSanitaria().setSimulazione(0L);
		
		request.getBody().getDatiIscrizioneSanitaria().setDerogaPediatra(richiesta.isFlDerogaPediatra() ? 1L : 0L);
		request.getBody().getDatiIscrizioneSanitaria().setMMG(richiesta.isFlMedicoGenerico() ? 1L : 0L);
		request.getBody().getDatiIscrizioneSanitaria().setDerogaAmbitoTerritoriale(richiesta.isFlDerogaTerritoriale() ? 1L : 0L);
		request.getBody().getDatiIscrizioneSanitaria().setDerogaAssociazione(richiesta.isFlDerogaAssociazione() ? 1L : 0L);
		request.getBody().getDatiIscrizioneSanitaria().setDerogaMassimale(richiesta.isFlDerogaMassimale() ? 1L : 0L);
		request.getBody().getDatiIscrizioneSanitaria().setDerogaMassimaleTemporaneo(richiesta.isFlDerogaMassimaleTmp() ? 1L : 0L);
		request.getBody().getDatiIscrizioneSanitaria().setNote(getStringaNota(richiesta.getNotaInterna()));
		
		return request;
	}
	
	private String getStringaNota(String nota) {
		if(nota != null) {
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			String s = dateFormat.format(System.currentTimeMillis());
			s = s.concat(" ");
			s = s.concat(nota);
			return s;
		}
		return null;
	}
	
	private AggiornamentoProfiloAnagrafico costruisciAggiornamentoProfiloAnagraficoIn(DatiAnagrafici cittadinoAura,
			SoggettoAuraBodyNew cittadinoAuraExtra, ScerevTPratica pratica, String shib) {
		AggiornamentoProfiloAnagrafico request = new AggiornamentoProfiloAnagrafico();

		request.setHeader(new AggiornamentoProfiloAnagraficoHeader());
		request.getHeader().setSender("SCEREV");
		request.getHeader().setIdAsr(pratica.getScerevDAsl2().getCodiceRegione() + pratica.getScerevDAsl2().getCodiceAzienda());
		request.getHeader().setRequestDateTime(Converter.getData(new Date(), "yyyy-MM-dd HH:mm:ss"));
		request.getHeader().setCfUtilizzatoreServizio(shib);
		
		AggiornamentoProfiloAnagraficoBody body = new AggiornamentoProfiloAnagraficoBody();
		
		body.setDatiPrimari(new AggiornamentoProfiloAnagraficoDatiPrimari());
		body.getDatiPrimari().setIdAura(cittadinoAura.getIdProfiloAnagrafico().toString());
		body.getDatiPrimari().setCodiceFiscaleAura(cittadinoAura.getCodiceFiscale());
		body.getDatiPrimari().setNome(cittadinoAura.getNome());
		body.getDatiPrimari().setCognome(cittadinoAura.getCognome());
		body.getDatiPrimari().setSesso(cittadinoAura.getSesso());
		body.getDatiPrimari().setDataNascita(cittadinoAura.getDataNascita());
		body.getDatiPrimari().setCodiceFiscale(pratica.getCfAssistito());
		body.getDatiPrimari().setCodiceStatoNascita(cittadinoAura.getCodiceStatoNascita());
		body.getDatiPrimari().setCodiceComuneNascitaItaliano(cittadinoAura.getCodiceComuneNascita());
		body.getDatiPrimari().setDescrizioneComuneNascita(cittadinoAura.getComuneNascita());
		body.getDatiPrimari().setDescrizioneStatoNascita(cittadinoAura.getStatoNascita());
		body.getDatiPrimari().setCodiceCittadinanza(pratica.getScerevDCittadinanza().getCodiceCittadinanza());

		body.setDatiResidenza(new AggiornamentoProfiloAnagraficoResidenza());
		ScerevTLocalita residenza = pratica.getScerevTLocalita1();
		body.getDatiResidenza().setCodiceStatoResidenza(residenza.getScerevDNazione().getCodiceNazione());
		body.getDatiResidenza().setCodiceComuneResidenza(residenza.getCodiceIstatComune());
		body.getDatiResidenza().setDescrizioneComuneResidenza(residenza.getComune());
		body.getDatiResidenza().setIndirizzoResidenza(residenza.getIndirizzo());
		body.getDatiResidenza().setNumerocivicoResidenza(residenza.getNumeroCivico());
		body.getDatiResidenza().setCAPresidenza(residenza.getCap());
		ScerevDAsl aslRes = dataDao.getAslByDenominazione(pratica.getAslResidenza());
		if(aslRes != null) body.getDatiResidenza().setCodiceAslResidenza(aslRes.getCodiceRegione()+aslRes.getCodiceAzienda());
		else body.getDatiResidenza().setCodiceAslResidenza("000000"); //workaround temporaneo per bypassare controllo formale. Da sostutire con nuovo servizio
		body.getDatiResidenza().setTelefonoResidenza(pratica.getRecapitoTelefonicoAssistito());
		
		
		body.setAltreInfo(new AggiornamentoProfiloAnagraficoAltreInfo());
//		body.getAltreInfo().setStatoContatto("3"); //3 = Verificato
		// 24/10/2020
		body.getAltreInfo().setStatoContatto("2"); //2 = Da verificare
		body.getAltreInfo().setDataInizioResidenza(Converter.getXMLGregorianCalendar(pratica.getDataCreazione()));

		body.setDatiDomicilio(new AggiornamentoProfiloAnagraficoDomicilio());
		ScerevTLocalita domicilio = pratica.getScerevTLocalita2();
		body.getDatiDomicilio().setCodiceStatoDomicilio(domicilio.getScerevDNazione().getCodiceNazione());
		body.getDatiDomicilio().setCodiceComuneDomicilio(domicilio.getCodiceIstatComune());
		body.getDatiDomicilio().setDescrizioneComuneDomicilio(domicilio.getComune());
		body.getDatiDomicilio().setIndirizzoDomicilio(domicilio.getIndirizzo());
		body.getDatiDomicilio().setNumerocivicoDomicilio(domicilio.getNumeroCivico());
		body.getDatiDomicilio().setCAPDomicilio(domicilio.getCap());
		body.getDatiDomicilio().setCodiceAslDomicilio(pratica.getScerevDAsl1().getCodiceRegione()+pratica.getScerevDAsl1().getCodiceAzienda());
		body.getDatiDomicilio().setTelefonoDomicilio(
				cittadinoAuraExtra != null && cittadinoAuraExtra.getInfoAnag() != null && cittadinoAuraExtra.getInfoAnag().getDomicilio() != null ? 
						cittadinoAuraExtra.getInfoAnag().getDomicilio().getTelefono() : null);
		
		request.setBody(body);
		
		return request;
	}
	
	private InserimentoProfiloAnagrafico costruisciInserimentoProfiloAnagraficoIn(AccettaRichiesta richiesta, InterrogaMefScerevResult cittadinoScerev,
			ScerevTPratica pratica, String shib) {
		InserimentoProfiloAnagrafico request = new InserimentoProfiloAnagrafico();

		request.setHeader(new InserimentoProfiloAnagraficoHeader());
		request.getHeader().setSender("SCEREV");
		request.getHeader().setIdAsr(pratica.getScerevDAsl2().getCodiceRegione()+pratica.getScerevDAsl2().getCodiceAzienda());
		request.getHeader().setRequestDateTime(Converter.getData(new Date(), "yyyy-MM-dd HH:mm:ss"));
		request.getHeader().setCfUtilizzatoreServizio(shib);
		
		InserimentoProfiloAnagraficoBody body = new InserimentoProfiloAnagraficoBody();
		
		body.setDatiPrimari(new InserimentoProfiloAnagraficoDatiPrimari());
		body.getDatiPrimari().setNome(cittadinoScerev.getNome());
		body.getDatiPrimari().setCognome(cittadinoScerev.getCognome());
		body.getDatiPrimari().setSesso(cittadinoScerev.getSesso());
		body.getDatiPrimari().setDataNascita(cittadinoScerev.getDatanascita());
		body.getDatiPrimari().setDataDecesso(cittadinoScerev.getDatadecesso() == null ? null : cittadinoScerev.getDatadecesso());
		body.getDatiPrimari().setCodiceFiscale(richiesta.getCodiceFiscaleAssistito());
		body.getDatiPrimari().setCodiceStatoNascita(cittadinoScerev.getIdStatonascita());
		
		// 22/12/2020
		if(cittadinoScerev.getIdStatonascita().equalsIgnoreCase(SharedConstants.ID_STATO_ITALIA_MEF)) {
			body.getDatiPrimari().setCodiceComuneNascitaItaliano(cittadinoScerev.getIdComunenascita());
			body.getDatiPrimari().setDescrizioneComuneNascita(cittadinoScerev.getComunenasc());
			body.getDatiPrimari().setCodiceProvinciaNascita(cittadinoScerev.getProvincianasc());
		}

		body.getDatiPrimari().setDescrizioneStatoNascita(cittadinoScerev.getNazionenasc());
		body.getDatiPrimari().setCodiceCittadinanza(pratica.getScerevDCittadinanza().getCodiceCittadinanza());

		body.setDatiResidenza(new InserimentoProfiloAnagraficoResidenza());
		ScerevTLocalita residenza = pratica.getScerevTLocalita1();
		body.getDatiResidenza().setCodiceStatoResidenza(residenza.getScerevDNazione().getCodiceNazione());
		body.getDatiResidenza().setCodiceComuneResidenza(residenza.getCodiceIstatComune());
		body.getDatiResidenza().setDescrizioneComuneResidenza(residenza.getComune());
		body.getDatiResidenza().setIndirizzoResidenza(residenza.getIndirizzo());
		body.getDatiResidenza().setNumerocivicoResidenza(residenza.getNumeroCivico());
		body.getDatiResidenza().setCAPresidenza(residenza.getCap());
		ScerevDAsl aslRes = dataDao.getAslByDenominazione(pratica.getAslResidenza());
		if(aslRes != null) body.getDatiResidenza().setCodiceAslResidenza(aslRes.getCodiceRegione()+aslRes.getCodiceAzienda());
		else body.getDatiResidenza().setCodiceAslResidenza("000000"); //workaround temporaneo per bypassare controllo formale. Da sostutire con nuovo servizio
		body.getDatiResidenza().setTelefonoResidenza(pratica.getRecapitoTelefonicoAssistito());
		
		body.setAltreInfo(new InserimentoProfiloAnagraficoAltreInfo());
//		body.getAltreInfo().setStatoContatto("3"); //3 = Verificato
		// 24/10/2020
		body.getAltreInfo().setStatoContatto("2"); //2 = Da verificare
		body.getAltreInfo().setDataInizioResidenza(Converter.getXMLGregorianCalendar(pratica.getDataCreazione()));

		body.setDatiDomicilio(new InserimentoProfiloAnagraficoDomicilio());
		ScerevTLocalita domicilio = pratica.getScerevTLocalita2();
		body.getDatiDomicilio().setCodiceStatoDomicilio("100");
		body.getDatiDomicilio().setCodiceComuneDomicilio(domicilio.getCodiceIstatComune());
		body.getDatiDomicilio().setDescrizioneComuneDomicilio(domicilio.getComune());
		body.getDatiDomicilio().setIndirizzoDomicilio(domicilio.getIndirizzo());
		body.getDatiDomicilio().setNumerocivicoDomicilio(domicilio.getNumeroCivico());
		body.getDatiDomicilio().setCAPDomicilio(domicilio.getCap());
		body.getDatiDomicilio().setCodiceAslDomicilio(pratica.getScerevDAsl1().getCodiceRegione()+pratica.getScerevDAsl1().getCodiceAzienda());

		request.setBody(body);
		return request;
	}

	/**
	 * Aggiunge un record nella ScerevTCronologia a fronte di un inserimento della nota interna
	 * @param nota
	 * @param userInfo
	 */
	public void aggiungiCronologiaNotaInterna(NotaInterna nota, UserInfo userInfo) {
		ScerevTPratica pratica = getPratica(Integer.parseInt(nota.getNumeroPratica()));

		ScerevTCronologia cronologia = new ScerevTCronologia();
		cronologia.setScerevTPratica(pratica);
		cronologia.setNotaRichiedente(null);
		cronologia.setNotaInterna(nota.getNotaInterna());
		cronologia.setUtente("OPERATORE: " + userInfo.getNome() + " " + userInfo.getCognome());
		cronologia.setScerevDPraticaStato(pratica.getScerevDPraticaStato());
		cronologia.setDataOraInserimento(new Timestamp(System.currentTimeMillis()));
		cronologia.setDataCreazione(new Timestamp(System.currentTimeMillis()));
		cronologia.setDataModifica(new Timestamp(System.currentTimeMillis()));
		dataDao.insertCronologia(cronologia);
	}
	
	/**
	 * Effettua la presa in carico logica su db	
	 * @param userInfo
	 * @param numPratica
	 * @throws Exception
	 */
	public ScerevTPratica prendiInCarico(UserInfo userInfo, String numPratica) throws Exception {
		ScerevTPratica pratica = getPratica(Integer.parseInt(numPratica));
		String valoreStato = pratica.getScerevDPraticaStato().getValorePraticaStato();
		if ( valoreStato.equalsIgnoreCase(SharedConstants.STATO_IN_LAVORAZIONE) || valoreStato.equalsIgnoreCase(SharedConstants.STATO_COMPLETATA)
				|| valoreStato.equalsIgnoreCase(SharedConstants.STATO_INVIATA)) {
			
			if (pratica.getCfOperatore() == null)
				pratica.setScerevDPraticaStato(getStatoByDescr(SharedConstants.STATO_IN_LAVORAZIONE));

			pratica.setCfOperatore(userInfo.getCodFisc());
			pratica.setCognomeOperatore(userInfo.getCognome());
			pratica.setNomeOperatore(userInfo.getNome());
			pratica.setDataModifica(new Timestamp(System.currentTimeMillis()));
			dataDao.updatePratica(pratica);

			ScerevTCronologia cronologia = new ScerevTCronologia();			
			cronologia.setScerevTPratica(pratica);
			cronologia.setUtente("OPERATORE: " + userInfo.getNome() + " " + userInfo.getCognome());
			cronologia.setScerevDPraticaStato(pratica.getScerevDPraticaStato());
			cronologia.setDataOraInserimento(new Timestamp(System.currentTimeMillis()));
			cronologia.setDataCreazione(new Timestamp(System.currentTimeMillis()));
			cronologia.setDataModifica(new Timestamp(System.currentTimeMillis()));
			dataDao.insertCronologia(cronologia);
			
			return pratica;
		} else
			throw new Exception("Controllare lo stato della pratica");
	}
	
	/**
	 * Ritorna un oggetto ScerevDPraticaStato
	 * @param value
	 * @return
	 */
	private ScerevDPraticaStato getStatoByDescr(String value) {
		List<ScerevDPraticaStato> listaStati = dataDao.getStatiPratica();		
		ScerevDPraticaStato statoreturn = null;		
		for(ScerevDPraticaStato stato : listaStati) {
			if(stato.getValorePraticaStato().equalsIgnoreCase(value)) statoreturn = stato;
		}
		return statoreturn;
	}

	public String getAslByDenominazione(String azienda) {		
		return dataDao.getAslByDenominazione(azienda).getCodiceAzienda();
	}

	@Override
	public ScerevTPratica accettaRichiesta(AccettaRichiesta richiesta, UserInfo userInfo) {
		//
		ScerevTPratica pratica = getPratica(Integer.parseInt(richiesta.getNumeroPratica()));
		String stato = richiesta.isDaChiudere() ? SharedConstants.STATO_CHIUSA : SharedConstants.STATO_COMPLETATA;
		pratica.setScerevDPraticaStato( getStatoByDescr(stato));

		pratica.setCfOperatore(userInfo.getCodFisc());
		pratica.setCognomeOperatore(userInfo.getCognome());
		pratica.setNomeOperatore(userInfo.getNome());
		
		pratica.setDataInizioAssistenzaImposta(new Timestamp(Converter.getData(richiesta.getDurataAssistenzaDa()).getTime()));
		pratica.setDataFineAssistenzaImposta(Checker.isValorizzato(richiesta.getDurataAssistenzaA()) ? 
				new Timestamp(Converter.getData(richiesta.getDurataAssistenzaA()).getTime()) : 
					new Timestamp(Converter.getData(SharedConstants.DATA_ILLIMITATA).getTime()));
		
		pratica.setScerevDTipologiaProfilo(getTipologiaByCodice(richiesta.getTipologia()));
		
		pratica.setDerogaPediatra(richiesta.isFlDerogaPediatra());
		pratica.setMedicoGenerico(richiesta.isFlMedicoGenerico());
		pratica.setRicongiungimentoFamiliare(richiesta.isFlRicongFamigliare());
		pratica.setDerogaTerritoriale(richiesta.isFlDerogaTerritoriale());
		pratica.setDerogaAssociazione(richiesta.isFlDerogaAssociazione());
		pratica.setDerogaMassimale(richiesta.isFlDerogaMassimale());
		pratica.setDerogaMassimaleTemp(richiesta.isFlDerogaMassimaleTmp());
		pratica.setMedicoRevocato(richiesta.isFlMedicoRevocato());
		
		pratica.setDataChiusura(stato.equalsIgnoreCase(SharedConstants.STATO_CHIUSA) ? new Timestamp(System.currentTimeMillis()) : null);
		pratica.setDataModifica(new Timestamp(System.currentTimeMillis()));
		dataDao.updatePratica(pratica);

		ScerevTPratica rev = dataDao.getPraticaForRevocaNuovaRichiesta(richiesta.getCodiceFiscaleAssistito(), pratica.getNumeroPratica());
		if(rev != null) {
			rev.setDataRevocaMedico(new Timestamp(System.currentTimeMillis()));
			rev.setDataRevocaAssistenza(new Timestamp(System.currentTimeMillis()));
			rev.setDataModifica(new Timestamp(System.currentTimeMillis()));
			dataDao.updatePratica(rev);
		}
		
		ScerevTCronologia cronologia = new ScerevTCronologia();
		cronologia.setScerevTPratica(pratica);
		cronologia.setNotaInterna(richiesta.getNotaInterna());
		cronologia.setUtente("OPERATORE: " + userInfo.getNome() + " " + userInfo.getCognome());
		cronologia.setScerevDPraticaStato(pratica.getScerevDPraticaStato());
		cronologia.setDataOraInserimento(new Timestamp(System.currentTimeMillis()));
		cronologia.setDataCreazione(new Timestamp(System.currentTimeMillis()));
		cronologia.setDataModifica(new Timestamp(System.currentTimeMillis()));
		dataDao.insertCronologia(cronologia);

		return pratica;
	}

	private ScerevDTipologiaProfilo getTipologiaByCodice(String tipologia) {
		List<ScerevDTipologiaProfilo> tpl = getTipologiaProfiloList();
		ScerevDTipologiaProfilo tipo = null;
		for (ScerevDTipologiaProfilo t : tpl) {
			if(t.getCodiceTipologiaProfilo().equalsIgnoreCase(tipologia)) {
				tipo = t;
				break;
			}
		}
		return tipo;
	}

	public List<String> getAslOperatore(String codFisc) {
		List<String> listAsl = new ArrayList<String>();
		List<ScerevTAslOperatore> aslOp = dataDao.getAslOperatore(codFisc);
		for(ScerevTAslOperatore op : aslOp) {
			listAsl.add(op.getScerevDAsl().getCodiceAzienda());
		}
		return listAsl;
	}

	public File getExportRicerca(List<RicercaOutput> ricerca) {
		XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Export ricerca");

        String[] head = {"Numero richiesta", "Assistito", 
        				 "ASL di residenza", "Medico", 
        				 "Distretto medico", "Ambito medico", 
        				 "Stato", "Data richiesta",	"Operatore" };
        
        int rowCount = 0;
        Row headRow = sheet.createRow(rowCount++);
        int headColumnCount = 0;
        for(String s: head) {
        	Cell cell = headRow.createCell(headColumnCount++);
        	cell.setCellValue(s);        
        }
        
        for (RicercaOutput r : ricerca) {
            Row row = sheet.createRow(rowCount++);
             
            int columnCount = 0;
            Cell cell = row.createCell(columnCount++);
            cell.setCellValue(r.getNumPratica());
            cell = row.createCell(columnCount++);
            cell.setCellValue(r.getAssistito() + " " + r.getCf_Assistito());
            cell = row.createCell(columnCount++);
            cell.setCellValue(r.getAsl());
            cell = row.createCell(columnCount++);
            cell.setCellValue(r.getMed_ped());
            cell = row.createCell(columnCount++);
            cell.setCellValue(r.getDistretto());
            cell = row.createCell(columnCount++);
            cell.setCellValue(r.getAmbito());
            cell = row.createCell(columnCount++);
            cell.setCellValue(r.getStato_pratica());
            cell = row.createCell(columnCount++);
            cell.setCellValue(r.getData_richiesta());
            cell = row.createCell(columnCount++);
            cell.setCellValue(r.getOperatore());
        }
        
        File xlsx = null;
        
        try {
			xlsx = File.createTempFile("export_", ".xlsx");
			FileOutputStream outputStream = new FileOutputStream(xlsx);
            workbook.write(outputStream);
        } catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        return xlsx;
	}

}
