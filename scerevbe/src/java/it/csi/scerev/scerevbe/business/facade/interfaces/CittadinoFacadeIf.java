/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevbe.business.facade.interfaces;

import it.csi.def.opessanws.opessan.SoggettoOpessanBody;
import it.csi.scerev.scerevbe.dto.ModelAmbulatorioLoccsiMedico;
import it.csi.scerev.scerevbe.dto.ModelAsl;
import it.csi.scerev.scerevbe.dto.ModelCittadinoDomicilio;
import it.csi.scerev.scerevbe.dto.ModelCittadinoResidenza;
import it.csi.scerev.scerevbe.dto.Payload;
import it.csi.scerev.scerevbe.dto.Payload3;
import it.csi.scerev.scerevjpa.business.entity.ScerevLAudit;
import it.csi.scerev.scerevjpa.business.entity.ScerevTFile;
import it.csi.scerev.scerevjpa.business.entity.ScerevTMonitoraggio;
import it.csi.scerev.scerevserviziesterni.aura.find.DatiAnagrafici;
import it.csi.scerev.scerevserviziesterni.aura.get.SoggettoAuraBodyNew;
import it.csi.scerev.scerevutil.business.exception.AllegatoNotFoundException;
import it.csi.scerev.scerevutil.business.exception.AuraNotFoundException;
import it.csi.scerev.scerevutil.business.exception.OpessanNotFoundException;

public interface CittadinoFacadeIf {
	boolean checkCorrettezzaResidenzaDomicilioAssistenza(Payload payload, String citId, ScerevLAudit audit, DatiAnagrafici cittadinoFind, SoggettoAuraBodyNew cittadinoGet) throws AuraNotFoundException;
	boolean checkVariazioneASLResidenzaDomicilio(Payload payload, String citId, ScerevLAudit audit, DatiAnagrafici cittadinoFind, SoggettoAuraBodyNew cittadinoGet) throws AuraNotFoundException;
	boolean checkDerogaTerritoriale(Payload payload, String citId, ScerevLAudit audit, DatiAnagrafici cittadinoFind, SoggettoAuraBodyNew cittadinoGet) throws AuraNotFoundException;
	boolean checkDerogaAssociazione(Payload payload, String citId, ScerevLAudit audit, DatiAnagrafici cittadinoFind, SoggettoAuraBodyNew cittadinoGet) throws OpessanNotFoundException, AuraNotFoundException, Exception;
	boolean checkDerogaMedicoPLS(Payload payload, String citId, ScerevLAudit audit, DatiAnagrafici cittadinoFind) throws AuraNotFoundException;
	boolean checkDerogaMedicoMMG(Payload payload, String citId, ScerevLAudit audit, DatiAnagrafici cittadinoFind) throws AuraNotFoundException;
	ModelAsl calcolaIscrizione(Payload payload);
	ModelAsl calcolaDomicilio(ModelCittadinoDomicilio domicilio, String citId, ScerevLAudit audit, DatiAnagrafici cittadinoFind, SoggettoAuraBodyNew cittadinoGet) throws AuraNotFoundException;
	ModelAsl calcolaResidenza(ModelCittadinoResidenza residenza, String citId, ScerevLAudit audit, DatiAnagrafici cittadinoFind, SoggettoAuraBodyNew cittadinoGet) throws AuraNotFoundException;
	String annullaRimuoviPratica(String richiestaId) throws Exception;
	String revocaMedico(SoggettoAuraBodyNew sog, SoggettoOpessanBody detOp, String citId, String shib, String motivo);
	public byte[] certificatoRevoca_getFile(String cit_Id, ScerevLAudit audit) throws AuraNotFoundException, Exception;
	public byte[] tesserinoMedico_getFile(String cit_Id, ScerevLAudit audit) throws AuraNotFoundException, Exception;
	public byte[] stampaRichiesta_getFile(String richiestaId, ScerevLAudit audit);
	byte[] stampaModuloDelegante_getFile(String citId, Payload3 payload, ScerevLAudit audit);
	void monitoraMedico(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
			String xCodiceServizio, String citId,ModelAmbulatorioLoccsiMedico payload);
	boolean chkPresenzaMedicoMonitorato(String shibId,String idAura);
	ScerevTMonitoraggio getMedicoMonitoratoByShib(String shibId,String idMedico);
	void rimuoviMedicoMonitorato(ScerevTMonitoraggio med);
	ScerevTFile getAllegato(String citId, String allegatoId) throws AllegatoNotFoundException;
	boolean checkRicusazioneMedico(ModelAmbulatorioLoccsiMedico medico, DatiAnagrafici cittadinoFind) throws Exception;
}
