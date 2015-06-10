package mx.isban.cifrascontrol.controller.cifrascontrol;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import mx.isban.agave.commons.architech.Architech;
import mx.isban.agave.commons.exception.BusinessException;
import mx.isban.cifrascontrol.beans.general.BeanProducto;

public class ControllerCifrasControl extends Architech {

	/**
	 * Numero de la clase serializada
	 */
	private static final long serialVersionUID = 7336548460682095091L;

	
	/**
	 * Arreglo de años (Falta saber el rango de años)
	 */
	private static final String[] ANIOS = {"2010","2015","2016","2017","2018","2019","2020","2021"};
	
	/**
	 * Propiedad que contiene los meses (Para seleccionar el periodo)
	 */
	private Map<String, String> meses;
	
	public ControllerCifrasControl() {
		meses = new LinkedHashMap<String, String>();
		meses.put("01","Enero");
		meses.put("02","Febrero");
		meses.put("03","Marzo");
		meses.put("04","Abril");
		meses.put("05","Mayo");
		meses.put("06","Junio");
		meses.put("07","Julio");
		meses.put("08","Agosto");
		meses.put("09","Septiembre");
		meses.put("10","Octubre");
		meses.put("11","Noviembre");
		meses.put("12","Diciembre");
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
		final List<BeanProducto> productos = null;// = boFactura.obtenerProductos(getArchitechBean());
		this.info("El total de productos obtenidos es:"+productos.size());
		parametros.put("productosList", productos);
		parametros.put("mesesList", meses);
		final List<String> aniosList = Arrays.asList(ANIOS);
		parametros.put("anioList", aniosList);
		this.info("Metodo de inicializacion del formulario de facturas inicializado con exito, direccionando a formularioFacturas.jsp");
		return new ModelAndView("formularioFacturas",parametros);
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
	
	
}
