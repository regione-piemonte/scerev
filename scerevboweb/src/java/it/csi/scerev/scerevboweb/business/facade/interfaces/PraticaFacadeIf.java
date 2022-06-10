/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevboweb.business.facade.interfaces;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import it.csi.def.opessanws.opessan.InfoRapportoDiLavoro;
import it.csi.def.opessanws.opessan.SoggettoOpessanBody;
import it.csi.scerev.scerevboweb.dto.AccettaRichiesta;
import it.csi.scerev.scerevboweb.dto.Cittadino;
import it.csi.scerev.scerevboweb.dto.DettaglioOutput;
import it.csi.scerev.scerevboweb.dto.ModelCronologia;
import it.csi.scerev.scerevboweb.dto.Motivazione;
import it.csi.scerev.scerevboweb.dto.NotaInterna;
import it.csi.scerev.scerevboweb.dto.Ricerca;
import it.csi.scerev.scerevboweb.dto.RicercaRichiesteInput;
import it.csi.scerev.scerevboweb.dto.RicercaRichiesteOutput;
import it.csi.scerev.scerevboweb.dto.StatoPratica;
import it.csi.scerev.scerevboweb.dto.TipologiaPratica;
import it.csi.scerev.scerevboweb.dto.TipologiaProfilo;
import it.csi.scerev.scerevboweb.dto.UpdatePratica;
import it.csi.scerev.scerevboweb.dto.UserInfo;
import it.csi.scerev.scerevboweb.dto.datatables.DataTableRequest;
import it.csi.scerev.scerevjpa.business.entity.ScerevDTipologiaProfilo;
import it.csi.scerev.scerevjpa.business.entity.ScerevLAudit;
import it.csi.scerev.scerevjpa.business.entity.ScerevTDocumento;
import it.csi.scerev.scerevjpa.business.entity.ScerevTFile;
import it.csi.scerev.scerevjpa.business.entity.ScerevTPratica;
import it.csi.scerev.scerevserviziesterni.aura.find.DatiAnagrafici;
import it.csi.scerev.scerevserviziesterni.aura.get.SoggettoAuraBodyNew;
import it.csi.scerev.scerevserviziesterni.aura.getnumeroassistitiincaricomedico.NumeroAssistitiInCaricoMedicoResponse;
import it.csi.scerev.scerevserviziesterni.ensemble.interrogamefscerev.InterrogaMefScerevResult;
import it.csi.scerev.scerevutil.business.exception.IntegritaException;
import it.csi.scerev.scerevutil.business.exception.ParametriObbligatoriException;

public interface PraticaFacadeIf {		
	public RicercaRichiesteOutput getListaPratiche(RicercaRichiesteInput input, UserInfo userInfo);
	public void checkDataTables(DataTableRequest dataTableRequest) throws IntegritaException;
	public void checkRicercaParameters(Ricerca ricerca) throws ParametriObbligatoriException, IntegritaException;
	public List<ModelCronologia> getStoricoPratica(Integer pk_praticaSelezionata); 
	public ArrayList<TipologiaPratica> getTipologiePratica();
	public ArrayList<StatoPratica> getStatoPratica(int tab);
	public ScerevTPratica getPratica(int numPratica);
	public DettaglioOutput dettaglioPraticaAssembly(DatiAnagrafici cittadino, SoggettoAuraBodyNew cittadinoExtra, ScerevTPratica pratica, SoggettoOpessanBody operatoreMedico, NumeroAssistitiInCaricoMedicoResponse numeroAssistiti, SoggettoAuraBodyNew parenteExtra, String erroreParente);
	public List<ScerevTDocumento> getDocumentoByPratica(Integer pkPratica_Selezionata);
	public ScerevTFile getFileByPkFile(Integer pkFile_Selezionato);
	public byte[] getReportPratica(Map<String, Object> parameters);
	public InfoRapportoDiLavoro getRapportoDiLavoro(List<InfoRapportoDiLavoro> infoList);
	public void checkRespingeParameters(UpdatePratica pratica)  throws ParametriObbligatoriException, IntegritaException;
	public void checkRichiestaRevisioneParameters(UpdatePratica pratica) throws ParametriObbligatoriException, IntegritaException;
	public ScerevTPratica respingeRichiesta(UpdatePratica pratica, UserInfo userInfo);
	public ScerevTPratica richiedeIntegrazione(UpdatePratica pratica, UserInfo userInfo);
	public List<Motivazione> getAllMotivazioni();
	public Map<String, Object> getReportParameters(ScerevTPratica praticaAssociata, DettaglioOutput dettagliPratica);
	public void checkAccettaParameters(AccettaRichiesta richiesta) throws IntegritaException, ParametriObbligatoriException;
	public void checkAccettaRichiestaDtInit(AccettaRichiesta richiesta, Cittadino cittadino) throws IntegritaException;
	public List<ScerevDTipologiaProfilo> getTipologiaProfiloList();
	public List<TipologiaProfilo> getTipologiaProfiloListDTO(ScerevTPratica pratica);
	//public void updateOnAura(AccettaRichiesta richiesta, DatiAnagrafici cittadinoAura, SoggettoAuraBodyNew cittadinoAuraExtra, FakeCittadinoMefScerev cittadinoScerev) throws Exception;
	public void updateOnAura(AccettaRichiesta richiesta, DatiAnagrafici cittadinoAura, SoggettoAuraBodyNew cittadinoAuraExtra, InterrogaMefScerevResult cittadinoScerev, String shib, ScerevLAudit audit) throws Exception;
	public void checkNotaInternaParameters(NotaInterna nota) throws ParametriObbligatoriException;
	public void aggiungiCronologiaNotaInterna(NotaInterna nota, UserInfo userInfo);
	public ScerevTPratica accettaRichiesta(AccettaRichiesta richiesta, UserInfo userInfo);
}
