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
import mx.isban.cifrascontrol.beans.administracion.modulo.BeanModulo;
import mx.isban.cifrascontrol.servicio.administracion.modulo.BOModulo;

@Controller
public class ControllerModulos extends Architech {


	private static final long serialVersionUID = 1L;
	
	private BOModulo boModulo;
	
	@RequestMapping("consultarModulos.do")
	public ModelAndView consultarModulos(HttpServletRequest request, HttpServletResponse response)throws Exception{
		this.info("Iniciando el formulario de detalle de modulos...");
		final List<BeanModulo> modulos = boModulo.obtenerTodosModulos(getArchitechBean());
		final Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("todosModulos", modulos);
		this.info("Finaliza la ejecucion del metodo consultarModulos");
		return new ModelAndView("consultarModulos",parametros);
	}

	@RequestMapping("modificarModuloInit.do")
	public ModelAndView modificarModuloInit(HttpServletRequest request, HttpServletResponse response)throws Exception{
		this.info("Iniciando el formulario de detalle de modulo...");
		final String idModulo = request.getParameter("idModulo");
		this.info("El id a buscar es:"+idModulo);
		final BeanModulo modulo = boModulo.obtenerModuloPorId(getArchitechBean(), idModulo);
		final Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("modulo", modulo);
		return new ModelAndView("modificarModulo",parametros);
	}
	
	
	@RequestMapping("modificarModulo.do")
	public ModelAndView modificarModulo(HttpServletRequest request, HttpServletResponse response)throws Exception{
		this.info("Iniciando el formulario de modificacion del modulo...");
		BeanModulo modulo = new BeanModulo();
		modulo.setIdModulo(request.getParameter("idModulo"));
		modulo.setNombreModulo(request.getParameter("nombreModulo"));
		modulo.setDescripcionModulo(request.getParameter("descripcionModulo"));
		boModulo.modificarModulo(getArchitechBean(), modulo);
		return this.consultarModulos(request, response);
	}
	
	@RequestMapping("altaModulo.do")
	public ModelAndView altaModulo(HttpServletRequest request, HttpServletResponse response)throws Exception{
		this.info("Iniciando el formulario de modificacion del modulo...");
		BeanModulo modulo = new BeanModulo();
		modulo.setNombreModulo(request.getParameter("nombreModulo"));
		modulo.setDescripcionModulo(request.getParameter("descripcionModulo"));
		boModulo.altaModulo(getArchitechBean(), modulo);
		return this.consultarModulos(request, response);
	}
	
	@RequestMapping("altaModuloInit.do")
	public ModelAndView altaModuloInit(HttpServletRequest request, HttpServletResponse response)throws Exception{
		this.info("Iniciando el formulario de modificacion del modulo...");
		return new ModelAndView("altaModulo");
	}

	
	public BOModulo getBoModulo() {
		return boModulo;
	}

	public void setBoModulo(BOModulo boModulo) {
		this.boModulo = boModulo;
	}
	
	

}
