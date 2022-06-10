/*
 Copyright Regione Piemonte - 2022
 SPDX-License-Identifier: EUPL-1.2
*/
import { Injectable } from '@angular/core'
import { environment } from "src/environments/environment";

@Injectable()
export class ConfigService {

    static getBEServer(): string {
        try { if ( ENV_PROPERTIES.endpoint != null ) return ENV_PROPERTIES.endpoint; }
        catch ( e ) { return environment.endpoint; }
    }
}
