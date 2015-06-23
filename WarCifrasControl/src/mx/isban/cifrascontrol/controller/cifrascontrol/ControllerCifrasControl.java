package mx.isban.cifrascontrol.controller.cifrascontrol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import mx.isban.agave.commons.architech.Architech;
import mx.isban.agave.commons.exception.BusinessException;
import mx.isban.cifrascontrol.beans.cifrascontrol.BeanCifrasControl;
import mx.isban.cifrascontrol.beans.cifrascontrol.BeanDetalleCifrasControl;
import mx.isban.cifrascontrol.beans.producto.BeanProducto;
import mx.isban.cifrascontrol.servicio.catalogos.BOCatalogos;
import mx.isban.cifrascontrol.servicio.cifrascontrol.BOCifrasControl;
import mx.isban.cifrascontrol.utileria.general.GeneradorCatalogos;

@Controller
public class ControllerCifrasControl extends Architech {

	/**
	 * Numero de la clase serializada
	 */
	private static final long serialVersionUID = 7336548460682095091L;

	/**
	 * Constante que contiene el tipo de cifras de control "ORIGEN"
	 */
	private static final String ORIGEN = "ORIGEN";
	
	/**
	 * Constante que contiene el tipo de cifras de control "CFD"
	 */
	private static final String CFD = "CFD";
	
	/**
	 * Constante que contiene el tipo de cifras de control "EDC"
	 */
	private static final String EDC = "EDC";
	
	/**
	 * Constante que contiene el tipo de cifras de control "SAT"
	 */
	private static final String SAT = "SAT";
	
	/**
	 * Objeto de negocio de tipo {@link BOCifrasControl}
	 */
	private BOCifrasControl boCifrasControl;
	
	private BOCatalogos boCatalogo;
	
	/**
	 * Lista que contiene el detalle de las cifras de control
	 */
	private List<BeanDetalleCifrasControl> detalleCifrasControl;

	public ControllerCifrasControl() {
		setDetalleCifrasControl(new ArrayList<BeanDetalleCifrasControl>());
	}
	
	
	/**
	 * Metodo encargado de inicializar el formulario de consulta de cifras de control
	 * 
	 * @param request Un objeto de tipo {@link HttpServletRequest}
	 * @param response Un objeto de tipo {@link HttpServletResponse}
	 * @return Un objeto de tipo {@link ModelAndView} que direcciona a la vista formularioCifrasControl.jsp
	 * @throws BusinessException En caso de presentarse un error al momento de consultar
	 * los diferentes productos
	 */
	@RequestMapping("cifrasInit.do")
	public ModelAndView cifrasInit(HttpServletRequest request, HttpServletResponse response)throws BusinessException{
		this.info("Iniciando el formulario de Cifras de control");
		final Map<String, Object> parametros = new HashMap<String, Object>();
		this.info("Se inicia la consulta de productos....");
		final List<BeanProducto> productos = boCatalogo.obtenerProductosUsuario(getArchitechBean(), getArchitechBean().getUsuario(), "FACT");
		this.info("El total de productos obtenidos es:"+productos.size());
		parametros.put("productosList", productos);
		parametros.put("mesesList", GeneradorCatalogos.obtenerListaMeses());
		parametros.put("anioList", GeneradorCatalogos.obtenerListaAnios(5,	0));
		this.info("Metodo de inicializacion del formulario de facturas inicializado con exito, direccionando a formularioCifras.jsp");
		return new ModelAndView("formularioCifrasControl",parametros);
	}
	
