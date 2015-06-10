//
// Generated By:JAX-WS RI IBM 2.2.1-11/28/2011 08:28 AM(foreman)- (JAXB RI IBM 2.2.3-11/28/2011 06:21 AM(foreman)-)
//


package mx.isban.cifrascontrol.webservice.reproceso;

import javax.xml.ws.WebFault;

@WebFault(name = "ReprocesoException", targetNamespace = "http://sei.reproceso.modulointegrador.isban.mx/")
public class ReprocesoException_Exception
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private ReprocesoException faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public ReprocesoException_Exception(String message, ReprocesoException faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param faultInfo
     * @param message
     * @param cause
     */
    public ReprocesoException_Exception(String message, ReprocesoException faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: mx.isban.cifrascontrol.webservice.solicitudreproceso.ReprocesoException
     */
    public ReprocesoException getFaultInfo() {
        return faultInfo;
    }

}
