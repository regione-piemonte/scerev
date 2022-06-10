/*
 Copyright Regione Piemonte - 2022
 SPDX-License-Identifier: EUPL-1.2
*/
import { Component, OnInit } from '@angular/core';
import { ScerevBOClient } from "src/app/ScerevBOClient";
import { NgbCalendar } from "@ng-bootstrap/ng-bootstrap";
import { NgbModal } from "@ng-bootstrap/ng-bootstrap";
import { NgbModalConfig } from "@ng-bootstrap/ng-bootstrap";
import { Cronologia } from "src/app/dto/Cronologia";
import { DatiRichiedente } from "src/app/dto/DatiRichiedente";
import { RicercaRichiesteOut } from "src/app/dto/RicercaRichiesteOut";
import { Ricerca } from "src/app/dto/Ricerca";

@Component({
  selector: 'app-cronologia',
  templateUrl: './cronologia.component.html',
  styleUrls: ['./cronologia.component.css']
})
export class CronologiaComponent implements OnInit {

  constructor( private client: ScerevBOClient ) { }
  
  cronologia: Cronologia[] = [];
  lista: RicercaRichiesteOut;
          
  ngOnInit() {
      this.cronologia = null;
      
      this.client.getCronologia().subscribe(
              response => {
                  this.cronologia = response;
              },
              error => {
                  this.client.idFeedback = 500;
                  this.client.messaggioFeedback = "Errore durante il caricamento della cronologia:" + error.error;
              }
          );      
  }

}
