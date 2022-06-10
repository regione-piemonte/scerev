/*
 Copyright Regione Piemonte - 2022
 SPDX-License-Identifier: EUPL-1.2
*/
import { expr } from "jquery";
import { Ambito } from "./Ambito";

export class Distretto{
    id: number;
    descrizione: string;
    ambiti : Ambito[];
}
