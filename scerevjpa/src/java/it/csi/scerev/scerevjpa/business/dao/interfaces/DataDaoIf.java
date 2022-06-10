/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevjpa.business.dao.interfaces;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import it.csi.scerev.scerevjpa.business.entity.ScerevDAmbito;
import it.csi.scerev.scerevjpa.business.entity.ScerevDAsl;
import it.csi.scerev.scerevjpa.business.entity.ScerevDAzione;
import it.csi.scerev.scerevjpa.business.entity.ScerevDCittadinanza;
import it.csi.scerev.scerevjpa.business.entity.ScerevDDistretto;
import it.csi.scerev.scerevjpa.business.entity.ScerevDDocumentoTipo;
import it.csi.scerev.scerevjpa.business.entity.ScerevDGradoParentela;
import it.csi.scerev.scerevjpa.business.entity.ScerevDGruppoUtenti;
import it.csi.scerev.scerevjpa.business.entity.ScerevDMotivazione;
import it.csi.scerev.scerevjpa.business.entity.ScerevDMotivoDomicilio;
import it.csi.scerev.scerevjpa.business.entity.ScerevDMotivoSoggiorno;
import it.csi.scerev.scerevjpa.business.entity.ScerevDMsg;
import it.csi.scerev.scerevjpa.business.entity.ScerevDNazione;
import it.csi.scerev.scerevjpa.business.entity.ScerevDPraticaStato;
import it.csi.scerev.scerevjpa.business.entity.ScerevDPraticaTipo;
import it.csi.scerev.scerevjpa.business.entity.ScerevDRegione;
import it.csi.scerev.scerevjpa.business.entity.ScerevDTipologiaMedico;
import it.csi.scerev.scerevjpa.business.entity.ScerevDTipologiaProfilo;
import it.csi.scerev.scerevjpa.business.entity.ScerevLAudit;
import it.csi.scerev.scerevjpa.business.entity.ScerevLogAura;
import it.csi.scerev.scerevjpa.business.entity.ScerevRPraticaPraticaTipo;
import it.csi.scerev.scerevjpa.business.entity.ScerevTAslOperatore;
import it.csi.scerev.scerevjpa.business.entity.ScerevTCronologia;
import it.csi.scerev.scerevjpa.business.entity.ScerevTDocumento;
import it.csi.scerev.scerevjpa.business.entity.ScerevTFile;
import it.csi.scerev.scerevjpa.business.entity.ScerevTInfoSupplementari;
import it.csi.scerev.scerevjpa.business.entity.ScerevTLocalita;
import it.csi.scerev.scerevjpa.business.entity.ScerevTMonitoraggio;
import it.csi.scerev.scerevjpa.business.entity.ScerevTPratica;

public interface DataDaoIf {

	ScerevDAmbito getAmbitoByDesc(String desc);
	
	ScerevDRegione getRegionePerCodice(String codRegione);

	ScerevDNazione getNazioneByCod(String codNzione);

	ScerevDRegione getRegionePerNome(String regione);

	ScerevDMsg getMessaggioPerCodice(String codMessaggio);

	void insertAudit(ScerevLAudit audit);

	boolean updateAudit(ScerevLAudit audit);

	ScerevLAudit findAudit(Long idAudit);

	List<ScerevLAudit> findAllAudit();

	ScerevTPratica getPraticaPerNumero(int numPratica);

	List<ScerevTDocumento> getDocumentoByPratica(Integer pkPratica_Selezionata);

	List<ScerevDCittadinanza> getListaCittadinanze();

	ScerevDCittadinanza getCittadinanzaByCod(String codCittadinanza);

	List<ScerevDMotivoSoggiorno> getListaMotiviSoggiorno();

	ScerevDMotivoSoggiorno getMotivoSoggiorno(String id);

	List<ScerevDMotivoDomicilio> getListaMotiviDomicilio();

	ScerevDMotivoDomicilio getMotivoDomicilio(String id);

	List<ScerevDTipologiaMedico> getListaTipologieMedico();

	List<ScerevTCronologia> getCronologiaPratica(Integer idPraticaScelta);

	List<ScerevTPratica> getListaPratiche(HashMap<String, Object> ricerca, boolean countBool);

	ScerevDAmbito getAmbito(int codAmbito);

	List<ScerevDAmbito> getAmbitoByCod(String codAmbito);

	List<ScerevDTipologiaProfilo> getListaTipologieProfilo();

