CREATE SEQUENCE scerev.scerev_d_asl_pk_azienda_seq INCREMENT 1 MINVALUE 0 START 0;

CREATE SEQUENCE scerev.scerev_d_ambito_pk_ambito_seq INCREMENT 1 MINVALUE 0 START 0;

CREATE SEQUENCE scerev.scerev_d_azione_pk_azione_seq INCREMENT 1 MINVALUE 0 START 0;

CREATE SEQUENCE scerev.scerev_d_gruppo_utenti_pk_gruppo_utenti_seq INCREMENT 1 MINVALUE 0 START 0;

CREATE SEQUENCE scerev.scerev_d_motivo_domicilio_pk_motivo_domicilio_seq INCREMENT 1 MINVALUE 0 START 0;

CREATE SEQUENCE scerev.scerev_d_nazione_pk_nazione_seq INCREMENT 1 MINVALUE 0 START 0;

CREATE SEQUENCE scerev.scerev_d_pratica_stato_pk_pratica_stato_seq INCREMENT 1 MINVALUE 0 START 0;

CREATE SEQUENCE scerev.scerev_d_pratica_tipo_pk_pratica_tipo_seq INCREMENT 1 MINVALUE 0 START 0;

CREATE SEQUENCE scerev.scerev_d_tipologia_profilo_pk_tipologia_profilo_seq INCREMENT 1 MINVALUE 0 START 0;

CREATE SEQUENCE scerev.scerev_r_motivo_soggiorno_doc_pk_motivo_soggiorno_documento_seq INCREMENT 1 MINVALUE 0 START 0;

CREATE SEQUENCE scerev.scerev_t_documento_pk_documento_seq INCREMENT 1 MINVALUE 0 START 0;

CREATE SEQUENCE scerev.scerev_t_file_pk_file_seq INCREMENT 1 MINVALUE 0 START 0;

CREATE SEQUENCE scerev.scerev_t_info_supplementari_pk_info_supplementari_seq INCREMENT 1 MINVALUE 0 START 0;

CREATE SEQUENCE scerev.scerev_t_localita_pk_localita_seq INCREMENT 1 MINVALUE 0 START 0;

CREATE SEQUENCE scerev.scerev_t_monitoraggio_pk_monitoraggio_seq INCREMENT 1 MINVALUE 0 START 0;

CREATE SEQUENCE scerev.scerev_t_pratica_pk_pratica_seq INCREMENT 1 MINVALUE 0 START 0;

CREATE SEQUENCE scerev.scerev_d_cittadinanza_pk_cittadinanza_seq INCREMENT 1 MINVALUE 0 START 0;

CREATE SEQUENCE scerev.scerev_d_distretto_pk_distretto_seq INCREMENT 1 MINVALUE 0 START 0;

CREATE SEQUENCE scerev.scerev_d_documento_tipo_pk_documento_tipo_seq INCREMENT 1 MINVALUE 0 START 0;

CREATE SEQUENCE scerev.scerev_d_grado_parentela_pk_grado_parentela_seq INCREMENT 1 MINVALUE 0 START 0;

CREATE SEQUENCE scerev.scerev_d_motivazione_pk_motivazione_seq INCREMENT 1 MINVALUE 0 START 0;

CREATE SEQUENCE scerev.scerev_d_motivo_soggiorno_pk_motivo_soggiorno_seq INCREMENT 1 MINVALUE 0 START 0;

CREATE SEQUENCE scerev.scerev_d_msg_pk_msg_seq INCREMENT 1 MINVALUE 0 START 0;

CREATE SEQUENCE scerev.scerev_d_regione_pk_regione_seq INCREMENT 1 MINVALUE 0 START 0;

CREATE SEQUENCE scerev.scerev_d_tipologia_medico_pk_tipologia_medico_seq INCREMENT 1 MINVALUE 0 START 0;

CREATE SEQUENCE scerev.scerev_l_audit_pk_audit_seq INCREMENT 1 MINVALUE 0 START 0;

CREATE SEQUENCE scerev.scerev_r_azioni_gruppo_utenti_pk_azioni_gruppo_utenti_seq INCREMENT 1 MINVALUE 0 START 0;

CREATE SEQUENCE scerev.scerev_r_pratica_pratica_tipo_pk_pratica_pratica_tipo_seq INCREMENT 1 MINVALUE 0 START 0;

CREATE SEQUENCE scerev.scerev_t_cronologia_pk_cronologia_seq INCREMENT 1 MINVALUE 0 START 0;

CREATE SEQUENCE scerev.version_control_change_number_seq INCREMENT 1 MINVALUE 0 START 0;

CREATE SEQUENCE scerev.scerev_t_asl_operatore_pk_asl_operatore_seq INCREMENT 1 MINVALUE 0 START 0;

CREATE SEQUENCE scerev.scerev_t_file_backup_pk_file_seq INCREMENT 1 MINVALUE 0 START 0;

CREATE SEQUENCE scerev.scerev_log_aura_pk_log_aura_seq INCREMENT 1 MINVALUE 0 START 0;

/* ---------------------------------------------------------------------- */
/* Add tables                                                             */
/* ---------------------------------------------------------------------- */

/* ---------------------------------------------------------------------- */
/* Add table "scerev.scerev_d_asl"                                        */
/* ---------------------------------------------------------------------- */

CREATE TABLE scerev.scerev_d_asl (
    pk_azienda INTEGER DEFAULT nextval('scerev_d_asl_pk_azienda_seq')  NOT NULL,
    codice_azienda TEXT  NOT NULL,
    codice_regione TEXT  NOT NULL,
    denominazione_azienda TEXT  NOT NULL,
    data_inizio_validita DATE  NOT NULL,
    data_fine_validita DATE,
    data_creazione TIMESTAMP  NOT NULL,
    data_modifica TIMESTAMP  NOT NULL,
    data_cancellazione TIMESTAMP,
    CONSTRAINT pk_scerev_d_asl PRIMARY KEY (pk_azienda)
);

CREATE UNIQUE INDEX unique_codice_azienda ON scerev.scerev_d_asl (codice_azienda);

COMMENT ON TABLE scerev.scerev_d_asl IS 'Contiene dati inerenti alle aziende sanitarie esistenti.';

COMMENT ON COLUMN scerev.scerev_d_asl.codice_azienda IS 'Codice identificativo dell''Azienda Sanitaria';

COMMENT ON COLUMN scerev.scerev_d_asl.codice_regione IS 'Codice della Regione di appartenenza dell''Azienda Sanitaria';

COMMENT ON COLUMN scerev.scerev_d_asl.denominazione_azienda IS 'Denominazione Azienda Sanitaria';

COMMENT ON COLUMN scerev.scerev_d_asl.data_inizio_validita IS 'Data di attivazione dell''Azienda Sanitaria. ';

COMMENT ON COLUMN scerev.scerev_d_asl.data_fine_validita IS 'Data di chiusura dell''Azienda Sanitaria';

COMMENT ON COLUMN scerev.scerev_d_asl.data_creazione IS 'Data creazione/inserimento record';

COMMENT ON COLUMN scerev.scerev_d_asl.data_modifica IS 'Data pari al primo inserimento o dell''avvenuta modifica del record';

COMMENT ON COLUMN scerev.scerev_d_asl.data_cancellazione IS 'Data valorizzata indica la cancellazione logica del record';

/* ---------------------------------------------------------------------- */
/* Add table "scerev.scerev_d_azione"                                     */
/* ---------------------------------------------------------------------- */

CREATE TABLE scerev.scerev_d_azione (
    pk_azione INTEGER DEFAULT nextval('scerev_d_azione_pk_azione_seq')  NOT NULL,
    cod_azione TEXT  NOT NULL,
    desc_azione TEXT  NOT NULL,
    CONSTRAINT pk_scerev_d_azione PRIMARY KEY (pk_azione)
);

CREATE UNIQUE INDEX unique_cod_azione ON scerev.scerev_d_azione (cod_azione);

COMMENT ON TABLE scerev.scerev_d_azione IS 'Elenco dei servizi previsti dall''applicazione';

COMMENT ON COLUMN scerev.scerev_d_azione.pk_azione IS 'Codice identificativo del servizio';

COMMENT ON COLUMN scerev.scerev_d_azione.cod_azione IS 'Codice del servizio';

COMMENT ON COLUMN scerev.scerev_d_azione.desc_azione IS 'Descrizione del servizio';

/* ---------------------------------------------------------------------- */
/* Add table "scerev.scerev_d_cittadinanza"                               */
/* ---------------------------------------------------------------------- */

CREATE TABLE scerev.scerev_d_cittadinanza (
    pk_cittadinanza INTEGER DEFAULT nextval('scerev_d_cittadinanza_pk_cittadinanza_seq')  NOT NULL,
    codice_cittadinanza TEXT  NOT NULL,
    valore_cittadinanza TEXT  NOT NULL,
    data_creazione TIMESTAMP  NOT NULL,
    data_modifica TIMESTAMP  NOT NULL,
    data_cancellazione TIMESTAMP,
    data_inizio_validita DATE  NOT NULL,
    data_fine_validita DATE,
    comunitario BOOLEAN  NOT NULL,
    CONSTRAINT pk_scerev_d_cittadinanza PRIMARY KEY (pk_cittadinanza)
);

CREATE UNIQUE INDEX unique_codice_cittadinanza ON scerev.scerev_d_cittadinanza (codice_cittadinanza);

COMMENT ON TABLE scerev.scerev_d_cittadinanza IS 'Contiene dati inerenti alle possibili cittadinanze esistenti.';

COMMENT ON COLUMN scerev.scerev_d_cittadinanza.codice_cittadinanza IS 'Codice per identificare una cittadinanza. Utile per popolare il menù a tendina ''cittadinanza''';

COMMENT ON COLUMN scerev.scerev_d_cittadinanza.valore_cittadinanza IS 'Valore della cittadinanza, ovvero valore che verrà visualizzato nel menù a tendina';

COMMENT ON COLUMN scerev.scerev_d_cittadinanza.data_creazione IS 'Data creazione/inserimento del record';

COMMENT ON COLUMN scerev.scerev_d_cittadinanza.data_modifica IS 'Data pari al primo inserimento o dell''avvenuta modifica del record';

COMMENT ON COLUMN scerev.scerev_d_cittadinanza.data_cancellazione IS 'Data valorizzata indica la cancellazione logica del record';

COMMENT ON COLUMN scerev.scerev_d_cittadinanza.data_inizio_validita IS 'Data inizio validità del record';

COMMENT ON COLUMN scerev.scerev_d_cittadinanza.data_fine_validita IS 'Data fine validità del record';

COMMENT ON COLUMN scerev.scerev_d_cittadinanza.comunitario IS 'Valore booleano per determinare se una cittadinanza è comunitaria (true) o extracomunitaria (false)';

/* ---------------------------------------------------------------------- */
/* Add table "scerev.scerev_d_distretto"                                  */
/* ---------------------------------------------------------------------- */

CREATE TABLE scerev.scerev_d_distretto (
    pk_distretto INTEGER DEFAULT nextval('scerev_d_distretto_pk_distretto_seq')  NOT NULL,
    codice_distretto TEXT  NOT NULL,
    denominazione_distretto TEXT  NOT NULL,
    fk_azienda INTEGER,
    data_inizio_validita DATE  NOT NULL,
    data_fine_validita DATE,
    data_creazione TIMESTAMP  NOT NULL,
    data_modifica TIMESTAMP  NOT NULL,
    data_cancellazione TIMESTAMP,
    CONSTRAINT pk_scerev_d_distretto PRIMARY KEY (pk_distretto)
);

CREATE INDEX ixfk_scerev_d_distretto_scerev_d_asl ON scerev.scerev_d_distretto (fk_azienda);

COMMENT ON TABLE scerev.scerev_d_distretto IS 'Contiene dati inerenti ai distretti';

COMMENT ON COLUMN scerev.scerev_d_distretto.codice_distretto IS 'Codice identificativo del Distretto';

COMMENT ON COLUMN scerev.scerev_d_distretto.denominazione_distretto IS 'Denominazione del Distretto';

COMMENT ON COLUMN scerev.scerev_d_distretto.fk_azienda IS 'Id dell''azienda sanitaria appartenente al distretto';

COMMENT ON COLUMN scerev.scerev_d_distretto.data_inizio_validita IS 'Data inizio validità del record';

COMMENT ON COLUMN scerev.scerev_d_distretto.data_fine_validita IS 'Data fine validità del record';

COMMENT ON COLUMN scerev.scerev_d_distretto.data_creazione IS 'Data creazione/inserimento record';

COMMENT ON COLUMN scerev.scerev_d_distretto.data_modifica IS 'Data pari al primo inserimento o alla modifica del record';

COMMENT ON COLUMN scerev.scerev_d_distretto.data_cancellazione IS 'Data cancellazione record';

/* ---------------------------------------------------------------------- */
/* Add table "scerev.scerev_d_documento_tipo"                             */
/* ---------------------------------------------------------------------- */

CREATE TABLE scerev.scerev_d_documento_tipo (
    pk_documento_tipo INTEGER DEFAULT nextval('scerev_d_documento_tipo_pk_documento_tipo_seq')  NOT NULL,
    codice_documento_tipo TEXT  NOT NULL,
    descrizione_documento_tipo TEXT  NOT NULL,
    data_creazione TIMESTAMP  NOT NULL,
    data_modifica TIMESTAMP  NOT NULL,
    data_cancellazione TIMESTAMP,
    data_inizio_validita DATE  NOT NULL,
    data_fine_validita DATE,
    CONSTRAINT pk_scerev_d_documento_tipo PRIMARY KEY (pk_documento_tipo)
);

