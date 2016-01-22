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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.isban.agave.commons.architech.Architech;
import mx.isban.agave.commons.exception.BusinessException;
import mx.isban.cifrascontrol.beans.facturas.BeanReporteFacturas;
import mx.isban.cifrascontrol.beans.producto.BeanProducto;
import mx.isban.cifrascontrol.servicio.catalogos.BOCatalogos;
import mx.isban.cifrascontrol.servicio.facturas.BOFactura;
import mx.isban.cifrascontrol.utileria.general.GeneradorCatalogos;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Clase ControllerFactura
 *
 * <P>
 * Clase encargada de definir los metodos que permitan las siguientes consultas:
 * <ul>
 * <li>Facturas</li>
 * <li>Notas de credito</li>
 * <li>Divisas</li>
 * <li>Recibos deducibles</li>
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
	 * Propiedad que contiene un objeto de negocio de tipo {@link BOFactura}
	 */
	private BOFactura boFactura;
	/**
	 * Objeto de la capa de negocio utilizado para acceder a los catalogos de productos.
	 */
	private BOCatalogos boCatalogo;

	/**
	 * Metodo encargado de inicializar el formulario de consulta de facturas
	 *
	 * @param request
	 *            Un objeto de tipo {@link HttpServletRequest}
	 * @param response
	 *            Un objeto de tipo {@link HttpServletResponse}
	 * @return Un objeto de tipo {@link ModelAndView} que direcciona a la vista
	 *         formularioFacturas.jsp
	 * @throws BusinessException
	 *             En caso de presentarse un error al momento de consultar los
	 *             diferentes productos
	 */
	@RequestMapping("facturasInit.do")
	public ModelAndView facturasInit(final HttpServletRequest request,
			final HttpServletResponse response) throws BusinessException {
		this.info("Iniciando el formulario de facturas");
		final Map<String, Object> parametros = new HashMap<String, Object>();
		this.info("Se inicia la consulta de productos....");
		final List<BeanProducto> productos = boCatalogo
				.obtenerProductosUsuario(getArchitechBean(), getArchitechBean()
						.getUsuario(), "FACT");
		this.info("El total de productos obtenidos es:" + productos.size());
		parametros.put("productosList", productos);
		parametros.put("mesesList", GeneradorCatalogos.obtenerListaMeses());
		parametros.put("anioList", GeneradorCatalogos.obtenerListaAnios(5, 0));
		this.info("Metodo de inicializacion del formulario de facturas inicializado con exito, direccionando a formularioFacturas.jsp");
		return new ModelAndView("formularioFacturas", parametros);
	}

	/**
	 * Metodo encargado de inicializar el formulario de consulta de las notas de
	 * credito
	 *
	 * @param request
	 *            Un objeto de tipo {@link HttpServletRequest}
	 * @param response
	 *            Un objeto de tipo {@link HttpServletResponse}
	 * @return Un objeto de tipo {@link ModelAndView} que direcciona a la vista
	 *         formularioNotasCredito.jsp
	 * @throws BusinessException
	 *             En caso de presentarse un error al momento de consultar los
	 *             diferentes productos
	 */
	@RequestMapping("notasCreditoInit.do")
	public ModelAndView notasCreditoInit(final HttpServletRequest request,
			final HttpServletResponse response) throws BusinessException {
		this.info("Iniciando el formulario de facturas");
		final Map<String, Object> parametros = new HashMap<String, Object>();
		this.info("Se inicia la consulta de productos....");
		final List<BeanProducto> productos = boCatalogo
				.obtenerProductosUsuario(getArchitechBean(), getArchitechBean()
						.getUsuario(), "FACT");
		this.info("El total de productos obtenidos es:" + productos.size());
		parametros.put("productosList", productos);
		parametros.put("mesesList", GeneradorCatalogos.obtenerListaMeses());
		parametros.put("anioList", GeneradorCatalogos.obtenerListaAnios(5, 0));
		this.info("Metodo de inicializacion del formulario de facturas inicializado con exito, direccionando a formularioNotasCredito.jsp");
		return new ModelAndView("formularioNotasCredito", parametros);
	}

	/**
	 * Metodo encargado de inicializar el formulario de consulta de divisas
	 *
	 * @param request
	 *            Un objeto de tipo {@link HttpServletRequest}
	 * @param response
	 *            Un objeto de tipo {@link HttpServletResponse}
	 * @return Un objeto de tipo {@link ModelAndView} que direcciona a la vista
	 *         formularioDivisas.jsp
	 * @throws BusinessException
	 *             En caso de presentarse un error al momento de consultar los
	 *             diferentes productos
	 */
	@RequestMapping("divisasInit.do")
	public ModelAndView divisasInit(final HttpServletRequest request,
			final HttpServletResponse response) throws BusinessException {
		this.info("Iniciando el formulario de facturas");
		final Map<String, Object> parametros = new HashMap<String, Object>();
		this.info("Se inicia la consulta de productos....");
		final List<BeanProducto> productos = boCatalogo
				.obtenerProductosUsuario(getArchitechBean(), getArchitechBean()
						.getUsuario(), "FACT");
		this.info("El total de productos obtenidos es:" + productos.size());
		parametros.put("productosList", productos);
		parametros.put("mesesList", GeneradorCatalogos.obtenerListaMeses());
		parametros.put("anioList", GeneradorCatalogos.obtenerListaAnios(5, 0));
		this.info("Metodo de inicializacion del formulario de divisas inicializado con exito, direccionando a formularioDivisas.jsp");
		return new ModelAndView("formularioDivisas", parametros);
	}

	/**
	 * Metodo encargado de inicializar el formulario de consulta de recibos
	 *
	 * @param request
	 *            Un objeto de tipo {@link HttpServletRequest}
	 * @param response
	 *            Un objeto de tipo {@link HttpServletResponse}
	 * @return Un objeto de tipo {@link ModelAndView} que direcciona a la vista
	 *         formularioRecibos.jsp
	 * @throws BusinessException
	 *             En caso de presentarse un error al momento de consultar los
	 *             diferentes productos
	 */
	@RequestMapping("recibosInit.do")
	public ModelAndView recibosInit(final HttpServletRequest request,
			final HttpServletResponse response) throws BusinessException {
		this.info("Iniciando el formulario de recibos");
		final Map<String, Object> parametros = new HashMap<String, Object>();
		this.info("Se inicia la consulta de productos....");
		final List<BeanProducto> productos = boCatalogo
				.obtenerProductosUsuario(getArchitechBean(), getArchitechBean()
						.getUsuario(), "FACT");
		this.info("El total de productos obtenidos es:" + productos.size());
		parametros.put("productosList", productos);
		parametros.put("mesesList", GeneradorCatalogos.obtenerListaMeses());
		parametros.put("anioList", GeneradorCatalogos.obtenerListaAnios(5, 0));
		this.info("Metodo de inicializacion del formulario de recibos inicializado con exito, direccionando a formularioRecibos.jsp");
		return new ModelAndView("formularioRecibos", parametros);
	}

	/**
	 * Metodo encargado de realizar la consulta de las facturas disponibles, en
	 * relacion a un tipo de aplicativo (FACT) y periodo
	 *
	 * @param request
	 *            Un objeto de tipo {@link HttpServletRequest}
	 * @param response
	 *            Un objeto de tipo {@link HttpServletResponse}
	 * @return Un objeto de tipo {@link ModelAndView} que direcciona a la vista
	 *         consultaFacturas.jsp, en caso de no presentarse un resultado,
	 *         direcciona a la vista formularioFacturas.jsp
	 * @throws BusinessException
	 *             En caso de presentarse un error al momento de consultar las
	 *             facturas
	 */
	@RequestMapping("consultaFacturas.do")
	public ModelAndView consultaFacturas(final HttpServletRequest request,
			final HttpServletResponse response) throws BusinessException {
		this.info("Iniciando la consulta de facturas");
		String aplicativo = request.getParameter("aplicativo");
		StringBuilder periodo = new StringBuilder(request.getParameter("anio"));
		periodo.append("-").append(request.getParameter("mes"));
		List<BeanReporteFacturas> totalReporte = new ArrayList<BeanReporteFacturas>();
		List<BeanReporteFacturas> infoReporteFacturas = new ArrayList<BeanReporteFacturas>();
		List<BeanReporteFacturas> infoReporteExtra = new ArrayList<BeanReporteFacturas>();
		if ("CONFIRMING Y FACTORAJE".equalsIgnoreCase(aplicativo)) {
			infoReporteFacturas = boFactura.obtenerReporteFacturas(CONFIRMING, periodo.toString(), 
					FACT, this.getArchitechBean());
			infoReporteExtra = boFactura.obtenerReporteFacturas(FACTORAJE, periodo.toString(), 
					FACT, this.getArchitechBean());
		} else {
			infoReporteFacturas = boFactura.obtenerReporteFacturas(aplicativo, periodo.toString(), 
					FACT, this.getArchitechBean());
		}
		if (!infoReporteFacturas.isEmpty() || !infoReporteExtra.isEmpty()) {
			this.info("Consulta de facturas realizada correctamente, realizando el calculo de los totales a mostrar");
			final Map<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("reporteFacturas", infoReporteFacturas);
			periodo = new StringBuilder(GeneradorCatalogos.obtenerListaMeses()
					.get(request.getParameter("mes")));
			periodo.append("-").append(request.getParameter("anio"));
			parametros.put("periodo", periodo.toString());
			if (!infoReporteExtra.isEmpty()) {
				parametros.put("reporteExtra", infoReporteExtra);
			}
			totalReporte.addAll(infoReporteFacturas);
			totalReporte.addAll(infoReporteExtra);
			parametros.put("reporteExport", totalReporte);
			parametros.put("aplicativo", aplicativo);
			parametros.put("mes", request.getParameter("mes"));
			this.info("Metodo de consulta de facturas inicializado con exito, direccionando a la vista consultaFacturas");
			return new ModelAndView("consultaFacturas", parametros);
		} else {
			this.info("No se encontraron resultados, regresando a la pagina de consulta...");
			ModelAndView mav = this.facturasInit(request, response);
			mav.addObject("sinDatos", "sinDatos");
			return mav;
		}
	}
	/**
	 * Metodo encargado de exportar a Excel el resultado de la consulta de facturas disponibles, en
	 * relacion a un tipo de aplicativo (FACT) y periodo
	 * @param request
	 *            Un objeto de tipo {@link HttpServletRequest}
	 * @param response
	 *            Un objeto de tipo {@link HttpServletResponse}
	 * @return Un objeto de tipo {@link ModelAndView} que direcciona a la vista
	 *         consultaFacturas.jsp, en caso de no presentarse un resultado,
	 *         direcciona a la vista formularioFacturas.jsp
	 * @throws BusinessException
	 *             En caso de presentarse un error al momento de consultar las
	 *             facturas
	 */
	@RequestMapping("consultaFacturasExcel.xls")
	public ModelAndView consultaFacturasExcel(final HttpServletRequest request,
			final HttpServletResponse response) throws BusinessException {
		this.info("Iniciando la consulta de facturas excel");
		String aplicativo =request.getParameter("aplicativo");
		StringBuilder periodo = new StringBuilder(request.getParameter("anio"));
		periodo.append("-").append(request.getParameter("mes"));
		List<BeanReporteFacturas> totalReporte = new ArrayList<BeanReporteFacturas>();
		List<BeanReporteFacturas> infoReporteFacturas = new ArrayList<BeanReporteFacturas>();
		List<BeanReporteFacturas> infoReporteExtra = new ArrayList<BeanReporteFacturas>();
		
		if ("CONFIRMING Y FACTORAJE".equalsIgnoreCase(aplicativo)) {
			infoReporteFacturas = boFactura.obtenerReporteFacturas(CONFIRMING, periodo.toString(), 
					FACT, this.getArchitechBean());
			infoReporteExtra = boFactura.obtenerReporteFacturas(FACTORAJE, periodo.toString(), 
					FACT, this.getArchitechBean());
		} else {
			infoReporteFacturas = boFactura.obtenerReporteFacturas(aplicativo, periodo.toString(), 
					FACT, this.getArchitechBean());
		}
		if (!infoReporteFacturas.isEmpty() || !infoReporteExtra.isEmpty()) {
			this.info("Consulta de facturas realizada correctamente, realizando el calculo de los totales a mostrar");
			final Map<String, Object> parametros = new HashMap<String, Object>();
			
			JRDataSource datasourceFacturas = null;
			if(!infoReporteFacturas.isEmpty())
			{
				datasourceFacturas=new JRBeanCollectionDataSource(infoReporteFacturas);
			}
			if(!infoReporteExtra.isEmpty())
			{
				datasourceFacturas=new JRBeanCollectionDataSource(infoReporteExtra);
			}			
			   parametros.put("dataSourceFacturas", datasourceFacturas);
			   parametros.put("aplicativo", aplicativo);
			   parametros.put("periodo", request.getParameter("mesText")+"-"+request.getParameter("anio"));
		       return new ModelAndView("xlsFacturas", parametros);
		} else {
			
			this.info("No se encontraron resultados, regresando a la pagina de consulta...");
			ModelAndView mav = this.facturasInit(request, response);
			mav.addObject("sinDatos", "sinDatos");
			return mav;
		}
	}

	/**
	 * Metodo encargado de realizar la consulta de las facturas disponibles, en
	 * relacion a un tipo de aplicativo (NOTA) y periodo
	 *
	 * @param request
	 *            Un objeto de tipo {@link HttpServletRequest}
	 * @param response
	 *            Un objeto de tipo {@link HttpServletResponse}
	 * @return Un objeto de tipo {@link ModelAndView} que direcciona a la vista
	 *         consultaNotas.jsp, en caso de no presentarse un resultado,
	 *         direcciona a la vista formularioNotasCredito.jsp
	 * @throws BusinessException
	 *             En caso de presentarse un error al momento de consultar las
	 *             notas de credito
	 */
	@RequestMapping("consultaNotasCredito.do")
	public ModelAndView consultaNotasCredito(final HttpServletRequest request,
			final HttpServletResponse response) throws BusinessException {
		this.info("Iniciando la consulta de notas de credito");
		String aplicativo = request.getParameter("aplicativo");
		StringBuilder periodo = new StringBuilder(request.getParameter("anio"));
		periodo.append("-").append(request.getParameter("mes"));
		List<BeanReporteFacturas> reporteNotas = new ArrayList<BeanReporteFacturas>();
		List<BeanReporteFacturas> reporteNotasExtra = new ArrayList<BeanReporteFacturas>();
		List<BeanReporteFacturas> totalReporte = new ArrayList<BeanReporteFacturas>();
		
		// Caso especial: en caso de ser confirming y factura, se realiza la
		// busqueda por separado
		if ("Confirming y Factoraje".equals(aplicativo)) {
			reporteNotas = boFactura.obtenerReporteFacturas(CONFIRMING, periodo.toString(), NOTA, 
					this.getArchitechBean());
			reporteNotasExtra = boFactura.obtenerReporteFacturas(FACTORAJE, periodo.toString(), NOTA, 
					this.getArchitechBean());
		} else {
			reporteNotas = boFactura.obtenerReporteFacturas(aplicativo, periodo.toString(), NOTA, 
					this.getArchitechBean());
		}
		if (!reporteNotas.isEmpty() || !reporteNotasExtra.isEmpty()) {
			this.info("Consulta de facturas realizada correctamente, realizando el calculo de los totales a mostrar");
			final Map<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("reporteNotas", reporteNotas);
			periodo = new StringBuilder(GeneradorCatalogos.obtenerListaMeses()
					.get(request.getParameter("mes")));
			periodo.append("-").append(request.getParameter("anio"));
			parametros.put("periodo", periodo.toString());
			if (!reporteNotasExtra.isEmpty()) {
				parametros.put("reporteNotasExtra", reporteNotasExtra);
			}
			totalReporte.addAll(reporteNotas);
			totalReporte.addAll(reporteNotasExtra);
			parametros.put("reporteNotasExport", totalReporte);
			parametros.put("aplicativo", aplicativo);
			parametros.put("mes", request.getParameter("mes"));
			this.info("Metodo de consulta de facturas inicializado con exito, direccionando a la vista consultaNotasCredito");
			return new ModelAndView("consultaNotasCredito", parametros);
		} else {
			this.info("No se encontraron resultados, regresando a la pagina de consulta...");
			ModelAndView mav = this.notasCreditoInit(request, response);
			mav.addObject("sinDatos", "sinDatos");
			return mav;
		}
	}

	
	
	
	@RequestMapping("consultaNotasCreditoExcel.xls")
	public ModelAndView consultaNotasCreditoExcel(final HttpServletRequest request,
			final HttpServletResponse response) throws BusinessException {
		this.info("Iniciando la consulta de notas de credito");
		String aplicativo = request.getParameter("aplicativo");
		StringBuilder periodo = new StringBuilder(request.getParameter("anio"));
		periodo.append("-").append(request.getParameter("mes"));
		List<BeanReporteFacturas> reporteNotas = new ArrayList<BeanReporteFacturas>();
		List<BeanReporteFacturas> reporteNotasExtra = new ArrayList<BeanReporteFacturas>();
		List<BeanReporteFacturas> totalReporte = new ArrayList<BeanReporteFacturas>();
		
		// Caso especial: en caso de ser confirming y factura, se realiza la
		// busqueda por separado
		if ("Confirming y Factoraje".equals(aplicativo)) {
			reporteNotas = boFactura.obtenerReporteFacturas(CONFIRMING, periodo.toString(), NOTA, 
					this.getArchitechBean());
			reporteNotasExtra = boFactura.obtenerReporteFacturas(FACTORAJE, periodo.toString(), NOTA, 
					this.getArchitechBean());
		} else {
			reporteNotas = boFactura.obtenerReporteFacturas(aplicativo, periodo.toString(), NOTA, 
					this.getArchitechBean());
		}
		if (!reporteNotas.isEmpty() || !reporteNotasExtra.isEmpty()) {
			this.info("Consulta de facturas realizada correctamente, realizando el calculo de los totales a mostrar");
			final Map<String, Object> parametros = new HashMap<String, Object>();
			JRDataSource datasourceNotas = null;
			if(!reporteNotas.isEmpty())
			{
				datasourceNotas=new JRBeanCollectionDataSource(reporteNotas);
			}
			if(!reporteNotasExtra.isEmpty())
			{
				datasourceNotas=new JRBeanCollectionDataSource(reporteNotasExtra);
			}			
			   parametros.put("dataSourceNotas", datasourceNotas);
			   parametros.put("aplicativo", aplicativo);
			   parametros.put("periodo", request.getParameter("mesText")+"-"+request.getParameter("anio"));
			return new ModelAndView("xlsNotas", parametros);
		} else {
			this.info("No se encontraron resultados, regresando a la pagina de consulta...");
			ModelAndView mav = this.notasCreditoInit(request, response);
			mav.addObject("sinDatos", "sinDatos");
			return mav;
		}
	}
	
	/**
	 * Metodo encargado de realizar la consulta de las facturas disponibles, en
	 * relacion a un tipo de aplicativo (DIVI) y periodo
	 *
	 * @param request
	 *            Un objeto de tipo {@link HttpServletRequest}
	 * @param response
	 *            Un objeto de tipo {@link HttpServletResponse}
	 * @return Un objeto de tipo {@link ModelAndView} que direcciona a la vista
	 *         consultaDivisas.jsp, en caso de no presentarse un resultado,
	 *         direcciona a la vista formularioFacturas.jsp
	 * @throws BusinessException
	 *             En caso de presentarse un error al momento de consultar las
	 *             divisas
	 */
	@RequestMapping("consultaDivisas.do")
	public ModelAndView consultaDivisas(final HttpServletRequest request,
			final HttpServletResponse response) throws BusinessException {
		this.info("Iniciando la consulta de divisas");
		String aplicativo = request.getParameter("aplicativo");
		StringBuilder periodo = new StringBuilder(request.getParameter("anio"));
		periodo.append("-").append(request.getParameter("mes"));
		
		List<BeanReporteFacturas> reporteDivisas = new ArrayList<BeanReporteFacturas>();
		List<BeanReporteFacturas> reporteDivisasExtra = new ArrayList<BeanReporteFacturas>();
		List<BeanReporteFacturas> reporteDivisasExport = new ArrayList<BeanReporteFacturas>();
		// Caso especial: en caso de ser confirming y factura, se realiza la
		// busqueda por separado
		if ("Confirming y Factoraje".equals(aplicativo)) {
			reporteDivisas = boFactura.obtenerReporteFacturas(CONFIRMING, periodo.toString(), DIVI, 
					this.getArchitechBean());
			reporteDivisasExtra = boFactura.obtenerReporteFacturas(FACTORAJE, periodo.toString(), DIVI, 
					this.getArchitechBean());
		} else {
			reporteDivisas = boFactura.obtenerReporteFacturas(aplicativo, periodo.toString(), DIVI, 
					this.getArchitechBean());
		}
		if (!reporteDivisas.isEmpty() || !reporteDivisasExtra.isEmpty()) {
			this.info("Consulta de facturas realizada correctamente, realizando el calculo de los totales a mostrar");
			final Map<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("reporteDivisas", reporteDivisas);
			periodo = new StringBuilder(GeneradorCatalogos.obtenerListaMeses()
					.get(request.getParameter("mes")));
			periodo.append("-").append(request.getParameter("anio"));
			parametros.put("periodo", periodo.toString());
			if (!reporteDivisasExtra.isEmpty()) {
				parametros.put("reporteDivisasExtra", reporteDivisasExtra);
			}
			reporteDivisasExport.addAll(reporteDivisas);
			reporteDivisasExport.addAll(reporteDivisasExtra);
			parametros.put("reporteDivisasExport", reporteDivisasExport);
			parametros.put("aplicativo", aplicativo);
			parametros.put("mes", request.getParameter("mes"));
			this.info("Metodo de consulta de facturas inicializado con exito, direccionando a la vista consultaNotasCredito");
			return new ModelAndView("consultaDivisas", parametros);
		} else {
			this.info("No se encontraron resultados, regresando a la pagina de consulta...");
			ModelAndView mav = this.divisasInit(request, response);
			mav.addObject("sinDatos", "sinDatos");
			return mav;
		}
	}
	
	
	@RequestMapping("consultaDivisasExcel.xls")
	public ModelAndView consultaDivisasExcel(final HttpServletRequest request,
			final HttpServletResponse response) throws BusinessException {
		this.info("Iniciando la consulta de divisas");
		String aplicativo = request.getParameter("aplicativo");
		StringBuilder periodo = new StringBuilder(request.getParameter("anio"));
		periodo.append("-").append(request.getParameter("mes"));
		
		List<BeanReporteFacturas> reporteDivisas = new ArrayList<BeanReporteFacturas>();
		List<BeanReporteFacturas> reporteDivisasExtra = new ArrayList<BeanReporteFacturas>();
		List<BeanReporteFacturas> reporteDivisasExport = new ArrayList<BeanReporteFacturas>();
		// Caso especial: en caso de ser confirming y factura, se realiza la
		// busqueda por separado
		if ("Confirming y Factoraje".equals(aplicativo)) {
			reporteDivisas = boFactura.obtenerReporteFacturas(CONFIRMING, periodo.toString(), DIVI, 
					this.getArchitechBean());
			reporteDivisasExtra = boFactura.obtenerReporteFacturas(FACTORAJE, periodo.toString(), DIVI, 
					this.getArchitechBean());
		} else {
			reporteDivisas = boFactura.obtenerReporteFacturas(aplicativo, periodo.toString(), DIVI, 
					this.getArchitechBean());
		}
		if (!reporteDivisas.isEmpty() || !reporteDivisasExtra.isEmpty()) {
			this.info("Consulta de facturas realizada correctamente, realizando el calculo de los totales a mostrar");
			final Map<String, Object> parametros = new HashMap<String, Object>();
			JRDataSource dataSourceDivisas = null;
			if(!reporteDivisas.isEmpty())
			{
				dataSourceDivisas=new JRBeanCollectionDataSource(reporteDivisas);
			}
			if(!reporteDivisasExtra.isEmpty())
			{
				dataSourceDivisas=new JRBeanCollectionDataSource(reporteDivisasExtra);
			}			
			   parametros.put("dataSourceDivisas", dataSourceDivisas);
			   parametros.put("aplicativo", aplicativo);
			   parametros.put("periodo", request.getParameter("mesText")+"-"+request.getParameter("anio"));
			return new ModelAndView("xlsDivisas", parametros);
		} else {
			this.info("No se encontraron resultados, regresando a la pagina de consulta...");
			ModelAndView mav = this.divisasInit(request, response);
			mav.addObject("sinDatos", "sinDatos");
			return mav;
		}
	}


	/**
	 * Metodo encargado de realizar la consulta de las facturas disponibles, en
	 * relacion a un tipo de aplicativo(RECI) y periodo
	 *
	 * @param request
	 *            Un objeto de tipo {@link HttpServletRequest}
	 * @param response
	 *            Un objeto de tipo {@link HttpServletResponse}
	 * @return Un objeto de tipo {@link ModelAndView} que direcciona a la vista
	 *         consultaFacturas.jsp, en caso de no presentarse un resultado,
	 *         direcciona a la vista formularioFacturas.jsp
	 * @throws BusinessException
	 *             En caso de presentarse un error al momento de consultar los
	 *             recibos
	 */
	@RequestMapping("consultaRecibosExcel.xls")
	public ModelAndView consultaRecibosExcel(final HttpServletRequest request,
			final HttpServletResponse response) throws BusinessException {
		this.info("Iniciando la consulta de recibos");
		String aplicativo = request.getParameter("aplicativo");
		StringBuilder periodo = new StringBuilder(request.getParameter("anio"));
		periodo.append("-").append(request.getParameter("mes"));
		List<BeanReporteFacturas> reporteRecibos = boFactura.obtenerReporteRecibos(aplicativo, periodo.toString(), 
				RECI, this.getArchitechBean());
		if (!reporteRecibos.isEmpty()) {
			final Map<String, Object> parametros = new HashMap<String, Object>();
			JRDataSource dataSourceRecibos = null;
			if(!reporteRecibos.isEmpty())
			{
				dataSourceRecibos=new JRBeanCollectionDataSource(reporteRecibos);
			}
			/*if(!reporteDivisasExtra.isEmpty())
			{
				dataSourceRecibos=new JRBeanCollectionDataSource(reporteDivisasExtra);
			}			*/
			   parametros.put("dataSourceRecibos", dataSourceRecibos);
			   parametros.put("aplicativo", aplicativo);
			   parametros.put("periodo", request.getParameter("mesText")+"-"+request.getParameter("anio"));
			return new ModelAndView("xlsRecibos", parametros);
		} else {
			this.info("No se encontraron resultados, regresando a la pagina de consulta...");
			ModelAndView mav = this.recibosInit(request, response);
			mav.addObject("sinDatos", "sinDatos");
			return mav;
		}
	}
	
	
	
	@RequestMapping("consultaRecibos.do")
	public ModelAndView consultaRecibos(final HttpServletRequest request,
			final HttpServletResponse response) throws BusinessException {
		this.info("Iniciando la consulta de recibos");
		String aplicativo = request.getParameter("aplicativo");
		StringBuilder periodo = new StringBuilder(request.getParameter("anio"));
		periodo.append("-").append(request.getParameter("mes"));
		List<BeanReporteFacturas> reporteRecibos = boFactura.obtenerReporteRecibos(aplicativo, periodo.toString(), 
				RECI, this.getArchitechBean());
		if (!reporteRecibos.isEmpty()) {
			final Map<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("reporteRecibos", reporteRecibos);
			periodo = new StringBuilder(GeneradorCatalogos.obtenerListaMeses()
					.get(request.getParameter("mes")));
			periodo.append("-").append(request.getParameter("anio"));
			parametros.put("periodo", periodo.toString());
			parametros.put("aplicativo", aplicativo);
			this.info("Metodo de consulta de facturas inicializado con exito, direccionando a la vista consultaRecibos");
			return new ModelAndView("consultaRecibos", parametros);
		} else {
			this.info("No se encontraron resultados, regresando a la pagina de consulta...");
			ModelAndView mav = this.recibosInit(request, response);
			mav.addObject("sinDatos", "sinDatos");
			return mav;
		}
	}

	/**
	 * Metodo encargado de procecesar los errores que se pueden presentar en el
	 * modulo de Facturas
	 *
	 * @param req
	 *            Un objeto de tipo {@link HttpServletRequest}
	 * @param res
	 *            Un objeto de tipo {@link HttpServletResponse}
	 * @param handler
	 *            Un objeto con la excepcion a procesar
	 * @param exception
	 *            Un objeto de tipo {@link Exception}
	 * @return Un objeto de tipo {@link ModelAndView} con la pantalla de manejo
	 *         de errores
	 */
	@ExceptionHandler
	public ModelAndView manejoErrores(final HttpServletRequest req,
			final HttpServletResponse res, final Object handler,
			final Exception exception) {
		this.info("Inicio de ejecucion de metodo de manejo de errores");
		String pagina = null;
		final Map<String, String> modelo = new HashMap<String, String>();
		if (handler instanceof BusinessException) {
			modelo.put("codeError", ((BusinessException) handler).getCode());
			pagina = "../errores/errorAgave.do";
			this.info("Fue cachada una excepcion BuisinessException "
					+ handler.toString());
		} else {
			pagina = "../errores/errorGrl.do";
			this.info("Fue cachada una excepcion " + handler.toString());
		}
		this.info("El modelo enviado al cliente es " + modelo.toString());
		this.info("La pagina de destino es " + pagina);
		return new ModelAndView("redirect:" + pagina, modelo);
	}

	/**
	 * Metodo encargado de obtener un objeto de tipo {@link BOFactura}
	 *
	 * @return Un objeto de tipo {@link BOFactura}
	 */
	public BOFactura getBoFactura() {
		return boFactura;
	}

	/**
	 * Metodo encargado de establecer un objeto de tipo {@link BOFactura}
	 *
	 * @param boFactura
	 *            El objeto de tipo {@link BOFactura} a establecer
	 */
	public void setBoFactura(final BOFactura boFactura) {
		this.boFactura = boFactura;
	}

	/**
	 * @return the boCatalogo
	 */
	public BOCatalogos getBoCatalogo() {
		return boCatalogo;
	}

	/**
	 * @param boCatalogo
	 *            the boCatalogo to set
	 */
	public void setBoCatalogo(final BOCatalogos boCatalogo) {
		this.boCatalogo = boCatalogo;
	}

}
