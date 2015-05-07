/**************************************************************
* Queretaro, Qro Abril 2015
*
* La redistribucion y el uso en formas fuente y binario, 
* son responsabilidad del propietario.
* 
* Este software fue elaborado en @Everis
* 
* Para mas informacion, consulte <www.everis.com/mexico>
***************************************************************/
package mx.isban.cifrascontrol.controller.administracion;

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
import mx.isban.cifrascontrol.beans.administracion.pantalla.BeanPantalla;
import mx.isban.cifrascontrol.servicio.administracion.pantalla.BOPantalla;

/**
* Clase ControllerPantallas
* 
* <P>Clase encargada rebibir y procesar todas las peticiones relacionadas a la administracion de pantallas.
* (Consulta, Alta y Modificacion)
*  
* @author Everis
* @version 1.0
* @see www.everis.com/mexico
* 
*/
@Controller
public class ControllerPantallas extends Architech {

	private static final long serialVersionUID = 1L;
	
	private BOPantalla boPantalla;
	
	@RequestMapping("consultarPantallas.do")
	public ModelAndView consultarPantallas(HttpServletRequest request, HttpServletResponse response)throws BusinessException{
		this.info("Iniciando el formulario de detalle de pantallas...");
		List<BeanPantalla> pantallas = boPantalla.buscarTodasPantallas(getArchitechBean());
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("todasPantallas", pantallas);
		return new ModelAndView("consultarPantallas",parametros);
	}
	
	@RequestMapping("altaPantallaInit.do")
	public ModelAndView altaPantallaInit(HttpServletRequest request, HttpServletResponse response){
		this.info("Iniciando el formulario de alta de pantalla...");
		//Peticiones a base de datos para obtener los datos de los perfiles
		return new ModelAndView("altaPantalla");
	}
	
	@RequestMapping("altaPantalla.do")
	public ModelAndView altaPantalla(HttpServletRequest request, HttpServletResponse response)throws BusinessException{
		this.info("Iniciando el formulario de alta de pantalla...");
		BeanPantalla pantalla = new BeanPantalla();
		pantalla.setNombrePantalla(request.getParameter("nombrePantalla"));
		pantalla.setDescripcionPantalla(request.getParameter("descripcionPantalla"));
		this.info("Valores:"+pantalla.getNombrePantalla()+pantalla.getDescripcionPantalla());
		boPantalla.agregarPantalla(getArchitechBean(),pantalla);
		List<BeanPantalla> pantallas = boPantalla.buscarTodasPantallas(getArchitechBean());
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("todasPantallas", pantallas);
		return new ModelAndView("consultarPantallas",parametros);
	}
	
	@RequestMapping("modificarPantallaInit.do")
	public ModelAndView modificarPantallaInit(HttpServletRequest request, HttpServletResponse response)throws BusinessException{
		this.info("Iniciando el formulario de detalle de pantalla...");
		String idPantalla = request.getParameter("idPantalla");
		this.info("El id a buscar es:"+idPantalla);
		BeanPantalla pantalla = boPantalla.obtenerPantallaPorId(getArchitechBean(), idPantalla);
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("pantalla", pantalla);
		return new ModelAndView("modificarPantalla",parametros);
	}
	
	@RequestMapping("modificarPantalla.do")
	public ModelAndView modificarPantalla(HttpServletRequest request, HttpServletResponse response)throws BusinessException{
		this.info("Iniciando el metodo para modificar la pantalla");
		BeanPantalla pantalla = new BeanPantalla();
		pantalla.setIdPantalla(request.getParameter("idPantalla"));
		pantalla.setNombrePantalla(request.getParameter("nombrePantalla"));
		pantalla.setDescripcionPantalla(request.getParameter("descripcionPantalla"));
		boPantalla.modificarPantalla(getArchitechBean(), pantalla);
		List<BeanPantalla> pantallas = boPantalla.buscarTodasPantallas(getArchitechBean());
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("todasPantallas", pantallas);
		return new ModelAndView("consultarPantallas",parametros);
	}
	
	@RequestMapping("borrarPantalla.do")
	public ModelAndView borrarPantalla(HttpServletRequest request, HttpServletResponse response)throws BusinessException{
		this.info("Iniciando el metodo para eliminar la pantalla");
		String idPantalla = request.getParameter("idPantallas");
		boPantalla.borrarPantalla(getArchitechBean(),idPantalla);
		List<BeanPantalla> pantallas = boPantalla.buscarTodasPantallas(getArchitechBean());
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("todasPantallas", pantallas);
		return new ModelAndView("consultarPantallas",parametros);
	}

	public BOPantalla getBoPantalla() {
		return boPantalla;
	}

	public void setBoPantalla(BOPantalla boPantalla) {
		this.boPantalla = boPantalla;
	}
	
	
}
