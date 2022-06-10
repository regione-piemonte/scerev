# Prodotto

SCEREV - Scelta e revoca del medico

## Versione

2.0.1

## Descrizione del prodotto

API REST per la scelta e la revoca del medico da parte del cittadino e webApp di backoffice per gli operatori sanitari della Regione Piemonte.

Elenco componenti:

* [SCEREVBE](scerevbe) API REST per la scelta e la revoca del medico a fruizione del frontend rivolto al cittadino
* [SCEREVBOWEB](scerevboweb) WebApp di backoffice ad uso degli operatori sanitari, con funzioni complementari alla gestione della scelta e della revoca del cambio del medico
* [SCEREVBOWCL](scerevbowcl) Componente Angular di frontend che si interfaccia alle API REST di SCEREVBOWEB
* [SCEREVSERVIZIESTERNI] (scerevserviziesterni) Componente di interfacciamento ai servizi ai AURA e OPESSAN
* [SCEREVJPA](scerevjpa) Componente di gestione della persistenza dei dati, utilizzata da SCEREVBOWEB e SCEREVBE
* [SCEREVUTIL](scerevutil) Funzioni di utilità, utilizzate da SCEREVBOWEB, SCEREVBE e SCEREVSERVIZIESTERNI
* [SCEREVNOTIFICAMEDBCH](scerevnotificamedbch) Batch di notifica al cittadino per segnalare la disponibilità del medico scelto
* [SCEREVDISTRETTIBCH](scerevdistrettibch) Batch di allineamento ad AURA delle tabelle dei distretti e degli ambiti
* [SCEREVSCADENZASSNBCH](scerevscadenzassnbch) Batch di notifica all'assistito per segnalare la prossima scadenza dell'assistenza
* [SCEREVDB](scerevdb) Script di creazione di tabelle, indici e procedure della base dati

LINK ai repository:xxx

## Configurazioni iniziali

Si rimanda ai readme delle singole componenti

* [SCEREVBE](scerevbe/README.md)
* [SCEREVBOWEB](scerevboweb/README.md)
* [SCEREVBOWCL](scerevbowcl/README.md)
* [SCEREVSERVIZIESTERNI](scerevserviziesterni/README.md)
* [SCEREVJPA](scerevjpa/README.md)
* [SCEREVUTIL](scerevutil/README.md)
* [SCEREVNOTIFICAMEDBCH](scerevnotificamedbch/README.md)
* [SCEREVDISTRETTIBCH](scerevdistrettibch/README.md)
* [SCEREVSCADENZASSNBCH](scerevscadenzassnbch/README.md)
* [SCEREVDB](scerevdb/README.md)

## Prerequisiti di sistema

Server Web:
Apache 2.4.53

Application Server:
Wildfly 17.0.0

Tipo di database:
postgres v9.6.19

Dipendenze elencate nella cartella docs/wsdl

## Versioning (Obbligatorio)

Per il versionamento del software si utilizza la modalità Semantic Versioning (http://semver.org).

## Authors

* [CSI Piemonte]

## Copyrights

“© Copyright Regione Piemonte – 2022”

## License

SPDX-License-Identifier: inserire il codice SPDX delle licenza
Veder il file LICENSE per i dettagli.
