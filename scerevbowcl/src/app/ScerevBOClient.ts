/*
 Copyright Regione Piemonte - 2022
 SPDX-License-Identifier: EUPL-1.2
*/
import { Injectable, Input } from "@angular/core";
import { Observable, pipe, throwError } from "rxjs";
import { catchError, retryWhen, timeout } from 'rxjs/operators';
import { of } from 'rxjs';
import { Http, Response, Headers, URLSearchParams, RequestOptions, ResponseContentType } from '@angular/http';
import { map } from "rxjs/internal/operators/map";
import { HttpModule } from '@angular/http';
import { Dettaglio } from "src/app/dto/Dettaglio";
import { UserInfo } from "src/app/dto/UserInfo";
import { RicercaRichiesteOut } from "src/app/dto/RicercaRichiesteOut";
import { TipologieMedico } from "src/app/dto/TipologieMedico";
import { ListaStati } from "src/app/dto/ListaStati";
import { RicercaRichiesteIn } from "src/app/dto/RicercaRichiesteIn";
import { RicercaRichiesteOutDT } from "src/app/dto/RicercaRichiesteOutDT";
import { RichiediRettificaIn } from "src/app/dto/RichiediRettificaIn";
import { RichiediRettificaOut } from "src/app/dto/RichiediRettificaOut";
import { RespingiIn } from "src/app/dto/RespingiIn";
import { RespingiOut } from "src/app/dto/RespingiOut";
import { AccettaIn } from "src/app/dto/AccettaIn";
import { AccettaOut } from "src/app/dto/AccettaOut";
import { AggiungiNotaOut } from "src/app/dto/AggiungiNotaOut";
import { AggiungiNotaIn } from "src/app/dto/AggiungiNotaIn";
import { Ricerca } from "src/app/dto/Ricerca";
import { Cronologia } from "src/app/dto/Cronologia";
import * as uuid from 'uuid';
import { Asl } from "src/app/dto/Asl";
import { ChiudeIn } from "src/app/dto/ChiudeIn";
import { ChiudeOut } from "src/app/dto/ChiudeOut";
import { PresaInCarico } from "src/app/dto/PresaInCarico";
import { ConfigService } from "src/app/config.service";
import { CookieService } from "ngx-cookie-service";
import { AslDistrettoAmbito } from "./dto/AslDistrettoAmbito";

@Injectable()
export class ScerevBOClient {

    public operatore = "OPERATORE_ASL_DISTRETTO_SCEREV";

    public myUUIDV4 = uuid.v4();
    public messaggioFeedback: string = null;
    public idFeedback: number = null;

    private headers: Headers;
    baseUrl = ConfigService.getBEServer();

    public dettaglio: Dettaglio;
    public userInfo: UserInfo;
    public listaRichieste: RicercaRichiesteOut[];
    public rettifica: RichiediRettificaOut;
    public respingi: RespingiOut;
    public accetta: AccettaOut;
    public aggiungi: AggiungiNotaOut;
    public cronologia: Cronologia;
    public cronologiaNumPratica: string;
    public ricerca: Ricerca;
    public recordsFiltered;
    public recordsTotal;
    public datDa;
    public datA;
    public datNasc;
    public pageLenght: number = 10;
    public page: number = 2;
    public dtparams: any;
    public pageStart: any = 0;

    constructor( private http: Http, private cookieService: CookieService
    ) {
    }

    isOperatore(): any {
        if ( this.userInfo.ruolo == this.operatore ) return true;
        else return false;
    }
    
    getHeader(): RequestOptions{
        let cpHeaders = new Headers( { 'Content-Type': 'application/json', 'Accept': 'application/json', 'XSRF-TOKEN': this.cookieService.get("XSRF-TOKEN") } );
        let options = new RequestOptions( { headers: cpHeaders } );
        return options;
    }


    getTipologieMedico(): Observable<Response | any> {
        let cpHeaders = new Headers( { 'Content-Type': 'application/json', 'Accept': 'application/json' } );
        let options = new RequestOptions( { headers: cpHeaders } );
        const url = this.baseUrl + '/tipologie-pratica'
        return this.http.get( url ).pipe(
            map( response => response.json() as TipologieMedico[] ),
            catchError(( res: Response ) => this.onError( res ) )
        );
    }

    prendiInCarico( numPratica: string ): Observable<Response | any> {
        let cpHeaders = new Headers( { 'Content-Type': 'application/json', 'Accept': 'application/json' } );
        let options = new RequestOptions( { headers: cpHeaders } );
        const url = this.baseUrl + '/prendi-in-carico/' + numPratica;
        return this.http.get( url ).pipe(
            map( response => response.json() as PresaInCarico[] ),
            catchError(( res: Response ) => this.onError( res ) )
        );
    }

    login(): Observable<Response | any> {
//        let cookiess = this.cookieService.getAll();
//        
//        let cpHeaders = new Headers( { 'Content-Type': 'application/json', 'Accept': 'application/json' } );
//        let options = new RequestOptions( { headers: cpHeaders } );
        const url = this.baseUrl + '/login'
        return this.http.get( url, this.getHeader() ).pipe(
            map( response => {
                this.userInfo = response.json() as UserInfo;
                return this.userInfo;
            } ),
            catchError(( res: Response ) => this.onError( res ) )
        );
    }

