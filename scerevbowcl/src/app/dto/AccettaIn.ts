/*
 Copyright Regione Piemonte - 2022
 SPDX-License-Identifier: EUPL-1.2
*/
export class AccettaIn {
    numeroPratica: string;
    codiceFiscaleAssistito: string;
    tipologia: string;
    durataAssistenzaDa: string;
    durataAssistenzaA: string;
    flMedicoGenerico: boolean;
	flDerogaPediatra: boolean;
    flDerogaMassimale: boolean;
    flDerogaMassimaleTmp: boolean;
    flDerogaTerritoriale: boolean;
    flRicongFamigliare: boolean;
    flMedicoRevocato: boolean;
    flDerogaAssociazione: boolean;
    notaInterna: string;
    daChiudere: boolean;
}   
