/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevjpa.business.dao.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import it.csi.scerev.scerevjpa.business.dao.interfaces.DataDaoIf;
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
import it.csi.scerev.scerevjpa.business.entity.ScerevRAzioniGruppoUtenti;
import it.csi.scerev.scerevjpa.business.entity.ScerevRPraticaPraticaTipo;
import it.csi.scerev.scerevjpa.business.entity.ScerevTAslOperatore;
import it.csi.scerev.scerevjpa.business.entity.ScerevTCronologia;
import it.csi.scerev.scerevjpa.business.entity.ScerevTDocumento;
import it.csi.scerev.scerevjpa.business.entity.ScerevTFile;
import it.csi.scerev.scerevjpa.business.entity.ScerevTInfoSupplementari;
import it.csi.scerev.scerevjpa.business.entity.ScerevTLocalita;
import it.csi.scerev.scerevjpa.business.entity.ScerevTMonitoraggio;
import it.csi.scerev.scerevjpa.business.entity.ScerevTPratica;


public class DataDaoImpl implements DataDaoIf  {

	@PersistenceContext
	private EntityManager em;

	public ScerevDRegione getRegionePerCodice(String codRegione) {
		TypedQuery<ScerevDRegione> query = em.createNamedQuery("ScerevDRegione.findRegionePerCodice",ScerevDRegione.class);
		query.setParameter("codRegione", codRegione);
		return query.getSingleResult(); // se dovessero esserci problemi di Exception per casi di null, sostituire con return getSingleResult(query)
	}
	
	public ScerevDRegione getRegionePerNome(String nomeRegione) {
		TypedQuery<ScerevDRegione> query = em.createNamedQuery("ScerevDRegione.findRegionePerNome",ScerevDRegione.class);
		query.setParameter("nomeReg", nomeRegione);
		return query.getSingleResult(); // se dovessero esserci problemi di Exception per casi di null, sostituire con return getSingleResult(query)
	}

