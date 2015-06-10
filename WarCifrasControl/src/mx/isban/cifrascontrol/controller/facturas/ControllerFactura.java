/**************************************************************
* Queretaro, Qro Junio 2015
*
* La redistribucion y el uso en formas fuente y binario, 
* son responsabilidad del propietario.
* 
* Este software fue elaborado en @Everis
* 
* Para mas informacion, consulte <www.everis.com/mexico>
***************************************************************/
package mx.isban.cifrascontrol.controller.facturas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
import mx.isban.cifrascontrol.beans.facturas.BeanFactura;
import mx.isban.cifrascontrol.beans.general.BeanProducto;
import mx.isban.cifrascontrol.servicio.facturas.BOFactura;

/**
* Clase ControllerFactura
* 
* <P>Clase encargada de definir los metodos que permitan las siguientes consultas:
* <ul>
* <li>Facturas</li>
* <li>Notas de credito </li>
* <li>Divisas </li>
* <li>Recibos deducibles </li>
* </ul>
*  
* @author Everis
* @version 1.0
* @see www.everis.com/mexico
* 
*/
@Controller
public class ControllerFactura extends Architech {

	/**
	 * Numero de clase serializada
	 */
	private static final long serialVersionUID = -4973060001712391604L;

	/**
	 * Tipo de factura para localizar las cuentas correctas e incorrectas
	 */
	private static final String CONFIRMING = "CONFIRMING";
	
	/**
	 * Tipo de factura para localizar las cuentas correctas e incorrectas
	 */
	private static final String FACTORAJE = "FACTORAJE";
	
	/**
	 * Tipo de factura para facturas
	 */
	private static final String FACT = "FACT";
	
	/**
	 * Tipo de factura para Notas de credito
	 */
	private static final String NOTA = "NOTA";
	/**
	 * Tipo de factura para divisas
	 */
	private static final String DIVI = "DIVI";
	/**
	 * Tipo de factura para recibos deducibles
	 */
	private static final String RECI = "RECI";
	
	/**
	 * Arreglo de años (Falta saber el rango de años)
	 */
	private static final String[] ANIOS = {"2010","2015","2016","2017","2018","2019","2020","2021"};
	
	/**
	 * Propiedad que contiene los meses (Para seleccionar el periodo)
	 */
	private Map<String, String> meses;
	
	/**
	 * Propiedad que contiene un objeto de negocio de tipo {@link BOFactura}
	 */
	private BOFactura boFactura;
	
