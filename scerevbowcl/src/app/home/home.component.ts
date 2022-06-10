/*
 Copyright Regione Piemonte - 2022
 SPDX-License-Identifier: EUPL-1.2
*/
import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from "@angular/router";
import { ScerevBOClient } from "src/app/ScerevBOClient";
import * as $ from "jquery";

@Component( {
    selector: 'app-home',
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.css']
} )
export class HomeComponent implements OnInit {
    
    enabled: boolean;  
    loading: boolean;

    constructor( private router: Router,
        public client: ScerevBOClient,
        private route: ActivatedRoute ) { }

    ngOnInit() {
        
        this.avviaSpinner();
        
        this.client.idFeedback = null;
        this.client.messaggioFeedback = null;
        this.enabled = false;
        
        $( "#buttonAccedi" ).css( "display", "none" );
        $( "#buttonAttendi" ).css( "display", "" );
        
        this.client.login().subscribe(
                response => {
                    this.stopSpinner();
                    $( "#buttonAccedi" ).css( "display", "" );
                    $( "#buttonAttendi" ).css( "display", "none" );
                   this.enabled = true;
                   this.client.chiudiAlert();
					this.toRicerca();
                },
                error => {
                    this.stopSpinner();
                    $( "#buttonAccedi" ).css( "display", "none" );
                    $( "#buttonAttendi" ).css( "display", "" );
                    this.enabled = false;
                    this.client.idFeedback = 500;
                    this.client.messaggioFeedback = "Errore login: "+error.error;
                }
            )
    }
    
    toRicerca() {
        this.router.navigate( ["../ricerca"], { relativeTo: this.route, skipLocationChange: true } );
        
    }
    
    avviaSpinner() {
        $( '#overlay' ).fadeIn();
    }

    stopSpinner() {
        $( '#overlay' ).fadeOut();
    }

    chiudiDanger() {
        //
    }
}
