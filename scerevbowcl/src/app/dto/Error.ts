/*
 Copyright Regione Piemonte - 2022
 SPDX-License-Identifier: EUPL-1.2
*/
export class SrvError {
    code: string;
    errorMessage: string;
    fields: string;
    messaggioCittadino: string;
    sessionExpired? : boolean;
}