CREATE UNIQUE INDEX unique_codice_documento_tipo ON scerev.scerev_d_documento_tipo (codice_documento_tipo);

COMMENT ON TABLE scerev.scerev_d_documento_tipo IS 'Contiene dati inerenti ai tipi di documenti.';

COMMENT ON COLUMN scerev.scerev_d_documento_tipo.codice_documento_tipo IS 'Codice utile per identificare un determinato documento';

COMMENT ON COLUMN scerev.scerev_d_documento_tipo.descrizione_documento_tipo IS 'Utile per descrivere il singolo documento che si sta dichiarando';

COMMENT ON COLUMN scerev.scerev_d_documento_tipo.data_creazione IS 'Data creazione del record';

COMMENT ON COLUMN scerev.scerev_d_documento_tipo.data_modifica IS 'Data pari al primo inserimento o della modifica del record';

COMMENT ON COLUMN scerev.scerev_d_documento_tipo.data_cancellazione IS 'Data valorizzata indica cancellazione logica del record';

COMMENT ON COLUMN scerev.scerev_d_documento_tipo.data_inizio_validita IS 'Data inizio validità del record';

COMMENT ON COLUMN scerev.scerev_d_documento_tipo.data_fine_validita IS 'Data fine validità del record';

/* ---------------------------------------------------------------------- */
/* Add table "scerev.scerev_d_grado_parentela"                            */
/* ---------------------------------------------------------------------- */

CREATE TABLE scerev.scerev_d_grado_parentela (
    pk_grado_parentela INTEGER DEFAULT nextval('scerev_d_grado_parentela_pk_grado_parentela_seq')  NOT NULL,
    codice_grado_parentela TEXT  NOT NULL,
    valore_grado_parentela TEXT  NOT NULL,
    data_creazione TIMESTAMP  NOT NULL,
    data_modifica TIMESTAMP  NOT NULL,
    data_cancellazione TIMESTAMP,
    data_inizio_validita DATE  NOT NULL,
    data_fine_validita DATE,
    CONSTRAINT pk_scerev_d_grado_parentela PRIMARY KEY (pk_grado_parentela)
);

CREATE UNIQUE INDEX unique_codice_grado_parentela ON scerev.scerev_d_grado_parentela (codice_grado_parentela);

COMMENT ON TABLE scerev.scerev_d_grado_parentela IS 'Contiene dati inerenti ai possibili gradi di parentela esistenti.';

COMMENT ON COLUMN scerev.scerev_d_grado_parentela.codice_grado_parentela IS 'Codice identificativo del grado di parentela. Utile per mostrare a video i diversi gradi di parentela che il sistema mette a disposizione';

COMMENT ON COLUMN scerev.scerev_d_grado_parentela.valore_grado_parentela IS 'Utile per visualizzare a video il valore del grado di parentela';

COMMENT ON COLUMN scerev.scerev_d_grado_parentela.data_creazione IS 'Data creazione/inserimento del record';

COMMENT ON COLUMN scerev.scerev_d_grado_parentela.data_modifica IS 'Data pari al primo inserimento o dell''avvenuta modifica del record';

COMMENT ON COLUMN scerev.scerev_d_grado_parentela.data_cancellazione IS 'Data valorizzata indica la cancellazione logica del recod';

COMMENT ON COLUMN scerev.scerev_d_grado_parentela.data_inizio_validita IS 'Data inizio validità del record';

COMMENT ON COLUMN scerev.scerev_d_grado_parentela.data_fine_validita IS 'Data fine validità del record';

/* ---------------------------------------------------------------------- */
/* Add table "scerev.scerev_d_gruppo_utenti"                              */
/* ---------------------------------------------------------------------- */

CREATE TABLE scerev.scerev_d_gruppo_utenti (
    pk_gruppo_utenti INTEGER DEFAULT nextval('scerev_d_gruppo_utenti_pk_gruppo_utenti_seq')  NOT NULL,
    cod_gruppo_utenti TEXT  NOT NULL,
    desc_gruppo_utenti TEXT  NOT NULL,
    CONSTRAINT pk_scerev_d_gruppo_utenti PRIMARY KEY (pk_gruppo_utenti)
);

CREATE UNIQUE INDEX unique_cod_gruppo_utenti ON scerev.scerev_d_gruppo_utenti (cod_gruppo_utenti);

COMMENT ON TABLE scerev.scerev_d_gruppo_utenti IS 'Rappresenta un gruppo utenti che svolgono un detrinato ruolo a cui corrispondono determinate abilitazioni';

COMMENT ON COLUMN scerev.scerev_d_gruppo_utenti.pk_gruppo_utenti IS 'Codice identificativo del gruppo utenti';

COMMENT ON COLUMN scerev.scerev_d_gruppo_utenti.cod_gruppo_utenti IS 'Codice del gruppo utenti';

COMMENT ON COLUMN scerev.scerev_d_gruppo_utenti.desc_gruppo_utenti IS 'Descrizione del gruppo utenti';

/* ---------------------------------------------------------------------- */
/* Add table "scerev.scerev_d_motivazione"                                */
/* ---------------------------------------------------------------------- */

CREATE TABLE scerev.scerev_d_motivazione (
    pk_motivazione INTEGER DEFAULT nextval('scerev_d_motivazione_pk_motivazione_seq')  NOT NULL,
    tipo_motivazione TEXT  NOT NULL,
    codice_motivazione TEXT  NOT NULL,
    valore_motivazione TEXT  NOT NULL,
    data_creazione TIMESTAMP  NOT NULL,
    data_modifica TIMESTAMP  NOT NULL,
    data_cancellazione TIMESTAMP,
    data_inizio_validita DATE  NOT NULL,
    data_fine_validita DATE,
    CONSTRAINT pk_scerev_d_motivazione PRIMARY KEY (pk_motivazione)
);

CREATE UNIQUE INDEX unique_codice_motivazione ON scerev.scerev_d_motivazione (codice_motivazione);

COMMENT ON TABLE scerev.scerev_d_motivazione IS 'Contiene dati inerenti alle possibili motivazioni che si possono assegnare ad una pratica in caso di rifiuto o rettifica.';

COMMENT ON COLUMN scerev.scerev_d_motivazione.tipo_motivazione IS 'Tipo per identificare gruppi di motivazioni';

COMMENT ON COLUMN scerev.scerev_d_motivazione.codice_motivazione IS 'Codice per identificare una motivazione che un operatore può assegnare ad una pratica che decide di rettificare o respingere';

COMMENT ON COLUMN scerev.scerev_d_motivazione.valore_motivazione IS 'Valore della motivazione che si può assegnare ad una pratica, ovvero sarà il valore che comparira nel menù a tendina ''motivazione'' in caso di rettifica o respinta pratica';

COMMENT ON COLUMN scerev.scerev_d_motivazione.data_creazione IS 'Data inserimento del record';

COMMENT ON COLUMN scerev.scerev_d_motivazione.data_modifica IS 'Data pari al primo inserimento o alla modifia del record';

COMMENT ON COLUMN scerev.scerev_d_motivazione.data_cancellazione IS 'Data valorizzata indica cancellazione logica del record';

COMMENT ON COLUMN scerev.scerev_d_motivazione.data_inizio_validita IS 'Data inizio validità del record';

COMMENT ON COLUMN scerev.scerev_d_motivazione.data_fine_validita IS 'Data fine validità del record';

/* ---------------------------------------------------------------------- */
/* Add table "scerev.scerev_d_motivo_domicilio"                           */
/* ---------------------------------------------------------------------- */

CREATE TABLE scerev.scerev_d_motivo_domicilio (
    pk_motivo_domicilio INTEGER DEFAULT nextval('scerev_d_motivo_domicilio_pk_motivo_domicilio_seq')  NOT NULL,
    codice_motivo_domicilio TEXT  NOT NULL,
    valore_motivo_domicilio TEXT  NOT NULL,
    data_creazione TIMESTAMP  NOT NULL,
    data_modifica TIMESTAMP  NOT NULL,
    data_cancellazione TIMESTAMP,
    data_inizio_validita DATE  NOT NULL,
    data_fine_validita DATE,
    CONSTRAINT pk_scerev_d_motivo_domicilio PRIMARY KEY (pk_motivo_domicilio)
);

CREATE UNIQUE INDEX unique_codice_motivo_domicilio ON scerev.scerev_d_motivo_domicilio (codice_motivo_domicilio);

COMMENT ON TABLE scerev.scerev_d_motivo_domicilio IS 'Contiene dati inerenti ai motivi di domicilio.';

COMMENT ON COLUMN scerev.scerev_d_motivo_domicilio.codice_motivo_domicilio IS 'Codice per identificare un motivo di domicilio che un utente può indicare quando lo richiede la procedura';

COMMENT ON COLUMN scerev.scerev_d_motivo_domicilio.valore_motivo_domicilio IS 'Valore del motivo domicilio che si può assegnare ad una pratica, ovvero sarà il valore che comparira nel menù a tendina ''motivo domicilio''';

COMMENT ON COLUMN scerev.scerev_d_motivo_domicilio.data_creazione IS 'Data inserimento del record';

COMMENT ON COLUMN scerev.scerev_d_motivo_domicilio.data_modifica IS 'Data pari al primo inserimento o alla modifica del record';

COMMENT ON COLUMN scerev.scerev_d_motivo_domicilio.data_cancellazione IS 'Data valorizzata indica cancellazione logica del record';

COMMENT ON COLUMN scerev.scerev_d_motivo_domicilio.data_inizio_validita IS 'Data inizio validità del record';

COMMENT ON COLUMN scerev.scerev_d_motivo_domicilio.data_fine_validita IS 'Data fine validità del record';

/* ---------------------------------------------------------------------- */
/* Add table "scerev.scerev_d_motivo_soggiorno"                           */
/* ---------------------------------------------------------------------- */

CREATE TABLE scerev.scerev_d_motivo_soggiorno (
    pk_motivo_soggiorno INTEGER DEFAULT nextval('scerev_d_motivo_soggiorno_pk_motivo_soggiorno_seq')  NOT NULL,
    codice_motivo_soggiorno TEXT  NOT NULL,
    valore_motivo_soggiorno TEXT  NOT NULL,
    data_creazione TIMESTAMP  NOT NULL,
    data_modifica TIMESTAMP  NOT NULL,
    data_cancellazione TIMESTAMP,
    data_inizio_validita DATE  NOT NULL,
    data_fine_validita DATE,
    cittadino_comunitario BOOLEAN DEFAULT false  NOT NULL,
    tipologia_assistenza CHARACTER VARYING(20) DEFAULT 'false'  NOT NULL,
    CONSTRAINT pk_scerev_d_motivo_soggiorno PRIMARY KEY (pk_motivo_soggiorno)
);

CREATE UNIQUE INDEX unique_codice_motivo_soggiorno ON scerev.scerev_d_motivo_soggiorno (codice_motivo_soggiorno);

COMMENT ON TABLE scerev.scerev_d_motivo_soggiorno IS 'Contiene dati inerenti ai motivi di soggiorno.';

COMMENT ON COLUMN scerev.scerev_d_motivo_soggiorno.codice_motivo_soggiorno IS 'Codice per identificare un motivo di soggiorno che un utente straniero può indicare quando richiede un medico';

COMMENT ON COLUMN scerev.scerev_d_motivo_soggiorno.valore_motivo_soggiorno IS 'Valore del motivo di soggiorno che si può assegnare ad una pratica, ovvero sarà il valore che comparira nel menù a tendina ''motivo di soggiorno''';

COMMENT ON COLUMN scerev.scerev_d_motivo_soggiorno.data_creazione IS 'Data inserimento del record';

COMMENT ON COLUMN scerev.scerev_d_motivo_soggiorno.data_modifica IS 'Data pari al primo inserimento o alla modifica del record';

COMMENT ON COLUMN scerev.scerev_d_motivo_soggiorno.data_cancellazione IS 'Data valorizzata indica cancellazione logica del record';

COMMENT ON COLUMN scerev.scerev_d_motivo_soggiorno.data_inizio_validita IS 'Data inizio validità del record';

COMMENT ON COLUMN scerev.scerev_d_motivo_soggiorno.data_fine_validita IS 'Data fine validità del record';

/* ---------------------------------------------------------------------- */
/* Add table "scerev.scerev_d_msg"                                        */
/* ---------------------------------------------------------------------- */

CREATE TABLE scerev.scerev_d_msg (
    pk_msg INTEGER DEFAULT nextval('scerev_d_msg_pk_msg_seq')  NOT NULL,
    tipo_msg TEXT  NOT NULL,
    codice_msg TEXT  NOT NULL,
    descrizione_msg TEXT  NOT NULL,
    valore_msg TEXT  NOT NULL,
    data_creazione TIMESTAMP  NOT NULL,
    data_modifica TIMESTAMP  NOT NULL,
    data_cancellazione TIMESTAMP,
    data_inizio_validita DATE  NOT NULL,
    data_fine_validita DATE,
    CONSTRAINT pk_scerev_d_msg PRIMARY KEY (pk_msg)
);

CREATE UNIQUE INDEX unique_codice_msg ON scerev.scerev_d_msg (codice_msg);

COMMENT ON TABLE scerev.scerev_d_msg IS 'Contiene dati inerenti ai possibili di messaggi che si possono usare all''interno del sistema.';

COMMENT ON COLUMN scerev.scerev_d_msg.tipo_msg IS 'Utile per sapere il tipo di messaggio (messaggio di errore, messaggio per notifica, messaggio per operazione riuscita/fallita ecc)';