	public ControllerFactura() {
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
	 * Metodo encargado de inicializar el formulario de consulta de facturas
	 * 
	 * @param request Un objeto de tipo {@link HttpServletRequest}
	 * @param response Un objeto de tipo {@link HttpServletResponse}
	 * @return Un objeto de tipo {@link ModelAndView} que direcciona a la vista formularioFacturas.jsp
	 * @throws BusinessException En caso de presentarse un error al momento de consultar
	 * los diferentes productos
	 */
	@RequestMapping("facturasInit.do")
	public ModelAndView facturasInit(HttpServletRequest request, HttpServletResponse response)throws BusinessException{
		this.info("Iniciando el formulario de facturas");
		final Map<String, Object> parametros = new HashMap<String, Object>();
		this.info("Se inicia la consulta de productos....");
		final List<BeanProducto> productos = boFactura.obtenerProductos(getArchitechBean());
		this.info("El total de productos obtenidos es:"+productos.size());
		parametros.put("productosList", productos);
		parametros.put("mesesList", meses);
		final List<String> aniosList = Arrays.asList(ANIOS);
		parametros.put("anioList", aniosList);
		this.info("Metodo de inicializacion del formulario de facturas inicializado con exito, direccionando a formularioFacturas.jsp");
		return new ModelAndView("formularioFacturas",parametros);
	}
	
	/**
	 * Metodo encargado de inicializar el formulario de consulta de las notas de credito
	 * 
	 * @param request Un objeto de tipo {@link HttpServletRequest}
	 * @param response Un objeto de tipo {@link HttpServletResponse}
	 * @return Un objeto de tipo {@link ModelAndView} que direcciona a la vista formularioNotasCredito.jsp
	 * @throws BusinessException En caso de presentarse un error al momento de consultar
	 * los diferentes productos
	 */
	@RequestMapping("notasCreditoInit.do")
	public ModelAndView notasCreditoInit(HttpServletRequest request, HttpServletResponse response)throws BusinessException{
		this.info("Iniciando el formulario de facturas");
		final Map<String, Object> parametros = new HashMap<String, Object>();
		this.info("Se inicia la consulta de productos....");
		final List<BeanProducto> productos = boFactura.obtenerProductos(getArchitechBean());
		this.info("El total de productos obtenidos es:"+productos.size());
		parametros.put("productosList", productos);
		parametros.put("mesesList", meses);
		final List<String> aniosList = Arrays.asList(ANIOS);
		parametros.put("anioList", aniosList);
		this.info("Metodo de inicializacion del formulario de facturas inicializado con exito, direccionando a formularioNotasCredito.jsp");
		return new ModelAndView("formularioNotasCredito",parametros);
	}
	
	/**
	 * Metodo encargado de inicializar el formulario de consulta de divisas
	 * 
	 * @param request Un objeto de tipo {@link HttpServletRequest}
	 * @param response Un objeto de tipo {@link HttpServletResponse}
	 * @return Un objeto de tipo {@link ModelAndView} que direcciona a la vista formularioDivisas.jsp
	 * @throws BusinessException En caso de presentarse un error al momento de consultar
	 * los diferentes productos
	 */
	@RequestMapping("divisasInit.do")
	public ModelAndView divisasInit(HttpServletRequest request, HttpServletResponse response)throws BusinessException{
		this.info("Iniciando el formulario de facturas");
		final Map<String, Object> parametros = new HashMap<String, Object>();
		this.info("Se inicia la consulta de productos....");
		final List<BeanProducto> productos = boFactura.obtenerProductos(getArchitechBean());
		this.info("El total de productos obtenidos es:"+productos.size());
		parametros.put("productosList", productos);
		parametros.put("mesesList", meses);
		final List<String> aniosList = Arrays.asList(ANIOS);
		parametros.put("anioList", aniosList);
		this.info("Metodo de inicializacion del formulario de divisas inicializado con exito, direccionando a formularioDivisas.jsp");
		return new ModelAndView("formularioDivisas",parametros);
	}
	
	/**
	 * Metodo encargado de inicializar el formulario de consulta de recibos
	 * 
	 * @param request Un objeto de tipo {@link HttpServletRequest}
	 * @param response Un objeto de tipo {@link HttpServletResponse}
	 * @return Un objeto de tipo {@link ModelAndView} que direcciona a la vista formularioRecibos.jsp
	 * @throws BusinessException En caso de presentarse un error al momento de consultar
	 * los diferentes productos
	 */
	@RequestMapping("recibosInit.do")
	public ModelAndView recibosInit(HttpServletRequest request, HttpServletResponse response)throws BusinessException{
		this.info("Iniciando el formulario de recibos");
		final Map<String, Object> parametros = new HashMap<String, Object>();
		this.info("Se inicia la consulta de productos....");
		final List<BeanProducto> productos = boFactura.obtenerProductos(getArchitechBean());
		this.info("El total de productos obtenidos es:"+productos.size());
		parametros.put("productosList", productos);
		parametros.put("mesesList", meses);
		final List<String> aniosList = Arrays.asList(ANIOS);
		parametros.put("anioList", aniosList);
		this.info("Metodo de inicializacion del formulario de recibos inicializado con exito, direccionando a formularioRecibos.jsp");
		return new ModelAndView("formularioRecibos",parametros);
	}

	/**
	 * Metodo encargado de realizar la consulta de las facturas disponibles, en relacion a un tipo de aplicativo (FACT) y periodo
	 * @param request Un objeto de tipo {@link HttpServletRequest}
	 * @param response Un objeto de tipo {@link HttpServletResponse}
	 * @return Un objeto de tipo {@link ModelAndView} que direcciona a la vista consultaFacturas.jsp, en caso de no presentarse
	 * un resultado, direcciona a la vista formularioFacturas.jsp
	 * @throws BusinessException En caso de presentarse un error al momento de consultar
	 * las facturas
	 */
	@RequestMapping("consultaFacturas.do")
	public ModelAndView consultaFacturas(HttpServletRequest request, HttpServletResponse response)throws BusinessException{
		this.info("Iniciando la consulta de facturas");
		String aplicativo = request.getParameter("aplicativo");
		StringBuilder periodo = new StringBuilder(request.getParameter("anio"));
		periodo.append("-").append(request.getParameter("mes"));
		List<BeanFactura> totalFacturas;
		List<BeanFactura> totalFactoraje = new ArrayList<BeanFactura>();
		//Caso especial: en caso de ser confirming y factura, se realiza la busqueda por separado
		if("ConfiFact".equals(aplicativo)){
			totalFacturas = boFactura.consultarFacturas(CONFIRMING, periodo.toString(), FACT, getArchitechBean());
			totalFactoraje = boFactura.consultarFacturas(FACTORAJE, periodo.toString(), FACT, getArchitechBean());
		}else{
			totalFacturas = boFactura.consultarFacturas(aplicativo, periodo.toString(), FACT, getArchitechBean());
		}
		if(!totalFacturas.isEmpty() || !totalFactoraje.isEmpty()){
			this.info("Consulta de facturas realizada correctamente, realizando el calculo de los totales a mostrar");
			List<BeanFactura> facturasCorrectas = boFactura.obtenerFacturasCorrectas(totalFacturas, getArchitechBean());
			List<BeanFactura> facturasIncorrectas = boFactura.obtenerFacturasIncorrectas(totalFacturas, getArchitechBean());
			List<BeanFactura> facturaList = new ArrayList<BeanFactura>();
			facturaList.addAll(facturasCorrectas);
			facturaList.addAll(facturasIncorrectas);
			final Map<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("facturasCorrectas", facturasCorrectas);
			parametros.put("facturasIncorrectas", facturasIncorrectas);
			String aplicativoBuscado = facturaList.get(0).getAplicativo();
			periodo = new StringBuilder(meses.get(request.getParameter("mes")));
			periodo.append("-").append(request.getParameter("anio"));
			parametros.put("periodo",periodo.toString());
			if(!totalFactoraje.isEmpty()){
				List<BeanFactura> factorajeList = new ArrayList<BeanFactura>();
				List<BeanFactura> factorajeCorrectas = boFactura.obtenerFacturasCorrectas(totalFactoraje, getArchitechBean());
				List<BeanFactura> factorajeIncorrectas = boFactura.obtenerFacturasIncorrectas(totalFactoraje, getArchitechBean());
				factorajeList.addAll(factorajeCorrectas);
				factorajeList.addAll(factorajeIncorrectas);
				facturaList.addAll(factorajeList);
				aplicativoBuscado = "Confirming y Factoraje";
				parametros.put("factoraje", factorajeList);
				parametros.put("factorajeCorrecto", factorajeCorrectas);
				parametros.put("factorajeIncorrecto", factorajeIncorrectas);
			}
			parametros.put("facturaList", facturaList);
			parametros.put("aplicativo", aplicativoBuscado);
			this.info("Metodo de consulta de facturas inicializado con exito, direccionando a la vista consultaFacturas");
			return new ModelAndView("consultaFacturas",parametros);
		}else{
			this.info("No se encontraron resultados, regresando a la pagina de consulta...");
			 ModelAndView mav = this.facturasInit(request, response);
			 mav.addObject("sinDatos", "sinDatos");
			 return mav;
		}
	}
	
	/**
	 * Metodo encargado de realizar la consulta de las facturas disponibles, en relacion a un tipo de aplicativo (NOTA) y periodo
	 * @param request Un objeto de tipo {@link HttpServletRequest}
	 * @param response Un objeto de tipo {@link HttpServletResponse}
	 * @return Un objeto de tipo {@link ModelAndView} que direcciona a la vista consultaNotas.jsp, en caso de no presentarse
	 * un resultado, direcciona a la vista formularioNotasCredito.jsp
	 * @throws BusinessException En caso de presentarse un error al momento de consultar
	 * las notas de credito
	 */
	@RequestMapping("consultaNotasCredito.do")
	public ModelAndView consultaNotasCredito(HttpServletRequest request, HttpServletResponse response)throws BusinessException{
		this.info("Iniciando la consulta de notas de credito");
		String aplicativo = request.getParameter("aplicativo");
		StringBuilder periodo = new StringBuilder(request.getParameter("anio"));
		periodo.append("-").append(request.getParameter("mes"));
		List<BeanFactura> totalFacturas;
		List<BeanFactura> totalFactoraje = new ArrayList<BeanFactura>();
		//Caso especial: en caso de ser confirming y factura, se realiza la busqueda por separado
		if("ConfiFact".equals(aplicativo)){
			totalFacturas = boFactura.consultarFacturas(CONFIRMING, periodo.toString(), NOTA, getArchitechBean());
			totalFactoraje = boFactura.consultarFacturas(FACTORAJE, periodo.toString(), NOTA, getArchitechBean());
		}else{
			totalFacturas = boFactura.consultarFacturas(aplicativo, periodo.toString(), NOTA, getArchitechBean());
		}
		if(!totalFacturas.isEmpty() || !totalFactoraje.isEmpty()){
			this.info("Consulta de facturas realizada correctamente, realizando el calculo de los totales a mostrar");
			List<BeanFactura> facturasCorrectas = boFactura.obtenerFacturasCorrectas(totalFacturas, getArchitechBean());
			List<BeanFactura> facturasIncorrectas = boFactura.obtenerFacturasIncorrectas(totalFacturas, getArchitechBean());
			List<BeanFactura> facturaList = new ArrayList<BeanFactura>();
			facturaList.addAll(facturasCorrectas);
			facturaList.addAll(facturasIncorrectas);
			final Map<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("facturasCorrectas", facturasCorrectas);
			parametros.put("facturasIncorrectas", facturasIncorrectas);
			String aplicativoBuscado = facturaList.get(0).getAplicativo();
			periodo = new StringBuilder(meses.get(request.getParameter("mes")));
			periodo.append("-").append(request.getParameter("anio"));
			parametros.put("periodo",periodo.toString());
			if(!totalFactoraje.isEmpty()){
				List<BeanFactura> factorajeList = new ArrayList<BeanFactura>();
				List<BeanFactura> factorajeCorrectas = boFactura.obtenerFacturasCorrectas(totalFactoraje, getArchitechBean());
				List<BeanFactura> factorajeIncorrectas = boFactura.obtenerFacturasIncorrectas(totalFactoraje, getArchitechBean());
				factorajeList.addAll(factorajeCorrectas);
				factorajeList.addAll(factorajeIncorrectas);
				facturaList.addAll(factorajeList);
				aplicativoBuscado = "Confirming y Factoraje";
				parametros.put("factoraje", factorajeList);
				parametros.put("factorajeCorrecto", factorajeCorrectas);
				parametros.put("factorajeIncorrecto", factorajeIncorrectas);
			}
			parametros.put("facturaList", facturaList);
			parametros.put("aplicativo", aplicativoBuscado);
			this.info("Metodo de consulta de facturas inicializado con exito, direccionando a la vista consultaNotasCredito");
			return new ModelAndView("consultaNotasCredito",parametros);
		}else{
			this.info("No se encontraron resultados, regresando a la pagina de consulta...");
			 ModelAndView mav = this.notasCreditoInit(request, response);
			 mav.addObject("sinDatos", "sinDatos");
			 return mav;
		}
	}
	
	/**
	 * Metodo encargado de realizar la consulta de las facturas disponibles, en relacion a un tipo de aplicativo (DIVI) y periodo
	 * @param request Un objeto de tipo {@link HttpServletRequest}
	 * @param response Un objeto de tipo {@link HttpServletResponse}
	 * @return Un objeto de tipo {@link ModelAndView} que direcciona a la vista consultaDivisas.jsp, en caso de no presentarse
	 * un resultado, direcciona a la vista formularioFacturas.jsp
	 * @throws BusinessException En caso de presentarse un error al momento de consultar
	 * las divisas
	 */
	@RequestMapping("consultaDivisas.do")
	public ModelAndView consultaDivisas(HttpServletRequest request, HttpServletResponse response)throws BusinessException{
		this.info("Iniciando la consulta de divisas");
		String aplicativo = request.getParameter("aplicativo");
		StringBuilder periodo = new StringBuilder(request.getParameter("anio"));
		periodo.append("-").append(request.getParameter("mes"));
		List<BeanFactura> totalFacturas;
		List<BeanFactura> totalFactoraje = new ArrayList<BeanFactura>();
		//Caso especial: en caso de ser confirming y factura, se realiza la busqueda por separado
		if("ConfiFact".equals(aplicativo)){
			totalFacturas = boFactura.consultarFacturas(CONFIRMING, periodo.toString(), DIVI, getArchitechBean());
			totalFactoraje = boFactura.consultarFacturas(FACTORAJE, periodo.toString(), DIVI, getArchitechBean());
		}else{
			totalFacturas = boFactura.consultarFacturas(aplicativo, periodo.toString(), DIVI, getArchitechBean());
		}
		if(!totalFacturas.isEmpty() || !totalFactoraje.isEmpty()){
			this.info("Consulta de facturas realizada correctamente, realizando el calculo de los totales a mostrar");
			List<BeanFactura> facturasCorrectas = boFactura.obtenerFacturasCorrectas(totalFacturas, getArchitechBean());
			List<BeanFactura> facturasIncorrectas = boFactura.obtenerFacturasIncorrectas(totalFacturas, getArchitechBean());
			List<BeanFactura> facturaList = new ArrayList<BeanFactura>();
			facturaList.addAll(facturasCorrectas);
			facturaList.addAll(facturasIncorrectas);
			final Map<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("facturasCorrectas", facturasCorrectas);
			parametros.put("facturasIncorrectas", facturasIncorrectas);
			String aplicativoBuscado = facturaList.get(0).getAplicativo();
			periodo = new StringBuilder(meses.get(request.getParameter("mes")));
			periodo.append("-").append(request.getParameter("anio"));
			parametros.put("periodo",periodo.toString());
			if(!totalFactoraje.isEmpty()){
				List<BeanFactura> factorajeList = new ArrayList<BeanFactura>();
				List<BeanFactura> factorajeCorrectas = boFactura.obtenerFacturasCorrectas(totalFactoraje, getArchitechBean());
				List<BeanFactura> factorajeIncorrectas = boFactura.obtenerFacturasIncorrectas(totalFactoraje, getArchitechBean());
				factorajeList.addAll(factorajeCorrectas);
				factorajeList.addAll(factorajeIncorrectas);
				facturaList.addAll(factorajeList);
				aplicativoBuscado = "Confirming y Factoraje";
				parametros.put("factoraje", factorajeList);
				parametros.put("factorajeCorrecto", factorajeCorrectas);
				parametros.put("factorajeIncorrecto", factorajeIncorrectas);
			}
			parametros.put("facturaList", facturaList);
			parametros.put("aplicativo", aplicativoBuscado);
			this.info("Metodo de consulta de facturas inicializado con exito, direccionando a la vista consultaDivisas");
			return new ModelAndView("consultaDivisas",parametros);
		}else{
			this.info("No se encontraron resultados, regresando a la pagina de consulta...");
			 ModelAndView mav = this.divisasInit(request, response);
			 mav.addObject("sinDatos", "sinDatos");
			 return mav;
		}
	}
	
	/**
	 * Metodo encargado de realizar la consulta de las facturas disponibles, en relacion a un tipo de aplicativo(RECI) y periodo
	 * @param request Un objeto de tipo {@link HttpServletRequest}
	 * @param response Un objeto de tipo {@link HttpServletResponse}
	 * @return Un objeto de tipo {@link ModelAndView} que direcciona a la vista consultaFacturas.jsp, en caso de no presentarse
	 * un resultado, direcciona a la vista formularioFacturas.jsp
	 * @throws BusinessException En caso de presentarse un error al momento de consultar
	 * los recibos
	 */
	@RequestMapping("consultaRecibos.do")
	public ModelAndView consultaRecibos(HttpServletRequest request, HttpServletResponse response)throws BusinessException{
		this.info("Iniciando la consulta de recibos");
		String aplicativo = request.getParameter("aplicativo");
		StringBuilder periodo = new StringBuilder(request.getParameter("anio"));
		periodo.append("-").append(request.getParameter("mes"));
		List<BeanFactura> recibosConsultados = boFactura.consultarFacturas(aplicativo, periodo.toString(), RECI, getArchitechBean());
		if(!recibosConsultados.isEmpty()){
			List<BeanFactura> recibosGenerados = boFactura.obtenerFacturasRecibosGenerados(recibosConsultados, getArchitechBean());
			List<BeanFactura> recibosCancelados = boFactura.obtenerFacturasRecibosCancelados(recibosConsultados, getArchitechBean());
			List<BeanFactura> totalRegistros = new ArrayList<BeanFactura>();
			totalRegistros.addAll(recibosGenerados);
			totalRegistros.addAll(recibosCancelados);
			final Map<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("recibosConsultados", totalRegistros);
			parametros.put("recibosGenerados", recibosGenerados);
			parametros.put("recibosCancelados", recibosCancelados);
			String aplicativoBuscado = recibosConsultados.get(0).getAplicativo();
			periodo = new StringBuilder(meses.get(request.getParameter("mes")));
			periodo.append("-").append(request.getParameter("anio"));
			parametros.put("periodo",periodo.toString());
			parametros.put("aplicativo", aplicativoBuscado);
			this.info("Metodo de consulta de facturas inicializado con exito, direccionando a la vista consultaRecibos");
			return new ModelAndView("consultaRecibos",parametros);
		}else{
			this.info("No se encontraron resultados, regresando a la pagina de consulta...");
			 ModelAndView mav = this.recibosInit(request, response);
			 mav.addObject("sinDatos", "sinDatos");
			 return mav;
		}
	}
	
	/**
	 * Metodo encargado de procecesar los errores que se pueden presentar en el modulo de Facturas
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
	 * Metodo encargado de obtener un objeto de tipo {@link BOFactura}
	 * @return Un objeto de tipo {@link BOFactura} 
	 */
	public BOFactura getBoFactura() {
		return boFactura;
	}

	/**
	 * Metodo encargado de establecer un objeto de tipo {@link BOFactura}
	 * @param boFactura El objeto de tipo {@link BOFactura} a establecer
	 */
	public void setBoFactura(BOFactura boFactura) {
		this.boFactura = boFactura;
	}

	/**
	 * Metodo encargado de obtener los meses
	 * @return los meses
	 */
	public Map<String, String> getMeses() {
		return meses;
	}

	/**
	 * Metodo encargado de establecer los meses
	 * @param meses Meses a establecer
	 */
	public void setMeses(Map<String, String> meses) {
		this.meses = meses;
	}
	
	
	
	
}
