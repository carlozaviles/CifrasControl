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
import mx.isban.cifrascontrol.beans.administracion.pantalla.BeanPantalla;
import mx.isban.cifrascontrol.servicio.administracion.grupo.BOGrupo;
import mx.isban.cifrascontrol.servicio.administracion.pantalla.BOPantalla;

/**
* Clase ControllerPerfiles
* 
* <P>Clase encargada rebibir y procesar todas las peticiones relacionadas a la administracion de perfiles.
* (Consulta, Alta y Modificacion)
*  
* @author Everis
* @version 1.0
* @see www.everis.com/mexico
* 
*/
@Controller
public class ControllerPerfiles extends Architech {

	private static final long serialVersionUID = 1L;
	
	private BOGrupo boGrupo;
	
	private BOPantalla boPantalla;

	/**
	 * Metodo encargado de consultar los perfiles disponibles y regresar a la vista los datos obtenidos
	 * @param request Un objeto de tipo {@link HttpServletRequest}
	 * @param response Un objeto de tipo {@link HttpServletResponse}
	 * @return Un objeto de tipo {@link ModelAndView} que direcciona a la vista consultarPerfiles.jsp
	 */
	@RequestMapping("consultarGrupos.do")
	public ModelAndView consultarPerfiles(HttpServletRequest request, HttpServletResponse response)throws BusinessException{
		this.info("Iniciando la busqueda de perfiles...");
		//Peticiones a base de datos para obtener los datos de los perfiles
		Map<String, Object> parametros = new HashMap<String, Object>();
		List<BeanGrupo> listaPerfiles  = boGrupo.buscarTodosGrupos(getArchitechBean());
		parametros.put("numeroElementos", listaPerfiles.size());
		parametros.put("registros", listaPerfiles);
		this.info("Direccionando a la vista: consultaPerfiles");
		return new ModelAndView("consultarPerfiles",parametros);
	}
	
	/**
	 * Metodo encargado de inicializar el formulario para dar de alta un nuevo perfil
	 * @param request Un objeto de tipo {@link HttpServletRequest}
	 * @param response Un objeto de tipo {@link HttpServletResponse}
	 * @return Un objeto de tipo {@link ModelAndView} que direcciona a la vista altaPerfil.jsp
	 */
	@RequestMapping("altaPerfilInit.do")
	public ModelAndView altaPerfilInit(HttpServletRequest request, HttpServletResponse response)throws BusinessException{
		this.info("Iniciando el formulario de alta de perfiles...");
		List<BeanPantalla> pantallas = boPantalla.buscarTodasPantallas(getArchitechBean());
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("todasPantallas", pantallas);
		return new ModelAndView("altaPerfiles",parametros);
	}
	
	@RequestMapping("altaPerfil.do")
	public ModelAndView altaPerfil(HttpServletRequest request, HttpServletResponse response)throws BusinessException{
		this.info("Iniciando el formulario de alta de perfiles...");
		BeanGrupo grupo = new BeanGrupo();
		grupo.setNombrePerfil(request.getParameter("nombreGrupo"));
		grupo.setDescripcionPerfil(request.getParameter("descripcion"));
		String[] pantallasChecadas = request.getParameterValues("pantallaActiva");
		List<BeanPantalla> listaPantallas = new ArrayList<BeanPantalla>();
		for (int i = 0; i < pantallasChecadas.length; i++) {
			BeanPantalla pantalla = new BeanPantalla();
			pantalla.setIdPantalla(pantallasChecadas[i]);
			listaPantallas.add(pantalla);
			this.info("idPantalla:"+pantallasChecadas[i]);
		}
		grupo.setPantallas(listaPantallas);
		boGrupo.agregarGrupo(grupo, getArchitechBean());
		Map<String, Object> parametros = new HashMap<String, Object>();
		List<BeanGrupo> listaPerfiles  = boGrupo.buscarTodosGrupos(getArchitechBean());
		parametros.put("numeroElementos", listaPerfiles.size());
		parametros.put("registros", listaPerfiles);
		this.info("Direccionando a la vista: consultaPerfiles");
		return new ModelAndView("consultarPerfiles",parametros);
	}
	
