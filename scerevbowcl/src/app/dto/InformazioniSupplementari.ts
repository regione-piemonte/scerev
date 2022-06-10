/*
 Copyright Regione Piemonte - 2022
 SPDX-License-Identifier: EUPL-1.2
*/
export class InformazioniSupplementari {
        id: number;
        professione: string;
        partitaIva: number;
        nIscrizione: number;
        nomeFamiliare: string;
        cognomeFamiliare: string;
        codiceFiscale: string;
        rapportoParentela: string;
        medicoFamiliare: string;
        
        dittaODatoreDiLavoro: string;
	nrTelefonoDatoreLavoro: string;
	comuneLavoro: string;
	provinciaLavoro: string;
	indirizzoLavoro: string;
	civicoLavoro: string;
	dataFineContrattoLavoro: Date;
	nomeIstitutoStudio: string;
	comuneIstitutoStudio: string;
	provinciaIstitutoStudio: string;
	indirizzoIstitutoStudio: string;
	civicoIstitutoStudio: string;
	dataFineIscrizioneStudio: Date;
	medicoDiverso: string;
	motivoDomicilio: string;
	motivoSoggiorno: string;
	motivoRevoca: string;
	motivazioneDerogaTerritoriale: string;

	residenzaFamiliare: string;
	domicilioFamiliare: string;
	dataNascitaFamiliare: string;
	tipoProfiloFamiliare: string;
	dataFineAslFamiliare: string;
}