    getListaStati( numTab: number ): Observable<Response | any> {
        let cpHeaders = new Headers( { 'Content-Type': 'application/json', 'Accept': 'application/json' } );
        let options = new RequestOptions( { headers: cpHeaders } );
        const url = this.baseUrl + '/stato-pratica/' + numTab;
        return this.http.get( url ).pipe(
            map( response => response.json() as ListaStati[] ),
            catchError(( res: Response ) => this.onError( res ) )
        );
    }

    getDettaglio( index: any ): Observable<Response | any> {
        let cpHeaders = new Headers( { 'Accept': 'application/json', 'X-Request-ID': uuid() } );
        let options = new RequestOptions( { headers: cpHeaders } );
        const url = this.baseUrl + '/dettaglio/' + index;
        return this.http.get( url, options ).pipe(
            map( response => {
                this.dettaglio = response.json() as Dettaglio;
                return this.dettaglio;
            } ),
            catchError(( res: Response ) => this.onError( res ) )
        );
    }

    getRespingi( request: RespingiIn ): Observable<Response | any> {
        let cpHeaders = new Headers( { 'Accept': 'application/json', 'X-Request-ID': uuid() } );
        let options = new RequestOptions( { headers: cpHeaders } );
        const url = this.baseUrl + '/respinge';
        return this.http.post( url, request, options ).pipe(
            map( response => {
                return response.json() as RespingiOut;
            } ),
            catchError(( res: Response ) => this.onError( res ) )
        );
    }

    getChiude( request: ChiudeIn ): Observable<Response | any> {
        let cpHeaders = new Headers( { 'Accept': 'application/json', 'X-Request-ID': uuid() } );
        let options = new RequestOptions( { headers: cpHeaders } );
        const url = this.baseUrl + '/chiude';
        return this.http.post( url, request, options ).pipe(
            map( response => {
                return response.json() as ChiudeOut;
            } ),
            catchError(( res: Response ) => this.onError( res ) )
        );
    }

    getRettifica( request: RichiediRettificaIn ): Observable<Response | any> {
        let cpHeaders = new Headers( { 'Accept': 'application/json', 'X-Request-ID': uuid() } );
        let options = new RequestOptions( { headers: cpHeaders } );
        const url = this.baseUrl + '/richiedi-rettifica';
        return this.http.post( url, request, options ).pipe(
            map( response => {
                return response.json() as RichiediRettificaOut;
            } ),
            catchError(( res: Response ) => this.onError( res ) )
        );
    }

    getAccetta( request: AccettaIn ): Observable<Response | any> {
        let cpHeaders = new Headers( { 'Accept': 'application/json', 'X-Request-ID': uuid() } );
        let options = new RequestOptions( { headers: cpHeaders } );
        const url = this.baseUrl + '/accetta';
        return this.http.post( url, request, options ).pipe(
            map( response => {
                return response.json() as AccettaOut;
            } ),
            catchError(( res: Response ) => this.onError( res ) )
        );
    }

    aggiungiNotaInterna( request: AggiungiNotaIn ): Observable<Response | any> {
        let cpHeaders = new Headers( { 'Accept': 'application/json', 'X-Request-ID': uuid() } );
        let options = new RequestOptions( { headers: cpHeaders } );
        const url = this.baseUrl + '/nota-interna';
        return this.http.post( url, request, options ).pipe(
            map( response => {
                return response.json() as AggiungiNotaOut;
            } ),
            catchError(( res: Response ) => this.onError( res ) )
        );
    }

    getRichieste( dataTablesParameters: any, ricerca: Ricerca, returned: number ): Observable<Response | any> {
        this.ricerca = ricerca;
        this.dtparams = dataTablesParameters;
        this.pageStart = dataTablesParameters.start;
        this.pageLenght = dataTablesParameters.length;
        if(returned === 1) {
            let response: RicercaRichiesteOutDT = {
                recordsFiltered: this.recordsFiltered,
                recordsTotal: this.recordsTotal,
                data: this.listaRichieste
            }
            return of(response);
        } else if(returned === 2) {
            let response: RicercaRichiesteOutDT = {
                recordsFiltered: '0',
                recordsTotal: '0',
                data: []
            }
            return of(response);
        }
        let cpHeaders = new Headers( { 'Accept': 'application/json', 'X-Request-ID': uuid() } );
        let options = new RequestOptions( { headers: cpHeaders } );
        const url = this.baseUrl + '/ricerca';
        if ( dataTablesParameters == null ) dataTablesParameters = "{}";
        var requestVar = new RicercaRichiesteIn();
        requestVar.dataTablesParameter = dataTablesParameters;
        requestVar.ricerca = ricerca;
        return this.http.post( url, requestVar, options ).pipe(
            map( response => {
                this.recordsFiltered = response.json().recordsFiltered;
                this.recordsTotal = response.json().recordsTotal;
                this.listaRichieste = response.json().data as RicercaRichiesteOut[];
                return response.json() as RicercaRichiesteOutDT;
            } ),
            catchError(( res: Response ) => this.onError( res ) )
        );
    }