	List<ScerevDPraticaTipo> getListaTipologiePratica();

	List<ScerevDPraticaStato> getListaStatoPratica(Integer tab);

	List<ScerevDPraticaStato> getStatiPratica();

	ScerevDPraticaStato getStatoPratica(String desc);

	ScerevDAsl getAsl(String codAsl);

	List<ScerevDAsl> getAllAsl();

	ScerevDPraticaTipo getTipologiaPratica(String codicePraticaTipo);
	
	ScerevDPraticaTipo getTipologiaPraticaByCodice(String codicePraticaTipo);

	ScerevTFile getFileByPkFile_Selezionato(Integer pkFile_Selezionato);

	ScerevTPratica updatePratica(ScerevTPratica pratica);

	List<ScerevDMotivazione> getAllMotivazioni();

	void insertCronologia(ScerevTCronologia cronologia);

	ScerevTPratica getPraticaCambioMedicoAssistenzaTemporanea(String codFiscale, String idASL, String idMedico,
			Date dataIllimitata);

	List<ScerevDAzione> getAzioniByGroupDesc(String role);

	List<ScerevDAzione> getAzioni();

	List<ScerevDGruppoUtenti> getGruppiUtenti();

	List<ScerevTPratica> getPraticaPerCFeStato(String cf, List<String> statiPratica);

	ScerevTPratica getUltimaPraticaAssistenzaRevocataCitt(String citId);

	void insertFile(ScerevTFile file);

	ScerevTDocumento updateDocumento(ScerevTDocumento documento);

	void insertScerevTDocumento(ScerevTDocumento std);

	ScerevTFile updateFile(ScerevTFile fileOld);

	List<ScerevDDocumentoTipo> getListScerevDDocumentoTipo();

	ScerevTPratica getPraticaForRevoca(String citId, String idAuraMedico);

	void updateDistretti(List<ScerevDDistretto> listaDistretti);

	void updateAmbiti(List<ScerevDAmbito> listaAmbiti);

	List<ScerevDDistretto> getDistretto(String idDistretto);

	ScerevDDistretto getDistrettoByDesc(String descDistretto);

	ScerevDNazione getNazione(String codNazione);

	ScerevTLocalita findLocalitaByAddress(String comune, String cap, String indirizzo, String civico,
			ScerevDNazione nazioneEntity);

	ScerevTLocalita updateLocalitaByAddress(String codComune, String comune, String cap, String indirizzo,
			String civico, ScerevDNazione nazioneEntity);

	ScerevTInfoSupplementari getInfoSupplementari(Integer pkFile_Selezionato);

	ScerevDGradoParentela getGradoParentela(String valore);

	void updateDistretto(ScerevDDistretto distretto);

	void updateAmbito(ScerevDAmbito ambito);

	void insertPraticaPraticaTipo(ScerevRPraticaPraticaTipo praticaPraticaTipo);

	ScerevDAsl getAslByDenominazione(String azienda);

	String getAllPraticheCount();

	List<ScerevTPratica> getPraticaPerCF(String citId);

	ScerevTPratica getPraticaForRevocaNuovaRichiesta(String codiceFiscaleAssistito, Integer numeroPratica);

	void insertScerevTInfoSupplementari(ScerevTInfoSupplementari info);

	List<ScerevDAsl> getAllAslDistrettoAmbito();

	List<ScerevDPraticaTipo> getTipologieRichiesta(Integer pkPratica);

	List<ScerevTPratica> getPraticaByCFandStato(String codFiscale, List<String> listaCodici);

	void saveDistretto(ScerevDDistretto distretto);

	ScerevDDistretto getDistrettoByPk(Integer pkDistretto);

	void saveAmbito(ScerevDAmbito ambito);

	ScerevDMotivoSoggiorno getMotivoSoggiornoPerControlloAllegati(String id);

	List<ScerevTAslOperatore> getAslOperatore(String codFisc);
	
	List<ScerevTMonitoraggio> getMediciMonitoratiByCittadino(String citId);
	
	void insertScerevTMonitoraggio(ScerevTMonitoraggio scerevTMonitoraggio);
	
	List<ScerevTMonitoraggio> getMediciMonitoratiByCittadino(String shibId,String idAura);

	void saveMonitoraggio(ScerevTMonitoraggio med);

	ScerevTFile getAllegatoByIdAndCf(String citId, String allegatoId);

	ScerevLogAura insertUpdateLogAura(ScerevLogAura logAura);

}
