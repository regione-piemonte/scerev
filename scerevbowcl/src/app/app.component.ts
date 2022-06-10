/*
 Copyright Regione Piemonte - 2022
 SPDX-License-Identifier: EUPL-1.2
*/
import { Component } from '@angular/core';
import { Router } from "@angular/router";
import { environment } from "src/environments/environment";
import { UserInfo } from './dto/UserInfo';
import { ScerevBOClient } from './ScerevBOClient';

@Component( {
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.css']
} )
export class AppComponent {
    public loading = false; 
    title = 'scerevwcl';
	
    constructor( private router: Router,
				 public client: ScerevBOClient
		 ) {
    }

    ngOnInit() {
        this.router.navigate( ["home"], { skipLocationChange: true } );
    }

    ApriHome() {
    }
    
    paginaPersonale() {
        window.open(environment.profilo, "_blank");
    }
    
    escilogout() {
        window.open(environment.esci, "_self");
    }

}
