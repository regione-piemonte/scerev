/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
/**
 * PolicyEnforcerBaseServiceServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.csi.scerev.scerevserviziesterni.iride.base;

public class PolicyEnforcerBaseServiceServiceLocator extends org.apache.axis.client.Service implements it.csi.scerev.scerevserviziesterni.iride.base.PolicyEnforcerBaseServiceService {

    public PolicyEnforcerBaseServiceServiceLocator() {
    }


    public PolicyEnforcerBaseServiceServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public PolicyEnforcerBaseServiceServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for PolicyEnforcerBase
    private java.lang.String PolicyEnforcerBase_address = "http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerBase";

    public java.lang.String getPolicyEnforcerBaseAddress() {
        return PolicyEnforcerBase_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String PolicyEnforcerBaseWSDDServiceName = "PolicyEnforcerBase";

    public java.lang.String getPolicyEnforcerBaseWSDDServiceName() {
        return PolicyEnforcerBaseWSDDServiceName;
    }

    public void setPolicyEnforcerBaseWSDDServiceName(java.lang.String name) {
        PolicyEnforcerBaseWSDDServiceName = name;
    }

    public it.csi.scerev.scerevserviziesterni.iride.base.PolicyEnforcerBaseService getPolicyEnforcerBase() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(PolicyEnforcerBase_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getPolicyEnforcerBase(endpoint);
    }

    public it.csi.scerev.scerevserviziesterni.iride.base.PolicyEnforcerBaseService getPolicyEnforcerBase(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            it.csi.scerev.scerevserviziesterni.iride.base.PolicyEnforcerBaseSoapBindingStub _stub = new it.csi.scerev.scerevserviziesterni.iride.base.PolicyEnforcerBaseSoapBindingStub(portAddress, this);
            _stub.setPortName(getPolicyEnforcerBaseWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setPolicyEnforcerBaseEndpointAddress(java.lang.String address) {
        PolicyEnforcerBase_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (it.csi.scerev.scerevserviziesterni.iride.base.PolicyEnforcerBaseService.class.isAssignableFrom(serviceEndpointInterface)) {
                it.csi.scerev.scerevserviziesterni.iride.base.PolicyEnforcerBaseSoapBindingStub _stub = new it.csi.scerev.scerevserviziesterni.iride.base.PolicyEnforcerBaseSoapBindingStub(new java.net.URL(PolicyEnforcerBase_address), this);
                _stub.setPortName(getPolicyEnforcerBaseWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("PolicyEnforcerBase".equals(inputPortName)) {
            return getPolicyEnforcerBase();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerBase", "PolicyEnforcerBaseServiceService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://tst-appweb.reteunitaria.piemonte.it/pep_wsfad_policy/services/PolicyEnforcerBase", "PolicyEnforcerBase"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("PolicyEnforcerBase".equals(portName)) {
            setPolicyEnforcerBaseEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
