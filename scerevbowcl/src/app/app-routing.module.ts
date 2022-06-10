/*
 Copyright Regione Piemonte - 2022
 SPDX-License-Identifier: EUPL-1.2
*/
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from "src/app/home/home.component";
import { DettaglioComponent } from "src/app/dettaglio/dettaglio.component";
import { RicercaComponent } from "src/app/ricerca/ricerca.component";
import { CronologiaComponent } from "src/app/cronologia/cronologia.component";

const routes: Routes = [

    { path: 'home', component: HomeComponent },
    {
        path: 'dettaglio', component: DettaglioComponent, children:
            [
                { path: 'cronologia', component: CronologiaComponent }
            ]
    },
    {
        path: 'ricerca', component: RicercaComponent, children:
            [
                { path: '', redirectTo: 'ricerca', pathMatch: 'full' },
//                { path: 'richiesteaperte', component: RichiesteaperteComponent },
//                { path: 'archiviorichieste', component: ArchiviorichiesteComponent },
                { path: 'cronologia', component: CronologiaComponent }
            ]
    },

];

@NgModule( {

    imports: [RouterModule.forRoot( routes )],
    exports: [RouterModule]
} )
export class AppRoutingModule { }