	public ScerevDMsg getMessaggioPerCodice(String codMessaggio) {
		TypedQuery<ScerevDMsg> query = em.createNamedQuery("ScerevDMsg.findMessaggioPerCodice",ScerevDMsg.class);
		query.setParameter("codMsg", codMessaggio);
		return query.getSingleResult(); // se dovessero esserci problemi di Exception per casi di null, sostituire con return getSingleResult(query)
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void insertAudit(ScerevLAudit audit) {
		audit.setDataOra(new Timestamp(new Date().getTime()));
		em.persist(audit);
	}

	@Transactional
	public boolean updateAudit(ScerevLAudit audit) {
		try{
			em.merge(audit);
			return true;
		}
		catch (Exception e) {	
			e.printStackTrace();
		}
		return false;
	}

	public ScerevLAudit findAudit(Long idAudit) {
		TypedQuery<ScerevLAudit> query = em.createNamedQuery("ScerevLAudit.findAudit",ScerevLAudit.class);
		query.setParameter("auditId", idAudit);
		return query.getSingleResult(); // se dovessero esserci problemi di Exception per casi di null, sostituire con return getSingleResult(query)
	}

	public List<ScerevLAudit> findAllAudit() {
		TypedQuery<ScerevLAudit> query = em.createNamedQuery("ScerevLAudit.findAll", ScerevLAudit.class);
		return query.getResultList();
	}
	
	public ScerevTPratica getPraticaPerNumero(int numeroPratica) {
		String hqlQuery = "SELECT s FROM ScerevTPratica s "
				+ "left join fetch s.scerevTCronologias c "
				+ "left join fetch s.scerevRPraticaPraticaTipos r "
				+ "left join fetch s.scerevTDocumentos d "
				+ "where s.numeroPratica = :numeroPratica";		
		TypedQuery<ScerevTPratica> query = em.createQuery(hqlQuery, ScerevTPratica.class);
		query.setParameter("numeroPratica", numeroPratica);
		return getSingleResult(query); // se dovessero esserci problemi di Exception per casi di null, sostituire con return getSingleResult(query)
	}
	
	public List<ScerevDCittadinanza> getListaCittadinanze() {
		TypedQuery<ScerevDCittadinanza> query = em.createNamedQuery("ScerevDCittadinanza.findAllAttiveOggi", ScerevDCittadinanza.class);
		return query.getResultList();
	}

	public List<ScerevDMotivoSoggiorno> getListaMotiviSoggiorno() {
		TypedQuery<ScerevDMotivoSoggiorno> query = em.createNamedQuery("ScerevDMotivoSoggiorno.findAllAttiviOggi", ScerevDMotivoSoggiorno.class);
		return query.getResultList();
	}	
	
	public ScerevDMotivoSoggiorno getMotivoSoggiorno(String id){
		TypedQuery<ScerevDMotivoSoggiorno> query = em.createNamedQuery("ScerevDMotivoSoggiorno.findById", ScerevDMotivoSoggiorno.class);
		query.setParameter("id", id);
		return getSingleResult(query);
	}	
	
	public ScerevDMotivoDomicilio getMotivoDomicilio(String id){
		TypedQuery<ScerevDMotivoDomicilio> query = em.createNamedQuery("ScerevDMotivoDomicilio.findById", ScerevDMotivoDomicilio.class);
		query.setParameter("id", id);
		return getSingleResult(query);
	}	

	@Override
	public List<ScerevDMotivoDomicilio> getListaMotiviDomicilio() {
		TypedQuery<ScerevDMotivoDomicilio> query = em.createNamedQuery("ScerevDMotivoDomicilio.findAllAttiviOggi", ScerevDMotivoDomicilio.class);
		return query.getResultList();
	}
	
	public List<ScerevDTipologiaMedico> getListaTipologieMedico() {
		TypedQuery<ScerevDTipologiaMedico> query = em.createNamedQuery("ScerevDTipologiaMedico.findAllValide", ScerevDTipologiaMedico.class);
		return query.getResultList();
	}
	
	public List<ScerevDTipologiaProfilo> getListaTipologieProfilo() {
		TypedQuery<ScerevDTipologiaProfilo> query = em.createNamedQuery("ScerevDTipologiaProfilo.findAll", ScerevDTipologiaProfilo.class);
		return query.getResultList();
	}
	
	public List<ScerevTCronologia> getCronologiaPratica(Integer idPraticaScelta) {
		TypedQuery<ScerevTCronologia> query = em.createNamedQuery("ScerevTCronologia.findAll_di_una_pratica", ScerevTCronologia.class);
		query.setParameter("idPraticaScelta", idPraticaScelta);
		return query.getResultList();
	}
	
	public List<ScerevTPratica> getListaPratiche(HashMap<String, Object> ricerca, boolean countBool) {
		try {
			// Compongo la query HQL
			String hqlQuery = "SELECT DISTINCT s FROM ScerevTPratica s "
					+ "left join fetch s.scerevRPraticaPraticaTipos t "
					+ "left join fetch s.scerevDPraticaStato d "
					+ "left join fetch s.scerevDAsl2 a "
					+ "left join fetch s.scerevDDistretto dist "
					+ "left join fetch s.scerevDAmbito amb where ( ";
			
			// Count per mappare i parametri nella query
			int count = 0;
			
			// Aggiunta filtro stato IN
			String statoStr = (String) ricerca.get("scerevDPraticaStato.codicePraticaStato");
			if(statoStr!=null) {
				hqlQuery += "d.codicePraticaStato in (";
				if(statoStr.contains(";")) {
					String[] statoArray = statoStr.split(";");
					for(int i=0; i<statoArray.length; i++) {
						hqlQuery +="'"+statoArray[i]+"'";
						if(i!=statoArray.length-1) hqlQuery += ",";
					}
				} else {
					hqlQuery +="'"+statoStr+"'";
				}
				hqlQuery += ") and ";
			}
			
			// Aggiunta filtro ASL IN (per operatore)
			String aslStr = (String) ricerca.get("utenteOperatore");
			if(aslStr!=null) {
				hqlQuery += "s.scerevDAsl2.codiceAzienda in (";
				if(aslStr.contains(";")) {
					String[] aslArray = aslStr.split(";");
					for(int i=0; i<aslArray.length; i++) {
						hqlQuery +="'"+aslArray[i]+"'";
						if(i!=aslArray.length-1) hqlQuery += ",";
					}
				} else {
					hqlQuery +="'"+aslStr+"'";
				}
				hqlQuery += ") and ";
			}
			
			// Aggiunta dei filtri di ricerca nella stringa HQL
			for (String key : ricerca.keySet()) {
				if(!key.equalsIgnoreCase("limit") && !key.equalsIgnoreCase("order") && !key.equalsIgnoreCase("orderBy") && !key.equalsIgnoreCase("start")
						&& !key.equalsIgnoreCase("scerevDPraticaStato.codicePraticaStato") && !key.equalsIgnoreCase("utenteOperatore")) {
					if(key.equalsIgnoreCase("scerevDPraticaTipo.codicePraticaTipo")) {
						hqlQuery += "UPPER(t." + key + ") =: valore" + (count++) + " and ";
					} else {
						if( ricerca.get(key) instanceof String )	hqlQuery += "UPPER(s." + key + ") =: valore" + (count++) + " and ";
						else	hqlQuery += "s." + key + "=: valore" + (count++) + " and ";
					}
				}
			}
						
			hqlQuery += "1 = 1 ) ";
			
			// Compongo l'ordinamento
			hqlQuery += "ORDER BY ";
			String[] orderBySplitted = ((String) ricerca.get("orderBy")).split(";");
			String[] orderSplitted = ((String) ricerca.get("order")).split(";");
			for(int i = 0; i<orderBySplitted.length; i++) {
				if(orderBySplitted[i].equalsIgnoreCase("scerevDPraticaStato.codicePraticaStato")) {
					orderBySplitted[i] = "scerevDPraticaStato.valorePraticaStato";
				}
				if(orderBySplitted[i].equalsIgnoreCase("tipologiaMedico")) {
					orderBySplitted[i] = "nomeMedico";
				}
				if(orderBySplitted[i].equalsIgnoreCase("scerevDAsl2.codiceAzienda")) {
					orderBySplitted[i] = "aslResidenza";
				}
				if(orderBySplitted[i].equalsIgnoreCase("scerevDDistretto.codiceDistretto")) {
					orderBySplitted[i] = "scerevDDistretto.denominazioneDistretto";
				}
//				if(orderBySplitted[i].equalsIgnoreCase("nomeOperatore")) {
//					hqlQuery += "s."+orderBySplitted[i]+" "+orderSplitted[i]+", ";
//					orderBySplitted[i] = "cognomeOperatore";
//				}
				if(orderBySplitted[i].isEmpty()) {
					orderBySplitted[i] = "scerevDAmbito.denominazioneAmbito";
				}
				hqlQuery += "s."+orderBySplitted[i]+" "+orderSplitted[i];
				if(i!=orderBySplitted.length-1) hqlQuery += ", ";
			}
			
			System.out.println(hqlQuery);
			
			TypedQuery<ScerevTPratica> query;
			if(countBool) query = em.createQuery(hqlQuery, ScerevTPratica.class);
			else { 
				query = em.createQuery(hqlQuery, ScerevTPratica.class).setFirstResult((int)ricerca.get("start")); //.setMaxResults((int)ricerca.get("limit"));
				if((int)ricerca.get("limit") > 0) query.setMaxResults((int)ricerca.get("limit"));
			}
			
						
			count = 0;
			
			// Assegno i valori alle corrispettive chiavi
			for (String key : ricerca.keySet()) {
				if(!key.equalsIgnoreCase("limit") && !key.equalsIgnoreCase("order") && !key.equalsIgnoreCase("orderBy") && !key.equalsIgnoreCase("start") 
						&& !key.equalsIgnoreCase("scerevDPraticaStato.codicePraticaStato") && !key.equalsIgnoreCase("utenteOperatore")) {
					if (ricerca.get(key) instanceof Integer)
						query.setParameter("valore" + (count++), (Integer) ricerca.get(key));
					if (ricerca.get(key) instanceof Boolean)
						query.setParameter("valore" + (count++), (Boolean) ricerca.get(key));
					if (ricerca.get(key) instanceof String) 
						query.setParameter("valore" + (count++), ((String) ricerca.get(key)).toUpperCase());
					if (ricerca.get(key) instanceof Date) 
						query.setParameter("valore" + (count++), (Date) ricerca.get(key));				
				}
			}

			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ScerevDAmbito getAmbito(int id) {
		TypedQuery<ScerevDAmbito> query = em.createNamedQuery("ScerevDAmbito.findById", ScerevDAmbito.class);
		query.setParameter("id", id);
		return getSingleResult(query);
	}
	
	public ScerevDAmbito getAmbitoByDesc(String desc) {
		TypedQuery<ScerevDAmbito> query = em.createNamedQuery("ScerevDAmbito.findByDesc", ScerevDAmbito.class);
		query.setParameter("desc", desc);
		return getSingleResult(query);
	}
	
	public List<ScerevDAmbito> getAmbitoByCod(String codAmbito) {
		TypedQuery<ScerevDAmbito> query = em.createNamedQuery("ScerevDAmbito.find", ScerevDAmbito.class);
		query.setParameter("codAmbito", codAmbito);
		return query.getResultList();
	}
	
	public List<ScerevDPraticaTipo> getListaTipologiePratica() {
		TypedQuery<ScerevDPraticaTipo> query = em.createNamedQuery("ScerevDPraticaTipo.findAllValide", ScerevDPraticaTipo.class);
		return query.getResultList();
	}
	
	public List<ScerevDPraticaStato> getListaStatoPratica(Integer tab) {
		if (tab == 0) {
			TypedQuery<ScerevDPraticaStato> query = em.createNamedQuery("ScerevDPraticaStato.findAllValide", ScerevDPraticaStato.class);
			return query.getResultList();
		} else {
			TypedQuery<ScerevDPraticaStato> query = em.createNamedQuery("ScerevDPraticaStato.findAllValideArchivio", ScerevDPraticaStato.class);
			return query.getResultList();
		}
	}
	
	public List<ScerevDPraticaStato> getStatiPratica() {
		TypedQuery<ScerevDPraticaStato> query = em.createNamedQuery("ScerevDPraticaStato.findAll",	ScerevDPraticaStato.class);
		return query.getResultList();

	}
	
	public ScerevDAsl getAsl(String codAsl) {
		if(codAsl != null) {
			if(codAsl.length()==6) codAsl = codAsl.replace("010", "");
			
			TypedQuery<ScerevDAsl> query = em.createNamedQuery("ScerevDAsl.find", ScerevDAsl.class);
			query.setParameter("codiceAzienda", codAsl);
			return getSingleResult(query);
		} else return null;
	}
	
	public List<ScerevDAsl> getAllAsl() {
		TypedQuery<ScerevDAsl> query = em.createNamedQuery("ScerevDAsl.findAll", ScerevDAsl.class);
		return query.getResultList();
	}
	
	public ScerevDPraticaTipo getTipologiaPratica(String codicePraticaTipo) {
		TypedQuery<ScerevDPraticaTipo> query = em.createNamedQuery("ScerevDPraticaTipo.find", ScerevDPraticaTipo.class);
		query.setParameter("valorePratica", codicePraticaTipo);
		return getSingleResult(query); // se dovessero esserci problemi di Exception per casi di null, sostituire con return getSingleResult(query)
	}
	
	public ScerevDPraticaTipo getTipologiaPraticaByCodice(String codicePraticaTipo) {
		TypedQuery<ScerevDPraticaTipo> query = em.createNamedQuery("ScerevDPraticaTipo.findByCodice", ScerevDPraticaTipo.class);
		query.setParameter("codicePratica", codicePraticaTipo);
		return getSingleResult(query); // se dovessero esserci problemi di Exception per casi di null, sostituire con return getSingleResult(query)
	}
	
	public List<ScerevTDocumento> getDocumentoByPratica(Integer pkPratica_Selezionata) {
		TypedQuery<ScerevTDocumento> query = em.createNamedQuery("ScerevTDocumento.findByPratica", ScerevTDocumento.class);
		query.setParameter("pkPratica_Selezionata", pkPratica_Selezionata);
		return query.getResultList();
	}
	
	public ScerevTFile getFileByPkFile_Selezionato(Integer pkFile_Selezionato) {
		TypedQuery<ScerevTFile> query = em.createNamedQuery("ScerevTFile.findByPkFile", ScerevTFile.class);
		query.setParameter("pkFile_Selezionato", pkFile_Selezionato);
		return query.getSingleResult(); // se dovessero esserci problemi di Exception per casi di null, sostituire con return getSingleResult(query)
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public ScerevTPratica updatePratica(ScerevTPratica pratica) {	 	
		if(pratica.getNumeroPratica() == null) {
			pratica.setNumeroPratica(99999999); // TODO TOGLI ASSOLUTAMENTE NON APPENA SARà PRONTA LA SEQUENCE		
			pratica = em.merge(pratica);
			pratica.setNumeroPratica(pratica.getPkPratica());	//
		}
		pratica = em.merge(pratica);
		em.flush();
		return pratica;
	}
	
	public List<ScerevDMotivazione> getAllMotivazioni() {
		TypedQuery<ScerevDMotivazione> query = em.createNamedQuery("ScerevDMotivazione.findAll", ScerevDMotivazione.class);
		return query.getResultList();
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void insertCronologia(ScerevTCronologia cronologia) {
		em.persist(cronologia);
	}

	/**
	 * Ricerca pratica per cambio del medico all'interno di un'assistenza 
	 * temporanea ga' in corso all'interno della stessa ASL
	 */
	public ScerevTPratica getPraticaCambioMedicoAssistenzaTemporanea(String codFiscale, String idASL, String idMedico, Date dataIllimitata) {
		TypedQuery<ScerevTPratica> query = em.createNamedQuery("ScerevTPratica.findCambioMedicoAssistenzaTemporanea",ScerevTPratica.class);
		query.setParameter("codFiscale", codFiscale);
		query.setParameter("idASL", idASL);
		query.setParameter("idMedico", idMedico);
        query.setParameter("dataIllimitata", dataIllimitata);
		return getSingleResult(query);
	}
	
	public List<ScerevDAzione> getAzioniByGroupDesc(String role) {
		String hqlQuery = "SELECT s.scerevDAzione "
				+ "FROM ScerevRAzioniGruppoUtenti s "
				+ "WHERE s.scerevDGruppoUtenti.descGruppoUtenti = :role";
		TypedQuery<ScerevDAzione> query = em.createQuery(hqlQuery, ScerevDAzione.class);
		query.setParameter("role", role);
		return query.getResultList();
	}
	
	public List<ScerevDAzione> getAzioni() {
		TypedQuery<ScerevDAzione> query = em.createNamedQuery("ScerevDAzione.findAll", ScerevDAzione.class);
		return query.getResultList();
	}
	
	public List<ScerevRAzioniGruppoUtenti> getAzioniGruppoUtenti() {
		TypedQuery<ScerevRAzioniGruppoUtenti> query = em.createNamedQuery("ScerevRAzioniGruppoUtenti.findAll", ScerevRAzioniGruppoUtenti.class);
		return query.getResultList();
	}
	
	public List<ScerevDGruppoUtenti> getGruppiUtenti() {
		TypedQuery<ScerevDGruppoUtenti> query = em.createNamedQuery("ScerevDGruppoUtenti.findAll", ScerevDGruppoUtenti.class);
		return query.getResultList();
	}
	
	/**
	 * la query potrebbe restituire più pratiche ma ne stiamo considerando solo una. 
	 */
	@Deprecated
	public List<ScerevTPratica> getPraticaPerCFeStato(String codFiscale, List<String> listaCodici) {
//		TypedQuery<ScerevTPratica> query = em.createNamedQuery("ScerevTPratica.findPraticaPerCFeStato",ScerevTPratica.class);
		String hqlQuery = "SELECT s FROM ScerevTPratica s where s.cfAssistito = :cfAssistito";
		if (listaCodici != null && listaCodici.size() > 0) {
			hqlQuery += " and s.scerevDPraticaStato.codicePraticaStato in ( ";
			for (Iterator<String> iterator = listaCodici.iterator(); iterator.hasNext();) {
				String code = (String) iterator.next();
				hqlQuery += "'"+code+"'";
				if (iterator.hasNext())  hqlQuery += ",";
				else  hqlQuery += ")";
			}
		}
		TypedQuery<ScerevTPratica> query = em.createQuery(hqlQuery, ScerevTPratica.class);
		query.setParameter("cfAssistito", codFiscale);
		return query.getResultList();
	}
	
	public static  <T> T getSingleResult(TypedQuery<T> query) {
		List<T> results = query.getResultList();
		if (!results.isEmpty())
			return (T) results.get(0);
		else
			return null;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void insertFile(ScerevTFile file) {
		em.persist(file);
	}

	@Transactional
	public ScerevTDocumento updateDocumento(ScerevTDocumento documento) {
		return em.merge(documento);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void insertScerevTDocumento(ScerevTDocumento std) {
		em.persist(std);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public ScerevTFile updateFile(ScerevTFile file) {
		file = em.merge(file);
		em.flush();
		return file;
	}

	public List<ScerevDDocumentoTipo> getListScerevDDocumentoTipo() {
		TypedQuery<ScerevDDocumentoTipo> query = em.createNamedQuery("ScerevDDocumentoTipo.findAll", ScerevDDocumentoTipo.class);
		return query.getResultList();
	}
	
	public ScerevTPratica getPraticaForRevoca(String citId, String idAuraMedico) {
			TypedQuery<ScerevTPratica> query = em.createNamedQuery("ScerevTPratica.findPerRevoca",ScerevTPratica.class);
			query.setParameter("citId", citId);
			query.setParameter("idAuraMedico", idAuraMedico);
			return getSingleResult(query);
	}
	
	public ScerevTPratica getUltimaPraticaAssistenzaRevocataCitt(String citId) {
		TypedQuery<ScerevTPratica> query = em.createNamedQuery("ScerevTPratica.findPraticaPerCFeDataRevocaAssistenza",ScerevTPratica.class);
		query.setParameter("citId", citId);
		return getSingleResult(query);
	}
		
	@Transactional
	public void updateDistretti(List<ScerevDDistretto> listaDistretti) {
		em.merge(listaDistretti);		
	}
	
	@Transactional
	public void updateAmbiti(List<ScerevDAmbito> listaAmbiti) {
		em.merge(listaAmbiti);				
	}

	public List<ScerevDDistretto> getDistretto(String idDistretto) {
		TypedQuery<ScerevDDistretto> query = em.createNamedQuery("ScerevDDistretto.find",ScerevDDistretto.class);
		query.setParameter("codDistretto", idDistretto);
		return query.getResultList();		
	}
	
	public ScerevDDistretto getDistrettoByPk(Integer pkDistretto) {
		TypedQuery<ScerevDDistretto> query = em.createNamedQuery("ScerevDDistretto.findByPk",ScerevDDistretto.class);
		query.setParameter("pk_distretto", pkDistretto);
		return getSingleResult(query);	
	}
	
	public ScerevDDistretto getDistrettoByDesc(String descDistretto) {
		TypedQuery<ScerevDDistretto> query = em.createNamedQuery("ScerevDDistretto.findByDesc",ScerevDDistretto.class);
		query.setParameter("denominazioneDistretto", descDistretto);
		return getSingleResult(query);		
	}

	public ScerevDNazione getNazione(String nomeNazione) {
		TypedQuery<ScerevDNazione> query = em.createNamedQuery("ScerevDNazione.findNazionePerNome",ScerevDNazione.class);
		query.setParameter("nomeNazione", nomeNazione.toUpperCase());
		return getSingleResult(query);
	}
	
	public ScerevDNazione getNazioneByCod(String codNzione) {
		TypedQuery<ScerevDNazione> query = em.createNamedQuery("ScerevDNazione.findNazionePerCod",ScerevDNazione.class);
		query.setParameter("cod", codNzione);
		return getSingleResult(query);
	}

	public ScerevDCittadinanza getCittadinanzaByCod(String codCittadinanza) {		
		TypedQuery<ScerevDCittadinanza> query = em.createNamedQuery("ScerevDCittadinanza.findCittadinanzaByCodice",ScerevDCittadinanza.class);
		query.setParameter("codCittadinanza", codCittadinanza);
		return getSingleResult(query);
	}

	public ScerevTLocalita findLocalitaByAddress(String comune, String cap, String indirizzo, String civico, ScerevDNazione nazioneEntity) {
		TypedQuery<ScerevTLocalita> query = em.createNamedQuery("ScerevTLocalita.find",ScerevTLocalita.class);		
		query.setParameter("nazione", nazioneEntity);
		query.setParameter("comune", comune);
		query.setParameter("cap", cap);
		query.setParameter("indirizzo", indirizzo);
		query.setParameter("civico", civico);
		return getSingleResult(query);
	}
	
	@Transactional
	public ScerevTLocalita updateLocalitaByAddress(String codComune, String comune,String cap, String indirizzo, String civico, ScerevDNazione nazioneEntity) {								
		ScerevTLocalita localita = new ScerevTLocalita();
		localita.setScerevDNazione(nazioneEntity);
		localita.setCap(cap);
		localita.setComune(comune);
		localita.setIndirizzo(indirizzo);
		localita.setNumeroCivico(civico);
		localita.setCodiceIstatComune(codComune);
		localita.setDataCreazione(new Timestamp(new Date().getTime()));
		localita.setDataModifica(new Timestamp(new Date().getTime()));		
		return em.merge(localita);
	}

	public ScerevDPraticaStato getStatoPratica(String desc) {
		TypedQuery<ScerevDPraticaStato> query = em.createNamedQuery("ScerevDPraticaStato.findByValore",ScerevDPraticaStato.class);
		query.setParameter("desc", desc);
		return getSingleResult(query);
	}
	
	public ScerevTInfoSupplementari getInfoSupplementari(Integer pkFile_Selezionato) {
		// TODO
		return null;
	}

	public ScerevDGradoParentela getGradoParentela(String valore) {
		TypedQuery<ScerevDGradoParentela> query = em.createNamedQuery("ScerevDGradoParentela.findByValore",ScerevDGradoParentela.class);
		query.setParameter("valore", valore.toUpperCase());
		return getSingleResult(query);
	}
	
	@Transactional
	public void updateDistretto(ScerevDDistretto distretto) {
		em.merge(distretto);		
	}

	@Transactional
	public void saveDistretto(ScerevDDistretto distretto) {
		em.persist(distretto);		
	}
	
	@Transactional
	public void updateAmbito(ScerevDAmbito ambito) {
		em.merge(ambito);		
	}

	@Transactional
	public void insertPraticaPraticaTipo(ScerevRPraticaPraticaTipo praticaPraticaTipo) {
		em.merge(praticaPraticaTipo);			
	}
	
	public ScerevDAsl getAslByDenominazione(String azienda) {
		TypedQuery<ScerevDAsl> query = em.createNamedQuery("ScerevDAsl.findDenominazione",ScerevDAsl.class);
		query.setParameter("denominazioneAzienda", azienda);
		return getSingleResult(query);
	}

	@Override
	public String getAllPraticheCount() {
		String hqlQuery = "SELECT count(*) FROM ScerevTPratica s";
		Query query = em.createQuery(hqlQuery);
		return query.getSingleResult().toString();
	}

	@Override
	public List<ScerevTPratica> getPraticaPerCF(String citId) {
		TypedQuery<ScerevTPratica> query = em.createNamedQuery("ScerevTPratica.findPerCF",ScerevTPratica.class);
		query.setParameter("cfAssistito", citId);
		return query.getResultList();	
	}

	@Override
	public ScerevTPratica getPraticaForRevocaNuovaRichiesta(String cf, Integer numeroPratica) {
		TypedQuery<ScerevTPratica> query = em.createNamedQuery("ScerevTPratica.findPerRevocaNuovaRichiesta",ScerevTPratica.class);
		query.setParameter("citId", cf);
		query.setParameter("numPratica", numeroPratica);
		return getSingleResult(query);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void insertScerevTInfoSupplementari(ScerevTInfoSupplementari info) {
		em.persist(info);
		
	}

	@Override
	public List<ScerevDAsl> getAllAslDistrettoAmbito() {
		List<ScerevDAsl> asls = em.createNamedQuery("ScerevDAsl.findAll", ScerevDAsl.class).getResultList();
		for(ScerevDAsl asl : asls) {
			asl.setScerevDDistrettos(em.createQuery("Select distinct d from ScerevDDistretto d left join fetch d.scerevDAmbitos where d.scerevDAsl in :asl", ScerevDDistretto.class).setParameter("asl", asl).getResultList());
		}
		return asls;
	}

	@Override
	public List<ScerevDPraticaTipo> getTipologieRichiesta(Integer pkPratica) {
		List<ScerevDPraticaTipo> tipi = new ArrayList<ScerevDPraticaTipo>();
		try {
			List<ScerevRPraticaPraticaTipo> rTipi = em.createQuery("Select r from ScerevRPraticaPraticaTipo r "
					+ "left join fetch r.scerevTPratica "
					+ "left join fetch r.scerevDPraticaTipo "
					+ "where r.scerevTPratica.pkPratica = :pk", ScerevRPraticaPraticaTipo.class).setParameter("pk", pkPratica).getResultList();
			for(ScerevRPraticaPraticaTipo r : rTipi) {
				tipi.add(r.getScerevDPraticaTipo());
			}
		} catch (Exception e) {
			return null; 
		}
		return tipi;
		
		
	}
	
	@Override
	public List<ScerevTPratica> getPraticaByCFandStato(String codFiscale, List<String> listaCodici) {
		
		String hqlQuery = "SELECT distinct scerevTPratica FROM ScerevTPratica scerevTPratica "
				+ "left join fetch scerevTPratica.scerevTCronologias scerevTCronologias "
				+ "left join fetch scerevTPratica.scerevRPraticaPraticaTipos scerevRPraticaPraticaTipos "
				+ "left join fetch scerevTPratica.scerevTDocumentos scerevTDocumentos "
				+ "where scerevTPratica.cfAssistito = :cfAssistito";
		if (listaCodici != null && listaCodici.size() > 0) {
			hqlQuery += " and scerevTPratica.scerevDPraticaStato.codicePraticaStato in ( ";
			for (Iterator<String> iterator = listaCodici.iterator(); iterator.hasNext();) {
				String code = (String) iterator.next();
				hqlQuery += "'"+code+"'";
				if (iterator.hasNext())  hqlQuery += ",";
				else  hqlQuery += ")";
			}
		}
		TypedQuery<ScerevTPratica> query = em.createQuery(hqlQuery, ScerevTPratica.class);
		query.setParameter("cfAssistito", codFiscale);
		return query.getResultList();
	}

	@Override
	public void saveAmbito(ScerevDAmbito ambito) {
		em.persist(ambito);
		
	}

	@Override
	public ScerevDMotivoSoggiorno getMotivoSoggiornoPerControlloAllegati(String id) {
		TypedQuery<ScerevDMotivoSoggiorno> query = em.createNamedQuery("ScerevDMotivoSoggiorno.findByIdPerAllegati", ScerevDMotivoSoggiorno.class);
		query.setParameter("id", id);
		return getSingleResult(query);
	}

	@Override
	public List<ScerevTAslOperatore> getAslOperatore(String codFisc) {
		TypedQuery<ScerevTAslOperatore> query = em.createNamedQuery("ScerevTAslOperatore.findbyCfOperatore", ScerevTAslOperatore.class);
		query.setParameter("cfOp", codFisc);
		return query.getResultList();
	}

	@Override
	public List<ScerevTMonitoraggio> getMediciMonitoratiByCittadino(String citId) {
		
		String hqlQuery = "SELECT m FROM ScerevTMonitoraggio m WHERE m.cfAssistito = : citId AND m.dataCancellazione IS NULL";
		TypedQuery<ScerevTMonitoraggio> query = em.createQuery(hqlQuery, ScerevTMonitoraggio.class);
		query.setParameter("citId", citId);
		return query.getResultList();
		
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void insertScerevTMonitoraggio(ScerevTMonitoraggio scerevTMonitoraggio) {
		em.persist(scerevTMonitoraggio);
	}

	@Override
	public List<ScerevTMonitoraggio> getMediciMonitoratiByCittadino(String shibId, String idAura) {
		String hqlQuery = "SELECT m FROM ScerevTMonitoraggio m WHERE m.cfAssistito = : shibId AND m.idAuraMedico = : idAura AND m.dataCancellazione IS NULL";
		TypedQuery<ScerevTMonitoraggio> query = em.createQuery(hqlQuery, ScerevTMonitoraggio.class);
		query.setParameter("idAura", idAura);
		query.setParameter("shibId", shibId);
		return query.getResultList();
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void saveMonitoraggio(ScerevTMonitoraggio med) {
		em.merge(med);
	}

	@Override
	public ScerevTFile getAllegatoByIdAndCf(String citId, String allegatoId) {
		String hqlQuery = "SELECT s FROM ScerevTFile s left join fetch s.scerevTDocumentos d "
						+ "WHERE s.pkFile = :id AND d.scerevTPratica.cfAssistito = :cf";
		try {
			TypedQuery<ScerevTFile> query = em.createQuery(hqlQuery, ScerevTFile.class);
			query.setParameter("id", Integer.valueOf(allegatoId));
			query.setParameter("cf", citId);
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public ScerevLogAura insertUpdateLogAura(ScerevLogAura logAura) {
		if(logAura.getPkLogAura() == null)
			logAura.setDataIns(new Timestamp(System.currentTimeMillis()));
		else
			logAura.setDataUpd(new Timestamp(System.currentTimeMillis()));
		return em.merge(logAura);
	}
}
