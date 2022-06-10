/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevbe.business.facade.interfaces;

import java.util.List;

import it.csi.scerev.scerevbe.dto.ModelAmbulatorioLoccsiMedico;
import it.csi.scerev.scerevbe.dto.Payload;
import it.csi.scerev.scerevbe.dto.Payload1;
import it.csi.scerev.scerevbe.dto.Payload2;
import it.csi.scerev.scerevbe.dto.Payload3;
import it.csi.scerev.scerevbe.dto.Payload4;
import it.csi.scerev.scerevutil.business.exception.IntegritaException;
import it.csi.scerev.scerevutil.business.exception.ParametriObbligatoriException;

public interface ControlloFacadeIf {
	void chkDatiObbligatoriRichiestaNuovoMedico(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,String xCodiceServizio, String citId, Payload1 payload) throws ParametriObbligatoriException;

	void chkDatiObbligatoriControlloCombinazione(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,String xCodiceServizio, String citId, Payload payload) throws ParametriObbligatoriException;
	
	void chkCongruitaInput(Payload1 payload) throws IntegritaException, ParametriObbligatoriException;
	
	void chkCongruitaInput(Payload payload) throws IntegritaException;

	void chkDatiObbligatoriInformazioniAssistito(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor, String xCodiceServizio, String citId) throws Exception;
	
	void chkDatiObbligatoriRettificaRichiesta(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor, String xCodiceServizio, String citId, String richiestaId, Payload2 payload) throws Exception;
	
	void chkCongruitaInput(String citId, String richiestaId, String shib) throws IntegritaException;
	
	void chkCongruitaInput(String citId) throws IntegritaException;
	
	void chkCongruitaInputCitIdShib(String citId, String shib) throws IntegritaException;

	void chkCongruitaInput(List<ModelAmbulatorioLoccsiMedico> payload) throws IntegritaException;

	void chkDatiObbligatoriMassimali(String shibIdentitaCodiceFiscale, String xRequestId,
			String xForwardedFor, List<ModelAmbulatorioLoccsiMedico> payload) throws ParametriObbligatoriException;
	
	void chkDatiObbligatoriTesserinoMedico(String shibIdentitaCodiceFiscale, String xRequestId,
			String xForwardedFor, String xCodiceServizio, String citId) throws ParametriObbligatoriException;
	
	void chkDatiObbligatoriCertificatoRevoca(String shibIdentitaCodiceFiscale, String xRequestId,
			String xForwardedFor, String xCodiceServizio, String citId) throws ParametriObbligatoriException;
	
	void chkDatiObbligatoriModuloDelegante(String shibIdentitaCodiceFiscale, String xRequestId,
			String xForwardedFor, String xCodiceServizio, String citId, Payload3 payload) throws ParametriObbligatoriException;

	void chkDatiObbligatoriRevocaMedico(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
			String xCodiceServizio, String citId, String motivo) throws ParametriObbligatoriException;

	void chkDatiObbligatoriAnnullaRichiesta(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
			String xCodiceServizio, String citId) throws ParametriObbligatoriException ;

	void chkDatiObbligatoriControlloAllegati(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
			String xCodiceServizio, String citId, Payload4 payload) throws ParametriObbligatoriException;
	
	void chkDatiObbligatoriMediciMonitorati(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
			String xCodiceServizio, String citId) throws ParametriObbligatoriException;
	
	void chkIntegritaMediciMonitorati(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
			String xCodiceServizio, String citId) throws IntegritaException;

	void chkDatiObbligatoriStampaAllegato(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
			String xCodiceServizio, String citId, String allegatoId) throws ParametriObbligatoriException;
}