COMMENT ON COLUMN scerev.scerev_d_msg.codice_msg IS 'Utile per identificare l''elemento con un codice descrittivo';

COMMENT ON COLUMN scerev.scerev_d_msg.descrizione_msg IS 'Utile per descrivere lo scopo di questo messaggio';

COMMENT ON COLUMN scerev.scerev_d_msg.valore_msg IS 'Contiene il messaggio che si vuole trasmettere';

COMMENT ON COLUMN scerev.scerev_d_msg.data_creazione IS 'Data creazione del record';

COMMENT ON COLUMN scerev.scerev_d_msg.data_modifica IS 'Data pari al primo inserimento o della modifica del record';

COMMENT ON COLUMN scerev.scerev_d_msg.data_cancellazione IS 'Data valorizzata indica cancellazione logica del record';

COMMENT ON COLUMN scerev.scerev_d_msg.data_inizio_validita IS 'Data inizio validità del record';

COMMENT ON COLUMN scerev.scerev_d_msg.data_fine_validita IS 'Data fine validità del record';

/* ---------------------------------------------------------------------- */
/* Add table "scerev.scerev_d_nazione"                                    */
/* ---------------------------------------------------------------------- */

CREATE TABLE scerev.scerev_d_nazione (
    pk_nazione INTEGER DEFAULT nextval('scerev_d_nazione_pk_nazione_seq')  NOT NULL,
    codice_nazione TEXT  NOT NULL,
    nome_nazione TEXT  NOT NULL,
    data_creazione TIMESTAMP  NOT NULL,
    data_modifica TIMESTAMP  NOT NULL,
    data_cancellazione TIMESTAMP,
    data_inizio_validita DATE  NOT NULL,
    data_fine_validita DATE,
    CONSTRAINT pk_scerev_d_nazione PRIMARY KEY (pk_nazione)
);

CREATE UNIQUE INDEX unique_codice_nazione ON scerev.scerev_d_nazione (codice_nazione);

COMMENT ON TABLE scerev.scerev_d_nazione IS 'Contiene dati inerenti alle nazioni esistenti.';

COMMENT ON COLUMN scerev.scerev_d_nazione.codice_nazione IS 'Codice per identificare una nazione. Utile per popolare il menù a tendina ''stato residenza''';

COMMENT ON COLUMN scerev.scerev_d_nazione.nome_nazione IS 'Nome della nazione, ovvero valori che verranno visualizzati nel menù a tendina';

COMMENT ON COLUMN scerev.scerev_d_nazione.data_creazione IS 'Data di inserimento/creazione del record';

COMMENT ON COLUMN scerev.scerev_d_nazione.data_modifica IS 'Data pari al primo inserimento o dell''avvenuta modifica del record';

COMMENT ON COLUMN scerev.scerev_d_nazione.data_cancellazione IS 'Data valorizzata indica la cancellazione logica del record';

COMMENT ON COLUMN scerev.scerev_d_nazione.data_inizio_validita IS 'Data inizio validità del record';

COMMENT ON COLUMN scerev.scerev_d_nazione.data_fine_validita IS 'Data fine validità del record';

/* ---------------------------------------------------------------------- */
/* Add table "scerev.scerev_d_pratica_stato"                              */
/* ---------------------------------------------------------------------- */

CREATE TABLE scerev.scerev_d_pratica_stato (
    pk_pratica_stato INTEGER DEFAULT nextval('scerev_d_pratica_stato_pk_pratica_stato_seq')  NOT NULL,
    codice_pratica_stato TEXT  NOT NULL,
    valore_pratica_stato TEXT  NOT NULL,
    data_creazione TIMESTAMP  NOT NULL,
    data_modifica TIMESTAMP  NOT NULL,
    data_cancellazione TIMESTAMP,
    data_inizio_validita DATE  NOT NULL,
    data_fine_validita DATE,
    CONSTRAINT pk_scerev_d_pratica_stato PRIMARY KEY (pk_pratica_stato)
);

CREATE UNIQUE INDEX unique_codice_pratica_stato ON scerev.scerev_d_pratica_stato (codice_pratica_stato);

COMMENT ON TABLE scerev.scerev_d_pratica_stato IS 'Contiene dati inerenti agli stati che può assumere una pratica.';

COMMENT ON COLUMN scerev.scerev_d_pratica_stato.codice_pratica_stato IS 'Codice per identificare uno stato che può assumere una pratica';

COMMENT ON COLUMN scerev.scerev_d_pratica_stato.valore_pratica_stato IS 'Vlore di uno stato che può assumere una pratica, ovvero sarà il valore che comparira nel menù a tendina del filtro ''stato''';

COMMENT ON COLUMN scerev.scerev_d_pratica_stato.data_creazione IS 'Data creazione/inserimento del record';

COMMENT ON COLUMN scerev.scerev_d_pratica_stato.data_modifica IS 'Data pari al primo inserimento o dell''avvenuta modifica del record';

COMMENT ON COLUMN scerev.scerev_d_pratica_stato.data_cancellazione IS 'Data valorizzata indica cancellazione logica del record';

COMMENT ON COLUMN scerev.scerev_d_pratica_stato.data_inizio_validita IS 'Data inizio validità del record';

COMMENT ON COLUMN scerev.scerev_d_pratica_stato.data_fine_validita IS 'Data fine validità del record';

/* ---------------------------------------------------------------------- */
/* Add table "scerev.scerev_d_pratica_tipo"                               */
/* ---------------------------------------------------------------------- */

CREATE TABLE scerev.scerev_d_pratica_tipo (
    pk_pratica_tipo INTEGER DEFAULT nextval('scerev_d_pratica_tipo_pk_pratica_tipo_seq')  NOT NULL,
    codice_pratica_tipo TEXT  NOT NULL,
    valore_pratica_tipo TEXT  NOT NULL,
    data_creazione TIMESTAMP  NOT NULL,
    data_modifica TIMESTAMP  NOT NULL,
    data_cancellazione TIMESTAMP,
    data_inizio_validita DATE  NOT NULL,
    data_fine_validita DATE,
    CONSTRAINT pk_scerev_d_pratica_tipo PRIMARY KEY (pk_pratica_tipo)
);

COMMENT ON TABLE scerev.scerev_d_pratica_tipo IS 'Contiene dati inerenti alle tipologie di pratiche.';

COMMENT ON COLUMN scerev.scerev_d_pratica_tipo.codice_pratica_tipo IS 'Codice per identificare una tipologia di pratica. Si noti che si potranno avere tipologie combinate (es. straniero comunitario-cambio residenza e domicilio) ma alle quali sarà sempre assegnato un proprio codice.';

COMMENT ON COLUMN scerev.scerev_d_pratica_tipo.valore_pratica_tipo IS 'Valore relativo all''id della tipologia della pratica. Valori che troveremo nel menù a tendina ''tipologia richiesta''';

COMMENT ON COLUMN scerev.scerev_d_pratica_tipo.data_creazione IS 'Data creazione del record';

COMMENT ON COLUMN scerev.scerev_d_pratica_tipo.data_modifica IS 'Data pari al primo inserimento o dell''avvenuta modifica del record';

COMMENT ON COLUMN scerev.scerev_d_pratica_tipo.data_cancellazione IS 'Data valorizzata indica cancellazione logica del record';

COMMENT ON COLUMN scerev.scerev_d_pratica_tipo.data_inizio_validita IS 'Data inizio validità del record';

COMMENT ON COLUMN scerev.scerev_d_pratica_tipo.data_fine_validita IS 'Data fine validità del record';

/* ---------------------------------------------------------------------- */
/* Add table "scerev.scerev_d_regione"                                    */
/* ---------------------------------------------------------------------- */

CREATE TABLE scerev.scerev_d_regione (
    pk_regione INTEGER DEFAULT nextval('scerev_d_regione_pk_regione_seq')  NOT NULL,
    codice_regione TEXT  NOT NULL,
    nome_regione TEXT  NOT NULL,
    data_creazione TIMESTAMP  NOT NULL,
    data_modifica TIMESTAMP  NOT NULL,
    data_cancellazione TIMESTAMP,
    data_inizio_validita DATE  NOT NULL,
    data_fine_validita DATE,
    CONSTRAINT pk_scerev_d_regione PRIMARY KEY (pk_regione)
);

CREATE UNIQUE INDEX unique_codice_regione ON scerev.scerev_d_regione (codice_regione);

COMMENT ON TABLE scerev.scerev_d_regione IS 'Contiene dati inerenti alle regioni italiane.';

COMMENT ON COLUMN scerev.scerev_d_regione.codice_regione IS 'Codice per identificare una regione. Utile per popolare il menù a tendina ''regione''';

COMMENT ON COLUMN scerev.scerev_d_regione.nome_regione IS 'Nome della regione, ovvero valori che verranno visualizzati nel menù a tendina';

COMMENT ON COLUMN scerev.scerev_d_regione.data_creazione IS 'Data creazione/inserimento del record';

COMMENT ON COLUMN scerev.scerev_d_regione.data_modifica IS 'Data pari al primo inserimento o dell''avvenuta modifica del record';

COMMENT ON COLUMN scerev.scerev_d_regione.data_cancellazione IS 'Data valorizzata indica la cancellazione logica del record';

COMMENT ON COLUMN scerev.scerev_d_regione.data_inizio_validita IS 'Data inizio validità del record';

COMMENT ON COLUMN scerev.scerev_d_regione.data_fine_validita IS 'Data fine validità del record';

/* ---------------------------------------------------------------------- */
/* Add table "scerev.scerev_d_tipologia_medico"                           */
/* ---------------------------------------------------------------------- */

CREATE TABLE scerev.scerev_d_tipologia_medico (
    pk_tipologia_medico INTEGER DEFAULT nextval('scerev_d_tipologia_medico_pk_tipologia_medico_seq')  NOT NULL,
    codice_tipologia_medico TEXT  NOT NULL,
    valore_tipologia_medico TEXT  NOT NULL,
    data_creazione TIMESTAMP  NOT NULL,
    data_modifica TIMESTAMP  NOT NULL,
    data_cancellazione TIMESTAMP,
    data_inizio_validita DATE  NOT NULL,
    data_fine_validita DATE,
    CONSTRAINT pk_scerev_d_tipologia_medico PRIMARY KEY (pk_tipologia_medico)
);

CREATE UNIQUE INDEX unique_codice_tipologia_medico ON scerev.scerev_d_tipologia_medico (codice_tipologia_medico);

COMMENT ON TABLE scerev.scerev_d_tipologia_medico IS 'Contiene dati inerenti alle tipologie di medico.';

COMMENT ON COLUMN scerev.scerev_d_tipologia_medico.codice_tipologia_medico IS 'Codice per identificare una tipologia medico';

COMMENT ON COLUMN scerev.scerev_d_tipologia_medico.valore_tipologia_medico IS 'Valore della tipologia medico che comparira nella relativa sezione dell''interfaccia';

COMMENT ON COLUMN scerev.scerev_d_tipologia_medico.data_creazione IS 'Data inserimento del record';

COMMENT ON COLUMN scerev.scerev_d_tipologia_medico.data_modifica IS 'Data pari al primo inserimento o alla modifica del record';

COMMENT ON COLUMN scerev.scerev_d_tipologia_medico.data_cancellazione IS 'Data valorizzata indica cancellazione logica del record';

COMMENT ON COLUMN scerev.scerev_d_tipologia_medico.data_inizio_validita IS 'Data inizio validità del record';

COMMENT ON COLUMN scerev.scerev_d_tipologia_medico.data_fine_validita IS 'Data fine validità del record';

/* ---------------------------------------------------------------------- */
/* Add table "scerev.scerev_d_tipologia_profilo"                          */
/* ---------------------------------------------------------------------- */

CREATE TABLE scerev.scerev_d_tipologia_profilo (
    pk_tipologia_profilo INTEGER DEFAULT nextval('scerev_d_tipologia_profilo_pk_tipologia_profilo_seq')  NOT NULL,
    codice_tipologia_profilo TEXT  NOT NULL,
    valore_tipologia_profilo TEXT  NOT NULL,
    data_inizio_validita DATE  NOT NULL,
    data_fine_validita DATE,
    asstemp TEXT  NOT NULL,
    descr_ass_temp TEXT  NOT NULL,
    data_creazione TIMESTAMP  NOT NULL,
    data_modifica TIMESTAMP  NOT NULL,
    data_cancellazione TIMESTAMP,
    CONSTRAINT pk_scerev_d_tipologia_profilo PRIMARY KEY (pk_tipologia_profilo)
);

CREATE UNIQUE INDEX unique_codice_tipologia_profilo ON scerev.scerev_d_tipologia_profilo (codice_tipologia_profilo);

COMMENT ON TABLE scerev.scerev_d_tipologia_profilo IS 'Contiene dati inerenti alle tipologie di profilo.';

COMMENT ON COLUMN scerev.scerev_d_tipologia_profilo.codice_tipologia_profilo IS 'Codice per identificare una tipologia profilo che un operatore può assegnare al richiedente coinvolto nella pratica';

COMMENT ON COLUMN scerev.scerev_d_tipologia_profilo.valore_tipologia_profilo IS 'Valore della tipologia profilo che si può assegnare ad una pratica, ovvero sarà il valore che comparira nel menù a tendina ''tipologia profilo''';

COMMENT ON COLUMN scerev.scerev_d_tipologia_profilo.data_inizio_validita IS 'Data inizio validità del record';

COMMENT ON COLUMN scerev.scerev_d_tipologia_profilo.data_fine_validita IS 'Data fine validità del record';

