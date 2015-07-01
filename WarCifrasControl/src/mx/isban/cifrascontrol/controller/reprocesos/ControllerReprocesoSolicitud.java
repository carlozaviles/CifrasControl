package mx.isban.cifrascontrol.controller.reprocesos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import mx.isban.agave.commons.architech.Architech;
import mx.isban.agave.commons.exception.BusinessException;
import mx.isban.cifrascontrol.bean.reprocesos.BeanDatosSolicitudReprocesos;
import mx.isban.cifrascontrol.beans.producto.BeanProducto;
import mx.isban.cifrascontrol.servicio.catalogos.BOCatalogos;
import mx.isban.cifrascontrol.servicio.reprocesos.BOReprocesos;
import mx.isban.cifrascontrol.utileria.general.GeneradorCatalogos;

@Controller
public class ControllerReprocesoSolicitud extends Architech{
	
	/**
	 * Serial.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Nombre de la pagina de solicitud de reprocesos.
	 */
	private static final String PAGINA_SOLICITUD_REPROCESOS = "solicitarReproceso";
	/**
	 * Cadena con la que se guardan los datos del cliente en sesion.
	 */
	private static final String DATOS_CLIENTE = "datos_cliente";
	/**
	 * Objeto de la capa de negocio del modulo de reprocesos.
	 */
	private BOReprocesos reprocesos;

	private BOCatalogos boCatalogo;
	
	/**
	 * Muestra el menu para que el usuario realice la consulta de personas.
	 * @param request Request
	 * @param response Response
	 * @return ModelAndView
	 */
	@RequestMapping("consultaPersonas.do")
	public ModelAndView muestraMenuConsultaPersonas(HttpServletRequest request, HttpServletResponse response){
		this.debug("Se muestra el menu para consulta de Personas.");
		this.info("Se redirige al usuario hacial la pagina: " + PAGINA_SOLICITUD_REPROCESOS);
		return new ModelAndView(PAGINA_SOLICITUD_REPROCESOS);
	}
	
	/**
	 * Realiza la consulta de Personas y muestra los datos fiscales encontrados en dicha consulta.
	 * @param req Request
	 * @param res Response
	 * @return ModelAndView
	 * @throws BusinessException Exception.
	 */
	@RequestMapping("datosFiscales.do")
	public ModelAndView solicitaConsultaPersonas(HttpServletRequest req, HttpServletResponse res) throws BusinessException {
		this.info("Se solicitara la ejecucion de la consulta de personas.");
		final String numeroCuenta = req.getParameter("numero-cuenta");
		this.info("El numero de cuenta que sera enviado como parametro es: " + numeroCuenta);
		final BeanDatosSolicitudReprocesos datosFiscales = reprocesos.realizarConsultaPersonas(numeroCuenta, 
				this.getArchitechBean());
		final Map<String, Object> modelo = new HashMap<String, Object>();
		if(datosFiscales != null){
			req.getSession().setAttribute(DATOS_CLIENTE, datosFiscales);						
			modelo.put("solicitudReprocesoForm", datosFiscales);
			modelo.put("numeroCuenta", numeroCuenta);
			modelo.put("catalogoMeses", GeneradorCatalogos.obtenerListaMeses());
			modelo.put("catalogoAnios", GeneradorCatalogos.obtenerListaAnios(5, 0));
			modelo.put("catalogoTiposMovimiento", GeneradorCatalogos.obtenerCatalogoTipoMov());
			final List<BeanProducto> productos = boCatalogo.obtenerProductosUsuario(getArchitechBean(), getArchitechBean().getUsuario(), "EDC");
			modelo.put("catalogoProductos", GeneradorCatalogos.obtenerCatalogoProductosReprocesos(productos));
			modelo.put("muestraDatosFiscales", true);
		}else{
			modelo.put("noHayDatos", true);
		}
		this.info("Se redirecciona al usuario hacial la pagina: " + PAGINA_SOLICITUD_REPROCESOS);
		this.info("El modelo enviado a la capa cliente es: " + modelo.toString());
		return new ModelAndView(PAGINA_SOLICITUD_REPROCESOS, modelo);
	}
	
	/**
	 * Realiza la solicitud para el registro de reproceso a la capa de negocio.
	 * @param req Request.
	 * @param res Response
	 * @param datosReproceso Contiene los parametros para la solicitud de reprocesos.
	 * @return ModelAndView
	 * @throws BusinessException Exception.
	 */
	@RequestMapping("solicitudReproceso.do") 
	public ModelAndView llamaSolicitudReproceso(HttpServletRequest req, HttpServletResponse res, 
			@ModelAttribute("solicitudReprocesoForm") BeanDatosSolicitudReprocesos datosReproceso) throws BusinessException {
		this.info("El usuario ha solicitado un reproceso.");
		this.info("Los valores de solitud para el reproceso son: " + datosReproceso.toString());
		final BeanDatosSolicitudReprocesos datosReprocesoSesion = (BeanDatosSolicitudReprocesos)req.getSession().getAttribute(DATOS_CLIENTE);
		req.getSession().removeAttribute(DATOS_CLIENTE);
		datosReprocesoSesion.setProducto(datosReproceso.getProducto());
		datosReprocesoSesion.setMovimiento(datosReproceso.getMovimiento());
		datosReprocesoSesion.setAnio(datosReproceso.getAnio());
		datosReprocesoSesion.setMes(datosReproceso.getMes());
		reprocesos.registrarSolicitudReproceso(datosReprocesoSesion, this.getArchitechBean());
		final Map<String, Object> modelo = new HashMap<String, Object>();
		modelo.put("solicitudReprocesoRealizada", true);
		this.info("El usuario es redirigido hacia la pagina: " + PAGINA_SOLICITUD_REPROCESOS);
		return new ModelAndView(PAGINA_SOLICITUD_REPROCESOS, modelo);
	}
	
	/**
	 * Manejador de errores de este controller.
	 * @param req Request
	 * @param res Response
	 * @param handler Handler.
	 * @param exception Exception.
	 * @return ModelAndView
	 */
	@ExceptionHandler
	public ModelAndView manejoErrores(HttpServletRequest req, HttpServletResponse res, Object handler, Exception exception){
		final String metodo = this.getClass().getName() + ".manejadorErrores";
		this.info("Inicio de ejecucion de metodo " + metodo);
		String pagina = null;
		final Map<String, String> modelo = new HashMap<String, String>();
		if(handler instanceof BusinessException){
			modelo.put("codeError", ((BusinessException)handler).getCode());
			pagina = "../errores/errorAgave.do";
			this.info("Fue cachada una excepcion BuisinessException " + handler.toString());
		}else{
			pagina = "../errores/errorGrl.do";
			this.info("Fue cachada una excepcion " + handler.toString());
		}
		this.info("El modelo enviado al cliente es " + modelo.toString());
		this.info("La pagina de destino es " + pagina);
		return new ModelAndView("redirect:" + pagina, modelo);
	}

	/**
	 * @return the reprocesos
	 */
	public BOReprocesos getReprocesos() {
		return reprocesos;
	}

	/**
	 * @param reprocesos the reprocesos to set
	 */
	public void setReprocesos(BOReprocesos reprocesos) {
		this.reprocesos = reprocesos;
	}

	/**
	 * @return the boCatalogo
	 */
	public BOCatalogos getBoCatalogo() {
		return boCatalogo;
	}

	/**
	 * @param boCatalogo the boCatalogo to set
	 */
	public void setBoCatalogo(BOCatalogos boCatalogo) {
		this.boCatalogo = boCatalogo;
	}
	
	
}
