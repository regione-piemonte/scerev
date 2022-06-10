/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevbe.business.facade.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import it.csi.scerev.scerevbe.business.facade.interfaces.PraticaFacadeIf;
import it.csi.scerev.scerevbe.business.serviziesterni.ServiziEsterniClient;
import it.csi.scerev.scerevbe.dto.ModelAllegati;
import it.csi.scerev.scerevbe.dto.ModelAllegatoTipo;
import it.csi.scerev.scerevbe.dto.ModelCambioMedicoRichiesta;
import it.csi.scerev.scerevbe.dto.ModelCambioMedicoRichiestaCustom;
import it.csi.scerev.scerevbe.dto.ModelCambioMedicoRichiestaDatiAggiuntivi;
import it.csi.scerev.scerevbe.dto.ModelCambioMedicoRichiestaDatiAggiuntivi.LavoroTipologiaEnum;
import it.csi.scerev.scerevbe.dto.ModelCittadinoInfoCustom;
import it.csi.scerev.scerevbe.dto.ModelCittadinoRecapiti;
import it.csi.scerev.scerevbe.dto.ModelCittadinoSistemaEsterno;
import it.csi.scerev.scerevbe.dto.ModelCittadinoSistemaEsternoAURA;
import it.csi.scerev.scerevbe.dto.ModelCittadinoSistemaEsternoTS;
import it.csi.scerev.scerevbe.dto.ModelControlloAllegati;
import it.csi.scerev.scerevbe.dto.ModelDocumento;
import it.csi.scerev.scerevbe.dto.Payload1;
import it.csi.scerev.scerevbe.dto.Payload2;
import it.csi.scerev.scerevbe.dto.Payload4;
import it.csi.scerev.scerevbe.dto.SyncType;
import it.csi.scerev.scerevbe.enums.DocumentoTipoEnum;
import it.csi.scerev.scerevbe.enums.StatoPraticaEnum;
import it.csi.scerev.scerevbe.enums.TipologiaPraticaEnum;
import it.csi.scerev.scerevbe.util.Constants;
import it.csi.scerev.scerevbe.util.FirmaDigitaleUtility;
import it.csi.scerev.scerevjpa.business.dao.interfaces.DataDaoIf;
import it.csi.scerev.scerevjpa.business.entity.ScerevDCittadinanza;
import it.csi.scerev.scerevjpa.business.entity.ScerevDDocumentoTipo;
import it.csi.scerev.scerevjpa.business.entity.ScerevDMotivoSoggiorno;
import it.csi.scerev.scerevjpa.business.entity.ScerevDMsg;
import it.csi.scerev.scerevjpa.business.entity.ScerevDNazione;
import it.csi.scerev.scerevjpa.business.entity.ScerevDPraticaStato;
import it.csi.scerev.scerevjpa.business.entity.ScerevLAudit;
import it.csi.scerev.scerevjpa.business.entity.ScerevRMotivoSoggiornoDocumentoTipo;
import it.csi.scerev.scerevjpa.business.entity.ScerevRPraticaPraticaTipo;
import it.csi.scerev.scerevjpa.business.entity.ScerevTCronologia;
import it.csi.scerev.scerevjpa.business.entity.ScerevTDocumento;
import it.csi.scerev.scerevjpa.business.entity.ScerevTFile;
import it.csi.scerev.scerevjpa.business.entity.ScerevTInfoSupplementari;
import it.csi.scerev.scerevjpa.business.entity.ScerevTLocalita;
import it.csi.scerev.scerevjpa.business.entity.ScerevTPratica;
import it.csi.scerev.scerevserviziesterni.aura.find.DatiAnagrafici;
import it.csi.scerev.scerevserviziesterni.aura.get.SoggettoAuraBodyNew;
import it.csi.scerev.scerevserviziesterni.aura.getasldistrettoambito.ASLDistrettoAmbitoResponse;
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
import it.csi.scerev.scerevutil.business.exception.AllegatoNonFirmatoException;
import it.csi.scerev.scerevutil.business.exception.AuraException;
import it.csi.scerev.scerevutil.business.exception.AuraNotFoundException;
import it.csi.scerev.scerevutil.business.exception.DomainException;
import it.csi.scerev.scerevutil.business.exception.SistemaTSNotFoundException;

public class PraticaFacade implements PraticaFacadeIf {
	private DataDaoIf dataDao;

	public DataDaoIf getDataDao() {
		return dataDao;
	}

	public void setDataDao(DataDaoIf dataDao) {
		this.dataDao = dataDao;
	}

	public ModelCambioMedicoRichiesta elaboraRichiesta(Payload1 input, ScerevLAudit audit, String citId, String shib)
			throws Exception {
		int tipoRichiesta = -1; // 0=bozza, 1=automatica, 2=operatore di backOffice

		verificaFirmaAllegati(input.getAllegati());

		ModelCittadinoSistemaEsterno da = getCittadinoSistemaEsterno(audit, citId);

		if (input.isBozza().booleanValue()) {
			tipoRichiesta = 0;
		} else if(input.getRinnovoAssistenza() != null && input.getRinnovoAssistenza()) {
			tipoRichiesta = 2;
		} else {

			//1. controllo presenza delega
			boolean chk1Automatico = (input.getDelegato() == null) || ( (input.getDelegato() != null) && 
					!Checker.isMaggiorenne(da.getDatanascita()) );
			
			//4. controllo tipologia assistenza
			boolean chk4Automatico = isBenefViventeAndAssistAttivaInPiemonte(da, input, audit);
			
			//6. Controllo informazioni supplementari e allegati
			boolean chk6Automatico = !isAllegatiValorizzato(input.getAllegati()) && !isDatiAggiuntiviValorizzato(input.getDatiAggiuntivi());

			boolean chk4BackOffice = isBenefViventeAndAssistAttivaFuoriPiemonte(da, input);

			boolean chk6BackOffice = !chk6Automatico;

			if (chk1Automatico && chk4Automatico && chk6Automatico)
				tipoRichiesta = 1;
			else if (chk4BackOffice || chk6BackOffice)
				tipoRichiesta = 2;
		}

		if (tipoRichiesta == -1)
			throw new DomainException(Constants.ERROR_PRATICA_NON_GESTITA);

		if (tipoRichiesta == 1) { //Gestione Automatica della richiesta
			//Il sistema dovra' aggiornare AURA solo nel caso di richiesta nuovo medico gestita AUTOMATICAMENTE da SCEREV.
			gestisciPraticaAutomatica(input, audit, citId, da);
		}
		ScerevTPratica pratica = aggiornamentoPraticaRichiestaNuovoMedico(input, citId, da, tipoRichiesta, shib, audit);
		ModelCambioMedicoRichiestaCustom res = new ModelCambioMedicoRichiestaCustom(pratica);
		res.setRecapiti(input.getRecapiti());
		return res;
	}

	private ModelCittadinoSistemaEsterno getCittadinoSistemaEsterno(ScerevLAudit audit, String citId)
			throws IllegalAccessException, Exception, DomainException, AuraException {
		ModelCittadinoSistemaEsterno da = null;
		
		Map<DatiAnagrafici, SoggettoAuraBodyNew> auraMap;
		try {
			List<DatiAnagrafici> datiCittadini = ServiziEsterniClient.getInstance().findCittadino(citId, null, null, null, audit);
			if (datiCittadini != null) {
				DatiAnagrafici datiCittadinoAura = null;
				SoggettoAuraBodyNew sog = null;
				auraMap = ServiziEsterniClient.getInstance().anagrafeSanitariaAttiva(datiCittadini, audit);
				if(auraMap != null) {
					datiCittadinoAura = auraMap.keySet().iterator().next();
					sog = auraMap.get(datiCittadinoAura);
					da = new ModelCittadinoSistemaEsternoAURA(datiCittadinoAura, sog);
				} else {
					da =  new ModelCittadinoSistemaEsternoTS(ServiziEsterniClient.getInstance().findCittadinoSuTS(citId, audit));
				}
			} else {
				da =  new ModelCittadinoSistemaEsternoTS(ServiziEsterniClient.getInstance().findCittadinoSuTS(citId, audit));
			}
		} catch (AuraNotFoundException e) {
			try {
				da =  new ModelCittadinoSistemaEsternoTS(ServiziEsterniClient.getInstance().findCittadinoSuTS(citId, audit));
			} catch (SistemaTSNotFoundException e1) {
				throw new DomainException(Constants.ERROR_AURA_TS_NF);
			}
		} catch (AuraException e) {
			throw new AuraException(SharedConstants.ERROR_CODE_AURA_NOT_FOUND);
		}
		return da;
	}

	private void gestisciPraticaAutomatica(Payload1 input, ScerevLAudit audit, String citId,
			ModelCittadinoSistemaEsterno da) throws Exception, AuraException, DomainException {
		BigDecimal idAura = null;
		boolean isFromMEF = false;
		if (da instanceof ModelCittadinoSistemaEsternoAURA) {
			if (
					(input.getDomicilio() != null && (input.getDomicilio().getCap() != null || input.getDomicilio().getCivico() != null || input.getDomicilio().getComune() != null || input.getDomicilio().getIndirizzo() != null)) ||
					(input.getResidenza() != null && (input.getResidenza().getCap() != null || input.getResidenza().getCivico() != null || input.getResidenza().getComune() != null || input.getResidenza().getIndirizzo() != null || input.getResidenza().getRegione() != null))
					) { //il cittadino ha cambiato residenza, aggiorno su AURA
				idAura = ((ModelCittadinoSistemaEsternoAURA) da).getIdAura();
				AggiornamentoProfiloAnagrafico in = costruisciAggiornamentoProfiloAnagraficoIn((ModelCittadinoSistemaEsternoAURA)da, input, citId, audit);
				
				// 23/10/2020 l'aggiornamento del profilo anagrafico aggiorna anche il profilo sanitario 
				ProfiloSanitario inProfSan = costruisciAggiornamentoProfiloSanitario(da, input, citId, idAura, isFromMEF);
				in.getBody().setProfiloSanitarioBody(inProfSan.getBody());
				
				AggiornamentoProfiloAnagraficoRes out = ServiziEsterniClient.getInstance().setAggiornamentoProfiloAnagrafico(in, audit);
				if (out == null || !out.getCodiceRitorno().equals("1") )
					throw new AuraException(out.getFooter().getAggiornamentoProfiloAnagraficoResErrore().get(0).getCodice() + ": " + out.getFooter().getAggiornamentoProfiloAnagraficoResErrore().get(0).getDescrizione());

				else {
					if (out.getBody() != null && out.getBody().getDataDecesso() != null)
						throw new DomainException(Constants.ERROR_AURA_CITTADINO_DECEDUTO);
					else if (out.getBody() != null && out.getBody().getCodiceFiscaleMEF()!=null && !out.getBody().getCodiceFiscaleMEF().equalsIgnoreCase(citId))
						throw new DomainException(Constants.ERROR_AURA_DISALLINEAMENTO_CF_MEF);
				}

			} else {
				idAura = ((ModelCittadinoSistemaEsternoAURA) da).getIdAura();
				ProfiloSanitarioRes out = ServiziEsterniClient.getInstance().setProfiloSanitario(costruisciAggiornamentoProfiloSanitario(da, input, citId, idAura, isFromMEF), audit);
				if (out.getCodiceRitorno() == null || !out.getCodiceRitorno().equals("1") )
					throw new AuraException(SharedConstants.ERROR_CODE_AURA_INSERTUPDATE_PROFILOANAGRAFICO);
			}
		} else {
			InserimentoProfiloAnagrafico in = costruisciInserimentoProfiloAnagraficoIn((ModelCittadinoSistemaEsternoTS)da, input, citId, audit);
			InserimentoProfiloAnagraficoRes out = ServiziEsterniClient.getInstance().setInserimentoProfiloAnagrafico(in, audit); //inserimento profilo anagrafico in AURA

			if (out == null || !out.getCodiceRitorno().equals("1") )
				throw new AuraException(SharedConstants.ERROR_CODE_AURA_INSERTUPDATE_PROFILOANAGRAFICO);
			else {
				if (out.getBody().getDataDecesso() != null)
					throw new DomainException(Constants.ERROR_AURA_CITTADINO_DECEDUTO);
				else if (!out.getBody().getCodiceFiscaleMEF().equalsIgnoreCase(citId))
					throw new DomainException(Constants.ERROR_AURA_DISALLINEAMENTO_CF_MEF);
			}
			idAura = new BigDecimal(out.getBody().getIdAura());
			isFromMEF = true;

			ProfiloSanitarioRes out1 = ServiziEsterniClient.getInstance().setProfiloSanitario(costruisciAggiornamentoProfiloSanitario(da, input, citId, idAura, isFromMEF), audit);
			if (out1.getCodiceRitorno() == null || !out1.getCodiceRitorno().equals("1") )
				throw new AuraException(SharedConstants.ERROR_CODE_AURA_INSERTUPDATE_PROFILOANAGRAFICO);
		}
	}