COMMENT ON COLUMN scerev.scerev_d_tipologia_profilo.data_creazione IS 'Data creazione/inserimento del record';

COMMENT ON COLUMN scerev.scerev_d_tipologia_profilo.data_modifica IS 'Data pari al primo inserimento o dell''avvenuta modifica del record';

COMMENT ON COLUMN scerev.scerev_d_tipologia_profilo.data_cancellazione IS 'Data valorizzata indica cancellazione logica del record';

/* ---------------------------------------------------------------------- */
/* Add table "scerev.scerev_l_audit"                                      */
/* ---------------------------------------------------------------------- */

CREATE TABLE scerev.scerev_l_audit (
    pk_audit INTEGER DEFAULT nextval('scerev_l_audit_pk_audit_seq')  NOT NULL,
    data_ora TIMESTAMP  NOT NULL,
    id_app CHARACTER VARYING(100)  NOT NULL,
    ip_address CHARACTER VARYING(100),
    utente CHARACTER VARYING(100)  NOT NULL,
    utente_del CHARACTER VARYING(100),
    utente_ben CHARACTER VARYING(100)  NOT NULL,
    operazione CHARACTER VARYING(50)  NOT NULL,
    ogg_oper CHARACTER VARYING(500)  NOT NULL,
    idrequest CHARACTER VARYING(200),
    xcod_servizio CHARACTER VARYING(100),
    key_oper CHARACTER VARYING(9999),
    CONSTRAINT pk_scerev_l_audit PRIMARY KEY (pk_audit)
);

COMMENT ON TABLE scerev.scerev_l_audit IS 'Contiene dati di audit, per tracciare le operazioni che vengono effettuate nel sistema.';

COMMENT ON COLUMN scerev.scerev_l_audit.data_ora IS 'Data e ora di creazione del record';

COMMENT ON COLUMN scerev.scerev_l_audit.id_app IS 'Id dell''applicativo che ha invocato il servizio';

COMMENT ON COLUMN scerev.scerev_l_audit.ip_address IS 'Elenco degli IP che hanno gestito la richiesta';

COMMENT ON COLUMN scerev.scerev_l_audit.utente IS 'Codice fiscale dell''utente loggato oppure nome della componente dell''applicativo';

COMMENT ON COLUMN scerev.scerev_l_audit.utente_del IS 'Codice fiscale dell''utente delegato';

COMMENT ON COLUMN scerev.scerev_l_audit.utente_ben IS 'Codice fiscale dell''utente beneficiario';

COMMENT ON COLUMN scerev.scerev_l_audit.operazione IS 'Tipologia di operazione richiesta dal servizio';

COMMENT ON COLUMN scerev.scerev_l_audit.ogg_oper IS 'Nome del servizio e fase di esecuzione del servizio (request/response)';

COMMENT ON COLUMN scerev.scerev_l_audit.idrequest IS 'Id univoco (uuid v4) della chiamata HTTP che viene generato dal front-end';

COMMENT ON COLUMN scerev.scerev_l_audit.xcod_servizio IS 'Stringa identificativa del servizio chiamante';

COMMENT ON COLUMN scerev.scerev_l_audit.key_oper IS 'Dettaglio della fase di esecuzione del servizio';

/* ---------------------------------------------------------------------- */
/* Add table "scerev.scerev_log_aura"                                     */
/* ---------------------------------------------------------------------- */

CREATE TABLE scerev.scerev_log_aura (
    pk_log_aura INTEGER DEFAULT nextval('scerev_log_aura_pk_log_aura_seq')  NOT NULL,
    id_user_chiamante CHARACTER VARYING(100),
    id_user_assistito CHARACTER VARYING(100),
    azione TEXT  NOT NULL,
    xml_input TEXT,
    xml_output TEXT,
    data_ins DATE  NOT NULL,
    data_upd DATE,
    codice_esito CHARACTER VARYING(1)
);

/* ---------------------------------------------------------------------- */
/* Add table "scerev.scerev_r_azioni_gruppo_utenti"                       */
/* ---------------------------------------------------------------------- */

CREATE TABLE scerev.scerev_r_azioni_gruppo_utenti (
    pk_azioni_gruppo_utenti INTEGER DEFAULT nextval('scerev_r_azioni_gruppo_utenti_pk_azioni_gruppo_utenti_seq')  NOT NULL,
    fk_gruppo_utenti INTEGER  NOT NULL,
    fk_azione INTEGER  NOT NULL,
    CONSTRAINT pk_scerev_r_azioni_gruppo_utenti PRIMARY KEY (pk_azioni_gruppo_utenti)
);

CREATE INDEX ixfk_scerev_r_azioni_gruppo_utenti_scerev_d_gruppo_utenti ON scerev.scerev_r_azioni_gruppo_utenti (fk_gruppo_utenti);

CREATE INDEX ixfk_scerev_r_azioni_gruppo_utenti_scerev_d_azione ON scerev.scerev_r_azioni_gruppo_utenti (fk_azione);

CREATE UNIQUE INDEX unique_fk_gruppo_utenti_fk_azione ON scerev.scerev_r_azioni_gruppo_utenti (fk_gruppo_utenti,fk_azione);

/* ---------------------------------------------------------------------- */
/* Add table "scerev.scerev_r_motivo_soggiorno_documento_tipo"            */
/* ---------------------------------------------------------------------- */

CREATE TABLE scerev.scerev_r_motivo_soggiorno_documento_tipo (
    pk_motivo_soggiorno_documento_tipo INTEGER DEFAULT nextval('scerev_r_motivo_soggiorno_doc_pk_motivo_soggiorno_documento_seq')  NOT NULL,
    fk_motivo_soggiorno INTEGER  NOT NULL,
    fk_documento_tipo INTEGER  NOT NULL,
    CONSTRAINT pk_scerev_r_motivo_soggiorno_documento_tipo PRIMARY KEY (pk_motivo_soggiorno_documento_tipo)
);

CREATE INDEX ixfk_scerev_r_motivo_soggiorno_documento_tipo_scerev_d_motivo_s ON scerev.scerev_r_motivo_soggiorno_documento_tipo (fk_motivo_soggiorno);

CREATE INDEX ixfk_scerev_r_motivo_soggiorno_documento_tipo_scerev_d_document ON scerev.scerev_r_motivo_soggiorno_documento_tipo (fk_documento_tipo);

CREATE UNIQUE INDEX unique_fk_motivo_soggiorno_fk_documento_tipo ON scerev.scerev_r_motivo_soggiorno_documento_tipo (fk_motivo_soggiorno,fk_documento_tipo);

/* ---------------------------------------------------------------------- */
/* Add table "scerev.scerev_t_asl_operatore"                              */
/* ---------------------------------------------------------------------- */

CREATE TABLE scerev.scerev_t_asl_operatore (
    pk_asl_operatore INTEGER DEFAULT nextval('scerev_t_asl_operatore_pk_asl_operatore_seq')  NOT NULL,
    cf_operatore TEXT  NOT NULL,
    cognome_operatore TEXT  NOT NULL,
    nome_operatore TEXT  NOT NULL,
    fk_asl_operatore INTEGER  NOT NULL,
    data_inizio_validita DATE  NOT NULL,
    data_fine_validita DATE
);

/* ---------------------------------------------------------------------- */
/* Add table "scerev.scerev_t_file"                                       */
/* ---------------------------------------------------------------------- */

CREATE TABLE scerev.scerev_t_file (
    pk_file INTEGER DEFAULT nextval('scerev_t_file_pk_file_seq')  NOT NULL,
    name_file TEXT  NOT NULL,
    size_file NUMERIC  NOT NULL,
    file BYTEA  NOT NULL,
    data_creazione TIMESTAMP  NOT NULL,
    data_modifica TIMESTAMP  NOT NULL,
    data_cancellazione TIMESTAMP,
    CONSTRAINT pk_scerev_t_file PRIMARY KEY (pk_file)
);

COMMENT ON TABLE scerev.scerev_t_file IS 'Contiene informazioni relative ai file che si possono caricare.';

COMMENT ON COLUMN scerev.scerev_t_file.name_file IS 'Utile per salvare il nome del file';

COMMENT ON COLUMN scerev.scerev_t_file.size_file IS 'Utile per salvare le dimensioni del file';

COMMENT ON COLUMN scerev.scerev_t_file.file IS 'Utile per salvare il file fisico';

COMMENT ON COLUMN scerev.scerev_t_file.data_creazione IS 'Data creazione/inserimento del record';

COMMENT ON COLUMN scerev.scerev_t_file.data_modifica IS 'Data pari al primo inserimento o dell''avvenuta modifica del record';

COMMENT ON COLUMN scerev.scerev_t_file.data_cancellazione IS 'Data valorizzata indica cancellazione logica del record';

/* ---------------------------------------------------------------------- */
/* Add table "scerev.scerev_t_info_supplementari"                         */
/* ---------------------------------------------------------------------- */

CREATE TABLE scerev.scerev_t_info_supplementari (
    pk_info_supplementari INTEGER DEFAULT nextval('scerev_t_info_supplementari_pk_info_supplementari_seq')  NOT NULL,
    tipologia_lavoro BOOLEAN,
    professione TEXT,
    partita_iva TEXT,
    numero_iscrizione TEXT,
    ditta_datore_lavoro TEXT,
    numero_telefono_datore TEXT,
    provincia_lavoro TEXT,
    comune_lavoro TEXT,
    indirizzo_lavoro TEXT,
    numero_civico_lavoro TEXT,
    data_fine_contratto TIMESTAMP,
    nome_istituto TEXT,
    comune_sede_studio TEXT,
    provincia_sede_studio TEXT,
    indirizzo_sede_studio TEXT,
    civico_sede_studio TEXT,
    data_fine_iscrizione TIMESTAMP,
    nome_parente TEXT,
    cognome_parente TEXT,
    cf_parente CHARACTER VARYING(16),
    fk_grado_parentela INTEGER,
    data_creazione TIMESTAMP  NOT NULL,
    data_modifica TIMESTAMP  NOT NULL,
    data_cancellazione TIMESTAMP,
    motivazione_domanda TEXT,
    CONSTRAINT pk_scerev_t_info_supplementari PRIMARY KEY (pk_info_supplementari)
);

CREATE INDEX ixfk_scerev_t_info_supplementari_scerev_d_grado_parentela ON scerev.scerev_t_info_supplementari (fk_grado_parentela);

COMMENT ON TABLE scerev.scerev_t_info_supplementari IS 'Contiene dati inerenti alle informazioni supplementari legate ad una pratica.';

COMMENT ON COLUMN scerev.scerev_t_info_supplementari.tipologia_lavoro IS 'Se true lavoro = autonomo, se false lavoro = subordinato';

COMMENT ON COLUMN scerev.scerev_t_info_supplementari.professione IS 'Utile per salvare la professione che l''utente può inserire come informazione suppementare in caso di motivo domicilio = lavoro e tipo di lavoro = autonomo';

COMMENT ON COLUMN scerev.scerev_t_info_supplementari.partita_iva IS 'Utile per salvare la partita iva che l''utente può inserire come informazione suppementare in caso di motivo domicilio = lavoro e tipo di lavoro = autonomo';

COMMENT ON COLUMN scerev.scerev_t_info_supplementari.numero_iscrizione IS 'Utile per salvare il numero iscrizione che l''utente può inserire come informazione suppementare in caso di motivo domicilio = lavoro e tipo di lavoro = autonomo';

COMMENT ON COLUMN scerev.scerev_t_info_supplementari.ditta_datore_lavoro IS 'Utile per salvare la ditta / il datore che l''utente può inserire come informazione suppementare in caso di motivo domicilio = lavoro e tipo di lavoro = subordinato';

COMMENT ON COLUMN scerev.scerev_t_info_supplementari.numero_telefono_datore IS 'Utile per salvare il numero di telefono del datore che l''utente può inserire come informazione suppementare in caso di motivo domicilio = lavoro e e tipo di lavoro = subordinato';

COMMENT ON COLUMN scerev.scerev_t_info_supplementari.provincia_lavoro IS 'Utile per salvare la provincia della sede di lavoro che l''utente può inserire come informazione suppementare in caso di motivo domicilio = lavoro e e tipo di lavoro = subordinato';

COMMENT ON COLUMN scerev.scerev_t_info_supplementari.comune_lavoro IS 'Utile per salvare il comune della sede di lavoro che l''utente può inserire come informazione suppementare in caso di motivo domicilio = lavoro e e tipo di lavoro = subordinato';

COMMENT ON COLUMN scerev.scerev_t_info_supplementari.indirizzo_lavoro IS 'Utile per salvare l''indirizzo della sede di lavoro che l''utente può inserire come informazione suppementare in caso di motivo domicilio = lavoro e e tipo di lavoro = subordinato';

COMMENT ON COLUMN scerev.scerev_t_info_supplementari.numero_civico_lavoro IS 'Utile per salvare il numero civico della sede di lavoro che l''utente può inserire come informazione suppementare in caso di motivo domicilio = lavoro e e tipo di lavoro = subordinato';

COMMENT ON COLUMN scerev.scerev_t_info_supplementari.data_fine_contratto IS 'Utile per salvare la data di fine contratto che l''utente può inserire come informazione suppementare in caso di motivo domicilio = lavoro e e tipo di lavoro = subordinato';

COMMENT ON COLUMN scerev.scerev_t_info_supplementari.nome_istituto IS 'Utile per salvare il nome dell''istituto che l''utente può inserire come informazione suppementare in caso di motivo domicilio = studio';

