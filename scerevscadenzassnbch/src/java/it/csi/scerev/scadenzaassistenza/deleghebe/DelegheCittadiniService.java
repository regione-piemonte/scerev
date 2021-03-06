/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.scerev.scadenzaassistenza.deleghebe;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "DelegheCittadiniService", targetNamespace = "http://deleghebe.csi.it/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface DelegheCittadiniService {


    /**
     * 
     * @param aggiornaDichiarazione
     * @return
     *     returns it.csi.scerev.scadenzaassistenza.deleghebe.AggiornaDichiarazioneResponse
     */
    @WebMethod
    @WebResult(name = "aggiornaDichiarazioneResponse", targetNamespace = "http://deleghebe.csi.it/")
    @RequestWrapper(localName = "aggiornaDichiarazioneService", targetNamespace = "http://deleghebe.csi.it/", className = "it.csi.scerev.scadenzaassistenza.deleghebe.AggiornaDichiarazioneService")
    @ResponseWrapper(localName = "aggiornaDichiarazioneServiceResponse", targetNamespace = "http://deleghebe.csi.it/", className = "it.csi.scerev.scadenzaassistenza.deleghebe.AggiornaDichiarazioneServiceResponse")
    public AggiornaDichiarazioneResponse aggiornaDichiarazioneService(
        @WebParam(name = "aggiornaDichiarazione", targetNamespace = "http://deleghebe.csi.it/")
        AggiornaDichiarazione aggiornaDichiarazione);

    /**
     * 
     * @param ricercaFile
     * @return
     *     returns it.csi.scerev.scadenzaassistenza.deleghebe.RicercaFileResponse
     */
    @WebMethod
    @WebResult(name = "ricercaFileDocumentoPerId", targetNamespace = "http://deleghebe.csi.it/")
    @RequestWrapper(localName = "ricercaFileDocumentoPerId", targetNamespace = "http://deleghebe.csi.it/", className = "it.csi.scerev.scadenzaassistenza.deleghebe.RicercaFileDocumentoPerId")
    @ResponseWrapper(localName = "ricercaFileDocumentoPerIdResponse", targetNamespace = "http://deleghebe.csi.it/", className = "it.csi.scerev.scadenzaassistenza.deleghebe.RicercaFileDocumentoPerIdResponse")
    public RicercaFileResponse ricercaFileDocumentoPerId(
        @WebParam(name = "ricercaFile", targetNamespace = "http://deleghebe.csi.it/")
        RicercaFile ricercaFile);

    /**
     * 
     * @param inserisciDelega
     * @return
     *     returns it.csi.scerev.scadenzaassistenza.deleghebe.InserisciDelegaResponse
     */
    @WebMethod
    @WebResult(name = "inserisciDelegaResponse", targetNamespace = "http://deleghebe.csi.it/")
    @RequestWrapper(localName = "inserisciDelegaService", targetNamespace = "http://deleghebe.csi.it/", className = "it.csi.scerev.scadenzaassistenza.deleghebe.InserisciDelegaService")
    @ResponseWrapper(localName = "inserisciDelegaServiceResponse", targetNamespace = "http://deleghebe.csi.it/", className = "it.csi.scerev.scadenzaassistenza.deleghebe.InserisciDelegaServiceResponse")
    public InserisciDelegaResponse inserisciDelegaService(
        @WebParam(name = "inserisciDelega", targetNamespace = "http://deleghebe.csi.it/")
        InserisciDelega inserisciDelega);

    /**
     * 
     * @param aggiornaCittadino
     * @return
     *     returns it.csi.scerev.scadenzaassistenza.deleghebe.AggiornaCittadinoResponse
     */
    @WebMethod
    @WebResult(name = "aggiornaCittadinoResponse", targetNamespace = "http://deleghebe.csi.it/")
    @RequestWrapper(localName = "aggiornaCittadinoService", targetNamespace = "http://deleghebe.csi.it/", className = "it.csi.scerev.scadenzaassistenza.deleghebe.AggiornaCittadinoService")
    @ResponseWrapper(localName = "aggiornaCittadinoServiceResponse", targetNamespace = "http://deleghebe.csi.it/", className = "it.csi.scerev.scadenzaassistenza.deleghebe.AggiornaCittadinoServiceResponse")
    public AggiornaCittadinoResponse aggiornaCittadinoService(
        @WebParam(name = "aggiornaCittadino", targetNamespace = "http://deleghebe.csi.it/")
        AggiornaCittadino aggiornaCittadino);

    /**
     * 
     * @param aggiornaDelega
     * @return
     *     returns it.csi.scerev.scadenzaassistenza.deleghebe.AggiornaDelegaResponse
     */
    @WebMethod
    @WebResult(name = "aggiornaDelegaResponse", targetNamespace = "http://deleghebe.csi.it/")
    @RequestWrapper(localName = "aggiornaDelegaService", targetNamespace = "http://deleghebe.csi.it/", className = "it.csi.scerev.scadenzaassistenza.deleghebe.AggiornaDelegaService")
    @ResponseWrapper(localName = "aggiornaDelegaServiceResponse", targetNamespace = "http://deleghebe.csi.it/", className = "it.csi.scerev.scadenzaassistenza.deleghebe.AggiornaDelegaServiceResponse")
    public AggiornaDelegaResponse aggiornaDelegaService(
        @WebParam(name = "aggiornaDelega", targetNamespace = "http://deleghebe.csi.it/")
        AggiornaDelega aggiornaDelega);

    /**
     * 
     * @param getDelegati
     * @return
     *     returns it.csi.scerev.scadenzaassistenza.deleghebe.GetDelegatiResponse
     */
    @WebMethod
    @WebResult(name = "getDelegatiResponse", targetNamespace = "http://deleghebe.csi.it/")
    @RequestWrapper(localName = "getDelegatiService", targetNamespace = "http://deleghebe.csi.it/", className = "it.csi.scerev.scadenzaassistenza.deleghebe.GetDelegatiService")
    @ResponseWrapper(localName = "getDelegatiServiceResponse", targetNamespace = "http://deleghebe.csi.it/", className = "it.csi.scerev.scadenzaassistenza.deleghebe.GetDelegatiServiceResponse")
    public GetDelegatiResponse getDelegatiService(
        @WebParam(name = "getDelegati", targetNamespace = "http://deleghebe.csi.it/")
        GetDelegati getDelegati);

    /**
     * 
     * @param ricercaDeleghe
     * @return
     *     returns it.csi.scerev.scadenzaassistenza.deleghebe.RicercaDelegheResponse
     */
    @WebMethod
    @WebResult(name = "ricercaDelegheResponse", targetNamespace = "http://deleghebe.csi.it/")
    @RequestWrapper(localName = "ricercaDelegheService", targetNamespace = "http://deleghebe.csi.it/", className = "it.csi.scerev.scadenzaassistenza.deleghebe.RicercaDelegheService")
    @ResponseWrapper(localName = "ricercaDelegheServiceResponse", targetNamespace = "http://deleghebe.csi.it/", className = "it.csi.scerev.scadenzaassistenza.deleghebe.RicercaDelegheServiceResponse")
    public RicercaDelegheResponse ricercaDelegheService(
        @WebParam(name = "ricercaDeleghe", targetNamespace = "http://deleghebe.csi.it/")
        RicercaDeleghe ricercaDeleghe);

    /**
     * 
     * @param isAlive
     * @return
     *     returns it.csi.scerev.scadenzaassistenza.deleghebe.IsAliveResponse
     */
    @WebMethod
    @WebResult(name = "isAliveResponse", targetNamespace = "http://deleghebe.csi.it/")
    @RequestWrapper(localName = "isAliveService", targetNamespace = "http://deleghebe.csi.it/", className = "it.csi.scerev.scadenzaassistenza.deleghebe.IsAliveService")
    @ResponseWrapper(localName = "isAliveServiceResponse", targetNamespace = "http://deleghebe.csi.it/", className = "it.csi.scerev.scadenzaassistenza.deleghebe.IsAliveServiceResponse")
    public IsAliveResponse isAliveService(
        @WebParam(name = "isAlive", targetNamespace = "http://deleghebe.csi.it/")
        IsAlive isAlive);

    /**
     * 
     * @param ricercaCittadino
     * @return
     *     returns it.csi.scerev.scadenzaassistenza.deleghebe.RicercaCittadiniResponse
     */
    @WebMethod
    @WebResult(name = "ricercaCittadiniResponse", targetNamespace = "http://deleghebe.csi.it/")
    @RequestWrapper(localName = "ricercaCittadiniService", targetNamespace = "http://deleghebe.csi.it/", className = "it.csi.scerev.scadenzaassistenza.deleghebe.RicercaCittadiniService")
    @ResponseWrapper(localName = "ricercaCittadiniServiceResponse", targetNamespace = "http://deleghebe.csi.it/", className = "it.csi.scerev.scadenzaassistenza.deleghebe.RicercaCittadiniServiceResponse")
    public RicercaCittadiniResponse ricercaCittadiniService(
        @WebParam(name = "ricercaCittadino", targetNamespace = "http://deleghebe.csi.it/")
        RicercaCittadini ricercaCittadino);

    /**
     * 
     * @param elencoGenitore
     * @return
     *     returns it.csi.scerev.scadenzaassistenza.deleghebe.ElencoGenitoriResponse
     */
    @WebMethod
    @WebResult(name = "elencoGenitoreResponse", targetNamespace = "http://deleghebe.csi.it/")
    @RequestWrapper(localName = "elencoGenitoriService", targetNamespace = "http://deleghebe.csi.it/", className = "it.csi.scerev.scadenzaassistenza.deleghebe.ElencoGenitoriService")
    @ResponseWrapper(localName = "elencoGenitoriServiceResponse", targetNamespace = "http://deleghebe.csi.it/", className = "it.csi.scerev.scadenzaassistenza.deleghebe.ElencoGenitoriServiceResponse")
    public ElencoGenitoriResponse elencoGenitoriService(
        @WebParam(name = "elencoGenitore", targetNamespace = "http://deleghebe.csi.it/")
        ElencoGenitori elencoGenitore);

    /**
     * 
     * @param rinunciaDelegato
     * @return
     *     returns it.csi.scerev.scadenzaassistenza.deleghebe.RinunciaDelegatoResponse
     */
    @WebMethod
    @WebResult(name = "rinunciaDelegatoResponse", targetNamespace = "http://deleghebe.csi.it/")
    @RequestWrapper(localName = "rinunciaDelegatoService", targetNamespace = "http://deleghebe.csi.it/", className = "it.csi.scerev.scadenzaassistenza.deleghebe.RinunciaDelegatoService")
    @ResponseWrapper(localName = "rinunciaDelegatoServiceResponse", targetNamespace = "http://deleghebe.csi.it/", className = "it.csi.scerev.scadenzaassistenza.deleghebe.RinunciaDelegatoServiceResponse")
    public RinunciaDelegatoResponse rinunciaDelegatoService(
        @WebParam(name = "rinunciaDelegato", targetNamespace = "http://deleghebe.csi.it/")
        RinunciaDelegato rinunciaDelegato);

    /**
     * 
     * @param ricercaCfOperatoreServizio
     * @return
     *     returns it.csi.scerev.scadenzaassistenza.deleghebe.RicercaCfOperatoreResponse
     */
    @WebMethod
    @WebResult(name = "ricercaCfOperatoreResponse", targetNamespace = "http://deleghebe.csi.it/")
    @RequestWrapper(localName = "ricercaCfOperatoreService", targetNamespace = "http://deleghebe.csi.it/", className = "it.csi.scerev.scadenzaassistenza.deleghebe.RicercaCfOperatoreService")
    @ResponseWrapper(localName = "ricercaCfOperatoreServiceResponse", targetNamespace = "http://deleghebe.csi.it/", className = "it.csi.scerev.scadenzaassistenza.deleghebe.RicercaCfOperatoreServiceResponse")
    public RicercaCfOperatoreResponse ricercaCfOperatoreService(
        @WebParam(name = "ricercaCfOperatoreServizio", targetNamespace = "http://deleghebe.csi.it/")
        RicercaCfOperatore ricercaCfOperatoreServizio);

    /**
     * 
     * @param inserisciDichiarazione
     * @return
     *     returns it.csi.scerev.scadenzaassistenza.deleghebe.InserisciDichiarazioneResponse
     */
    @WebMethod
    @WebResult(name = "inserisciDichiarazioneResponse", targetNamespace = "http://deleghebe.csi.it/")
    @RequestWrapper(localName = "inserisciDichiarazioneService", targetNamespace = "http://deleghebe.csi.it/", className = "it.csi.scerev.scadenzaassistenza.deleghebe.InserisciDichiarazioneService")
    @ResponseWrapper(localName = "inserisciDichiarazioneServiceResponse", targetNamespace = "http://deleghebe.csi.it/", className = "it.csi.scerev.scadenzaassistenza.deleghebe.InserisciDichiarazioneServiceResponse")
    public InserisciDichiarazioneResponse inserisciDichiarazioneService(
        @WebParam(name = "inserisciDichiarazione", targetNamespace = "http://deleghebe.csi.it/")
        InserisciDichiarazione inserisciDichiarazione);

    /**
     * 
     * @param saveDelegati
     * @return
     *     returns it.csi.scerev.scadenzaassistenza.deleghebe.SaveDelegatiResponse
     */
    @WebMethod
    @WebResult(name = "saveDelegatiResponse", targetNamespace = "http://deleghebe.csi.it/")
    @RequestWrapper(localName = "saveDelegatiService", targetNamespace = "http://deleghebe.csi.it/", className = "it.csi.scerev.scadenzaassistenza.deleghebe.SaveDelegatiService")
    @ResponseWrapper(localName = "saveDelegatiServiceResponse", targetNamespace = "http://deleghebe.csi.it/", className = "it.csi.scerev.scadenzaassistenza.deleghebe.SaveDelegatiServiceResponse")
    public SaveDelegatiResponse saveDelegatiService(
        @WebParam(name = "saveDelegati", targetNamespace = "http://deleghebe.csi.it/")
        SaveDelegati saveDelegati);

    /**
     * 
     * @param getDeleganti
     * @return
     *     returns it.csi.scerev.scadenzaassistenza.deleghebe.GetDelegantiResponse
     */
    @WebMethod
    @WebResult(name = "getDelegantiResponse", targetNamespace = "http://deleghebe.csi.it/")
    @RequestWrapper(localName = "getDelegantiService", targetNamespace = "http://deleghebe.csi.it/", className = "it.csi.scerev.scadenzaassistenza.deleghebe.GetDelegantiService")
    @ResponseWrapper(localName = "getDelegantiServiceResponse", targetNamespace = "http://deleghebe.csi.it/", className = "it.csi.scerev.scadenzaassistenza.deleghebe.GetDelegantiServiceResponse")
    public GetDelegantiResponse getDelegantiService(
        @WebParam(name = "getDeleganti", targetNamespace = "http://deleghebe.csi.it/")
        GetDeleganti getDeleganti);

    /**
     * 
     * @param ricercaDichiarazioni
     * @return
     *     returns it.csi.scerev.scadenzaassistenza.deleghebe.RicercaDichiarazioniResponse
     */
    @WebMethod
    @WebResult(name = "ricercaDichiarazioniResponse", targetNamespace = "http://deleghebe.csi.it/")
    @RequestWrapper(localName = "ricercaDichiarazioniService", targetNamespace = "http://deleghebe.csi.it/", className = "it.csi.scerev.scadenzaassistenza.deleghebe.RicercaDichiarazioniService")
    @ResponseWrapper(localName = "ricercaDichiarazioniServiceResponse", targetNamespace = "http://deleghebe.csi.it/", className = "it.csi.scerev.scadenzaassistenza.deleghebe.RicercaDichiarazioniServiceResponse")
    public RicercaDichiarazioniResponse ricercaDichiarazioniService(
        @WebParam(name = "ricercaDichiarazioni", targetNamespace = "http://deleghebe.csi.it/")
        RicercaDichiarazioni ricercaDichiarazioni);

    /**
     * 
     * @param inserisciCittadino
     * @return
     *     returns it.csi.scerev.scadenzaassistenza.deleghebe.InserisciCittadinoResponse
     */
    @WebMethod
    @WebResult(name = "inserisciCittadinoResponse", targetNamespace = "http://deleghebe.csi.it/")
    @RequestWrapper(localName = "inserisciCittadinoService", targetNamespace = "http://deleghebe.csi.it/", className = "it.csi.scerev.scadenzaassistenza.deleghebe.InserisciCittadinoService")
    @ResponseWrapper(localName = "inserisciCittadinoServiceResponse", targetNamespace = "http://deleghebe.csi.it/", className = "it.csi.scerev.scadenzaassistenza.deleghebe.InserisciCittadinoServiceResponse")
    public InserisciCittadinoResponse inserisciCittadinoService(
        @WebParam(name = "inserisciCittadino", targetNamespace = "http://deleghebe.csi.it/")
        InserisciCittadino inserisciCittadino);

}