	/**
	 * Metodo encargado de inicializar el formulario de consulta de cifras de control
	 * 
	 * @param request Un objeto de tipo {@link HttpServletRequest}
	 * @param response Un objeto de tipo {@link HttpServletResponse}
	 * @return Un objeto de tipo {@link ModelAndView} que direcciona a la vista formularioCifrasControl.jsp
	 * @throws BusinessException En caso de presentarse un error al momento de consultar
	 * los diferentes productos
	 */
	@RequestMapping("consultaCifras.do")
	public ModelAndView consultaCifras(HttpServletRequest request, HttpServletResponse response)throws BusinessException{
		this.info("Iniciando la consulta de las Cifras de control");
		final Map<String, Object> parametros = new HashMap<String, Object>();
		final String aplicativo = request.getParameter("aplicativo");
		final StringBuilder periodo = new StringBuilder(request.getParameter("anio"));
		periodo.append("-").append(request.getParameter("mes"));
		final int registros = boCifrasControl.consultarCifrasControl(aplicativo, periodo.toString(),getArchitechBean());
		if(registros > 0){
			this.detalleCifrasControl = boCifrasControl.obtenerDetalleCifrasControl(aplicativo, periodo.toString(), getArchitechBean());
			final String aplicativoText = request.getParameter("productoText");
			final String periodoText = request.getParameter("periodoText");
			final int tamanioDetalle = detalleCifrasControl.size();
			final List<BeanCifrasControl> listaCifrasOrigen = boCifrasControl.obtenerCifrasPorAplicativo(ORIGEN, getArchitechBean());
			final List<BeanCifrasControl> listaCifrasInterfase = boCifrasControl.obtenerCifrasPorAplicativo(CFD, getArchitechBean());
			final List<BeanCifrasControl> listaCifrasControlSat = boCifrasControl.obtenerCifrasPorAplicativo(SAT, getArchitechBean());
			final List<BeanCifrasControl> listaCifrasControlEdc = boCifrasControl.obtenerCifrasPorAplicativo(EDC, getArchitechBean());
			final List<BeanCifrasControl> listaTotal = new ArrayList<BeanCifrasControl>();
			listaTotal.addAll(listaCifrasOrigen);
			listaTotal.addAll(listaCifrasInterfase);
			listaTotal.addAll(listaCifrasControlSat);
			listaTotal.addAll(listaCifrasControlEdc);
			parametros.put("origenList", listaCifrasOrigen);
			parametros.put("interfaseList", listaCifrasInterfase);
			parametros.put("satList", listaCifrasControlSat);
			parametros.put("edcList", listaCifrasControlEdc);
			parametros.put("listaExportar", listaTotal);
			parametros.put("tamanioDetalle", tamanioDetalle);
			parametros.put("aplicativo", aplicativoText);
			parametros.put("periodo", periodoText);
			this.info("Metodo de consulta de cifras inicializado con exito, direccionando a la vista consultaCifras");
			return new ModelAndView("consultaCifras",parametros);
		}else{
			this.info("No se encontraron resultados, regresando a la pagina de consulta...");
			 final ModelAndView mav = this.cifrasInit(request, response);
			 mav.addObject("sinDatos", "sinDatos");
			 return mav;
		}
	}
	
	/**
	 * Metodo encargado de inicializar el formulario de consulta de cifras de control
	 * 
	 * @param request Un objeto de tipo {@link HttpServletRequest}
	 * @param response Un objeto de tipo {@link HttpServletResponse}
	 * @return Un objeto de tipo {@link ModelAndView} que direcciona a la vista formularioCifrasControl.jsp
	 * @throws BusinessException En caso de presentarse un error al momento de consultar
	 * los diferentes productos
	 */
	@RequestMapping("consultaDetalleCifras.do")
	public ModelAndView consultaDetalleCifras(HttpServletRequest request, HttpServletResponse response)throws BusinessException{
		this.info("Iniciando la consulta del detalle de las Cifras de control");
		final Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("listaDetalle", detalleCifrasControl);
		final String aplicativoText = request.getParameter("aplicativo");
		final String periodoText = request.getParameter("periodo");
		parametros.put("aplicativo", aplicativoText);
		parametros.put("periodo", periodoText);
		this.info("Metodo de consulta de detalle de cifras inicializado con exito, direccionando a la vista consultaDetalle");
		return new ModelAndView("detalleCifras",parametros);
	}
	
	
	/**
	 * Metodo encargado de procecesar los errores que se pueden presentar en el modulo de cifrascontrol
	 * @param req Un objeto de tipo {@link HttpServletRequest}
	 * @param res Un objeto de tipo {@link HttpServletResponse}
	 * @param handler Un objeto con la excepcion a procesar
	 * @param exception Un objeto de tipo {@link Exception}
	 * @return Un objeto de tipo {@link ModelAndView} con la pantalla de manejo de errores
	 */
	@ExceptionHandler
	public ModelAndView manejoErrores(HttpServletRequest req, HttpServletResponse res, Object handler, Exception exception){
		this.info("Inicio de ejecucion de metodo de manejo de errores");
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
	 * @return the boCifrasControl
	 */
	public BOCifrasControl getBoCifrasControl() {
		return boCifrasControl;
	}


	/**
	 * @param boCifrasControl the boCifrasControl to set
	 */
	public void setBoCifrasControl(BOCifrasControl boCifrasControl) {
		this.boCifrasControl = boCifrasControl;
	}
	
	

	/**
	 * @return the detalleCifrasControl
	 */
	public List<BeanDetalleCifrasControl> getDetalleCifrasControl() {
		return detalleCifrasControl;
	}


	/**
	 * @param detalleCifrasControl the detalleCifrasControl to set
	 */
	public void setDetalleCifrasControl(List<BeanDetalleCifrasControl> detalleCifrasControl) {
		this.detalleCifrasControl = detalleCifrasControl;
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
