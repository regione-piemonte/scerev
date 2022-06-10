/*
 Copyright Regione Piemonte - 2022
 SPDX-License-Identifier: EUPL-1.2
*/
import { ActivatedRoute, Router } from "@angular/router";
import { NgbDatepicker, NgbModal } from "@ng-bootstrap/ng-bootstrap";
import { Subject } from "rxjs";
import { DataTableDirective } from "angular-datatables";
import { RicercaRichiesteIn } from "src/app/dto/RicercaRichiesteIn";
import { RicercaRichiesteOut } from "src/app/dto/RicercaRichiesteOut";
import { TipologieMedico } from "src/app/dto/TipologieMedico";
import { ListaStati } from "src/app/dto/ListaStati";
import { ScerevBOClient } from "src/app/ScerevBOClient";
import { Component, ViewChild, OnInit } from "@angular/core";
import { NgbDatepickerI18n } from "@ng-bootstrap/ng-bootstrap";
import { I18n, CustomDatepickerI18n } from "src/app/i18n/datepicker-i18n";
import { NgbDateStruct } from "@ng-bootstrap/ng-bootstrap";
import { Ricerca } from "src/app/dto/Ricerca";
import { RicercaRichiesteOutDT } from "src/app/dto/RicercaRichiesteOutDT";
import { Asl } from "src/app/dto/Asl";
import { MomentDateFormatter } from "src/app/MomentDateFormatter";
import * as $ from "jquery";
import { AslDistrettoAmbito } from "../dto/AslDistrettoAmbito";
import { Distretto } from "../dto/Distretto";
import { Ambito } from "../dto/Ambito";

@Component( {
    selector: 'app-ricerca',
    templateUrl: './ricerca.component.html',
    styleUrls: ['./ricerca.component.css'],
    providers: [I18n, { provide: NgbDatepickerI18n, useClass: CustomDatepickerI18n }] // define custom NgbDatepickerI18n provider
} )

export class RicercaComponent implements OnInit {
    model: NgbDateStruct;

    tabActive: string = '';
    dtOptions: DataTables.Settings = {};
    dtTrigger: Subject<any> = new Subject();

    @ViewChild( DataTableDirective, { static: false } )
    datatableElement: DataTableDirective;

    ricerca: Ricerca = new Ricerca();
    listaRichieste: RicercaRichiesteOut[]

    listaTipologieMedico: TipologieMedico[];
    listaListaStati: ListaStati[];
    listaAsl: Asl[];
    numTab: number = 0;
    listaAslDistrettoAmbito: AslDistrettoAmbito[];
    distretti: Distretto[];
    ambiti: Ambito[];
    dtParameters: any;
    backAction: string;
    returned: number = 0; // 0=default, 1=return from dettaglio, 2=change tab to 'archivio'
    tabs = ['Richieste aperte', 'Archivio richieste'];
    active: string;
    
    constructor( private router: Router,
        private route: ActivatedRoute,
        public client: ScerevBOClient,
        private modalService: NgbModal ) { }

