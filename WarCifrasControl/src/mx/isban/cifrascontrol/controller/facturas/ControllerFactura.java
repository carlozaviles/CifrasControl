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

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import mx.isban.agave.commons.architech.Architech;
import mx.isban.agave.commons.exception.BusinessException;
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
	 * 
	 */
	private static final String[] ANIOS = {"2015","2016","2017","2018","2019","2020","2012"};
	
	private Map<String, String> meses;
	
	/**
	 * Propiedad que contiene un objeto de negocio de tipo {@link BOFactura}
	 */
	private BOFactura boFactura;
	
	public ControllerFactura() {
		meses = new HashMap<String, String>();
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
		final List<BeanProducto> productos = boFactura.obtenerProductos(getArchitechBean());
		parametros.put("productosList", productos);
		parametros.put("mesesList", meses);
		final List<String> aniosList = Arrays.asList(ANIOS);
		parametros.put("anioList", aniosList);
		this.info("Metodo de inicializacion del formulario de facturas inicializado con exito, direccionando a formularioFacturas.jsp");
		return new ModelAndView("formularioFacturas",parametros);
	}

	@RequestMapping("consultaFacturas.do")
	public ModelAndView consultaFacturas(HttpServletRequest request, HttpServletResponse response)throws BusinessException{
		this.info("Iniciando la consulta de facturas");
		final String aplicativo = request.getParameter("aplicativo");
		final String mes = request.getParameter("mes");
		final String anio = request.getParameter("anio");
		final StringBuilder periodo = new StringBuilder(anio);
		periodo.append("-").append(mes);
		
		this.info("Metodo de inicializacion del formulario de facturas inicializado con exito, direccionando a formularioFacturas.jsp");
		return new ModelAndView("formularioFacturas");
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
	
	
}