COMMENT ON COLUMN scerev.scerev_t_info_supplementari.comune_sede_studio IS 'Utile per salvare la il comune della sede di studio che l''utente può inserire come informazione suppementare in caso di motivo domicilio = studio';

COMMENT ON COLUMN scerev.scerev_t_info_supplementari.provincia_sede_studio IS 'Utile per salvare la la provincia della sede di studio che l''utente può inserire come informazione suppementare in caso di motivo domicilio = studio';

COMMENT ON COLUMN scerev.scerev_t_info_supplementari.indirizzo_sede_studio IS 'Utile per salvare la l''indirizzo della sede di studio che l''utente può inserire come informazione suppementare in caso di motivo domicilio = studio';

COMMENT ON COLUMN scerev.scerev_t_info_supplementari.civico_sede_studio IS 'Utile per salvare il civico della sede di studio che l''utente può inserire come informazione suppementare in caso di motivo domicilio = studio';

COMMENT ON COLUMN scerev.scerev_t_info_supplementari.data_fine_iscrizione IS 'Utile per salvare la data di fine iscrizione che l''utente può inserire come informazione suppementare in caso di motivo domicilio = studio';

COMMENT ON COLUMN scerev.scerev_t_info_supplementari.nome_parente IS 'Utile per salvare il nome del parente che l''utente può inserire come informazione suppementare in caso di motivo domicilio = ricongiungimento familiare';

COMMENT ON COLUMN scerev.scerev_t_info_supplementari.cognome_parente IS 'Utile per salvare il cognome del parente che l''utente può inserire come informazione suppementare in caso di motivo domicilio = ricongiungimento familiare';

COMMENT ON COLUMN scerev.scerev_t_info_supplementari.cf_parente IS 'Utile per salvare il codice fiscale del parente che l''utente può inserire come informazione suppementare in caso di motivo domicilio = ricongiungimento familiare';

COMMENT ON COLUMN scerev.scerev_t_info_supplementari.fk_grado_parentela IS 'Utile per salvare il grado di parentela che l''utente può inserire come informazione suppementare in caso di motivo domicilio = ricongiungimento familiare';

COMMENT ON COLUMN scerev.scerev_t_info_supplementari.data_creazione IS 'Data creazione/inserimento del record';

COMMENT ON COLUMN scerev.scerev_t_info_supplementari.data_modifica IS 'Data pari al primo inserimento o dell''avvenuta modifica del record';

COMMENT ON COLUMN scerev.scerev_t_info_supplementari.data_cancellazione IS 'Data valorizzata indica cancellazione logica del record';

/* ---------------------------------------------------------------------- */
/* Add table "scerev.scerev_t_localita"                                   */
/* ---------------------------------------------------------------------- */

CREATE TABLE scerev.scerev_t_localita (
    pk_localita INTEGER DEFAULT nextval('scerev_t_localita_pk_localita_seq')  NOT NULL,
    fk_nazione INTEGER,
    comune TEXT  NOT NULL,
    cap TEXT  NOT NULL,
    indirizzo TEXT  NOT NULL,
    numero_civico TEXT  NOT NULL,
    data_creazione TIMESTAMP  NOT NULL,
    data_modifica TIMESTAMP  NOT NULL,
    data_cancellazione TIMESTAMP,
    codice_istat_comune TEXT  NOT NULL,
    CONSTRAINT pk_scerev_t_localita PRIMARY KEY (pk_localita)
);

CREATE INDEX ixfk_scerev_t_localita_scerev_d_nazione ON scerev.scerev_t_localita (fk_nazione);

COMMENT ON TABLE scerev.scerev_t_localita IS 'Contiene dati inerenti alle località, come domicilio o residenza.';

COMMENT ON COLUMN scerev.scerev_t_localita.fk_nazione IS 'Utile nel caso in cui un utente nell''inserimento della residenza debba specificare anche lo stato (nazione)';

COMMENT ON COLUMN scerev.scerev_t_localita.comune IS 'Comune della località';

COMMENT ON COLUMN scerev.scerev_t_localita.cap IS 'Cap della località';

COMMENT ON COLUMN scerev.scerev_t_localita.indirizzo IS 'Indirizzo della località';

COMMENT ON COLUMN scerev.scerev_t_localita.numero_civico IS 'Numero civico della località';

COMMENT ON COLUMN scerev.scerev_t_localita.data_creazione IS 'Data di creazione/inserimento record';

COMMENT ON COLUMN scerev.scerev_t_localita.data_modifica IS 'Data pari al primo inserimento o dell''avvenuta modifica del record';

COMMENT ON COLUMN scerev.scerev_t_localita.data_cancellazione IS 'Data valorizzata indica la cancellazione logica del record';

COMMENT ON COLUMN scerev.scerev_t_localita.codice_istat_comune IS 'Codice istat del comune della località';

/* ---------------------------------------------------------------------- */
/* Add table "scerev.scerev_t_monitoraggio"                               */
/* ---------------------------------------------------------------------- */

CREATE TABLE scerev.scerev_t_monitoraggio (
    pk_monitoraggio INTEGER DEFAULT nextval('scerev_t_monitoraggio_pk_monitoraggio_seq')  NOT NULL,
    cf_assistito CHARACTER VARYING(16)  NOT NULL,
    id_aura_medico TEXT  NOT NULL,
    data_creazione TIMESTAMP  NOT NULL,
    data_modifica TIMESTAMP  NOT NULL,
    data_cancellazione TIMESTAMP,
    cf_medico CHARACTER VARYING(16)  NOT NULL,
    nome_medico TEXT  NOT NULL,
    cognome_medico TEXT  NOT NULL,
    selezionabile BOOLEAN  NOT NULL,
    selezionabile_ricongiungimento_familiare BOOLEAN  NOT NULL,
    selezionabile_assistenza_temporanea BOOLEAN  NOT NULL,
    selezionabile_infanzia BOOLEAN  NOT NULL,
    CONSTRAINT pk_scerev_t_monitoraggio PRIMARY KEY (pk_monitoraggio)
);

COMMENT ON TABLE scerev.scerev_t_monitoraggio IS 'Contiene dati inerenti al monitoraggio.';

COMMENT ON COLUMN scerev.scerev_t_monitoraggio.cf_assistito IS 'Cf del cittadino assistito che richiede il monitoraggio';

COMMENT ON COLUMN scerev.scerev_t_monitoraggio.id_aura_medico IS 'Id AURA del medico, utile per poter reperire tutte le informazioni relative al medico';

COMMENT ON COLUMN scerev.scerev_t_monitoraggio.data_creazione IS 'Data creazione del record';

COMMENT ON COLUMN scerev.scerev_t_monitoraggio.data_modifica IS 'Data pari al primo inserimento o alla modifica del record';

COMMENT ON COLUMN scerev.scerev_t_monitoraggio.data_cancellazione IS 'Data valorizzata indica cancellazione logica del record';

COMMENT ON COLUMN scerev.scerev_t_monitoraggio.cf_medico IS 'CF del medico di cui si richiede il monitoraggio';

COMMENT ON COLUMN scerev.scerev_t_monitoraggio.nome_medico IS 'Nome del medico di cui si richiede il monitoraggio';

COMMENT ON COLUMN scerev.scerev_t_monitoraggio.cognome_medico IS 'Cognome del medico di cui si richiede il monitoraggio';

COMMENT ON COLUMN scerev.scerev_t_monitoraggio.selezionabile IS 'flag che indica se il medico ha posti disponibili';

COMMENT ON COLUMN scerev.scerev_t_monitoraggio.selezionabile_ricongiungimento_familiare IS 'flag che indica se il medico ha posti disponibili per ricongiungimento familiare';

COMMENT ON COLUMN scerev.scerev_t_monitoraggio.selezionabile_assistenza_temporanea IS 'flag che indica se il medico ha posti disponibili per assistenza temporanea';

COMMENT ON COLUMN scerev.scerev_t_monitoraggio.selezionabile_infanzia IS 'flag che indica se il medico ha posti disponibili per assistiti con età inferiore ai sei anni';

/* ---------------------------------------------------------------------- */
/* Add table "scerev.scerev_t_pratica"                                    */
/* ---------------------------------------------------------------------- */

CREATE TABLE scerev.scerev_t_pratica (
    pk_pratica INTEGER DEFAULT nextval('scerev_t_pratica_pk_pratica_seq')  NOT NULL,
    numero_pratica INTEGER  NOT NULL,
    tipologia_gestione_pratica TEXT  NOT NULL,
    fk_pratica_stato INTEGER  NOT NULL,
    cf_delegato CHARACTER VARYING(16),
    nome_delegato TEXT,
    cognome_delegato TEXT,
    cf_assistito CHARACTER VARYING(16)  NOT NULL,
    nome_assistito TEXT  NOT NULL,
    cognome_assistito TEXT  NOT NULL,
    luogo_nascita_assistito TEXT  NOT NULL,
    data_nascita_assistito TIMESTAMP  NOT NULL,
    sesso_assistito CHARACTER(1),
    fk_cittadinanza INTEGER,
    fk_localita_residenza_dichiarata INTEGER,
    fk_localita_domicilio_dichiarato INTEGER,
    fk_azienda_domicilio_dichiarata INTEGER,
    distretto_domicilio_dichiarato TEXT,
    ambito_domicilio_dichiarato TEXT,
    fk_azienda_iscrizione INTEGER  NOT NULL,
    asl_residenza TEXT  NOT NULL,
    cf_operatore CHARACTER VARYING(16),
    nome_operatore TEXT,
    cognome_operatore TEXT,
    id_aura_medico TEXT  NOT NULL,
    tipologia_medico TEXT  NOT NULL,
    nome_medico_precedente TEXT,
    cognome_medico_precedente TEXT,
    fk_regione INTEGER,
    codice_asl_precedente TEXT,
    fk_motivo_soggiorno INTEGER,
    fk_motivo_domicilio INTEGER,
    data_fine_assistenza_richiesta TIMESTAMP,
    data_inizio_assistenza_imposta TIMESTAMP,
    data_fine_assistenza_imposta TIMESTAMP,
    fk_tipologia_profilo INTEGER,
    fk_motivazione INTEGER,
    fk_info_supplementari INTEGER,
    deroga_pediatra BOOLEAN,
    medico_generico BOOLEAN,
    ricongiungimento_familiare BOOLEAN,
    deroga_territoriale BOOLEAN,
    deroga_associazione BOOLEAN,
    deroga_massimale BOOLEAN,
    deroga_massimale_temp BOOLEAN,
    medico_revocato BOOLEAN,
    data_chiusura TIMESTAMP,
    data_revoca_medico TIMESTAMP,
    data_revoca_assistenza TIMESTAMP,
    data_creazione TIMESTAMP  NOT NULL,
    data_modifica TIMESTAMP  NOT NULL,
    data_cancellazione TIMESTAMP,
    nome_medico TEXT  NOT NULL,
    cognome_medico TEXT  NOT NULL,
    fk_azienda_medico INTEGER  NOT NULL,
    fk_distretto_medico INTEGER  NOT NULL,
    fk_ambito_medico INTEGER,
    flag_autolimitato_medico BOOLEAN,
    codice_fiscale_medico TEXT  NOT NULL,
    recapito_telefonico_assistito CHARACTER VARYING(20),
    motivo_revoca_medico TEXT,
    CONSTRAINT pk_scerev_t_pratica PRIMARY KEY (pk_pratica)
);

CREATE INDEX ixfk_scerev_t_pratica_scerev_t_localita_02 ON scerev.scerev_t_pratica (fk_localita_domicilio_dichiarato);

CREATE INDEX ixfk_scerev_t_pratica_scerev_t_localita_01 ON scerev.scerev_t_pratica (fk_localita_residenza_dichiarata);

CREATE INDEX ixfk_scerev_t_pratica_scerev_t_info_supplementari ON scerev.scerev_t_pratica (fk_info_supplementari);

CREATE INDEX ixfk_scerev_t_pratica_scerev_d_tipologia_profilo ON scerev.scerev_t_pratica (fk_tipologia_profilo);

CREATE INDEX ixfk_scerev_t_pratica_scerev_d_regione ON scerev.scerev_t_pratica (fk_regione);

CREATE INDEX ixfk_scerev_t_pratica_scerev_d_pratica_stato ON scerev.scerev_t_pratica (fk_pratica_stato);

CREATE INDEX ixfk_scerev_t_pratica_scerev_d_motivo_soggiorno ON scerev.scerev_t_pratica (fk_motivo_soggiorno);

CREATE INDEX ixfk_scerev_t_pratica_scerev_d_motivo_domicilio ON scerev.scerev_t_pratica (fk_motivo_domicilio);

CREATE INDEX ixfk_scerev_t_pratica_scerev_d_motivazione ON scerev.scerev_t_pratica (fk_motivazione);

CREATE INDEX ixfk_scerev_t_pratica_scerev_d_distretto ON scerev.scerev_t_pratica (fk_distretto_medico);

CREATE INDEX ixfk_scerev_t_pratica_scerev_d_cittadinanza ON scerev.scerev_t_pratica (fk_cittadinanza);

CREATE INDEX ixfk_scerev_t_pratica_scerev_d_asl_03 ON scerev.scerev_t_pratica (fk_azienda_medico);

CREATE INDEX ixfk_scerev_t_pratica_scerev_d_asl_02 ON scerev.scerev_t_pratica (fk_azienda_iscrizione);

CREATE INDEX ixfk_scerev_t_pratica_scerev_d_asl_01 ON scerev.scerev_t_pratica (fk_azienda_domicilio_dichiarata);

