
package com.bola.nwcl.common.util.lingling.impl;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "DataFormatError", targetNamespace = "http://entrance.ws.smart.com/")
public class DataFormatError_Exception
    extends Exception
{

    /**
	 * 
	 */
	private static final long serialVersionUID = -6016126328366410927L;
	/**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private DataFormatError faultInfo;

    /**
     * 
     * @param message
     * @param faultInfo
     */
    public DataFormatError_Exception(String message, DataFormatError faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param message
     * @param faultInfo
     * @param cause
     */
    public DataFormatError_Exception(String message, DataFormatError faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: com.lingling.ws.impl.DataFormatError
     */
    public DataFormatError getFaultInfo() {
        return faultInfo;
    }

}
