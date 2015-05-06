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
import mx.isban.cifrascontrol.beans.administracion.grupo.BeanGrupo;

/**
* Clase ControllerUsuarios
* 
* <P>Clase encargada rebibir y procesar todas las peticiones relacionadas a la administracion de usarios.
* (Consulta, Alta y Modificacion)
*  
* @author Everis
* @version 1.0
* @see www.everis.com/mexico
* 
*/
@Controller
public class ControllerUsuarios extends Architech {

	private static final long serialVersionUID = 1L;

	/**
	 * Metodo encargado de consultar los usuarios disponibles y regresar a la vista los datos obtenidos
	 * @param request Un objeto de tipo {@link HttpServletRequest}
	 * @param response Un objeto de tipo {@link HttpServletResponse}
	 * @return Un objeto de tipo {@link ModelAndView} que direcciona a la vista consultarUsuarios.jsp
	 */
	@RequestMapping("consultarUsuarios.do")
	public ModelAndView consultarUsuarios(HttpServletRequest request, HttpServletResponse response){
		this.info("Iniciando el formulario de consulta de usuarios...");
		//Peticiones a base de datos para obtener los datos de los perfiles
		Map<String, Object> parametros = new HashMap<String, Object>();
		List<BeanGrupo> listaPerfiles  = boGrupo.buscarTodosGrupos(getArchitechBean());
		parametros.put("numeroElementos", listaPerfiles.size());
		parametros.put("registros", listaPerfiles);
		this.info("Direccionando a la vista: consultaPerfiles");
		return new ModelAndView("consultarUsuarios");
	}
	
	/**
	 * Metodo encargado de inicializar el formulario de alta de usuarios
	 * @param request Un objeto de tipo {@link HttpServletRequest}
	 * @param response Un objeto de tipo {@link HttpServletResponse}
	 * @return Un objeto de tipo {@link ModelAndView} que direcciona a la vista altaUsuarioInit.jsp
	 */
	@RequestMapping("altaUsuarioInit.do")
	public ModelAndView altaUsuarioInit(HttpServletRequest request, HttpServletResponse response){
		this.info("Iniciando el formulario de consulta de usuarios...");
		//Peticiones a base de datos para obtener los datos de los perfiles
		return new ModelAndView("altaUsuario");
	}
	
	@RequestMapping("altaUsuario.do")
	public ModelAndView altaUsuario(HttpServletRequest request, HttpServletResponse response){
		this.info("Iniciando el formulario de consulta de usuarios...");
		//Peticiones a base de datos para obtener los datos de los perfiles
		return new ModelAndView("altaUsuario");
	}
	
	@RequestMapping("bajaUsuario.do")
	public ModelAndView bajaUsuario(HttpServletRequest request, HttpServletResponse response){
		this.info("Iniciando el formulario de consulta de usuarios...");
		//Peticiones a base de datos para obtener los datos de los perfiles
		return new ModelAndView("altaUsuario");
	}
	
	/**
	 * Metodo encargado de inicializar el formulario de modificacion de usuario
	 * @param request Un objeto de tipo {@link HttpServletRequest}
	 * @param response Un objeto de tipo {@link HttpServletResponse}
	 * @return Un objeto de tipo {@link ModelAndView} que direcciona a la vista modificarUsuario.jsp
	 */
	@RequestMapping("modificarUsuarioInit.do")
	public ModelAndView modificarUsuarioInit(HttpServletRequest request, HttpServletResponse response){
		this.info("Iniciando el formulario de consulta de usuarios...");
		//Peticiones a base de datos para obtener los datos de los perfiles
		return new ModelAndView("modificarUsuario");
	}
}