	/*
	 * Verifica se almeno un campo di datiAggiuntivi sia valorizzato
	 */
	private boolean isDatiAggiuntiviValorizzato(ModelCambioMedicoRichiestaDatiAggiuntivi datiAggiuntivi) {
		return datiAggiuntivi != null &&
				(datiAggiuntivi.getAssitenzaTemporaneaDataFineValidita() != null ||
				datiAggiuntivi.getLavoroAzienda() != null ||
				datiAggiuntivi.getLavoroAziendaCivico() != null ||
				datiAggiuntivi.getLavoroAziendaComune() != null ||
				datiAggiuntivi.getLavoroAziendaIndirizzo() != null ||
				datiAggiuntivi.getLavoroAziendaProvincia() != null ||
				datiAggiuntivi.getLavoroAziendaTelefono() != null ||
				datiAggiuntivi.getLavoroDataFineValiditaContratto() != null ||
				datiAggiuntivi.getLavoroNumeroIscrizione() != null ||
				datiAggiuntivi.getLavoroPartitaIva() != null ||
				datiAggiuntivi.getLavoroProfessione() != null ||
				datiAggiuntivi.getLavoroTipologia() != null ||
				datiAggiuntivi.getMotivazioneDomicilio() != null ||
				datiAggiuntivi.getMotivazioneSoggiorno() != null ||
				datiAggiuntivi.getNote() != null ||
				datiAggiuntivi.getRicongiungimentoCodiceFiscale() != null ||
				datiAggiuntivi.getRicongiungimentoCognome() != null ||
				datiAggiuntivi.getRicongiungimentoNome() != null ||
				datiAggiuntivi.getRicongiungimentoParentela() != null ||
				datiAggiuntivi.getStudioDataTermineIscrizione() != null ||
				datiAggiuntivi.getStudioIstitutoCivico() != null ||
				datiAggiuntivi.getStudioIstitutoComune() != null ||
				datiAggiuntivi.getStudioIstitutoIndirizzo() != null ||
				datiAggiuntivi.getStudioIstitutoProvincia() != null ||
				datiAggiuntivi.getStudioNomeIstituto() != null ||
				datiAggiuntivi.getMotivazioneDomanda() != null);
				
	}

	/*
	 * Controlla che almeno un campo di Allegati sia valorizzato
	 */
	private boolean isAllegatiValorizzato(ModelAllegati allegati) {
		return allegati != null &&
				!allegati.isEmpty();
	}

	private ProfiloSanitario costruisciAggiornamentoProfiloSanitario(ModelCittadinoSistemaEsterno da, Payload1 in, String citId, BigDecimal idAura, boolean isFromMEF) throws Exception {
		
		String idAsr = "010"+in.getMedico().getAsl().getId();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		ProfiloSanitario request = new ProfiloSanitario();
		request.setHeader(new ProfiloSanitarioHeader());
		request.getHeader().setIdAsr(idAsr);
		request.getHeader().setRequestDateTime(Converter.getData(new Date(), "yyyy-MM-dd HH:mm:ss"));
		request.getHeader().setSender(Constants.AURA_COMPONENT_NAME);
		request.getHeader().setCfUtilizzatoreServizio(citId);

		request.setBody(new ProfiloSanitarioBody());
		request.getBody().setIdAuraAssistito(idAura.toString());
		request.getBody().setCfAuraAssistito(da.getCodicefiscale());

		request.getBody().setIdAuraMedico(in.getMedico().getId());
		request.getBody().setCfAuraMedico(in.getMedico().getCodiceFiscale());
		request.getBody().setTipoMovimento("S");
		request.getBody().setRicongiungimentoFam(0L);

		// 11/01/2021 aggiunto controllo assistenza attiva
		Date dataInizioASL = !isFromMEF && ((ModelCittadinoSistemaEsternoAURA)da).getAslAssistenza() != null && 
			((ModelCittadinoSistemaEsternoAURA)da).getAslAssistenza().equals(idAsr) && !in.getControlloCombinazione().isVariazioneAsl() &&
			((ModelCittadinoSistemaEsternoAURA)da).getDataFineASL() != null && !((ModelCittadinoSistemaEsternoAURA)da).getDataFineASL().before(new Date()) ? 
			((ModelCittadinoSistemaEsternoAURA)da).getDataInizioAsl() : new Date(System.currentTimeMillis());
		
		// 14/01/2021 controllo 3 asl e cittadinanza
		String aslResidenza = in.getControlloCombinazione().getAslResidenza().getId() == null ? "" : in.getControlloCombinazione().getAslResidenza().getId();
		String aslDomicilio = in.getControlloCombinazione().getAslDomicilio().getId() == null ? "" : in.getControlloCombinazione().getAslDomicilio().getId();
		String aslIscrizione= in.getControlloCombinazione().getAslIscrizione().getId() == null ? "" : in.getControlloCombinazione().getAslIscrizione().getId();
		
		boolean checkAslUguali = aslResidenza.equalsIgnoreCase(aslDomicilio) && aslDomicilio.equalsIgnoreCase(aslIscrizione);
		boolean cittadinanzaItaliana = dataDao.getMessaggioPerCodice(SharedConstants.COD_CITTADINANZA_ITALIANA).getValoreMsg().equalsIgnoreCase(in.getCittadinanza().getId());
				
		Date dataFineASL = !isFromMEF && !(checkAslUguali && cittadinanzaItaliana) ?
			((ModelCittadinoSistemaEsternoAURA)da).getDataFineASL() : Converter.getData(SharedConstants.DATA_ILLIMITATA);
				
		request.getBody().setDatiIscrizioneSanitaria(new DatiIscrizioneSanitaria());

		request.getBody().getDatiIscrizioneSanitaria().setDataInizioASL(dataInizioASL == null ? null : Converter.getXMLGregorianCalendar(sdf.parse(Converter.getData(dataInizioASL))));
		request.getBody().getDatiIscrizioneSanitaria().setDataFineASL(dataFineASL == null ? null : Converter.getXMLGregorianCalendar(sdf.parse(Converter.getData(dataFineASL))));
		request.getBody().getDatiIscrizioneSanitaria().setDataDecorrenza(Converter.getXMLGregorianCalendar(sdf.parse(Converter.getData(new Date(System.currentTimeMillis())))));

		// 14/01/2021 controllo 3 asl e cittadinanza
		request.getBody().getDatiIscrizioneSanitaria().setCodTipoProfiloSanitario(!isFromMEF && 
				!(checkAslUguali && cittadinanzaItaliana) ? 
				((ModelCittadinoSistemaEsternoAURA)da).getTipoProfilo() : "01");
		
		request.getBody().getDatiIscrizioneSanitaria().setSimulazione(0L);

		request.getBody().getDatiIscrizioneSanitaria().setDerogaAmbitoTerritoriale(0L);
		request.getBody().getDatiIscrizioneSanitaria().setDerogaAssociazione(0L);
		request.getBody().getDatiIscrizioneSanitaria().setDerogaPediatra(0L);
		request.getBody().getDatiIscrizioneSanitaria().setMMG(in.getControlloCombinazione().isDerogaMmg() ? 1L : 0L);
		request.getBody().getDatiIscrizioneSanitaria().setDerogaMassimale(0L);
		request.getBody().getDatiIscrizioneSanitaria().setDerogaMassimaleTemporaneo(0L);
		request.getBody().getDatiIscrizioneSanitaria().setNote(null);

		return request;
	}