    getCronologia(): Observable<Response | any> {
        let cpHeaders = new Headers( { 'Content-Type': 'application/json', 'Accept': 'application/json', 'X-Request-ID': uuid() } );
        let options = new RequestOptions( { headers: cpHeaders } );
        const url = this.baseUrl + '/cronologia-pratica/' + this.cronologiaNumPratica;
        return this.http.get( url, options ).pipe(
            map( response => {
                return response.json() as Cronologia[];
            } ),
            catchError(( res: Response ) => this.onError( res ) )
        );
    }

    getAllAsl(): Observable<Response | any> {
        let cpHeaders = new Headers( { 'Content-Type': 'application/json', 'Accept': 'application/json', 'X-Request-ID': uuid() } );
        let options = new RequestOptions( { headers: cpHeaders } );
        const url = this.baseUrl + '/asl'
        return this.http.get( url, options ).pipe(
            map( response => response.json() as Asl[] ),
            catchError(( res: Response ) => this.onError( res ) )
        );
    }

    getAllegato( cit_id: string, num_pratica: string, pkFile: any ): Observable<Response | any> {
        let cpHeaders = new Headers( { 'X-Request-ID': uuid() } );
        let options = new RequestOptions( { headers: cpHeaders } );
        options.responseType = ResponseContentType.Blob;
        const url = this.baseUrl + '/download/' + num_pratica + '?pkFile=' + pkFile;
        return this.http.get( url, options ).pipe(
            map( response => {
                let m = new Map<string, any>();
                let name = response.headers.get( "Content-Disposition" );
                name = name.substring( name.lastIndexOf( '=' ) + 1, name.length );
                m.set( "name", name );
                m.set( "file", new Blob( [response.blob()], { type: 'application/pdf' } ) );
                return m;
            } ), 
            catchError(( res: Response ) => this.onError( res ) ));
    }
    
    getAllAslDistrettoAmbito() {
        let cpHeaders = new Headers( { 'Content-Type': 'application/json', 'Accept': 'application/json', 'X-Request-ID': uuid() } );
        let options = new RequestOptions( { headers: cpHeaders } );
        const url = this.baseUrl + '/aslDistrettoAmbito'
        return this.http.get( url, options ).pipe(
            map( response => response.json() as AslDistrettoAmbito[] ),
            catchError(( res: Response ) => this.onError( res ) )
        );
    }


    stampaPratica( cit_id: string, num_pratica: string ): Observable<Response | any> {
        let cpHeaders = new Headers( { 'X-Request-ID': uuid() } );
        let options = new RequestOptions( { headers: cpHeaders } );
        options.responseType = ResponseContentType.Blob;
        const url = this.baseUrl + '/download/' + num_pratica;
        return this.http.get( url, options ).pipe(
                map( response => {
                    let m = new Map<string, any>();
                    let name = response.headers.get( "Content-Disposition" );
                    name = name.substring( name.lastIndexOf( '=' ) + 1, name.length );
                    m.set( "name", name );
                    m.set( "file", new Blob( [response.blob()], { type: 'application/pdf' } ) );
                    return m;
                } ), 
                catchError(( res: Response ) => this.onError( res ) ));
    }

    downloadExportRicerca( dataTablesParameters: any, ricerca: Ricerca ): Observable<Response | any> {
        let cpHeaders = new Headers( { 'X-Request-ID': uuid() } );
        let options = new RequestOptions( { headers: cpHeaders } );
        options.responseType = ResponseContentType.Blob;
        if ( dataTablesParameters == null ) dataTablesParameters = "{}";
        var requestVar = new RicercaRichiesteIn();
        requestVar.dataTablesParameter = dataTablesParameters;
        requestVar.ricerca = ricerca;
        const url = this.baseUrl + '/downloadExportRicerca';
        return this.http.post( url, requestVar, options ).pipe(
                map( response => {
                    let m = new Map<string, any>();
                    let name = response.headers.get( "Content-Disposition" );
                    name = name.substring( name.lastIndexOf( '=' ) + 1, name.length );
                    m.set( "name", name );
                    m.set( "file", new Blob( [response.blob()], { type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet' } ) );
                    return m;
                } ), 
                catchError(( res: Response ) => this.onError( res ) ));
    }


    private setHeaders() {
        this.headers = new Headers();
        this.headers.append( 'Content-Type', 'application/json' );
        this.headers.append( 'Accept', 'application/pdf' );

    }

    private extractData( res: Response ) {
        let body = res.json();
        return body;
    }


    private handleError( error: Response | any ) {
        console.error( error.message || error );
        return Observable.throw( error );
    }

    chiudiAlert() {
        this.messaggioFeedback = null;
        this.idFeedback = null;
    }

    onError( res: Response ) {
        const statusCode = res.status;
        const body = res.json();
        const error = {
            statusCode: statusCode,
            error: body.descrizione
        };
        return throwError( error );
    }
}