	@RequestMapping("borrarGrupo.do")
	public ModelAndView borrarGrupo(HttpServletRequest request, HttpServletResponse response)throws BusinessException{
		this.info("Iniciando el borrado del grupo...");
		String idGrupo = request.getParameter("idGrupoBorrar");
		boGrupo.borrarGrupo(idGrupo, getArchitechBean());
		Map<String, Object> parametros = new HashMap<String, Object>();
		List<BeanGrupo> listaPerfiles  = boGrupo.buscarTodosGrupos(getArchitechBean());
		parametros.put("numeroElementos", listaPerfiles.size());
		parametros.put("registros", listaPerfiles);
		this.info("Direccionando a la vista: consultaPerfiles");
		return new ModelAndView("consultarPerfiles",parametros);
	}
	
	/**
	 * Metodo encargado de inicializar y establecer los datos en el formulario de modificacion de perfiles.
	 * @param request Un objeto de tipo {@link HttpServletRequest}
	 * @param response Un objeto de tipo {@link HttpServletResponse}
	 * @return Un objeto de tipo {@link ModelAndView} que direcciona a la vista modificaPerfil.jsp
	 * @throws BusinessException Excepcion que se presenta si ocurre un error al momento de consultar el grupo
	 */
	@RequestMapping("modificarPerfil.do")
	public ModelAndView modificarPerfil(HttpServletRequest request, HttpServletResponse response) throws BusinessException{
		this.info("Iniciando el metodo para modificar el perfil");
		BeanGrupo grupo = new BeanGrupo();
		grupo.setIdPerfil(request.getParameter("idGrupo"));
		grupo.setNombrePerfil(request.getParameter("nombreGrupo"));
		grupo.setDescripcionPerfil(request.getParameter("descripcion"));
		String[] pantallasChecadas = request.getParameterValues("pantallaActiva");
		List<BeanPantalla> listaPantallas = new ArrayList<BeanPantalla>();
		for (int i = 0; i < pantallasChecadas.length; i++) {
			BeanPantalla pantalla = new BeanPantalla();
			pantalla.setIdPantalla(pantallasChecadas[i]);
			listaPantallas.add(pantalla);
			this.info("idPantalla:"+pantallasChecadas[i]);
		}
		grupo.setPantallas(listaPantallas);
		boGrupo.modificarGrupo(grupo, getArchitechBean());
		Map<String, Object> parametros = new HashMap<String, Object>();
		List<BeanGrupo> listaPerfiles  = boGrupo.buscarTodosGrupos(getArchitechBean());
		parametros.put("numeroElementos", listaPerfiles.size());
		parametros.put("registros", listaPerfiles);
		this.info("Direccionando a la vista: consultaPerfiles");
		return new ModelAndView("consultarPerfiles",parametros);
	}
	
	@RequestMapping("modificarPerfilInit.do")
	public ModelAndView modificarPerfilInit(HttpServletRequest request, HttpServletResponse response) throws BusinessException{
		this.info("Iniciando el formulario de detalle de perfiles...");
		String idGrupo = request.getParameter("radio");
		this.info("El id a buscar es:"+idGrupo);
		BeanGrupo grupo = boGrupo.consultarGrupo(idGrupo, getArchitechBean());
		List<BeanPantalla> pantallas = grupo.getPantallas();
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("grupo", grupo);
		parametros.put("todasPantallas", pantallas);
		return new ModelAndView("modificarPerfil",parametros);
	}

	public BOGrupo getBoGrupo() {
		return boGrupo;
	}

	public void setBoGrupo(BOGrupo boPerfiles) {
		this.boGrupo = boPerfiles;
	}

	public BOPantalla getBoPantalla() {
		return boPantalla;
	}

	public void setBoPantalla(BOPantalla boPantalla) {
		this.boPantalla = boPantalla;
	}
	
}