	private InserimentoProfiloAnagrafico costruisciInserimentoProfiloAnagraficoIn(ModelCittadinoSistemaEsternoTS da, Payload1 payload, String codFiscale, ScerevLAudit audit) {
		InserimentoProfiloAnagrafico request = new InserimentoProfiloAnagrafico();
		request.setHeader(new InserimentoProfiloAnagraficoHeader());
		request.getHeader().setSender(Constants.COMPONENT_NAME);
		request.getHeader().setIdAsr("010"+payload.getMedico().getAsl().getId());
		request.getHeader().setRequestDateTime(Converter.getData(new Date(), "yyyy-MM-dd HH:mm:ss"));
		request.getHeader().setCfUtilizzatoreServizio(codFiscale);

		InserimentoProfiloAnagraficoBody body = new InserimentoProfiloAnagraficoBody();

		body.setDatiPrimari(new InserimentoProfiloAnagraficoDatiPrimari());
		body.getDatiPrimari().setNome(da.getNome());
		body.getDatiPrimari().setCognome(da.getCognome());
		body.getDatiPrimari().setSesso(da.getSesso());
		body.getDatiPrimari().setDataNascita(Converter.getXMLGregorianCalendar(da.getDatanascita()));
		body.getDatiPrimari().setDataDecesso(da.getDatadecesso() == null ? null : Converter.getXMLGregorianCalendar(da.getDatadecesso()));
		body.getDatiPrimari().setCodiceFiscale(codFiscale);
		body.getDatiPrimari().setCodiceStatoNascita(da.getIdStatonascita());
		body.getDatiPrimari().setDescrizioneStatoNascita(da.getStatoNasc());
		// 22/12/2020
		if(da.getIdStatonascita().equalsIgnoreCase(SharedConstants.ID_STATO_ITALIA_MEF)) {
			body.getDatiPrimari().setCodiceComuneNascitaItaliano(da.getIdComunenascita());
			body.getDatiPrimari().setDescrizioneComuneNascita(da.getComunenasc());
			body.getDatiPrimari().setCodiceProvinciaNascita(da.getProvincianasc());
		}
		body.getDatiPrimari().setCodiceCittadinanza(payload.getCittadinanza().getId());

		body.setDatiResidenza(new InserimentoProfiloAnagraficoResidenza());
		body.getDatiResidenza().setCodiceStatoResidenza(dataDao.getNazione(payload.getResidenza().getNazione()).getCodiceNazione());
		body.getDatiResidenza().setDescrizioneComuneResidenza(payload.getResidenza().getComune() == null ? da.getComuneResidenza() : payload.getResidenza().getComune());
		body.getDatiResidenza().setCodiceComuneResidenza(payload.getResidenza().getCodComune());
		body.getDatiResidenza().setIndirizzoResidenza(payload.getResidenza().getIndirizzo());
		body.getDatiResidenza().setNumerocivicoResidenza(payload.getResidenza().getCivico());
		body.getDatiResidenza().setCAPresidenza(payload.getResidenza().getCap());
		body.getDatiResidenza().setCodiceAslResidenza(payload.getControlloCombinazione() == null ? null : 
			payload.getControlloCombinazione().getAslResidenza() == null ? null : 
				payload.getControlloCombinazione().getAslResidenza().getId().equalsIgnoreCase(SharedConstants.EXTRA_REGIONE) ? "000000" : //FIXME Manlio workaround temporaneo per bypassare controllo formale. Da sostutire con nuovo servizio  
					"010"+payload.getControlloCombinazione().getAslResidenza().getId());
		//
		body.getDatiResidenza().setTelefonoResidenza(payload.getRecapiti() != null ? payload.getRecapiti().getTelefono() : null);
		
		body.setAltreInfo(new InserimentoProfiloAnagraficoAltreInfo());
		body.getAltreInfo().setDataInizioResidenza(Converter.getXMLGregorianCalendar(new Date()));
		body.getAltreInfo().setDataInizioDomicilio(Converter.getXMLGregorianCalendar(new Date()));
		body.getAltreInfo().setStatoContatto("2"); // 2 = Da verificare

		body.setDatiDomicilio(new InserimentoProfiloAnagraficoDomicilio());
		if (payload.getDomicilio() != null) {
			body.getDatiDomicilio().setCodiceComuneDomicilio(payload.getDomicilio().getCodComune());
			body.getDatiDomicilio().setDescrizioneComuneDomicilio(payload.getDomicilio().getComune());
			body.getDatiDomicilio().setIndirizzoDomicilio(payload.getDomicilio().getIndirizzo());
			body.getDatiDomicilio().setNumerocivicoDomicilio(payload.getDomicilio().getCivico());
			body.getDatiDomicilio().setCAPDomicilio(payload.getDomicilio().getCap());
		}
		body.getDatiDomicilio().setCodiceStatoDomicilio(dataDao.getMessaggioPerCodice(it.csi.scerev.scerevutil.business.SharedConstants.COD_CITTADINANZA_ITALIANA).getValoreMsg());
		body.getDatiDomicilio().setCodiceAslDomicilio(payload.getControlloCombinazione() == null ? null : 
			payload.getControlloCombinazione().getAslDomicilio() == null ? null : 
				payload.getControlloCombinazione().getAslDomicilio().getId().equalsIgnoreCase(SharedConstants.EXTRA_REGIONE) ? null :
					"010"+payload.getControlloCombinazione().getAslDomicilio().getId());

		request.setBody(body);
		return request;
	}

	private AggiornamentoProfiloAnagrafico costruisciAggiornamentoProfiloAnagraficoIn(
			ModelCittadinoSistemaEsternoAURA da, Payload1 payload, String codFiscale, ScerevLAudit audit) {
		AggiornamentoProfiloAnagrafico request = new AggiornamentoProfiloAnagrafico();

		request.setHeader(new AggiornamentoProfiloAnagraficoHeader());
		request.getHeader().setSender(Constants.COMPONENT_NAME);
		request.getHeader().setIdAsr("010" + payload.getMedico().getAsl().getId());
		request.getHeader().setRequestDateTime(Converter.getData(new Date(), "yyyy-MM-dd HH:mm:ss"));
		request.getHeader().setCfUtilizzatoreServizio(codFiscale);

		AggiornamentoProfiloAnagraficoBody body = new AggiornamentoProfiloAnagraficoBody();

		body.setDatiPrimari(new AggiornamentoProfiloAnagraficoDatiPrimari());
		body.getDatiPrimari().setIdAura(da.getIdAura().toString());
		body.getDatiPrimari().setCodiceFiscaleAura(da.getCodicefiscale());
		body.getDatiPrimari().setNome(da.getNome());
		body.getDatiPrimari().setCognome(da.getCognome());
		body.getDatiPrimari().setSesso(da.getSesso());
		body.getDatiPrimari().setDataNascita(Converter.getXMLGregorianCalendar(da.getDatanascita()));
		body.getDatiPrimari().setCodiceFiscale(codFiscale);
		body.getDatiPrimari().setDataDecesso(da.getDatadecesso() == null ? null : Converter.getXMLGregorianCalendar(da.getDatadecesso()));
		body.getDatiPrimari().setCodiceStatoNascita(da.getIdStatonascita());
		
		// 22/12/2020
		if(da.getIdStatonascita().equalsIgnoreCase(dataDao.getMessaggioPerCodice(SharedConstants.COD_CITTADINANZA_ITALIANA).getValoreMsg())) {
			body.getDatiPrimari().setCodiceComuneNascitaItaliano(da.getCodComuneNascita());
			body.getDatiPrimari().setDescrizioneComuneNascita(da.getComunenasc());
		}
		body.getDatiPrimari().setCodiceCittadinanza(payload.getCittadinanza().getId());

		body.setDatiResidenza(new AggiornamentoProfiloAnagraficoResidenza());
		if(payload.getResidenza()!=null) {
			if(payload.getResidenza().getNazione() == null) body.getDatiResidenza().setCodiceStatoResidenza(dataDao.getNazioneByCod(da.getNazioneResidenza()).getCodiceNazione());
			else body.getDatiResidenza().setCodiceStatoResidenza(dataDao.getNazione(payload.getResidenza().getNazione()).getCodiceNazione());
			body.getDatiResidenza().setCodiceComuneResidenza(payload.getResidenza().getCodComune() == null ? da.getComuneResidenza() : payload.getResidenza().getCodComune());
			body.getDatiResidenza().setDescrizioneComuneResidenza(payload.getResidenza().getComune() == null ? da.getDescComuneResidenza() : payload.getResidenza().getComune());
			body.getDatiResidenza().setIndirizzoResidenza(payload.getResidenza().getIndirizzo() == null ? da.getIndirizzoResidenza() : payload.getResidenza().getIndirizzo());
			body.getDatiResidenza().setNumerocivicoResidenza(payload.getResidenza().getCivico() == null ? da.getCivicoResidenza() : payload.getResidenza().getCivico());
			body.getDatiResidenza().setCAPresidenza(payload.getResidenza().getCap() == null ? da.getCapResidenza() : payload.getResidenza().getCap());
			body.getDatiResidenza().setCodiceAslResidenza(payload.getControlloCombinazione() == null ? null : 
				payload.getControlloCombinazione().getAslResidenza() == null ? null :
					payload.getControlloCombinazione().getAslResidenza().getId() == null ? da.getAslResidenza() : 
					payload.getControlloCombinazione().getAslResidenza().getId().equalsIgnoreCase(SharedConstants.EXTRA_REGIONE) ? "000000" : //FIXME Manlio workaround temporaneo per bypassare controllo formale. Da sostutire con nuovo servizio
						"010"+payload.getControlloCombinazione().getAslResidenza().getId());
			//
			body.getDatiResidenza().setTelefonoResidenza(payload.getRecapiti() != null ? payload.getRecapiti().getTelefono() : null);
		}
		body.setAltreInfo(new AggiornamentoProfiloAnagraficoAltreInfo());
		body.getAltreInfo().setDataInizioResidenza(payload.getResidenza() == null ? Converter.getXMLGregorianCalendar(da.getDataInizioResidenza()) : Converter.getXMLGregorianCalendar(new Date()));
		body.getAltreInfo().setDataInizioDomicilio(payload.getDomicilio() == null ? Converter.getXMLGregorianCalendar(da.getDataInizioDomicilio()) : Converter.getXMLGregorianCalendar(new Date()));
		body.getAltreInfo().setStatoContatto("2"); //2 = Da verificare

		body.setDatiDomicilio(new AggiornamentoProfiloAnagraficoDomicilio());
		if (payload.getDomicilio() != null) {
			body.getDatiDomicilio().setCodiceComuneDomicilio(payload.getDomicilio().getCodComune() == null ? da.getCodComuneDomicilio() : payload.getDomicilio().getCodComune());
			body.getDatiDomicilio().setDescrizioneComuneDomicilio(payload.getDomicilio().getComune() == null ? da.getDescComune() : payload.getDomicilio().getComune());
			body.getDatiDomicilio().setIndirizzoDomicilio(payload.getDomicilio().getIndirizzo() == null ? da.getIndirizzoDomicilio() : payload.getDomicilio().getIndirizzo());
			body.getDatiDomicilio().setNumerocivicoDomicilio(payload.getDomicilio().getCivico() == null ? da.getCivicoDomicilio() : payload.getDomicilio().getCivico());
			body.getDatiDomicilio().setCAPDomicilio(payload.getDomicilio().getCap() == null ? da.getCapDomicilio() : payload.getDomicilio().getCap());
			//
			body.getDatiDomicilio().setTelefonoDomicilio(da.getTelefonoDomicilio());
			body.getDatiDomicilio().setCodiceAslDomicilio(payload.getControlloCombinazione() == null ? null : 
				payload.getControlloCombinazione().getAslDomicilio() == null ? null :
					payload.getControlloCombinazione().getAslDomicilio().getId() == null ? da.getAslDomicilio() : 
					payload.getControlloCombinazione().getAslDomicilio().getId().equalsIgnoreCase(SharedConstants.EXTRA_REGIONE) ?  "000000" : //FIXME Manlio workaround temporaneo per bypassare controllo formale. Da sostutire con nuovo servizio
					"010"+payload.getControlloCombinazione().getAslDomicilio().getId());

			if(payload.getResidenza()!=null) {
				if(payload.getResidenza().getNazione() == null) body.getDatiDomicilio().setCodiceStatoDomicilio(dataDao.getNazioneByCod(da.getNazioneResidenza()).getCodiceNazione());
				else body.getDatiDomicilio().setCodiceStatoDomicilio(dataDao.getNazione(payload.getResidenza().getNazione()).getCodiceNazione());
			}
		}

		request.setBody(body);

		return request;

	}