    ngOnInit() {
        this.avviaSpinner();

        this.client.getTipologieMedico().subscribe(
            tipologie => {
                this.listaTipologieMedico = tipologie
                this.listaTipologieMedico = this.listaTipologieMedico.sort((a,b) => a.descrizione.localeCompare(b.descrizione));
            },
            error => {
                window.scrollTo( { top: 0, left: 0, behavior: 'smooth' } );
                this.client.idFeedback = 500;
                this.client.messaggioFeedback = "Errore get tipologie medico:" + error.error;
            }
        )



        this.route.params.subscribe(params => {
            this.backAction = params['action'];
            if(this.backAction == 'back') {
                this.returned = 1;
                this.tabActive = params['tab'];
                this.ricerca = this.client.ricerca;
                this.datDa = this.client.datDa;
                this.datA = this.client.datA;
                this.datNasc = this.client.datNasc;
                // this.listaRichieste = this.client.listaRichieste;
                if(this.tabActive == 'archivio') this.numTab = 1;
            }
            this.active = this.tabs[this.numTab];
        })

        

        this.client.getListaStati( this.numTab ).subscribe(
            stati => {
                this.listaListaStati = stati
            },
            error => {
                window.scrollTo( { top: 0, left: 0, behavior: 'smooth' } );
                this.client.idFeedback = 500;
                this.client.messaggioFeedback = "Errore get lista stati:" + error.error;
            }
        )

        this.client.getAllAslDistrettoAmbito().subscribe(
            aslDistrettoAmbito => {
                this.listaAslDistrettoAmbito = aslDistrettoAmbito
                if ( this.client.isOperatore() ) {
                    this.listaAslDistrettoAmbito = this.listaAslDistrettoAmbito.filter( a => a.id.toString() == this.client.userInfo.codASL.find(b => b == a.id.toString()));
                    if(this.listaAslDistrettoAmbito.length == 1){
                        this.distretti = this.listaAslDistrettoAmbito[0].distretti;
                    }
                }
                if(this.returned) {
                    this.getDistretti();
                    this.getAmbiti();
                }
                this.returned = 0;
            },
            error => {
                window.scrollTo( { top: 0, left: 0, behavior: 'smooth' } );
                this.client.idFeedback = 500;
                this.client.messaggioFeedback = "Errore get lista aslDistrettoAmbito:" + error.error;
            }
        )

        if ( this.tabActive == '' ) {
            this.tabActive = "aperte";
            this.ricerca.stato = "aperte";
        }

        this.dtOptions = {
            deferRender: true,
            searching: false,
            retrieve: true,
            ordering: true,
            serverSide: true,
            processing: !this.returned,
            order: [0, this.tabActive=="archivio" ? 'desc' : 'asc'],
            pageLength : this.client.pageLenght,
            displayStart : this.client.pageStart,
            ajax: ( dataTablesParameters: any, callback ) => {
                    if(this.returned === 1) {
                        dataTablesParameters = this.client.dtparams;
                    }

                    this.client.getRichieste( dataTablesParameters, this.ricerca, this.returned ).subscribe( response => {
    //                    this.client.chiudiAlert();
                        this.dtParameters = dataTablesParameters;
                        this.listaRichieste = response.data;
                        this.client.datDa = this.datDa;
                        this.client.datA = this.datA;
                        this.client.datNasc = this.datNasc;
                        this.dtTrigger.next();
                        this.stopSpinner();
                        callback( {
                            recordsTotal: response.recordsTotal,
                            recordsFiltered: response.recordsFiltered,
                            data: []
                        } );
                    },
                        error => {
                            window.scrollTo( { top: 0, left: 0, behavior: 'smooth' } );
                            this.stopSpinner();
                            this.client.idFeedback = 500;
                            this.client.messaggioFeedback = "Errore Ricerca: " + error.error;
                        } );
                // } else {
                //     callback({
                //         recordsTotal: this.client.recordsTotal,
                //         recordsFiltered: this.client.recordsFiltered,
                //         data: this.ricerca
                //     });
                //     // this.returned = false;
                //     // this.stopSpinner();
                // }
            },
            columns: [{ data: 'numRichiesta' },
            { data: 'nomeAssistito' },
            { data: 'asl' },
            { data: 'medPed' },
            { data: 'distretto' },
            { data: 'ambito' },
            { data: 'stato' },
            { data: 'dataCreazione' },
            { data: 'nomeOperatore' },
            { data: '',  orderable: false }],
            language: {
                url: "//cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/Italian.json"
            }
        },
            error => {
                window.scrollTo( { top: 0, left: 0, behavior: 'smooth' } );
                this.client.idFeedback = 500;
                this.client.messaggioFeedback = "Errore nella datatables:" + error.error;
            };
    }

    open( content ) {
        this.client.cronologiaNumPratica = content._parentView.oldValues[0];
        this.modalService.open( content, { size: 'xl' } );
        this.router.navigate( ["../ricerca/cronologia"], { skipLocationChange: true } );
    }

    changeTab( form ) {
        this.client.chiudiAlert();
        console.log("change " + this.tabActive);
        if ( this.tabActive == "archivio" ) {
            this.datatableElement.dtInstance.then(( dtInstance: DataTables.Api ) => {
                dtInstance.order([0,'asc']);
            } );
            this.tabActive = "aperte";
            this.numTab = 0;
            this.client.getListaStati( this.numTab ).subscribe(
                stati => {
                    this.listaListaStati = stati
                },
                error => {
                    window.scrollTo( { top: 0, left: 0, behavior: 'smooth' } );
                    this.client.idFeedback = 500;
                    this.client.messaggioFeedback = "Errore get lista stati:" + error.error;
                }
            );
            this.ricerca.stato = null;
            this.ricercaAperte();
        } else if ( this.tabActive == "aperte" ) {
            this.avviaSpinner();
            this.tabActive = "archivio";
            this.datatableElement.dtInstance.then(( dtInstance: DataTables.Api ) => {
                dtInstance.order([0,'desc']);
            } );
            
            this.numTab = 1;
            this.client.getListaStati( this.numTab ).subscribe(
                stati => {
                    this.listaListaStati = stati
                },
                error => {
                    window.scrollTo( { top: 0, left: 0, behavior: 'smooth' } );
                    this.client.idFeedback = 500;
                    this.client.messaggioFeedback = "Errore get lista stati:" + error.error;
                }
            );
            this.ricerca.stato = null;
            
            this.datatableElement.dtInstance.then(( dtInstance: DataTables.Api ) => {
                this.returned = 2;
                dtInstance.draw();
                this.returned = 0;
                this.stopSpinner();
            } );

        }
    }

