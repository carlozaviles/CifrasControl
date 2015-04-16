package mx.isban.cifrascontrol.controller.principal;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import mx.isban.agave.commons.architech.Architech;

@Controller
public class ControllerErrores extends Architech{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 960423900408489008L;

	@RequestMapping("errorAgave.do")
	public ModelAndView errorEBE(final HttpServletRequest req, final HttpServletResponse res) throws Exception {
		final String nombreParametro = "codeError";
		Map<String, String> modelo = new HashMap<String, String>();
		modelo.put(nombreParametro, req.getParameter(nombreParametro));
		return new ModelAndView("excepcionInesperadaArq", modelo);
	}
	
	@RequestMapping("errorGrl.do")
	public ModelAndView errorGrl(final HttpServletRequest req, final HttpServletResponse res) throws Exception {
		return new ModelAndView("excepcionInesperadaGrl");
	}
}