	private AggiornamentoProfiloAnagrafico costruisciAggiornamentoProfiloAnagraficoInForSync(
			ModelCittadinoSistemaEsternoAURA da, Payload1 payload, String codFiscale, ScerevLAudit audit) {
		AggiornamentoProfiloAnagrafico request = new AggiornamentoProfiloAnagrafico();

		request.setHeader(new AggiornamentoProfiloAnagraficoHeader());
		request.getHeader().setSender(Constants.COMPONENT_NAME);
		request.getHeader().setIdAsr("010" + payload.getMedico().getAsl().getId());// confermato da Marcucci
		request.getHeader().setRequestDateTime(Converter.getData(new Date(), "yyyy-MM-dd HH:mm:ss"));
		request.getHeader().setCfUtilizzatoreServizio(codFiscale);

		AggiornamentoProfiloAnagraficoBody body = new AggiornamentoProfiloAnagraficoBody();

		body.setDatiPrimari(new AggiornamentoProfiloAnagraficoDatiPrimari());
		body.getDatiPrimari().setIdAura(da.getIdAura().toString());
		body.getDatiPrimari().setCodiceFiscaleAura(da.getCodicefiscale());
		body.getDatiPrimari().setNome(da.getNome());
		body.getDatiPrimari().setCognome(da.getCognome());
		body.getDatiPrimari().setSesso(da.getSesso());
		body.getDatiPrimari().setDataNascita(Converter.getXMLGregorianCalendar(da.getDatanascita()));
		body.getDatiPrimari().setCodiceFiscale(codFiscale);
		body.getDatiPrimari().setDataDecesso(da.getDatadecesso() == null ? null : Converter.getXMLGregorianCalendar(da.getDatadecesso()));
		body.getDatiPrimari().setCodiceStatoNascita(da.getIdStatonascita());
		body.getDatiPrimari().setCodiceComuneNascitaItaliano(da.getCodComuneNascita());
		body.getDatiPrimari().setDescrizioneComuneNascita(da.getComunenasc());
		body.getDatiPrimari().setCodiceCittadinanza(payload.getCittadinanza().getId());

		body.setDatiResidenza(new AggiornamentoProfiloAnagraficoResidenza());
		if(payload.getResidenza()!=null) {
			if(payload.getResidenza().getNazione() == null) body.getDatiResidenza().setCodiceStatoResidenza(dataDao.getNazioneByCod(da.getNazioneResidenza()).getCodiceNazione());
			else body.getDatiResidenza().setCodiceStatoResidenza(dataDao.getNazione(payload.getResidenza().getNazione()).getCodiceNazione());
			body.getDatiResidenza().setCodiceComuneResidenza(payload.getResidenza().getCodComune() == null ? da.getComuneResidenza() : payload.getResidenza().getCodComune());
			body.getDatiResidenza().setDescrizioneComuneResidenza(payload.getResidenza().getComune() == null ? da.getDescComuneResidenza() : payload.getResidenza().getComune());
			body.getDatiResidenza().setIndirizzoResidenza(payload.getResidenza().getIndirizzo() == null ? da.getIndirizzoResidenza() : payload.getResidenza().getIndirizzo());
			body.getDatiResidenza().setNumerocivicoResidenza(payload.getResidenza().getCivico() == null ? da.getCivicoResidenza() : payload.getResidenza().getCivico());
			body.getDatiResidenza().setCAPresidenza(payload.getResidenza().getCap() == null ? da.getCapResidenza() : payload.getResidenza().getCap());
			body.getDatiResidenza().setCodiceAslResidenza(payload.getControlloCombinazione() == null ? null : 
				payload.getControlloCombinazione().getAslResidenza() == null ? null :
					payload.getControlloCombinazione().getAslResidenza().getId() == null ? da.getAslResidenza() : 
					payload.getControlloCombinazione().getAslResidenza().getId().equalsIgnoreCase(SharedConstants.EXTRA_REGIONE) ?  "000000" : //FIXME Manlio workaround temporaneo per bypassare controllo formale. Da sostutire con nuovo servizio
						"010"+payload.getControlloCombinazione().getAslResidenza().getId());
			body.getDatiResidenza().setTelefonoResidenza(payload.getRecapiti() != null ? payload.getRecapiti().getTelefono() : null);
		} else {
			body.getDatiResidenza().setCodiceStatoResidenza(dataDao.getNazioneByCod(da.getNazioneResidenza()).getCodiceNazione());
			body.getDatiResidenza().setCodiceComuneResidenza(da.getComuneResidenza());
			body.getDatiResidenza().setDescrizioneComuneResidenza(da.getDescComuneResidenza());
			body.getDatiResidenza().setIndirizzoResidenza(da.getIndirizzoResidenza());
			body.getDatiResidenza().setNumerocivicoResidenza(da.getCivicoResidenza());
			body.getDatiResidenza().setCAPresidenza(da.getCapResidenza());
			body.getDatiResidenza().setCodiceAslResidenza(da.getAslResidenza());
			//
			body.getDatiResidenza().setTelefonoResidenza(payload.getRecapiti() != null ? payload.getRecapiti().getTelefono() : null);
		}
		body.setAltreInfo(new AggiornamentoProfiloAnagraficoAltreInfo());
		body.getAltreInfo().setDataInizioResidenza(payload.getResidenza() == null ? Converter.getXMLGregorianCalendar(da.getDataInizioResidenza()) : Converter.getXMLGregorianCalendar(new Date()));
		body.getAltreInfo().setDataInizioDomicilio(payload.getDomicilio() == null ? Converter.getXMLGregorianCalendar(da.getDataInizioDomicilio()) : Converter.getXMLGregorianCalendar(new Date()));
		body.getAltreInfo().setStatoContatto("2"); // 2 = Da verificare

		body.setDatiDomicilio(new AggiornamentoProfiloAnagraficoDomicilio());
		if (payload.getDomicilio() != null) {
			body.getDatiDomicilio().setCodiceComuneDomicilio(payload.getDomicilio().getCodComune() == null ? da.getCodComuneDomicilio() : payload.getDomicilio().getCodComune());
			body.getDatiDomicilio().setDescrizioneComuneDomicilio(payload.getDomicilio().getComune() == null ? da.getDescComune() : payload.getDomicilio().getComune());
			body.getDatiDomicilio().setIndirizzoDomicilio(payload.getDomicilio().getIndirizzo() == null ? da.getIndirizzoDomicilio() : payload.getDomicilio().getIndirizzo());
			body.getDatiDomicilio().setNumerocivicoDomicilio(payload.getDomicilio().getCivico() == null ? da.getCivicoDomicilio() : payload.getDomicilio().getCivico());
			body.getDatiDomicilio().setCAPDomicilio(payload.getDomicilio().getCap() == null ? da.getCapDomicilio() : payload.getDomicilio().getCap());
			//
			body.getDatiDomicilio().setTelefonoDomicilio(da.getTelefonoDomicilio());
			if(payload.getResidenza()!=null) {
				if(payload.getResidenza().getNazione() == null) body.getDatiDomicilio().setCodiceStatoDomicilio(dataDao.getNazioneByCod(da.getNazioneResidenza()).getCodiceNazione());
				else body.getDatiDomicilio().setCodiceStatoDomicilio(dataDao.getNazione(payload.getResidenza().getNazione()).getCodiceNazione());
			}
			body.getDatiDomicilio().setCodiceAslDomicilio(payload.getControlloCombinazione() == null ? null : 
				payload.getControlloCombinazione().getAslDomicilio() == null ? null :
					payload.getControlloCombinazione().getAslDomicilio().getId() == null ? da.getAslDomicilio() : 
					payload.getControlloCombinazione().getAslDomicilio().getId().equalsIgnoreCase(SharedConstants.EXTRA_REGIONE) ?  "000000" : //FIXME Manlio workaround temporaneo per bypassare controllo formale. Da sostutire con nuovo servizio
					"010"+payload.getControlloCombinazione().getAslDomicilio().getId());
		} else {
			body.getDatiDomicilio().setCodiceComuneDomicilio(da.getCodComuneDomicilio());
			body.getDatiDomicilio().setDescrizioneComuneDomicilio(da.getDescComune());
			body.getDatiDomicilio().setIndirizzoDomicilio(da.getIndirizzoDomicilio());
			body.getDatiDomicilio().setNumerocivicoDomicilio(da.getCivicoDomicilio());
			body.getDatiDomicilio().setCAPDomicilio(da.getCapDomicilio());
			//
			body.getDatiDomicilio().setTelefonoDomicilio(da.getTelefonoDomicilio());
			body.getDatiDomicilio().setCodiceStatoDomicilio(dataDao.getNazioneByCod(da.getNazioneResidenza()).getCodiceNazione());
			body.getDatiDomicilio().setCodiceAslDomicilio(payload.getControlloCombinazione() == null ? null : payload.getControlloCombinazione().getAslDomicilio() == null ? null : "010"+payload.getControlloCombinazione().getAslDomicilio().getId());
		}

		request.setBody(body);

		return request;

	}		

	private boolean isBenefViventeAndAssistAttivaInPiemonte(ModelCittadinoSistemaEsterno datiCittadino, Payload1 input, ScerevLAudit audit) throws IllegalArgumentException, IllegalAccessException, Exception {
		boolean esito = false;
		if (datiCittadino.getDatadecesso() != null)
			return esito;
		if (datiCittadino instanceof ModelCittadinoSistemaEsternoAURA) {
			if (((ModelCittadinoSistemaEsternoAURA)datiCittadino).getIdAura() != null) { //trovato in aura
				Date today = new Date();
				// 03/11/20: Se l'assistenza e' scaduta o non ha assistenza controllo su sistema TS l'assistenza
				Date dataFineASL = ((ModelCittadinoSistemaEsternoAURA)datiCittadino).getDataFineASL() ;
				if( (dataFineASL == null) || (dataFineASL != null && !dataFineASL.after(today)) ) {
					InterrogaMefScerevResult soggTS =  ServiziEsterniClient.getInstance().findCittadinoSuTS(datiCittadino.getCodicefiscale(), audit);
					esito = !(isAssistenzaMefFuoriRegione(soggTS, null)) //&&		// 27/11/2020
							//(input.getMedicoAltraRegione() == null)
							;
				} else return true;
				
				return esito;
			}
		} else {
			esito = !(isAssistenzaMefFuoriRegione(null, ((ModelCittadinoSistemaEsternoTS)datiCittadino))) //&&		// 27/11/2020
					//(input.getMedicoAltraRegione() == null)
					;
			return esito;
		}
		return esito;
	}

	private boolean isAssistenzaMefFuoriRegione(InterrogaMefScerevResult soggTS, ModelCittadinoSistemaEsternoTS modelTS) {
		String codRegPiemonte = dataDao.getMessaggioPerCodice(SharedConstants.COD_REGIONE_PIEMONTE).getValoreMsg();
		
		if(soggTS != null) {
			return (soggTS.getCodiceTipoAssistito() != null) &&
				   ( (soggTS.getCodiceRegioneAssistenza()) == null || !(soggTS.getCodiceRegioneAssistenza().equalsIgnoreCase(codRegPiemonte)) ) 
				    && ( (soggTS.getDataFineValidita() == null) ||  !(Converter.getData(soggTS.getDataFineValidita()).before(new Date())));
		} else {
			return (modelTS.getCodiceTipoAssistito() != null) &&
				   ( (modelTS.getCodiceRegioneResidenza()) == null || !(modelTS.getCodiceRegioneResidenza().equalsIgnoreCase(codRegPiemonte)) ) 
				    && ( (modelTS.getDataFineValidita() == null) ||  !(Converter.getData(modelTS.getDataFineValidita()).before(new Date())));
			
		}
	}