    getDettaglio( index: any ) {
        this.avviaSpinner();
        this.client.getDettaglio( this.client.listaRichieste[index].numPratica ).subscribe(
            dettagli => {
                this.stopSpinner();
                this.router.navigate( ["../dettaglio", {tab: this.tabActive}], { relativeTo: this.route, skipLocationChange: true } );
            },
            error => {
                window.scrollTo( { top: 0, left: 0, behavior: 'smooth' } );
                this.stopSpinner();
                this.client.idFeedback = 500;
                this.client.messaggioFeedback = "Errore durante l'apertura di una pratica:" + error.error;
            }
        );
    }

    testEmitter( event: any ) {
        $( "#btnApplica" ).css( "display", "none" );
        $( "#btnLoading" ).css( "display", "none" );
    }

    ricercaAperte() {
        //        var stato = ( <HTMLInputElement>document.getElementById( "statofiltro" ) ).value;
        //        var tipologia = ( <HTMLInputElement>document.getElementById( "listaTipologieMedico" ) ).value;
        //        var medici = ( <HTMLInputElement>document.getElementById( "defaultInline1" ) ).value;
        //        var pediatri = ( <HTMLInputElement>document.getElementById( "defaultInline2" ) ).value;
        //        var tutti = ( <HTMLInputElement>document.getElementById( "defaultInline3" ) ).value;
        //
        //        if ( stato == "" ) {
        //            alert( "Inserire lo Stato poichè è un parametro obbligatorio" );
        //        } else if ( tipologia == "" ) {
        //            alert( "Inserire la Tipologia poichè è un parametro obbligatorio" );
        //        } else if ( medici == "" && pediatri == "" && tutti == "" ) {
        //            alert( "Specificare se medici/pediatri o tutti" );
        //        } else {
        this.ricerca.dataInsDa = ( <HTMLInputElement>document.getElementById( "dpDa" ) ).value;
        if ( this.ricerca.dataInsDa == "" ) this.ricerca.dataInsDa = null;
        this.ricerca.dataInsA = ( <HTMLInputElement>document.getElementById( "dpA" ) ).value;
        if ( this.ricerca.dataInsA == "" ) this.ricerca.dataInsA = null;
        this.ricerca.dataNascita = ( <HTMLInputElement>document.getElementById( "dpNascita" ) ).value;
        if ( this.ricerca.dataNascita == "" ) this.ricerca.dataNascita = null;
        if (this.ricerca.stato == null || this.ricerca.stato == undefined || this.ricerca.stato == '') {
            if ( this.tabActive == "aperte" ) this.ricerca.stato = "aperte";
            if ( this.tabActive == "archivio" ) this.ricerca.stato = "archivio";
        }
        this.avviaSpinner();
        this.datatableElement.dtInstance.then(( dtInstance: DataTables.Api ) => {
            dtInstance.draw();
        } );
        //        }
    }

    avviaSpinner() {
        window.scrollTo( { top: 0, left: 0, behavior: 'smooth' } );
        $( '#overlay' ).fadeIn();

        $( "#btnApplica" ).css( "display", "none" );
        $( "#btnLoading" ).css( "display", "" );
    }

    stopSpinner() {
        $( "#btnApplica" ).css( "display", "" );
        $( "#btnLoading" ).css( "display", "none" );

        $( '#overlay' ).fadeOut();
    }

    chiudiSuccess() {
        $( "#alertSuccesso" ).css( "display", "none" );
    }

    chiudiDanger() {
        $( "#alertFail" ).css( "display", "none" );
    }

    focusDate(a: any) {
        console.log(a);
    }

    datDa: any;
    datA: any;
    datNasc: any;

