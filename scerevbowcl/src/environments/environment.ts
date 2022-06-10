/*
 Copyright Regione Piemonte - 2022
 SPDX-License-Identifier: EUPL-1.2
*/
// This file can be replaced during build by using the `fileReplacements` array.
// `ng build --prod` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

export const environment = {
    production: false,
    //endpoint: "https://tst-scerev.ruparpiemonte.it/scerevweb/restfacade/be",
    endpoint: "http://localhost:8080/scerevboweb/restfacade/be",
    esci: "https://tst-esenred-pa-sistemapiemonte.isan.csi.it/tst_ese_pa_443sliv1wrup/Shibboleth.sso/Logout",
    profilo: "https://tst-secure.sistemapiemonte.it/salute/lamiasalute/profilo.php?servizio=profilo"
};

/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/dist/zone-error';  // Included with Angular CLI.
