
package com.bola.nwcl.common.util.lingling.impl;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebService(name = "IOpenResidentialResources", targetNamespace = "http://entrance.ws.smart.com/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface IOpenResidentialResources {


    /**
     * 
     * @param message
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "activateRegcode", targetNamespace = "http://entrance.ws.smart.com/", className = "com.bola.nwcl.common.util.lingling.impl.ActivateRegcode")
    @ResponseWrapper(localName = "activateRegcodeResponse", targetNamespace = "http://entrance.ws.smart.com/", className = "com.bola.nwcl.common.util.lingling.impl.ActivateRegcodeResponse")
    public String activateRegcode(
        @WebParam(name = "MESSAGE", targetNamespace = "http://entrance.ws.smart.com/")
        String message);

    /**
     * 
     * @param message
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "updateOwnerDevice", targetNamespace = "http://entrance.ws.smart.com/", className = "com.bola.nwcl.common.util.lingling.impl.UpdateOwnerDevice")
    @ResponseWrapper(localName = "updateOwnerDeviceResponse", targetNamespace = "http://entrance.ws.smart.com/", className = "com.bola.nwcl.common.util.lingling.impl.UpdateOwnerDeviceResponse")
    public String updateOwnerDevice(
        @WebParam(name = "MESSAGE", targetNamespace = "http://entrance.ws.smart.com/")
        String message);

    /**
     * 
     * @param message
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "continueOwner", targetNamespace = "http://entrance.ws.smart.com/", className = "com.bola.nwcl.common.util.lingling.impl.ContinueOwner")
    @ResponseWrapper(localName = "continueOwnerResponse", targetNamespace = "http://entrance.ws.smart.com/", className = "com.bola.nwcl.common.util.lingling.impl.ContinueOwnerResponse")
    public String continueOwner(
        @WebParam(name = "MESSAGE", targetNamespace = "http://entrance.ws.smart.com/")
        String message);

    /**
     * 
     * @param message
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "addResidential", targetNamespace = "http://entrance.ws.smart.com/", className = "com.bola.nwcl.common.util.lingling.impl.AddResidential")
    @ResponseWrapper(localName = "addResidentialResponse", targetNamespace = "http://entrance.ws.smart.com/", className = "com.bola.nwcl.common.util.lingling.impl.AddResidentialResponse")
    public String addResidential(
        @WebParam(name = "MESSAGE", targetNamespace = "http://entrance.ws.smart.com/")
        String message);

    /**
     * 
     * @param message
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "queryServerAddr", targetNamespace = "http://entrance.ws.smart.com/", className = "com.bola.nwcl.common.util.lingling.impl.QueryServerAddr")
    @ResponseWrapper(localName = "queryServerAddrResponse", targetNamespace = "http://entrance.ws.smart.com/", className = "com.bola.nwcl.common.util.lingling.impl.QueryServerAddrResponse")
    public String queryServerAddr(
        @WebParam(name = "MESSAGE", targetNamespace = "http://entrance.ws.smart.com/")
        String message);

    /**
     * 
     * @param message
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "ownerLogin", targetNamespace = "http://entrance.ws.smart.com/", className = "com.bola.nwcl.common.util.lingling.impl.OwnerLogin")
    @ResponseWrapper(localName = "ownerLoginResponse", targetNamespace = "http://entrance.ws.smart.com/", className = "com.bola.nwcl.common.util.lingling.impl.OwnerLoginResponse")
    public String ownerLogin(
        @WebParam(name = "MESSAGE", targetNamespace = "http://entrance.ws.smart.com/")
        String message);

    /**
     * 
     * @param message
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "managerLogin", targetNamespace = "http://entrance.ws.smart.com/", className = "com.bola.nwcl.common.util.lingling.impl.ManagerLogin")
    @ResponseWrapper(localName = "managerLoginResponse", targetNamespace = "http://entrance.ws.smart.com/", className = "com.bola.nwcl.common.util.lingling.impl.ManagerLoginResponse")
    public String managerLogin(
        @WebParam(name = "MESSAGE", targetNamespace = "http://entrance.ws.smart.com/")
        String message);

    /**
     * 
     * @param message
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "addOwner", targetNamespace = "http://entrance.ws.smart.com/", className = "com.bola.nwcl.common.util.lingling.impl.AddOwner")
    @ResponseWrapper(localName = "addOwnerResponse", targetNamespace = "http://entrance.ws.smart.com/", className = "com.bola.nwcl.common.util.lingling.impl.AddOwnerResponse")
    public String addOwner(
        @WebParam(name = "MESSAGE", targetNamespace = "http://entrance.ws.smart.com/")
        String message);

    /**
     * 
     * @param message
     * @return
     *     returns java.lang.String
     * @throws DataFormatError_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "addDevice", targetNamespace = "http://entrance.ws.smart.com/", className = "com.bola.nwcl.common.util.lingling.impl.AddDevice")
    @ResponseWrapper(localName = "addDeviceResponse", targetNamespace = "http://entrance.ws.smart.com/", className = "com.bola.nwcl.common.util.lingling.impl.AddDeviceResponse")
    public String addDevice(
        @WebParam(name = "MESSAGE", targetNamespace = "http://entrance.ws.smart.com/")
        String message)
        throws DataFormatError_Exception
    ;

    /**
     * 
     * @param message
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "addManager", targetNamespace = "http://entrance.ws.smart.com/", className = "com.bola.nwcl.common.util.lingling.impl.AddManager")
    @ResponseWrapper(localName = "addManagerResponse", targetNamespace = "http://entrance.ws.smart.com/", className = "com.bola.nwcl.common.util.lingling.impl.AddManagerResponse")
    public String addManager(
        @WebParam(name = "MESSAGE", targetNamespace = "http://entrance.ws.smart.com/")
        String message);

}