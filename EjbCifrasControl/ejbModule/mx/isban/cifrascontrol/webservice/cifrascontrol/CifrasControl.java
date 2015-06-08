//
// Generated By:JAX-WS RI IBM 2.2.1-11/28/2011 08:28 AM(foreman)- (JAXB RI IBM 2.2.3-11/28/2011 06:21 AM(foreman)-)
//


package mx.isban.cifrascontrol.webservice.cifrascontrol;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.FaultAction;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

@WebService(name = "CifrasControl", targetNamespace = "http://sei.cifrascontrol.modulointegrador.isban.mx/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface CifrasControl {


    /**
     * 
     * @param solicitudCifrasControl
     * @return
     *     returns java.util.List<mx.isban.cifrascontrol.webservice.cifrascontrol.CifrasControlDTO>
     * @throws CifrasControlException_Exception
     */
    @WebMethod
    @WebResult(name = "cifrasControlList", targetNamespace = "")
    @RequestWrapper(localName = "consultarCifrasControl", targetNamespace = "http://sei.cifrascontrol.modulointegrador.isban.mx/", className = "mx.isban.cifrascontrol.webservice.cifrascontrol.ConsultarCifrasControl")
    @ResponseWrapper(localName = "consultarCifrasControlResponse", targetNamespace = "http://sei.cifrascontrol.modulointegrador.isban.mx/", className = "mx.isban.cifrascontrol.webservice.cifrascontrol.ConsultarCifrasControlResponse")
    @Action(input = "http://sei.cifrascontrol.modulointegrador.isban.mx/CifrasControl/consultarCifrasControlRequest", output = "http://sei.cifrascontrol.modulointegrador.isban.mx/CifrasControl/consultarCifrasControlResponse", fault = {
        @FaultAction(className = CifrasControlException_Exception.class, value = "http://sei.cifrascontrol.modulointegrador.isban.mx/CifrasControl/consultarCifrasControl/Fault/CifrasControlException")
    })
    public List<CifrasControlDTO> consultarCifrasControl(
        @WebParam(name = "SolicitudCifrasControl", targetNamespace = "")
        SolicitudCifrasControlDTO solicitudCifrasControl)
        throws CifrasControlException_Exception
    ;

    /**
     * 
     * @param solicitudDetalleCifrasControl
     * @return
     *     returns java.util.List<mx.isban.cifrascontrol.webservice.cifrascontrol.DetalleCifrasControlDTO>
     * @throws CifrasControlException_Exception
     */
    @WebMethod
    @WebResult(name = "detalleCifrasControlList", targetNamespace = "")
    @RequestWrapper(localName = "consultarDetalleCifrasControl", targetNamespace = "http://sei.cifrascontrol.modulointegrador.isban.mx/", className = "mx.isban.cifrascontrol.webservice.cifrascontrol.ConsultarDetalleCifrasControl")
    @ResponseWrapper(localName = "consultarDetalleCifrasControlResponse", targetNamespace = "http://sei.cifrascontrol.modulointegrador.isban.mx/", className = "mx.isban.cifrascontrol.webservice.cifrascontrol.ConsultarDetalleCifrasControlResponse")
    @Action(input = "http://sei.cifrascontrol.modulointegrador.isban.mx/CifrasControl/consultarDetalleCifrasControlRequest", output = "http://sei.cifrascontrol.modulointegrador.isban.mx/CifrasControl/consultarDetalleCifrasControlResponse", fault = {
        @FaultAction(className = CifrasControlException_Exception.class, value = "http://sei.cifrascontrol.modulointegrador.isban.mx/CifrasControl/consultarDetalleCifrasControl/Fault/CifrasControlException")
    })
    public List<DetalleCifrasControlDTO> consultarDetalleCifrasControl(
        @WebParam(name = "SolicitudDetalleCifrasControl", targetNamespace = "")
        SolicitudCifrasControlDTO solicitudDetalleCifrasControl)
        throws CifrasControlException_Exception
    ;

    /**
     * 
     * @param solicitudFacturaCifrasControl
     * @return
     *     returns java.util.List<mx.isban.cifrascontrol.webservice.cifrascontrol.FacturaDTO>
     * @throws CifrasControlException_Exception
     */
    @WebMethod
    @WebResult(name = "facturasCifrasControlList", targetNamespace = "")
    @RequestWrapper(localName = "consultarFacturasCifrasControl", targetNamespace = "http://sei.cifrascontrol.modulointegrador.isban.mx/", className = "mx.isban.cifrascontrol.webservice.cifrascontrol.ConsultarFacturasCifrasControl")
    @ResponseWrapper(localName = "consultarFacturasCifrasControlResponse", targetNamespace = "http://sei.cifrascontrol.modulointegrador.isban.mx/", className = "mx.isban.cifrascontrol.webservice.cifrascontrol.ConsultarFacturasCifrasControlResponse")
    @Action(input = "http://sei.cifrascontrol.modulointegrador.isban.mx/CifrasControl/consultarFacturasCifrasControlRequest", output = "http://sei.cifrascontrol.modulointegrador.isban.mx/CifrasControl/consultarFacturasCifrasControlResponse", fault = {
        @FaultAction(className = CifrasControlException_Exception.class, value = "http://sei.cifrascontrol.modulointegrador.isban.mx/CifrasControl/consultarFacturasCifrasControl/Fault/CifrasControlException")
    })
    public List<FacturaDTO> consultarFacturasCifrasControl(
        @WebParam(name = "SolicitudFacturaCifrasControl", targetNamespace = "")
        SolicitudFacturaDTO solicitudFacturaCifrasControl)
        throws CifrasControlException_Exception
    ;

}