	private boolean isBenefViventeAndAssistAttivaFuoriPiemonte(ModelCittadinoSistemaEsterno datiCittadino, Payload1 input) throws Exception {		
		// 22/12/2020 beneficiario vivente con medico in altra regione
		return datiCittadino.getDatadecesso() == null && input.getMedicoAltraRegione() != null;
	}

	public ModelCittadinoInfoCustom getCittadinoInfo(String citId, ScerevLAudit audit) throws Exception {
		// 3. controllo presenza assistito su AURA o su Sistema TS
		ModelCittadinoInfoCustom mcic = null;
		SoggettoAuraBodyNew sog = null;
		Map<DatiAnagrafici, SoggettoAuraBodyNew> auraMap;
		try {
			List<DatiAnagrafici> datiCittadini = ServiziEsterniClient.getInstance().findCittadino(citId, null, null, null, audit);		
			
			if (datiCittadini != null) {
				auraMap = ServiziEsterniClient.getInstance().anagrafeSanitariaAttiva(datiCittadini, audit);
				if(auraMap != null) {
					DatiAnagrafici datiCittadinoAura = auraMap.keySet().iterator().next();
					sog = auraMap.get(datiCittadinoAura);
					mcic =  new ModelCittadinoInfoCustom(datiCittadinoAura, sog);
					mcic.getAslAssistenza().setDescrizione(mcic.getAslAssistenza().getId() == null ? null : dataDao.getAsl(mcic.getAslAssistenza().getId()).getDenominazioneAzienda());
					
					if(mcic.getCittadinanza().getId() != null) {
						ScerevDCittadinanza citt = dataDao.getCittadinanzaByCod(mcic.getCittadinanza().getId());
						mcic.getCittadinanza().setComunitario(citt != null ? citt.getComunitario() : null);
					}
					
					//15/09/20: Se l'assistenza e' scaduta o non ha assistenza controllo su sistema TS la regione di assistenza
					if( (mcic.getDataInizioAssistenza() == null || mcic.getDataFineAssistenza() == null) || 
							(mcic.getDataFineAssistenza() != null && mcic.getDataFineAssistenza().before(new Date())) ) {
						InterrogaMefScerevResult soggTS =  ServiziEsterniClient.getInstance().findCittadinoSuTS(citId, audit);
						mcic.setMedicoAltraRegione(isAssistenzaMefFuoriRegione(soggTS, null));
					}
					
				} else throw new AuraNotFoundException();
				
			} else throw new AuraNotFoundException();
		} catch (AuraNotFoundException e) {
			try {
				InterrogaMefScerevResult soggTS =  ServiziEsterniClient.getInstance().findCittadinoSuTS(citId, audit);
				mcic =  new ModelCittadinoInfoCustom(soggTS);
				mcic.setMedicoAltraRegione(isAssistenzaMefFuoriRegione(soggTS, null));			
			} catch (Exception e1) {
				throw new SistemaTSNotFoundException(); 
			}
		} catch (AuraException e) {
			throw new AuraNotFoundException();
		}			
		if (mcic != null) {
			// Ricerco eventuale pratica pendente (stato bozza oppure in corso di gestione
			// in Backoffice)
			List<String> statiPratica = new ArrayList<String>();
			statiPratica.add(StatoPraticaEnum.BOZZA.getKey());
			statiPratica.add(StatoPraticaEnum.IN_LAVORAZIONE.getKey());
			statiPratica.add(StatoPraticaEnum.IN_REVISIONE.getKey());
			statiPratica.add(StatoPraticaEnum.INVIATA.getKey());
			List<ScerevTPratica> elencoPratiche = dataDao.getPraticaByCFandStato(citId, statiPratica);
			if (elencoPratiche != null && elencoPratiche.size() > 0) {
				mcic.setRichiestaCambio(new ModelCambioMedicoRichiestaCustom(elencoPratiche.get(0)));
				ModelCittadinoRecapiti rec = new ModelCittadinoRecapiti();
				rec.setTelefono(sog != null && sog.getInfoAnag() != null && sog.getInfoAnag().getResidenza() != null ? sog.getInfoAnag().getResidenza().getTelefono() : null);
				mcic.getRichiestaCambio().setRecapiti(rec);
			}
		}
		return mcic;
	}

	public ModelCambioMedicoRichiestaCustom rettificaRichiesta(ScerevLAudit audit, String citId, String richiestaId,
			Payload2 payload) throws Exception {

		verificaFirmaAllegati(payload.getAllegati());

		ScerevTPratica pratica = getDataDao().getPraticaPerNumero(Converter.getInt(richiestaId));

		if (!pratica.getScerevDPraticaStato().getCodicePraticaStato()
				.equalsIgnoreCase(StatoPraticaEnum.IN_REVISIONE.getKey()))
			throw new Exception("Lo stato della pratica deve essere 'IN REVISIONE'");
		Set<ScerevTDocumento> documento = new HashSet<ScerevTDocumento>(
				dataDao.getDocumentoByPratica(pratica.getPkPratica()));
		pratica.setScerevTDocumentos(documento);

		List<ScerevDPraticaStato> listStati = dataDao.getStatiPratica();
		for (ScerevDPraticaStato stato : listStati) {
			if (stato.getCodicePraticaStato().equalsIgnoreCase(StatoPraticaEnum.IN_LAVORAZIONE.getKey())) {
				pratica.setScerevDPraticaStato(stato);
				break;
			}
		}
		pratica.setDataModifica(new Timestamp(System.currentTimeMillis()));

		ScerevTCronologia cronologia = new ScerevTCronologia();
		cronologia.setScerevTPratica(pratica);
		cronologia.setNotaRichiedente(payload.getNote());
		cronologia.setUtente(pratica.getNomeAssistito() + " " + pratica.getCognomeAssistito());
		cronologia.setScerevDPraticaStato(pratica.getScerevDPraticaStato());
		cronologia.setDataOraInserimento(new Timestamp(System.currentTimeMillis()));
		cronologia.setDataCreazione(new Timestamp(System.currentTimeMillis()));
		cronologia.setDataModifica(new Timestamp(System.currentTimeMillis()));
		cronologia.setScerevTPratica(pratica);
		dataDao.insertCronologia(cronologia);

		if (payload.getAllegati() != null) {
			for (ModelDocumento d : payload.getAllegati()) {
				pratica = aggiornaFile(pratica, d.getTipo(), d.getNomeFile(), d.getBase64().getBytes());
			}
		}

		dataDao.updatePratica(pratica);
		return new ModelCambioMedicoRichiestaCustom(pratica);
	}

	private void verificaFirmaAllegati(ModelAllegati allegati) throws Exception {
		if (allegati != null) {
			ScerevDMsg paramVerificaFirmaAllegati = dataDao.getMessaggioPerCodice("VERIFICA_FIRMA_ALLEGATI");
			ScerevDMsg paramVerificaFirmaAllegatiLog = dataDao.getMessaggioPerCodice("VERIFICA_FIRMA_ALLEGATI_LOG");
			boolean logAttivo = paramVerificaFirmaAllegatiLog != null
					&& "true".equalsIgnoreCase(paramVerificaFirmaAllegatiLog.getValoreMsg());

			if (paramVerificaFirmaAllegati != null
					&& "true".equalsIgnoreCase(paramVerificaFirmaAllegati.getValoreMsg())) {
				for (ModelDocumento d : allegati) {
					if (d.getTipo()
							.equalsIgnoreCase(DocumentoTipoEnum.DOCUMENTAZIONE_CLINICA_FIRMATA_DIGITALMENTE.getKey())) {
						if (!FirmaDigitaleUtility.signedAttachment(d.getBase64(), logAttivo)) {
							throw new AllegatoNonFirmatoException(SharedConstants.ERROR_CODE_ALLEGATO_NON_FIRMATO, d.getNomeFile());
						}
					}
				}
			}
		}
	}

	private ScerevTPratica aggiornaFile(ScerevTPratica pratica, String tipoDocumento, String fileName,
			byte[] fileContent) {
		ScerevTFile file = new ScerevTFile();
		file.setDataCreazione(new Timestamp(System.currentTimeMillis()));
		file.setDataModifica(new Timestamp(System.currentTimeMillis()));
		file.setFile(fileContent);
		file.setNameFile(fileName);
		file.setSizeFile(new BigDecimal(fileContent.length));
		dataDao.insertFile(file);

		boolean isFound = false;
		boolean hasDocuments = false;
		if (pratica.getScerevTDocumentos() != null) {
			hasDocuments = true;
			for (Iterator<ScerevTDocumento> iterator = pratica.getScerevTDocumentos().iterator(); iterator.hasNext();) {
				ScerevTDocumento documento = (ScerevTDocumento) iterator.next();
				if (documento.getScerevDDocumentoTipo().getCodiceDocumentoTipo().equalsIgnoreCase(tipoDocumento)) {
					isFound = true;
					ScerevTFile fileOld = documento.getScerevTFile();
					fileOld.setDataCancellazione(new Timestamp(System.currentTimeMillis()));
					dataDao.updateFile(fileOld);

					documento.setScerevTFile(file);
					break;
				}
			}
		}
		if (!isFound) {
			if (!hasDocuments)
				pratica.setScerevTDocumentos(new HashSet<ScerevTDocumento>());

			ScerevTDocumento std = new ScerevTDocumento();
			std.setDataCreazione(new Timestamp(System.currentTimeMillis()));
			std.setDataModifica(new Timestamp(System.currentTimeMillis()));
			std.setScerevDDocumentoTipo(new ScerevDDocumentoTipo());
			List<ScerevDDocumentoTipo> listScerevDDocumentoTipo = dataDao.getListScerevDDocumentoTipo();
			for (ScerevDDocumentoTipo docTipo : listScerevDDocumentoTipo) {
				if (docTipo.getCodiceDocumentoTipo().equalsIgnoreCase(tipoDocumento)) {
					std.setScerevDDocumentoTipo(docTipo);
					break;
				}
			}
			std.setScerevTFile(file);
			std.setScerevTPratica(pratica);
			pratica.getScerevTDocumentos().add(std);
		}
		return pratica;
	}

	/**
	 * Verifica che nel DB non ci sia un record nella tabella SCEREV_T_PRATICA tale
	 * per cui CF_ASSISTITO = citId, in stato BOZZA, INVIATA, IN REVISIONE, IN
	 * LAVORAZIONE  //o COMPLETATA// NON PIU'
	 * 
	 * @throws PraticaNonValidaException
	 */
	public void chkPraticaPendente(String citId) throws DomainException {
		List<String> statiPratica = new ArrayList<String>();
		// statiPratica.add(StatoPraticaEnum.BOZZA.getKey());
		statiPratica.add(StatoPraticaEnum.INVIATA.getKey());
		statiPratica.add(StatoPraticaEnum.IN_REVISIONE.getKey());
		statiPratica.add(StatoPraticaEnum.IN_LAVORAZIONE.getKey());
//		statiPratica.add(StatoPraticaEnum.COMPLETATA.getKey());
		List<ScerevTPratica> elencoPratiche = dataDao.getPraticaByCFandStato(citId, statiPratica);
		if (elencoPratiche != null && elencoPratiche.size() > 0)
			throw new DomainException(Constants.ESISTE_PRATICA_PENDENTE);
	}