CREATE INDEX ixfk_scerev_t_pratica_scerev_d_ambito ON scerev.scerev_t_pratica (fk_ambito_medico);

CREATE UNIQUE INDEX unique_numero_pratica ON scerev.scerev_t_pratica (numero_pratica);

COMMENT ON TABLE scerev.scerev_t_pratica IS 'Contiene dati inerenti alla pratica.';

COMMENT ON COLUMN scerev.scerev_t_pratica.numero_pratica IS 'Numero della pratica inserita nel sistema';

COMMENT ON COLUMN scerev.scerev_t_pratica.tipologia_gestione_pratica IS 'Tipologia della pratica in base a come viene gestita dal sistema (AUTOMATICA, BACK-OFFICE o BOZZA)';

COMMENT ON COLUMN scerev.scerev_t_pratica.fk_pratica_stato IS 'Id utile per capire lo stato della pratica (sarà anche lo stato da copiare in cronologia quando vi è un aggiornamento sulla richiesta)';

COMMENT ON COLUMN scerev.scerev_t_pratica.cf_delegato IS 'Codice Fiscale dell''eventuale Cittadino delegato che effettua la richiesta';

COMMENT ON COLUMN scerev.scerev_t_pratica.nome_delegato IS 'Nome dell''eventuale Cittadino delegato che effettua la richiesta';

COMMENT ON COLUMN scerev.scerev_t_pratica.cognome_delegato IS 'Cognome dell''eventuale Cittadino delegato che effettua la richiesta';

COMMENT ON COLUMN scerev.scerev_t_pratica.cf_assistito IS 'Codice Fiscale del Cittadino assistito (che in caso di deleghe sarebbe il delegante) a cui si riferisce la pratica. Utile per reperire i dati necessari da AURA o da TS (utile per interfaccia back office). (Potrà essere  l''utente da copiare in cronologia quando viene aggiornata la richiesta)';

COMMENT ON COLUMN scerev.scerev_t_pratica.nome_assistito IS 'Nome del Cittadino assistito (che in caso di deleghe sarebbe il delegante) a cui si riferisce la pratica. (Potrà essere  l''utente da copiare in cronologia quando viene aggiornata la richiesta)';

COMMENT ON COLUMN scerev.scerev_t_pratica.cognome_assistito IS 'Cognome del Cittadino assistito (che in caso di deleghe sarebbe il delegante) a cui si riferisce la pratica. (Potrà essere  l''utente da copiare in cronologia quando viene aggiornata la richiesta)';

COMMENT ON COLUMN scerev.scerev_t_pratica.luogo_nascita_assistito IS 'Luogo di nascita del cittadino assistito, recuperato dal sistema AURA o TS, oppure inseriti dall''utente';

COMMENT ON COLUMN scerev.scerev_t_pratica.data_nascita_assistito IS 'Data di nascita del cittadino assistito, recuperata dal sistema AURA o TS, oppure inseriti dall''utente';

COMMENT ON COLUMN scerev.scerev_t_pratica.sesso_assistito IS 'Sesso dell''assistito recuperato da AURA o TS, oppure inserito dall''utente';

COMMENT ON COLUMN scerev.scerev_t_pratica.fk_cittadinanza IS 'Cittadinanza presa da sistema AURA o dichiarata dal cittadino';

COMMENT ON COLUMN scerev.scerev_t_pratica.fk_localita_residenza_dichiarata IS 'Residenza dichiarata dall''utente in fase di richiesta assistenza, che può corrispondere con quello che c''è su AURA o potrebbe essere nuovo';

COMMENT ON COLUMN scerev.scerev_t_pratica.fk_localita_domicilio_dichiarato IS 'Domicilio dichiarata dall''utente in fase di richiesta assistenza, che può corrispondere con quello che c''è su AURA o potrebbe essere nuovo';

COMMENT ON COLUMN scerev.scerev_t_pratica.fk_azienda_domicilio_dichiarata IS 'Asl domicilio inserita dal cittadino assistito';

COMMENT ON COLUMN scerev.scerev_t_pratica.distretto_domicilio_dichiarato IS 'Pari al CODICE_DISTRETTO calcolato a partire dall''indirizzo di domcilio inserito dal cittadino assistito';

COMMENT ON COLUMN scerev.scerev_t_pratica.ambito_domicilio_dichiarato IS 'Pari al CODICE_AMBITO calcolato a partire dall''indirizzo di domcilio inserito dal cittadino assistito';

COMMENT ON COLUMN scerev.scerev_t_pratica.fk_azienda_iscrizione IS 'Asl iscrizione inserita dal cittadino assistito';

COMMENT ON COLUMN scerev.scerev_t_pratica.asl_residenza IS 'ASL di residenza dell''assistito utile ai fini della richiesta (coincide con quella dichiarata dall''assistito se cambiata rispetto a quella presente in AURA)';

COMMENT ON COLUMN scerev.scerev_t_pratica.cf_operatore IS 'Codice Fiscale dell''ultimo operatore ASL che ha preso in carico la richiesta.  (Questo operatore potrà essere  l''utente da copiare in cronologia quando viene aggiornata la richiesta)';

COMMENT ON COLUMN scerev.scerev_t_pratica.nome_operatore IS 'Nome dell''ultimo operatore ASL che ha preso in carico la richiesta.  (Questo operatore potrà essere  l''utente da copiare in cronologia quando viene aggiornata la richiesta)';

COMMENT ON COLUMN scerev.scerev_t_pratica.cognome_operatore IS 'Cognome dell''ultimo operatore ASL che ha preso in carico la richiesta.  (Questo operatore potrà essere  l''utente da copiare in cronologia quando viene aggiornata la richiesta)';

COMMENT ON COLUMN scerev.scerev_t_pratica.id_aura_medico IS 'ID AURA del medico selezionato, utile per poter reperire tutte le sue info';

COMMENT ON COLUMN scerev.scerev_t_pratica.tipologia_medico IS 'Tipologia del medico selezionato, da recuperare da AURA o OPESSAN, utile per filtrare in ricerca la pratiche per tipologia medico';

COMMENT ON COLUMN scerev.scerev_t_pratica.nome_medico_precedente IS 'Nome del medico precedente che un cittadino può indicare';

COMMENT ON COLUMN scerev.scerev_t_pratica.cognome_medico_precedente IS 'Cognome del medico precedente che un cittadino può indicare';

COMMENT ON COLUMN scerev.scerev_t_pratica.fk_regione IS 'Id da salvare in caso viene indicata la regione relativa alla asl del medico precedente. Id relativo alla tabella REGIONE, la quale conterrà i dati delle regioni ';

COMMENT ON COLUMN scerev.scerev_t_pratica.codice_asl_precedente IS 'Codice identificativo della asl precedente che un cittadino può indicare';

COMMENT ON COLUMN scerev.scerev_t_pratica.fk_motivo_soggiorno IS 'Utile nel momento in cui la procedura di richiesta richiede ad un utente  il motivo di soggiorno';

COMMENT ON COLUMN scerev.scerev_t_pratica.fk_motivo_domicilio IS 'Utile nel momento in cui la procedura di richiesta richiede ad un utente  il motivo di domicilio';

COMMENT ON COLUMN scerev.scerev_t_pratica.data_fine_assistenza_richiesta IS 'Data di fine, indicata dal cittadino, in caso di richiesta di rinnovo  assistenza';

COMMENT ON COLUMN scerev.scerev_t_pratica.data_inizio_assistenza_imposta IS 'Data di partenza, indicata dall''operatore asl, per una richiesta di  assistenza';

COMMENT ON COLUMN scerev.scerev_t_pratica.data_fine_assistenza_imposta IS 'Data di fine, indicata dall''operatore asl, per una richiesta di  assistenza';

COMMENT ON COLUMN scerev.scerev_t_pratica.fk_tipologia_profilo IS 'Utile se l''operatore di back office indica un profilo da assegnare al cittadino (profilo preso da un menu a tendina, per cui avremo il riferimento all''id del relativo elemento preso dall''insieme delle tipologie profili)';

COMMENT ON COLUMN scerev.scerev_t_pratica.fk_motivazione IS 'Utile se l''operatore di back office respinge o chiede la rettifica di una pratica indicando una motivazione (motivazione presa da un menu a tendina, per cui avremo il riferimento all''id del relativo elemento preso dall''insieme delle motivazioni definite)';

COMMENT ON COLUMN scerev.scerev_t_pratica.fk_info_supplementari IS 'Utile per avere il riferimento alle informazioni supplementari  che l''utente può inserire in determinati casi';

COMMENT ON COLUMN scerev.scerev_t_pratica.deroga_pediatra IS 'Utile nel caso in cui l''operatore, durante l''analisi della pratica, decide di spuntare la relativa check box presente nell''interfaccia';

COMMENT ON COLUMN scerev.scerev_t_pratica.medico_generico IS 'Utile nel caso in cui l''operatore, durante l''analisi della pratica, decide di spuntare la relativa check box presente nell''interfaccia';

COMMENT ON COLUMN scerev.scerev_t_pratica.ricongiungimento_familiare IS 'Utile nel caso in cui l''operatore, durante l''analisi della pratica, decide di spuntare la relativa check box presente nell''interfaccia';

COMMENT ON COLUMN scerev.scerev_t_pratica.deroga_territoriale IS 'Utile nel caso in cui l''operatore, durante l''analisi della pratica, decide di spuntare la relativa check box presente nell''interfaccia';

COMMENT ON COLUMN scerev.scerev_t_pratica.deroga_associazione IS 'Utile nel caso in cui l''operatore, durante l''analisi della pratica, decide di spuntare la relativa check box presente nell''interfaccia';

COMMENT ON COLUMN scerev.scerev_t_pratica.deroga_massimale IS 'Utile nel caso in cui l''operatore, durante l''analisi della pratica, decide di spuntare la relativa check box presente nell''interfaccia';

COMMENT ON COLUMN scerev.scerev_t_pratica.deroga_massimale_temp IS 'Utile nel caso in cui l''operatore, durante l''analisi della pratica, decide di spuntare la relativa check box presente nell''interfaccia';

COMMENT ON COLUMN scerev.scerev_t_pratica.medico_revocato IS 'Utile nel caso in cui l''operatore, durante l''analisi della pratica, decide di spuntare la relativa check box presente nell''interfaccia';

COMMENT ON COLUMN scerev.scerev_t_pratica.data_chiusura IS 'Data valorizzata indica chiusura della pratica da parte di un operatore';

COMMENT ON COLUMN scerev.scerev_t_pratica.data_revoca_medico IS 'Se valorizzata indica che il cittadino ha richiesto la revoca del medico indicato in questa pratica';

COMMENT ON COLUMN scerev.scerev_t_pratica.data_revoca_assistenza IS 'Se valorizzata indica che il cittadino ha richiesto la revoca dell''assistenza indicata in questa pratica';

COMMENT ON COLUMN scerev.scerev_t_pratica.data_creazione IS 'Data inserimento pratica da parte del cittadino';

COMMENT ON COLUMN scerev.scerev_t_pratica.data_modifica IS 'Data pari al primo inserimento o dell''avvenuta modifica del record';

COMMENT ON COLUMN scerev.scerev_t_pratica.data_cancellazione IS 'Data valorizzata indica cancellazione logica del record';

COMMENT ON COLUMN scerev.scerev_t_pratica.nome_medico IS 'Nome del medico scelto';

COMMENT ON COLUMN scerev.scerev_t_pratica.cognome_medico IS 'Cognome del medico scelto';

COMMENT ON COLUMN scerev.scerev_t_pratica.fk_azienda_medico IS 'Id relativo all''azienda del medico scelto';

COMMENT ON COLUMN scerev.scerev_t_pratica.fk_distretto_medico IS 'Id relativo al domicilio del medico scelto';

COMMENT ON COLUMN scerev.scerev_t_pratica.fk_ambito_medico IS 'Id relativo all''ambito del medico scelto';

COMMENT ON COLUMN scerev.scerev_t_pratica.flag_autolimitato_medico IS 'Valore booleano per determinare se un medico è autolimitato (true) o meno (false)';

COMMENT ON COLUMN scerev.scerev_t_pratica.codice_fiscale_medico IS 'Codice fiscale del medico scelto';

COMMENT ON COLUMN scerev.scerev_t_pratica.recapito_telefonico_assistito IS 'Indica il recapito telefonico dell''assistito';

/* ---------------------------------------------------------------------- */
/* Add table "scerev.version_control"                                     */
/* ---------------------------------------------------------------------- */

CREATE TABLE scerev.version_control (
    change_number INTEGER DEFAULT nextval('version_control_change_number_seq')  NOT NULL,
    release_version CHARACTER VARYING(11),
    description CHARACTER VARYING(200)  NOT NULL,
    release_type CHARACTER VARYING(20)  NOT NULL,
    script_name CHARACTER VARYING(1000)  NOT NULL,
    checksum UUID,
    installed_by CHARACTER VARYING(100)  NOT NULL,
    installed_on TIMESTAMP DEFAULT now()  NOT NULL,
    execution_time INTEGER  NOT NULL,
    success BOOLEAN  NOT NULL,
    CONSTRAINT version_control_pkey PRIMARY KEY (change_number)
);

/* ---------------------------------------------------------------------- */
/* Add table "scerev.scerev_d_ambito"                                     */
/* ---------------------------------------------------------------------- */

