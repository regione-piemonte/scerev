/*
 Copyright Regione Piemonte - 2022
 SPDX-License-Identifier: EUPL-1.2
*/
import { DatiRichiedente } from "src/app/dto/DatiRichiedente";
import { DatiAuraMedico } from "src/app/dto/DatiAuraMedico";
import { DatiMedicoExtra } from "src/app/dto/DatiMedicoExtra";
import { InformazioniSupplementari } from "src/app/dto/InformazioniSupplementari";
import { DettaglioAllegato } from "src/app/dto/Allegati";
import { DatiMedicoRichiesto } from "src/app/dto/DatiMedicoRichiesto";
import { TipologiaProfilo } from "src/app/dto/TipologiaProfilo";
import { Motivazione } from "src/app/dto/Motivazione";
import { DettaglioDeroghe } from "src/app/dto/DettaglioDeroghe";
import { DatiDelegato } from "./DatiDelegato";

export class Dettaglio {
    dati_richiedente: DatiRichiedente;
    dati_aura_medico: DatiAuraMedico;
    dati_medico_extra: DatiMedicoExtra;
    dati_delegato: DatiDelegato;
    informazioni_supplementari: InformazioniSupplementari;
    allegati: DettaglioAllegato[];
    dati_medico_richiesto: DatiMedicoRichiesto;
    inCarico: string;
    stato: string;
    numPratica: string;
    tipologieProfilo: TipologiaProfilo[];
    motivazioni: Motivazione[];
    tipologieRichiesta: string;
    deroghe: DettaglioDeroghe;
    dataInizioAss: string;
    dataFineAss: string;
    cfInCarico: string;
    msgIfClosed: string;
}