    pulisciFiltri() {
        this.client.chiudiAlert();
        ( <HTMLInputElement>document.getElementById( "richiesta" ) ).value = "";
        this.ricerca.numRichiesta = null;
        ( <HTMLInputElement>document.getElementById( 'statofiltro' ) ).value = "";
        this.ricerca.stato = null;
        ( <HTMLInputElement>document.getElementById( "listaTipologieMedico" ) ).value = "";
        this.ricerca.tipologiaMedico = null;;
        ( <HTMLInputElement>document.getElementById( "dpDa" ) ).value = null;
        this.ricerca.dataInsDa = null;;
        this.datDa = null;
        //( <HTMLInputElement>document.getElementById( "dpDa" ) ).placeholder = "gg/mm/aaaa";
        ( <HTMLInputElement>document.getElementById( "dpA" ) ).value = null;
        this.ricerca.dataInsA = null;;
        this.datA = null;
        //( <HTMLInputElement>document.getElementById( "dpA" ) ).placeholder = "gg/mm/aaaa";
        ( <HTMLInputElement>document.getElementById( "distretto" ) ).value = " ";
        this.ricerca.distretto = null;;
        ( <HTMLInputElement>document.getElementById( "ambito" ) ).value = " ";
        this.ricerca.ambitoDomicilio = null;;
        ( <HTMLInputElement>document.getElementById( "defaultInline1" ) ).checked = false;
        this.ricerca.medPed = null;;
        ( <HTMLInputElement>document.getElementById( "defaultInline2" ) ).checked = false;
        ( <HTMLInputElement>document.getElementById( "defaultInline3" ) ).checked = false;
        ( <HTMLInputElement>document.getElementById( "cognomeAssistito" ) ).value = "";
        this.ricerca.cognomeAssistito = null;;
        ( <HTMLInputElement>document.getElementById( "nomeAssistito" ) ).value = "";
        this.ricerca.nomeAssistito = null;;
        ( <HTMLInputElement>document.getElementById( "CfAssistito" ) ).value = "";
        this.ricerca.codiceFiscale = null;;
        ( <HTMLInputElement>document.getElementById( "dpNascita" ) ).value = null;
        this.ricerca.dataNascita = null;;
        this.datNasc = null;
        //( <HTMLInputElement>document.getElementById( "dpNascita" ) ).placeholder = "gg/mm/aaaa";
        ( <HTMLInputElement>document.getElementById( "cognomeOperatore" ) ).value = "";
        this.ricerca.cognomeOperatore = null;;
        ( <HTMLInputElement>document.getElementById( "nomeOperatore" ) ).value = "";
        this.ricerca.nomeOperatore = null;;
        ( <HTMLInputElement>document.getElementById( "customCheck1" ) ).checked = false;
        this.ricerca.inCarico = null;;
        this.ricerca.asl = null;
        this.ricerca.stato = null;
        this.ricerca.tipologia = null;
        this.ricerca.distrettoDomicilio = null;
    }

    getDistretti() {
        if(!this.returned) {
            this.ricerca.distrettoDomicilio = null;
            this.ricerca.ambitoDomicilio = null;
            this.distretti = null;
            this.ambiti = null;
        }
        if(this.listaAslDistrettoAmbito != null && this.listaAslDistrettoAmbito != undefined && this.ricerca.asl != null && this.ricerca.asl != '') {
            this.distretti = this.listaAslDistrettoAmbito.filter( asl => asl.id.toString() == this.ricerca.asl)[0].distretti;
        }
    }

    getAmbiti() {
        if(!this.returned) {
            this.ricerca.ambitoDomicilio = null;
            this.ambiti = null;
        }
        if(this.distretti != null && this.distretti != undefined && this.ricerca.distrettoDomicilio != null && this.ricerca.distrettoDomicilio != '') {
            this.ambiti = this.distretti.filter(distretto => distretto.descrizione == this.ricerca.distrettoDomicilio)[0].ambiti;
        }
    }

    exportRicerca() {
        this.avviaSpinner();
        // this.ricerca.dataInsDa = ( <HTMLInputElement>document.getElementById( "dpDa" ) ).value;
        // if ( this.ricerca.dataInsDa == "" ) this.ricerca.dataInsDa = null;
        // this.ricerca.dataInsA = ( <HTMLInputElement>document.getElementById( "dpA" ) ).value;
        // if ( this.ricerca.dataInsA == "" ) this.ricerca.dataInsA = null;
        // this.ricerca.dataNascita = ( <HTMLInputElement>document.getElementById( "dpNascita" ) ).value;
        // if ( this.ricerca.dataNascita == "" ) this.ricerca.dataNascita = null;
        // if (this.ricerca.stato == null || this.ricerca.stato == undefined || this.ricerca.stato == '') {
        //     if ( this.tabActive == "aperte" ) this.ricerca.stato = "aperte";
        //     if ( this.tabActive == "archivio" ) this.ricerca.stato = "archivio";
        // }
        this.client.downloadExportRicerca(this.dtParameters, this.ricerca).subscribe(
            ( res ) => {
                this.stopSpinner();
                let name: string = res.get( "name" ) as string;
                let file: Blob = res.get( "file" ) as Blob;
                var fileURL = URL.createObjectURL( file );
                var link = document.createElement( 'a' );
                link.href = window.URL.createObjectURL( file );
                link.download = name;
                document.body.appendChild( link );
                link.click();
                document.body.removeChild( link );
            },
            errorCode => {
                this.stopSpinner();
                var str = errorCode._body;
                var objmsg = JSON.parse( str );
                console.log( objmsg );
            }
        );
    }
}
function getDataNg(dataIns: string): any {
    if(dataIns != null && dataIns != undefined && dataIns != '') {
        let data = new Date(dataIns);
        return {
            year: data.getFullYear(),
            month: data.getMonth(),
            day: data.getDay()
        }
    }
    return null;
}

