/*
 Copyright Regione Piemonte - 2022
 SPDX-License-Identifier: EUPL-1.2
*/
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { NgxLoadingModule } from 'ngx-loading';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { Routes } from "@angular/router";
import { DettaglioComponent } from "src/app/dettaglio/dettaglio.component";
import { RicercaComponent } from "src/app/ricerca/ricerca.component";
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { HttpModule } from "@angular/http";
import { FormsModule } from "@angular/forms";
import { CronologiaComponent } from './cronologia/cronologia.component';
import { DataTablesModule } from 'angular-datatables';
import { NgbDateParserFormatter } from "@ng-bootstrap/ng-bootstrap";
import { MomentDateFormatter } from "src/app/MomentDateFormatter";
import { ScerevBOClient } from "src/app/ScerevBOClient";
import { CookieService } from "ngx-cookie-service";


const routes: Routes = [];


@NgModule( {
    declarations: [
        AppComponent,
        HomeComponent,
        DettaglioComponent,
        RicercaComponent,
        CronologiaComponent
    ],
    imports: [
        BrowserModule,
        FormsModule,
        DataTablesModule,
        NgbModule,
        AppRoutingModule,
        NgxLoadingModule.forRoot( {} ),
        HttpModule
    ],
    providers: [ScerevBOClient,
        { provide: NgbDateParserFormatter, useClass: MomentDateFormatter },  CookieService], 
    bootstrap: [AppComponent]
} )

export class AppModule { }
