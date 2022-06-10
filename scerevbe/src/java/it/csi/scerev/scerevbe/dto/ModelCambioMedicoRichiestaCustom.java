/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevbe.dto;

import java.util.ArrayList;
import java.util.List;

import it.csi.scerev.scerevbe.dto.ModelCambioMedicoRichiestaDatiAggiuntivi.LavoroTipologiaEnum;
import it.csi.scerev.scerevbe.enums.MotivazioneDomicilioEnum;
import it.csi.scerev.scerevbe.enums.StatoPraticaEnum;
import it.csi.scerev.scerevjpa.business.entity.ScerevRPraticaPraticaTipo;
import it.csi.scerev.scerevjpa.business.entity.ScerevTCronologia;
import it.csi.scerev.scerevjpa.business.entity.ScerevTDocumento;
import it.csi.scerev.scerevjpa.business.entity.ScerevTPratica;
import it.csi.scerev.scerevutil.business.Checker;
import it.csi.scerev.scerevutil.business.SharedConstants;

public class ModelCambioMedicoRichiestaCustom extends ModelCambioMedicoRichiesta {

	public ModelCambioMedicoRichiestaCustom(ScerevTPratica pratica) {
		try {
			this.setId(pratica.getNumeroPratica().toString());
			this.setTipologia(TipologiaEnum.valueOf(pratica.getTipologiaGestionePratica().replace("-", "_")));
			if (pratica.getScerevDPraticaStato().getCodicePraticaStato().equalsIgnoreCase(StatoPraticaEnum.BOZZA.getKey())) {
				this.setBozza(true);
				this.setAnnullabile(true);
				this.setRettificabile(false);
			} else if (pratica.getScerevDPraticaStato().getCodicePraticaStato().equalsIgnoreCase(StatoPraticaEnum.INVIATA.getKey())) {
				this.setBozza(false);
				this.setAnnullabile(true);
				this.setRettificabile(false);
			} else if (pratica.getScerevDPraticaStato().getCodicePraticaStato().equalsIgnoreCase(StatoPraticaEnum.IN_LAVORAZIONE.getKey())) {
				this.setBozza(false);
				this.setAnnullabile(false);
				this.setRettificabile(false);
				//this.setRettificabile(true); errore di analisi corretto jira 6
			} else if (pratica.getScerevDPraticaStato().getCodicePraticaStato().equalsIgnoreCase(StatoPraticaEnum.IN_REVISIONE.getKey())) {
				this.setBozza(false);
				this.setAnnullabile(true);
				this.setRettificabile(true);
//				this.setRettificabile(false); errore di analisi corretto jira 6
			}
			
			this.setRinnovoAssistenza(false);
			if(pratica.getScerevRPraticaPraticaTipos() != null) {
				for(ScerevRPraticaPraticaTipo tipo : pratica.getScerevRPraticaPraticaTipos()) {
					if(tipo.getScerevDPraticaTipo() != null 
					&& tipo.getScerevDPraticaTipo().getCodicePraticaTipo()
					.equalsIgnoreCase(SharedConstants.RINNOVO_ASSISTENZA_TYPE))
						this.setRinnovoAssistenza(true);
				}
			}
			
			this.setStato(new ModelCambioMedicoRichiestaStato());
			this.getStato().setId(pratica.getScerevDPraticaStato().getCodicePraticaStato());
			this.getStato().setNome(pratica.getScerevDPraticaStato().getValorePraticaStato());
			this.setCittadinanza(new ModelCittadinanza());
			this.getCittadinanza().setId(pratica.getScerevDCittadinanza().getCodiceCittadinanza());
			this.getCittadinanza().setDescrizione(pratica.getScerevDCittadinanza().getValoreCittadinanza());
			
			if (pratica.getScerevTLocalita2() != null) {
				this.setDomicilio(new ModelCittadinoDomicilio());
				this.getDomicilio().setComune(pratica.getScerevTLocalita2().getComune());
				this.getDomicilio().setCap(pratica.getScerevTLocalita2().getCap());
				this.getDomicilio().setCodComune(pratica.getScerevTLocalita2().getCodiceIstatComune());
				this.getDomicilio().setIndirizzo(pratica.getScerevTLocalita2().getIndirizzo());
				this.getDomicilio().setCivico(pratica.getScerevTLocalita2().getNumeroCivico());
			}
			
			if (pratica.getScerevTLocalita1() != null) {
				this.setResidenza(new ModelCittadinoResidenza());
				this.getResidenza().setComune(pratica.getScerevTLocalita1().getComune());
				this.getResidenza().setCap(pratica.getScerevTLocalita1().getCap());
				this.getResidenza().setCodComune(pratica.getScerevTLocalita1().getCodiceIstatComune());
				this.getResidenza().setIndirizzo(pratica.getScerevTLocalita1().getIndirizzo());
				this.getResidenza().setCivico(pratica.getScerevTLocalita1().getNumeroCivico());
				this.getResidenza().setNazione(pratica.getScerevTLocalita1().getScerevDNazione() == null ? null : pratica.getScerevTLocalita1().getScerevDNazione().getNomeNazione());
			}
			
			this.setMedico(new ModelCittadinoInfoMedico());
			this.getMedico().setId(pratica.getIdAuraMedico());
			this.getMedico().setCodiceFiscale(pratica.getCodiceFiscaleMedico());
			this.setMedicoAltraRegione(new ModelCambioMedicoMedicoAltraRegione());
			this.getMedicoAltraRegione().setAziendaSanitaria(pratica.getCodiceAslPrecedente());
			this.getMedicoAltraRegione().setNome(pratica.getNomeMedicoPrecedente());
			this.getMedicoAltraRegione().setCognome(pratica.getCognomeMedicoPrecedente());
			this.getMedicoAltraRegione().setRegione(pratica.getScerevDRegione() != null ? pratica.getScerevDRegione().getNomeRegione() : null);
			
			this.setDatiAggiuntivi(new ModelCambioMedicoRichiestaDatiAggiuntivi());
			if (pratica.getScerevDMotivoSoggiorno() != null) {
				this.getDatiAggiuntivi().setMotivazioneSoggiorno(new ModelMotivazioneSoggiorno());
				this.getDatiAggiuntivi().getMotivazioneSoggiorno().setId(pratica.getScerevDMotivoSoggiorno().getCodiceMotivoSoggiorno());
				this.getDatiAggiuntivi().getMotivazioneSoggiorno().setDescrizione(pratica.getScerevDMotivoSoggiorno().getValoreMotivoSoggiorno());
			}
			this.getDatiAggiuntivi().setAssitenzaTemporaneaDataFineValidita(pratica.getDataFineAssistenzaRichiesta());
			//prendo la nota dalla cronologia facendo la query
			this.getDatiAggiuntivi().setNote(pratica.getScerevTCronologias() != null && !pratica.getScerevTCronologias().isEmpty() ? pratica.getScerevTCronologias().iterator().next().getNotaRichiedente() : null);
			
			if (pratica.getScerevDMotivoDomicilio() != null) {
				this.getDatiAggiuntivi().setMotivazioneDomicilio(new ModelMotivazioneDomicilio());
				this.getDatiAggiuntivi().getMotivazioneDomicilio().setId(pratica.getScerevDMotivoDomicilio().getCodiceMotivoDomicilio());
				this.getDatiAggiuntivi().getMotivazioneDomicilio().setDescrizione(pratica.getScerevDMotivoDomicilio().getValoreMotivoDomicilio());
				if (pratica.getScerevDMotivoDomicilio().getCodiceMotivoDomicilio().equalsIgnoreCase(MotivazioneDomicilioEnum.LAVORO.getKey())) {
					if(pratica.getScerevTInfoSupplementari() != null && pratica.getScerevTInfoSupplementari().getTipologiaLavoro() != null) {
						this.getDatiAggiuntivi().setLavoroTipologia(pratica.getScerevTInfoSupplementari().getTipologiaLavoro() ? LavoroTipologiaEnum.AUTONOMO : LavoroTipologiaEnum.SUBORDINATO);
					}
					this.getDatiAggiuntivi().setLavoroProfessione(pratica.getScerevTInfoSupplementari().getProfessione());
					this.getDatiAggiuntivi().setLavoroPartitaIva(pratica.getScerevTInfoSupplementari().getPartitaIva());
					this.getDatiAggiuntivi().setLavoroNumeroIscrizione(pratica.getScerevTInfoSupplementari().getNumeroIscrizione());
					this.getDatiAggiuntivi().setLavoroAzienda(pratica.getScerevTInfoSupplementari().getDittaDatoreLavoro());
					this.getDatiAggiuntivi().setLavoroAziendaTelefono(pratica.getScerevTInfoSupplementari().getNumeroTelefonoDatore());
					this.getDatiAggiuntivi().setLavoroAziendaProvincia(pratica.getScerevTInfoSupplementari().getProvinciaLavoro());
					this.getDatiAggiuntivi().setLavoroAziendaComune(pratica.getScerevTInfoSupplementari().getComuneLavoro());
					this.getDatiAggiuntivi().setLavoroAziendaIndirizzo(pratica.getScerevTInfoSupplementari().getIndirizzoLavoro());
					this.getDatiAggiuntivi().setLavoroAziendaCivico(pratica.getScerevTInfoSupplementari().getNumeroCivicoLavoro());
					this.getDatiAggiuntivi().setLavoroDataFineValiditaContratto(pratica.getScerevTInfoSupplementari().getDataFineContratto());
				} else if (pratica.getScerevDMotivoDomicilio().getCodiceMotivoDomicilio().equalsIgnoreCase(MotivazioneDomicilioEnum.STUDIO.getKey())) {
					this.getDatiAggiuntivi().setStudioNomeIstituto(pratica.getScerevTInfoSupplementari().getNomeIstituto());
					this.getDatiAggiuntivi().setStudioIstitutoProvincia(pratica.getScerevTInfoSupplementari().getProvinciaSedeStudio());
					this.getDatiAggiuntivi().setStudioIstitutoComune(pratica.getScerevTInfoSupplementari().getComuneSedeStudio());
					this.getDatiAggiuntivi().setStudioIstitutoIndirizzo(pratica.getScerevTInfoSupplementari().getIndirizzoSedeStudio());
					this.getDatiAggiuntivi().setStudioDataTermineIscrizione(pratica.getScerevTInfoSupplementari().getDataFineIscrizione());
				} else if (pratica.getScerevDMotivoDomicilio().getCodiceMotivoDomicilio().equalsIgnoreCase(MotivazioneDomicilioEnum.RICONGIUNGIMENTO_FAMILIARE.getKey()) 
						|| pratica.getScerevDMotivoDomicilio().getCodiceMotivoDomicilio().equalsIgnoreCase(MotivazioneDomicilioEnum.RICONGIUNGIMENTO_FAMILIARE_LAVORATORE.getKey())
						|| pratica.getScerevDMotivoDomicilio().getCodiceMotivoDomicilio().equalsIgnoreCase(MotivazioneDomicilioEnum.RICONGIUNGIMENTO_FAMILIARE_MOTIVI_DI_SALUTE.getKey())) {
					this.getDatiAggiuntivi().setRicongiungimentoNome(pratica.getScerevTInfoSupplementari().getNomeParente());
					this.getDatiAggiuntivi().setRicongiungimentoCognome(pratica.getScerevTInfoSupplementari().getCognomeParente());
					this.getDatiAggiuntivi().setRicongiungimentoCodiceFiscale(pratica.getScerevTInfoSupplementari().getCfParente());
					this.getDatiAggiuntivi().setRicongiungimentoParentela(pratica.getScerevTInfoSupplementari().getScerevDGradoParentela().getCodiceGradoParentela());
				}
			}
			
			if(pratica.getScerevTInfoSupplementari() != null)
				this.getDatiAggiuntivi().setMotivazioneDomanda(pratica.getScerevTInfoSupplementari().getMotivazioneDomanda());
			
			if (pratica.getScerevTDocumentos() != null) {
				List<ModelDocumentoLettura> all = new ArrayList<ModelDocumentoLettura>();
				for(ScerevTDocumento d : pratica.getScerevTDocumentos()) {
					ModelDocumentoLettura a = new ModelDocumentoLettura();
					a.setId(d.getScerevTFile().getPkFile().toString());
					a.setTipo(d.getScerevDDocumentoTipo().getCodiceDocumentoTipo());
					a.setDescrizione(d.getScerevDDocumentoTipo().getDescrizioneDocumentoTipo());
					a.setNomeFile(d.getScerevTFile().getNameFile());
					all.add(a);
				}
				this.setAllegati(all);
			}
			if(pratica.getScerevTCronologias() != null && !pratica.getScerevTCronologias().isEmpty() ) {
				List<ModelCambioMedicoRichiestaChatOperatore> chat = new ArrayList<ModelCambioMedicoRichiestaChatOperatore>();
				for(ScerevTCronologia crono : pratica.getScerevTCronologias()) {					
					if(Checker.isValorizzato(crono.getUtente()) && Checker.isValorizzato(crono.getNotaRichiedente())) {
						ModelCambioMedicoRichiestaChatOperatore c = new ModelCambioMedicoRichiestaChatOperatore();
						try {
							c.setCittadino(!crono.getUtente().startsWith("OPERATORE"));
						} catch (Exception e) {
							c.setCittadino(true);
						}
						c.setData(crono.getDataOraInserimento());
						c.setTesto(crono.getNotaRichiedente());
						chat.add(c);
					}
				}
				this.setChatOperatore(chat);
			} else this.setChatOperatore(null);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