	public ScerevTPratica aggiornamentoPraticaRichiestaNuovoMedico(Payload1 input, String citId, ModelCittadinoSistemaEsterno model, int tipoRichiesta, String shib, ScerevLAudit audit) {
		//tiporichiesta 0=bozza, 1=automatica, 2=operatorei di backOffice
		ScerevTPratica pratica = null;
		ScerevTPratica bozza = getPraticaBozza(citId);
		switch(tipoRichiesta) {
			case 1: {
				// 27/11/2020
				pratica = prepareScerevTPratica(input, model, null, citId, TipologiaPraticaEnum.AUTOMATICA.getKey(), audit);
				pratica = dataDao.updatePratica(pratica);
				pratica = addPraticaTipo(input, pratica, shib, citId);
				pratica = dataDao.updatePratica(pratica);

//				// Revoca medico precedentemente associato
				revocaPratica(citId, pratica);
				
				break;
			}
			case 2: {
				pratica = prepareScerevTPratica(input, model, bozza, citId, TipologiaPraticaEnum.BACK_OFFICE.getKey(), audit);
				pratica = dataDao.updatePratica(pratica);
	
				if(bozza == null) {
					pratica = addPraticaTipo(input, pratica, shib, citId);
					pratica = dataDao.updatePratica(pratica);
				}
				
				pratica = addAllegati(input, pratica);
				pratica = dataDao.updatePratica(pratica);
				
				ScerevTCronologia cronologia = new ScerevTCronologia();
				cronologia.setScerevTPratica(pratica);
				if(input.getDatiAggiuntivi()!=null) cronologia.setNotaRichiedente(input.getDatiAggiuntivi().getNote());
				cronologia.setUtente(pratica.getNomeAssistito() + " " + pratica.getCognomeAssistito());
				cronologia.setScerevDPraticaStato(pratica.getScerevDPraticaStato());
				cronologia.setDataOraInserimento(new Timestamp(System.currentTimeMillis()));
				cronologia.setDataCreazione(new Timestamp(System.currentTimeMillis()));
				cronologia.setDataModifica(new Timestamp(System.currentTimeMillis()));
				dataDao.insertCronologia(cronologia);

				Set<ScerevTCronologia> cr = new HashSet<ScerevTCronologia>();
				cr.add(cronologia);
				pratica.setScerevTCronologias(cr);
				
				break;
			}
	
			case 0: {
				if(bozza == null) {
					pratica = prepareScerevTPratica(input, model, bozza, citId, TipologiaPraticaEnum.BOZZA.getKey(), audit);
					pratica = dataDao.updatePratica(pratica);
					pratica = addPraticaTipo(input, pratica, shib, citId);
					pratica = dataDao.updatePratica(pratica);	
				} else pratica = bozza;
				pratica = addAllegati(input, pratica);
				pratica = dataDao.updatePratica(pratica);
				
				break;
			}
		}
		return pratica;
	}
	
	private ScerevTPratica addAllegati(Payload1 input, ScerevTPratica pratica) {
		if(input.getAllegati() != null) {
			
			pratica.setScerevTDocumentos(new HashSet<ScerevTDocumento>());
			ModelAllegati allegati = input.getAllegati();
			for(ModelDocumento d : allegati) {
				pratica = aggiornaFile(pratica, d.getTipo(), d.getNomeFile(), d.getBase64().getBytes());
			}
		}

		return pratica;
	}

