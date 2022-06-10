/*
 Copyright Regione Piemonte - 2022
 SPDX-License-Identifier: EUPL-1.2
*/
import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from "@angular/router";
import { NgbDate, NgbCalendar, NgbDateParserFormatter, NgbModalConfig, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { MomentDateFormatter } from "src/app/MomentDateFormatter";
import { TipologieMedico } from "src/app/dto/TipologieMedico";
import { Dettaglio } from "src/app/dto/Dettaglio";
import { ScerevBOClient } from "src/app/ScerevBOClient";
import { I18n, CustomDatepickerI18n } from "src/app/i18n/datepicker-i18n";
import { NgbDatepickerI18n, NgbDateStruct } from "@ng-bootstrap/ng-bootstrap";
import { RicercaComponent } from "src/app/ricerca/ricerca.component";
import { AccettaOut } from "src/app/dto/AccettaOut";
import { AccettaIn } from "src/app/dto/AccettaIn";
import { RichiediRettificaIn } from "src/app/dto/RichiediRettificaIn";
import { RespingiOut } from "src/app/dto/RespingiOut";
import { RespingiIn } from "src/app/dto/RespingiIn";
import { AggiungiNotaIn } from "src/app/dto/AggiungiNotaIn";
import { ChiudeIn } from "src/app/dto/ChiudeIn";
import * as $ from "jquery";
import { formatDate } from '@angular/common';
import { Motivazione } from '../dto/Motivazione';

@Component( {
    selector: 'app-dettaglio',
    templateUrl: './dettaglio.component.html',
    styleUrls: ['./dettaglio.component.css'],
    providers: [NgbModalConfig, NgbModal, I18n, { provide: NgbDatepickerI18n, useClass: CustomDatepickerI18n }]
} )
export class DettaglioComponent implements OnInit {
    model: NgbDateStruct;
    public isCollapsedMedico = true;
    public isCollapsedCronologia = true;
    public isCollapsed = false;
    hoveredDate: NgbDate | null = null;
    fromDate: NgbDate | null;
    public formatter: MomentDateFormatter = new MomentDateFormatter();
    toDate: NgbDate | null;

    //Dichiarazione variabili di input per i bottoni nel dettaglio

    nota: string;
    numPratica: string;
    tipologia: string;
    flMedicoRevocato: boolean;
    familiare: boolean;
    territoriale: boolean;
    massimale_temp: boolean;
    massimale: boolean;
    medico_generico: boolean;
	pediatra: boolean;
    data_fine: string;
    data_inizio: string;
    data_inizio_place: string;
    data_fine_place: string;
    motivazione: string;
    nota_assistito: string;
    associazione: boolean;
    flagRevocaMedico: boolean;


    constructor( private router: Router,
        private route: ActivatedRoute,
        public client: ScerevBOClient,
        private calendar: NgbCalendar,
        private modalService: NgbModal,
        config: NgbModalConfig ) {
        config.backdrop = 'static';
        config.keyboard = false;
        //        this.fromDate = calendar.getToday();
        //        this.toDate = calendar.getNext( calendar.getToday(), 'd', 10 );
    }


    //listaTipologieMedico: TipologieMedico[];
    dettaglio: Dettaglio;
    accettaOut: AccettaOut;
    accettaIn: AccettaIn;
    sceltaDiModal: string = "ACCETTA";
    listaMotivazioni: Motivazione[];

    currentDate = new Date();


    open( content, sceltaModal: string ) {
        switch ( sceltaModal ) {
            case "INTERNA": {
                this.modalService.open( content );
                document.getElementById( "titolo" ).innerHTML = "AGGIUNGI NOTA INTERNA";
                document.getElementById( "domanda" ).style.display = "none";
                document.getElementById( "avviso" ).style.display = "none";
                document.getElementById( "motivazioneText" ).style.display = "none";
                document.getElementById( "motivazioneSelect" ).style.display = "none";
                document.getElementById( "notaRichiedenteArea" ).style.display = "none";
                document.getElementById( "notaRichiedenteText" ).style.display = "none";
                document.getElementById( "si" ).innerHTML = "Salva";
                document.getElementById( "no" ).innerHTML = "Annulla";
                this.sceltaDiModal = "INTERNA";
                break;
            }

            case "RETTIFICA": {
                this.modalService.open( content );
                this.sceltaDiModal = "RETTIFICA";
                this.listaMotivazioni = this.dettaglio.motivazioni.filter(a => a.tipoMotivazione == "Rettifica");
                break;
            }

            case "RESPINGI": {
                this.modalService.open( content );
                document.getElementById( "titolo" ).innerHTML = "RESPINGI";
                document.getElementById( "domanda" ).innerHTML = "Sei sicuro di voler respingere l'assegnazione del medico ?";
                document.getElementById( "avviso" ).innerHTML = "Se respingi una richiesta devi indicare una motivazione";
                this.sceltaDiModal = "RESPINGI";
                this.listaMotivazioni = this.dettaglio.motivazioni.filter(a => a.tipoMotivazione == "Rifiuto");
                break;
            }

            case "CHIUDE": {
                this.modalService.open( content );
                document.getElementById( "titolo" ).innerHTML = "CHIUDE";
                document.getElementById( "domanda" ).innerHTML = "Sei sicuro di voler chiudere l'assegnazione del medico ?";
                document.getElementById( "avviso" ).style.display = "none";
                document.getElementById( "motivazioneText" ).style.display = "none";
                document.getElementById( "motivazioneSelect" ).style.display = "none";
                document.getElementById( "notaRichiedenteArea" ).style.display = "none";
                document.getElementById( "notaRichiedenteText" ).style.display = "none";
                document.getElementById( "si" ).innerHTML = "Salva";
                document.getElementById( "no" ).innerHTML = "Annulla";
                this.sceltaDiModal = "CHIUDE";
                break;
            }

            case "ACCETTA": {
                this.modalService.open( content );
                document.getElementById( "titolo" ).innerHTML = "ACCETTA RICHIESTA";
                document.getElementById( "domanda" ).innerHTML = "Sei sicuro di voler accettare l'assegnazione del medico ?"
                document.getElementById( "avviso" ).style.display = "none";
                document.getElementById( "motivazioneText" ).style.display = "none";
                document.getElementById( "motivazioneSelect" ).style.display = "none";
                document.getElementById( "notaRichiedenteArea" ).style.display = "none";
                document.getElementById( "notaRichiedenteText" ).style.display = "none";
                document.getElementById( "notaInternaText" ).innerHTML = "Nota interna (sarà riportata su AURA)";
                break;
            }
        }
    }

    avviaSpinner() {
        $( '#overlay' ).fadeIn();
    }

    stopSpinner() {
        $( '#overlay' ).fadeOut();
    }

    tab:string;

    ngOnInit() {
        this.stopSpinner();

        // this.client.getTipologieMedico().subscribe(
        //     tipologie => {
        //         this.listaTipologieMedico = tipologie
        //     }
        // )
        this.dettaglio = this.client.dettaglio;
        this.dettaglio.tipologieProfilo = this.dettaglio.tipologieProfilo.sort((a,b)=> a.descTipologia.localeCompare(b.descTipologia));
        this.numPratica = this.dettaglio.numPratica;
        // this.data_inizio = formatDate(this.dettaglio.dataInizioAss, 'yyyy-MM-dd',);
        if (this.dettaglio.dataInizioAss != null) {
            this.data_inizio = this.dettaglio.dataInizioAss;
            this.data_inizio_place = this.dettaglio.dataInizioAss;
        } else {
            this.data_inizio = formatDate(this.currentDate, 'dd/MM/yyyy', 'en');
        }
        console.log(this.data_inizio);
        if (this.dettaglio.dataFineAss != null) {
            this.data_fine_place = this.dettaglio.dataFineAss;
        }

        if(this.dettaglio.tipologieProfilo.length == 1) {
            this.tipologia = this.dettaglio.tipologieProfilo[0].codTipologia;
        }

/*        if ( this.dettaglio.inCarico == ( this.client.userInfo.nome + ' ' + this.client.userInfo.cognome ) ) {
            if(this.dettaglio.stato == 'In lavorazione' || this.dettaglio.stato == 'Completata')
                if(document.getElementById( "notaInterna" ) != null)
                    ( <HTMLInputElement>document.getElementById( "notaInterna" ) ).disabled = false;
            if(this.dettaglio.stato == 'In lavorazione') {
                if(document.getElementById( "rettifica" ) != null)
                    ( <HTMLInputElement>document.getElementById( "rettifica" ) ).disabled = false;
                if(document.getElementById( "respingi" ) != null)
                    ( <HTMLInputElement>document.getElementById( "respingi" ) ).disabled = false;
                if(document.getElementById( "accetta" ) != null)
                    ( <HTMLInputElement>document.getElementById( "accetta" ) ).disabled = false;
            }
            if(document.getElementById( "chiude" ) != null)
                ( <HTMLInputElement>document.getElementById( "chiude" ) ).disabled = false;
        }*/

        this.pediatra = this.dettaglio.deroghe.derogaPediatra;
        this.familiare = this.dettaglio.deroghe.ricongiungimentoFamiliare;
        this.flMedicoRevocato = this.dettaglio.deroghe.medicoRevocato;
        this.massimale = this.dettaglio.deroghe.derogaMassimale;
        this.massimale_temp = this.dettaglio.deroghe.derogaMassimaleTemp;
        this.territoriale = this.dettaglio.deroghe.derogaTerritoriale;
        this.medico_generico = this.dettaglio.deroghe.medicoGenerico;
        this.associazione = this.dettaglio.deroghe.derogaAssociazione;

        this.client.idFeedback = null;
        this.client.cronologiaNumPratica = this.dettaglio.numPratica;
        this.router.navigate( ["../dettaglio/cronologia"], { skipLocationChange: true } );


        this.route.params.subscribe(params => {
            if(!this.tab)
                this.tab = params['tab'];
        })
    }

    backButton() {
        this.client.idFeedback = null;
        this.router.navigate( ["../ricerca"], { relativeTo: this.route, skipLocationChange: true } );
    }

    goBack() {
        this.client.idFeedback = null;
        this.router.navigate( ["../ricerca", {action: 'back', tab: this.tab}], { relativeTo: this.route, skipLocationChange: true } );
    }

    prendiInCarico() {
        this.avviaSpinner();
        this.client.prendiInCarico( this.dettaglio.numPratica ).subscribe(
            response => {
                this.client.getDettaglio( this.numPratica ).subscribe(
                    dettagli => {
                        this.ngOnInit();
                    },
                    error => {
                        window.scrollTo( { top: 0, left: 0, behavior: 'smooth' } );
                        this.stopSpinner();
                        this.client.idFeedback = 500;
                        this.client.messaggioFeedback = "Errore durante l'apertura di una pratica:" + error.error;
                    }
                );
            },
            error => {
                window.scrollTo( { top: 0, left: 0, behavior: 'smooth' } );
                this.stopSpinner();
                this.client.idFeedback = 500;
                this.client.messaggioFeedback = "Errore presa in carico: " + error.error;
            }
        )
    }


    onDateSelection( date: NgbDate ) {
        if ( !this.fromDate && !this.toDate ) {
            this.fromDate = date;
        } else if ( this.fromDate && !this.toDate && date && date.after( this.fromDate ) ) {
            this.toDate = date;
        } else {
            this.toDate = null;
            this.fromDate = date;
        }
    }

    onDateSelectionFrom( date: NgbDate ) {
        this.fromDate = date;
        this.data_inizio = this.formatter.format(this.fromDate);
    }

    onDateSelectionTo( date: NgbDate ) {
        this.toDate = date;
        this.data_fine = this.formatter.format(this.toDate);
    }
    
    onDateWritten(date: string) {
        this.toDate = this.validateInput(this.toDate, date);
        this.data_fine = this.formatter.format(this.toDate);

    }

    isHovered( date: NgbDate ) {
        return this.fromDate && !this.toDate && this.hoveredDate && date.after( this.fromDate ) && date.before( this.hoveredDate );
    }

    chiudiSuccess() {
        $( "#alertSuccesso" ).css( "display", "none" );
    }

    chiudiDanger() {
        $( "#alertFail" ).css( "display", "none" );
    }

    isInside( date: NgbDate ) {
        return this.toDate && date.after( this.fromDate ) && date.before( this.toDate );
    }

    isRange( date: NgbDate ) {
        return date.equals( this.fromDate ) || ( this.toDate && date.equals( this.toDate ) ) || this.isInside( date ) || this.isHovered( date );
    }

    validateInput( currentValue: NgbDate | null, input: string ): NgbDate | null {
        const parsed = this.formatter.parse( input );
        return parsed && this.calendar.isValid( NgbDate.from( parsed ) ) ? NgbDate.from( parsed ) : currentValue;
    }

    getAllegato( i, j ) {
        this.avviaSpinner();
        this.client.getAllegato( this.dettaglio.dati_richiedente.codice_fiscale, this.dettaglio.numPratica, this.client.dettaglio.allegati[i].allegati[j].pkAllegato ).subscribe(
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

    stampaPratica() {
        this.client.idFeedback = null;
        this.avviaSpinner();
        this.client.stampaPratica( this.dettaglio.dati_richiedente.codice_fiscale, this.dettaglio.numPratica ).subscribe(
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
            error => {
                window.scrollTo( { top: 0, left: 0, behavior: 'smooth' } );
                this.stopSpinner();
                this.client.idFeedback = 500;
                this.client.messaggioFeedback = "Errore durante la stampa: " + error.error;
            }
        );
    }

    SiSalva( operazione ) {
        this.client.idFeedback = null;
        this.avviaSpinner();
        switch ( operazione ) {
            case "INTERNA": {
                var inputInterna = new AggiungiNotaIn();
                inputInterna.notaInterna = this.nota;
                inputInterna.numeroPratica = this.numPratica;
                if ( inputInterna.notaInterna != undefined && inputInterna.notaInterna != "" ) {
                    this.client.aggiungiNotaInterna( inputInterna ).subscribe(
                        response => {
                            this.stopSpinner();
                            if ( response.id == 200 ) {
                                this.modalService.dismissAll();
                                this.client.idFeedback = response.id;
                                this.client.messaggioFeedback = response.descrizione;
                                this.router.navigate( ["../ricerca"], { skipLocationChange: true } );
                            } else {
                                this.modalService.dismissAll();
                                this.client.idFeedback = response.id;
                                this.client.messaggioFeedback = response.descrizione;
                                this.router.navigate( ["../ricerca"], { skipLocationChange: true } );
                            }
                        },
                        error => {
                            window.scrollTo( { top: 0, left: 0, behavior: 'smooth' } );
                            this.stopSpinner();
                            this.modalService.dismissAll();
                            this.client.idFeedback = 500;
                            this.client.messaggioFeedback = "Errore inserimento nota interna: " + error.error;
                        }
                    )
                } else {
                    this.stopSpinner();
                    alert( "Inserire una Nota!" );
                }
                break;
            }

            case "ACCETTA": {
                var inputAccetta = new AccettaIn();
                //Riempio l'input
                inputAccetta.numeroPratica = this.numPratica;
                inputAccetta.codiceFiscaleAssistito = this.dettaglio.dati_richiedente.codice_fiscale;
                inputAccetta.notaInterna = this.nota;
                inputAccetta.tipologia = this.tipologia;
                if(this.data_inizio == null && this.fromDate != null) {
                    this.data_inizio = this.formatter.format(this.fromDate);
                }
                inputAccetta.durataAssistenzaDa = this.data_inizio
                if( (this.data_fine == undefined || this.data_fine == null || this.data_fine == '') && this.toDate != null) {
                    this.data_fine = this.formatter.format(this.toDate);
                }
                inputAccetta.flDerogaPediatra = this.pediatra;
                inputAccetta.durataAssistenzaA = this.data_fine;
                inputAccetta.flMedicoGenerico = this.medico_generico;
                inputAccetta.flDerogaMassimale = this.massimale;
                inputAccetta.flDerogaMassimaleTmp = this.massimale_temp;
                inputAccetta.flDerogaTerritoriale = this.territoriale;
                inputAccetta.flRicongFamigliare = this.familiare;
                inputAccetta.flDerogaAssociazione = this.associazione;
                
                if (this.dettaglio.dati_medico_extra == null || 
                    this.dettaglio.dati_medico_extra.id != null || 
                    this.dettaglio.tipologieRichiesta.includes('Rinnovo assistenza')) {
                    inputAccetta.daChiudere = true;
                } else {
                    inputAccetta.daChiudere = this.flagRevocaMedico;
                }

                if ( inputAccetta.tipologia != undefined ) {
                    this.client.getAccetta( inputAccetta ).subscribe(
                        response => {
                            this.stopSpinner();
                            if ( response.id == 200 ) {
                                //Operazione andata a buon fine
                                this.modalService.dismissAll();
                                this.client.idFeedback = response.id;
                                this.client.messaggioFeedback = "Pratica accettata con successo";
                                this.router.navigate( ["../ricerca"], { skipLocationChange: true } );
                            } else if ( response.id == 400 ) {
                                //Operazione NON andata a buon fine 
                                window.scrollTo( { top: 0, left: 0, behavior: 'smooth' } );
                                this.stopSpinner();
                                this.modalService.dismissAll();
                                this.client.idFeedback = response.id;
                                this.client.messaggioFeedback = "Errore durante l'elaborazione della pratica: " + response.descrizione;
                            }
                        },
                        error => {
                            window.scrollTo( { top: 0, left: 0, behavior: 'smooth' } );
                            this.stopSpinner();
                            this.modalService.dismissAll();
                            this.client.idFeedback = 500;
                            this.client.messaggioFeedback = "Errore durante l'elaborazione della pratica: " + error.error;
                        }
                    )
                } else {
                    this.stopSpinner();
                    alert( "Scegliere una tipologia!" );
                }

                break;
            }

            case "RETTIFICA": {
                var inputRettifica = new RichiediRettificaIn();
                inputRettifica.nota_interna = this.nota;
                inputRettifica.nota_assistito = this.nota_assistito;
                inputRettifica.motivazione = this.motivazione;
                inputRettifica.id = parseInt( this.dettaglio.numPratica );
                if ( inputRettifica.motivazione != undefined && inputRettifica.nota_interna != undefined && inputRettifica.nota_interna != "" ) {
                    this.client.getRettifica( inputRettifica ).subscribe(
                        response => {
                            this.stopSpinner();
                            if ( response.id == 200 ) {
                                this.modalService.dismissAll();
                                this.client.idFeedback = response.id;
                                this.client.messaggioFeedback = "Rettifica effettuata con successo";
                                this.router.navigate( ["../ricerca"], { skipLocationChange: true } );
                            } else {
                                window.scrollTo( { top: 0, left: 0, behavior: 'smooth' } );
                                this.stopSpinner();
                                this.modalService.dismissAll();
                                this.client.idFeedback = response.id;
                                this.client.messaggioFeedback = "Errore durante l'elaborazione della pratica: " + response.descrizione;
                            }
                        },
                        error => {
                            window.scrollTo( { top: 0, left: 0, behavior: 'smooth' } );
                            this.stopSpinner();
                            this.modalService.dismissAll();
                            this.client.idFeedback = 500;
                            this.client.messaggioFeedback = "Errore durante l'elaborazione della pratica: " + error.error;
                        }
                    )
                } else {
                    this.stopSpinner();
                    alert( "Motivazione e Nota Interna sono due parametri obbligatori" );
                }
                break;
            }

            case "RESPINGI": {
                var inputRespingi = new RespingiIn();
                inputRespingi.nota_interna = this.nota;
                inputRespingi.nota_assistito = this.nota_assistito;
                inputRespingi.motivazione = this.motivazione;
                inputRespingi.id = parseInt( this.dettaglio.numPratica );
                if ( inputRespingi.nota_interna != undefined && inputRespingi.nota_assistito != undefined && inputRespingi.motivazione != undefined && inputRespingi.nota_interna != "" && inputRespingi.nota_assistito != "" ) {
                    this.client.getRespingi( inputRespingi ).subscribe(
                        response => {
                            this.stopSpinner();
                            if ( response.id == 200 ) {
                                this.modalService.dismissAll();
                                this.client.idFeedback = response.id;
                                this.client.messaggioFeedback = "Pratica respinta con successo";
                                this.router.navigate( ["../ricerca"], { skipLocationChange: true } );
                            } else {
                                window.scrollTo( { top: 0, left: 0, behavior: 'smooth' } );
                                this.stopSpinner();
                                this.modalService.dismissAll();
                                this.client.idFeedback = response.id;
                                this.client.messaggioFeedback = "Errore durante l'elaborazione della pratica: " + response.descrizione;
                            }
                        },
                        error => {
                            window.scrollTo( { top: 0, left: 0, behavior: 'smooth' } );
                            this.stopSpinner();
                            this.modalService.dismissAll();
                            this.client.idFeedback = 500;
                            this.client.messaggioFeedback = "Errore durante l'elaborazione della pratica: " + error.error;
                        }
                    )
                } else {
                    this.stopSpinner();
                    alert( "Tutti i campi sono obbligatori!" );
                }
                break;
            }

            case "CHIUDE": {
                var inputChiude = new ChiudeIn();
                inputChiude.numeroPratica = this.numPratica;
                inputChiude.notaInterna = this.nota;
                inputChiude.flMedicoRevocato = this.flMedicoRevocato;
                this.client.getChiude( inputChiude ).subscribe(
                    response => {
                        this.stopSpinner();
                        if ( response.id == 200 ) {
                            this.modalService.dismissAll();
                            this.client.idFeedback = response.id;
                            this.client.messaggioFeedback = "Chiusura della pratica effettuata con successo";
                            this.router.navigate( ["../ricerca"], { skipLocationChange: true } );
                        } else {
                            this.modalService.dismissAll();
                            this.client.idFeedback = response.id;
                            this.client.messaggioFeedback = response.descrizione;
                            this.router.navigate( ["../ricerca"], { skipLocationChange: true } );
                        }
                    },
                    error => {
                        window.scrollTo( { top: 0, left: 0, behavior: 'smooth' } );
                        this.stopSpinner();
                        this.modalService.dismissAll();
                        this.client.idFeedback = 500;
                        this.client.messaggioFeedback = "Errore durante l'elaborazione della pratica: " + error.error;
                    }
                )
            }
        }
    }

    toggleRevocaMedico(event) {
        if ( event.target.checked ) {
            this.flagRevocaMedico = true;
        }
    }
}