CREATE TABLE scerev.scerev_d_ambito (
    pk_ambito INTEGER DEFAULT nextval('scerev_d_ambito_pk_ambito_seq')  NOT NULL,
    codice_ambito TEXT  NOT NULL,
    denominazione_ambito TEXT  NOT NULL,
    fk_distretto INTEGER,
    data_inizio_validita DATE  NOT NULL,
    data_fine_validita DATE,
    data_creazione TIMESTAMP  NOT NULL,
    data_modifica TIMESTAMP  NOT NULL,
    data_cancellazione TIMESTAMP,
    CONSTRAINT pk_scerev_d_ambito PRIMARY KEY (pk_ambito)
);

CREATE INDEX ixfk_scerev_d_ambito_scerev_d_distretto ON scerev.scerev_d_ambito (fk_distretto);

COMMENT ON TABLE scerev.scerev_d_ambito IS 'Contiene dati inerenti agli ambiti';

COMMENT ON COLUMN scerev.scerev_d_ambito.codice_ambito IS 'Codice identificativo dell''ambito';

COMMENT ON COLUMN scerev.scerev_d_ambito.denominazione_ambito IS 'Denominazione dell''ambito';

COMMENT ON COLUMN scerev.scerev_d_ambito.fk_distretto IS 'Indice del distretto appartenente all''ambito';

COMMENT ON COLUMN scerev.scerev_d_ambito.data_inizio_validita IS 'Data inizio validità del record';

COMMENT ON COLUMN scerev.scerev_d_ambito.data_fine_validita IS 'Data fine validità del record';

COMMENT ON COLUMN scerev.scerev_d_ambito.data_creazione IS 'Data creazione/inserimento del record';

COMMENT ON COLUMN scerev.scerev_d_ambito.data_modifica IS 'Data pari al primo inserimento o dell''avvenuta modifica del record';

COMMENT ON COLUMN scerev.scerev_d_ambito.data_cancellazione IS 'Data cancellazione del record';

/* ---------------------------------------------------------------------- */
/* Add table "scerev.scerev_r_pratica_pratica_tipo"                       */
/* ---------------------------------------------------------------------- */

CREATE TABLE scerev.scerev_r_pratica_pratica_tipo (
    pk_pratica_pratica_tipo INTEGER DEFAULT nextval('scerev_r_pratica_pratica_tipo_pk_pratica_pratica_tipo_seq')  NOT NULL,
    fk_pratica INTEGER  NOT NULL,
    fk_pratica_tipo INTEGER  NOT NULL,
    data_creazione TIMESTAMP  NOT NULL,
    data_modifica TIMESTAMP  NOT NULL,
    data_cancellazione TIMESTAMP,
    CONSTRAINT pk_scerev_r_pratica_pratica_tipo PRIMARY KEY (pk_pratica_pratica_tipo)
);

CREATE INDEX ixfk_scerev_r_pratica_pratica_tipo_scerev_t_pratica ON scerev.scerev_r_pratica_pratica_tipo (fk_pratica);

CREATE INDEX ixfk_scerev_r_pratica_pratica_tipo_scerev_d_pratica_tipo ON scerev.scerev_r_pratica_pratica_tipo (fk_pratica_tipo);

CREATE UNIQUE INDEX unique_fk_pratica_fk_pratica_tipo ON scerev.scerev_r_pratica_pratica_tipo (fk_pratica,fk_pratica_tipo);

COMMENT ON TABLE scerev.scerev_r_pratica_pratica_tipo IS 'Contiene dati inerenti alle tipologie di pratica che si possono assegnare ad una singola pratica.';

COMMENT ON COLUMN scerev.scerev_r_pratica_pratica_tipo.fk_pratica IS 'Indice identificativo della pratica';

COMMENT ON COLUMN scerev.scerev_r_pratica_pratica_tipo.fk_pratica_tipo IS 'Indice identificativo del tipo di pratica';

COMMENT ON COLUMN scerev.scerev_r_pratica_pratica_tipo.data_creazione IS 'Data inserimento/creazione del record';

COMMENT ON COLUMN scerev.scerev_r_pratica_pratica_tipo.data_modifica IS 'Data pari al primo inserimento o dell''avvenuta modifica del record';

COMMENT ON COLUMN scerev.scerev_r_pratica_pratica_tipo.data_cancellazione IS 'Data valorizzata indica la cancellazione logica del record';

/* ---------------------------------------------------------------------- */
/* Add table "scerev.scerev_t_cronologia"                                 */
/* ---------------------------------------------------------------------- */

CREATE TABLE scerev.scerev_t_cronologia (
    pk_cronologia INTEGER DEFAULT nextval('scerev_t_cronologia_pk_cronologia_seq')  NOT NULL,
    fk_pratica INTEGER  NOT NULL,
    nota_richiedente TEXT,
    nota_interna TEXT,
    utente TEXT  NOT NULL,
    fk_pratica_stato INTEGER  NOT NULL,
    data_ora_inserimento TIMESTAMP  NOT NULL,
    data_creazione DATE  NOT NULL,
    data_modifica DATE  NOT NULL,
    data_cancellazione DATE,
    CONSTRAINT pk_scerev_t_cronologia PRIMARY KEY (pk_cronologia)
);

CREATE INDEX ixfk_scerev_t_cronologia_scerev_t_pratica ON scerev.scerev_t_cronologia (fk_pratica);

CREATE INDEX ixfk_scerev_t_cronologia_scerev_d_pratica_stato ON scerev.scerev_t_cronologia (fk_pratica_stato);

COMMENT ON TABLE scerev.scerev_t_cronologia IS 'Contiene dati inerenti alla cronologia della pratica.';

COMMENT ON COLUMN scerev.scerev_t_cronologia.fk_pratica IS 'Utile per aver il riferimento della pratica a cui la cronologia si riferisce';

COMMENT ON COLUMN scerev.scerev_t_cronologia.nota_richiedente IS 'Utile per evidenziare delle richieste che l''operatore fa all''utente (e viceversa) durante la valutazione della richiesta';

COMMENT ON COLUMN scerev.scerev_t_cronologia.nota_interna IS 'Utile per evidenziare delle note che l''operatore fa ad uso interno tra i vari operatori, visto che la gestione della richiesta può passare tra diversi operatori';

COMMENT ON COLUMN scerev.scerev_t_cronologia.utente IS 'Utente che inserisce nota per questo stato della cronologia. Potrà essere NOME COGNOME del cittadino o OPERATORE ID dell''operatore.';

COMMENT ON COLUMN scerev.scerev_t_cronologia.fk_pratica_stato IS 'Stato della pratica in quel momento cronologico';

COMMENT ON COLUMN scerev.scerev_t_cronologia.data_ora_inserimento IS 'Data e ora relativi al rilascio del nuovo stato cronologico';

COMMENT ON COLUMN scerev.scerev_t_cronologia.data_creazione IS 'Data creazione/inserimento del record';

COMMENT ON COLUMN scerev.scerev_t_cronologia.data_modifica IS 'Data pari al primo inserimento o dell''avvenuta modifica del record';

COMMENT ON COLUMN scerev.scerev_t_cronologia.data_cancellazione IS 'Data valorizzata indica cancellazione logica del record';

/* ---------------------------------------------------------------------- */
/* Add table "scerev.scerev_t_documento"                                  */
/* ---------------------------------------------------------------------- */

CREATE TABLE scerev.scerev_t_documento (
    pk_documento INTEGER DEFAULT nextval('scerev_t_documento_pk_documento_seq')  NOT NULL,
    fk_documento_tipo INTEGER  NOT NULL,
    fk_file INTEGER  NOT NULL,
    fk_pratica INTEGER  NOT NULL,
    data_creazione TIMESTAMP  NOT NULL,
    data_modifica TIMESTAMP  NOT NULL,
    data_cancellazione TIMESTAMP,
    CONSTRAINT pk_scerev_t_documento PRIMARY KEY (pk_documento)
);

CREATE INDEX ixfk_scerev_t_documento_scerev_t_pratica ON scerev.scerev_t_documento (fk_pratica);

CREATE INDEX ixfk_scerev_t_documento_scerev_t_file ON scerev.scerev_t_documento (fk_file);

CREATE INDEX ixfk_scerev_t_documento_scerev_d_documento_tipo ON scerev.scerev_t_documento (fk_documento_tipo);

COMMENT ON TABLE scerev.scerev_t_documento IS 'Contiene dati inerenti ai documenti che si possono caricare.';

COMMENT ON COLUMN scerev.scerev_t_documento.fk_documento_tipo IS 'Utile per identificare il tipo di documento che si sta inserendo';

COMMENT ON COLUMN scerev.scerev_t_documento.fk_file IS 'Utile per legare il file fisico legato a questo documento';

COMMENT ON COLUMN scerev.scerev_t_documento.fk_pratica IS 'Id facente riferimento alla pratica in cui viene allegato questo documento';

COMMENT ON COLUMN scerev.scerev_t_documento.data_creazione IS 'Data creazione/inserimento delr record';

COMMENT ON COLUMN scerev.scerev_t_documento.data_modifica IS 'Data pari al primo inserimento o dell''avvenuta modifica del record';

COMMENT ON COLUMN scerev.scerev_t_documento.data_cancellazione IS 'Data valorizzata indica cancellazione logica del record';

/* ---------------------------------------------------------------------- */
/* Add foreign key constraints                                            */
/* ---------------------------------------------------------------------- */

ALTER TABLE scerev.scerev_d_ambito ADD CONSTRAINT fk_scerev_d_ambito_scerev_d_distretto 
    FOREIGN KEY (fk_distretto) REFERENCES scerev.scerev_d_distretto (pk_distretto);

ALTER TABLE scerev.scerev_d_distretto ADD CONSTRAINT fk_scerev_d_distretto_scerev_d_asl 
    FOREIGN KEY (fk_azienda) REFERENCES scerev.scerev_d_asl (pk_azienda);

ALTER TABLE scerev.scerev_r_azioni_gruppo_utenti ADD CONSTRAINT fk_scerev_r_azioni_gruppo_utenti_scerev_d_azione 
    FOREIGN KEY (fk_azione) REFERENCES scerev.scerev_d_azione (pk_azione);

ALTER TABLE scerev.scerev_r_azioni_gruppo_utenti ADD CONSTRAINT fk_scerev_r_azioni_gruppo_utenti_scerev_d_gruppo_utenti 
    FOREIGN KEY (fk_gruppo_utenti) REFERENCES scerev.scerev_d_gruppo_utenti (pk_gruppo_utenti);

ALTER TABLE scerev.scerev_r_motivo_soggiorno_documento_tipo ADD CONSTRAINT fk_scerev_r_motivo_soggiorno_documento_tipo_scerev_d_documento_ 
    FOREIGN KEY (fk_documento_tipo) REFERENCES scerev.scerev_d_documento_tipo (pk_documento_tipo);

ALTER TABLE scerev.scerev_r_motivo_soggiorno_documento_tipo ADD CONSTRAINT fk_scerev_r_motivo_soggiorno_documento_tipo_scerev_d_motivo_sog 
    FOREIGN KEY (fk_motivo_soggiorno) REFERENCES scerev.scerev_d_motivo_soggiorno (pk_motivo_soggiorno);

ALTER TABLE scerev.scerev_r_pratica_pratica_tipo ADD CONSTRAINT fk_scerev_r_pratica_pratica_tipo_scerev_d_pratica_tipo 
    FOREIGN KEY (fk_pratica_tipo) REFERENCES scerev.scerev_d_pratica_tipo (pk_pratica_tipo);

ALTER TABLE scerev.scerev_r_pratica_pratica_tipo ADD CONSTRAINT fk_scerev_r_pratica_pratica_tipo_scerev_t_pratica 
    FOREIGN KEY (fk_pratica) REFERENCES scerev.scerev_t_pratica (pk_pratica);

ALTER TABLE scerev.scerev_t_asl_operatore ADD CONSTRAINT fk_scerev_t_asl_operatore_scerev_d_asl 
    FOREIGN KEY (fk_asl_operatore) REFERENCES scerev.scerev_d_asl (pk_azienda);

ALTER TABLE scerev.scerev_t_cronologia ADD CONSTRAINT fk_scerev_t_cronologia_scerev_d_pratica_stato 
    FOREIGN KEY (fk_pratica_stato) REFERENCES scerev.scerev_d_pratica_stato (pk_pratica_stato);

ALTER TABLE scerev.scerev_t_cronologia ADD CONSTRAINT fk_scerev_t_cronologia_scerev_t_pratica 
    FOREIGN KEY (fk_pratica) REFERENCES scerev.scerev_t_pratica (pk_pratica);

ALTER TABLE scerev.scerev_t_documento ADD CONSTRAINT fk_scerev_t_documento_scerev_d_documento_tipo 
    FOREIGN KEY (fk_documento_tipo) REFERENCES scerev.scerev_d_documento_tipo (pk_documento_tipo);

ALTER TABLE scerev.scerev_t_documento ADD CONSTRAINT fk_scerev_t_documento_scerev_t_file 
    FOREIGN KEY (fk_file) REFERENCES scerev.scerev_t_file (pk_file);

ALTER TABLE scerev.scerev_t_documento ADD CONSTRAINT fk_scerev_t_documento_scerev_t_pratica 
    FOREIGN KEY (fk_pratica) REFERENCES scerev.scerev_t_pratica (pk_pratica);

ALTER TABLE scerev.scerev_t_info_supplementari ADD CONSTRAINT fk_scerev_t_info_supplementari_scerev_d_grado_parentela 
    FOREIGN KEY (fk_grado_parentela) REFERENCES scerev.scerev_d_grado_parentela (pk_grado_parentela);