	private boolean revocaPratica(String citId, ScerevTPratica pratica) {
		try {
			List<ScerevTPratica> listaPratiche = dataDao.getPraticaPerCF(citId);
			for (ScerevTPratica praticaDaRevocare : listaPratiche) {
				if (!praticaDaRevocare.getNumeroPratica().equals(pratica.getNumeroPratica())
						&& praticaDaRevocare.getDataRevocaMedico() == null && praticaDaRevocare.getScerevDPraticaStato()
								.getCodicePraticaStato().equals(StatoPraticaEnum.CHIUSA.getKey())) {
					praticaDaRevocare.setDataRevocaMedico(new Timestamp(new Date().getTime()));
					praticaDaRevocare.setDataRevocaAssistenza(new Timestamp(new Date().getTime()));
					praticaDaRevocare.setDataModifica(new Timestamp(new Date().getTime()));
					dataDao.updatePratica(praticaDaRevocare);
				}
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	private ScerevTPratica addPraticaTipo(Payload1 input, ScerevTPratica pratica, String shib, String citId) {
		ScerevRPraticaPraticaTipo praticaPraticaTipo = new ScerevRPraticaPraticaTipo();	
		praticaPraticaTipo.setScerevTPratica(pratica);
		praticaPraticaTipo.setDataCreazione(new Timestamp(new Date().getTime()));
		praticaPraticaTipo.setDataModifica(new Timestamp(new Date().getTime()));

		if(input.getDelegato() != null && (Checker.isMaggiorenne(pratica.getDataNascitaAssistito())) ) {
			praticaPraticaTipo.setScerevDPraticaTipo(dataDao.getTipologiaPratica(SharedConstants.DELEGANTE_MAGGIORENNE_TYPE));
			if(praticaPraticaTipo.getScerevDPraticaTipo()!=null) dataDao.insertPraticaPraticaTipo(praticaPraticaTipo);
		}
		
		if(input.getDelegato() != null && (!Checker.isMaggiorenne(pratica.getDataNascitaAssistito())) ) {
			praticaPraticaTipo.setScerevDPraticaTipo(dataDao.getTipologiaPratica(SharedConstants.DELEGANTE_MINORENNE_TYPE));		
			if(praticaPraticaTipo.getScerevDPraticaTipo()!=null) dataDao.insertPraticaPraticaTipo(praticaPraticaTipo);	
		}

		if(dataDao.getCittadinanzaByCod(input.getCittadinanza().getId()).getValoreCittadinanza().equalsIgnoreCase(SharedConstants.CITTADINANZA_ITALIANA)) {
			praticaPraticaTipo.setScerevDPraticaTipo(dataDao.getTipologiaPratica(SharedConstants.ASSISTITO_ITALIANO_TYPE));		
			if(praticaPraticaTipo.getScerevDPraticaTipo()!=null) dataDao.insertPraticaPraticaTipo(praticaPraticaTipo);	
		} else if (dataDao.getCittadinanzaByCod(input.getCittadinanza().getId()).getComunitario()) {
			praticaPraticaTipo.setScerevDPraticaTipo(dataDao.getTipologiaPratica(SharedConstants.ASSISTITO_COMUNITARIO_TYPE));		
			if(praticaPraticaTipo.getScerevDPraticaTipo()!=null) dataDao.insertPraticaPraticaTipo(praticaPraticaTipo);	
		} else {
			praticaPraticaTipo.setScerevDPraticaTipo(dataDao.getTipologiaPratica(SharedConstants.ASSISTITO_EXTRACOMUNITARIO_TYPE));		
			if(praticaPraticaTipo.getScerevDPraticaTipo()!=null) dataDao.insertPraticaPraticaTipo(praticaPraticaTipo);	
		}

		if(input.getDomicilio() == null  && input.getResidenza() == null) {
			praticaPraticaTipo.setScerevDPraticaTipo(dataDao.getTipologiaPratica(SharedConstants.NO_VARIAZIONE_TYPE));		
			if(praticaPraticaTipo.getScerevDPraticaTipo()!=null) dataDao.insertPraticaPraticaTipo(praticaPraticaTipo);	
		} else if (input.getDomicilio() != null  && input.getResidenza() == null ) {
			praticaPraticaTipo.setScerevDPraticaTipo(dataDao.getTipologiaPratica(SharedConstants.VARIAZIONE_DOMICILIO_TYPE));		
			if(praticaPraticaTipo.getScerevDPraticaTipo()!=null) dataDao.insertPraticaPraticaTipo(praticaPraticaTipo);
		}else if (input.getDomicilio() == null  && input.getResidenza() != null ) {
			praticaPraticaTipo.setScerevDPraticaTipo(dataDao.getTipologiaPratica(SharedConstants.VARIAZIONE_RESIDENZA_TYPE));		
			if(praticaPraticaTipo.getScerevDPraticaTipo()!=null) dataDao.insertPraticaPraticaTipo(praticaPraticaTipo);
		}else if (input.getDomicilio() != null  && input.getResidenza() != null ) {
			praticaPraticaTipo.setScerevDPraticaTipo(dataDao.getTipologiaPratica(SharedConstants.VARIAZIONE_INDIRIZZO_RESIDENZA_TYPE));		
			if(praticaPraticaTipo.getScerevDPraticaTipo()!=null) dataDao.insertPraticaPraticaTipo(praticaPraticaTipo);
		}

		if(input.getDatiAggiuntivi()!=null) {
			if(input.getDatiAggiuntivi().getMotivazioneDomicilio() != null) {
				if(input.getDatiAggiuntivi().getMotivazioneDomicilio().getDescrizione().equalsIgnoreCase("Studio")
						|| input.getDatiAggiuntivi().getStudioNomeIstituto() != null) {
					praticaPraticaTipo.setScerevDPraticaTipo(dataDao.getTipologiaPratica(SharedConstants.DEROGA_STUDIO_TYPE));		
					if(praticaPraticaTipo.getScerevDPraticaTipo()!=null) dataDao.insertPraticaPraticaTipo(praticaPraticaTipo);					
				} 
				
				if(input.getDatiAggiuntivi().getMotivazioneDomicilio().getDescrizione().equalsIgnoreCase("Cura")) {
					praticaPraticaTipo.setScerevDPraticaTipo(dataDao.getTipologiaPratica(SharedConstants.DEROGA_CURA_TYPE));		
					if(praticaPraticaTipo.getScerevDPraticaTipo()!=null) dataDao.insertPraticaPraticaTipo(praticaPraticaTipo);
				}
				
				if(input.getDatiAggiuntivi().getMotivazioneDomicilio().getDescrizione().equalsIgnoreCase("Lavoro")
						|| input.getDatiAggiuntivi().getLavoroAzienda() != null 
						|| input.getDatiAggiuntivi().getLavoroPartitaIva() != null) {
					praticaPraticaTipo.setScerevDPraticaTipo(dataDao.getTipologiaPratica(SharedConstants.DEROGA_LAVORO_TYPE));		
					if(praticaPraticaTipo.getScerevDPraticaTipo()!=null) dataDao.insertPraticaPraticaTipo(praticaPraticaTipo);
				}
				
				boolean isRicongiungimento = Checker.isValorizzato(input.getDatiAggiuntivi().getRicongiungimentoCodiceFiscale()) || 
											 Checker.isValorizzato(input.getDatiAggiuntivi().getRicongiungimentoCognome()) || 
											 Checker.isValorizzato(input.getDatiAggiuntivi().getRicongiungimentoNome()) ||
											 Checker.isValorizzato(input.getDatiAggiuntivi().getRicongiungimentoParentela());
				
				String aslResidenza = input.getControlloCombinazione().getAslResidenza().getId() == null ? "" : input.getControlloCombinazione().getAslResidenza().getId();
				String aslDomicilio = input.getControlloCombinazione().getAslDomicilio().getId() == null ? "" : input.getControlloCombinazione().getAslDomicilio().getId();
				String aslIscrizione= input.getControlloCombinazione().getAslIscrizione().getId() == null ? "" : input.getControlloCombinazione().getAslIscrizione().getId();
				
				boolean derogaPerRicong    = (aslResidenza.equalsIgnoreCase(aslDomicilio) && aslDomicilio.equalsIgnoreCase(aslIscrizione));
				boolean domicilioPerRicong = (aslResidenza.equalsIgnoreCase(aslDomicilio) && !aslDomicilio.equalsIgnoreCase(aslIscrizione) ||
											  aslDomicilio.equalsIgnoreCase(aslIscrizione) && !aslIscrizione.equalsIgnoreCase(aslResidenza));
				
				if(isRicongiungimento && derogaPerRicong) {
					praticaPraticaTipo.setScerevDPraticaTipo(dataDao.getTipologiaPratica(SharedConstants.DEROGA_RICONG_TYPE));		
					if(praticaPraticaTipo.getScerevDPraticaTipo()!=null) dataDao.insertPraticaPraticaTipo(praticaPraticaTipo);
				} else if(isRicongiungimento && domicilioPerRicong) {
					// 12/05/2021 split motivo domicilio
					if(input.getDatiAggiuntivi().getMotivazioneDomicilio().getId().equalsIgnoreCase("5")) { // Ricongiungimento del nucleo familiare con lavoratore domiciliato
						praticaPraticaTipo.setScerevDPraticaTipo(dataDao.getTipologiaPraticaByCodice(SharedConstants.COD_DOMICILIO_RICONG_5_TYPE));
					} else if(input.getDatiAggiuntivi().getMotivazioneDomicilio().getId().equalsIgnoreCase("6")) { // Motivi di salute con assistenza garantibile nell'ambito familiare
						praticaPraticaTipo.setScerevDPraticaTipo(dataDao.getTipologiaPraticaByCodice(SharedConstants.COD_DOMICILIO_RICONG_6_TYPE));
					}
					if(praticaPraticaTipo.getScerevDPraticaTipo()!=null) dataDao.insertPraticaPraticaTipo(praticaPraticaTipo);
				}
			}
		}

		if(input.getControlloCombinazione().isDerogaAssociazione()) {
			praticaPraticaTipo.setScerevDPraticaTipo(dataDao.getTipologiaPratica(SharedConstants.DEROGA_ASSOC_TYPE));		
			if(praticaPraticaTipo.getScerevDPraticaTipo()!=null) dataDao.insertPraticaPraticaTipo(praticaPraticaTipo);
		}

		if (input.getControlloCombinazione().isDerogaTerritoriale()) {
			praticaPraticaTipo.setScerevDPraticaTipo(dataDao.getTipologiaPratica(SharedConstants.DEROGA_TERR_TYPE));
			if (praticaPraticaTipo.getScerevDPraticaTipo() != null)
				dataDao.insertPraticaPraticaTipo(praticaPraticaTipo);
		}

		if(input.getControlloCombinazione().isDerogaPls()) {
			praticaPraticaTipo.setScerevDPraticaTipo(dataDao.getTipologiaPratica(SharedConstants.DEROGA_PLS_TYPE));		
			if(praticaPraticaTipo.getScerevDPraticaTipo()!=null) dataDao.insertPraticaPraticaTipo(praticaPraticaTipo);
		}
		
		if(input.getRinnovoAssistenza()) {
			praticaPraticaTipo.setScerevDPraticaTipo(dataDao.getTipologiaPratica(SharedConstants.RINNOVO_ASSISTENZA_TYPE));		
			if(praticaPraticaTipo.getScerevDPraticaTipo()!=null) dataDao.insertPraticaPraticaTipo(praticaPraticaTipo);
		}

		return pratica;
	}

	private ScerevTPratica prepareScerevTPratica(Payload1 input, ModelCittadinoSistemaEsterno model, ScerevTPratica bozza,
			String citId, String tipologia, ScerevLAudit audit) {
		SyncType sync = null;
		String descComune = null;
		if (model instanceof ModelCittadinoSistemaEsternoAURA) {
			sync = new SyncType(costruisciAggiornamentoProfiloAnagraficoInForSync((ModelCittadinoSistemaEsternoAURA) model,
					input, citId, audit));
			descComune = ((ModelCittadinoSistemaEsternoAURA) model).getDescComune();
			sync.getDatiPrimari().setDescrizioneStatoNascita(((ModelCittadinoSistemaEsternoAURA) model).getDescStatoNascita());
		} else if (model instanceof ModelCittadinoSistemaEsternoTS) {
			sync = new SyncType(costruisciInserimentoProfiloAnagraficoIn((ModelCittadinoSistemaEsternoTS) model, input,
					citId, audit));
		}
		ScerevTPratica pratica;

		if (bozza != null) {
			pratica = bozza;
		} else {
			pratica = new ScerevTPratica();

			if (input.getDelegato() != null) {
				pratica.setCfDelegato(input.getDelegato().getCodiceFiscale());
				pratica.setNomeDelegato(input.getDelegato().getNome());
				pratica.setCognomeDelegato(input.getDelegato().getCognome());
			}

			pratica.setNomeAssistito(sync.getDatiPrimari().getNome());
			pratica.setCognomeAssistito(sync.getDatiPrimari().getCognome());
			pratica.setCfAssistito(sync.getDatiPrimari().getCodiceFiscale());

			// 22/12/2020 se nato all'estero e presente su AURA il comune di nascita non e' valorizzato quindi si passa lo stato di nascita
			pratica.setLuogoNascitaAssistito(sync.getDatiPrimari().getDescrizioneComuneNascita() != null ? 
												sync.getDatiPrimari().getDescrizioneComuneNascita() : 
												sync.getDatiPrimari().getDescrizioneStatoNascita());
			
			pratica.setDataNascitaAssistito(new Timestamp(sync.getDatiPrimari().getDataNascita().toGregorianCalendar().getTimeInMillis()));
			pratica.setSessoAssistito(sync.getDatiPrimari().getSesso());
			pratica.setScerevDCittadinanza(dataDao.getCittadinanzaByCod(input.getCittadinanza().getId()));

			ScerevDNazione nazioneEntity = null;
			if (input.getResidenza() != null && input.getResidenza().getNazione() != null) {
				nazioneEntity = dataDao.getNazione(input.getResidenza().getNazione());
			} else if (sync.getDatiResidenza().getCodiceStatoResidenza() != null) {
				nazioneEntity = dataDao.getNazioneByCod(sync.getDatiResidenza().getCodiceStatoResidenza());
			}

			ScerevTLocalita residenzaLoc = dataDao.findLocalitaByAddress(sync.getDatiResidenza().getCodiceComuneResidenza(),
					sync.getDatiResidenza().getCAPresidenza(), sync.getDatiResidenza().getIndirizzoResidenza(),
					sync.getDatiResidenza().getNumerocivicoResidenza(), nazioneEntity);
			if (residenzaLoc == null)
				residenzaLoc = dataDao.updateLocalitaByAddress(sync.getDatiResidenza().getCodiceComuneResidenza(),
						sync.getDatiResidenza().getDescrizioneComuneResidenza(), sync.getDatiResidenza().getCAPresidenza(),
						sync.getDatiResidenza().getIndirizzoResidenza(), sync.getDatiResidenza().getNumerocivicoResidenza(),
						nazioneEntity);
			pratica.setScerevTLocalita1(residenzaLoc); // residenza

			// 03/11/2020 il domicilio ha nazione fissa ITALIA
			String codNazione = dataDao.getMessaggioPerCodice(SharedConstants.COD_CITTADINANZA_ITALIANA).getValoreMsg();
			ScerevDNazione nazioneDomicilio = dataDao.getNazioneByCod(codNazione);

			ScerevTLocalita domicilioLoc = dataDao.findLocalitaByAddress(
					sync.getDatiDomicilio().getDescrizioneComuneDomicilio(), sync.getDatiDomicilio().getCAPDomicilio(),
					sync.getDatiDomicilio().getIndirizzoDomicilio(), sync.getDatiDomicilio().getNumerocivicoDomicilio(),
					nazioneDomicilio);
			if (domicilioLoc == null)
				domicilioLoc = dataDao.updateLocalitaByAddress(sync.getDatiDomicilio().getCodiceComuneDomicilio(),
						sync.getDatiDomicilio().getDescrizioneComuneDomicilio(),
						sync.getDatiDomicilio().getCAPDomicilio(), sync.getDatiDomicilio().getIndirizzoDomicilio(),
						sync.getDatiDomicilio().getNumerocivicoDomicilio(), nazioneDomicilio);
			pratica.setScerevTLocalita2(domicilioLoc); // domicilio

			pratica.setScerevDAsl1(input.getControlloCombinazione().getAslDomicilio() != null
					&& input.getControlloCombinazione().getAslDomicilio().getId() != null
							? dataDao.getAsl(input.getControlloCombinazione().getAslDomicilio().getId())
							: null);
			String tipoMedico = null;
			try {
				tipoMedico = input.getMedico().getTipologia().getId();
			} catch (Exception e) {
				tipoMedico = SharedConstants.MEDICO;
			}
			ASLDistrettoAmbitoResponse aslDistrettoAmbito = null;
			if (input.getDomicilio() != null && Checker.isValorizzato(input.getDomicilio().getComune())) {
				aslDistrettoAmbito = ServiziEsterniClient.getInstance()
						.getASLDistrettoAmbito(input.getDomicilio().getComune(), tipoMedico, audit);
			} else if (Checker.isValorizzato(descComune)) {
				aslDistrettoAmbito = ServiziEsterniClient.getInstance().getASLDistrettoAmbito(descComune, tipoMedico,
						audit);
			}
			if (aslDistrettoAmbito != null) {
				pratica.setDistrettoDomicilioDichiarato(aslDistrettoAmbito.getDistretto());
				pratica.setAmbitoDomicilioDichiarato(aslDistrettoAmbito.getAmbito());
			}

			pratica.setScerevDAsl2(dataDao.getAsl(input.getControlloCombinazione().getAslIscrizione().getId()));
			pratica.setScerevDAsl3(dataDao.getAsl(input.getMedico().getAsl().getId()));
			pratica.setAslResidenza(input.getControlloCombinazione().getAslResidenza() != null
					&& input.getControlloCombinazione().getAslResidenza().getDescrizione() != null
							? input.getControlloCombinazione().getAslResidenza().getDescrizione()
							: sync.getDatiResidenza().getCodiceAslResidenza());
			if (input.getMedico().getDistretto().getDescrizione() != null
					&& !input.getMedico().getDistretto().getDescrizione().equals(""))
				pratica.setScerevDDistretto(
						dataDao.getDistrettoByDesc(input.getMedico().getDistretto().getDescrizione()));

			// NESSUN AMBITO PER 301 TO
			if (pratica.getScerevDAsl1().getCodiceAzienda() != "301"
					&& input.getMedico().getAmbito().getAmbitoDescrizione() != null
					&& !input.getMedico().getAmbito().getAmbitoDescrizione().contentEquals(""))
				pratica.setScerevDAmbito(dataDao.getAmbitoByDesc(input.getMedico().getAmbito().getAmbitoDescrizione()));

			pratica.setIdAuraMedico(input.getMedico().getId());
			pratica.setNomeMedico(input.getMedico().getNome());
			pratica.setCognomeMedico(input.getMedico().getCognome());
			pratica.setCodiceFiscaleMedico(input.getMedico().getCodiceFiscale());
			pratica.setFlagAutolimitatoMedico(input.getMedico().isAutolimitato());
			pratica.setTipologiaMedico(input.getMedico().getTipologia().getId());

			if (input.getMedicoAltraRegione() != null) {
				pratica.setNomeMedicoPrecedente(input.getMedicoAltraRegione().getNome());
				pratica.setCognomeMedicoPrecedente(input.getMedicoAltraRegione().getCognome());
				pratica.setScerevDRegione(input.getMedicoAltraRegione().getRegione() == null ? null
						: dataDao.getRegionePerNome(input.getMedicoAltraRegione().getRegione().toUpperCase()));
				pratica.setCodiceAslPrecedente(input.getMedicoAltraRegione().getAziendaSanitaria());
			}

			if (input.getDatiAggiuntivi() != null) {
				pratica.setScerevDMotivoSoggiorno(input.getDatiAggiuntivi().getMotivazioneSoggiorno() == null ? null
						: dataDao.getMotivoSoggiorno(input.getDatiAggiuntivi().getMotivazioneSoggiorno().getId()));

				pratica.setScerevDMotivoDomicilio(input.getDatiAggiuntivi().getMotivazioneDomicilio() == null ? null
						: dataDao.getMotivoDomicilio(input.getDatiAggiuntivi().getMotivazioneDomicilio().getId()));

				pratica.setDataFineAssistenzaRichiesta(
						input.getDatiAggiuntivi().getAssitenzaTemporaneaDataFineValidita() == null ? null
								: new Timestamp(input.getDatiAggiuntivi().getAssitenzaTemporaneaDataFineValidita().getTime()));
			}

			pratica.setScerevTInfoSupplementari(getInfoSupplementari(input));
			pratica.setRecapitoTelefonicoAssistito(sync.getDatiResidenza().getTelefonoResidenza());
			pratica.setDataCreazione(new Timestamp(new Date().getTime()));
		}
		pratica.setMedicoGenerico(input.getControlloCombinazione().isDerogaMmg() ? true : null);

		pratica.setTipologiaGestionePratica(tipologia);

		if (tipologia.equalsIgnoreCase("BOZZA")) {
			pratica.setScerevDPraticaStato(dataDao.getStatoPratica(SharedConstants.STATO_BOZZA));
			pratica.setMedicoGenerico(null);

		} else if (tipologia.equalsIgnoreCase("BACK-OFFICE")) {
			pratica.setScerevDPraticaStato(dataDao.getStatoPratica(SharedConstants.STATO_INVIATA));
			pratica.setDerogaPediatra(input.getControlloCombinazione().isDerogaPls() ? true : null);

			if (input.getDatiAggiuntivi() != null)
				pratica.setRicongiungimentoFamiliare(isRicongiungimentoFamiliare(input) ? true : null);
			pratica.setDerogaTerritoriale(input.getControlloCombinazione().isDerogaTerritoriale() ? true : null);
			pratica.setDerogaAssociazione(input.getControlloCombinazione().isDerogaAssociazione() ? true : null);
			// fix matteo - 30/09/2020 - il flag non deve essere mai valorizzato
			if (input.getDatiAggiuntivi() != null)
				pratica.setDerogaMassimale(null);

		} else if (tipologia.equalsIgnoreCase("AUTOMATICA")) {
			pratica.setScerevDPraticaStato(dataDao.getStatoPratica(SharedConstants.STATO_CHIUSA));
			pratica.setNomeMedicoPrecedente(null);
			pratica.setCognomeMedicoPrecedente(null);
			pratica.setScerevDRegione(null);
			pratica.setCodiceAslPrecedente(null);
			pratica.setDataFineAssistenzaRichiesta(null);
			pratica.setDataChiusura(new Timestamp(new Date().getTime()));

		}
		pratica.setDataModifica(new Timestamp(new Date().getTime()));

		return pratica;
	}

	private ScerevTPratica getPraticaBozza(String citId) {
		List<String> statiPratica = new ArrayList<String>();
		try {
			statiPratica.add(StatoPraticaEnum.BOZZA.getKey());
			List<ScerevTPratica> elencoPratiche = dataDao.getPraticaByCFandStato(citId, statiPratica);
			if(elencoPratiche.size() == 0) return null;
			else if(elencoPratiche.size() > 1) {
				ScerevTPratica temp = elencoPratiche.get(0);
				for(ScerevTPratica t : elencoPratiche) {
					if(t.getDataCreazione().after(temp.getDataCreazione())) {
						temp = t;
					}
				}
				return temp;
			} else return elencoPratiche.get(0);
		} catch (Exception e) {
			return null;
		}
	}

	private Boolean isRicongiungimentoFamiliare(Payload1 input) {
		ModelCambioMedicoRichiestaDatiAggiuntivi da = input.getDatiAggiuntivi();
		return (Checker.isValorizzato(da.getRicongiungimentoCodiceFiscale())
				|| Checker.isValorizzato(da.getRicongiungimentoCognome())
				|| Checker.isValorizzato(da.getRicongiungimentoNome())
				|| Checker.isValorizzato(da.getRicongiungimentoParentela()))
				&& (input.getControlloCombinazione().getAslDomicilio().getId()
						.equalsIgnoreCase(input.getControlloCombinazione().getAslResidenza().getId())
						&& input.getControlloCombinazione().getAslResidenza().getId()
								.equalsIgnoreCase(input.getControlloCombinazione().getAslIscrizione().getId()));
	}

	private ScerevTInfoSupplementari getInfoSupplementari(Payload1 input) {
		ScerevTInfoSupplementari info = new ScerevTInfoSupplementari();
		if(input.getDatiAggiuntivi()!=null) {
			info.setTipologiaLavoro(input.getDatiAggiuntivi().getLavoroTipologia() == null ? null : input.getDatiAggiuntivi().getLavoroTipologia().equals(LavoroTipologiaEnum.AUTONOMO));
			info.setProfessione(input.getDatiAggiuntivi().getLavoroProfessione());
			info.setPartitaIva(input.getDatiAggiuntivi().getLavoroPartitaIva());
			info.setNumeroIscrizione(input.getDatiAggiuntivi().getLavoroNumeroIscrizione());
			info.setDittaDatoreLavoro(input.getDatiAggiuntivi().getLavoroAzienda());
			info.setNumeroTelefonoDatore(input.getDatiAggiuntivi().getLavoroAziendaTelefono());
			info.setProvinciaLavoro(input.getDatiAggiuntivi().getLavoroAziendaProvincia());
			info.setComuneLavoro(input.getDatiAggiuntivi().getLavoroAziendaComune());
			info.setIndirizzoLavoro(input.getDatiAggiuntivi().getLavoroAziendaIndirizzo());
			info.setNumeroCivicoLavoro(input.getDatiAggiuntivi().getLavoroAziendaCivico());
			info.setDataFineContratto(input.getDatiAggiuntivi().getLavoroDataFineValiditaContratto() == null ? null : new Timestamp(input.getDatiAggiuntivi().getLavoroDataFineValiditaContratto().getTime()));
			info.setNomeIstituto(input.getDatiAggiuntivi().getStudioNomeIstituto());
			info.setComuneSedeStudio(input.getDatiAggiuntivi().getStudioIstitutoComune());
			info.setProvinciaSedeStudio(input.getDatiAggiuntivi().getStudioIstitutoProvincia());
			info.setIndirizzoSedeStudio(input.getDatiAggiuntivi().getStudioIstitutoIndirizzo());
			info.setCivicoSedeStudio(input.getDatiAggiuntivi().getStudioIstitutoCivico());
			info.setDataFineIscrizione(input.getDatiAggiuntivi().getStudioDataTermineIscrizione() == null ? null : new Timestamp(input.getDatiAggiuntivi().getStudioDataTermineIscrizione().getTime()));
			info.setNomeParente(input.getDatiAggiuntivi().getRicongiungimentoNome());
			info.setCognomeParente(input.getDatiAggiuntivi().getRicongiungimentoCognome());
			info.setCfParente(input.getDatiAggiuntivi().getRicongiungimentoCodiceFiscale());		
			info.setScerevDGradoParentela(input.getDatiAggiuntivi().getRicongiungimentoParentela() == null ? null : dataDao.getGradoParentela(input.getDatiAggiuntivi().getRicongiungimentoParentela()));
			info.setMotivazioneDomanda(input.getDatiAggiuntivi().getMotivazioneDomanda());
		}
		info.setDataCreazione(new Timestamp(new Date().getTime()));
		info.setDataModifica(new Timestamp(new Date().getTime()));
		dataDao.insertScerevTInfoSupplementari(info);
		return info;
	}

	@Override
	public ModelControlloAllegati controlloAllegati(Payload4 payload, ScerevLAudit audit, String citId,
			String shibIdentitaCodiceFiscale) {
		ModelControlloAllegati model = new ModelControlloAllegati();
		ScerevDMotivoSoggiorno motivoSoggiorno = dataDao.getMotivoSoggiornoPerControlloAllegati(payload.getMotivazioneSoggiorno().getId());
		
		boolean checkAsl = payload.getAslResidenza().getId().equalsIgnoreCase(payload.getAslDomicilio().getId()) && 
						   payload.getAslResidenza().getId().equalsIgnoreCase(payload.getAslIscrizione().getId());
		
		model.setAssistenzaTemporaneaStranieri( 
				!(motivoSoggiorno.getTipologiaAssistenza().equalsIgnoreCase(SharedConstants.ASSISTENZA_ILLIMITATA) && checkAsl) );
		
		if(motivoSoggiorno.getScerevRMotivoSoggiornoDocumentoTipos() != null) {
			for(ScerevRMotivoSoggiornoDocumentoTipo r : motivoSoggiorno.getScerevRMotivoSoggiornoDocumentoTipos()) {
				if(r.getScerevDDocumentoTipo().getDataFineValidita() == null || r.getScerevDDocumentoTipo().getDataFineValidita().after(new Date())) {					
					ModelAllegatoTipo a = new ModelAllegatoTipo();
					a.setDescrizione(r.getScerevDDocumentoTipo().getDescrizioneDocumentoTipo());
					a.setTipo(r.getScerevDDocumentoTipo().getCodiceDocumentoTipo());
					a.setStranieri(true);
					model.getAllegati().add(a);
				}
			}
		}

		return model;
	}
}
