package mx.isban.cifrascontrol.controller.prueba;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import mx.isban.agave.commons.architech.Architech;

@Controller
public class ControllerPrueba extends Architech{

	private static final long serialVersionUID = 1L;

	@RequestMapping("prueba.do")
	public ModelAndView consultaMonitorCargas(HttpServletRequest request, HttpServletResponse response){
		this.debug("Iniciando el menu de prueba");
		return new ModelAndView("prueba");
	}
	
	
}
