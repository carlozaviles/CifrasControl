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

import java.util.ArrayList;
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
import mx.isban.cifrascontrol.beans.administracion.grupo.BeanGrupo;
import mx.isban.cifrascontrol.beans.administracion.usuario.BeanUsuario;
import mx.isban.cifrascontrol.servicio.administracion.grupo.BOGrupo;
import mx.isban.cifrascontrol.servicio.administracion.usuario.BOUsuario;

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
	
	private BOUsuario boUsuario;
	private BOGrupo boGrupo;
	
	/**
	 * Metodo encargado de consultar los usuarios disponibles y regresar a la vista los datos obtenidos
	 * @param request Un objeto de tipo {@link HttpServletRequest}
	 * @param response Un objeto de tipo {@link HttpServletResponse}
	 * @return Un objeto de tipo {@link ModelAndView} que direcciona a la vista consultarUsuarios.jsp
	 */
	@RequestMapping("consultarUsuarios.do")
	public ModelAndView consultarUsuarios(HttpServletRequest request, HttpServletResponse response)throws BusinessException{
		this.info("Iniciando el formulario de consulta de usuarios...");
		//Peticiones a base de datos para obtener los datos de los perfiles
		Map<String, Object> parametros = new HashMap<String, Object>();
		List<BeanUsuario> listaUsuarios  = boUsuario.obtenerTodosUsuarios(getArchitechBean());
		parametros.put("registros", listaUsuarios);
		this.info("Direccionando a la vista: consultaUsuarios");
		return new ModelAndView("consultarUsuarios",parametros);
	}
	
	/**
	 * Metodo encargado de inicializar el formulario de alta de usuarios
	 * @param request Un objeto de tipo {@link HttpServletRequest}
	 * @param response Un objeto de tipo {@link HttpServletResponse}
	 * @return Un objeto de tipo {@link ModelAndView} que direcciona a la vista altaUsuarioInit.jsp
	 */
	@RequestMapping("altaUsuarioInit.do")
	public ModelAndView altaUsuarioInit(HttpServletRequest request, HttpServletResponse response)throws BusinessException{
		this.info("Iniciando el formulario de consulta de usuarios...");
		List<BeanGrupo> listaGrupos = boGrupo.buscarTodosGrupos(getArchitechBean());
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("todosGrupos", listaGrupos);
		this.info("Direccionando a la vista: altaUsuario");
		return new ModelAndView("altaUsuario",parametros);
	}
	
	@RequestMapping("altaUsuario.do")
	public ModelAndView altaUsuario(HttpServletRequest request, HttpServletResponse response)throws BusinessException{
		this.info("Iniciando el alta de un usuario");
		BeanUsuario usuario = new BeanUsuario();
		usuario.setIdUsuario(request.getParameter("idUsuario"));
		String usuarioActivo = request.getParameter("usuarioActivo");
		if(null != usuarioActivo && "activo".equals(usuarioActivo)){
			usuario.setEstatus(true);
		}
		String[] gruposSeleccionados = request.getParameterValues("idGrupo");
		List<BeanGrupo> listaGrupos = new ArrayList<BeanGrupo>();
		for (int i = 0; i < gruposSeleccionados.length; i++) {
			BeanGrupo grupo = new BeanGrupo();
			grupo.setIdGrupo(gruposSeleccionados[i]);
			listaGrupos.add(grupo);
			this.info("idGrupo:"+gruposSeleccionados[i]);
		}
		usuario.setGrupos(listaGrupos);
		boUsuario.altaUsuario(getArchitechBean(), usuario);
		this.info("Termina la ejecucion del metodo de alta de usuario");
		return this.consultarUsuarios(request, response);
	}
	
	
	/**
	 * Metodo encargado de inicializar el formulario de modificacion de usuario
	 * @param request Un objeto de tipo {@link HttpServletRequest}
	 * @param response Un objeto de tipo {@link HttpServletResponse}
	 * @return Un objeto de tipo {@link ModelAndView} que direcciona a la vista modificarUsuario.jsp
	 */
	@RequestMapping("modificarUsuarioInit.do")
	public ModelAndView modificarUsuarioInit(HttpServletRequest request, HttpServletResponse response)throws BusinessException{
		this.info("Iniciando el formulario de consulta de usuarios...");
		String idUsuario = request.getParameter("idUsuario");
		this.info("El id de usuario es:"+idUsuario);
		BeanUsuario usuario = boUsuario.obtenerUsuarioPorID(getArchitechBean(), idUsuario);
		List<BeanGrupo> grupos = usuario.getGrupos();
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("usuario", usuario);
		parametros.put("todosGrupos", grupos);
		return new ModelAndView("modificarUsuario",parametros);
	}
	
	@RequestMapping("modificarUsuario.do")
	public ModelAndView modificarUsuario(HttpServletRequest request, HttpServletResponse response)throws BusinessException{
		this.info("Iniciando el formulario de modificacion de usuarios");
		BeanUsuario usuario = new BeanUsuario();
		usuario.setIdUsuario(request.getParameter("idUsuario"));
		String usuarioActivo = request.getParameter("usuarioActivo");
		if(null != usuarioActivo && "activo".equals(usuarioActivo)){
			usuario.setEstatus(true);
		}
		String[] gruposSeleccionados = request.getParameterValues("idGrupo");
		List<BeanGrupo> listaGrupos = new ArrayList<BeanGrupo>();
		for (int i = 0; i < gruposSeleccionados.length; i++) {
			BeanGrupo grupo = new BeanGrupo();
			grupo.setIdGrupo(gruposSeleccionados[i]);
			listaGrupos.add(grupo);
			this.info("idGrupo:"+gruposSeleccionados[i]);
		}
		usuario.setGrupos(listaGrupos);
		boUsuario.modificarUsuario(getArchitechBean(), usuario);
		this.info("Termina la ejecucion del metodo de actualizacion");
		return this.consultarUsuarios(request, response);
	}

	public BOUsuario getBoUsuario() {
		return boUsuario;
	}

	public void setBoUsuario(BOUsuario boUsuario) {
		this.boUsuario = boUsuario;
	}

	public BOGrupo getBoGrupo() {
		return boGrupo;
	}

	public void setBoGrupo(BOGrupo boGrupo) {
		this.boGrupo = boGrupo;
	}
	
	
}
