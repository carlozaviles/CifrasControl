/**
 * 
 */
package mx.isban.cifrascontrol.controller.reprocesos;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import mx.isban.agave.commons.architech.Architech;
import mx.isban.agave.commons.exception.BusinessException;
import mx.isban.cifrascontrol.bean.reprocesos.BeanCancelacion;
import mx.isban.cifrascontrol.bean.reprocesos.BeanParamsConsultaPrevios;
import mx.isban.cifrascontrol.bean.reprocesos.BeanParamsConsultaReproceso;
import mx.isban.cifrascontrol.bean.reprocesos.BeanPrevioEdc;
import mx.isban.cifrascontrol.bean.reprocesos.BeanRegistroReproceso;
import mx.isban.cifrascontrol.beans.producto.BeanProducto;
import mx.isban.cifrascontrol.servicio.catalogos.BOCatalogos;
import mx.isban.cifrascontrol.servicio.reprocesos.BOReprocesos;
import mx.isban.cifrascontrol.utileria.general.GeneradorCatalogos;

/**
 * @author everis
 *
 */
@Controller
public class ControllerConsultaReprocesos extends Architech {

	/**
	 * Serial.
	 */
	private static final long serialVersionUID = -5416267042375998870L;
	/**
	 * Pagina para realizar la consulta de reprocesos.
	 */
	private static final String PAGINA_CONSULTA_REPROCESOS = "consultaReprocesos";
	/**
	 * Nombre de la forma utilizada para almacenar los parametros para la consulta de reprocesos.
	 */
	private static final String FORMA_CONSULTA_REPROCESOS = "consultaReprocesoForm";
	/**
	 * Nombre de la pagina en la que se muestran los resultados de la consulta de reprocesos.
	 */
	private static final String PAGINA_REPORTE_REPROCESOS = "listadoReprocesos";
	/**
	 * Interfaz remota del servicio de reprocesos.
	 */
	private BOReprocesos reprocesos;
	
	private BOCatalogos boCatalogo;
	
	/**
	 * Muestra el menu por medio del cual el usuario elegira los parametros para la consulta de reprocesos.
	 * @param req Request
	 * @param res Response.
	 * @return ModelAndView
	 */
	@RequestMapping("consultaReprocesos.do")
	public ModelAndView muestraMenuConsulta(HttpServletRequest req, HttpServletResponse res){
		this.info("Se redirecciona al usuario hacia la pagina " + PAGINA_CONSULTA_REPROCESOS);
		final Map<String, Object> modelo = new HashMap<String, Object>();
		modelo.put("catalogoMes", GeneradorCatalogos.obtenerListaMeses());
		modelo.put("catalogoAnios", GeneradorCatalogos.obtenerListaAnios(5,	0));
		modelo.put(FORMA_CONSULTA_REPROCESOS, new BeanParamsConsultaReproceso());
		return new ModelAndView(PAGINA_CONSULTA_REPROCESOS, modelo);
	}
	
	/**
	 * Recibe los parametros elegidos por el usuario para ejecutar la consulta de reprocesos.
	 * @param req Request
	 * @param res Response
	 * @param parametrosConsulta Contiene los parametros elegidos por el usuario para realizar la consulta.
	 * @return ModelAndView
	 * @throws BusinessException Exception
	 */
	@RequestMapping("realizaConsultaReproceso.do")
	public ModelAndView ejecutaConsultaReproceso(HttpServletRequest req, HttpServletResponse res, 
			@ModelAttribute(FORMA_CONSULTA_REPROCESOS) BeanParamsConsultaReproceso parametrosConsulta) throws BusinessException{
		this.info("Realizando la busqueda de productos que el usuario puede consultar");
		List<BeanProducto> listaProductos = boCatalogo.obtenerProductosUsuario(getArchitechBean(), getArchitechBean().getUsuario(), "EDC");
		parametrosConsulta.setProductos(listaProductos);
		this.info("Se realizara la peticion de consulta de reprocesos con los siguientes parametros: " + parametrosConsulta.toString());
		final List<BeanRegistroReproceso> listaReprocesos = reprocesos.ejecutaConsultaReprocesos(parametrosConsulta, 
				this.getArchitechBean());
		final Map<String, Object> modelo = new HashMap<String, Object>();
		if(listaReprocesos != null){
			this.info("Cantidad de reprocesos encontrada para la consulta: " + listaReprocesos.size());
			modelo.put("listaReprocesos", listaReprocesos);
		}else{
			this.info("No fueron encontrados reprocesos para la consulta realizada.");
			modelo.put("noHayDatos", true);
		}
		this.info("El usuario es redireccionado hacia la pagina: " + PAGINA_REPORTE_REPROCESOS);
		return new ModelAndView(PAGINA_REPORTE_REPROCESOS, modelo);
	}
	