ALTER TABLE scerev.scerev_t_localita ADD CONSTRAINT fk_scerev_t_localita_scerev_d_nazione 
    FOREIGN KEY (fk_nazione) REFERENCES scerev.scerev_d_nazione (pk_nazione);

ALTER TABLE scerev.scerev_t_pratica ADD CONSTRAINT fk_scerev_t_pratica_scerev_d_asl_01 
    FOREIGN KEY (fk_azienda_domicilio_dichiarata) REFERENCES scerev.scerev_d_asl (pk_azienda);

ALTER TABLE scerev.scerev_t_pratica ADD CONSTRAINT fk_scerev_t_pratica_scerev_d_asl_02 
    FOREIGN KEY (fk_azienda_iscrizione) REFERENCES scerev.scerev_d_asl (pk_azienda);

ALTER TABLE scerev.scerev_t_pratica ADD CONSTRAINT fk_scerev_t_pratica_scerev_d_asl_03 
    FOREIGN KEY (fk_azienda_medico) REFERENCES scerev.scerev_d_asl (pk_azienda);

ALTER TABLE scerev.scerev_t_pratica ADD CONSTRAINT fk_scerev_t_pratica_scerev_d_cittadinanza 
    FOREIGN KEY (fk_cittadinanza) REFERENCES scerev.scerev_d_cittadinanza (pk_cittadinanza);

ALTER TABLE scerev.scerev_t_pratica ADD CONSTRAINT fk_scerev_t_pratica_scerev_d_distretto 
    FOREIGN KEY (fk_distretto_medico) REFERENCES scerev.scerev_d_distretto (pk_distretto);

ALTER TABLE scerev.scerev_t_pratica ADD CONSTRAINT fk_scerev_t_pratica_scerev_d_motivazione 
    FOREIGN KEY (fk_motivazione) REFERENCES scerev.scerev_d_motivazione (pk_motivazione);

ALTER TABLE scerev.scerev_t_pratica ADD CONSTRAINT fk_scerev_t_pratica_scerev_d_motivo_domicilio 
    FOREIGN KEY (fk_motivo_domicilio) REFERENCES scerev.scerev_d_motivo_domicilio (pk_motivo_domicilio);

ALTER TABLE scerev.scerev_t_pratica ADD CONSTRAINT fk_scerev_t_pratica_scerev_d_motivo_soggiorno 
    FOREIGN KEY (fk_motivo_soggiorno) REFERENCES scerev.scerev_d_motivo_soggiorno (pk_motivo_soggiorno);

ALTER TABLE scerev.scerev_t_pratica ADD CONSTRAINT fk_scerev_t_pratica_scerev_d_pratica_stato 
    FOREIGN KEY (fk_pratica_stato) REFERENCES scerev.scerev_d_pratica_stato (pk_pratica_stato);

ALTER TABLE scerev.scerev_t_pratica ADD CONSTRAINT fk_scerev_t_pratica_scerev_d_regione 
    FOREIGN KEY (fk_regione) REFERENCES scerev.scerev_d_regione (pk_regione);

ALTER TABLE scerev.scerev_t_pratica ADD CONSTRAINT fk_scerev_t_pratica_scerev_d_tipologia_profilo 
    FOREIGN KEY (fk_tipologia_profilo) REFERENCES scerev.scerev_d_tipologia_profilo (pk_tipologia_profilo);

ALTER TABLE scerev.scerev_t_pratica ADD CONSTRAINT fk_scerev_t_pratica_scerev_t_info_supplementari 
    FOREIGN KEY (fk_info_supplementari) REFERENCES scerev.scerev_t_info_supplementari (pk_info_supplementari);

ALTER TABLE scerev.scerev_t_pratica ADD CONSTRAINT fk_scerev_t_pratica_scerev_t_localita_01 
    FOREIGN KEY (fk_localita_residenza_dichiarata) REFERENCES scerev.scerev_t_localita (pk_localita);

ALTER TABLE scerev.scerev_t_pratica ADD CONSTRAINT fk_scerev_t_pratica_scerev_t_localita_02 
    FOREIGN KEY (fk_localita_domicilio_dichiarato) REFERENCES scerev.scerev_t_localita (pk_localita);

/* ---------------------------------------------------------------------- */
/* Add procedures                                                         */
/* ---------------------------------------------------------------------- */

CREATE OR REPLACE FUNCTION scerev.aggiornacompletateextraregionesenzamedico () RETURNS integer AS
$BODY$
DECLARE
counter int := 0;
my_curs CURSOR FOR
select * from  scerev.scerev_t_pratica p
where FK_PRATICA_STATO = 6
 and asl_residenza = 'Extra-regione'
 and nome_medico_precedente is null
 and cognome_medico_precedente is null;
my_record scerev_t_pratica%rowtype;

BEGIN
FOR my_record IN my_curs
LOOP
counter := counter + 1;
INSERT INTO scerev.scerev_t_cronologia (fk_pratica,nota_interna,utente,fk_pratica_stato,data_ora_inserimento,data_creazione,data_modifica)
VALUES (my_record.numero_pratica,'L''assistito Extraregione non aveva medico precedente e quindi si aggiorna lo stato della pratica da Completata a Chiusa' ,'OPERATORE:'||'CSI PIEMONTE ',my_record.fk_pratica_stato,NOW(),NOW(),NOW());

update scerev.scerev_t_pratica
set fk_pratica_stato = 8,
    data_modifica = now()
where numero_pratica=(my_record.numero_pratica);

END LOOP;
RETURN counter;
commit;
END; $BODY$
LANGUAGE 'plpgsql' VOLATILE;

CREATE OR REPLACE FUNCTION scerev.aggiornamotivodidomicilio () RETURNS integer AS
$BODY$
DECLARE
counter int := 0;
my_curs CURSOR FOR
select numero_pratica from  scerev.scerev_t_pratica p
where p.fk_motivo_domicilio = 5
and p.ricongiungimento_familiare = 'Y'
order by p.data_creazione  desc;
my_record scerev_t_pratica%rowtype;

BEGIN
FOR my_record IN my_curs
LOOP
counter := counter + 1;

update scerev.scerev_t_pratica
set fk_motivo_domicilio = 4 ,
    data_modifica = now()
where numero_pratica = my_record.numero_pratica;

--INSERT INTO scerev.scerev_t_cronologia (fk_pratica,nota_interna,utente,fk_pratica_stato,data_ora_inserimento,data_creazione,data_modifica)
--VALUES (my_record.numero_pratica,'L''assistito Extraregione non aveva medico precedente e quindi si aggiorna lo stato della pratica da Completata a Chiusa' ,'OPERATORE:'||'CSI PIEMONTE ',my_record.fk_pratica_stato,NOW(),NOW(),NOW());



END LOOP;
RETURN counter;
commit;
END; $BODY$
LANGUAGE 'plpgsql' VOLATILE;

CREATE OR REPLACE FUNCTION scerev.inseriscitagmancante () RETURNS integer AS
$BODY$
DECLARE
counter int := 0;
my_curs CURSOR FOR
select * from scerev.scerev_t_pratica p
where p.fk_motivo_domicilio in (5,6)
and NVL(p.ricongiungimento_familiare,'N') = 'N'
and numero_pratica not in
(select fk_pratica
   from scerev_r_pratica_pratica_tipo
  where fk_pratica_tipo in (select pk_pratica_tipo
                             from scerev_d_pratica_tipo
                            where codice_pratica_tipo ='Motivi domicilio 5'
                              or codice_pratica_tipo ='Motivi domicilio 6'))
                             order by numero_pratica desc ;
my_record scerev_t_pratica%rowtype;

BEGIN
FOR my_record IN my_curs
LOOP
counter := counter + 1;


if my_record.fk_motivo_domicilio = 5 then

insert into scerev_r_pratica_pratica_tipo (fk_pratica,fk_pratica_tipo,data_creazione,data_modifica)
values (my_record.numero_pratica,(select pk_pratica_tipo
                                   from scerev_d_pratica_tipo
                                  where codice_pratica_tipo ='Motivi domicilio 5'), now(), now());

elseif my_record.fk_motivo_domicilio = 6 then

insert into scerev_r_pratica_pratica_tipo (fk_pratica,fk_pratica_tipo,data_creazione,data_modifica)
values (my_record.numero_pratica,(select pk_pratica_tipo
                                   from scerev_d_pratica_tipo
                                  where codice_pratica_tipo ='Motivi domicilio 6'), now(), now());

end if;


update scerev.scerev_t_pratica
set data_modifica = now()
where numero_pratica = my_record.numero_pratica;
--INSERT INTO scerev.scerev_t_cronologia (fk_pratica,nota_interna,utente,fk_pratica_stato,data_ora_inserimento,data_creazione,data_modifica)
--VALUES (my_record.numero_pratica,'L''assistito Extraregione non aveva medico precedente e quindi si aggiorna lo stato della pratica da Completata a Chiusa' ,'OPERATORE:'||'CSI PIEMONTE ',my_record.fk_pratica_stato,NOW(),NOW(),NOW());



END LOOP;
RETURN counter;
commit;
END; $BODY$
LANGUAGE 'plpgsql' VOLATILE;

CREATE OR REPLACE FUNCTION scerev.inseriscinotaperdatifamiliarimancanti () RETURNS integer AS
$BODY$
DECLARE
counter int := 0;
my_curs CURSOR FOR
select * from scerev_t_pratica p
join scerev_t_info_supplementari s on s.pk_info_supplementari = p.fk_info_supplementari
where p.fk_motivo_domicilio in(4,5,6)
and s.cognome_parente is null order by p.data_creazione desc;

my_record scerev_t_pratica%rowtype;

BEGIN
FOR my_record IN my_curs
LOOP
counter := counter + 1;


INSERT INTO scerev.scerev_t_cronologia (fk_pratica,nota_interna,utente,fk_pratica_stato,data_ora_inserimento,data_creazione,data_modifica)
VALUES (my_record.numero_pratica,'A causa di un''anomalia temporanea, non sono stati richiesti all''assistito i dati relativi al familiare.' ,'OPERATORE:'||'CSI PIEMONTE ',
my_record.fk_pratica_stato,NOW(),NOW(),NOW());



END LOOP;
RETURN counter;
commit;
END; $BODY$
LANGUAGE 'plpgsql' VOLATILE;

CREATE OR REPLACE FUNCTION scerev.aggiornastatopratiche (elencopratichedaaggiornare text, annullamentoochiusura text, nomespaziocognome text, incident text) RETURNS integer AS
$BODY$
DECLARE
counter int := 0;
elenco_PraticheDaAggiornare text :='';
stato_pratica int :=0;
annullamento_O_chiusura text := '';
nome_Spazio_Cognome text := 'CSI PIEMONTE';
msgPerCronologia text := '';
msgAzione text :='';
inc text ='La richiesta non deriva da un INC';



my_curs CURSOR for
select * from  scerev.scerev_t_pratica p
 WHERE p.numero_pratica IN (elenco_PraticheDaAggiornare);

my_record scerev_t_pratica%rowtype;

begin
if annullamentoOchiusura is not null and annullamentoOchiusura <> '' then
  annullamento_O_chiusura = annullamentoOchiusura;
end if;
if incident is not null and incident <> '' then
  inc = incident;
end if;
if elencoPraticheDaAggiornare is null or elencoPraticheDaAggiornare='' then
   RAISE NOTICE 'Non sono state indicate le pratiche da aggiornare';
else
  if nomeSpazioCognome is null or nomeSpazioCognome='' then
    RAISE NOTICE 'L''aggiornamento sarà targato CSI PIEMONTE';
  else
   nome_Spazio_Cognome:=nomeSpazioCognome;
  end if;
  if annullamento_O_chiusura is null or annullamento_O_chiusura ='' then
     RAISE NOTICE 'Indicare l''operazione da fare';
   elsif annullamento_O_chiusura = 'ANNULLAMENTO' THEN
          msgAzione := 'L''annullamento della pratica è stato fatto in assistenza: non deve essere notificato al cittadino. La pratica è stata gestita direttamente su Aura, allo sportello o via mail ' ;
          stato_pratica := 3 ;
   elsif  annullamentoOchiusura = 'CHIUSURA'  THEN
          msgAzione := 'La chiusura è avvenuta in assistenza:la pratica è già stata gestita per mail o allo sportello ';
          stato_pratica := 8 ;
   end if;
      if incident <> 'La richiesta non deriva da un INC'  then
         msgAzione := msgAzione !! '(' !! incident || ').' ;
      else
         msgAzione := msgAzione !! '.' ;
      end if;

      FOR my_record IN my_curs
      LOOP
        counter := counter + 1;

        if (stato_pratica <> 0) then

          update scerev.scerev_t_pratica
          set fk_pratica_stato = stato_pratica,
          DATA_CHIUSURA = now() --verificare se sia o meno il caso di aggiornarla
          -- data_modifica = oracle.sysdate() -- se si tratta di una forzatura non la modifico così resta memorizzata la data dell'ultimo aggiornamento utente o assistito
          where numero_pratica in (my_record.numero_pratica);

		  INSERT INTO scerev.scerev_t_cronologia (fk_pratica,nota_interna,utente,fk_pratica_stato,
		                                          data_ora_inserimento,data_creazione,data_modifica)
		  VALUES (my_record.numero_pratica, msgPerCronologia,' OPERATORE:'|| nome_Spazio_Cognome,
                  my_record.fk_pratica_stato,NOW(),NOW(),NOW());

          stato_pratica := 0;
          msgPerCronologia := '';
          msgAzione :='';

        end if;

     END LOOP;

 end if;
RETURN counter;
--- commit;
END; $BODY$
LANGUAGE 'plpgsql' VOLATILE;