	/**
	 * Muestra el formulario de consulta de previos.
	 * @param modelo Modelo Spring MVC
	 * @return ModelAndView
	 */
	@RequestMapping("initConsultaPrevios.do")
	public ModelAndView muestraFormularioPrevios(Map<String, Object> modelo) throws BusinessException {
		this.info("Se muestra al usuario el formulario de consulta de Previos.");
		List<BeanProducto> listaProductos = boCatalogo.obtenerProductosUsuario(getArchitechBean(), 
				getArchitechBean().getUsuario(), "EDC");
		modelo.put("productosList", listaProductos);
		modelo.put("mesesList", GeneradorCatalogos.obtenerListaMeses());
		modelo.put("aniosList", GeneradorCatalogos.obtenerListaAnios(5, 0));
		return new ModelAndView("formularioConsultaPrevios", modelo);
	}
	
	/**
	 * Realiza la peticion para realizar la consulta de previos.
	 * @param request Request
	 * @param response Response
	 * @param modelo Modelo Spring MVC
	 * @return ModelAndView
	 * @throws BusinessException Exception
	 */
	@RequestMapping("consultaPrevios.do")
	public ModelAndView llamaConsultaPrevios(HttpServletRequest request, HttpServletResponse response, 
			Map<String, Object> modelo) throws BusinessException {
		this.info("Se realiza la peticion para la consulta de Previos.");
		final BeanParamsConsultaPrevios filtros = new BeanParamsConsultaPrevios();
		filtros.setNumeroCuenta(request.getParameter("numeroCuenta"));
		filtros.setProducto(request.getParameter("aplicativo"));
		filtros.setMes(request.getParameter("mes"));
		filtros.setAnio(request.getParameter("anio"));
		this.info("La consulta de previos se realiza con los siguientes filtros: " + filtros.toString());
		List<BeanPrevioEdc> listaPrevios = reprocesos.ejecutaConsultaPrevios(filtros, this.getArchitechBean());
		if(listaPrevios.size() > 0){
			this.info("La consulta de previos arrojo el siguiente numero de coincidencias: " + listaPrevios.size());
			request.getSession().setAttribute("listaPrevios", listaPrevios);
			modelo.put("listaPrevios", listaPrevios);
			return new ModelAndView("consultaPrevios", modelo);
		}else {
			this.info("La consulta de previos no arrojo coincidencias.");
			modelo.put("noCoincidencias", true);
			return muestraFormularioPrevios(modelo);
		}
	}
	
	@RequestMapping("descargaPrevio.do")
	public void descargaPrevio(HttpServletRequest request, HttpServletResponse response) {
		this.info("Se recibe la peticion para la descarga de previos.");
		@SuppressWarnings("unchecked")
		final List<BeanPrevioEdc> listaPrevios = (List<BeanPrevioEdc>)request.getSession().getAttribute("listaPrevios");
		final int indicePrevio = Integer.parseInt(request.getParameter("indice"));
		final BeanPrevioEdc previoDescarga = listaPrevios.get(indicePrevio);
		this.info("El previo a descargar se muestra a continuacion: " + previoDescarga.getRutaPrevio());
		
	}
	
	/**
	 * Muestra el formulario de consulta de cancelaciones.
	 * @param modelo Modelo Spring MVC
	 * @return ModelAndView
	 */
	@RequestMapping("initConsultaCancelaciones.do")
	public ModelAndView muestraFormularioCancelaciones(Map<String, Object> modelo){
		this.info("Se muestra al usuario el formulario para la consulta de cancelaciones.");
		modelo.put("listaMeses", GeneradorCatalogos.obtenerListaMeses(3));
		return new ModelAndView("formularioCancelaciones", modelo);
	}
	
	/**
	 * Realiza la consulta de cancelaciones y muestra los resultados.
	 * @param mes Parametro utilizado como filtro para la consulta de cancelaciones.
	 * @param modelo Modelo Spring MVC
	 * @return ModelAndView
	 * @throws BusinessException Exception
	 */
	@RequestMapping("consultaCancelaciones.do")
	public ModelAndView llamaConsultaCancelaciones(HttpServletRequest request, @RequestParam("mes")String mes, Map<String, Object> modelo) 
			throws BusinessException, ParseException{
		this.info("Se ejecutara la consulta de cancelaciones para el siguiente mes: " + mes);
		List<BeanCancelacion> listaCancelaciones = reprocesos.ejecutaConsultaCancelaciones(mes, this.getArchitechBean());
		this.info("Se encontro el siguiente numero de coincidencias: " + listaCancelaciones.size());
		if(listaCancelaciones.size() > 0){
			SimpleDateFormat sdf = new SimpleDateFormat();
			sdf.applyPattern("yyyyMM");
			Date fecha = sdf.parse(mes);
			sdf = new SimpleDateFormat("MMMM yyyy", new Locale("es-MX"));
			final String cadenaFecha = sdf.format(fecha);
			modelo.put("fecha", cadenaFecha);
			modelo.put("listaCancelaciones", listaCancelaciones);
			return new ModelAndView("consultaCancelaciones", modelo);
		}else{
			modelo.put("sinResultados", true);
			return muestraFormularioCancelaciones(modelo);
		}
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
	 * Obtiene la referencia del campo reprocesos
	 * @return BOReprocesos
	 */
	public BOReprocesos getReprocesos() {
		return reprocesos;
	}

	/**
	 * Establece la referencia del campo reprocesos.
	 * @param reprocesos Referencia que sera colocada en el campo reprocesos.
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
